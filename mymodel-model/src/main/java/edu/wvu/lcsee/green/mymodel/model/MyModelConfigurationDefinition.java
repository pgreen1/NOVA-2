package edu.wvu.lcsee.green.mymodel.model;

import edu.wvu.lcsee.green.model.Constraints;
import com.google.common.collect.ImmutableMap;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.ModelConfiguration;
import java.io.Serializable;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.CaseStudy;
import edu.wvu.lcsee.green.model.ModelConfigurationBuilder;
import edu.wvu.lcsee.green.model.NamedConstrainableAttributes;
import edu.wvu.lcsee.green.model.impl.CaseStudyImpl;
import edu.wvu.lcsee.green.model.impl.NamedConstrainableAttributesImpl;
import edu.wvu.lcsee.green.model.impl.SetConstraints;

import static edu.wvu.lcsee.green.mymodel.model.MyModelAttribute.*;

/**
 *
 * @author pdgreen
 */
public class MyModelConfigurationDefinition {

  public static ModelConfiguration MY_MODEL_CONFIGURATION = ModelConfigurationBuilder.newInstance().addAttribute(FF, new SetConstraints(
          0.0, 0.25, 0.50, 0.75, 1.0)).addAttribute(MEXP, new SetConstraints(
          1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
          12, 13, 14, 15, 16, 117, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30)).addAttribute(PSIZE, new SetConstraints(ProjectSize.
          values())).build();
  public static NamedConstrainableAttributes ATTRIBUTE_CONTEXT_DEFAULT = new NamedConstrainableAttributesImpl("DEFAULT", ImmutableSet.<Attribute<? extends Serializable>>of(MEXP, PSIZE));
  public static CaseStudy CASE_STUDY_DEFAULT = new CaseStudyImpl("DEFAULT", ImmutableMap.<Attribute<? extends Serializable>, Constraints<? extends Serializable>>
          of());
}
