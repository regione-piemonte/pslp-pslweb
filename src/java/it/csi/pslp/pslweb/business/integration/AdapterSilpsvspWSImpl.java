/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import it.csi.pslp.pslcommonobj.dto.UtenteDTO;
import it.csi.pslp.pslweb.business.be.impl.ConfigurazioneProfiling;
import it.csi.pslp.pslweb.business.common.PslwebRuntimeConfig;
import it.csi.pslp.pslweb.dto.be.AdesioneYG;
import it.csi.pslp.pslweb.dto.be.Comune;
import it.csi.pslp.pslweb.dto.be.DomandaRDC;
import it.csi.pslp.pslweb.dto.be.Ente;
import it.csi.pslp.pslweb.dto.be.Esito;
import it.csi.pslp.pslweb.dto.be.EsitoRiepilogoCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.EsitoSalvataggioRedditoCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.ParametriSalvataggioRedditoCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.ParametriSalvataggioSAP;
import it.csi.pslp.pslweb.dto.be.ProfilingYG;
import it.csi.pslp.pslweb.dto.be.SchedaAnagraficoProfessionale;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.GsonUtils;
import it.csi.pslp.pslweb.util.MappingUtils;
import it.csi.pslp.pslweb.util.mapper.SapLavoratoreMapper;
import it.csi.pslp.pslweb.util.mapper.SapMapper;
import it.csi.pslp.pslweb.util.mapper.collocamentomirato.IscrizioneCollocamentoMiratoMapper;
import it.csi.pslp.pslweb.util.mapper.collocamentomirato.RedditoCollocamentoMiratoMapper;
import it.csi.silos.silcommon.constants.SilCommonConstants;
import it.csi.silos.silcommon.dati.MessaggioEsito;
import it.csi.silos.silcommon.util.SilCommonUtils;
import it.csi.silpcommonobj.dati.collocamentomirato.reddito.EsitoSaveRedditoCollocamentoMiratoDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.riepilogo.EsitoCollocamentoMiratoRiepilogoDTO;
import it.csi.silpcommonobj.dati.decodifiche.MotivoRichiestaSapSilpDTO;
import it.csi.silpcommonobj.dati.redditodicittadinanza.EsitoFindBeneficiarioRdcDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.profiling.EsitoProfilingYGSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.profiling.ProfilingYGSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.sap.EsitoOperazioneSAPSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.sap.SchedaAnagraficoProfessionaleReportDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.sap.TipoVariazioneDTO;
import it.csi.silpcommonobj.util.SilpCommonUtils;
import it.csi.silpservizi.sesp.bo.sap.AdesioniFilter;
import it.csi.silpservizi.sesp.bo.sap.EsitoOperazioneFindAdesioniSilp;
import it.csi.silpservizi.sesp.bo.sap.SchedaAnagraficoProfessionaleLavoratoreDTO;
import it.csi.silpservizi.sesp.bo.sap.YouthGuaranteeSilpDTO;
import it.csi.silpsv.silpsvsp.cxfclient.CollocamentoMiratoRiepilogoSilpFilter;
import it.csi.silpsv.silpsvsp.cxfclient.ProfilingHeaderSilpFilter;
import it.csi.silpsv.silpsvsp.cxfclient.SaveRedditoCollocamentoMiratoInputParamDTO;
import it.csi.silpsv.silpsvsp.cxfclient.SchedaAnagraficoProfessionaleLavoratoreFilter;
import it.csi.silpsv.silpsvsp.cxfclient.WSContainerDTO;
import it.csi.util.performance.StopWatch;

/**
 * The Class AdapterSilpsvspWSImpl.
 */
public class AdapterSilpsvspWSImpl {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The Constant COD_MIN_PIEMONTE. */
    private static final String COD_MIN_PIEMONTE = "13";

    /** The factory. */
    public AdapterSilpsvspWSFactory factory = new AdapterSilpsvspWSFactory();

    /** The instance. */
    private static AdapterSilpsvspWSImpl instance = null;

    /**
     * Gets the single instance of AdapterSilpsvspWSImpl.
     *
     * @return single instance of AdapterSilpsvspWSImpl
     */
    public static AdapterSilpsvspWSImpl getInstance() {
        if (instance == null) {
            instance = new AdapterSilpsvspWSImpl();
        }
        return instance;
    }

