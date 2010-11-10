package edu.wvu.lcsee.green.search;

import com.google.common.collect.ImmutableList;
import java.util.Date;

/**
 *
 * @author pdgreen
 */
public interface Path {

  ImmutableList<State> getStates();

  Date getTimeEnded();

  Date getTimeStarted();
}
