package edu.wvu.lcsee.green.model;

import edu.wvu.lcsee.green.model.impl.DefaultAttribute;
import java.io.Serializable;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Specification for {@link ConstraintsContextBuilder}.
 * @author pdgreen
 */
public class ConstraintsContextBuilderSpec {

  static final Attribute<Number> ATTRIBUTE_1 = new DefaultAttribute<Number>("ATTRIBUTE_1", "Testing Attribute 1",
          Number.class);

  @Test
  public void valuesAddedShouldBeInTheBuiltContext() {
    final Number value = Long.valueOf(100L);

    final ConstraintsContextBuilder instance = new ConstraintsContextBuilder();

    instance.addValue(ATTRIBUTE_1, value);

    assertThat(instance.build().getValueFor(ATTRIBUTE_1), is(value));
  }

  @Test(expected = IllegalArgumentException.class)
  public void builtContextShouldThrowIllegalArgumentExceptionWhenValueHasNotBeenAdded() {
    final ConstraintsContextBuilder instance = new ConstraintsContextBuilder();

    instance.build().getValueFor(ATTRIBUTE_1);
  }

  @Test
  public void valuesAddedShouldBeInTheBuiltMap() {
    final Number value = Long.valueOf(100L);

    final ConstraintsContextBuilder instance = new ConstraintsContextBuilder();

    instance.addValue(ATTRIBUTE_1, value);

    assertThat(instance.buildMap().get(ATTRIBUTE_1), is((Serializable) value));
  }
}
