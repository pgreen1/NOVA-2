package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface ModelConfiguration {

  ImmutableSet<Attribute> getAllAttributes();

  <V extends Serializable> ImmutableMap<Attribute<V>, Constraints<V>> getDefaultConstraints();

  Scenario generateModelInstance(@Nonnull Policy policy, @Nonnull CaseStudy caseStudy);
}
