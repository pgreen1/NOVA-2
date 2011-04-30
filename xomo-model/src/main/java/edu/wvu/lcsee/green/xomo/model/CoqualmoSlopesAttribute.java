package edu.wvu.lcsee.green.xomo.model;

import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.impl.DefaultAttribute;

/**
 * When calculating scores for COQUALMO, the defects calculation uses a slope.
 * This slope changes depending on the attribute.
 * @author pdgreen
 */
public class CoqualmoSlopesAttribute {

  public static final Attribute<DefectsIntroducedSlopesValue> SF_DEFECTS_INTRODUCED_SLOPES = DefaultAttribute.
          newInstance(
          "SF_DEFECTS_INTRODUCED_SLOPES", "Slopes for defects introduced from Scale Factors",
          DefectsIntroducedSlopesValue.class);
  public static final Attribute<DefectsIntroducedSlopesValue> EM_PLUS_DEFECTS_INTRODUCED_SLOPES = DefaultAttribute.
          newInstance(
          "EM_PLUS_DEFECTS_INTRODUCED_SLOPES",
          "Slopes for defects introduced from Effort Multipliers with positive correlation",
          DefectsIntroducedSlopesValue.class);
  public static final Attribute<DefectsIntroducedSlopesValue> EM_MINUS_DEFECTS_INTRODUCED_SLOPES = DefaultAttribute.
          newInstance(
          "EM_MINUS_DEFECTS_INTRODUCED_SLOPES",
          "Slopes for defects introduced from Effort Multipliers with negative correlation",
          DefectsIntroducedSlopesValue.class);
  public static final Attribute<DefectsRemovedSlopesValue> DEFECT_REMOVAL_SLOPES = DefaultAttribute.newInstance(
          "DEFECT_REMOVAL_SLOPES", "Slopes for defects removed", DefectsRemovedSlopesValue.class);
}
