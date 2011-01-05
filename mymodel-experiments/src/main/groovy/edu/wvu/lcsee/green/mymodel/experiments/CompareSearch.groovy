package edu.wvu.lcsee.green.mymodel.experiments

import edu.wvu.lcsee.green.search.Path;
import edu.wvu.lcsee.green.search.SearchEngine;
import edu.wvu.lcsee.green.search.EvaluationFunction;
import edu.wvu.lcsee.green.control.NovaBootstrap;
import edu.wvu.lcsee.green.control.NovaControl;
import edu.wvu.lcsee.green.model.Project;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.ProjectGenerator;
import edu.wvu.lcsee.green.mymodel.model.MyModelConfiguration;


import edu.wvu.lcsee.green.search.impl.MeanProjectScoreEvaluationFunction;

final NovaControl novaControl = NovaBootstrap.loadFactory().newInstance();

final ProjectGenerator projectGenerator = novaControl.getProjectGenerator();

final MyModelConfiguration myModelConfiguration = novaControl.getModelConfigration(MyModelConfiguration.class);

final Scenario scenario = myModelConfiguration.generateDefaultScenario();

final EvaluationFunction evaluationFunction = new MeanProjectScoreEvaluationFunction(projectGenerator, 100, myModelConfiguration.getCostScoringFunction());


def runSearchEngine(searchEngine, evaluationFunction, scenario) {
  final Path path = searchEngine.search(evaluationFunction, scenario);
  System.out.println(searchEngine.getKey())
  System.out.println(path);
  System.out.println(path.getStates());
}


runSearchEngine(novaControl.getSearchEngineForKey("strawman"),evaluationFunction, scenario);
runSearchEngine(novaControl.getSearchEngineForKey("isamp"),evaluationFunction,scenario);
runSearchEngine(novaControl.getSearchEngineForKey("sa"),evaluationFunction,scenario);