/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslweb.business.be.PingApi;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.RestHelper;

/**
 * The Class PingApiServiceImpl.
 */
@Component("pingApi")
public class PingApiServiceImpl implements PingApi {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The rest helper. */
    @Autowired
    private RestHelper restHelper;

    /**
     * Ping.
     *
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    public Response ping(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        String cf = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
        String ip = restHelper.getIncomingIPAddress(httpRequest);
        log.info("[PingApiServiceImpl::ping] Ip=" + ip + ", User:" + cf);
        return Response.ok("\"PING OK! (Ip=" + ip + ", User:" + cf + ")\"").header("someheader", "" + System.currentTimeMillis()).build();
    }

}
