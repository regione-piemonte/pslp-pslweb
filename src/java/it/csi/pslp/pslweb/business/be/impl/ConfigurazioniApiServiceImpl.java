/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslweb.business.be.ConfigurazioniApi;
import it.csi.pslp.pslweb.business.integration.AdapterSilpserver;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneInformazioneAggiuntiva;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.RestHelper;
import it.csi.util.performance.StopWatch;

/**
 * The Class ConfigurazioniApiServiceImpl.
 */
@Component("configurazioneApi")
public class ConfigurazioniApiServiceImpl implements ConfigurazioniApi {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The rest helper. */
    @Autowired
    private RestHelper restHelper;

    /** The messaggi utils. */
    @Autowired
    private MessaggiUtils messaggiUtils;

    /** The tracciamento utils. */
    @Autowired
    private TracciamentoUtils tracciamentoUtils;

    /**
     * Gets the configurazioni informazioni aggiuntive.
     *
     * @param codAmbito the cod ambito
     * @param flagAll the flag all
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the configurazioni informazioni aggiuntive
     */
    @Override
    public Response getConfigurazioniInformazioniAggiuntive(String codAmbito, String flagAll, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[ConfigurazioniApiServiceImpl::getConfigurazioniInformazioniAggiuntive] codAmbito=" + codAmbito + ", flagAll=" + flagAll);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            List<ConfigurazioneInformazioneAggiuntiva> configurazioni = AdapterSilpserver.getInstance().getInformazioniAggiuntive(codiceFiscaleUtente,
                    codAmbito, flagAll);
            log.debug("[ConfigurazioniApiServiceImpl::getConfigurazioniInformazioniAggiuntive] configurazioni=" + configurazioni);
            return Response.ok(configurazioni).build();
        } catch (Exception ex) {
            log.error("[ConfigurazioniApiServiceImpl::getConfigurazioniInformazioniAggiuntive]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_CONFIGURAZIONI_INFO_AGGIUNTIVE, httpRequest, null,
                    "Errore di sistema: " + ex.getClass().getName(), codAmbito);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed("ConfigurazioniApiServiceImpl", "getConfigurazioniInformazioniAggiuntive()",
                    "invocazione API ConfigurazioniApiServiceImpl.getConfigurazioniInformazioniAggiuntive", "");
            watcher.stop();
        }
    }

    /**
     * Creates the error response.
     *
     * @param code the code
     * @param messageCode the message code
     * @return the response
     */
    protected Response createErrorResponse(String code, String messageCode) {
        ErrorDef err = new ErrorDef();
        err.setCode(code);
        err.setErrorMessage(messaggiUtils.loadMessaggioErrore(messageCode).getTesto());
        return Response.serverError().entity(err).build();
    }

}
