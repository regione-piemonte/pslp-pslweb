/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import it.csi.csi.porte.InfoPortaDelegata;
import it.csi.csi.porte.proxy.PDProxy;
import it.csi.csi.util.xml.PDConfigReader;
import it.csi.pslp.pslweb.business.be.impl.ConfigurazioneProfiling;
import it.csi.pslp.pslweb.dto.be.CategoriaProtetta;
import it.csi.pslp.pslweb.dto.be.CategoriaProtettaDisab;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneFamiliariACarico;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneInformazioneAggiuntiva;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneProfilingDid;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneRisposta;
import it.csi.pslp.pslweb.dto.be.DatiInputAggiornamentoDid;
import it.csi.pslp.pslweb.dto.be.DatiInputProfilingDid;
import it.csi.pslp.pslweb.dto.be.DatiInputSaveRichiestaIscrizioneCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.DatiProfilingDid;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.DettaglioCompletoDichiarazioneFamiliariACarico;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.EsitoDettaglioDid;
import it.csi.pslp.pslweb.dto.be.EsitoRicercaRichiestaIscrizioneCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.EsitoSaveDichiarazione;
import it.csi.pslp.pslweb.dto.be.EsitoSaveDid;
import it.csi.pslp.pslweb.dto.be.EsitoSaveProfilingDid;
import it.csi.pslp.pslweb.dto.be.EsitoSaveRichiestaIscrizioneCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.InformazioneAggiuntiva;
import it.csi.pslp.pslweb.dto.be.MappaErrori;
import it.csi.pslp.pslweb.dto.be.MotivoFamiliareACarico;
import it.csi.pslp.pslweb.dto.be.ParametriCalcoloProfilingYG;
import it.csi.pslp.pslweb.dto.be.ProfilingYG;
import it.csi.pslp.pslweb.util.Cache;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.mapper.DatiInputAggiornamentoDidMapper;
import it.csi.pslp.pslweb.util.mapper.DatiInputProfilingDidMapper;
import it.csi.pslp.pslweb.util.mapper.DatiProfilingDidMapper;
import it.csi.pslp.pslweb.util.mapper.DettaglioCompletoDichiarazioneFamiliariMapper;
import it.csi.pslp.pslweb.util.mapper.DettaglioRichiestaIscrizioneL68Mapper;
import it.csi.pslp.pslweb.util.mapper.EsitoRicercaRichiestaIscrizioneCollocamentoMiratoMapper;
import it.csi.pslp.pslweb.util.mapper.did.ConfigurazioneProfilingDidMapper;
import it.csi.silos.silcommon.constants.SilCommonConstants;
import it.csi.silos.silcommon.dati.EsitoWS;
import it.csi.silos.silcommon.dati.MessaggioEsito;
import it.csi.silos.silcommon.generic.CallInfoDTO;
import it.csi.silos.silcommon.util.SilTimeUtils;
import it.csi.silp.silpserver.interfacecsi.gestioneprovvedimenti.GestioneProvvedimentiSrv;
import it.csi.silp.silpserver.interfacecsi.gestionesap.GestioneSapSrv;
import it.csi.silpcommonobj.dati.collocamentomirato.CategoriaProtettaDisabSilpDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.CategoriaProtettaSilpDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.ConfigurazioneCollocamentoMiratoDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.ConfigurazioneFamiliariACaricoSilpDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.DettaglioCompletoDichiarazioneFamiliariACaricoDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.EsitoConfigurazioneCollocamentoMiratoDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.RicercaDichiarazioneFamiliariACaricoHeaderDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.SaveEsitoFamiliariACaricoSilpDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.richiestaiscrizione.DatiInputRichiestaIscrizioneCollocamentoMiratoSilpDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.richiestaiscrizione.EsitoRicercaRichiestaIscrizioneCollocamentoMiratoSilpDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.richiestaiscrizione.SaveEsitoRichiestaIscrizioneCollocamentoMiratoSilpDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.richiestaiscrizione.StatoRichiestaIscrizioneL68SilpDTO;
import it.csi.silpcommonobj.dati.common.CallInfoSilpDTO;
import it.csi.silpcommonobj.dati.common.DecodificaSilpDTO;
import it.csi.silpcommonobj.dati.decodifiche.CategoriaInformazioniAggiuntiveSilpDTO;
import it.csi.silpcommonobj.dati.decodifiche.InformazioniAggiuntiveSilpDTO;
import it.csi.silpcommonobj.dati.decodifiche.TMotivoCaricoSilpDTO;
import it.csi.silpcommonobj.dati.informazioniaggiuntive.EsitoInformazioniAggiuntiveSilpDTO;
import it.csi.silpcommonobj.dati.informazioniaggiuntive.InformazioniAggiuntiveHeaderSilpDTO;
import it.csi.silpcommonobj.dati.informazioniaggiuntive.InformazioniAggiuntiveLavoratoreSilpDTO;
import it.csi.silpcommonobj.dati.questionari.RispostaQuestionarioDettaglioSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.did.EsitoConfigurazioneConferimentoDidDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.did.EsitoRicercaDidService;
import it.csi.silpcommonobj.dati.schedaprofessionale.did.pslp.DatiInputAggiornamentoDidDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.did.pslp.DatiInputProfilingDidDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.did.pslp.EsitoAggiornamentoDidDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.did.pslp.EsitoProfilingDidDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.profiling.ConfigurazioneProfilingSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.profiling.EsitoProfilingCalcoloSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.profiling.ProfilingSilpDTO;
import it.csi.silpcommonobj.filter.collocamentomirato.FamiliariACaricoHeaderSilpFilter;
import it.csi.silpcommonobj.filter.collocamentomirato.richiestaiscrizione.RichiestaIscrizioneL68HeaderFilter;
import it.csi.silpcommonobj.filter.informazioniaggiuntive.InformazioniAggiuntiveHeaderSilpFilter;
import it.csi.silpcommonobj.filter.schedaprofessionale.profiling.ProfilingCalcoloSilpFilter;
import it.csi.util.performance.StopWatch;

/**
 * The Class AdapterSilpserver.
 */
public class AdapterSilpserver {

    /** The Constant FILTER2. */
    private static final String FILTER2 = ", filter=";

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The Constant PD_GESTIONE_SAP. */
    private static final String PD_GESTIONE_SAP = "/defpd_gestionesapsoap.xml";

    /** The Constant PD_GESTIONE_PROVV. */
    private static final String PD_GESTIONE_PROVV = "/defpd_gestioneprovvedimentisoap.xml";

    /** The gestione sap service. */
    private GestioneSapSrv gestioneSapService = null;

    /** The gestione provv service. */
    private GestioneProvvedimentiSrv gestioneProvvService = null;

    /** The Constant instance. */
    private static final AdapterSilpserver instance = new AdapterSilpserver();

    /**
     * Gets the single instance of AdapterSilpserver.
     *
     * @return single instance of AdapterSilpserver
     */
    public static AdapterSilpserver getInstance() {
        return instance;
    }

    /**
     * Gets the gestione sap service.
     *
     * @return the gestione sap service
     * @throws Exception the exception
     */
    private GestioneSapSrv getGestioneSapService() throws Exception {
        if (gestioneSapService == null) {
            InputStream pd = this.getClass().getResourceAsStream(PD_GESTIONE_SAP);
            InfoPortaDelegata info = PDConfigReader.read(pd);
            gestioneSapService = (GestioneSapSrv) PDProxy.newInstance(info);
        }
        return gestioneSapService;
    }

    /**
     * Gets the gestione provv service.
     *
     * @return the gestione provv service
     * @throws Exception the exception
     */
    private GestioneProvvedimentiSrv getGestioneProvvService() throws Exception {
        if (gestioneProvvService == null) {
            InputStream pd = this.getClass().getResourceAsStream(PD_GESTIONE_PROVV);
            InfoPortaDelegata info = PDConfigReader.read(pd);
            gestioneProvvService = (GestioneProvvedimentiSrv) PDProxy.newInstance(info);
        }
        return gestioneProvvService;
    }

