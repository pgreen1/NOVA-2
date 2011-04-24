package edu.wvu.lcsee.green.model.impl;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsContext;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import java.util.Random;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public class RangeConstraints implements Constraints<Double> {

  private static final Random RANDOM = new Random();
  private final Double mininumValue;
  private final Double maximumValue;
  private final Double discretizeSize;

  public RangeConstraints(@Nonnull final Double value) {
    this.mininumValue = value;
    this.maximumValue = value;
    this.discretizeSize = null;
  }

  public RangeConstraints(@Nonnull final Double minimumValue, @Nonnull final Double maximumValue,
          @Nonnull final Double discretizeSize) {
    this.mininumValue = minimumValue;
    this.maximumValue = maximumValue;
    this.discretizeSize = discretizeSize;
  }

  @Override
  public ImmutableSet<Attribute<?>> getDependentAttributes() {
    return ImmutableSet.of();
  }

  @Override
  public Double generateValue(final ConstraintsContext currentContext) {
    final double minVal = mininumValue;
    final double maxVal = maximumValue;

    return Double.valueOf(RANDOM.nextDouble() * (maxVal - minVal) + minVal);
  }

  public boolean isSingleValued() {
    return mininumValue.equals(maximumValue);
  }

  public boolean isSubrangeOf(final RangeConstraints that) {
    return (this.mininumValue >= that.mininumValue && this.mininumValue <= that.maximumValue)
            && (this.maximumValue >= that.mininumValue && this.maximumValue <= that.maximumValue);
  }

  @Override
  public Constraints<Double> mergeConstraints(final Constraints<Double> constraintsToMerge) {
    Preconditions.checkArgument(constraintsToMerge instanceof RangeConstraints,
            "Only merging of " + RangeConstraints.class + " is supported: " + constraintsToMerge);
    final RangeConstraints that = (RangeConstraints) constraintsToMerge;

    final Double mergedMinimumValue;
    final Double mergedMaximumValue;
    if (this.isSubrangeOf(that)) {
      mergedMinimumValue = this.mininumValue;
      mergedMaximumValue = this.maximumValue;
    } else if (that.isSubrangeOf(this)) {
      mergedMinimumValue = that.mininumValue;
      mergedMaximumValue = that.maximumValue;
    } else {
      throw new IllegalArgumentException(String.format("Argument %s is neither a subrange nor superrange of %s", that,
              this));
    }

    //always use discretizeSize of this because it controls merging
    final RangeConstraints mergedConstraints = new RangeConstraints(mergedMinimumValue, mergedMaximumValue,
            this.discretizeSize);
    return mergedConstraints;
  }

  public Double getMininumValue() {
    return mininumValue;
  }

  public Double getMaximumValue() {
    return maximumValue;
  }

  public Double getDiscretizeSize() {
    return discretizeSize;
  }

  @Override
  public ConstraintsEditor<Double> getEditor() {
    return RangeConstraintsEditor.newInstanceFrom(this);
  }

  @Override
  public boolean isFullyConstrained() {
    return isSingleValued();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(mininumValue, maximumValue, discretizeSize);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RangeConstraints)) {
      return false;
    }
    final RangeConstraints that = (RangeConstraints) o;

    return Objects.equal(this.mininumValue, that.mininumValue) && Objects.equal(this.maximumValue, that.maximumValue) && Objects.
            equal(this.discretizeSize, that.discretizeSize);
  }

  @Override
  public String toString() {
    if (isSingleValued()) {
      return Objects.toStringHelper(this).addValue(mininumValue).toString();
    } else {
      return Objects.toStringHelper(this).add("mininumValue", mininumValue).add("maximumValue", maximumValue).add(
              "discretizeSize", discretizeSize).toString();
    }
  }
}
