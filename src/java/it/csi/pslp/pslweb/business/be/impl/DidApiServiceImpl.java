/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslcommonobj.dbdef.UtenteDBDef;
import it.csi.pslp.pslcommonobj.dto.ParametroDTO;
import it.csi.pslp.pslcommonobj.dto.UtenteDTO;
import it.csi.pslp.pslcommonobj.filter.UtenteFilter;
import it.csi.pslp.pslweb.business.be.DidApi;
import it.csi.pslp.pslweb.business.integration.AdapterSilpserver;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneProfilingDid;
import it.csi.pslp.pslweb.dto.be.DatiInputAggiornamentoDid;
import it.csi.pslp.pslweb.dto.be.DatiInputProfilingDid;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.EsitoDettaglioDid;
import it.csi.pslp.pslweb.dto.be.EsitoSaveDid;
import it.csi.pslp.pslweb.dto.be.EsitoSaveProfilingDid;
import it.csi.pslp.pslweb.dto.be.MappaErrori;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.ParametroUtils;
import it.csi.pslp.pslweb.util.RestHelper;
import it.csi.pslp.pslweb.util.SpringSupportedResource;
import it.csi.silos.jedi.core.DAO;
import it.csi.silos.jedi.core.DAOException;
import it.csi.silos.silcommon.util.SilCommonUtils;
import it.csi.util.performance.StopWatch;

/**
 * The Class DidApiServiceImpl.
 */
@Component("didApi")
public class DidApiServiceImpl extends SpringSupportedResource implements DidApi {

    /** The Constant DID. */
    private static final String DID = " -- DID--";

    /** The Constant BR. */
    private static final String BR = " <br>";

    /** The Constant I_DATI_PROFILING. */
    private static final String I_DATI_PROFILING = " DatiInputProfilingDid=";

    /** The Constant ERRORE_DI_SISTEMA. */
    private static final String ERRORE_DI_SISTEMA = "Errore di sistema: ";

    /** The Constant CLASSE. */
    private static final String CLASSE = "DidApiServiceImpl";

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

    /**
     * Load configurazione profiling did.
     * 
     * Metodo per caricare tutte le tabelle di configurazione per poter permettere
     * l'inserimento del profiling legato alla did.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response loadConfigurazioneProfilingDid(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DidApiServiceImpl::loadConfigurazioneProfilingDid]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            ConfigurazioneProfilingDid confDid = AdapterSilpserver.getInstance().loadConfigurazioneProfilingDid(codiceFiscaleUtente);
            return Response.ok(confDid).build();
        } catch (Exception ex) {
            log.error("[DidApiServiceImpl::loadConfigurazioneProfilingDid]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.LOAD_CONFIGURAZIONI_PROFILING_DID, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    "DID");
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DidApiServiceImpl.CLASSE, "loadConfigurazioneProfilingDid()",
                    "invocazione API DidApiServiceImpl.loadConfigurazioneProfilingDid", "");
            watcher.stop();
        }
    }

    /**
     * Save profiling did service.
     * 
     * Metodo per eseguire l'inserimento o l'aggiornamento dei dati relativi al
     * profiling
     *
     * @param idUtente        the id utente
     * @param iDatiProfiling  the i dati profiling
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response saveProfilingDidService(Long idUtente, DatiInputProfilingDid iDatiProfiling, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[DidApiServiceImpl::saveProfilingDidService] idUtente=" + idUtente + I_DATI_PROFILING + iDatiProfiling);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.SAVE_PROFILING_DID, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            EsitoSaveProfilingDid esitoProfilingDid = AdapterSilpserver.getInstance().saveProfilingDidService(codiceFiscaleUtente, iDatiProfiling);
            return Response.ok(esitoProfilingDid).build();
        } catch (Exception ex) {
            log.error("[DidApiServiceImpl::saveProfilingDidService]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_PROFILING_DID, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), "DID");
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DidApiServiceImpl.CLASSE, "saveProfilingDidService()", "invocazione API DidApiServiceImpl.saveProfilingDidService", "");
            watcher.stop();
        }
    }

    /**
     * Save did service.
     * 
     * Metodo per eseguire il salvataggio delle informazioni delle DID inserite
     *
     * @param idUtente        the id utente
     * @param iDatiDellaDid   the i dati della did
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response saveDidService(Long idUtente, DatiInputAggiornamentoDid iDatiDellaDid, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        /*
         * sovrascrivo la data di sistema onde evitare che sul sistema dell'utente ci
         * sia una data sbagliata in questo modo la data viene presa dai sistemi di csi,
         * e non dalla data del sistema dell'utente.
         */
        iDatiDellaDid.setDataDid(new Date());
        iDatiDellaDid.setDataStatoDid(new Date());

