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
  private final ExtremeValueExtractor<V> extremeValueExtractor;

  SetConstraintsEditor(@Nonnull final Set<SetDiscreteValue<V>> discreteValues,
          @Nonnull final ExtremeValueExtractor<V> extremeValueExtractor) {
    this.allValues = ImmutableSet.<DiscreteValue<V>>copyOf(discreteValues);
    this.extremeValues = ImmutableSet.<DiscreteValue<V>>copyOf(extremeValueExtractor.extractExtremeDiscreteValues(
            discreteValues));
    this.currentDiscreteValues = Sets.newHashSet(discreteValues);
    this.extremeValueExtractor = extremeValueExtractor;
  }

  static class ExtremeValueExtractor<V extends Serializable> {

    private final Ordering<SetDiscreteValue<V>> discreteValueOrdering;

    ExtremeValueExtractor(@Nullable final Ordering<V> valueOrdering) {
      if (valueOrdering == null) {
        this.discreteValueOrdering = null;
      } else {
        this.discreteValueOrdering = new Ordering<SetDiscreteValue<V>>() {

          @Override
          public int compare(final SetDiscreteValue<V> left,
                  final SetDiscreteValue<V> right) {
            return valueOrdering.compare(left.getValue(), right.getValue());
          }
        };
      }
    }

    @Nonnull
    ImmutableSet<SetDiscreteValue<V>> extractExtremeDiscreteValues(
            @Nonnull final Set<SetDiscreteValue<V>> discreteValues) {
      if (discreteValueOrdering == null) {
        return ImmutableSet.copyOf(discreteValues);
      } else {
        return ImmutableSet.of(discreteValueOrdering.min(discreteValues), discreteValueOrdering.max(discreteValues));
      }
    }

    @Nullable
    static <V extends Serializable> ExtremeValueExtractor newInstance(
            @Nonnull final Class<V> valueType,
            @Nullable final Comparator<V> optionalComparator) {
      @Nullable
      final Ordering<V> ordering;
      if (optionalComparator != null) {
        ordering = Ordering.from(optionalComparator);
      } else if (Comparable.class.isAssignableFrom(valueType)) {
        //FIXME casting with rawtype is bad, but i'm not sure how to handle the situation where V is comparable and when it isn't
        ordering = (Ordering) Ordering.natural();
      } else {
        ordering = null;
      }
      return new ExtremeValueExtractor(ordering);
    }
  }

  static <V extends Serializable> Set<SetDiscreteValue<V>> generateExtremeDiscreteValues(
          Set<SetDiscreteValue<V>> discreteValues, Ordering<SetDiscreteValue<V>> discreteValueOrdering) {
    final Set<SetDiscreteValue<V>> extremeDiscreteValues;
    if (discreteValueOrdering == null) {
      extremeDiscreteValues = discreteValues;
    } else {
      extremeDiscreteValues = ImmutableSet.of(discreteValueOrdering.min(discreteValues), discreteValueOrdering.max(
              discreteValues));
    }
    return extremeDiscreteValues;
  }

  public static <V extends Serializable> SetConstraintsEditor<V> newInstanceWithValues(@Nonnull final Set<V> values) {
    @SuppressWarnings("cast")//if this doesn't work, there is bigger problems.
    final Class<V> valueType = (Class<V>) values.iterator().next().getClass();

    final ExtremeValueExtractor<V> extremeValueExtractor = ExtremeValueExtractor.newInstance(valueType, null);//TODO support optional comparator,
    final Set<SetDiscreteValue<V>> discreteValues = Sets.newHashSetWithExpectedSize(values.size());
    for (final V value : values) {
      discreteValues.add(new SetDiscreteValue<V>(value));
    }
    return new SetConstraintsEditor<V>(discreteValues, extremeValueExtractor);
  }

  public static <V extends Serializable> SetConstraintsEditor<V> newInstanceFromIntersection(
          @Nonnull final SetConstraintsEditor<V> thisEditor, @Nonnull final SetConstraintsEditor<V> thatEditor) {
    final ExtremeValueExtractor<V> extremeValueExtractor = thisEditor.extremeValueExtractor;
    final Set<SetDiscreteValue<V>> discreteValues = Sets.intersection(thisEditor.currentDiscreteValues,
            thatEditor.currentDiscreteValues);
    return new SetConstraintsEditor<V>(discreteValues, extremeValueExtractor);
  }

  @Override
  public Constraints<V> generateConstraints() {
    checkState(!currentDiscreteValues.isEmpty(),
            "Before a Constraints can be generated, atleast one value must be added.");

    final List<V> values = Lists.newArrayListWithCapacity(currentDiscreteValues.size());
    for (final SetDiscreteValue<V> discreteValue : currentDiscreteValues) {
      values.add(discreteValue.getValue());
    }

    return new SetConstraints<V>(Sets.newHashSet(values));
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
