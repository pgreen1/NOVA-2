package edu.wvu.lcsee.green.xomo.model;

import com.google.common.base.Preconditions;
import edu.wvu.lcsee.green.model.Attribute;

/**
 * Effort Multipliers from the COCOMO II model.
 * @author pdgreen
 */
public enum EffortMultiplierAttribute implements Attribute<EffortMultiplierValue> {

  ACAP("Analyst Capability"),
  AEXP("Applications Experience"),
  CPLX("Product Complexity"),
  DATA("Data Base Size"),
  DOCU("Documentation match to life-cycle needs"),
  LTEX("Language and Tool Experience"),
  PCAP("Programmer Capability"),
  PCON("Personnel Continuity"),
  PEXP("Platform Experience"),
  PVOL("Platform Volatility"),
  RELY("Required Software Reliability"),
  RUSE("Required Reusability"),
  SCED("Required Development Schedule"),
  SITE("Multisite Development"),
  STOR("Main Storage Constraint"),
  TIME("Execution Time Constraint"),
  TOOL("Use of Software Tools");
  private final String description;

  EffortMultiplierAttribute(final String description) {
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
  public Class<EffortMultiplierValue> getValueType() {
    return EffortMultiplierValue.class;
  }
}
