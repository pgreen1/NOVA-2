package edu.wvu.lcsee.green.xomo.model;

import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.impl.DefaultAttribute;

/**
 * When calculating scores for COQUALMO, the defects calculation uses a slope.
 * This slope changes depending on the attribute.
 * @author pdgreen
 */
public class CoqualmoSlopesAttribute {

  public final static Attribute<Long> SF_DEFECTS_INTRODUCED_SLOPES = new DefaultAttribute<DefectsIntroducedSlopesValue>(
          "SF_DEFECTS_INTRODUCED_SLOPES", "Slopes for defects introduced from Scale Factors",
          DefectsIntroducedSlopesValue.class);
  public final static Attribute<Long> EM_PLUS_DEFECTS_INTRODUCED_SLOPES = new DefaultAttribute<DefectsIntroducedSlopesValue>(
          "EM_DEFECTS_INTRODUCED_SLOPES", "Slopes for defects introduced from Effort Multipliers with positive correlation",
          DefectsIntroducedSlopesValue.class);
  public final static Attribute<Long> EM_MINUS_DEFECTS_INTRODUCED_SLOPES = new DefaultAttribute<DefectsIntroducedSlopesValue>(
          "EM_DEFECTS_INTRODUCED_SLOPES", "Slopes for defects introduced from Effort Multipliers with negative correlation",
          DefectsIntroducedSlopesValue.class);
  public final static Attribute<Long> DEFECT_REMOVAL_SLOPES = new DefaultAttribute<DefectsRemovedSlopesValue>(
          "DEFECT_REMOVAL_SLOPES", "Slopes for defects removed", DefectsRemovedSlopesValue.class);
}
