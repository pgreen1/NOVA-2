package edu.wvu.lcsee.green.ui;

import com.google.common.collect.ImmutableList;
import java.util.ServiceLoader;

/**
 *
 * @author pdgreen
 */
public class ServiceLoaderHelper {

  private ServiceLoaderHelper() {
  }

  public static <T> T loadSingleService(Class<T> serviceClass) {
    final ServiceLoader<T> serviceLoader = ServiceLoader.load(serviceClass);

    final ImmutableList<T> serviceList = ImmutableList.copyOf(serviceLoader.iterator());

    final T service;
    if (serviceList.isEmpty()) {
      throw new IllegalStateException("No " + serviceClass + " implementions registered");
    } else if (serviceList.size() > 1) {
      throw new IllegalStateException(
              "Too many " + serviceClass + " implementions registered: " + serviceList);
    } else {
      service = serviceList.get(0);
    }

    return service;
  }
}
