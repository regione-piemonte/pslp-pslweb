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

import it.csi.pslp.pslcommonobj.dto.ParametroDTO;
import it.csi.pslp.pslweb.business.be.ParametriApi;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.Parametro;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.ParametroUtils;
import it.csi.silos.silcommon.util.SilCommonUtils;
import it.csi.util.performance.StopWatch;

/**
 * The Class ParametriApiServiceImpl.
 */
@Component("parametriApi")
public class ParametriApiServiceImpl implements ParametriApi {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The messaggi utils. */
    @Autowired
    private MessaggiUtils messaggiUtils;

    /** The parametro utils. */
    @Autowired
    private ParametroUtils parametroUtils;

    /**
     * Gets the parametro.
     *
     * @param codParametro the cod parametro
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the parametro
     */
    @Override
    public Response getParametro(String codParametro, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[ParametriApiServiceImpl::getParametro] codParametro=" + codParametro);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            ParametroDTO parametroDTO = parametroUtils.getParametroDTO(codParametro);
            if (parametroDTO == null) {
                log.debug("[ParametriApiServiceImpl::getParametro] esito=404");
                return createErrorResponse("404", MessaggiUtils.ME014);
            }
            log.debug("[ParametriApiServiceImpl::getParametro] parametro=" + parametroDTO);
            Parametro parametro = new Parametro();
            parametro.setIdParametro(parametroDTO.getIdParametro());
            parametro.setCodParametro(parametroDTO.getCodParametro());
            parametro.setDescrParametro(parametroDTO.getDescrParametro());
            String valoreParametro = (String) SilCommonUtils.nvl(parametroDTO.getValoreParametroExt(), parametroDTO.getValoreParametro());
            parametro.setValoreParametro(valoreParametro);
            parametro.setDtInizio(parametroDTO.getDtInizio());
            parametro.setDtFine(parametroDTO.getDtFine());
            return Response.ok(parametro).build();
        } catch (Exception ex) {
            log.error("[ParametriApiServiceImpl::getParametro]", ex);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed("ParametriApiServiceImpl", "getParametro()", "invocazione API ParametriApiServiceImpl.getParametro", "");
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
