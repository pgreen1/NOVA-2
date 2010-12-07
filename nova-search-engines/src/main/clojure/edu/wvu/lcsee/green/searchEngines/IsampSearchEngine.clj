(ns edu.wvu.lcsee.green.searchEngines.IsampSearchEngine
  (:gen-class
      :implements [edu.wvu.lcsee.green.search.SearchEngine]
      :prefix "se-impl-")
  ;(:require )
  ;(:use )
  (:import (com.google.common.collect ImmutableList Maps)
           (edu.wvu.lcsee.green.model.impl ScenarioImpl TreatmentImpl)
           (edu.wvu.lcsee.green.search.impl StateImpl PathImpl)
           (java.util Date))
  )

(defn se-impl-getKey [this]
  "isamp")


(defn filter-for-unconstrained-attributes [scenario]
  (filter (fn [attribute]
            (not (.isFullyConstrained (.getConstraintsFor scenario attribute))))
      (.getConstrainableAttributes scenario)))

(defn createStateFrom [evaluationFunction scenario]
  (new StateImpl
    scenario
    (.evaluate evaluationFunction scenario)))

(defn constrainScenario [scenario attributesLeftToConstrain]
  (let [attribute (rand-nth (seq attributesLeftToConstrain))
        editor (.getEditor (.getConstraintsFor scenario attribute))]
    (.removeValue editor
      (rand-nth (seq (.getAllValues editor))))
    (.applyTreatment scenario
      (TreatmentImpl/newSingletonTreatment attribute (.generateConstraints editor)))))

(defn search-1 [evaluationFunction initialScenario]
  (letfn [(search-recur [scenario attributesLeftToConstrain states]
            (if (empty? attributesLeftToConstrain)
              states
              (let [newScenario (constrainScenario scenario attributesLeftToConstrain)]
                (recur newScenario
                       (filter-for-unconstrained-attributes newScenario)
                       (conj
                         states
                         (createStateFrom evaluationFunction newScenario))))))]
    (cons
      (createStateFrom evaluationFunction initialScenario)
      (search-recur
        initialScenario
        (filter-for-unconstrained-attributes initialScenario)
        []))))

(defn determine-best-state-seq [state-seqs]
  (letfn [(max-score-in-state-seq [state-seq]
            (.getScore
              (reduce (fn ([] nil)
                        ([state1 state2] (if (> (.getScore state2) (.getScore state1))
                                           state2
                                           state1)))
                state-seq)))]
    (reduce (fn
              ([] nil)
              ([state-seq1 state-seq2]
                (if (> (max-score-in-state-seq state-seq2) (max-score-in-state-seq state-seq1))
                  state-seq2
                  state-seq1)))
      state-seqs)))

(defn search-all [evaluationFunction initialScenario retries]
  (determine-best-state-seq
    (for [i (range retries)]
      (search-1 evaluationFunction initialScenario))))

(defn se-impl-search [this evaluationFunction initialScenario]
  (let [startTime (new Date)
        states (ImmutableList/copyOf (search-all evaluationFunction initialScenario 25));TODO make 25 a parameter
        endTime  (new Date)]
    (new PathImpl startTime endTime states)))


