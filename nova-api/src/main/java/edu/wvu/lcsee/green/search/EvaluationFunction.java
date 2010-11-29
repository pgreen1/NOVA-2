package edu.wvu.lcsee.green.search;

import edu.wvu.lcsee.green.model.Scenario;
import javax.annotation.Nonnull;

/**
 * Evaluates a {@link Scenario} and returns a score.  This score is used in {@link SearchEngine} in finding the best {@link Scenario}.
 * @author pdgreen
 * @see SearchEngine
 * @see Scenario
 */
public interface EvaluationFunction {

  /**
   * Evaluates a {@link Scenario} and returns a score.
   * @param scenario {@link Scenario} to be scored.
   * @return the evaluated score of a {@link Scenario}
   */
  @Nonnull
  Number evaluate(@Nonnull Scenario scenario);
}
