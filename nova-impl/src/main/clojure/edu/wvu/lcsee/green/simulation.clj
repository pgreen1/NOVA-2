(ns edu.wvu.lcsee.green.simulation
	(:import ;(edu.wvu.lcsee.green.spi ProjectGenerator)
             ;(edu.wvu.lcsee.green.model.impl ProjectImpl)
             (com.google.common.collect ImmutableSet))
	;(:require )
)

(gen-class
  :name edu.wvu.lcsee.green.simulation.ProjectGeneratorImpl
  :implements [edu.wvu.lcsee.green.spi.ProjectGenerator]
  :prefix "pg-impl-")

(defn getScoringFunctions [scoreFunctionKeys]
  (let [allScoringFunctions (.getScoringFunctions edu.wvu.lcsee.green.ui.NovaControl/INSTANCE)]
     (map (fn [key]
            (.get allScoringFunctions key))
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
      (scoreProject project (getScoringFunctions scoreFunctionKeys)))))

(defn pg-impl-generateManyProjects [this scenario numberOfProjectsToGenerate]
  (. ImmutableSet copyOf
    (map (fn [n]
          (.generateProject this scenario))
      (range numberOfProjectsToGenerate))))

(defn pg-impl-generateManyScoredProjects [this scenario numberOfProjectsToGenerate scoreFunctionKeys]
  (let [scoreFunctions (getScoringFunctions scoreFunctionKeys)]
    (. ImmutableSet copyOf
      (map (fn [n]
            (scoreProject (.generateProject this scenario) scoreFunctions))
        (range numberOfProjectsToGenerate)))))