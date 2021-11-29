/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import it.csi.pslp.pslweb.business.SpringApplicationContextHelper;

/**
 * The Class SpringInjectorInterceptor.
 */
@Provider
public class SpringInjectorInterceptor implements PreProcessInterceptor {

    /**
     * Pre process.
     *
     * @param request the request
     * @param method the method
     * @return the server response
     * @throws Failure the failure
     * @throws WebApplicationException the web application exception
     */
    @Override
    public ServerResponse preProcess(HttpRequest request, ResourceMethod method) throws Failure, WebApplicationException {
    	
		SpringApplicationContextHelper.injectSpringBeansIntoRestEasyService(method.getResourceClass().getName());
        
        return null;
    }
}