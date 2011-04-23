(ns edu.wvu.lcsee.green.clojure.impl.ProjectGeneratorImplTest
 ;(:require)
 (:use clojure.test edu.wvu.lcsee.green.clojure.impl.ProjectGeneratorImpl)
 (:import
   (edu.wvu.lcsee.green.model.impl DefaultAttribute ScenarioImpl RangeConstraints))
 )


(defn generateScenario []
  (let [attribute1 (new DefaultAttribute "ATTR1" "Test Attribute 1" Number)
        attribute2 (new DefaultAttribute "ATTR2" "Test Attribute 2" Number)]
    (new ScenarioImpl
      (hash-map
        attribute1 (new RangeConstraints 5)
        attribute2 (new RangeConstraints 10))
      (hash-set  attribute1 attribute2))))


(deftest generateProject-with-valid-scenario-should-generate-a-valid-project
  (let [scenario (generateScenario)
        projectGeneratorImpl (new edu.wvu.lcsee.green.clojure.impl.ProjectGeneratorImpl)]
    (is (.generateProject projectGeneratorImpl scenario))))

