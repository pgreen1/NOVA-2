package edu.wvu.lcsee.green.search.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Treatment;
import edu.wvu.lcsee.green.search.Feature;
import edu.wvu.lcsee.green.search.State;
import java.util.Date;
import java.util.Set;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public class StateImpl implements State {

  @Nonnull
  private final Date timeCreated;
  @Nonnull
  private final ImmutableSet<Feature> ignore;
  @Nonnull
  private final ImmutableSet<Feature> open;
  @Nonnull
  private final ImmutableSet<Feature> closed;
  @Nonnull
  private final ImmutableMap<String, Number> scores;

  public StateImpl(Date timeCreated, ImmutableSet<Feature> ignore, ImmutableSet<Feature> open,
          ImmutableSet<Feature> closed, ImmutableMap<String, Number> scores) {
    this.timeCreated = timeCreated;
    this.ignore = ignore;
    this.open = open;
    this.closed = closed;
    this.scores = scores;
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
    if (this.ignore != other.ignore && (this.ignore == null || !this.ignore.equals(other.ignore))) {
      return false;
    }
    if (this.open != other.open && (this.open == null || !this.open.equals(other.open))) {
      return false;
    }
    if (this.closed != other.closed && (this.closed == null || !this.closed.equals(other.closed))) {
      return false;
    }
    return true;
  }

  //TODO overRide toString
  @Override
  public int hashCode() {
    int hash = 3;
    hash = 47 * hash + (this.timeCreated != null ? this.timeCreated.hashCode() : 0);
    hash = 47 * hash + (this.ignore != null ? this.ignore.hashCode() : 0);
    hash = 47 * hash + (this.open != null ? this.open.hashCode() : 0);
    hash = 47 * hash + (this.closed != null ? this.closed.hashCode() : 0);
    return hash;
  }

  /**
   * @return the timeCreated
   */
  @Override
  public Date getTimeCreated() {
    return timeCreated;
  }

  /**
   * @return the ignore
   */
  @Override
  public Set<Feature> getRequiredFeatures() {
    return ignore;
  }

  /**
   * @return the open
   */
  @Override
  public Set<Feature> getActiveFeatures() {
    return open;
  }

  /**
   * @return the closed
   */
  @Override
  public Set<Feature> getInactiveFeatures() {
    return closed;
  }

  @Override
  public ImmutableMap<String, Number> getScores() {
    return scores;
  }

  @Override
  public Treatment determineTreatmentFrom(State state) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
