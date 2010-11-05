package edu.wvu.lcsee.green.spi;

import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Project;
import edu.wvu.lcsee.green.model.Scenario;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface ProjectGenerator {

  @Nonnull
  Project generate(@Nonnull Scenario scenario);

  @Nonnull
  ImmutableSet<Project> generateMany(@Nonnull Scenario scenario,
          @Nonnegative int numberOfProjectsToCreate);
}
