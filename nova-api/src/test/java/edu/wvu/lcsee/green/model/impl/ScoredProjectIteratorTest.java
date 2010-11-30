package edu.wvu.lcsee.green.model.impl;

import edu.wvu.lcsee.green.model.impl.ScoredProjectIterable.ScoredProjectIterator;


import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.ProjectGenerator;
import edu.wvu.lcsee.green.model.spi.ScoringFunction;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Tests for ScoredProjectIterator.
 * @author pdgreen
 */
public class ScoredProjectIteratorTest {

  @Test
  public void aScoredProjectIteratorShouldProduceTheCorrectNumberOfProjects() {
    final ProjectGenerator mockProjectGenerator = mock(ProjectGenerator.class);
    final Scenario mockScenario = mock(Scenario.class);
    final ScoringFunction mockScoringFunction1 = mock(ScoringFunction.class);
    final ScoringFunction[] scoringFunctions = {mockScoringFunction1};
    final long testIterations = 10;

    final ScoredProjectIterator instance = (ScoredProjectIterator) new ScoredProjectIterable(mockProjectGenerator,
            scoringFunctions,
            mockScenario,
            testIterations).iterator();

    assertThat(instance.hasNext(), is(true));
    for (int i = 0; i < testIterations; i++) {
      instance.next();
    }
    assertThat(instance.hasNext(), is(false));

    verify(mockProjectGenerator, times((int) testIterations)).generateScoredProject(mockScenario,scoringFunctions);
    verifyZeroInteractions(mockScenario);
  }

  @Test(expected = NoSuchElementException.class)
  public void aScoredProjectIteratorShouldThrowNoSuchElementExceptionOnNextWhenHasNextIsFalse() {
    final ProjectGenerator mockProjectGenerator = mock(ProjectGenerator.class);
    final Scenario mockScenario = mock(Scenario.class);
    final ScoringFunction mockScoringFunction1 = mock(ScoringFunction.class);
    final ScoringFunction[] scoringFunctions = {mockScoringFunction1};
    final long testIterations = 1;

    final ScoredProjectIterator instance = (ScoredProjectIterator) new ScoredProjectIterable(mockProjectGenerator,
            scoringFunctions,
            mockScenario,
            testIterations).iterator();

    assertThat(instance.hasNext(), is(true));
    instance.next();

    assertThat(instance.hasNext(), is(false));
    instance.next();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void aScoredProjectIteratorShouldThrowUnsupportedOperationExceptionOnRemove() {
    final ProjectGenerator mockProjectGenerator = mock(ProjectGenerator.class);
    final Scenario mockScenario = mock(Scenario.class);
    final ScoringFunction mockScoringFunction1 = mock(ScoringFunction.class);
    final ScoringFunction[] scoringFunctions = {mockScoringFunction1};
    final long testIterations = 1;

    final ScoredProjectIterator instance = (ScoredProjectIterator) new ScoredProjectIterable(mockProjectGenerator,
            scoringFunctions,
            mockScenario,
            testIterations).iterator();

    instance.remove();
  }
}
