package edu.wvu.lcsee.green.model.impl;

import java.util.Set;
import java.io.Serializable;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ModelConfiguration;
import edu.wvu.lcsee.green.model.Treatment;

import edu.wvu.lcsee.green.model.Scenario;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Tests for {@link ScenarioImpl}.
 * @author pdgreen
 */
public class ScenarioImplTest {

  @Test
  public void aScenarioImplOnGetconstrainableAttributesShouldReturnOnlyModifableAttributes() {
    final ModelConfiguration mockModelConfiguration = mock(ModelConfiguration.class);
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);
    final Set<Attribute<? extends Serializable>> constrainableAttributes = ImmutableSet.<Attribute<? extends Serializable>>
            of(mockAttribute1);


    final ScenarioImpl instance = new ScenarioImpl(mockModelConfiguration, attributeConstraints, constrainableAttributes);


    ImmutableSet<Attribute<? extends Serializable>> expResult = ImmutableSet.<Attribute<? extends Serializable>>of(
            mockAttribute1);
    ImmutableSet<Attribute<? extends Serializable>> result = instance.getConstrainableAttributes();
    assertThat(result, is(expResult));
  }

  @Test
  public void aScenarioImplOnApplyTreatmentShouldMergeOnlyAttributeConstraintsInTheTreatment() {
    final ModelConfiguration mockModelConfiguration = mock(ModelConfiguration.class);
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);
    final Set<Attribute<? extends Serializable>> constrainableAttributes = ImmutableSet.<Attribute<? extends Serializable>>
            of(mockAttribute1);


    final Constraints mockConstraints3 = mock(Constraints.class);
    final Treatment mockTreatment = mock(Treatment.class);
    when(mockTreatment.getAllAttributes()).thenReturn(ImmutableSet.<Attribute<? extends Serializable>>of(mockAttribute1));
    when(mockTreatment.getConstraintsFor(mockAttribute1)).thenReturn(mockConstraints3);

    final Constraints mockMergedConstraints = mock(Constraints.class);
    when(mockConstraints1.mergeConstraints(mockConstraints3)).thenReturn(mockMergedConstraints);


    final ScenarioImpl instance = new ScenarioImpl(mockModelConfiguration, attributeConstraints, constrainableAttributes);


    final Scenario result = instance.applyTreatment(mockTreatment);

    verify(mockTreatment).getConstraintsFor(mockAttribute1);
    verify(mockConstraints1).mergeConstraints(mockConstraints3);
    verifyZeroInteractions(mockConstraints2);

    assertThat(result.getAllAttributes(), is(ImmutableSet.of(mockAttribute1, mockAttribute2)));
    assertThat(result.getConstrainableAttributes(), is(constrainableAttributes));
    assertThat(result.getConstraintsFor(mockAttribute1), is(mockMergedConstraints));
    assertThat(result.getConstraintsFor(mockAttribute2), is((Constraints) mockConstraints2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void aScenarioImplOnApplyTreamentShouldThrowIllegalArgumentExceptionIfAnAttributeOfATreatmentIsNotInconstrainableAttributes() {
    final ModelConfiguration mockModelConfiguration = mock(ModelConfiguration.class);
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);
    final Set<Attribute<? extends Serializable>> constrainableAttributes = ImmutableSet.<Attribute<? extends Serializable>>
            of(mockAttribute1);

    final Constraints mockConstraints3 = mock(Constraints.class);
    final Treatment mockTreatment = mock(Treatment.class);
    when(mockTreatment.getAllAttributes()).thenReturn(ImmutableSet.<Attribute<? extends Serializable>>of(mockAttribute2));
    when(mockTreatment.getConstraintsFor(mockAttribute2)).thenReturn(mockConstraints3);

    final Constraints mockMergedConstraints = mock(Constraints.class);
    when(mockConstraints1.mergeConstraints(mockConstraints3)).thenReturn(mockMergedConstraints);


    final ScenarioImpl instance = new ScenarioImpl(mockModelConfiguration, attributeConstraints, constrainableAttributes);

    instance.applyTreatment(mockTreatment);
  }
}
