package edu.wvu.lcsee.green.model.impl;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.NamedConstrainableAttributes;
import java.io.Serializable;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Standard implementation of {@link NamedConstrainableAttributes}.
 *
 * @author pdgreen
 */
public class NamedConstrainableAttributesImpl implements NamedConstrainableAttributes {

  private final String name;
  private final ImmutableSet<Attribute<? extends Serializable>> constrainableAttributes;

  public NamedConstrainableAttributesImpl(@Nonnull final Set<Attribute<? extends Serializable>> constrainableAttributes) {
    this(null, constrainableAttributes);
  }

  public NamedConstrainableAttributesImpl(@Nullable final String name,
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

  @Override
  public int hashCode() {
    return Objects.hashCode(name, constrainableAttributes);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NamedConstrainableAttributesImpl)) {
      return false;
    }
    final NamedConstrainableAttributesImpl that = (NamedConstrainableAttributesImpl) o;

    return Objects.equal(this.name, that.name)
            && Objects.equal(this.constrainableAttributes, that.constrainableAttributes);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("name", name).add("attributeConstraints", constrainableAttributes).toString();
  }
}
