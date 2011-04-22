package edu.wvu.lcsee.green.xomo.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import edu.wvu.lcsee.green.model.ConstraintsEditor.DiscreteValue;
import java.util.Set;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public abstract class CocomoValueConstraintsEditor<C extends CocomoValue> implements ConstraintsEditor<C> {

  protected final ConstraintsEditor<CocomoLevel> delegate;

  public CocomoValueConstraintsEditor(@Nonnull final ConstraintsEditor<CocomoLevel> delegateEditor) {
    this.delegate = Preconditions.checkNotNull(delegateEditor);
  }

  @Override
  public ImmutableSet<DiscreteValue> getAllValues() {
    return delegate.getAllValues();
  }

  @Override
  public ImmutableSet<DiscreteValue> getExtremesValues() {
    return delegate.getExtremesValues();
  }

  @Override
  public Set<DiscreteValue> getCurrentValues() {
    return delegate.getCurrentValues();
  }

  @Override
  public ImmutableSet<DiscreteValue> getRemovableValues() {
    return delegate.getRemovableValues();
  }

  @Override
  public boolean isSingletonValue() {
    return delegate.isSingletonValue();
  }

  @Override
  public boolean addValue(final DiscreteValue value) {
    return delegate.addValue(value);
  }

  @Override
  public boolean removeValue(final DiscreteValue value) {
    return delegate.removeValue(value);
  }

  @Override
  public void removeAllValues() {
    delegate.removeAllValues();
  }
}
