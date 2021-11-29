/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import it.csi.pslp.pslcommonobj.dbdef.AmbitoDBDef;
import it.csi.pslp.pslcommonobj.dbdef.CalendarioDBDef;
import it.csi.pslp.pslcommonobj.dbdef.EccezioneDBDef;
import it.csi.pslp.pslcommonobj.dbdef.MessaggioAggiuntivoDBDef;
import it.csi.pslp.pslcommonobj.dbdef.MessaggioCalendarioDBDef;
import it.csi.pslp.pslcommonobj.dbdef.MessaggioDBDef;
import it.csi.pslp.pslcommonobj.dbdef.PeriodoDBDef;
import it.csi.pslp.pslcommonobj.dbdef.PeriodoGiornoSettDBDef;
import it.csi.pslp.pslcommonobj.dbdef.PrenotazioneDBDef;
import it.csi.pslp.pslcommonobj.dbdef.SlotDBDef;
import it.csi.pslp.pslcommonobj.dbdef.UtenteDBDef;
import it.csi.pslp.pslcommonobj.dbdef.UtenteEnteDBDef;
import it.csi.pslp.pslcommonobj.dto.AmbitoDTO;
import it.csi.pslp.pslcommonobj.dto.CalendarioDTO;
import it.csi.pslp.pslcommonobj.dto.EccezioneDTO;
import it.csi.pslp.pslcommonobj.dto.EsitoDTO;
import it.csi.pslp.pslcommonobj.dto.MessaggioAggiuntivoDTO;
import it.csi.pslp.pslcommonobj.dto.MessaggioCalendarioDTO;
import it.csi.pslp.pslcommonobj.dto.MessaggioDTO;
import it.csi.pslp.pslcommonobj.dto.ParametriAnnulloCalendario;
import it.csi.pslp.pslcommonobj.dto.PeriodoDTO;
import it.csi.pslp.pslcommonobj.dto.PeriodoGiornoSettDTO;
import it.csi.pslp.pslcommonobj.dto.PrenotazioneDTO;
import it.csi.pslp.pslcommonobj.dto.SlotDTO;
import it.csi.pslp.pslcommonobj.dto.UtenteDTO;
import it.csi.pslp.pslcommonobj.dto.UtenteEnteDTO;
import it.csi.pslp.pslcommonobj.filter.AmbitoFilter;
import it.csi.pslp.pslcommonobj.filter.CalendarioFilter;
import it.csi.pslp.pslcommonobj.filter.EccezioneFilter;
import it.csi.pslp.pslcommonobj.filter.MessaggioAggiuntivoFilter;
import it.csi.pslp.pslcommonobj.filter.MessaggioCalendarioFilter;
import it.csi.pslp.pslcommonobj.filter.MessaggioFilter;
import it.csi.pslp.pslcommonobj.filter.PeriodoFilter;
import it.csi.pslp.pslcommonobj.filter.PeriodoGiornoSettFilter;
import it.csi.pslp.pslcommonobj.filter.PrenotazioneFilter;
import it.csi.pslp.pslcommonobj.filter.SlotFilter;
import it.csi.pslp.pslcommonobj.filter.UtenteEnteFilter;
import it.csi.pslp.pslcommonobj.filter.UtenteFilter;
import it.csi.pslp.pslweb.business.be.OperatoreApi;
import it.csi.pslp.pslweb.business.integration.AdapterSilpserver;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvinWSImpl;
import it.csi.pslp.pslweb.business.integration.PslOrchApiClient;
import it.csi.pslp.pslweb.dto.be.Ambito;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCalendario;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCalendarioDatiOperativi;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCalendarioEccezione;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCalendarioFascia;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCalendarioHeader;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCalendarioMail;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCalendarioPeriodoValidita;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneInformazioneAggiuntiva;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneMessaggiAggiuntivi;
import it.csi.pslp.pslweb.dto.be.Ente;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.Esito;
import it.csi.pslp.pslweb.dto.be.EsitoDuplicazionePeriodo;
import it.csi.pslp.pslweb.dto.be.Messaggio;
import it.csi.pslp.pslweb.dto.be.Operatore;
import it.csi.pslp.pslweb.dto.be.ParametriDatoCalendari;
import it.csi.pslp.pslweb.dto.be.ParametriDuplicazioneCalendario;
import it.csi.pslp.pslweb.dto.be.ParametriDuplicazionePeriodo;
import it.csi.pslp.pslweb.dto.be.ParametriEccezioneCalendari;
import it.csi.pslp.pslweb.dto.be.ParametriEliminazioneEccezione;
import it.csi.pslp.pslweb.dto.be.ParametriEliminazioneFascia;
import it.csi.pslp.pslweb.dto.be.ParametriRicercaCalendari;
import it.csi.pslp.pslweb.dto.be.ParametriRicercaIncontriCalendario;
import it.csi.pslp.pslweb.dto.be.PrenotazioneIncontro;
import it.csi.pslp.pslweb.dto.be.SlotIncontro;
import it.csi.pslp.pslweb.dto.be.Sportello;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.ParametroUtils;
import it.csi.pslp.pslweb.util.RestHelper;
import it.csi.pslp.pslweb.util.SpringSupportedResource;
import it.csi.silos.jedi.core.DAO;
import it.csi.silos.jedi.core.DAOException;
import it.csi.silos.jedi.core.QueryResult;
import it.csi.silos.jedi.core.StdRowReader;
import it.csi.silos.jedi.engine.DBUtils;
import it.csi.silos.silcommon.util.SilTimeUtils;
import it.csi.util.performance.StopWatch;

/**
 * The Class OperatoreApiServiceImpl.
 */
@Component("operatoreApi")
public class OperatoreApiServiceImpl extends SpringSupportedResource implements OperatoreApi {

    /** The Constant OPERATORE_API_SERVICE_IMPL_DUPLICA_PERIODO_ID_UTENTE. */
    private static final String OPERATORE_API_SERVICE_IMPL_DUPLICA_PERIODO_ID_UTENTE = "[OperatoreApiServiceImpl::duplicaPeriodo] idUtente=";

    /** The Constant ERRORE_DI_SISTEMA. */
    private static final String ERRORE_DI_SISTEMA = "Errore di sistema: ";

    /** The Constant PARAMETRI_RICERCA_INCONTRI_CALENDARIO. */
    private static final String PARAMETRI_RICERCA_INCONTRI_CALENDARIO = ",parametriRicercaIncontriCalendario=";

    /** The Constant ID_CALENDARIO2. */
    private static final String ID_CALENDARIO2 = ", idCalendario=";

    /** The Constant CONFIGURAZIONE_CALENDARIO2. */
    private static final String CONFIGURAZIONE_CALENDARIO2 = ", configurazioneCalendario=";

    /** The Constant COD_TIPO_UTENTE. */
    private static final String COD_TIPO_UTENTE = ", codTipoUtente=";

    /** The Constant CONFIGURAZIONE_CALENDARIO. */
    private static final String CONFIGURAZIONE_CALENDARIO = "configurazioneCalendario=";

    /** The Constant DD_MM_YYYY. */
    private static final String DD_MM_YYYY = "dd/MM/yyyy";

    /** The Constant ID_CALENDARIO. */
    private static final String ID_CALENDARIO = "idCalendario=";

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The Constant SEZIONE_DATI_GENERALI. */
    private static final String SEZIONE_DATI_GENERALI = "G";
    
    /** The Constant SEZIONE_DATI_OPERATIVI. */
    private static final String SEZIONE_DATI_OPERATIVI = "O";
    
    /** The Constant SEZIONE_DATI_FASCE. */
    private static final String SEZIONE_DATI_FASCE = "F";
    
    /** The Constant SEZIONE_DATI_ECCEZIONI. */
    private static final String SEZIONE_DATI_ECCEZIONI = "E";
    
    /** The Constant SEZIONE_DATI_MAIL. */
    private static final String SEZIONE_DATI_MAIL = "M";

    /** The Constant CAMPO_VISIBILE_IN_BASE. */
    private static final String CAMPO_VISIBILE_IN_BASE = "visibile_in_base";
    
    /** The Constant CAMPO_ORE_LIMITE_SPOSTAMENTO. */
    private static final String CAMPO_ORE_LIMITE_SPOSTAMENTO = "ore_limite_spostamento";
    
    /** The Constant CAMPO_ANNULLA_GARANZIA_GIOVANI. */
    private static final String CAMPO_ANNULLA_GARANZIA_GIOVANI = "annulla_garanzia_giovani";
    
    /** The Constant CAMPO_MESSAGGIO_ANNULLAMENTO_APPUNTAMENTO. */
    private static final String CAMPO_MESSAGGIO_ANNULLAMENTO_APPUNTAMENTO = "messaggio_annullamento_appuntamento";
    
