package edu.wvu.lcsee.green.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Nonnull;

/**
 * This builder is design for efficiency.  It creates only one ConstraintsContext and updates it as values are added,
 * so nothing related to this class is thread safe.
 * <b>Not thread safe</b><br>
 * <b>ConstraintsContext generated isn't thread safe either</b>
 * @author pdgreen
 */
public class ConstraintsContextBuilder {

  private final Map<Attribute<? extends Serializable>, Serializable> values;
  private final Map<Attribute<? extends Serializable>, Serializable> unmodifiableVlaues;
  private final ConstraintsContextImpl constraintsContextImpl;

  public ConstraintsContextBuilder() {
    super();

    values = Maps.newHashMap();
    unmodifiableVlaues = Collections.unmodifiableMap(values);
    constraintsContextImpl = new ConstraintsContextImpl();
  }

  public <V extends Serializable> ConstraintsContextBuilder addValue(@Nonnull final Attribute<V> attribute,
          @Nonnull final V value) {
    Preconditions.checkArgument(!values.containsKey(attribute), "value already set for attribute: {}=>{}", attribute,
            value);
    values.put(attribute, value);

    return this;
  }

  public ConstraintsContext build() {
    return constraintsContextImpl;
  }

  public Map<Attribute<? extends Serializable>, ? extends Serializable> buildMap() {
    return unmodifiableVlaues;
  }

  class ConstraintsContextImpl implements ConstraintsContext {

    @Override
    public <V extends Serializable> V getValueFor(final Attribute<V> attribute) {
      Preconditions.checkArgument(values.containsKey(attribute), "attribute not in context {}", attribute);
      return (V) values.get(attribute);
    }
  }
}
