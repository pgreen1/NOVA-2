package edu.wvu.lcsee.green.xomo.model.impl;

import edu.wvu.lcsee.green.xomo.model.DefectsIntroducedSlopesValue;

/**
 *
 * @author pdgreen
 */
public class DefectsIntroducedSlopesValueImpl implements DefectsIntroducedSlopesValue {

  private final double requirementsDefectsIntroducedSlope;
  private final double designDefectsIntroducedSlope;
  private final double codingDefectsIntroducedSlope;

  public DefectsIntroducedSlopesValueImpl(double requirementsDefectsIntroducedSlope,
          double designDefectsIntroducedSlope, double codingDefectsIntroducedSlope) {
    this.requirementsDefectsIntroducedSlope = requirementsDefectsIntroducedSlope;
    this.designDefectsIntroducedSlope = designDefectsIntroducedSlope;
    this.codingDefectsIntroducedSlope = codingDefectsIntroducedSlope;
  }

  @Override
  public double getRequirmentsDefectsIntroducedSlope() {
    return requirementsDefectsIntroducedSlope;
  }

  @Override
  public double getDesignDefectsIntroducedSlope() {
    return designDefectsIntroducedSlope;
  }

  @Override
  public double getCodingDefectsIntroducedSlope() {
    return codingDefectsIntroducedSlope;
  }
}
