package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 * A Treatment consists of constrained {@link Attribute}s that can be applied to a {@link Scenario} to constrain it further..
 *
 * @author pdgreen
 * @see Attribute
 * @see Constraints
 * @see Scenario
 */
public interface Treatment {

  /**
   * Returns all {@link Attribute}s associated with this Treatment.
   * @return all attributes associated with this Treatment
   */
  @Nonnull
  ImmutableSet<Attribute<? extends Serializable>> getAllAttributes();


  /**
   * Returns the {@link Constraints} for the specified {@link Attribute}.
   * @param <V> Value type of the Attribute and resulting Constraints
   * @param attribute the Attribute for which the Constraints is desired
   * @return the Constraints of the Attribute
   * @throws IllegalArgumentException when an Attribute is not contained in this Scenario
   */
  @Nonnull
  <V extends Serializable> Constraints<V> getConstraintsFor(@Nonnull Attribute<V> attribute);
}
