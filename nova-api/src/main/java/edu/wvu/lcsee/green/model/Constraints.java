package edu.wvu.lcsee.green.model;

import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 * Constraints is used to define Constraints on Values for Attributes.
 * Constraints are immutable, if one needs to modify them, use mergeConstraints()
 * or generated a {@link ConstraintsEditor}.
 *
 * @author pdgreen
 * @see Attribute
 * @see ConstraintsEditor
 * @see CaseStudy
 * @see Treatment
 * @see Scenario
 */
public interface Constraints<V extends Serializable> extends Serializable {


  /**
   * Returns any value allowable by the Constraints.
   * @return a value allowable by the Constraints
   */
  @Nonnull
  V generateValue();

  /**
   * Merges the current constraints and the specified constraints into a new Constraints.
   * @param constraintsToMerge the constraints to be merged into the current
   * @return a new Constraints created from merging the current Constraints and the specified Constraints
   * @throws IllegalArgumentException when no new Constraints can be created due to no intersection between the Constraints.
   */
  @Nonnull
  Constraints<V> mergeConstraints(@Nonnull Constraints<V> constraintsToMerge);

  /**
   * Generates an {@link ConstraintsEditor} with the same Values as the Constraints.
   * @return a ConstraintsEditor with the same Values as the Constraints
   */
  @Nonnull
  ConstraintsEditor<V> getEditor();

  /**
   * Returns whether or not the Constraints is fully constrained or not.  Fully Constrained is defined as not being able to be constrained any further.
   * @return whether or not the Constraints is fully constrained.
   */
  @Nonnull
  boolean isFullyConstrained();
}
