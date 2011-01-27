package edu.wvu.lcsee.green.xomo.model;

import com.google.common.base.Preconditions;
import edu.wvu.lcsee.green.model.Attribute;

/**
 * Defect Removals from the COQUALMO model.
 * @author pdgreen
 */
public enum DefectRemovalAttribute implements Attribute<DefectRemovalValue> {
  AA("Automated Analysis"),
  ETAT("Execution Testing and Tools"),
  PR("People Reviews");
  private final String description;

  DefectRemovalAttribute(final String description) {
    this.description = Preconditions.checkNotNull(description);
  }

  @Override
  public String getName() {
    return name();
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public Class<DefectRemovalValue> getValueType() {
    return DefectRemovalValue.class;
  }
}
