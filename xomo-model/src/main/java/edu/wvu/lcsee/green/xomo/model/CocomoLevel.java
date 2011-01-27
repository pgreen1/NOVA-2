package edu.wvu.lcsee.green.xomo.model;

import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public enum CocomoLevel {

  XL(0), VL(1), L(2), N(3), H(4), VH(5), XH(6);
  private final int numericValue;

  private CocomoLevel(@Nonnull final int numericValue) {
    this.numericValue = numericValue;
  }

  public int getNumericValue() {
    return numericValue;
  }
}
