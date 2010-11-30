package edu.wvu.lcsee.green.model.impl;

import java.util.Set;
import java.io.Serializable;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.Treatment;

import edu.wvu.lcsee.green.model.Scenario;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Tests for ScenarioImpl.
 * @author pdgreen
 */
public class ScenarioImplTest {

  @Test
  public void aScenarioImplShouldReturnAllAttributes() {
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);
    final Set<Attribute<? extends Serializable>> modifiableAttributes = ImmutableSet.<Attribute<? extends Serializable>>
            of(mockAttribute1);


    final ScenarioImpl instance = new ScenarioImpl(attributeConstraints, modifiableAttributes);


    ImmutableSet<Attribute<? extends Serializable>> expResult = ImmutableSet.<Attribute<? extends Serializable>>of(
            mockAttribute1, mockAttribute2);
    ImmutableSet<Attribute<? extends Serializable>> result = instance.getAllAttributes();
    assertThat(result, is(expResult));
  }

  @Test
  public void aScenarioImplShouldReturnTheCorrectConstraintsForGetConstraintsFor() {
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);
    final Set<Attribute<? extends Serializable>> modifiableAttributes = ImmutableSet.<Attribute<? extends Serializable>>
            of(mockAttribute1);


    final ScenarioImpl instance = new ScenarioImpl(attributeConstraints, modifiableAttributes);


    Constraints result = instance.getConstraintsFor(mockAttribute1);
    assertThat(result, is((Constraints) mockConstraints1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void aScenarioImplShouldThrowIllegalArgumentExceptionForGetConstraintsForWhenAttributeNotInGetAllAttributes() {
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);
    final Set<Attribute<? extends Serializable>> modifiableAttributes = ImmutableSet.<Attribute<? extends Serializable>>
            of(mockAttribute1);

    final Attribute<? extends Serializable> mockOtherAttribute = mock(Attribute.class);

    final ScenarioImpl instance = new ScenarioImpl(attributeConstraints, modifiableAttributes);

    assertThat(instance.getAllAttributes().contains(mockOtherAttribute), is(false));

    instance.getConstraintsFor(mockOtherAttribute);
  }

  @Test
  public void aScenarioImplShouldReturnModifableAttributes() {
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);
    final Set<Attribute<? extends Serializable>> modifiableAttributes = ImmutableSet.<Attribute<? extends Serializable>>
            of(mockAttribute1);


    final ScenarioImpl instance = new ScenarioImpl(attributeConstraints, modifiableAttributes);


    ImmutableSet<Attribute<? extends Serializable>> expResult = ImmutableSet.<Attribute<? extends Serializable>>of(
            mockAttribute1);
    ImmutableSet<Attribute<? extends Serializable>> result = instance.getModifiableAttributes();
    assertThat(result, is(expResult));
  }

  @Test
  public void aScenarioImplShouldReturnAMapEqualToInputAttributeConstraintsForAsMap() {
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);
    final Set<Attribute<? extends Serializable>> modifiableAttributes = ImmutableSet.<Attribute<? extends Serializable>>
            of(mockAttribute1);

    final ScenarioImpl instance = new ScenarioImpl(attributeConstraints, modifiableAttributes);


    Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> result = instance.asMap();
    assertThat(result, is(attributeConstraints));
  }

  @Test
  public void aScenarioImplShouldMergeOnlyAttributeConstraintsInTheTreatmentForApplyTreatment() {
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);
    final Set<Attribute<? extends Serializable>> modifiableAttributes = ImmutableSet.<Attribute<? extends Serializable>>
            of(mockAttribute1);


    final Constraints mockConstraints3 = mock(Constraints.class);
    final Treatment mockTreatment = mock(Treatment.class);
    when(mockTreatment.getAllAttributes()).thenReturn(ImmutableSet.<Attribute<? extends Serializable>>of(mockAttribute1));
    when(mockTreatment.getConstraintsFor(mockAttribute1)).thenReturn(mockConstraints3);

    final Constraints mockMergedConstraints = mock(Constraints.class);
    when(mockConstraints1.mergeConstraints(mockConstraints3)).thenReturn(mockMergedConstraints);


    final ScenarioImpl instance = new ScenarioImpl(attributeConstraints, modifiableAttributes);


    final Scenario result = instance.applyTreatment(mockTreatment);

    verify(mockTreatment).getConstraintsFor(mockAttribute1);
    verify(mockConstraints1).mergeConstraints(mockConstraints3);
    verifyZeroInteractions(mockConstraints2);

    assertThat(result.getAllAttributes(), is(ImmutableSet.of(mockAttribute1, mockAttribute2)));
    assertThat(result.getModifiableAttributes(), is(modifiableAttributes));
    assertThat(result.getConstraintsFor(mockAttribute1), is(mockMergedConstraints));
    assertThat(result.getConstraintsFor(mockAttribute2), is((Constraints) mockConstraints2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void aScenarioImplShouldThrowIllegalArgumentExceptionIfAnAttributeOfATreatmentIsNotInModifiableAttributesForApplyTreatment() {
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);
    final Set<Attribute<? extends Serializable>> modifiableAttributes = ImmutableSet.<Attribute<? extends Serializable>>
            of(mockAttribute1);

    final Constraints mockConstraints3 = mock(Constraints.class);
    final Treatment mockTreatment = mock(Treatment.class);
    when(mockTreatment.getAllAttributes()).thenReturn(ImmutableSet.<Attribute<? extends Serializable>>of(mockAttribute2));
    when(mockTreatment.getConstraintsFor(mockAttribute2)).thenReturn(mockConstraints3);

    final Constraints mockMergedConstraints = mock(Constraints.class);
    when(mockConstraints1.mergeConstraints(mockConstraints3)).thenReturn(mockMergedConstraints);


    final ScenarioImpl instance = new ScenarioImpl(attributeConstraints, modifiableAttributes);

    instance.applyTreatment(mockTreatment);
  }
}
