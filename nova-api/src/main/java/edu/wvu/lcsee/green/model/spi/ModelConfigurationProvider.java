package edu.wvu.lcsee.green.model.spi;

import edu.wvu.lcsee.green.model.ModelConfiguration;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface ModelConfigurationProvider {

  @Nonnull
  ModelConfiguration getModelConfiguration();
}
