package edu.wvu.lcsee.green.mymodel;

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
import java.util.Date;
import java.util.Map;
import static edu.wvu.lcsee.green.mymodel.model.MyModelConfigurationDefinition.*;

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
    final ImmutableSet<ScoredProject> scoredProjects = projectGenerator.generateManyScoredProjects(scenario, 20,
            "duration", "cost");
    final Date after = new Date();
    System.out.println("time (MS)" + (after.getTime() - before.getTime()));
    System.out.println(scoredProjects);

    final Project project = projectGenerator.generateScoredProject(scenario);

    final Map<String, Number> scores = Maps.newHashMap();
    for (final ScoringFunction scoringFunction : novaControl.getAllScoringFunctions()) {
      scores.put(scoringFunction.getKey(), scoringFunction.score(project));
    }
    System.out.println(ImmutableMap.copyOf(scores));

    final ScoringFunction scoringFunction = novaControl.getScoringFunctionForKey("cost");
    final SearchEngine searchEngine = novaControl.getSearchEngineForKey("strawman");

    System.out.println(searchEngine.search(scoringFunction, scenario));
  }
}
