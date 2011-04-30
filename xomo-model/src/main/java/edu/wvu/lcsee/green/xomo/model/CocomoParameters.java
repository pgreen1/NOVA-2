package edu.wvu.lcsee.green.xomo.model;

import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.impl.DefaultAttribute;

/**
 * COCOMO Parameters contains the non-attribute inputs for COCOMO.
 * @author pdgreen
 */
public class CocomoParameters {

  public final static Attribute<Long> KLOC = DefaultAttribute.newInstance("KLOC", "Thousand lines of code", Long.class);
  public final static Attribute<Double> A = DefaultAttribute.newInstance("A", "COCOMO Tuning Parameter A", Double.class);
  public final static Attribute<Double> B = DefaultAttribute.newInstance("B", "COCOMO Tuning Parameter B", Double.class);
  public final static Attribute<Double> C = DefaultAttribute.newInstance("C", "COCOMO Tuning Parameter C", Double.class);
  public final static Attribute<Double> D = DefaultAttribute.newInstance("D", "COCOMO Tuning Parameter D", Double.class);
}
