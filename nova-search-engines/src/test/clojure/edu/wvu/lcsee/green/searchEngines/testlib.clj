
(ns edu.wvu.lcsee.green.searchEngines.testlib
  ;(:require )
  ;(:use )
  ;(:import )
  )

(defn states-sorted-by-score? 
  "returns whether all of the states in a path are sorted ascendingly by score"
  [path]
  (let [states (seq (.getStates path))]
    (=
      states
      (sort-by (fn [state] (.getScore state))
        states))))
