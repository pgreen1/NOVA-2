(ns edu.wvu.lcsee.green.clojure.impl.NovaControlFactoryImplTest
 ;(:require)
 (:use clojure.test edu.wvu.lcsee.green.clojure.impl.NovaControlFactoryImpl)
 (:import (edu.wvu.lcsee.green.model ModelConfiguration))
 )


(gen-interface
  :name edu.wvu.lcsee.green.clojure.impl.NovaControlFactoryImplTest.TestModelConfiguration
  :extends [edu.wvu.lcsee.green.model.ModelConfiguration]
  )

(gen-interface
  :name edu.wvu.lcsee.green.clojure.impl.NovaControlFactoryImplTest.TestSubModelConfiguration
  :extends [edu.wvu.lcsee.green.clojure.impl.NovaControlFactoryImplTest.TestModelConfiguration]
  )


(deftest getAllInterfaces-with-object-should-be-empty
  (let [interfaces (set (getAllInterfaces Object))]
    (is (empty? interfaces))))

(deftest getAllInterfaces-class-with-direct-interfaces-only-should-be-correct
  (let [interfaces (set (getAllInterfaces Long))]
    (is (= interfaces 
          (set (list java.io.Serializable java.lang.Comparable))))))

(deftest getAllInterfaces-with-modelConfiguration-should-be-empty
  (let [interfaces (set (getAllInterfaces ModelConfiguration))]
    (is (empty? interfaces))))

(deftest getAllInterfaces-with-test-interface-should-be-modelConfiguration
  (let [interfaces (set (getAllInterfaces edu.wvu.lcsee.green.clojure.impl.NovaControlFactoryImplTest.TestModelConfiguration))]
    (is (= interfaces
          (set (list ModelConfiguration))))))

(deftest getAllInterfaces-with-test-sub-interface-should-be-modelConfiguration-and-test-interface
  (let [interfaces (set (getAllInterfaces edu.wvu.lcsee.green.clojure.impl.NovaControlFactoryImplTest.TestSubModelConfiguration))]
    (is (= interfaces
          (set (list edu.wvu.lcsee.green.clojure.impl.NovaControlFactoryImplTest.TestModelConfiguration ModelConfiguration))))))