package edu.wvu.lcsee.green.xomo.model;

import java.io.Serializable;

/**
 *
 * @author pdgreen
 */
public interface XomoSlopesValue extends Serializable {

  double getEffortCoefficentSlope();

  double getEmRequirmentsDefectsIntroducedSlope();

  double getEmDesignDefectsIntroducedSlope();

  double getEmCodingDefectsIntroducedSlope();

  double getSfRequirmentsDefectsIntroducedSlope();

  double getSfDesignDefectsIntroducedSlope();

  double getSfCodingDefectsIntroducedSlope();

  double getRequirmentsDefectsRemovedSlope();

  double getDesignDefectsRemovedSlope();

  double getCodingDefectsRemovedSlope();
}
