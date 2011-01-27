package edu.wvu.lcsee.green.xomo.model;

import edu.wvu.lcsee.green.model.ModelConfiguration;
import edu.wvu.lcsee.green.model.ScoringFunction;
import javax.annotation.Nonnull;

/**
 * ModelConfiguration for XOMO.
 *
 * @author pdgreen
 */
public interface XomoModelConfiguration extends ModelConfiguration {

  public static String SCORING_FUNCTION_KEY_COCOMO_EFFORT = "effort";
  public static String SCORING_FUNCTION_KEY_COCOMO_SCHEDULE = "schedule";
  public static String SCORING_FUNCTION_KEY_COQUALMO_DEFECTS = "defects";

  @Nonnull
  ScoringFunction getEfforttScoringFunction();

  @Nonnull
  ScoringFunction getScheduleScoringFunction();

  @Nonnull
  ScoringFunction getDefectsScoringFunction();
}
