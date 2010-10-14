package edu.wvu.lcsee.green.apps;

import edu.wvu.lcsee.green.search.State;
import java.util.Comparator;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface ScoringMethod extends Comparator<Object> {

  ScoringMethodId getId();

  Number score(@Nonnull State state);

  static class ScoringMethodId {

    private final String idAsString;

    public ScoringMethodId(String idAsString) {
      this.idAsString = idAsString;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      final ScoringMethodId other = (ScoringMethodId) obj;
      if ((this.idAsString == null) ? (other.idAsString != null) : !this.idAsString.equals(other.idAsString)) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int hash = 3;
      hash = 17 * hash + (this.idAsString != null ? this.idAsString.hashCode() : 0);
      return hash;
    }

    @Override
    public String toString() {
      return idAsString;
    }
  }
}
