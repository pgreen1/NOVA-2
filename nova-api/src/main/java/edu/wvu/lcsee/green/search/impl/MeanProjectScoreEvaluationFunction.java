package edu.wvu.lcsee.green.search.impl;

import edu.wvu.lcsee.green.model.ProjectGenerator;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.ScoredProject;
import edu.wvu.lcsee.green.model.ScoringFunction;
import edu.wvu.lcsee.green.search.EvaluationFunction;
import java.math.BigDecimal;
import javax.annotation.Nonnull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author pdgreen
 */
public class MeanProjectScoreEvaluationFunction implements EvaluationFunction {

  @Nonnull
  private final ProjectGenerator projectGenerator;
  @Nonnull
  private int numberOfProjectsToGenerate;
  @Nonnull
  private final ScoringFunction scoringFunction;

  public MeanProjectScoreEvaluationFunction(@Nonnull final ProjectGenerator projectGenerator,
          @Nonnull final int numberOfProjectsToGenerate,
          @Nonnull final ScoringFunction scoringFunction) {
    this.projectGenerator = checkNotNull(projectGenerator);
    this.numberOfProjectsToGenerate = numberOfProjectsToGenerate;
    this.scoringFunction = checkNotNull(scoringFunction);
  }

  @Override
  public Number evaluate(@Nonnull final Scenario scenario) {
    double total = 0;
    for (final ScoredProject scoredProject : projectGenerator.generateManyScoredProjects(scenario,
            numberOfProjectsToGenerate,
            scoringFunction)) {
      total += scoredProject.getScores().get(scoringFunction.getKey()).doubleValue();
    }
    return new BigDecimal(total / numberOfProjectsToGenerate);
  }
}
