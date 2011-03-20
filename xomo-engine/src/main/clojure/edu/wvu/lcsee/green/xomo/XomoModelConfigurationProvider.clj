(ns edu.wvu.lcsee.green.xomo.XomoModelConfigurationProvider
 (:gen-class
   :implements [edu.wvu.lcsee.green.model.spi.ModelConfigurationProvider])
 (:require (edu.wvu.lcsee.green.xomo XomoModelConfigurationImpl EffortScoringFunction ScheduleScoringFunction))
 (:import (edu.wvu.lcsee.green.xomo.model CocomoLevel CocomoParameters XomoSlopesAttribute ScaleFactorAttribute EffortMultiplierAttribute DefectRemovalAttribute)
   (edu.wvu.lcsee.green.model Attribute ModelConfigurationBuilder)
   (edu.wvu.lcsee.green.model.impl SetConstraints RangeConstraints))
 )

(defn -getModelConfiguration [this]
  (new edu.wvu.lcsee.green.xomo.XomoModelConfigurationImpl
    (.build
      (doto (ModelConfigurationBuilder/newInstance)
        (.addUnconstrainableAttribute CocomoParameters/KLOC (new RangeConstraints 2 2000) (into-array Attribute nil) )
        (.addUnconstrainableAttribute CocomoParameters/A    (new RangeConstraints 2 2000) (into-array Attribute nil) )
        (.addUnconstrainableAttribute CocomoParameters/B    (new RangeConstraints 2 2000) (into-array Attribute (list CocomoParameters/A)) )
        (.addUnconstrainableAttribute CocomoParameters/C    (new RangeConstraints 2 2000) (into-array Attribute nil) )
        (.addUnconstrainableAttribute CocomoParameters/D    (new RangeConstraints 2 2000) (into-array Attribute (list CocomoParameters/C)) )

        ;(.addConstrainableAttribute MyModelAttribute/MEXP (new SetConstraints (set (list 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30))) (make-array Attribute 0))
        ;(.addConstrainableAttribute MyModelAttribute/PSIZE (new SetConstraints (MyModelAttribute$ProjectSize/values)) (make-array Attribute 0) )
        (.addScoringFunction (new edu.wvu.lcsee.green.xomo.EffortScoringFunction))
        (.addScoringFunction (new edu.wvu.lcsee.green.xomo.ScheduleScoringFunction))))))





