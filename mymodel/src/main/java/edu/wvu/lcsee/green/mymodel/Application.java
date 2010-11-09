package edu.wvu.lcsee.green.mymodel;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import edu.wvu.lcsee.green.control.NovaBootstrap;
import edu.wvu.lcsee.green.control.NovaControl;
import edu.wvu.lcsee.green.model.Project;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.mymodel.model.MyModelConfigurationDefinition;
import edu.wvu.lcsee.green.spi.NovaControlFactory;
import edu.wvu.lcsee.green.model.ProjectGenerator;
import edu.wvu.lcsee.green.spi.ScoringFunction;
import java.util.Map;

/**
 *
 * @author pdgreen
 */
public class Application {

  public static void main(final String[] args) {
    final NovaControl novaControl = NovaBootstrap.loadFactory().newInstance();

    final ProjectGenerator projectGenerator = novaControl.getProjectGenerator();

    final Scenario scenario = MyModelConfigurationDefinition.MY_MODEL_CONFIGURATION.generateScenario(
            MyModelConfigurationDefinition.ALL, MyModelConfigurationDefinition.DEFAULT);

    System.out.println(projectGenerator.generateManyScoredProjects(scenario, 20, "duration"));

    final Project project = projectGenerator.generateScoredProject(scenario);

    final Map<String, Number> scores = Maps.newHashMap();
    for (final Map.Entry<String, ScoringFunction> entry : novaControl.getAllScoringFunctions().entrySet()) {
      scores.put(entry.getKey(), entry.getValue().score(project));
    }
    System.out.println(ImmutableMap.copyOf(scores));


  }
}
