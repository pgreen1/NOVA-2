package edu.wvu.lcsee.green.spi;

import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Project;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.ScoredProject;
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
  ScoredProject generateScoredProject(@Nonnull Scenario scenario, String... scoreFunctionKeys);

  @Nonnull
  ImmutableSet<Project> generateManyProjects(@Nonnull Scenario scenario,
          @Nonnegative int numberOfProjectsToCreate);

  @Nonnull
  ImmutableSet<ScoredProject> generateManyScoredProjects(@Nonnull Scenario scenario,
          @Nonnegative int numberOfProjectsToCreate, String... scoreFunctionKeys);
}
