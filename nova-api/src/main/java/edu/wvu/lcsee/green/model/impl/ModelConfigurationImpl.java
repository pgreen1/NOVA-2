package edu.wvu.lcsee.green.model.impl;

import java.util.SortedMap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Set;
import com.google.common.collect.Maps;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.CaseStudy;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ModelConfiguration;
import edu.wvu.lcsee.green.model.NamedConstrainableAttributes;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.ScoringFunction;
import java.io.Serializable;
import javax.annotation.Nonnull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author pdgreen
 */
public class ModelConfigurationImpl implements ModelConfiguration {

  private static final String DEFAULT_CONSTRAINABLE_ATTRIBUTES_NAME = "DEFAULT";
  private final ImmutableSortedMap<Attribute<? extends Serializable>, Constraints<? extends Serializable>> defaultAttributeConstraints;
  private final NamedConstrainableAttributes defaultConstrainableAttributes;
  private final ImmutableBiMap<String, ScoringFunction> scoringFunctionsRegistry;

  public ModelConfigurationImpl(
          @Nonnull final SortedMap<Attribute<? extends Serializable>, Constraints<? extends Serializable>> defaultAttributeConstraints,
          @Nonnull final Set<Attribute<? extends Serializable>> defaultConstrainableAttributes,
          @Nonnull final Set<ScoringFunction> allScoringFunctions) {
    this.defaultAttributeConstraints = ImmutableSortedMap.copyOfSorted(defaultAttributeConstraints);
    this.defaultConstrainableAttributes = new NamedConstrainableAttributesImpl(DEFAULT_CONSTRAINABLE_ATTRIBUTES_NAME,
            defaultConstrainableAttributes);
    final BiMap<String, ScoringFunction> sfr = HashBiMap.create(allScoringFunctions.size());
    for (final ScoringFunction scoringFunction : allScoringFunctions) {
      sfr.put(scoringFunction.getKey(), scoringFunction);
    }
    this.scoringFunctionsRegistry = ImmutableBiMap.copyOf(sfr);
  }

  @Override
  public ImmutableSortedSet<Attribute<? extends Serializable>> getAllAttributes() {
    return defaultAttributeConstraints.keySet();
  }

  @Override
  public <V extends Serializable> Constraints<V> getDefaultConstraintsFor(@Nonnull final Attribute<V> attribute) {
    checkNotNull(attribute);
    return (Constraints<V>) defaultAttributeConstraints.get(attribute);
  }

  @Override
  public NamedConstrainableAttributes getDefaultConstrainableAttributes() {
    return defaultConstrainableAttributes;
  }

  @Override
  public Scenario generateScenario(@Nonnull final NamedConstrainableAttributes attributeContext,
          @Nonnull final CaseStudy caseStudy) {
    checkNotNull(attributeContext);
    checkNotNull(caseStudy);
    final SortedMap<Attribute<? extends Serializable>, Constraints<? extends Serializable>> attributeConstraints = Maps.
            newTreeMap(defaultAttributeConstraints);

    for (final Attribute<? extends Serializable> attribute : caseStudy.getAllAttributes()) {
      attributeConstraints.put(attribute,
              attributeConstraints.get(attribute).mergeConstraints((Constraints) caseStudy.getConstraintsFor(attribute)));
    }
    return new ScenarioImpl(this, attributeConstraints, attributeContext.getConstrainableAttributes());
  }

  @Override
  public Scenario generateDefaultScenario() {
    return generateScenario(getDefaultConstrainableAttributes(), new CaseStudyImpl(defaultAttributeConstraints));
  }

  @Override
  public ImmutableSet<ScoringFunction> getAllScoringFunctions() {
    return scoringFunctionsRegistry.values();
  }

  @Override
  public ScoringFunction getScoringFunctionForKey(@Nonnull final String key) {
    return scoringFunctionsRegistry.get(key);
  }
}
