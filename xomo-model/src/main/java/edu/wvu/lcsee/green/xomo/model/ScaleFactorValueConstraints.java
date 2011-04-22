package edu.wvu.lcsee.green.xomo.model;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import edu.wvu.lcsee.green.model.ConstraintsEditor.DiscreteValue;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Specifies Constraints for a {@link ScaleFactorValue}.
 * @author pdgreen
 */
public class ScaleFactorValueConstraints implements Constraints<ScaleFactorValue> {

  final private Constraints<CocomoLevel> levelConstraints;

  public ScaleFactorValueConstraints(@Nonnull final Constraints<CocomoLevel> levelConstraints) {
    this.levelConstraints = checkNotNull(levelConstraints);
  }

  @Override
  public ScaleFactorValue generateValue() {
    //FIXME ScaleFactorValue should generate random value, but it needs to be passed dependent attributes
    return new ScaleFactorValue() {

      @Override
      public CocomoLevel getLevel() {
        return CocomoLevel.N;
      }

      @Override
      public double getEffortCoefficient() {
        return 0;
      }

      @Override
      public double getRequirementsDefects() {
        return 0;
      }

      @Override
      public double getDesignDefects() {
        return 0;
      }

      @Override
      public double getCodingDefects() {
        return 0;
      }
    };
  }

  @Override
  public Constraints<ScaleFactorValue> mergeConstraints(final Constraints<ScaleFactorValue> constraintsToMerge) {
    Preconditions.checkArgument(constraintsToMerge instanceof ScaleFactorValueConstraints,
            "Only merging of {} is supported: {}", ScaleFactorValueConstraints.class, constraintsToMerge);
    @SuppressWarnings("cast")
    final ScaleFactorValueConstraints that = (ScaleFactorValueConstraints) constraintsToMerge;

    return new ScaleFactorValueConstraints(this.levelConstraints.mergeConstraints(that.levelConstraints));
  }

  @Override
  public ConstraintsEditor<ScaleFactorValue, ? extends DiscreteValue<ScaleFactorValue>> getEditor() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isFullyConstrained() {
    return levelConstraints.isFullyConstrained();
  }
}
