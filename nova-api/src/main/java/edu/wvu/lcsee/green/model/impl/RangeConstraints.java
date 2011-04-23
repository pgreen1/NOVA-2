package edu.wvu.lcsee.green.model.impl;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsContext;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import java.math.BigDecimal;
import java.util.Random;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public class RangeConstraints implements Constraints<Number> {

  private static final Random RANDOM = new Random();
  private final Number mininumValue;
  private final Number maximumValue;
  private final Number discretizeSize;

  public RangeConstraints(@Nonnull final Number value) {
    this.mininumValue = value;
    this.maximumValue = value;
    this.discretizeSize = null;
  }

  public RangeConstraints(@Nonnull final Number minimumValue, @Nonnull final Number maximumValue,
          @Nonnull final Number discretizeSize) {
    this.mininumValue = minimumValue;
    this.maximumValue = maximumValue;
    this.discretizeSize = discretizeSize;
  }

  @Override
  public Number generateValue(final ConstraintsContext currentContext) {
    final double minVal = mininumValue.doubleValue();
    final double maxVal = maximumValue.doubleValue();

    return BigDecimal.valueOf(RANDOM.nextDouble() * (maxVal - minVal) + minVal);
  }

  public boolean isSingleValued() {
    return mininumValue.equals(maximumValue);
  }

  public boolean isSubrangeOf(final RangeConstraints that) {
    return (this.mininumValue.doubleValue() >= that.mininumValue.doubleValue() && this.mininumValue.doubleValue() <= that.maximumValue.
            doubleValue())
            && (this.maximumValue.doubleValue() >= that.mininumValue.doubleValue() && this.maximumValue.doubleValue() <= that.maximumValue.
            doubleValue());
  }

  @Override
  public Constraints<Number> mergeConstraints(final Constraints<Number> constraintsToMerge) {
    Preconditions.checkArgument(constraintsToMerge instanceof RangeConstraints,
            "Only merging of " + RangeConstraints.class + " is supported: " + constraintsToMerge);
    final RangeConstraints that = (RangeConstraints) constraintsToMerge;

    final Number mergedMinimumValue;
    final Number mergedMaximumValue;
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

  public Number getMininumValue() {
    return mininumValue;
  }

  public Number getMaximumValue() {
    return maximumValue;
  }

  public Number getDiscretizeSize() {
    return discretizeSize;
  }

  @Override
  public ConstraintsEditor<Number> getEditor() {
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
