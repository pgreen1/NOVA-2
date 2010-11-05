package edu.wvu.lcsee.green.model;

import java.io.Serializable;

/**
 *
 * @author pdgreen
 */
public class ModelConfigurationBuilder {

  private ModelConfigurationBuilder() {

  }

  public static ModelConfigurationBuilder newInstance() {
    return new ModelConfigurationBuilder();
  }

  public <V extends Serializable> ModelConfigurationBuilder addAttribute(Attribute<V> attribute, Constraints<V> defaultConstraints,
          Attribute... dependents) {
    //TODO implement me
    throw new UnsupportedOperationException("not implemented");
  }

  public ModelConfiguration build() {
    //TODO implement me
    throw new UnsupportedOperationException("not implemented");

  }
}
