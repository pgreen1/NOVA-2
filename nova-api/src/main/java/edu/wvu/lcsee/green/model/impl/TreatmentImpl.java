package edu.wvu.lcsee.green.model.impl;

import java.util.Map;
import javax.annotation.Nonnull;
import com.google.common.collect.ImmutableMap;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.Treatment;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Standard implementation of {@link Treatment}.
 *
 * @author pdgreen
 */
public class TreatmentImpl extends AbstractAttributeConstrainable implements Treatment {

  public TreatmentImpl(
          @Nonnull final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints) {
    super(attributeConstraints);
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
