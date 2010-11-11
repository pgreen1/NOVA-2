
(ns edu.wvu.lcsee.green.clojure.impl.NovaControlImpl
  (:gen-class
      :implements [edu.wvu.lcsee.green.control.NovaControl]
      :init init
      :constructors {[edu.wvu.lcsee.green.model.ProjectGenerator java.util.Map java.util.Map] []}
      :state state
      :prefix "nc-impl-")
  ;(:require )
  ;(:use )
  (:import (com.google.common.collect ImmutableMap ImmutableSet))
  )

(defn nc-impl-init [projectGenerator scoringFunctionRegistry searchEngineRegistry]
  [[] {:projectGenerator projectGenerator 
       :scoringFunctionRegistry (ImmutableMap/copyOf scoringFunctionRegistry)
       :searchEngineRegistry (ImmutableMap/copyOf searchEngineRegistry)}])

(defn nc-impl-getProjectGenerator [this]
  (get (.state this) :projectGenerator))


(defn nc-impl-getAllScoringFunctions [this]
  (ImmutableSet/copyOf
    (.values
      (get (.state this) :scoringFunctionRegistry))))


(defn nc-impl-getScoringFunctionForKey [this key]
  (.get
     (get (.state this) :scoringFunctionRegistry)
     key))

(defn nc-impl-getAllSearchEngines [this]
  (ImmutableSet/copyOf
    (.values
      (get (.state this) :searchEngineRegistry))))


(defn nc-impl-getSearchEngineForKey [this key]
  (.get
    (get (.state this) :searchEngineRegistry)
    key))
