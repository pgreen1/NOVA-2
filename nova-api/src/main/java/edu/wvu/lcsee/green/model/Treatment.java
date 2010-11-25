package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface Treatment {

  @Nonnull
  ImmutableSet<Attribute<? extends Serializable>> getAllAttributes();

  @Nonnull
  <V extends Serializable> Constraints<V> getConstraintsFor(@Nonnull Attribute<V> attribute);
}
