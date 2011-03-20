package edu.wvu.lcsee.green.model;

import javax.annotation.Nonnull;
import com.google.common.collect.Sets;
import java.util.Set;
import com.google.common.collect.Maps;
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
          @Nonnull final Constraints<V> defaultConstraints,
          @Nonnull final Attribute... dependents) {
    checkNotNull(attribute);
    checkNotNull(defaultConstraints);
    checkNotNull(dependents);
    checkState(!defaultAttributeConstraints.containsKey(attribute), "attribute already added: " + attribute.getName());
    defaultAttributeConstraints.put(attribute, defaultConstraints);
    return this;
  }

  public <V extends Serializable> ModelConfigurationBuilder addConstrainableAttribute(
          @Nonnull final Attribute<V> attribute,
          @Nonnull final Constraints<V> defaultConstraints,
          @Nonnull final Attribute... dependents) {
    addUnconstrainableAttribute(attribute, defaultConstraints, dependents);
    constrainableAttributes.add(attribute);
    return this;
  }

  public ModelConfigurationBuilder addScoringFunction(@Nonnull final ScoringFunction scoringFunction) {
    checkNotNull(scoringFunction);
    scoringFunctions.add(scoringFunction);
    return this;
  }

  public ModelConfiguration build() {
    return new ModelConfigurationImpl(defaultAttributeConstraints, constrainableAttributes, scoringFunctions);
  }
}
