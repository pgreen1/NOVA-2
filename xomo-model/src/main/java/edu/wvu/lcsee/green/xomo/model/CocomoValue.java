package edu.wvu.lcsee.green.xomo.model;

import java.io.Serializable;

/**
 *
 * @author pdgreen
 */
public interface CocomoValue extends Serializable {

  CocomoLevel getLevel();

  double getEffortCoefficient();
}
