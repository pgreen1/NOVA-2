package edu.wvu.lcsee.green.spi;

import edu.wvu.lcsee.green.model.impl.ProjectImpl;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.util.Set;
import java.util.Map;
import edu.wvu.lcsee.green.model.Attribute;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.Project;
import edu.wvu.lcsee.green.model.Scenario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.matchers.JUnitMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author pdgreen
 */
public class WhenGeneratingProjects {

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  protected ProjectGenerator createProjectGeneratorForTesting() {
    return new ProjectGeneratorImpl();
  }

  @Test
  public void aProjectShouldHaveAllOfTheAttributesOfTheScenario() {

    final ProjectGenerator projectGenerator = createProjectGeneratorForTesting();
    final Scenario mockScenario = mock(Scenario.class);

    final Attribute<Number> mockAttribute1 = mock(Attribute.class, "mockAttribute1");
    final Constraints<Number> mockConstraints1 = mock(Constraints.class, "mockConstraints1");
    final Number testValue1 = new BigInteger("1234");


    final Attribute<Number> mockAttribute2 = mock(Attribute.class, "mockAttribute2");
    final Constraints<Number> mockConstraints2 = mock(Constraints.class, "mockConstraints2");
    final Number testValue2 = new BigDecimal("1231.2343");

    final ImmutableSet<Attribute<? extends Serializable>> attributes = ImmutableSet.<Attribute<? extends Serializable>>
            of(mockAttribute1, mockAttribute2);

    when(mockScenario.getAllAttributes()).thenReturn(attributes);
    when(mockScenario.getConstraintsFor(mockAttribute1)).thenReturn(mockConstraints1);
    when(mockConstraints1.generateValue()).thenReturn(testValue1);
    when(mockScenario.getConstraintsFor(mockAttribute2)).thenReturn(mockConstraints2);
    when(mockConstraints2.generateValue()).thenReturn(testValue2);


    final Project project = projectGenerator.generate(mockScenario);
    assertNotNull(project);
    //FIXME create matcher to match sets
    assertThat(project.getAttributes(), hasItems((Attribute<? extends Serializable>[]) mockScenario.getAllAttributes().
            toArray(new Attribute[0])));
  }

  public static class ProjectGeneratorImpl implements ProjectGenerator {

    public Project generate(Scenario scenario) {
      //TODO i had to make map raw because I couldn't figure out the generic capturing
      final Map values = Maps.newHashMap();

      for (final Attribute<? extends Serializable> attribute : scenario.getAllAttributes()) {
        values.put(attribute, scenario.getConstraintsFor(attribute).generateValue());
      }
      return new ProjectImpl(values);
    }

    public ImmutableSet<Project> generateMany(Scenario scenario, int numberOfProjectsToCreate) {
      final Set<Project> projects = Sets.newHashSet();
      for (int i = 0; i < numberOfProjectsToCreate; i++) {
        projects.add(generate(scenario));
      }
      return ImmutableSet.copyOf(projects);
    }
  }
}
