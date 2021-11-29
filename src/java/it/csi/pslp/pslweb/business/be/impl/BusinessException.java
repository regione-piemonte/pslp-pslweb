/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

/**
 * The Class BusinessException.
 */
public class BusinessException extends Exception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The code. */
  private String code;
  
  /** The args. */
  private Object[] args;

  /**
   * Instantiates a new business exception.
   *
   * @param code the code
   */
  public BusinessException(String code) {
    this.code = code;
  }

  /**
   * Instantiates a new business exception.
   *
   * @param code the code
   * @param args the args
   */
  public BusinessException(String code, Object... args) {
    this.code = code;
    this.args = args;
  }

  /**
   * Gets the code.
   *
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * Sets the code.
   *
   * @param code the new code
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * Gets the args.
   *
   * @return the args
   */
  public Object[] getArgs() {
    return args;
  }

  /**
   * Sets the args.
   *
   * @param args the new args
   */
  public void setArgs(Object[] args) {
    this.args = args;
  }

}
