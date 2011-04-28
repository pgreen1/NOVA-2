package edu.wvu.lcsee.green.model.impl;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
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

  public ProjectImpl(final Map<Attribute<? extends Serializable>, ? extends Serializable> values) {
    Preconditions.checkArgument(!values.isEmpty(), "Project must contain atleast one attribute: %s", values);
    this.values = ImmutableMap.copyOf(values);
  }

  @Override
  public ImmutableSet<Attribute<? extends Serializable>> getAttributes() {
    return values.keySet();
  }

  @Override
  public <V extends Serializable> V getValueFor(final Attribute<V> attribute) {
    Preconditions.checkArgument(values.containsKey(attribute), "attribute (%s) not in project (%s)", attribute, values.
            keySet());
    return (V) values.get(attribute);
  }

  @Override
  public ImmutableMap<Attribute<? extends Serializable>, ? extends Serializable> getValuesAsMap() {
    return values;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(values);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ProjectImpl)) {
      return false;
    }
    final ProjectImpl that = (ProjectImpl) o;

    return Objects.equal(this.values, that.values);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).addValue(values).toString();
  }
}
