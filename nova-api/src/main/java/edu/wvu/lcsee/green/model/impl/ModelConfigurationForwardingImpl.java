package edu.wvu.lcsee.green.model.impl;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.CaseStudy;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ModelConfiguration;
import edu.wvu.lcsee.green.model.NamedConstrainableAttributes;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.ScoringFunction;
import java.io.Serializable;

/**
 * Implementation for {@link ModelConfiguration} where all methods delegate to the
 * ModelConfiguration returned by {@link ModelConfigurationForwardingImpl#delegate()}.
 *
 * @author pdgreen
 */
public abstract class ModelConfigurationForwardingImpl implements ModelConfiguration {

  /**
   * This method should return the delegate for forwarding of methods.
   *
   * @return the delegate to forward methods onto.
   */
  protected abstract ModelConfiguration delegate();

  @Override
  public ImmutableSortedSet<Attribute<? extends Serializable>> getAllAttributes() {
    return delegate().getAllAttributes();
  }

  @Override
  public <V extends Serializable> Constraints<V> getDefaultConstraintsFor(final Attribute<V> attribute) {
    return delegate().getDefaultConstraintsFor(attribute);
  }

  @Override
  public NamedConstrainableAttributes getDefaultConstrainableAttributes() {
    return delegate().getDefaultConstrainableAttributes();
  }

  @Override
  public Scenario generateScenario(final NamedConstrainableAttributes namedConstrainableAttributes,
          final CaseStudy caseStudy) {
    return delegate().generateScenario(namedConstrainableAttributes, caseStudy);
  }

  @Override
  public Scenario generateDefaultScenario() {
    return delegate().generateDefaultScenario();
  }

  @Override
  public ImmutableSet<ScoringFunction> getAllScoringFunctions() {
    return delegate().getAllScoringFunctions();
  }

  @Override
  public ScoringFunction getScoringFunctionForKey(final String key) {
    return delegate().getScoringFunctionForKey(key);
  }
}
