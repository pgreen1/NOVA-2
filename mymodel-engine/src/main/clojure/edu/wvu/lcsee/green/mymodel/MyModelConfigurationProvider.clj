(ns edu.wvu.lcsee.green.mymodel.MyModelConfigurationProvider
 (:gen-class
   :implements [edu.wvu.lcsee.green.model.spi.ModelConfigurationProvider])
 (:require (edu.wvu.lcsee.green.mymodel MyModelConfigurationImpl CostScoringFunction DurationScoringFunction))
 (:import (edu.wvu.lcsee.green.mymodel.model MyModelAttribute MyModelAttribute$ProjectSize)
   (edu.wvu.lcsee.green.model Attribute ModelConfigurationBuilder)
   (edu.wvu.lcsee.green.model.impl SetConstraints))
 )

(defn -getModelConfiguration [this]
  (new edu.wvu.lcsee.green.mymodel.MyModelConfigurationImpl
    (.build
      (doto (ModelConfigurationBuilder/newInstance)
        (.addUnconstrainableAttribute MyModelAttribute/FF (new SetConstraints (set (list 0.0, 0.25, 0.50, 0.75, 1.0))) (make-array Attribute 0) )
        (.addConstrainableAttribute MyModelAttribute/MEXP (new SetConstraints (set (list 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30))) (make-array Attribute 0))
        (.addConstrainableAttribute MyModelAttribute/PSIZE (new SetConstraints (MyModelAttribute$ProjectSize/values)) (make-array Attribute 0) )
        (.addScoringFunction (new edu.wvu.lcsee.green.mymodel.CostScoringFunction))
        (.addScoringFunction (new edu.wvu.lcsee.green.mymodel.DurationScoringFunction))))))





