package edu.wvu.lcsee.green.search;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public class Treatment {

  @Nonnull
  private final ImmutableMultimap<Attribute, Number> features;

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Treatment other = (Treatment) obj;
    if (this.features != other.features && (this.features == null || !this.features.equals(other.features))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 53 * hash + (this.features != null ? this.features.hashCode() : 0);
    return hash;
  }

  public Treatment(@Nonnull final ImmutableSet<Feature> features) {
    final Multimap<Attribute, Number> mutableFeatures = HashMultimap.create();
    for (final Feature feature : features) {
      mutableFeatures.putAll(feature.getAttribute(), feature.getValues());
    }
    this.features = ImmutableMultimap.copyOf(mutableFeatures);
  }

  @Nonnull
  public ImmutableSet<Feature> getCoalescedFeatures() {
    final Set<Feature> coalescedFeatures = Sets.newHashSet();
    for (Map.Entry<Attribute, Collection<Number>> featureToBe : features.asMap().entrySet()) {
      coalescedFeatures.add(new Feature(featureToBe.getKey(), ImmutableSet.copyOf(featureToBe.getValue())));
    }
    return ImmutableSet.copyOf(coalescedFeatures);
  }

  @Nonnull
  public ImmutableSet<Feature> getSplitFeatures() {
    final Set<Feature> coalescedFeatures = Sets.newHashSet();
    for (Map.Entry<Attribute, Number> featureToBe : features.entries()) {
      coalescedFeatures.add(new Feature(featureToBe.getKey(), ImmutableSet.of(featureToBe.getValue())));
    }
    return ImmutableSet.copyOf(coalescedFeatures);
  }
  //TODO overRide toString
}
