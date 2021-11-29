/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import it.csi.pslp.pslweb.dto.be.AdesioneYG;
import it.csi.pslp.pslweb.dto.be.DatiInputStatoAdesione;
import it.csi.pslp.pslweb.dto.be.EsitoSendStatoAdesione;
import it.csi.pslp.pslweb.dto.be.MappaErrori;
import it.csi.pslp.pslweb.dto.be.ParametriSalvataggioSAP;
import it.csi.pslp.pslweb.dto.be.SchedaAnagraficoProfessionale;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.GsonUtils;
import it.csi.pslp.pslweb.util.mapper.DatiSendStatoAdesioneMapper;
import it.csi.pslp.pslweb.util.mapper.SapMapper;
import it.csi.silos.orchsil.cxfclient.CallInfoDTO;
import it.csi.silos.orchsil.cxfclient.WSContainerDTO;
import it.csi.silos.silcommon.dati.MessaggioEsito;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.EsitoOperazioneInvioNuovaSap;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.EsitoOperazioneSap;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.SchedaAnagraficoProfessionaleDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.adesioneyg.DatiInputSendStatoAdesioneSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.adesioneyg.EsitoSendStatoAdesioneSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.sap.TipoVariazioneDTO;
import it.csi.util.performance.StopWatch;

/**
 * The Class AdapterOrchsilWSImpl.
 */
public class AdapterOrchsilWSImpl {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The factory. */
    public AdapterOrchsilWSFactory factory = new AdapterOrchsilWSFactory();

    /** The instance. */
    private static AdapterOrchsilWSImpl instance = null;

    /**
     * Gets the single instance of AdapterOrchsilWSImpl.
     *
     * @return single instance of AdapterOrchsilWSImpl
     */
    public static AdapterOrchsilWSImpl getInstance() {
        if (instance == null) {
            instance = new AdapterOrchsilWSImpl();
        }
        return instance;
    }

