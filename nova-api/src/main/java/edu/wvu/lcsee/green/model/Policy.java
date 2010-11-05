package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *
 * @author pdgreen
 */
public interface Policy {

  @Nullable
  String getName();

  @Nonnull
  ImmutableSet<Attribute> getModifiableAttibutes();
}
