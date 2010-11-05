package edu.wvu.lcsee.green.model;

import java.io.Serializable;

/**
 *
 * @author pdgreen
 */
public interface Attribute<V extends Serializable> extends Serializable{

  String getName();

  String getDescription();

  Class<V> getValueType();
}
