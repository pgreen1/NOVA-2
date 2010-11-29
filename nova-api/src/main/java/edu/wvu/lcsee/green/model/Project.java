package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 * An instance of Project models a real life project where each attribute has been assigned its appropriate value.
 *
 * @author pdgreen
 */
public interface Project {

  /**
   * Returns all {@link Attribute}s associated with this project.
   * @return all {@link Attribute}s associated with this project
   */
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

  /**
   * Returns the values as an ImmutableMap where the key is the attribute.
   * @return an ImmutableMap of attribute to value
   */
  @Nonnull
  ImmutableMap<Attribute<? extends Serializable>, ? extends Serializable> getValuesAsMap();
}
