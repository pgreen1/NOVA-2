package edu.wvu.lcsee.green.xomo.model;

import com.google.common.base.Preconditions;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.impl.SetConstraints;
import java.util.EnumSet;
import java.util.Set;
import javax.annotation.Nonnull;

/**
 * Levels are defined in the COCOMO Spec.  They range fro Extreme Lowe (XL) to Extreme High (XH).
 * @author pdgreen
 */
public enum CocomoLevel {

  XL(0), VL(1), L(2), N(3), H(4), VH(5), XH(6);
  private final int numericValue;

  private CocomoLevel(@Nonnull final int numericValue) {
    this.numericValue = numericValue;
  }

  public int getNumericValue() {
    return numericValue;
  }

  /**
   * Returns the enum constant of this type with the specified numeric value.
   *
   * @param numericValue numeric value of the desired CocomoLevel
   * @return the enum constant with the specified numeric value
   * @throws IllegalArgumentException if this enum type has no constant with the specified numeric value
   */
  public static CocomoLevel valueOf(int numericValue) {
    for (final CocomoLevel level : values()) {
      if (level.numericValue == numericValue) {
        return level;
      }
    }
    throw new IllegalArgumentException("no level for numericValue: " + numericValue);
  }

  /**
   * Generates constraints limited to levels between lowestLevel and highestLevel (inclusively).
   * @param lowestLevel lowestLevel contained in the generated constraints
   * @param highestLevel
   * @return
   */
  public static Constraints<CocomoLevel> constraintsFrom(@Nonnull final CocomoLevel lowestLevel,
          @Nonnull final CocomoLevel highestLevel) {
    if (lowestLevel.numericValue > highestLevel.numericValue) {
      throw new IllegalArgumentException("lowestLevel can't be higher than highestLevel");
    }
    final Set<CocomoLevel> levelsForConstraints = EnumSet.noneOf(CocomoLevel.class);
    for (final CocomoLevel level : values()) {
      if (lowestLevel.numericValue <= level.numericValue && level.numericValue <= highestLevel.numericValue) {
        levelsForConstraints.add(level);
      }
    }
    return new SetConstraints<CocomoLevel>(levelsForConstraints);
  }
}
