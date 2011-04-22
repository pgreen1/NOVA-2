package edu.wvu.lcsee.green.xomo.model;

import java.io.Serializable;

/**
 *
 * @author pdgreen
 */
public interface DefectsRemovedSlopesValue extends Serializable {

  double getRequirmentsDefectsRemovedSlope();

  double getDesignDefectsRemovedSlope();

  double getCodingDefectsRemovedSlope();
}
