/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslcommonobj.dbdef.DocumentoDBDef;
import it.csi.pslp.pslcommonobj.dbdef.RelUtenteDBDef;
import it.csi.pslp.pslcommonobj.dbdef.RelUtentePrivacyDBDef;
import it.csi.pslp.pslcommonobj.dbdef.ResponsabileDBDef;
import it.csi.pslp.pslcommonobj.dbdef.UtenteDBDef;
import it.csi.pslp.pslcommonobj.dbdef.UtentePrivacyDBDef;
import it.csi.pslp.pslcommonobj.dto.AmbitoDTO;
import it.csi.pslp.pslcommonobj.dto.CalendarioDTO;
import it.csi.pslp.pslcommonobj.dto.EsitoSalvataggioIncontroDTO;
import it.csi.pslp.pslcommonobj.dto.ParametriSalvataggioIncontroDTO;
import it.csi.pslp.pslcommonobj.dto.PrenotazioneDTO;
import it.csi.pslp.pslcommonobj.dto.PrenotazioniDTO;
import it.csi.pslp.pslcommonobj.dto.RelUtenteDTO;
import it.csi.pslp.pslcommonobj.dto.RelUtentePrivacyDTO;
import it.csi.pslp.pslcommonobj.dto.ResponsabileDTO;
import it.csi.pslp.pslcommonobj.dto.SlotDTO;
import it.csi.pslp.pslcommonobj.dto.TipoResponsabilitaDTO;
import it.csi.pslp.pslcommonobj.dto.UtenteDTO;
import it.csi.pslp.pslcommonobj.dto.UtentePrivacyDTO;
import it.csi.pslp.pslcommonobj.filter.DocumentoFilter;
import it.csi.pslp.pslcommonobj.filter.RelUtenteFilter;
import it.csi.pslp.pslcommonobj.filter.RelUtentePrivacyFilter;
import it.csi.pslp.pslcommonobj.filter.ResponsabileFilter;
import it.csi.pslp.pslcommonobj.filter.UtenteFilter;
import it.csi.pslp.pslcommonobj.filter.UtentePrivacyFilter;
import it.csi.pslp.pslweb.business.be.UtentiApi;
import it.csi.pslp.pslweb.business.integration.AdapterOrchsilWSImpl;
import it.csi.pslp.pslweb.business.integration.AdapterSilpserver;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvalWSImpl;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvdeWSImpl;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvinWSImpl;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvspWSImpl;
import it.csi.pslp.pslweb.business.integration.EsitoRicercaLavoratore;
import it.csi.pslp.pslweb.business.integration.PslOrchApiClient;
import it.csi.pslp.pslweb.dto.be.AdesioneYG;
import it.csi.pslp.pslweb.dto.be.ControlliIscrizione;
import it.csi.pslp.pslweb.dto.be.DatiInputStatoAdesione;
import it.csi.pslp.pslweb.dto.be.Documento;
import it.csi.pslp.pslweb.dto.be.DomandaRDC;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.Esito;
import it.csi.pslp.pslweb.dto.be.EsitoRiepilogoIscrizione;
import it.csi.pslp.pslweb.dto.be.EsitoSendStatoAdesione;
import it.csi.pslp.pslweb.dto.be.EsitoVerificaEsistenzaSap;
import it.csi.pslp.pslweb.dto.be.EsitoVerificaMinore;
import it.csi.pslp.pslweb.dto.be.InformazioneAggiuntiva;
import it.csi.pslp.pslweb.dto.be.Lavoratore;
import it.csi.pslp.pslweb.dto.be.MappaErrori;
import it.csi.pslp.pslweb.dto.be.ParametriCalcoloProfilingYG;
import it.csi.pslp.pslweb.dto.be.ParametriSalvataggioSAP;
import it.csi.pslp.pslweb.dto.be.PrenotazioneIncontro;
import it.csi.pslp.pslweb.dto.be.ProfilingYG;
import it.csi.pslp.pslweb.dto.be.Responsabile;
import it.csi.pslp.pslweb.dto.be.SchedaAnagraficoProfessionale;
import it.csi.pslp.pslweb.dto.be.SlotIncontro;
import it.csi.pslp.pslweb.dto.be.Sportello;
import it.csi.pslp.pslweb.dto.be.TipoResponsabilita;
import it.csi.pslp.pslweb.dto.be.Utente;
import it.csi.pslp.pslweb.dto.be.UtenteACarico;
import it.csi.pslp.pslweb.dto.be.UtenteACaricoComparable;
import it.csi.pslp.pslweb.dto.be.UtentePresaVisione;
import it.csi.pslp.pslweb.dto.be.UtenteRiepilogoIscrizione;
import it.csi.pslp.pslweb.dto.be.UtenteRiepilogoIscrizioneComparable;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.GsonUtils;
import it.csi.pslp.pslweb.util.ParametroUtils;
import it.csi.pslp.pslweb.util.RestHelper;
import it.csi.pslp.pslweb.util.SpringSupportedResource;
import it.csi.pslp.pslweb.util.mapper.DocumentoMapper;
import it.csi.pslp.pslweb.util.mapper.ResponsabileMapper;
import it.csi.pslp.pslweb.util.mapper.SapMapper;
import it.csi.pslp.pslweb.util.mapper.UtenteMapper;
import it.csi.silos.jedi.core.DAO;
import it.csi.silos.jedi.core.DAOException;
import it.csi.silos.jedi.core.QueryResult;
import it.csi.silos.silcommon.constants.SilCommonConstants;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.EsitoOperazioneInvioNuovaSap;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.EsitoOperazioneSap;
import it.csi.silos.silcommon.util.SilCommonUtils;
import it.csi.silos.silcommon.util.SilTimeUtils;
import it.csi.silpcommonobj.util.SilpConvertUtils;
import it.csi.util.performance.StopWatch;

// TODO: Auto-generated Javadoc
/**
 * The Class UtentiApiServiceImpl.
 */
@Component("utentiApi")
public class UtentiApiServiceImpl extends SpringSupportedResource implements UtentiApi {

    /** The Constant COD_AMBITO2. */
    private static final String COD_AMBITO2 = ", codAmbito=";

    /** The Constant COND_OCCUP. */
    private static final String COND_OCCUP = ",condOccup=";

    /** The Constant COD_AMBITO. */
    private static final String COD_AMBITO = ",codAmbito=";

    /** The Constant PRES_IN_ITALIA2. */
    private static final String PRES_IN_ITALIA2 = ",presInItalia=";

    /** The Constant TITOLO_STUDIO. */
    private static final String TITOLO_STUDIO = ",titoloStudio=";

    /** The Constant ERRORE_DI_SISTEMA. */
    private static final String ERRORE_DI_SISTEMA = "Errore di sistema: ";

    /** The Constant UTENTE_NON_PRESENTE. */
    private static final String UTENTE_NON_PRESENTE = "Utente non presente: ";

    /** The Constant UTENTI_API_SERVICE_IMPL_GET_DATI_RESPONSABILE_ID_UTENTE. */
    private static final String UTENTI_API_SERVICE_IMPL_GET_DATI_RESPONSABILE_ID_UTENTE = "[UtentiApiServiceImpl::getDatiResponsabile] idUtente=";

    /**
     * The Constant
     * UTENTI_API_SERVICE_IMPL_GET_UTENTE_BY_CF_ESITO_402_MESSAGGIO_ME002.
     */
    private static final String UTENTI_API_SERVICE_IMPL_GET_UTENTE_BY_CF_ESITO_402_MESSAGGIO_ME002 = "[UtentiApiServiceImpl::getUtenteByCf] esito=402 - Messaggio ME002";

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

    /** The date utils. */
    @Autowired
    private DBDateUtils dateUtils;

    /**
     * Ricerca il cittadino su SILP per codice fiscale e su PLSP per
     * idSilLavAnagrafica. Se il cittadino non e' presente su SILP con quel codice
     * fiscale, o sono presenti piu' record, restituisce un errore.
     *
     * @param codiceFiscale   the codice fiscale
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the utente by cf
     */
    @Override
    public Response getUtenteByCf(String codiceFiscale, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        codiceFiscale = StringUtils.upperCase(codiceFiscale);
        log.info("[UtentiApiServiceImpl::getUtenteByCf] codiceFiscale=" + codiceFiscale);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        Utente utente = new Utente();
        Long idUtente = null;
        try {
            utente.setCodiceFiscale(codiceFiscale);
            UtenteFilter utenteFilterCF = new UtenteFilter();
            utenteFilterCF.getCodTipoUtente().eq("CIT");
            utenteFilterCF.getCfUtente().eq(utente.getCodiceFiscale());
            UtenteDTO utenteDTOCF = dao.findFirst(UtenteDBDef.class, utenteFilterCF);
            if (utenteDTOCF != null) {
                utente.setIdUtente(utenteDTOCF.getIdUtente());
                idUtente = utenteDTOCF.getIdUtente();
                utente.setEmail(utenteDTOCF.getEmail());
                utente.setIdentificativoSap(utenteDTOCF.getIdentificativoSap());
                utente.setCognome(utenteDTOCF.getCognome());
                utente.setNome(utenteDTOCF.getNome());
                utente.setPreseVisione(getPreseVisioneUtente(idUtente));
            }

            // Ricerca il lavoratore su SILPSV.silpsval
            EsitoRicercaLavoratore esitoRicercaLavoratore = AdapterSilpsvalWSImpl.getInstance().ricercaLavoratoreInfoArchiviato(codiceFiscale);

            if (esitoRicercaLavoratore.getCodiceErrore() != null) {
                log.error("[UtentiApiServiceImpl::getUtenteByCf] Eccezione restituita da SILPSV.silpsval: " + esitoRicercaLavoratore.getCodiceErrore() + " - "
                        + esitoRicercaLavoratore.getMessaggioErrore());
                tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_UTENTE, httpRequest, idUtente,
                        "Errore nella chiamata a silpsval: " + esitoRicercaLavoratore.getCodiceErrore() + " - " + esitoRicercaLavoratore.getMessaggioErrore(),
                        null);
                return createErrorResponse("500", MessaggiUtils.ME002);
            }

            if (esitoRicercaLavoratore.getLavoratori().size() == 1) {

                // Valorizza l'utente da restituire al client
                Lavoratore lavoratore = esitoRicercaLavoratore.getLavoratori().get(0);
                utente.setCognome(lavoratore.getCognome());
                utente.setNome(lavoratore.getNome());
                utente.setIdSilLavAnagrafica(lavoratore.getIdLavoratore().longValue());

                // Ricerca l'utente su PSLP per id e codice fiscale
                UtenteFilter utenteFilter1 = new UtenteFilter();
                utenteFilter1.getIdSilLavAngrafica().eq(utente.getIdSilLavAnagrafica());
                UtenteDTO utenteDTO1 = dao.findFirst(UtenteDBDef.class, utenteFilter1);

                // Se il lavoratore non e' registrato su PSLP lo restituisco senza idUtente
                if (utenteDTO1 == null && utenteDTOCF == null) {
                    tracciamentoUtils.tracciaOk(TracciamentoUtils.GET_UTENTE, httpRequest, idUtente, "Utente non registrato su PSLP (" + codiceFiscale + ")",
                            null);
                    return Response.ok(utente).build();
                }

                // Se l'utente ha idSilLavAnagrafica a null lo restituisco
                if (utenteDTO1 == null && utenteDTOCF != null && utenteDTOCF.getIdSilLavAngrafica() == null) {
                    log.debug("[UtentiApiServiceImpl::getUtenteByCf] Aggiornata idSilLavAnagrafica; utente=" + utente);
                    utenteDTOCF.setIdSilLavAngrafica(utente.getIdSilLavAnagrafica());
                    dao.update(UtenteDBDef.class, utenteDTOCF);
                    tracciamentoUtils.tracciaOk(TracciamentoUtils.GET_UTENTE, httpRequest, idUtente, "utente idSilLavAnagrafica null (" + codiceFiscale + ")", null);
                    return Response.ok(utente).build();
                }

                // Verifica che l'utente registrato su PLSP abbia cf e idSilLavAnagrafica
                // congruenti
                if ((utenteDTO1 != null && utenteDTOCF == null) || (utenteDTO1 == null && utenteDTOCF != null)) {
                    tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_UTENTE, httpRequest, idUtente, "Disallineamento idUtente/CF fra PSLP e SILP (401) codFisc='"+codiceFiscale+"'", null);
                    log.debug("[UtentiApiServiceImpl::getUtenteByCf] esito=401 - Messaggio ME002");
                    return createErrorResponse("401", MessaggiUtils.ME002); // Sostituito a ME049
                }
                if (!utenteDTO1.getIdUtente().equals(utenteDTOCF.getIdUtente())) {
                    tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_UTENTE, httpRequest, idUtente, "Disallineamento idUtente/CF fra PSLP e SILP (402) codFisc=' "+codiceFiscale+ "'", null);
                    log.debug(UTENTI_API_SERVICE_IMPL_GET_UTENTE_BY_CF_ESITO_402_MESSAGGIO_ME002);
                    return createErrorResponse("402", MessaggiUtils.ME002); // Sostituito a ME049
                }

