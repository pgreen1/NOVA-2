package edu.wvu.lcsee.green.mymodel.model;

import edu.wvu.lcsee.green.model.impl.DefaultAttribute;
import edu.wvu.lcsee.green.model.Attribute;

/**
 * Attributes used in MyModel.
 *
 * @author pdgreen
 */
public final class MyModelAttribute {

  public static final Attribute<Double> FF = DefaultAttribute.newInstance("FF", "Fudge Factor", Double.class);
  public static final Attribute<Integer> MEXP = DefaultAttribute.newInstance("MEXP", "Mean Experience", Integer.class);
  public static final Attribute<ProjectSize> PSIZE = DefaultAttribute.newInstance("PSIZE", "Project Size",
          ProjectSize.class);

  public enum ProjectSize {

    SMALL, MEDIUM, LARGE;
  }

  private MyModelAttribute() {
    super();
  }
}
