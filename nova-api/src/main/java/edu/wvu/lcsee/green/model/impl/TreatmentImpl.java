package edu.wvu.lcsee.green.model.impl;

import java.util.Map;
import javax.annotation.Nonnull;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.Treatment;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author pdgreen
 */
public class TreatmentImpl implements Treatment {

  private final ImmutableMap<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints;

  public TreatmentImpl(
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
    return (Constraints<V>) attributeConstraints.get(attribute);
  }

  public static <T extends Serializable> Treatment newSingletonTreatment(@Nonnull final Attribute<T> attribute,
          @Nonnull final Constraints<T> attributeConstraints) {
    checkNotNull(attribute);
    checkNotNull(attributeConstraints);
    return new TreatmentImpl(ImmutableMap.<Attribute<? extends Serializable>, Constraints<? extends Serializable>>of(
            attribute, attributeConstraints));
  }
}
