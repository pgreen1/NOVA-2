package edu.wvu.lcsee.green.xomo.model;

import java.io.Serializable;

/**
 *
 * @author pdgreen
 */
public interface DefectsIntroducedSlopesValue extends Serializable {

  double getRequirmentsDefectsIntroducedSlope();

  double getDesignDefectsIntroducedSlope();

  double getCodingDefectsIntroducedSlope();
}