    /**
     * Gets the report SAP.
     *
     * @param idSilLavAnagrafica the id sil lav anagrafica
     * @return the report SAP
     * @throws Exception the exception
     */
    public SchedaAnagraficoProfessionaleReportDTO getReportSAP(Long idSilLavAnagrafica) throws Exception {
        return getReportSAP(idSilLavAnagrafica, null);
    }

    /**
     * Gets the report SAP.
     *
     * @param idSilLavAnagrafica the id sil lav anagrafica
     * @param numeroMassimoEsperienzeLavoroSap the numero massimo esperienze lavoro sap
     * @return the report SAP
     * @throws Exception the exception
     */
    public SchedaAnagraficoProfessionaleReportDTO getReportSAP(Long idSilLavAnagrafica, Long numeroMassimoEsperienzeLavoroSap) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        SchedaAnagraficoProfessionaleReportDTO result = new SchedaAnagraficoProfessionaleReportDTO();
        try {
            SchedaAnagraficoProfessionaleLavoratoreFilter filter = new SchedaAnagraficoProfessionaleLavoratoreFilter();
            filter.setIdLavoratore(idSilLavAnagrafica);
            filter.setNumeroMassimoElementiEstrattiInElenchi(numeroMassimoEsperienzeLavoroSap);
            WSContainerDTO sapWS = factory.getService().getSAP(Constants.CLIENT_SILP_NAME, filter);
            SchedaAnagraficoProfessionaleLavoratoreDTO sapLav = (SchedaAnagraficoProfessionaleLavoratoreDTO) GsonUtils.toGsonObject(sapWS.getValue(),
                    SchedaAnagraficoProfessionaleLavoratoreDTO.class);
            result.setSap(sapLav.getSchedaAnagraficaProfessionale());
        } finally {
            watcher.dumpElapsed("AdapterSilpsvspWSImpl", "getSAP()", "invocazione servizio [SILPSV.silpsvsp::getSAP]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Reperisce una sap da silp dato almeno uno tra l'id anagrafica silp/codice
     * fiscale/codice sap.
     *
     * @param idSilLavAnagrafica the id sil lav anagrafica
     * @param codiceFiscale the codice fiscale
     * @param codiceSAP the codice SAP
     * @param numeroMassimoEsperienzeLavoro the numero massimo esperienze lavoro
     * @return the sap
     * @throws Exception the exception
     */

    public SchedaAnagraficoProfessionale getSAP(Long idSilLavAnagrafica, String codiceFiscale, String codiceSAP, Long numeroMassimoEsperienzeLavoro)
            throws Exception {
        return getSAPPrivate(idSilLavAnagrafica, codiceFiscale, codiceSAP, numeroMassimoEsperienzeLavoro);
    }

    /**
     * Metodo privato per cercare una sap in silp, dato l'id anagrafica o la coppia
     * codice fiscale/codice sap.
     *
     * @param idSilLavAnagrafica the id sil lav anagrafica
     * @param codiceFiscale the codice fiscale
     * @param codiceSAP the codice SAP
     * @param numeroMassimoEsperienzeLavoro the numero massimo esperienze lavoro
     * @return the SAP private
     * @throws Exception the exception
     */
    private SchedaAnagraficoProfessionale getSAPPrivate(Long idSilLavAnagrafica, String codiceFiscale, String codiceSAP, Long numeroMassimoEsperienzeLavoro)
            throws Exception {
        log.info(
                "AdapterSilpsvspWSImpl.getSAPPrivate idSilLavAnagrafica=" + idSilLavAnagrafica + " codiceFiscale=" + codiceFiscale + " codiceSAP=" + codiceSAP);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        SchedaAnagraficoProfessionale result = new SchedaAnagraficoProfessionale();
        try {
            SchedaAnagraficoProfessionaleLavoratoreFilter filter = new SchedaAnagraficoProfessionaleLavoratoreFilter();
            filter.setIdLavoratore(idSilLavAnagrafica);
            filter.setCodiceFiscale(codiceFiscale);
            filter.setCodiceSAP(codiceSAP);
            filter.setNumeroMassimoElementiEstrattiInElenchi(numeroMassimoEsperienzeLavoro);
            WSContainerDTO sapWS = factory.getService().getSAP(Constants.CLIENT_SILP_NAME, filter);
            SchedaAnagraficoProfessionaleLavoratoreDTO sap = (SchedaAnagraficoProfessionaleLavoratoreDTO) GsonUtils.toGsonObject(sapWS.getValue(),
                    SchedaAnagraficoProfessionaleLavoratoreDTO.class);
            if (PslwebRuntimeConfig.isLocalExecutionAndrea()) {
                log.debug("AdapterSilpsvspWSImpl::getSAPPrivate log per esecuzione locale della sap ottenuta: " + GsonUtils.toGsonString(sap));
            }
            if (sap == null) {
                return null;
            }
            result = new SapLavoratoreMapper().mapReverse(sap);
            log.debug("AdapterSilpsvspWSImpl.getSAPPrivate ottenuta sap " + result);

        } finally {
            watcher.dumpElapsed("AdapterSilpsvspWSImpl", "getSAPPrivate()", "invocazione servizio [SILPSV.silpsvsp::getSAPPrivate]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Gets the adesione YG.
     *
     * @param idSilLavAnagrafica the id sil lav anagrafica
     * @param idSilLavAdesione the id sil lav adesione
     * @return the adesione YG
     * @throws Exception the exception
     */
    public AdesioneYG getAdesioneYG(Long idSilLavAnagrafica, Long idSilLavAdesione) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        AdesioneYG result = null;
        try {
            AdesioniFilter filter = new AdesioniFilter();
            filter.setIdLavoratore(idSilLavAnagrafica);
            filter.setCodMinRegione(COD_MIN_PIEMONTE);
            WSContainerDTO filterWS = new WSContainerDTO();
            filterWS.setValue(GsonUtils.toGsonString(filter));
            WSContainerDTO esitoWS = factory.getService().findAdesioniYG(Constants.CLIENT_SILP_NAME, filterWS);
            EsitoOperazioneFindAdesioniSilp esito = (EsitoOperazioneFindAdesioniSilp) GsonUtils.toGsonObject(esitoWS.getValue(),
                    EsitoOperazioneFindAdesioniSilp.class);
            if (!esito.isEsitoPositivo()) {
                throw new Exception("Errore nella chiamata a silpsvsp.findAdesioniYG: " + esito.getMessaggiErrorAsString());
            }
            log.debug("AdapterSilpsvspWSImpl.getAdesioneYG ottenute n." + esito.getAdesioni().size() + " adesioni piemontesi le analizzo...");

            // Mantiene l'adesione scelta in base all'analisi di quelle silp, l'ultima
            // aperta (stato non finale) oppure l'ultima in assoluto in assenza di
            // aperte
            YouthGuaranteeSilpDTO adesioneDaConsiderare = null;
            int conta = 0;
            if (idSilLavAdesione != null) {
                for (YouthGuaranteeSilpDTO adesioneDTO : esito.getAdesioni()) {
                    log.debug("AdapterSilpsvspWSImpl.getAdesioneYG adesione=" + adesioneDTO.getId() + " - " + adesioneDTO);
                    if (idSilLavAdesione.equals(adesioneDTO.getId())) {
                        adesioneDaConsiderare = adesioneDTO;
                    }
                }
            }

            else {
                for (YouthGuaranteeSilpDTO adesioneDTO : esito.getAdesioni()) {
                    log.debug("AdapterSilpsvspWSImpl.getAdesioneYG adesione=" + adesioneDTO.getId() + " - " + adesioneDTO);
                    // La salvo comunque essendo la piu' recente in assoluto, da mantenere
                    // se non ne trovo di aperte
                    if (adesioneDaConsiderare == null) {

                        adesioneDaConsiderare = adesioneDTO;
                    }

                    boolean statoCorrenteFinale = adesioneDTO.getStatoCorrenteFinale() != null ? adesioneDTO.getStatoCorrenteFinale() : false;
                    if (statoCorrenteFinale) {
                        log.debug("AdapterSilpsvspWSImpl.getAdesioneYG stato corrente finale, passo alla successiva");
                    } else {
                        log.debug("AdapterSilpsvspWSImpl.getAdesioneYG stato corrente aperto, mantengo questa");
                        adesioneDaConsiderare = adesioneDTO;

                        conta++;
                        break;
                    }

                }
            }
            log.info("AdapterSilpsvspWSImpl.getAdesioneYG adesione finale considerata: =" + adesioneDaConsiderare);
            // Alla fine dell'analisi converto quella che ho deciso di mantenere
            result = convertAdesioneSilpInAdesionePslp(adesioneDaConsiderare, conta);

        } finally {
            watcher.dumpElapsed("AdapterSilpsvspWSImpl", "getAdesioneYG()", "invocazione servizio [SILPSV.silpsvsp::findAdesioniYG]", "");
            watcher.stop();
        }
        return result;

    }

    /**
     * Converte un oggetto adesione ottenuto dal servizio silp
     * (YouthGuaranteeSilpDTO) in un oggetto adesione utilizzato all'interno di pslp
     * (AdesioneYG).
     *
     * @param adesioneDTO the adesione DTO
     * @param numAdesioniAperte the num adesioni aperte
     * @return the adesione YG
     */
    private AdesioneYG convertAdesioneSilpInAdesionePslp(YouthGuaranteeSilpDTO adesioneDTO, int numAdesioniAperte) {
        if (adesioneDTO == null) {
            return null;
        }
        AdesioneYG result = new AdesioneYG();
        result.setCodice(adesioneDTO.getCodiceStatoCorrente());
        result.setDataAdesione(adesioneDTO.getDataAdesione());
        result.setDataStatoCorrente(adesioneDTO.getDataStatoCorrente());
        result.setIdSilLavAdesione(adesioneDTO.getId());
        result.setCodiceFiscale(adesioneDTO.getCodiceFiscale());
        result.setIdentificativoSap(adesioneDTO.getIdentificativoSap());
        result.setRegione(adesioneDTO.getRegioneAdesione());

        result.setMotivoRifiutoStatoCorrente(adesioneDTO.getMotivoRifiutoStatoCorrente());

        result.setDataRifiuto(adesioneDTO.getDataRifiutoStatoCorrente());
        boolean statoCorrenteFinale = adesioneDTO.getStatoCorrenteFinale() != null ? adesioneDTO.getStatoCorrenteFinale() : false;
        if (statoCorrenteFinale) {
            result.setStatoCorrenteFinale(SilCommonConstants.S);
        } else {
            result.setStatoCorrenteFinale(SilCommonConstants.N);
        }

        if (adesioneDTO.getFlgAnpalStatoCorrente() != null) {
            result.setFlgAnpalStatoCorrente(adesioneDTO.getFlgAnpalStatoCorrente());
        } else {
            result.setFlgAnpalStatoCorrente(SilCommonConstants.N);
        }
        if (adesioneDTO.getFlgRifiutoStatoCorrente() != null) {
            result.setFlgRifiutoStatoCorrente(adesioneDTO.getFlgRifiutoStatoCorrente());
        } else {
            result.setFlgRifiutoStatoCorrente(SilCommonConstants.N);
        }
        if (numAdesioniAperte > 1) {
            result.setPresenzaPiuAdesioniAperte(true);
        } else {
            result.setPresenzaPiuAdesioniAperte(false);
        }
        return result;
    }

    /**
     * Gets the profiling YG.
     *
     * @param idSilLavAnagrafica the id sil lav anagrafica
     * @param codiceFiscaleUtente the codice fiscale utente
     * @return the profiling YG
     * @throws Exception the exception
     */
    public ProfilingYG getProfilingYG(Long idSilLavAnagrafica, String codiceFiscaleUtente) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        ProfilingYG result = null;
        try {
            ProfilingHeaderSilpFilter filterWS = new ProfilingHeaderSilpFilter();
            filterWS.setIdLavoratore(idSilLavAnagrafica);
            // filterWS.setIdProvincia(codiceMinisterialeProvincia);
            WSContainerDTO esitoWSContainer = factory.getService().findProfilingYGJSON(Constants.CLIENT_SILP_NAME, filterWS);

            EsitoProfilingYGSilpDTO esitoWS = GsonUtils.toGsonObject(esitoWSContainer.getValue(), EsitoProfilingYGSilpDTO.class);
            if (esitoWS != null && esitoWS.getProfiling() != null) {
                ConfigurazioneProfiling configurazione = AdapterSilpserver.getInstance().loadConfigurazioneProfiling(codiceFiscaleUtente);
                ProfilingYGSilpDTO profilingDTO = esitoWS.getProfiling();
                result = new ProfilingYG();
                result.setIndice(profilingDTO.getIndice2());
                result.setDescrizioneIndice(profilingDTO.getDescrizioneIndice2());
                result.setDataProfiling(profilingDTO.getDataProfiling());
                result.setEta(profilingDTO.getEta());
                result.setCodiceMinisterialeProvincia(profilingDTO.getCodiceProvincia());
                result.setGenere(profilingDTO.getSesso());
                result.setCondizioneOccupazionale(
                        configurazione.getCondizioneOccupazionalePerCodiceMinisteriale(profilingDTO.getCodiceCondizioneOccupazionale()));
                result.setTitoloStudio(configurazione.getTitoloStudioPerCodiceMinisteriale(profilingDTO.getCodiceTitoloStudio()));
                result.setMotivoPresenzaInItalia(configurazione.getMotivoPresenzaInItaliaPerCodiceMinisteriale(profilingDTO.getCodicePresenzaInItalia()));
            }
            return result;
        } finally {
            watcher.dumpElapsed("AdapterSilpsvspWSImpl", "getProfilingYG()", "invocazione servizio [SILPSV.silpsvsp::getProfilingYG]", "");
            watcher.stop();
        }
    }

    /**
     * Gets the domanda RDC by SILP.
     *
     * @param idLavoratoreSilp the id lavoratore silp
     * @return the domanda RDC by SILP
     * @throws Exception the exception
     */
    public DomandaRDC getDomandaRDCBySILP(Long idLavoratoreSilp) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        DomandaRDC result = null;
        try {
            WSContainerDTO esitoWSContainer = factory.getService().findBeneficiarioHeader(Constants.CLIENT_SILP_NAME, idLavoratoreSilp);

            EsitoFindBeneficiarioRdcDTO esitoWS = GsonUtils.toGsonObject(esitoWSContainer.getValue(), EsitoFindBeneficiarioRdcDTO.class);
            if (esitoWS.isEsitoPositivo() && esitoWS.getIdSilLavDomandaRdc() != null) {
                result = convertBeneficiarioSilpInDomandaRdcPSLP(esitoWS);
            } else {
                log.error("[AdapterSilpsvspWSImpl::getDomandaRDCBySILP errori nella ricerca in silp: " + esitoWS.getDettaglioEsitoAsString());
            }
            return result;
        } finally {
            watcher.dumpElapsed("AdapterSilpsvspWSImpl", "getDomandaRDCBySILP()", "invocazione servizio [SILPSV.silpsvsp::getDomandaRDCBySILP]", "");
            watcher.stop();
        }
    }

    /**
     * Convert beneficiario silp in domanda rdc PSLP.
     *
     * @param e the e
     * @return the domanda RDC
     */
    private DomandaRDC convertBeneficiarioSilpInDomandaRdcPSLP(EsitoFindBeneficiarioRdcDTO e) {
        DomandaRDC d = new DomandaRDC();
        d.setIdSilLavDomandaRdc(e.getIdSilLavDomandaRdc());
        d.setCodice(e.getCodiceProtocollo());
        d.setDataDomanda(e.getDataDomanda());
        d.setDataNotificaSilp(e.getDataNotificaSilp());
        d.setStatoDomanda(e.getStatoDomanda());
        d.setStatoPoliticaRc1(e.getStatoPoliticaRc1());
        d.setDataStatoPoliticaRc1(e.getDataStatoPoliticaRc1());
        d.setComuneResidenza(new Comune());
        d.getComuneResidenza().setCodiceMinisteriale(e.getCodiceComuneResidenza());
        d.getComuneResidenza().setDescrizione(e.getDescrizioneComuneResidenza());
        d.getComuneResidenza().setCap(e.getCapComuneResidenza());
        d.setEnteResidenza(new Ente());
        d.getEnteResidenza().setCodOperatore(e.getCodOperatore());
        d.getEnteResidenza().setGruppoOperatore(e.getGruppoOperatore());
        d.getEnteResidenza().setSubcodice(e.getSubcodice());

        return d;
    }

    /**
     * Send SAP.
     *
     * @param idSilLavAnagrafica the id sil lav anagrafica
     * @param cfUtente the cf utente
     * @param sapPLSP the sap PLSP
     * @param parametriSap the parametri sap
     * @param codiceFiscaleUtente the codice fiscale utente
     * @return the esito
     * @throws Exception the exception
     */
    public Esito sendSAP(Long idSilLavAnagrafica, String cfUtente, SchedaAnagraficoProfessionale sapPLSP, ParametriSalvataggioSAP parametriSap,
            String codiceFiscaleUtente) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        Esito result = null;
        try {
            // Retrieve old SAP
            SchedaAnagraficoProfessionaleLavoratoreFilter filter = new SchedaAnagraficoProfessionaleLavoratoreFilter();
            filter.setIdLavoratore(idSilLavAnagrafica); // meglio passare entrambi
            filter.setCodiceFiscale(cfUtente);
            WSContainerDTO sapWS = factory.getService().getSAP(Constants.CLIENT_SILP_NAME, filter);
            SchedaAnagraficoProfessionaleLavoratoreDTO sap = GsonUtils.toGsonObject(sapWS.getValue(), SchedaAnagraficoProfessionaleLavoratoreDTO.class);
            // Map data into SAP
            Set<String> setSezioni = new HashSet<>();
            setSezioni.addAll(parametriSap.getSezioni());
            SapMapper mapper = new SapMapper();
            mapper.map(sapPLSP, sap.getSchedaAnagraficaProfessionale(), TipoVariazioneDTO.TIPO_VARIAZIONE_02_AGGIORNAMENTO, setSezioni, codiceFiscaleUtente,
                    parametriSap.getCodiceAmbito());
            sap.setMotivoRichiestaSap(Long.valueOf(MotivoRichiestaSapSilpDTO.MOTIVO_RICHIESTA_SAP_AGGIORNAMENTO_SAP));
            String sapJSON = GsonUtils.toGsonString(sap);
            WSContainerDTO sapContainer = new WSContainerDTO();
            sapContainer.setValue(sapJSON);
            log.debug("[AdapterSilpsvspWSImpl::saveSAP] sapJSON da inviare=" + sapJSON);
            WSContainerDTO sendSAP = factory.getService().sendSAP(Constants.CLIENT_SILP_NAME, sapContainer);
            log.debug("[AdapterSilpsvspWSImpl::saveSAP] sendSAP esito=" + sendSAP.getValue());
            // SendSAPResultSILPDTO sendSAPResult =
            // GsonUtils.toGsonObject(sendSAP.getValue(), SendSAPResultSILPDTO.class);
            EsitoOperazioneSAPSilpDTO esitoSendSap = GsonUtils.toGsonObject(sendSAP.getValue(), EsitoOperazioneSAPSilpDTO.class);
            result = new Esito();

            if (esitoSendSap.getIdInterscambioSAP() == null || !esitoSendSap.isEsitoPositivo()) {
                result.setCode(SilCommonConstants.KO);
                result.setMessaggioCittadino(esitoSendSap.getMessaggiErrorAsString("\n"));
            } else {
                result.setCode(SilCommonConstants.OK);
                result.setMessaggioCittadino("OK - Id interscambio SAP: " + esitoSendSap.getIdInterscambioSAP());
            }

            completaEsitoConWarning(result, esitoSendSap);
        } finally {
            watcher.dumpElapsed("AdapterSilpsvspWSImpl", "getSAP()", "invocazione servizio [SILPSV.silpsvsp::getSAP]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Verifica se nell'esito di invio sap sono presenti dei warning e li aggiunge
     * ad un messaggio informativo dell'esito.
     *
     * @param result the result
     * @param esitoSendSap the esito send sap
     */
    private void completaEsitoConWarning(Esito result, EsitoOperazioneSAPSilpDTO esitoSendSap) {
        if (SilCommonUtils.isNotVoid(esitoSendSap.getMessaggiWarning())) {
            log.debug("[AdapterSilpsvspWSImpl::saveSAP] presenti messaggi warning");
            StringBuffer sb = null;
            for (MessaggioEsito mw : esitoSendSap.getMessaggiWarning()) {
                if (sb != null) {
                    sb.append("\n" + mw.getMessaggio() + ";");
                } else {
                    sb = new StringBuffer(mw.getMessaggio() + ";");
                }
            }
            result.setMessaggioInformativo(sb.toString());
        }
    }

    // **************** METODI PER COLLOCAMENTO MIRATO ************************//

    /**
     * Load riepilogo collocamento mirato.
     *
     * @param idSilLavAnagrafica the id sil lav anagrafica
     * @param codiceProvincia the codice provincia
     * @param codiceFiscaleUtente the codice fiscale utente
     * @return the esito riepilogo collocamento mirato
     * @throws Exception the exception
     */
    public EsitoRiepilogoCollocamentoMirato loadRiepilogoCollocamentoMirato(Long idSilLavAnagrafica, String codiceProvincia, String codiceFiscaleUtente)
            throws Exception {

        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        EsitoRiepilogoCollocamentoMirato result = new EsitoRiepilogoCollocamentoMirato();
        try {
            CollocamentoMiratoRiepilogoSilpFilter filterWS = new CollocamentoMiratoRiepilogoSilpFilter();
            filterWS.setIdLavoratore(idSilLavAnagrafica);
            filterWS.setIdProvincia(codiceProvincia);
            filterWS.setCodiceOperatore(codiceFiscaleUtente);
            WSContainerDTO esitoWSContainer = factory.getService().loadRiepilogoCollocamentoMirato(Constants.CLIENT_SILP_NAME, filterWS);

            EsitoCollocamentoMiratoRiepilogoDTO esitoWS = GsonUtils.toGsonObject(esitoWSContainer.getValue(), EsitoCollocamentoMiratoRiepilogoDTO.class);
            if (esitoWS != null && esitoWS.isEsitoPositivo()) {
                IscrizioneCollocamentoMiratoMapper iscrMapper = new IscrizioneCollocamentoMiratoMapper();
                RedditoCollocamentoMiratoMapper redMapper = new RedditoCollocamentoMiratoMapper();
                result.setIscrizioneDisabili(iscrMapper.mapNullableReverse(esitoWS.getIscrizioneDisabili()));
                result.setIscrizioneAltreCategorie(iscrMapper.mapNullableReverse(esitoWS.getIscrizioneAltreCategorie()));
                result.setRedditi(redMapper.mapListReverse(esitoWS.getRedditi()));
            } else {
                result.setMessaggioErrore(esitoWS.getDettaglioEsitoAsString());
            }
            return result;
        } finally {
            watcher.dumpElapsed("AdapterSilpsvspWSImpl", "loadRiepilogoCollocamentoMirato()",
                    "invocazione servizio [SILPSV.silpsvsp::loadRiepilogoCollocamentoMirato]", "");
            watcher.stop();
        }
    }

    /**
     * Save reddito collocamento mirato.
     *
     * @param utente the utente
     * @param params the params
     * @return the esito salvataggio reddito collocamento mirato
     * @throws Exception the exception
     */
    public EsitoSalvataggioRedditoCollocamentoMirato saveRedditoCollocamentoMirato(UtenteDTO utente, ParametriSalvataggioRedditoCollocamentoMirato params)
            throws Exception {

        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        EsitoSalvataggioRedditoCollocamentoMirato result = new EsitoSalvataggioRedditoCollocamentoMirato();
        try {
            SaveRedditoCollocamentoMiratoInputParamDTO paramsWs = new SaveRedditoCollocamentoMiratoInputParamDTO();

            paramsWs.setCodiceApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            paramsWs.setIdAnagrafica(utente.getIdSilLavAngrafica());
            paramsWs.setCodiceFiscale(utente.getCfUtente());
            paramsWs.setCodiceFiscaleOperatore(utente.getCfUtente());
            paramsWs.setAnno(params.getAnno());
            paramsWs.setDataInserimento(MappingUtils.toXMLGregorianCalendar(params.getDataInserimento()));
            paramsWs.setValore(params.getValore());
            paramsWs.setCodiceProvincia(params.getCodiceProvincia());
            paramsWs.setNote(params.getNote());

            if (params.getIdRedditoPerAnnullo() != null) {
                paramsWs.setOperazioneAnnullamento(true);
                paramsWs.setIdRedditoPerAggiornamento(params.getIdRedditoPerAnnullo());
                paramsWs.setMotivoAnnullamento(params.getMotivoAnnullo());

            }

            EsitoSaveRedditoCollocamentoMiratoDTO esitoWS = new EsitoSaveRedditoCollocamentoMiratoDTO();
            WSContainerDTO esitoWSContainer = factory.getService().saveRedditoCollocamentoMirato(Constants.CLIENT_SILP_NAME, paramsWs);
            esitoWS = GsonUtils.toGsonObject(esitoWSContainer.getValue(), EsitoSaveRedditoCollocamentoMiratoDTO.class);
            if (esitoWS != null && esitoWS.isEsitoPositivo()) {
                if (null != esitoWS.getIdAnagrafica()) {
                    result.setIdAnagraficaSilp(esitoWS.getIdAnagrafica());
                } else {
                    result.setIdAnagraficaSilp(utente.getIdSilLavAngrafica());
                }
                result.setCodiceFiscale(utente.getCfUtente());
                result.setReddito(new RedditoCollocamentoMiratoMapper().mapNullableReverse(esitoWS.getReddito()));
            } else {
                result.setMessaggioErrore(SilpCommonUtils.concat(esitoWS.getDescrizioneEsito(), esitoWS.getDettaglioEsitoAsString()));
            }
            return result;
        } finally {
            watcher.dumpElapsed("AdapterSilpsvspWSImpl", "loadRiepilogoCollocamentoMirato()",
                    "invocazione servizio [SILPSV.silpsvsp::saveRedditoCollocamentoMirato]", "");
            watcher.stop();
        }
    }

    /**
     * Gets the adesione per iscrizione.
     *
     * @param idSilLavAnagrafica the id sil lav anagrafica
     * @param idSilLavAdesione the id sil lav adesione
     * @return the adesione per iscrizione
     * @throws Exception the exception
     */
    public AdesioneYG getAdesionePerIscrizione(Long idSilLavAnagrafica, Long idSilLavAdesione) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        AdesioneYG result = null;
        try {
            AdesioniFilter filter = new AdesioniFilter();
            filter.setIdLavoratore(idSilLavAnagrafica);
            // prendi tutte le regioni
            WSContainerDTO filterWS = new WSContainerDTO();
            filterWS.setValue(GsonUtils.toGsonString(filter));
            WSContainerDTO esitoWS = factory.getService().findAdesioniYG(Constants.CLIENT_SILP_NAME, filterWS);
            EsitoOperazioneFindAdesioniSilp esito = (EsitoOperazioneFindAdesioniSilp) GsonUtils.toGsonObject(esitoWS.getValue(),
                    EsitoOperazioneFindAdesioniSilp.class);
            if (!esito.isEsitoPositivo()) {
                throw new Exception("Errore nella chiamata a silpsvsp.findAdesioniYG: " + esito.getMessaggiErrorAsString());
            }
            log.debug("AdapterSilpsvspWSImpl.getAdesionePerIscrizione ottenute n." + esito.getAdesioni().size() + " adesioni piemontesi le analizzo...");

            // Mantiene l'adesione scelta in base all'analisi di quelle silp, l'ultima
            // aperta (stato non finale) oppure l'ultima in assoluto in assenza di
            // aperte
            YouthGuaranteeSilpDTO adesioneDaConsiderare = null;
            int contaAdesioniAperte = 0;
            if (idSilLavAdesione != null) {
                for (YouthGuaranteeSilpDTO adesioneDTO : esito.getAdesioni()) {
                    log.debug("AdapterSilpsvspWSImpl.getAdesionePerIscrizione adesione=" + adesioneDTO.getId() + " - " + adesioneDTO);
                    if (idSilLavAdesione.equals(adesioneDTO.getId())) {
                        adesioneDaConsiderare = adesioneDTO;
                    }
                }
            }

            else {

                for (YouthGuaranteeSilpDTO adesioneDTO : esito.getAdesioni()) {
                    log.debug("AdapterSilpsvspWSImpl.getAdesionePerIscrizione adesione=" + adesioneDTO.getId() + " - " + adesioneDTO);
                    // La salvo comunque essendo la piu' recente in assoluto, da mantenere
                    // se non ne trovo di aperte
                    if (adesioneDaConsiderare == null) {
                        adesioneDaConsiderare = adesioneDTO;
                    }

                    boolean statoCorrenteFinale = adesioneDTO.getStatoCorrenteFinale() != null ? adesioneDTO.getStatoCorrenteFinale() : false;
                    if (statoCorrenteFinale) {
                        log.debug("AdapterSilpsvspWSImpl.getAdesionePerIscrizione stato corrente finale, passo alla successiva");
                    } else {
                        boolean statoCorrenteFinaleAdesioneDaCons = adesioneDaConsiderare.getStatoCorrenteFinale() != null
                                ? adesioneDaConsiderare.getStatoCorrenteFinale()
                                : false;
                        if (statoCorrenteFinaleAdesioneDaCons) {
                            // se l'adesione da considerare non e' aperta prendo questa
                            // se questa non e' rifiutata
                            if (adesioneDTO.getDataRifiutoStatoCorrente() == null) {
                                adesioneDaConsiderare = adesioneDTO;
                                log.debug("AdapterSilpsvspWSImpl.getAdesionePerIscrizione stato corrente aperto, mantengo questa");
                            }
                        }
                        if (adesioneDTO.getDataRifiutoStatoCorrente() == null) {
                            contaAdesioniAperte++;
                        }

                    }

                }
            }
            log.info("AdapterSilpsvspWSImpl.getAdesionePerIscrizione adesione finale considerata: =" + adesioneDaConsiderare);
            // Alla fine dell'analisi converto quella che ho deciso di mantenere
            result = convertAdesioneSilpInAdesionePslp(adesioneDaConsiderare, contaAdesioniAperte);

        } finally {
            watcher.dumpElapsed("AdapterSilpsvspWSImpl", "getAdesionePerIscrizione()", "invocazione servizio [SILPSV.silpsvsp::findAdesioniYG]", "");
            watcher.stop();
        }
        return result;

    }

}
