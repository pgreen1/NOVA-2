package edu.wvu.lcsee.green.xomo.model;

import java.io.Serializable;

/**
 * A value of a COQUALMO DefectIntroducer Attribute. It contains defects introduces at different parts of the development life cycle.
 * @author pdgreen
 */
public interface DefectIntroducerValue extends Serializable, CoqualmoValue {

  double getRequirementsDefects();

  double getDesignDefects();

  double getCodingDefects();
}
