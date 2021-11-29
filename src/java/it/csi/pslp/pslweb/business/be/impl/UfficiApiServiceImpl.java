/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslcommonobj.dbdef.EnteServizioLavoroDBDef;
import it.csi.pslp.pslcommonobj.dto.EnteServizioLavoroDTO;
import it.csi.pslp.pslcommonobj.filter.EnteServizioLavoroFilter;
import it.csi.pslp.pslweb.business.be.UfficiApi;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvdeWSImpl;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.Provincia;
import it.csi.pslp.pslweb.dto.be.Ufficio;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.ParametroUtils;
import it.csi.pslp.pslweb.util.RestHelper;
import it.csi.pslp.pslweb.util.SpringSupportedResource;
import it.csi.silos.jedi.core.DAO;
import it.csi.util.performance.StopWatch;

// TODO: Auto-generated Javadoc
/**
 * The Class UfficiApiServiceImpl.
 */
@Component("ufficiApi")
public class UfficiApiServiceImpl extends SpringSupportedResource implements UfficiApi {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The dao. */
    @Autowired
    private DAO dao;

    /** The rest helper. */
    @Autowired
    private RestHelper restHelper;

    /** The messaggi utils. */
    @Autowired
    private MessaggiUtils messaggiUtils;

    /** The tracciamento utils. */
    @Autowired
    private TracciamentoUtils tracciamentoUtils;

    /** The parametro utils. */
    @Autowired
    private ParametroUtils parametroUtils;

    /**
     * Gets the uffici.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the uffici
     */
    @Override
    public Response getUffici(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UfficiApiServiceImpl::getUffici]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();

        try {
            Map<String, Provincia> province = AdapterSilpsvdeWSImpl.getInstance().getMapProvincePerCodiceMinisteriale();
            List<Ufficio> result = new ArrayList<>();
            EnteServizioLavoroFilter filter = new EnteServizioLavoroFilter();
            filter.getIdEnteServizioLavoro().setOrderAsc(1);
            for (EnteServizioLavoroDTO enteDTO : dao.findAll(EnteServizioLavoroDBDef.class, filter, 0)) {
                Ufficio ufficio = new Ufficio();
                ufficio.setId(enteDTO.getIdEnteServizioLavoro());
                ufficio.setNome(enteDTO.getDescrizione());
                ufficio.setTipo(enteDTO.getCodiceTipoEnte());
                ufficio.setCodiceProvincia(enteDTO.getIdProvincia());
                ufficio.setIndirizzo(enteDTO.getIndirizzo());
                ufficio.setLatitudine(enteDTO.getLatitudine());
                ufficio.setLongitudine(enteDTO.getLongitidine());
                List<String> orari = new ArrayList<>();
                if (enteDTO.getOrarioDiApertura() != null) {
                    for (String orario : enteDTO.getOrarioDiApertura().split("\n")) {
                        orari.add(orario.trim());
                    }
                }
                ufficio.setOrario(orari);
                Provincia provincia = province.get(enteDTO.getIdProvincia());
                if (provincia != null)
                    ufficio.setProvincia(provincia.getDescrizione());
                result.add(ufficio);
            }
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[UfficiApiServiceImpl::getUffici]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.FIND_DOCUMENTI, httpRequest, null, "Errore di sistema: " + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed("UfficiApiServiceImpl", "getUffici()", "invocazione API OperatoreApiServiceImpl.getUffici", "");
            watcher.stop();
        }
    }

    /**
     * Creates the error response.
     *
     * @param code        the code
     * @param messageCode the message code
     * @return the response
     */
    protected Response createErrorResponse(String code, String messageCode) {
        ErrorDef err = new ErrorDef();
        err.setCode(code);
        err.setErrorMessage(messaggiUtils.loadMessaggioErrore(messageCode).getTesto());
        if ("404".equals(code)) {
            return Response.status(Response.Status.NOT_FOUND).entity(err).build();
        }
        return Response.serverError().entity(err).build();
    }

}
