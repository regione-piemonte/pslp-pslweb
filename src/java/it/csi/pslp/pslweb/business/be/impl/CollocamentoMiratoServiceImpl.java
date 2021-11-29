/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.pslp.pslcommonobj.dbdef.UtenteDBDef;
import it.csi.pslp.pslcommonobj.dto.UtenteDTO;
import it.csi.pslp.pslcommonobj.filter.UtenteFilter;
import it.csi.pslp.pslweb.business.be.CollocamentoMiratoApi;
import it.csi.pslp.pslweb.business.integration.AdapterSilpserver;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvspWSImpl;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneFamiliariACarico;
import it.csi.pslp.pslweb.dto.be.DatiInputSaveRichiestaIscrizioneCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.DettaglioCompletoDichiarazioneFamiliariACarico;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.EsitoRicercaRichiestaIscrizioneCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.EsitoRiepilogoCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.EsitoSalvataggioRedditoCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.EsitoSaveDichiarazione;
import it.csi.pslp.pslweb.dto.be.EsitoSaveRichiestaIscrizioneCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.ParametriSalvataggioRedditoCollocamentoMirato;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.RestHelper;
import it.csi.pslp.pslweb.util.SpringSupportedResource;
import it.csi.pslp.pslweb.util.mapper.DettaglioCompletoDichiarazioneFamiliariMapper;
import it.csi.silos.jedi.core.DAO;
import it.csi.silpcommonobj.dati.collocamentomirato.DettaglioCompletoDichiarazioneFamiliariACaricoDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.RicercaDichiarazioneFamiliariACaricoHeaderDTO;
import it.csi.util.performance.StopWatch;

// TODO: Auto-generated Javadoc
/**
 * The Class CollocamentoMiratoServiceImpl.
 */
@Component("collocamentoMiratoApi")
public class CollocamentoMiratoServiceImpl extends SpringSupportedResource implements CollocamentoMiratoApi {

    /** The Constant COD_AMBITO. */
    private static final String COD_AMBITO = ",codAmbito=";

