package edu.wvu.lcsee.green.search.impl;

import edu.wvu.lcsee.green.search.State;
import com.google.common.base.Objects;
import java.util.List;
import javax.annotation.Nonnull;
import com.google.common.collect.ImmutableList;
import java.util.Date;
import edu.wvu.lcsee.green.search.Path;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Standard implementation {@link Path}.
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
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final PathImpl that = (PathImpl) obj;

    return Objects.equal(this.timeStarted, that.timeStarted) && Objects.equal(this.timeEnded, that.timeEnded) && Objects.
            equal(this.states, that.states);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(timeStarted, timeEnded, states);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("started", timeStarted).add("ended", timeEnded).add("stateCount", states.
            size()).toString();
  }
}
