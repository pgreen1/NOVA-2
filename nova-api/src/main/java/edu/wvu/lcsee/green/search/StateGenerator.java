package edu.wvu.lcsee.green.search;

import edu.wvu.lcsee.green.model.Treatment;
import edu.wvu.lcsee.green.model.ModelConfiguration;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface StateGenerator {

  State generate(@Nonnull final ModelConfiguration modelConfiguration);

  State generate(@Nonnull final ModelConfiguration modelConfiguration, @Nonnull final Treatment treatment);
}
