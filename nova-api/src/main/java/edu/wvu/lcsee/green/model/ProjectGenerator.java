package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.spi.ScoringFunction;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface ProjectGenerator {

  @Nonnull
  Project generateProject(@Nonnull Scenario scenario);

  @Nonnull
  ScoredProject generateScoredProject(@Nonnull Scenario scenario, @Nonnull ScoringFunction... scoringFunctions);

  @Nonnull
  ImmutableSet<Project> generateManyProjects(@Nonnull Scenario scenario,
          @Nonnegative int numberOfProjectsToCreate);

  @Nonnull
  ImmutableSet<ScoredProject> generateManyScoredProjects(@Nonnull Scenario scenario,
          @Nonnegative int numberOfProjectsToCreate, @Nonnull ScoringFunction... scoringFunctions);
}
