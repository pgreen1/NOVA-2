package edu.wvu.lcsee.green.model;

import edu.wvu.lcsee.green.model.ModelConfiguration;
import edu.wvu.lcsee.green.model.Project;
import javax.annotation.Nonnull;

/**
 * Encapsulates the logic to score a {@link Project} of a particular {@link ModelConfiguration}.
 *
 * @author pdgreen
 * @see Project
 * @see ModelConfiguration
 */
public interface ScoringFunction {

  /**
   * Returns the identifier of the ScoringFunction.
   * @return the identifier of the ScoringFunction
   */
  @Nonnull
  String getKey();

  /**
   * Scores a project.
   * 
   * @param project the project to be scored
   * @return score of the project
   * @throws IllegalArgumentException when the project doesn't have the Attributes required for the scoring to occur.
   */
  @Nonnull
  Number score(@Nonnull Project project);
}
