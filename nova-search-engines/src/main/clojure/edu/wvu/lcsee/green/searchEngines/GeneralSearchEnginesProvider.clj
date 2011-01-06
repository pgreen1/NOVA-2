(ns edu.wvu.lcsee.green.searchEngines.GeneralSearchEnginesProvider
  (:gen-class
      :implements [edu.wvu.lcsee.green.search.spi.SearchEnginesProvider]
      :init init
      :constructors {[] []}
      :state state
      :prefix "sep-impl-")
  (:require (edu.wvu.lcsee.green.searchEngines StrawManSearchEngine IsampSearchEngine SimulatedAnnealingSearchEngine SeesawRandomSearchEngine SeesawDeterministicSearchEngine))
  ;(:use )
  (:import (com.google.common.collect ImmutableSet))
  )

(defn sep-impl-init []
  [[] {:searchEngines (ImmutableSet/of
                        (new edu.wvu.lcsee.green.searchEngines.StrawManSearchEngine)
                        (new edu.wvu.lcsee.green.searchEngines.IsampSearchEngine)
                        (new edu.wvu.lcsee.green.searchEngines.SimulatedAnnealingSearchEngine 500 100000000 0.3)
                        (new edu.wvu.lcsee.green.searchEngines.SeesawRandomSearchEngine)
                        (new edu.wvu.lcsee.green.searchEngines.SeesawDeterministicSearchEngine))}])

(defn sep-impl-getSearchEngines [this]
  (get (.state this) :searchEngines))
