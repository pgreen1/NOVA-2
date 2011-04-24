(ns edu.wvu.lcsee.green.clojure.impl.ProjectGeneratorImplTest
 ;(:require)
 (:use clojure.test edu.wvu.lcsee.green.clojure.impl.ProjectGeneratorImpl edu.wvu.lcsee.green.clojure.impl.mockobjects)
 (:import
   (edu.wvu.lcsee.green.model.impl DefaultAttribute ScenarioImpl RangeConstraints))
 )


(deftest generateProject-with-valid-scenario-should-generate-a-valid-project
  (let [scenario (generate-mock-scenario)
        projectGeneratorImpl (new edu.wvu.lcsee.green.clojure.impl.ProjectGeneratorImpl)]
    (is (.generateProject projectGeneratorImpl scenario))))

