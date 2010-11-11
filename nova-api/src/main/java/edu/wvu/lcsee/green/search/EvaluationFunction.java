
package edu.wvu.lcsee.green.search;

import edu.wvu.lcsee.green.model.Scenario;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface EvaluationFunction {

  @Nonnull
  Number evaluate(@Nonnull Scenario scenario);
}
