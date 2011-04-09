(ns edu.wvu.lcsee.green.xomo.testdata
 ;(:require )
 (:use
   clojure.test)
 (:import (edu.wvu.lcsee.green.xomo.model
            CocomoParameters XomoSlopesAttribute
            ScaleFactorAttribute EffortMultiplierAttribute DefectRemovalAttribute
            
            CocomoLevel)
   (edu.wvu.lcsee.green.xomo.model.impl ValueFactory XomoSlopesValueImpl)
   (edu.wvu.lcsee.green.model.impl ProjectImpl) )
 )



;;FIXME update test data with more accurate values
(defn generateXomoProject
  "generates a xomo project"
  []
  (let [xomo-slopes (new XomoSlopesValueImpl 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5)]
    (new ProjectImpl
      (hash-map
        CocomoParameters/KLOC  20
        CocomoParameters/A  0.1
        CocomoParameters/B  2.1
        CocomoParameters/C  4.2
        CocomoParameters/D  1.2
        XomoSlopesAttribute/DEFECT_REMOVAL_SLOPE xomo-slopes
        ScaleFactorAttribute/FLEX (ValueFactory/newScaleFactorValue CocomoLevel/N xomo-slopes)
        ScaleFactorAttribute/PMAT (ValueFactory/newScaleFactorValue CocomoLevel/N xomo-slopes)
        ScaleFactorAttribute/PREC (ValueFactory/newScaleFactorValue CocomoLevel/N xomo-slopes)
        ScaleFactorAttribute/RESL (ValueFactory/newScaleFactorValue CocomoLevel/N xomo-slopes)
        ScaleFactorAttribute/TEAM (ValueFactory/newScaleFactorValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/ACAP (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/AEXP (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/CPLX (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/DATA (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/DOCU (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/LTEX (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/PCAP (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/PCON (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/PEXP (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/PVOL (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/RELY (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/RUSE (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/SCED (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/SITE (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/STOR (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/TIME (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        EffortMultiplierAttribute/TOOL (ValueFactory/newEffortMuliplierValue CocomoLevel/N xomo-slopes)
        DefectRemovalAttribute/AA (ValueFactory/newDefectRemoval CocomoLevel/N xomo-slopes)
        DefectRemovalAttribute/ETAT (ValueFactory/newDefectRemoval CocomoLevel/N xomo-slopes)
        DefectRemovalAttribute/PR (ValueFactory/newDefectRemoval CocomoLevel/N xomo-slopes)
        ))))