    /** The Constant ERRORE_DI_SISTEMA. */
    private static final String ERRORE_DI_SISTEMA = "Errore di sistema: ";

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
     * Gets the riepilogo collocamento mirato.
     *
     * @param idUtente        the id utente
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the riepilogo collocamento mirato
     */
    @Override
    public Response getRiepilogoCollocamentoMirato(Long idUtente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[CollocamentoMiratoServiceImpl::getRiepilogoCollocamentoMirato] idUtente=" + idUtente);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.COLLOCAMENTO_MIRATO_RIEPILOGO, idUtente);
            UtenteDTO utente = getUtente(idUtente);
            EsitoRiepilogoCollocamentoMirato riepilogo = AdapterSilpsvspWSImpl.getInstance().loadRiepilogoCollocamentoMirato(utente.getIdSilLavAngrafica(),
                    null, utente.getCfUtente());

            Vector<RicercaDichiarazioneFamiliariACaricoHeaderDTO> elencoDicFamACarico = AdapterSilpserver.getInstance()
                    .findDichiarazioniFamiliariACarico(utente.getIdSilLavAngrafica());
            List<DettaglioCompletoDichiarazioneFamiliariACarico> laListaDichi = new ArrayList<DettaglioCompletoDichiarazioneFamiliariACarico>();
            boolean ultima = false;
            for (RicercaDichiarazioneFamiliariACaricoHeaderDTO ricercaDichiarazioneFamiliariACaricoHeaderDTO : elencoDicFamACarico) {
                DettaglioCompletoDichiarazioneFamiliariACaricoDTO dettaglio = AdapterSilpserver.getInstance().loadDettaglioDichiarazioneFamiliariCarico(
                        utente.getIdSilLavAngrafica(), ricercaDichiarazioneFamiliariACaricoHeaderDTO.getIdSilLavParentelaCarico());
                DettaglioCompletoDichiarazioneFamiliariACarico ilDettaglio = new DettaglioCompletoDichiarazioneFamiliariMapper().mapReverse(dettaglio);
                if (null != ricercaDichiarazioneFamiliariACaricoHeaderDTO.getIdSilLavParentelaCarico()) {
                    ilDettaglio.setIdDichiarazione(ricercaDichiarazioneFamiliariACaricoHeaderDTO.getIdSilLavParentelaCarico());
                }
                if (!ultima) {
                    Date now = new Date();

                    Calendar c = Calendar.getInstance();
                    c.setTime(now);
                    int year = c.get(Calendar.YEAR);
                    if (ilDettaglio.getAnnoValidita().intValue() == year) {
                        ilDettaglio.setFlagUltimaDichiarazioneInserita(true);
                        ultima = true;
                    }

                }
                laListaDichi.add(ilDettaglio);
            }
            riepilogo.setDettaglioCompletoDichiarazioneFamiliariACarico(laListaDichi);
            log.debug("[CollocamentoMiratoServiceImpl::getRiepilogoCollocamentoMirato] riepilogo=" + riepilogo);
            if (null == riepilogo.getIscrizioneAltreCategorie() && null == riepilogo.getIscrizioneDisabili()
                    && riepilogo.getDettaglioCompletoDichiarazioneFamiliariACarico().isEmpty() && riepilogo.getRedditi().isEmpty()) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.COLLOCAMENTO_MIRATO_RIEPILOGO, httpRequest, idUtente, "Nessun Riepilogo restituito da SRV",
                        Constants.CODICE_AMBITO_COMI);
            } else {
                tracciamentoUtils.tracciaOk(TracciamentoUtils.COLLOCAMENTO_MIRATO_RIEPILOGO, httpRequest, idUtente,
                        "[CollocamentoMiratoServiceImpl::getRiepilogoCollocamentoMirato] riepilogo=" + riepilogo, Constants.CODICE_AMBITO_COMI);
            }

            /*
             * aggiunto elenco richieste a riepilogo
             */
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            EsitoRicercaRichiestaIscrizioneCollocamentoMirato esito = AdapterSilpserver.getInstance()
                    .findRichiestaIscrizioneCollocamentoMirato(codiceFiscaleUtente, utente.getIdSilLavAngrafica(), null);
            riepilogo.setElencoRichieste(esito.getElencoRichieste());

            return Response.ok(riepilogo).build();
        } catch (Exception ex) {
            log.error("[CollocamentoMiratoServiceImpl::getRiepilogoCollocamentoMirato]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.COLLOCAMENTO_MIRATO_RIEPILOGO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    Constants.CODICE_AMBITO_COMI);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(CollocamentoMiratoServiceImpl.class.getName(), "getRiepilogoCollocamentoMirato()",
                    "invocazione API CollocamentoMiratoServiceImpl.getRiepilogoCollocamentoMirato", "");
            watcher.stop();
        }
    }

    /**
     * Save reddito collocamento mirato.
     *
     * @param idUtente         the id utente
     * @param parametriReddito the parametri reddito
     * @param securityContext  the security context
     * @param httpHeaders      the http headers
     * @param httpRequest      the http request
     * @return the response
     */
    public Response saveRedditoCollocamentoMirato(@PathParam("id_utente") Long idUtente, ParametriSalvataggioRedditoCollocamentoMirato parametriReddito,
            SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[CollocamentoMiratoServiceImpl::saveRedditoCollocamentoMirato] idUtente=" + idUtente + "parametriReddito=" + parametriReddito);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            restHelper.checkUtente(httpRequest, TracciamentoUtils.COLLOCAMENTO_MIRATO_SAVE_REDDITO, idUtente);
            UtenteDTO utente = getUtente(idUtente);
            EsitoSalvataggioRedditoCollocamentoMirato esito = AdapterSilpsvspWSImpl.getInstance().saveRedditoCollocamentoMirato(utente, parametriReddito);
            log.debug("[CollocamentoMiratoServiceImpl::saveRedditoCollocamentoMirato] esito=" + esito);
            if (null != parametriReddito) {
                if (null != parametriReddito.getIdRedditoPerAnnullo()) {
                    tracciamentoUtils.tracciaOk(TracciamentoUtils.COLLOCAMENTO_MIRATO_SAVE_REDDITO, httpRequest, idUtente,
                            "[CollocamentoMiratoServiceImpl::Annullamento Reddito] esito=" + esito, Constants.CODICE_AMBITO_COMI);
                } else {
                    tracciamentoUtils.tracciaOk(TracciamentoUtils.COLLOCAMENTO_MIRATO_SAVE_REDDITO, httpRequest, idUtente,
                            "[CollocamentoMiratoServiceImpl::Inserimento Reddito] esito=" + esito, Constants.CODICE_AMBITO_COMI);
                }
            } else {
                tracciamentoUtils.tracciaOk(TracciamentoUtils.COLLOCAMENTO_MIRATO_SAVE_REDDITO, httpRequest, idUtente,
                        "[CollocamentoMiratoServiceImpl::Inserimento Reddito] esito=" + esito, Constants.CODICE_AMBITO_COMI);
            }
            return Response.ok(esito).build();
        } catch (Exception ex) {
            log.error("[CollocamentoMiratoServiceImpl::saveRedditoCollocamentoMirato]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.COLLOCAMENTO_MIRATO_SAVE_REDDITO, httpRequest, idUtente, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(CollocamentoMiratoServiceImpl.class.getName(), "saveRedditoCollocamentoMirato()",
                    "invocazione API CollocamentoMiratoServiceImpl.saveRedditoCollocamentoMirato", "");
            watcher.stop();
        }
    }

    /**
     * Load configurazione familiari A carico.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response loadConfigurazioneFamiliariACarico(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[CollocamentoMiratoServiceImpl::loadConfigurazioneFamiliariACarico] ");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            ConfigurazioneFamiliariACarico conf = AdapterSilpserver.getInstance().loadConfigurazioneFamiliariACarico();

            /* Codice aggiunto per recuperare la descrizione lunga di silp */
            List<Decodifica> elencoMotivoCarico = new ArrayList<>();
            for (Decodifica decOrigin : conf.getElencoDecodeMotivoCarico()) {
                Decodifica dec = new Decodifica();
                dec.setCodiceSilp(decOrigin.getCodiceSilp());
                if (null != messaggiUtils.loadMessaggio(decOrigin.getCodiceSilp())) {
                    dec.setDescrizione(messaggiUtils.loadMessaggio(decOrigin.getCodiceSilp()).getTesto());
                }
                elencoMotivoCarico.add(dec);
            }
            conf.setElencoMotivoCaricoDescrizioneLunga(elencoMotivoCarico);

            log.debug("[CollocamentoMiratoServiceImpl::loadConfigurazioneFamiliariACarico] esito=" + conf);
            return Response.ok(conf).build();
        } catch (Exception ex) {
            log.error("[CollocamentoMiratoServiceImpl::loadConfigurazioneFamiliariACarico]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.COLLOCAMENTO_MIRATO_RIEPILOGO, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    Constants.CODICE_AMBITO_COMI);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed("CollocamentoMiratoServiceImpl", "loadConfigurazioneFamiliariACarico()",
                    "invocazione API CollocamentoMiratoServiceImpl.loadConfigurazioneFamiliariACarico", "");
            watcher.stop();
        }
    }

    /**
     * Gets the utente.
     *
     * @param idUtente the id utente
     * @return the utente
     * @throws Exception the exception
     */
    private UtenteDTO getUtente(Long idUtente) throws Exception {
        UtenteFilter filter = new UtenteFilter();
        filter.getIdUtente().eq(idUtente);
        UtenteDTO utente = dao.findFirst(UtenteDBDef.class, filter);
        return utente;
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

    /**
     * Save familiari A carico.
     *
     * @param idUtente        the id utente
     * @param ilDettaglio     the il dettaglio
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    @Transactional
    public Response saveFamiliariACarico(Long idUtente, DettaglioCompletoDichiarazioneFamiliariACarico ilDettaglio, SecurityContext securityContext,
            HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[CollocamentoMiratoServiceImpl::saveFamiliariACarico] idUtente=" + idUtente + " ilDettaglio=" + ilDettaglio);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {

            restHelper.checkUtente(httpRequest, TracciamentoUtils.COLLOCAMENTO_MIRATO_SAVE_FAMILIARI_A_CARICO, idUtente);
            UtenteDTO utente = getUtente(idUtente);

            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);

            EsitoSaveDichiarazione loEsito = AdapterSilpserver.getInstance().saveFamiliariACarico(codiceFiscaleUtente, utente.getIdSilLavAngrafica(),
                    ilDettaglio);
            if (loEsito == null) {
                return createErrorResponse("401", MessaggiUtils.ME002);
            }
            if (null != loEsito.getMessaggioErrore() && !("").equals(loEsito.getMessaggioErrore())) {
                if (ilDettaglio.isFlagDuplicazioneDichiarazione()) {
                    tracciamentoUtils.tracciaKo(TracciamentoUtils.COLLOCAMENTO_MIRATO_SAVE_FAMILIARI_A_CARICO, httpRequest, idUtente,
                            "[CollocamentoMiratoServiceImpl::isDichiarazioneDuplicata dichiarazione] esito=" + loEsito, Constants.CODICE_AMBITO_COMI);
                } else {
                    tracciamentoUtils.tracciaKo(TracciamentoUtils.COLLOCAMENTO_MIRATO_SAVE_FAMILIARI_A_CARICO, httpRequest, idUtente,
                            "[CollocamentoMiratoServiceImpl::Inserimento dichiarazione] esito=" + loEsito, Constants.CODICE_AMBITO_COMI);
                }
            } else {
                if (ilDettaglio.isFlagDuplicazioneDichiarazione()) {
                    tracciamentoUtils.tracciaOk(TracciamentoUtils.COLLOCAMENTO_MIRATO_SAVE_FAMILIARI_A_CARICO, httpRequest, idUtente,
                            "[CollocamentoMiratoServiceImpl::Inserimento dichiarazione] isDichiarazioneDuplicata "
                                    + ilDettaglio.isFlagDuplicazioneDichiarazione(),
                            Constants.CODICE_AMBITO_COMI);
                } else {
                    tracciamentoUtils.tracciaOk(TracciamentoUtils.COLLOCAMENTO_MIRATO_SAVE_FAMILIARI_A_CARICO, httpRequest, idUtente,
                            "[CollocamentoMiratoServiceImpl::Inserimento dichiarazione] esito=" + loEsito, Constants.CODICE_AMBITO_COMI);
                }
            }
            return Response.ok(loEsito).build();
        } catch (Exception ex) {
            log.error("[CollocamentoMiratoServiceImpl::saveFamiliariACarico]", ex);
            if (ilDettaglio.isFlagDuplicazioneDichiarazione()) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.COLLOCAMENTO_MIRATO_SAVE_FAMILIARI_A_CARICO, httpRequest, idUtente,
                        "[CollocamentoMiratoServiceImpl::Inserimento dichiarazione] isDichiarazioneDuplicata " + ilDettaglio.isFlagDuplicazioneDichiarazione(),
                        Constants.CODICE_AMBITO_COMI);
            }
            tracciamentoUtils.tracciaKo(TracciamentoUtils.COLLOCAMENTO_MIRATO_SAVE_FAMILIARI_A_CARICO, httpRequest, idUtente,
                    "[CollocamentoMiratoServiceImpl::Inserimento dichiarazione] Errore di sistema: " + ex.getClass().getName(), Constants.CODICE_AMBITO_COMI);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed("CollocamentoMiratoServiceImpl", "saveFamiliariACarico()", "invocazione API CollocamentoMiratoServiceImpl.saveFamiliariACarico",
                    "");
            watcher.stop();
        }
    }

    /**
     * Legge da silpserver tutte le configurazioni utilizzate per il Collocamento
     * Mirato. so Lillo
     * 
     * @return un oggetto ConfigurazioneCollocamentoMirato completamente valorizzato
     */
    @Override
    public Response loadConfigurazioniCollocamentoMirato(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[CollocamentoMiratoServiceImpl::loadConfigurazioniCollocamentoMirato]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);

            ConfigurazioneCollocamentoMirato confCollocamentoMirato = AdapterSilpserver.getInstance().loadConfigurazioniCollocamentoMirato(codiceFiscaleUtente);
            return Response.ok(confCollocamentoMirato).build();
        } catch (Exception ex) {
            log.error("[CollocamentoMiratoServiceImpl::loadConfigurazioniCollocamentoMirato]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.LOAD_CONFIGURAZIONI_PROFILING_L68, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    "COMI");
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed("CollocamentoMiratoServiceImpl", "loadConfigurazioniCollocamentoMirato()",
                    "invocazione API CollocamentoMiratoServiceImpl.loadConfigurazioniCollocamentoMirato", "");
            watcher.stop();
        }
    }

    @Override
    public Response findRichiestaIscrizioneCollocamentoMirato(Long idUtente, Long idRichiesta, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[CollocamentoMiratoServiceImpl::findRichiestaIscrizioneCollocamentoMirato]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            UtenteDTO utente = getUtente(idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            EsitoRicercaRichiestaIscrizioneCollocamentoMirato esito = AdapterSilpserver.getInstance()
                    .findRichiestaIscrizioneCollocamentoMirato(codiceFiscaleUtente, utente.getIdSilLavAngrafica(), idRichiesta);
            return Response.ok(esito).build();
        } catch (Exception ex) {
            log.error("[CollocamentoMiratoServiceImpl::findRichiestaIscrizioneCollocamentoMirato]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.LOAD_CONFIGURAZIONI_PROFILING_L68, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    "COMI");
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed("CollocamentoMiratoServiceImpl", "findRichiestaIscrizioneCollocamentoMirato()",
                    "invocazione API CollocamentoMiratoServiceImpl.findRichiestaIscrizioneCollocamentoMirato", "");
            watcher.stop();
        }
    }

    @Override
    public Response saveRichiestaIscrizioneCollocamentoMirato(Long idUtente, DatiInputSaveRichiestaIscrizioneCollocamentoMirato parametriRichiesta,
            SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[CollocamentoMiratoServiceImpl::saveRichiestaIscrizioneCollocamentoMirato]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            UtenteDTO utente = getUtente(idUtente);
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            EsitoSaveRichiestaIscrizioneCollocamentoMirato esito = AdapterSilpserver.getInstance()
                    .saveRichiestaIscrizioneCollocamentoMirato(codiceFiscaleUtente, utente.getIdSilLavAngrafica(), parametriRichiesta);
            return Response.ok(esito).build();
        } catch (Exception ex) {
            log.error("[CollocamentoMiratoServiceImpl::saveRichiestaIscrizioneCollocamentoMirato]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.LOAD_CONFIGURAZIONI_PROFILING_L68, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(),
                    "COMI");
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed("CollocamentoMiratoServiceImpl", "saveRichiestaIscrizioneCollocamentoMirato()",
                    "invocazione API CollocamentoMiratoServiceImpl.saveRichiestaIscrizioneCollocamentoMirato", "");
            watcher.stop();
        }
    }

}
