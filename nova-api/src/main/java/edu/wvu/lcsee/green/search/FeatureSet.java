package edu.wvu.lcsee.green.search;

import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public class FeatureSet {

  @Nonnull
  private final ImmutableSet<Feature> features;

  public FeatureSet(@Nonnull final Collection<Feature> features) {
    this.features = ImmutableSet.copyOf(features);
  }

  @Nonnull
  public ImmutableSet<Feature> getAsImmutableSet() {
    return features;
  }


}
