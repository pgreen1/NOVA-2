package edu.wvu.lcsee.green.xomo.model.impl;

import com.google.common.base.Preconditions;
import edu.wvu.lcsee.green.xomo.model.CocomoLevel;
import edu.wvu.lcsee.green.xomo.model.DefectRemovalValue;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public class DefectRemovalValueImpl implements DefectRemovalValue {

  private final CocomoLevel level;
  private final double requirmentsDefectsRemoved;
  private final double designDefectsRemoved;
  private final double codingDefectsRemoved;

  DefectRemovalValueImpl(@Nonnull final CocomoLevel level,
          @Nonnegative final double requirmentsDefectsRemoved,
          @Nonnegative final double designDefectsRemoved,
          @Nonnegative final double codingDefectsRemoved) {
    this.level = Preconditions.checkNotNull(level);
    this.requirmentsDefectsRemoved = requirmentsDefectsRemoved;
    this.designDefectsRemoved = designDefectsRemoved;
    this.codingDefectsRemoved = codingDefectsRemoved;
  }

  @Override
  public CocomoLevel getLevel() {
    return level;
  }

  @Override
  public double getEffortCoefficient() {
    throw new UnsupportedOperationException("Not supported yet because this method should be removed from CocomoValue");
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
