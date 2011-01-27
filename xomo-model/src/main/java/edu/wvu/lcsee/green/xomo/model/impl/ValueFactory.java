package edu.wvu.lcsee.green.xomo.model.impl;

import edu.wvu.lcsee.green.xomo.model.CocomoLevel;
import edu.wvu.lcsee.green.xomo.model.DefectRemovalValue;
import edu.wvu.lcsee.green.xomo.model.EffortMultiplierValue;
import edu.wvu.lcsee.green.xomo.model.ScaleFactorValue;
import edu.wvu.lcsee.green.xomo.model.XomoSlopesValue;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public final class ValueFactory {

  static double calcValueFromPivotedLine(@Nonnull final double x, @Nonnull final double m) {
    return m * (x - 3) + 1;
  }

  static double calcValueFromHingedLine(@Nonnull final double x, @Nonnull final double m) {
    return m * (x - 6);
  }

  static double calcValueFromCoqualmoHingedLine(@Nonnull final double x, @Nonnull final double m) {
    return m * (x - 1);
  }

  public static EffortMultiplierValue newEffortMuliplierValue(@Nonnull final CocomoLevel level,
          @Nonnull final XomoSlopesValue slopes) {
    return new EffortMultiplierValueImpl(
            level,
            calcValueFromPivotedLine(level.getNumericValue(), slopes.getEffortCoefficentSlope()),
            calcValueFromPivotedLine(level.getNumericValue(), slopes.getEmRequirmentsDefectsIntroducedSlope()),
            calcValueFromPivotedLine(level.getNumericValue(), slopes.getEmDesignDefectsIntroducedSlope()),
            calcValueFromPivotedLine(level.getNumericValue(), slopes.getEmCodingDefectsIntroducedSlope()));
  }

  public static ScaleFactorValue newScaleFactorValue(@Nonnull final CocomoLevel level,
          @Nonnull final XomoSlopesValue slopes) {
    return new ScaleFactorValueImpl(
            level,
            calcValueFromHingedLine(level.getNumericValue(), slopes.getEffortCoefficentSlope()),
            calcValueFromPivotedLine(level.getNumericValue(), slopes.getSfRequirmentsDefectsIntroducedSlope()),
            calcValueFromPivotedLine(level.getNumericValue(), slopes.getSfDesignDefectsIntroducedSlope()),
            calcValueFromPivotedLine(level.getNumericValue(), slopes.getSfCodingDefectsIntroducedSlope()));
  }

  public static DefectRemovalValue newDefectRemoval(@Nonnull final CocomoLevel level,
          @Nonnull final XomoSlopesValue slopes) {
    return new DefectRemovalValueImpl(
            calcValueFromCoqualmoHingedLine(level.getNumericValue(), slopes.getRequirmentsDefectsRemovedSlope()),
            calcValueFromCoqualmoHingedLine(level.getNumericValue(), slopes.getDesignDefectsRemovedSlope()),
            calcValueFromCoqualmoHingedLine(level.getNumericValue(), slopes.getCodingDefectsRemovedSlope()));
  }
}
