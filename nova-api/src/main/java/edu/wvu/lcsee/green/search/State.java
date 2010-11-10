package edu.wvu.lcsee.green.search;

import edu.wvu.lcsee.green.model.Treatment;
import com.google.common.collect.ImmutableMap;
import java.util.Date;
import java.util.Set;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface State {

  Treatment determineTreatmentFrom(@Nonnull State state);

  @Nonnull
  Date getTimeCreated();

  @Nonnull
  Set<Feature> getRequiredFeatures();

  @Nonnull
  Set<Feature> getActiveFeatures();

  @Nonnull
  Set<Feature> getInactiveFeatures();

  @Nonnull
  ImmutableMap<String, Number> getScores();
}
