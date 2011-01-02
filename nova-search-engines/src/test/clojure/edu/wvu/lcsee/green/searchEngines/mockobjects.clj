(ns edu.wvu.lcsee.green.searchEngines.mockobjects
  (:import (com.google.common.collect ImmutableList)
           (edu.wvu.lcsee.green.model.impl SetConstraints ScenarioImpl))
  (:use clojure.test)
 )

(defn generate-mock-evaluationFunction [eval-fn]
  (proxy [edu.wvu.lcsee.green.search.EvaluationFunction] []
    (evaluate [scenario]
      (eval-fn scenario))))

(deftest generate-mock-evaluationFunction-should-not-return-nil
  (let [mock-evaluationFunction (generate-mock-evaluationFunction (fn [_] 4))]
    (is mock-evaluationFunction)
    (is (= 4 (.evaluate mock-evaluationFunction nil)))))


(defn generate-mock-attribute []
  (let [name (str (gensym "attribute-"))]
    (proxy [edu.wvu.lcsee.green.model.Attribute] []
      (getName [] name)
      (getValueType [] Long))))

(deftest generate-mock-attribute-should-not-return-nil
  (let [mock-attribute (generate-mock-attribute)]
    (is mock-attribute)))


(defn generate-mock-constraints []
  (new edu.wvu.lcsee.green.model.impl.SetConstraints (set (take (+ 2 (rand-int 8)) (shuffle (range 10))))))

(deftest generate-mock-constraints-should-not-return-nil
  (let [mock-constraints (generate-mock-constraints)]
    (is mock-constraints)))


(defn generate-mock-scenario []
  (let [attributes (map (fn [_] (generate-mock-attribute)) (range 2 (rand-int 8)))
        constrainableAttributes (apply hash-set (take (/ (count attributes) 2) attributes))
        attributeConstraints (apply hash-map (mapcat (fn [attribute] (list attribute (generate-mock-constraints))) attributes))]
    (new edu.wvu.lcsee.green.model.impl.ScenarioImpl attributeConstraints constrainableAttributes)))

(deftest generate-mock-scenario-should-not-return-nil
  (let [mock-scenario (generate-mock-scenario)]
    (is mock-scenario)))