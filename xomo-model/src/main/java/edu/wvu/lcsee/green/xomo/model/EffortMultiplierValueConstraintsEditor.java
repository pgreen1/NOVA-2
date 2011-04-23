package edu.wvu.lcsee.green.xomo.model;

import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import javax.annotation.Nonnull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author pdgreen
 */
public class EffortMultiplierValueConstraintsEditor extends CocomoValueConstraintsEditor<EffortMultiplierValue> {

  private final Attribute<Double> effortCoefficientSlopeAttribute;
  private final Attribute<DefectsIntroducedSlopesValue> defectsIntroducedSlopeAttribute;
  //TODO consider making editors inner non-static classes, that would prevent the need to keep passing
  //atttibutes from constraints that are only used to generate a new constraint

  public EffortMultiplierValueConstraintsEditor(@Nonnull final Attribute<Double> effortCoefficientSlopeAttribute,
          @Nonnull final Attribute<DefectsIntroducedSlopesValue> defectsIntroducedSlopeAttribute,
          @Nonnull final ConstraintsEditor<CocomoLevel> delegateEditor) {
    super(delegateEditor);
    this.effortCoefficientSlopeAttribute = checkNotNull(effortCoefficientSlopeAttribute);
    this.defectsIntroducedSlopeAttribute = checkNotNull(defectsIntroducedSlopeAttribute);
  }

  @Override
  public Constraints<EffortMultiplierValue> generateConstraints() {
    return new EffortMultiplierValueConstraints(effortCoefficientSlopeAttribute, defectsIntroducedSlopeAttribute, delegate.
            generateConstraints());
  }
}
