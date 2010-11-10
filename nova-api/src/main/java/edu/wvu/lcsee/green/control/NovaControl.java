package edu.wvu.lcsee.green.control;

import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnull;
import com.google.common.collect.ImmutableMap;
import edu.wvu.lcsee.green.model.ProjectGenerator;
import edu.wvu.lcsee.green.model.spi.ScoringFunction;
import edu.wvu.lcsee.green.search.SearchEngine;

/**
 *
 * @author pdgreen
 */
public interface NovaControl {

  ProjectGenerator getProjectGenerator();

  ImmutableMap<String, ScoringFunction> getAllScoringFunctions();

  ImmutableSet<ScoringFunction> getScoringFunctionsForTheseKeys(@Nonnull final String... scoringFunctionKeys);

  ImmutableMap<String, SearchEngine> getAllSearchEngines();

  ImmutableSet<SearchEngine> getSearchEnginesForTheseKeys(@Nonnull final String... searchEngineKeys);
}
