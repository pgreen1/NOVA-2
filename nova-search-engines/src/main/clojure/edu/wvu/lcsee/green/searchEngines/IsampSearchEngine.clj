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

(defn constrainScenario [scenario attributesLeftToConstrain]
  (let [attribute (rand-nth (seq attributesLeftToConstrain))
        editor (.getEditor (.getConstraintsFor scenario attribute))]
    (.removeValue editor
      (rand-nth (seq (.getAllValues editor))))
    (.applyTreatment scenario
      (TreatmentImpl/newSingletonTreatment attribute (.generateConstraints editor)))))

(defn search [evaluationFunction initialScenario retries]
  (let [states [(createStateFrom evaluationFunction initialScenario)]]
    (letfn [(search-recur [scenario best-score attributesLeftToConstrain]
              (when-not (empty? attributesLeftToConstrain)
                (let [newScenario (constrainScenario scenario attributesLeftToConstrain)
                      newState (createStateFrom evaluationFunction newScenario)
                      newScore (.getScore newState)
                      better? (> newScore best-score)]
                  (when (> newScore best-score)
                    (conj states newState))
                  (recur newScenario
                    (if better? newScore best-score)
                    (filter-for-unconstrained-attributes newScenario)))))]
      (dotimes [i retries]
        (search-recur
          initialScenario
          (.getScore (last states))
          (filter-for-unconstrained-attributes initialScenario))))
    states))

(defn se-impl-search [this evaluationFunction initialScenario]
  (statesGenerator2path (search evaluationFunction initialScenario 25)));TODO make 25 a parameter