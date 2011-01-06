package edu.wvu.lcsee.green.model.impl;

import edu.wvu.lcsee.green.model.Constraints;
import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 * Implementation of {@link Constraints} when there is only one value.
 *
 * @author pdgreen
 */
public class SingletonConstraints<V extends Serializable> extends SetConstraints<V> {

  public SingletonConstraints(@Nonnull final V value) {
    super(value);
  }
}
