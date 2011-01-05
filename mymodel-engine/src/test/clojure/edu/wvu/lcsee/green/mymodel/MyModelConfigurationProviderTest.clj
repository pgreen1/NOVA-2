(ns edu.wvu.lcsee.green.mymodel.MyModelConfigurationProviderTest
 ;(:require)
 (:use clojure.test)
 ;(:import)
 )

(deftest MyModelConfigurationProvider-getModelConfiguration-should-provide-modelConfiguration
  (let [myModelConfigurationProvider (new edu.wvu.lcsee.green.mymodel.MyModelConfigurationProvider)]
    (is (.getModelConfiguration myModelConfigurationProvider))))
