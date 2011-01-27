package edu.wvu.lcsee.green.xomo.model.impl;

import edu.wvu.lcsee.green.xomo.model.DefectRemovalValue;

/**
 *
 * @author pdgreen
 */
public class DefectRemovalValueImpl implements DefectRemovalValue {

  private final double requirmentsDefectsRemoved;
  private final double designDefectsRemoved;
  private final double codingDefectsRemoved;

  DefectRemovalValueImpl(double requirmentsDefectsRemoved, double designDefectsRemoved,
          double codingDefectsRemoved) {
    this.requirmentsDefectsRemoved = requirmentsDefectsRemoved;
    this.designDefectsRemoved = designDefectsRemoved;
    this.codingDefectsRemoved = codingDefectsRemoved;
  }

  @Override
  public double getRequirementsDefectsRemoved() {
    return requirmentsDefectsRemoved;
  }

  @Override
  public double getDesignDefectsRemoved() {
    return designDefectsRemoved;
  }

  @Override
  public double getCodingDefectsRemoved() {
    return codingDefectsRemoved;
  }
}
