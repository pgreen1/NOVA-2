
package edu.wvu.lcsee.green.mymodel.experiments


import org.junit.Test
import static org.junit.Assert.*
import static org.junit.matchers.JUnitMatchers.*
import static org.hamcrest.CoreMatchers.*
import edu.wvu.lcsee.green.model.AttributeContext

import static edu.wvu.lcsee.green.mymodel.model.MyModelAttribute.*
import static edu.wvu.lcsee.green.mymodel.model.MyModelAttribute.ProjectSize.*

/**
 *
 * @author pdgreen
 */
class AttributeContextBuilderTest {

  @Test
  void aAttributeContextShouldBuildWithNameAndAttributes() {
    final AttributeContext all = new AttributeContextBuilder().attributeContext{
      name 'Company X'
      attributes( [MEXP, PSIZE])
    }

    assertThat(all.name, is('Company X'))
    assertThat(all.getConstrainableAttributes().size(), is(2))
    assertThat(all.getConstrainableAttributes(), hasItems(MEXP,PSIZE))
  }

  @Test
  void aAttributeContextShouldBuildWithAttributes() {
    final AttributeContext some = new AttributeContextBuilder().attributeContext{
      attributes ([PSIZE])
    }

    assertThat(some.name, is(null))
    assertThat(some.getConstrainableAttributes().size(), is(1))
    assertThat(some.getConstrainableAttributes(), hasItems(PSIZE))
  }

}

