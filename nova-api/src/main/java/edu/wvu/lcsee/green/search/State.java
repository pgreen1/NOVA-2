package edu.wvu.lcsee.green.search;

import edu.wvu.lcsee.green.model.Treatment;
import com.google.common.collect.ImmutableMap;
import edu.wvu.lcsee.green.model.Scenario;
import java.util.Date;
import java.util.Set;
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
}
