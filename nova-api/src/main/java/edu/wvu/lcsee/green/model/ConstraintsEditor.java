package edu.wvu.lcsee.green.model;

import java.io.Serializable;
import java.util.Set;
import javax.annotation.Nonnull;

/**
 * {@link Constraints} are immutable.  In situations when a mutable version is required,
 * ConstraintsEditor should be used.
 * ConstraintsEditor allows for values to added and removed.
 * @author pdgreen
 */
public interface ConstraintsEditor<V extends Serializable> {

  /**
   * Generates Constraints from the editor.
   * @return Constraints with values of ConstraintEditor
   * @throws IllegalStateException when there is no current values associated with the ConstraintsEditor.
   */
  @Nonnull
  Constraints<V> generateConstraints();

  /**
   * Returns discrete values for any extremes.  If there are not values that are "extreme", then this should return the same as getAllValues().
   * @return the set of values which would be considered the extremes if the values of some kind of ordering.
   */
  @Nonnull
  Set<DiscreteValue<V>> getExtremesValues();

  /**
   * Return all values.
   * @return a set of all values currently in the ConstraintsEditor
   */
  @Nonnull
  Set<DiscreteValue<V>> getAllValues();

  /**
   * Returns whether or not there is only one single value associated with this ConstraintsEditor.
   * @return whether or not there is only one single value associated
   */
  @Nonnull
  boolean isSingletonValue();

  /**
   * Add a new value.
   * @param value
   * @return whether or not the value was added
   */
  @Nonnull
  boolean addValue(@Nonnull DiscreteValue<V> value);

  /**
   * Remove a value.
   * @param value
   * @return whether or not anything was removed.
   */
  @Nonnull
  boolean removeValue(@Nonnull DiscreteValue<V> value);

  /**
   * This represents a disscrete value from the constraint.  The reason values can't be returned is because there could be an infinite amount of the constraint was over a range of double.
   * @param <V>
   */
  public interface DiscreteValue<V extends Serializable> {
  }
}
