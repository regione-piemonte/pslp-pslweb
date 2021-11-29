/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import it.csi.pslp.pslweb.dto.be.CentroPerImpiego;
import it.csi.pslp.pslweb.dto.be.Cittadinanza;
import it.csi.pslp.pslweb.dto.be.Comune;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.MotivoRilascioPermessoSoggiorno;
import it.csi.pslp.pslweb.dto.be.Nazione;
import it.csi.pslp.pslweb.dto.be.Provincia;
import it.csi.pslp.pslweb.dto.be.Sedime;
import it.csi.pslp.pslweb.dto.be.StatoAdesione;
import it.csi.pslp.pslweb.dto.be.StatusExtraUE;
import it.csi.pslp.pslweb.dto.be.TipoConoscenzaInformatica;
import it.csi.pslp.pslweb.dto.be.TipoContratto;
import it.csi.pslp.pslweb.util.Cache;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.GsonUtils;
import it.csi.pslp.pslweb.util.MappingUtils;
import it.csi.silos.silcommon.util.SilCommonUtils;
import it.csi.silos.silcommon.util.SilTimeUtils;
import it.csi.silpcommonobj.dati.decodifiche.ToponimoSilpDTO;
import it.csi.silpcommonobj.dati.incontri.StatoAdesioneSilpDTO;
import it.csi.silpcommonobj.filter.incontri.EsitoFindElencoStatoAdesioneDTO;
import it.csi.silpsv.silpsvde.cxfclient.Albo;
import it.csi.silpsv.silpsvde.cxfclient.Ateco02;
import it.csi.silpsv.silpsvde.cxfclient.CategoriaInquadramento;
import it.csi.silpsv.silpsvde.cxfclient.CertificazioneAttestazione;
import it.csi.silpsv.silpsvde.cxfclient.DecodificaFilter;
import it.csi.silpsv.silpsvde.cxfclient.FindElencoStatoAdesioneInputParam;
import it.csi.silpsv.silpsvde.cxfclient.GradoConInf;
import it.csi.silpsv.silpsvde.cxfclient.GradoConLingue;
import it.csi.silpsv.silpsvde.cxfclient.Informatica;
import it.csi.silpsv.silpsvde.cxfclient.ModalitaLavoro;
import it.csi.silpsv.silpsvde.cxfclient.Regione;
import it.csi.silpsv.silpsvde.cxfclient.TipoDocumentoSoggiorno;
import it.csi.silpsv.silpsvde.cxfclient.TipoLinguaggio;
import it.csi.silpsv.silpsvde.cxfclient.TipoMotivoRilascioPermessoSoggiorno;
import it.csi.silpsv.silpsvde.cxfclient.TipoPatente;
import it.csi.silpsv.silpsvde.cxfclient.TipoQualifica;
import it.csi.silpsv.silpsvde.cxfclient.WSContainerDTO;
import it.csi.util.performance.StopWatch;

/**
 * The Class AdapterSilpsvdeWSImpl.
 */
public class AdapterSilpsvdeWSImpl {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The factory. */
    public AdapterSilpsvdeWSFactory factory = new AdapterSilpsvdeWSFactory();

    /** The instance. */
    private static AdapterSilpsvdeWSImpl instance = null;

    /**
     * Gets the single instance of AdapterSilpsvdeWSImpl.
     *
     * @return single instance of AdapterSilpsvdeWSImpl
     */
    public static AdapterSilpsvdeWSImpl getInstance() {
        if (instance == null) {
            instance = new AdapterSilpsvdeWSImpl();
        }
        return instance;
    }

    /**
     * Find province.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Provincia> findProvince() throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        List<Provincia> result = Cache.getInstance().getEl(Cache.PROVINCE);
        if (result != null)
            return result;

        result = new ArrayList<>();
        try {
            DecodificaFilter filter = new DecodificaFilter();
            List<it.csi.silpsv.silpsvde.cxfclient.Provincia> provinceWS = factory.getService().ricercaProvince(Constants.CLIENT_SILP_NAME, filter);
            for (it.csi.silpsv.silpsvde.cxfclient.Provincia provinciaWS : provinceWS) {
                Provincia provincia = new Provincia();
                provincia.setCodiceMinisteriale(provinciaWS.getIdProvincia());
                provincia.setDescrizione(provinciaWS.getDescProvincia());
                provincia.setTarga(provinciaWS.getDescSiglaProvincia());
                provincia.setCodiceRegioneSilp(provinciaWS.getIdRegione());
                result.add(provincia);
            }
            Cache.getInstance().putEl(Cache.PROVINCE, result);
        } finally {
            watcher.dumpElapsed("AdapterSilpsvdeWSImpl", "findProvince()", "invocazione servizio [SILPSV.silpsvde::ricercaProvince]", "");
            watcher.stop();
        }
        return result;
    }

    public List<CentroPerImpiego> findCpi() throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        List<CentroPerImpiego> result = Cache.getInstance().getEl(Cache.CPI);
        if (result != null)
            return result;

        result = new ArrayList<>();
        try {
            DecodificaFilter filter = new DecodificaFilter();
            List<it.csi.silpsv.silpsvde.cxfclient.Cpi> elencoCpiWS = factory.getService().ricercaCpi(Constants.CLIENT_SILP_NAME, filter);
            for (it.csi.silpsv.silpsvde.cxfclient.Cpi cpiWS : elencoCpiWS) {
                CentroPerImpiego cpi = new CentroPerImpiego();
                cpi.setCodice(cpiWS.getCodCpi());
                cpi.setDescrizione(cpiWS.getDsSilTCpi());
                cpi.setFlgAttivo("S".equalsIgnoreCase(cpiWS.getFlgAttivo()));
                cpi.setCodiceMinisterialeProvincia(cpiWS.getIdProvincia());
                if (cpiWS.getIdCpi() != null)
                    cpi.setIdCpiSilp(Long.parseLong(cpiWS.getIdCpi()));
                result.add(cpi);
            }
            Cache.getInstance().putEl(Cache.CPI, result);
        } finally {
            watcher.dumpElapsed("AdapterSilpsvdeWSImpl", "findCpi()", "invocazione servizio [SILPSV.silpsvde::ricercaCpi]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Gets the map province per targa.
     *
     * @return the map province per targa
     * @throws Exception the exception
     */
    public Map<String, Provincia> getMapProvincePerTarga() throws Exception {
        Map<String, Provincia> map = new HashMap<>();
        for (Provincia provincia : findProvince()) {
            map.put(provincia.getTarga(), provincia);
        }
        return map;
    }

