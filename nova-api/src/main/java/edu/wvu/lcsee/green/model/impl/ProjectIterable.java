package edu.wvu.lcsee.green.model.impl;

import javax.annotation.Nonnull;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.Project;
import edu.wvu.lcsee.green.model.ProjectGenerator;
import java.util.Iterator;
import javax.annotation.Nonnegative;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * An {@link Iterable} to be used by {@link ProjectGenerator} implementations.
 * It generates an {@link Iterator} that generates a new @{link Project}
 * using the contained {@link ProjectGenerator} and {@link Scenerio}.
 * @author pdgreen
 * @see ProjectGenerator
 * @see Project
 * @see Scenario
 */
public class ProjectIterable implements Iterable<Project> {

  private final ProjectGenerator projectGenerator;
  private final Scenario scenario;
  private final long iterations;

  public ProjectIterable(@Nonnull final ProjectGenerator projectGenerator, @Nonnull final Scenario scenario,
          @Nonnegative final long iterations) {
    this.projectGenerator = checkNotNull(projectGenerator);
    this.scenario = checkNotNull(scenario);
    checkArgument(iterations >= 0, "iterations must be greater than 0");
    this.iterations = iterations;
  }

  @Override
  public Iterator<Project> iterator() {
    return new ProjectIterator();
  }

  class ProjectIterator implements Iterator<Project> {

    private long currentIteration = 0;

    @Override
    public boolean hasNext() {
      return currentIteration < iterations;
    }

    @Override
    public Project next() {
      final Project project = projectGenerator.generateProject(scenario);
      currentIteration++;
      return project;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Iterator is unmodifiable");
    }
  }
}
