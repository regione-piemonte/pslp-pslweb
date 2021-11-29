/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import it.csi.pslp.pslweb.dto.be.Ente;
import it.csi.pslp.pslweb.dto.be.Sportello;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.GsonUtils;
import it.csi.silpcommonobj.dati.ente.EnteSilpHeaderDTO;
import it.csi.silpcommonobj.dati.ente.EsitoFindElencoEntiSilpDTO;
import it.csi.silpsv.silpsvin.cxfclient.FindElencoEntiInputParam;
import it.csi.silpsv.silpsvin.cxfclient.WSContainerDTO;
import it.csi.util.performance.StopWatch;

/**
 * The Class AdapterSilpsvinWSImpl.
 */
public class AdapterSilpsvinWSImpl {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The factory. */
    public AdapterSilpsvinWSFactory factory = new AdapterSilpsvinWSFactory();

    /** The instance. */
    private static AdapterSilpsvinWSImpl instance = null;

    /**
     * Gets the single instance of AdapterSilpsvinWSImpl.
     *
     * @return single instance of AdapterSilpsvinWSImpl
     */
    public static AdapterSilpsvinWSImpl getInstance() {
        if (instance == null) {
            instance = new AdapterSilpsvinWSImpl();
        }
        return instance;
    }

    /**
     * Reperisce un elenco di enti da silpsvin.
     *
     * @param codiceFiscaleOperatore the codice fiscale operatore
     * @param codiceMinisterialeComune the codice ministeriale comune
     * @return the list
     * @throws Exception the exception
     */
    public List<Ente> findElencoEnti(String codiceFiscaleOperatore, String codiceMinisterialeComune) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            FindElencoEntiInputParam filter = new FindElencoEntiInputParam();
            filter.setCodiceApplicativoChiamante(Constants.CLIENT_SILP_NAME);
            filter.setCodiceFiscaleOperatore(codiceFiscaleOperatore);
            filter.setTipoRicercaEnte("CPI_S");
            if (codiceMinisterialeComune != null)
                filter.setCodiceComune(codiceMinisterialeComune);
            WSContainerDTO esitoWS = factory.getService().findElencoEnti(Constants.CLIENT_SILP_NAME, filter);
            EsitoFindElencoEntiSilpDTO esitoDTO = (EsitoFindElencoEntiSilpDTO) GsonUtils.toGsonObject(esitoWS.getValue(), EsitoFindElencoEntiSilpDTO.class);
            log.info("[AdapterSilpsvinWSImpl::findElencoEnti] esitoDTO=" + esitoDTO.getCodiceEsito() + " - " + esitoDTO.getDescrizioneEsito());
            if (!esitoDTO.isEsitoPositivo()) {
                throw new Exception("Errore nella chiamata a silpsvin.findElencoEnti: " + esitoDTO.getCodiceEsito() + " - " + esitoDTO.getDescrizioneEsito()
                        + " - " + esitoDTO.getDettaglioEsitoAsString());
            }
            List<Ente> result = new ArrayList<>();
            for (EnteSilpHeaderDTO enteDTO : esitoDTO.getElencoEnti()) {
                if ("CPI".equals(enteDTO.getCodiceTipoEnte())) {
                    Ente ente = new Ente();
                    ente.setCodiceIntermediario(enteDTO.getCodiceIntermediario());
                    ente.setGruppoOperatore(enteDTO.getGruppoOperatoreEnte());
                    if (enteDTO.getCodiceOperatoreEnte() != null)
                        ente.setCodOperatore(Long.parseLong(enteDTO.getCodiceOperatoreEnte()));
                    if (enteDTO.getSubCodiceEnte() != null)
                        ente.setSubcodice(Long.parseLong(enteDTO.getSubCodiceEnte()));
                    ente.setDescrizione(enteDTO.getDescrizioneEnte());
                    ente.setCodiceMinisterialeComune(enteDTO.getCodiceComune());
                    List<Sportello> sportelli = new ArrayList<>();
                    ente.setSportelli(sportelli);
                    Sportello sportelloEnte = new Sportello();
                    sportelloEnte.setGruppoOperatore(ente.getGruppoOperatore());
                    sportelloEnte.setCodiceIntermediario(ente.getCodiceIntermediario());
                    sportelloEnte.setCodOperatore(ente.getCodOperatore());
                    sportelloEnte.setSubcodice(ente.getSubcodice());
                    sportelloEnte.setDescrizioneIndirizzo(indirizzo(enteDTO));
                    sportelloEnte.setDescrizione(ente.getDescrizione());
                    sportelloEnte.setCodiceMinisterialeComune(ente.getCodiceMinisterialeComune());
                    sportelloEnte.setGruppoOperatoreEnteAppartenenza(ente.getGruppoOperatore());
                    sportelloEnte.setCodOperatoreEnteAppartenenza(ente.getCodOperatore());
                    sportelloEnte.setSubcodiceEnteAppartenenza(ente.getSubcodice());
                    sportelli.add(sportelloEnte);
                    for (EnteSilpHeaderDTO sottoEnteDTO : enteDTO.getSottoEnti()) {
                        Sportello sportello = new Sportello();
                        sportello.setGruppoOperatoreEnteAppartenenza(ente.getGruppoOperatore());
                        sportello.setCodOperatoreEnteAppartenenza(ente.getCodOperatore());
                        sportello.setSubcodiceEnteAppartenenza(ente.getSubcodice());
                        sportello.setGruppoOperatore(sottoEnteDTO.getGruppoOperatoreEnte());
                        sportello.setCodiceIntermediario(sottoEnteDTO.getCodiceIntermediario());
                        if (sottoEnteDTO.getCodiceOperatoreEnte() != null)
                            sportello.setCodOperatore(Long.parseLong(sottoEnteDTO.getCodiceOperatoreEnte()));
                        if (sottoEnteDTO.getSubCodiceEnte() != null)
                            sportello.setSubcodice(Long.parseLong(sottoEnteDTO.getSubCodiceEnte()));
                        sportello.setDescrizione(sottoEnteDTO.getDescrizioneEnte());
                        sportello.setDescrizioneIndirizzo(indirizzo(sottoEnteDTO));
                        sportello.setCodiceMinisterialeComune(sottoEnteDTO.getCodiceComune());
                        sportelli.add(sportello);
                    }
                    result.add(ente);
                }
            }
            return result;
        } finally {
            watcher.dumpElapsed("AdapterSilpsvinWSImpl", "findElencoEnti()", "invocazione servizio [SILPSV.silpsvin::findElencoEnti]", "");
            watcher.stop();
        }
    }

    /**
     * Find sportelli map.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @return the map
     * @throws Exception the exception
     */
    public Map<String, Sportello> findSportelliMap(String codiceFiscaleUtente) throws Exception {
        Map<String, Sportello> sportelli = new HashMap<>();
        List<Ente> enti = AdapterSilpsvinWSImpl.getInstance().findElencoEnti(codiceFiscaleUtente, null);
        for (Ente ente : enti) {
            for (Sportello sportello : ente.getSportelli()) {
                String key = sportello.getGruppoOperatore() + "-" + sportello.getCodOperatore() + "-" + sportello.getSubcodice();
                sportelli.put(key, sportello);
            }
        }
        return sportelli;
    }

    /**
     * Indirizzo.
     *
     * @param enteDTO the ente DTO
     * @return the string
     */
    private String indirizzo(EnteSilpHeaderDTO enteDTO) {
        StringBuilder s = new StringBuilder();
        s.append(enteDTO.getIndirizzo());
        return s.toString();
    }

}
