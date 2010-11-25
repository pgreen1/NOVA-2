package edu.wvu.lcsee.green.model;

import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface Constraints<V extends Serializable> extends Serializable {

  @Nonnull
  V generateValue();

  @Nonnull
  Constraints<V> mergeConstraints(@Nonnull Constraints<V> constraintsToMerge);

  @Nonnull
  ConstraintsEditor<V> getEditor();

  @Nonnull
  boolean isFullyConstrained();
}
