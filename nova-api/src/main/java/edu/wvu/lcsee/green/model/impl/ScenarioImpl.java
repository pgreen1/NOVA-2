package edu.wvu.lcsee.green.model.impl;

import com.google.common.collect.Maps;
import java.util.Set;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.Treatment;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nonnull;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * The Standard implementation of {@link Scenario}.
 *
 * @author pdgreen
 */
public class ScenarioImpl extends AbstractAttributeConstrainable implements Scenario {

  private final ImmutableSet<Attribute<? extends Serializable>> constrainableConstraints;

  public ScenarioImpl(
          @Nonnull final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints,
          Set<Attribute<? extends Serializable>> constrainableConstraints) {
    super(attributeConstraints);
    this.constrainableConstraints = ImmutableSet.copyOf(constrainableConstraints);
  }

 

  @Override
  public ImmutableSet<Attribute<? extends Serializable>> getConstrainableAttributes() {
    return constrainableConstraints;
  }



  @Override
  public Scenario applyTreatment(@Nonnull final Treatment treatment) {
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> mutableAttributeConstraints = Maps.
            newHashMap(asMap());

    for (final Attribute<?> attribute : treatment.getAllAttributes()) {
      checkArgument(constrainableConstraints.contains(attribute), "unable to apply treatment for " + attribute.getName());
      final Constraints currentConstraints = mutableAttributeConstraints.get(attribute);
      final Constraints treatmentConstraints = treatment.getConstraintsFor(attribute);
      mutableAttributeConstraints.put(attribute, currentConstraints.mergeConstraints(treatmentConstraints));
    }

    return new ScenarioImpl(mutableAttributeConstraints, constrainableConstraints);
  }
}
