package edu.wvu.lcsee.green.xomo.model;

import com.google.common.base.Preconditions;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import edu.wvu.lcsee.green.model.ConstraintsEditor.DiscreteValue;
import javax.annotation.Nonnull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Specifies constraints for a DefectRemovalValue.
 * @author pdgreen
 */
public class DefectRemovalValueConstraints implements Constraints<DefectRemovalValue> {

  final private Constraints<CocomoLevel> levelConstraints;

  public DefectRemovalValueConstraints(@Nonnull final Constraints<CocomoLevel> levelConstraints) {
    this.levelConstraints = checkNotNull(levelConstraints);
  }

  @Override
  public DefectRemovalValue generateValue() {
    //FIXME DefectRemovalValue should generate random value, but it needs to be passed dependent attributes
    return new DefectRemovalValue() {

      @Override
      public double getRequirementsDefectsRemoved() {
        return 0;
      }

      @Override
      public double getDesignDefectsRemoved() {
        return 0;
      }

      @Override
      public double getCodingDefectsRemoved() {
        return 0;
      }

      @Override
      public CocomoLevel getLevel() {
        return CocomoLevel.N;
      }

      @Override
      public double getEffortCoefficient() {
        throw new UnsupportedOperationException("Not supported yet.");
      }
    };
  }

  @Override
  public Constraints<DefectRemovalValue> mergeConstraints(final Constraints<DefectRemovalValue> constraintsToMerge) {
    Preconditions.checkArgument(constraintsToMerge instanceof DefectRemovalValueConstraints,
            "Only merging of {} is supported: {}", DefectRemovalValueConstraints.class, constraintsToMerge);
    @SuppressWarnings("cast")
    final DefectRemovalValueConstraints that = (DefectRemovalValueConstraints) constraintsToMerge;

    return new DefectRemovalValueConstraints(this.levelConstraints.mergeConstraints(that.levelConstraints));
  }

  @Override
  public ConstraintsEditor<DefectRemovalValue, ? extends DiscreteValue<DefectRemovalValue>> getEditor() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isFullyConstrained() {
    return levelConstraints.isFullyConstrained();
  }
}
