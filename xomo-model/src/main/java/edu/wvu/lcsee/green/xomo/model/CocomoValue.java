package edu.wvu.lcsee.green.xomo.model;

import java.io.Serializable;

/**
 * A value of a COQUALMO Attribute. It contains a level and a coefficient to be used for calculating the effort.
 * @author pdgreen
 */
public interface CocomoValue extends Serializable {

  CocomoLevel getLevel();

  //TODO remove getEffortCoefficient from CocomoValue or provide some other interface that has getLevel
  double getEffortCoefficient();
}
