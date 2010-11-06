package edu.wvu.lcsee.green.model;

import com.google.common.collect.Maps;
import edu.wvu.lcsee.green.model.impl.ModelConfigurationImpl;
import java.io.Serializable;
import java.util.Map;
import static com.google.common.base.Preconditions.checkState;

/**
 *
 * @author pdgreen
 */
public class ModelConfigurationBuilder {

  private final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> defaultAttributeConstraints = Maps.
          newHashMap();

  private ModelConfigurationBuilder() {
  }

  public static ModelConfigurationBuilder newInstance() {
    return new ModelConfigurationBuilder();
  }

  public <V extends Serializable> ModelConfigurationBuilder addAttribute(Attribute<V> attribute,
          Constraints<V> defaultConstraints,
          Attribute... dependents) {
    checkState(!defaultAttributeConstraints.containsKey(attribute), "attribute already added: " + attribute.getName());
    defaultAttributeConstraints.put(attribute, defaultConstraints);
    return this;
  }

  public ModelConfiguration build() {
    return new ModelConfigurationImpl(defaultAttributeConstraints);
  }
}
