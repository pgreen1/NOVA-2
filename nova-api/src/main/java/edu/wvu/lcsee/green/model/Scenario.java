package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface Scenario {

  @Nonnull
  ImmutableSet<Attribute<? extends Serializable>> getAllAttributes();

  @Nonnull
  ImmutableSet<Attribute<? extends Serializable>> getModifiableAttributes();

  @Nonnull
  <V extends Serializable> Constraints<V> getConstraintsFor(@Nonnull Attribute<V> attribute);

  @Nonnull
  Scenario applyTreatment(@Nonnull Treatment treatment);

  @Nonnull
  ImmutableMap<Attribute<? extends Serializable>, Constraints<? extends Serializable>> asMap();
}
