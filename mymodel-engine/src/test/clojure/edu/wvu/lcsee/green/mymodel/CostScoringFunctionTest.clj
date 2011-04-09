(ns edu.wvu.lcsee.green.mymodel.CostScoringFunctionTest
 (:import (edu.wvu.lcsee.green.mymodel.model MyModelConfiguration) )
 ;(:require)
 (:use clojure.test edu.wvu.lcsee.green.mymodel.testdata)
 )

(deftest CostScoringFunction-getKey-should-have-correct-key
  (let [costScoringFunction (new edu.wvu.lcsee.green.mymodel.CostScoringFunction)]
    (is (= (.getKey costScoringFunction) MyModelConfiguration/SCORING_FUNCTION_KEY_COST))))


(deftest CostScoringFunction-score-should-calculate-score-correctly
  (let [costScoringFunction (new edu.wvu.lcsee.green.mymodel.CostScoringFunction)
        project (generateMymodelProject)]
    (is (= (.score costScoringFunction project) 3.5626713590868144))))
