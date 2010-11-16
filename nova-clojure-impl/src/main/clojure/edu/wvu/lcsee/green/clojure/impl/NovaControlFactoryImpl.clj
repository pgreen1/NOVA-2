(ns edu.wvu.lcsee.green.clojure.impl.NovaControlFactoryImpl
  (:gen-class
      :implements [edu.wvu.lcsee.green.control.spi.NovaControlFactory]
      :prefix "ncf-impl-")
  (:require (edu.wvu.lcsee.green.clojure.impl NovaControlImpl ProjectGeneratorImpl))
  ;(:use )
  (:import (com.google.common.collect ImmutableList ImmutableMap)
           (edu.wvu.lcsee.green.model.spi ScoringFunction)
           (edu.wvu.lcsee.green.search.spi SearchEnginesProvider))
  )

(defn createRegistryFrom [getKeyFn objList]
  (ImmutableMap/copyOf
    (apply hash-map
        (mapcat (fn [obj]
               (list
                 (getKeyFn obj)
                 obj))
          objList))))

(defn loadServicesOfType [serviceClass]
  (ImmutableList/copyOf
    (.iterator
      (java.util.ServiceLoader/load serviceClass))))

(defn ncf-impl-newInstance [this]
  (let [scoringFunctionRegistry (createRegistryFrom
                                   (memfn getKey)
                                   (loadServicesOfType ScoringFunction))
        searchEngineRegistry (createRegistryFrom
                                   (memfn getKey)
                                   (flatten
                                     (map (comp seq (memfn getSearchEngines))
                                          (loadServicesOfType SearchEnginesProvider))))]
    (new edu.wvu.lcsee.green.clojure.impl.NovaControlImpl 
      (new edu.wvu.lcsee.green.clojure.impl.ProjectGeneratorImpl scoringFunctionRegistry)
      scoringFunctionRegistry
      searchEngineRegistry)))

