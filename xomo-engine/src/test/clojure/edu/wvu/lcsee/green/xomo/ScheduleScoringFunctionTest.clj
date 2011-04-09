(ns edu.wvu.lcsee.green.xomo.ScheduleScoringFunctionTest
 ;(:require)
 (:use clojure.test edu.wvu.lcsee.green.xomo.testdata)
 (:import (edu.wvu.lcsee.green.xomo.model XomoModelConfiguration) )
 )

(deftest ScheduleScoringFunction-getKey-should-have-correct-key
  (let [scheduleScoringFunction (new edu.wvu.lcsee.green.xomo.ScheduleScoringFunction)]
    (is (.getKey scheduleScoringFunction) XomoModelConfiguration/SCORING_FUNCTION_KEY_COCOMO_SCHEDULE)))


(deftest ScheduleScoringFunction-score-should-calculate-score-correctly
  (let [scheduleScoringFunction (new edu.wvu.lcsee.green.xomo.ScheduleScoringFunction)
        project (generateXomoProject)]
    (is (= (.score scheduleScoringFunction project) 387.27668424797685))))
