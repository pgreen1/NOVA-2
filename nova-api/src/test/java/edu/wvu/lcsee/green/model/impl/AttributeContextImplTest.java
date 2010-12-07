package edu.wvu.lcsee.green.model.impl;

import java.util.Set;
import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Constraints;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for {@link AttributeContextImplTest}.
 *
 * @author pdgreen
 */
public class AttributeContextImplTest {

  @Test
  public void anAttributeContextImplConstructedWithNameShouldReturnTheSameName() {
    final String testName = "testName";

    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Set<Attribute<? extends Serializable>> constrainableAttributes = ImmutableSet.of(mockAttribute1, mockAttribute2);

    final AttributeContextImpl instance = new AttributeContextImpl(testName, constrainableAttributes);

    assertThat(instance.getName(), is(testName));
  }

  @Test
  public void anAttributeContextImplConstructedWithNoNameShouldReturnNullName() {
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Set<Attribute<? extends Serializable>> constrainableAttributes = ImmutableSet.of(mockAttribute1, mockAttribute2);

    final AttributeContextImpl instance = new AttributeContextImpl(constrainableAttributes);

    assertThat(instance.getName(), is(nullValue()));
  }

  @Test
  public void anAttributeContextImplShouldReturnProperConstrainableAttributes() {
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Set<Attribute<? extends Serializable>> constrainableAttributes = ImmutableSet.of(mockAttribute1, mockAttribute2);

    final AttributeContextImpl instance = new AttributeContextImpl(constrainableAttributes);

    assertThat(instance.getConstrainableAttributes(), is(constrainableAttributes));
  }
}
