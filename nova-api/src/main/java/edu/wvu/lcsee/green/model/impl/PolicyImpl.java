package edu.wvu.lcsee.green.model.impl;

import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Policy;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author pdgreen
 */
public class PolicyImpl implements Policy {

  private final String name;
  private final ImmutableSet<Attribute<? extends Serializable>> modifiableAttributes;

  public PolicyImpl(String name,
          Set<Attribute<? extends Serializable>> modifiableAttributes) {
    this.name = name;
    this.modifiableAttributes = ImmutableSet.copyOf(modifiableAttributes);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ImmutableSet<Attribute<? extends Serializable>> getModifiableAttibutes() {
    return modifiableAttributes;
  }
}
