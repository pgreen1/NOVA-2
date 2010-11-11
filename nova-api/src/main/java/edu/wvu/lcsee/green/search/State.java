package edu.wvu.lcsee.green.search;

import edu.wvu.lcsee.green.model.Scenario;
import java.util.Date;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface State {

  @Nonnull
  Date getTimeCreated();

  @Nonnull
  Scenario getScenario();

  @Nonnull
  Number getScore();
}
