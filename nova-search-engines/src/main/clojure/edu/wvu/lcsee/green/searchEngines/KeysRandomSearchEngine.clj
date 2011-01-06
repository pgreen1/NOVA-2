(ns edu.wvu.lcsee.green.searchEngines.KeysRandomSearchEngine
  (:gen-class
    :implements [edu.wvu.lcsee.green.search.SearchEngine]
    :prefix "rkeys-impl-")
  (:use (edu.wvu.lcsee.green.searchEngines searchlib IsampSearchEngine))
  (:import (edu.wvu.lcsee.green.model.impl TreatmentImpl))
  )

(defn rkeys-impl-getKey [this]
  "keys-r")


(defn rkeys-constrainScenario [evaluationFunction scenario attributesLeftToConstrain]
  (let [attribute (rand-nth (seq attributesLeftToConstrain))
        originalAttributeConstrains (.getConstraintsFor scenario attribute)
        extremeValues (.getExtremesValues (.getEditor originalAttributeConstrains))
        extremeScenarios (map (fn [extremeValue]
                                (.applyTreatment scenario
                                  (TreatmentImpl/newSingletonTreatment attribute
                                    (.generateConstraints (doto (.getEditor originalAttributeConstrains)
                                                            (.removeAllValues)
                                                            (.addValue extremeValue))))))
                           extremeValues)]
    (first (sort-by
             (memoize (fn [scenario] (.evaluate evaluationFunction scenario)))
             >
             extremeScenarios))))


(defn rkeys-impl-search [this evaluationFunction initialScenario]
  (statesGenerator2path (search evaluationFunction initialScenario rkeys-constrainScenario 1)))