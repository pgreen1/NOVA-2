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
        (.addUnconstrainableAttribute CocomoParameters/KLOC (new RangeConstraints 2.0  2000.0   10.0    ))
        (.addUnconstrainableAttribute CocomoParameters/A    (new RangeConstraints 2.0    11.0    0.1  ))
        (.addUnconstrainableAttribute CocomoParameters/B    (new RangeConstraints 0.5     1.0    0.01 ))
        (.addUnconstrainableAttribute CocomoParameters/C    (new RangeConstraints 3.0     3.67   0.01 ))
        (.addUnconstrainableAttribute CocomoParameters/D    (new RangeConstraints 0.28    0.33   0.001))
        (.addUnconstrainableAttribute CocomoSlopesAttribute/SF_EFFORT_COEFFICIENT_SLOPE    (new RangeConstraints -1.56   -1.01   0.005))
        (.addUnconstrainableAttribute CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE    (new RangeConstraints 0.07   0.21   0.005))
        (.addUnconstrainableAttribute CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE    (new RangeConstraints -0.18   -0.08   0.005))
        (.addUnconstrainableAttribute CoqualmoSlopesAttribute/SF_DEFECTS_INTRODUCED_SLOPES  (new DefectsIntroducedSlopesValueConstraints
                                                                                              (new RangeConstraints -0.1  -0.04   0.005)
                                                                                              (new RangeConstraints -0.21 -0.05   0.005)
                                                                                              (new RangeConstraints -0.19 -0.05   0.005)))
        (.addUnconstrainableAttribute CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES  (new DefectsIntroducedSlopesValueConstraints
                                                                                                   (new RangeConstraints  0.00  0.11   0.005)
                                                                                                   (new RangeConstraints  0.00  0.14   0.005)
                                                                                                   (new RangeConstraints  0.00  0.14   0.005)))
        (.addUnconstrainableAttribute CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES  (new DefectsIntroducedSlopesValueConstraints
                                                                                                    (new RangeConstraints -0.18 -0.04   0.005)
                                                                                                    (new RangeConstraints -0.21 -0.05   0.005)
                                                                                                    (new RangeConstraints -0.19 -0.05   0.005)))
        (.addUnconstrainableAttribute CoqualmoSlopesAttribute/DEFECT_REMOVAL_SLOPES  (new DefectsRemovedSlopesValueConstraints
                                                                                       (new RangeConstraints 0.08   0.14   0.005)
                                                                                       (new RangeConstraints 0.10   0.16   0.005)
                                                                                       (new RangeConstraints 0.11   0.18   0.005)))
        ;; Scale Factors
        (.addConstrainableAttribute ScaleFactorAttribute/FLEX (new ScaleFactorValueConstraints
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH)))
        (.addConstrainableAttribute ScaleFactorAttribute/PMAT (new ScaleFactorValueConstraints 
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH)))
        (.addConstrainableAttribute ScaleFactorAttribute/PREC (new ScaleFactorValueConstraints 
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH)))
        (.addConstrainableAttribute ScaleFactorAttribute/RESL (new ScaleFactorValueConstraints 
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH)))
        (.addConstrainableAttribute ScaleFactorAttribute/TEAM (new ScaleFactorValueConstraints 
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH)))
        ;; Effort Multipliers
        (.addConstrainableAttribute EffortMultiplierAttribute/ACAP (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH)))
        (.addConstrainableAttribute EffortMultiplierAttribute/AEXP (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH)))
        (.addConstrainableAttribute EffortMultiplierAttribute/CPLX (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH)))
        (.addConstrainableAttribute EffortMultiplierAttribute/DATA (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/L CocomoLevel/VH)))
        (.addConstrainableAttribute EffortMultiplierAttribute/DOCU (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH)))
        (.addConstrainableAttribute EffortMultiplierAttribute/LTEX (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH)))
        ;TODO PCAP needs to zero out RIN
        (.addConstrainableAttribute EffortMultiplierAttribute/PCAP (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH)))
        (.addConstrainableAttribute EffortMultiplierAttribute/PCON (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH)))
        (.addConstrainableAttribute EffortMultiplierAttribute/PEXP (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH)))
        (.addConstrainableAttribute EffortMultiplierAttribute/PVOL (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/L CocomoLevel/VH)))
        ;;TODO add in augmentation with AA, ETAT, and PR
        (.addConstrainableAttribute EffortMultiplierAttribute/RELY (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH)))
        (.addConstrainableAttribute EffortMultiplierAttribute/RUSE(new EffortMultiplierValueConstraints
                                                                    CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                    CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES
                                                                    (CocomoLevel/constraintsFrom CocomoLevel/L CocomoLevel/XH)))
        (.addConstrainableAttribute EffortMultiplierAttribute/SCED (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH)))
        (.addConstrainableAttribute EffortMultiplierAttribute/SITE (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH)))
        (.addConstrainableAttribute EffortMultiplierAttribute/STOR (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/N CocomoLevel/XH)))
        (.addConstrainableAttribute EffortMultiplierAttribute/TIME (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/N CocomoLevel/XH)))
        ;;TODO add in augmentation with AA and ETAT
        (.addConstrainableAttribute EffortMultiplierAttribute/TOOL (new EffortMultiplierValueConstraints
                                                                     CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE
                                                                     CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES
                                                                     (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH)))
        ;; Defect Removals
        (.addConstrainableAttribute DefectRemovalAttribute/AA (new DefectRemovalValueConstraints 
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/XH)))
        (.addConstrainableAttribute DefectRemovalAttribute/ETAT (new DefectRemovalValueConstraints
                                                                  (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH)))
        (.addConstrainableAttribute DefectRemovalAttribute/PR (new DefectRemovalValueConstraints
                                                                (CocomoLevel/constraintsFrom CocomoLevel/VL CocomoLevel/VH)))
        (.addScoringFunction (new edu.wvu.lcsee.green.xomo.EffortScoringFunction))
        (.addScoringFunction (new edu.wvu.lcsee.green.xomo.ScheduleScoringFunction))))))





