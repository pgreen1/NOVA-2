(ns edu.wvu.lcsee.green.searchEngines.KeysDeterministicSearchEngineTest
 ;(:require)
 (:use
   clojure.test
   edu.wvu.lcsee.green.searchEngines.mockobjects
   edu.wvu.lcsee.green.searchEngines.testlib)
 ;(:import)
 )

(deftest RandomKeysSearchEngine-key-should-be-keys-r
  (let [searchEngine (new edu.wvu.lcsee.green.searchEngines.KeysDeterministicSearchEngine)]
    (is (= "keys-d" (.getKey searchEngine)))))


(deftest RandomKeysSearchEngine-should-search
  (let [searchEngine (new edu.wvu.lcsee.green.searchEngines.KeysDeterministicSearchEngine)
        evaluationFunction (generate-mock-evaluationFunction (fn [_] (rand 10)))
        initialScenario (generate-mock-scenario)
        result-path (.search searchEngine evaluationFunction initialScenario)]
    (is (states-sorted-by-score? result-path))
    (is result-path)
    (is (.before (.getTimeStarted result-path) (.getTimeEnded result-path)))))