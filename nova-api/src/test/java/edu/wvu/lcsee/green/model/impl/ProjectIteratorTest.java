package edu.wvu.lcsee.green.model.impl;

import edu.wvu.lcsee.green.model.impl.ProjectIterable.ProjectIterator;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.ProjectGenerator;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Tests for ProjectIterator.
 * @author pdgreen
 */
public class ProjectIteratorTest {

  @Test
  public void aProjectIteratorShouldProduceTheCorrectNumberOfProjects() {
    final ProjectGenerator mockProjectGenerator = mock(ProjectGenerator.class);
    final Scenario mockScenario = mock(Scenario.class);
    final long testIterations = 10;

    final ProjectIterator instance = (ProjectIterator) new ProjectIterable(mockProjectGenerator, mockScenario,
            testIterations).iterator();

    assertThat(instance.hasNext(), is(true));
    for (int i = 0; i < testIterations; i++) {
      instance.next();
    }
    assertThat(instance.hasNext(), is(false));

    verify(mockProjectGenerator, times((int) testIterations)).generateProject(mockScenario);
    verifyZeroInteractions(mockScenario);
  }

  @Test(expected = NoSuchElementException.class)
  public void aProjectIteratorShouldThrowNoSuchElementExceptionOnNextWhenHasNextIsFalse() {
    final ProjectGenerator mockProjectGenerator = mock(ProjectGenerator.class);
    final Scenario mockScenario = mock(Scenario.class);
    final long testIterations = 1;

    final ProjectIterator instance = (ProjectIterator) new ProjectIterable(mockProjectGenerator, mockScenario,
            testIterations).iterator();

    assertThat(instance.hasNext(), is(true));
    instance.next();

    assertThat(instance.hasNext(), is(false));
    instance.next();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void aProjectIteratorShouldThrowUnsupportedOperationExceptionOnRemove() {
    final ProjectGenerator mockProjectGenerator = mock(ProjectGenerator.class);
    final Scenario mockScenario = mock(Scenario.class);
    final long testIterations = 1;

    final ProjectIterator instance = (ProjectIterator) new ProjectIterable(mockProjectGenerator, mockScenario,
            testIterations).iterator();

    instance.remove();
  }
}
