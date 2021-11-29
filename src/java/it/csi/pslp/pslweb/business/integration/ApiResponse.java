/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

import java.util.List;
import java.util.Map;

/**
 * API response returned by API call.
 *
 */
public class ApiResponse {
  
  /** The status code. */
  private final int statusCode;
  
  /** The headers. */
  private final Map<String, List<String>> headers;
  
  /** The data. */
  private final String data;

  /**
   * Instantiates a new api response.
   *
   * @param statusCode The status code of HTTP response
   * @param headers The headers of HTTP response
   */
  public ApiResponse(int statusCode, Map<String, List<String>> headers) {
      this(statusCode, headers, null);
  }

  /**
   * Instantiates a new api response.
   *
   * @param statusCode The status code of HTTP response
   * @param headers The headers of HTTP response
   * @param data The object deserialized from response bod
   */
  public ApiResponse(int statusCode, Map<String, List<String>> headers, String data) {
      this.statusCode = statusCode;
      this.headers = headers;
      this.data = data;
  }

  /**
   * Gets the status code.
   *
   * @return the status code
   */
  public int getStatusCode() {
      return statusCode;
  }

  /**
   * Gets the headers.
   *
   * @return the headers
   */
  public Map<String, List<String>> getHeaders() {
      return headers;
  }

  /**
   * Gets the data.
   *
   * @return the data
   */
  public String getData() {
      return data;
  }

}
