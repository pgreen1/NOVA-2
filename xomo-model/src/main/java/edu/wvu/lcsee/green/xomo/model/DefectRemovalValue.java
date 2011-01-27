package edu.wvu.lcsee.green.xomo.model;

import java.io.Serializable;

/**
 *
 * @author pdgreen
 */
public interface DefectRemovalValue extends Serializable, CoqualmoValue {

  double getRequirementsDefectsRemoved();

  double getDesignDefectsRemoved();

  double getCodingDefectsRemoved();
}
