package edu.wvu.lcsee.green.model.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.ScoredProject;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Project;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nonnull;
import static com.google.common.base.Preconditions.*;

/**
 *
 * @author pdgreen
 */
public class ScoredProjectImpl implements ScoredProject {

  private final Project project;
  private final ImmutableMap<String, Number> scores;

  public ScoredProjectImpl(@Nonnull final Project project,
          @Nonnull final Map<String, Number> scores) {
    this.project = checkNotNull(project);
    this.scores = ImmutableMap.copyOf(scores);
  }

  @Override
  public ImmutableMap<String, Number> getScores() {
    return scores;
  }

  @Override
  public ImmutableSet<Attribute<? extends Serializable>> getAttributes() {
    return project.getAttributes();
  }

  @Override
  public <V extends Serializable> V getValueFor(Attribute<V> attribute) {
    return project.getValueFor(attribute);
  }

  @Override
  public ImmutableMap<Attribute<? extends Serializable>, ? extends Serializable> getValuesAsMap() {
    return project.getValuesAsMap();
  }
}
