package edu.wvu.lcsee.green.xomo.model;

import com.google.common.base.Preconditions;
import edu.wvu.lcsee.green.model.Attribute;

/**
 * Scale factors from the COCOMO II model.
 * @author pdgreen
 */
public enum ScaleFactorAttribute implements Attribute<ScaleFactorValue> {

  FLEX("Development Flexability"),
  PMAT("Process Maturity"),
  PREC("Precedentedness"),
  RESL("Architecture / Risk Resolution"),
  TEAM("Team Cohesion");
  private final String description;

  ScaleFactorAttribute(final String description) {
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
  public Class<ScaleFactorValue> getValueType() {
    return ScaleFactorValue.class;
  }
}
