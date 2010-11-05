package edu.wvu.lcsee.green.model.impl;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import edu.wvu.lcsee.green.model.Constraints;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nonnull;

/**
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
    final Set<V> newValues = Sets.intersection(values, ((SetConstraints) constraintsToMerge).values);
    return new SetConstraints(newValues);
  }
}
