(ns edu.wvu.lcsee.green.searchEngines.IsampSearchEngine
 (:gen-class
   :implements [edu.wvu.lcsee.green.search.SearchEngine]
   :prefix "se-impl-")
 ;(:require )
 (:use edu.wvu.lcsee.green.searchEngines.searchlib)
 (:import (edu.wvu.lcsee.green.model.impl TreatmentImpl))
 )

(defn se-impl-getKey [this]
  "isamp")

(defn filter-for-unconstrained-attributes [scenario]
  (filter (fn [attribute]
            (not (.isFullyConstrained (.getConstraintsFor scenario attribute))))
    (.getConstrainableAttributes scenario)))

(defn constrainScenario [_ scenario attributesLeftToConstrain]
  (let [attribute (rand-nth (seq attributesLeftToConstrain))
        editor (.getEditor (.getConstraintsFor scenario attribute))]
    (.removeValue editor
      (rand-nth (seq (.getAllValues editor))))
    (.applyTreatment scenario
      (TreatmentImpl/newSingletonTreatment attribute (.generateConstraints editor)))))

(defn search [evaluationFunction initialScenario constrain-scenario-fn retries]
  (let [initialUnconstrainedAttributes (filter-for-unconstrained-attributes initialScenario)]
    (letfn [(search-recur [scenario best-score attributesLeftToConstrain lives states]
              (cond 
                (= 0 lives) states
                (empty? attributesLeftToConstrain)  (recur initialScenario best-score initialUnconstrainedAttributes (- lives 1) states)
                true 
                (let [newScenario (constrain-scenario-fn evaluationFunction scenario attributesLeftToConstrain)
                      newState (createStateFrom evaluationFunction newScenario)
                      newScore (.getScore newState)
                      better? (> newScore best-score)]
                  (recur
                    newScenario
                    (if better? newScore best-score)
                    (filter-for-unconstrained-attributes newScenario)
                    lives
                    (if better? (conj states newState) states)))))]
      (let [initialState (createStateFrom evaluationFunction initialScenario)]
        (search-recur
          initialScenario
          (.getScore initialState)
          initialUnconstrainedAttributes
          retries
          [initialState])))))

(defn se-impl-search [this evaluationFunction initialScenario]
  (statesGenerator2path (search evaluationFunction initialScenario constrainScenario 25)));TODO make 25 a parameter