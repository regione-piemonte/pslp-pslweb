/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

import java.io.InputStream;

import org.apache.log4j.Logger;

import it.csi.csi.porte.InfoPortaDelegata;
import it.csi.csi.porte.proxy.PDProxy;
import it.csi.csi.util.xml.PDConfigReader;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.silp.silpgsp.dto.GspDatiDocumentoBeansDTO;
import it.csi.silp.silpgsp.dto.GspDocumentoPdfDTO;
import it.csi.silp.silpgsp.interf.GspFacade;
import it.csi.silp.silpserver.util.SilpServerConstants;
import it.csi.util.performance.StopWatch;

/**
 * The Class AdapterSilpgsp.
 */
public class AdapterSilpgsp {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The Constant PD_SILPGSP. */
    private static final String PD_SILPGSP = "/defpd_silpgsp.xml";

    /** The service. */
    private GspFacade service = null;

    /** The Constant instance. */
    private static final AdapterSilpgsp instance = new AdapterSilpgsp();

    /**
     * Gets the single instance of AdapterSilpgsp.
     *
     * @return single instance of AdapterSilpgsp
     */
    public static AdapterSilpgsp getInstance() {
        return instance;
    }

    /**
     * Gets the service.
     *
     * @return the service
     * @throws Exception the exception
     */
    private GspFacade getService() throws Exception {
        if (service == null) {
            InputStream pd = this.getClass().getResourceAsStream(PD_SILPGSP);
            InfoPortaDelegata info = PDConfigReader.read(pd);
            service = (GspFacade) PDProxy.newInstance(info);
        }
        return service;
    }

    /**
     * Crea documento PDF.
     *
     * @param codiceModello the codice modello
     * @param param the param
     * @return the byte[]
     * @throws Exception the exception
     */
    public byte[] creaDocumentoPDF(String codiceModello, Object param) throws Exception {
        StopWatch watcher = new StopWatch(SilpServerConstants.LOGGER_PREFIX);
        watcher.start();
        try {
            GspDatiDocumentoBeansDTO datiDocumento = new GspDatiDocumentoBeansDTO(null, param);
            datiDocumento.setCodiceModello(codiceModello);
            GspDocumentoPdfDTO documentoPdf = getService().creaDocumentoPDF(datiDocumento);
            log.info("[AdapterSilpgsp::creaDocumentoPDF] Risultato chiamata servizio: "
                    + (documentoPdf.getContentPdf() != null ? documentoPdf.getContentPdf().length : 0));
            return documentoPdf.getContentPdf();
        } finally {
            watcher.dumpElapsed(getClass().getName(), "creaDocumentoPDF()", "GSP", "creaDocumentoPDF()");
            watcher.stop();
            log.debug("[AdapterSilpgsp::creaDocumentoRTF] END");
        }
    }

}
