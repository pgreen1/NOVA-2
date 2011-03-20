package edu.wvu.lcsee.green.model;

import javax.annotation.Nullable;

/**
 * A CaseStudy defines a particular organization with constraints.
 * While a Project has well defined values for each {@link Attribute}, a case study can have multiple values for an Attribute.
 * Because of this, they are modeled by CaseStudy and using {@link Constrains} for the allowable values of the case study.
 * @author pdgreen
 */
public interface CaseStudy extends AttributeConstrainable {

  /**
   * The name of the CaseStudy.
   * @return name of a the CaseStudy
   */
  @Nullable
  String getName();

}
