(ns edu.wvu.lcsee.green.xomo.EffortScoringFunction
 (:import (edu.wvu.lcsee.green.xomo.model CocomoParameters EffortMultiplierAttribute ScaleFactorAttribute
            XomoModelConfiguration)
   (java.lang Math))
 ;(:require )
 (:gen-class
   :implements [edu.wvu.lcsee.green.model.ScoringFunction]
   :prefix "effort-"))

(defn effort-getKey [this]
  XomoModelConfiguration/SCORING_FUNCTION_KEY_COCOMO_EFFORT)

(defn effort-score [this project]
  (let [a (.getValueFor project CocomoParameters/A)
        b  (.getValueFor project CocomoParameters/B)
        kloc  (.getValueFor project CocomoParameters/KLOC)
        sf-sum (reduce +
                 (map (fn [attribute]
                        (.getEffortCoefficient (.getValueFor project attribute)))
                   (ScaleFactorAttribute/values)))
        em-product (reduce *
                     (map (fn [attribute]
                            (.getEffortCoefficient (.getValueFor project attribute)))
                       (EffortMultiplierAttribute/values)))
        e (+ b (* 0.01 sf-sum))]
    (* a
      (Math/pow kloc e)
      em-product)))
