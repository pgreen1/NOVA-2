package edu.wvu.lcsee.green.control;

import com.google.common.collect.ImmutableList;
import edu.wvu.lcsee.green.spi.NovaControlFactory;
import java.util.ServiceLoader;

/**
 * Loads the NovaControlFactory by using SPI.
 * @author pdgreen
 */
public class NovaBootstrap {

  public static NovaControlFactory loadFactory() {
    final ServiceLoader<NovaControlFactory> serviceLoader = ServiceLoader.load(NovaControlFactory.class);

    final ImmutableList<NovaControlFactory> serviceList = ImmutableList.copyOf(serviceLoader.iterator());

    if (serviceList.isEmpty()) {
      throw new IllegalStateException("No " + NovaControlFactory.class + " implementions registered");
    } else if (serviceList.size() >= 2) {
      throw new IllegalStateException("More than one implementation of " + NovaControlFactory.class);
    }

    return serviceList.get(0);
  }
}
