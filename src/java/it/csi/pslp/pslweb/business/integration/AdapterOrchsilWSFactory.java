/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.log4j.Logger;

import it.csi.pslp.pslweb.util.Constants;
import it.csi.silos.orchsil.cxfclient.GestioneSilSrv;

/**
 * A factory for creating AdapterOrchsilWS objects.
 */
public class AdapterOrchsilWSFactory {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The s. */
    private Service s = null;

    /** The endpoint. */
    private String endpoint;

    /**
     * Gets the service.
     *
     * @return the service
     * @throws Exception the exception
     */
    public GestioneSilSrv getService() throws Exception {
        if (s == null) {
            log.info("[AdapterOrchsilWSImpl::getService] Look up to SILOS.orchsil....");
            s = Service.create(getClass().getResource("/silos.orchsil.wsdl"), new QName("urn:orchsil", "GestioneSilSrvService"));
        }

        GestioneSilSrv service = s.getPort(GestioneSilSrv.class);
        BindingProvider bp = (BindingProvider) service;
        Map<String, Object> context = bp.getRequestContext();
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, getEndpoint());
        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(service);
        HTTPConduit conduit = (HTTPConduit) client.getConduit();
        HTTPClientPolicy policy = conduit.getClient();
        policy.setConnectionTimeout(60000);
        policy.setReceiveTimeout(60000);
        log.info("[AdapterOrchsilWSImpl::getService] Look up to SILOS.orchsil completed to endpoint " + getEndpoint());

        return service;
    }

    /**
     * Gets the endpoint.
     *
     * @return the endpoint
     * @throws Exception the exception
     */
    public String getEndpoint() throws Exception {
        if (endpoint == null) {
            Properties properties = new Properties();
            InputStream stream = this.getClass().getResourceAsStream("/wsEndpointUrls.properties");
            properties.load(stream);
            endpoint = properties.getProperty("orchsil.endpoint");
        }
        return endpoint;
    }

}
