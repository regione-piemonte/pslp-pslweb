/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

import java.util.List;

import org.apache.log4j.Logger;

import it.csi.pslp.pslweb.dto.be.Lavoratore;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.silpsv.silpsval.cxfclient.AnagraficaLavoratore;
import it.csi.silpsv.silpsval.cxfclient.ParamRicercaLavoratoreFilter;
import it.csi.silpsv.silpsval.cxfclient.ResultRicercaLavoratore;
import it.csi.silpsv.silpsval.cxfclient.ResultRicercaLavoratoreInfoArchiviato;
import it.csi.util.performance.StopWatch;

/**
 * The Class AdapterSilpsvalWSImpl.
 */
public class AdapterSilpsvalWSImpl {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The Constant ESITO_OK. */
    private static final String ESITO_OK = "OK000001";
    
    /** The Constant ESITO_NON_TROVATO. */
    private static final String ESITO_NON_TROVATO = "ANA00001";

    /** The factory. */
    public AdapterSilpsvalWSFactory factory = new AdapterSilpsvalWSFactory();

    /** The instance. */
    private static AdapterSilpsvalWSImpl instance = null;

    /**
     * Gets the single instance of AdapterSilpsvalWSImpl.
     *
     * @return single instance of AdapterSilpsvalWSImpl
     */
    public static AdapterSilpsvalWSImpl getInstance() {
        if (instance == null) {
            instance = new AdapterSilpsvalWSImpl();
        }
        return instance;
    }

