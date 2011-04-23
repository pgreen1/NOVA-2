package edu.wvu.lcsee.green.xomo.model;

import edu.wvu.lcsee.green.xomo.model.impl.ValueFactory;
import edu.wvu.lcsee.green.model.ConstraintsContext;
import com.google.common.base.Preconditions;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Specifies Constraints for a {@link EffortMultiplierValue}.
 * @author pdgreen
 */
public class EffortMultiplierValueConstraints implements Constraints<EffortMultiplierValue> {

  final private Constraints<CocomoLevel> levelConstraints;

  public EffortMultiplierValueConstraints(@Nonnull final Constraints<CocomoLevel> levelConstraints) {
    this.levelConstraints = checkNotNull(levelConstraints);
  }

  @Override
  public EffortMultiplierValue generateValue(final ConstraintsContext currentContext) {
    //FIXME EffortMultiplierValueConstraints constructor needs to define the correct EM PLUS/MINUS slopes
    return ValueFactory.newEffortMuliplierValue(levelConstraints.generateValue(currentContext),
            currentContext.getValueFor(CocomoSlopesAttribute.EM_MINUS_EFFORT_COEFFICIENT_SLOPE),
            currentContext.getValueFor(CoqualmoSlopesAttribute.EM_PLUS_DEFECTS_INTRODUCED_SLOPES));
  }

  @Override
  public Constraints<EffortMultiplierValue> mergeConstraints(final Constraints<EffortMultiplierValue> constraintsToMerge) {
    Preconditions.checkArgument(constraintsToMerge instanceof EffortMultiplierValueConstraints,
            "Only merging of {} is supported: {}", EffortMultiplierValueConstraints.class, constraintsToMerge);
    @SuppressWarnings("cast")
    final EffortMultiplierValueConstraints that = (EffortMultiplierValueConstraints) constraintsToMerge;

    return new EffortMultiplierValueConstraints(this.levelConstraints.mergeConstraints(that.levelConstraints));
  }

  @Override
  public ConstraintsEditor<EffortMultiplierValue> getEditor() {
    return new EffortMultiplierValueConstraintsEditor(levelConstraints.getEditor());
  }

  @Override
  public boolean isFullyConstrained() {
    return levelConstraints.isFullyConstrained();
  }
}
