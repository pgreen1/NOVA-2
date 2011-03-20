(ns edu.wvu.lcsee.green.xomo.XomoModelConfigurationImpl
 (:gen-class
   :implements [edu.wvu.lcsee.green.xomo.model.XomoModelConfiguration]
   :extends edu.wvu.lcsee.green.model.impl.ModelConfigurationForwardingImpl
   :init init
   :constructors {[edu.wvu.lcsee.green.model.ModelConfiguration] []}
   :state modelConfigurationDelegate)
 (:import (edu.wvu.lcsee.green.xomo.model XomoModelConfiguration))
 )

(defn -init [modelConfigurationDelegate]
  [[] modelConfigurationDelegate])

(defn -delegate [this]
  (.modelConfigurationDelegate this))

(defn -getEffortScoringFunction [this]
  (.getScoringFunctionForKey this XomoModelConfiguration/SCORING_FUNCTION_KEY_COCOMO_EFFORT))

(defn -getScheduleScoringFunction [this]
  (.getScoringFunctionForKey this XomoModelConfiguration/SCORING_FUNCTION_KEY_COCOMO_SCHEDULE))

(defn -getDefectsScoringFunction [this]
  (.getScoringFunctionForKey this XomoModelConfiguration/SCORING_FUNCTION_KEY_COQUALMO_DEFECTS))
