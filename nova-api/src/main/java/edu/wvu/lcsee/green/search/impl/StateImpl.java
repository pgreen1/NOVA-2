package edu.wvu.lcsee.green.search.impl;

import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.search.State;
import java.util.Date;
import javax.annotation.Nonnull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author pdgreen
 */
public class StateImpl implements State {

  @Nonnull
  private final Date timeCreated = new Date();
  @Nonnull
  private final Scenario scenario;

  public StateImpl(@Nonnull final Scenario scenario) {
    this.scenario = checkNotNull(scenario);
  }

  @Override
  public Date getTimeCreated() {
    return timeCreated;
  }

  @Override
  public Scenario getScenario() {
    return scenario;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final StateImpl other = (StateImpl) obj;
    if (this.timeCreated != other.timeCreated && (this.timeCreated == null || !this.timeCreated.equals(other.timeCreated))) {
      return false;
    }
    if (this.scenario != other.scenario && (this.scenario == null || !this.scenario.equals(other.scenario))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 59 * hash + (this.timeCreated != null ? this.timeCreated.hashCode() : 0);
    hash = 59 * hash + (this.scenario != null ? this.scenario.hashCode() : 0);
    return hash;
  }
  //TODO overRide toString
}
