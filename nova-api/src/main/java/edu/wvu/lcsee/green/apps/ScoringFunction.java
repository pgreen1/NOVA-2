package edu.wvu.lcsee.green.apps;

import com.google.common.base.Preconditions;
import edu.wvu.lcsee.green.model.Project;
import java.util.Comparator;
import javax.annotation.Nonnull;

/**
 *
 * @author pdgreen
 */
public interface ScoringFunction extends Comparator<Object> {

  ScoringFunctionId getId();

  Number score(@Nonnull Project project);

  public static class ScoringFunctionId {

    private final String idAsString;

    public ScoringFunctionId(@Nonnull final String idAsString) {
      this.idAsString = Preconditions.checkNotNull(idAsString);
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      final ScoringFunctionId other = (ScoringFunctionId) obj;
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
