
package edu.wvu.lcsee.green.mymodel.experiments

import org.junit.Test
import static org.junit.Assert.*
import static org.junit.matchers.JUnitMatchers.*
import static org.hamcrest.CoreMatchers.*
import edu.wvu.lcsee.green.model.CaseStudy

import static edu.wvu.lcsee.green.mymodel.model.MyModelAttribute.*
import static edu.wvu.lcsee.green.mymodel.model.MyModelAttribute.ProjectSize.*

class CaseStudyBuilderTest {

  @Test
  void aCaseStudyShouldBuildWithNameAndConstraints() {
    final CaseStudy companyX = new CaseStudyBuilder().caseStudy{
      name 'Company X'
      constraints (
        (MEXP): 5..10,
        (PSIZE): MEDIUM
      )
    }

    assertThat(companyX.name, is('Company X'))
    assertThat(companyX.getAllAttributes().size(), is(2))
    assertThat(companyX.getAllAttributes(), hasItems(MEXP,PSIZE))
  }

    @Test
  void aCaseStudyShouldBuildWithConstraints() {
    final CaseStudy caseStudy = new CaseStudyBuilder().caseStudy{
      constraints (
        (MEXP): 3..7,
        (PSIZE): SMALL
      )
    }

    assertThat(caseStudy.name, is(null))
    assertThat(caseStudy.getAllAttributes().size(), is(2))
    assertThat(caseStudy.getAllAttributes(), hasItems(MEXP,PSIZE))
  }

}

