package edu.wvu.lcsee.green.xomo.model.impl;

import edu.wvu.lcsee.green.xomo.model.XomoSlopesValue;

/**
 *
 * @author pdgreen
 */
public class XomoSlopesValueImpl implements XomoSlopesValue {

  private final double effortCoefficentSlope;
  private final double emRequirmentsDefectsIntroducedSlope;
  private final double emDesignDefectsIntroducedSlope;
  private final double emCodingDefectsIntroducedSlope;
  private final double sfRequirmentsDefectsIntroducedSlope;
  private final double sfDesignDefectsIntroducedSlope;
  private final double sfCodingDefectsIntroducedSlope;
  private final double requirmentsDefectsRemovedSlope;
  private final double designDefectsRemovedSlope;
  private final double codingDefectsRemovedSlope;

  //FIXME too many parameters
  public XomoSlopesValueImpl(double effortCoefficentSlope, double emRequirmentsDefectsIntroducedSlope,
          double emDesignDefectsIntroducedSlope, double emCodingDefectsIntroducedSlope,
          double sfRequirmentsDefectsIntroducedSlope, double sfDesignDefectsIntroducedSlope,
          double sfCodingDefectsIntroducedSlope, double requirmentsDefectsRemovedSlope, double designDefectsRemovedSlope,
          double codingDefectsRemovedSlope) {
    this.effortCoefficentSlope = effortCoefficentSlope;
    this.emRequirmentsDefectsIntroducedSlope = emRequirmentsDefectsIntroducedSlope;
    this.emDesignDefectsIntroducedSlope = emDesignDefectsIntroducedSlope;
    this.emCodingDefectsIntroducedSlope = emCodingDefectsIntroducedSlope;
    this.sfRequirmentsDefectsIntroducedSlope = sfRequirmentsDefectsIntroducedSlope;
    this.sfDesignDefectsIntroducedSlope = sfDesignDefectsIntroducedSlope;
    this.sfCodingDefectsIntroducedSlope = sfCodingDefectsIntroducedSlope;
    this.requirmentsDefectsRemovedSlope = requirmentsDefectsRemovedSlope;
    this.designDefectsRemovedSlope = designDefectsRemovedSlope;
    this.codingDefectsRemovedSlope = codingDefectsRemovedSlope;
  }

  @Override
  public double getCodingDefectsRemovedSlope() {
    return codingDefectsRemovedSlope;
  }

  @Override
  public double getDesignDefectsRemovedSlope() {
    return designDefectsRemovedSlope;
  }

  @Override
  public double getEffortCoefficentSlope() {
    return effortCoefficentSlope;
  }

  @Override
  public double getEmCodingDefectsIntroducedSlope() {
    return emCodingDefectsIntroducedSlope;
  }

  @Override
  public double getEmDesignDefectsIntroducedSlope() {
    return emDesignDefectsIntroducedSlope;
  }

  @Override
  public double getEmRequirmentsDefectsIntroducedSlope() {
    return emRequirmentsDefectsIntroducedSlope;
  }

  @Override
  public double getRequirmentsDefectsRemovedSlope() {
    return requirmentsDefectsRemovedSlope;
  }

  @Override
  public double getSfCodingDefectsIntroducedSlope() {
    return sfCodingDefectsIntroducedSlope;
  }

  @Override
  public double getSfDesignDefectsIntroducedSlope() {
    return sfDesignDefectsIntroducedSlope;
  }

  @Override
  public double getSfRequirmentsDefectsIntroducedSlope() {
    return sfRequirmentsDefectsIntroducedSlope;
  }
}
