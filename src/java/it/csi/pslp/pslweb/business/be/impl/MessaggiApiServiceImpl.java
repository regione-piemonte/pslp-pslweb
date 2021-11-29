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

import it.csi.pslp.pslcommonobj.dto.MessaggioDTO;
import it.csi.pslp.pslweb.business.be.MessaggiApi;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.Messaggio;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.RestHelper;
import it.csi.util.performance.StopWatch;

/**
 * The Class MessaggiApiServiceImpl.
 */
@Component("messaggiApi")
public class MessaggiApiServiceImpl implements MessaggiApi {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The rest helper. */
    @Autowired
    private RestHelper restHelper;

    /** The messaggi utils. */
    @Autowired
    private MessaggiUtils messaggiUtils;

    /**
     * Load messaggio.
     *
     * @param codMessaggio    the cod messaggio
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response loadMessaggio(String codMessaggio, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[MessaggiApiServiceImpl::loadMessaggio] codMessaggio=" + codMessaggio);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            MessaggioDTO messaggioDTO = messaggiUtils.loadMessaggio(codMessaggio);
            if (messaggioDTO == null) {
                ErrorDef err = new ErrorDef();
                err.setCode("400");
                err.setErrorMessage("Messaggio non trovato: " + codMessaggio);
                return Response.serverError().entity(err).status(400).build();
            }
            Messaggio messaggio = new Messaggio();
            messaggio.setCodAmbito(messaggioDTO.getCodAmbito());
            messaggio.setCodMessaggio(messaggioDTO.getCodMessaggio());
            messaggio.setCodTipoMessaggio(messaggioDTO.getCodTipoMessaggio());
            messaggio.setIntestazione(messaggioDTO.getIntestazione());
            messaggio.setTesto(messaggioDTO.getTesto());
            return Response.ok(messaggio).build();
        } catch (Exception ex) {
            log.error("[MessaggiApiServiceImpl::loadMessaggio]", ex);
            ErrorDef err = new ErrorDef();
            err.setCode("500");
            err.setErrorMessage(messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME002).getTesto());
            return Response.serverError().entity(err).status(500).build();
        } finally {
            watcher.dumpElapsed("MessaggiApiServiceImpl", "loadMessaggio()", "invocazione API MessaggiApiServiceImpl.loadMessaggio", "");
            watcher.stop();
        }
    }

}
