package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *
 * @author pdgreen
 */
public interface CaseStudy {

  @Nullable
  String getName();

  @Nonnull
  <V extends Serializable> ImmutableMap<Attribute<V>, Constraints<V>> getConstraints();
}
