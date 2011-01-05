package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;

/**
 * A {@link Project} that has been scored my {@link ScoringFunction}s.
 * @author pdgreen
 * @see Project
 * @see ScoringFunction
 */
public interface ScoredProject extends Project {
/**
 * An ImmutableMap of scores generated from {@link ScoringFunction}s.
 * @return ImmutableMap of scores generated from {@link ScoringFunction}sF
 */
  @Nonnull
  ImmutableMap<String, Number> getScores();
}