    /**
     * Load configurazione profiling.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @return the configurazione profiling
     * @throws Exception the exception
     */
    public ConfigurazioneProfiling loadConfigurazioneProfiling(String codiceFiscaleUtente) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            ConfigurazioneProfiling conf = (ConfigurazioneProfiling) Cache.getInstance().getEl(Cache.CONFIGURAZIONE_PROFILING);
            if (conf != null)
                return conf;
            CallInfoSilpDTO callInfo = new CallInfoSilpDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtente);
            ConfigurazioneProfilingSilpDTO confDTO = getGestioneSapService().loadConfigurazioneProfiling(callInfo);
            conf = new ConfigurazioneProfiling(confDTO);
            Cache.getInstance().putEl(Cache.CONFIGURAZIONE_PROFILING, conf);
            return conf;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "loadConfigurazioneProfiling()",
                    "invocazione servizio [SILP.silpserver::loadConfigurazioneProfiling]", "");
            watcher.stop();
        }
    }

    /**
     * Calcola profiling.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @param idLavoratoreSilp    the id lavoratore silp
     * @param params              the params
     * @return the profiling YG
     * @throws Exception the exception
     */
    public ProfilingYG calcolaProfiling(String codiceFiscaleUtente, Long idLavoratoreSilp, ParametriCalcoloProfilingYG params) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            CallInfoSilpDTO callInfo = new CallInfoSilpDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtente);
            ProfilingCalcoloSilpFilter filter = new ProfilingCalcoloSilpFilter();
            filter.setIdCondizioneOccupazionale(params.getCodiceSilpCondizioneOccupazionale());
            filter.setIdLavoratore(idLavoratoreSilp);
            filter.setIdPresenzaInItalia(params.getCodiceSilpPresenzaInItalia());
            filter.setIdProvincia(params.getCodiceMinisterialeProvincia());
            filter.setIdTitoloStudio(params.getCodiceSilpTitoloStudio());
            filter.setUtenteHaConfermatoIlSalvataggio(true);
            log.debug("[AdapterSilpserver::calcolaProfiling] callInfo=" + callInfo + FILTER2 + filter);
            EsitoProfilingCalcoloSilpDTO calcoloDTO = getGestioneSapService().calcoloProfiling(callInfo, filter);
            log.debug("[AdapterSilpserver::calcolaProfiling] calcoloDTO=" + calcoloDTO);
            if (!calcoloDTO.isEsitoPositivo()) {
                String errori = calcoloDTO.getMessaggiErrorAsString();
                throw new Exception("Errore nel richiamo di silpserver.calcoloProfiling: " + errori);
            }
            ProfilingYG result = new ProfilingYG();
            if (calcoloDTO != null && calcoloDTO.getOperazione() != null && calcoloDTO.getOperazione().getProfilingSilp() != null) {
                ConfigurazioneProfiling configurazione = loadConfigurazioneProfiling(codiceFiscaleUtente);
                ProfilingSilpDTO profilingSilp = calcoloDTO.getOperazione().getProfilingSilp();
                result = new ProfilingYG();
                result.setIndice(profilingSilp.getIndice2());
                result.setDescrizioneIndice(profilingSilp.getDescrizioneIndice2());
                result.setCodiceMinisterialeProvincia(profilingSilp.getProvincia().getCodiceMinisteriale());
                result.setCondizioneOccupazionale(
                        configurazione.getCondizioneOccupazionalePerCodiceMinisteriale(profilingSilp.getCondizioneOccupazionale().getCodiceMinisteriale()));
                result.setDataProfiling(profilingSilp.getDataProfiling());
                result.setEta(profilingSilp.getEta());
                result.setGenere(profilingSilp.getGenere());
                result.setMotivoPresenzaInItalia(
                        configurazione.getMotivoPresenzaInItaliaPerCodiceMinisteriale(profilingSilp.getPresenzaInItalia().getCodiceMinisteriale()));
                result.setTitoloStudio(configurazione.getTitoloStudioPerCodiceMinisteriale(profilingSilp.getTitoloStudioProfiling().getCodiceMinisteriale()));
            }
            return result;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "calcolaProfiling()", "invocazione servizio [SILP.silpserver::calcoloProfiling]", "");
            watcher.stop();
        }
    }

    /**
     * Find informazioni aggiuntive lavoratore.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @param idSilLavAnagrafica  the id sil lav anagrafica
     * @param codAmbito           the cod ambito
     * @return the list
     * @throws Exception the exception
     */
    public List<InformazioneAggiuntiva> findInformazioniAggiuntiveLavoratore(String codiceFiscaleUtente, Long idSilLavAnagrafica, String codAmbito)
            throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {

            /**
             * Andrea 19/09/2019: il codice ambito per il quale si sta lavorando deve essere
             * passato in input a questo metodo, in modo da poi poter filtrare solo le
             * informazioni aggiuntive del lavoratore di tale ambito. Per ora forzato a GG,
             * unico esistente
             */
            CallInfoSilpDTO callInfo = new CallInfoSilpDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtente);
            InformazioniAggiuntiveHeaderSilpFilter filter = new InformazioniAggiuntiveHeaderSilpFilter();
            filter.setIdSilLavAnagrafica(idSilLavAnagrafica);
            log.debug("[AdapterSilpserver::findInformazioniAggiuntiveLavoratore] callInfo=" + callInfo + FILTER2 + filter);
            EsitoInformazioniAggiuntiveSilpDTO esitoDTO = getGestioneSapService().findInformazioniAggiuntiveLavoratoreService(callInfo, filter);
            log.debug("[AdapterSilpserver::findInformazioniAggiuntiveLavoratore] esitoDTO=" + esitoDTO);
            if (esitoDTO.getCodiceEsito() != null && !EsitoWS.E0000_CODICE_ESITO_POSITIVO.equals(esitoDTO.getCodiceEsito())) {
                throw new Exception("Errore nella chiamata a silpserver.findInformazioniAggiuntiveLavoratoreService: " + esitoDTO.getCodiceEsito() + " - "
                        + esitoDTO.getDescrizioneEsito());
            }
            List<InformazioneAggiuntiva> result = new ArrayList<>();
            if (esitoDTO.getElencoInformazioniAggiuntiveLavoratore() != null) {
                for (InformazioniAggiuntiveHeaderSilpDTO infoDTO : esitoDTO.getElencoInformazioniAggiuntiveLavoratore()) {

                    if (codAmbito.equals(infoDTO.getCodiceCategoria())) {
                        InformazioneAggiuntiva info = new InformazioneAggiuntiva();
                        info.setCodice(infoDTO.getIdSilLavInfoAggiuntive());
                        info.setCodiceConfigurazione(infoDTO.getIdSilTInfoAggiuntive());
                        info.setDescrizioneConfigurazione(infoDTO.getDesInfoAggiuntive());
                        info.setData(infoDTO.getDtInizio());
                        info.setNote(infoDTO.getDsNote());
                        info.setValore(infoDTO.getFlgValore());
                        result.add(info);
                    }
                }
            }
            return result;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "loadConfigurazioneProfiling()",
                    "invocazione servizio [SILP.silpserver::loadConfigurazioneProfiling]", "");
            watcher.stop();
        }
    }

    /**
     * Save informazioni aggiuntive lavoratore.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @param idSilLavAnagrafica  the id sil lav anagrafica
     * @param info                the info
     * @return the informazione aggiuntiva
     * @throws Exception the exception
     */
    public InformazioneAggiuntiva saveInformazioniAggiuntiveLavoratore(String codiceFiscaleUtente, Long idSilLavAnagrafica, InformazioneAggiuntiva info)
            throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            CallInfoSilpDTO callInfo = new CallInfoSilpDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtente);
            InformazioniAggiuntiveLavoratoreSilpDTO data = new InformazioniAggiuntiveLavoratoreSilpDTO();
            data.setIdSilLavInfoAggiuntive(info.getCodice());
            data.setCodUserAggiorn(codiceFiscaleUtente);
            data.setCodUserInserim(codiceFiscaleUtente);
            data.setInfoAggiuntiva(new InformazioniAggiuntiveSilpDTO());
            data.getInfoAggiuntiva().setIdSilTInfoAggiuntive(info.getCodiceConfigurazione());
            data.setDsNote(info.getNote());

            if (null != info.getValore()) {
                if (info.getValore().equalsIgnoreCase("No")) {
                    data.setFlgValore("N");
                } else if (info.getValore().equalsIgnoreCase("Si")) {
                    data.setFlgValore("S");
                }
            }
            data.setIdSilLavAnagrafica(idSilLavAnagrafica);
            data.setDtInizio(info.getData());
            log.debug("[AdapterSilpserver::saveInformazioniAggiuntiveLavoratore] callInfo=" + callInfo + ", data=" + data);
            log.debug("[AdapterSilpserver::saveInformazioniAggiuntiveLavoratore] callInfo=" + callInfo + ", data=" + data);
            EsitoInformazioniAggiuntiveSilpDTO esitoDTO = getGestioneSapService().saveInformazioniAggiuntiveLavoratoreService(callInfo, data);
            log.debug("[AdapterSilpserver::saveInformazioniAggiuntiveLavoratore] esitoDTO=" + esitoDTO);
            if (esitoDTO.getCodiceEsito() != null && !"E0000".equals(esitoDTO.getCodiceEsito())) {
                throw new Exception("Errore nella chiamata a silpserver.saveInformazioniAggiuntiveLavoratore: " + esitoDTO.getCodiceEsito() + " - "
                        + esitoDTO.getDescrizioneEsito());
            }
            InformazioneAggiuntiva result = null;
            if (esitoDTO != null) {
                result = new InformazioneAggiuntiva();
                InformazioniAggiuntiveLavoratoreSilpDTO resultDTO = esitoDTO.getInformazioneAggiuntivaLavoratoreSalvata();
                result.setCodice(resultDTO.getIdSilLavInfoAggiuntive());
                result.setCodiceConfigurazione(resultDTO.getInfoAggiuntiva().getIdSilTInfoAggiuntive());
                result.setData(resultDTO.getDtAggiorn());
                result.setNote(resultDTO.getDsNote());
                result.setValore(resultDTO.getFlgValore());
            }
            return result;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "saveInformazioniAggiuntiveLavoratore()",
                    "invocazione servizio [SILP.silpserver::saveInformazioniAggiuntiveLavoratoreService]", "");
            watcher.stop();
        }
    }

    /**
     * Gets the informazioni aggiuntive.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @param codAmbito           the cod ambito
     * @param flagAll             the flag all
     * @return the informazioni aggiuntive
     * @throws Exception the exception
     */
    public List<ConfigurazioneInformazioneAggiuntiva> getInformazioniAggiuntive(String codiceFiscaleUtente, String codAmbito, String flagAll) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            List<ConfigurazioneInformazioneAggiuntiva> result = new ArrayList<>();
            CallInfoSilpDTO callInfo = new CallInfoSilpDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtente);
            InformazioniAggiuntiveSilpDTO filter = new InformazioniAggiuntiveSilpDTO();
            log.debug("[AdapterSilpserver::getInformazioniAggiuntive] callInfo=" + callInfo + FILTER2 + filter);
            EsitoInformazioniAggiuntiveSilpDTO esitoDTO = getGestioneSapService().getInformazioniAggiuntiveService(callInfo, filter);
            log.debug("[AdapterSilpserver::getInformazioniAggiuntive] esitoDTO=" + esitoDTO);
            if (esitoDTO.getCodiceEsito() != null && !"E0000".equals(esitoDTO.getCodiceEsito())) {
                throw new Exception(
                        "Errore nella chiamata a silpserver.getInformazioniAggiuntive: " + esitoDTO.getCodiceEsito() + " - " + esitoDTO.getDescrizioneEsito());
            }
            if (esitoDTO != null && esitoDTO.getConfigurazioneInformazioniAggiuntive() != null) {
                /**
                 * Andrea 19/09/2019: patch veloce per mantenere solo le informazioni aggiuntive
                 * valide legate alla politica garanzia giovani, visto che sara' l'unica
                 * all'avvio. A tendere questo metodo dovra' ricevere in input il codice della
                 * politica (GG,Rdc) prelevata dal calendario e passarlo a silp per il filtro o
                 * filtrare a valle come fa ora.
                 * 
                 */
                addInformazioniAggiuntiveByCodAmbito(result, esitoDTO.getConfigurazioneInformazioniAggiuntive(), codAmbito, flagAll);
            }
            return result;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "getInformazioniAggiuntive()",
                    "invocazione servizio [SILP.silpserver::getInformazioniAggiuntiveService]", "");
            watcher.stop();
        }
    }

    /**
     * Da un elenco di configurazioni informazioni aggiuntive ricevute, aggiunge al
     * risultato solo quelle valide ad oggi, relative a PSLP e della data categoria
     * di politica attiva (GG o Rdc).
     *
     * @param result                              the result
     * @param elencoInformazioniAggiuntiveSilpDTO the elenco informazioni aggiuntive
     *                                            silp DTO
     * @param codAmbito                           the cod ambito
     * @param flagAll                             the flag all
     */
    private void addInformazioniAggiuntiveByCodAmbito(List<ConfigurazioneInformazioneAggiuntiva> result,
            InformazioniAggiuntiveSilpDTO[] elencoInformazioniAggiuntiveSilpDTO, String codAmbito, String flagAll) {
        Date now = new Date();
        for (InformazioniAggiuntiveSilpDTO confDTO : elencoInformazioniAggiuntiveSilpDTO) {

            if (("S".equals(flagAll) || SilCommonConstants.S.equals(confDTO.getFlgVisibilitaPSLP()))
                    && (codAmbito == null || confDTO.getCategoriaInformazioni().getCodCategInfoAggMin().equalsIgnoreCase(codAmbito))
                    && ("S".equals(flagAll) || SilTimeUtils.isDataCompresa(now, confDTO.getDtInizio(), confDTO.getDtFine()))) {

                ConfigurazioneInformazioneAggiuntiva conf = new ConfigurazioneInformazioneAggiuntiva();
                conf.setCodice(confDTO.getIdSilTInfoAggiuntive());
                conf.setCodAmbito(confDTO.getCategoriaInformazioni().getCodCategInfoAggMin());
                conf.setDescrizione(confDTO.getDescrizioneDecodifica());
                conf.setBloccante(confDTO.getFlgBloccante());
                conf.setValore(confDTO.getFlgValore());
                conf.setCodiceMinisteriale(confDTO.getCodInfoAggiuntiveMin());
                conf.setDataInizio(confDTO.getDtInizio());
                conf.setDataFine(confDTO.getDtFine());
                conf.setFlagVisibilePslp(confDTO.getFlgVisibilitaPSLP());
                result.add(conf);

            }
        }
    }

    /**
     * Gets the categorie informazioni aggiuntive.
     *
     * @return the categorie informazioni aggiuntive
     * @throws Exception the exception
     */
    private List<CategoriaInformazioniAggiuntiveSilpDTO> getCategorieInformazioniAggiuntive() throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            log.debug("[AdapterSilpserver::getCategorieInformazioniAggiuntive]");
            Vector<CategoriaInformazioniAggiuntiveSilpDTO> result = getGestioneSapService().loadCategorieInformazioniAggiuntive();
            log.debug("[AdapterSilpserver::getCategorieInformazioniAggiuntive] result=" + result);
            return result;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "getCategorieInformazioniAggiuntive()",
                    "invocazione servizio [SILP.silpserver::getCategorieInformazioniAggiuntive]", "");
            watcher.stop();
        }
    }

    /**
     * Gets the map categorie informazioni aggiuntive.
     *
     * @return the map categorie informazioni aggiuntive
     * @throws Exception the exception
     */
    private Map<String, Long> getMapCategorieInformazioniAggiuntive() throws Exception {
        List<CategoriaInformazioniAggiuntiveSilpDTO> categorie = getCategorieInformazioniAggiuntive();
        Map<String, Long> result = new HashMap<>(categorie.size());
        for (CategoriaInformazioniAggiuntiveSilpDTO cat : categorie) {
            result.put(cat.getCodCategInfoAggMin(), cat.getIdSilTCategInfoAgg());
        }
        return result;
    }

    /**
     * Save configurazione informazioni aggiuntive.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @param conf                the conf
     * @return the configurazione informazione aggiuntiva
     * @throws Exception the exception
     */
    public ConfigurazioneInformazioneAggiuntiva saveConfigurazioneInformazioniAggiuntive(String codiceFiscaleUtente, ConfigurazioneInformazioneAggiuntiva conf)
            throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            Map<String, Long> categorie = getMapCategorieInformazioniAggiuntive();
            CallInfoSilpDTO callInfo = new CallInfoSilpDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtente);
            InformazioniAggiuntiveSilpDTO data = new InformazioniAggiuntiveSilpDTO();
            data.setIdSilTInfoAggiuntive(conf.getCodice());
            data.setDsSilTInfoAggiuntive(conf.getDescrizione());
            data.setIdSilTCategoriaInfoAggiuntiva(categorie.get(conf.getCodAmbito()));
            if (conf.getCodiceMinisteriale() == null) {
                // workaround per un bug in SILP.silpserver in attesa di correzione
                data.setCodInfoAggiuntiveMin("");
            } else {
                data.setCodInfoAggiuntiveMin(conf.getCodiceMinisteriale());
            }
            data.setFlgVisibilitaPSLP(conf.getFlagVisibilePslp());
            data.setFlgBloccante(conf.getBloccante());
            data.setFlgValore(conf.getValore());
            data.setDtInizio(conf.getDataInizio());
            data.setDtFine(conf.getDataFine());
            log.debug("[AdapterSilpserver::saveConfigurazioneInformazioniAggiuntive] callInfo=" + callInfo + ", data=" + data);
            EsitoInformazioniAggiuntiveSilpDTO esitoDTO = getGestioneSapService().saveConfigurazioneInformazioniAggiuntiveService(callInfo, data);
            log.debug("[AdapterSilpserver::saveConfigurazioneInformazioniAggiuntive] esitoDTO=" + esitoDTO + ";" + esitoDTO.getDettaglioEsitoAsString());
            if (esitoDTO.isEsitoPositivo()) {
                conf.setCodice(esitoDTO.getInformazioneAggiuntivaSalvata().getIdSilTInfoAggiuntive());
                return conf;
            }
            return null;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "saveConfigurazioneInformazioniAggiuntive()",
                    "invocazione servizio [SILP.silpserver::saveConfigurazioneInformazioniAggiuntiveService]", "");
            watcher.stop();
        }
    }

    /**
     * Load configurazione familiari A carico.
     *
     * @return the configurazione familiari A carico
     * @throws Exception the exception
     */
    public ConfigurazioneFamiliariACarico loadConfigurazioneFamiliariACarico() throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            log.debug("[AdapterSilpserver::loadConfigurazioneFamiliariACarico]");
            ConfigurazioneFamiliariACaricoSilpDTO confDTO = getGestioneSapService().loadConfigurazioneFamiliariACarico();
            ConfigurazioneFamiliariACarico conf = new ConfigurazioneFamiliariACarico();

            List<MotivoFamiliareACarico> elencoCompletoMotivoCarico = new ArrayList<>();
            for (TMotivoCaricoSilpDTO decDTO : confDTO.getElencoCompletoMotivoCarico()) {
                MotivoFamiliareACarico dec = new MotivoFamiliareACarico();
                dec.setIdSilMotivo(decDTO.getIdSilTMotivoCarico());
                dec.setIdSilGradoParentela(decDTO.getIdSilTGradoParentela());
                dec.setDescrizione(decDTO.getDsSilTMotivoCarico());
                elencoCompletoMotivoCarico.add(dec);
            }
            conf.setElencoCompletoMotivoCarico(elencoCompletoMotivoCarico);

            List<MotivoFamiliareACarico> elencoCompletoMotivoCaricoCompresiScaduti = new ArrayList<>();
            for (TMotivoCaricoSilpDTO decDTO : confDTO.getElencoCompletoMotivoCaricoCompresiScaduti()) {
                MotivoFamiliareACarico dec = new MotivoFamiliareACarico();
                dec.setIdSilMotivo(decDTO.getIdSilTMotivoCarico());
                dec.setIdSilGradoParentela(decDTO.getIdSilTGradoParentela());
                dec.setDescrizione(decDTO.getDsSilTMotivoCarico());
                elencoCompletoMotivoCaricoCompresiScaduti.add(dec);
            }
            conf.setElencoCompletoMotivoCaricoCompresiScaduti(elencoCompletoMotivoCaricoCompresiScaduti);

            List<Decodifica> elencoDecodeMotivoCarico = new ArrayList<>();
            for (DecodificaSilpDTO decDTO : confDTO.getElencoDecodeMotivoCarico()) {
                Decodifica dec = new Decodifica();
                dec.setCodiceSilp(decDTO.getIdentificativoDecodifica().toString());
                dec.setDescrizione(decDTO.getDescrizioneDecodifica());
                elencoDecodeMotivoCarico.add(dec);
            }
            conf.setElencoDecodeMotivoCarico(elencoDecodeMotivoCarico);

            List<Decodifica> elencoGradoParentela = new ArrayList<>();
            for (DecodificaSilpDTO decDTO : confDTO.getElencoGradoParentela()) {
                Decodifica dec = new Decodifica();
                dec.setCodiceSilp(decDTO.getIdentificativoDecodifica().toString());
                dec.setDescrizione(decDTO.getDescrizioneDecodifica());
                elencoGradoParentela.add(dec);
            }
            conf.setElencoGradoParentela(elencoGradoParentela);

            return conf;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "loadConfigurazioneFamiliariACarico()",
                    "invocazione servizio [SILP.silpserver::loadConfigurazioneFamiliariACarico]", "");
            watcher.stop();
        }
    }

    /**
     * Carica un oggetto di configurazione con elenchi di dati selezionabili da
     * utilizzare per il profiling di una did.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @return the configurazione profiling did
     * @throws Exception the exception
     */
    public ConfigurazioneProfilingDid loadConfigurazioneProfilingDid(String codiceFiscaleUtente) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            log.debug("[AdapterSilpserver::loadConfigurazioneProfilingDid]");
            CallInfoSilpDTO callInfo = new CallInfoSilpDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtente);
            EsitoConfigurazioneConferimentoDidDTO esitoConfDidSilpDTO = getGestioneProvvService().loadConfigurazioniConferimentoDIDService(callInfo);
            ConfigurazioneProfilingDid conf = new ConfigurazioneProfilingDidMapper().mapReverse(esitoConfDidSilpDTO.getDatiConfigurazione());

            return conf;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "loadConfigurazioneProfilingDid()",
                    "invocazione servizio [SILP.silpserver::loadConfigurazioneProfilingDid]", "");
            watcher.stop();
        }
    }

    /**
     * Find dichiarazioni familiari A carico.
     *
     * @param idSilLavAnagrafica the id sil lav anagrafica
     * @return the vector
     * @throws Exception the exception
     */
    public Vector<RicercaDichiarazioneFamiliariACaricoHeaderDTO> findDichiarazioniFamiliariACarico(Long idSilLavAnagrafica) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            log.debug("[AdapterSilpserver::findDichiarazioniFamiliariACarico]");
            FamiliariACaricoHeaderSilpFilter filter = new FamiliariACaricoHeaderSilpFilter();
            filter.setIdSilLavAnagrafica(idSilLavAnagrafica);
            filter.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            Vector<RicercaDichiarazioneFamiliariACaricoHeaderDTO> dichiarazioniDTO = getGestioneSapService().findDichiarazioniFamiliariACarico(filter);
            return dichiarazioniDTO;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "findDichiarazioniFamiliariACarico()",
                    "invocazione servizio [SILP.silpserver::findDichiarazioniFamiliariACarico]", "");
            watcher.stop();
        }
    }

    /**
     * Load dettaglio dichiarazione familiari carico.
     *
     * @param idSilLavAnagrafica           the id sil lav anagrafica
     * @param idParentelaCaricoSelezionato the id parentela carico selezionato
     * @return the dettaglio completo dichiarazione familiari A carico DTO
     * @throws Exception the exception
     */
    public DettaglioCompletoDichiarazioneFamiliariACaricoDTO loadDettaglioDichiarazioneFamiliariCarico(Long idSilLavAnagrafica,
            Long idParentelaCaricoSelezionato) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            log.debug("[AdapterSilpserver::loadDettaglioDichiarazioneFamiliariCarico]");
            DettaglioCompletoDichiarazioneFamiliariACaricoDTO dettaglioDTO = getGestioneSapService()
                    .loadDettaglioDichiarazioneFamiliariCarico(idSilLavAnagrafica, idParentelaCaricoSelezionato, false);

            return dettaglioDTO;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "loadDettaglioDichiarazioneFamiliariCarico()",
                    "invocazione servizio [SILP.silpserver::loadDettaglioDichiarazioneFamiliariCarico]", "");
            watcher.stop();
        }
    }

    /*
     * public DettaglioCompletoDichiarazioneFamiliariACaricoDTO
     * copiaFamiliariUltimaDichiarazione(Long idSilLavAnagrafica) throws Exception {
     * StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME); watcher.start();
     * try { log.debug("[AdapterSilpserver::copiaFamiliariUltimaDichiarazione]");
     * DettaglioCompletoDichiarazioneFamiliariACaricoDTO dettaglioDTO =
     * getGestioneSapService().copiaFamiliariUltimaDichiarazione(idSilLavAnagrafica)
     * ; return dettaglioDTO; } finally {
     * watcher.dumpElapsed(AdapterSilpserver.class.getName(),
     * "copiaFamiliariUltimaDichiarazione()",
     * "invocazione servizio [SILP.silpserver::copiaFamiliariUltimaDichiarazione]",
     * ""); watcher.stop(); } }
     */

    /**
     * Save familiari A carico.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @param idSilLavAnagrafica  the id sil lav anagrafica
     * @param ilDettaglio         the il dettaglio
     * @return the esito save dichiarazione
     * @throws Exception the exception
     */
    @SuppressWarnings("finally")
    public EsitoSaveDichiarazione saveFamiliariACarico(String codiceFiscaleUtente, Long idSilLavAnagrafica,
            DettaglioCompletoDichiarazioneFamiliariACarico ilDettaglio) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        EsitoSaveDichiarazione loEsito = new EsitoSaveDichiarazione();
        try {
            log.debug("[AdapterSilpserver::saveFamiliariACarico]");
            CallInfoSilpDTO callInfo = new CallInfoSilpDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtente);

            DettaglioCompletoDichiarazioneFamiliariACaricoDTO ilDettaglioDTO = new DettaglioCompletoDichiarazioneFamiliariMapper().map(ilDettaglio);
            ilDettaglioDTO.setIdSilLavAnagrafica(idSilLavAnagrafica);
            SaveEsitoFamiliariACaricoSilpDTO esitoDTO = getGestioneSapService().saveFamiliariACarico(callInfo, ilDettaglioDTO);
            DettaglioCompletoDichiarazioneFamiliariACarico laDichiarazione = new DettaglioCompletoDichiarazioneFamiliariMapper()
                    .mapReverse(esitoDTO.getDettaglioSalvato());
            loEsito.setDichiarazione(laDichiarazione);
            loEsito.setMessaggioErrore(esitoDTO.getMessaggiAllAsString());
            loEsito.setCodiceFiscale(codiceFiscaleUtente);
        } catch (Exception e) {
            log.error("[AdapterSilpserver::saveFamiliariACarico]", e);
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "saveFamiliariACarico()",
                    "Errore nella invocazione servizio [SILP.silpserver::saveFamiliariACarico]", e.getMessage());
            loEsito.setMessaggioErrore("KO-saveFamiliariACarico");
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "saveFamiliariACarico()", "invocazione servizio [SILP.silpserver::saveFamiliariACarico]",
                    "");
            watcher.stop();
            return loEsito;
        }
    }

    /**
     * Save profiling did service.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @param datiProfilingiDid   the dati profilingi did
     * @return the esito save profiling did
     * @throws Exception the exception
     */
    public EsitoSaveProfilingDid saveProfilingDidService(String codiceFiscaleUtente, DatiInputProfilingDid datiProfilingiDid) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        EsitoSaveProfilingDid loEsito = new EsitoSaveProfilingDid();
        try {
            log.debug("[AdapterSilpserver::saveProfilingDidService]");
            CallInfoSilpDTO callInfo = new CallInfoSilpDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtente);
            DatiInputProfilingDidDTO ilDto = new DatiInputProfilingDidMapper().map(datiProfilingiDid);

            EsitoProfilingDidDTO esitoConfDidSilpDTO = getGestioneProvvService().saveProfilingDidService(callInfo, ilDto);
            loEsito.setIdDatiProfiling(esitoConfDidSilpDTO.getIdDatiProfiling());
            loEsito.setMessaggioCittadino(esitoConfDidSilpDTO.getMessaggiAllAsString());
            return loEsito;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "saveProfilingDidService()",
                    "invocazione servizio [SILP.silpserver::saveProfilingDidService]", "");
            watcher.stop();
        }
    }

    /**
     * Save did service.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @param datiDellaDid        the dati della did
     * @param soloControlli       the solo controlli
     * @return the esito save did
     * @throws Exception the exception
     */
    public EsitoSaveDid saveDidService(String codiceFiscaleUtente, DatiInputAggiornamentoDid datiDellaDid, boolean soloControlli) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        EsitoSaveDid loEsito = new EsitoSaveDid();
        try {
            log.debug("[AdapterSilpserver::saveDidService]");
            CallInfoSilpDTO callInfo = new CallInfoSilpDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtente);

            /**
             * 14/10/2020: il metodo. e' da chiamare con i seguenti
             * campi in caso di primo inserimento did CodiceFiscale IdAnagrafica DataDid
             * CodStatoDid = I DataStatoDid
             * 
             * nella prima chiamata si deve impostare ModalitaSoloControlli(true);
             * 
             * se in EsitoAggiornamentoDidDTO.getHeader torna - EsitoAggiornamentoDidDTO.
             * COD_ESITO_IS_DID vuol dire che c'e' un' altra did presente COD_ESITO_IS_RAPP
             * vuol dire che ci sono dei rapporti di lavoro attivi COD_ESITO_NO_DOM_PIEMONTE
             * residenza fuori piemonte
             * 
             * se non tornano errori allora si puo' richiamare lo stesso metodo con
             * ModalitaSoloControlli = false
             * 
             * 
             * se parametro pslp DID_CONF_AUTO impostato a TRUE richiamare lo stesso metodo
             * con CodiceFiscale IdAnagrafica DataDid CodStatoDid = C DataStatoDid idDid =
             * ottenuto dalla prima chiamata
             */

            DatiInputAggiornamentoDidDTO ilDtoSilp = new DatiInputAggiornamentoDidMapper().map(datiDellaDid);
            /* PRIMA CHIAMATA AL SERVIZIO */
            ilDtoSilp.setModalitaSoloControlli(soloControlli);
            EsitoAggiornamentoDidDTO esitoAggiornamentoDidSilpDTO = getGestioneProvvService().aggiornamentoDidService(callInfo, ilDtoSilp);

            loEsito.setHeader(esitoAggiornamentoDidSilpDTO.getHeader());
            loEsito.setIdDid(esitoAggiornamentoDidSilpDTO.getIdDid());
            loEsito.setMessaggioCittadino(esitoAggiornamentoDidSilpDTO.getMessaggiAllAsString());
            if (null != esitoAggiornamentoDidSilpDTO.getMessaggiAllAsArray() && esitoAggiornamentoDidSilpDTO.getMessaggiAllAsArray().length > 0) {
                if (null != esitoAggiornamentoDidSilpDTO.getMessaggiErrorAsArray() && esitoAggiornamentoDidSilpDTO.getMessaggiErrorAsArray().length > 0) {
                    List<MappaErrori> listErrori = new ArrayList<MappaErrori>();
                    for (MessaggioEsito error : esitoAggiornamentoDidSilpDTO.getMessaggiErrorAsArray()) {
                        MappaErrori map = new MappaErrori();
                        if (null != error.getCodice()) {
                            map.setCodiceMessaggio(error.getCodice());
                            map.setDescrMessaggio(error.getMessaggio());
                        } else if (error.getMessaggio().contains("(E")) {
                            String codiceErrore = error.getMessaggio().substring(error.getMessaggio().indexOf("("), error.getMessaggio().indexOf(")"));
                            map.setCodiceMessaggio(codiceErrore);
                            map.setDescrMessaggio(error.getMessaggio());
                        }
                        listErrori.add(map);
                    }
                    loEsito.setError(listErrori);
                    loEsito.setMessaggioErrori(esitoAggiornamentoDidSilpDTO.getMessaggiErrorAsString());
                } else if (null != esitoAggiornamentoDidSilpDTO.getMessaggiInfoAsArray() && esitoAggiornamentoDidSilpDTO.getMessaggiInfoAsArray().length > 0) {
                    List<MappaErrori> listInfo = new ArrayList<MappaErrori>();
                    for (MessaggioEsito info : esitoAggiornamentoDidSilpDTO.getMessaggiInfoAsArray()) {
                        MappaErrori map = new MappaErrori();
                        map.setCodiceMessaggio(info.getCodice());
                        map.setDescrMessaggio(info.getMessaggio());
                        listInfo.add(map);
                    }
                    loEsito.setInfo(listInfo);
                    loEsito.setMessaggioInfo(esitoAggiornamentoDidSilpDTO.getMessaggiInfoAsString());
                } else if (null != esitoAggiornamentoDidSilpDTO.getMessaggiWarningAsArray()
                        && esitoAggiornamentoDidSilpDTO.getMessaggiWarningAsArray().length > 0) {
                    List<MappaErrori> listWarning = new ArrayList<MappaErrori>();
                    for (MessaggioEsito warning : esitoAggiornamentoDidSilpDTO.getMessaggiWarningAsArray()) {
                        MappaErrori map = new MappaErrori();
                        map.setCodiceMessaggio(warning.getCodice());
                        map.setDescrMessaggio(warning.getMessaggio());
                        listWarning.add(map);
                    }
                    loEsito.setWarning(listWarning);
                }
            }
            return loEsito;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "saveDidService()", "invocazione servizio [SILP.silpserver::saveDidService]", "");
            watcher.stop();
        }
    }

    /**
     * Ricerca dettaglio DID service.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @param idSilLavAnagrafica  the id sil lav anagrafica
     * @return the esito dettaglio did
     * @throws Exception the exception
     */
    @SuppressWarnings("finally")
    public EsitoDettaglioDid ricercaDettaglioDIDService(String codiceFiscaleUtente, Long idSilLavAnagrafica) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        EsitoDettaglioDid esitoRicercaDid = null;
        try {
            log.debug("[AdapterSilpserver::ricercaDettaglioDIDService]");
            CallInfoSilpDTO callInfo = new CallInfoSilpDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtente);
            EsitoRicercaDidService esitoRicercaDidServiceDto = getGestioneProvvService().ricercaDettaglioDIDService(callInfo, idSilLavAnagrafica);
            if (null != esitoRicercaDidServiceDto && null != esitoRicercaDidServiceDto.getDid() && null != esitoRicercaDidServiceDto.getDid().getIdDid()) {
                /* bisogna mappare il risultato della did */
                esitoRicercaDid = new EsitoDettaglioDid();
                esitoRicercaDid.setIdDid(esitoRicercaDidServiceDto.getDid().getIdDid());
                esitoRicercaDid.setDataDid(esitoRicercaDidServiceDto.getDid().getDataDid());
                if (null != esitoRicercaDidServiceDto.getDid().getStatoCorrenteOUltimoNonRespinto()) {
                    esitoRicercaDid.setCodUltimoStato(esitoRicercaDidServiceDto.getDid().getStatoCorrenteOUltimoNonRespinto().getCodStatoDid());
                    esitoRicercaDid.setDataStato(esitoRicercaDidServiceDto.getDid().getStatoCorrenteOUltimoNonRespinto().getdStato());
                    esitoRicercaDid.setFlgAttesaInvio(esitoRicercaDidServiceDto.getDid().getStatoCorrenteOUltimoNonRespinto().getFlgAttesaInvio());
                    esitoRicercaDid.setFlgStatoFinale(esitoRicercaDidServiceDto.getDid().getStatoCorrenteOUltimoNonRespinto().getFlgStatoFinale());
                }
                esitoRicercaDid.setDataDidRespinta(esitoRicercaDidServiceDto.getDid().getDataUltimaDidRespinta());
                esitoRicercaDid.setFlgRifiuto(esitoRicercaDidServiceDto.getDid().getFlgDidRespinta());
                esitoRicercaDid.setMotivoRifiuto(esitoRicercaDidServiceDto.getDid().getMotivoRespingimento());
                if (null != esitoRicercaDidServiceDto.getDid().getUltimoProfilingDid()) {
                    DatiProfilingDid ilProf = new DatiProfilingDidMapper().mapReverse(esitoRicercaDidServiceDto.getDid().getUltimoProfilingDid());
                    esitoRicercaDid.setDatiProfilingDid(ilProf);
                }

                esitoRicercaDid.setEnteTitolarita(esitoRicercaDidServiceDto.getDid().getDescEnteTitolareDid());
                esitoRicercaDid.setFlgEnteTitolaritaPiemontese(esitoRicercaDidServiceDto.getDid().isTitolaritaPiemontese() == true ? "S" : "N");
                if (null != esitoRicercaDidServiceDto.getDid().getQuestionario() && null != esitoRicercaDidServiceDto.getDid().getQuestionario().getRisposte()
                        && !esitoRicercaDidServiceDto.getDid().getQuestionario().getRisposte().isEmpty()) {
                    Long result = 0l;
                    for (int i = 0; i < esitoRicercaDidServiceDto.getDid().getQuestionario().getRisposte().size(); i++) {
                        RispostaQuestionarioDettaglioSilpDTO risp = esitoRicercaDidServiceDto.getDid().getQuestionario().getRisposte().get(i);
                        if (risp.getRispostaScelta().getIdentificativoDecodifica() instanceof Long) {
                            result = ((Long) risp.getRispostaScelta().getIdentificativoDecodifica()).longValue();
                            if (result == 17061) {
                                esitoRicercaDid.setFlgRapportoLavoroAperto("S");
                            }
                            if (result == 17062) {
                                esitoRicercaDid.setFlgRapportoLavoroAperto("N");
                            }
                            if (result == 17063) {
                                esitoRicercaDid.setFlgPercettore("S");
                            }
                            if (result == 17064) {
                                esitoRicercaDid.setFlgPercettore("N");
                            }
                        }
                    }

                }
                if (null != esitoRicercaDidServiceDto.getDid().getQuestionario() && null != esitoRicercaDidServiceDto.getDid().getQuestionario().getRisposte()
                        && !esitoRicercaDidServiceDto.getDid().getQuestionario().getRisposte().isEmpty()) {
                    List<ConfigurazioneRisposta> listaRisposte = new ArrayList<ConfigurazioneRisposta>();
                    for (int i = 0; i < esitoRicercaDidServiceDto.getDid().getQuestionario().getRisposte().size(); i++) {
                        ConfigurazioneRisposta laRisp = new ConfigurazioneRisposta();
                        RispostaQuestionarioDettaglioSilpDTO laRisposta = esitoRicercaDidServiceDto.getDid().getQuestionario().getRisposte().get(i);
                        laRisp.setIdDomanda(new Long(laRisposta.getDomanda().getIdentificativoDecodifica().toString()));
                        laRisp.setIdRisposta(new Long(laRisposta.getRispostaScelta().getIdentificativoDecodifica().toString()));
                        listaRisposte.add(laRisp);
                    }
                    esitoRicercaDid.setContenuto(listaRisposte);
                }
            } else if (!esitoRicercaDidServiceDto.getMessaggiAll().isEmpty()) {
                /* GEstire eventuali messaggi di errore restituiti dal servizio */
                esitoRicercaDid = new EsitoDettaglioDid();

                if (null != esitoRicercaDidServiceDto.getMessaggiAllAsArray() && esitoRicercaDidServiceDto.getMessaggiAllAsArray().length > 0) {
                    if (null != esitoRicercaDidServiceDto.getMessaggiErrorAsArray() && esitoRicercaDidServiceDto.getMessaggiErrorAsArray().length > 0) {
                        List<String> listErrori = new ArrayList<String>();
                        for (MessaggioEsito error : esitoRicercaDidServiceDto.getMessaggiErrorAsArray()) {
                            if (null != error.getCodice()) {
                                listErrori.add(error.getCodice());
                            } else if (error.getMessaggio().contains("(E")) {
                                String codiceErrore = error.getMessaggio().substring(error.getMessaggio().indexOf("("), error.getMessaggio().indexOf(")"));
                                listErrori.add(codiceErrore);
                            }
                        }
                        esitoRicercaDid.setError(listErrori);
                    } else if (null != esitoRicercaDidServiceDto.getMessaggiInfoAsArray() && esitoRicercaDidServiceDto.getMessaggiInfoAsArray().length > 0) {
                        List<String> listInfo = new ArrayList<String>();
                        for (MessaggioEsito info : esitoRicercaDidServiceDto.getMessaggiInfoAsArray()) {
                            listInfo.add(info.getCodice());
                        }
                        esitoRicercaDid.setInfo(listInfo);
                    } else if (null != esitoRicercaDidServiceDto.getMessaggiWarningAsArray()
                            && esitoRicercaDidServiceDto.getMessaggiWarningAsArray().length > 0) {
                        List<String> listWarning = new ArrayList<String>();
                        for (MessaggioEsito warning : esitoRicercaDidServiceDto.getMessaggiWarningAsArray()) {
                            listWarning.add(warning.getCodice());
                        }
                        esitoRicercaDid.setWarning(listWarning);
                    }
                }

            }
            log.debug(esitoRicercaDidServiceDto.getDid().getIdDid());
            return esitoRicercaDid;
        } catch (Exception e) {
            log.error("[AdapterSilpserver::ricercaDettaglioDIDService]", e);
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "ricercaDettaglioDIDService()",
                    "Errore nella invocazione servizio [SILP.silpserver::ricercaDettaglioDIDService]", e.getMessage());
            ErrorDef err = new ErrorDef();
            err.setErrorMessage("KO-ricercaDettaglioDIDService");
            esitoRicercaDid = new EsitoDettaglioDid();
            esitoRicercaDid.setErroreRicercaDid(err);
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "ricercaDettaglioDIDService()",
                    "invocazione servizio [SILP.silpserver::ricercaDettaglioDIDService]", "");
            watcher.stop();
            return esitoRicercaDid;
        }
    }

    /**
     * Carica un oggetto di configurazione con elenchi di dati selezionabili da
     * utilizzare per il collocamento mirato.
     *
     * @param codiceFiscaleUtente codice fiscale passato a silpserver come
     *                            operatore.
     * @return la configurazione valorizzata da silpserver
     */
    public ConfigurazioneCollocamentoMirato loadConfigurazioniCollocamentoMirato(String codiceFiscaleUtente) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            log.debug("[AdapterSilpserver::loadConfigurazioniCollocamentoMirato]");
            CallInfoSilpDTO callInfo = new CallInfoSilpDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtente);
            EsitoConfigurazioneCollocamentoMiratoDTO esitoConfSilpDTO = getGestioneSapService().loadConfigurazioniCollocamentoMirato(callInfo);
            ConfigurazioneCollocamentoMirato conf = new ConfigurazioneCollocamentoMirato();
            ConfigurazioneCollocamentoMiratoDTO c = esitoConfSilpDTO.getDatiConfigurazione();
            // * c.getElencoCategoriaInvaliditaDisabili();
            // * c.getElencoCategoriaProtetta();
            // * c.getElencoCategoriaProtettaDisabili();
            // * c.getElencoMotivoIscrizione();
            c.getElencoPossibiliPassaggiStato();
            c.getElencoPresaInCarico();
            // * c.getElencoQualificheNonVedenti();
            // c.getElencoStatiAmmessi(codStato);
            // * c.getElencoStatoIscrizione();
            // * c.getElencoTipoComunicazione();
            // * c.getElencoTipoIscrizione();

            conf.setElencoCategoriaInvaliditaDisabili(mapDecodifiche(c.getElencoCategoriaInvaliditaDisabili()));
            conf.setElencoCategoriaProtetta(mapDecodificheCategoriaProtetta(c.getElencoCategoriaProtetta()));
            conf.setElencoTipoIscrizione(mapDecodifiche(c.getElencoTipoIscrizione()));
            conf.setElencoCategoriaProtettaDisabili(mapDecodificheCategoriaProtettaDisabili(c.getElencoCategoriaProtettaDisabili()));
            conf.setElencoMotivoIscrizione(mapDecodifiche(c.getElencoMotivoIscrizione()));
            conf.setElencoQualificheNonVedenti(mapDecodifiche(c.getElencoQualificheNonVedenti()));
            conf.setElencoStatoIscrizione(mapDecodifiche(c.getElencoStatoIscrizione()));
            // conf.setElencoTipoCategoriaProtetta(mapDecodifiche(c.getElencoCategoriaProtetta()));
            conf.setElencoTipoComunicazione(mapDecodifiche(c.getElencoTipoComunicazione()));
            conf.setLimiteRedditoLordoAnnuoRapportoAutonomo(c.getLimiteRedditoLordoAnnuoRapportoAutonomo());
            conf.setLimiteRedditoLordoAnnuoRapportoSubordinato(c.getLimiteRedditoLordoAnnuoRapportoSubordinato());
            // aggiunto valori statici per poter provare
            conf.setGradoDisabilitaNonVedenti(c.getGradoDisabilitaNonVedenti());
            conf.setGradoDisabilitaSordomuti(c.getGradoDisabilitaSordoMuti());
            conf.setElencoDichiarazioneVisitaRevisioneInvaliditaCivile(mapDecodifiche(c.getElencoDichiarazioneVisitaRevisioneInvaliditaCivile()));
            return conf;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "loadConfigurazioniCollocamentoMirato()",
                    "invocazione servizio [SILP.silpserver::loadConfigurazioniCollocamentoMirato]", "");
            watcher.stop();
        }
    }

    /**
     * Metodo di utilita' per convertire le decodifiche da silpcommonobj a pslweb.
     * 
     * @param in le decodifiche in fomato silpcommonobj
     * @return le decodifiche in fomato pslweb
     */
    private List<CategoriaProtetta> mapDecodificheCategoriaProtetta(List<CategoriaProtettaSilpDTO> in) {
        List<CategoriaProtetta> result = new ArrayList<>();
        if (in != null) {
            for (CategoriaProtettaSilpDTO dIn : in) {
                CategoriaProtetta d = new CategoriaProtetta();
                d.setCodiceMinistero(dIn.getCodMinistero());
                d.setDataFine(dIn.getDtFine());
                d.setDataInizio(dIn.getDtInizio());
                d.setDescrizioneMinistero(dIn.getDsMinistero());
                d.setDescrizioneSilp(dIn.getDsSilTCategoriaProtetta());
                d.setFlgSpeciale(dIn.getFlgSpeciale());
                d.setIdSilp(dIn.getIdSilTCategoriaProtetta());
                result.add(d);
            }
        }
        return result;
    }

    /**
     * Metodo di utilita' per convertire le decodifiche da silpcommonobj a pslweb.
     * 
     * @param in le decodifiche in fomato silpcommonobj
     * @return le decodifiche in fomato pslweb
     */
    private List<CategoriaProtettaDisab> mapDecodificheCategoriaProtettaDisabili(List<CategoriaProtettaDisabSilpDTO> in) {
        List<CategoriaProtettaDisab> result = new ArrayList<>();
        if (in != null) {
            for (CategoriaProtettaDisabSilpDTO dIn : in) {
                CategoriaProtettaDisab d = new CategoriaProtettaDisab();
                d.setDescrizione(dIn.getDsSilTCategProtettaDisab());
                d.setFlgAttivareGradoOCategoria(dIn.getFlgAttivareGradoOCategoria());
                d.setIdSilp(dIn.getIdSilTCategProtettaDisab());
                d.setNumGradoDisabilitaMin(dIn.getNumGradoDisabilitaMin());
                result.add(d);
            }
        }
        return result;
    }

    /**
     * Metodo di utilita' per convertire le decodifiche da silpcommonobj a pslweb.
     * 
     * @param in le decodifiche in fomato silpcommonobj
     * @return le decodifiche in fomato pslweb
     */
    private List<Decodifica> mapDecodifiche(List<DecodificaSilpDTO> in) {
        List<Decodifica> result = new ArrayList<>();
        if (in != null) {
            for (DecodificaSilpDTO dIn : in) {
                Decodifica d = new Decodifica();
                d.setCodiceMinisteriale(dIn.getIdentificativoDecodifica().toString());
                d.setDescrizione(dIn.getDescrizioneDecodifica());
                result.add(d);
            }
        }
        return result;
    }

    /**
     * Metodo di utilita' per convertire le decodifiche
     * StatoRichiestaIscrizioneL68SilpDTO da silpcommonobj a pslweb.
     * 
     * @param in le decodifiche in fomato silpcommonobj
     * @return le decodifiche in fomato pslweb
     */
    private List<Decodifica> mapDecodifiche(Vector<StatoRichiestaIscrizioneL68SilpDTO> elencoStatoIscrizione) {
        List<Decodifica> result = new ArrayList<>();
        if (elencoStatoIscrizione != null) {
            for (DecodificaSilpDTO dIn : elencoStatoIscrizione) {
                Decodifica d = new Decodifica();
                d.setCodiceMinisteriale(dIn.getIdentificativoDecodifica().toString());
                d.setDescrizione(dIn.getDescrizioneDecodifica());
                result.add(d);
            }
        }
        return result;
    }

    /**
     * Carica un oggetto con il risultato della ricerca elenco di tutte le
     * iscrizioni l68 se input solo iidAnagrafica oppure carica la richiesta
     * specifica se valorizzato idRichiesta
     *
     * @param codiceFiscaleUtente codice fiscale passato a silpserver come
     *                            operatore.
     * @return esito della ricerca
     */
    public EsitoRicercaRichiestaIscrizioneCollocamentoMirato findRichiestaIscrizioneCollocamentoMirato(String codiceFiscaleUtente, Long idAnagrafica,
            Long idRichiesta) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        EsitoRicercaRichiestaIscrizioneCollocamentoMirato conf = new EsitoRicercaRichiestaIscrizioneCollocamentoMirato();
        watcher.start();
        try {
            log.debug("[AdapterSilpserver::findRichiestaIscrizioneCollocamentoMirato]");
            CallInfoSilpDTO callInfo = new CallInfoSilpDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtente);
            RichiestaIscrizioneL68HeaderFilter filter = new RichiestaIscrizioneL68HeaderFilter();

            filter.setIdAnagrafica(idAnagrafica);
            filter.setIdRichiesta(idRichiesta);

            EsitoRicercaRichiestaIscrizioneCollocamentoMiratoSilpDTO esitoRicercaSilpDTO = getGestioneSapService()
                    .findRichiestaIscrizioneCollocamentoMirato(callInfo, filter);
            conf = new EsitoRicercaRichiestaIscrizioneCollocamentoMiratoMapper().mapReverse(esitoRicercaSilpDTO);

            return conf;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "findRichiestaIscrizioneCollocamentoMirato()",
                    "invocazione servizio [SILP.silpserver::findRichiestaIscrizioneCollocamentoMirato]", "");
            watcher.stop();
        }
    }

    /**
     * Chiama il salvataggio della richiesta iscrizione
     *
     * @param codiceFiscaleUtente codice fiscale passato a silpserver come
     *                            operatore.
     * @return richiesta risultante dal salvataggio
     */
    public EsitoSaveRichiestaIscrizioneCollocamentoMirato saveRichiestaIscrizioneCollocamentoMirato(String codiceFiscaleUtente, Long idAnagrafica,
            DatiInputSaveRichiestaIscrizioneCollocamentoMirato input) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        EsitoSaveRichiestaIscrizioneCollocamentoMirato conf = new EsitoSaveRichiestaIscrizioneCollocamentoMirato();
        watcher.start();
        try {
            log.debug("[AdapterSilpserver::saveRichiestaIscrizioneCollocamentoMirato]");
            CallInfoSilpDTO callInfo = new CallInfoSilpDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtente);

            DatiInputRichiestaIscrizioneCollocamentoMiratoSilpDTO datiInput = new DatiInputRichiestaIscrizioneCollocamentoMiratoSilpDTO();
            datiInput.setRichiesta(new DettaglioRichiestaIscrizioneL68Mapper().map(input.getRichiesta()));
            datiInput.setStepControllo(input.getStepControllo());

            SaveEsitoRichiestaIscrizioneCollocamentoMiratoSilpDTO esitoSaveSilpDTO = getGestioneSapService().saveRichiestaIscrizioneCollocamentoMirato(callInfo,
                    datiInput);

            conf.setRichiesta(new DettaglioRichiestaIscrizioneL68Mapper().mapReverse(esitoSaveSilpDTO.getRichiesta()));

            conf.setMessaggioCittadino(esitoSaveSilpDTO.getMessaggiAllAsString());
            conf.setEsitoPositivo(esitoSaveSilpDTO.isEsitoPositivo());
            conf.setHeader(esitoSaveSilpDTO.getHeader());

            if (null != esitoSaveSilpDTO.getMessaggiErrorAsArray() && esitoSaveSilpDTO.getMessaggiErrorAsArray().length > 0) {
                List<MappaErrori> listErrori = new ArrayList<MappaErrori>();

                for (MessaggioEsito error : esitoSaveSilpDTO.getMessaggiErrorAsArray()) {
                    MappaErrori map = new MappaErrori();
                    if (null != error.getCodice()) {
                        map.setCodiceMessaggio(error.getCodice());
                        map.setDescrMessaggio(error.getMessaggio());
                    } else if (error.getMessaggio().contains("(E")) {
                        String codiceErrore = error.getMessaggio().substring(error.getMessaggio().indexOf("("), error.getMessaggio().indexOf(")"));
                        map.setCodiceMessaggio(codiceErrore);
                        map.setDescrMessaggio(error.getMessaggio());
                    }
                    listErrori.add(map);
                }
                conf.setError(listErrori);
                conf.setMessaggioErrori(esitoSaveSilpDTO.getMessaggiErrorAsString());
            }

            if (null != esitoSaveSilpDTO.getMessaggiInfoAsArray() && esitoSaveSilpDTO.getMessaggiInfoAsArray().length > 0) {
                List<MappaErrori> listErrori = new ArrayList<MappaErrori>();

                for (MessaggioEsito error : esitoSaveSilpDTO.getMessaggiInfoAsArray()) {
                    MappaErrori map = new MappaErrori();

                    map.setCodiceMessaggio(error.getCodice());
                    map.setDescrMessaggio(error.getMessaggio());

                    listErrori.add(map);
                }
                conf.setInfo(listErrori);
                conf.setMessaggioInfo(esitoSaveSilpDTO.getMessaggiInfoAsString());
            }
            if (null != esitoSaveSilpDTO.getMessaggiWarningAsArray() && esitoSaveSilpDTO.getMessaggiWarningAsArray().length > 0) {
                List<MappaErrori> listErrori = new ArrayList<MappaErrori>();

                for (MessaggioEsito error : esitoSaveSilpDTO.getMessaggiWarningAsArray()) {
                    MappaErrori map = new MappaErrori();

                    map.setCodiceMessaggio(error.getCodice());
                    map.setDescrMessaggio(error.getMessaggio());

                    listErrori.add(map);
                }
                conf.setWarning(listErrori);
                conf.setMessaggioInfo(esitoSaveSilpDTO.getMessaggiWarnAsString());
            }
            return conf;
        } finally {
            watcher.dumpElapsed(AdapterSilpserver.class.getName(), "saveRichiestaIscrizioneCollocamentoMirato()",
                    "invocazione servizio [SILP.silpserver::saveRichiestaIscrizioneCollocamentoMirato]", "");
            watcher.stop();
        }
    }

}
