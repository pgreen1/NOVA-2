package edu.wvu.lcsee.green.model.impl;

import com.google.common.base.Objects;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.CaseStudy;
import edu.wvu.lcsee.green.model.Constraints;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Standard implementation of {@link CaseStudy}.
 *
 * @author pdgreen
 */
public class CaseStudyImpl extends AbstractAttributeConstrainable implements CaseStudy {

  private final String name;

  public CaseStudyImpl(
          @Nonnull final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints) {
    this(null, attributeConstraints);
  }

  public CaseStudyImpl(@Nullable final String name,
          @Nonnull final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints) {
    super(attributeConstraints);
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name, asMap());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CaseStudyImpl)) {
      return false;
    }
    final CaseStudyImpl that = (CaseStudyImpl) o;

    return Objects.equal(this.name, that.name)
            && Objects.equal(this.asMap(), that.asMap());
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("name", name).add("attributeConstraints", asMap()).toString();
  }
}
