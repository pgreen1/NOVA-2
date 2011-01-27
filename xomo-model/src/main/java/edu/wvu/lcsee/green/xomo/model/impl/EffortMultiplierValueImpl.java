package edu.wvu.lcsee.green.xomo.model.impl;

import edu.wvu.lcsee.green.xomo.model.CocomoLevel;
import edu.wvu.lcsee.green.xomo.model.EffortMultiplierValue;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public class EffortMultiplierValueImpl implements EffortMultiplierValue {

  private final CocomoLevel level;
  private final double effortCoefficent;
  private final double requirmentsDefects;
  private final double designDefects;
  private final double codingDefects;

  EffortMultiplierValueImpl(@Nonnull final CocomoLevel level, @Nonnull final double effortCoefficent,
          @Nonnull final double requirmentsDefects, @Nonnull final double designDefects,
          @Nonnull final double codingDefects) {
    this.level = level;
    this.effortCoefficent = effortCoefficent;
    this.requirmentsDefects = requirmentsDefects;
    this.designDefects = designDefects;
    this.codingDefects = codingDefects;
  }

  @Override
  public CocomoLevel getLevel() {
    return level;
  }

  @Override
  public double getEffortCoefficient() {
    return effortCoefficent;
  }

  @Override
  public double getRequirementsDefects() {
    return requirmentsDefects;
  }

  @Override
  public double getDesignDefects() {
    return designDefects;
  }

  @Override
  public double getCodingDefects() {
    return codingDefects;
  }
}
