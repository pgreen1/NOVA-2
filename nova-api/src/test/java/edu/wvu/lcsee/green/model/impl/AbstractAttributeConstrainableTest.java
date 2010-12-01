package edu.wvu.lcsee.green.model.impl;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Constraints;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Tests for AbstractAttributeConstrainable.
 * 
 * @author pdgreen
 */
public class AbstractAttributeConstrainableTest {

  @Test
  public void anAbstractAttributeConstrainableOnGetAllAttributesShouldReturnAllAttributes() {
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);


    final AbstractAttributeConstrainable instance = new AbstractAttributeConstrainableImpl(attributeConstraints);


    ImmutableSet<Attribute<? extends Serializable>> expResult = ImmutableSet.<Attribute<? extends Serializable>>of(
            mockAttribute1, mockAttribute2);
    ImmutableSet<Attribute<? extends Serializable>> result = instance.getAllAttributes();
    assertThat(result, is(expResult));
  }

  @Test
  public void anAbstractAttributeConstrainableOnGetConstraintsForShouldReturnTheCorrectConstraints() {
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);


    final AbstractAttributeConstrainable instance = new AbstractAttributeConstrainableImpl(attributeConstraints);


    Constraints result = instance.getConstraintsFor(mockAttribute1);
    assertThat(result, is((Constraints) mockConstraints1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void anAbstractAttributeConstrainableOnGetConstraintsForShouldThrowIllegalArgumentExceptionWhenAttributeNotInAllAttributes() {
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);

    final Attribute<? extends Serializable> mockOtherAttribute = mock(Attribute.class);

    final AbstractAttributeConstrainable instance = new AbstractAttributeConstrainableImpl(attributeConstraints);

    assertThat(instance.getAllAttributes().contains(mockOtherAttribute), is(false));

    instance.getConstraintsFor(mockOtherAttribute);
  }

  @Test
  public void anAbstractAttributeConstrainableOnAMapShouldReturnAMapEqualToInputAttributeConstraints() {
    final Attribute<? extends Serializable> mockAttribute1 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints1 = mock(Constraints.class);
    final Attribute<? extends Serializable> mockAttribute2 = mock(Attribute.class);
    final Constraints<? extends Serializable> mockConstraints2 = mock(Constraints.class);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = ImmutableMap.
            of(mockAttribute1, mockConstraints1, mockAttribute2, mockConstraints2);

    final AbstractAttributeConstrainable instance = new AbstractAttributeConstrainableImpl(attributeConstraints);

    Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> result = instance.asMap();
    assertThat(result, is(attributeConstraints));
  }

  /**
   * Basic implementation of AbstractAttributeConstrainable to be used for unit tests.
   */
  static class AbstractAttributeConstrainableImpl extends AbstractAttributeConstrainable {

    public AbstractAttributeConstrainableImpl(
            @Nonnull final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints) {
      super(attributeConstraints);
    }
  }
}
