(ns edu.wvu.lcsee.green.clojure.impl.ProjectGeneratorImpl
 (:gen-class
    :implements [edu.wvu.lcsee.green.model.ProjectGenerator]
    :prefix "pg-impl-")
 (:import
     (edu.wvu.lcsee.green.model ConstraintsContextBuilder)
     (edu.wvu.lcsee.green.model.impl ProjectIterable ScoredProjectIterable)
     (com.google.common.collect ImmutableSet ImmutableMap))
  )

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
    (let [builder (new ConstraintsContextBuilder)]
      (doseq [attribute (seq (. scenario getAllAttributes))]
        (.addValue builder
          attribute
          (.. scenario (getConstraintsFor attribute) (generateValue (.build builder)))))
      (.buildMap builder))))

(defn pg-impl-generateScoredProject [this scenario scoringFunctions]
  (let [project (.generateProject this scenario)]
    (new edu.wvu.lcsee.green.model.impl.ScoredProjectImpl
      project
      (scoreProject project scoringFunctions))))

(defn pg-impl-generateManyProjects [this scenario numberOfProjectsToGenerate]
  (new ProjectIterable this scenario numberOfProjectsToGenerate))

(defn pg-impl-generateManyScoredProjects [this scenario numberOfProjectsToGenerate scoringFunctions]
  (new ScoredProjectIterable this scoringFunctions scenario numberOfProjectsToGenerate))