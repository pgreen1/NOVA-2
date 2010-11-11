package edu.wvu.lcsee.green.search.spi;

import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.search.SearchEngine;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface SearchEnginesProvider {

  @Nonnull
  ImmutableSet<SearchEngine> getSearchEngines();
}
