package edu.wvu.lcsee.green.xomo.model;

import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public class EffortMultiplierValueConstraintsEditor extends CocomoValueConstraintsEditor<EffortMultiplierValue> {

  public EffortMultiplierValueConstraintsEditor(@Nonnull final ConstraintsEditor<CocomoLevel> delegateEditor) {
    super(delegateEditor);
  }

  @Override
  public Constraints<EffortMultiplierValue> generateConstraints() {
    return new EffortMultiplierValueConstraints(delegate.generateConstraints());
  }
}
