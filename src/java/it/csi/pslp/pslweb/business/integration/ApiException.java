/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

import java.util.List;
import java.util.Map;

/**
 * The Class ApiException.
 */
public class ApiException extends Exception {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8073039391445682811L;
    
    /** The code. */
    private int code = 0;
    
    /** The response headers. */
    private Map<String, List<String>> responseHeaders = null;
    
    /** The response body. */
    private String responseBody = null;

    /**
     * Instantiates a new api exception.
     */
    public ApiException() {
    }

    /**
     * Instantiates a new api exception.
     *
     * @param throwable the throwable
     */
    public ApiException(Throwable throwable) {
        super(throwable);
    }

    /**
     * Instantiates a new api exception.
     *
     * @param message the message
     * @param throwable the throwable
     */
    public ApiException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instantiates a new api exception.
     *
     * @param message the message
     */
    public ApiException(String message) {
        super(message);
    }

    /**
     * Instantiates a new api exception.
     *
     * @param message the message
     * @param throwable the throwable
     * @param code the code
     * @param responseHeaders the response headers
     * @param responseBody the response body
     */
    public ApiException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, throwable);
        this.code = code;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
    }

    /**
     * Instantiates a new api exception.
     *
     * @param message the message
     * @param code the code
     * @param responseHeaders the response headers
     * @param responseBody the response body
     */
    public ApiException(String message, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        this(message, (Throwable) null, code, responseHeaders, responseBody);
    }

    /**
     * Instantiates a new api exception.
     *
     * @param message the message
     * @param throwable the throwable
     * @param code the code
     * @param responseHeaders the response headers
     */
    public ApiException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders) {
        this(message, throwable, code, responseHeaders, null);
    }

    /**
     * Instantiates a new api exception.
     *
     * @param code the code
     * @param responseHeaders the response headers
     * @param responseBody the response body
     */
    public ApiException(int code, Map<String, List<String>> responseHeaders, String responseBody) {
        this((String) null, (Throwable) null, code, responseHeaders, responseBody);
    }

    /**
     * Instantiates a new api exception.
     *
     * @param code the code
     * @param message the message
     */
    public ApiException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Instantiates a new api exception.
     *
     * @param code the code
     * @param message the message
     * @param responseHeaders the response headers
     * @param responseBody the response body
     */
    public ApiException(int code, String message, Map<String, List<String>> responseHeaders, String responseBody) {
        this(code, message);
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
    }

    /**
     * Get the HTTP status code.
     *
     * @return HTTP status code
     */
    public int getCode() {
        return code;
    }

    /**
     * Get the HTTP response headers.
     *
     * @return A map of list of string
     */
    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }

    /**
     * Get the HTTP response body.
     *
     * @return Response body in the form of string
     */
    public String getResponseBody() {
        return responseBody;
    }

}
