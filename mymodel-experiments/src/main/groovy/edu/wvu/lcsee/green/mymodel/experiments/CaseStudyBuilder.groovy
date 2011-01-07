
package edu.wvu.lcsee.green.mymodel.experiments

import edu.wvu.lcsee.green.model.Constraints
import edu.wvu.lcsee.green.model.Attribute
import edu.wvu.lcsee.green.model.impl.CaseStudyImpl
import edu.wvu.lcsee.green.model.impl.SetConstraints
import com.google.common.collect.ImmutableSet
import groovy.util.BuilderSupport

class CaseStudyBuilder extends BuilderSupport {

  private String name
  private Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints

  private Object caseStudyMarker = new Object();

  protected void setParent(Object parent, Object child) {
  }

  protected Object createNode(Object name) {
    if("caseStudy" == name) {
      return caseStudyMarker
    } else {
      throw new IllegalArgumentException("unexpected name: " + name)
    }
  }

  protected Object postNodeCompletion(Object parent, Object node) {
    if(caseStudyMarker == node) {
      return new CaseStudyImpl(name, attributeConstraints)
    }
    return super.postNodeCompletion(parent,node)
  }

  protected Object createNode(Object name, Object value) {
    if("name" == name) {
      this.name = value
    } else {
      throw new IllegalArgumentException("unexpected name-value: " + name +"-" + value )
    }
  }

  protected Object createNode(Object name, Map attrs) {
    if("constraints" == name) {
      this.attributeConstraints = generateAttributeConstraints(attrs)
    } else {
      throw new IllegalArgumentException("unexpected name-attrs: " + name +"-" + attrs )
    }
  }

  protected Object createNode(Object name, Map attrs, Object value) {
    throw new IllegalArgumentException("unexpected name-attrs-value: " + name +"-" + attrs + "-" +value)
  }

  protected Map<Attribute, Constraints> generateAttributeConstraints(Map<Attribute, Object> attrs) {
    
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = [:]
    for(final Map.Entry<Attribute<? extends Serializable>, Object> entry : attrs.entrySet()) {
      final Object values = entry.value
      final Constraints<? extends Serializable> constraints;
      if(values instanceof Collection) {
        constraints = new SetConstraints(ImmutableSet.copyOf((Collection)values))
      } else {
        constraints = new SetConstraints(ImmutableSet.of(values))
      }

      attributeConstraints.put(entry.key,constraints)
    }
    return attributeConstraints
  }
}

