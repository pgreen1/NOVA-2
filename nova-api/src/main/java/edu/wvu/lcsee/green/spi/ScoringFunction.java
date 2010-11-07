package edu.wvu.lcsee.green.spi;

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
