/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslcommonobj.dbdef.CalendarioDBDef;
import it.csi.pslp.pslcommonobj.dbdef.PrenotazioneDBDef;
import it.csi.pslp.pslcommonobj.dbdef.UtenteDBDef;
import it.csi.pslp.pslcommonobj.dto.AmbitoDTO;
import it.csi.pslp.pslcommonobj.dto.CalendarioDTO;
import it.csi.pslp.pslcommonobj.dto.ParametroDTO;
import it.csi.pslp.pslcommonobj.dto.PrenotazioneDTO;
import it.csi.pslp.pslcommonobj.dto.UtenteDTO;
import it.csi.pslp.pslcommonobj.filter.CalendarioFilter;
import it.csi.pslp.pslcommonobj.filter.PrenotazioneFilter;
import it.csi.pslp.pslcommonobj.filter.UtenteFilter;
import it.csi.pslp.pslweb.business.be.StampeApi;
import it.csi.pslp.pslweb.business.integration.AdapterSilpgsp;
import it.csi.pslp.pslweb.business.integration.AdapterSilpserver;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvdeWSImpl;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvinWSImpl;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvspWSFactory;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvspWSImpl;
import it.csi.pslp.pslweb.dto.be.AdesioneYG;
import it.csi.pslp.pslweb.dto.be.DomandaRDC;
import it.csi.pslp.pslweb.dto.be.Ente;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.EsitoDettaglioDid;
import it.csi.pslp.pslweb.dto.be.Sportello;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.GsonUtils;
import it.csi.pslp.pslweb.util.ParametroUtils;
import it.csi.pslp.pslweb.util.RestHelper;
import it.csi.silos.jedi.core.DAO;
import it.csi.silos.silcommon.dati.anagrafe.DatiAnagraficiPersonaDTO;
import it.csi.silp.silpgsp.dto.GspDatiDocumentoBeansDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.riepilogo.EsitoCollocamentoMiratoRiepilogoDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.riepilogo.IscrizioneCollocamentoMiratoDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.stampe.IscrizioneCollocamentoMiratoReportDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.adesioneyg.AdesioneYouthGuaranteeReportDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.adesioneyg.PattoDiServizioReportDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.adesioneyg.RedditoDiCittadinanzaReportDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.adesioneyg.StampaAdesioneYouthGuaranteeSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.adesioneyg.StampaPattoDiServizioSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.adesioneyg.StampaRedditoDiCittadinanzaSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.sap.SchedaAnagraficoProfessionaleReportDTO;
import it.csi.silpcommonobj.dbdef.common.TemplateStampaSilpDTO;
import it.csi.silpcommonobj.util.SilpConvertUtils;
import it.csi.silpcommonobj.util.SilpTimeUtils;
import it.csi.silpsv.silpsvsp.cxfclient.CollocamentoMiratoRiepilogoSilpFilter;
import it.csi.silpsv.silpsvsp.cxfclient.WSContainerDTO;
import it.csi.util.performance.StopWatch;

/**
 * The Class StampeApiServiceImpl.
 */
@Component("stampeApi")
public class StampeApiServiceImpl implements StampeApi {

    /** The Constant ERRORE_DI_SISTEMA. */
    private static final String ERRORE_DI_SISTEMA = "Errore di sistema: ";

