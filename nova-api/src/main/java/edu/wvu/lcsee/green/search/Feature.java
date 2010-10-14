package edu.wvu.lcsee.green.search;

import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public class Feature {

  @Nonnull
  private final Attribute attribute;
  @Nonnull
  private final ImmutableSet<Number> values;

  public Feature(Attribute attribute, ImmutableSet<Number> values) {
    this.attribute = attribute;
    this.values = values;
  }

  public Attribute getAttribute() {
    return attribute;
  }

  public ImmutableSet<Number> getValues() {
    return values;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Feature other = (Feature) obj;
    if (this.attribute != other.attribute && (this.attribute == null || !this.attribute.equals(other.attribute))) {
      return false;
    }
    if (this.values != other.values && (this.values == null || !this.values.equals(other.values))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + (this.attribute != null ? this.attribute.hashCode() : 0);
    hash = 97 * hash + (this.values != null ? this.values.hashCode() : 0);
    return hash;
  }

  //TODO overRide toString
}
