package edu.wvu.lcsee.green.mymodel.model;

import edu.wvu.lcsee.green.model.Attribute;
import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface MyModelAttribute {

  public final static Attribute<Double> FF = new StandardAttribute<Double>("FF", "Fudge Factor", Double.class);
  public final static Attribute<Integer> MEXP = new StandardAttribute<Integer>("MEXP", "Mean Experience", Integer.class);
  public final static Attribute<ProjectSize> PSIZE = new StandardAttribute<ProjectSize>("PSIZE", "Project Size",
          ProjectSize.class);

  public enum ProjectSize {

    SMALL, MEDIUM, LARGE;
  }

  static class StandardAttribute<V extends Serializable> implements Attribute {

    private final String name;
    private final String description;
    private final Class<V> valueType;

    public StandardAttribute(final @Nonnull String name, final @Nonnull String description,
            final @Nonnull Class<V> valueType) {
      this.name = name;
      this.description = description;
      this.valueType = valueType;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public String getDescription() {
      return description;
    }

    @Override
    public Class<V> getValueType() {
      return valueType;
    }

    @Override
    public String toString() {
      return getName();
    }


  }
}
