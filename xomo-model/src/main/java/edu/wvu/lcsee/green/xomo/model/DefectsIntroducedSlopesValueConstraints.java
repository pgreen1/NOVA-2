package edu.wvu.lcsee.green.xomo.model;

import com.google.common.base.Preconditions;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import edu.wvu.lcsee.green.model.ConstraintsEditor.DiscreteValue;
import edu.wvu.lcsee.green.model.impl.RangeConstraints;
import edu.wvu.lcsee.green.xomo.model.impl.DefectsIntroducedSlopesValueImpl;
import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Specifies Constraints for a {@link DefectsIntroducedSlopesValue}.
 * @author pdgreen
 */
public class DefectsIntroducedSlopesValueConstraints implements Constraints<DefectsIntroducedSlopesValue> {

  private final RangeConstraints requirementsDefectsIntroducedSlopeRangeConstraints;
  private final RangeConstraints designDefectsIntroducedSlopeRangeConstraints;
  private final RangeConstraints codingDefectsIntroducedSlopeRangeConstraints;

  public DefectsIntroducedSlopesValueConstraints(
          @Nonnull final RangeConstraints requirementsDefectsIntroducedSlopeRangeConstraints,
          @Nonnull final RangeConstraints designDefectsIntroducedSlopeRangeConstraints,
          @Nonnull final RangeConstraints codingDefectsIntroducedSlopeRangeConstraints) {
    this.requirementsDefectsIntroducedSlopeRangeConstraints = checkNotNull(
            requirementsDefectsIntroducedSlopeRangeConstraints);
    this.designDefectsIntroducedSlopeRangeConstraints = checkNotNull(designDefectsIntroducedSlopeRangeConstraints);
    this.codingDefectsIntroducedSlopeRangeConstraints = checkNotNull(codingDefectsIntroducedSlopeRangeConstraints);
  }

  @Override
  public DefectsIntroducedSlopesValue generateValue() {
    return new DefectsIntroducedSlopesValueImpl(
            requirementsDefectsIntroducedSlopeRangeConstraints.generateValue().doubleValue(),
            designDefectsIntroducedSlopeRangeConstraints.generateValue().doubleValue(),
            codingDefectsIntroducedSlopeRangeConstraints.generateValue().doubleValue());
  }

  @Override
  public Constraints<DefectsIntroducedSlopesValue> mergeConstraints(
          final Constraints<DefectsIntroducedSlopesValue> constraintsToMerge) {
    Preconditions.checkArgument(constraintsToMerge instanceof DefectsIntroducedSlopesValueConstraints,
            "Only merging of {} is supported: {}", DefectsIntroducedSlopesValueConstraints.class, constraintsToMerge);
    @SuppressWarnings("cast")
    final DefectsIntroducedSlopesValueConstraints that = (DefectsIntroducedSlopesValueConstraints) constraintsToMerge;

    return new DefectsIntroducedSlopesValueConstraints(
            (RangeConstraints) this.requirementsDefectsIntroducedSlopeRangeConstraints.mergeConstraints(
            that.requirementsDefectsIntroducedSlopeRangeConstraints),
            (RangeConstraints) this.designDefectsIntroducedSlopeRangeConstraints.mergeConstraints(
            that.designDefectsIntroducedSlopeRangeConstraints),
            (RangeConstraints) this.codingDefectsIntroducedSlopeRangeConstraints.mergeConstraints(
            that.codingDefectsIntroducedSlopeRangeConstraints));
  }

  @Override
  public ConstraintsEditor<DefectsIntroducedSlopesValue> getEditor() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isFullyConstrained() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
