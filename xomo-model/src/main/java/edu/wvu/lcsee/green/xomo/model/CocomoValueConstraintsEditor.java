package edu.wvu.lcsee.green.xomo.model;

import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import edu.wvu.lcsee.green.model.ConstraintsEditor.DiscreteValue;
import java.util.Set;

/**
 *
 * @author pdgreen
 */
public abstract class CocomoValueConstraintsEditor<C extends CocomoValue> implements ConstraintsEditor<C, DiscreteValue<C>> {

  protected final ConstraintsEditor<C, ConstraintsEditor.DiscreteValue<C>> delegate;

  public CocomoValueConstraintsEditor(ConstraintsEditor<C, ? extends ConstraintsEditor.DiscreteValue<C>> delegateEditor) {
    this.delegate = (ConstraintsEditor<C, ConstraintsEditor.DiscreteValue<C>>) delegateEditor;
  }

  @Override
  public ImmutableSet<DiscreteValue<C>> getAllValues() {
    return delegate.getAllValues();
  }

  @Override
  public ImmutableSet<DiscreteValue<C>> getExtremesValues() {
    return delegate.getExtremesValues();
  }

  @Override
  public Set<DiscreteValue<C>> getCurrentValues() {
    return delegate.getCurrentValues();
  }

  @Override
  public ImmutableSet<DiscreteValue<C>> getRemovableValues() {
    return delegate.getRemovableValues();
  }

  @Override
  public boolean isSingletonValue() {
    return delegate.isSingletonValue();
  }

  @Override
  public boolean addValue(final DiscreteValue<C> value) {
    return delegate.addValue(value);
  }

  @Override
  public boolean removeValue(final DiscreteValue<C> value) {
    return delegate.removeValue(value);
  }

  @Override
  public void removeAllValues() {
    delegate.removeAllValues();
  }
}
