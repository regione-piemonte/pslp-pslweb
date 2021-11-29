/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

/**
 * The Class Pair.
 */
public class Pair {
  
  /** The name. */
  private String name = "";

  /** The value. */
  private String value = "";

  /**
   * Instantiates a new pair.
   *
   * @param name the name
   * @param value the value
   */
  public Pair(String name, String value) {
    setName(name);
    setValue(value);
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  private void setName(String name) {
    if (!isValidString(name)) return;

    this.name = name;
  }

  /**
   * Sets the value.
   *
   * @param value the new value
   */
  private void setValue(String value) {
    if (!isValidString(value)) return;

    this.value = value;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  public String getValue() {
    return this.value;
  }

  /**
   * Checks if is valid string.
   *
   * @param arg the arg
   * @return true, if is valid string
   */
  private boolean isValidString(String arg) {
    if (arg == null) return false;
    if (arg.trim().isEmpty()) return false;

    return true;
  }

}
