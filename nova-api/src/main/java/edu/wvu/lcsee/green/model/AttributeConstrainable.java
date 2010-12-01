package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 * Contains {@link Constraints} for {@link Attribute}s.
 * @author pdgreen
 * @see Attribute
 * @see Constraints
 */
public interface AttributeConstrainable {

  /**
   * Returns all {@link Attribute}s being constrained with this AttributeConstrainable.
   * @return all attributes constrained with this AttributeConstrainable
   */
  @Nonnull
  ImmutableSet<Attribute<? extends Serializable>> getAllAttributes();

  /**
   * Returns the {@link Constraints} for the specified {@link Attribute}.
   * @param <V> Value type of the Attribute and resulting Constraints
   * @param attribute the Attribute for which the Constraints is desired
   * @return the Constraints of the Attribute
   * @throws IllegalArgumentException when an Attribute is not contained in this AttributeConstrainable
   */
  @Nonnull
  <V extends Serializable> Constraints<V> getConstraintsFor(@Nonnull Attribute<V> attribute);
}
