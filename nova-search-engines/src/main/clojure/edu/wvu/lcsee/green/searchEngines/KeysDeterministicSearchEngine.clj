(ns edu.wvu.lcsee.green.searchEngines.KeysDeterministicSearchEngine
 (:gen-class
   :implements [edu.wvu.lcsee.green.search.SearchEngine]
   :prefix "dkeys-impl-")
 (:use (edu.wvu.lcsee.green.searchEngines searchlib IsampSearchEngine))
 (:import (edu.wvu.lcsee.green.model.impl TreatmentImpl))
 )

(defn dkeys-impl-getKey [this]
  "keys-d")

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

(defn dkeys-constrainScenario [evaluationFunction scenario attributesLeftToConstrain]
  (determineBestScenario
    evaluationFunction
    (mapcat (partial generateExtremeScenariosFor scenario)
      attributesLeftToConstrain)))


(defn dkeys-impl-search [this evaluationFunction initialScenario]
  (statesGenerator2path (search evaluationFunction initialScenario dkeys-constrainScenario 1)))