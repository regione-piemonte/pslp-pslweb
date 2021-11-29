/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import it.csi.pslp.pslweb.util.Constants;

/**
 * The Class ApiClient.
 */
public class ApiClient {

    /** The logger. */
    private static Logger logger = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The default header map. */
    protected Map<String, String> defaultHeaderMap = new HashMap<>();

    /** The http client. */
    protected CloseableHttpClient httpClient;

    /** The url. */
    private String url;

    /**
     * Instantiates a new api client.
     *
     * @param propertyName the property name
     * @param timeout the timeout
     * @throws Exception the exception
     */
    public ApiClient(String propertyName, int timeout) throws Exception {
        this.url = getUrl(propertyName);
        httpClient = buildHttpClient(timeout);
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Escape the given string to be used as URL query value.
     * 
     * @param str String
     * @return Escaped string
     */
    public String escapeString(String str) {
        try {
            return URLEncoder.encode(str, "utf8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * Invoke API by sending HTTP request with the given options.
     *
     * @param path         The sub-path of the HTTP URL
     * @param method       The request method, one of "GET", "POST", "PUT", "HEAD"
     *                     and "DELETE"
     * @param queryParams  The query parameters
     * @param body         The request body object as string
     * @param headerParams The header parameters
     * @param accept       The request's Accept header
     * @param contentType  The request's Content-Type header
     * @return The response body in type of string
     * @throws ApiException API exception
     */
    public ApiResponse invokeAPI(String path, String method, List<Pair> queryParams, String body, Map<String, String> headerParams, String accept,
            String contentType) throws ApiException {

        logger.info("[ApiClient::invokeAPI] External call started " + method + " " + path + "");
        it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(Constants.COMPONENT_NAME);
        // inizio misurazione
        watcher.start();

        CloseableHttpResponse response = null;

        try {

            URIBuilder target = new URIBuilder(this.url + path);

            if (queryParams != null) {
                for (Pair queryParam : queryParams) {
                    if (queryParam.getValue() != null) {
                        target.addParameter(queryParam.getName(), queryParam.getValue());
                    }
                }
            }

            URI uri = target.build();
            StringEntity entity = null;
            if (body != null)
                entity = new StringEntity(body, "UTF-8");

            HttpRequestBase request = null;

            if ("GET".equalsIgnoreCase(method)) {
                request = new HttpGet(uri);
            } else if ("POST".equalsIgnoreCase(method)) {
                HttpEntityEnclosingRequestBase httpEntityRequest = new HttpPost(uri);
                if (entity != null)
                    httpEntityRequest.setEntity(entity);
                request = httpEntityRequest;
            } else if ("PUT".equalsIgnoreCase(method)) {
                HttpEntityEnclosingRequestBase httpEntityRequest = new HttpPut(uri);
                if (entity != null)
                    httpEntityRequest.setEntity(entity);
                request = httpEntityRequest;
            } else if ("DELETE".equalsIgnoreCase(method)) {
                request = new HttpDelete(uri);
            } else if ("PATCH".equalsIgnoreCase(method)) {
                HttpEntityEnclosingRequestBase httpEntityRequest = new HttpPatch(uri);
                if (entity != null)
                    httpEntityRequest.setEntity(entity);
                request = httpEntityRequest;
            } else if ("HEAD".equalsIgnoreCase(method)) {
                request = new HttpHead(uri);
            } else {
                logger.error("[ApiClient::invokeAPI] Richiesto metodo HTTP non esistente:" + method);
                throw new ApiException(500, "unknown method type " + method);
            }

            request.addHeader(HttpHeaders.ACCEPT, accept);
            request.addHeader(HttpHeaders.CONTENT_TYPE, contentType);

            for (Entry<String, String> entry : headerParams.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    request.addHeader(entry.getKey(), value);
                }
            }

            for (Entry<String, String> entry : defaultHeaderMap.entrySet()) {
                String key = entry.getKey();
                if (!headerParams.containsKey(key)) {
                    String value = entry.getValue();
                    if (value != null) {
                        request.addHeader(key, value);
                    }
                }
            }

            response = httpClient.execute(request);
            watcher.dumpElapsed("ApiClient", "httpExecute()", "invocazione http (no token)", "(valore input omesso)");

            int statusCode = response.getStatusLine().getStatusCode();

            logger.info("[ApiClient::invokeAPI] Status Code ritornato " + statusCode);

            String entityResponse = null;

            if (response.getEntity() != null) {
                entityResponse = EntityUtils.toString(response.getEntity());
            }
            Map<String, List<String>> responseHeaders = buildResponseHeaders(response);

            if (statusCode == HttpStatus.SC_NO_CONTENT) {
                return new ApiResponse(statusCode, responseHeaders);
            } else if (statusCode >= 200 && statusCode < 300) {
                if (entityResponse == null)
                    return new ApiResponse(statusCode, responseHeaders);
                else
                    return new ApiResponse(statusCode, responseHeaders, entityResponse);
            } else {
                logger.warn("[ApiClient::invokeAPI] Servizio ritorna status code:[" + statusCode + "]");
                String message = "error, service returned status code " + statusCode;
                throw new ApiException(statusCode, message, buildResponseHeaders(response), entityResponse);
            }
        } catch (ClientProtocolException e1) {
            logger.error("[ApiClient::invokeAPI] Eccezione:", e1);
            watcher.dumpElapsed("ApiClient", "httpExecute()", "ClientProtocolException", "(valore input omesso)");
            throw new ApiException(500, "Exception " + e1.getMessage());
        } catch (IOException e1) {
            logger.error("[ApiClient::invokeAPI] Eccezione:", e1);
            watcher.dumpElapsed("ApiClient", "httpExecute()", "IOException", "(valore input omesso)");
            throw new ApiException(500, "Exception " + e1.getMessage());
        } catch (URISyntaxException e1) {
            logger.error("[ApiClient::invokeAPI] Eccezione:", e1);
            watcher.dumpElapsed("ApiClient", "httpExecute()", "URISyntaxException", "(valore input omesso)");
            throw new ApiException(500, "Exception " + e1.getMessage());
        } catch (ApiException e1) {
            watcher.dumpElapsed("ApiClient", "httpExecute()", "ApiException", "(valore input omesso)");
            throw e1;
        } finally {
            logger.info("[ApiClient::invokeAPI] External call End " + method + " " + path + "");
            watcher.stop();
            try {
                if (response != null)
                    response.close();
            } catch (Exception e) {
                // it's not critical, since the response object is local in method
                // invokeAPI;
                // that's fine, just continue
            }
        }
    }

    /**
     * Build the Client used to make HTTP requests.
     *
     * @param timeout the timeout
     * @return Client
     */
    protected CloseableHttpClient buildHttpClient(int timeout) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(50);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(30 * timeout).build();
        return HttpClients.custom().useSystemProperties().setConnectionManager(cm).setDefaultRequestConfig(config).build();
    }

    /**
     * Builds the response headers.
     *
     * @param response the response
     * @return the map
     */
    protected Map<String, List<String>> buildResponseHeaders(CloseableHttpResponse response) {
        Map<String, List<String>> responseHeaders = new HashMap<String, List<String>>();
        for (Header entry : response.getAllHeaders()) {
            HeaderElement[] values = entry.getElements();
            List<String> headers = new ArrayList<>();
            for (HeaderElement o : values) {
                headers.add(o.getValue());
            }
            responseHeaders.put(entry.getName(), headers);
        }
        return responseHeaders;
    }

    /**
     * Gets the url.
     *
     * @param propertyName the property name
     * @return the url
     * @throws Exception the exception
     */
    public String getUrl(String propertyName) throws Exception {
        if (url == null) {
            Properties properties = new Properties();
            InputStream stream = this.getClass().getResourceAsStream("/wsEndpointUrls.properties");
            properties.load(stream);
            url = properties.getProperty(propertyName);
        }
        return url;
    }

}
