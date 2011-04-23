package edu.wvu.lcsee.green.model;

import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface ConstraintsContext {

  /**
   * Returns the value for the specified attribute.
   *
   * @param <V> value type
   * @param attribute attribute for which a value is desired
   * @return the value associated with the specified attribute.
   * @throws IllegalArgumentException when <code>attribute</code> is not in the context.
   */
  @Nonnull
  <V extends Serializable> V getValueFor(@Nonnull Attribute<V> attribute);
}
