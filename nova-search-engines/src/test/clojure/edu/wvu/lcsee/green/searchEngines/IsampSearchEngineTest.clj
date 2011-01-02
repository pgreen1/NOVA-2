(ns edu.wvu.lcsee.green.searchEngines.IsampSearchEngineTest
 ;(:require)
 (:use
   clojure.test
   edu.wvu.lcsee.green.searchEngines.mockobjects
   edu.wvu.lcsee.green.searchEngines.testlib)
 ;(:import)
 )

(deftest IsampSearchEngine-key-should-be-strawman
  (let [searchEngine (new edu.wvu.lcsee.green.searchEngines.IsampSearchEngine)]
    (is (= "isamp" (.getKey searchEngine)))))


(deftest IsampSearchEngine-should-search
  (let [searchEngine (new edu.wvu.lcsee.green.searchEngines.IsampSearchEngine)
        evaluationFunction (generate-mock-evaluationFunction (fn [_] (rand 10)))
        initialScenario (generate-mock-scenario)
        result (.search searchEngine evaluationFunction initialScenario)]
    (is (states-sorted-by-score? result))
    (is result)))