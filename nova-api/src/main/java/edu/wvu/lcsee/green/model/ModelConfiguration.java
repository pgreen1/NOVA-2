package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface ModelConfiguration {

  @Nonnull
  ImmutableSet<Attribute<? extends Serializable>> getAllAttributes();

  @Nonnull
  <V extends Serializable> Constraints<V> getDefaultConstraintsFor(@Nonnull Attribute<V> attribute);

  @Nonnull
  Scenario generateScenario(@Nonnull Policy policy, @Nonnull CaseStudy caseStudy);
}
