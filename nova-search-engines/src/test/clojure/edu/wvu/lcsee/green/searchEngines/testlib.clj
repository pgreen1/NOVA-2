(ns edu.wvu.lcsee.green.searchEngines.testlib
  ;(:require )
 (:use
   clojure.test)
  ;(:import )
  )



(defn is-proper-path?
  "checks that path was created properly:
  1) end time not before start time
  2) states with a size of atleast 1
  3) states sorted by score"
  [path]
  (do
    (is path "path should not be null")
    (let [states (seq (.getStates path))
          sorted-states (sort-by (memfn getScore) states)]
      (is states "there must be atleast one state")
      (is (= states sorted-states) "states should be sorted")
      (is (not (.after (.getTimeStarted path) (.getTimeEnded path))) "timeStarted should not be after timeEnded"))))
