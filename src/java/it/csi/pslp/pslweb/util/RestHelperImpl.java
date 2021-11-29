/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.pslp.pslcommonobj.dbdef.RelUtenteDBDef;
import it.csi.pslp.pslcommonobj.dbdef.UtenteDBDef;
import it.csi.pslp.pslcommonobj.dto.RelUtenteDTO;
import it.csi.pslp.pslcommonobj.dto.UtenteDTO;
import it.csi.pslp.pslcommonobj.filter.RelUtenteFilter;
import it.csi.pslp.pslcommonobj.filter.UtenteFilter;
import it.csi.pslp.pslweb.business.be.impl.TracciamentoUtils;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvalWSImpl;
import it.csi.pslp.pslweb.dto.be.Lavoratore;
import it.csi.pslp.pslweb.dto.be.UserInfo;
import it.csi.pslp.pslweb.filter.IrideIdAdapterFilter;
import it.csi.silos.jedi.core.DAO;

/**
 * The Class RestHelperImpl.
 */
public class RestHelperImpl implements RestHelper {

    /** The Constant LOG. */
    protected static final Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME + ".CurrentUserApiServiceImpl");

    /** The dao. */
    @Autowired
    private DAO dao;

    /** The parametro utils. */
    @Autowired
    private ParametroUtils parametroUtils;

    /** The tracciamento utils. */
    @Autowired
    private TracciamentoUtils tracciamentoUtils;

    /** The ambiente. */
    private String ambiente;

    /**
     * Gets the codice fiscale current user.
     *
     * @param httpRequest the http request
     * @return the codice fiscale current user
     */
    @Override
    public String getCodiceFiscaleCurrentUser(HttpServletRequest httpRequest) {
        String codiceFiscale = null;

        if (httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR) != null) {
            UserInfo ui = (UserInfo) httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
            codiceFiscale = ui.getCodFisc();
        }
        LOG.debug("[RestHelperImpl::getCodiceFiscaleCurrentUser] Codice fiscale " + codiceFiscale);
        return codiceFiscale;
    }

    /**
     * Gets the incoming IP address.
     *
     * @param req the req
     * @return the incoming IP address
     */
    @Override
    public String getIncomingIPAddress(HttpServletRequest req) {

        String xForwardedFor = req.getHeader("X-Forwarded-For");
        xForwardedFor = xForwardedFor != null && xForwardedFor.contains(",") ? xForwardedFor.split(",")[0] : xForwardedFor;
        String remoteHost = req.getRemoteHost();
        String remoteAddr = req.getRemoteAddr();
        int remotePort = req.getRemotePort();
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(remoteHost) && !remoteHost.equalsIgnoreCase(remoteAddr)) {
            sb.append(remoteHost).append(" ");
        }
        if (StringUtils.isNotBlank(xForwardedFor)) {
            sb.append(xForwardedFor).append("(fwd)=>");
        }
        if (StringUtils.isNotBlank(remoteAddr)) {
            sb.append(remoteAddr).append(":").append(remotePort);
        }
        return sb.toString();
    }

    /**
     * Verifica che il codice fiscale per cui si richiede l'operazione sia
     * effettivamente l'utente loggato.
     *
     * @param httpRequest the http request
     * @param idMetodo the id metodo
     * @param idSilLavAnagrafica the id sil lav anagrafica
     * @throws Exception the exception
     */
    public void checkUtenteBySILP(HttpServletRequest httpRequest, Long idMetodo, Long idSilLavAnagrafica) throws Exception {
        String codiceFiscaleUtente = getCodiceFiscaleCurrentUser(httpRequest);
        UtenteFilter filter = new UtenteFilter();
        filter.getIdSilLavAngrafica().eq(idSilLavAnagrafica);
        UtenteDTO utente = dao.findFirst(UtenteDBDef.class, filter);
        if (utente == null) {
            // Se l'utente loggato e' un operatore puo' operare anche se l'utente non esiste
            if (isOperatore(codiceFiscaleUtente)) {
                return;
            }
            Lavoratore lavoratore = AdapterSilpsvalWSImpl.getInstance().visualizzaDettaglioLavoratore(idSilLavAnagrafica);
            if (lavoratore == null) {
                raiseException(httpRequest, idMetodo, "L'utente per cui e' richiesta l'operazione [idSilLavAnagrafica=" + idSilLavAnagrafica
                        + "] non e' registrato sul Portale e non e' presente su SILP");
            }
            checkUtente(httpRequest, idMetodo, lavoratore.getCodiceFiscale());
        } else {
            checkUtente(httpRequest, idMetodo, utente.getIdUtente());
        }
    }

    /**
     * Verifica che il codice fiscale per cui si richiede l'operazione sia
     * effettivamente l'utente loggato.
     *
     * @param httpRequest the http request
     * @param idMetodo the id metodo
     * @param codiceFiscale the codice fiscale
     * @throws Exception the exception
     */
    public void checkUtente(HttpServletRequest httpRequest, Long idMetodo, String codiceFiscale) throws Exception {
        if (!("prod".equals(getAmbiente()))) {
            return;
        }

        String codiceFiscaleUtente = getCodiceFiscaleCurrentUser(httpRequest);
        if (codiceFiscaleUtente.equals(codiceFiscale))
            return;
        UtenteFilter filter = new UtenteFilter();
        filter.getCfUtente().eq(codiceFiscale);
        UtenteDTO utente = dao.findFirst(UtenteDBDef.class, filter);

        if (utente == null) {
            // Se l'utente loggato e' un operatore puo' operare anche se l'utente non esiste
            if (isOperatore(codiceFiscaleUtente)) {
                return;
            }
            raiseException(httpRequest, idMetodo,
                    "L'utente per cui e' richiesta l'operazione [codice fiscale richiesto=" + codiceFiscale + "] non e' registrato sul Portale");
        }
        checkUtente(httpRequest, idMetodo, utente.getIdUtente());
    }

    /**
     * Verifica che l'idUtente per cui si richiede l'operazione sia effettivamente
     * l'utente loggato.
     *
     * @param httpRequest the http request
     * @param idMetodo the id metodo
     * @param idUtente    identificativo utente per cui si richiedono i dati
     * @throws Exception the exception
     */
    public void checkUtente(HttpServletRequest httpRequest, Long idMetodo, Long idUtente) throws Exception {
        String codiceFiscaleUtente = getCodiceFiscaleCurrentUser(httpRequest);

        // Controlla se l'utente loggato corrisponde all'id richiesto
        boolean ok = false;
        UtenteFilter filter = new UtenteFilter();
        filter.getCfUtente().eq(codiceFiscaleUtente);
        UtenteDTO utente = dao.findFirst(UtenteDBDef.class, filter);
        if (utente != null) {
            ok = utente.getIdUtente().equals(idUtente);
        }

        if (!ok) {
            // Se l'utente loggato e' un operatore puo' operare per qualunque utente
            if (isOperatore(codiceFiscaleUtente)) {
                return;
            }

            if (utente != null) {
                // Controlla se l'utente loggato e' un tutore per l'id richiesto
                RelUtenteFilter filterTutelati = new RelUtenteFilter();
                filterTutelati.getTutelato().getIdUtente().eq(idUtente);
                for (RelUtenteDTO relUtente : dao.findAll(RelUtenteDBDef.class, filterTutelati, 0)) {
                    if (relUtente.getIdResponsabile().equals(utente.getIdUtente())) {
                        ok = true;
                        break;
                    }
                }
            }
        }

        if (!ok) {
            raiseException(httpRequest, idMetodo, "L'utente per cui e' richiesta l'operazione [idUtente=" + idUtente
                    + "] non corrisponde al codice fiscale dell'utente loggato: " + codiceFiscaleUtente);
        }
    }

    /**
     * Verifica che - l'utente corrente sia un operatore abilitato per l'operazione
     * richiesta.
     *
     * @param httpRequest the http request
     * @param idMetodo the id metodo
     * @param idUtente the id utente
     * @throws Exception the exception
     */
    public void checkUtenteOperatore(HttpServletRequest httpRequest, Long idMetodo, Long idUtente) throws Exception {
        String codiceFiscaleUtente = getCodiceFiscaleCurrentUser(httpRequest);
        if (!isOperatore(codiceFiscaleUtente)) {
            raiseException(httpRequest, idMetodo,
                    "L'utente per cui e' richiesta l'operazione [codiceFiscaleUtente=" + codiceFiscaleUtente + "] non e' un Operatore");
        }
    }

    /**
     * Checks if is operatore.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @return true, if is operatore
     * @throws Exception the exception
     */
    private boolean isOperatore(String codiceFiscaleUtente) throws Exception {
        UtenteFilter filter = new UtenteFilter();
        filter.getCfUtente().eq(codiceFiscaleUtente);
        filter.getCodTipoUtente().ne("CIT");
        UtenteDTO utente = dao.findFirst(UtenteDBDef.class, filter);
        if (utente != null) {
            return true;
        }
        return false;
    }

    /**
     * Gets the ambiente.
     *
     * @return the ambiente
     * @throws Exception the exception
     */
    public String getAmbiente() throws Exception {
        ambiente = parametroUtils.getParametro("CHECK_UTENTE", "test");
        return ambiente;
    }

    /**
     * Raise exception.
     *
     * @param httpRequest the http request
     * @param idMetodo the id metodo
     * @param msg the msg
     * @throws Exception the exception
     */
    private void raiseException(HttpServletRequest httpRequest, Long idMetodo, String msg) throws Exception {
        if ("prod".equals(getAmbiente())) {
            tracciamentoUtils.tracciaKo(idMetodo, httpRequest, null, msg, null);
            throw new IllegalStateException(msg);
        } else {
            LOG.warn("[RestHelperImpl::raiseException] " + msg);
        }
    }

}
