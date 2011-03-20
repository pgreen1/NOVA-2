@Grapes([
  @GrabResolver(name='local_m2', root='file:${user.home}/.m2/repository/', m2Compatible=true),
  @Grab(group='edu.wvu.lcsee.green', module='nova-api', version='[2.0.0-SNAPSHOT,)'),
  @Grab(group='edu.wvu.lcsee.green', module='nova-clojure-impl', version='[2.0.0-SNAPSHOT,)'),
  @Grab(group='edu.wvu.lcsee.green', module='nova-search-engines', version='[2.0.0-SNAPSHOT,)'),
  @Grab(group='edu.wvu.lcsee.green.xomo', module='xomo-model', version='[2.0.0-SNAPSHOT,)'),
  @Grab(group='edu.wvu.lcsee.green.xomo', module='xomo-engine', version='[2.0.0-SNAPSHOT,)')
])
package xomo

import edu.wvu.lcsee.green.search.Path;
import edu.wvu.lcsee.green.search.State;
import edu.wvu.lcsee.green.search.SearchEngine;
import edu.wvu.lcsee.green.search.EvaluationFunction;
import edu.wvu.lcsee.green.control.NovaBootstrap;
import edu.wvu.lcsee.green.control.NovaControl;
import edu.wvu.lcsee.green.model.Project;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.ProjectGenerator;
import edu.wvu.lcsee.green.xomo.model.XomoModelConfiguration;


import edu.wvu.lcsee.green.search.impl.MeanProjectScoreEvaluationFunction;

final NovaControl novaControl = NovaBootstrap.loadFactory().newInstance();

final ProjectGenerator projectGenerator = novaControl.getProjectGenerator();

final XomoModelConfiguration xomoModelConfiguration = novaControl.getModelConfigration(XomoModelConfiguration.class);

final Scenario scenario = xomoModelConfiguration.generateDefaultScenario();

final EvaluationFunction evaluationFunction = new MeanProjectScoreEvaluationFunction(projectGenerator, 100, xomoModelConfiguration.getEffortScoringFunction());


def runSearchEngine(searchEngine, evaluationFunction, scenario) {
  final Path path = searchEngine.search(evaluationFunction, scenario);
  System.out.println(searchEngine.getKey())
  System.out.println(path);
  final List<State> states = path.getStates();
  System.out.println(states);
  System.out.println(states.get(states.size()-1).getScore());
}


runSearchEngine(novaControl.getSearchEngineForKey("strawman"),evaluationFunction, scenario);
runSearchEngine(novaControl.getSearchEngineForKey("isamp"),evaluationFunction,scenario);
runSearchEngine(novaControl.getSearchEngineForKey("sa"),evaluationFunction,scenario);
runSearchEngine(novaControl.getSearchEngineForKey("seesaw-r"),evaluationFunction,scenario);
runSearchEngine(novaControl.getSearchEngineForKey("seesaw-d"),evaluationFunction,scenario);
