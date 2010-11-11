package edu.wvu.lcsee.green.search.impl;

import com.google.common.base.Objects;
import java.util.List;
import javax.annotation.Nonnull;
import edu.wvu.lcsee.green.search.*;
import com.google.common.collect.ImmutableList;
import java.util.Date;
import edu.wvu.lcsee.green.search.Path;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author pdgreen
 */
public class PathImpl implements Path {

  private final Date timeStarted;
  private final Date timeEnded;
  private final ImmutableList<State> states;

  public PathImpl(@Nonnull final Date timeStarted, @Nonnull final Date timeEnded, @Nonnull final List<State> states) {
    this.timeStarted = checkNotNull(timeStarted);
    this.timeEnded = checkNotNull(timeEnded);
    this.states = ImmutableList.copyOf(states);
  }

  @Override
  public ImmutableList<State> getStates() {
    return states;
  }

  @Override
  public Date getTimeEnded() {
    return timeEnded;
  }

  @Override
  public Date getTimeStarted() {
    return timeStarted;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final PathImpl other = (PathImpl) obj;
    if (this.timeStarted != other.timeStarted && (this.timeStarted == null || !this.timeStarted.equals(other.timeStarted))) {
      return false;
    }
    if (this.timeEnded != other.timeEnded && (this.timeEnded == null || !this.timeEnded.equals(other.timeEnded))) {
      return false;
    }
    if (this.states != other.states && (this.states == null || !this.states.equals(other.states))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 61 * hash + (this.timeStarted != null ? this.timeStarted.hashCode() : 0);
    hash = 61 * hash + (this.timeEnded != null ? this.timeEnded.hashCode() : 0);
    hash = 61 * hash + (this.states != null ? this.states.hashCode() : 0);
    return hash;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("started", timeStarted).add("ended", timeEnded).add("stateCount", states.
            size()).toString();
  }
  //TODO overRide toString
}
