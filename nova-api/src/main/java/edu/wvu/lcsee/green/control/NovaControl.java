package edu.wvu.lcsee.green.control;

import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnull;
import edu.wvu.lcsee.green.model.ProjectGenerator;
import edu.wvu.lcsee.green.model.spi.ScoringFunction;
import edu.wvu.lcsee.green.search.SearchEngine;

/**
 *
 * @author pdgreen
 */
public interface NovaControl {

  ProjectGenerator getProjectGenerator();

  ImmutableSet<ScoringFunction> getAllScoringFunctions();

  ScoringFunction getScoringFunctionForKey(@Nonnull String key);

  ImmutableSet<SearchEngine> getAllSearchEngines();

  SearchEngine getSearchEngineForKey(@Nonnull String key);
}
