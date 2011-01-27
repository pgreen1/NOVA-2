package edu.wvu.lcsee.green.xomo.model;

import java.io.Serializable;

/**
 *
 * @author pdgreen
 */
public interface DefectIntroducerValue extends Serializable, CoqualmoValue {

  double getRequirementsDefects();

  double getDesignDefects();

  double getCodingDefects();
}
