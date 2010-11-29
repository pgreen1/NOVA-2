
package edu.wvu.lcsee.green.mymodel.experiments


import edu.wvu.lcsee.green.model.Attribute
import edu.wvu.lcsee.green.model.impl.AttributeContextImpl
/**
 *
 * @author pdgreen
 */
class AttributeContextBuilder extends BuilderSupport {

  private String name
  private Set<Attribute<? extends Serializable>> modifiableAttributes

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
      return new AttributeContextImpl(name, modifiableAttributes)
    }
    return super.postNodeCompletion(parent,node)
  }

  protected Object createNode(Object name, Object value) {
    if("name" == name) {
      this.name = value
    } else if ("attributes" == name) {
      this.modifiableAttributes = new HashSet((Collection)value)
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
