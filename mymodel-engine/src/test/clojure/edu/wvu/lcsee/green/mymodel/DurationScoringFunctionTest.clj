(ns edu.wvu.lcsee.green.mymodel.DurationScoringFunctionTest
 (:import (edu.wvu.lcsee.green.mymodel.model MyModelConfiguration) )
 ;(:require)
 (:use clojure.test edu.wvu.lcsee.green.mymodel.testdata)
 )

(deftest DurationScoringFunction-getKey-should-have-correct-key
  (let [durationScoringFunction (new edu.wvu.lcsee.green.mymodel.DurationScoringFunction)]
    (is (= (.getKey durationScoringFunction) MyModelConfiguration/SCORING_FUNCTION_KEY_DURATION))))


(deftest DurationScoringFunction-score-should-calculate-score-correctly
  (let [durationScoringFunction (new edu.wvu.lcsee.green.mymodel.DurationScoringFunction)
        project (generateMymodelProject)]
    (is (= (.score durationScoringFunction project) 0.4923628231771296))))
