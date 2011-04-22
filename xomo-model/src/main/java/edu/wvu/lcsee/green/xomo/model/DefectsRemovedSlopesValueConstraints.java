package edu.wvu.lcsee.green.xomo.model;

import com.google.common.base.Preconditions;
import edu.wvu.lcsee.green.xomo.model.impl.DefectsRemovedSlopesValueImpl;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import edu.wvu.lcsee.green.model.ConstraintsEditor.DiscreteValue;
import edu.wvu.lcsee.green.model.impl.RangeConstraints;
import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Specifies Constraints for a {@link DefectsRemovedSlopesValue}.
 * @author pdgreen
 */
public class DefectsRemovedSlopesValueConstraints implements Constraints<DefectsRemovedSlopesValue> {

  private final RangeConstraints requirementsDefectsRemovedSlopeRangeConstraints;
  private final RangeConstraints designDefectsRemovedSlopeRangeConstraints;
  private final RangeConstraints codingDefectsRemovedSlopeRangeConstraints;

  public DefectsRemovedSlopesValueConstraints(
          @Nonnull final RangeConstraints requirementsDefectsRemovedSlopeRangeConstraints,
          @Nonnull final RangeConstraints designDefectsRemovedSlopeRangeConstraints,
          @Nonnull final RangeConstraints codingDefectsRemovedSlopeRangeConstraints) {
    this.requirementsDefectsRemovedSlopeRangeConstraints = checkNotNull(
            requirementsDefectsRemovedSlopeRangeConstraints);
    this.designDefectsRemovedSlopeRangeConstraints = checkNotNull(designDefectsRemovedSlopeRangeConstraints);
    this.codingDefectsRemovedSlopeRangeConstraints = checkNotNull(codingDefectsRemovedSlopeRangeConstraints);
  }

  @Override
  public DefectsRemovedSlopesValue generateValue() {
    return new DefectsRemovedSlopesValueImpl(
            requirementsDefectsRemovedSlopeRangeConstraints.generateValue().doubleValue(),
            designDefectsRemovedSlopeRangeConstraints.generateValue().doubleValue(),
            codingDefectsRemovedSlopeRangeConstraints.generateValue().doubleValue());
  }

  @Override
  public Constraints<DefectsRemovedSlopesValue> mergeConstraints(
          Constraints<DefectsRemovedSlopesValue> constraintsToMerge) {
    Preconditions.checkArgument(constraintsToMerge instanceof DefectsRemovedSlopesValueConstraints,
            "Only merging of {} is supported: {}", DefectsRemovedSlopesValueConstraints.class, constraintsToMerge);
    @SuppressWarnings("cast")
    final DefectsRemovedSlopesValueConstraints that = (DefectsRemovedSlopesValueConstraints) constraintsToMerge;

    return new DefectsRemovedSlopesValueConstraints(
            (RangeConstraints) this.requirementsDefectsRemovedSlopeRangeConstraints.mergeConstraints(
            that.requirementsDefectsRemovedSlopeRangeConstraints),
            (RangeConstraints) this.designDefectsRemovedSlopeRangeConstraints.mergeConstraints(
            that.designDefectsRemovedSlopeRangeConstraints),
            (RangeConstraints) this.codingDefectsRemovedSlopeRangeConstraints.mergeConstraints(
            that.codingDefectsRemovedSlopeRangeConstraints));
  }

  @Override
  public ConstraintsEditor<DefectsRemovedSlopesValue, ? extends DiscreteValue<DefectsRemovedSlopesValue>> getEditor() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isFullyConstrained() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
