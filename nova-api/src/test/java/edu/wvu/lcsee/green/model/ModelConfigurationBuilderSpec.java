package edu.wvu.lcsee.green.model;

import com.google.common.collect.Lists;
import java.util.List;
import edu.wvu.lcsee.green.model.impl.DefaultAttribute;
import edu.wvu.lcsee.green.model.impl.RangeConstraints;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Specification for {@link ModelConfigurationBuilder}.
 * @author pdgreen
 */
public class ModelConfigurationBuilderSpec {

  @Test
  public void modelConfigurationBuiltShouldReturnAllAttributesInOrderTheyWereAdded() {

    final Attribute<Double> attr1 = DefaultAttribute.newInstance("ATTR1", "Attribute 1", Double.class);
    final Attribute<Double> attr2 = DefaultAttribute.newInstance("ATTR2", "Attribute 2", Double.class);
    final Attribute<Double> attr3 = DefaultAttribute.newInstance("ATTR3", "Attribute 3", Double.class);
    final Attribute<Double> attr4 = DefaultAttribute.newInstance("ATTR4", "Attribute 4", Double.class);


    final ModelConfigurationBuilder instance = ModelConfigurationBuilder.newInstance();

    final ModelConfiguration modelConfiguration = ModelConfigurationBuilder.newInstance().addConstrainableAttribute(
            attr4, new RangeConstraints(44.0)).
            addUnconstrainableAttribute(attr2, new RangeConstraints(22.0)).addUnconstrainableAttribute(attr3, new RangeConstraints(
            33.0)).addConstrainableAttribute(attr1, new RangeConstraints(11.0)).build();


    final List<Attribute<?>> orderedAttributes = Lists.newArrayList(modelConfiguration.getAllAttributes());

    assertThat(orderedAttributes.get(0), is((Attribute) attr4));
    assertThat(orderedAttributes.get(1), is((Attribute) attr2));
    assertThat(orderedAttributes.get(2), is((Attribute) attr3));
    assertThat(orderedAttributes.get(3), is((Attribute) attr1));
  }
}
