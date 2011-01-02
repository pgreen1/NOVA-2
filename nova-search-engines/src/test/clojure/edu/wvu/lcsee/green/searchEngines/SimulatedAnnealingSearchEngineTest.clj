(ns edu.wvu.lcsee.green.searchEngines.SimulatedAnnealingSearchEngineTest
 ;(:require)
 (:use
   clojure.test
   edu.wvu.lcsee.green.searchEngines.mockobjects
   edu.wvu.lcsee.green.searchEngines.testlib)
 ;(:import)
 )

(deftest SimulatedAnnealingSearchEngine-key-should-be-strawman
  (let [searchEngine (new edu.wvu.lcsee.green.searchEngines.SimulatedAnnealingSearchEngine 500 100000 0.3)]
    (is (= "sa" (.getKey searchEngine)))))


(deftest SimulatedAnnealingSearchEngine-should-search
  (let [searchEngine (new edu.wvu.lcsee.green.searchEngines.SimulatedAnnealingSearchEngine  50 100000 0.3)
        evaluationFunction (generate-mock-evaluationFunction (fn [_] (rand 10)))
        initialScenario (generate-mock-scenario)
        result (.search searchEngine evaluationFunction initialScenario)]
    (is (states-sorted-by-score? result))
    (is result)))