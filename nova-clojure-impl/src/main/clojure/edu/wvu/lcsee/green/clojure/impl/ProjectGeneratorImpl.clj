(ns edu.wvu.lcsee.green.clojure.impl.ProjectGeneratorImpl
 (:gen-class
    :implements [edu.wvu.lcsee.green.model.ProjectGenerator]
    :init init
    :constructors {[java.util.Map] []}
    :state state
    :prefix "pg-impl-")
 (:import
    (com.google.common.collect ImmutableSet ImmutableMap))
  )

(defn pg-impl-init [scoringFunctionRegistry]
  [[] {:scoringFunctionRegistry (ImmutableMap/copyOf scoringFunctionRegistry)}])


(defn getScoringFunctions [projectGenerator scoreFunctionKeys]
  (let [scoringFunctionRegistry (get (.state projectGenerator) :scoringFunctionRegistry)]
     (map (fn [key]
            (.get scoringFunctionRegistry key))
        scoreFunctionKeys)))

(defn scoreProject [project scoreFunctions]
  (apply hash-map
    (flatten
      (map (fn [scoreFunction]
             (list
               (.getKey scoreFunction)
               (.score scoreFunction project)))
        scoreFunctions))))


(defn pg-impl-generateProject [this scenario]
  (new edu.wvu.lcsee.green.model.impl.ProjectImpl
    (apply hash-map
      (flatten
        (map (fn [attribute]
               (list
                 attribute
                 (.. scenario (getConstraintsFor attribute) (generateValue))))
          (. scenario getAllAttributes))))))

(defn pg-impl-generateScoredProject [this scenario scoreFunctionKeys]
  (let [project (.generateProject this scenario)]
    (new edu.wvu.lcsee.green.model.impl.ScoredProjectImpl
      project
      (scoreProject project (getScoringFunctions this scoreFunctionKeys)))))

(defn pg-impl-generateManyProjects [this scenario numberOfProjectsToGenerate]
  (. ImmutableSet copyOf
    (map (fn [n]
          (.generateProject this scenario))
      (range numberOfProjectsToGenerate))))

(defn pg-impl-generateManyScoredProjects [this scenario numberOfProjectsToGenerate scoreFunctionKeys]
  (let [scoreFunctions (getScoringFunctions this scoreFunctionKeys)]
    (. ImmutableSet copyOf
      (map (fn [n]
             (let [project (.generateProject this scenario)]
               (new edu.wvu.lcsee.green.model.impl.ScoredProjectImpl
                 project
                 (scoreProject project scoreFunctions))))
        (range numberOfProjectsToGenerate)))))