        log.info("[DidApiServiceImpl::saveDidService] idUtente=" + idUtente + I_DATI_PROFILING + iDatiDellaDid);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.SAVE_DID, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            /* CHIAMATA AL SERVIZIO INSERIMENTO DID */
            boolean isDID_IND_ST_CONVAL = getParm("DID_INS_ST_CONVAL");
            EsitoSaveDid esitoDid = new EsitoSaveDid();
            if (isDID_IND_ST_CONVAL) {
                esitoDid = AdapterSilpserver.getInstance().saveDidService(codiceFiscaleUtente, iDatiDellaDid, false);
                settaggioMsgErrori(httpRequest, esitoDid, idUtente, TracciamentoUtils.SAVE_DID, true);
                settaggioMsgInfo(httpRequest, esitoDid, idUtente, TracciamentoUtils.SAVE_DID, true);
            } else {
                esitoDid.setMessaggioCittadino("La DID e' stata correttamente inserita ma sara' convalidata/confermata successivamente dal CPI");
            }
            return Response.ok(esitoDid).build();
        } catch (Exception ex) {
            log.error("[DidApiServiceImpl::saveDidService]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_DID, httpRequest, idUtente,
                    ERRORE_DI_SISTEMA + ex.getClass().getName() + DID + iDatiDellaDid.toString(), "DID");
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DidApiServiceImpl.CLASSE, "saveDidService()", "invocazione API DidApiServiceImpl.saveDidService", "");
            watcher.stop();
        }

    }

    /**
     * Change state did after insert profiling service.
     * 
     * Metodo per aggiornamere lo stato della did dopo aver aggiornato il profiling
     *
     * @param idUtente        the id utente
     * @param iDatiDellaDid   the i dati della did
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response changeStateDidAfterInsertProfilingService(Long idUtente, DatiInputAggiornamentoDid iDatiDellaDid, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        /*
         * sovrascrivo la data di sistema onde evitare che sul sistema dell'utente ci
         * sia una data sbagliata in questo modo la data viene presa dai sistemi di csi,
         * e non dalla data del sistema dell'utente.
         */
        iDatiDellaDid.setDataStatoDid(new Date());
        log.info("[DidApiServiceImpl::changeStateDidAfterInsertProfilingService] idUtente=" + idUtente + I_DATI_PROFILING + iDatiDellaDid);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.SAVE_PROFILING_DID_CONTROLLER, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            EsitoSaveDid esitoDid = AdapterSilpserver.getInstance().saveDidService(codiceFiscaleUtente, iDatiDellaDid, false);
            settaggioMsgErrori(httpRequest, esitoDid, idUtente, TracciamentoUtils.LOG_DID, true);
            settaggioMsgInfo(httpRequest, esitoDid, idUtente, TracciamentoUtils.LOG_DID, true);

            return Response.ok(esitoDid).build();
        } catch (Exception ex) {
            log.error("[DidApiServiceImpl::changeStateDidAfterInsertProfilingService]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_PROFILING_DID_CONTROLLER, httpRequest, idUtente,
                    ERRORE_DI_SISTEMA + ex.getClass().getName() + DID + iDatiDellaDid.toString(), "DID");
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DidApiServiceImpl.CLASSE, "changeStateDidAfterInsertProfilingService()",
                    "invocazione API DidApiServiceImpl.changeStateDidAfterInsertProfilingService", "");
            watcher.stop();
        }

    }

    /**
     * Controllo did service.
     * 
     * Metodo che richiama i servizi Silp per verifcare se sia possibile inserire
     * eventualmente la did o altrimenti restituisce i messaggi di errore.
     *
     * @param idUtente        the id utente
     * @param scrivereLogSuDb the scrivere log su db
     * @param iDatiDellaDid   the i dati della did
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response controlloDidService(Long idUtente, String scrivereLogSuDb, DatiInputAggiornamentoDid iDatiDellaDid, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        /*
         * sovrascrivo la data di sistema onde evitare che sul sistema dell'utente ci
         * sia una data sbagliata in questo modo la data viene presa dai sistemi di csi,
         * e non dalla data del sistema dell'utente.
         */
        iDatiDellaDid.setDataStatoDid(new Date());
        if (iDatiDellaDid.getCodStatoDid().equalsIgnoreCase("I")) {
            iDatiDellaDid.setDataDid(new Date());
        }
        log.info("[DidApiServiceImpl::saveDidService] idUtente=" + idUtente + I_DATI_PROFILING + iDatiDellaDid);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        boolean scrivereLog = true;
        if (null != scrivereLogSuDb && !scrivereLogSuDb.equals("") && scrivereLogSuDb.equalsIgnoreCase("N")) {
            scrivereLog = false;
        } else {
            scrivereLog = true;
        }
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.SAVE_DID_CONTROLLER, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);

            /* PRIMA CHIAMATA AL SERVIZIO SOLO PER CONTROLLI */
            EsitoSaveDid esitoDid = AdapterSilpserver.getInstance().saveDidService(codiceFiscaleUtente, iDatiDellaDid, true);
            settaggioMsgErrori(httpRequest, esitoDid, idUtente, TracciamentoUtils.SAVE_DID_CONTROLLER, scrivereLog);
            settaggioMsgInfo(httpRequest, esitoDid, idUtente, TracciamentoUtils.SAVE_DID_CONTROLLER, scrivereLog);
            return Response.ok(esitoDid).build();
        } catch (Exception ex) {
            log.error("[DidApiServiceImpl::saveDidService]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_DID_CONTROLLER, httpRequest, idUtente,
                    ERRORE_DI_SISTEMA + ex.getClass().getName() + DID + iDatiDellaDid.toString(), "DID");
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DidApiServiceImpl.CLASSE, "saveDidService()", "invocazione API DidApiServiceImpl.saveDidService", "");
            watcher.stop();
        }

    }

    /**
     * Settaggio msg errori.
     * 
     * Nel caso in cui un servizio restituisca messaggi di tipo ERRORE questo metodo
     * li traduce per poterli visualizzare in PSLP front-end.
     *
     * @param httpRequest     the http request
     * @param esitoDid        the esito did
     * @param idUtente        the id utente
     * @param traccia         the traccia
     * @param scrivereLogSuDb the scrivere log su db
     * @throws Exception the exception
     */
    private void settaggioMsgErrori(HttpServletRequest httpRequest, EsitoSaveDid esitoDid, Long idUtente, Long traccia, boolean scrivereLogSuDb)
            throws Exception {
        if (null != esitoDid.getError() && !esitoDid.getError().isEmpty()) {
            if (scrivereLogSuDb) {
                tracciamentoUtils.tracciaKo(traccia, httpRequest, idUtente, esitoDid.getMessaggioErrori(), "DID");
            }
            StringBuilder descrErrori = new StringBuilder().append(messaggiUtils.loadMessaggio(MessaggiUtils.ME148).getTesto() + "");
            for (MappaErrori map : esitoDid.getError()) {
                if (null != map) {
                    if (null != map.getCodiceMessaggio()) {
                        switch (map.getCodiceMessaggio()) {
                        case "MSG-DID-ANPAL-01":
                            descrErrori.append("- " + map.getDescrMessaggio() + BR);
                            break;
                        case "MSG-DID-ANPAL-02":
                            descrErrori = new StringBuilder().append(messaggiUtils.loadMessaggio(MessaggiUtils.ME003).getTesto() + "");
                            break;
                        case "MSG-DID-ANPAL-12":
                            descrErrori = new StringBuilder().append(messaggiUtils.loadMessaggio(MessaggiUtils.ME153).getTesto() + "");
                            break;
                        case "MSG-DID-ANPAL-21":
                            descrErrori.append("- " + map.getDescrMessaggio() + BR);
                            break;
                        case "MSG-DID-ANPAL-22":
                            descrErrori.append("- " + map.getDescrMessaggio() + BR);
                            break;
                        case "MSG-DID-ANPAL-24":
                            descrErrori.append("- " + map.getDescrMessaggio() + BR);
                            break;
                        case "MSG-DID-ANPAL-31":
                            descrErrori.append("- " + map.getDescrMessaggio() + BR);
                            break;
                        case "NO_DOM_PIEMONTE":
                            descrErrori.append("- " + map.getDescrMessaggio() + BR);
                            break;
                        default:
                            descrErrori = new StringBuilder().append(messaggiUtils.loadMessaggio(MessaggiUtils.ME002).getTesto() + "");
                            break;
                        }
                    }
                }
            }
            esitoDid.setMessaggioErrori(descrErrori.toString());
        }
    }

    /**
     * Settaggio msg info.
     *
     * Nel caso in cui un servizio restituisca degli messaggi di tipo INFO questo
     * metodo li traduce per poterli gestire in PSLP front-end.
     * 
     * @param httpRequest     the http request
     * @param esitoDid        the esito did
     * @param idUtente        the id utente
     * @param traccia         the traccia
     * @param scrivereLogSuDb the scrivere log su db
     * @throws Exception the exception
     */
    private void settaggioMsgInfo(HttpServletRequest httpRequest, EsitoSaveDid esitoDid, Long idUtente, Long traccia, boolean scrivereLogSuDb)
            throws Exception {
        if (null != esitoDid.getInfo() && !esitoDid.getInfo().isEmpty()) {
            if (scrivereLogSuDb) {
                tracciamentoUtils.tracciaOk(traccia, httpRequest, idUtente, esitoDid.getMessaggioInfo(), "DID");
            }
            StringBuilder descrInfoSB = new StringBuilder().append(messaggiUtils.loadMessaggio(MessaggiUtils.ME148).getTesto() + "");
            for (MappaErrori map : esitoDid.getInfo()) {
                if (null != map) {
                    if (null != map.getCodiceMessaggio()) {
                        switch (map.getCodiceMessaggio()) {
                        case "MSG-DID-ANPAL-06":
                            String msgModificato_45 = messaggiUtils.loadMessaggio(MessaggiUtils.MI045).getTesto().replace("{0}", "") + "";
                            msgModificato_45 = msgModificato_45.replace("{1}", "") + "";
                            descrInfoSB = new StringBuilder().append(msgModificato_45);
                            break;
                        case "MSG-DID-ANPAL-11":
                            String msgModificato_44 = messaggiUtils.loadMessaggio(MessaggiUtils.MI044).getTesto().replace("{0}", "") + "";
                            msgModificato_44 = msgModificato_44.replace("{1}", "") + "";
                            descrInfoSB = new StringBuilder().append(msgModificato_44);
                            break;
                        case "MSG-DID-ANPAL-25":
                            String msgModificato_45_46 = messaggiUtils.loadMessaggio(MessaggiUtils.MI045).getTesto().replace("{0}", " non ") + "";
                            msgModificato_45_46 = msgModificato_45_46.replace("{1}", messaggiUtils.loadMessaggio(MessaggiUtils.MI046).getTesto()) + "";
                            descrInfoSB = new StringBuilder().append(msgModificato_45_46);
                            break;
                        case "MSG-DID-ANPAL-34":
                            String msgModificato_44_46 = messaggiUtils.loadMessaggio(MessaggiUtils.MI044).getTesto().replace("{0}", " non ") + "";
                            msgModificato_44_46 = msgModificato_44_46.replace("{1}", messaggiUtils.loadMessaggio(MessaggiUtils.MI046).getTesto()) + "";
                            descrInfoSB = new StringBuilder().append(msgModificato_44_46);
                            break;
                        default:
                            descrInfoSB = new StringBuilder().append(messaggiUtils.loadMessaggio(MessaggiUtils.ME002).getTesto() + "");
                            break;
                        }
                    }
                }
            }
            esitoDid.setMessaggioInfo(descrInfoSB.toString());
        }
    }

    /**
     * Gets the utente by id.
     * 
     * Metodo per farsi restituire l'utente in base all'id che gli viene passato
     *
     * @param idUtente the id utente
     * @return the utente by id
     * @throws DAOException the DAO exception
     */
    private UtenteDTO getUtenteById(Long idUtente) throws DAOException {
        UtenteFilter utenteFilter = new UtenteFilter();
        utenteFilter.getIdUtente().eq(idUtente);
        UtenteDTO utenteDTO = dao.findFirst(UtenteDBDef.class, utenteFilter);
        return utenteDTO;
    }

    /**
     * Ricerca dettaglio DID service.
     * 
     * Metodo che restituisce il dettaglio della did legata all'idUtente che si sta
     * passando. Eventualmente se non fosse presente non restituisce nulla o
     * messaggi di errore
     *
     * @param idUtente        the id utente
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response ricercaDettaglioDIDService(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DidApiServiceImpl::ricercaDettaglioDIDService]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        String msgME002 = "";
        try {
            msgME002 = messaggiUtils.loadMessaggio(MessaggiUtils.ME002).getTesto();

            UtenteDTO utente = getUtenteById(idUtente);
            EsitoDettaglioDid esitoRicercaDid = AdapterSilpserver.getInstance().ricercaDettaglioDIDService(utente.getCfUtente(), utente.getIdSilLavAngrafica());
            if (null != esitoRicercaDid) {
                if (null != esitoRicercaDid.getError() && !esitoRicercaDid.getError().isEmpty()) {
                    List<String> descrErrori = new ArrayList<String>();
                    StringBuilder laStringaDegliErrori = new StringBuilder();
                    for (String codiceErrore : esitoRicercaDid.getError()) {
                        switch (codiceErrore) {
                        case "MSG-DID-SRV-01":
                            laStringaDegliErrori.append("MSG-DID-SRV-01 - Valorizzare il filtro di ricerca con l'identificativo lavoratore; ");
                            break;
                        case "MSG-DID-SRV-02":
                            laStringaDegliErrori.append("MSG-DID-SRV-02 - Esiste piu' di una DID aperta per il lavoratore; ");
                            break;
                        case "MSG-DID-SRV-03":
                            laStringaDegliErrori
                                    .append("MSG-DID-SRV-03 - La data did lavoratore non coincide con la data did presente nell'ultimo profiling; ");
                            break;
                        case "MSG-DID-SRV-SYS-ERR":
                            laStringaDegliErrori.append("MSG-DID-SRV-SYS-ERR - Errore di sistema proveniente da Servizio di ricerca; ");
                            break;
                        default:
                            laStringaDegliErrori.append(codiceErrore).append(" - messaggio di errore non presente; ");
                            break;
                        }
                        if (null != messaggiUtils.loadMessaggio(codiceErrore)) {
                            descrErrori.add(messaggiUtils.loadMessaggio(codiceErrore).getTesto());
                            laStringaDegliErrori.append(codiceErrore).append(" - ").append(messaggiUtils.loadMessaggio(codiceErrore).getTesto()).append(";");
                        } else {
                            descrErrori.add(messaggiUtils.loadMessaggio(MessaggiUtils.ME003).getTesto());
                        }
                    }
                    esitoRicercaDid.setError(descrErrori);
                    tracciamentoUtils.tracciaKo(TracciamentoUtils.RICERCA_DETTAGLIO_DID, httpRequest, idUtente, laStringaDegliErrori.toString(), "DID");
                } else if (null != esitoRicercaDid.getErroreRicercaDid()) {
                    throw new Exception(esitoRicercaDid.getErroreRicercaDid().getErrorMessage());
                }
                if (null != esitoRicercaDid.getFlgStatoFinale() && esitoRicercaDid.getFlgStatoFinale().equalsIgnoreCase("N")
                        && (null == esitoRicercaDid.getDataDid() || null == esitoRicercaDid.getDataStato())) {
                    tracciamentoUtils.tracciaKo(TracciamentoUtils.RICERCA_DETTAGLIO_DID, httpRequest, idUtente, "DATA STATO O DATA DID NON VALORIZZATE ",
                            "DID");

                }
                if (null != esitoRicercaDid.getCodUltimoStato() && esitoRicercaDid.getCodUltimoStato().equalsIgnoreCase("E")) {
                    tracciamentoUtils.tracciaKo(TracciamentoUtils.RICERCA_DETTAGLIO_DID, httpRequest, idUtente, "STATO DID \"E\" NON IDONEO ", "DID");

                }
            }
            if (null != esitoRicercaDid && null != esitoRicercaDid.getCodUltimoStato() && esitoRicercaDid.getCodUltimoStato().equals("E")) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.RICERCA_DETTAGLIO_DID, httpRequest, idUtente, "DID IN STATO NON AMMESSO ", "DID");
            }

            return Response.ok(esitoRicercaDid).build();
        } catch (Exception ex) {
            log.error("[DidApiServiceImpl::ricercaDettaglioDIDService]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.RICERCA_DETTAGLIO_DID, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), "DID");
            EsitoDettaglioDid esitoRicercaDid = new EsitoDettaglioDid();
            List<String> errori = new ArrayList<String>();
            errori.add(msgME002);
            esitoRicercaDid.setError(errori);
            return Response.ok(esitoRicercaDid).build();
        } finally {
            watcher.dumpElapsed(DidApiServiceImpl.CLASSE, "ricercaDettaglioDIDService()", "invocazione API DidApiServiceImpl.ricercaDettaglioDIDService", "");
            watcher.stop();
        }
    }

    /** The parametro utils. */
    @Autowired
    private ParametroUtils parametroUtils;

    /**
     * Gets the parm.
     * 
     * @param codice the codice
     * @return the parm
     * @throws Exception the exception
     */
    private boolean getParm(String codice) throws Exception {
        ParametroDTO parametroDTO = parametroUtils.getParametroDTO(codice);
        String valoreParametro = (String) SilCommonUtils.nvl(parametroDTO.getValoreParametroExt(), parametroDTO.getValoreParametro());
        boolean flg = ("S".equalsIgnoreCase(valoreParametro) ? true : false);
        return flg;
    }

    /**
     * Log service.
     * 
     * Metodo che permette a PSLP lato front-end di scrivere sulla tabella di log
     *
     * @param idUtente        the id utente
     * @param msg             the msg
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response logService(Long idUtente, String msg, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DidApiServiceImpl::logService]");
        tracciamentoUtils.tracciaOk(TracciamentoUtils.LOG_DID, httpRequest, idUtente, msg, "DID");
        return null;
    }

}
