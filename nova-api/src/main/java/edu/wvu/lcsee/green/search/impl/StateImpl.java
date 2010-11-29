package edu.wvu.lcsee.green.search.impl;

import com.google.common.base.Objects;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.search.State;
import java.util.Date;
import javax.annotation.Nonnull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Standard implementation {@link State}.
 * @author pdgreen
 */
public class StateImpl implements State {

  @Nonnull
  private final Date timeCreated = new Date();
  @Nonnull
  private final Scenario scenario;
  @Nonnull
  private final Number score;

  public StateImpl(@Nonnull final Scenario scenario, @Nonnull final Number score) {
    this.scenario = checkNotNull(scenario);
    this.score = checkNotNull(score);
  }

  @Nonnull
  @Override
  public Date getTimeCreated() {
    return timeCreated;
  }

  @Nonnull
  @Override
  public Scenario getScenario() {
    return scenario;
  }

  @Nonnull
  @Override
  public Number getScore() {
    return score;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final StateImpl that = (StateImpl) obj;

    return Objects.equal(this.timeCreated, that.timeCreated) && Objects.equal(this.scenario, that.scenario);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(timeCreated, scenario);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("timeCreated", timeCreated).add("score", score).add("scenario", scenario).
            toString();
  }
}
