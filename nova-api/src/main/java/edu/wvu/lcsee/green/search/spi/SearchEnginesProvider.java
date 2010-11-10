
package edu.wvu.lcsee.green.search.spi;

import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.search.SearchEngine;

/**
 *
 * @author pdgreen
 */
public interface SearchEnginesProvider {

  ImmutableSet<SearchEngine> getSearchEngines();

}
