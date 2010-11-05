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

(defn pg-impl-generate [this scenario]
  (new edu.wvu.lcsee.green.model.impl.ProjectImpl
    (apply hash-map
      (flatten
        (map (fn [attribute]
               (list
                 attribute
                 (.. scenario (getConstraintsFor attribute) (generateValue))))
          (. scenario getAllAttributes))))))

(defn pg-impl-generateMany [this scenario numberOfProjectsToGenerate]
  (. ImmutableSet copyOf
    (map (fn [n]
          (.generate this scenario))
      (range numberOfProjectsToGenerate))))