(ns edu.wvu.lcsee.green.xomo.XomoModelConfigurationProvider
 (:gen-class
   :implements [edu.wvu.lcsee.green.model.spi.ModelConfigurationProvider])
 (:require (edu.wvu.lcsee.green.xomo
             XomoModelConfigurationImpl EffortScoringFunction ScheduleScoringFunction))
 (:import (edu.wvu.lcsee.green.xomo.model 
            CocomoLevel CocomoParameters
            CocomoSlopesAttribute CoqualmoSlopesAttribute
            ScaleFactorAttribute ScaleFactorValueConstraints
            EffortMultiplierAttribute EffortMultiplierValueConstraints
            DefectRemovalAttribute
            DefectsIntroducedSlopesValueConstraints DefectsRemovedSlopesValueConstraints
            DefectRemovalValueConstraints)
   (edu.wvu.lcsee.green.model Attribute
     ModelConfigurationBuilder)
   (edu.wvu.lcsee.green.model.impl
     SetConstraints RangeConstraints))
 )

(defn -getModelConfiguration [this]
  (new edu.wvu.lcsee.green.xomo.XomoModelConfigurationImpl
    (.build
      (doto (ModelConfigurationBuilder/newInstance)
        ;FIXME add in correlation between A/B and C/D
        (.addUnconstrainableAttribute CocomoParameters/KLOC (new RangeConstraints 2   2000     10    ) (into-array Attribute nil) )
        (.addUnconstrainableAttribute CocomoParameters/A    (new RangeConstraints 2     11      0.1  ) (into-array Attribute nil) )
        (.addUnconstrainableAttribute CocomoParameters/B    (new RangeConstraints 0.5    1      0.01 ) (into-array Attribute (list CocomoParameters/A)) )
        (.addUnconstrainableAttribute CocomoParameters/C    (new RangeConstraints 3      3.67   0.01 ) (into-array Attribute nil) )
        (.addUnconstrainableAttribute CocomoParameters/D    (new RangeConstraints 0.28   0.33   0.001) (into-array Attribute (list CocomoParameters/C)) )
        (.addUnconstrainableAttribute CocomoSlopesAttribute/SF_EFFORT_COEFFICIENT_SLOPE    (new RangeConstraints -1.56   -1.01   0.005) (into-array Attribute nil) )
        (.addUnconstrainableAttribute CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE    (new RangeConstraints 0.07   0.21   0.005) (into-array Attribute nil) )
        (.addUnconstrainableAttribute CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE    (new RangeConstraints -0.18   -0.08   0.005) (into-array Attribute nil) )
        (.addUnconstrainableAttribute CoqualmoSlopesAttribute/SF_DEFECTS_INTRODUCED_SLOPES  (new DefectsIntroducedSlopesValueConstraints
                                                                                               (new RangeConstraints -0.1  -0.04   0.005)
                                                                                               (new RangeConstraints -0.21 -0.05   0.005)
                                                                                               (new RangeConstraints -0.19 -0.05   0.005)) (into-array Attribute nil) )
        (.addUnconstrainableAttribute CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES  (new DefectsIntroducedSlopesValueConstraints
                                                                                               (new RangeConstraints  0.00  0.11   0.005)
                                                                                               (new RangeConstraints  0.00  0.14   0.005)
                                                                                               (new RangeConstraints  0.00  0.14   0.005)) (into-array Attribute nil) )
        (.addUnconstrainableAttribute CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES  (new DefectsIntroducedSlopesValueConstraints
                                                                                               (new RangeConstraints -0.18 -0.04   0.005)
                                                                                               (new RangeConstraints -0.21 -0.05   0.005)
                                                                                               (new RangeConstraints -0.19 -0.05   0.005)) (into-array Attribute nil) )
        (.addUnconstrainableAttribute CoqualmoSlopesAttribute/DEFECT_REMOVAL_SLOPES  (new DefectsRemovedSlopesValueConstraints
                                                                                               (new RangeConstraints 0.08   0.14   0.005)
                                                                                               (new RangeConstraints 0.10   0.16   0.005)
                                                                                               (new RangeConstraints 0.11   0.18   0.005)) (into-array Attribute nil) )
        ;; Scale Factors
        (.addConstrainableAttribute ScaleFactorAttribute/FLEX (new ScaleFactorValueConstraints
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/SF_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/SF_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute ScaleFactorAttribute/PMAT (new ScaleFactorValueConstraints 
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/SF_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/SF_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute ScaleFactorAttribute/PREC (new ScaleFactorValueConstraints 
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/SF_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/SF_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute ScaleFactorAttribute/RESL (new ScaleFactorValueConstraints 
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/SF_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/SF_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute ScaleFactorAttribute/TEAM (new ScaleFactorValueConstraints 
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/SF_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/SF_DEFECTS_INTRODUCED_SLOPES)))
        ;; Effort Multipliers
        (.addConstrainableAttribute EffortMultiplierAttribute/ACAP (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute EffortMultiplierAttribute/AEXP (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute EffortMultiplierAttribute/CPLX (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute EffortMultiplierAttribute/DATA (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/L CocomoLevel/VH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute EffortMultiplierAttribute/DOCU (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute EffortMultiplierAttribute/LTEX (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES)))
        ;TODO PCAP needs to zero out RIN
        (.addConstrainableAttribute EffortMultiplierAttribute/PCAP (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute EffortMultiplierAttribute/PCON (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute EffortMultiplierAttribute/PEXP (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute EffortMultiplierAttribute/PVOL (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/L CocomoLevel/VH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES)))
        ;;TODO add in augmentation with AA, ETAT, and PR
        (.addConstrainableAttribute EffortMultiplierAttribute/RELY (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute EffortMultiplierAttribute/RUSE(new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/L CocomoLevel/XH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute EffortMultiplierAttribute/SCED (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute EffortMultiplierAttribute/SITE (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute EffortMultiplierAttribute/STOR (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/N CocomoLevel/XH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES)))
        (.addConstrainableAttribute EffortMultiplierAttribute/TIME (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/N CocomoLevel/XH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES)))
        ;;TODO add in augmentation with AA and ETAT
        (.addConstrainableAttribute EffortMultiplierAttribute/TOOL (new EffortMultiplierValueConstraints
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH))
                                                              (into-array Attribute (list CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                                          CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES)))
        ;; Defect Removals
        (.addConstrainableAttribute DefectRemovalAttribute/AA (new DefectRemovalValueConstraints 
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH))
                                                              (into-array Attribute (list CoqualmoSlopesAttribute/DEFECT_REMOVAL_SLOPES)))
         (.addConstrainableAttribute DefectRemovalAttribute/ETAT (new DefectRemovalValueConstraints
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH))
                                                              (into-array Attribute (list CoqualmoSlopesAttribute/DEFECT_REMOVAL_SLOPES)))
         (.addConstrainableAttribute DefectRemovalAttribute/PR (new DefectRemovalValueConstraints
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH))
                                                              (into-array Attribute (list CoqualmoSlopesAttribute/DEFECT_REMOVAL_SLOPES)))
        (.addScoringFunction (new edu.wvu.lcsee.green.xomo.EffortScoringFunction))
        (.addScoringFunction (new edu.wvu.lcsee.green.xomo.ScheduleScoringFunction))))))