    /** The Constant CAMPO_MESSAGGIO_SPOSTAMENTO_APPUNTAMENTO. */
    private static final String CAMPO_MESSAGGIO_SPOSTAMENTO_APPUNTAMENTO = "messaggio_spostamento_appuntamento";

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
     * Ricerca l'operatore su PLSP.
     *
     * @param codiceFiscale the codice fiscale
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the operatore by cf
     */
    @Override
    public Response getOperatoreByCf(String codiceFiscale, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        // Safety: codice fiscale to uppercase
        codiceFiscale = StringUtils.upperCase(codiceFiscale);
        log.info("[OperatoreApiServiceImpl::getOperatoreByCf] codiceFiscale=" + codiceFiscale);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            UtenteFilter utenteFilter = new UtenteFilter();
            utenteFilter.getCfUtente().eq(codiceFiscale);
            utenteFilter.getCodTipoUtente().ne("CIT");
            QueryResult<UtenteDTO> utentiDTO = dao.findAll(UtenteDBDef.class, utenteFilter, 10);

            if (utentiDTO == null || utentiDTO.size() == 0) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_UTENTE, httpRequest, null, "Operatore non trovato", null);
                log.debug("[OperatoreApiServiceImpl::getOperatoreByCf] esito=404");
                return Response.status(404).build();
            }

            List<Operatore> operatori = new ArrayList<Operatore>(utentiDTO.size());
            for (UtenteDTO utenteDTO : utentiDTO) {
                Operatore operatore = new Operatore();
                operatore.setIdUtente(utenteDTO.getIdUtente());
                operatore.setEmail(utenteDTO.getEmail());
                operatore.setCognome(utenteDTO.getCognome());
                operatore.setNome(utenteDTO.getNome());
                operatore.setCodiceTipoUtente(utenteDTO.getCodTipoUtente());
                operatori.add(operatore);
            }
            return Response.ok(operatori).build();
        } catch (DAOException e) {
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_UTENTE, httpRequest, null, e.getMessage(), null);
            log.debug("[OperatoreApiServiceImpl::getOperatoreByCf] esito=404 messaggio=" + e.getMessage());
            return Response.status(404).build();
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "getOperatoreByCf()", "invocazione API OperatoreApiServiceImpl.getOperatoreByCf", "");
            watcher.stop();
        }
    }

    /**
     * Gets the messaggi.
     *
     * @param idUtente the id utente
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the messaggi
     */
    @Override
    public Response getMessaggi(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::getMessaggi] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.GET_MESSAGGI, idUtente);
            MessaggioFilter filter = new MessaggioFilter();
            filter.getCodTipoMessaggio().eq("MAIL");
            filter.getCodMessaggio().setOrderAsc(1);
            List<Messaggio> result = new ArrayList<>();
            for (MessaggioDTO messaggioDTO : dao.findAll(MessaggioDBDef.class, filter, 0)) {
                Messaggio messaggio = new Messaggio();
                messaggio.setIdMessaggio(messaggioDTO.getIdMessaggio());
                messaggio.setCodAmbito(messaggioDTO.getCodAmbito());
                messaggio.setCodMessaggio(messaggioDTO.getCodMessaggio());
                messaggio.setCodTipoMessaggio(messaggioDTO.getCodTipoMessaggio());
                messaggio.setIntestazione(messaggioDTO.getIntestazione());
                messaggio.setTesto(messaggioDTO.getTesto());
                result.add(messaggio);
            }
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::getMessaggi] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_MESSAGGI, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "getMessaggi()", "invocazione API OperatoreApiServiceImpl.getMessaggi", "");
            watcher.stop();
        }
    }

    /**
     * Save messaggio.
     *
     * @param idUtente the id utente
     * @param messaggio the messaggio
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response saveMessaggio(Long idUtente, Messaggio messaggio, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::saveMessaggio] idUtente=" + idUtente + ", messaggio=" + messaggio.getIdMessaggio());
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.SAVE_MESSAGGIO, idUtente);
            MessaggioFilter filter = new MessaggioFilter();
            filter.getIdMessaggio().eq(messaggio.getIdMessaggio());
            MessaggioDTO messaggioDTO = dao.findFirst(MessaggioDBDef.class, filter);
            if (messaggioDTO == null) {
                return createErrorResponse("404", MessaggiUtils.ME005);
            }
            messaggioDTO.setTesto(messaggio.getTesto());
            dao.update(MessaggioDBDef.class, messaggioDTO);
            return Response.ok(messaggio).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::saveMessaggio] idUtente=" + idUtente + ", messaggio=" + messaggio.getIdMessaggio(), ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_MESSAGGIO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    messaggio.getCodAmbito());
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "saveMessaggio()", "invocazione API OperatoreApiServiceImpl.saveMessaggio", "");
            watcher.stop();
        }
    }

    /**
     * Save configurazione informazioni aggiuntive.
     *
     * @param idUtente the id utente
     * @param conf the conf
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response saveConfigurazioneInformazioniAggiuntive(Long idUtente, ConfigurazioneInformazioneAggiuntiva conf, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::saveConfigurazioneInformazioniAggiuntive] idUtente=" + idUtente + "conf=" + conf);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.SAVE_CONFIGURAZIONI_INFO_AGGIUNTIVE, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            conf = AdapterSilpserver.getInstance().saveConfigurazioneInformazioniAggiuntive(codiceFiscaleUtente, conf);
            if (conf == null) {
                return createErrorResponse("401", MessaggiUtils.ME002);
            }
            return Response.ok(conf).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::saveConfigurazioneInformazioniAggiuntive] idUtente=" + idUtente + ", conf=" + conf, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_CONFIGURAZIONI_INFO_AGGIUNTIVE, httpRequest, idUtente,
                    ERRORE_DI_SISTEMA + ex.getClass().getName(), conf.getCodAmbito());
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "saveConfigurazioneInformazioniAggiuntive()",
                    "invocazione API OperatoreApiServiceImpl.saveConfigurazioneInformazioniAggiuntive", "");
            watcher.stop();
        }
    }

    /**
     * Find calendari.
     *
     * @param idUtente the id utente
     * @param parametriRicercaCalendari the parametri ricerca calendari
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    public Response findCalendari(Long idUtente, ParametriRicercaCalendari parametriRicercaCalendari, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::findCalendari] idUtente=" + idUtente + "parametriRicercaCalendari=" + parametriRicercaCalendari);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.FIND_CALENDARI, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);

            Set<String> setEntiOperatoreCpi = new HashSet<>();
            if ("CPI".equals(parametriRicercaCalendari.getCodTipoUtente())) {
                UtenteEnteFilter filterUtentiEnti = new UtenteEnteFilter();
                filterUtentiEnti.getIdUtente().eq(idUtente);
                for (UtenteEnteDTO utenteEnte : dao.findAll(UtenteEnteDBDef.class, filterUtentiEnti, 0)) {
                    String keyEnte = utenteEnte.getGruppoOperatore() + "-" + utenteEnte.getCodOperatore() + "-" + utenteEnte.getSubcodice();
                    setEntiOperatoreCpi.add(keyEnte);
                }
                log.debug("[OperatoreApiServiceImpl::findCalendari] Sportelli operatore: " + setEntiOperatoreCpi);
            }

            Map<String, Sportello> sportelli = AdapterSilpsvinWSImpl.getInstance().findSportelliMap(codiceFiscaleUtente);
            List<ConfigurazioneCalendarioHeader> result = new ArrayList<>();
            Map<String, Object> calendariFilter = new HashMap<>();
            if (parametriRicercaCalendari.getCodAmbito() != null) {
                calendariFilter.put("ambito", parametriRicercaCalendari.getCodAmbito());
            }
            if (parametriRicercaCalendari.getGruppoOperatore() != null) {
                calendariFilter.put("gruppoOperatore", parametriRicercaCalendari.getGruppoOperatore());
            }
            if (parametriRicercaCalendari.getCodOperaratore() != null) {
                calendariFilter.put("codiceOperatore", parametriRicercaCalendari.getCodOperaratore());
            }
            if (parametriRicercaCalendari.getSubcodice() != null) {
                calendariFilter.put("subcodice", parametriRicercaCalendari.getSubcodice());
            }
            if (parametriRicercaCalendari.getNomeCalendario() != null) {
                calendariFilter.put("nome", "%" + parametriRicercaCalendari.getNomeCalendario() + "%");
            }
            if (parametriRicercaCalendari.isCalendarioEliminato() != null) {
                if (parametriRicercaCalendari.isCalendarioEliminato()) {
                    calendariFilter.put("annullato", "S");
                } else {
                    calendariFilter.put("nonAnnullato", "S");
                }
            }
            int margine = Integer.parseInt(parametroUtils.getParametro("APPUNT_MARGINE_GG", "1"));
            calendariFilter.put("margine", margine);

            if (parametriRicercaCalendari.getIdEccezioneApplicabile() != null) {
                EccezioneFilter eccezioneFilter = new EccezioneFilter();
                eccezioneFilter.getIdEccezione().eq(parametriRicercaCalendari.getIdEccezioneApplicabile());
                EccezioneDTO eccezione = dao.findFirst(EccezioneDBDef.class, eccezioneFilter);
                calendariFilter.put("oraInizioEccezione", eccezione.getOraInizio());
                calendariFilter.put("oraFineEccezione", eccezione.getOraFine());
                calendariFilter.put("giornoEccezione", eccezione.getGiorno());
            }

            Map<Long, ConfigurazioneCalendarioHeader> headers = new HashMap<>();
            dao.eachRow("be/impl/OperatoreApiServiceImpl.ricercaCalendari", calendariFilter, new StdRowReader() {
                public void stdRowReaded(ResultSet rs) throws DAOException {
                    ConfigurazioneCalendarioHeader header = new ConfigurazioneCalendarioHeader();
                    Long idCalendario = DBUtils.getLong(rs, "ID_CALENDARIO");
                    header.setIdCalendario(idCalendario);
                    headers.put(idCalendario, header);
                    header.setNome(DBUtils.getString(rs, "NOME"));
                    String gruppoOperatore = DBUtils.getString(rs, "GRUPPO_OPERATORE");
                    Integer codOperatore = DBUtils.getInteger(rs, "COD_OPERATORE");
                    Integer subcodice = DBUtils.getInteger(rs, "SUBCODICE");
                    String sportelloKey = gruppoOperatore + "-" + codOperatore + "-" + subcodice;
                    if (!sportelli.containsKey(sportelloKey))
                        return;
                    Sportello sportello = sportelli.get(sportelloKey);
                    String keyEnteAppartenenza = sportello.getGruppoOperatoreEnteAppartenenza() + "-" + sportello.getCodOperatoreEnteAppartenenza() + "-"
                            + sportello.getSubcodiceEnteAppartenenza();
                    if ("CPI".equals(parametriRicercaCalendari.getCodTipoUtente()) && !setEntiOperatoreCpi.contains(keyEnteAppartenenza)) {
                        log.debug("[CalendarioApiServiceImpl::findCalendari] Escludo sportello " + keyEnteAppartenenza
                                + " perche' l'utente opera come operatore CPI e non e' abilitato per questo sportello");
                        return;
                    }
                    header.setDescrizioneEnte(sportello.getDescrizione());
                    header.setDescrizioneAmbito(DBUtils.getString(rs, "DESCR_AMBITO"));
                    Date dataAnnullamento = DBUtils.getDate(rs, "D_ANNULLAMENTO");
                    header.setEliminato(dataAnnullamento != null);
                    Integer oreInvioPropmemoria = DBUtils.getInteger(rs, "ORE_INVIO_PROMEMORIA");
                    header.setPromemoria(oreInvioPropmemoria != null && oreInvioPropmemoria.intValue() > 0);
                    header.setOreLimiteSpostamento(DBUtils.getInteger(rs, "ORE_LIMITE_SPOSTAMENTO"));
                    String flgAnnullaGaranziaGiovani = DBUtils.getString(rs, "FLG_ANNULLA_GARANZIA_GIOVANI");
                    if ("S".equals(flgAnnullaGaranziaGiovani))
                        header.setAnnullaGaranziaGiovani(true);
                    else if ("N".equals(flgAnnullaGaranziaGiovani))
                        header.setAnnullaGaranziaGiovani(false);
                    String flagVisibilita = DBUtils.getString(rs, "FLG_VISIBILE_CPI");
                    if (flagVisibilita != null) {
                        switch (flagVisibilita) {
                        case "D":
                            header.setVisibileInBase("Domicilio");
                            break;
                        case "R":
                            header.setVisibileInBase("Residenza");
                            break;
                        case "T":
                            header.setVisibileInBase("Residenza o Domicilio");
                            break;
                        default:
                            break;
                        }
                    }
                    String flagBloccato = DBUtils.getString(rs, "FLG_BLOCCATO");
                    header.setBloccato("S".equals(flagBloccato));
                    header.setNumeroSlotLiberi(DBUtils.getInteger(rs, "TOT_SLOT"));
                    header.setNumeroSlotPrenotabili(DBUtils.getInteger(rs, "TOT_SLOT_LIB"));
                    result.add(header);
                }
            });

            // Reperisce i messaggi calendari
            MessaggioCalendarioFilter messaggioCalendarioFilter = new MessaggioCalendarioFilter();
            Long[] idCalendari = new Long[result.size()];
            for (int i = 0; i < result.size(); i++) {
                idCalendari[i] = result.get(i).getIdCalendario();
            }
            messaggioCalendarioFilter.getIdCalendario().in(idCalendari);
            messaggioCalendarioFilter.getCodTipoMessaggioCalendario()
                    .in(new String[] { MessaggioCalendarioDTO.SPOSTAMENTO_APPUNTAMENTO, MessaggioCalendarioDTO.ANNULLAMENTO_APPUNTAMENTO });
            for (MessaggioCalendarioDTO msgCalDTO : dao.findAll(MessaggioCalendarioDBDef.class, messaggioCalendarioFilter, 0)) {
                ConfigurazioneCalendarioHeader header = headers.get(msgCalDTO.getIdCalendario());
                if (header != null) {
                    switch (msgCalDTO.getCodTipoMessaggioCalendario()) {
                    case MessaggioCalendarioDTO.SPOSTAMENTO_APPUNTAMENTO:
                        header.setMessaggioSpostamentoAppuntamento(msgCalDTO.getTesto());
                        break;
                    case MessaggioCalendarioDTO.ANNULLAMENTO_APPUNTAMENTO:
                        header.setMessaggioAnnullamentoAppuntamento(msgCalDTO.getTesto());
                        break;
                    default:
                        break;
                    }
                }
            }

            List<ConfigurazioneCalendarioHeader> result2 = new ArrayList<>(result.size());
            for (ConfigurazioneCalendarioHeader header : result) {
                PeriodoFilter periodoFilter = new PeriodoFilter();
                periodoFilter.getCalendario().getIdCalendario().eq(header.getIdCalendario());
                periodoFilter.getDtInizio().setOrderAsc(1);
                Date dataInizio = null;
                Date dataFine = null;
                for (PeriodoDTO periodoDTO : dao.findAll(PeriodoDBDef.class, periodoFilter, 0)) {
                    if (dataInizio == null)
                        dataInizio = periodoDTO.getDtInizio();
                    dataFine = periodoDTO.getDtFine();
                }
                if (SilTimeUtils.isConflittoTraIntervalliTemporaliEstremiCompresi(parametriRicercaCalendari.getDataDa(), parametriRicercaCalendari.getDataA(),
                        dataInizio, dataFine)) {
                    result2.add(header);
                }
                SimpleDateFormat df = new SimpleDateFormat(DD_MM_YYYY);
                if (dataInizio != null && dataFine != null)
                    header.setPeriodo("Da " + df.format(dataInizio) + " a " + df.format(dataFine));
            }

            return Response.ok(result2).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::findCalendari] idUtente=" + idUtente + ", v=" + parametriRicercaCalendari, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.FIND_CALENDARI, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    parametriRicercaCalendari.getCodAmbito());
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "findCalendari()", "invocazione API OperatoreApiServiceImpl.findCalendari", "");
            watcher.stop();
        }
    }

    /**
     * Gets the ambiti.
     *
     * @param idUtente the id utente
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the ambiti
     */
    @Override
    public Response getAmbiti(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::getAmbiti] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.GET_AMBITI, idUtente);
            List<Ambito> result = new ArrayList<>();
            AmbitoFilter filter = new AmbitoFilter();
            filter.getIdSilTInServizio().ne(null);
            filter.getDescrAmbito().setOrderAsc(1);
            for (AmbitoDTO ambitoDTO : dao.findAll(AmbitoDBDef.class, filter, 0)) {
                Ambito ambito = new Ambito();
                ambito.setCodice(ambitoDTO.getCodAmbito());
                ambito.setDescrizione(ambitoDTO.getDescrAmbito());
                result.add(ambito);
            }
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::getAmbiti] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_AMBITI, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "getAmbiti()", "invocazione API OperatoreApiServiceImpl.getAmbiti", "");
            watcher.stop();
        }
    }

    /**
     * Gets the enti.
     *
     * @param idUtente the id utente
     * @param codTipoUtente the cod tipo utente
     * @param modalita the modalita
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the enti
     */
    @Override
    public Response getEnti(Long idUtente, String codTipoUtente, String modalita, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::getEnti] idUtente=" + idUtente + COD_TIPO_UTENTE + codTipoUtente + ", modalita=" + modalita);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.GET_ENTI, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);

            List<Ente> enti = AdapterSilpsvinWSImpl.getInstance().findElencoEnti(codiceFiscaleUtente, null);
            Map<String, Ente> mapEnti = new HashMap<>();
            Set<String> setEntiOperatoreCpi = new HashSet<>();
            if ("CPI".equals(codTipoUtente)) {
                UtenteEnteFilter filterUtentiEnti = new UtenteEnteFilter();
                filterUtentiEnti.getIdUtente().eq(idUtente);
                if ("I".equals(modalita))
                    filterUtentiEnti.getFlgUtenteMaster().eq("S");
                for (UtenteEnteDTO utenteEnte : dao.findAll(UtenteEnteDBDef.class, filterUtentiEnti, 0)) {
                    String keyEnte = utenteEnte.getGruppoOperatore() + "-" + utenteEnte.getCodOperatore() + "-" + utenteEnte.getSubcodice();
                    setEntiOperatoreCpi.add(keyEnte);
                }
                log.info("[OperatoreApiServiceImpl::getEnti] Sportelli operatore: " + setEntiOperatoreCpi);
            }

            List<Ente> result = new ArrayList<>();
            for (Ente ente : enti) {
                String keyEnte = ente.getGruppoOperatore() + "-" + ente.getCodOperatore() + "-" + ente.getSubcodice();
                mapEnti.put(keyEnte, ente);
                if ("CPI".equals(codTipoUtente) && !setEntiOperatoreCpi.contains(keyEnte)) {
                    log.debug("[OperatoreApiServiceImpl::getEnti] Escludo sportello " + keyEnte
                            + " perche' l'utente opera come operatore CPI e non e' abilitato o non e' master per questo sportello");
                    continue;
                }
                result.add(ente);
            }
            log.debug("[OperatoreApiServiceImpl::getEnti] result=" + result);
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::getEnti] idUtente=" + idUtente + COD_TIPO_UTENTE + codTipoUtente + ", modalita=" + modalita, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_ENTI, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "getEnti()", "invocazione API OperatoreApiServiceImpl.getEnti", "");
            watcher.stop();
        }
    }

    /**
     * Save calendario dati generali.
     *
     * @param idUtente the id utente
     * @param configurazioneCalendario the configurazione calendario
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response saveCalendarioDatiGenerali(Long idUtente, ConfigurazioneCalendario configurazioneCalendario, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::saveCalendarioDatiGenerali] idUtente=" + idUtente + CONFIGURAZIONE_CALENDARIO + configurazioneCalendario);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.SAVE_CALENDARIO_DATI_GENERALI, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);

            // Verifica se esiste gia' un calendario attivo per ambito ed ente
            if (configurazioneCalendario.getIdCalendario() == null) {
                CalendarioFilter filter = new CalendarioFilter();
                filter.getDAnnullamento().eq(null);
                filter.getAmbito().getCodAmbito().eq(configurazioneCalendario.getCodiceAmbito());
                filter.getGruppoOperatore().eq(configurazioneCalendario.getGruppoOperatore());
                filter.getCodOperatore().eq(configurazioneCalendario.getCodiceOperatore());
                filter.getSubcodice().eq(configurazioneCalendario.getSubcodice());
                CalendarioDTO calendarioDTO = dao.findFirst(CalendarioDBDef.class, filter);
                if (calendarioDTO != null) {
                    throw new BusinessException(MessaggiUtils.ME069);
                }
            }

            saveCalendario(SEZIONE_DATI_GENERALI, configurazioneCalendario, codiceFiscaleUtente);
            configurazioneCalendario = loadCalendario(configurazioneCalendario.getIdCalendario());
            return Response.ok(configurazioneCalendario).build();
        } catch (BusinessException ex) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("452", ex.getCode(), ex.getArgs());
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::saveCalendarioDatiGenerali] idUtente=" + idUtente + CONFIGURAZIONE_CALENDARIO2 + configurazioneCalendario, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_CALENDARIO_DATI_GENERALI, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    configurazioneCalendario.getCodiceAmbito());
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "saveCalendarioDatiGenerali()",
                    "invocazione API OperatoreApiServiceImpl.saveCalendarioDatiGenerali", "");
            watcher.stop();
        }
    }

    /**
     * Save calendario.
     *
     * @param sezione the sezione
     * @param calendario the calendario
     * @param codiceFiscaleUtente the codice fiscale utente
     * @throws Exception the exception
     */
    private void saveCalendario(String sezione, ConfigurazioneCalendario calendario, String codiceFiscaleUtente) throws Exception {

        // Crea / recupera il calendario
        CalendarioDTO calendarioDTO = null;
        if (calendario.getIdCalendario() == null) {
            calendarioDTO = new CalendarioDTO();
            calendarioDTO.setFlgVisibileCpi(null);
            calendarioDTO.setFlgInvioConfermaPrenotaz("S");
            calendarioDTO.setDInserim(new Date());
            calendarioDTO.setCodUserInserim(codiceFiscaleUtente);
        } else {
            CalendarioFilter calendarioFilter = new CalendarioFilter();
            calendarioFilter.getIdCalendario().eq(calendario.getIdCalendario());
            calendarioDTO = dao.findFirst(CalendarioDBDef.class, calendarioFilter);
        }

        // Imposta i dati
        if (SEZIONE_DATI_GENERALI.equals(sezione)) {
            calendarioDTO.setAmbito(new AmbitoDTO(calendario.getCodiceAmbito()));
            calendarioDTO.setGruppoOperatore(calendario.getGruppoOperatore());
            calendarioDTO.setCodOperatore(calendario.getCodiceOperatore());
            calendarioDTO.setSubcodice(calendario.getSubcodice());
            calendarioDTO.setNome(calendario.getNome());

            calendarioDTO.setOreLimiteSpostamento(calendarioDTO.getOreLimiteSpostamento());
            calendarioDTO.setDAggiorn(new Date());
            calendarioDTO.setCodUserAggiorn(codiceFiscaleUtente);

            // Salva il calendario
            if (calendarioDTO.getIdCalendario() == null) {
                calendarioDTO = dao.insert(CalendarioDBDef.class, calendarioDTO);
                calendario.setIdCalendario(calendarioDTO.getIdCalendario());
            } else {
                dao.update(CalendarioDBDef.class, calendarioDTO);
            }
        }

        // Salva i periodi di validita'
        if (calendario.getPeriodiValidita() != null) {

            if (SEZIONE_DATI_GENERALI.equals(sezione)) {
                // Controlla che non ci siano intersezioni
                for (int i = 0; i < calendario.getPeriodiValidita().size(); i++) {
                    Date dataInizio1 = calendario.getPeriodiValidita().get(i).getDataDa();
                    Date dataFine1 = calendario.getPeriodiValidita().get(i).getDataA();
                    if (dataInizio1.equals(dataFine1) || dataInizio1.after(dataFine1)) {
                        throw new BusinessException(MessaggiUtils.ME053);
                    }
                    for (int j = 0; j < calendario.getPeriodiValidita().size(); j++) {
                        if (i == j)
                            continue;
                        Date dataInizio2 = calendario.getPeriodiValidita().get(j).getDataDa();
                        Date dataFine2 = calendario.getPeriodiValidita().get(j).getDataA();
                        if (SilTimeUtils.isConflittoTraIntervalliTemporaliEstremiCompresi(dataInizio1, dataFine1, dataInizio2, dataFine2)) {
                            throw new BusinessException(MessaggiUtils.ME052);
                        }
                    }
                }
            }

            // Salva i periodi
            for (ConfigurazioneCalendarioPeriodoValidita periodo : calendario.getPeriodiValidita()) {
                savePeriodo(sezione, calendarioDTO, periodo, codiceFiscaleUtente);
            }
        }

        if (SEZIONE_DATI_OPERATIVI.equals(sezione)) {
            saveDatiOperativi(calendarioDTO, calendario.getDatiOperativi(), codiceFiscaleUtente);
        }

        if (SEZIONE_DATI_MAIL.equals(sezione)) {
            saveConfigurazioneMail(calendarioDTO, calendario, codiceFiscaleUtente);
        }
    }

    /**
     * Save dati operativi.
     *
     * @param calendarioDTO the calendario DTO
     * @param datiOperativi the dati operativi
     * @param codiceFiscaleUtente the codice fiscale utente
     * @throws Exception the exception
     */
    private void saveDatiOperativi(CalendarioDTO calendarioDTO, ConfigurazioneCalendarioDatiOperativi datiOperativi, String codiceFiscaleUtente)
            throws Exception {
        if (datiOperativi.getFlagVisibilitaCpi() != null)
            calendarioDTO.setFlgVisibileCpi(datiOperativi.getFlagVisibilitaCpi());
        if (datiOperativi.isFlagAnnullamento())
            calendarioDTO.setFlgAnnullaGaranziaGiovani("S");
        else
            calendarioDTO.setFlgAnnullaGaranziaGiovani(null);
        calendarioDTO.setOreLimiteSpostamento(datiOperativi.getOreLimiteSpostamento());
        dao.update(CalendarioDBDef.class, calendarioDTO);
        Map<String, MessaggioCalendarioDTO> messaggi = new HashMap<>();
        MessaggioCalendarioFilter messaggioCalendarioFilter = new MessaggioCalendarioFilter();
        messaggioCalendarioFilter.getIdCalendario().eq(calendarioDTO.getIdCalendario());
        messaggioCalendarioFilter.getCodTipoMessaggioCalendario()
                .in(new String[] { MessaggioCalendarioDTO.SPOSTAMENTO_APPUNTAMENTO, MessaggioCalendarioDTO.ANNULLAMENTO_APPUNTAMENTO });
        for (MessaggioCalendarioDTO msgCalDTO : dao.findAll(MessaggioCalendarioDBDef.class, messaggioCalendarioFilter, 0)) {
            messaggi.put(msgCalDTO.getCodTipoMessaggioCalendario(), msgCalDTO);
        }
        MessaggioCalendarioDTO spostamentoDTO = messaggi.get(MessaggioCalendarioDTO.SPOSTAMENTO_APPUNTAMENTO);
        if (!StringUtils.isEmpty(datiOperativi.getMessaggioSpostamento()) && spostamentoDTO == null) {
            spostamentoDTO = new MessaggioCalendarioDTO();
            messaggi.put(MessaggioCalendarioDTO.SPOSTAMENTO_APPUNTAMENTO, spostamentoDTO);
        }
        MessaggioCalendarioDTO annullamentoDTO = messaggi.get(MessaggioCalendarioDTO.ANNULLAMENTO_APPUNTAMENTO);
        if (!StringUtils.isEmpty(datiOperativi.getMessaggioAnnullamento()) && annullamentoDTO == null) {
            annullamentoDTO = new MessaggioCalendarioDTO();
            messaggi.put(MessaggioCalendarioDTO.ANNULLAMENTO_APPUNTAMENTO, annullamentoDTO);
        }
        if (spostamentoDTO != null) {
            spostamentoDTO.setTesto(datiOperativi.getMessaggioSpostamento());
        }
        if (annullamentoDTO != null) {
            annullamentoDTO.setTesto(datiOperativi.getMessaggioAnnullamento());
        }
        // Salva i messaggi
        for (Map.Entry<String, MessaggioCalendarioDTO> entry : messaggi.entrySet()) {
            MessaggioCalendarioDTO msgDTO = entry.getValue();
            msgDTO.setCodUserAggiorn(codiceFiscaleUtente);
            msgDTO.setDAggiorn(new Date());
            if (msgDTO.getIdMessaggio() == null) {
                msgDTO.setCodUserInserim(codiceFiscaleUtente);
                msgDTO.setDInserim(new Date());
                msgDTO.setIdCalendario(calendarioDTO.getIdCalendario());
                msgDTO.setCodTipoMessaggioCalendario(entry.getKey());
                dao.insert(MessaggioCalendarioDBDef.class, msgDTO);
            } else {
                if (StringUtils.isEmpty(msgDTO.getTesto())) {
                    dao.delete(MessaggioCalendarioDBDef.class, msgDTO);
                } else {
                    dao.update(MessaggioCalendarioDBDef.class, msgDTO);
                }
            }
        }
    }

    /**
     * Save periodo.
     *
     * @param sezione the sezione
     * @param calendarioDTO the calendario DTO
     * @param periodo the periodo
     * @param codiceFiscaleUtente the codice fiscale utente
     * @throws Exception the exception
     */
    private void savePeriodo(String sezione, CalendarioDTO calendarioDTO, ConfigurazioneCalendarioPeriodoValidita periodo, String codiceFiscaleUtente)
            throws Exception {

        // Crea / recupera il periodo
        PeriodoDTO periodoDTO = null;
        if (periodo.getIdPeriodo() == null) {
            periodoDTO = new PeriodoDTO();
            periodoDTO.setCalendario(calendarioDTO);
            periodoDTO.setDInserim(new Date());
            periodoDTO.setCodUserInserim(codiceFiscaleUtente);
        } else {
            PeriodoFilter periodoFilter = new PeriodoFilter();
            periodoFilter.getIdPeriodo().eq(periodo.getIdPeriodo());
            periodoDTO = dao.findFirst(PeriodoDBDef.class, periodoFilter);
        }

        if (SEZIONE_DATI_GENERALI.equals(sezione)) {
            // Controlli sui dati
            if (periodo.getDataA().before(periodo.getDataDa())) {
                throw new BusinessException(MessaggiUtils.ME053);
            }

            // Imposta i dati
            periodoDTO.setDtInizio(periodo.getDataDa());
            periodoDTO.setDtFine(periodo.getDataA());
            periodoDTO.setCodUserAggiorn(codiceFiscaleUtente);
            periodoDTO.setDAggiorn(new Date());

            // Salva il periodo
            if (periodoDTO.getIdPeriodo() == null) {
                periodoDTO = dao.insert(PeriodoDBDef.class, periodoDTO);
                periodo.setIdPeriodo(periodoDTO.getIdPeriodo());
            } else {
                dao.update(PeriodoDBDef.class, periodoDTO);
            }
        }

        if (SEZIONE_DATI_FASCE.equals(sezione) && periodo.getFasce() != null) {

            // Verifica che le fasce non si sovrappongano
            for (int i = 0; i < periodo.getFasce().size(); i++) {
                Integer da1 = periodo.getFasce().get(i).getOraInizio();
                Integer a1 = periodo.getFasce().get(i).getOraFine();
                if (da1 >= a1) {
                    throw new BusinessException(MessaggiUtils.ME055);
                }
                for (int j = 0; j < periodo.getFasce().size(); j++) {
                    if (i == j)
                        continue;
                    if (periodo.getFasce().get(i).getIdGiornoSettimana().equals(periodo.getFasce().get(j).getIdGiornoSettimana())) {
                        Integer da2 = periodo.getFasce().get(j).getOraInizio();
                        Integer a2 = periodo.getFasce().get(j).getOraFine();
                        // modifica richiesta su anomalia Gest_Operat_01
                        if ((da1 > da2 && da1 < a2) || (a1 > da2 && a1 < a2)) {
                            throw new BusinessException(MessaggiUtils.ME054);
                        }
                    }
                }
            }

            // Salva le fasce
            for (ConfigurazioneCalendarioFascia fascia : periodo.getFasce()) {
                saveFascia(calendarioDTO, periodoDTO, fascia, codiceFiscaleUtente);
            }
        }

        if (SEZIONE_DATI_ECCEZIONI.equals(sezione) && periodo.getEccezioni() != null) {

            // Verifica i dati
            Date now = new Date();
            Date nowDay = DateUtils.truncate(now, Calendar.DATE);
            for (ConfigurazioneCalendarioEccezione eccezione : periodo.getEccezioni()) {
                if (eccezione.getIdEccezione() == null) {
                    Date eccezioneDay = DateUtils.truncate(eccezione.getGiorno(), Calendar.DATE);
                    if (eccezioneDay.before(nowDay)) {
                        throw new BusinessException(MessaggiUtils.ME073);
                    }
                    EccezioneFilter filterEccezioni = new EccezioneFilter();
                    filterEccezioni.getIdPeriodo().eq(periodoDTO.getIdPeriodo());
                    filterEccezioni.getFlgElaborata().eq(null);
                    for (EccezioneDTO eccezioneDTO : dao.findAll(EccezioneDBDef.class, filterEccezioni, 0)) {
                        log.info("[OperatoreApiServiceImpl::saveEccezione] eccezioneDTO.getGiorno()=" + eccezioneDTO.getGiorno() + ", eccezione.getGiorno()="
                                + eccezione.getGiorno());
                        if (eccezione.getGiorno().compareTo(eccezioneDTO.getGiorno()) == 0) {
                            log.info("[OperatoreApiServiceImpl::saveEccezione] 1");
                            if (eccezioneDTO.getOraInizio() == null) {
                                log.info("[OperatoreApiServiceImpl::saveEccezione] 2");
                                throw new BusinessException(MessaggiUtils.ME074);
                            } else {
                                if (eccezioneDTO.getOraInizio() == null) {
                                    log.info("[OperatoreApiServiceImpl::saveEccezione] 3");
                                    throw new BusinessException(MessaggiUtils.ME074);
                                }
                            }
                        }
                    }
                }
            }

            // Salva le eccezioni
            for (ConfigurazioneCalendarioEccezione eccezione : periodo.getEccezioni()) {
                saveEccezione(calendarioDTO, periodoDTO, eccezione, codiceFiscaleUtente);
            }
        }

    }

    /**
     * Save fascia.
     *
     * @param calendarioDTO the calendario DTO
     * @param periodoDTO the periodo DTO
     * @param fascia the fascia
     * @param codiceFiscaleUtente the codice fiscale utente
     * @throws Exception the exception
     */
    private void saveFascia(CalendarioDTO calendarioDTO, PeriodoDTO periodoDTO, ConfigurazioneCalendarioFascia fascia, String codiceFiscaleUtente)
            throws Exception {

        // Controlli sui dati
        if (fascia.getOraInizio() >= fascia.getOraFine()) {
            throw new BusinessException(MessaggiUtils.ME053);
        }

        // Crea / recupera la fascia
        PeriodoGiornoSettDTO fasciaDTO = null;
        if (fascia.getIdFascia() == null) {
            fasciaDTO = new PeriodoGiornoSettDTO();
            fasciaDTO.setIdPeriodo(periodoDTO.getIdPeriodo());
            fasciaDTO.setDInserim(new Date());
            fasciaDTO.setCodUserInserim(codiceFiscaleUtente);
        } else {
            PeriodoGiornoSettFilter fasciaFilter = new PeriodoGiornoSettFilter();
            fasciaFilter.getIdPeriodoGiornoSett().eq(fascia.getIdFascia());
            fasciaDTO = dao.findFirst(PeriodoGiornoSettDBDef.class, fasciaFilter);
        }

        // Imposta i dati
        fasciaDTO.setIdGiornoSettimana(fascia.getIdGiornoSettimana());
        fasciaDTO.setOraInizio(fascia.getOraInizio());
        fasciaDTO.setOraFine(fascia.getOraFine());
        fasciaDTO.setDurataSlot(fascia.getDurataSlot());
        fasciaDTO.setNumMaxPrenotazioni(fascia.getNumMaxPrenotazioni());
        fasciaDTO.setCodUserAggiorn(codiceFiscaleUtente);
        fasciaDTO.setDAggiorn(new Date());

        // Salva la fascia
        if (fasciaDTO.getIdPeriodoGiornoSett() == null) {
            fasciaDTO = dao.insert(PeriodoGiornoSettDBDef.class, fasciaDTO);
            fascia.setIdFascia(fasciaDTO.getIdPeriodoGiornoSett());
        } else {
            dao.update(PeriodoGiornoSettDBDef.class, fasciaDTO);
        }
    }

    /**
     * Save eccezione.
     *
     * @param calendarioDTO the calendario DTO
     * @param periodoDTO the periodo DTO
     * @param eccezione the eccezione
     * @param codiceFiscaleUtente the codice fiscale utente
     * @throws Exception the exception
     */
    private void saveEccezione(CalendarioDTO calendarioDTO, PeriodoDTO periodoDTO, ConfigurazioneCalendarioEccezione eccezione, String codiceFiscaleUtente)
            throws Exception {

        if (eccezione.isFlagElaborata() != null && eccezione.isFlagElaborata())
            return;

        // Crea / recupera l'eccezione
        EccezioneDTO eccezioneDTO = null;
        if (eccezione.getIdEccezione() == null) {
            eccezioneDTO = new EccezioneDTO();
            eccezioneDTO.setIdPeriodo(periodoDTO.getIdPeriodo());
        } else {
            EccezioneFilter eccezioneFilter = new EccezioneFilter();
            eccezioneFilter.getIdEccezione().eq(eccezione.getIdEccezione());
            eccezioneDTO = dao.findFirst(EccezioneDBDef.class, eccezioneFilter);
        }

        // Imposta i dati
        eccezioneDTO.setGiorno(eccezione.getGiorno());
        eccezioneDTO.setOraInizio(eccezione.getOraInizio());
        eccezioneDTO.setOraFine(eccezione.getOraFine());
        eccezioneDTO.setNumMaxPrenotazioni(eccezione.getNumMaxPrenotazioni());

        // Salva l'eccezione
        if (eccezioneDTO.getIdEccezione() == null) {
            eccezioneDTO = dao.insert(EccezioneDBDef.class, eccezioneDTO);
            eccezione.setIdEccezione(eccezioneDTO.getIdPeriodo());
        } else {
            dao.update(EccezioneDBDef.class, eccezioneDTO);
        }

        // Aggiorna lo slot relativo
        SlotFilter slotFilter = new SlotFilter();
        slotFilter.getGiorno().getPeriodo().getIdPeriodo().eq(periodoDTO.getIdPeriodo());
        Date giorno1 = DateUtils.truncate(eccezione.getGiorno(), Calendar.DATE);
        // Giordano G 16/7/2020 provo non between non deve selezionare due giorni
        slotFilter.getGiorno().getGiorno().eq(giorno1);
        slotFilter.getOraInizio().eq(eccezione.getOraInizio());
        slotFilter.getOraFine().eq(eccezione.getOraFine());
        QueryResult<SlotDBDef> slotsDTO = dao.findAll(SlotDBDef.class, slotFilter, 0);
        SimpleDateFormat df = new SimpleDateFormat(DD_MM_YYYY);
        for (SlotDTO slotDTO : slotsDTO) {
            if (eccezione.getNumMaxPrenotazioni() < slotDTO.getNumPrenotazioniValide()) {
                String giorno = df.format(slotDTO.getGiorno().getGiorno());
                String ore = slotDTO.getDescrizioneOraInizio();
                String prenotazioni = "" + slotDTO.getNumPrenotazioniValide();
                throw new BusinessException(MessaggiUtils.ME059, giorno, ore, prenotazioni);
            } else {
                slotDTO.setNumMaxPrenotazioni(eccezione.getNumMaxPrenotazioni());
                slotDTO.setFlgEccezione("S");
                dao.update(SlotDBDef.class, slotDTO);
            }
        }
        if (slotsDTO.size() > 0) {
            eccezioneDTO.setFlgElaborata("S");
            eccezione.setFlagElaborata(true);
            dao.update(EccezioneDBDef.class, eccezioneDTO);
        }

    }

    /**
     * Save configurazione mail.
     *
     * @param calendarioDTO the calendario DTO
     * @param calendario the calendario
     * @param codiceFiscaleUtente the codice fiscale utente
     * @throws Exception the exception
     */
    private void saveConfigurazioneMail(CalendarioDTO calendarioDTO, ConfigurazioneCalendario calendario, String codiceFiscaleUtente) throws Exception {

        Date now = new Date();

        // [Gianluca G] 18/12/19
        calendarioDTO.setOreInvioPromemoria(calendario.getMail().getOreInvioMailPromemoriaPrenotazione());
        if (calendario.getMail().isFlagInvioMailConfermaPrenotazione()) {
            calendarioDTO.setFlgInvioConfermaPrenotaz("S");
        } else {
            calendarioDTO.setFlgInvioConfermaPrenotaz("N");
        }
        calendarioDTO.setOggettoCalendario(calendario.getMail().getOggettoMail());
        calendarioDTO.setCodUserAggiorn(codiceFiscaleUtente);
        calendarioDTO.setDAggiorn(now);
        dao.update(CalendarioDBDef.class, calendarioDTO);

        // Crea / recupera i messaggi aggiuntivi
        String[] tipologieMail = { MessaggioAggiuntivoDTO.MAIL_CONFERMA, MessaggioAggiuntivoDTO.MAIL_ANNULLAMENTO, MessaggioAggiuntivoDTO.MAIL_NON_PRESENTATO,
                MessaggioAggiuntivoDTO.MAIL_PROMEMORIA };
        Map<String, MessaggioDTO> messaggi = new HashMap<>();
        Map<String, MessaggioAggiuntivoDTO> messaggiAggiuntivi = new HashMap<>();
        MessaggioFilter messaggioFilter = new MessaggioFilter();
        messaggioFilter.getCodAmbito().eq(calendarioDTO.getAmbito().getCodAmbito());
        messaggioFilter.getCodTipoMessaggio().in(tipologieMail);
        for (MessaggioDTO messaggioDTO : dao.findAll(MessaggioDBDef.class, messaggioFilter, 0)) {
            Date dataInizio = messaggioDTO.getdInizio();
            Date dataFine = messaggioDTO.getdFine();
            if (dataInizio != null && dataInizio.after(now))
                continue;
            if (dataFine != null && dataFine.before(now))
                continue;
            messaggi.put(messaggioDTO.getCodTipoMessaggio(), messaggioDTO);
        }
        MessaggioAggiuntivoFilter messaggioAggiuntivoFilter = new MessaggioAggiuntivoFilter();
        messaggioAggiuntivoFilter.getCalendario().getIdCalendario().eq(calendarioDTO.getIdCalendario());
        messaggioAggiuntivoFilter.getMessaggio().getCodTipoMessaggio().in(tipologieMail);
        for (MessaggioAggiuntivoDTO msgAggDTO : dao.findAll(MessaggioAggiuntivoDBDef.class, messaggioAggiuntivoFilter, 0)) {
            messaggiAggiuntivi.put(msgAggDTO.getMessaggio().getCodTipoMessaggio(), msgAggDTO);
        }

        MessaggioAggiuntivoDTO confermaDTO = messaggiAggiuntivi.get(MessaggioAggiuntivoDTO.MAIL_CONFERMA);
        if (!StringUtils.isEmpty(calendario.getMail().getMessaggioAggiuntivoMailConferma()) && confermaDTO == null) {
            confermaDTO = new MessaggioAggiuntivoDTO();
            messaggiAggiuntivi.put(MessaggioAggiuntivoDTO.MAIL_CONFERMA, confermaDTO);
        }
        MessaggioAggiuntivoDTO annullamentoDTO = messaggiAggiuntivi.get(MessaggioAggiuntivoDTO.MAIL_ANNULLAMENTO);
        if (!StringUtils.isEmpty(calendario.getMail().getMessaggioAggiuntivoMailAnnullamento()) && annullamentoDTO == null) {
            annullamentoDTO = new MessaggioAggiuntivoDTO();
            messaggiAggiuntivi.put(MessaggioAggiuntivoDTO.MAIL_ANNULLAMENTO, annullamentoDTO);
        }
        MessaggioAggiuntivoDTO nonPresentatoDTO = messaggiAggiuntivi.get(MessaggioAggiuntivoDTO.MAIL_NON_PRESENTATO);
        if (!StringUtils.isEmpty(calendario.getMail().getMessaggioAggiuntivoMailNonPresentato()) && nonPresentatoDTO == null) {
            nonPresentatoDTO = new MessaggioAggiuntivoDTO();
            messaggiAggiuntivi.put(MessaggioAggiuntivoDTO.MAIL_NON_PRESENTATO, nonPresentatoDTO);
        }
        MessaggioAggiuntivoDTO promemoriaDTO = messaggiAggiuntivi.get(MessaggioAggiuntivoDTO.MAIL_PROMEMORIA);
        if (!StringUtils.isEmpty(calendario.getMail().getMessaggioAggiuntivoMailPromemoria()) && promemoriaDTO == null) {
            promemoriaDTO = new MessaggioAggiuntivoDTO();
            messaggiAggiuntivi.put(MessaggioAggiuntivoDTO.MAIL_PROMEMORIA, promemoriaDTO);
        }

        // Imposta i dati
        if (confermaDTO != null) {
            confermaDTO.setTesto(calendario.getMail().getMessaggioAggiuntivoMailConferma());
        }
        if (annullamentoDTO != null) {
            annullamentoDTO.setTesto(calendario.getMail().getMessaggioAggiuntivoMailAnnullamento());
        }
        if (nonPresentatoDTO != null) {
            nonPresentatoDTO.setTesto(calendario.getMail().getMessaggioAggiuntivoMailNonPresentato());
        }
        if (promemoriaDTO != null) {
            promemoriaDTO.setTesto(calendario.getMail().getMessaggioAggiuntivoMailPromemoria());
        }

        // Salva i messaggi aggiuntivi
        for (Map.Entry<String, MessaggioAggiuntivoDTO> entry : messaggiAggiuntivi.entrySet()) {
            MessaggioAggiuntivoDTO msgAggDTO = entry.getValue();
            msgAggDTO.setCodUserAggiorn(codiceFiscaleUtente);
            msgAggDTO.setDAggiorn(now);
            if (msgAggDTO.getIdMessaggioAggiuntivo() == null) {
                msgAggDTO.setCodUserInserim(codiceFiscaleUtente);
                msgAggDTO.setDInserim(now);
                msgAggDTO.setCalendario(calendarioDTO);
                msgAggDTO.setMessaggio(messaggi.get(entry.getKey()));
                dao.insert(MessaggioAggiuntivoDBDef.class, msgAggDTO);
            } else {
                if (StringUtils.isEmpty(msgAggDTO.getTesto())) {
                    dao.delete(MessaggioAggiuntivoDBDef.class, msgAggDTO);
                } else {
                    dao.update(MessaggioAggiuntivoDBDef.class, msgAggDTO);
                }
            }
        }
    }

    /**
     * Save calendario mail.
     *
     * @param idUtente the id utente
     * @param configurazioneCalendario the configurazione calendario
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response saveCalendarioMail(Long idUtente, ConfigurazioneCalendario configurazioneCalendario, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::saveCalendarioMail] idUtente=" + idUtente + CONFIGURAZIONE_CALENDARIO + configurazioneCalendario);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.SAVE_CALENDARIO_MAIL, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            saveCalendario(SEZIONE_DATI_MAIL, configurazioneCalendario, codiceFiscaleUtente);
            configurazioneCalendario = loadCalendario(configurazioneCalendario.getIdCalendario());
            return Response.ok(configurazioneCalendario).build();
        } catch (BusinessException ex) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("452", ex.getCode(), ex.getArgs());
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::saveCalendarioMail] idUtente=" + idUtente + CONFIGURAZIONE_CALENDARIO2 + configurazioneCalendario, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_CALENDARIO_MAIL, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    configurazioneCalendario.getCodiceAmbito());
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "saveCalendarioMail()", "invocazione API OperatoreApiServiceImpl.saveCalendarioMail",
                    "");
            watcher.stop();
        }
    }

    /**
     * Load calendario.
     *
     * @param idUtente the id utente
     * @param idCalendario the id calendario
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    public Response loadCalendario(Long idUtente, Long idCalendario, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::loadCalendario] idUtente=" + idCalendario + "conf=" + idCalendario);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.LOAD_CALENDARIO, idUtente);
            ConfigurazioneCalendario result = loadCalendario(idCalendario);
            if (result == null) {
                return createErrorResponse("401", MessaggiUtils.ME002);
            }
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::loadCalendario] idUtente=" + idUtente + ID_CALENDARIO2 + idCalendario, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.LOAD_CALENDARIO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "loadCalendario()", "invocazione API OperatoreApiServiceImpl.loadCalendario", "");
            watcher.stop();
        }
    }

    /**
     * Load calendario.
     *
     * @param idCalendario the id calendario
     * @return the configurazione calendario
     * @throws Exception the exception
     */
    private ConfigurazioneCalendario loadCalendario(Long idCalendario) throws Exception {
        CalendarioFilter calendarioFilter = new CalendarioFilter();
        calendarioFilter.getIdCalendario().eq(idCalendario);
        CalendarioDTO calendarioDTO = dao.findFirst(CalendarioDBDef.class, calendarioFilter);
        if (calendarioDTO == null) {
            return null;
        }
        ConfigurazioneCalendario result = new ConfigurazioneCalendario();
        result.setIdCalendario(calendarioDTO.getIdCalendario());
        result.setGruppoOperatore(calendarioDTO.getGruppoOperatore());
        result.setCodiceOperatore(calendarioDTO.getCodOperatore());
        result.setSubcodice(calendarioDTO.getSubcodice());
        result.setNome(calendarioDTO.getNome());
        result.setCodiceAmbito(calendarioDTO.getAmbito().getCodAmbito());
        ConfigurazioneCalendarioDatiOperativi datiOperativi = new ConfigurazioneCalendarioDatiOperativi();
        result.setDatiOperativi(datiOperativi);
        datiOperativi.setFlagAnnullamento("S".equals(calendarioDTO.getFlgAnnullaGaranziaGiovani()));
        datiOperativi.setFlagVisibilitaCpi(calendarioDTO.getFlgVisibileCpi());
        ConfigurazioneCalendarioMail mail = new ConfigurazioneCalendarioMail();
        MessaggioAggiuntivoFilter messaggioAggiuntivoFilter = new MessaggioAggiuntivoFilter();
        messaggioAggiuntivoFilter.getCalendario().getIdCalendario().eq(idCalendario);
        Date now = new Date();
        for (MessaggioAggiuntivoDTO msgAggDTO : dao.findAll(MessaggioAggiuntivoDBDef.class, messaggioAggiuntivoFilter, 0)) {
            Date dataInizio = msgAggDTO.getMessaggio().getdInizio();
            Date dataFine = msgAggDTO.getMessaggio().getdFine();
            if (dataInizio != null && dataInizio.after(now))
                continue;
            if (dataFine != null && dataFine.before(now))
                continue;
            switch (msgAggDTO.getMessaggio().getCodTipoMessaggio()) {
            case MessaggioAggiuntivoDTO.MAIL_CONFERMA:
                mail.setMessaggioAggiuntivoMailConferma(msgAggDTO.getTesto());
                break;
            case MessaggioAggiuntivoDTO.MAIL_ANNULLAMENTO:
                mail.setMessaggioAggiuntivoMailAnnullamento(msgAggDTO.getTesto());
                break;
            case MessaggioAggiuntivoDTO.MAIL_NON_PRESENTATO:
                mail.setMessaggioAggiuntivoMailNonPresentato(msgAggDTO.getTesto());
                break;
            case MessaggioAggiuntivoDTO.MAIL_PROMEMORIA:
                mail.setMessaggioAggiuntivoMailPromemoria(msgAggDTO.getTesto());
                break;
            default:
                break;
            }
        }

        mail.setFlagInvioMailConfermaPrenotazione("S".equals(calendarioDTO.getFlgInvioConfermaPrenotaz()));
        mail.setOreInvioMailPromemoriaPrenotazione(calendarioDTO.getOreInvioPromemoria());
        mail.setOggettoMail(calendarioDTO.getOggettoCalendario());
        result.setMail(mail);
        MessaggioCalendarioFilter messaggioCalendarioFilter = new MessaggioCalendarioFilter();
        messaggioCalendarioFilter.getIdCalendario().eq(idCalendario);
        for (MessaggioCalendarioDTO msgCalDTO : dao.findAll(MessaggioCalendarioDBDef.class, messaggioCalendarioFilter, 0)) {
            switch (msgCalDTO.getCodTipoMessaggioCalendario()) {
            case MessaggioCalendarioDTO.SPOSTAMENTO_APPUNTAMENTO:
                datiOperativi.setMessaggioSpostamento(msgCalDTO.getTesto());
                break;
            case MessaggioCalendarioDTO.ANNULLAMENTO_APPUNTAMENTO:
                datiOperativi.setMessaggioAnnullamento(msgCalDTO.getTesto());
                break;
            default:
                break;
            }
        }
        datiOperativi.setOreLimiteSpostamento(calendarioDTO.getOreLimiteSpostamento());
        List<ConfigurazioneCalendarioPeriodoValidita> periodiValidita = new ArrayList<>();
        PeriodoFilter periodoFilter = new PeriodoFilter();
        periodoFilter.getCalendario().getIdCalendario().eq(idCalendario);
        periodoFilter.getDtInizio().setOrderDesc(1);
        for (PeriodoDTO periodoDTO : dao.findAll(PeriodoDBDef.class, periodoFilter, 0)) {
            ConfigurazioneCalendarioPeriodoValidita periodo = new ConfigurazioneCalendarioPeriodoValidita();
            periodo.setIdPeriodo(periodoDTO.getIdPeriodo());
            periodo.setDataDa(periodoDTO.getDtInizio());
            periodo.setDataA(periodoDTO.getDtFine());
            Map<String, Object> params = new HashMap<>();
            params.put("idPeriodo", periodoDTO.getIdPeriodo());
            String flagSlot = dao.readString("be/impl/OperatoreApiServiceImpl.esistonoSlotPerPeriodo", params);
            periodo.setFlagSlotGenerati("S".equals(flagSlot));
            List<ConfigurazioneCalendarioFascia> fasce = new ArrayList<>();
            PeriodoGiornoSettFilter fasciaFilter = new PeriodoGiornoSettFilter();
            fasciaFilter.getIdPeriodo().eq(periodoDTO.getIdPeriodo());
            fasciaFilter.getOraInizio().setOrderAsc(1);
            for (PeriodoGiornoSettDTO fasciaDTO : dao.findAll(PeriodoGiornoSettDBDef.class, fasciaFilter, 0)) {
                ConfigurazioneCalendarioFascia fascia = new ConfigurazioneCalendarioFascia();
                fascia.setIdFascia(fasciaDTO.getIdPeriodoGiornoSett());
                fascia.setIdGiornoSettimana(fasciaDTO.getIdGiornoSettimana());
                fascia.setDurataSlot(fasciaDTO.getDurataSlot());
                fascia.setNumMaxPrenotazioni(fasciaDTO.getNumMaxPrenotazioni());
                fascia.setOraInizio(fasciaDTO.getOraInizio());
                fascia.setOraFine(fasciaDTO.getOraFine());
                fasce.add(fascia);
            }
            periodo.setFasce(fasce);

            List<ConfigurazioneCalendarioEccezione> eccezioni = new ArrayList<>();
            EccezioneFilter eccezioneFilter = new EccezioneFilter();
            eccezioneFilter.getIdPeriodo().eq(periodoDTO.getIdPeriodo());
            eccezioneFilter.getGiorno().setOrderAsc(1);
            eccezioneFilter.getOraInizio().setOrderAsc(2);
            eccezioneFilter.getIdEccezione().setOrderAsc(3);
            for (EccezioneDTO eccezioneDTO : dao.findAll(EccezioneDBDef.class, eccezioneFilter, 0)) {
                ConfigurazioneCalendarioEccezione eccezione = new ConfigurazioneCalendarioEccezione();
                eccezione.setIdEccezione(eccezioneDTO.getIdEccezione());
                eccezione.setGiorno(eccezioneDTO.getGiorno());
                eccezione.setOraInizio(eccezioneDTO.getOraInizio());
                eccezione.setOraFine(eccezioneDTO.getOraFine());
                eccezione.setNumMaxPrenotazioni(eccezioneDTO.getNumMaxPrenotazioni());
                eccezione.setFlagElaborata("S".equals(eccezioneDTO.getFlgElaborata()));
                eccezioni.add(eccezione);
            }
            periodo.setEccezioni(eccezioni);

            periodiValidita.add(periodo);
        }
        result.setPeriodiValidita(periodiValidita);
        return result;
    }

    /**
     * Save calendario dati operativi.
     *
     * @param idUtente the id utente
     * @param configurazioneCalendario the configurazione calendario
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response saveCalendarioDatiOperativi(Long idUtente, ConfigurazioneCalendario configurazioneCalendario, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::saveCalendarioDatiOperativi] idUtente=" + idUtente + ",configurazioneCalendario=" + configurazioneCalendario);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.SAVE_CALENDARIO_DATI_OPERATIVI, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            saveCalendario(SEZIONE_DATI_OPERATIVI, configurazioneCalendario, codiceFiscaleUtente);
            configurazioneCalendario = loadCalendario(configurazioneCalendario.getIdCalendario());
            return Response.ok(configurazioneCalendario).build();
        } catch (BusinessException ex) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("452", ex.getCode(), ex.getArgs());
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::saveCalendarioDatiOperativi] idUtente=" + idUtente + CONFIGURAZIONE_CALENDARIO2 + configurazioneCalendario,
                    ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_CALENDARIO_DATI_OPERATIVI, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    configurazioneCalendario.getCodiceAmbito());
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "saveCalendarioDatiOperativi()",
                    "invocazione API OperatoreApiServiceImpl.saveCalendarioDatiOperativi", "");
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
        return createErrorResponse(code, messageCode, null);
    }

    /**
     * Creates the error response.
     *
     * @param code the code
     * @param messageCode the message code
     * @param args the args
     * @return the response
     */
    protected Response createErrorResponse(String code, String messageCode, Object[] args) {
        ErrorDef err = new ErrorDef();
        err.setCode(code);
        String testo = messaggiUtils.loadMessaggioErrore(messageCode).getTesto();
        if (args != null) {
            testo = MessageFormat.format(testo, args);
        }
        err.setErrorMessage(testo);
        if ("404".equals(code)) {
            return Response.status(Response.Status.NOT_FOUND).entity(err).build();
        }
        return Response.serverError().entity(err).build();
    }

    /**
     * Creates the error response with text.
     *
     * @param code the code
     * @param testo the testo
     * @return the response
     */
    protected Response createErrorResponseWithText(String code, String testo) {
        ErrorDef err = new ErrorDef();
        err.setCode(code);
        err.setErrorMessage(testo);
        if ("404".equals(code)) {
            return Response.status(Response.Status.NOT_FOUND).entity(err).build();
        }
        return Response.serverError().entity(err).build();
    }

    /**
     * Imposta blocco calendario.
     *
     * @param idUtente the id utente
     * @param idCalendario the id calendario
     * @param flagBloccoCalendario the flag blocco calendario
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    public Response impostaBloccoCalendario(Long idUtente, Long idCalendario, Boolean flagBloccoCalendario, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::impostaBloccoCalendario] idUtente=" + idUtente + ",idCalendario=" + idCalendario + ",flagBloccoCalendario="
                + flagBloccoCalendario);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.IMPOSTA_BLOCCO_CALENDARIO, idUtente);
            CalendarioFilter calendarioFilter = new CalendarioFilter();
            calendarioFilter.getIdCalendario().eq(idCalendario);
            CalendarioDTO calendarioDTO = dao.findFirst(CalendarioDBDef.class, calendarioFilter);
            if (calendarioDTO == null) {
                return createErrorResponse("401", MessaggiUtils.ME002);
            }
            String flgBloccato = null;
            if (flagBloccoCalendario)
                flgBloccato = "S";
            else
                flgBloccato = null;
            calendarioDTO.setFlgBloccato(flgBloccato);
            dao.update(CalendarioDBDef.class, calendarioDTO);
            Esito esito = new Esito();
            esito.setCode("OK");
            if (Boolean.TRUE.equals(flagBloccoCalendario)) {
                esito.setMessaggioCittadino("Il calendario è stato bloccato");
            } else {
                esito.setMessaggioCittadino("Il calendario è stato sbloccato");
            }
            return Response.ok(esito).build();
        } catch (BusinessException ex) {
            return createErrorResponse("452", ex.getCode(), ex.getArgs());
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::impostaBloccoCalendario] idUtente=" + idUtente + ",idCalendario=" + idCalendario + ",flagBloccoCalendario="
                    + flagBloccoCalendario, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.IMPOSTA_BLOCCO_CALENDARIO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "impostaBloccoCalendario()",
                    "invocazione API OperatoreApiServiceImpl.impostaBloccoCalendario", "");
            watcher.stop();
        }
    }

    /**
     * Duplica calendario.
     *
     * @param idUtente the id utente
     * @param idCalendario the id calendario
     * @param parametriDuplicazioneCalendario the parametri duplicazione calendario
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response duplicaCalendario(Long idUtente, Long idCalendario, ParametriDuplicazioneCalendario parametriDuplicazioneCalendario,
            SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::duplicaCalendario] idUtente=" + idUtente + ID_CALENDARIO + idCalendario);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.DUPLICA_CALENDARIO, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);

            ConfigurazioneCalendario calendario = loadCalendario(idCalendario);
            Date now = new Date();
            CalendarioDTO calendarioDTO = new CalendarioDTO();
            calendarioDTO.setNome(parametriDuplicazioneCalendario.getNomeCalendario());
            String codiceAmbito = parametriDuplicazioneCalendario.getAmbito();
            if (codiceAmbito == null)
                codiceAmbito = calendario.getCodiceAmbito();

            // Verifica se esiste gia' un calendario attivo per ambito ed ente
            CalendarioFilter filter = new CalendarioFilter();
            filter.getDAnnullamento().eq(null);
            filter.getAmbito().getCodAmbito().eq(codiceAmbito);
            filter.getGruppoOperatore().eq(parametriDuplicazioneCalendario.getGruppoOperatore());
            filter.getCodOperatore().eq(parametriDuplicazioneCalendario.getCodiceOperatore());
            filter.getSubcodice().eq(parametriDuplicazioneCalendario.getSubcodice());
            if (dao.findFirst(CalendarioDBDef.class, filter) != null) {
                throw new BusinessException(MessaggiUtils.ME069);
            }

            calendarioDTO.setAmbito(new AmbitoDTO(codiceAmbito));
            calendarioDTO.setGruppoOperatore(parametriDuplicazioneCalendario.getGruppoOperatore());
            calendarioDTO.setCodOperatore(parametriDuplicazioneCalendario.getCodiceOperatore());
            calendarioDTO.setSubcodice(parametriDuplicazioneCalendario.getSubcodice());
            calendarioDTO.setFlgVisibileCpi(calendario.getDatiOperativi().getFlagVisibilitaCpi());
            if (calendario.getMail().isFlagInvioMailConfermaPrenotazione())
                calendarioDTO.setFlgInvioConfermaPrenotaz("S");
            else
                calendarioDTO.setFlgInvioConfermaPrenotaz("N");
            calendarioDTO.setDInserim(now);
            calendarioDTO.setDAggiorn(now);
            calendarioDTO.setCodUserInserim(codiceFiscaleUtente);
            calendarioDTO.setCodUserAggiorn(codiceFiscaleUtente);
            calendarioDTO = dao.insert(CalendarioDBDef.class, calendarioDTO);
            String[] tipologieMail = { MessaggioAggiuntivoDTO.MAIL_CONFERMA, MessaggioAggiuntivoDTO.MAIL_ANNULLAMENTO,
                    MessaggioAggiuntivoDTO.MAIL_NON_PRESENTATO, MessaggioAggiuntivoDTO.MAIL_PROMEMORIA };
            Map<String, MessaggioDTO> messaggi = new HashMap<>();
            MessaggioFilter messaggioFilter = new MessaggioFilter();
            messaggioFilter.getCodAmbito().eq(calendarioDTO.getAmbito().getCodAmbito());
            messaggioFilter.getCodTipoMessaggio().in(tipologieMail);
            for (MessaggioDTO messaggioDTO : dao.findAll(MessaggioDBDef.class, messaggioFilter, 0)) {
                Date dataInizio = messaggioDTO.getdInizio();
                Date dataFine = messaggioDTO.getdFine();
                if (dataInizio != null && dataInizio.after(now))
                    continue;
                if (dataFine != null && dataFine.before(now))
                    continue;
                messaggi.put(messaggioDTO.getCodTipoMessaggio(), messaggioDTO);
            }
            for (String tipologiaMail : tipologieMail) {
                String testo = null;
                switch (tipologiaMail) {
                case MessaggioAggiuntivoDTO.MAIL_CONFERMA:
                    testo = calendario.getMail().getMessaggioAggiuntivoMailConferma();
                    break;
                case MessaggioAggiuntivoDTO.MAIL_ANNULLAMENTO:
                    testo = calendario.getMail().getMessaggioAggiuntivoMailAnnullamento();
                    break;
                case MessaggioAggiuntivoDTO.MAIL_NON_PRESENTATO:
                    testo = calendario.getMail().getMessaggioAggiuntivoMailNonPresentato();
                    break;
                case MessaggioAggiuntivoDTO.MAIL_PROMEMORIA:
                    testo = calendario.getMail().getMessaggioAggiuntivoMailPromemoria();
                    break;
                default:
                    break;
                }
                if (testo != null) {
                    MessaggioAggiuntivoDTO messaggioDTO = new MessaggioAggiuntivoDTO();
                    messaggioDTO.setTesto(testo);
                    messaggioDTO.setCalendario(calendarioDTO);
                    messaggioDTO.setCodUserAggiorn(codiceFiscaleUtente);
                    messaggioDTO.setCodUserInserim(codiceFiscaleUtente);
                    messaggioDTO.setDAggiorn(now);
                    messaggioDTO.setDInserim(now);
                    messaggioDTO.setMessaggio(messaggi.get(tipologiaMail));
                    dao.insert(MessaggioAggiuntivoDBDef.class, messaggioDTO);
                }
            }
            if (parametriDuplicazioneCalendario.isDuplicaFasce()) {
                for (ConfigurazioneCalendarioPeriodoValidita periodo : calendario.getPeriodiValidita()) {
                    if (periodo.getDataA().before(now))
                        continue;
                    PeriodoDTO periodoDTO = new PeriodoDTO();
                    periodoDTO.setCalendario(calendarioDTO);
                    periodoDTO.setCodUserInserim(codiceFiscaleUtente);
                    periodoDTO.setCodUserAggiorn(codiceFiscaleUtente);
                    periodoDTO.setDInserim(now);
                    periodoDTO.setDInserim(now);
                    periodoDTO.setDtInizio(periodo.getDataDa());
                    if (periodo.getDataDa().before(now))
                        periodoDTO.setDtInizio(now);
                    periodoDTO.setDtFine(periodo.getDataA());
                    periodoDTO = dao.insert(PeriodoDBDef.class, periodoDTO);
                    for (ConfigurazioneCalendarioFascia fascia : periodo.getFasce()) {
                        PeriodoGiornoSettDTO fasciaDTO = new PeriodoGiornoSettDTO();
                        fasciaDTO.setIdPeriodo(periodoDTO.getIdPeriodo());
                        fasciaDTO.setOraInizio(fascia.getOraInizio());
                        fasciaDTO.setOraFine(fascia.getOraFine());
                        fasciaDTO.setDurataSlot(fascia.getDurataSlot());
                        fasciaDTO.setIdGiornoSettimana(fascia.getIdGiornoSettimana());
                        fasciaDTO.setNumMaxPrenotazioni(fascia.getNumMaxPrenotazioni());
                        fasciaDTO.setCodUserAggiorn(codiceFiscaleUtente);
                        fasciaDTO.setCodUserInserim(codiceFiscaleUtente);
                        fasciaDTO.setDAggiorn(new Date());
                        fasciaDTO.setDInserim(new Date());
                        fasciaDTO = dao.insert(PeriodoGiornoSettDBDef.class, fasciaDTO);
                    }
                    if (parametriDuplicazioneCalendario.isDuplicaEccezioni()) {
                        for (ConfigurazioneCalendarioEccezione eccezione : periodo.getEccezioni()) {
                            EccezioneDTO eccezioneDTO = new EccezioneDTO();
                            if (eccezione.isFlagElaborata())
                                eccezioneDTO.setFlgElaborata("S");
                            else
                                eccezioneDTO.setFlgElaborata(null);
                            eccezioneDTO.setGiorno(eccezione.getGiorno());
                            eccezioneDTO.setIdPeriodo(periodoDTO.getIdPeriodo());
                            eccezioneDTO.setNumMaxPrenotazioni(eccezione.getNumMaxPrenotazioni());
                            eccezioneDTO.setOraFine(eccezione.getOraFine());
                            eccezioneDTO.setOraInizio(eccezione.getOraInizio());
                            eccezioneDTO = dao.insert(EccezioneDBDef.class, eccezioneDTO);
                        }
                    }
                }
            }
            ConfigurazioneCalendario calendarioDuplicato = loadCalendario(calendarioDTO.getIdCalendario());
            return Response.ok(calendarioDuplicato).build();
        } catch (BusinessException ex) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("452", ex.getCode(), ex.getArgs());
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::duplicaCalendario] idUtente=" + idUtente + ID_CALENDARIO2 + idCalendario, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.DUPLICA_CALENDARIO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    parametriDuplicazioneCalendario.getAmbito());
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "duplicaCalendario()", "invocazione API OperatoreApiServiceImpl.duplicaCalendario",
                    "");
            watcher.stop();
        }
    }

    /**
     * Elimina calendario.
     *
     * @param idUtente the id utente
     * @param idCalendario the id calendario
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    public Response eliminaCalendario(Long idUtente, Long idCalendario, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::eliminaCalendario] idUtente=" + idUtente + ID_CALENDARIO + idCalendario);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.ELIMINA_CALENDARIO, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            PslOrchApiClient client = new PslOrchApiClient();
            ParametriAnnulloCalendario params = new ParametriAnnulloCalendario();
            params.setIdCalendario(idCalendario);
            params.setCodiceFiscaleOperatore(codiceFiscaleUtente);
            EsitoDTO esitoDTO = client.annullaCalendario(params);
            Esito esito = new Esito();
            esito.setCode(esitoDTO.getCodiceEsito());
            esito.setMessaggioCittadino(esitoDTO.getDescrizioneEsito());
            return Response.ok(esito).build();
        } catch (BusinessException ex) {
            return createErrorResponse("452", ex.getCode(), ex.getArgs());
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::eliminaCalendario] idUtente=" + idUtente + ID_CALENDARIO2 + idCalendario, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.ELIMINA_CALENDARIO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "eliminaCalendario()", "invocazione API OperatoreApiServiceImpl.eliminaCalendario",
                    "");
            watcher.stop();
        }
    }

    /**
     * Save calendario eccezioni.
     *
     * @param idUtente the id utente
     * @param configurazioneCalendario the configurazione calendario
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response saveCalendarioEccezioni(Long idUtente, ConfigurazioneCalendario configurazioneCalendario, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::saveCalendarioEccezioni] idUtente=" + idUtente + "v=" + configurazioneCalendario);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.SAVE_CALENDARIO_ECCEZIONI, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            saveCalendario(SEZIONE_DATI_ECCEZIONI, configurazioneCalendario, codiceFiscaleUtente);
            configurazioneCalendario = loadCalendario(configurazioneCalendario.getIdCalendario());
            return Response.ok(configurazioneCalendario).build();
        } catch (BusinessException ex) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("452", ex.getCode(), ex.getArgs());
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::saveCalendarioEccezioni] idUtente=" + idUtente + CONFIGURAZIONE_CALENDARIO2 + configurazioneCalendario, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_CALENDARIO_ECCEZIONI, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    configurazioneCalendario.getCodiceAmbito());
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "saveCalendarioEccezioni()",
                    "invocazione API OperatoreApiServiceImpl.saveCalendarioEccezioni", "");
            watcher.stop();
        }
    }

    /**
     * Save calendario fasce.
     *
     * @param idUtente the id utente
     * @param configurazioneCalendario the configurazione calendario
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response saveCalendarioFasce(Long idUtente, ConfigurazioneCalendario configurazioneCalendario, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::saveCalendarioFasce] idUtente=" + idUtente + CONFIGURAZIONE_CALENDARIO + configurazioneCalendario);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.SAVE_CALENDARIO_FASCE, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            saveCalendario(SEZIONE_DATI_FASCE, configurazioneCalendario, codiceFiscaleUtente);
            configurazioneCalendario = loadCalendario(configurazioneCalendario.getIdCalendario());
            return Response.ok(configurazioneCalendario).build();
        } catch (BusinessException ex) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("452", ex.getCode(), ex.getArgs());
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::saveCalendarioFasce] idUtente=" + idUtente + CONFIGURAZIONE_CALENDARIO2 + configurazioneCalendario, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_CALENDARIO_FASCE, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    configurazioneCalendario.getCodiceAmbito());
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "saveCalendarioFasce()", "invocazione API OperatoreApiServiceImpl.saveCalendarioFasce",
                    "");
            watcher.stop();
        }
    }

    /**
     * Genera slot.
     *
     * @param idUtente the id utente
     * @param idPeriodo the id periodo
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response generaSlot(Long idUtente, Long idPeriodo, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::generaSlot] idUtente=" + idUtente + "idPeriodo=" + idPeriodo);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.GENERA_SLOT, idUtente);
            String esitoGenerazioneSlot = dao.callFunctionPlSqlReturnString("be/impl/OperatoreApiServiceImpl.generaGiornoSlot", idPeriodo);
            log.info("[OperatoreApiServiceImpl::generaSlot] esitoGenerazioneSlot=" + esitoGenerazioneSlot);
            String esitoGenerazioneEccezioni = dao.callFunctionPlSqlReturnString("be/impl/OperatoreApiServiceImpl.elaboraEccezioni", idPeriodo);
            log.info("[OperatoreApiServiceImpl::generaSlot] esitoGenerazioneEccezioni=" + esitoGenerazioneEccezioni);

            Esito esito = new Esito();
            if ("0".equals(esitoGenerazioneSlot) && "0".equals(esitoGenerazioneEccezioni))
                esito.setCode("OK");
            else
                esito.setCode("KO");
            return Response.ok(esito).build();
        } catch (BusinessException ex) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("452", ex.getCode(), ex.getArgs());
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::generaSlot] idUtente=" + idUtente + ", idPeriodo=" + idPeriodo, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GENERA_SLOT, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "generaSlot()", "invocazione API OperatoreApiServiceImpl.generaSlot", "");
            watcher.stop();
        }
    }

    /**
     * Verifica vincoli eliminazione calendario.
     *
     * @param idUtente the id utente
     * @param idCalendario the id calendario
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    public Response verificaVincoliEliminazioneCalendario(Long idUtente, Long idCalendario, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::verificaVincoliEliminazioneCalendario] idUtente=" + idUtente + ID_CALENDARIO + idCalendario);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.VERIFICA_VINCOLI_ELIMINAZIONE_CALENDARIO, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            List<PrenotazioneIncontro> prenotazioniDaErogare = findIncontriCalendario(idUtente, idCalendario, null, codiceFiscaleUtente);
            Esito esito = new Esito();
            if (!prenotazioniDaErogare.isEmpty()) {
                esito.setCode("WN");
                if (prenotazioniDaErogare.size() == 1) {
                    esito.setMessaggioCittadino("E' presente un incontro in stato 'Da Erogare' che verra' annullato.");
                } else {
                    esito.setMessaggioCittadino("Sono presenti " + prenotazioniDaErogare.size() + " incontri in stato 'Da Erogare' che verranno annullati.");
                }
            } else {
                esito.setCode("OK");
            }
            return Response.ok(esito).build();
        } catch (BusinessException ex) {
            return createErrorResponse("452", ex.getCode(), ex.getArgs());
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::verificaVincoliEliminazioneCalendario] idUtente=" + idUtente + ID_CALENDARIO2 + idCalendario, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.VERIFICA_VINCOLI_ELIMINAZIONE_CALENDARIO, httpRequest, idUtente,
                    ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "verificaVincoliEliminazioneCalendario()",
                    "invocazione API OperatoreApiServiceImpl.verificaVincoliEliminazioneCalendario", "");
            watcher.stop();
        }
    }

    /**
     * Find incontri calendario.
     *
     * @param idUtente the id utente
     * @param idCalendario the id calendario
     * @param parametriRicercaIncontriCalendario the parametri ricerca incontri calendario
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    public Response findIncontriCalendario(Long idUtente, Long idCalendario, ParametriRicercaIncontriCalendario parametriRicercaIncontriCalendario,
            SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::findIncontriCalendario] idUtente=" + idUtente + ID_CALENDARIO + idCalendario + PARAMETRI_RICERCA_INCONTRI_CALENDARIO
                + parametriRicercaIncontriCalendario);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.FIND_INCONTRI_CALENDARIO, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            List<PrenotazioneIncontro> prenotazioniDaErogare = findIncontriCalendario(idUtente, idCalendario, parametriRicercaIncontriCalendario,
                    codiceFiscaleUtente);
            return Response.ok(prenotazioniDaErogare).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::findIncontriCalendario] idUtente=" + idUtente + ID_CALENDARIO2 + idCalendario
                    + PARAMETRI_RICERCA_INCONTRI_CALENDARIO + parametriRicercaIncontriCalendario, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.FIND_INCONTRI_CALENDARIO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "findIncontriCalendario()",
                    "invocazione API OperatoreApiServiceImpl.findIncontriCalendario", "");
            watcher.stop();
        }
    }

    /**
     * Find incontri calendario.
     *
     * @param idUtente the id utente
     * @param idCalendario the id calendario
     * @param parametriRicercaIncontriCalendario the parametri ricerca incontri calendario
     * @param codiceFiscaleUtente the codice fiscale utente
     * @return the list
     * @throws Exception the exception
     */
    private List<PrenotazioneIncontro> findIncontriCalendario(Long idUtente, Long idCalendario,
            ParametriRicercaIncontriCalendario parametriRicercaIncontriCalendario, String codiceFiscaleUtente) throws Exception {
        List<PrenotazioneIncontro> result = new ArrayList<>();
        Map<String, Sportello> sportelli = AdapterSilpsvinWSImpl.getInstance().findSportelliMap(codiceFiscaleUtente);
        PrenotazioneFilter filter = new PrenotazioneFilter();
        filter.getSlot().getGiorno().getPeriodo().getCalendario().getIdCalendario().eq(idCalendario);
        filter.getIdStatoAppuntamento().eq("DE");
        if (parametriRicercaIncontriCalendario != null) {
            if (parametriRicercaIncontriCalendario.getDataDa() != null) {
                if (parametriRicercaIncontriCalendario.getDataA() != null)
                    filter.getSlot().getGiorno().getGiorno().between(parametriRicercaIncontriCalendario.getDataDa(),
                            parametriRicercaIncontriCalendario.getDataA());
                else
                    filter.getSlot().getGiorno().getGiorno().ge(parametriRicercaIncontriCalendario.getDataDa());
            } else if (parametriRicercaIncontriCalendario.getDataA() != null) {
                filter.getSlot().getGiorno().getGiorno().le(parametriRicercaIncontriCalendario.getDataA());
            }
        }
        SimpleDateFormat df = new SimpleDateFormat(DD_MM_YYYY);
        for (PrenotazioneDTO prenotazioneDTO : dao.findAll(PrenotazioneDBDef.class, filter, 0)) {
            CalendarioDTO calendarioDTO = prenotazioneDTO.getSlot().getGiorno().getPeriodo().getCalendario();
            String keySportello = calendarioDTO.getGruppoOperatore() + "-" + calendarioDTO.getCodOperatore() + "-" + calendarioDTO.getSubcodice();
            PrenotazioneIncontro prenotazione = new PrenotazioneIncontro();
            prenotazione.setCodAmbito(calendarioDTO.getAmbito().getCodAmbito());
            prenotazione.setCodiceAnpalStatoIncontro(prenotazioneDTO.getIdStatoAppuntamento());
            prenotazione.setIdPrenotazione(prenotazioneDTO.getIdPrenotazione());
            SlotIncontro slot = new SlotIncontro();
            SlotDTO slotDTO = prenotazioneDTO.getSlot();
            slot.setIdSlot(slotDTO.getIdSlot());
            slot.setGiorno(df.format(slotDTO.getGiorno().getGiorno()));
            slot.setDaOra(slotDTO.getDescrizioneOraInizio());
            slot.setAOra(slotDTO.getDescrizioneOraFine());
            slot.setDisponibilita(slotDTO.getNumMaxPrenotazioni() - slotDTO.getNumPrenotazioniValide());
            prenotazione.setSlot(slot);
            prenotazione.setSportello(sportelli.get(keySportello));
            result.add(prenotazione);
        }
        return result;
    }

    /**
     * Gets the sportelli.
     *
     * @param idUtente the id utente
     * @param codTipoUtente the cod tipo utente
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the sportelli
     */
    @Override
    public Response getSportelli(Long idUtente, String codTipoUtente, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::getSportelli] idUtente=" + idUtente + COD_TIPO_UTENTE + codTipoUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.GET_SPORTELLI, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);

            Set<String> setEntiOperatoreCpi = new HashSet<>();
            if ("CPI".equals(codTipoUtente)) {
                UtenteEnteFilter filterUtentiEnti = new UtenteEnteFilter();
                filterUtentiEnti.getIdUtente().eq(idUtente);
                for (UtenteEnteDTO utenteEnte : dao.findAll(UtenteEnteDBDef.class, filterUtentiEnti, 0)) {
                    String keyEnte = utenteEnte.getGruppoOperatore() + "-" + utenteEnte.getCodOperatore() + "-" + utenteEnte.getSubcodice();
                    setEntiOperatoreCpi.add(keyEnte);
                }
                log.debug("[OperatoreApiServiceImpl::getSportelli] Sportelli operatore: " + setEntiOperatoreCpi);
            }

            Map<String, Sportello> sportelli = AdapterSilpsvinWSImpl.getInstance().findSportelliMap(codiceFiscaleUtente);
            CalendarioFilter filter = new CalendarioFilter();
            List<Sportello> result = new ArrayList<>();
            Set<String> keys = new HashSet<>();
            for (CalendarioDTO calendarioDTO : dao.findAll(CalendarioDBDef.class, filter, 0)) {
                String key = calendarioDTO.getGruppoOperatore() + "-" + calendarioDTO.getCodOperatore() + "-" + calendarioDTO.getSubcodice();
                if (!sportelli.containsKey(key))
                    continue;
                if (keys.contains(key))
                    continue;
                Sportello sportello = sportelli.get(key);
                String keyEnteAppartenenza = sportello.getGruppoOperatoreEnteAppartenenza() + "-" + sportello.getCodOperatoreEnteAppartenenza() + "-"
                        + sportello.getSubcodiceEnteAppartenenza();
                if ("CPI".equals(codTipoUtente) && !setEntiOperatoreCpi.contains(keyEnteAppartenenza)) {
                    log.debug("[CalendarioApiServiceImpl::getSportelli] Escludo sportello " + keyEnteAppartenenza
                            + " perche' l'utente opera come operatore CPI e non e' abilitato per questo sportello");
                    continue;
                }
                keys.add(key);
                result.add(sportello);
            }

            // Ordina il risultato
            Collections.sort(result, new Comparator<Sportello>() {
                @Override
                public int compare(Sportello s1, Sportello s2) {
                    return s1.getDescrizione().compareTo(s2.getDescrizione());
                }
            });

            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::getSportelli] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_SPORTELLI, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "getSportelli()", "invocazione API OperatoreApiServiceImpl.getSportelli", "");
            watcher.stop();
        }
    }

    /**
     * Duplica periodo.
     *
     * @param idUtente the id utente
     * @param parametriDuplicazionePeriodo the parametri duplicazione periodo
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response duplicaPeriodo(Long idUtente, ParametriDuplicazionePeriodo parametriDuplicazionePeriodo, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info(OPERATORE_API_SERVICE_IMPL_DUPLICA_PERIODO_ID_UTENTE + idUtente + "parametriDuplicazionePeriodo=" + parametriDuplicazionePeriodo);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.DUPLICAZIONE_PERIODO, idUtente);
            String duplicaEccezione = "N";
            if (parametriDuplicazionePeriodo.isDuplicaEccezione() != null && parametriDuplicazionePeriodo.isDuplicaEccezione())
                duplicaEccezione = "S";
            String result = dao.callFunctionPlSqlReturnString("be/impl/OperatoreApiServiceImpl.estendiPeriodo", parametriDuplicazionePeriodo.getIdPeriodo(),
                    parametriDuplicazionePeriodo.getDataA(), duplicaEccezione);
            EsitoDuplicazionePeriodo esito = new EsitoDuplicazionePeriodo();
            if (result != null) {
                esito.setCode("OK");
                esito.setIdPeriodoCeato(Long.parseLong(result));
            } else {
                esito.setCode("KO");
            }
            return Response.ok(esito).build();
        } catch (DAOException ex) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            String messaggio = getMessaggio(ex);
            if (messaggio != null)
                return createErrorResponseWithText("452", messaggio);
            else {
                log.error(OPERATORE_API_SERVICE_IMPL_DUPLICA_PERIODO_ID_UTENTE + idUtente + ", parametriDuplicazionePeriodo=" + parametriDuplicazionePeriodo,
                        ex);
                tracciamentoUtils.tracciaKo(TracciamentoUtils.DUPLICAZIONE_PERIODO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
                return createErrorResponse("500", MessaggiUtils.ME002);
            }
        } catch (BusinessException ex) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("452", ex.getCode(), ex.getArgs());
        } catch (Exception ex) {
            log.error(OPERATORE_API_SERVICE_IMPL_DUPLICA_PERIODO_ID_UTENTE + idUtente + ", parametriDuplicazionePeriodo=" + parametriDuplicazionePeriodo, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.DUPLICAZIONE_PERIODO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "duplicaPeriodo()", "invocazione API OperatoreApiServiceImpl.duplicaPeriodo", "");
            watcher.stop();
        }
    }

    /**
     * Gets the messaggio.
     *
     * @param ex the ex
     * @return the messaggio
     */
    private String getMessaggio(DAOException ex) {
        if (ex.getCause() instanceof SQLException) {
            SQLException sqlex = (SQLException) ex.getCause();
            if (sqlex.getErrorCode() > 20100 && sqlex.getMessage() != null) {
                String message = sqlex.getMessage();
                if (message != null && message.startsWith("ORA-")) {
                    message = message.substring(10).trim();
                    int i = message.indexOf("\n");
                    if (i > 0)
                        message = message.substring(0, i);
                }
                return message;
            }
        }
        return null;
    }

    /**
     * Find slot calendario.
     *
     * @param idUtente the id utente
     * @param idCalendario the id calendario
     * @param parametriRicercaIncontriCalendario the parametri ricerca incontri calendario
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    public Response findSlotCalendario(Long idUtente, Long idCalendario, ParametriRicercaIncontriCalendario parametriRicercaIncontriCalendario,
            SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::findSlotCalendario] idUtente=" + idUtente + ID_CALENDARIO + idCalendario + PARAMETRI_RICERCA_INCONTRI_CALENDARIO
                + parametriRicercaIncontriCalendario);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.FIND_SLOT_CALENDARIO, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            List<SlotIncontro> slots = findSlotIncontroCalendario(idUtente, idCalendario, parametriRicercaIncontriCalendario, codiceFiscaleUtente);
            return Response.ok(slots).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::findSlotCalendario] idUtente=" + idUtente + ID_CALENDARIO2 + idCalendario
                    + PARAMETRI_RICERCA_INCONTRI_CALENDARIO + parametriRicercaIncontriCalendario, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.FIND_SLOT_CALENDARIO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "findSlotCalendario()", "invocazione API OperatoreApiServiceImpl.findSlotCalendario",
                    "");
            watcher.stop();
        }
    }

    /**
     * Find slot incontro calendario.
     *
     * @param idUtente the id utente
     * @param idCalendario the id calendario
     * @param parametriRicercaIncontriCalendario the parametri ricerca incontri calendario
     * @param codiceFiscaleUtente the codice fiscale utente
     * @return the list
     * @throws Exception the exception
     */
    private List<SlotIncontro> findSlotIncontroCalendario(Long idUtente, Long idCalendario,
            ParametriRicercaIncontriCalendario parametriRicercaIncontriCalendario, String codiceFiscaleUtente) throws Exception {
        List<SlotIncontro> result = new ArrayList<>();
        SlotFilter filter = new SlotFilter();
        filter.getGiorno().getPeriodo().getCalendario().getIdCalendario().eq(idCalendario);
        if (parametriRicercaIncontriCalendario != null) {
            if (parametriRicercaIncontriCalendario.getDataDa() != null) {
                if (parametriRicercaIncontriCalendario.getDataA() != null)
                    filter.getGiorno().getGiorno().between(parametriRicercaIncontriCalendario.getDataDa(), parametriRicercaIncontriCalendario.getDataA());
                else
                    filter.getGiorno().getGiorno().ge(parametriRicercaIncontriCalendario.getDataDa());
            } else if (parametriRicercaIncontriCalendario.getDataA() != null) {
                filter.getGiorno().getGiorno().le(parametriRicercaIncontriCalendario.getDataA());
            }
        }
        SimpleDateFormat df = new SimpleDateFormat(DD_MM_YYYY);
        for (SlotDTO slotDTO : dao.findAll(SlotDBDef.class, filter, 0)) {
            SlotIncontro slot = new SlotIncontro();
            slot.setIdSlot(slotDTO.getIdSlot());
            slot.setGiorno(df.format(slotDTO.getGiorno().getGiorno()));
            slot.setDaOra(slotDTO.getDescrizioneOraInizio());
            slot.setAOra(slotDTO.getDescrizioneOraFine());
            slot.setDisponibilita(slotDTO.getNumMaxPrenotazioni() - slotDTO.getNumPrenotazioniValide());
            slot.setNumeroPrenotazioniMassimo(slotDTO.getNumMaxPrenotazioni());
            slot.setNumeroPrenotazioniValide(slotDTO.getNumPrenotazioniValide());
            slot.setFlagEccezione("S".equalsIgnoreCase(slotDTO.getFlgEccezione()));
            result.add(slot);
        }
        return result;
    }

    /**
     * Load messaggi aggiuntivi ambito.
     *
     * @param idUtente the id utente
     * @param codAmbito the cod ambito
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    public Response loadMessaggiAggiuntiviAmbito(Long idUtente, String codAmbito, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::loadMessaggiAggiuntiviAmbito] idUtente=" + idUtente + ", codAmbito=" + codAmbito);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.LOAD_MESSAGGI_AGGIUNTIVI_AMBITO, idUtente);
            String[] tipologieMail = { MessaggioAggiuntivoDTO.MAIL_OPEN_SILP, MessaggioAggiuntivoDTO.MAIL_CLOSE_SILP };
            MessaggioAggiuntivoFilter filter = new MessaggioAggiuntivoFilter();
            filter.getMessaggio().getCodTipoMessaggio().in(tipologieMail);
            if (codAmbito != null)
                filter.getMessaggio().getCodAmbito().eq(codAmbito);
            Map<String, ConfigurazioneMessaggiAggiuntivi> messaggi = new HashMap<>();
            List<ConfigurazioneMessaggiAggiuntivi> result = new ArrayList<>();
            for (MessaggioAggiuntivoDTO messaggioDTO : dao.findAll(MessaggioAggiuntivoDBDef.class, filter, 0)) {
                String codAmbitoDTO = messaggioDTO.getMessaggio().getCodAmbito();
                String codTipoMessaggioDTO = messaggioDTO.getMessaggio().getCodTipoMessaggio();
                ConfigurazioneMessaggiAggiuntivi messaggio = messaggi.get(codAmbitoDTO);
                if (messaggio == null) {
                    messaggio = new ConfigurazioneMessaggiAggiuntivi();
                    messaggio.setCodAmbito(codAmbitoDTO);
                    result.add(messaggio);
                    messaggi.put(codAmbitoDTO, messaggio);
                }
                switch (codTipoMessaggioDTO) {
                case MessaggioAggiuntivoDTO.MAIL_OPEN_SILP:
                    messaggio.setMessaggioAggiuntivoMailNotificaInizioDomanda(messaggioDTO.getTesto());
                    break;
                case MessaggioAggiuntivoDTO.MAIL_CLOSE_SILP:
                    messaggio.setMessaggioAggiuntivoMailNotificaTermineDomanda(messaggioDTO.getTesto());
                    break;
                default:
                    break;
                }
            }
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::loadMessaggiAggiuntiviAmbito] idUtente=" + idUtente + ", codAmbito=" + codAmbito, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.LOAD_MESSAGGI_AGGIUNTIVI_AMBITO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    codAmbito);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "loadMessaggiAggiuntiviAmbito()",
                    "invocazione API OperatoreApiServiceImpl.loadMessaggiAggiuntiviAmbito", "");
            watcher.stop();
        }
    }

    /**
     * Save messaggi aggiuntivi ambito.
     *
     * @param idUtente the id utente
     * @param messaggi the messaggi
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response saveMessaggiAggiuntiviAmbito(Long idUtente, ConfigurazioneMessaggiAggiuntivi messaggi, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::saveMessaggiAggiuntiviAmbito] idUtente=" + idUtente + ", messaggi=" + messaggi);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.SAVE_MESSAGGI_AGGIUNTIVI_AMBITO, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            salvaMessaggioAggiuntivo(messaggi.getCodAmbito(), MessaggioAggiuntivoDTO.MAIL_OPEN_SILP, messaggi.getMessaggioAggiuntivoMailNotificaInizioDomanda(),
                    codiceFiscaleUtente);
            salvaMessaggioAggiuntivo(messaggi.getCodAmbito(), MessaggioAggiuntivoDTO.MAIL_CLOSE_SILP,
                    messaggi.getMessaggioAggiuntivoMailNotificaTermineDomanda(), codiceFiscaleUtente);
            Esito esito = new Esito();
            esito.setCode("OK");
            return Response.ok(esito).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::saveMessaggiAggiuntiviAmbito] idUtente=" + idUtente + ", messaggi=" + messaggi, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_MESSAGGI_AGGIUNTIVI_AMBITO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    messaggi.getCodAmbito());
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "saveMessaggiAggiuntiviAmbito()",
                    "invocazione API OperatoreApiServiceImpl.saveMessaggiAggiuntiviAmbito", "");
            watcher.stop();
        }
    }

    /**
     * Salva messaggio aggiuntivo.
     *
     * @param codAmbito the cod ambito
     * @param codTipoMessaggio the cod tipo messaggio
     * @param testo the testo
     * @param codiceFiscaleUtente the codice fiscale utente
     * @throws Exception the exception
     */
    private void salvaMessaggioAggiuntivo(String codAmbito, String codTipoMessaggio, String testo, String codiceFiscaleUtente) throws Exception {
        Date now = new Date();
        MessaggioAggiuntivoFilter filter = new MessaggioAggiuntivoFilter();
        filter.getMessaggio().getCodAmbito().eq(codAmbito);
        filter.getMessaggio().getCodTipoMessaggio().eq(codTipoMessaggio);
        MessaggioAggiuntivoDTO messaggio = dao.findFirst(MessaggioAggiuntivoDBDef.class, filter);
        if (messaggio == null) {
            MessaggioFilter messaggioFilter = new MessaggioFilter();
            messaggioFilter.getCodAmbito().eq(codAmbito);
            messaggioFilter.getCodTipoMessaggio().eq(codTipoMessaggio);
            MessaggioDTO messaggioDTO = dao.findFirst(MessaggioDBDef.class, messaggioFilter);
            messaggio = new MessaggioAggiuntivoDTO();
            messaggio.setCodUserInserim(codiceFiscaleUtente);
            messaggio.setDInserim(now);
            messaggio.setMessaggio(messaggioDTO);
            messaggio.setTesto(testo);
            messaggio.setCodUserAggiorn(codiceFiscaleUtente);
            messaggio.setDAggiorn(now);
            dao.insert(MessaggioAggiuntivoDBDef.class, messaggio);
        } else {
            messaggio.setTesto(testo);
            messaggio.setCodUserAggiorn(codiceFiscaleUtente);
            messaggio.setDAggiorn(now);
            dao.update(MessaggioAggiuntivoDBDef.class, messaggio);
        }
    }

    /**
     * Elimina eccezione.
     *
     * @param idUtente the id utente
     * @param parametriEliminazioneEccezione the parametri eliminazione eccezione
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response eliminaEccezione(Long idUtente, ParametriEliminazioneEccezione parametriEliminazioneEccezione, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::eliminaEccezione] idUtente=" + idUtente + ", parametriEliminazioneEccezione=" + parametriEliminazioneEccezione);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.ELIMINAZIONE_ECCEZIONE, idUtente);
            Esito esito = new Esito();
            EccezioneFilter filter = new EccezioneFilter();
            filter.getIdEccezione().eq(parametriEliminazioneEccezione.getIdEccezione());
            EccezioneDTO eccezione = dao.findFirst(EccezioneDBDef.class, filter);
            if (eccezione != null) {
                if ("S".equals(eccezione.getFlgElaborata())) {
                    esito.setCode("KO");
                    esito.setMessaggioCittadino("Non e' possibile rimuovere l'eccezione perche' e' gia' stata elaborata");
                    return Response.ok(esito).build();
                }
                dao.delete(EccezioneDBDef.class, eccezione);
                esito.setCode("OK");
                esito.setMessaggioCittadino("Eccezione rimossa dalla configurazione del Calendario");
                return Response.ok(esito).build();
            }
            esito.setCode("KO");
            esito.setMessaggioCittadino("Eccezione non presente nella configurazione del Calendario");
            return Response.ok(esito).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::eliminaEccezione] idUtente=" + idUtente + ", parametriEliminazioneEccezione=" + parametriEliminazioneEccezione,
                    ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.ELIMINAZIONE_ECCEZIONE, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "eliminaEccezione()", "invocazione API OperatoreApiServiceImpl.eliminaEccezione", "");
            watcher.stop();
        }
    }

    /**
     * Elimina fascia.
     *
     * @param idUtente the id utente
     * @param parametriEliminazioneFascia the parametri eliminazione fascia
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response eliminaFascia(Long idUtente, ParametriEliminazioneFascia parametriEliminazioneFascia, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::eliminaFascia] idUtente=" + idUtente + ", parametriEliminazioneFascia=" + parametriEliminazioneFascia);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            Esito esito = new Esito();
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.ELIMINAZIONE_FASCIA, idUtente);
            PeriodoGiornoSettFilter filter = new PeriodoGiornoSettFilter();
            filter.getIdPeriodoGiornoSett().eq(parametriEliminazioneFascia.getIdFascia());
            PeriodoGiornoSettDTO fascia = dao.findFirst(PeriodoGiornoSettDBDef.class, filter);
            if (fascia != null) {
                Map<String, Object> params = new HashMap<>();
                params.put("idPeriodo", fascia.getIdPeriodo());
                String flagSlot = dao.readString("be/impl/OperatoreApiServiceImpl.esistonoSlotPerPeriodo", params);
                if ("S".equals(flagSlot)) {
                    esito.setCode("KO");
                    esito.setMessaggioCittadino("Non e' possibile rimuovere la fascia perche' e' gia' stata elaborata");
                    return Response.ok(esito).build();
                }
                dao.delete(PeriodoGiornoSettDBDef.class, fascia);
                esito.setCode("OK");
                esito.setMessaggioCittadino("Fascia rimossa dalla configurazione del Calendario");
                return Response.ok(esito).build();
            }
            esito.setCode("KO");
            esito.setMessaggioCittadino("Fascia non presente nella configurazione del Calendario");
            return Response.ok(esito).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::eliminaFascia] idUtente=" + idUtente + ", parametriEliminazioneFascia=" + parametriEliminazioneFascia, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.ELIMINAZIONE_FASCIA, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "eliminaFascia()", "invocazione API OperatoreApiServiceImpl.eliminaFascia", "");
            watcher.stop();
        }
    }

    /**
     * Applica dato A calendari.
     *
     * @param idUtente the id utente
     * @param parametriDatoCalendari the parametri dato calendari
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response applicaDatoACalendari(Long idUtente, ParametriDatoCalendari parametriDatoCalendari, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::applicaDatoACalendari] idUtente=" + idUtente + ", parametriDatoCalendari=" + parametriDatoCalendari);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.APPLICA_DATO_A_CALENDARI, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            Date now = new Date();
            Long[] idCalendari = new Long[parametriDatoCalendari.getIdCalendari().size()];
            for (int i = 0; i < parametriDatoCalendari.getIdCalendari().size(); i++) {
                idCalendari[i] = parametriDatoCalendari.getIdCalendari().get(i);
            }

            String campo = parametriDatoCalendari.getCampo();
            String valore = parametriDatoCalendari.getValore();
            if (CAMPO_VISIBILE_IN_BASE.equals(campo) || CAMPO_ORE_LIMITE_SPOSTAMENTO.equals(campo) || CAMPO_ANNULLA_GARANZIA_GIOVANI.equals(campo)) {
                CalendarioFilter filter = new CalendarioFilter();
                filter.getIdCalendario().in(idCalendari);
                for (CalendarioDTO calendario : dao.findAll(CalendarioDBDef.class, filter, 0)) {
                    switch (campo) {
                    case CAMPO_VISIBILE_IN_BASE:
                        calendario.setFlgVisibileCpi(valore);
                        break;
                    case CAMPO_ORE_LIMITE_SPOSTAMENTO:
                        if (valore != null)
                            calendario.setOreLimiteSpostamento(Integer.parseInt(valore));
                        else
                            calendario.setOreLimiteSpostamento(null);
                        break;
                    case CAMPO_ANNULLA_GARANZIA_GIOVANI:
                        calendario.setFlgAnnullaGaranziaGiovani(valore);
                        break;
                    default:
                        break;
                    }
                    dao.update(CalendarioDBDef.class, calendario);
                }
            } else if (CAMPO_MESSAGGIO_ANNULLAMENTO_APPUNTAMENTO.equals(campo) || CAMPO_MESSAGGIO_SPOSTAMENTO_APPUNTAMENTO.equals(campo)) {

                Set<Long> idCalendariMessaggi = new HashSet<>();
                for (Long idCalendario : parametriDatoCalendari.getIdCalendari()) {
                    idCalendariMessaggi.add(idCalendario);
                }

                MessaggioCalendarioFilter filter = new MessaggioCalendarioFilter();
                filter.getIdCalendario().in(idCalendari);
                if (CAMPO_MESSAGGIO_ANNULLAMENTO_APPUNTAMENTO.equals(campo))
                    filter.getCodTipoMessaggioCalendario().eq(MessaggioCalendarioDTO.ANNULLAMENTO_APPUNTAMENTO);
                else if (CAMPO_MESSAGGIO_SPOSTAMENTO_APPUNTAMENTO.equals(campo))
                    filter.getCodTipoMessaggioCalendario().eq(MessaggioCalendarioDTO.SPOSTAMENTO_APPUNTAMENTO);

                // Aggiorna o cancella i messaggi gia' presenti
                for (MessaggioCalendarioDTO messaggio : dao.findAll(MessaggioCalendarioDBDef.class, filter, 0)) {
                    idCalendariMessaggi.remove(messaggio.getIdCalendario());
                    if (!StringUtils.isEmpty(valore)) {
                        messaggio.setCodUserAggiorn(codiceFiscaleUtente);
                        messaggio.setDAggiorn(now);
                        messaggio.setTesto(valore);
                        dao.update(MessaggioCalendarioDBDef.class, messaggio);
                    } else {
                        dao.delete(MessaggioCalendarioDBDef.class, messaggio);
                    }
                }

                // Inserisce i messaggi mancanti
                if (!StringUtils.isEmpty(valore)) {
                    for (Long idCalendario : idCalendariMessaggi) {
                        MessaggioCalendarioDTO messaggio = new MessaggioCalendarioDTO();
                        if (CAMPO_MESSAGGIO_ANNULLAMENTO_APPUNTAMENTO.equals(campo))
                            messaggio.setCodTipoMessaggioCalendario(MessaggioCalendarioDTO.ANNULLAMENTO_APPUNTAMENTO);
                        else if (CAMPO_MESSAGGIO_SPOSTAMENTO_APPUNTAMENTO.equals(campo))
                            messaggio.setCodTipoMessaggioCalendario(MessaggioCalendarioDTO.SPOSTAMENTO_APPUNTAMENTO);
                        messaggio.setCodUserAggiorn(codiceFiscaleUtente);
                        messaggio.setCodUserInserim(codiceFiscaleUtente);
                        messaggio.setDAggiorn(now);
                        messaggio.setDInserim(now);
                        messaggio.setIdCalendario(idCalendario);
                        messaggio.setTesto(valore);
                        dao.insert(MessaggioCalendarioDBDef.class, messaggio);
                    }
                }
            }

            Esito esito = new Esito();
            esito.setCode("OK");
            return Response.ok(esito).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::applicaDatoACalendari] idUtente=" + idUtente + ", parametriDatoCalendari=" + parametriDatoCalendari, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.APPLICA_DATO_A_CALENDARI, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "applicaDatoACalendari()",
                    "invocazione API OperatoreApiServiceImpl.applicaDatoACalendari", "");
            watcher.stop();
        }
    }

    /**
     * Applica eccezione A calendari.
     *
     * @param idUtente the id utente
     * @param parametriEccezioneCalendari the parametri eccezione calendari
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response applicaEccezioneACalendari(Long idUtente, ParametriEccezioneCalendari parametriEccezioneCalendari, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[OperatoreApiServiceImpl::applicaEccezioneACalendari] idUtente=" + idUtente + ", parametriEccezioneCalendari=" + parametriEccezioneCalendari);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtenteOperatore(httpRequest, TracciamentoUtils.APPLICA_ECCEZIONE_A_CALENDARI, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            Date now = new Date();
            EccezioneFilter eccezioneFilter = new EccezioneFilter();
            eccezioneFilter.getIdEccezione().eq(parametriEccezioneCalendari.getIdEccezione());
            EccezioneDTO eccezione = dao.findFirst(EccezioneDBDef.class, eccezioneFilter);
            SlotFilter slotFilter = new SlotFilter();
            Long[] idCalendari = new Long[parametriEccezioneCalendari.getIdCalendari().size()];
            idCalendari = parametriEccezioneCalendari.getIdCalendari().toArray(idCalendari);
            slotFilter.getGiorno().getPeriodo().getCalendario().getIdCalendario().in(idCalendari);
            Date giorno1 = DateUtils.truncate(eccezione.getGiorno(), Calendar.DATE);
            Date giorno2 = DateUtils.addDays(giorno1, 1);
            slotFilter.getGiorno().getGiorno().between(giorno1, giorno2);
            slotFilter.getOraInizio().eq(eccezione.getOraInizio());
            slotFilter.getOraFine().eq(eccezione.getOraFine());
            for (SlotDTO slot : dao.findAll(SlotDBDef.class, slotFilter, 0)) {
                String slotDesc = "slot " + slot.getIdSlot() + " del calendario " + slot.getGiorno().getPeriodo().getCalendario().getIdCalendario();
                log.debug("[OperatoreApiServiceImpl::applicaEccezioneACalendari] Applicazione eccezione a " + slotDesc + "...");
                if (slot.getNumPrenotazioniValide() > eccezione.getNumMaxPrenotazioni()) {
                    log.debug("[OperatoreApiServiceImpl::applicaEccezioneACalendari] Eccezione non applicata allo " + slotDesc
                            + " perche' slot.numPrenotazioniValide (" + slot.getNumPrenotazioniValide() + ") > eccezione.numMaxPrenotazioni ("
                            + eccezione.getNumMaxPrenotazioni() + ")");
                } else {
                    log.debug("[OperatoreApiServiceImpl::applicaEccezioneACalendari] Eccezione applicata allo " + slotDesc);
                    EccezioneDTO eccezioneSlot = new EccezioneDTO();
                    eccezioneSlot.setFlgElaborata("S");
                    eccezioneSlot.setGiorno(eccezione.getGiorno());
                    eccezioneSlot.setIdPeriodo(slot.getGiorno().getPeriodo().getIdPeriodo());
                    eccezioneSlot.setNumMaxPrenotazioni(eccezione.getNumMaxPrenotazioni());
                    eccezioneSlot.setOraInizio(eccezione.getOraInizio());
                    eccezioneSlot.setOraFine(eccezione.getOraFine());
                    dao.insert(EccezioneDBDef.class, eccezioneSlot);
                    slot.setCodUserAggiorn(codiceFiscaleUtente);
                    slot.setDAggiorn(now);
                    slot.setFlgEccezione("S");
                    slot.setNumMaxPrenotazioni(eccezione.getNumMaxPrenotazioni());
                    dao.update(SlotDBDef.class, slot);
                }
            }
            Esito esito = new Esito();
            esito.setCode("OK");
            return Response.ok(esito).build();
        } catch (Exception ex) {
            log.error("[OperatoreApiServiceImpl::applicaEccezioneACalendari] idUtente=" + idUtente + ", parametriEccezioneCalendari="
                    + parametriEccezioneCalendari, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.APPLICA_ECCEZIONE_A_CALENDARI, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    null);
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(OperatoreApiServiceImpl.class.getName(), "applicaEccezioneACalendari()",
                    "invocazione API OperatoreApiServiceImpl.applicaEccezioneACalendari", "");
            watcher.stop();
        }
    }

}