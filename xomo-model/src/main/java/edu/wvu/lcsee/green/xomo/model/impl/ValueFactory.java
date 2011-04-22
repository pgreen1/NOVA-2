package edu.wvu.lcsee.green.xomo.model.impl;

import edu.wvu.lcsee.green.xomo.model.CocomoLevel;
import edu.wvu.lcsee.green.xomo.model.DefectRemovalValue;
import edu.wvu.lcsee.green.xomo.model.DefectsIntroducedSlopesValue;
import edu.wvu.lcsee.green.xomo.model.DefectsRemovedSlopesValue;
import edu.wvu.lcsee.green.xomo.model.EffortMultiplierValue;
import edu.wvu.lcsee.green.xomo.model.ScaleFactorValue;
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
          @Nonnull final double effortCoefficentSlope,
          @Nonnull final DefectsIntroducedSlopesValue emDefectsIntroducedSlopes) {
    return new EffortMultiplierValueImpl(
            level,
            calcValueFromPivotedLine(level.getNumericValue(), effortCoefficentSlope),
            calcValueFromPivotedLine(level.getNumericValue(), emDefectsIntroducedSlopes.
            getRequirmentsDefectsIntroducedSlope()),
            calcValueFromPivotedLine(level.getNumericValue(),
            emDefectsIntroducedSlopes.getDesignDefectsIntroducedSlope()),
            calcValueFromPivotedLine(level.getNumericValue(),
            emDefectsIntroducedSlopes.getCodingDefectsIntroducedSlope()));
  }

  public static ScaleFactorValue newScaleFactorValue(@Nonnull final CocomoLevel level,
          @Nonnull final double effortCoefficentSlope,
          @Nonnull final DefectsIntroducedSlopesValue sfDefectsIntroducedSlopes) {
    return new ScaleFactorValueImpl(
            level,
            calcValueFromHingedLine(level.getNumericValue(), effortCoefficentSlope),
            calcValueFromPivotedLine(level.getNumericValue(), sfDefectsIntroducedSlopes.
            getRequirmentsDefectsIntroducedSlope()),
            calcValueFromPivotedLine(level.getNumericValue(),
            sfDefectsIntroducedSlopes.getDesignDefectsIntroducedSlope()),
            calcValueFromPivotedLine(level.getNumericValue(),
            sfDefectsIntroducedSlopes.getCodingDefectsIntroducedSlope()));
  }

  public static DefectRemovalValue newDefectRemoval(@Nonnull final CocomoLevel level,
          @Nonnull final DefectsRemovedSlopesValue defectsRemovedSlopes) {
    return new DefectRemovalValueImpl(
            level,
            calcValueFromCoqualmoHingedLine(level.getNumericValue(), defectsRemovedSlopes.
            getRequirmentsDefectsRemovedSlope()),
            calcValueFromCoqualmoHingedLine(level.getNumericValue(), defectsRemovedSlopes.getDesignDefectsRemovedSlope()),
            calcValueFromCoqualmoHingedLine(level.getNumericValue(), defectsRemovedSlopes.getCodingDefectsRemovedSlope()));
  }
}