    /** The Constant DD_MM_YYYY. */
    private static final String DD_MM_YYYY = "dd/MM/yyyy";

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
     * Crea stampa adesione.
     *
     * @param idUtente         the id utente
     * @param idSilLavAdesione the id sil lav adesione
     * @param securityContext  the security context
     * @param httpHeaders      the http headers
     * @param httpRequest      the http request
     * @return the response
     */
    @Override
    public Response creaStampaAdesione(Long idUtente, Long idSilLavAdesione, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[StampeApiServiceImpl::creaStampaAdesione] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.STAMPA_ADESIONE, idUtente);
            UtenteFilter filter = new UtenteFilter();
            filter.getIdUtente().eq(idUtente);
            UtenteDTO utente = dao.findFirst(UtenteDBDef.class, filter);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            SchedaAnagraficoProfessionaleReportDTO sap = getReportSap(utente.getIdSilLavAngrafica());
            AdesioneYG adesione = AdapterSilpsvspWSImpl.getInstance().getAdesioneYG(utente.getIdSilLavAngrafica(), idSilLavAdesione);
            Map<String, String> statiAdesione = AdapterSilpsvdeWSImpl.getInstance().findMapStatiAdesione(codiceFiscaleUtente);
            Date dataAdesione = null;
            Date dataStatoAdesione = null;
            String statoAdesione = null;
            String testoStatoAdesione = null;
            String numeroAdesione = null;
            if (adesione != null) {
                dataAdesione = adesione.getDataAdesione();
                dataStatoAdesione = adesione.getDataStatoCorrente();
                statoAdesione = statiAdesione.get(adesione.getCodice());
                numeroAdesione = "" + adesione.getIdSilLavAdesione();
                SimpleDateFormat df = new SimpleDateFormat(DD_MM_YYYY);
                testoStatoAdesione = "Lo stato attuale dell'adesione e' " + statoAdesione + " a partire dal " + df.format(dataStatoAdesione) + ".";
            }
            StampaAdesioneYouthGuaranteeSilpDTO datiAdesioneDTO = new StampaAdesioneYouthGuaranteeSilpDTO();
            DatiAnagraficiPersonaDTO datiAnagrafici = sap.getSap().getDatiAnagrafici().getDatiPersonali();

            datiAdesioneDTO.setCodiceFiscale(datiAnagrafici.getCodiceFiscale());
            datiAdesioneDTO.setCognome(datiAnagrafici.getCognome());
            String luogoNascita = datiAnagrafici.getLuogoNascita().getComuneOrStato();
            if (datiAnagrafici.getLuogoNascita().getComune() != null && datiAnagrafici.getLuogoNascita().getComune().getProvincia() != null
                    && datiAnagrafici.getLuogoNascita().getComune().getProvincia().getTarga() != null && !datiAnagrafici.getLuogoNascita().getComune()
                            .getDescrizione().equals(datiAnagrafici.getLuogoNascita().getComune().getProvincia().getDescrizione())) {
                luogoNascita += " (" + datiAnagrafici.getLuogoNascita().getComune().getProvincia().getTarga() + ")";
            }
            datiAdesioneDTO.setComuneONazioneDiNascita(luogoNascita);
            datiAdesioneDTO.setDataAdesione(dataAdesione);
            datiAdesioneDTO.setDataDiNascita(datiAnagrafici.getDataNascita());
            datiAdesioneDTO.setDataStatoAdesione(dataStatoAdesione);
            if (numeroAdesione != null)
                datiAdesioneDTO.setTestoAndNumeroAdesione(", con il numero " + numeroAdesione + ".");
            else
                datiAdesioneDTO.setTestoAndNumeroAdesione(".");

            datiAdesioneDTO.setDescrizioneCpi(getDescrizioneCpi(idUtente, codiceFiscaleUtente, sap, AmbitoDTO.COD_AMBITO_GG_GARANZIA_GIOVANI));