                // Restituisce il lavoratore al client
                log.debug("[UtentiApiServiceImpl::getUtenteByCf] utente=" + utente);
                tracciamentoUtils.tracciaOk(TracciamentoUtils.GET_UTENTE, httpRequest, idUtente, "utente restituito", null);
                return Response.ok(utente).build();
            }

            if (esitoRicercaLavoratore.getLavoratori().size() > 1) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_UTENTE, httpRequest, idUtente,
                        "Trovati piu' lavoratori per il codice fiscale " + codiceFiscale, null);
                log.debug(UTENTI_API_SERVICE_IMPL_GET_UTENTE_BY_CF_ESITO_402_MESSAGGIO_ME002);
                return createErrorResponse("402", MessaggiUtils.ME002); // Sostituito a ME049
            }
            if (esitoRicercaLavoratore.isArchiviatiPresenti()) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_UTENTE, httpRequest, idUtente,
                        " Lavoratore non presente su PSLP e reperito su SILP per codice fiscale  ma solo archiviato " + codiceFiscale, null);
                log.debug(UTENTI_API_SERVICE_IMPL_GET_UTENTE_BY_CF_ESITO_402_MESSAGGIO_ME002);
                return createErrorResponse("402", MessaggiUtils.ME002);
            }
            // Nessun lavoratore trovato su SILP
            if (utente.getIdUtente() != null) {
                // Solo su PSLP e non su SILP: restituisco l'utente
                tracciamentoUtils.tracciaOk(TracciamentoUtils.GET_UTENTE, httpRequest, idUtente,
                        "Lavoratore presente su PSLP ma non reperito su SILP per codice fiscale " + codiceFiscale, null);
                log.debug("[UtentiApiServiceImpl::getUtenteByCf] utente: " + utente);
                return Response.ok(utente).build();
            }

            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_UTENTE, httpRequest, idUtente,
                    "Lavoratore non presente su PSLP e non reperito su SILP per codice fiscale " + codiceFiscale, null);
            log.debug("[UtentiApiServiceImpl::getUtenteByCf] esito=404 - Messaggio ME014");
            return createErrorResponse("404", MessaggiUtils.ME048);
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::getUtenteByCf] codiceFiscale=" + codiceFiscale, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_UTENTE, httpRequest, idUtente,
                    ERRORE_DI_SISTEMA + ex.getClass().getName() + "; codiceFiscale=" + codiceFiscale, null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "getUtenteByCf()", "invocazione API UtentiApiServiceImpl.getUtenteByCf", "");
            watcher.stop();
        }
    }

    /**
     * Gets the prese visione utente.
     *
     * @param idUtente the id utente
     * @return the prese visione utente
     * @throws DAOException the DAO exception
     */
    private List<UtentePresaVisione> getPreseVisioneUtente(Long idUtente) throws DAOException {
        List<UtentePresaVisione> preseVisione = new ArrayList<>();
        UtentePrivacyFilter utentePrivacyFilter = new UtentePrivacyFilter();
        utentePrivacyFilter.getIdUtente().eq(idUtente);
        for (UtentePrivacyDTO relUtentePrivacyDTO : dao.findAll(UtentePrivacyDBDef.class, utentePrivacyFilter, 0)) {
            UtentePresaVisione presaVisione = new UtentePresaVisione();
            presaVisione.setCodAmbito(relUtentePrivacyDTO.getCodAmbito());
            preseVisione.add(presaVisione);
        }
        return preseVisione;
    }

    /**
     * Gets the utente by id.
     *
     * @param idUtente        the id utente
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the utente by id
     */
    @Override
    public Response getUtenteById(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.GET_UTENTE, idUtente);
            UtenteFilter utenteFilter = new UtenteFilter();
            utenteFilter.getIdUtente().eq(idUtente);
            UtenteDTO utenteDTO = dao.findFirst(UtenteDBDef.class, utenteFilter);
            if (utenteDTO == null) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_UTENTE, httpRequest, idUtente, "Lavoratore non presente su PSLP " + idUtente, null);
                log.debug("[UtentiApiServiceImpl::getUtenteByCf] esito=404 - Messaggio ME014");
                return createErrorResponse("404", MessaggiUtils.ME014);
            }
            Utente utente = new Utente();
            utente.setIdUtente(utenteDTO.getIdUtente());
            utente.setCodiceFiscale(utenteDTO.getCfUtente());
            utente.setEmail(utenteDTO.getEmail());
            utente.setIdentificativoSap(utenteDTO.getIdentificativoSap());
            utente.setCodiceTipoUtente(utenteDTO.getCodTipoUtente());
            utente.setIdSilLavAnagrafica(utenteDTO.getIdSilLavAngrafica());
            utente.setCognome(utenteDTO.getCognome());
            utente.setNome(utenteDTO.getNome());
            utente.setPreseVisione(getPreseVisioneUtente(idUtente));
            return Response.ok(utente).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::getUtenteById] id=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_UTENTE, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName() + "; id=" + idUtente,
                    null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "getUtenteById()", "invocazione API UtentiApiServiceImpl.getUtenteById", "");
            watcher.stop();
        }
    }

    /**
     * Verifica esistenza SAP su sistema ministeriale.
     *
     * @param codiceFiscale   the codice fiscale
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response verificaEsistenzaSAPSuSistemaMinisteriale(String codiceFiscale, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::verificaEsistenzaSAPSuSistemaMinisteriale] codiceFiscale=" + codiceFiscale);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            EsitoVerificaEsistenzaSap esitoVerificaSap = new EsitoVerificaEsistenzaSap();
            restHelper.checkUtente(httpRequest, TracciamentoUtils.GET_SAP, codiceFiscale);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            EsitoOperazioneSap esitoVerificaSapOrchsil = AdapterOrchsilWSImpl.getInstance().verificaEsistenzaSAP(codiceFiscaleUtente, codiceFiscale);
            if (esitoVerificaSapOrchsil != null && SilCommonUtils.isNotVoid(esitoVerificaSapOrchsil.getIdentificativoSap())) {
                log.debug("[UtentiApiServiceImpl::verificaEsistenzaSAPSuSistemaMinisteriale] codiceSAP=" + esitoVerificaSapOrchsil.getIdentificativoSap());
                esitoVerificaSap.setCodiceSap(esitoVerificaSapOrchsil.getIdentificativoSap());
                return Response.ok(esitoVerificaSap).build();
            } else {
                String messaggi = "";
                if (esitoVerificaSapOrchsil != null) {
                    messaggi = esitoVerificaSapOrchsil.getMessaggiAllAsString();
                }
                log.debug("[UtentiApiServiceImpl::verificaEsistenzaSAPSuSistemaMinisteriale] messaggi=" + messaggi);
                // Valutare se tracciare con un nuovo codice
                // tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_SAP, httpRequest, idUtente,

                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage(messaggi);
                return Response.status(404).entity(err).build();

            }
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::verificaEsistenzaSAPSuSistemaMinisteriale] EXCEPTION codiceFiscale=" + codiceFiscale, ex);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "verificaEsistenzaSAPSuSistemaMinisteriale()",
                    "invocazione API UtentiApiServiceImpl.verificaEsistenzaSAPSuSistemaMinisteriale", "");
            watcher.stop();
        }
    }

    /**
     * Gets the sap.
     *
     * @param idUtente        the id utente
     * @param codAmbito       the cod ambito
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the sap
     */
    @Override
    public Response getSAP(Long idUtente, String codAmbito, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::getSAP] idUtente=" + idUtente + COD_AMBITO2 + codAmbito);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.GET_SAP, idUtente);
            UtenteDTO utente = getUtenteById(idUtente);
            if (utente == null) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_SAP, httpRequest, idUtente, "Utente non presente per idUtente=" + idUtente, codAmbito);
                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage(UTENTE_NON_PRESENTE + idUtente);
                log.debug("[UtentiApiServiceImpl::getSAP] esito=404-" + err.getErrorMessage());
                return Response.serverError().entity(err).status(404).build();
            }
            SchedaAnagraficoProfessionale sap = getSapConVerificaEsistenza(utente.getIdSilLavAngrafica(), utente.getCfUtente(),
                    restHelper.getCodiceFiscaleCurrentUser(httpRequest));
            if (sap != null) {
                if (StringUtils.isBlank(sap.getIdentificativoSap()) && isAmbitoCheRichiedeIdentificativoSap(codAmbito)) {
                    tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_SAP, httpRequest, idUtente,
                            "IdentificativoSap non valorizzato per idSilp=" + utente.getIdSilLavAngrafica(), codAmbito);
                    log.debug("[UtentiApiServiceImpl::getSAP] esito=402 - Messaggio ME003");
                    return createErrorResponse("402", MessaggiUtils.ME003);
                }

                sap.setIdSilLavAnagrafica(utente.getIdSilLavAngrafica());
                if (sap.getRecapito() != null && sap.getRecapito().getEmail() != null) {
                    utente.setEmail(sap.getRecapito().getEmail());
                }
                utente.setIdentificativoSap(sap.getIdentificativoSap());
                utente.setCognome(sap.getCognome());
                utente.setNome(sap.getNome());
                dao.update(UtenteDBDef.class, utente);
                log.debug("[UtentiApiServiceImpl::getSAP] sap=" + sap);
                return Response.ok(sap).build();
            } else { // Nessuna SAP trovata
                tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_SAP, httpRequest, idUtente, "SAP non presente per idUtente=" + idUtente, codAmbito);
                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage("Scheda AnagraficoProfessionale non trovata per l'utente " + idUtente);
                log.debug("[UtentiApiServiceImpl::getSAP] esito=404-" + err.getErrorMessage());
                return Response.serverError().entity(err).status(404).build();
            }
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::getSAP] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_SAP, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), codAmbito);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "getSAP()", "invocazione API UtentiApiServiceImpl.getSAP", "");
            watcher.stop();
        }
    }

    /**
     * Per alcuni ambiti funzionali la sap ottenuta avere un codice sap valorizzato,
     * ossia essere gia' stata interscambiata con i sistemi ministariali.
     * 
     * Attualmente gli ambiti che lo richiedono sono Geranzia Giovani e Reddito di
     * Cittadinanza
     * 
     * negli altri casi la sap puo' provenire semplicemnte dai dati di silp
     *
     * @param codAmbito the cod ambito
     * @return true, if is ambito che richiede identificativo sap
     */
    private boolean isAmbitoCheRichiedeIdentificativoSap(String codAmbito) {
        return SilCommonUtils.in(codAmbito, AmbitoDTO.COD_AMBITO_GG_GARANZIA_GIOVANI, AmbitoDTO.COD_AMBITO_RDC_REDDITO_DI_CITTADINANZA);
    }

    /**
     * Richiede la sap su silp dato un identificativo lavoratore silp. Nel caso non
     * la trovi effettua una verifica esistenza sap sui sistemi ministeriali con il
     * codice fiscale cittadino. Se viene trovata, il processo invocato effettua
     * gia' l'importazione su silp e viene ricaricata sa esso
     *
     * @param idAnagraficaSilp             the id anagrafica silp
     * @param codiceFiscaleSAP             the codice fiscale SAP
     * @param codiceFiscaleUtenteCollegato the codice fiscale utente collegato
     * @return the sap con verifica esistenza
     * @throws Exception the exception
     */
    private SchedaAnagraficoProfessionale getSapConVerificaEsistenza(Long idAnagraficaSilp, String codiceFiscaleSAP, String codiceFiscaleUtenteCollegato)
            throws Exception {
        log.info("[UtentiApiServiceImpl::getSapConVerificaEsistenza] idAnagraficaSilp=" + idAnagraficaSilp + " codiceFiscale=" + codiceFiscaleSAP);
        SchedaAnagraficoProfessionale sap = null;
        if (SilCommonUtils.isVoid(idAnagraficaSilp) && SilCommonUtils.isVoid(codiceFiscaleSAP)) {
            throw new IllegalArgumentException("Parametri obbligatori mancanti in invocazione metodo getSapConVerificaEsistenza ");
        }

        // 1. richiesta sap direttamente a silp, avendo l'id anagrafica
        if (idAnagraficaSilp != null) {
            sap = getSAP(idAnagraficaSilp, null, null);
        }

        // 2. se non esiste su silp di prova a cercarla su ministero e import in silp
        if ((sap == null || sap.getIdentificativoSap() == null) && SilCommonUtils.isNotVoid(codiceFiscaleSAP)) {
            EsitoOperazioneSap esitoVerificaEsistenza = AdapterOrchsilWSImpl.getInstance().verificaEsistenzaSAP(codiceFiscaleUtenteCollegato, codiceFiscaleSAP);
            if (esitoVerificaEsistenza.isEsitoPositivo()) {
                sap = getSAP(null, codiceFiscaleSAP, esitoVerificaEsistenza.getIdentificativoSap());
            }
        }

        return sap;

    }

    /**
     * Gets the sap.
     *
     * @param idAnagraficaSilp  the id anagrafica silp
     * @param codiceFiscaleSAP  the codice fiscale SAP
     * @param identificativoSap the identificativo sap
     * @return the sap
     * @throws Exception the exception
     */
    private SchedaAnagraficoProfessionale getSAP(Long idAnagraficaSilp, String codiceFiscaleSAP, String identificativoSap) throws Exception {
        if (SilCommonUtils.isVoid(idAnagraficaSilp) && SilCommonUtils.isVoid(codiceFiscaleSAP) && SilCommonUtils.isVoid(identificativoSap)) {
            throw new IllegalArgumentException("Impossibile richiedere una sap senza i parametri input valorizzati idAnagraficaSilp o cf o idSap");
        }
        Long numeroMassimoEsperienzeLavoroSap = SilpConvertUtils.toLong(parametroUtils.getParametro(ParametroUtils.NUMERO_MASSIMO_ESPERIENZE_LAVORO_SAP));
        return AdapterSilpsvspWSImpl.getInstance().getSAP(idAnagraficaSilp, codiceFiscaleSAP, identificativoSap, numeroMassimoEsperienzeLavoroSap);
    }

    /**
     * Save utente.
     *
     * @param utente          the utente
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response saveUtente(Utente utente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::saveUtente] utente=" + utente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            String codiceFiscale = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            if (utente.getIdUtente() != null && !codiceFiscale.equals(utente.getCodiceFiscale())) {
                restHelper.checkUtente(httpRequest, TracciamentoUtils.SAVE_UTENTE, utente.getIdUtente());
            }

            /* Nel caso arrivi un utente completamente vuoto non gli facciamo fare nulla */
            if (null == utente.getIdUtente() && null == utente.getCodiceFiscale() && null == utente.getIdSilLavAnagrafica()) {
                log.debug("[UtentiApiServiceImpl::saveUtente] utente completamente vuoto utente=" + utente);
            } else {
                persistUtente(utente);
                log.debug("[UtentiApiServiceImpl::saveUtente] utente=" + utente);
            }
            return Response.ok(utente).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::saveUtente] codiceFiscale=" + utente.getCodiceFiscale(), ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_UTENTE, httpRequest, utente.getIdUtente(), ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "saveUtente()", "invocazione API UtentiApiServiceImpl.saveUtente", "");
            watcher.stop();
        }
    }

    /**
     * Persist utente.
     *
     * @param utente the utente
     * @throws DAOException the DAO exception
     */
    private void persistUtente(Utente utente) throws DAOException {
        UtenteDTO utenteDTO = null;
        Date now = new Date();
        Set<String> ambitiPresaVisione = new HashSet<>();
        if (utente.getIdSilLavAnagrafica() != null) {
            UtenteFilter utenteFilter = new UtenteFilter();
            utenteFilter.getIdSilLavAngrafica().eq(utente.getIdSilLavAnagrafica());
            utenteDTO = dao.findFirst(UtenteDBDef.class, utenteFilter);
        }
        // Try once again by idUtente
        if (utenteDTO == null && utente.getIdUtente() != null) {
            UtenteFilter utenteFilter = new UtenteFilter();
            utenteFilter.getIdUtente().eq(utente.getIdUtente());
            utenteDTO = dao.findFirst(UtenteDBDef.class, utenteFilter);
        }
        if (utenteDTO == null) {
            utenteDTO = new UtenteDTO();
            utenteDTO.setCodUserInserim(utente.getCodiceFiscale());
            utenteDTO.setDInserim(now);
        }
        if (StringUtils.isBlank(utente.getCodiceTipoUtente())) {
            utente.setCodiceTipoUtente(StringUtils.isBlank(utenteDTO.getCodTipoUtente()) ? "CIT" : utenteDTO.getCodTipoUtente());
        }
        if (utente.getCodiceFiscale() != null) {
            utenteDTO.setCfUtente(utente.getCodiceFiscale().toUpperCase());
        }
        utenteDTO.setCodTipoUtente(utente.getCodiceTipoUtente());
        utenteDTO.setCodUserAggiorn(utente.getCodiceFiscale());
        utenteDTO.setCognome(utente.getCognome());
        utenteDTO.setNome(utente.getNome());
        utenteDTO.setEmail(utente.getEmail());
        if (utente.getIdentificativoSap() != null) {
            utenteDTO.setIdentificativoSap(utente.getIdentificativoSap());
        }
        if (utente.getIdSilLavAnagrafica() != null) {
            utenteDTO.setIdSilLavAngrafica(utente.getIdSilLavAnagrafica());
        }

        if (utenteDTO.getIdUtente() != null) {
            for (UtentePresaVisione presaVisione : getPreseVisioneUtente(utenteDTO.getIdUtente())) {
                ambitiPresaVisione.add(presaVisione.getCodAmbito());
            }
        }

        if (utenteDTO.getIdUtente() == null) {
            utenteDTO = dao.insert(UtenteDBDef.class, utenteDTO);
        } else {
            utenteDTO = dao.update(UtenteDBDef.class, utenteDTO);
        }
        for (UtentePresaVisione presaVisione : utente.getPreseVisione()) {
            if (!ambitiPresaVisione.contains(presaVisione.getCodAmbito())) {
                UtentePrivacyDTO utentePrivacyDTO = new UtentePrivacyDTO();
                utentePrivacyDTO.setCodAmbito(presaVisione.getCodAmbito());
                utentePrivacyDTO.setCodUserAggiorn(utente.getCodiceFiscale());
                utentePrivacyDTO.setCodUserInserim(utente.getCodiceFiscale());
                utentePrivacyDTO.setDataPresaVisione(new Date());
                utentePrivacyDTO.setIdUtente(utenteDTO.getIdUtente());
                dao.insert(UtentePrivacyDBDef.class, utentePrivacyDTO);
            }
        }
        utente.setIdUtente(utenteDTO.getIdUtente());
    }

    /**
     * Find utenti A carico.
     *
     * @param idUtente        the id utente
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response findUtentiACarico(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::findUtentiACarico] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.UTENTI_A_CARICO, idUtente);
            RelUtenteFilter filter = new RelUtenteFilter();
            filter.getIdResponsabile().eq(idUtente);
            QueryResult<RelUtenteDTO> rels = dao.findAll(RelUtenteDBDef.class, filter, 0);
            List<UtenteACaricoComparable> result = new ArrayList<>();
            UtenteMapper utenteMapper = new UtenteMapper();
            for (RelUtenteDTO rel : rels) {
                if (rel.getTutelato() != null) {
                    UtenteACaricoComparable utenteACarico = new UtenteACaricoComparable();
                    TipoResponsabilita tipoResponsabilita = new TipoResponsabilita();
                    tipoResponsabilita.setCodice(rel.getTipoResponsabilita().getCodice());
                    tipoResponsabilita.setDescrizione(rel.getTipoResponsabilita().getDescrizione());
                    utenteACarico.setTipoResponsabilita(tipoResponsabilita);
                    utenteACarico.setTutelato(utenteMapper.mapReverse(rel.getTutelato()));
                    utenteACarico.setPreseVisione(getPreseVisioneUtenteACarico(idUtente, utenteACarico.getTutelato().getIdUtente()));
                    result.add(utenteACarico);
                }
            }
            // ordino utenti a carico per cognome nome
            Collections.sort(result);
            log.debug("[UtentiApiServiceImpl::findUtentiACarico] result=" + result);
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::findUtentiACarico] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.UTENTI_A_CARICO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "findUtentiACarico()", "invocazione API UtentiApiServiceImpl.findUtentiACarico", "");
            watcher.stop();
        }
    }

    /**
     * Gets the prese visione utente A carico.
     *
     * @param idUtente   the id utente
     * @param idTutelato the id tutelato
     * @return the prese visione utente A carico
     * @throws DAOException the DAO exception
     */
    private List<UtentePresaVisione> getPreseVisioneUtenteACarico(Long idUtente, Long idTutelato) throws DAOException {
        List<UtentePresaVisione> preseVisione = new ArrayList<>();
        RelUtentePrivacyFilter relUtentePrivacyFilter = new RelUtentePrivacyFilter();
        relUtentePrivacyFilter.getIdResponsabile().eq(idUtente);
        relUtentePrivacyFilter.getIdTutelato().eq(idTutelato);
        for (RelUtentePrivacyDTO relUtentePrivacyDTO : dao.findAll(RelUtentePrivacyDBDef.class, relUtentePrivacyFilter, 0)) {
            UtentePresaVisione presaVisione = new UtentePresaVisione();
            presaVisione.setCodAmbito(relUtentePrivacyDTO.getCodAmbito());
            preseVisione.add(presaVisione);
        }
        return preseVisione;
    }

    /**
     * Gets the SAP vuota.
     *
     * @param idUtente        the id utente
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the SAP vuota
     */
    @Override
    public Response getSAPVuota(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::getSAPVuota] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.DATI_RESPONSABILE, idUtente);
            UtenteDTO utenteDTO = getUtenteById(idUtente);
            if (utenteDTO == null) {
                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage(UTENTE_NON_PRESENTE + idUtente);
                log.debug("[UtentiApiServiceImpl::getSAPVuota] esito=404-" + err.getErrorMessage());
                return Response.serverError().entity(err).status(404).build();
            }
            ResponsabileDTO responsabileDTO = new ResponsabileDTO();
            responsabileDTO.setIdResponsabile(idUtente);
            ResponsabileMapper mapper = new ResponsabileMapper();
            SchedaAnagraficoProfessionale sap = mapper.migraToSAP(responsabileDTO, utenteDTO);
            return Response.ok(sap).build();
        } catch (Exception ex) {
            log.error(UTENTI_API_SERVICE_IMPL_GET_DATI_RESPONSABILE_ID_UTENTE + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.DATI_RESPONSABILE, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "getDatiResponsabile()", "invocazione API UtentiApiServiceImpl.getDatiResponsabile", "");
            watcher.stop();
        }
    }

    /**
     * Gets the utente by id.
     *
     * @param idUtente the id utente
     * @return the utente by id
     * @throws DAOException the DAO exception
     */
    private UtenteDTO getUtenteById(Long idUtente) throws DAOException {
        UtenteFilter utenteFilter = new UtenteFilter();
        utenteFilter.getIdUtente().eq(idUtente);
        return dao.findFirst(UtenteDBDef.class, utenteFilter);
    }

    /**
     * Gets the dati responsabile.
     *
     * @param idUtente        the id utente
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the dati responsabile
     */
    @Override
    public Response getDatiResponsabile(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info(UTENTI_API_SERVICE_IMPL_GET_DATI_RESPONSABILE_ID_UTENTE + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.DATI_RESPONSABILE, idUtente);
            UtenteDTO utenteDTO = getUtenteById(idUtente);
            if (utenteDTO == null) {
                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage(UTENTE_NON_PRESENTE + idUtente);
                log.debug("[UtentiApiServiceImpl::getDatiResponsabile] esito=404-" + err.getErrorMessage());
                return Response.serverError().entity(err).status(404).build();
            }
            /*
             * 1) Se ho l'id SAP, cerco direttamente su SILP. 2) In caso contrario, verifico
             * il dato su PSLP. Se ho il responsabile, leggo tale dato. 3) In caso
             * contrario, verifico nuovamente su SILP (solo se non ho l'identificativo SAP,
             * altrimenti sarebbe gia' coperto dal punto 1, e se ho l'id SILP) 4) In caso
             * contrario, inizializzo il responsabile su PSLP
             */

            // Cerca la SAP su SILP
            if (StringUtils.isNotBlank(utenteDTO.getIdentificativoSap())) {
                SchedaAnagraficoProfessionale sap = getSapConVerificaEsistenza(getIdSilLavAnagrafica(utenteDTO), null,
                        restHelper.getCodiceFiscaleCurrentUser(httpRequest));
                if (sap != null) {
                    return Response.ok(sap).build();
                }
            }

            // Cerco il responsabile su PSLP
            ResponsabileFilter responsabileFilter = new ResponsabileFilter();
            responsabileFilter.getIdResponsabile().eq(idUtente);
            ResponsabileDTO responsabileDTO = dao.findFirst(ResponsabileDBDef.class, responsabileFilter);
            ResponsabileMapper mapper = new ResponsabileMapper();
            mapper.setComuni(AdapterSilpsvdeWSImpl.getInstance().findComuni());
            mapper.setProvince(AdapterSilpsvdeWSImpl.getInstance().findProvince());
            mapper.setNazioni(AdapterSilpsvdeWSImpl.getInstance().findNazioni());
            mapper.setCittadinanze(AdapterSilpsvdeWSImpl.getInstance().findCittadinanze());
            mapper.setMotiviRilascioPermessoSoggiorno(AdapterSilpsvdeWSImpl.getInstance().findMotiviRilascioPermessoSoggiorno());
            mapper.setStatusExtraUE(AdapterSilpsvdeWSImpl.getInstance().ricercaStatusExtraUE());

            if (responsabileDTO != null) {
                // Mappatura verso SAP
                SchedaAnagraficoProfessionale sap = mapper.migraToSAP(responsabileDTO, utenteDTO);
                return Response.ok(sap).build();
            }

            if (StringUtils.isBlank(utenteDTO.getIdentificativoSap()) && getIdSilLavAnagrafica(utenteDTO) != null) {
                SchedaAnagraficoProfessionale sap = getSapConVerificaEsistenza(getIdSilLavAnagrafica(utenteDTO), utenteDTO.getCfUtente(),
                        restHelper.getCodiceFiscaleCurrentUser(httpRequest));

                if (sap != null) {
                    utenteDTO.setIdentificativoSap(sap.getIdentificativoSap());
                    dao.update(UtenteDBDef.class, utenteDTO);
                    return Response.ok(sap).build();
                }
            }
            // Creazione nuovo dato
            Date now = new Date();
            responsabileDTO = new ResponsabileDTO();
            responsabileDTO.setIdResponsabile(idUtente);
            responsabileDTO.setDInserim(now);
            responsabileDTO.setDAggiorn(now);
            responsabileDTO.setCodUserInserim(utenteDTO.getCfUtente());
            responsabileDTO.setCodUserAggiorn(utenteDTO.getCfUtente());
            responsabileDTO = dao.insert(ResponsabileDBDef.class, responsabileDTO);
            // Mappatura verso SAP
            SchedaAnagraficoProfessionale sap = mapper.migraToSAP(responsabileDTO, utenteDTO);
            return Response.ok(sap).build();
        } catch (Exception ex) {
            log.error(UTENTI_API_SERVICE_IMPL_GET_DATI_RESPONSABILE_ID_UTENTE + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.DATI_RESPONSABILE, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "getDatiResponsabile()", "invocazione API UtentiApiServiceImpl.getDatiResponsabile", "");
            watcher.stop();
        }
    }

    /**
     * Gets the adesione YG.
     *
     * @param idUtente        the id utente
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the adesione YG
     */
    @Override
    public Response getAdesioneYG(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::getAdesioneYG] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.ADESIONE, idUtente);
            return retrieveAdesioneFromSilp("getAdesioneYG", idUtente, getIdSilLavAnagrafica(idUtente), httpRequest);
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::getAdesioneYG] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.ADESIONE, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    AmbitoDTO.COD_AMBITO_GG_GARANZIA_GIOVANI);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "getAdesioneYG()", "invocazione API UtentiApiServiceImpl.getAdesioneYG", "");
            watcher.stop();
        }
    }

    /**
     * Send stato adesione.
     *
     * @param idUtente                   the id utente
     * @param parametriSendStatoAdesione the parametri send stato adesione
     * @param securityContext            the security context
     * @param httpHeaders                the http headers
     * @param httpRequest                the http request
     * @return the response
     */
    @Override
    public Response sendStatoAdesione(Long idUtente, DatiInputStatoAdesione parametriSendStatoAdesione, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::sendStatoAdesione] idUtente=" + idUtente + " parametri=" + parametriSendStatoAdesione);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        boolean isIscrizione = true;
        Long eventoSend = TracciamentoUtils.ISCRIZIONE_GG;
        if ("D".equalsIgnoreCase(parametriSendStatoAdesione.getCodiceStatoAdesione())) {
            isIscrizione = false;
            eventoSend = TracciamentoUtils.ANNULLAMENTO_GG;
        }
        try {
            PrenotazioneIncontro incontroDaCancellare = parametriSendStatoAdesione.getAppuntamentoDaCancellare();

            restHelper.checkUtente(httpRequest, eventoSend, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            UtenteDTO utenteDTO = getUtenteById(idUtente);
            // Non sono sicuro arrivi dal client, eventualmente li recupero dall'utente
            if (parametriSendStatoAdesione.getIdAnagrafica() == null) {
                parametriSendStatoAdesione.setIdAnagrafica(utenteDTO.getIdSilLavAngrafica());
            }
            if (parametriSendStatoAdesione.getCodiceFiscale() == null) {
                parametriSendStatoAdesione.setCodiceFiscale(utenteDTO.getCfUtente());
            }
            if (parametriSendStatoAdesione.getCodiceFiscaleOperatore() == null) {
                parametriSendStatoAdesione.setCodiceFiscaleOperatore(codiceFiscaleUtente);
            } else {
                codiceFiscaleUtente = parametriSendStatoAdesione.getCodiceFiscaleOperatore();
            }

            // per evitare che la data sia diversa da quella del server sovrascriviamo
            // data stato adesione per annullamento adesione
            // data adesione per nuova adesione
            if ("D".equalsIgnoreCase(parametriSendStatoAdesione.getCodiceStatoAdesione())) {
                parametriSendStatoAdesione.setDataStatoAdesione(new Date());
            } else {
                parametriSendStatoAdesione.setDataAdesione(new Date());
            }
            EsitoSendStatoAdesione esito = AdapterOrchsilWSImpl.getInstance().sendStatoAdesione(codiceFiscaleUtente, parametriSendStatoAdesione);
            if ("ERR".equals(esito.getCode())) {
                String msg = esito.getMessaggioCittadino();
                String msg1 = messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME002).getTesto();
                esito.setMessaggioCittadino(msg1);
                esito.setCode("ERR");
                if (esito.getWarning() != null) {
                    List<MappaErrori> warning = esito.getWarning();
                    MappaErrori map = new MappaErrori();
                    map.setCodiceMessaggio("ERR");
                    map.setDescrMessaggio(msg);
                    warning.add(map);
                }
                tracciamentoUtils.tracciaKo(eventoSend, httpRequest, idUtente, msg, AmbitoDTO.COD_AMBITO_GG_GARANZIA_GIOVANI);

            } else if (esito.getWarning() != null) {
                boolean flgA009 = false;
                boolean flgA008 = false;
                boolean flgA012 = false;
                boolean flgA022 = false;
                boolean flgME148 = false;
                boolean flgME003 = false;
                boolean flgImprevisto = false;
                String concatenaME003 = "<br>";
                String concatenaME148 = "<br>";
                String concatenaImprevisto = "<br>";
                for (MappaErrori map : esito.getWarning()) {
                    if (null != map) {
                        if (isIscrizione) {
                            if (null != map.getCodiceMessaggio()) {
                                if ("AYG-009".equals(map.getCodiceMessaggio())) {
                                    flgA009 = true;
                                } else if ("AYG-008".equals(map.getCodiceMessaggio())) {
                                    flgA008 = true;
                                } else if ("AYG-012".equals(map.getCodiceMessaggio()) || "AYG-015".equals(map.getCodiceMessaggio())) {
                                    flgA012 = true;
                                } else if ("GGP-B11".equals(map.getCodiceMessaggio()) || "IS_GG".equals(map.getCodiceMessaggio())) {
                                    flgME003 = true;
                                    concatenaME003 = concatenaME003 + "<br>" + map.getCodiceMessaggio() + " " + map.getDescrMessaggio();
                                } else if ("NO_DOM_PIEMONTE".equals(map.getCodiceMessaggio()) || "GGP-B34".equals(map.getCodiceMessaggio())
                                        || "GGP-B35".equals(map.getCodiceMessaggio())) {
                                    flgME148 = true;
                                    concatenaME148 = concatenaME148 + "<br>" + map.getDescrMessaggio();
                                } else if ("NO_NEET".equals(map.getCodiceMessaggio()) || "INFO".equals(map.getCodiceMessaggio())) {
                                    // ignorare messaggio NO_NEET
                                } else {
                                    flgImprevisto = true;
                                    concatenaImprevisto = concatenaImprevisto + "<br>" + map.getCodiceMessaggio() + " " + map.getDescrMessaggio();
                                }
                            }
                        } else {
                            // Annulment Adhesion
                            if (null != map.getCodiceMessaggio()) {
                                if ("AYG-009".equals(map.getCodiceMessaggio())) {
                                    flgA009 = true;
                                } else if ("AYG-008".equals(map.getCodiceMessaggio())) {
                                    flgA008 = true;
                                } else if ("AYG-022".equals(map.getCodiceMessaggio()) || "AYG-015".equals(map.getCodiceMessaggio())) {
                                    flgA022 = true;
                                } else if ("GGP-B11".equals(map.getCodiceMessaggio()) || "IS_GG".equals(map.getCodiceMessaggio())) {
                                    flgME003 = true;
                                    concatenaME003 = concatenaME003 + "<br>" + map.getCodiceMessaggio() + " " + map.getDescrMessaggio();
                                } else if ("NO_NEET".equals(map.getCodiceMessaggio()) || "INFO".equals(map.getCodiceMessaggio())) {
                                    // ignorare messaggio NO_NEET
                                } else {
                                    flgImprevisto = true;
                                    concatenaImprevisto = concatenaImprevisto + "<br>" + map.getCodiceMessaggio() + " " + map.getDescrMessaggio();
                                }
                            }
                        }
                    }
                }
                if (isIscrizione) {
                    String msg = messaggiUtils.loadMessaggioErrore(MessaggiUtils.MI041).getTesto();
                    if (flgA012) {
                        // esito positivo
                        String msg1 = messaggiUtils.loadMessaggioErrore(MessaggiUtils.MI046).getTesto();
                        msg = msg.replace("{0}", "non ");
                        msg = msg.replace("{1}", msg1);
                        esito.setMessaggioCittadino(msg);
                        esito.setCode("OK");
                    } else if (flgA008 && flgA009) {
                        // esito positivo
                        msg = msg.replace("{0}", "");
                        msg = msg.replace("{1}", "");
                        esito.setMessaggioCittadino(msg);
                        esito.setCode("OK");
                    } else if (flgME003) {
                        // esito negativo
                        String msg1 = messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME154).getTesto();
                        esito.setMessaggioCittadino(msg1);
                        esito.setCode("KO");
                        tracciamentoUtils.tracciaKo(eventoSend, httpRequest, idUtente, concatenaME003, null);
                    } else if (flgME148) {
                        String msg1 = messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME148).getTesto();
                        esito.setMessaggioCittadino(msg1 + concatenaME148);
                        esito.setCode("KO");
                        tracciamentoUtils.tracciaKo(eventoSend, httpRequest, idUtente, msg1 + concatenaME148, null);
                    } else if (flgImprevisto) {
                        String msg1 = messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME002).getTesto();
                        esito.setMessaggioCittadino(msg1);
                        esito.setCode("ERR");
                        tracciamentoUtils.tracciaKo(eventoSend, httpRequest, idUtente, concatenaImprevisto, null);
                    } else {
                        String msg1 = messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME002).getTesto();
                        esito.setMessaggioCittadino(msg1);
                        esito.setCode("ERR");
                        tracciamentoUtils.tracciaKo(eventoSend, httpRequest, idUtente, esito.getMessaggioCittadino(), null);
                    }
                } else { // annullamento adesione
                    String msg = messaggiUtils.loadMessaggioErrore(MessaggiUtils.MI043).getTesto();

                    if (flgA022 || (flgA022 && flgA008)) {
                        // esito positivo
                        String msg1 = messaggiUtils.loadMessaggioErrore(MessaggiUtils.MI046).getTesto();
                        msg = msg.replace("{0}", "non ");
                        msg = msg.replace("{1}", msg1);
                        esito.setMessaggioCittadino(msg);
                        esito.setCode("OK");
                    } else if (flgA008 && flgA009) {
                        // esito positivo
                        msg = msg.replace("{0}", "");
                        msg = msg.replace("{1}", "");
                        esito.setMessaggioCittadino(msg);
                        esito.setCode("OK");
                    } else if (flgME003) {
                        // esito negativo
                        String msg1 = messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME003).getTesto();
                        esito.setMessaggioCittadino(msg1);
                        esito.setCode("KO");
                        tracciamentoUtils.tracciaKo(eventoSend, httpRequest, idUtente, concatenaME003, null);
                    } else if (flgImprevisto) {
                        String msg1 = messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME002).getTesto();
                        esito.setMessaggioCittadino(msg1);
                        esito.setCode("ERR");
                        tracciamentoUtils.tracciaKo(eventoSend, httpRequest, idUtente, concatenaImprevisto, null);
                    } else {
                        String msg1 = messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME002).getTesto();
                        esito.setMessaggioCittadino(msg1);
                        esito.setCode("ERR");
                        tracciamentoUtils.tracciaKo(eventoSend, httpRequest, idUtente, esito.getMessaggioCittadino(), null);
                    }
                }

            }
            if ("OK".equals(esito.getCode())) {

                tracciamentoUtils.tracciaOk(eventoSend, httpRequest, idUtente, "ESITO OK =" + esito.toString(), null);
                // se esito ok
                // e si tratta di annullamento Adesione con incontro da cancellare
                // tento di cancello l'incontro
                boolean cancellaIncontro = true;
                if (cancellaIncontro && !isIscrizione && incontroDaCancellare != null) {
                    PslOrchApiClient client = new PslOrchApiClient();
                    ParametriSalvataggioIncontroDTO parametriSalvataggioIncontroDTO = new ParametriSalvataggioIncontroDTO();
                    parametriSalvataggioIncontroDTO.setIdPrenotazione(incontroDaCancellare.getIdPrenotazione());
                    parametriSalvataggioIncontroDTO.setIdPrenotazioneOld(null);
                    parametriSalvataggioIncontroDTO.setIdSlot(incontroDaCancellare.getSlot().getIdSlot());
                    parametriSalvataggioIncontroDTO.setIdStatoAppuntamento("DC");
                    parametriSalvataggioIncontroDTO.setIdUtente(idUtente);
                    parametriSalvataggioIncontroDTO.setNote("Cancellazione incontro per annullamento Adesione");
                    parametriSalvataggioIncontroDTO.setCodiceFiscaleUtenteCollegato(codiceFiscaleUtente);
                    EsitoSalvataggioIncontroDTO esitoDTO = client.saveIncontro(parametriSalvataggioIncontroDTO);

                    esito.setIncontroCancellato(esitoDTO.isEsitoPositivo());
                    if (esitoDTO.isEsitoPositivo()) {
                        tracciamentoUtils.tracciaOk(eventoSend, httpRequest, idUtente,
                                "ESITO OK riuscita cancellazione incontro id " + incontroDaCancellare.getIdPrenotazione(), null);
                    } else {
                        tracciamentoUtils.tracciaKo(eventoSend, httpRequest, idUtente, "ESITO KO non riuscita cancellazione incontro id "
                                + incontroDaCancellare.getIdPrenotazione() + " esito =" + esitoDTO.toString(), null);
                    }
                }

            } else if (esito.getCode() == null) {
                tracciamentoUtils.tracciaOk(eventoSend, httpRequest, idUtente, "ESITO KO =" + esito.toString(), null);
            }
            return Response.ok(esito).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::sendStatoAdesione] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(eventoSend, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "sendStatoAdesione()", "invocazione API UtentiApiServiceImpl.sendStatoAdesione", "");
            watcher.stop();
        }
    }

    /**
     * Gets the profiling YG.
     *
     * @param idUtente        the id utente
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the profiling YG
     */
    @Override
    public Response getProfilingYG(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::getProfilingYG] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.PROFILING, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            ProfilingYG profiling = AdapterSilpsvspWSImpl.getInstance().getProfilingYG(getIdSilLavAnagrafica(idUtente), codiceFiscaleUtente);
            if (profiling != null) {
                log.debug("[UtentiApiServiceImpl::getProfilingYG] profiling=" + profiling);
                return Response.ok(profiling).build();
            } else {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.PROFILING, httpRequest, idUtente, "Profiling non presente", null);
                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage("Profiling non reperito per l'utente " + idUtente);
                log.debug("[UtentiApiServiceImpl::getProfilingYG] esito=404-" + err.getErrorMessage());
                return Response.serverError().entity(err).status(404).build();
            }
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::getProfilingYG] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.PROFILING, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "getProfilingYG()", "invocazione API UtentiApiServiceImpl.getProfilingYG", "");
            watcher.stop();
        }
    }

    /**
     * Find incontri.
     *
     * @param idUtente        the id utente
     * @param codAmbito       the cod ambito
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response findIncontri(Long idUtente, String codAmbito, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::findIncontri] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.INCONTRI, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            List<PrenotazioneIncontro> prenotazioni = leggiPrenotazioni(idUtente, codAmbito, codiceFiscaleUtente);
            log.debug("[UtentiApiServiceImpl::findIncontri] prenotazioni=" + prenotazioni);
            return Response.ok(prenotazioni).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::findIncontri] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.INCONTRI, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), codAmbito);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "findIncontri()", "invocazione API UtentiApiServiceImpl.findIncontri", "");
            watcher.stop();
        }
    }

    /**
     * Leggi prenotazioni.
     *
     * @param idUtente            the id utente
     * @param codAmbito           the cod ambito
     * @param codiceFiscaleUtente the codice fiscale utente
     * @return the list
     * @throws Exception the exception
     */
    private List<PrenotazioneIncontro> leggiPrenotazioni(Long idUtente, String codAmbito, String codiceFiscaleUtente) throws Exception {
        PslOrchApiClient client = new PslOrchApiClient();
        PrenotazioniDTO prenotazioniDTO = client.findPrenotazioniIncontri(idUtente, codAmbito);
        List<PrenotazioneIncontro> prenotazioni = new ArrayList<>(prenotazioniDTO.getEls().size());
        if (prenotazioniDTO.getEls() != null && !prenotazioniDTO.getEls().isEmpty()) {
            Map<String, Sportello> sportelli = AdapterSilpsvinWSImpl.getInstance().findSportelliMap(codiceFiscaleUtente);

            for (PrenotazioneDTO prenotazioneDTO : prenotazioniDTO.getEls()) {
                SlotDTO slotDTO = prenotazioneDTO.getSlot();
                PrenotazioneIncontro prenotazione = new PrenotazioneIncontro();
                SlotIncontro slot = new SlotIncontro();
                slot.setIdSlot(slotDTO.getIdSlot());
                slot.setGiorno(SilTimeUtils.convertDataInStringa(slotDTO.getGiorno().getGiorno()));
                slot.setDaOra(slotDTO.getDescrizioneOraInizio());
                slot.setAOra(slotDTO.getDescrizioneOraFine());
                prenotazione.setSlot(slot);
                prenotazione.setIdPrenotazione(prenotazioneDTO.getIdPrenotazione());
                prenotazione.setCodAmbito(slotDTO.getGiorno().getPeriodo().getCalendario().getAmbito().getCodAmbito());
                prenotazione.setCodiceAnpalStatoIncontro(prenotazioneDTO.getIdStatoAppuntamento());
                CalendarioDTO calendarioDTO = prenotazioneDTO.getSlot().getGiorno().getPeriodo().getCalendario();
                String gruppoOperatore = calendarioDTO.getGruppoOperatore();
                Long codOperatore = calendarioDTO.getCodOperatore();
                Long subcodice = calendarioDTO.getSubcodice();
                prenotazione.setSportello(sportelli.get(gruppoOperatore + "-" + codOperatore + "-" + subcodice));
                prenotazioni.add(prenotazione);
            }
        }
        return prenotazioni;
    }

    /**
     * Find informazioni aggiuntive.
     *
     * @param idUtente        the id utente
     * @param codAmbito       the cod ambito
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response findInformazioniAggiuntive(Long idUtente, String codAmbito, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::findInformazioniAggiuntive] idUtente=" + idUtente + COD_AMBITO2 + codAmbito);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.INFORMAZIONI_AGGIUNTIVE, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            List<InformazioneAggiuntiva> informazioniAggiuntive = AdapterSilpserver.getInstance().findInformazioniAggiuntiveLavoratore(codiceFiscaleUtente,
                    getIdSilLavAnagrafica(idUtente), codAmbito);
            log.debug("[UtentiApiServiceImpl::findInformazioniAggiuntive] informazioniAggiuntive=" + informazioniAggiuntive);
            return Response.ok(informazioniAggiuntive).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::findInformazioniAggiuntive]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.INFORMAZIONI_AGGIUNTIVE, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    codAmbito);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "findInformazioniAggiuntive()",
                    "invocazione API UtentiApiServiceImpl.findInformazioniAggiuntive", "");
            watcher.stop();
        }
    }

    /**
     * Save informazioni aggiuntive.
     *
     * @param idUtente               the id utente
     * @param informazioneAggiuntiva the informazione aggiuntiva
     * @param securityContext        the security context
     * @param httpHeaders            the http headers
     * @param httpRequest            the http request
     * @return the response
     */
    @Override
    public Response saveInformazioniAggiuntive(Long idUtente, InformazioneAggiuntiva informazioneAggiuntiva, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::saveInformazioniAggiuntive] idUtente=" + idUtente + " params=" + informazioneAggiuntiva);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.SAVE_INFORMAZIONI_AGGIUNTIVE, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            InformazioneAggiuntiva result = AdapterSilpserver.getInstance().saveInformazioniAggiuntiveLavoratore(codiceFiscaleUtente,
                    getIdSilLavAnagrafica(idUtente), informazioneAggiuntiva);
            log.debug("[UtentiApiServiceImpl::saveInformazioniAggiuntive] result=" + result);
            tracciamentoUtils.tracciaOk(TracciamentoUtils.SAVE_INFORMAZIONI_AGGIUNTIVE, httpRequest, idUtente,
                    "Modificata InformazioneAggiuntiva(codice=" + informazioneAggiuntiva.getCodiceConfigurazione() + ",valore="
                            + informazioneAggiuntiva.getValore() + ",codConf=" + informazioneAggiuntiva.getCodiceConfigurazione() + ",descrConf="
                            + informazioneAggiuntiva.getDescrizioneConfigurazione() + ",note=" + informazioneAggiuntiva.getNote() + ")",
                    null);
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::saveInformazioniAggiuntive] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_INFORMAZIONI_AGGIUNTIVE, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "saveInformazioniAggiuntive()",
                    "invocazione API UtentiApiServiceImpl.saveInformazioniAggiuntive", "");
            watcher.stop();
        }
    }

    /**
     * Per casi di test vedere su sil_profiling legato con id operaizone alla
     * sil_operazione profiling di tipo C.
     *
     * @param idUtente        the id utente
     * @param params          the params
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response calcolaProfilingYG(Long idUtente, ParametriCalcoloProfilingYG params, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::calcolaProfilingYG] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.CALCOLO_PROFILING, idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            ProfilingYG profiling = AdapterSilpserver.getInstance().calcolaProfiling(codiceFiscaleUtente, getIdSilLavAnagrafica(idUtente), params);
            if (profiling != null) {
                tracciamentoUtils.tracciaOk(TracciamentoUtils.CALCOLO_PROFILING, httpRequest, idUtente,
                        "Calcolato Profiling: params=Parametri(prov=" + params.getCodiceMinisterialeProvincia() + TITOLO_STUDIO
                                + params.getCodiceSilpTitoloStudio() + COND_OCCUP + params.getCodiceSilpCondizioneOccupazionale() + PRES_IN_ITALIA2
                                + params.getCodiceSilpPresenzaInItalia() + "), esito=" + profiling.getIndice(),
                        null);
                log.debug("[UtentiApiServiceImpl::calcolaProfilingYG] profiling=" + profiling);
                return Response.ok(profiling).build();
            } else {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.CALCOLO_PROFILING, httpRequest, idUtente,
                        "Calcolo profiling non presente: params=Parametri(prov=" + params.getCodiceMinisterialeProvincia() + TITOLO_STUDIO
                                + params.getCodiceSilpTitoloStudio() + COND_OCCUP + params.getCodiceSilpCondizioneOccupazionale() + PRES_IN_ITALIA2
                                + params.getCodiceSilpPresenzaInItalia() + ")",
                        null);
                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage("Profiling non calcolato per l'utente " + idUtente);
                log.debug("[UtentiApiServiceImpl::calcolaProfilingYG] esito=404-" + err.getErrorMessage());
                return Response.serverError().entity(err).status(404).build();
            }
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::calcolaProfilingYG] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.CALCOLO_PROFILING, httpRequest, idUtente,
                    ex.getMessage() + " Errore di sistema: " + ex.getClass().getName() + "; params=Parametri(prov=" + params.getCodiceMinisterialeProvincia()
                            + TITOLO_STUDIO + params.getCodiceSilpTitoloStudio() + COND_OCCUP + params.getCodiceSilpCondizioneOccupazionale() + PRES_IN_ITALIA2
                            + params.getCodiceSilpPresenzaInItalia() + ")",
                    null);
            return createErrorResponse("500", MessaggiUtils.ME152);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "calcolaProfilingYG()", "invocazione API UtentiApiServiceImpl.calcolaProfilingYG", "");
            watcher.stop();
        }
    }

    /**
     * Save responsabile.
     *
     * @param idUtente        the id utente
     * @param sap             the sap
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response saveResponsabile(Long idUtente, SchedaAnagraficoProfessionale sap, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::saveResponsabile] idUtente=" + idUtente + " params=" + sap);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.SAVE_DATI_RESPONSABILE, idUtente);
            if (StringUtils.isNotBlank(sap.getIdentificativoSap())) {
                ParametriSalvataggioSAP parametriSalvataggioSAP = new ParametriSalvataggioSAP();
                parametriSalvataggioSAP.setSap(sap);
                List<String> sezioni = new ArrayList<>();
                sezioni.add(SapMapper.DATI_ANAGRAFICI);
                sezioni.add(SapMapper.DATI_AMMINISTRATIVI);
                parametriSalvataggioSAP.setSezioni(sezioni);
                return this.saveSAP(idUtente, parametriSalvataggioSAP, securityContext, httpHeaders, httpRequest);
            }
            // SAP non di SILP: salvataggio dati come responsabile
            UtenteFilter utenteFilter = new UtenteFilter();
            utenteFilter.getIdUtente().eq(idUtente);
            UtenteDTO utenteDTO = dao.findFirst(UtenteDBDef.class, utenteFilter);
            if (utenteDTO == null) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_DATI_RESPONSABILE, httpRequest, idUtente, "Utente non trovato", null);
                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage("Utente " + idUtente + " non trovato");
                log.debug("[UtentiApiServiceImpl::saveResponsabile] esito=404-" + err.getErrorMessage());
                return Response.serverError().entity(err).status(404).build();
            }
            ResponsabileFilter filter = new ResponsabileFilter();
            filter.getIdResponsabile().eq(idUtente);
            ResponsabileDTO responsabileDTOOriginal = dao.findFirst(ResponsabileDBDef.class, filter);
            ResponsabileMapper mapper = new ResponsabileMapper();
            Responsabile responsabile = mapper.migraDaSAP(sap);
            // Forzo l'id utente
            responsabile.setIdResponsabile(idUtente);
            ResponsabileDTO responsabileDTO = mapper.map(responsabile);
            responsabileDTO.setCodUserAggiorn(utenteDTO.getCfUtente());
            responsabileDTO.setDAggiorn(new Date());
            if (responsabileDTOOriginal == null) {
                responsabileDTO.setCodUserInserim(utenteDTO.getCfUtente());
                responsabileDTO.setDInserim(new Date());
                responsabileDTO = dao.insert(ResponsabileDBDef.class, responsabileDTO);
            } else {
                responsabileDTO.setCodUserInserim(responsabileDTOOriginal.getCodUserInserim());
                responsabileDTO.setDInserim(responsabileDTOOriginal.getDInserim());
                responsabileDTO = dao.update(ResponsabileDBDef.class, responsabileDTO);
            }

            // Save utente
            utenteDTO.setEmail(sap.getRecapito().getEmail());
            utenteDTO = dao.update(UtenteDBDef.class, utenteDTO);
            log.debug("[UtentiApiServiceImpl::saveResponsabile] responsabile=" + responsabile);
            return Response.ok(sap).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::saveResponsabile] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_DATI_RESPONSABILE, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "saveResponsabile()", "invocazione API UtentiApiServiceImpl.saveResponsabile", "");
            watcher.stop();
        }
    }

    /**
     * Save SAP.
     *
     * @param idUtente        the id utente
     * @param parametriSap    the parametri sap
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response saveSAP(Long idUtente, ParametriSalvataggioSAP parametriSap, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::saveSAP] idUtente=" + idUtente);
        //Salvo i parametri in input, sia per log, sia per tracciamento in tabella evento in caso di errore
        String parametriSapJson = GsonUtils.toGsonString(parametriSap);
        log.debug("[UtentiApiServiceImpl::saveSAP] parametriSap (formato json per eventuali test di invio)  =" + parametriSapJson);

        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();

        Long codiceTracciamento = null;
        if (parametriSap.getSap().getIdentificativoSap() == null) {
            codiceTracciamento = TracciamentoUtils.SAVE_SAP_INSERIMENTO;
        } else {
            codiceTracciamento = TracciamentoUtils.SAVE_SAP_MODIFICA;
        }
        String sezioniModificate = "";
        if (parametriSap.getSezioni() != null) {
            sezioniModificate = " - Sezioni: " + Arrays.toString(parametriSap.getSezioni().toArray()) + " - ";
        }

        try {
            restHelper.checkUtente(httpRequest, codiceTracciamento, idUtente);
            SchedaAnagraficoProfessionale sap = parametriSap.getSap();
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            UtenteFilter filter = new UtenteFilter();
            filter.getIdUtente().eq(idUtente);
            UtenteDTO utenteDto = dao.findFirst(UtenteDBDef.class, filter);
            if (sap.getRecapito() != null && sap.getRecapito().getEmail() != null) {
                utenteDto.setEmail(sap.getRecapito().getEmail());
                dao.update(UtenteDBDef.class, utenteDto);
            }

            Esito esito = null;
            if (sap.getIdentificativoSap() == null) {
                esito = new Esito();
                EsitoOperazioneInvioNuovaSap esitoNuovaSAP = AdapterOrchsilWSImpl.getInstance().sendNuovaSAP(codiceFiscaleUtente, sap, parametriSap);
                if (esitoNuovaSAP.isEsitoPositivo()) {
                    utenteDto.setIdentificativoSap(esitoNuovaSAP.getIdentificativoSap());
                    utenteDto.setIdSilLavAngrafica(esitoNuovaSAP.getIdAnagraficaSilp());
                    dao.update(UtenteDBDef.class, utenteDto);
                    esito.setCode("OK");
                } else {
                    // KO la sap non e' andata ma torno dei messaggi informativi per informare
                    // l'utente del tipo di errore e tracciare l'esito
                    esito.setCode("KO");
                    esito.setMessaggioInformativo(AdapterOrchsilWSImpl.estraiMessaggioDaErroriMinisteriali("" + esitoNuovaSAP.getMessaggiAllAsString()));
                }
            } else {
                esito = AdapterSilpsvspWSImpl.getInstance().sendSAP(utenteDto.getIdSilLavAngrafica(), utenteDto.getCfUtente(), sap, parametriSap,
                        codiceFiscaleUtente);
            }

            // Occhio perche' questo esito puo' provenire dall'invio al ministero o
            // dall'invio a silp
            if (esito != null) {
                log.debug("[UtentiApiServiceImpl::saveSAP] esito=" + esito);
                String messaggiToLog = (esito.getMessaggioCittadino() != null ? "MessaggioCittadino=" + esito.getMessaggioCittadino() : "");
                messaggiToLog += (esito.getMessaggioInformativo() != null ? " MessaggioInformativo=" + esito.getMessaggioInformativo() : "");
                // Traccio comunque errori e warning. Se poi ho effettivo KO e messaggio grave
                // per cittadino torno errore 402,
                // altrimenti se ho solo un KO con messaggio informativo torno response ok per
                // farlo visualizzare
                if ("KO".equals(esito.getCode())) {
                    log.info("[UtentiApiServiceImpl::saveSAP] KO idUtente=" + idUtente + ", " + sezioniModificate + messaggiToLog);
                    messaggiToLog += " parametriSap=" + parametriSapJson;
                    tracciamentoUtils.tracciaKo(codiceTracciamento, httpRequest, idUtente, sezioniModificate + messaggiToLog, parametriSap.getCodiceAmbito());
                    // Se ho effettivamente un errore grave per cittadino altrimenti torno ok con
                    // messaggi informativi
                    if (SilCommonUtils.isNotVoid(esito.getMessaggioCittadino())) {
                        return createErrorResponse("402", MessaggiUtils.ME002);
                    } else if (SilCommonUtils.isNotVoid(esito.getMessaggioInformativo())) {
                        return Response.ok(esito).build();
                    }
                    // non dovrebbe capitare, KO senza messaggi
                    else {
                        return createErrorResponse("402", MessaggiUtils.ME002);
                    }
                }
                tracciamentoUtils.tracciaOk(codiceTracciamento, httpRequest, idUtente,
                        "Effettuato salvataggio SAP " + sap.getIdentificativoSap() + sezioniModificate + messaggiToLog, parametriSap.getCodiceAmbito());
                return Response.ok(esito).build();
            } else {
                // Nessuna SAP trovata
                ErrorDef err = new ErrorDef();
                err.setCode("400");
                err.setErrorMessage("Aggiornamento SchedaAnagraficoProfessionale impossibile per l'utente " + idUtente);
                log.debug("[UtentiApiServiceImpl::getSAP] esito=400-" + err.getErrorMessage());
                return Response.status(400).entity(err).build();
            }
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::saveSAP] " + sezioniModificate + "idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(codiceTracciamento, httpRequest, idUtente, sezioniModificate + ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    parametriSap.getCodiceAmbito());
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "saveSAP()", "invocazione API UtentiApiServiceImpl.saveSAP", "");
            watcher.stop();
        }
    }

    /**
     * Save utente A carico.
     *
     * @param idUtente        the id utente
     * @param utenteACarico   the utente A carico
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response saveUtenteACarico(Long idUtente, UtenteACarico utenteACarico, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::saveUtenteACarico] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.SAVE_UTENTI_A_CARICO, idUtente);
            UtenteDTO utenteDTO = getUtenteById(idUtente);
            if (utenteDTO == null) {
                log.error("[UtentiApiServiceImpl::saveUtenteACarico] utente non presente, idUtente=" + idUtente);
                tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_UTENTI_A_CARICO, httpRequest, idUtente, "Utente non mappato su sistema", null);
                ErrorDef err = new ErrorDef();
                err.setCode("500");
                err.setErrorMessage(messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME002).getTesto());
                return Response.serverError().entity(err).build();
            }
            if (utenteACarico == null || utenteACarico.getTutelato() == null || utenteACarico.getTipoResponsabilita() == null
                    || StringUtils.isBlank(utenteACarico.getTipoResponsabilita().getCodice())) {
                log.error("[UtentiApiServiceImpl::saveUtenteACarico] Dati tutelato omessi, idUtente=" + idUtente + ", utente a carico=" + utenteACarico);
                tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_UTENTI_A_CARICO, httpRequest, idUtente, "Utente a carido non inviato correttamente", null);
                ErrorDef err = new ErrorDef();
                err.setCode("400");
                err.setErrorMessage(messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME002).getTesto());
                return Response.status(Response.Status.BAD_REQUEST).entity(err).build();
            }
            if (utenteACarico.getTutelato().getIdUtente() == null) {
                persistUtente(utenteACarico.getTutelato());
            }

            RelUtenteFilter relFilter = new RelUtenteFilter();
            relFilter.getIdResponsabile().eq(idUtente);
            relFilter.getTutelato().getIdUtente().eq(utenteACarico.getTutelato().getIdUtente());
            RelUtenteDTO relUtenteDTO = dao.findFirst(RelUtenteDBDef.class, relFilter);
            if (relUtenteDTO == null) {
                relUtenteDTO = new RelUtenteDTO();
                relUtenteDTO.setIdResponsabile(idUtente);
                UtenteDTO tutelatoDTO = new UtenteDTO();
                tutelatoDTO.setIdUtente(utenteACarico.getTutelato().getIdUtente());
                relUtenteDTO.setTutelato(tutelatoDTO);
                TipoResponsabilitaDTO tipoResponsabilitaDTO = new TipoResponsabilitaDTO();
                tipoResponsabilitaDTO.setCodice(utenteACarico.getTipoResponsabilita().getCodice());
                relUtenteDTO.setTipoResponsabilita(tipoResponsabilitaDTO);
                relUtenteDTO.setCodUserInserim(utenteDTO.getCfUtente());
                relUtenteDTO.setCodUserAggiorn(utenteDTO.getCfUtente());
                relUtenteDTO.setDInserim(new Date());
                relUtenteDTO.setDAggiorn(new Date());
                dao.insert(RelUtenteDBDef.class, relUtenteDTO);
                for (UtentePresaVisione presaVisione : utenteACarico.getPreseVisione()) {
                    RelUtentePrivacyDTO utentePrivacyDTO = new RelUtentePrivacyDTO();
                    utentePrivacyDTO.setCodAmbito(presaVisione.getCodAmbito());
                    utentePrivacyDTO.setCodUserAggiorn(utenteDTO.getCfUtente());
                    utentePrivacyDTO.setCodUserInserim(utenteDTO.getCfUtente());
                    utentePrivacyDTO.setDataPresaVisione(new Date());
                    utentePrivacyDTO.setIdResponsabile(utenteDTO.getIdUtente());
                    utentePrivacyDTO.setIdTutelato(relUtenteDTO.getTutelato().getIdUtente());
                    dao.insert(RelUtentePrivacyDBDef.class, utentePrivacyDTO);
                }
            } else {
                Set<String> ambitiPresaVisione = new HashSet<>();
                for (UtentePresaVisione presaVisione : getPreseVisioneUtenteACarico(utenteDTO.getIdUtente(), utenteACarico.getTutelato().getIdUtente())) {
                    ambitiPresaVisione.add(presaVisione.getCodAmbito());
                }
                for (UtentePresaVisione presaVisione : utenteACarico.getPreseVisione()) {
                    if (!ambitiPresaVisione.contains(presaVisione.getCodAmbito())) {
                        RelUtentePrivacyDTO utentePrivacyDTO = new RelUtentePrivacyDTO();
                        utentePrivacyDTO.setCodAmbito(presaVisione.getCodAmbito());
                        utentePrivacyDTO.setCodUserAggiorn(utenteDTO.getCfUtente());
                        utentePrivacyDTO.setCodUserInserim(utenteDTO.getCfUtente());
                        utentePrivacyDTO.setDataPresaVisione(new Date());
                        utentePrivacyDTO.setIdResponsabile(utenteDTO.getIdUtente());
                        utentePrivacyDTO.setIdTutelato(relUtenteDTO.getTutelato().getIdUtente());
                        dao.insert(RelUtentePrivacyDBDef.class, utentePrivacyDTO);
                    }
                }
                if (utenteACarico.getTipoResponsabilita() != null && !StringUtils.isBlank(utenteACarico.getTipoResponsabilita().getCodice())) {
                    TipoResponsabilitaDTO tipoResponsabilitaDTO = new TipoResponsabilitaDTO();
                    tipoResponsabilitaDTO.setCodice(utenteACarico.getTipoResponsabilita().getCodice());
                    relUtenteDTO.setTipoResponsabilita(tipoResponsabilitaDTO);
                }
                relUtenteDTO.setCodUserAggiorn(utenteDTO.getCfUtente());
                relUtenteDTO.setDAggiorn(new Date());
                dao.update(RelUtenteDBDef.class, relUtenteDTO);
            }
            Esito esito = new Esito();
            esito.setCode("200");
            log.debug("[UtentiApiServiceImpl::saveUtenteACarico] esito=" + esito);
            return Response.ok(esito).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::saveUtenteACarico] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_UTENTI_A_CARICO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "saveUtente()", "invocazione API UtentiApiServiceImpl.saveUtente", "");
            watcher.stop();
        }
    }

    /**
     * Gets the id sil lav anagrafica.
     *
     * @param idUtente the id utente
     * @return the id sil lav anagrafica
     * @throws DAOException the DAO exception
     */
    private Long getIdSilLavAnagrafica(Long idUtente) throws DAOException {
        UtenteFilter filter = new UtenteFilter();
        filter.getIdUtente().eq(idUtente);
        UtenteDTO utente = dao.findFirst(UtenteDBDef.class, filter);
        return getIdSilLavAnagrafica(utente);
    }

    /**
     * Gets the id sil lav anagrafica.
     *
     * @param utente the utente
     * @return the id sil lav anagrafica
     */
    private Long getIdSilLavAnagrafica(UtenteDTO utente) {
        return utente.getIdSilLavAngrafica();
    }

    /**
     * Find documenti.
     *
     * @param idUtente        the id utente
     * @param codAmbito       the cod ambito
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response findDocumenti(Long idUtente, String codAmbito, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::findDocumenti] idUtente=" + idUtente + COD_AMBITO + codAmbito);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.FIND_DOCUMENTI, idUtente);
            DocumentoFilter filter = new DocumentoFilter();
            filter.getUtente().getIdUtente().eq(idUtente);
            filter.getCodAmbito().eq(codAmbito);

            List<Documento> documenti = new DocumentoMapper().mapListNoPdf(dao.findAll(DocumentoDBDef.class, filter, 0));
            return Response.ok(documenti).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::findDocumenti] idUtente=" + idUtente + COD_AMBITO + codAmbito, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.FIND_DOCUMENTI, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), codAmbito);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "findDocumenti()", "invocazione API UtentiApiServiceImpl.findDocumenti", "");
            watcher.stop();
        }
    }

    /**
     * Find documenti patti servizio.
     *
     * @param idUtente        the id utente
     * @param idDid           the id did
     * @param codAmbito       the cod ambito
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response findDocumentiPattiServizio(Long idUtente, Long idDid, String codAmbito, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::findDocumentiPattiServizio] idUtente=" + idUtente + COD_AMBITO + codAmbito + ", idDid=" + idDid);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.FIND_DOCUMENTI_DID, idUtente);
            DocumentoFilter filter = new DocumentoFilter();
            filter.getUtente().getIdUtente().eq(idUtente);
            filter.getCodAmbito().eq(codAmbito);
            filter.getIdSilLavSapDid().eq(idDid);
            List<Documento> documenti = new DocumentoMapper().mapListNoPdf(dao.findAll(DocumentoDBDef.class, filter, 0));
            return Response.ok(documenti).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::findDocumentiPattiServizio] idUtente=" + idUtente + COD_AMBITO + codAmbito + ", idDid=" + idDid, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.FIND_DOCUMENTI_DID, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), codAmbito);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "findDocumentiPattiServizio()",
                    "invocazione API UtentiApiServiceImpl.findDocumentiPattiServizio", "");
            watcher.stop();
        }
    }

    /**
     * Gets the adesione YG by SILP.
     *
     * @param idSilp          the id silp
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the adesione YG by SILP
     */
    @Override
    public Response getAdesioneYGBySILP(Long idSilp, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::getAdesioneYGBySILP] idSilp=" + idSilp);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            return retrieveAdesioneFromSilp("getAdesioneYGBySILP", null, idSilp, httpRequest);
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::getAdesioneYGBySILP] idSilp=" + idSilp, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.ADESIONE, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName() + ", idSilp=" + idSilp,
                    AmbitoDTO.COD_AMBITO_GG_GARANZIA_GIOVANI);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "getAdesioneYGBySILP()", "invocazione API UtentiApiServiceImpl.getAdesioneYGBySILP", "");
            watcher.stop();
        }
    }

    /**
     * In.
     *
     * @param s   the s
     * @param els the els
     * @return true, if successful
     */
    private boolean in(String s, String... els) {
        for (String el : els) {
            if (el.equals(s))
                return true;
        }
        return false;
    }

    /**
     * Gets the SAP by SILP.
     *
     * @param idSilp          the id silp
     * @param codAmbito       the cod ambito
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the SAP by SILP
     */
    @Override
    public Response getSAPBySILP(Long idSilp, String codAmbito, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::getSAPBySILP] idSilp=" + idSilp + COD_AMBITO2 + codAmbito);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            SchedaAnagraficoProfessionale sap = getSapConVerificaEsistenza(idSilp, null, restHelper.getCodiceFiscaleCurrentUser(httpRequest));

            if (sap != null) {
                restHelper.checkUtente(httpRequest, TracciamentoUtils.GET_SAP, sap.getCodiceFiscale());
                if (sap.getIdentificativoSap() == null && !in(codAmbito, AmbitoDTO.COD_AMBITO_FASCICOLO, AmbitoDTO.COD_AMBITO_COLLOCAMENTO_MIRATO)) {
                    tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_SAP, httpRequest, null, "IdentificativoSap non valorizzato per idSilp=" + idSilp,
                            codAmbito);
                    log.debug("[UtentiApiServiceImpl::getSAP] esito=402 - Messaggio ME003");
                    return createErrorResponse("402", MessaggiUtils.ME003);
                }
                sap.setIdSilLavAnagrafica(idSilp);
                UtenteFilter filter = new UtenteFilter();
                filter.getIdSilLavAngrafica().eq(idSilp);
                UtenteDTO utente = dao.findFirst(UtenteDBDef.class, filter);
                if (utente != null) {
                    if (sap.getRecapito() != null && sap.getRecapito().getEmail() != null) {
                        utente.setEmail(sap.getRecapito().getEmail());
                    }
                    utente.setIdentificativoSap(sap.getIdentificativoSap());
                    utente.setCognome(sap.getCognome());
                    utente.setNome(sap.getNome());
                    dao.update(UtenteDBDef.class, utente);
                }
                log.debug("[UtentiApiServiceImpl::getSAPBySILP] sap=" + sap);
                return Response.ok(sap).build();
            } else {
                // Nessuna SAP trovata
                tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_SAP, httpRequest, null, "SAP non presente per idSilp=" + idSilp, codAmbito);
                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage("Scheda AnagraficoProfessionale non trovata per l'utente " + idSilp);
                log.debug("[UtentiApiServiceImpl::getSAPBySILP] esito=404-" + err.getErrorMessage());
                return Response.serverError().entity(err).status(404).build();
            }
        } catch (Exception ex) {
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_SAP, httpRequest, null, "ERRORE SOAP - SAP non presente per idSilp = " + idSilp, codAmbito);
            log.error("[UtentiApiServiceImpl::getSAPBySILP] idSilp=" + idSilp, ex);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "getSAP()", "invocazione API UtentiApiServiceImpl.getSAP", "");
            watcher.stop();
        }
    }

    /**
     * Retrieve adesione from silp.
     *
     * @param methodName  the method name
     * @param idUtente    the id utente
     * @param idSilp      the id silp
     * @param httpRequest the http request
     * @return the response
     * @throws Exception the exception
     */
    private Response retrieveAdesioneFromSilp(String methodName, Long idUtente, Long idSilp, HttpServletRequest httpRequest) throws Exception {
        if (idSilp == null) {
            tracciamentoUtils.tracciaKo(TracciamentoUtils.ADESIONE, httpRequest, idUtente, "Id silp non presente per idUtente=" + idUtente,
                    AmbitoDTO.COD_AMBITO_GG_GARANZIA_GIOVANI);
            return createErrorResponse("404", MessaggiUtils.ME008);
        }
        String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
        AdesioneYG adesione = AdapterSilpsvspWSImpl.getInstance().getAdesioneYG(idSilp, null);
        Map<String, String> statiAdesione = AdapterSilpsvdeWSImpl.getInstance().findMapStatiAdesione(codiceFiscaleUtente);
        if (adesione != null) {
            if (idUtente != null) {
                restHelper.checkUtente(httpRequest, TracciamentoUtils.ADESIONE, idUtente);
            } else if (idSilp != null) {
                restHelper.checkUtenteBySILP(httpRequest, TracciamentoUtils.ADESIONE, idSilp);
            }
            String paramDataGGOk = parametroUtils.getParametro("GG_DT_OK");
            if (paramDataGGOk != null) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date dataGGOk = df.parse(paramDataGGOk);
                if (adesione.getDataAdesione().before(dataGGOk)) {
                    log.debug("[UtentiApiServiceImpl::" + methodName + "] La data adesione e' minore di GG_DT_OK per idAdesione="
                            + adesione.getIdSilLavAdesione() + ": " + adesione.getDataAdesione() + " < " + dataGGOk);
                    tracciamentoUtils.tracciaKo(TracciamentoUtils.ADESIONE, httpRequest, idUtente, "La data adesione e' minore di GG_DT_OK per idAdesione="
                            + adesione.getIdSilLavAdesione() + ": " + adesione.getDataAdesione() + " < " + dataGGOk, null);
                    return createErrorResponse("404", MessaggiUtils.ME008);
                }
            }
            adesione.setDescrizione(statiAdesione.get(adesione.getCodice()));
            log.debug("[UtentiApiServiceImpl::" + methodName + "] adesione=" + adesione);
            return Response.ok(adesione).build();
        }
        // Nessuna Adesione trovata
        tracciamentoUtils.tracciaKo(TracciamentoUtils.ADESIONE, httpRequest, idUtente, "Adesione non trovata per idSilp=" + idSilp, null);
        return createErrorResponse("404", MessaggiUtils.ME008);
    }

    /**
     * Sets the descrizione adesione.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @param codiceAdesione      the codice adesione
     * @return the string
     * @throws Exception the exception
     */
    private String setDescrizioneAdesione(String codiceFiscaleUtente, String codiceAdesione) throws Exception {
        Map<String, String> statiAdesione = AdapterSilpsvdeWSImpl.getInstance().findMapStatiAdesione(codiceFiscaleUtente);
        return statiAdesione.get(codiceAdesione);

    }

    /**
     * Find tutori.
     *
     * @param idUtente        the id utente
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response findTutori(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::findTutori] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            RelUtenteFilter relUtenteFilter = new RelUtenteFilter();
            relUtenteFilter.getTutelato().getIdUtente().eq(idUtente);
            QueryResult<RelUtenteDTO> relUtenteDTOs = dao.findAll(RelUtenteDBDef.class, relUtenteFilter, 0);
            List<Utente> res = new ArrayList<>();
            if (relUtenteDTOs != null) {
                UtenteMapper mapper = new UtenteMapper();
                for (RelUtenteDTO rud : relUtenteDTOs) {
                    UtenteFilter utenteFilter = new UtenteFilter();
                    utenteFilter.getIdUtente().eq(rud.getIdResponsabile());
                    UtenteDTO utenteDTO = dao.findFirst(UtenteDBDef.class, utenteFilter);
                    if (utenteDTO != null) {
                        Utente utente = mapper.mapReverse(utenteDTO);
                        res.add(utente);
                    }
                }
            }
            log.debug("[UtentiApiServiceImpl::findTutori] tutori=" + res);
            return Response.ok(res).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::findTutori] idUtente=" + idUtente, ex);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "findTutori()", "invocazione API UtentiApiServiceImpl.findTutori", "");
            watcher.stop();
        }
    }

    /**
     * Gets the domanda RDC.
     *
     * @param idUtente        the id utente
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the domanda RDC
     */
    @Override
    public Response getDomandaRDC(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::getDomandaRDC] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.DOMANDA_RDC, idUtente);
            DomandaRDC domanda = AdapterSilpsvspWSImpl.getInstance().getDomandaRDCBySILP(getIdSilLavAnagrafica(idUtente));
            if (domanda != null) {
                log.debug("[UtentiApiServiceImpl::getDomandaRDC] domanda=" + domanda);
                return Response.ok(domanda).build();
            }
            // Nessuna Domanda trovata
            tracciamentoUtils.tracciaKo(TracciamentoUtils.DOMANDA_RDC, httpRequest, idUtente, "DomandaRDC non trovata per idUtente=" + idUtente, null);
            ErrorDef err = new ErrorDef();
            err.setCode("404");
            err.setErrorMessage(messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME008).getTesto());
            log.debug("[UtentiApiServiceImpl::getDomandaRDC] esito=404-" + err.getErrorMessage());
            return Response.status(404).entity(err).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::getDomandaRDC] idUtente=" + idUtente, ex);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "getDomandaRDC()", "invocazione API UtentiApiServiceImpl.getDomandaRDC", "");
            watcher.stop();
        }
    }

    /**
     * Gets the domanda RDC by SILP.
     *
     * @param idSilp          the id silp
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the domanda RDC by SILP
     */
    @Override
    public Response getDomandaRDCBySILP(Long idSilp, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::getDomandaRDCBySILP] idSilp=" + idSilp);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            DomandaRDC domanda = AdapterSilpsvspWSImpl.getInstance().getDomandaRDCBySILP(idSilp);

            if (domanda != null) {
                restHelper.checkUtenteBySILP(httpRequest, TracciamentoUtils.DOMANDA_RDC, idSilp);
                log.debug("[UtentiApiServiceImpl::getDomandaRDCBySILP] domanda=" + domanda);
                return Response.ok(domanda).build();
            } else {
                UtenteFilter filter = new UtenteFilter();
                filter.getIdSilLavAngrafica().eq(idSilp);
                UtenteDTO utente = dao.findFirst(UtenteDBDef.class, filter);
                if (null != utente) {
                    tracciamentoUtils.tracciaKo(TracciamentoUtils.DOMANDA_RDC, httpRequest, utente.getIdUtente(), "DomandaRDC non trovata per idSilp=" + idSilp,
                            null);
                }
                // Nessuna Domanda trovata
                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage(messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME008).getTesto());
                log.debug("[UtentiApiServiceImpl::getDomandaRDCBySILP] esito=404-" + err.getErrorMessage());
                return Response.status(404).entity(err).build();
            }
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::getDomandaRDCBySILP] idSilp=" + idSilp, ex);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "getDomandaRDCBySILP()", "invocazione API UtentiApiServiceImpl.getDomandaRDCBySILP", "");
            watcher.stop();
        }
    }

    /** The Constant ESITO_VERIFICA_MINORI_TUTORI_GIA_PRESENTI. */
    public static final String ESITO_VERIFICA_MINORI_TUTORI_GIA_PRESENTI = "TUTORI_GIA_PRESENTI";

    /** The Constant ESITO_VERIFICA_MINORI_TUTORE_GIA_COLLEGATO. */
    public static final String ESITO_VERIFICA_MINORI_TUTORE_GIA_COLLEGATO = "TUTORE_GIA_COLLEGATO";

    /** The Constant ESITO_VERIFICA_MINORI_UTENTE_SILP_NON_PRESENTE. */
    public static final String ESITO_VERIFICA_MINORI_UTENTE_SILP_NON_PRESENTE = "UTENTE_SILP_NON_PRESENTE";

    /** The Constant ESITO_VERIFICA_MINORI_SAP_NON_PRESENTE. */
    public static final String ESITO_VERIFICA_MINORI_SAP_NON_PRESENTE = "SAP_NON_PRESENTE";

    /** The Constant ESITO_VERIFICA_MINORI_UTENTE_NON_MINORENNE. */
    public static final String ESITO_VERIFICA_MINORI_UTENTE_NON_MINORENNE = "UTENTE_NON_MINORENNE";

    /** The Constant ESITO_VERIFICA_MINORI_UTENTE_ETA_MINIMA_NON_RISPETTATA. */
    public static final String ESITO_VERIFICA_MINORI_UTENTE_ETA_MINIMA_NON_RISPETTATA = "UTENTE_ETA_MINIMA_NON_RISPETTATA";

    /** The Constant ESITO_VERIFICA_MINORI_ADESIONE_NON_PRESENTE. */
    public static final String ESITO_VERIFICA_MINORI_ADESIONE_NON_PRESENTE = "ADESIONE_NON_PRESENTE";

    /** The Constant ESITO_VERIFICA_MINORI_INCONTRO_DE_ER. */
    public static final String ESITO_VERIFICA_MINORI_INCONTRO_DE_ER = "INCONTRO_DE_ER";

    /** The Constant ESITO_VERIFICA_MINORI_ADESIONE_RESPINTA. */
    public static final String ESITO_VERIFICA_MINORI_ADESIONE_RESPINTA = "ADESIONE_RESPINTA";

    /**
     * Verifica minore.
     *
     * @param idUtente        the id utente
     * @param codiceFiscale   the codice fiscale
     * @param codAmbito       the cod ambito
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response verificaMinore(Long idUtente, String codiceFiscale, String codAmbito, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::verificaMinore] codiceFiscale=" + codiceFiscale);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            EsitoVerificaMinore esito = new EsitoVerificaMinore();
            Date now = new Date();
            esito.setCodiciEsito(new ArrayList<>());
            Long idSilp = null;
            UtenteFilter utenteFilter = new UtenteFilter();
            utenteFilter.getCodTipoUtente().eq("CIT");
            utenteFilter.getCfUtente().eq(codiceFiscale);
            UtenteDTO utenteDTO = dao.findFirst(UtenteDBDef.class, utenteFilter);
            Utente utente = new Utente();
            if (utenteDTO != null) {
                utente.setCodiceFiscale(utenteDTO.getCfUtente());
                utente.setCodiceTipoUtente(utente.getCodiceTipoUtente());
                utente.setCognome(utenteDTO.getCognome());
                utente.setNome(utenteDTO.getNome());
                utente.setEmail(utenteDTO.getEmail());
                utente.setIdSilLavAnagrafica(utenteDTO.getIdSilLavAngrafica());
                utente.setIdUtente(utenteDTO.getIdUtente());
                utente.setIdentificativoSap(utenteDTO.getIdentificativoSap());
                utente.setPreseVisione(getPreseVisioneUtente(utenteDTO.getIdUtente()));
                idSilp = utenteDTO.getIdSilLavAngrafica();

                // Verifica la presenza di altri tutori
                RelUtenteFilter relUtenteFilter = new RelUtenteFilter();
                relUtenteFilter.getTutelato().getIdUtente().eq(utenteDTO.getIdUtente());
                boolean tutoriGiaPresenti = false;
                for (RelUtenteDTO relUtenteDTO : dao.findAll(RelUtenteDBDef.class, relUtenteFilter, 0)) {
                    if (relUtenteDTO.getIdResponsabile().equals(idUtente)) {
                        esito.getCodiciEsito().add(ESITO_VERIFICA_MINORI_TUTORE_GIA_COLLEGATO);
                    } else {
                        if (!tutoriGiaPresenti) {
                            tutoriGiaPresenti = true;
                            esito.getCodiciEsito().add(ESITO_VERIFICA_MINORI_TUTORI_GIA_PRESENTI);
                        }
                    }
                }

                // Verifica la presenza di incontri in stato DE o ER
                if (!in(codAmbito, AmbitoDTO.COD_AMBITO_FASCICOLO, AmbitoDTO.COD_AMBITO_COLLOCAMENTO_MIRATO)) {
                    PslOrchApiClient client = new PslOrchApiClient();
                    PrenotazioniDTO prenotazioniDTO = client.findPrenotazioniIncontri(utente.getIdUtente(), codAmbito);
                    for (PrenotazioneDTO prenotazione : prenotazioniDTO.getEls()) {
                        String stato = prenotazione.getIdStatoAppuntamento();
                        if ("DE".equals(stato) || "ER".equals(stato)) {
                            esito.getCodiciEsito().add(ESITO_VERIFICA_MINORI_INCONTRO_DE_ER);
                            break;
                        }
                    }
                }

            }
            if (idSilp == null) {
                EsitoRicercaLavoratore esitoRicercaLavoratore = AdapterSilpsvalWSImpl.getInstance().ricercaLavoratoreInfoArchiviato(codiceFiscale);

                if (esitoRicercaLavoratore.getCodiceErrore() == null && esitoRicercaLavoratore.getLavoratori().size() == 1) {
                    idSilp = esitoRicercaLavoratore.getLavoratori().get(0).getIdLavoratore().longValue();
                }
            }
            if (idSilp == null) {
                esito.getCodiciEsito().add(ESITO_VERIFICA_MINORI_UTENTE_SILP_NON_PRESENTE);
            } else {
                SchedaAnagraficoProfessionale sap = getSapConVerificaEsistenza(idSilp, null, restHelper.getCodiceFiscaleCurrentUser(httpRequest));
                if (sap == null) {
                    esito.getCodiciEsito().add(ESITO_VERIFICA_MINORI_SAP_NON_PRESENTE);
                } else {
                    int minoreEta = Integer.parseInt(parametroUtils.getParametro(ParametroUtils.MINORE_ETA, "0"));
                    int anni = getDiffYears(sap.getDataDiNascita(), now);
                    if (anni >= 18) {
                        esito.getCodiciEsito().add(ESITO_VERIFICA_MINORI_UTENTE_NON_MINORENNE);
                    } else if (in(codAmbito, AmbitoDTO.COD_AMBITO_FASCICOLO) && minoreEta > 0 && anni <= minoreEta) {
                        esito.getCodiciEsito().add(ESITO_VERIFICA_MINORI_UTENTE_ETA_MINIMA_NON_RISPETTATA);
                    } else {
                        utente.setCodiceFiscale(codiceFiscale);
                        utente.setCodiceTipoUtente("CIT");
                        utente.setCognome(sap.getCognome());
                        utente.setNome(sap.getNome());
                        if (sap.getRecapito() != null && sap.getRecapito().getEmail() != null) {
                            utente.setEmail(sap.getRecapito().getEmail());
                        }
                        utente.setIdentificativoSap(sap.getIdentificativoSap());
                        utente.setIdSilLavAnagrafica(idSilp);
                    }
                }

                if (!in(codAmbito, AmbitoDTO.COD_AMBITO_FASCICOLO, AmbitoDTO.COD_AMBITO_COLLOCAMENTO_MIRATO)) {
                    AdesioneYG adesione = AdapterSilpsvspWSImpl.getInstance().getAdesioneYG(idSilp, null);
                    if (adesione == null || !("A".equals(adesione.getCodice()))) {
                        esito.getCodiciEsito().add(ESITO_VERIFICA_MINORI_ADESIONE_NON_PRESENTE);
                    } else {
                        log.info("[UtentiApiServiceImpl::verificaMinore] adesione=" + adesione);
                        String paramDataGGOk = parametroUtils.getParametro("GG_DT_OK");
                        if (paramDataGGOk != null) {
                            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                            Date dataGGOk = df.parse(paramDataGGOk);
                            if (adesione.getDataAdesione().before(dataGGOk)) {
                                esito.getCodiciEsito().add(ESITO_VERIFICA_MINORI_ADESIONE_NON_PRESENTE);
                            } else {
                                // controlla lo stato adesione, deve essere NON finale
                                log.info("[UtentiApiServiceImpl::verificaMinore] adesione=" + adesione);
                                if (SilCommonConstants.S.equals(adesione.getStatoCorrenteFinale())) {
                                    esito.getCodiciEsito().add(ESITO_VERIFICA_MINORI_ADESIONE_NON_PRESENTE);
                                } else if (adesione.getDataRifiuto() != null) {
                                    esito.getCodiciEsito().add(ESITO_VERIFICA_MINORI_ADESIONE_RESPINTA);
                                }
                            }

                        }
                    }
                }
            }

            esito.setUtente(utente);
            return Response.ok(esito).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::verificaMinore] codiceFiscale=" + codiceFiscale, ex);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "verificaMinore()", "invocazione API UtentiApiServiceImpl.verificaMinore", "");
            watcher.stop();
        }
    }

    /**
     * Gets the diff years.
     *
     * @param first the first
     * @param last  the last
     * @return the diff years
     */
    private static int getDiffYears(Date first, Date last) {

        Calendar dobDate = getCalendar(first); // Set this to date to check
        Calendar today = getCalendar(last);
        int curYear = today.get(Calendar.YEAR);
        int curMonth = today.get(Calendar.MONTH);
        int curDay = today.get(Calendar.DAY_OF_MONTH);

        int year = dobDate.get(Calendar.YEAR);
        int month = dobDate.get(Calendar.MONTH);
        int day = dobDate.get(Calendar.DAY_OF_MONTH);

        int age = curYear - year;
        if (curMonth < month || (month == curMonth && curDay < day)) {
            age--;
        }

        return age;
    }

    /**
     * Gets the calendar.
     *
     * @param date the date
     * @return the calendar
     */
    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.ITALIAN);
        cal.setTime(date);
        return cal;
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
        if ("404".equals(code))
            return Response.status(Response.Status.NOT_FOUND).entity(err).build();
        return Response.serverError().entity(err).build();
    }

    /**
     * Find riepilogo utenti iscrizione.
     *
     * @param idUtente        the id utente
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response findRiepilogoUtentiIscrizione(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::findRiepilogoUtentiIscrizione] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            EsitoRiepilogoIscrizione result = new EsitoRiepilogoIscrizione();

            int etaMin = Integer.parseInt(parametroUtils.getParametro(ParametroUtils.GG_MINORE_ETA, "0"));
            int etaMax = Integer.parseInt(parametroUtils.getParametro(ParametroUtils.GG_MAGGIORE_ETA, "0"));
            String msgEtaNonCoerente = messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME143).getTesto();
            msgEtaNonCoerente = msgEtaNonCoerente.replace("{0}", "" + etaMin);
            msgEtaNonCoerente = msgEtaNonCoerente.replace("{1}", "" + etaMax);

            result.setMessaggioEtaNonCoerente(msgEtaNonCoerente);
            List<UtenteRiepilogoIscrizioneComparable> tutelati = new ArrayList<UtenteRiepilogoIscrizioneComparable>();

            restHelper.checkUtente(httpRequest, TracciamentoUtils.RIEPILOGO_ISCRIZIONE, idUtente);
            String codFiscaleUser = restHelper.getCodiceFiscaleCurrentUser(httpRequest);

            // utente tutore
            UtenteMapper utenteMapper = new UtenteMapper();
            UtenteDTO utenteDTO = getUtenteById(idUtente);
            UtenteRiepilogoIscrizione utenteTutore = new UtenteRiepilogoIscrizione();
            utenteTutore.setTipoResponsabilita(null);
            utenteTutore.setUtente(utenteMapper.mapReverse(utenteDTO));
            List<UtentePresaVisione> presePrivacy = getPreseVisioneUtente(idUtente);
            utenteTutore.setPreseVisione(presePrivacy);
            ControlliIscrizione datiTutore = doControlliIscrizioneGG(true, utenteDTO.getIdUtente(), utenteDTO.getIdSilLavAngrafica(), etaMin, etaMax,
                    utenteDTO.getCfUtente(), codFiscaleUser, msgEtaNonCoerente, presePrivacy);
            utenteTutore.setDati(datiTutore);
            if (datiTutore != null && datiTutore.getAdesione() != null && datiTutore.getAdesione().isPresenzaPiuAdesioniAperte()) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.RIEPILOGO_ISCRIZIONE, httpRequest, idUtente,
                        "sono presenti piu' adesioni aperte " + utenteDTO.getCfUtente(), AmbitoDTO.COD_AMBITO_GG_GARANZIA_GIOVANI);
            }
            result.setUtenteTutore(utenteTutore);
            result.setIdUtente(idUtente);
            // utenti a carico
            RelUtenteFilter filter = new RelUtenteFilter();
            filter.getIdResponsabile().eq(idUtente);
            QueryResult<RelUtenteDTO> rels = dao.findAll(RelUtenteDBDef.class, filter, 0);

            for (RelUtenteDTO rel : rels) {
                if (rel.getTutelato() != null) {

                    UtenteRiepilogoIscrizioneComparable utenteACarico = new UtenteRiepilogoIscrizioneComparable();

                    TipoResponsabilita tipoResponsabilita = new TipoResponsabilita();
                    tipoResponsabilita.setCodice(rel.getTipoResponsabilita().getCodice());
                    tipoResponsabilita.setDescrizione(rel.getTipoResponsabilita().getDescrizione());
                    utenteACarico.setTipoResponsabilita(tipoResponsabilita);
                    utenteACarico.setUtente(utenteMapper.mapReverse(rel.getTutelato()));
                    List<UtentePresaVisione> prese = getPreseVisioneUtenteACarico(idUtente, utenteACarico.getUtente().getIdUtente());
                    utenteACarico.setPreseVisione(prese);

                    ControlliIscrizione dati = doControlliIscrizioneGG(false, rel.getTutelato().getIdUtente(), rel.getTutelato().getIdSilLavAngrafica(), etaMin,
                            etaMax, rel.getTutelato().getCfUtente(), codFiscaleUser, msgEtaNonCoerente, prese);
                    utenteACarico.setDati(dati);
                    if (dati != null && dati.getAdesione() != null && dati.getAdesione().isPresenzaPiuAdesioniAperte()) {
                        tracciamentoUtils.tracciaKo(TracciamentoUtils.RIEPILOGO_ISCRIZIONE, httpRequest, utenteACarico.getUtente().getIdUtente(),
                                "sono presenti piu' adesioni aperte " + utenteACarico.getUtente().getCodiceFiscale(), AmbitoDTO.COD_AMBITO_GG_GARANZIA_GIOVANI);
                    }
                    tutelati.add(utenteACarico);
                }
            }

            // ordino i tutelati per cognome nome
            Collections.sort(tutelati);
            List<UtenteRiepilogoIscrizione> tutelatiSort = new ArrayList<UtenteRiepilogoIscrizione>();
            for (UtenteRiepilogoIscrizioneComparable ut : tutelati) {
                tutelatiSort.add((UtenteRiepilogoIscrizione) ut);
            }
            result.setTutelati(tutelatiSort);

            log.debug("[UtentiApiServiceImpl::findRiepilogoUtentiIscrizione] result=" + result);
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[UtentiApiServiceImpl::findRiepilogoUtentiIscrizione] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.RIEPILOGO_ISCRIZIONE, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    AmbitoDTO.COD_AMBITO_GG_GARANZIA_GIOVANI);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "findRiepilogoUtentiIscrizione()",
                    "invocazione API UtentiApiServiceImpl.findRiepilogoUtentiIscrizione", "");
            watcher.stop();
        }
    }

    /**
     * Do controlli iscrizione GG.
     *
     * @param isTutore          the is tutore
     * @param idUtente          the id utente
     * @param idSilLav          the id sil lav
     * @param etaMin            the eta min
     * @param etaMax            the eta max
     * @param codiceFiscale     the codice fiscale
     * @param codFiscaleUser    the cod fiscale user
     * @param msgEtaNonCoerente the msg eta non coerente
     * @param prese             the prese
     * @return the controlli iscrizione
     * @throws Exception the exception
     */
    private ControlliIscrizione doControlliIscrizioneGG(boolean isTutore, Long idUtente, Long idSilLav, int etaMin, int etaMax, String codiceFiscale,
            String codFiscaleUser, String msgEtaNonCoerente, List<UtentePresaVisione> prese) throws Exception {
        ControlliIscrizione dati = new ControlliIscrizione();
        List<String> messaggi = new ArrayList<>();
        // retrieveAdesionefromSIlp
        AdesioneYG adesione = null;
        if (idSilLav != null) {
            adesione = AdapterSilpsvspWSImpl.getInstance().getAdesionePerIscrizione(idSilLav, null);
            if (adesione != null) {
                adesione.setDescrizione(this.setDescrizioneAdesione(codFiscaleUser, adesione.getCodice()));
                if (!"PIEMONTE".equalsIgnoreCase(adesione.getRegione()) && !"S".equals(adesione.getStatoCorrenteFinale())) {
                    String msgRegione = messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME144).getTesto();
                    msgRegione = msgRegione.replace("{0}", adesione.getRegione());
                    messaggi.add(msgRegione);
                    dati.setSoggettoIdoneo(false);

                }
                dati.setAdesioneFuoriRegione(!"PIEMONTE".equalsIgnoreCase(adesione.getRegione()));
                List<PrenotazioneIncontro> prenotazioni = leggiPrenotazioni(idUtente, AmbitoDTO.COD_AMBITO_GG_GARANZIA_GIOVANI, codFiscaleUser);
                // riporto l'ultimo
                PrenotazioneIncontro maxId = null;
                if (prenotazioni != null && !prenotazioni.isEmpty()) {
                    for (PrenotazioneIncontro appuntamento : prenotazioni) {
                        if (maxId == null || appuntamento.getIdPrenotazione().intValue() > maxId.getIdPrenotazione().intValue()) {
                            maxId = appuntamento;
                        }
                    }
                    dati.setAppuntamento(maxId);
                }
            }

            dati.setAdesione(adesione);

        }

        SchedaAnagraficoProfessionale sap = null;
        if (idSilLav != null) {
            sap = getSapConVerificaEsistenza(idSilLav, codiceFiscale, codFiscaleUser);
            dati.setSap(sap);
        }
        if (sap == null) {
            messaggi.add("Fascicolo Lavoratore non presente");
        }
        dati.setPresentePrivacy(false);
        for (UtentePresaVisione presaVisione : prese) {
            if (AmbitoDTO.COD_AMBITO_GG_GARANZIA_GIOVANI.equals(presaVisione.getCodAmbito())) {
                dati.setPresentePrivacy(true);
            }
        }
        if (sap != null) {
            Date now = new Date();
            int anni = getDiffYears(sap.getDataDiNascita(), now);
            dati.setEta(anni);
            if (anni < etaMin || anni > etaMax) {
                if (isTutore) {
                    String msgEta = messaggiUtils.loadMessaggioErrore(MessaggiUtils.MI051).getTesto();
                    messaggi.add(msgEta);
                }
                dati.setEtaCoerente(false);

            } else {
                dati.setEtaCoerente(true);
            }
            if (!isTutore && anni < etaMin) {
                String msgEta = messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME020).getTesto();
                messaggi.add(msgEta);
            }
            if (anni >= 18) {
                dati.setMaggiorenne(true);
                if (!isTutore) {
                    String msgEta = messaggiUtils.loadMessaggioErrore(MessaggiUtils.MI055).getTesto();
                    messaggi.add(msgEta);
                }
            } else {
                dati.setMaggiorenne(false);
            }
            if (sap.getDomicilio() != null && sap.getDomicilio().getComune() != null && sap.getDomicilio().getComune().getProvincia() != null
                    && sap.getDomicilio().getComune().getProvincia().getDescrizione() != null) {
                String provincia = sap.getDomicilio().getComune().getProvincia().getDescrizione().toUpperCase();

                List<String> piemonteProv = Arrays.asList("TORINO", "CUNEO", "VERCELLI", "ALESSANDRIA", "BIELLA", "NOVARA", "VERBANO CUSIO OSSOLA", "ASTI");

                dati.setDomicilioPiemonte(piemonteProv.contains(provincia));

            } else {
                dati.setDomicilioPiemonte(false);
            }
            if (!dati.isDomicilioPiemonte()) {
                String msgDom = messaggiUtils.loadMessaggioErrore(MessaggiUtils.MI050).getTesto();
                messaggi.add(msgDom);
            }
            dati.setResidenzaItalia(false);
            if (sap.getResidenza() != null && sap.getResidenza().getStato() != null) {
                String stato = sap.getResidenza().getStato().getDescrizione();
                if (stato == null || "ITALIA".equalsIgnoreCase(stato)) {
                    dati.setResidenzaItalia(true);
                }
            }
            if (!dati.isResidenzaItalia()) {
                String msgRes = messaggiUtils.loadMessaggioErrore(MessaggiUtils.MI047).getTesto();
                messaggi.add(msgRes);
            }
        }

        dati.setMessaggi(messaggi);

        dati.setPossibileIscrizione(controlliPossibileIscrizione(idSilLav, dati, isTutore));
        dati.setPossibileAnnullamento(controlliPossibileAnnullamento(idSilLav, dati, isTutore));
        dati.setPossibileStampa(controlliPossibileStampa(idSilLav, dati, isTutore));
        dati.setPossibileAppuntamento(controlliPossibileAppuntamento(idSilLav, dati, isTutore));
        return dati;
    }

    /**
     * Controlli possibile iscrizione.
     *
     * @param idSilLav the id sil lav
     * @param dati     the dati
     * @param isTutore the is tutore
     * @return the boolean
     */
    private Boolean controlliPossibileIscrizione(Long idSilLav, ControlliIscrizione dati, boolean isTutore) {
        Boolean result = false;
        if (idSilLav == null) {
            // non esiste su Silp
            result = true;
        } else {
            if (dati.getAdesione() == null) {
                // adesione non presente
                result = true;
            } else {
                // adesione in piemonte con stato corrente finale
                if (!dati.isAdesioneFuoriRegione().booleanValue() && "S".equalsIgnoreCase(dati.getAdesione().getStatoCorrenteFinale())) {
                    result = true;
                } else // adesione rifiutata
                if ("S".equalsIgnoreCase(dati.getAdesione().getFlgRifiutoStatoCorrente()) || dati.getAdesione().getDataRifiuto() != null) {
                    result = true;
                } else if (dati.isAdesioneFuoriRegione().booleanValue() && "S".equalsIgnoreCase(dati.getAdesione().getStatoCorrenteFinale())) {
                    result = true;
                }
            }
            if (result) {
                if (dati.isDomicilioPiemonte() && dati.isResidenzaItalia()) {
                    // eta non coerente
                    if (!dati.isEtaCoerente() || !isTutore && dati.isMaggiorenne()) {
                        // tutelato ma maggiorenne
                        result = false;
                    }
                } else {
                    // domicilio non Piemonte o residenza non Italia
                    result = false;
                }
            }
        }

        return result;
    }

    /**
     * Controlli possibile stampa.
     *
     * @param idSilLav the id sil lav
     * @param dati     the dati
     * @param isTutore the is tutore
     * @return the boolean
     */
    private Boolean controlliPossibileStampa(Long idSilLav, ControlliIscrizione dati, boolean isTutore) {
        Boolean result = false;

        if (dati.getAdesione() != null) {

            result = true;
            // adesione non in piemonte
            if (dati.isAdesioneFuoriRegione()) {
                result = false;
            }
            // non stampabile se rifiutata
            if ("S".equalsIgnoreCase(dati.getAdesione().getFlgRifiutoStatoCorrente())) {
                result = false;
            }
            // non stampabile se non inviata ad anpal

            if (!"S".equalsIgnoreCase(dati.getAdesione().getFlgAnpalStatoCorrente())) {
                result = false;
            }
        }

        return result;
    }

    /**
     * Controlli possibile appuntamento.
     *
     * @param idSilLav the id sil lav
     * @param dati     the dati
     * @param isTutore the is tutore
     * @return the boolean
     */
    private Boolean controlliPossibileAppuntamento(Long idSilLav, ControlliIscrizione dati, boolean isTutore) {
        Boolean result = false;

        if (dati.getAdesione() != null) {

            result = true;
            // adesione non in piemonte
            if (dati.isAdesioneFuoriRegione()) {
                result = false;
            }
            // non se rifiutata
            if ("S".equalsIgnoreCase(dati.getAdesione().getFlgRifiutoStatoCorrente())) {
                result = false;
            }
            if (result && dati.getAppuntamento() != null && ("DE".equalsIgnoreCase(dati.getAppuntamento().getCodiceAnpalStatoIncontro())
                    || "ER".equalsIgnoreCase(dati.getAppuntamento().getCodiceAnpalStatoIncontro()))) {
                result = false;

            }

        }
        return result;
    }

    /**
     * Controlli possibile annullamento.
     *
     * @param idSilLav the id sil lav
     * @param dati     the dati
     * @param isTutore the is tutore
     * @return the boolean
     */
    private Boolean controlliPossibileAnnullamento(Long idSilLav, ControlliIscrizione dati, boolean isTutore) {
        Boolean result = false;
        if (idSilLav == null) {
            // non esiste su Silp
            result = false;
        } else {
            if (dati.getAdesione() == null) {
                // adesione non presente
                result = false;
            } else {
                // adesione in piemonte con stato A
                // (sia inviato o non inviato ad anpal quindi non necessita controllo su invio)
                if (!dati.isAdesioneFuoriRegione() && "A".equalsIgnoreCase(dati.getAdesione().getCodice())) {
                    result = true;
                }
                // non annullabile se rifiutata
                if ("S".equalsIgnoreCase(dati.getAdesione().getFlgRifiutoStatoCorrente())) {
                    result = false;
                }

            }

        }

        return result;
    }

    @Override
    public Response findDocumentiRichiestaIscrizione(Long idUtente, Long idRichiesta, String codAmbito, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[UtentiApiServiceImpl::findDocumentiRichiestaIscrizione] idUtente=" + idUtente + COD_AMBITO + codAmbito + ", idRichiesta =" + idRichiesta);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.FIND_DOCUMENTI, idUtente);
            DocumentoFilter filter = new DocumentoFilter();
            filter.getUtente().getIdUtente().eq(idUtente);
            filter.getCodAmbito().eq(codAmbito);
            filter.getIdSilpRichCollMir().eq(idRichiesta);
            List<Documento> listaDiTuttiIDocumentiDelloUtente = new DocumentoMapper().mapListNoPdf(dao.findAll(DocumentoDBDef.class, filter, 0));
            return Response.ok(listaDiTuttiIDocumentiDelloUtente).build();
        } catch (Exception ex) {
            log.error(
                    "[UtentiApiServiceImpl::findDocumentiRichiestaIscrizione] idUtente=" + idUtente + COD_AMBITO + codAmbito + ", idRichiesta =" + idRichiesta,
                    ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.FIND_DOCUMENTI, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), codAmbito);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(UtentiApiServiceImpl.class.getName(), "findDocumentiRichiestaIscrizione()",
                    "invocazione API UtentiApiServiceImpl.findDocumentiRichiestaIscrizione", "");
            watcher.stop();
        }
    }

}
