package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A set of attributes which can be constrained.
 * TODO need better name for attribute context
 *
 * @author pdgreen
 */
public interface AttributeContext {

  /**
   * The name of the AttributeContext.
   * @return name of a the AttributeContext
   */
  @Nullable
  String getName();


   /**
   * Returns constrainable {@link Attribute}s associated with this AttributeContext.
   * @return all constrainable attributes associated with this AttributeContext
   */
  @Nonnull
  ImmutableSet<Attribute<? extends Serializable>> getConstrainableAttributes();
}
