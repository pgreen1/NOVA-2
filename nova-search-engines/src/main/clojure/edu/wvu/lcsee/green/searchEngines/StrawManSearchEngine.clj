(ns edu.wvu.lcsee.green.searchEngines.StrawManSearchEngine
  (:gen-class
      :implements [edu.wvu.lcsee.green.search.SearchEngine]
      :prefix "se-impl-")
  ;(:require )
  (:use edu.wvu.lcsee.green.searchEngines.searchlib)
  (:import (com.google.common.collect ImmutableList)
           (edu.wvu.lcsee.green.search.impl PathImpl)
           (java.util Date))
  )

(defn se-impl-getKey [this]
  "strawman")

(defn se-impl-search [this evaluationFunction initialScenario]
  (let [startTime (new Date)
        states (ImmutableList/of (createStateFrom evaluationFunction initialScenario))
        endTime  (new Date)]
    (new PathImpl startTime endTime states)))