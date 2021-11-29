/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import it.csi.pslp.pslweb.business.be.impl.CalendarioApiServiceImpl;
import it.csi.pslp.pslweb.business.be.impl.CollocamentoMiratoServiceImpl;
import it.csi.pslp.pslweb.business.be.impl.ConfigurazioniApiServiceImpl;
import it.csi.pslp.pslweb.business.be.impl.CurrentUserApiServiceImpl;
import it.csi.pslp.pslweb.business.be.impl.DecodificheApiServiceImpl;
import it.csi.pslp.pslweb.business.be.impl.DidApiServiceImpl;
import it.csi.pslp.pslweb.business.be.impl.DocumentiApiServiceImpl;
import it.csi.pslp.pslweb.business.be.impl.MenuApiServiceImpl;
import it.csi.pslp.pslweb.business.be.impl.MessaggiApiServiceImpl;
import it.csi.pslp.pslweb.business.be.impl.PingApiServiceImpl;
import it.csi.pslp.pslweb.business.be.impl.PrivacyApiServiceImpl;
import it.csi.pslp.pslweb.business.be.impl.StampeApiServiceImpl;
import it.csi.pslp.pslweb.business.be.impl.UfficiApiServiceImpl;
import it.csi.pslp.pslweb.business.be.impl.UtentiApiServiceImpl;
import it.csi.pslp.pslweb.util.SpringInjectorInterceptor;
import it.csi.pslp.pslweb.util.SpringSupportedResource;

/**
 * The Class PslwebRestApplication.
 */
@ApplicationPath("restfacade/be")
public class PslwebRestApplication extends Application {
    
    /** The singletons. */
    private Set<Object> singletons = new HashSet<Object>();
    
    /** The empty. */
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    /**
     * Instantiates a new pslweb rest application.
     */
    public PslwebRestApplication() {
        // singletons.add(new PslwclBE());
        singletons.add(new CalendarioApiServiceImpl());
        singletons.add(new ConfigurazioniApiServiceImpl());
        singletons.add(new CurrentUserApiServiceImpl());
        singletons.add(new DecodificheApiServiceImpl());
        singletons.add(new DocumentiApiServiceImpl());
        singletons.add(new MessaggiApiServiceImpl());
        singletons.add(new MenuApiServiceImpl());
        singletons.add(new PingApiServiceImpl());
        singletons.add(new PrivacyApiServiceImpl());
        singletons.add(new StampeApiServiceImpl());
        singletons.add(new UtentiApiServiceImpl());
        singletons.add(new UfficiApiServiceImpl());
        singletons.add(new CollocamentoMiratoServiceImpl());
        singletons.add(new DidApiServiceImpl());
        singletons.add(new SpringInjectorInterceptor());
        for (Object c : singletons) {
            if (c instanceof SpringSupportedResource) {
                SpringApplicationContextHelper.registerRestEasyController(c);
            }
        }
    }

    /**
     * Gets the classes.
     *
     * @return the classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    /**
     * Gets the singletons.
     *
     * @return the singletons
     */
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

}
