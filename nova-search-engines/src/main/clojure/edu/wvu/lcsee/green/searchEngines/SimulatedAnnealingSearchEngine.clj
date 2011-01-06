(ns edu.wvu.lcsee.green.searchEngines.SimulatedAnnealingSearchEngine
 (:gen-class
   :implements [edu.wvu.lcsee.green.search.SearchEngine]
   :init init
   :constructors {[long long double] []}
   :state parameters
   :prefix "se-impl-")
 ;(:require )
 (:use clojure.contrib.generic.math-functions
   edu.wvu.lcsee.green.searchEngines.searchlib)
 (:import (edu.wvu.lcsee.green.model.impl ScenarioImpl))
 )


(defn se-impl-init [kmax emax neighborChangeProbability]
  [[] {:kmax kmax,
       :emax emax,
       :neighborChangeProbability neighborChangeProbability}])

(defn se-impl-getKey [this]
  "sa")

(defn generateChanges [constrainableAttributes globalAttributeConstraints changeProbability]
  (let [attributesToChange (filter (fn [_] (< (rand) changeProbability))
                             constrainableAttributes)]
    (reduce merge
      (map (fn [attribute]
             (let [editor (.getEditor (get globalAttributeConstraints attribute))
                   valuesToRemove (filter (fn [_] (< (rand) 0.5))
                                    (.getAllValues editor))]
               (map (fn [value]
                      (.removeValue editor value))
                 valuesToRemove)
               {attribute (.generateConstraints editor)}))
        attributesToChange))))


(defn calculate-neighbor-state [neighborChangeProbability evaluationFunction constrainableAttributes globalAttributeConstraints state]
  (createStateFrom
    evaluationFunction
    (new ScenarioImpl (merge
                        (into {} (.asMap (.getScenario state)))
                        (generateChanges constrainableAttributes globalAttributeConstraints neighborChangeProbability))
      constrainableAttributes)))


(defn calculate-energy [state]
  (.doubleValue (.getScore state)))

(defn calculate-temperature 
  "Returns the temperature for the kth iteration."
  [k kmax]
  (exp (/ (* -5 k) kmax)))

(defn calculate-acceptance-probability
  "Returns the acceptance probability for moving to a new state."
  [previous-energy new-energy temperature]
  (let [e-max   (max previous-energy new-energy)
        ep-norm (/ previous-energy e-max)
        en-norm (/ new-energy e-max)]
    (exp (/ (- ep-norm en-norm) temperature))))

(defn search [evaluationFunction initialScenario kmax emax neighborChangeProbability]
  (letfn [(search-recur [states best-energy previous-state previous-energy k]
            (if (or (> k kmax)
                  (> previous-energy emax))
              states
              (let [neighbor-state (calculate-neighbor-state neighborChangeProbability evaluationFunction (.getConstrainableAttributes initialScenario) (.asMap initialScenario) previous-state)
                    neighbor-energy (calculate-energy neighbor-state)
                    better-state? (> neighbor-energy best-energy)
                    new-best-energy (max best-energy neighbor-energy)
                    move? (> (calculate-acceptance-probability previous-energy neighbor-energy (calculate-temperature k kmax))
                            (rand))];TODO PULL OUT RAND
                (recur
                  (if better-state? (conj states neighbor-state) states)
                  new-best-energy
                  (if move? neighbor-state previous-state)
                  (if move? neighbor-energy previous-energy)
                  (+ k 1)))))]
    (let [initialState (createStateFrom evaluationFunction initialScenario)
          initialEnergy (calculate-energy initialState)]
      (search-recur [initialState] initialEnergy initialState initialEnergy 1))))

(defn se-impl-search [this evaluationFunction initialScenario]
 (statesGenerator2path
   (search evaluationFunction initialScenario
     (get (.parameters this) :kmax)
     (get (.parameters this) :emax)
     (get (.parameters this) :neighborChangeProbability))))
