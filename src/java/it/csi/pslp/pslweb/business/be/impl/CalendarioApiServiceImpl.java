/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslcommonobj.dbdef.CalendarioDBDef;
import it.csi.pslp.pslcommonobj.dbdef.MessaggioCalendarioDBDef;
import it.csi.pslp.pslcommonobj.dbdef.PeriodoDBDef;
import it.csi.pslp.pslcommonobj.dbdef.PrenotazioneDBDef;
import it.csi.pslp.pslcommonobj.dbdef.SlotDBDef;
import it.csi.pslp.pslcommonobj.dbdef.UtenteDBDef;
import it.csi.pslp.pslcommonobj.dbdef.UtenteEnteDBDef;
import it.csi.pslp.pslcommonobj.dto.CalendarioDTO;
import it.csi.pslp.pslcommonobj.dto.EsitoDTO;
import it.csi.pslp.pslcommonobj.dto.EsitoSalvataggioIncontroDTO;
import it.csi.pslp.pslcommonobj.dto.MessaggioCalendarioDTO;
import it.csi.pslp.pslcommonobj.dto.ParametriRicercaCalendarioDTO;
import it.csi.pslp.pslcommonobj.dto.ParametriRicercaSlotDTO;
import it.csi.pslp.pslcommonobj.dto.ParametriSalvataggioIncontroDTO;
import it.csi.pslp.pslcommonobj.dto.PeriodoDTO;
import it.csi.pslp.pslcommonobj.dto.PrenotazioneDTO;
import it.csi.pslp.pslcommonobj.dto.SlotDTO;
import it.csi.pslp.pslcommonobj.dto.SlotHeaderDTO;
import it.csi.pslp.pslcommonobj.dto.SlotHeadersDTO;
import it.csi.pslp.pslcommonobj.dto.UtenteDTO;
import it.csi.pslp.pslcommonobj.dto.UtenteEnteDTO;
import it.csi.pslp.pslcommonobj.filter.CalendarioFilter;
import it.csi.pslp.pslcommonobj.filter.MessaggioCalendarioFilter;
import it.csi.pslp.pslcommonobj.filter.PeriodoFilter;
import it.csi.pslp.pslcommonobj.filter.PrenotazioneFilter;
import it.csi.pslp.pslcommonobj.filter.SlotFilter;
import it.csi.pslp.pslcommonobj.filter.UtenteEnteFilter;
import it.csi.pslp.pslcommonobj.filter.UtenteFilter;
import it.csi.pslp.pslweb.business.be.CalendarioApi;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvinWSImpl;
import it.csi.pslp.pslweb.business.integration.PslOrchApiClient;
import it.csi.pslp.pslweb.dto.be.Calendario;
import it.csi.pslp.pslweb.dto.be.Ente;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.EsitoSalvataggioIncontro;
import it.csi.pslp.pslweb.dto.be.ParametriRicercaDisponibilitaIncontri;
import it.csi.pslp.pslweb.dto.be.ParametriRicercaPrimaDisponibilitaIncontri;
import it.csi.pslp.pslweb.dto.be.ParametriSalvataggioIncontro;
import it.csi.pslp.pslweb.dto.be.PrenotazioneIncontro;
import it.csi.pslp.pslweb.dto.be.SlotIncontro;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.RestHelper;
import it.csi.silos.jedi.core.DAO;
import it.csi.silos.silcommon.util.SilTimeUtils;
import it.csi.util.performance.StopWatch;

// TODO: Auto-generated Javadoc
/**
 * The Class CalendarioApiServiceImpl.
 */
@Component("calendarioApi")
public class CalendarioApiServiceImpl implements CalendarioApi {

    /** The Constant ERRORE_DI_SISTEMA. */
    private static final String ERRORE_DI_SISTEMA = "Errore di sistema: ";