    /**
     * Gets the map province per codice ministeriale.
     *
     * @return the map province per codice ministeriale
     * @throws Exception the exception
     */
    public Map<String, Provincia> getMapProvincePerCodiceMinisteriale() throws Exception {
        Map<String, Provincia> map = new HashMap<>();
        for (Provincia provincia : findProvince()) {
            map.put(provincia.getCodiceMinisteriale(), provincia);
        }
        return map;
    }

    /**
     * Find comuni.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Comune> findComuni() throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        List<Comune> result = Cache.getInstance().getEl(Cache.COMUNI);
        if (result != null)
            return result;

        result = new ArrayList<>();
        Map<String, Comune> mapComuniByCodice = new HashMap<String, Comune>();
        try {
            Map<String, Provincia> province = getMapProvincePerTarga();
            DecodificaFilter filter = new DecodificaFilter();
            List<it.csi.silpsv.silpsvde.cxfclient.Comune> comuniWS = factory.getService().ricercaComuni(Constants.CLIENT_SILP_NAME, filter);
            for (it.csi.silpsv.silpsvde.cxfclient.Comune comuneWS : comuniWS) {
                if (!("S".equals(comuneWS.getFlgAttivo())))
                    continue;
                Comune comune = new Comune();
                comune.setCodiceMinisteriale(comuneWS.getIdComune());
                comune.setDescrizione(comuneWS.getDescComune());
                comune.setProvincia(province.get(comuneWS.getCodProv()));
                if (comuneWS.getIdCpi() != null)
                    comune.setIdCpiSilp(Long.parseLong(comuneWS.getIdCpi()));
                result.add(comune);
                mapComuniByCodice.put(comune.getCodiceMinisteriale(), comune);
            }
            Cache.getInstance().putEl(Cache.COMUNI, result);
            Cache.getInstance().putEl(Cache.COMUNI_MAP_BY_CODICE, mapComuniByCodice);

        } finally {
            watcher.dumpElapsed("AdapterSilpsvdeWSImpl", "findComuni()", "invocazione servizio [SILPSV.silpsvde::ricercaComuni]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Ritorna un oggetto comune caricato da un hashmap per codice. Utile per
     * reperire velocemente il dato della provincia per integrare la sap con
     * descrizioni varie. E' caricato assieme all'elenco comuni nella findComuni
     *
     * @param codiceComune the codice comune
     * @return the comune by codice
     * @throws Exception the exception
     */
    public Comune getComuneByCodice(String codiceComune) throws Exception {
        Map<String, Comune> mapComuniByCodice = Cache.getInstance().getEl(Cache.COMUNI_MAP_BY_CODICE);
        if (mapComuniByCodice == null || mapComuniByCodice.size() == 0) {
            findComuni();
            mapComuniByCodice = Cache.getInstance().getEl(Cache.COMUNI_MAP_BY_CODICE);
        }
        return mapComuniByCodice.get(codiceComune);
    }

    /**
     * Ritorna un oggetto comune caricato da un hashmap per codice. Utile per
     * reperire velocemente il dato della provincia per integrare la sap con
     * descrizioni varie. E' caricato assieme all'elenco comuni nella findComuni
     *
     * @param codiceNazione the codice nazione
     * @return the nazione by codice
     * @throws Exception the exception
     */
    public Nazione getNazioneByCodice(String codiceNazione) throws Exception {
        List<Nazione> nazioni = Cache.getInstance().getEl(Cache.NAZIONI);
        if (nazioni == null || nazioni.isEmpty()) {
            findNazioni();
            nazioni = Cache.getInstance().getEl(Cache.NAZIONI);
        }
        for (Nazione n : nazioni) {
            if (n.getCodiceMinisteriale().equals(codiceNazione)) {
                return n;
            }
        }
        return null;
    }

