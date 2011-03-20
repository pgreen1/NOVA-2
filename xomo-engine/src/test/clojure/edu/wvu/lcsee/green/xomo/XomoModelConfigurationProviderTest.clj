(ns edu.wvu.lcsee.green.xomo.XomoModelConfigurationProviderTest
 ;(:require)
 (:use clojure.test)
 ;(:import)
 )

(deftest XomoModelConfigurationProvider-getModelConfiguration-should-provide-modelConfiguration
  (let [xomoModelConfigurationProvider (new edu.wvu.lcsee.green.xomo.XomoModelConfigurationProvider)]
    (is (.getModelConfiguration xomoModelConfigurationProvider))))
