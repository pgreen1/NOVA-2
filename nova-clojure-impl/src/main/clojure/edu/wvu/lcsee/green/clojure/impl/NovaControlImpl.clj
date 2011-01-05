
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

(defn nc-impl-init [projectGenerator modelConfigurationRegistry searchEngineRegistry]
  [[] {:projectGenerator projectGenerator
       :modelConfigurationRegistry modelConfigurationRegistry
       :searchEngineRegistry (ImmutableMap/copyOf searchEngineRegistry)}])

(defn nc-impl-getProjectGenerator [this]
  (get (.state this) :projectGenerator))

(defn nc-impl-getModelConfigration [this modelConfigurationType]
  (.get
    (get (.state this) :modelConfigurationRegistry)
    modelConfigurationType))

(defn nc-impl-getAllSearchEngines [this]
  (ImmutableSet/copyOf
    (.values
      (get (.state this) :searchEngineRegistry))))

(defn nc-impl-getSearchEngineForKey [this key]
  (.get
    (get (.state this) :searchEngineRegistry)
    key))
