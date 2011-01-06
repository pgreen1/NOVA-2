(ns edu.wvu.lcsee.green.searchEngines.SeesawDeterministicSearchEngineTest
 ;(:require)
 (:use
   clojure.test
   edu.wvu.lcsee.green.searchEngines.mockobjects
   edu.wvu.lcsee.green.searchEngines.testlib)
 ;(:import)
 )

(deftest RandomKeysSearchEngine-key-should-be-seesaw-r
  (let [searchEngine (new edu.wvu.lcsee.green.searchEngines.SeesawDeterministicSearchEngine)]
    (is (= "seesaw-d" (.getKey searchEngine)))))


(deftest RandomKeysSearchEngine-should-search
  (let [searchEngine (new edu.wvu.lcsee.green.searchEngines.SeesawDeterministicSearchEngine)
        evaluationFunction (generate-mock-evaluationFunction (fn [_] (rand 10)))
        initialScenario (generate-mock-scenario)
        result-path (.search searchEngine evaluationFunction initialScenario)]
    (is (states-sorted-by-score? result-path))
    (is result-path)
    (is (.before (.getTimeStarted result-path) (.getTimeEnded result-path)))))