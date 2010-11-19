
package edu.wvu.lcsee.green.model.impl;

import edu.wvu.lcsee.green.model.Constraints;
import java.io.Serializable;
import javax.annotation.Nonnull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author pdgreen
 */
public class SingletonConstraints<V extends Serializable> implements Constraints<V> {

  private final V value;

  public SingletonConstraints(@Nonnull final V value) {
    this.value = checkNotNull(value);
  }

  @Override
  public V generateValue() {
    return value;
  }

  @Override
  public Constraints<V> mergeConstraints(@Nonnull final Constraints<V> constraintsToMerge) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
