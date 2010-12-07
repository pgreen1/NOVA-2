package edu.wvu.lcsee.green.model.impl;

import java.util.Set;
import com.google.common.collect.Maps;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.CaseStudy;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ModelConfiguration;
import edu.wvu.lcsee.green.model.AttributeContext;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.spi.ScoringFunction;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nonnull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author pdgreen
 */
public class ModelConfigurationImpl implements ModelConfiguration {

  private final ImmutableMap<Attribute<? extends Serializable>, Constraints<? extends Serializable>> defaultAttributeConstraints;
  private final ImmutableSet<ScoringFunction> allScoringFunctions;

  public ModelConfigurationImpl(
          @Nonnull final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> defaultAttributeConstraints,
          @Nonnull final Set<ScoringFunction> allScoringFunctions) {
    this.defaultAttributeConstraints = ImmutableMap.copyOf(defaultAttributeConstraints);
    this.allScoringFunctions = ImmutableSet.copyOf(allScoringFunctions);
  }

  @Override
  public ImmutableSet<Attribute<? extends Serializable>> getAllAttributes() {
    return defaultAttributeConstraints.keySet();
  }

  @Override
  public <V extends Serializable> Constraints<V> getDefaultConstraintsFor(@Nonnull final Attribute<V> attribute) {
    checkNotNull(attribute);
    return (Constraints<V>) defaultAttributeConstraints.get(attribute);
  }

  @Override
  public Scenario generateScenario(@Nonnull final AttributeContext attributeContext, @Nonnull final CaseStudy caseStudy) {
    checkNotNull(attributeContext);
    checkNotNull(caseStudy);
    final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = Maps.
            newHashMap(defaultAttributeConstraints);

    for (final Attribute<? extends Serializable> attribute : caseStudy.getAllAttributes()) {
      attributeConstraints.put(attribute,
              attributeConstraints.get(attribute).mergeConstraints((Constraints) caseStudy.getConstraintsFor(attribute)));
    }
    return new ScenarioImpl(attributeConstraints, attributeContext.getConstrainableAttributes());
  }

  @Override
  public ImmutableSet<ScoringFunction> getAllScoringFunctions() {
    return allScoringFunctions;
  }
}
