package edu.wvu.lcsee.green.search.spi;

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
  ImmutableList<State> search(@Nonnull ScoringFunction scoringFunction, @Nonnull final Scenario initialScenerio);
}
