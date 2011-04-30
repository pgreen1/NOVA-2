package edu.wvu.lcsee.green.model.impl;

import com.google.common.base.Objects;
import edu.wvu.lcsee.green.model.Attribute;
import java.io.Serializable;
import javax.annotation.Nonnull;

public class DefaultAttribute<V extends Serializable> implements Attribute {

  private final String name;
  private final String description;
  private final Class<V> valueType;

  DefaultAttribute(@Nonnull final String name, @Nonnull final String description,
          @Nonnull final Class<V> valueType) {
    this.name = name;
    this.description = description;
    this.valueType = valueType;
  }

  public static <T extends Serializable> DefaultAttribute<T> newInstance(
          @Nonnull final String name,
          @Nonnull final String description,
          @Nonnull final Class<T> valueType) {
    return new DefaultAttribute<T>(name, description, valueType);
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
  public int hashCode() {
    return Objects.hashCode(name, valueType);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DefaultAttribute)) {
      return false;
    }
    final DefaultAttribute that = (DefaultAttribute) o;

    return Objects.equal(this.name, that.name)
            && Objects.equal(this.valueType, this.valueType);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).addValue(name).toString();
  }
}
