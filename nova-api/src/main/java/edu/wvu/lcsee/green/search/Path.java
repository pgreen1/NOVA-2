package edu.wvu.lcsee.green.search;

import com.google.common.collect.ImmutableList;
import java.util.Date;

/**
 * Represents a path of {@link State} instances that a 
 * {@link SearchEngine} transversed to find the best {@link Scenario}.
 *
 * @author pdgreen
 */
public interface Path {

  /**
   * The instance of time where the {@SearchEngine} started searching for this Path.
   * @return time instance the path search was started
   */
  Date getTimeStarted();


  /**
   * The instance of time where the {@SearchEngine} ended searching for this Path.
   * @return time instance the path search was ended
   */
  Date getTimeEnded();

  /**
   * The {@State}s the {@link SearchEngine} transversed to find the best {@link Scenario}.
   * @return the states in the path
   */
  ImmutableList<State> getStates();
}
