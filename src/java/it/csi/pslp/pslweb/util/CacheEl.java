/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util;

/**
 * The Class CacheEl.
 */
public class CacheEl {

  /** The value. */
  private Object value;
  
  /** The time. */
  private long time;
  
  /**
   * Instantiates a new cache el.
   *
   * @param value the value
   */
  public CacheEl(Object value) {
    this.value = value;
    this.time = System.currentTimeMillis();
  }
  
  /**
   * Checks if is expired.
   *
   * @return true, if is expired
   */
  public boolean isExpired() {
    return (System.currentTimeMillis()-time)>Cache.TIMEOUT;
  }
  
  /**
   * Gets the value.
   *
   * @return the value
   */
  public Object getValue() {
    return value;
  }
}
