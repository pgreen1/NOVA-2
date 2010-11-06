package edu.wvu.lcsee.green.ui;

import edu.wvu.lcsee.green.spi.ProjectGenerator;

/**
 *
 * @author pdgreen
 */
public class NovaControl {

  public static NovaControl INSTANCE = new NovaControl();
  private final ProjectGenerator projectGenerator;

  private NovaControl() {
    projectGenerator = ServiceLoaderHelper.loadSingleService(ProjectGenerator.class);
  }

  public ProjectGenerator getProjectGenerator() {
    return projectGenerator;
  }
}
