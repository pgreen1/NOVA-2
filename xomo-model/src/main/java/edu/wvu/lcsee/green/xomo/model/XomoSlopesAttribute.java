package edu.wvu.lcsee.green.xomo.model;

import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.impl.DefaultAttribute;

/**
 *
 * @author pdgreen
 */
public final class XomoSlopesAttribute {

  public final static Attribute<XomoSlopesValue> DEFECT_REMOVAL_SLOPE = new DefaultAttribute<XomoSlopesValue>(
          "DEFECT_REMOVAL_SLOPE", "Slope for Defect Removal calculations", XomoSlopesValue.class);
}
