package edu.wvu.lcsee.green.search;

import com.google.common.collect.ImmutableList;
import java.util.Date;

/**
 *
 * @author pdgreen
 */
public class Path {

  private final Date timeStarted;
  private final Date timeEnded;
  private final ImmutableList<State> states;

  public Path(Date timeStarted, Date timeEnded, ImmutableList<State> states) {
    this.timeStarted = timeStarted;
    this.timeEnded = timeEnded;
    this.states = states;
  }

  public ImmutableList<State> getStates() {
    return states;
  }

  public Date getTimeEnded() {
    return timeEnded;
  }

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
    final Path other = (Path) obj;
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
  //TODO overRide toString
}
