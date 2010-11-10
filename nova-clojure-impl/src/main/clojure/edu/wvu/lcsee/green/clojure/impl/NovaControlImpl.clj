
(ns edu.wvu.lcsee.green.clojure.impl.NovaControlImpl
  (:gen-class
      :implements [edu.wvu.lcsee.green.control.NovaControl]
      :init init
      :constructors {[edu.wvu.lcsee.green.model.ProjectGenerator java.util.Map] []}
      :state state
      :prefix "nc-impl-")
  ;(:require )
  ;(:use )
  (:import (com.google.common.collect ImmutableMap))
  )

(defn nc-impl-init [projectGenerator scoringFunctionRegistry]
  [[] {:projectGenerator projectGenerator 
       :scoringFunctionRegistry (ImmutableMap/copyOf scoringFunctionRegistry)}])

(defn nc-impl-getProjectGenerator [this]
  (get (.state this) :projectGenerator))


(defn nc-impl-getAllScoringFunctions [this]
  (get (.state this) :scoringFunctionRegistry))