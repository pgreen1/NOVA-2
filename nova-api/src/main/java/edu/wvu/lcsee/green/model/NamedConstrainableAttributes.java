package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A set of attributes which can be constrained.
 *
 * @author pdgreen
 */
public interface NamedConstrainableAttributes {

  /**
   * The name of the NamedConstrainableAttributes.
   * @return name of a the NamedConstrainableAttributes
   */
  @Nullable
  String getName();


   /**
   * Returns constrainable {@link Attribute}s associated with this NamedConstrainableAttributes.
   * @return all constrainable attributes associated with this NamedConstrainableAttributes
   */
  @Nonnull
  ImmutableSet<Attribute<? extends Serializable>> getConstrainableAttributes();
}
