package edu.wvu.lcsee.green.search;

import edu.wvu.lcsee.green.model.Treatment;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import edu.wvu.lcsee.green.apps.ScoringFunction.ScoringFunctionId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public class State {

  @Nonnull
  private final Date timeCreated;
  @Nonnull
  private final ImmutableSet<Feature> ignore;
  @Nonnull
  private final ImmutableSet<Feature> open;
  @Nonnull
  private final ImmutableSet<Feature> closed;
  @Nonnull
  private final ImmutableMap<ScoringFunctionId, Number> scores;

  public State(Date timeCreated, ImmutableSet<Feature> ignore, ImmutableSet<Feature> open, ImmutableSet<Feature> closed, ImmutableMap<ScoringFunctionId, Number> scores) {
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
    final State other = (State) obj;
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

  public State applyTreatment(@Nonnull final Treatment treatment) {
    throw new UnsupportedOperationException("not implemented");
//TODO implement me
//    final Set<Feature> all = Sets.union(open, closed);
//    final ImmutableSet<Feature> newOpen = treatment.getSplitFeatures();
//    final ImmutableSet<Feature> newClosed = ImmutableSet.copyOf(Sets.difference(all, newOpen));
//
//    return new State(new Date(), ignore, newOpen, newClosed, scores);
  }

  public State addScore(@Nonnull final ScoringFunctionId scoringMethodId, @Nonnull final Number score) {
    final Map<ScoringFunctionId, Number> mutableScores = new HashMap(scores);
    mutableScores.put(scoringMethodId, score);
    return new State(new Date(), ignore, open, closed, ImmutableMap.copyOf(mutableScores));
  }

  public State addScores(@Nonnull final Map<ScoringFunctionId, Number> newScores) {
    final Map<ScoringFunctionId, Number> mutableScores = new HashMap(scores);
    mutableScores.putAll(newScores);
    return new State(new Date(), ignore, open, closed, ImmutableMap.copyOf(mutableScores));
  }

  /**
   * @return the timeCreated
   */
  public Date getTimeCreated() {
    return timeCreated;
  }

  /**
   * @return the ignore
   */
  public Set<Feature> getIgnore() {
    return ignore;
  }

  /**
   * @return the open
   */
  public Set<Feature> getOpen() {
    return open;
  }

  /**
   * @return the closed
   */
  public Set<Feature> getClosed() {
    return closed;
  }

  public ImmutableMap<ScoringFunctionId, Number> getScores() {
    return scores;
  }
}
