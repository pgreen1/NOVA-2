package edu.wvu.lcsee.green.model.impl;

import com.google.common.base.Objects;
import static com.google.common.base.Preconditions.*;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import edu.wvu.lcsee.green.model.ConstraintsEditor.DiscreteValue;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public class SetConstraintsEditor<V extends Serializable> implements ConstraintsEditor<V> {

  private final Set<SetDiscreteValue<V>> discreteValues;

  public SetConstraintsEditor(@Nonnull final Set<SetDiscreteValue<V>> discreteValues) {
    this.discreteValues = Sets.newHashSet(discreteValues);
  }

  public static <V extends Serializable> SetConstraintsEditor<V> newInstanceWithValues(@Nonnull final Set<V> values) {
    final Set<SetDiscreteValue<V>> discreteValues = Sets.newHashSet();
    for (final V value : values) {
      discreteValues.add(new SetDiscreteValue<V>(value));
    }
    return new SetConstraintsEditor<V>(discreteValues);
  }

  @Override
  public Constraints<V> generateConstraints() {
    checkState(!discreteValues.isEmpty(), "Before a Constraints can be generated, atleast one value must be added.");

    final List<V> values = Lists.newArrayListWithCapacity(discreteValues.size());
    for (final SetDiscreteValue<V> discreteValue : discreteValues) {
      values.add(discreteValue.getValue());
    }

    if (isSingletonValue()) {
      return new SingletonConstraints<V>(values.get(0));
    } else {
      return new SetConstraints<V>(Sets.newHashSet(values));
    }
  }

  @Override
  public Set<DiscreteValue<V>> getExtremesValues() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Set<DiscreteValue<V>> getAllValues() {
    return ImmutableSet.<DiscreteValue<V>>copyOf(discreteValues);
  }

  @Override
  public boolean isSingletonValue() {
    return discreteValues.size() == 1;
  }

  @Override
  public boolean addValue(@Nonnull final DiscreteValue<V> value) {
    checkArgument(value instanceof SetDiscreteValue,
            "unsupported DiscreteValue: " + value + " should be " + SetDiscreteValue.class);
    return discreteValues.add((SetDiscreteValue<V>) value);
  }

  @Override
  public boolean removeValue(@Nonnull final DiscreteValue<V> value) {
    return discreteValues.remove(value);
  }

  static class SetDiscreteValue<DV extends Serializable> implements DiscreteValue<DV> {

    final DV value;

    public SetDiscreteValue(@Nonnull DV value) {
      this.value = checkNotNull(value);
    }

    public DV getValue() {
      return value;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      final SetDiscreteValue<DV> that = (SetDiscreteValue<DV>) obj;

      return Objects.equal(this.value, that.value);
    }

    @Override
    public int hashCode() {
      int hash = 3;
      hash = 19 * hash + (this.value != null ? this.value.hashCode() : super.hashCode());
      return hash;
    }
  }
}
