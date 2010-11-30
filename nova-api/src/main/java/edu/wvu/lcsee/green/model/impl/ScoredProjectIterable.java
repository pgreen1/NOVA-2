package edu.wvu.lcsee.green.model.impl;

import java.util.NoSuchElementException;
import edu.wvu.lcsee.green.model.spi.ScoringFunction;
import edu.wvu.lcsee.green.model.ScoredProject;
import javax.annotation.Nonnull;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.ProjectGenerator;
import java.util.Iterator;
import javax.annotation.Nonnegative;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * An {@link Iterable} to be used by {@link ProjectGenerator} implementations.
 * It generates an {@link Iterator} that generates a new @{link ScoredProject}
 * using the contained {@link ProjectGenerator} and {@link Scenerio}.
 * @author pdgreen
 * @see ProjectGenerator
 * @see ScoredProject
 * @see Scenario
 */
public class ScoredProjectIterable implements Iterable<ScoredProject> {

  private final ProjectGenerator projectGenerator;
  private final ScoringFunction[] scoringFunctions;
  private final Scenario scenario;
  private final long iterations;

  public ScoredProjectIterable(@Nonnull final ProjectGenerator projectGenerator,
          @Nonnull final ScoringFunction[] scoringFunctions,
          @Nonnull final Scenario scenario,
          @Nonnegative final long iterations) {
    this.projectGenerator = checkNotNull(projectGenerator);
    this.scoringFunctions = checkNotNull(scoringFunctions);
    this.scenario = checkNotNull(scenario);
    checkArgument(iterations >= 0, "iterations must be greater than 0");
    this.iterations = iterations;
  }

  @Override
  public Iterator<ScoredProject> iterator() {
    return new ScoredProjectIterator();
  }

  class ScoredProjectIterator implements Iterator<ScoredProject> {

    private long currentIteration = 0;

    @Override
    public boolean hasNext() {
      return currentIteration < iterations;
    }

    @Override
    public ScoredProject next() {
      if(!hasNext()) {
        throw new NoSuchElementException();
      }
      final ScoredProject scoredProject = projectGenerator.generateScoredProject(scenario, scoringFunctions);
      currentIteration++;
      return scoredProject;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Iterator is unmodifiable");
    }
  }
}
