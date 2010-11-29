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
import static com.google.common.base.Preconditions.checkArgument;

/**
 * The Standard implementation of {@link Treatment}.
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
    checkArgument(attributeConstraints.containsKey(attribute), "Argument not in this Treatment: " + attribute);
    return (Constraints<V>) attributeConstraints.get(attribute);
  }

  /**
   * Creates a Treatment where there is only a single Attribute being constrained.
   * @param <V> Value type of the Attribute and Constraints
   * @param attribute Attribute being constrained
   * @param constraints Constraints for the Attribute
   * @return A Treatment
   */
  public static <V extends Serializable> Treatment newSingletonTreatment(@Nonnull final Attribute<V> attribute,
          @Nonnull final Constraints<V> constraints) {
    checkNotNull(attribute);
    checkNotNull(constraints);
    return new TreatmentImpl(ImmutableMap.<Attribute<? extends Serializable>, Constraints<? extends Serializable>>of(
            attribute, constraints));
  }
}
