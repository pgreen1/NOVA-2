
package edu.wvu.lcsee.green.nova.groovy


import edu.wvu.lcsee.green.model.Attribute
import edu.wvu.lcsee.green.model.impl.NamedConstrainableAttributesImpl
/**
 *
 * @author pdgreen
 */
class NamedConstrainableAttributesBuilder extends BuilderSupport {

  private String name
  private Set<Attribute<? extends Serializable>> constrainableAttributes

  private Object rootMarker = new Object();

  protected void setParent(Object parent, Object child) {
  }

  protected Object createNode(Object name) {
    if("attributeContext" == name) {
      return rootMarker
    } else {
      throw new IllegalArgumentException("unexpected name: " + name)
    }
  }

  protected Object postNodeCompletion(Object parent, Object node) {
    if(rootMarker == node) {
      return new NamedConstrainableAttributesImpl(name, constrainableAttributes)
    }
    return super.postNodeCompletion(parent,node)
  }

  protected Object createNode(Object name, Object value) {
    if("name" == name) {
      this.name = value
    } else if ("attributes" == name) {
      this.constrainableAttributes = new HashSet((Collection)value)
    } else {
      throw new IllegalArgumentException("unexpected name-value: " + name +"-" + value )
    }
  }

  protected Object createNode(Object name, Map attrs) {
    throw new IllegalArgumentException("unexpected name-attrs: " + name +"-" + attrs )
  }

  protected Object createNode(Object name, Map attrs, Object value) {
    throw new IllegalArgumentException("unexpected name-attrs-value: " + name +"-" + attrs + "-" +value)
  }
}

