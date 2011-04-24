package edu.wvu.lcsee.green.xomo.model;

import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.ConstraintsContext;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import edu.wvu.lcsee.green.xomo.model.impl.ValueFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Specifies Constraints for a {@link ScaleFactorValue}.
 * @author pdgreen
 */
public class ScaleFactorValueConstraints implements Constraints<ScaleFactorValue> {

  private final static ImmutableSet<Attribute<?>> DEPENDENT_ATTRIBUTES = ImmutableSet.<Attribute<?>>of(
          CocomoSlopesAttribute.SF_EFFORT_COEFFICIENT_SLOPE,
          CoqualmoSlopesAttribute.SF_DEFECTS_INTRODUCED_SLOPES);
  private final Constraints<CocomoLevel> levelConstraints;

  public ScaleFactorValueConstraints(@Nonnull final Constraints<CocomoLevel> levelConstraints) {
    this.levelConstraints = checkNotNull(levelConstraints);
  }

  @Override
  public ImmutableSet<Attribute<?>> getDependentAttributes() {
    return DEPENDENT_ATTRIBUTES;
  }

  @Override
  public ScaleFactorValue generateValue(final ConstraintsContext currentContext) {
    return ValueFactory.newScaleFactorValue(levelConstraints.generateValue(currentContext),
            currentContext.getValueFor(CocomoSlopesAttribute.SF_EFFORT_COEFFICIENT_SLOPE).doubleValue(),
            currentContext.getValueFor(CoqualmoSlopesAttribute.SF_DEFECTS_INTRODUCED_SLOPES));
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
  public ConstraintsEditor<ScaleFactorValue> getEditor() {
    return new ScaleFactorValueConstraintsEditor(levelConstraints.getEditor());
  }

  @Override
  public boolean isFullyConstrained() {
    return levelConstraints.isFullyConstrained();
  }
}
