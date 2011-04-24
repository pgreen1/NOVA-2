package edu.wvu.lcsee.green.xomo.model;

import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
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

  private final Attribute<Double> effortCoefficientSlopeAttribute;
  private final Attribute<DefectsIntroducedSlopesValue> defectsIntroducedSlopeAttribute;
  private final ImmutableSet<Attribute<?>> dependentAttributes;
  private final Constraints<CocomoLevel> levelConstraints;

  public EffortMultiplierValueConstraints(
          @Nonnull final Attribute<Double> effortCoefficientSlopeAttribute,
          @Nonnull final Attribute<DefectsIntroducedSlopesValue> defectsIntroducedSlopeAttribute,
          @Nonnull final Constraints<CocomoLevel> levelConstraints) {
    this.effortCoefficientSlopeAttribute = checkNotNull(effortCoefficientSlopeAttribute);
    this.defectsIntroducedSlopeAttribute = checkNotNull(defectsIntroducedSlopeAttribute);
    this.dependentAttributes = ImmutableSet.<Attribute<?>>of(
            effortCoefficientSlopeAttribute,
            defectsIntroducedSlopeAttribute);
    this.levelConstraints = checkNotNull(levelConstraints);
  }

  @Override
  public ImmutableSet<Attribute<?>> getDependentAttributes() {
    return dependentAttributes;
  }

  @Override
  public EffortMultiplierValue generateValue(final ConstraintsContext currentContext) {
    return ValueFactory.newEffortMuliplierValue(levelConstraints.generateValue(currentContext),
            currentContext.getValueFor(effortCoefficientSlopeAttribute).doubleValue(),
            currentContext.getValueFor(defectsIntroducedSlopeAttribute));
  }

  @Override
  public Constraints<EffortMultiplierValue> mergeConstraints(final Constraints<EffortMultiplierValue> constraintsToMerge) {
    Preconditions.checkArgument(constraintsToMerge instanceof EffortMultiplierValueConstraints,
            "Only merging of {} is supported: {}", EffortMultiplierValueConstraints.class, constraintsToMerge);
    @SuppressWarnings("cast")
    final EffortMultiplierValueConstraints that = (EffortMultiplierValueConstraints) constraintsToMerge;

    return new EffortMultiplierValueConstraints(effortCoefficientSlopeAttribute, defectsIntroducedSlopeAttribute, this.levelConstraints.
            mergeConstraints(that.levelConstraints));
  }

  @Override
  public ConstraintsEditor<EffortMultiplierValue> getEditor() {
    return new EffortMultiplierValueConstraintsEditor(effortCoefficientSlopeAttribute, defectsIntroducedSlopeAttribute, levelConstraints.getEditor());
  }

  @Override
  public boolean isFullyConstrained() {
    return levelConstraints.isFullyConstrained();
  }
}
