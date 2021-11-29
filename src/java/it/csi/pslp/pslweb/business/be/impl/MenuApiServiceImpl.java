/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslweb.business.be.MenuApi;
import it.csi.pslp.pslweb.dto.be.DomandeDidPage;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.MenuHelpPage;
import it.csi.pslp.pslweb.dto.be.MenuHomeCard;
import it.csi.pslp.pslweb.dto.be.UserInfo;
import it.csi.pslp.pslweb.dto.be.Utente;
import it.csi.pslp.pslweb.filter.IrideIdAdapterFilter;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.RestHelper;
import it.csi.util.performance.StopWatch;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuApiServiceImpl.
 */
@Component("menuApi")
public class MenuApiServiceImpl implements MenuApi {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The rest helper. */
    @Autowired
    private RestHelper restHelper;
    
    /** The messaggi utils. */
    @Autowired
    private MessaggiUtils messaggiUtils;

    /** The menu utils. */
    @Autowired
    private MenuUtils menuUtils;

    /**
     * Load menu.
     *
     * @param codMenu         the cod menu
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response loadMenu(String codMenu, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[MessaggiApiServiceImpl::loadMenu] codMessaggio=" + codMenu);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();

        try {
            
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            // attenzione se codMenu inizia con OP- segue codice fiscale dell'operatore
            if (codMenu.startsWith("OP-")) {
                codiceFiscaleUtente = codMenu.substring(3);
                codMenu = "OP";
            }
            ArrayList<MenuHomeCard> menuArray = menuUtils.getMenu(codMenu, codiceFiscaleUtente);
            if (menuArray == null) {
                ErrorDef err = new ErrorDef();
                err.setCode("400");
                err.setErrorMessage("Menu non trovato: " + codMenu);
                return Response.serverError().entity(err).status(400).build();
            }

            return Response.ok(menuArray).build();
        } catch (Exception ex) {
            log.error("[MessaggiApiServiceImpl::loadMenu]", ex);
            ErrorDef err = new ErrorDef();
            err.setCode("500");
            err.setErrorMessage(messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME002).getTesto());
            return Response.serverError().entity(err).status(500).build();
        } finally {
            watcher.dumpElapsed(MenuApiServiceImpl.class.getName(), "loadMenu()", "invocazione API MenuiApiServiceImpl.loadMenu", "");
            watcher.stop();
        }
    }

    /**
     * Load menu help page.
     *
     * @param codHelp         the cod help
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response loadMenuHelpPage(String codHelp, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[MessaggiApiServiceImpl::loadMenuHelpPage]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {

            MenuHelpPage result = menuUtils.getMenuHelpPage(codHelp);

            return Response.ok(result).build();

        } catch (Exception ex) {
            log.error("[MessaggiApiServiceImpl::loadMenuHelpPage]", ex);
            ErrorDef err = new ErrorDef();
            err.setCode("500");
            err.setErrorMessage(messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME002).getTesto());
            return Response.serverError().entity(err).status(500).build();
        } finally {
            watcher.dumpElapsed(MenuApiServiceImpl.class.getName(), "loadMenuHelpPage()", "invocazione API MenuiApiServiceImpl.loadMenuHelpPage", "");
            watcher.stop();
        }
    }

    /**
     * Load domande did page.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    public Response loadDomandeDidPage(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[MessaggiApiServiceImpl::loadDomandeDidPage]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            DomandeDidPage result = menuUtils.getDomandeDidPage();
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[MessaggiApiServiceImpl::loadDomandeDidPage]", ex);
            ErrorDef err = new ErrorDef();
            err.setCode("500");
            err.setErrorMessage(messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME002).getTesto());
            return Response.serverError().entity(err).status(500).build();
        } finally {
            watcher.dumpElapsed(MenuApiServiceImpl.class.getName(), "loadDomandeDidPage()", "invocazione API MenuiApiServiceImpl.loadDomandeDidPage", "");
            watcher.stop();
        }
    }

}
