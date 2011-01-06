(ns edu.wvu.lcsee.green.searchEngines.KeysRandomSearchEngine
  (:gen-class
    :implements [edu.wvu.lcsee.green.search.SearchEngine]
    :prefix "rkeys-impl-")
  (:use (edu.wvu.lcsee.green.searchEngines searchlib IsampSearchEngine KeysDeterministicSearchEngine))
  (:import (edu.wvu.lcsee.green.model.impl TreatmentImpl))
  )

(defn rkeys-impl-getKey [this]
  "keys-r")

(defn rkeys-constrainScenario [evaluationFunction scenario attributesLeftToConstrain]
  (determineBestScenario
    evaluationFunction
    (generateExtremeScenariosFor scenario (rand-nth (seq attributesLeftToConstrain)))))
      
(defn rkeys-impl-search [this evaluationFunction initialScenario]
  (statesGenerator2path (search evaluationFunction initialScenario rkeys-constrainScenario 1)))