    /**
     * Reperisce un elenco di lavoratori da silpsval.
     *
     * @param codiceFiscale the codice fiscale
     * @return the esito ricerca lavoratore
     */
    public EsitoRicercaLavoratore ricercaLavoratore_old(String codiceFiscale) {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        EsitoRicercaLavoratore result = new EsitoRicercaLavoratore();
        try {
            ParamRicercaLavoratoreFilter filter = new ParamRicercaLavoratoreFilter();
            filter.setCodiceFiscale(codiceFiscale);
            filter.setMaxNumeroRecord(2);
            List<ResultRicercaLavoratore> lavoratoriWS = factory.getService().ricercaLavoratorePerDatiAnagrafici(Constants.CLIENT_SILP_NAME, filter);
            for (ResultRicercaLavoratore lavoratoreWS : lavoratoriWS) {
                if (ESITO_NON_TROVATO.equals(lavoratoreWS.getCodErrore())) {
                    /*
                     * ripristinato funzionamento precedente 25/06/2020 GG
                     * 
                     * result.setCodiceErrore(lavoratoreWS.getCodErrore());
                     * result.setMessaggioErrore(lavoratoreWS.getDescrizioneErrore());
                     */
                    break;
                }
                if (lavoratoreWS.getCodErrore() != null && !ESITO_OK.equals(lavoratoreWS.getCodErrore())) {
                    result.setCodiceErrore(lavoratoreWS.getCodErrore());
                    result.setMessaggioErrore(lavoratoreWS.getDescrizioneErrore());
                    break;
                }
                Lavoratore lavoratore = new Lavoratore();
                lavoratore.setCodiceFiscale(lavoratoreWS.getCodiceFiscale());
                lavoratore.setIdLavoratore(Integer.parseInt(lavoratoreWS.getIdLavoratore()));
                lavoratore.setCognome(lavoratoreWS.getCognome());
                lavoratore.setNome(lavoratoreWS.getNome());
                result.getLavoratori().add(lavoratore);
            }
        } catch (Throwable ex) {
            log.error("AdapterSilpsvalWSImpl::ricercaLavoratore", ex);
            result.setCodiceErrore("SYSERR");
            result.setMessaggioErrore("Errore nella chiamata al servizio SILPSV.silpsval:" + ex.getClass().getName());
        } finally {
            watcher.dumpElapsed("AdapterSilpsvalWSImpl", "ricercaLavoratore()", "invocazione servizio [SILPSV.silpsval::ricercaLavoratore]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Reperisce un elenco di lavoratori da silpsval con info archiviato.
     *
     * @param codiceFiscale the codice fiscale
     * @return the esito ricerca lavoratore
     */
    public EsitoRicercaLavoratore ricercaLavoratoreInfoArchiviato(String codiceFiscale) {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        EsitoRicercaLavoratore result = new EsitoRicercaLavoratore();
        try {
            ParamRicercaLavoratoreFilter filter = new ParamRicercaLavoratoreFilter();
            filter.setCodiceFiscale(codiceFiscale);
            filter.setMaxNumeroRecord(2);
            List<ResultRicercaLavoratoreInfoArchiviato> lavoratoriWS = factory.getService()
                    .ricercaLavoratorePerDatiAnagraficiInfoArchiviato(Constants.CLIENT_SILP_NAME, filter);
            for (ResultRicercaLavoratoreInfoArchiviato lavoratoreWS : lavoratoriWS) {
                if (ESITO_NON_TROVATO.equals(lavoratoreWS.getCodErrore())) {
                    /*
                     * ripristinato funzionamento precedente 25/06/2020 GG
                     * 
                     * result.setCodiceErrore(lavoratoreWS.getCodErrore());
                     * result.setMessaggioErrore(lavoratoreWS.getDescrizioneErrore());
                     */
                    break;
                }
                if (lavoratoreWS.getCodErrore() != null && !ESITO_OK.equals(lavoratoreWS.getCodErrore())) {
                    result.setCodiceErrore(lavoratoreWS.getCodErrore());
                    result.setMessaggioErrore(lavoratoreWS.getDescrizioneErrore());
                    break;
                }
                if ("N".equals(lavoratoreWS.getFlgArichiviato())) {
                    Lavoratore lavoratore = new Lavoratore();
                    lavoratore.setCodiceFiscale(lavoratoreWS.getCodiceFiscale());
                    lavoratore.setIdLavoratore(Integer.parseInt(lavoratoreWS.getIdLavoratore()));
                    lavoratore.setCognome(lavoratoreWS.getCognome());
                    lavoratore.setNome(lavoratoreWS.getNome());
                    result.getLavoratori().add(lavoratore);
                } else {
                    result.setArchiviatiPresenti(true);
                }
            }

        } catch (Throwable ex) {
            log.error("AdapterSilpsvalWSImpl::ricercaLavoratoreInfoArchiviato", ex);
            result.setCodiceErrore("SYSERR");
            result.setMessaggioErrore("Errore nella chiamata al servizio SILPSV.silpsval:" + ex.getClass().getName());
        } finally {
            watcher.dumpElapsed("AdapterSilpsvalWSImpl", "ricercaLavoratoreInfoArchiviato()",
                    "invocazione servizio [SILPSV.silpsval::ricercaLavoratoreInfoArchiviato]", "");
            watcher.stop();
        }
        return result;
    }

    /**
     * Reperisce un elenco di lavoratori da silpsval.
     *
     * @param idSilLavAnagrafica the id sil lav anagrafica
     * @return the lavoratore
     */
    public Lavoratore visualizzaDettaglioLavoratore(Long idSilLavAnagrafica) {
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        EsitoRicercaLavoratore result = new EsitoRicercaLavoratore();
        Lavoratore lavoratore = null;
        try {
            AnagraficaLavoratore lavoratoreWS = factory.getService().visualizzaDettaglioLavoratore(Constants.CLIENT_SILP_NAME, idSilLavAnagrafica.toString());
            if (lavoratoreWS != null) {
                lavoratore = new Lavoratore();
                lavoratore.setCodiceFiscale(lavoratoreWS.getCodiceFiscale());
                lavoratore.setCognome(lavoratoreWS.getCognome());
                lavoratore.setNome(lavoratoreWS.getNome());
                lavoratore.setIdLavoratore(idSilLavAnagrafica.intValue());
            }
        } catch (Throwable ex) {
            log.error("AdapterSilpsvalWSImpl::visualizzaDettaglioLavoratore", ex);
            result.setCodiceErrore("SYSERR");
            result.setMessaggioErrore("Errore nella chiamata al servizio SILPSV.silpsval:" + ex.getClass().getName());
        } finally {
            watcher.dumpElapsed("AdapterSilpsvalWSImpl", "visualizzaDettaglioLavoratore()",
                    "invocazione servizio [SILPSV.silpsval::visualizzaDettaglioLavoratore]", "");
            watcher.stop();
        }
        return lavoratore;
    }

}
