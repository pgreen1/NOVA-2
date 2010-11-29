package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A CaseStudy defines a particular organization with constraints.
 * While a Project has well defined values for each {@link Attribute}, a case study can have multiple values for an Attribute.
 * Because of this, they are modeled by CaseStudy and using {@link Constrains} for the allowable values of the case study.
 * @author pdgreen
 */
public interface CaseStudy {

  /**
   * The name of the CaseStudy.
   * @return name of a the CaseStudy
   */
  @Nullable
  String getName();

  /**
   * All attributes specified in the CaseStudy.
   * @return all attributes specified in the CaseStudy
   */
  @Nonnull
  ImmutableSet<Attribute<? extends Serializable>> getAllAttributes();

  /**
   * Returns the Constraints for the given Attribute
   * @param <V> The Value Type of the Attribute and Constraints.
   * @param attribute the given attribute for which to return the Constraints.
   * @return The Constraints for the given attribute.
   */
  @Nonnull
  <V extends Serializable> Constraints<V> getConstraintsFor(@Nonnull Attribute<V> attribute);
}
