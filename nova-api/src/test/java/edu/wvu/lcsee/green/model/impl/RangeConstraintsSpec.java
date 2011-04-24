package edu.wvu.lcsee.green.model.impl;

import edu.wvu.lcsee.green.model.ConstraintsContext;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Spec for {@link RangeConstraints}.
 * @author pdgreen
 */
public class RangeConstraintsSpec {

  @Test
  public void itShouldGenerateValueThatIsValidWhenNonSingleValue() {
    final double min = 5;
    final double max = 10;
    final ConstraintsContext constraintsContext = mock(ConstraintsContext.class);

    final RangeConstraints instance = new RangeConstraints(min, max, 1.0);

    final Number result = instance.generateValue(constraintsContext);

    assertTrue(result.doubleValue() <= max);
    assertTrue(result.doubleValue() >= min);
  }

  @Test
  public void itShouldGenerateValueThatIsValidWhenSingleValue() {
    final double value = 10;
    final ConstraintsContext constraintsContext = mock(ConstraintsContext.class);

    final RangeConstraints instance = new RangeConstraints(value);

    final Number result = instance.generateValue(constraintsContext);

    assertEquals(value, result.doubleValue(), 0.0000001);
  }

  @Test
  public void itShouldReturnTrueForIsSingleValuedWhenSingleValue() {
    final double value = 10;
    final RangeConstraints instance = new RangeConstraints(value);

    assertThat(instance.isSingleValued(), is(true));
  }

  @Test
  public void itShouldReturnFalseForIsSingleValuedWhenNonSingleValue() {
    final double min = 5;
    final double max = 10;
    final RangeConstraints instance = new RangeConstraints(min, max, 1.0);

    assertThat(instance.isSingleValued(), is(false));
  }

  @Test
  public void isShouldReturnTrueForIsSubrangeWhenSubrange() {
    final RangeConstraints largeInstance = new RangeConstraints(1.0, 10.0, 1.0);
    final RangeConstraints frontInstance = new RangeConstraints(1.0, 5.0, 1.0);
    final RangeConstraints backInstance = new RangeConstraints(5.0, 10.0, 1.0);
    final RangeConstraints innerInstance = new RangeConstraints(2.0, 6.0, 1.0);
    final RangeConstraints otherInstance = new RangeConstraints(15.0, 20.0, 1.0);

    assertThat(frontInstance.isSubrangeOf(largeInstance), is(true));
    assertThat(largeInstance.isSubrangeOf(frontInstance), is(false));

    assertThat(backInstance.isSubrangeOf(largeInstance), is(true));
    assertThat(largeInstance.isSubrangeOf(backInstance), is(false));

    assertThat(innerInstance.isSubrangeOf(largeInstance), is(true));
    assertThat(largeInstance.isSubrangeOf(innerInstance), is(false));

    assertThat(otherInstance.isSubrangeOf(largeInstance), is(false));
    assertThat(largeInstance.isSubrangeOf(otherInstance), is(false));
  }

  @Test
  public void isShouldReturnTrueForIsSubrangeWhenSubrangeSingle() {
    final RangeConstraints largeInstance = new RangeConstraints(1.0, 10.0, 1.0);
    final RangeConstraints singleInstance = new RangeConstraints(5.0);
    final RangeConstraints otherSingleInstance = new RangeConstraints(15.0);

    assertThat(singleInstance.isSubrangeOf(largeInstance), is(true));
    assertThat(largeInstance.isSubrangeOf(singleInstance), is(false));

    assertThat(otherSingleInstance.isSubrangeOf(largeInstance), is(false));
    assertThat(largeInstance.isSubrangeOf(otherSingleInstance), is(false));
  }

  @Test
  public void isShouldReturnTrueForIsSubrangeWhenSameRange() {
    final double min = 5;
    final double max = 10;
    final RangeConstraints instance = new RangeConstraints(min, max, 1.0);

    assertThat(instance.isSubrangeOf(instance), is(true));
  }

  @Test
  public void isShouldReturnTrueForIsSubrangeWhenSameSingle() {
    final double value = 10;
    final RangeConstraints instance = new RangeConstraints(value);

    assertThat(instance.isSubrangeOf(instance), is(true));
  }
}
