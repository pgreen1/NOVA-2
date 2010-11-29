package edu.wvu.lcsee.green.search.spi;

import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.search.SearchEngine;
import javax.annotation.Nonnull;

/**
 * Provides SearchEngines via SPI to be available for NovaControl.
 * Implementors of this should register with SPI.
 *
 * @author pdgreen
 */
public interface SearchEnginesProvider {

  /**
   * Returns a set of SearchEngines to be available through NovaControl.
   * @return a set of SearchEngines
   */
  @Nonnull
  ImmutableSet<SearchEngine> getSearchEngines();
}
