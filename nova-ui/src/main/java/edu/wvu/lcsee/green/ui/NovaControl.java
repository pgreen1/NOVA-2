package edu.wvu.lcsee.green.ui;

import com.google.common.collect.Maps;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import edu.wvu.lcsee.green.spi.ProjectGenerator;
import edu.wvu.lcsee.green.spi.ScoringFunction;
import static edu.wvu.lcsee.green.ui.ServiceLoaderHelper.*;

/**
 *
 * @author pdgreen
 */
public class NovaControl {

  public static NovaControl INSTANCE = new NovaControl();
  private final ProjectGenerator projectGenerator;
  private final ImmutableMap<String, ScoringFunction> scoringFunctions;

  private NovaControl() {
    projectGenerator = loadSingleService(ProjectGenerator.class);
    final Map<String, ScoringFunction> scoringFunctions = Maps.newHashMap();
    for (final ScoringFunction scoringFunction : loadMultiService(ScoringFunction.class)) {
      scoringFunctions.put(scoringFunction.getKey(), scoringFunction);
    }
    this.scoringFunctions = ImmutableMap.copyOf(scoringFunctions);
  }

  public ProjectGenerator getProjectGenerator() {
    return projectGenerator;
  }

  public ImmutableMap<String, ScoringFunction> getScoringFunctions() {
    return scoringFunctions;
  }
}