    /** The Constant ERRORE_RESTITUITO_DA_PSLORCH. */
    private static final String ERRORE_RESTITUITO_DA_PSLORCH = "Errore restituito da pslorch: ";

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
     * Find slots.
     *
     * @param parametriRicercaDisponibilitaIncontri the parametri ricerca
     *                                              disponibilita incontri
     * @param securityContext                       the security context
     * @param httpHeaders                           the http headers
     * @param httpRequest                           the http request
     * @return the response
     */
    @Override
    public Response findSlots(ParametriRicercaDisponibilitaIncontri parametriRicercaDisponibilitaIncontri, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[CalendarioApiServiceImpl::findSlots] parametriRicercaDisponibilitaIncontri=" + parametriRicercaDisponibilitaIncontri);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        String codiceAmbito = parametriRicercaDisponibilitaIncontri.getCodAmbito();
        try {
            PslOrchApiClient client = new PslOrchApiClient();
            ParametriRicercaSlotDTO parametriRicercaSlotDTO = new ParametriRicercaSlotDTO();
            parametriRicercaSlotDTO.setDaGiorno(parametriRicercaDisponibilitaIncontri.getDataDa());
            parametriRicercaSlotDTO.setAGiorno(parametriRicercaDisponibilitaIncontri.getDataA());
            ParametriRicercaCalendarioDTO parametriRicercaCalendarioDTO = new ParametriRicercaCalendarioDTO();
            parametriRicercaCalendarioDTO.setCodAmbito(codiceAmbito);
            parametriRicercaCalendarioDTO.setCodOperatore(parametriRicercaDisponibilitaIncontri.getCodOperaratore());
            parametriRicercaCalendarioDTO.setGruppoOperatore(parametriRicercaDisponibilitaIncontri.getGruppoOperatore());
            parametriRicercaCalendarioDTO.setSubcodice(parametriRicercaDisponibilitaIncontri.getSubcodice());
            parametriRicercaSlotDTO.setParametriRicercaCalendarioDTO(parametriRicercaCalendarioDTO);
            SlotHeadersDTO slotsDTO = client.findSlot(parametriRicercaSlotDTO);
            if (slotsDTO == null || slotsDTO.getEls() == null || slotsDTO.getEls().isEmpty()) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.FIND_SLOTS, httpRequest, null,
                        "Slot non trovati per parametri=" + parametriRicercaDisponibilitaIncontri, codiceAmbito);
                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage(messaggiUtils.loadMessaggioErrore(MessaggiUtils.ME030).getTesto());
                log.debug("[CalendarioApiServiceImpl::findSlots] esito=404-" + err.getErrorMessage());
                return Response.serverError().entity(err).status(404).build();
            }
            List<SlotIncontro> slots = new ArrayList<>();
            for (SlotHeaderDTO slotDTO : slotsDTO.getEls()) {
                SlotIncontro slot = new SlotIncontro();
                slot.setIdSlot(slotDTO.getIdSlot());
                slot.setGiorno(slotDTO.getGiorno());
                slot.setDaOra(slotDTO.getDescrizioneOraInizio());
                slot.setAOra(slotDTO.getDescrizioneOraFine());
                slot.setDisponibilita(slotDTO.getDisponibilita());
                slots.add(slot);
            }
            log.debug("[CalendarioApiServiceImpl::findSlots] slots=" + slots);
            return Response.ok(slots).build();
        } catch (Exception ex) {
            log.error("[CalendarioApiServiceImpl::findSlots] parametriRicercaDisponibilitaIncontri=" + parametriRicercaDisponibilitaIncontri, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.FIND_SLOTS, httpRequest, null,
                    ERRORE_DI_SISTEMA + ex.getClass().getName() + "; Parametri=" + parametriRicercaDisponibilitaIncontri, codiceAmbito);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(CalendarioApiServiceImpl.class.getName(), "findSlots()", "invocazione API CalendarioApiServiceImpl.findSlots", "");
            watcher.stop();
        }
    }

    /**
     * Save incontro.
     *
     * @param parametriSalvataggioIncontro the parametri salvataggio incontro
     * @param securityContext              the security context
     * @param httpHeaders                  the http headers
     * @param httpRequest                  the http request
     * @return the response
     */
    @Override
    public Response saveIncontro(ParametriSalvataggioIncontro parametriSalvataggioIncontro, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[CalendarioApiServiceImpl::saveIncontro] parametriSalvataggioIncontro=" + parametriSalvataggioIncontro);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {

            // Verifica che non sia gia' presente un altro incontro in stato ER per l'ambito
            if (parametriSalvataggioIncontro.getIdPrenotazione() == null && parametriSalvataggioIncontro.getIdPrenotazioneOld() == null) {
                SlotFilter slotFilter = new SlotFilter();
                slotFilter.getIdSlot().eq(parametriSalvataggioIncontro.getIdSlot());
                SlotDTO slotDTO = dao.findFirst(SlotDBDef.class, slotFilter);
                String codAmbito = slotDTO.getGiorno().getPeriodo().getCalendario().getAmbito().getCodAmbito();
                PrenotazioneFilter prenotazioneFilter = new PrenotazioneFilter();
                prenotazioneFilter.getUtente().getIdUtente().eq(parametriSalvataggioIncontro.getIdUtente());
                prenotazioneFilter.getIdStatoAppuntamento().eq("DE");
                prenotazioneFilter.getSlot().getGiorno().getPeriodo().getCalendario().getAmbito().getCodAmbito().eq(codAmbito);
                PrenotazioneDTO prenotazioneDaErogareDTO = dao.findFirst(PrenotazioneDBDef.class, prenotazioneFilter);
                if (prenotazioneDaErogareDTO != null) {
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    throw new BusinessException(MessaggiUtils.ME077, df.format(prenotazioneDaErogareDTO.getSlot().getGiorno().getGiorno()),
                            prenotazioneDaErogareDTO.getSlot().getDescrizioneOraInizio());
                }
            }

            PslOrchApiClient client = new PslOrchApiClient();
            ParametriSalvataggioIncontroDTO parametriSalvataggioIncontroDTO = new ParametriSalvataggioIncontroDTO();
            parametriSalvataggioIncontroDTO.setIdPrenotazione(parametriSalvataggioIncontro.getIdPrenotazione());
            parametriSalvataggioIncontroDTO.setIdPrenotazioneOld(parametriSalvataggioIncontro.getIdPrenotazioneOld());
            parametriSalvataggioIncontroDTO.setIdSlot(parametriSalvataggioIncontro.getIdSlot());
            parametriSalvataggioIncontroDTO.setIdStatoAppuntamento(parametriSalvataggioIncontro.getIdStatoAppuntamento());
            parametriSalvataggioIncontroDTO.setIdUtente(parametriSalvataggioIncontro.getIdUtente());
            parametriSalvataggioIncontroDTO.setNote(parametriSalvataggioIncontro.getNote());
            parametriSalvataggioIncontroDTO.setIdAdesione(parametriSalvataggioIncontro.getIdSilRifAmbito());
            parametriSalvataggioIncontroDTO.setCodiceFiscaleUtenteCollegato(restHelper.getCodiceFiscaleCurrentUser(httpRequest));
            EsitoSalvataggioIncontroDTO esitoDTO = client.saveIncontro(parametriSalvataggioIncontroDTO);
            EsitoSalvataggioIncontro esito = new EsitoSalvataggioIncontro();
            if (esitoDTO.isEsitoPositivo()) {
                PrenotazioneDTO prenotazioneDTO = esitoDTO.getPrenotazione();
                PrenotazioneIncontro prenotazione = new PrenotazioneIncontro();
                esito.setPrenotazioneIncontro(prenotazione);
                prenotazione.setIdPrenotazione(prenotazioneDTO.getIdPrenotazione());
                SlotDTO slotDTO = prenotazioneDTO.getSlot();
                SlotIncontro slot = new SlotIncontro();
                slot.setIdSlot(slotDTO.getIdSlot());
                slot.setGiorno(SilTimeUtils.convertDataInStringa(slotDTO.getGiorno().getGiorno()));
                slot.setDaOra(slotDTO.getDescrizioneOraInizio());
                slot.setAOra(slotDTO.getDescrizioneOraFine());
                slot.setDisponibilita(slotDTO.getNumMaxPrenotazioni() - slotDTO.getNumPrenotazioniValide());
                prenotazione.setSlot(slot);
                log.debug("[CalendarioApiServiceImpl::saveIncontro] esito=" + esito);
                tracciamentoUtils.tracciaOk(TracciamentoUtils.SAVE_INCONTRO, httpRequest, parametriSalvataggioIncontro.getIdUtente(),
                        "Prenotazione: " + prenotazione.getIdPrenotazione(), null);
                return Response.ok(esito).build();
            } else {
                if (EsitoDTO.E0101_EROGAZIONE_IN_DATA_FUTURA.equals(esitoDTO.getCodiceEsito())) {
                    log.debug("[CalendarioApiServiceImpl::saveIncontro] esito=402-" + esitoDTO.getDescrizioneEsito());
                    tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_INCONTRO, httpRequest, parametriSalvataggioIncontro.getIdUtente(),
                            ERRORE_RESTITUITO_DA_PSLORCH + esitoDTO.getDescrizioneEsito(), null);
                    return createErrorResponse("402", MessaggiUtils.ME014);
                } else if (EsitoDTO.E0102_MODIFICA_INCONTRO_IN_STATO_FINALE.equals(esitoDTO.getCodiceEsito())) {
                    log.debug("[CalendarioApiServiceImpl::saveIncontro] esito=403-" + esitoDTO.getDescrizioneEsito());
                    tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_INCONTRO, httpRequest, parametriSalvataggioIncontro.getIdUtente(),
                            ERRORE_RESTITUITO_DA_PSLORCH + esitoDTO.getDescrizioneEsito(), null);
                    return createErrorResponse("403", MessaggiUtils.ME022);
                } else { // Errore da pslorch non mappato su un messaggio utente
                    log.debug("[CalendarioApiServiceImpl::saveIncontro] esito=452-[" + esitoDTO.getCodiceEsito() + "] " + esitoDTO.getDescrizioneEsito());
                    tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_INCONTRO, httpRequest, parametriSalvataggioIncontro.getIdUtente(),
                            ERRORE_RESTITUITO_DA_PSLORCH + esitoDTO.getDescrizioneEsito(), null);
                    return Response.serverError().entity(esitoDTO.getDescrizioneEsito()).status(452).build();
                }
            }
        } catch (BusinessException ex) {
            return createErrorResponse("452", ex.getCode(), ex.getArgs());
        } catch (Exception ex) {
            log.error("[CalendarioApiServiceImpl::saveIncontro] parametriSalvataggioIncontro=" + parametriSalvataggioIncontro, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_INCONTRO, httpRequest, parametriSalvataggioIncontro.getIdUtente(),
                    ERRORE_DI_SISTEMA + ex.getClass().getName() + "; parametri=" + parametriSalvataggioIncontro, null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(CalendarioApiServiceImpl.class.getName(), "saveIncontro()", "invocazione API CalendarioApiServiceImpl.saveIncontro", "");
            watcher.stop();
        }
    }

    /**
     * Find intervallo disponibile.
     *
     * @param parametriRicercaDisponibilitaIncontri the parametri ricerca
     *                                              disponibilita incontri
     * @param securityContext                       the security context
     * @param httpHeaders                           the http headers
     * @param httpRequest                           the http request
     * @return the response
     */
    @Override
    public Response findIntervalloDisponibile(ParametriRicercaPrimaDisponibilitaIncontri parametriRicercaDisponibilitaIncontri, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[CalendarioApiServiceImpl::findIntervalloDisponibile] parametriRicercaDisponibilitaIncontri=" + parametriRicercaDisponibilitaIncontri);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            PslOrchApiClient client = new PslOrchApiClient();
            ParametriRicercaCalendarioDTO parametriRicercaCalendarioDTO = new ParametriRicercaCalendarioDTO();
            parametriRicercaCalendarioDTO.setCodAmbito(parametriRicercaDisponibilitaIncontri.getCodAmbito());
            parametriRicercaCalendarioDTO.setCodOperatore(parametriRicercaDisponibilitaIncontri.getCodOperaratore());
            parametriRicercaCalendarioDTO.setGruppoOperatore(parametriRicercaDisponibilitaIncontri.getGruppoOperatore());
            parametriRicercaCalendarioDTO.setSubcodice(parametriRicercaDisponibilitaIncontri.getSubcodice());
            String[] giorni = client.findIntervalloDisponibile(parametriRicercaCalendarioDTO);
            if (giorni == null || giorni.length == 0 || giorni[0] == null || giorni[0].length() == 0) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.PRIMO_GIORNO_DISPONIBILE, httpRequest, null,
                        "Nessun giorno disponibile per " + parametriRicercaDisponibilitaIncontri, parametriRicercaDisponibilitaIncontri.getCodAmbito());
                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage("Nessun giorno disponibile");
                log.debug("[CalendarioApiServiceImpl::findIntervalloDisponibile] esito=404-" + err.getErrorMessage());
                return Response.serverError().entity(err).status(404).build();
            }
            Date primoGiorno = SilTimeUtils.convertStringaInData(giorni[0]);
            Date ultimoGiorno = SilTimeUtils.convertStringaInData(giorni[1]);
            Date[] result = { primoGiorno, ultimoGiorno };
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[CalendarioApiServiceImpl::findIntervalloDisponibile] parametriRicercaDisponibilitaIncontri=" + parametriRicercaDisponibilitaIncontri,
                    ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.PRIMO_GIORNO_DISPONIBILE, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    parametriRicercaDisponibilitaIncontri.getCodAmbito());
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(CalendarioApiServiceImpl.class.getName(), "findIntervalloDisponibile()",
                    "invocazione API CalendarioApiServiceImpl.findIntervalloDisponibile", "");
            watcher.stop();
        }
    }

    /**
     * Find sportelli.
     *
     * @param codAmbito                         the cod ambito
     * @param codTipoUtente                     the cod tipo utente
     * @param codiceMinisterialeComuneDomicilio the codice ministeriale comune
     *                                          domicilio
     * @param codiceMinisterialeComuneResidenza the codice ministeriale comune
     *                                          residenza
     * @param securityContext                   the security context
     * @param httpHeaders                       the http headers
     * @param httpRequest                       the http request
     * @return the response
     */
    @Override
    public Response findSportelli(String codAmbito, String codTipoUtente, String codiceMinisterialeComuneDomicilio, String codiceMinisterialeComuneResidenza,
            SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info(
                "[CalendarioApiServiceImpl::findSportelli] codAmbito=" + codAmbito + ", codTipoUtente=" + codTipoUtente + ", codiceMinisterialeComuneDomicilio="
                        + codiceMinisterialeComuneDomicilio + ",codiceMinisterialeComuneResidenza=" + codiceMinisterialeComuneResidenza);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            if (codiceMinisterialeComuneDomicilio == null)
                codiceMinisterialeComuneDomicilio = codiceMinisterialeComuneResidenza;
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            List<Ente> enti = AdapterSilpsvinWSImpl.getInstance().findElencoEnti(codiceFiscaleUtente, null);
            List<Ente> entiResidenza = new ArrayList<>();
            List<Ente> entiDomicilio = new ArrayList<>();
            if (codiceMinisterialeComuneResidenza != null) {
                entiResidenza = AdapterSilpsvinWSImpl.getInstance().findElencoEnti(codiceFiscaleUtente, codiceMinisterialeComuneResidenza);
            }
            if (codiceMinisterialeComuneDomicilio != null) {
                entiDomicilio = AdapterSilpsvinWSImpl.getInstance().findElencoEnti(codiceFiscaleUtente, codiceMinisterialeComuneDomicilio);
            }
            Map<String, Ente> mapEnti = new HashMap<>();
            Set<String> setEntiResidenza = new HashSet<>();
            Set<String> setEntiDomicilio = new HashSet<>();
            Set<String> setEntiOperatoreCpi = new HashSet<>();
            for (Ente ente : enti) {
                String keyEnte = ente.getGruppoOperatore() + "-" + ente.getCodOperatore() + "-" + ente.getSubcodice();
                mapEnti.put(keyEnte, ente);
            }
            for (Ente ente : entiResidenza) {
                String keyEnte = ente.getGruppoOperatore() + "-" + ente.getCodOperatore() + "-" + ente.getSubcodice();
                setEntiResidenza.add(keyEnte);
            }
            for (Ente ente : entiDomicilio) {
                String keyEnte = ente.getGruppoOperatore() + "-" + ente.getCodOperatore() + "-" + ente.getSubcodice();
                setEntiDomicilio.add(keyEnte);
            }
            log.debug("[CalendarioApiServiceImpl::findSportelli] setEntiResidenza: " + setEntiResidenza);
            log.debug("[CalendarioApiServiceImpl::findSportelli] setEntiDomicilio: " + setEntiDomicilio);
            if ("CPI".equals(codTipoUtente)) {
                Long idUtente = getIdUtente(codiceFiscaleUtente, codTipoUtente);
                if (idUtente == null) {
                    log.info("[CalendarioApiServiceImpl::findSportelli] Operatore CPI non reperito per CF codiceFiscaleUtente " + codiceFiscaleUtente
                            + ": non carico gli sportelli");
                } else {
                    UtenteEnteFilter filterUtentiEnti = new UtenteEnteFilter();
                    filterUtentiEnti.getIdUtente().eq(idUtente);
                    for (UtenteEnteDTO utenteEnte : dao.findAll(UtenteEnteDBDef.class, filterUtentiEnti, 0)) {
                        String keyEnte = utenteEnte.getGruppoOperatore() + "-" + utenteEnte.getCodOperatore() + "-" + utenteEnte.getSubcodice();
                        setEntiOperatoreCpi.add(keyEnte);
                    }
                }
                log.info("[CalendarioApiServiceImpl::findSportelli] Sportelli operatore: " + setEntiOperatoreCpi);
            }

            // Carica tutti i calendari che hanno almeno un periodo impostato
            PeriodoFilter periodoFilter = new PeriodoFilter();
            periodoFilter.getCalendario().getAmbito().getCodAmbito().eq(codAmbito);
            periodoFilter.getCalendario().getDAnnullamento().eq(null);
            Set<Long> setCalendariConPeriodiAssociati = new HashSet<>();
            for (PeriodoDTO periodo : dao.findAll(PeriodoDBDef.class, periodoFilter, 0)) {
                setCalendariConPeriodiAssociati.add(periodo.getCalendario().getIdCalendario());
            }

            // Seleziona gli enti
            CalendarioFilter filter = new CalendarioFilter();
            filter.getAmbito().getCodAmbito().eq(codAmbito);
            filter.getDAnnullamento().eq(null);
            filter.getFlgBloccato().eq(null);
            Set<String> setEnti = new LinkedHashSet<>();
            for (CalendarioDTO calendario : dao.findAll(CalendarioDBDef.class, filter, 0)) {
                if (!setCalendariConPeriodiAssociati.contains(calendario.getIdCalendario())) {
                    log.debug("[CalendarioApiServiceImpl::findSportelli] Escludo calendario " + calendario.getIdCalendario()
                            + " perche' non ha periodi associati");
                    continue;
                }
                String key = calendario.getGruppoOperatore() + "-" + calendario.getCodOperatore() + "-" + calendario.getSubcodice();
                Ente ente = mapEnti.get(key);
                log.debug("[CalendarioApiServiceImpl::findSportelli] Verifico Calendario " + calendario.getIdCalendario());
                if (calendario.getFlgVisibileCpi() == null) {
                    if ("GG".equals(codAmbito))
                        calendario.setFlgVisibileCpi("D");
                    else
                        calendario.setFlgVisibileCpi("R");
                }
                if (ente == null) {
                    log.debug("[CalendarioApiServiceImpl::findSportelli] Escludo calendario " + calendario.getIdCalendario() + " perche' ente con chiave " + key
                            + " e' null");
                    continue;
                }
                if (ente != null) {
                    switch (calendario.getFlgVisibileCpi()) {
                    case "D":
                        if (setEntiDomicilio.contains(key))
                            setEnti.add(key);
                        break;
                    case "R":
                        if (setEntiResidenza.contains(key))
                            setEnti.add(key);
                        break;
                    case "T":
                        if (setEntiDomicilio.contains(key) || setEntiResidenza.contains(key))
                            setEnti.add(key);
                        break;
                    default:
                        break;
                    }
                }
            }
            List<Ente> result = new ArrayList<>();
            for (String key : setEnti) {
                Ente ente = mapEnti.get(key);
                if (ente.getSubcodice() == null) {
                    log.debug("[CalendarioApiServiceImpl::findSportelli] Escludo sportello " + key + " perche' ha subcodice a null");
                    continue;
                }
                if ("CPI".equals(codTipoUtente)) {
                    if (!setEntiOperatoreCpi.contains(key)) {
                        log.debug("[CalendarioApiServiceImpl::findSportelli] Escludo sportello " + key
                                + " perche' l'utente opera come operatore CPI e non e' abilitato per questo sportello");
                        continue;
                    }
                }
                result.add(ente);
            }
            log.debug("[CalendarioApiServiceImpl::findSportelli] result=" + result);
            if (result.isEmpty()) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.FIND_SPORTELLI, httpRequest, null, "Nessuno sportello reperito per codAmbito " + codAmbito
                        + ", domiciclio=" + codiceMinisterialeComuneDomicilio + ", residenza=" + codiceMinisterialeComuneResidenza, codAmbito);
            }
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[CalendarioApiServiceImpl::findSportelli] codAmbito=" + codAmbito + ", codiceMinisterialeComuneDomicilio="
                    + codiceMinisterialeComuneDomicilio + ",codiceMinisterialeComuneResidenza=" + codiceMinisterialeComuneResidenza, ex);
            tracciamentoUtils
                    .tracciaKo(
                            TracciamentoUtils.FIND_SPORTELLI, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName() + "; parametri codAmbito"
                                    + codAmbito + ", domiciclio=" + codiceMinisterialeComuneDomicilio + ", residenza=" + codiceMinisterialeComuneResidenza,
                            codAmbito);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(CalendarioApiServiceImpl.class.getName(), "findSportelli()", "invocazione API CalendarioApiServiceImpl.findSportelli", "");
            watcher.stop();
        }
    }

    /**
     * Find calendario.
     *
     * @param parametriRicercaDisponibilitaIncontri the parametri ricerca
     *                                              disponibilita incontri
     * @param securityContext                       the security context
     * @param httpHeaders                           the http headers
     * @param httpRequest                           the http request
     * @return the response
     */
    @Override
    public Response findCalendario(ParametriRicercaPrimaDisponibilitaIncontri parametriRicercaDisponibilitaIncontri, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[CalendarioApiServiceImpl::findCalendario] parametriRicercaDisponibilitaIncontri=" + parametriRicercaDisponibilitaIncontri);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            CalendarioFilter calendarioFilter = new CalendarioFilter();
            calendarioFilter.getAmbito().getCodAmbito().eq(parametriRicercaDisponibilitaIncontri.getCodAmbito());
            calendarioFilter.getGruppoOperatore().eq(parametriRicercaDisponibilitaIncontri.getGruppoOperatore());
            calendarioFilter.getDAnnullamento().eq(null);
            if (parametriRicercaDisponibilitaIncontri.getCodOperaratore() != null) {
                calendarioFilter.getCodOperatore().eq(Long.valueOf(parametriRicercaDisponibilitaIncontri.getCodOperaratore().longValue()));
            }
            if (parametriRicercaDisponibilitaIncontri.getCodOperaratore() != null) {
                calendarioFilter.getSubcodice().eq(Long.valueOf(parametriRicercaDisponibilitaIncontri.getSubcodice().longValue()));
            }
            CalendarioDTO calDTO = dao.findFirst(CalendarioDBDef.class, calendarioFilter);
            if (calDTO == null) {
                // Not found
                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage("Nessun calendario disponibile");
                log.debug("[CalendarioApiServiceImpl::findCalendario] esito=404-" + err.getErrorMessage());
                return Response.serverError().entity(err).status(404).build();
            }
            Calendario cal = new Calendario();
            cal.setInvioConfermaPrenotazione(calDTO.getFlgInvioConfermaPrenotaz());
            cal.setCodiceAmbito(calDTO.getAmbito().getCodAmbito());
            cal.setCodiceOperatore(calDTO.getCodOperatore());
            cal.setIdCalendario(calDTO.getIdCalendario());
            cal.setFlagAnnullaGaranziaGiovani(calDTO.getFlgAnnullaGaranziaGiovani());
            cal.setSubcodice(calDTO.getSubcodice());
            cal.setOreInvioPromemoria(calDTO.getOreInvioPromemoria());
            cal.setOreLimiteSpostamento(calDTO.getOreLimiteSpostamento());
            cal.setFlagVisibileCpi(calDTO.getFlgVisibileCpi());
            cal.setGruppoOperatore(calDTO.getGruppoOperatore());
            cal.setNome(calDTO.getNome());
            cal.setFlagBloccato(calDTO.getFlgBloccato());
            MessaggioCalendarioFilter messaggioCalendarioFilter = new MessaggioCalendarioFilter();
            messaggioCalendarioFilter.getIdCalendario().eq(calDTO.getIdCalendario());
            messaggioCalendarioFilter.getCodTipoMessaggioCalendario()
                    .in(new String[] { MessaggioCalendarioDTO.SPOSTAMENTO_APPUNTAMENTO, MessaggioCalendarioDTO.ANNULLAMENTO_APPUNTAMENTO });
            for (MessaggioCalendarioDTO msgCalDTO : dao.findAll(MessaggioCalendarioDBDef.class, messaggioCalendarioFilter, 0)) {
                switch (msgCalDTO.getCodTipoMessaggioCalendario()) {
                case MessaggioCalendarioDTO.SPOSTAMENTO_APPUNTAMENTO:
                    cal.setMessaggioSpostamento(msgCalDTO.getTesto());
                    break;
                case MessaggioCalendarioDTO.ANNULLAMENTO_APPUNTAMENTO:
                    cal.setMessaggioAnnullamento(msgCalDTO.getTesto());
                    break;
                default:
                    break;
                }
            }
            log.debug("[CalendarioApiServiceImpl::findCalendario] calendario=" + cal);
            return Response.ok(cal).build();
        } catch (Exception ex) {
            log.error("[CalendarioApiServiceImpl::findCalendario] parametriRicercaDisponibilitaIncontri=" + parametriRicercaDisponibilitaIncontri, ex);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(CalendarioApiServiceImpl.class.getName(), "findCalendario()", "invocazione API CalendarioApiServiceImpl.findCalendario", "");
            watcher.stop();
        }
    }

    /**
     * Gets the id utente.
     *
     * @param codiceFiscale the codice fiscale
     * @param codTipoUtente the cod tipo utente
     * @return the id utente
     * @throws Exception the exception
     */
    private Long getIdUtente(String codiceFiscale, String codTipoUtente) throws Exception {
        UtenteFilter filter = new UtenteFilter();
        filter.getCfUtente().eq(codiceFiscale);
        filter.getCodTipoUtente().eq(codTipoUtente);
        UtenteDTO utente = dao.findFirst(UtenteDBDef.class, filter);
        if (utente != null)
            return utente.getIdUtente();
        return null;
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
        return Response.serverError().entity(err).build();
    }

    /**
     * Creates the error response.
     *
     * @param code        the code
     * @param messageCode the message code
     * @param args        the args
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
}
