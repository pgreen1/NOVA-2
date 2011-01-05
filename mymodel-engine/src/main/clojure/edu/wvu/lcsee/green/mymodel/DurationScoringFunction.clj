(ns edu.wvu.lcsee.green.mymodel.DurationScoringFunction
	(:import (edu.wvu.lcsee.green.mymodel.model MyModelAttribute MyModelAttribute$ProjectSize)
             (java.lang Math))
	;(:require )
    (:gen-class
      :implements [edu.wvu.lcsee.green.model.ScoringFunction]
      :prefix "duration-"))

(defn duration-getKey [this]
  "duration")

(defn duration-score [this project]
   (/ (* (Math/pow 15  (.getValueFor project MyModelAttribute/FF))
        (condp =  (.getValueFor project MyModelAttribute/PSIZE)
          MyModelAttribute$ProjectSize/SMALL  2.0
          MyModelAttribute$ProjectSize/MEDIUM 1.0
          MyModelAttribute$ProjectSize/LARGE  0.5))
      (.getValueFor project MyModelAttribute/MEXP)))
