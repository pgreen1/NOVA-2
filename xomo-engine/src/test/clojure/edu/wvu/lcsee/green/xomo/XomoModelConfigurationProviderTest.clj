(ns edu.wvu.lcsee.green.xomo.XomoModelConfigurationProviderTest
 ;(:require)
 (:use clojure.test edu.wvu.lcsee.green.xomo.testdata)
 ;(:import)
 )

(deftest XomoModelConfigurationProvider-getModelConfiguration-should-provide-modelConfiguration
  (let [xomoModelConfigurationProvider (new edu.wvu.lcsee.green.xomo.XomoModelConfigurationProvider)]
    (is (.getModelConfiguration xomoModelConfigurationProvider))))

(deftest XomoModelConfigurationProvider-modelConfiguration-should-generate-default-scenario
  (let [xomoModelConfigurationProvider (new edu.wvu.lcsee.green.xomo.XomoModelConfigurationProvider)]
    (is (.generateDefaultScenario 
          (.getModelConfiguration xomoModelConfigurationProvider)))))

(deftest XomoModelConfigurationProvider-modelConfiguration-should-generate-default-scenario-that-generates-project
  (let [xomoModelConfigurationProvider (new edu.wvu.lcsee.green.xomo.XomoModelConfigurationProvider)
        modelConfiguration (.getModelConfiguration xomoModelConfigurationProvider)
        defaultScenario    (.generateDefaultScenario modelConfiguration)
        projectGenerator (generateProjectGenerator)]
    (is (.generateProject projectGenerator defaultScenario))))
