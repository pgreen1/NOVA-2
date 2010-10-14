package edu.wvu.lcsee.green.apps;

import com.google.common.collect.ImmutableList;
import edu.wvu.lcsee.green.search.State;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface SearchEngine {

  @Nonnull
  ImmutableList<State> search(@Nonnull final State initialState);
}
