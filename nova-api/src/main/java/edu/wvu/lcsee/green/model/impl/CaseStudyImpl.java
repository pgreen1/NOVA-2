package edu.wvu.lcsee.green.model.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.CaseStudy;
import edu.wvu.lcsee.green.model.Constraints;
import java.io.Serializable;
import java.util.Map;

/**
 * Standard implementation of {@link CaseStudy}.
 *
 * @author pdgreen
 */
public class CaseStudyImpl implements CaseStudy {

  private final String name;
  private final ImmutableMap<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints;

  public CaseStudyImpl(String name,
          Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints) {
    this.name = name;
    this.attributeConstraints = ImmutableMap.copyOf(attributeConstraints);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ImmutableSet<Attribute<? extends Serializable>> getAllAttributes() {
    return attributeConstraints.keySet();
  }

  @Override
  public <V extends Serializable> Constraints<V> getConstraintsFor(Attribute<V> attribute) {
    return (Constraints<V>) attributeConstraints.get(attribute);
  }
}
