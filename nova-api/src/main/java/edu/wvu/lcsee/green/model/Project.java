package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface Project {

  @Nonnull
  ImmutableSet<Attribute<? extends Serializable>> getAttributes();

  /**
   * Returns the value for the specified attribute.
   *
   * @param <V> value type
   * @param attribute attribute for which a value is desired
   * @return the value associated with the specified attribute.
   * @throws IllegalArgumentException when <code>attribute</code> is not in the set returned by {@link #getAttributes() }
   */
  @Nonnull
  <V extends Serializable> V getValueFor(@Nonnull Attribute<V> attribute);

  @Nonnull
  ImmutableMap<Attribute<? extends Serializable>, ? extends Serializable> getValuesAsMap();
}
