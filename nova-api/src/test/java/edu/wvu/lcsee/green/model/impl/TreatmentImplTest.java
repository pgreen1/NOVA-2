package edu.wvu.lcsee.green.model.impl;

import edu.wvu.lcsee.green.model.Treatment;

import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Constraints;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Tests for {@link TreatmentImpl}.
 * @author pdgreen
 */
public class TreatmentImplTest {

  @Test
  public void newSingletonTreatmentShouldReturnANewSingletonTreatment() {
    final Attribute<Long> mockAttribute = mock(Attribute.class);
    final Constraints<Long> mockConstraints = mock(Constraints.class);

    final Treatment result = TreatmentImpl.newSingletonTreatment(mockAttribute, mockConstraints);

    assertThat(result.getAllAttributes().size(), is(1));
    assertThat(result.getAllAttributes().contains(mockAttribute),is(true));

    assertThat(result.getConstraintsFor(mockAttribute), is(mockConstraints));

  }
}
