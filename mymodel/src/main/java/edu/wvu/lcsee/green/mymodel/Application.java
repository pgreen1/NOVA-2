package edu.wvu.lcsee.green.mymodel;

import edu.wvu.lcsee.green.search.Path;
import edu.wvu.lcsee.green.search.SearchEngine;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import edu.wvu.lcsee.green.control.NovaBootstrap;
import edu.wvu.lcsee.green.control.NovaControl;
import edu.wvu.lcsee.green.model.Project;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.ProjectGenerator;
import edu.wvu.lcsee.green.model.ScoredProject;
import edu.wvu.lcsee.green.model.spi.ScoringFunction;
import edu.wvu.lcsee.green.search.EvaluationFunction;
import java.util.Date;
import java.util.Map;
import static edu.wvu.lcsee.green.mymodel.model.MyModelConfigurationDefinition.*;

import edu.wvu.lcsee.green.mymodel.CostScoringFunction;
import edu.wvu.lcsee.green.mymodel.DurationScoringFunction;
import edu.wvu.lcsee.green.search.impl.MeanProjectScoreEvaluationFunction;

/**
 *
 * @author pdgreen
 */
public class Application {

  public static void main(final String[] args) {
    final NovaControl novaControl = NovaBootstrap.loadFactory().newInstance();

    final ProjectGenerator projectGenerator = novaControl.getProjectGenerator();

    final Scenario scenario = MY_MODEL_CONFIGURATION.generateScenario(POLICY_ALL, CASE_STUDY_DEFAULT);

    final Date before = new Date();
    final ImmutableSet<ScoredProject> scoredProjects = projectGenerator.generateManyScoredProjects(scenario, 20, new DurationScoringFunction(),
            new CostScoringFunction());
    final Date after = new Date();
    System.out.println("time (MS)" + (after.getTime() - before.getTime()));
    System.out.println(scoredProjects);

    final Project project = projectGenerator.generateScoredProject(scenario);

    final Map<String, Number> scores = Maps.newHashMap();
    for (final ScoringFunction scoringFunction : novaControl.getAllScoringFunctions()) {
      scores.put(scoringFunction.getKey(), scoringFunction.score(project));
    }
    System.out.println(ImmutableMap.copyOf(scores));

    final SearchEngine searchEngine = novaControl.getSearchEngineForKey("strawman");

    final Path path = searchEngine.search(new MeanProjectScoreEvaluationFunction(projectGenerator,
            100,  new CostScoringFunction()), scenario);
    System.out.println(path);
    System.out.println(path.getStates());
  }
}
