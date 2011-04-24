package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nonnull;
import com.google.common.collect.Sets;
import java.util.Set;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import edu.wvu.lcsee.green.model.impl.ModelConfigurationImpl;
import java.io.Serializable;
import java.util.Map;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author pdgreen
 */
public final class ModelConfigurationBuilder {

  private final List<Attribute<? extends Serializable>> orderedAttributes = Lists.newArrayList();
  private final Map<Attribute<? extends Serializable>, Constraints<? extends Serializable>> defaultAttributeConstraints = Maps.
          newHashMap();
  private final Set<Attribute<? extends Serializable>> constrainableAttributes = Sets.newHashSet();
  private final Set<ScoringFunction> scoringFunctions = Sets.newHashSet();

  private ModelConfigurationBuilder() {
  }

  public static ModelConfigurationBuilder newInstance() {
    return new ModelConfigurationBuilder();
  }

  public <V extends Serializable> ModelConfigurationBuilder addUnconstrainableAttribute(
          @Nonnull final Attribute<V> attribute,
          @Nonnull final Constraints<V> defaultConstraints) {
    checkNotNull(attribute);
    checkNotNull(defaultConstraints);
    checkState(!defaultAttributeConstraints.containsKey(attribute), "attribute already added: " + attribute.getName());
    checkDependencies(defaultAttributeConstraints.keySet(), defaultConstraints.getDependentAttributes());
    orderedAttributes.add(attribute);
    defaultAttributeConstraints.put(attribute, defaultConstraints);
    return this;
  }

  static void checkDependencies(@Nonnull final Set<?> currentAttributes,
          @Nonnull final Set<?> requiredAttributes) {
    final Set<?> unfilledDependencies = Sets.difference(requiredAttributes,
            currentAttributes);
    checkState(unfilledDependencies.isEmpty(), "unfullfilled required attributes: %s", unfilledDependencies);
  }

  public <V extends Serializable> ModelConfigurationBuilder addConstrainableAttribute(
          @Nonnull final Attribute<V> attribute,
          @Nonnull final Constraints<V> defaultConstraints) {
    addUnconstrainableAttribute(attribute, defaultConstraints);
    constrainableAttributes.add(attribute);
    return this;
  }

  public ModelConfigurationBuilder addScoringFunction(@Nonnull final ScoringFunction scoringFunction) {
    checkNotNull(scoringFunction);
    scoringFunctions.add(scoringFunction);
    return this;
  }

  public ModelConfiguration build() {
    //since the complete ordering isn't known until build time, sorting is delayed until now
    final ImmutableSortedMap<Attribute<? extends Serializable>, Constraints<? extends Serializable>> sortedDefaultAttributeConstraints =
            ImmutableSortedMap.copyOf(defaultAttributeConstraints, Ordering.explicit(orderedAttributes));
    return new ModelConfigurationImpl(sortedDefaultAttributeConstraints, constrainableAttributes, scoringFunctions);
  }
}
