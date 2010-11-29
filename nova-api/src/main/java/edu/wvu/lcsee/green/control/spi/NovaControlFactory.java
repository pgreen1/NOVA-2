package edu.wvu.lcsee.green.control.spi;

import edu.wvu.lcsee.green.control.NovaControl;

/**
 * NovaControlFactory is used to load a NovaControl.  Implementors of this should register with SPI.
 * Instances of NovaControlFactory can be loaded with {@link NovaBootstrap}.
 *
 * @author pdgreen
 */
public interface NovaControlFactory {

  /**
   * Instantiates a new instance of NovaControl.
   * @return a new instance of NovaControl.
   */
  NovaControl newInstance();
}
