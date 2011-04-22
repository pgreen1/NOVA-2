(ns edu.wvu.lcsee.green.xomo.testdata
 ;(:require )
 (:use
   clojure.test)
 (:import (edu.wvu.lcsee.green.xomo.model
            CocomoParameters CocomoSlopesAttribute CoqualmoSlopesAttribute
            ScaleFactorAttribute EffortMultiplierAttribute DefectRemovalAttribute
            
            CocomoLevel)
   (edu.wvu.lcsee.green.xomo.model.impl ValueFactory DefectsIntroducedSlopesValueImpl DefectsRemovedSlopesValueImpl)
   (edu.wvu.lcsee.green.model.impl ProjectImpl)
   (edu.wvu.lcsee.green.clojure.impl ProjectGeneratorImpl) )
 )

;;FIXME update test data with more accurate values
(defn generateXomoProject
  "generates a xomo project"
  []
  (let [effort-coefficient-slope 0.5
        sf-defects-introduced-slopes (new DefectsIntroducedSlopesValueImpl 0.1 0.4 1.0)
        em-defects-introduced-slopes (new DefectsIntroducedSlopesValueImpl 0.1 0.4 1.0)
        defects-removed-slopes (new DefectsRemovedSlopesValueImpl 0.2 0.4 0.6)]
    (new ProjectImpl
      (hash-map
        CocomoParameters/KLOC  20
        CocomoParameters/A  0.1
        CocomoParameters/B  2.1
        CocomoParameters/C  4.2
        CocomoParameters/D  1.2
        CocomoSlopesAttribute/SF_EFFORT_COEFFICIENT_SLOPE effort-coefficient-slope
        CocomoSlopesAttribute/EM_PLUS_EFFORT_COEFFICIENT_SLOPE effort-coefficient-slope
        CocomoSlopesAttribute/EM_MINUS_EFFORT_COEFFICIENT_SLOPE effort-coefficient-slope
        CoqualmoSlopesAttribute/SF_DEFECTS_INTRODUCED_SLOPES sf-defects-introduced-slopes
        CoqualmoSlopesAttribute/EM_PLUS_DEFECTS_INTRODUCED_SLOPES em-defects-introduced-slopes
        CoqualmoSlopesAttribute/EM_MINUS_DEFECTS_INTRODUCED_SLOPES em-defects-introduced-slopes
        CoqualmoSlopesAttribute/DEFECT_REMOVAL_SLOPES defects-removed-slopes
        ScaleFactorAttribute/FLEX (ValueFactory/newScaleFactorValue CocomoLevel/N effort-coefficient-slope sf-defects-introduced-slopes)
        ScaleFactorAttribute/PMAT (ValueFactory/newScaleFactorValue CocomoLevel/N effort-coefficient-slope sf-defects-introduced-slopes)
        ScaleFactorAttribute/PREC (ValueFactory/newScaleFactorValue CocomoLevel/N effort-coefficient-slope sf-defects-introduced-slopes)
        ScaleFactorAttribute/RESL (ValueFactory/newScaleFactorValue CocomoLevel/N effort-coefficient-slope sf-defects-introduced-slopes)
        ScaleFactorAttribute/TEAM (ValueFactory/newScaleFactorValue CocomoLevel/N effort-coefficient-slope sf-defects-introduced-slopes)
        EffortMultiplierAttribute/ACAP (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/AEXP (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/CPLX (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/DATA (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/DOCU (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/LTEX (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/PCAP (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/PCON (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/PEXP (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/PVOL (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/RELY (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/RUSE (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/SCED (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/SITE (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/STOR (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/TIME (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        EffortMultiplierAttribute/TOOL (ValueFactory/newEffortMuliplierValue CocomoLevel/N effort-coefficient-slope em-defects-introduced-slopes)
        DefectRemovalAttribute/AA (ValueFactory/newDefectRemoval CocomoLevel/N defects-removed-slopes)
        DefectRemovalAttribute/ETAT (ValueFactory/newDefectRemoval CocomoLevel/N defects-removed-slopes)
        DefectRemovalAttribute/PR (ValueFactory/newDefectRemoval CocomoLevel/N defects-removed-slopes)
        ))))

(defn generateProjectGenerator
  "generates a project generator"
  []
  (new edu.wvu.lcsee.green.clojure.impl.ProjectGeneratorImpl))