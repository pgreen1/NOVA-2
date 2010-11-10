package edu.wvu.lcsee.green.control.spi;

import edu.wvu.lcsee.green.control.NovaControl;

/**
 *
 * @author pdgreen
 */
public interface NovaControlFactory {

  NovaControl newInstance();
}
