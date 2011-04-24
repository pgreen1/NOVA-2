package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface ModelConfiguration {

  @Nonnull
  ImmutableSortedSet<Attribute<? extends Serializable>> getAllAttributes();

  @Nonnull
  <V extends Serializable> Constraints<V> getDefaultConstraintsFor(@Nonnull Attribute<V> attribute);

  @Nonnull
  NamedConstrainableAttributes getDefaultConstrainableAttributes();

  @Nonnull
  Scenario generateScenario(@Nonnull NamedConstrainableAttributes namedConstrainableAttributes,
          @Nonnull CaseStudy caseStudy);

  /**
   * Generates a Scenario where the constrainable attributes are the same as returned by
   * {@link ModelConfiguration#getDefaultConstrainableAttributes()} and all constraints are the same as
   * returned by {@link ModelConfiguration#getDefaultConstraintsFor(edu.wvu.lcsee.green.model.Attribute)}.
   *
   * @return a scenario with default constrainable attributes and constraints.
   */
  @Nonnull
  Scenario generateDefaultScenario();

  @Nonnull
  ImmutableSet<ScoringFunction> getAllScoringFunctions();

  @Nonnull
  ScoringFunction getScoringFunctionForKey(@Nonnull String key);
}
