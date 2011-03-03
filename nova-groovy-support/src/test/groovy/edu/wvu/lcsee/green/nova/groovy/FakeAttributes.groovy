
package edu.wvu.lcsee.green.nova.groovy

import edu.wvu.lcsee.green.model.Attribute
import edu.wvu.lcsee.green.model.impl.DefaultAttribute

/**
 * This class containts some basic attributes that can be use for testing.
 * @author pdgreen
 */
class FakeAttributes {
  public final static Attribute<Integer> ATTR1 = new DefaultAttribute<Integer>("ATTR1", "Attribute 1", Integer.class);
  public final static Attribute<Integer> ATTR2 = new DefaultAttribute<Integer>("ATTR2", "Attribute 2", Integer.class);
}