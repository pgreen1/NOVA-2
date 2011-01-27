(ns edu.wvu.lcsee.green.xomo.ScheduleScoringFunction
 (:import (edu.wvu.lcsee.green.xomo.model CocomoParameters CocomoLevel EffortMultiplierAttribute ScaleFactorAttribute
            XomoModelConfiguration)
   (java.lang Math))
 (:use (edu.wvu.lcsee.green.xomo EffortScoringFunction))
 (:gen-class
   :implements [edu.wvu.lcsee.green.model.ScoringFunction]
   :prefix "schedule-"))

(defn schedule-getKey [this]
  XomoModelConfiguration/SCORING_FUNCTION_KEY_COCOMO_SCHEDULE)

(defn calculate-sced-percent [sced]
  (condp =  (.getLevel sced)
    CocomoLevel/VL  75
    CocomoLevel/L   85
    CocomoLevel/N  100
    CocomoLevel/H  130
    CocomoLevel/VH 160))

(defn schedule-score [this project]
  (let [c (.getValueFor project CocomoParameters/C)
        d  (.getValueFor project CocomoParameters/D)
        sced (.getValueFor project EffortMultiplierAttribute/SCED)
        sced-percent (calculate-sced-percent sced)
        pm-no-sced (/ (effort-score project) (.getEffortCoefficient sced))
        sf-sum (reduce +
                 (map (fn [attribute]
                        (.getEffortCoefficent (.getValueFor project attribute)))
                   (ScaleFactorAttribute/values)))
        e-no-b (* 0.01 1)
        f (+ d (* 0.2 e-no-b))]
    (* c
      (Math/pow pm-no-sced f)
      (/ sced-percent 100))))


