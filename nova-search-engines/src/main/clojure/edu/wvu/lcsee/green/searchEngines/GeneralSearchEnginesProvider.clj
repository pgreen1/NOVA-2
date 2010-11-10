(ns edu.wvu.lcsee.green.searchEngines.GeneralSearchEnginesProvider
  (:gen-class
      :implements [edu.wvu.lcsee.green.search.spi.SearchEnginesProvider]
      :init init
      :constructors {[] []}
      :state state
      :prefix "sep-impl-")
  (:require (edu.wvu.lcsee.green.searchEngines StrawManSearchEngine))
  ;(:use )
  (:import (com.google.common.collect ImmutableSet))
  )

(defn sep-impl-init []
  [[] {:searchEngines (ImmutableSet/of
                        (new edu.wvu.lcsee.green.searchEngines.StrawManSearchEngine))}])

(defn sep-impl-getSearchEngines [this]
  (get (.state this) :searchEngines))