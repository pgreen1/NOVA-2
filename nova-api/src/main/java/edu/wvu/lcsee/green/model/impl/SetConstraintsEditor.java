package edu.wvu.lcsee.green.model.impl;

import com.google.common.base.Objects;
import static com.google.common.base.Preconditions.*;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import edu.wvu.lcsee.green.model.ConstraintsEditor.DiscreteValue;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *
 * @author pdgreen
 */
public class SetConstraintsEditor<V extends Serializable> implements ConstraintsEditor<V> {

  private final ImmutableSet<DiscreteValue<V>> allValues;
  private final ImmutableSet<DiscreteValue<V>> extremeValues;
  private final Set<SetDiscreteValue<V>> currentDiscreteValues;

  SetConstraintsEditor(@Nonnull final ImmutableSet<DiscreteValue<V>> allValues,
          @Nonnull final ImmutableSet<DiscreteValue<V>> extremeValues,
          @Nonnull final Set<SetDiscreteValue<V>> currentDiscreteValues) {
    this.allValues = allValues;
    this.extremeValues = extremeValues;
    this.currentDiscreteValues = currentDiscreteValues;
  }

  @Nonnull
  static <V extends Serializable> Set<V> determineExtremeValues(
          @Nonnull final Set<V> allValues,
          @Nullable final Comparator<V> optionalComparator) {
    @Nullable
    final Ordering<V> ordering;
    if (optionalComparator != null) {
      ordering = Ordering.from(optionalComparator);
    } else if (allValues.iterator().next() instanceof Comparable) {//FIXME should be better way of determing if V is Comparable
      //FIXME casting with rawtype is bad, but i'm not sure how to handle the situation where V is comparable and when it isn't
      ordering = (Ordering) Ordering.natural();
    } else {
      ordering = null;
    }

    final Set<V> extremeValues;
    if (ordering == null) {
      extremeValues = allValues;
    } else {
      extremeValues = ImmutableSet.of(ordering.min(allValues), ordering.max(allValues));
    }
    return extremeValues;
  }

  public static <V extends Serializable> SetConstraintsEditor<V> newInstanceWithValues(@Nonnull final Set<V> values) {
    final Set<V> extremeValues = determineExtremeValues(values, null);//TODO support optional comparator,

    final Set<SetDiscreteValue<V>> discreteValues = Sets.newHashSet();

    final Set<SetDiscreteValue<V>> extremeDiscreteValues = Sets.newHashSet();
    for (final V value : values) {
      final SetDiscreteValue<V> discreteValue = new SetDiscreteValue<V>(value);
      discreteValues.add(discreteValue);
      if (extremeValues.contains(value)) {
        extremeDiscreteValues.add(discreteValue);
      }
    }
    return new SetConstraintsEditor<V>(ImmutableSet.<DiscreteValue<V>>copyOf(discreteValues),
            ImmutableSet.<DiscreteValue<V>>copyOf(extremeDiscreteValues),
            Sets.newHashSet(discreteValues));
  }
//FIXME may want to consider storing an ordering for DiscreteValue that can be used to clean up the following code; yucky to convert back and forth between DiscreteValue and Value

  public static <V extends Serializable> SetConstraintsEditor<V> newInstanceFromIntersection(
          @Nonnull final SetConstraintsEditor<V> thisEditor, @Nonnull final SetConstraintsEditor<V> thatEditor) {

    final Set<DiscreteValue<V>> discreteValues = Sets.intersection(thisEditor.getAllValues(), thatEditor.getAllValues());

    final Set<V> values = Sets.newHashSetWithExpectedSize(discreteValues.size());
    for (final DiscreteValue<V> discreteValue : discreteValues) {
      values.add(((SetDiscreteValue<V>) discreteValue).getValue());
    }

    return newInstanceWithValues(values);//TODO support optional comparator,
  }

  @Override
  public Constraints<V> generateConstraints() {
    checkState(!currentDiscreteValues.isEmpty(),
            "Before a Constraints can be generated, atleast one value must be added.");

    final List<V> values = Lists.newArrayListWithCapacity(currentDiscreteValues.size());
    for (final SetDiscreteValue<V> discreteValue : currentDiscreteValues) {
      values.add(discreteValue.getValue());
    }

    if (isSingletonValue()) {
      return new SingletonConstraints<V>(values.get(0));
    } else {
      return new SetConstraints<V>(Sets.newHashSet(values));
    }
  }

  @Override
  public ImmutableSet<DiscreteValue<V>> getAllValues() {
    return allValues;
  }

  @Override
  public ImmutableSet<DiscreteValue<V>> getExtremesValues() {
    return extremeValues;
  }

  @Override
  public Set<DiscreteValue<V>> getCurrentValues() {
    return Collections.<DiscreteValue<V>>unmodifiableSet(currentDiscreteValues);
  }

  @Override
  public boolean isSingletonValue() {
    return currentDiscreteValues.size() == 1;
  }

  @Override
  public boolean addValue(@Nonnull final DiscreteValue<V> value) {
    checkArgument(value instanceof SetDiscreteValue,
            "unsupported DiscreteValue: " + value + " should be " + SetDiscreteValue.class);
    return currentDiscreteValues.add((SetDiscreteValue<V>) value);
  }

  @Override
  public boolean removeValue(@Nonnull final DiscreteValue<V> value) {
    return currentDiscreteValues.remove(value);
  }

  @Override
  public void removeAllValues() {
    currentDiscreteValues.clear();
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
