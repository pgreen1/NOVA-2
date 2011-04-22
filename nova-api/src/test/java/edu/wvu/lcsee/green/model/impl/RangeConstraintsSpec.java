package edu.wvu.lcsee.green.model.impl;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Spec for {@link RangeConstraints}.
 * @author pdgreen
 */
public class RangeConstraintsSpec {

  @Test
  public void itShouldGenerateValueThatIsValidWhenNonSingleValue() {
    final int min = 5;
    final int max = 10;
    final RangeConstraints instance = new RangeConstraints(min, max, 1);

    final Number result = instance.generateValue();

    assertTrue(result.doubleValue() <= max);
    assertTrue(result.doubleValue() >= min);
  }

  @Test
  public void itShouldGenerateValueThatIsValidWhenSingleValue() {
    final int value = 10;
    final RangeConstraints instance = new RangeConstraints(value);

    final Number result = instance.generateValue();

    assertEquals(value, result.doubleValue(), 0.0000001);
  }

  @Test
  public void itShouldReturnTrueForIsSingleValuedWhenSingleValue() {
    final int value = 10;
    final RangeConstraints instance = new RangeConstraints(value);

    assertThat(instance.isSingleValued(), is(true));
  }

  @Test
  public void itShouldReturnFalseForIsSingleValuedWhenNonSingleValue() {
    final int min = 5;
    final int max = 10;
    final RangeConstraints instance = new RangeConstraints(min, max, 1);

    assertThat(instance.isSingleValued(), is(false));
  }

  @Test
  public void isShouldReturnTrueForIsSubrangeWhenSubrange() {
    final RangeConstraints largeInstance = new RangeConstraints(1, 10, 1);
    final RangeConstraints frontInstance = new RangeConstraints(1, 5, 1);
    final RangeConstraints backInstance = new RangeConstraints(5, 10, 1);
    final RangeConstraints innerInstance = new RangeConstraints(2, 6, 1);
    final RangeConstraints otherInstance = new RangeConstraints(15, 20, 1);

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
    final RangeConstraints largeInstance = new RangeConstraints(1, 10, 1);
    final RangeConstraints singleInstance = new RangeConstraints(5);
    final RangeConstraints otherSingleInstance = new RangeConstraints(15);

    assertThat(singleInstance.isSubrangeOf(largeInstance), is(true));
    assertThat(largeInstance.isSubrangeOf(singleInstance), is(false));

    assertThat(otherSingleInstance.isSubrangeOf(largeInstance), is(false));
    assertThat(largeInstance.isSubrangeOf(otherSingleInstance), is(false));
  }

  @Test
  public void isShouldReturnTrueForIsSubrangeWhenSameRange() {
    final int min = 5;
    final int max = 10;
    final RangeConstraints instance = new RangeConstraints(min, max, 1);

    assertThat(instance.isSubrangeOf(instance), is(true));
  }

  @Test
  public void isShouldReturnTrueForIsSubrangeWhenSameSingle() {
    final int value = 10;
    final RangeConstraints instance = new RangeConstraints(value);

    assertThat(instance.isSubrangeOf(instance), is(true));
  }
}
