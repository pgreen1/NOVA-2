package mymodel

import edu.wvu.lcsee.green.model.CaseStudy
import edu.wvu.lcsee.green.nova.groovy.CaseStudyBuilder

import static edu.wvu.lcsee.green.mymodel.model.MyModelAttribute.*
import static edu.wvu.lcsee.green.mymodel.model.MyModelAttribute.ProjectSize.*


/**
 *
 * @author pdgreen
 */
class MyCaseStudies {

  static final CaseStudy COMPANY_X = new CaseStudyBuilder().caseStudy{
      name 'Company X'
      constraints (
        (MEXP): 5..10,
        (PSIZE): MEDIUM
      )
    }


    static final CaseStudy COMPANY_Y = new CaseStudyBuilder().caseStudy{
      name 'Company Y'
      constraints (
        (MEXP): 1..5,
        (PSIZE): [SMALL, MEDIUM]
      )
    }


    static final CaseStudy COMPANY_Z = new CaseStudyBuilder().caseStudy{
      name 'Company Z'
      constraints (
        (MEXP): 7..12,
        (PSIZE): [MEDIUM, LARGE]
      )
    }
}

