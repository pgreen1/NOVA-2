
(ns edu.wvu.lcsee.green.clojure.impl.NovaControlFactoryImpl
  (:gen-class
      :implements [edu.wvu.lcsee.green.spi.NovaControlFactory]
      :prefix "ncf-impl-")
  (:require (edu.wvu.lcsee.green.clojure.impl NovaControlImpl ProjectGeneratorImpl))
  ;(:use )
  (:import (com.google.common.collect ImmutableList ImmutableMap)
           (edu.wvu.lcsee.green.spi ScoringFunction))
  )

(defn loadServicesOfType [serviceClass]
  (ImmutableList/copyOf
    (.iterator
      (java.util.ServiceLoader/load serviceClass))))

(defn ncf-impl-newInstance [this]
  (let [scoringFunctionRegistry (ImmutableMap/copyOf
                                  (apply hash-map
                                    (flatten
                                      (map (fn [scoringFunction]
                                            (list
                                              (.getKey scoringFunction)
                                              scoringFunction))
                                           (loadServicesOfType ScoringFunction)))))]
    (new edu.wvu.lcsee.green.clojure.impl.NovaControlImpl 
      (new edu.wvu.lcsee.green.clojure.impl.ProjectGeneratorImpl scoringFunctionRegistry)
      scoringFunctionRegistry)))

