
package edu.wvu.lcsee.green.mymodel;

import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.mymodel.model.MyModelConfigurationDefinition;
import edu.wvu.lcsee.green.spi.ProjectGenerator;
import edu.wvu.lcsee.green.ui.NovaControl;

/**
 *
 * @author pdgreen
 */
public class Application {

  public static void main(final String[] args) {
    final ProjectGenerator projectGenerator = NovaControl.INSTANCE.getProjectGenerator();

    final Scenario scenario = MyModelConfigurationDefinition.MY_MODEL_CONFIGURATION.generateScenario(MyModelConfigurationDefinition.ALL, MyModelConfigurationDefinition.DEFAULT);

    System.out.println(projectGenerator.generateMany(scenario, 20));
  }

}
