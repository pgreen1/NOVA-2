package edu.wvu.lcsee.green.model.spi;

import edu.wvu.lcsee.green.model.Project;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface ScoringFunction {

  String getKey();

  Number score(@Nonnull Project project);
 
}
