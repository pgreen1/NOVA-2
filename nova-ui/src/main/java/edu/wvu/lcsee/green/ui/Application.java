package edu.wvu.lcsee.green.ui;

import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.Treatment;
import edu.wvu.lcsee.green.model.impl.SetConstraints;
import edu.wvu.lcsee.green.spi.ProjectGenerator;
import java.io.Serializable;

/**
 *
 * @author pdgreen
 */
public class Application {

  public static void main(final String[] args) {

    final ProjectGenerator projectGenerator = NovaControl.INSTANCE.getProjectGenerator();

    final Scenario scenario = loadScenario();

    System.out.println(projectGenerator.generateProject(scenario));


    System.out.println(projectGenerator.generateManyProjects(scenario, 20));
  }

  static enum TestAttributes implements Attribute<Number> {

    ATTRIBUTE1, ATTRIBUTE2, ATTRIBUTE3;

    @Override
    public String getName() {
      return toString();
    }

    @Override
    public String getDescription() {
      return toString();
    }

    @Override
    public Class<Number> getValueType() {
      return Number.class;
    }
  }

  static Scenario loadScenario() {
    return new Scenario() {

      @Override
      public ImmutableSet<Attribute<? extends Serializable>> getAllAttributes() {
        return ImmutableSet.<Attribute<? extends Serializable>>copyOf(TestAttributes.values());
      }

      @Override
      public ImmutableSet<Attribute<? extends Serializable>> getModifiableAttributes() {
        return getAllAttributes();
      }

      @Override
      public <V extends Serializable> Constraints<V> getConstraintsFor(Attribute<V> attribute) {
        return (Constraints<V>) new SetConstraints<Long>(1L, 2L, 3L, 4L, 5L);
      }

      @Override
      public Scenario applyTreatment(Treatment treatment) {
        throw new UnsupportedOperationException("Not supported yet.");
      }
    };
  }
}
