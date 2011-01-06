(ns edu.wvu.lcsee.green.searchEngines.StrawManSearchEngineTest
  ;(:require)
  (:use clojure.test edu.wvu.lcsee.green.searchEngines.mockobjects)
  ;(:import)
  )


(deftest StrawManSearchEngine-key-should-be-strawman
  (let [searchEngine (new edu.wvu.lcsee.green.searchEngines.StrawManSearchEngine)]
    (is (= "strawman" (.getKey searchEngine)))))


(deftest StrawManSearchEngine-should-search
  (let [searchEngine (new edu.wvu.lcsee.green.searchEngines.StrawManSearchEngine)
        evaluationFunction (generate-mock-evaluationFunction (fn [_] 10))
        initialScenario (generate-mock-scenario)
        result-path (.search searchEngine evaluationFunction initialScenario)]
    (is result-path)))