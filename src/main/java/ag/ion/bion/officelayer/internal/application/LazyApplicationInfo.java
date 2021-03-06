/**
 * ************************************************************************** ubion.ORS - The Open
 * Report Suite * * ------------------------------------------------------------------------ * *
 * Subproject: NOA (Nice Office Access) * * * The Contents of this file are made available subject
 * to * the terms of GNU Lesser General Public License Version 2.1. * * GNU Lesser General Public
 * License Version 2.1 * ======================================================================== *
 * Copyright 2003-2005 by IOn AG * * This library is free software; you can redistribute it and/or *
 * modify it under the terms of the GNU Lesser General Public * License version 2.1, as published by
 * the Free Software Foundation. * * This library is distributed in the hope that it will be useful,
 * * but WITHOUT ANY WARRANTY; without even the implied warranty of * MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU * Lesser General Public License for more details. * * You
 * should have received a copy of the GNU Lesser General Public * License along with this library;
 * if not, write to the Free Software * Foundation, Inc., 59 Temple Place, Suite 330, Boston, * MA
 * 02111-1307 USA * * Contact us: * http://www.ion.ag * info@ag.ion.ag * *
 * **************************************************************************
 */

/*
 * Last changes made by $Author: markus $, $Date: 2008-11-18 11:22:42 +0100 (Di, 18 Nov 2008) $
 */
package ag.ion.bion.officelayer.internal.application;

import ag.ion.bion.officelayer.application.IApplicationProperties;
import ag.ion.bion.officelayer.application.ILazyApplicationInfo;

/**
 * Information provider of an office application.
 *
 * @author Andreas Bröker
 * @version $Revision: 11681 $
 * @deprecated
 */
public class LazyApplicationInfo implements ILazyApplicationInfo {

  private static final String PRODUCT_NAME = "OpenOffice.org";
  private IApplicationProperties applicationProperties = null;
  private String home = null;
  private int majorVersion = -1;
  private int minorVersion = -1;
  private int updateVersion = -1;

  //----------------------------------------------------------------------------

  /**
   * Constructs new ApplicationInfo.
   *
   * @param home home of the office application to be used
   * @param applicationProperties application properties to be used
   * @throws IllegalArgumentException if the submitted home path is not valid
   * @author Andreas Bröker
   * @deprecated
   */
  public LazyApplicationInfo(String home, IApplicationProperties applicationProperties)
      throws IllegalArgumentException {
    if (home == null) {
      throw new IllegalArgumentException("The submitted home path is not valid.");
    }
    this.home = home;
    this.applicationProperties = applicationProperties;
    if (applicationProperties != null) {
      initVersion(applicationProperties);
    }
  }

  //----------------------------------------------------------------------------

  /**
   * Sets version of the office application.
   *
   * @param major major version to be used
   * @param minor minor version to be used
   * @param update update version to be used
   * @author Andreas Bröker
   * @deprecated
   */
  public void setVersion(int major, int minor, int update) {
    majorVersion = major;
    minorVersion = minor;
    updateVersion = update;
  }

  //----------------------------------------------------------------------------

  /**
   * Returns home of the office application.
   *
   * @return home of the office application
   * @author Andreas Bröker
   * @deprecated
   */
  public String getHome() {
    return home;
  }

  //----------------------------------------------------------------------------

  /**
   * Returns properties of the office application. The properties will be invetigated from the
   * bootstrap.ini file. Returns null if the properties are not available.
   *
   * @return properties of the office application or null if the properties are not available
   * @author Andreas Bröker
   * @deprecated
   */
  public IApplicationProperties getProperties() {
    return applicationProperties;
  }

  //----------------------------------------------------------------------------

  /**
   * Returns major version of the office application. Returns
   * <code>-1</code> if the major version is not available.
   *
   * @return major version of the office application or <code>-1</code> if the major version is not
   * available
   * @author Andreas Bröker
   * @deprecated
   */
  public int getMajorVersion() {
    return majorVersion;
  }

  //----------------------------------------------------------------------------

  /**
   * Returns minor version of the office application. Returns
   * <code>-1</code> if the minor version is not available.
   *
   * @return minor version of the office application or <code>-1</code> if the minor version is not
   * available
   * @author Andreas Bröker
   * @deprecated
   */
  public int getMinorVersion() {
    return minorVersion;
  }

  //----------------------------------------------------------------------------

  /**
   * Returns update version of the office application. Returns
   * <code>-1</code> if the update version is not available.
   *
   * @return update version of the office application or <code>-1</code> if the update version is
   * not available
   * @author Andreas Bröker
   * @deprecated
   */
  public int getUpdateVersion() {
    return updateVersion;
  }

  //----------------------------------------------------------------------------

  /**
   * Inits version number.
   *
   * @param applicationProperties application properties to be used
   * @author Andreas Bröker
   * @deprecated
   */
  private void initVersion(IApplicationProperties applicationProperties) {
    String productKey = applicationProperties
        .getPropertyValue(IApplicationProperties.PRODUCT_MAJOR_VERSION_PROPERTY);
    if (productKey != null) {
      String version = productKey.substring(PRODUCT_NAME.length()).trim();
      String[] versionParts = version.split("\\.");
      int majorVersion = 0;
      int minorVersion = 0;
      int updateVersion = 0;
      //only major version is important, minor amd update version are included anyway.. 350, 400 .. cannot find another consistent oo/lo version property
      try {
        majorVersion = Integer.parseInt(productKey);
      } catch (Throwable throwable) {
        // do not consume
        throwable.printStackTrace();
      }
      setVersion(majorVersion, minorVersion, updateVersion);
    }
  }
  //----------------------------------------------------------------------------
}