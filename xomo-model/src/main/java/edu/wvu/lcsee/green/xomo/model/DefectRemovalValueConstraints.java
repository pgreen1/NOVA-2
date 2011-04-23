package edu.wvu.lcsee.green.xomo.model;

import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.ConstraintsContext;
import com.google.common.base.Preconditions;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import edu.wvu.lcsee.green.xomo.model.impl.ValueFactory;
import javax.annotation.Nonnull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Specifies constraints for a DefectRemovalValue.
 * @author pdgreen
 */
public class DefectRemovalValueConstraints implements Constraints<DefectRemovalValue> {

  private final static ImmutableSet<Attribute<?>> DEPENDENT_ATTRIBUTES = ImmutableSet.<Attribute<?>>of(
          CoqualmoSlopesAttribute.DEFECT_REMOVAL_SLOPES);
  private final Constraints<CocomoLevel> levelConstraints;

  public DefectRemovalValueConstraints(@Nonnull final Constraints<CocomoLevel> levelConstraints) {
    this.levelConstraints = checkNotNull(levelConstraints);
  }

  @Override
  public ImmutableSet<Attribute<?>> getDependentAttributes() {
    return DEPENDENT_ATTRIBUTES;
  }

  @Override
  public DefectRemovalValue generateValue(final ConstraintsContext currentContext) {
    return ValueFactory.newDefectRemoval(levelConstraints.generateValue(currentContext), currentContext.getValueFor(
            CoqualmoSlopesAttribute.DEFECT_REMOVAL_SLOPES));
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
  public ConstraintsEditor<DefectRemovalValue> getEditor() {
    return new DefectRemovalValueConstraintsEditor(levelConstraints.getEditor());
  }

  @Override
  public boolean isFullyConstrained() {
    return levelConstraints.isFullyConstrained();
  }
}
