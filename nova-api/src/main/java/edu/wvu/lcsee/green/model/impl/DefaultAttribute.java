package edu.wvu.lcsee.green.model.impl;

import edu.wvu.lcsee.green.model.Attribute;
import java.io.Serializable;
import javax.annotation.Nonnull;

public class DefaultAttribute<V extends Serializable> implements Attribute {

  private final String name;
  private final String description;
  private final Class<V> valueType;

  public DefaultAttribute(@Nonnull final String name, @Nonnull final String description,
          @Nonnull final Class<V> valueType) {
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
