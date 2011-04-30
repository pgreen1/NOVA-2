package edu.wvu.lcsee.green.xomo.model;

import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.impl.DefaultAttribute;

/**
 * When calculating scores for COCOMO, the effort calculation uses a slope.
 * This slope changes depending on the attribute.
 * @author pdgreen
 */
public class CocomoSlopesAttribute {

  public static final Attribute<Double> SF_EFFORT_COEFFICIENT_SLOPE = DefaultAttribute.newInstance(
          "SF_EFFORT_COEFFICENT_SLOPE", "Slope for effort coefficient for SF", Double.class);
  public static final Attribute<Double> EM_PLUS_EFFORT_COEFFICIENT_SLOPE = DefaultAttribute.newInstance(
          "EM_PLUS_EFFORT_COEFFICENT_SLOPE", "Slope for effort coefficient for EM with positive correlation",
          Double.class);
  public static final Attribute<Double> EM_MINUS_EFFORT_COEFFICIENT_SLOPE = DefaultAttribute.newInstance(
          "EM_MINUS_EFFORT_COEFFICENT_SLOPE", "Slope for effort coefficient for EM with negative correlation",
          Double.class);
}
