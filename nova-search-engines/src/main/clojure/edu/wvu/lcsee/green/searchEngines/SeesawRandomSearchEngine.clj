(ns edu.wvu.lcsee.green.searchEngines.SeesawRandomSearchEngine
  (:gen-class
    :implements [edu.wvu.lcsee.green.search.SearchEngine]
    :prefix "rseesaw-impl-")
  (:use (edu.wvu.lcsee.green.searchEngines searchlib IsampSearchEngine SeesawDeterministicSearchEngine))
  (:import (edu.wvu.lcsee.green.model.impl TreatmentImpl))
  )

(defn rseesaw-impl-getKey [this]
  "seesaw-r")

(defn rseesaw-constrainScenario [evaluationFunction scenario attributesLeftToConstrain]
  (determineBestScenario
    evaluationFunction
    (generateExtremeScenariosFor scenario (rand-nth (seq attributesLeftToConstrain)))))
      
(defn rseesaw-impl-search [this evaluationFunction initialScenario]
  (statesGenerator2path (search evaluationFunction initialScenario rseesaw-constrainScenario 1)))