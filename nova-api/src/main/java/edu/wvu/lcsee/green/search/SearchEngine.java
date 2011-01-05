package edu.wvu.lcsee.green.search;

import com.google.common.collect.ImmutableList;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.ScoringFunction;
import edu.wvu.lcsee.green.search.State;
import javax.annotation.Nonnull;

/**
 * Searches for the best {@link Scenario} based upon a specific {@link EvaluationFunction} and starting from a specified initial {@link Scenerio}.
 * @author pdgreen
 * @see EvaluationFunction
 * @see Scenario
 * @see Path
 */
public interface SearchEngine {

  /**
   * Returns the key of the SearchEngine to be used to reference it in {@link NovaControl}.
   * @return reference key of the SearchEngine
   */
  @Nonnull
  String getKey();

  /**
   * Searches for the best {@link Path} as determined by the specified {@link EvaluationFunction}, evaluationFunction,
   * starting from the specified {@link Scenario}, initialScenario.
   * @param evaluationFunction evaluationFunction used to score a scenario
   * @param initialScenerio the starting point of the search
   * @return A {@link Path} of "increasingly better" states
   */
  @Nonnull
  Path search(@Nonnull EvaluationFunction evaluationFunction, @Nonnull final Scenario initialScenerio);
}
