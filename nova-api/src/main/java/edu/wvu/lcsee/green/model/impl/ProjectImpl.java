package edu.wvu.lcsee.green.model.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Project;
import java.io.Serializable;
import java.util.Map;

/**
 * Standard implementation of {@link Project}.
 *
 * @author pdgreen
 */
public class ProjectImpl implements Project {

  private final ImmutableMap<Attribute<? extends Serializable>, ? extends Serializable> values;

  public ProjectImpl(Map<Attribute<? extends Serializable>, ? extends Serializable> values) {
    this.values = ImmutableMap.copyOf(values);
  }

  @Override
  public ImmutableSet<Attribute<? extends Serializable>> getAttributes() {
    return values.keySet();
  }

  @Override
  public <V extends Serializable> V getValueFor(final Attribute<V> attribute) {
    return (V) values.get(attribute);
  }

  @Override
  public ImmutableMap<Attribute<? extends Serializable>, ? extends Serializable> getValuesAsMap() {
    return values;
  }

  @Override
  public String toString() {
    return values.toString();
  }
}