    /**
     * Find sedimi.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Sedime> findSedimi() throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        List<Sedime> result = new ArrayList<>();
        try {
            DecodificaFilter filter = new DecodificaFilter();
            List<it.csi.silpsv.silpsvde.cxfclient.Toponimo> toponimiWS = factory.getService().ricercaToponimi(Constants.CLIENT_SILP_NAME, filter);
            for (it.csi.silpsv.silpsvde.cxfclient.Toponimo toponimoWS : toponimiWS) {
                Sedime sedime = new Sedime();
                sedime.setCodiceMinisteriale(toponimoWS.getIdToponiomo());
                sedime.setDescrizione(toponimoWS.getDescToponimo());
                result.add(sedime);
            }
        } finally {
            watcher.dumpElapsed("AdapterSilpsvdeWSImpl", "findSedimi()", "invocazione servizio [SILPSV.silpsvde::ricercaToponimi]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Find sedimi.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Sedime> findIlSedime(String codiceMinisterialeSedime) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        List<Sedime> result = new ArrayList<>();
        try {
            DecodificaFilter filter = new DecodificaFilter();
            filter.setCodice(codiceMinisterialeSedime);
            List<it.csi.silpsv.silpsvde.cxfclient.Toponimo> toponimiWS = factory.getService().ricercaToponimi(Constants.CLIENT_SILP_NAME, filter);
            for (it.csi.silpsv.silpsvde.cxfclient.Toponimo toponimoWS : toponimiWS) {
                Sedime sedime = new Sedime();
                sedime.setCodiceMinisteriale(toponimoWS.getIdToponiomo());
                sedime.setDescrizione(toponimoWS.getDescToponimo());
                result.add(sedime);
            }
        } finally {
            watcher.dumpElapsed("AdapterSilpsvdeWSImpl", "findSedimi()", "invocazione servizio [SILPSV.silpsvde::ricercaToponimi]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Find toponimi silp.
     *
     * @return the vector
     * @throws Exception the exception
     */
    public Vector<ToponimoSilpDTO> findToponimiSilp() throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        Vector<ToponimoSilpDTO> result = new Vector<>();
        try {
            DecodificaFilter filter = new DecodificaFilter();
            List<it.csi.silpsv.silpsvde.cxfclient.Toponimo> toponimiWS = factory.getService().ricercaToponimi(Constants.CLIENT_SILP_NAME, filter);
            for (it.csi.silpsv.silpsvde.cxfclient.Toponimo toponimoWS : toponimiWS) {
                ToponimoSilpDTO toponimoSilp = new ToponimoSilpDTO();
                toponimoSilp.setId(toponimoWS.getIdToponiomo());
                toponimoSilp.setDescrizione(toponimoWS.getDescToponimo());
                result.add(toponimoSilp);
            }
        } finally {
            watcher.dumpElapsed("AdapterSilpsvdeWSImpl", "findToponimiSilp()", "invocazione servizio [SILPSV.silpsvde::ricercaToponimi]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Find cittadinanze.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Cittadinanza> findCittadinanze() throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        List<Cittadinanza> result = Cache.getInstance().getEl(Cache.CITTADINANZE);
        if (result != null)
            return result;
        result = new ArrayList<>();
        try {
            DecodificaFilter filter = new DecodificaFilter();
            List<it.csi.silpsv.silpsvde.cxfclient.Cittadinanza> cittadinanzeWS = factory.getService().ricercaCittadinanze(Constants.CLIENT_SILP_NAME, filter);
            for (it.csi.silpsv.silpsvde.cxfclient.Cittadinanza cittadinanzaWS : cittadinanzeWS) {
                Cittadinanza cittadinanza = new Cittadinanza();
                cittadinanza.setCodiceMinisteriale(cittadinanzaWS.getCodiceMinisteriale());
                cittadinanza.setDescrizione(cittadinanzaWS.getDescCittadinanza());
                cittadinanza.setCodiceMinisterialeNazione(cittadinanzaWS.getIdNazione());
                result.add(cittadinanza);
            }
            Cache.getInstance().putEl(Cache.CITTADINANZE, result);
        } finally {
            watcher.dumpElapsed("AdapterSilpsvdeWSImpl", "findCittadinanze()", "invocazione servizio [SILPSV.silpsvde::ricercaCittadinanze]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Find nazioni.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Nazione> findNazioni() throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        List<Nazione> result = Cache.getInstance().getEl(Cache.NAZIONI);
        if (result != null)
            return result;

        result = new ArrayList<>();
        try {
            DecodificaFilter filter = new DecodificaFilter();
            List<it.csi.silpsv.silpsvde.cxfclient.Nazione> nazioniWS = factory.getService().ricercaNazioni(Constants.CLIENT_SILP_NAME, filter);
            for (it.csi.silpsv.silpsvde.cxfclient.Nazione nazioneWS : nazioniWS) {
                Nazione nazione = new Nazione();
                nazione.setCodiceMinisteriale(nazioneWS.getIdNazione());
                nazione.setDescrizione(nazioneWS.getDescNazione());
                nazione.setFlagUe(MappingUtils.toBoolean(nazioneWS.getFlgUe(), "S", "N"));
                result.add(nazione);
            }
            Cache.getInstance().putEl(Cache.NAZIONI, result);
        } finally {
            watcher.dumpElapsed("AdapterSilpsvdeWSImpl", "findNazioni()", "invocazione servizio [SILPSV.silpsvde::ricercaNazioni]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Find stati adesione.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @return the list
     * @throws Exception the exception
     */
    public List<StatoAdesione> findStatiAdesione(String codiceFiscaleUtente) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        List<StatoAdesione> result = new ArrayList<>();
        try {
            FindElencoStatoAdesioneInputParam filter = new FindElencoStatoAdesioneInputParam();
            filter.setCodOperatore(codiceFiscaleUtente);
            WSContainerDTO esitoWS = factory.getService().findElencoStatoAdesioneJSON(Constants.CLIENT_SILP_NAME, filter);
            EsitoFindElencoStatoAdesioneDTO esitoDTO = (EsitoFindElencoStatoAdesioneDTO) GsonUtils.toGsonObject(esitoWS.getValue(),
                    EsitoFindElencoStatoAdesioneDTO.class);
            log.info("[AdapterSilpsvdeWSImpl::findStatiAdesione] esitoDTO=" + esitoDTO.getCodiceEsito() + " - " + esitoDTO.getDescrizioneEsito());
            if (!esitoDTO.isEsitoPositivo()) {
                throw new Exception("Errore nella chiamata a silpsvde.findStatiAdesione: " + esitoDTO.getCodiceEsito() + " - " + esitoDTO.getDescrizioneEsito()
                        + " - " + esitoDTO.getDettaglioEsitoAsString());
            }
            Date now = new Date();
            for (StatoAdesioneSilpDTO statoDTO : esitoDTO.getElencoStatoAdesione()) {
                Date dataInizio = statoDTO.getDataInizio();
                Date dataFine = statoDTO.getDataFine();
                if (dataInizio != null && dataInizio.after(now))
                    continue;
                if (dataFine != null && dataFine.before(now))
                    continue;
                StatoAdesione stato = new StatoAdesione();
                stato.setCodice(statoDTO.getCodAdesioneMin());
                stato.setDescrizione(statoDTO.getDescrizioneMin());
                result.add(stato);
            }
        } finally {
            watcher.dumpElapsed("AdapterSilpsvdeWSImpl", "findStatiAdesione()", "invocazione servizio [SILPSV.silpsvde::findStatiAdesione]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Find map stati adesione.
     *
     * @param codiceFiscaleUtente the codice fiscale utente
     * @return the map
     * @throws Exception the exception
     */
    public Map<String, String> findMapStatiAdesione(String codiceFiscaleUtente) throws Exception {
        Map<String, String> result = new HashMap<>();
        List<StatoAdesione> stati = findStatiAdesione(codiceFiscaleUtente);
        for (StatoAdesione stato : stati) {
            result.put(stato.getCodice(), stato.getDescrizione());
        }
        return result;
    }

    /**
     * Find motivi rilascio permesso soggiorno.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<MotivoRilascioPermessoSoggiorno> findMotiviRilascioPermessoSoggiorno() throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        List<MotivoRilascioPermessoSoggiorno> result = Cache.getInstance().getEl(Cache.MOTIVI_RILASCIO_PERMESSO_SOGGIORNO);
        if (result != null)
            return result;
        result = new ArrayList<>();
        try {
            TipoMotivoRilascioPermessoSoggiorno filter = new TipoMotivoRilascioPermessoSoggiorno();
            List<TipoMotivoRilascioPermessoSoggiorno> tipiMotivoRilascioPermessoSoggiornoWS = factory.getService()
                    .ricercaTipiMotivoRilascioPermessoSoggiorno(Constants.CLIENT_SILP_NAME, filter);
            for (TipoMotivoRilascioPermessoSoggiorno tipoMotivoRilascioPermessoSoggiornoWS : tipiMotivoRilascioPermessoSoggiornoWS) {
                if (!"S".equals(tipoMotivoRilascioPermessoSoggiornoWS.getFlagVigore()))
                    continue;
                MotivoRilascioPermessoSoggiorno motivoRilascioPermessoSoggiorno = new MotivoRilascioPermessoSoggiorno();
                motivoRilascioPermessoSoggiorno.setDescrizione(tipoMotivoRilascioPermessoSoggiornoWS.getDescrTipoMotRilPerm());
                motivoRilascioPermessoSoggiorno.setVigore(tipoMotivoRilascioPermessoSoggiornoWS.getFlagVigore());
                motivoRilascioPermessoSoggiorno.setId(tipoMotivoRilascioPermessoSoggiornoWS.getCodiceMinisteriale());
                result.add(motivoRilascioPermessoSoggiorno);
            }
            Cache.getInstance().putEl(Cache.MOTIVI_RILASCIO_PERMESSO_SOGGIORNO, result);
        } finally {
            watcher.dumpElapsed("AdapterSilpsvdeWSImpl", "findNazioni()", "invocazione servizio [SILPSV.silpsvde::ricercaTipiMotivoRilascioPermessoSoggiorno]",
                    "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Ricerca status extra UE.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<StatusExtraUE> ricercaStatusExtraUE() throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        List<StatusExtraUE> result = Cache.getInstance().getEl(Cache.STATUS_EXTRA_UE);
        if (result != null)
            return result;
        result = new ArrayList<>();
        try {
            TipoDocumentoSoggiorno filter = new TipoDocumentoSoggiorno();
            List<TipoDocumentoSoggiorno> tipiDocumentoSoggiorno = factory.getService().ricercaTipiDocumentoSoggiorno(Constants.CLIENT_SILP_NAME, filter);
            Date now = new Date();
            for (TipoDocumentoSoggiorno tipoDocumentoSoggiorno : tipiDocumentoSoggiorno) {
                Date dataInizio = MappingUtils.toDate(tipoDocumentoSoggiorno.getDataInizio());
                Date dataFine = MappingUtils.toDate(tipoDocumentoSoggiorno.getDataFine());
                if (dataInizio != null && dataInizio.after(now))
                    continue;
                if (dataFine != null && dataFine.before(now))
                    continue;
                StatusExtraUE statusExtraUE = new StatusExtraUE();
                statusExtraUE.setCodiceMinisteriale(tipoDocumentoSoggiorno.getCodiceMinisteriale());
                statusExtraUE.setDescrizione(tipoDocumentoSoggiorno.getDescrizione());
                statusExtraUE.setIdTipoDocumentoSoggiorno(tipoDocumentoSoggiorno.getIdTipoDocumentoSoggiorno());
                statusExtraUE.setDataInizio(MappingUtils.toDate(tipoDocumentoSoggiorno.getDataInizio()));
                statusExtraUE.setDataFine(MappingUtils.toDate(tipoDocumentoSoggiorno.getDataFine()));
                result.add(statusExtraUE);
            }
            Cache.getInstance().putEl(Cache.STATUS_EXTRA_UE, result);
        } finally {
            watcher.dumpElapsed("AdapterSilpsvdeWSImpl", "ricercaStatusExtraUE()", "invocazione servizio [SILPSV.silpsvde::ricercaTipiDocumentoSoggiorno]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Cerca l.elenco di lingue utilizzabili in una sap
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Decodifica> findLingue() throws Exception {
        return genericFindDecodifica(new DecodificaAdapter<TipoLinguaggio>() {
            public String getCacheName() {
                return Cache.LINGUE;
            }

            public List<TipoLinguaggio> callSilpsvdeService() throws Exception {
                return factory.getService().ricercaTipiLinguaggio(Constants.CLIENT_SILP_NAME, new DecodificaFilter());
            }

            public void remapSingleDecodificaObjectFromWS(TipoLinguaggio elem, Decodifica d) {
                d.setCodiceMinisteriale(elem.getCodiceMinisteriale());
                d.setDescrizione(elem.getDescTipoLinguaggio());
                d.setCodiceSilp(elem.getIdTipoLinguaggio());
                d.setDataInizioValidita(MappingUtils.toDate(elem.getDataInizio()));
                d.setDataFineValidita(MappingUtils.toDate(elem.getDataFine()));
            }

            public String getThisMethodName() {
                return "findLingue()";
            }

            public String getSilpsvdeMethodName() {
                return "ricercaTipiLinguaggio";
            }
        });
    }

    /**
     * Cerca le decodifiche relative ai gradi di conoscenza di un alingue.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Decodifica> findGradiConoscenzaLingue() throws Exception {
        return genericFindDecodifica(new DecodificaAdapter<GradoConLingue>() {
            public String getCacheName() {
                return Cache.GRADI_CONOSCENZA_LINGUE;
            }

            public List<GradoConLingue> callSilpsvdeService() throws Exception {
                return factory.getService().ricercaGradiConLingue(Constants.CLIENT_SILP_NAME, new DecodificaFilter());
            }

            public void remapSingleDecodificaObjectFromWS(GradoConLingue elem, Decodifica d) {
                d.setCodiceMinisteriale(elem.getCodMinistero());
                // Si aggiunge il codice nella descrizione perche' riconosciuto universalmente e
                // utile per ordinamento
                d.setDescrizione(elem.getCodMinistero() + " " + elem.getDescGradoConLingue());
                d.setCodiceSilp(elem.getIdGradoConLingue());
                d.setDataInizioValidita(MappingUtils.toDate(elem.getDataInizio()));
                d.setDataFineValidita(MappingUtils.toDate(elem.getDataFine()));
            }

            @Override
            public boolean filterNotValidToday() {
                return true;
            }

            public String getThisMethodName() {
                return "findGradiConoscenzaLingue()";
            }

            public String getSilpsvdeMethodName() {
                return "ricercaGradiConLingue";
            }
        });
    }

    /**
     * Invoca il servizio silpsv.silpsvde per caricare l'elenco di voci relative di
     * conoscenze informatiche tabella silp di riferimento sil_t_informatica_min. In
     * ogni record, oltre ai codici della conoscenza informatica, torna un
     * identificativo della categoria silp di conoscenza informatica, un dato
     * interno di silp che non ha corrispondenza con codici ministeriali.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<TipoConoscenzaInformatica> findConoscenzeInformatiche() throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        List<TipoConoscenzaInformatica> result = Cache.getInstance().getEl(Cache.INFORMATICA);
        if (result != null)
            return result;
        result = new ArrayList<>();
        try {
            List<it.csi.silpsv.silpsvde.cxfclient.Informatica> tipiConoscenzaInformatica = factory.getService()
                    .ricercaConoscenzeInformaticheMin(Constants.CLIENT_SILP_NAME, null);

            for (it.csi.silpsv.silpsvde.cxfclient.Informatica wsItemElement : tipiConoscenzaInformatica) {
                TipoConoscenzaInformatica d = new TipoConoscenzaInformatica();
                d.setCodiceMinisteriale(wsItemElement.getCodiceMinisteriale());
                d.setDescrizione(wsItemElement.getDescInformatica());
                d.setCodiceSilp(wsItemElement.getIdInformatica());
                d.setDataInizioValidita(MappingUtils.toDate(wsItemElement.getDataInizio()));
                d.setDataFineValidita(MappingUtils.toDate(wsItemElement.getDataFine()));
                d.setCodiceCategoriaConoscenzaInformatica(wsItemElement.getIdTipoConoscenzaInformatica());
                result.add(d);
            }
            Cache.getInstance().putEl(Cache.INFORMATICA, result);
        } finally {
            watcher.dumpElapsed("AdapterSilpsvdeWSImpl", "findConoscenzeInformatiche()",
                    "invocazione servizio [SILPSV.silpsvde::ricercaConoscenzeInformaticheMin]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Find categorie conoscenza informatica.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Decodifica> findCategorieConoscenzaInformatica() throws Exception {
        return genericFindDecodifica(new DecodificaAdapter<Informatica>() {
            public String getCacheName() {
                return Cache.CATEGORIA_INFORMATICA;
            }

            public List<Informatica> callSilpsvdeService() throws Exception {
                return factory.getService().ricercaInformatica(Constants.CLIENT_SILP_NAME, new DecodificaFilter());
            }

            public void remapSingleDecodificaObjectFromWS(Informatica elem, Decodifica d) {
                d.setCodiceMinisteriale(elem.getIdInformatica());
                d.setDescrizione(elem.getDescInformatica());
                d.setCodiceSilp(elem.getIdInformatica());
                d.setDataInizioValidita(MappingUtils.toDate(elem.getDataInizio()));
                d.setDataFineValidita(MappingUtils.toDate(elem.getDataFine()));
            }

            public String getThisMethodName() {
                return "findCategorieConoscenzaInformatica()";
            }

            public String getSilpsvdeMethodName() {
                return "ricercaInformatica";
            }
        });
    }

    /**
     * Invoca il servizio silpsv.silpsvde per caricare l'elenco di voci relative al
     * grado di conoscenze informatiche tabella silp di riferimento
     * SIL_T_GRADO_CON_INF
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Decodifica> findGradiConoscenzeInformatiche() throws Exception {
        return genericFindDecodifica(new DecodificaAdapter<GradoConInf>() {
            public String getCacheName() {
                return Cache.GRADI_CONOSCENZA_INFORMATICA;
            }

            public List<GradoConInf> callSilpsvdeService() throws Exception {
                return factory.getService().ricercaGradoConInf(Constants.CLIENT_SILP_NAME, new DecodificaFilter());
            }

            public void remapSingleDecodificaObjectFromWS(GradoConInf elem, Decodifica d) {
                d.setCodiceMinisteriale(elem.getCodiceMinisteriale());
                d.setDescrizione(elem.getDescGradoConInf());
                d.setCodiceSilp(elem.getIdGradoConInf());
                d.setDataInizioValidita(MappingUtils.toDate(elem.getDataInizio()));
                d.setDataFineValidita(MappingUtils.toDate(elem.getDataFine()));
            }

            public String getThisMethodName() {
                return "findGradiConoscenzeInformatiche()";
            }

            public String getSilpsvdeMethodName() {
                return "ricercaGradoConInf";
            }
        });
    }

    /**
     * Find patenti.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Decodifica> findPatenti() throws Exception {
        return genericFindDecodifica(new PatenteAdapter());
    }

    /**
     * Find patentini.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Decodifica> findPatentini() throws Exception {
        return genericFindDecodifica(new PatentinoAdapter());
    }

    /**
     * The Class PatenteAdapter.
     */
    class PatenteAdapter extends DecodificaAdapter<TipoPatente> {

        /**
         * Checks if is patente del tipo voluto.
         *
         * @param p the p
         * @return true, if is patente del tipo voluto
         */
        protected boolean isPatenteDelTipoVoluto(TipoPatente p) {
            return !p.isPatentino();
        }

        /**
         * Gets the cache name.
         *
         * @return the cache name
         */
        public String getCacheName() {
            return Cache.PATENTI;
        }

        /**
         * Call silpsvde service.
         *
         * @return the list
         * @throws Exception the exception
         */
        public List<TipoPatente> callSilpsvdeService() throws Exception {
            return factory.getService().ricercaPatenti(Constants.CLIENT_SILP_NAME);
        }

        /**
         * Remap single decodifica object from WS.
         *
         * @param elem the elem
         * @param d    the d
         */
        public void remapSingleDecodificaObjectFromWS(TipoPatente elem, Decodifica d) {
            if (isPatenteDelTipoVoluto(elem)) {
                d.setCodiceMinisteriale(elem.getCodMinistero());
                d.setDescrizione(elem.getDescrTipoPatente());
                d.setCodiceSilp(elem.getIdTipoPatente());
                d.setDataInizioValidita(MappingUtils.toDate(elem.getDataInizio()));
                d.setDataFineValidita(MappingUtils.toDate(elem.getDataFine()));
            }

        }

        /**
         * Gets the this method name.
         *
         * @return the this method name
         */
        public String getThisMethodName() {
            return "findPatenti()";
        }

        /**
         * Gets the silpsvde method name.
         *
         * @return the silpsvde method name
         */
        public String getSilpsvdeMethodName() {
            return "ricercaPatenti";
        }
    }

    /**
     * The Class PatentinoAdapter.
     */
    class PatentinoAdapter extends PatenteAdapter {

        /**
         * Gets the cache name.
         *
         * @return the cache name
         */
        public String getCacheName() {
            return Cache.PATENTINI;
        }

        /**
         * Checks if is patente del tipo voluto.
         *
         * @param p the p
         * @return true, if is patente del tipo voluto
         */
        protected boolean isPatenteDelTipoVoluto(TipoPatente p) {
            return p.isPatentino();
        }

        /**
         * Gets the this method name.
         *
         * @return the this method name
         */
        public String getThisMethodName() {
            return "findPatentini()";
        }

        /**
         * Gets the silpsvde method name.
         *
         * @return the silpsvde method name
         */
        public String getSilpsvdeMethodName() {
            return "ricercaPatenti";
        }
    }

    /**
     * Invoca il servizio silpsv.silpsvde per caricare l'elenco di voci relative
     * alle categorie inquadramento ministeriali tabella silp di riferimento
     * SIL_T_GRADO_CONTRATTUALE
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Decodifica> findCategorieInquadramento() throws Exception {
        return genericFindDecodifica(new DecodificaAdapter<CategoriaInquadramento>() {
            public String getCacheName() {
                return Cache.CATEGORIE_INQUADRAMENTO;
            }

            public List<CategoriaInquadramento> callSilpsvdeService() throws Exception {
                return factory.getService().ricercaCategorieInquadramento(Constants.CLIENT_SILP_NAME, new DecodificaFilter());
            }

            public void remapSingleDecodificaObjectFromWS(CategoriaInquadramento elem, Decodifica d) {
                d.setCodiceMinisteriale(elem.getCodiceMinisteriale());
                d.setDescrizione(elem.getDescrizione());
                d.setCodiceSilp(elem.getId());
                d.setDataInizioValidita(MappingUtils.toDate(elem.getDataInizio()));
                d.setDataFineValidita(MappingUtils.toDate(elem.getDataFine()));
            }

            public String getThisMethodName() {
                return "findCategorieInquadramento()";
            }

            public String getSilpsvdeMethodName() {
                return "ricercaCategorieInquadramento";
            }
        });
    }

    /**
     * Invoca il servizio silpsv.silpsvde per caricare l'elenco di voci relative
     * alle qualifiche professionali ministeriali tabella silp di riferimento
     * SIL_T_QUALIFICA
     *
     * @param codice      the codice
     * @param descrizione the descrizione
     * @return the list
     * @throws Exception the exception
     */
    public List<Decodifica> findQualificheProfessionali(String codice, String descrizione) throws Exception {
        return genericFindDecodifica(new DecodificaAdapter<TipoQualifica>() {
            public String getCacheName() {
                return Cache.QUALIFICHE_PROFESSIONALI;
            }

            public List<TipoQualifica> callSilpsvdeService() throws Exception {
                TipoQualifica filter = new TipoQualifica();
                filter.setCodMinistero(SilCommonUtils.toUpperCaseAndClearHtml(codice));
                filter.setDescrTipoQualifica(SilCommonUtils.toUpperCaseAndClearHtml(descrizione));
                return factory.getService().ricercaTipiQualifica(Constants.CLIENT_SILP_NAME, filter);
            }

            public void remapSingleDecodificaObjectFromWS(TipoQualifica elem, Decodifica d) {
                d.setCodiceMinisteriale(elem.getCodMinistero());
                d.setDescrizione(elem.getDescrTipoQualifica());
                d.setCodiceSilp(elem.getIdTipoQualifica());
                d.setDataInizioValidita(MappingUtils.toDate(elem.getDataInizio()));
                d.setDataFineValidita(MappingUtils.toDate(elem.getDataFine()));
            }

            public String getThisMethodName() {
                return "findQualificheProfessionali()";
            }

            public String getSilpsvdeMethodName() {
                return "ricercaTipiQualifica";
            }
        });
    }

    /**
     * Invoca il servizio silpsv.silpsvde per caricare l'elenco di voci relative ai
     * tipi contratto ministeriali tabella silp di riferimento
     * SIL_T_TIPO_LAVORO_MINISTERO e SIL_T_TIPO_LAVORO (corrispettivo silp con
     * configurazioni)
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<TipoContratto> findTipiContratto() throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        List<TipoContratto> result = Cache.getInstance().getEl(Cache.TIPI_CONTRATTO);
        if (result != null)
            return result;
        result = new ArrayList<>();
        try {
            List<it.csi.silpsv.silpsvde.cxfclient.TipoContratto> tipiContratto = factory.getService().ricercaTipiContratto(Constants.CLIENT_SILP_NAME, null);

            for (it.csi.silpsv.silpsvde.cxfclient.TipoContratto wsItemElement : tipiContratto) {
                TipoContratto d = new TipoContratto();
                d.setCodiceMinisteriale(wsItemElement.getCodiceMinisteriale());
                d.setDescrizione(wsItemElement.getDescrizione());
                d.setCodiceSilp(wsItemElement.getId());
                d.setDataInizioValidita(MappingUtils.toDate(wsItemElement.getDataInizio()));
                d.setDataFineValidita(MappingUtils.toDate(wsItemElement.getDataFine()));
                d.setCodiceTipoLavoro(wsItemElement.getCodiceTipoLavoro());
                d.setAmmissibileLegge68(wsItemElement.isAmmissibileLegge68());
                d.setAmmissibileMissione(wsItemElement.isAmmissibileMissione());
                d.setAmmissibileMobilita(wsItemElement.isAmmissibileMobilita());
                d.setAmmissibileStagionale(wsItemElement.isAmmissibileStagionale());
                d.setAmmissibileAgricoltura(wsItemElement.isAmmissibileAgricoltura());
                d.setAmmissibileFormaTD(wsItemElement.isAmmissibileFormaTD());
                d.setAmmissibileFormaTI(wsItemElement.isAmmissibileFormaTI());
                result.add(d);
            }
            Cache.getInstance().putEl(Cache.TIPI_CONTRATTO, result);
        } finally {
            watcher.dumpElapsed("AdapterSilpsvdeWSImpl", "findTipiContratto()", "invocazione servizio [SILPSV.silpsvde::ricercaTipiContratto]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Find albi.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Decodifica> findAlbi() throws Exception {
        return genericFindDecodifica(new DecodificaAdapter<Albo>() {
            public String getCacheName() {
                return Cache.ALBI;
            }

            public List<Albo> callSilpsvdeService() throws Exception {
                return factory.getService().ricercaAlbi(Constants.CLIENT_SILP_NAME, null);
            }

            public void remapSingleDecodificaObjectFromWS(Albo elem, Decodifica d) {
                d.setCodiceMinisteriale(elem.getCodiceMinisteriale());
                d.setDescrizione(elem.getDescrizione());
                d.setCodiceSilp(elem.getIdAlbo());
                d.setDataInizioValidita(MappingUtils.toDate(elem.getDataInizio()));
                d.setDataFineValidita(MappingUtils.toDate(elem.getDataFine()));
            }

            public String getThisMethodName() {
                return "findAlbi()";
            }

            public String getSilpsvdeMethodName() {
                return "ricercaAlbi";
            }
        });
    }

    /**
     * Find modalita lavoro.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Decodifica> findModalitaLavoro() throws Exception {
        return genericFindDecodifica(new DecodificaAdapter<ModalitaLavoro>() {
            public String getCacheName() {
                return Cache.MODALITA_LAVORO;
            }

            public List<ModalitaLavoro> callSilpsvdeService() throws Exception {
                return factory.getService().ricercaModalitaLavoro(Constants.CLIENT_SILP_NAME, null);
            }

            public void remapSingleDecodificaObjectFromWS(ModalitaLavoro elem, Decodifica d) {
                d.setCodiceMinisteriale(elem.getCodiceMinisteriale());
                d.setDescrizione(elem.getDescrizione());
                d.setCodiceSilp(elem.getIdModalitaLavoro());
                d.setDataInizioValidita(MappingUtils.toDate(elem.getDataInizio()));
                d.setDataFineValidita(MappingUtils.toDate(elem.getDataFine()));
            }

            public String getThisMethodName() {
                return "findModalitaLavoro()";
            }

            public String getSilpsvdeMethodName() {
                return "ricercaModalitaLavoro";
            }
        });
    }

    /**
     * Find certificazioni attestazioni.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Decodifica> findCertificazioniAttestazioni() throws Exception {
        return genericFindDecodifica(new DecodificaAdapter<CertificazioneAttestazione>() {
            public String getCacheName() {
                return Cache.CERTIFICAZIONI_ATTESTAZIONI;
            }

            public List<CertificazioneAttestazione> callSilpsvdeService() throws Exception {
                return factory.getService().ricercaCertificazioniAttestazioni(Constants.CLIENT_SILP_NAME, null);
            }

            public void remapSingleDecodificaObjectFromWS(CertificazioneAttestazione elem, Decodifica d) {
                d.setCodiceMinisteriale(elem.getCodiceMinisteriale());
                d.setDescrizione(elem.getDescrizione());
                // d.setCodiceSilp(elem.getIdModalitaLavoro()); // Non ha codice silp per ora
                d.setDataInizioValidita(MappingUtils.toDate(elem.getDataInizio()));
                d.setDataFineValidita(MappingUtils.toDate(elem.getDataFine()));
            }

            public String getThisMethodName() {
                return "findCertificazioniAttestazioni()";
            }

            public String getSilpsvdeMethodName() {
                return "ricercaCertificazioniAttestazioni";
            }
        });
    }

    /**
     * Find regioni.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Decodifica> findRegioni() throws Exception {
        return genericFindDecodifica(new DecodificaAdapter<Regione>() {
            public String getCacheName() {
                return Cache.REGIONI;
            }

            public List<Regione> callSilpsvdeService() throws Exception {
                return factory.getService().ricercaRegioni(Constants.CLIENT_SILP_NAME, null, null, null);
            }

            public void remapSingleDecodificaObjectFromWS(Regione elem, Decodifica d) {
                d.setCodiceMinisteriale(elem.getCodiceMinisteriale());
                d.setDescrizione(elem.getDsRegione());
                d.setCodiceSilp(elem.getIdRegione());
                d.setDataInizioValidita(SilTimeUtils.convertStringaInData(elem.getDataInizio()));
                d.setDataFineValidita(SilTimeUtils.convertStringaInData(elem.getDataFine()));
            }

            public String getThisMethodName() {
                return "findRegioni()";
            }

            public String getSilpsvdeMethodName() {
                return "ricercaRegioni";
            }
        });
    }

    /**
     * Find settori ateco.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Decodifica> findSettoriAteco() throws Exception {
        return genericFindDecodifica(new DecodificaAdapter<Ateco02>() {
            public String getCacheName() {
                return Cache.SETTORI_ATECO;
            }

            public List<Ateco02> callSilpsvdeService() throws Exception {
                return factory.getService().ricercaAteco02(Constants.CLIENT_SILP_NAME, new Ateco02());
            }

            public void remapSingleDecodificaObjectFromWS(Ateco02 elem, Decodifica d) {
                d.setCodiceMinisteriale(elem.getCodAteco02());
                d.setDescrizione(elem.getDescrAteco02());
                d.setCodiceSilp(elem.getIdAteco02());
                d.setDataInizioValidita(MappingUtils.toDate(elem.getDataInizio()));
                d.setDataFineValidita(MappingUtils.toDate(elem.getDataFine()));
            }

            public String getThisMethodName() {
                return "findSettoriAteco()";
            }

            public String getSilpsvdeMethodName() {
                return "ricercaAteco02";
            }
        });
    }

    /**
     * Metodo generico per effettuare l'invocazione di un servizio di silpsvde che
     * ritorni un elenco di decodifiche.
     *
     * @param <I>     the generic type
     * @param <O>     the generic type
     * @param adapter classe che consente di fornire alcune implementazioni
     *                specifiche come il servizio invocato, la rimappatura di ogni
     *                elemento ottenuto la chiave della cache da gestire, alcune
     *                stringhe per i log
     * @return the list
     * @throws Exception the exception
     */
    private <I extends Decodifica, O> List<I> genericFindDecodifica(DecodificaAdapterInt<I, O> adapter) throws Exception {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        List<I> result = null;
        if (adapter.getCacheName() != null) {
            result = Cache.getInstance().getEl(adapter.getCacheName());
            if (result != null)
                return result;
        }
        result = new ArrayList<>();
        try {

            List<O> elencoDecoWS = adapter.callSilpsvdeService();
            for (Iterator<O> iterator = elencoDecoWS.iterator(); iterator.hasNext();) {
                O decodificaItemWS = iterator.next();
                I deco = adapter.initOutputElement();
                adapter.remapSingleDecodificaObjectFromWS(decodificaItemWS, deco);
                if (deco.getCodiceMinisteriale() != null && deco.getDescrizione() != null) {
                    // Imposto un flag di utilita' se il valore e' valido in data odierna
                    deco.setValidoOggi(SilTimeUtils.isDataCompresa(SilTimeUtils.today(), deco.getDataInizioValidita(), deco.getDataFineValidita()));
                    // In certe ricerche specifiche non voglio quelli scaduti caricati dal servizio
                    if (adapter.filterNotValidToday() && !deco.isValidoOggi()) {
                        continue;
                    }
                    result.add(deco);
                }
            }
            if (adapter.getCacheName() != null) {
                Cache.getInstance().putEl(adapter.getCacheName(), result);
            }
        } finally {
            watcher.dumpElapsed("AdapterSilpsvdeWSImpl", adapter.getThisMethodName(),
                    "invocazione servizio [SILPSV.silpsvde::" + adapter.getSilpsvdeMethodName() + "", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Definisce alcuni comortamenti specifici da implementare nella ricerca di un
     * generico elenco di decodifiche da silpsvde.
     *
     * @author CSI
     * @param <O> the generic type
     * @param <I> the generic type
     */
    private static interface DecodificaAdapterInt<O extends Decodifica, I> {

        /**
         * Gets the cache name.
         *
         * @return the cache name
         */
        String getCacheName();

        /**
         * Call silpsvde service.
         *
         * @return the list
         * @throws Exception the exception
         */
        List<I> callSilpsvdeService() throws Exception;

        /**
         * Remap single decodifica object from WS.
         *
         * @param wsItemElement the ws item element
         * @param d             the d
         */
        void remapSingleDecodificaObjectFromWS(I wsItemElement, O d);

        /**
         * Gets the this method name.
         *
         * @return the this method name
         */
        String getThisMethodName();

        /**
         * Gets the silpsvde method name.
         *
         * @return the silpsvde method name
         */
        String getSilpsvdeMethodName();

        /**
         * Inits the output element.
         *
         * @return the o
         */
        O initOutputElement();

        /**
         * Filter not valid today.
         *
         * @return true, if successful
         */
        boolean filterNotValidToday();
    }

    /**
     * The Class DecodificaAdapter.
     *
     * @param <I> the generic type
     */
    private static abstract class DecodificaAdapter<I> implements DecodificaAdapterInt<Decodifica, I> {

        /**
         * Filter not valid today.
         *
         * @return true, if successful
         */
        public boolean filterNotValidToday() {
            return false;
        }

        /**
         * Inits the output element.
         *
         * @return the decodifica
         */
        public Decodifica initOutputElement() {
            return new Decodifica();
        }
    }

}
