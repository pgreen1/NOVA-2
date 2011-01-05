(ns edu.wvu.lcsee.green.mymodel.MyModelConfigurationImpl
 (:gen-class
   :implements [edu.wvu.lcsee.green.mymodel.model.MyModelConfiguration]
   :extends edu.wvu.lcsee.green.model.impl.ModelConfigurationForwardingImpl
   :init init
   :constructors {[edu.wvu.lcsee.green.model.ModelConfiguration] []}
   :state modelConfigurationDelegate)
 (:import (edu.wvu.lcsee.green.mymodel.model MyModelConfiguration))
 )

(defn -init [modelConfigurationDelegate]
  [[] modelConfigurationDelegate])

(defn -delegate [this]
  (.modelConfigurationDelegate this))

(defn -getCostScoringFunction [this]
  (.getScoringFunctionForKey this MyModelConfiguration/SCORING_FUNCTION_KEY_COST))

(defn -getDurationScoringFunction [this]
  (.getScoringFunctionForKey this MyModelConfiguration/SCORING_FUNCTION_KEY_DURATION))
