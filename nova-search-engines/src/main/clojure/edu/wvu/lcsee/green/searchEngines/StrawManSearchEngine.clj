(ns edu.wvu.lcsee.green.searchEngines.StrawManSearchEngine
  (:gen-class
      :implements [edu.wvu.lcsee.green.search.SearchEngine]
      :prefix "se-impl-")
  ;(:require )
  ;(:use )
  (:import (com.google.common.collect ImmutableList)
           (edu.wvu.lcsee.green.search.impl StateImpl PathImpl)
           (java.util Date))
  )

(defn se-impl-getKey [this]
  "strawman")

(defn se-impl-search [this scoringFunction initialScenario]
  (let [startTime (new Date)
        states (ImmutableList/of (new StateImpl initialScenario))
        endTime  (new Date)]
    (new PathImpl startTime endTime states)))