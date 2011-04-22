package edu.wvu.lcsee.green.xomo.model.impl;

import edu.wvu.lcsee.green.xomo.model.DefectsRemovedSlopesValue;

/**
 *
 * @author pdgreen
 */
public class DefectsRemovedSlopesValueImpl implements DefectsRemovedSlopesValue {

  private final double requirmentsDefectsRemovedSlope;
  private final double designDefectsRemovedSlope;
  private final double codingDefectsRemovedSlope;

  public DefectsRemovedSlopesValueImpl(double requirmentsDefectsRemovedSlope, double designDefectsRemovedSlope,
          double codingDefectsRemovedSlope) {
    this.requirmentsDefectsRemovedSlope = requirmentsDefectsRemovedSlope;
    this.designDefectsRemovedSlope = designDefectsRemovedSlope;
    this.codingDefectsRemovedSlope = codingDefectsRemovedSlope;
  }

  @Override
  public double getCodingDefectsRemovedSlope() {
    return codingDefectsRemovedSlope;
  }

  @Override
  public double getDesignDefectsRemovedSlope() {
    return designDefectsRemovedSlope;
  }

  @Override
  public double getRequirmentsDefectsRemovedSlope() {
    return requirmentsDefectsRemovedSlope;
  }
}
