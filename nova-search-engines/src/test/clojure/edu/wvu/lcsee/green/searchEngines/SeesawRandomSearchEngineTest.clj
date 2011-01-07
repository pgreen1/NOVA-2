(ns edu.wvu.lcsee.green.searchEngines.SeesawRandomSearchEngineTest
 ;(:require)
 (:use
   clojure.test
   edu.wvu.lcsee.green.searchEngines.mockobjects
   edu.wvu.lcsee.green.searchEngines.testlib)
 ;(:import)
 )

(deftest RandomKeysSearchEngine-key-should-be-seesaw-r
  (let [searchEngine (new edu.wvu.lcsee.green.searchEngines.SeesawRandomSearchEngine)]
    (is (= "seesaw-r" (.getKey searchEngine)))))


(deftest RandomKeysSearchEngine-should-search
  (let [searchEngine (new edu.wvu.lcsee.green.searchEngines.SeesawRandomSearchEngine)
        evaluationFunction (generate-mock-evaluationFunction (fn [_] (rand 10)))
        initialScenario (generate-mock-scenario)
        result-path (.search searchEngine evaluationFunction initialScenario)]
    (is-proper-path? result-path)))