package edu.wvu.lcsee.green.xomo.model;

import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public class DefectRemovalValueConstraintsEditor extends CocomoValueConstraintsEditor<DefectRemovalValue> {

  public DefectRemovalValueConstraintsEditor(@Nonnull final ConstraintsEditor<CocomoLevel> delegateEditor) {
    super(delegateEditor);
  }

  @Override
  public Constraints<DefectRemovalValue> generateConstraints() {
    return new DefectRemovalValueConstraints(delegate.generateConstraints());
  }
}
