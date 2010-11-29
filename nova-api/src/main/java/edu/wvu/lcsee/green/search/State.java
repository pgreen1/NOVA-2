package edu.wvu.lcsee.green.search;

import edu.wvu.lcsee.green.model.Scenario;
import java.util.Date;
import javax.annotation.Nonnull;

/**
 * States are the best {@link Scenario}s found during a search within a {@link SearchEngine}.
 *
 * @author pdgreen
 */
public interface State {

  /**
   * The time instance that the state was created.
   * @return time instance the state was created.
   */
  @Nonnull
  Date getTimeCreated();

  /**
   * The {@link Scenario} found for this State.
   * @return the {@link Scenario found for this State.
   */
  @Nonnull
  Scenario getScenario();

  /**
   * The score of the {@link Scenario}.
   * @return the score of the {@link Scenario}
   */
  @Nonnull
  Number getScore();
}
