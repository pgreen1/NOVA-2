(ns edu.wvu.lcsee.green.searchEngines.searchlib
  ;(:require )
  ;(:use )
  (:import (edu.wvu.lcsee.green.search.impl StateImpl))
  )

(defn createStateFrom 
  "Creates a state from an evaluationFunction and a scenario"
  [evaluationFunction scenario]
  (new StateImpl
    scenario
    (.evaluate evaluationFunction scenario)))