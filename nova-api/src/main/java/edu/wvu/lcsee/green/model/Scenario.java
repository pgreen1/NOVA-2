package edu.wvu.lcsee.green.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 * A Scenario is composed of constrained {@link Attribute}s where only some of the 
 * Attributes can be modified by applying a {@link Treatment}s.
 *
 * @author pdgreen
 * @see Attribute
 * @see Constraints
 * @see Treatment
 */
public interface Scenario {

  /**
   * Returns all {@link Attribute}s associated with this Scenario.
   * @return all attributes associated with this Scenario
   */
  @Nonnull
  ImmutableSet<Attribute<? extends Serializable>> getAllAttributes();

  /**
   * Returns modifiable {@link Attribute}s associated with this Scenario.
   * @return all modifiable attributes associated with this Scenario
   */
  @Nonnull
  ImmutableSet<Attribute<? extends Serializable>> getModifiableAttributes();

  /**
   * Returns the {@link Constraints} for the specified {@link Attribute}.
   * @param <V> Value type of the Attribute and resulting Constraints
   * @param attribute the Attribute for which the Constraints is desired
   * @return the Constraints of the Attribute
   * @throws IllegalArgumentException when an Attribute is not contained in this Scenario
   */
  @Nonnull
  <V extends Serializable> Constraints<V> getConstraintsFor(@Nonnull Attribute<V> attribute);

  /**
   * Creates a new Scenario based on the original Scenario but with the {@link Treatment} applied.
   * 
   * @param treatment the Treatment to apply
   * @return a new Scenario based on the original Scenario but with the Treatment applied
   * @throws IllegalArgumentException when an Attribute in the Treatment is not a modifiable attribute
   */
  @Nonnull
  Scenario applyTreatment(@Nonnull Treatment treatment);

  /**
   * Returns the Scenario as an ImmutableMap of {@link Attribute} to {@link Constraints}.
   * Since the Map is immutable, no distinction is made between modifiable and unmodifiable attributes.
   * @return the Scenario as an ImmutableMap of Attribute to Constraints.
   */
  @Nonnull
  ImmutableMap<Attribute<? extends Serializable>, Constraints<? extends Serializable>> asMap();
}
