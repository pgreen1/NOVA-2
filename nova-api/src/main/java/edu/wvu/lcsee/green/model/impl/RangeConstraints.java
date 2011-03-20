package edu.wvu.lcsee.green.model.impl;

import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import java.math.BigDecimal;
import java.util.Random;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public class RangeConstraints implements Constraints<Number> {

  public final static int DEFAULT_SPLITS = 4;
  public final static int DEFAULT_SPLIT_FACTOR = 2;
  private static final Random RANDOM = new Random();
  private final Number mininumValue;
  private final Number maximumValue;
  private final int splits;
  private final int splitFactor;

  public RangeConstraints(@Nonnull final Number value) {
    this.mininumValue = value;
    this.maximumValue = value;
    this.splits = DEFAULT_SPLITS;
    this.splitFactor = DEFAULT_SPLIT_FACTOR;
  }

  public RangeConstraints(@Nonnull final Number minimumValue, @Nonnull final Number maximumValue) {
    this.mininumValue = minimumValue;
    this.maximumValue = maximumValue;
    this.splits = DEFAULT_SPLITS;
    this.splitFactor = DEFAULT_SPLIT_FACTOR;
  }

  @Override
  public Number generateValue() {
    final double minVal = mininumValue.doubleValue();
    final double maxVal = maximumValue.doubleValue();

    return BigDecimal.valueOf(RANDOM.nextDouble() * (maxVal - minVal) + minVal);
  }

  @Override
  public Constraints<Number> mergeConstraints(final Constraints<Number> constraintsToMerge) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public ConstraintsEditor<Number> getEditor() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isFullyConstrained() {
    return mininumValue.equals(maximumValue);
  }
}
