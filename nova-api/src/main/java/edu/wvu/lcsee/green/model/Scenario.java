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
public interface Scenario extends AttributeConstrainable{


  /**
   * Returns modifiable {@link Attribute}s associated with this Scenario.
   * @return all modifiable attributes associated with this Scenario
   */
  @Nonnull
  ImmutableSet<Attribute<? extends Serializable>> getModifiableAttributes();


  /**
   * Creates a new Scenario based on the original Scenario but with the {@link Treatment} applied.
   * 
   * @param treatment the Treatment to apply
   * @return a new Scenario based on the original Scenario but with the Treatment applied
   * @throws IllegalArgumentException when an Attribute in the Treatment is not a modifiable attribute
   */
  @Nonnull
  Scenario applyTreatment(@Nonnull Treatment treatment);

}
