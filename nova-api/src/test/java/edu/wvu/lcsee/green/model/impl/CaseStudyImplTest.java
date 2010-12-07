package edu.wvu.lcsee.green.model.impl;

import java.io.Serializable;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Constraints;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for {@link CaseStudyImpl}.
 *
 * @author pdgreen
 */
public class CaseStudyImplTest {

  @Test
  public void aCaseStudyImplConstructedWithNameShouldReturnTheSameName() {
    final String testName = "testName";

    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);

    final CaseStudyImpl instance = new CaseStudyImpl(testName, attributeConstraints);

    assertThat(instance.getName(), is(testName));
  }

  @Test
  public void aCaseStudyImplConstructedWithNoNameShouldReturnNullName() {
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);

    final CaseStudyImpl instance = new CaseStudyImpl(attributeConstraints);

    assertThat(instance.getName(), is(nullValue()));
  }
}
