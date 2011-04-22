package edu.wvu.lcsee.green.xomo.model;

import java.io.Serializable;

/**
 * /**
 * A value of a COQUALMO DefectRemoval Attribute. It contains defects removed at different parts of the development life cycle.
 * @author pdgreen
 */
public interface DefectRemovalValue extends Serializable, CocomoValue, CoqualmoValue {

  double getRequirementsDefectsRemoved();

  double getDesignDefectsRemoved();

  double getCodingDefectsRemoved();
}