            datiAdesioneDTO.setNome(datiAnagrafici.getNome());
            datiAdesioneDTO.setStatoAdesione(testoStatoAdesione);
            String testoPrivacy = parametroUtils.getParametro(ParametroUtils.STAMPA_PRIVACY);
            datiAdesioneDTO.setTestoPrivacy(testoPrivacy);
            datiAdesioneDTO.setTitolo(messaggiUtils.loadMessaggio(MessaggiUtils.ST001).getIntestazione());
            AdesioneYouthGuaranteeReportDTO stampaAdesioneReportDTO = new AdesioneYouthGuaranteeReportDTO();
            stampaAdesioneReportDTO.setAdesione(datiAdesioneDTO);
            byte[] pdf = AdapterSilpgsp.getInstance().creaDocumentoPDF(TemplateStampaSilpDTO.CODICE_MODELLO_STAMPA_ADESIONE_GG, stampaAdesioneReportDTO);
            return Response.ok(pdf).build();
        } catch (Exception ex) {
            log.error("[StampeApiServiceImpl::creaStampaAdesione] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.STAMPA_ADESIONE, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(StampeApiServiceImpl.class.getName(), "creaStampaAdesione()", "invocazione API UtentiApiServiceImpl.creaStampaAdesione", "");
            watcher.stop();
        }
    }

    /**
     * Gets the descrizione cpi.
     *
     * @param idUtente            the id utente
     * @param codiceFiscaleUtente the codice fiscale utente
     * @param sap                 the sap
     * @param ambito              the ambito
     * @return the descrizione cpi
     * @throws Exception the exception
     */
    private String getDescrizioneCpi(Long idUtente, String codiceFiscaleUtente, SchedaAnagraficoProfessionaleReportDTO sap, String ambito) throws Exception {
        String descrizioneCpi = "-";
        PrenotazioneFilter prenotazioneFilter = new PrenotazioneFilter();
        prenotazioneFilter.getUtente().getIdUtente().eq(idUtente);
        prenotazioneFilter.getIdStatoAppuntamento().in(new String[] { "ER" });
        prenotazioneFilter.getSlot().getGiorno().getGiorno().setOrderAsc(1);
        PrenotazioneDTO prenotazione = dao.findFirst(PrenotazioneDBDef.class, prenotazioneFilter);
        if (prenotazione != null) {
            CalendarioDTO calendarioDTO = prenotazione.getSlot().getGiorno().getPeriodo().getCalendario();
            String gruppoOperatore = calendarioDTO.getGruppoOperatore();
            Long codOperatore = calendarioDTO.getCodOperatore();
            Long subcodice = calendarioDTO.getSubcodice();
            Map<String, Sportello> sportelli = AdapterSilpsvinWSImpl.getInstance().findSportelliMap(codiceFiscaleUtente);
            descrizioneCpi = sportelli.get(gruppoOperatore + "-" + codOperatore + "-" + subcodice).getDescrizione();
            log.debug("[StampeApiServiceImpl::getDescrizioneCpi] descrizioneCpi reperita dall'ultima prenotazione: " + descrizioneCpi);
        } else {
            log.debug("[StampeApiServiceImpl::getDescrizioneCpi] Non ci sono prenotazioni in stato ER, reperisco il CPI dal domicilio");

            Ente ente = null;
            Ente enteDomicilio = null;
            Ente enteResidenza = null;
            if (sap.getSap().getDatiAnagrafici().getDomicilio() != null && sap.getSap().getDatiAnagrafici().getDomicilio().getComune() != null) {
                String codiceMinisterialeComuneDomicilio = sap.getSap().getDatiAnagrafici().getDomicilio().getComune().getCodiceMinisteriale();
                List<Ente> entiDomicilio = AdapterSilpsvinWSImpl.getInstance().findElencoEnti(codiceFiscaleUtente, codiceMinisterialeComuneDomicilio);
                if (entiDomicilio != null && !entiDomicilio.isEmpty())
                    enteDomicilio = entiDomicilio.get(0);
            }
            if (sap.getSap().getDatiAnagrafici().getResidenza() != null && sap.getSap().getDatiAnagrafici().getResidenza().getComune() != null) {
                String codiceMinisterialeComuneResidenza = sap.getSap().getDatiAnagrafici().getResidenza().getComune().getCodiceMinisteriale();
                List<Ente> entiResidenza = AdapterSilpsvinWSImpl.getInstance().findElencoEnti(codiceFiscaleUtente, codiceMinisterialeComuneResidenza);
                if (entiResidenza != null && !entiResidenza.isEmpty())
                    enteResidenza = entiResidenza.get(0);
            }

            if (enteDomicilio != null) {
                CalendarioFilter calendarioFilter = new CalendarioFilter();
                calendarioFilter.getAmbito().getCodAmbito().eq(ambito);
                calendarioFilter.getFlgVisibileCpi().eq("D");
                calendarioFilter.getGruppoOperatore().eq(enteDomicilio.getGruppoOperatore());
                calendarioFilter.getCodOperatore().eq(enteDomicilio.getCodOperatore());
                calendarioFilter.getSubcodice().eq(enteDomicilio.getSubcodice());
                CalendarioDTO calendario = dao.findFirst(CalendarioDBDef.class, calendarioFilter);
                if (calendario != null)
                    ente = enteDomicilio;
            }
            if (ente == null && enteResidenza != null) {
                CalendarioFilter calendarioFilter = new CalendarioFilter();
                calendarioFilter.getAmbito().getCodAmbito().eq(ambito);
                calendarioFilter.getFlgVisibileCpi().eq("R");
                calendarioFilter.getGruppoOperatore().eq(enteResidenza.getGruppoOperatore());
                calendarioFilter.getCodOperatore().eq(enteResidenza.getCodOperatore());
                calendarioFilter.getSubcodice().eq(enteResidenza.getSubcodice());
                CalendarioDTO calendario = dao.findFirst(CalendarioDBDef.class, calendarioFilter);
                if (calendario != null)
                    ente = enteResidenza;
            }

            if (ente == null) {
                if (ambito.equals(AmbitoDTO.COD_AMBITO_GG_GARANZIA_GIOVANI)) {
                    if (enteDomicilio != null)
                        ente = enteDomicilio;
                    else
                        ente = enteResidenza;
                } else {
                    if (enteResidenza != null)
                        ente = enteResidenza;
                    else
                        ente = enteDomicilio;
                }
            }

            if (ente != null) {
                descrizioneCpi = ente.getDescrizione();
            } else {
                log.debug("[StampeApiServiceImpl::getDescrizioneCpi] codiceMinisterialeComune non reperito");
            }
        }
        return descrizioneCpi;
    }

    /**
     * Crea stampa reddito di cittadinanza.
     *
     * @param idUtente        the id utente
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response creaStampaRedditoDiCittadinanza(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[StampeApiServiceImpl::creaStampaRedditoDiCittadinanza] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.STAMPA_ADESIONE, idUtente);
            UtenteFilter filter = new UtenteFilter();
            filter.getIdUtente().eq(idUtente);
            UtenteDTO utente = dao.findFirst(UtenteDBDef.class, filter);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            SchedaAnagraficoProfessionaleReportDTO sap = getReportSap(utente.getIdSilLavAngrafica());
            DatiAnagraficiPersonaDTO datiAnagrafici = sap.getSap().getDatiAnagrafici().getDatiPersonali();
            DomandaRDC domandaRdC = AdapterSilpsvspWSImpl.getInstance().getDomandaRDCBySILP(utente.getIdSilLavAngrafica());
            StampaRedditoDiCittadinanzaSilpDTO stampaDTO = new StampaRedditoDiCittadinanzaSilpDTO();
            stampaDTO.setCodiceFiscale(datiAnagrafici.getCodiceFiscale());
            stampaDTO.setCognome(datiAnagrafici.getCognome());
            stampaDTO.setNome(datiAnagrafici.getNome());
            String luogoNascita = datiAnagrafici.getLuogoNascita().getComuneOrStato();
            if (datiAnagrafici.getLuogoNascita().getComune() != null && datiAnagrafici.getLuogoNascita().getComune().getProvincia() != null
                    && datiAnagrafici.getLuogoNascita().getComune().getProvincia().getTarga() != null && !datiAnagrafici.getLuogoNascita().getComune()
                            .getDescrizione().equals(datiAnagrafici.getLuogoNascita().getComune().getProvincia().getDescrizione())) {
                luogoNascita += " (" + datiAnagrafici.getLuogoNascita().getComune().getProvincia().getTarga() + ")";
            }
            SimpleDateFormat df = new SimpleDateFormat(DD_MM_YYYY);
            String dataAndLuogoDiNascita = "nato/a il " + df.format(datiAnagrafici.getDataNascita()) + " a " + luogoNascita;
            stampaDTO.setDataAndLuogoDiNascita(dataAndLuogoDiNascita);
            stampaDTO.setDescrizioneCpi(getDescrizioneCpi(idUtente, codiceFiscaleUtente, sap, AmbitoDTO.COD_AMBITO_RDC_REDDITO_DI_CITTADINANZA));
            String testo = "ha presentato domanda di Reddito di Cittadinanza in data " + df.format(domandaRdC.getDataDomanda()) + ", con il numero "
                    + domandaRdC.getCodice() + ".";
            stampaDTO.setTesto(testo);
            stampaDTO.setTitolo(messaggiUtils.loadMessaggio(MessaggiUtils.ST002).getIntestazione());
            RedditoDiCittadinanzaReportDTO report = new RedditoDiCittadinanzaReportDTO();
            report.setRdc(stampaDTO);
            byte[] pdf = AdapterSilpgsp.getInstance().creaDocumentoPDF(TemplateStampaSilpDTO.CODICE_MODELLO_STAMPA_RDC, report);
            return Response.ok(pdf).build();
        } catch (Exception ex) {
            log.error("[StampeApiServiceImpl::creaStampaRedditoDiCittadinanza] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.STAMPA_ADESIONE, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(StampeApiServiceImpl.class.getName(), "creaStampaRedditoDiCittadinanza()",
                    "invocazione API UtentiApiServiceImpl.creaStampaRedditoDiCittadinanza", "");
            watcher.stop();
        }
    }

    /**
     * Crea stampa sap.
     *
     * @param idUtente        the id utente
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response creaStampaSap(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[StampeApiServiceImpl::creaStampaSap] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.STAMPA_ADESIONE, idUtente);
            SchedaAnagraficoProfessionaleReportDTO sap = getReportSap(getIdSilLavAnagrafica(idUtente));
            byte[] pdf = AdapterSilpgsp.getInstance().creaDocumentoPDF(TemplateStampaSilpDTO.CODICE_MODELLO_STAMPA_SAP, sap);
            return Response.ok(pdf).build();
        } catch (Exception ex) {
            log.error("[StampeApiServiceImpl::creaStampaSap] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.STAMPA_ADESIONE, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(StampeApiServiceImpl.class.getName(), "creaStampaSap()", "invocazione API UtentiApiServiceImpl.creaStampaSap", "");
            watcher.stop();
        }
    }

    /**
     * Ritorna un oggetto contenente una sap di un lavoratore silp, aggiunto un
     * parametro per limitare eventuali liste eccessivamente lunghe di esperienze di
     * lavoro.
     *
     * @param idSilLavAnagrafica the id sil lav anagrafica
     * @return the report sap
     * @throws Exception the exception
     */
    private SchedaAnagraficoProfessionaleReportDTO getReportSap(Long idSilLavAnagrafica) throws Exception {
        String numeroMassimoEsperienzeLavoroSap = parametroUtils.getParametro(ParametroUtils.NUMERO_MASSIMO_ESPERIENZE_LAVORO_SAP);
        return AdapterSilpsvspWSImpl.getInstance().getReportSAP(idSilLavAnagrafica, SilpConvertUtils.toLong(numeroMassimoEsperienzeLavoroSap));
    }

    /**
     * Gets the id sil lav anagrafica.
     *
     * @param idUtente the id utente
     * @return the id sil lav anagrafica
     * @throws Exception the exception
     */
    private Long getIdSilLavAnagrafica(Long idUtente) throws Exception {
        UtenteFilter filter = new UtenteFilter();
        filter.getIdUtente().eq(idUtente);
        UtenteDTO utente = dao.findFirst(UtenteDBDef.class, filter);
        return utente.getIdSilLavAngrafica();
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
     * Crea stampa patto di servizio.
     *
     * @param idUtente        the id utente
     * @param idDid           the id did
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response creaStampaPattoDiServizio(Long idUtente, Long idDid, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[StampeApiServiceImpl::creaStampaPattoDiServizio] idUtente=" + idUtente + ", idDid= " + idDid);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.STAMPA_PATTO_DI_SERVIZIO, idUtente);
            PattoDiServizioReportDTO pattoDiServizio = new PattoDiServizioReportDTO();

            StampaPattoDiServizioSilpDTO ilPatto = new StampaPattoDiServizioSilpDTO();

            UtenteFilter filter = new UtenteFilter();
            filter.getIdUtente().eq(idUtente);
            UtenteDTO utente = dao.findFirst(UtenteDBDef.class, filter);
            SchedaAnagraficoProfessionaleReportDTO sap = getReportSap(utente.getIdSilLavAngrafica());
            DatiAnagraficiPersonaDTO datiAnagrafici = sap.getSap().getDatiAnagrafici().getDatiPersonali();

            ilPatto.setCodiceFiscale(datiAnagrafici.getCodiceFiscale());
            ilPatto.setCognome(datiAnagrafici.getCognome());
            ilPatto.setNome(datiAnagrafici.getNome());

            EsitoDettaglioDid esitoRicercaDid = AdapterSilpserver.getInstance().ricercaDettaglioDIDService(utente.getCfUtente(), utente.getIdSilLavAngrafica());
            if (null != esitoRicercaDid.getFlgPercettore() && esitoRicercaDid.getFlgPercettore().equals("S")) {
                ilPatto.setNonPercettore("");
                ilPatto.setPercettore("X");
            } else {
                ilPatto.setNonPercettore("X");
                ilPatto.setPercettore("");
            }
            ParametroDTO parametroDTO = parametroUtils.getParametroDTO("DID_TITOLO_PATTO");
            ilPatto.setTitolo(parametroDTO.getValoreParametro());
            parametroDTO = parametroUtils.getParametroDTO("DID_SOTTOTIT_PATTO");
            ilPatto.setSottotitolo(parametroDTO.getValoreParametro());

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(DD_MM_YYYY);

            ilPatto.setDataDownload("in data: " + sdf.format(cal.getTime()));

            pattoDiServizio.setPds(ilPatto);
            log.info("[StampeApiServiceImpl::creaStampaPattoDiServizio] --->pattoDiServizio--->:");
            byte[] pdf = AdapterSilpgsp.getInstance().creaDocumentoPDF(TemplateStampaSilpDTO.CODICE_MODELLO_STAMPA_PDS, pattoDiServizio);
            return Response.ok(pdf).build();
        } catch (Exception ex) {
            log.error("[StampeApiServiceImpl::creaStampaPattoDiServizio] idUtente=" + idUtente + ", idDid= " + idDid, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.STAMPA_PATTO_DI_SERVIZIO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(StampeApiServiceImpl.class.getName(), "creaStampaPattoDiServizio()",
                    "invocazione API StampeApiServiceImpl.creaStampaPattoDiServizio", "");
            watcher.stop();
        }
    }

    @Override
    public Response creaStampaIscrizioneCollocamentoMirato(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[StampeApiServiceImpl::creaStampaIscrizioneCollocamentoMirato] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        AdapterSilpsvspWSFactory factory = new AdapterSilpsvspWSFactory();
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.STAMPA_ISCRIZIONE_COLLOCAMENTO_MIRATO, idUtente);
            UtenteFilter filter = new UtenteFilter();
            filter.getIdUtente().eq(idUtente);
            UtenteDTO utente = dao.findFirst(UtenteDBDef.class, filter);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            SchedaAnagraficoProfessionaleReportDTO sap = getReportSap(utente.getIdSilLavAngrafica());
            DatiAnagraficiPersonaDTO datiAnagrafici = sap.getSap().getDatiAnagrafici().getDatiPersonali();

            CollocamentoMiratoRiepilogoSilpFilter filterWS = new CollocamentoMiratoRiepilogoSilpFilter();
            filterWS.setIdLavoratore(utente.getIdSilLavAngrafica());
            filterWS.setIdProvincia(null);
            filterWS.setCodiceOperatore(codiceFiscaleUtente);
            WSContainerDTO esitoWSContainer = factory.getService().loadRiepilogoCollocamentoMirato(Constants.CLIENT_SILP_NAME, filterWS);

            EsitoCollocamentoMiratoRiepilogoDTO esitoWS = GsonUtils.toGsonObject(esitoWSContainer.getValue(), EsitoCollocamentoMiratoRiepilogoDTO.class);
            String codiceModelloStampa = "";

            IscrizioneCollocamentoMiratoDTO datiIscrizione = new IscrizioneCollocamentoMiratoDTO();
            if (null != esitoWS.getIscrizioneDisabili() && !esitoWS.getIscrizioneDisabili().isStatoFinale()) {
                codiceModelloStampa = TemplateStampaSilpDTO.CODICE_MODELLO_STAMPA_ISCR_L68_ART_1_DISABILI;
                datiIscrizione = esitoWS.getIscrizioneDisabili();
            } else if (null != esitoWS.getIscrizioneAltreCategorie() && !esitoWS.getIscrizioneAltreCategorie().isStatoFinale()) {
                codiceModelloStampa = TemplateStampaSilpDTO.CODICE_MODELLO_STAMPA_ISCR_L68_ART_18_ALTRE_CATEGORIE;
                datiIscrizione = esitoWS.getIscrizioneAltreCategorie();
            }
            IscrizioneCollocamentoMiratoReportDTO iscrReportDto = new IscrizioneCollocamentoMiratoReportDTO();
            iscrReportDto.setDescrCpi(datiIscrizione.getCpi().getDescrizione());
            iscrReportDto.setLuogoEData(iscrReportDto.getDescrCpi() + ", " + SilpTimeUtils.convertDataInStringa(new Date()));
            iscrReportDto.setNominativo(datiAnagrafici.getCognomeNome());
            if (datiAnagrafici.getLuogoNascita().getComune().getDescrizione() != null) {
                iscrReportDto.setLuogoNascita(datiAnagrafici.getLuogoNascita().getComune().getDescrizione() + " ("
                        + datiAnagrafici.getLuogoNascita().getComune().getProvincia().getTarga() + ")");

            } else {
                iscrReportDto.setLuogoNascita(datiAnagrafici.getLuogoNascita().getStato().getDescrizione());
            }
            SimpleDateFormat df = new SimpleDateFormat(DD_MM_YYYY);
            iscrReportDto.setDataNascita(df.format(datiAnagrafici.getDataNascita()));
            iscrReportDto.setIndirizzoDomicilio(sap.getSap().getDatiAnagrafici().getDomicilio().getIndirizzo() + " - "
                    + sap.getSap().getDatiAnagrafici().getDomicilio().getComune().getCap() + " "
                    + sap.getSap().getDatiAnagrafici().getDomicilio().getComune().getDescrizione() + " ("
                    + sap.getSap().getDatiAnagrafici().getDomicilio().getComune().getProvincia().getTarga() + ")");
            iscrReportDto.setDescrCategoria(datiIscrizione.getCategoria());
            iscrReportDto.setIdAnagrafica(utente.getIdSilLavAngrafica());
            iscrReportDto.setResponsabile(datiIscrizione.getResponsabileCpi());

            datiIscrizione.setDataIscrizione(datiIscrizione.getDataIscrizione());

            iscrReportDto.setIscrizione(datiIscrizione);

            Vector beansPerStampa = new Vector();
            beansPerStampa.add(iscrReportDto);

            GspDatiDocumentoBeansDTO datiDocumento = new GspDatiDocumentoBeansDTO(null, beansPerStampa);
            datiDocumento.setCodiceModello(codiceModelloStampa);

            log.info("[StampeApiServiceImpl::creaStampaIscrizioneCollocamentoMirato] ");
            byte[] pdf = AdapterSilpgsp.getInstance().creaDocumentoPDF(codiceModelloStampa, iscrReportDto);
            return Response.ok(pdf).build();
        } catch (Exception ex) {
            log.error("[StampeApiServiceImpl::creaStampaIscrizioneCollocamentoMirato] idUtente=" + idUtente, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.STAMPA_ISCRIZIONE_COLLOCAMENTO_MIRATO, httpRequest, idUtente,
                    ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME175);
        } finally {
            watcher.dumpElapsed(StampeApiServiceImpl.class.getName(), "creaStampaIscrizioneCollocamentoMirato()",
                    "invocazione API StampeApiServiceImpl.creaStampaIscrizioneCollocamentoMirato", "");
            watcher.stop();
        }
    }

}
