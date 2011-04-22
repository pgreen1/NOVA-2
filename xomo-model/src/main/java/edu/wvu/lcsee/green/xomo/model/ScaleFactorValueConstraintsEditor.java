package edu.wvu.lcsee.green.xomo.model;

import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public class ScaleFactorValueConstraintsEditor extends CocomoValueConstraintsEditor<ScaleFactorValue> {

  public ScaleFactorValueConstraintsEditor(@Nonnull final ConstraintsEditor<CocomoLevel> delegateEditor) {
    super(delegateEditor);
  }

  @Override
  public Constraints<ScaleFactorValue> generateConstraints() {
    return new ScaleFactorValueConstraints(delegate.generateConstraints());
  }
}
