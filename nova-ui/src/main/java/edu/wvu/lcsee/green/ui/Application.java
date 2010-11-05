package edu.wvu.lcsee.green.ui;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import edu.wvu.lcsee.green.model.Attribute;
import edu.wvu.lcsee.green.model.Constraints;
import edu.wvu.lcsee.green.model.Scenario;
import edu.wvu.lcsee.green.model.Treatment;
import edu.wvu.lcsee.green.model.impl.SetConstraints;
import edu.wvu.lcsee.green.spi.ProjectGenerator;
import java.io.Serializable;
import java.util.ServiceLoader;

/**
 *
 * @author pdgreen
 */
public class Application {

  public static void main(final String[] args) {
    final ServiceLoader<ProjectGenerator> projectGeneratorLoader = ServiceLoader.load(ProjectGenerator.class);

    final ImmutableList<ProjectGenerator> projectGenerators = ImmutableList.copyOf(projectGeneratorLoader.iterator());

    final ProjectGenerator projectGenerator;
    if (projectGenerators.isEmpty()) {
      throw new IllegalStateException("No " + ProjectGenerator.class + " implementions registered");
    } else if (projectGenerators.size() > 1) {
      throw new IllegalStateException(
              "Too many " + ProjectGenerator.class + " implementions registered: " + projectGenerators);
    } else {
      projectGenerator = projectGenerators.get(0);
    }

    final Scenario scenario = loadScenario();

    System.out.println(projectGenerator.generate(scenario));


    System.out.println(projectGenerator.generateMany(scenario,20));
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
      public ImmutableMap<Attribute<? extends Serializable>, Constraints<? extends Serializable>> getConstraintsAsMap() {
        throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public Scenario applyTreatment(Treatment treatment) {
        throw new UnsupportedOperationException("Not supported yet.");
      }
    };
  }
}
