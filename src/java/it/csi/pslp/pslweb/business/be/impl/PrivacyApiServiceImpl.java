/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslweb.business.be.PrivacyApi;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.Privacy;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.silos.jedi.core.DAO;
import it.csi.silos.jedi.core.DAOException;
import it.csi.silos.jedi.core.QueryResult;
import it.csi.silos.jedi.core.RowReader;
import it.csi.util.performance.StopWatch;

/**
 * The Class PrivacyApiServiceImpl.
 */
@Component("privacyApi")
public class PrivacyApiServiceImpl implements PrivacyApi {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The dao. */
    @Autowired
    private DAO dao;
    
    /** The messaggi utils. */
    @Autowired
    private MessaggiUtils messaggiUtils;

    /**
     * Load privacy minore.
     *
     * @param idUtente the id utente
     * @param idMinore the id minore
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    public Response loadPrivacyMinore(Long idUtente, Long idMinore, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[PrivacyApiServiceImpl::loadPrivacyMinore] idUtente=" + idUtente + " ----- idMinore =" + idMinore);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            String stringQuery = "be/impl/PrivacyApiServiceImpl.findPrivacyUtente";
            Map<String, Object> params = new HashMap<>();
            params.put("idUtente", idUtente);
            if (null != idMinore) {
                params.put("idMinore", idMinore);
                stringQuery = "be/impl/PrivacyApiServiceImpl.findPrivacyMinore";
            }
            log.info("[PrivacyApiServiceImpl::loadPrivacyMinore] query da Eseguire =" + stringQuery);
            List<Privacy> result = new ArrayList<>();

            QueryResult<Privacy> privacies = dao.findAll(stringQuery, params, new PrivacyRowReader(), 0);
            if (privacies == null || privacies.size() == 0) {
                ErrorDef err = new ErrorDef();
                err.setCode("400");
                err.setErrorMessage("Privacy non trovata per l'utente " + idUtente);
                return Response.serverError().entity(err).status(400).build();
            }

            for (Iterator<Privacy> it = privacies.iterator(); it.hasNext();) {
                result.add(it.next());
            }
            return Response.ok(result).build();

        } catch (Exception ex) {
            log.error("[PrivacyApiServiceImpl::loadPrivacyMinore]", ex);
            ErrorDef err = new ErrorDef();
            err.setCode("500");
            err.setErrorMessage(messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME002).getTesto());
            return Response.serverError().entity(err).status(500).build();
        } finally {
            watcher.dumpElapsed("PrivacyApiServiceImpl", "loadPrivacyMinore()", "invocazione API MessaggiApiServiceImpl.loadMessaggio", "");
            watcher.stop();
        }
    }

    /**
     * Load privacy utente.
     *
     * @param idUtente the id utente
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    public Response loadPrivacyUtente(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        return loadPrivacyMinore(idUtente, null, securityContext, httpHeaders, httpRequest);
    }

    /**
     * The Class PrivacyRowReader.
     */
    private static class PrivacyRowReader implements RowReader<Privacy> {

        /**
         * Row readed.
         *
         * @param rs the rs
         * @return the privacy
         * @throws DAOException the DAO exception
         */
        @Override
        public Privacy rowReaded(ResultSet rs) throws DAOException {
            Privacy p = new Privacy();
            try {
                p.setDescrAmbito(rs.getString(1));
                p.setCodAmbito(rs.getString(2));
                p.setStato(rs.getString(3).equalsIgnoreCase("S") ? true : false);
                String elencoCodici = rs.getString(4);
                String str[] = elencoCodici.split(";");
                List<String> al = new ArrayList<String>();
                al = Arrays.asList(str);
                p.setCodeMsg(al);
                p.setIdTutelato(rs.getBigDecimal(5));
                p.setDataPresaVisione(rs.getDate(6));
                if (rs.getMetaData().getColumnCount() > 6) {
                    p.setIdTutelato(rs.getBigDecimal(7));
                }
            } catch (SQLException e) {
                throw new DAOException("Errore nella lettura dei dati per la privacy", e);
            }
            return p;
        }

    }

}
