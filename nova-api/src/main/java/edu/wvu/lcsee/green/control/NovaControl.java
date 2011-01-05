package edu.wvu.lcsee.green.control;

import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.ModelConfiguration;
import javax.annotation.Nonnull;
import edu.wvu.lcsee.green.model.ProjectGenerator;
import edu.wvu.lcsee.green.search.SearchEngine;

/**
 * NovaControl is the central object for using NOVA.  All tasks is handled through this class.
 *
 * @author pdgreen
 */
public interface NovaControl {

  @Nonnull
  ProjectGenerator getProjectGenerator();

  @Nonnull
  <M extends ModelConfiguration> M getModelConfigration(@Nonnull Class<M> modelConfigurationType);

  @Nonnull
  ImmutableSet<SearchEngine> getAllSearchEngines();

  @Nonnull
  SearchEngine getSearchEngineForKey(@Nonnull String key);
}
