package edu.wvu.lcsee.green.model;

import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 * An attribute is the smallest unit of a model.  
 * Each attribute has associated with it a set of values
 * of the type specified by {@link #getValueType()}
 * @author pdgreen
 */
public interface Attribute<V extends Serializable> extends Serializable{

  /**
   * A short name used to identify an attribute.
   * @return name of the attribute
   */
  @Nonnull
  String getName();

  /**
   * The description is more detailed than the name.  
   * It won't be used for identification, only for situations where a more 
   * detailed description of the attribute is required.
   * @return description of the attribute
   */
  @Nonnull
  String getDescription();

  /**
   * Each attribute values of a specified type associated with it.
   * @return type of value associated with the attribute
   */
  @Nonnull
  Class<V> getValueType();
}
