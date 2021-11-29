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
import it.csi.silpsv.silpsval.cxfclient.EstrazioneAnagraficaLavoratore;

/**
 * A factory for creating AdapterSilpsvalWS objects.
 */
public class AdapterSilpsvalWSFactory {

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
    public EstrazioneAnagraficaLavoratore getService() throws Exception {
        if (s == null) {
            log.info("[AdapterSilpsvalWSFactory::getService] Look up to SILPSV.silpsval....");
            s = Service.create(getClass().getResource("/silpsv.silpsval.wsdl"), new QName("urn:silpsval", "EstrazioneAnagraficaLavoratoreService"));
        }
        EstrazioneAnagraficaLavoratore service = s.getPort(EstrazioneAnagraficaLavoratore.class);
        BindingProvider bp = (BindingProvider) service;
        Map<String, Object> context = bp.getRequestContext();
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, getEndpoint());
        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(service);
        HTTPConduit conduit = (HTTPConduit) client.getConduit();
        HTTPClientPolicy policy = conduit.getClient();
        policy.setConnectionTimeout(30000);
        policy.setReceiveTimeout(30000);
        log.info("[AdapterSilpsvalWSFactory::getService] Look up to SILPSV.silpsval completed to endpoint " + getEndpoint());

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
            endpoint = properties.getProperty("silpsval.endpoint");
        }
        return endpoint;
    }

}
