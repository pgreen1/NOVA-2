package edu.wvu.lcsee.green.model.impl;

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

/**
 *
 * @author pdgreen
 */
public class ScenarioImpl implements Scenario {

  private final ImmutableMap<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints;
  private final ImmutableSet<Attribute<? extends Serializable>> modifiableConstraints;

  public ScenarioImpl(
          @Nonnull final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints,
          Set<Attribute<? extends Serializable>> modifiableConstraints) {
    this.attributeConstraints = ImmutableMap.copyOf(attributeConstraints);
    this.modifiableConstraints = ImmutableSet.copyOf(modifiableConstraints);
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

  @Override
  public ImmutableSet<Attribute<? extends Serializable>> getModifiableAttributes() {
    return modifiableConstraints;
  }

  @Override
  public Scenario applyTreatment(Treatment treatment) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
