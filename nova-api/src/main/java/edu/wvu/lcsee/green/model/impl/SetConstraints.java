package edu.wvu.lcsee.green.model.impl;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.ConstraintsEditor;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nonnull;

/**
 * Implementation of {@link Constraints} when the values are stored as a Set.
 *
 * @author pdgreen
 */
public class SetConstraints<V extends Serializable> implements Constraints<V> {

  private static final Random random = new Random();
  private final ImmutableSet<V> values;
  private final List<V> valuesAsList;

  public SetConstraints(final V... values) {
    this(new HashSet<V>(Arrays.asList(values)));
  }

  public SetConstraints(final @Nonnull Set<V> values) {
    this.values = ImmutableSet.copyOf(values);
    this.valuesAsList = Lists.newArrayList(this.values);
  }

  @Override
  public V generateValue() {
    return valuesAsList.get(random.nextInt(valuesAsList.size()));
  }

  @Override
  public Constraints<V> mergeConstraints(@Nonnull final Constraints<V> constraintsToMerge) {
    final ConstraintsEditor<V> thisEditor = getEditor();
    final ConstraintsEditor<V> thatEditor = constraintsToMerge.getEditor();

    final Set<ConstraintsEditor.DiscreteValue<V>> discreteValues = Sets.intersection(thisEditor.getAllValues(), thatEditor.getAllValues());

    //FIXME: used rawtype to get around generics
    final ConstraintsEditor<V> newEditor = new SetConstraintsEditor(discreteValues);

    return newEditor.generateConstraints();
  }

  @Override
  public boolean isFullyConstrained() {
    return values.size() == 1;
  }

  @Override
  public ConstraintsEditor<V> getEditor() {
    return SetConstraintsEditor.newInstanceWithValues(values);
  }
}
