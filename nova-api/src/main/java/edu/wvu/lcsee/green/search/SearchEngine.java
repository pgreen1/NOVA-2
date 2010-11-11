package edu.wvu.lcsee.green.search;

import com.google.common.collect.ImmutableList;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.spi.ScoringFunction;
import edu.wvu.lcsee.green.search.State;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface SearchEngine {

  String getKey();

  @Nonnull
  Path search(@Nonnull EvaluationFunction evaluationFunction, @Nonnull final Scenario initialScenerio);
}
