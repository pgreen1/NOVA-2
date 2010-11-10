(ns edu.wvu.lcsee.green.mymodel.scoringfunctions
	(:import (edu.wvu.lcsee.green.mymodel.model MyModelAttribute MyModelAttribute$ProjectSize)
             (java.lang Math))
	;(:require )
)

(gen-class
  :name edu.wvu.lcsee.green.mymodel.CostScoringFunction
  :implements [edu.wvu.lcsee.green.model.spi.ScoringFunction]
  :prefix "cost-")

(defn cost-getKey [this]
  "cost")

(defn cost-score [this project]
  (*  (Math/pow 2 (.getValueFor project MyModelAttribute/FF))
      (condp =  (.getValueFor project MyModelAttribute/PSIZE)
          MyModelAttribute$ProjectSize/SMALL  1.0
          MyModelAttribute$ProjectSize/MEDIUM 3.0
          MyModelAttribute$ProjectSize/LARGE  9.0)
      (/ (.getValueFor project MyModelAttribute/MEXP)
          10)))

(gen-class
  :name edu.wvu.lcsee.green.mymodel.DurationScoringFunction
  :implements [edu.wvu.lcsee.green.model.spi.ScoringFunction]
  :prefix "duration-")

(defn duration-getKey [this]
  "duration")

(defn duration-score [this project]
   (/ (* (Math/pow 15  (.getValueFor project MyModelAttribute/FF))
        (condp =  (.getValueFor project MyModelAttribute/PSIZE)
          MyModelAttribute$ProjectSize/SMALL  2.0
          MyModelAttribute$ProjectSize/MEDIUM 1.0
          MyModelAttribute$ProjectSize/LARGE  0.5))
      (.getValueFor project MyModelAttribute/MEXP)))
