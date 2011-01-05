(ns edu.wvu.lcsee.green.clojure.impl.NovaControlFactoryImpl
 (:gen-class
   :implements [edu.wvu.lcsee.green.control.spi.NovaControlFactory]
   :prefix "ncf-impl-")
 (:require (edu.wvu.lcsee.green.clojure.impl NovaControlImpl ProjectGeneratorImpl))
 ;(:use )
 (:import (com.google.common.collect ImmutableList ImmutableMap)
   (edu.wvu.lcsee.green.model ModelConfiguration ScoringFunction)
   (edu.wvu.lcsee.green.model.spi ModelConfigurationProvider)
   (edu.wvu.lcsee.green.search.spi SearchEnginesProvider))
 )

(defn loadServicesOfType [serviceClass]
  (ImmutableList/copyOf
    (.iterator
      (java.util.ServiceLoader/load serviceClass))))

(defn createRegistryFrom [getKeyFn objList]
  (ImmutableMap/copyOf
    (apply hash-map
      (mapcat (fn [obj]
                (list
                  (getKeyFn obj)
                  obj))
        objList))))

(defn getAllInterfaces [clazz]
  (letfn [(getAllInterfaces-recur [clazzList]
            (if (empty? clazzList) nil
              (set (mapcat (fn [currentClazz]
                             (let [interfaces (seq (.getInterfaces currentClazz))
                                   superClazz (.getSuperclass currentClazz)
                                   next-clazzes (if superClazz (conj interfaces superClazz) interfaces)]
                               (concat interfaces (getAllInterfaces-recur next-clazzes))))
                     clazzList))))]
    (getAllInterfaces-recur (list clazz))))

(defn createModelConfigurationRegistry [modelConfigurationProviders]
  (ImmutableMap/copyOf
    (apply merge-with
      (fn [val-in-result val-in-latter] ;any duplicate should be an exception
        (throw (new IllegalStateException (conj "Multiple ModelConfiguration with same key: " val-in-result "|" val-in-latter  ))))
      (map (fn [modelConfigurationProvider]
             (let [modelConfiguration (.getModelConfiguration modelConfigurationProvider)
                   implementedModelConfigurationInterfaces (filter (fn [interface]
                                                                     (and ;only return subclasses of ModelConfiguration
                                                                       (.isAssignableFrom ModelConfiguration interface)
                                                                       (not (= interface ModelConfiguration))))
                                                             (getAllInterfaces (.getClass modelConfiguration)))]
               (apply hash-map (interleave
                           implementedModelConfigurationInterfaces
                           (cycle (list modelConfiguration)))))) ;trick to create a hashmap with the same value
        modelConfigurationProviders))))


(defn ncf-impl-newInstance [this]
  (let [modelConfigurationRegistry (createModelConfigurationRegistry
                                     (loadServicesOfType ModelConfigurationProvider))
        searchEngineRegistry (createRegistryFrom
                               (memfn getKey)
                               (flatten
                                 (map (comp seq (memfn getSearchEngines))
                                   (loadServicesOfType SearchEnginesProvider))))]
    (new edu.wvu.lcsee.green.clojure.impl.NovaControlImpl
      (new edu.wvu.lcsee.green.clojure.impl.ProjectGeneratorImpl)
      modelConfigurationRegistry
      searchEngineRegistry)))

