package edu.wvu.lcsee.green.model.impl;

import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.AttributeContext;
import java.io.Serializable;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Standard implementation of {@link AttributeContext}.
 *
 * @author pdgreen
 */
public class AttributeContextImpl implements AttributeContext {

  private final String name;
  private final ImmutableSet<Attribute<? extends Serializable>> constrainableAttributes;

  public AttributeContextImpl(@Nonnull final Set<Attribute<? extends Serializable>> constrainableAttributes) {
    this(null, constrainableAttributes);
  }

  public AttributeContextImpl(@Nullable final String name,
          @Nonnull final Set<Attribute<? extends Serializable>> constrainableAttributes) {
    this.name = name;
    this.constrainableAttributes = ImmutableSet.copyOf(constrainableAttributes);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ImmutableSet<Attribute<? extends Serializable>> getConstrainableAttributes() {
    return constrainableAttributes;
  }
}