    /**
     * Verifica esistenza SAP.
     *
     * @param codiceFiscaleUtenteCollegato the codice fiscale utente collegato
     * @param codiceFiscaleSAP the codice fiscale SAP
     * @return the esito operazione sap
     */
    public EsitoOperazioneSap verificaEsistenzaSAP(String codiceFiscaleUtenteCollegato, String codiceFiscaleSAP) {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        EsitoOperazioneSap esito = new EsitoOperazioneSap();
        try {

            CallInfoDTO callInfo = new CallInfoDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtenteCollegato);
            WSContainerDTO resultWS = factory.getService().verificaEsistenzaSAP(callInfo, codiceFiscaleSAP);
            log.info("resultWS=" + resultWS.getValue());
            esito = (EsitoOperazioneSap) GsonUtils.toGsonObject(resultWS.getValue(), EsitoOperazioneSap.class);
        } catch (Throwable ex) {
            log.error("AdapterOrchsilWSImpl::verificaEsistenzaSAP", ex);
            esito.addMessaggioError("Errore durante la chiamata al metodo verificaEsistenzaSAP. Exception=" + ex.getMessage());
        } finally {
            watcher.dumpElapsed("AdapterOrchsilWSImpl", "verificaEsistenzaSAP()", "invocazione servizio [SILOS.orchsil::verificaEsistenzaSAP]", "");
            watcher.stop();
        }
        return esito;
    }

    /**
     * Send nuova SAP.
     *
     * @param codiceFiscaleUtenteCollegato the codice fiscale utente collegato
     * @param sap the sap
     * @param parametriSap the parametri sap
     * @return the esito operazione invio nuova sap
     */
    public EsitoOperazioneInvioNuovaSap sendNuovaSAP(String codiceFiscaleUtenteCollegato, SchedaAnagraficoProfessionale sap,
            ParametriSalvataggioSAP parametriSap) {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        EsitoOperazioneInvioNuovaSap esito = new EsitoOperazioneInvioNuovaSap();
        List<String> sezioni = parametriSap.getSezioni();
        try {

            CallInfoDTO callInfo = new CallInfoDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtenteCollegato);
            callInfo.setCodApplicativoDestinatario("SILP");
            Set<String> setSezioni = new HashSet<>();
            for (String sezione : sezioni)
                setSezioni.add(sezione);
            setSezioni.add(SapMapper.DATI_ANAGRAFICI);

            SchedaAnagraficoProfessionaleDTO sapDTO = new SapMapper().map(sap, null, TipoVariazioneDTO.TIPO_VARIAZIONE_01_PRIMO_INSERIMENTO, setSezioni,
                    codiceFiscaleUtenteCollegato, parametriSap.getCodiceAmbito());
            String sapJSON = GsonUtils.toGsonString(sapDTO);
            log.debug("sapJSON nuova sap per SILOS.orchsil =" + sapJSON);
            WSContainerDTO sapContainer = new WSContainerDTO();
            sapContainer.setValue(sapJSON);
            WSContainerDTO resultWS = factory.getService().sendNuovaSAP(callInfo, sapContainer);
            log.debug("resultWS=" + resultWS.getValue());
            esito = (EsitoOperazioneInvioNuovaSap) GsonUtils.toGsonObject(resultWS.getValue(), EsitoOperazioneInvioNuovaSap.class);
            log.info("AdapterOrchsilWSImpl::sendNuovaSAP esito sendNuovaSAP=" + esito);
        } catch (Throwable ex) {
            log.error("AdapterOrchsilWSImpl::sendNuovaSAP", ex);
            esito.addMessaggioError("Errore durante la chiamata al metodo sendNuovaSAP. Exception=" + ex.getMessage());
        } finally {
            watcher.dumpElapsed("AdapterOrchsilWSImpl", "sendNuovaSAP()", "invocazione servizio [SILOS.orchsil::sendNuovaSAP]", "");
            watcher.stop();
        }
        return esito;
    }

    /**
     * Estrai messaggio da errori ministeriali.
     *
     * @param messaggi the messaggi
     * @return the string
     */
    public static String estraiMessaggioDaErroriMinisteriali(String messaggi) {
        String tag = "DescrizioneAnomalia";
        // Non ci sono errori ministeriali
        if (messaggi == null) {
            return messaggi;
        }

        StringBuffer sb = new StringBuffer(messaggi.length());

        if (messaggi.indexOf(tag) >= 0) {
            String regExpPattern = "<" + tag + ">(.*?)</" + tag + ">";
            Pattern patt = Pattern.compile(regExpPattern, Pattern.DOTALL);
            Matcher m = patt.matcher(messaggi);
            while (m.find()) {
                String pezzoStringaMatchataPrimaParentesi = m.group(1);
                // la parte tra parentesi trovata
                pezzoStringaMatchataPrimaParentesi = miglioraDescrizioneMessaggioSeNecessario(pezzoStringaMatchataPrimaParentesi);

                sb.append(pezzoStringaMatchataPrimaParentesi + ";");
            }
        }

        if (messaggi.indexOf("GGP-S01") >= 0) {
            sb.append("Il sistema ministeriale non e' momentaneamente disponibile.");
        }

        return sb.toString();
    }

    /**
     * Migliora descrizione messaggio se necessario.
     *
     * @param pezzoStringaMatchataPrimaParentesi the pezzo stringa matchata prima parentesi
     * @return the string
     */
    private static String miglioraDescrizioneMessaggioSeNecessario(String pezzoStringaMatchataPrimaParentesi) {
        if ("Codice Fiscale Personale".equalsIgnoreCase(pezzoStringaMatchataPrimaParentesi)) {
            pezzoStringaMatchataPrimaParentesi = "Dati anagrafici non consoni al Codice Fiscale";
        }
        return pezzoStringaMatchataPrimaParentesi;
    }

    /**
     * Send stato adesione.
     *
     * @param codiceFiscaleUtenteCollegato the codice fiscale utente collegato
     * @param parametriSend the parametri send
     * @return the esito send stato adesione
     */
    public EsitoSendStatoAdesione sendStatoAdesione(String codiceFiscaleUtenteCollegato, DatiInputStatoAdesione parametriSend) {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        EsitoSendStatoAdesione esito = new EsitoSendStatoAdesione();

        try {

            CallInfoDTO callInfo = new CallInfoDTO();
            callInfo.setCodApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            callInfo.setCodiceFiscaleOperatore(codiceFiscaleUtenteCollegato);
            callInfo.setCodApplicativoDestinatario("SILP");

            DatiInputSendStatoAdesioneSilpDTO statoDTO = (DatiInputSendStatoAdesioneSilpDTO) new DatiSendStatoAdesioneMapper().map(parametriSend);
            String statoAdesioneJSON = GsonUtils.toGsonString(statoDTO);
            log.debug("Dati adesione per SILOS.orchsil =" + statoAdesioneJSON);
            WSContainerDTO sendStatoAdesioneContainer = new WSContainerDTO();
            sendStatoAdesioneContainer.setValue(statoAdesioneJSON);
            WSContainerDTO resultWS = factory.getService().sendStatoAdesione(callInfo, sendStatoAdesioneContainer);
            log.debug("resultWS=" + resultWS.getValue());
            EsitoSendStatoAdesioneSilpDTO esitoSilp = (EsitoSendStatoAdesioneSilpDTO) GsonUtils.toGsonObject(resultWS.getValue(),
                    EsitoSendStatoAdesioneSilpDTO.class);
            log.info("AdapterOrchsilWSImpl::sendStatoAdesione esitoSilp " + esito);
            esito.setIdAdesioneSilp(esitoSilp.getIdAdesione());
            esito.setIdAnagraficaSilp(esitoSilp.getIdAnagrafica());
            List<MappaErrori> warning = new ArrayList<MappaErrori>();

            for (MessaggioEsito error : esitoSilp.getMessaggi()) {
                MappaErrori map = new MappaErrori();
                if (null != error.getCodice() || null != error.getTipo()) {
                    if (error.getCodice() != null) {
                        map.setCodiceMessaggio(error.getCodice());
                    } else {
                        map.setCodiceMessaggio(error.getTipo());
                    }
                    map.setDescrMessaggio(error.getMessaggio());
                    warning.add(map);
                }
            }

            esito.setWarning(warning);

            esito.setMessaggioCittadino(esitoSilp.getMessaggiAllAsString("\n"));
            if (esitoSilp.getIdAdesione() != null) {
                AdesioneYG adesione = AdapterSilpsvspWSImpl.getInstance().getAdesionePerIscrizione(parametriSend.getIdAnagrafica(), esitoSilp.getIdAdesione());
                esito.setAdesione(adesione);
            }

        } catch (Throwable ex) {
            log.error("AdapterOrchsilWSImpl::sendStatoAdesione", ex);
            esito.setMessaggioCittadino("Errore durante la chiamata al metodo sendStatoAdesione. Exception=" + ex.getMessage());
            esito.setCode("ERR");
        } finally {
            watcher.dumpElapsed("AdapterOrchsilWSImpl", "sendStatoAdesione()", "invocazione servizio [SILOS.orchsil::sendStatoAdesione]", "");
            watcher.stop();
        }
        return esito;
    }
}
