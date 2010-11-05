package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableMap;
import java.io.Serializable;

/**
 *
 * @author pdgreen
 */
public interface Treatment {

  <V extends Serializable> ImmutableMap<Attribute<V>, Constraints<V>> getConstraints();
}
