
package edu.wvu.lcsee.green.nova.groovy

import org.junit.Test
import static org.junit.Assert.*
import static org.junit.matchers.JUnitMatchers.*
import static org.hamcrest.CoreMatchers.*
import edu.wvu.lcsee.green.model.CaseStudy
import static edu.wvu.lcsee.green.nova.groovy.FakeAttributes.*;

class CaseStudyBuilderTest {


 
  @Test
  void aCaseStudyShouldBuildWithNameAndConstraints() {
    final CaseStudy companyX = new CaseStudyBuilder().caseStudy{
      name 'Company X'
      constraints (
        (ATTR1): 5..10,
        (ATTR2): 2
      )
    }

    assertThat(companyX.name, is('Company X'))
    assertThat(companyX.getAllAttributes().size(), is(2))
    assertThat(companyX.getAllAttributes(), hasItems(ATTR1,ATTR2))
  }

    @Test
  void aCaseStudyShouldBuildWithConstraints() {
    final CaseStudy caseStudy = new CaseStudyBuilder().caseStudy{
      constraints (
        (ATTR1): 3..7,
        (ATTR2): 5
      )
    }

    assertThat(caseStudy.name, is(null))
    assertThat(caseStudy.getAllAttributes().size(), is(2))
    assertThat(caseStudy.getAllAttributes(), hasItems(ATTR1,ATTR2))
  }

}

