(ns edu.wvu.lcsee.green.searchEngines.StrawManSearchEngine
  (:gen-class
      :implements [edu.wvu.lcsee.green.search.SearchEngine]
      :prefix "se-impl-")
  ;(:require )
  (:use edu.wvu.lcsee.green.searchEngines.searchlib)
  )

(defn se-impl-getKey [this]
  "strawman")

(defn se-impl-search [this evaluationFunction initialScenario]
  (statesGenerator2path 
    (com.google.common.collect.ImmutableList/of 
      (createStateFrom evaluationFunction initialScenario))))