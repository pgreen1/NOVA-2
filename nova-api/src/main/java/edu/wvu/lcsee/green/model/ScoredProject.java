package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableMap;
import edu.wvu.lcsee.green.model.Project;

/**
 *
 * @author pdgreen
 */
public interface ScoredProject extends Project {

  ImmutableMap<String, Number> getScores();
}
