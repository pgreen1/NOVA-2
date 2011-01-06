(ns edu.wvu.lcsee.green.searchEngines.searchlib
  ;(:require )
  ;(:use )
  (:import (edu.wvu.lcsee.green.search.impl StateImpl))
  )

(defn createStateFrom 
  "Creates a state from an evaluationFunction and a scenario"
  [evaluationFunction scenario]
  (new StateImpl
    scenario
    (.evaluate evaluationFunction scenario)))


(defmacro statesGenerator2path 
  "This macro is to be used for implimentations of SearchEngine to create the path.
   The start and end times will be marked when evaluation the statesGenerator expression.
   Finally, the result of the expression will be converted into an ImmutableList and a 
   new Path will be created."
   {:added "2.0.0"}
  [statesGenerator]
  `(let [startTime# (new java.util.Date)
        states# (com.google.common.collect.ImmutableList/copyOf ~statesGenerator)
        endTime#  (new java.util.Date)]
    (new edu.wvu.lcsee.green.search.impl.PathImpl startTime# endTime# states#)))