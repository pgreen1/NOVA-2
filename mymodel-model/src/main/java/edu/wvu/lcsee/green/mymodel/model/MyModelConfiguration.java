package edu.wvu.lcsee.green.mymodel.model;

import edu.wvu.lcsee.green.model.ModelConfiguration;
import edu.wvu.lcsee.green.model.ScoringFunction;
import javax.annotation.Nonnull;

/**
 * ModelConfiguration for MyModel.
 *
 * @author pdgreen
 */
public interface MyModelConfiguration extends ModelConfiguration {

  String SCORING_FUNCTION_KEY_COST = "cost";
  String SCORING_FUNCTION_KEY_DURATION = "duration";

  @Nonnull
  ScoringFunction getCostScoringFunction();

  @Nonnull
  ScoringFunction getDurationScoringFunction();
}
