(ns edu.wvu.lcsee.green.searchEngines.SeesawDeterministicSearchEngine
 (:gen-class
   :implements [edu.wvu.lcsee.green.search.SearchEngine]
   :prefix "dseesaw-impl-")
 (:use (edu.wvu.lcsee.green.searchEngines searchlib IsampSearchEngine))
 (:import (edu.wvu.lcsee.green.model.impl TreatmentImpl))
 )

(defn dseesaw-impl-getKey [this]
  "seesaw-d")

(defn generateExtremeScenariosFor [scenario attribute]
  (let [originalAttributeConstrains (.getConstraintsFor scenario attribute)
        extremeValues (.getExtremesValues (.getEditor originalAttributeConstrains))]
    (map (fn [extremeValue]
           (.applyTreatment scenario
             (TreatmentImpl/newSingletonTreatment attribute
               (.generateConstraints (doto (.getEditor originalAttributeConstrains)
                                       (.removeAllValues)
                                       (.addValue extremeValue))))))
      extremeValues)))

(defn determineBestScenario [evaluationFunction scenarios]
  (first (sort-by
           (memoize (fn [scenario] (.evaluate evaluationFunction scenario)))
           >
           scenarios)))

(defn dseesaw-constrainScenario [evaluationFunction scenario attributesLeftToConstrain]
  (determineBestScenario
    evaluationFunction
    (mapcat (partial generateExtremeScenariosFor scenario)
      attributesLeftToConstrain)))


(defn dseesaw-impl-search [this evaluationFunction initialScenario]
  (statesGenerator2path (search evaluationFunction initialScenario dseesaw-constrainScenario 1)))