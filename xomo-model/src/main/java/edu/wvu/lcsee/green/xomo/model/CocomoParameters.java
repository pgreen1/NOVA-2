package edu.wvu.lcsee.green.xomo.model;

import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.impl.DefaultAttribute;

/**
 * COCOMO Parameters contains the non-attribute inputs for COCOMO.
 * @author pdgreen
 */
public class CocomoParameters {

  public final static Attribute<Long> KLOC = new DefaultAttribute<Long>("KLOC", "Thousand lines of code", Long.class);
  public final static Attribute<Double> A = new DefaultAttribute<Double>("A", "COCOMO Tuning Parameter A", Double.class);
  public final static Attribute<Double> B = new DefaultAttribute<Double>("B", "COCOMO Tuning Parameter B", Double.class);
  public final static Attribute<Double> C = new DefaultAttribute<Double>("C", "COCOMO Tuning Parameter C", Double.class);
  public final static Attribute<Double> D = new DefaultAttribute<Double>("D", "COCOMO Tuning Parameter D", Double.class);
}
