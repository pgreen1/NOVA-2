package edu.wvu.lcsee.green.model.impl;

import com.google.common.collect.Lists;
import java.util.List;
import com.google.common.collect.Sets;
import java.util.Collections;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import edu.wvu.lcsee.green.model.ConstraintsEditor.DiscreteValue;
import java.util.Set;
import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.*;

/**
 *
 * @author pdgreen
 */
public class RangeConstraintsEditor implements ConstraintsEditor<Number> {

  private final ImmutableSet<DiscreteValue> allValues;
  private final ImmutableSet<DiscreteValue> extremeValues;
  private final Set<RangeDiscreteValue> currentValues;
  private final Number discretizeSize;

  public RangeConstraintsEditor(@Nonnull final Set<RangeDiscreteValue> allValues,
          @Nonnull final Set<RangeDiscreteValue> currentValues, @Nonnull final Number discretizeSize) {
    this.allValues = ImmutableSet.<DiscreteValue>copyOf(allValues);
    this.extremeValues = ImmutableSet.<DiscreteValue>of(Collections.min(allValues), Collections.max(allValues));
    this.currentValues = Sets.newHashSet(currentValues);
    this.discretizeSize = checkNotNull(discretizeSize);
  }

  @Override
  public Constraints<Number> generateConstraints() {
    if (currentValues.size() == 1) {
      return new RangeConstraints(currentValues.iterator().next().minValue);
    } else {

      final List<RangeDiscreteValue> sortedCurrentValues = Lists.newArrayList(currentValues);
      Collections.sort(sortedCurrentValues);
      //TODO clean up with iterators?
      for (int i = 0; i < sortedCurrentValues.size() - 1; i++) {
        final Number currentMaxValue = sortedCurrentValues.get(i).maxValue;
        final Number nextMinValue = sortedCurrentValues.get(i + 1).minValue;
        final boolean connectedValues = currentMaxValue.equals(nextMinValue);
        checkState(connectedValues, "values are not connected: {}", currentValues);
      }
      //TODO clean up
      return new RangeConstraints(sortedCurrentValues.get(0).minValue, sortedCurrentValues.get(
              sortedCurrentValues.size() - 1).maxValue, null);
    }

  }

  @Override
  public ImmutableSet<DiscreteValue> getAllValues() {
    return allValues;
  }

  @Override
  public ImmutableSet<DiscreteValue> getExtremesValues() {
    return extremeValues;
  }

  @Override
  public Set<DiscreteValue> getCurrentValues() {
    return Collections.<DiscreteValue>unmodifiableSet(currentValues);
  }

  @Override
  public ImmutableSet<DiscreteValue> getRemovableValues() {
    return ImmutableSet.<DiscreteValue>of(Collections.min(currentValues), Collections.max(currentValues));
  }

  @Override
  public boolean isSingletonValue() {
    return allValues.size() == 1;
  }

  @Override
  public boolean addValue(final DiscreteValue value) {
    checkArgument(value instanceof RangeDiscreteValue, "{} not instance of {}", value, RangeDiscreteValue.class);
    checkArgument(allValues.contains(value), "{} not in {}", value, allValues);
    return currentValues.add((RangeDiscreteValue) value);
  }

  @Override
  public boolean removeValue(final DiscreteValue value) {
    return currentValues.remove(value);
  }

  @Override
  public void removeAllValues() {
    currentValues.clear();
  }

  public static RangeConstraintsEditor newInstanceFrom(@Nonnull final RangeConstraints rangeConstraints) {
    final Set<RangeDiscreteValue> values;
    if (rangeConstraints.isSingleValued()) {
      values = ImmutableSet.of(new RangeDiscreteValue(rangeConstraints.getMininumValue(), rangeConstraints.
              getMaximumValue()));
    } else {
      values = generateValues(rangeConstraints.getMininumValue(), rangeConstraints.getMaximumValue(), rangeConstraints.
              getDiscretizeSize());
    }

    return new RangeConstraintsEditor(values, values, rangeConstraints.getDiscretizeSize());
  }

  static Set<RangeDiscreteValue> generateValues(@Nonnull final Number mininumValue, @Nonnull final Number maximumValue,
          @Nonnull final Number discretizeSize) {
    final Set<RangeDiscreteValue> values = Sets.newHashSet();

    final double increment = maximumValue.doubleValue() - mininumValue.doubleValue();

    for (double currentValue = mininumValue.doubleValue(), nextValue; currentValue <= maximumValue.doubleValue(); currentValue = nextValue) {
      nextValue = currentValue + increment;
      values.add(new RangeDiscreteValue(currentValue, Math.min(nextValue, maximumValue.doubleValue())));
    }

    return values;
  }

  static class RangeDiscreteValue implements DiscreteValue, Comparable<RangeDiscreteValue> {

    private final Number minValue;
    private final Number meanValue;
    private final Number maxValue;

    public RangeDiscreteValue(@Nonnull final Number minValue, @Nonnull final Number maxValue) {
      this.minValue = checkNotNull(minValue);
      this.maxValue = checkNotNull(maxValue);
      this.meanValue = (maxValue.doubleValue() + minValue.doubleValue()) / 2;
    }

    public Number getValue() {
      return meanValue;
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      final RangeDiscreteValue that = (RangeDiscreteValue) obj;

      return Objects.equal(this.minValue, that.minValue) && Objects.equal(this.maxValue, that.maxValue);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(minValue, maxValue);
    }

    @Override
    public int compareTo(final RangeDiscreteValue that) {
      return ComparisonChain.start().compare(this.meanValue.doubleValue(), that.meanValue.doubleValue()).compare(this.minValue.
              doubleValue(), that.minValue.doubleValue()).compare(this.maxValue.doubleValue(),
              that.maxValue.doubleValue()).result();
    }

    @Override
    public String toString() {
      return new StringBuilder().append("[").append(minValue).append(";").append(meanValue).append(";").append(maxValue).
              append("]").toString();

    }
  }
}
