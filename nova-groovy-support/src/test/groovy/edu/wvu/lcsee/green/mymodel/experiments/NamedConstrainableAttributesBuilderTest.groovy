
package edu.wvu.lcsee.green.mymodel.experiments


import org.junit.Test
import static org.junit.Assert.*
import static org.junit.matchers.JUnitMatchers.*
import static org.hamcrest.CoreMatchers.*
import edu.wvu.lcsee.green.model.NamedConstrainableAttributes

import static edu.wvu.lcsee.green.mymodel.experiments.FakeAttributes.*;

/**
 *
 * @author pdgreen
 */
class AttributeContextBuilderTest {

  @Test
  void aAttributeContextShouldBuildWithNameAndAttributes() {
    final NamedConstrainableAttributes all = new NamedConstrainableAttributesBuilder().attributeContext{
      name 'Company X'
      attributes( [ATTR1, ATTR2])
    }

    assertThat(all.name, is('Company X'))
    assertThat(all.getConstrainableAttributes().size(), is(2))
    assertThat(all.getConstrainableAttributes(), hasItems(ATTR1,ATTR2))
  }

  @Test
  void aAttributeContextShouldBuildWithAttributes() {
    final NamedConstrainableAttributes some = new NamedConstrainableAttributesBuilder().attributeContext{
      attributes ([ATTR2])
    }

    assertThat(some.name, is(null))
    assertThat(some.getConstrainableAttributes().size(), is(1))
    assertThat(some.getConstrainableAttributes(), hasItems(ATTR2))
  }

}

