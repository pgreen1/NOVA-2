package edu.wvu.lcsee.green.model.impl;

import edu.wvu.lcsee.green.model.AttributeConstrainable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Constraints;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nonnull;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * Abstract implementation of {@link AttributeConstrainable}.
 * @author pdgreen
 */
public abstract class AbstractAttributeConstrainable implements AttributeConstrainable {

  private final ImmutableMap<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints;

  public AbstractAttributeConstrainable(
          @Nonnull final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints) {
    this.attributeConstraints = ImmutableMap.copyOf(attributeConstraints);
  }

  @Override
  public ImmutableSet<Attribute<? extends Serializable>> getAllAttributes() {
    return attributeConstraints.keySet();
  }

  @Override
  public <V extends Serializable> Constraints<V> getConstraintsFor(@Nonnull final Attribute<V> attribute) {
    checkNotNull(attribute);
    checkArgument(attributeConstraints.containsKey(attribute), "Attribute not being Constrained: " + attribute);
    return (Constraints<V>) attributeConstraints.get(attribute);
  }

  /**
   * Makes the {@link Attribute}s and {@link Constraints} available as an {@link ImmutableMap} for subclasses.
   * @return Attributes and Constraints as an ImmutableMap of Attribute to Constraints.
   */
  protected ImmutableMap<Attribute<? extends Serializable>, Constraints<? extends Serializable>> asMap() {
    return attributeConstraints;
  }
}
