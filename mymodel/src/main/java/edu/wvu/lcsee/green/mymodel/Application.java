package edu.wvu.lcsee.green.mymodel;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import edu.wvu.lcsee.green.model.Project;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.mymodel.model.MyModelConfigurationDefinition;
import edu.wvu.lcsee.green.spi.ProjectGenerator;
import edu.wvu.lcsee.green.spi.ScoringFunction;
import edu.wvu.lcsee.green.ui.NovaControl;
import java.util.Map;

/**
 *
 * @author pdgreen
 */
public class Application {

  public static void main(final String[] args) {
    final ProjectGenerator projectGenerator = NovaControl.INSTANCE.getProjectGenerator();

    final Scenario scenario = MyModelConfigurationDefinition.MY_MODEL_CONFIGURATION.generateScenario(
            MyModelConfigurationDefinition.ALL, MyModelConfigurationDefinition.DEFAULT);

    System.out.println(projectGenerator.generateMany(scenario, 20));

    final Project project = projectGenerator.generate(scenario);

    final Map<String, Number> scores = Maps.newHashMap();
    for (final Map.Entry<String, ScoringFunction> entry : NovaControl.INSTANCE.getScoringFunctions().entrySet()) {
      scores.put(entry.getKey(), entry.getValue().score(project));
    }
    System.out.println(ImmutableMap.copyOf(scores));


  }
}
