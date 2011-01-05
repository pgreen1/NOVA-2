(ns edu.wvu.lcsee.green.mymodel.CostScoringFunction
	(:import (edu.wvu.lcsee.green.mymodel.model MyModelAttribute MyModelAttribute$ProjectSize)
             (java.lang Math))
	;(:require )
  (:gen-class
    :implements [edu.wvu.lcsee.green.model.ScoringFunction]
    :prefix "cost-"))

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
