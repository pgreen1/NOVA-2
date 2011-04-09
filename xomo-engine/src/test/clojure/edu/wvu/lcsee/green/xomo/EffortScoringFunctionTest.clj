(ns edu.wvu.lcsee.green.xomo.EffortScoringFunctionTest
 ;(:require)
 (:use clojure.test edu.wvu.lcsee.green.xomo.testdata)
 (:import (edu.wvu.lcsee.green.xomo.model XomoModelConfiguration) )
 )

(deftest EffortScoringFunction-getKey-should-have-correct-key
  (let [effortScoringFunction (new edu.wvu.lcsee.green.xomo.EffortScoringFunction)]
    (is (.getKey effortScoringFunction) XomoModelConfiguration/SCORING_FUNCTION_KEY_COCOMO_EFFORT)))


(deftest EffortScoringFunction-score-should-calculate-score-correctly
  (let [effortScoringFunction (new edu.wvu.lcsee.green.xomo.EffortScoringFunction)
        project (generateXomoProject)]
    (is (= (.score effortScoringFunction project) 43.11076616608045))))
