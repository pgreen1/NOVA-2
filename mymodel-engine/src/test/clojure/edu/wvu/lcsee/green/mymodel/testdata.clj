(ns edu.wvu.lcsee.green.mymodel.testdata
  ;(:require )
 (:use
   clojure.test)
  (:import (edu.wvu.lcsee.green.mymodel.model MyModelAttribute MyModelAttribute$ProjectSize)
           (edu.wvu.lcsee.green.model.impl ProjectImpl) )
  )



(defn generateMymodelProject
  "generates a mymodel project"
  []
  (new ProjectImpl
    (hash-map
      MyModelAttribute/FF  0.4
      MyModelAttribute/MEXP 3
      MyModelAttribute/PSIZE MyModelAttribute$ProjectSize/LARGE)))