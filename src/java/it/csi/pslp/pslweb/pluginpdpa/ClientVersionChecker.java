/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.pluginpdpa;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import it.csi.csi.pfh.AbstractFunctHandler;
import it.csi.csi.pfh.FHInfo;
import it.csi.csi.pfh.FHResult;
import it.csi.csi.util.Param;
import it.csi.csi.wrapper.CSIException;
import it.csi.silpcommonobj.util.pluginpapd.ParametriConInformazioniAusiliarie;

/**
 * The Class ClientVersionChecker.
 */
public class ClientVersionChecker extends AbstractFunctHandler {
    
    /** The logger. */
    Logger logger = Logger.getLogger(ClientVersionChecker.class);
    
    /** The version. */
    String version = "";

    /**
     * Instantiates a new client version checker.
     */
    public ClientVersionChecker() {
    }

    /**
     * Instantiates a new client version checker.
     *
     * @param info the info
     */
    public ClientVersionChecker(FHInfo info) {
        super(info);
    }

    /**
     * Do on invoke synch.
     *
     * @param methodName the method name
     * @param paramList the param list
     * @param prevResult the prev result
     * @param bag the bag
     * @param inPreChain the in pre chain
     * @return the FH result
     * @throws CSIException the CSI exception
     */
    public FHResult doOnInvokeSynch(String methodName, Param[] paramList, FHResult prevResult, Hashtable bag,
            boolean inPreChain) throws CSIException {
        ParametriConInformazioniAusiliarie info = new ParametriConInformazioniAusiliarie();
        // valori farlocchi per poter bypassare i controlli del token su silpserver
        info.setVersioneClient("1");
        info.setIdClient("0");
        info.setParametri(paramList);
        logger.debug("IDCLIENT DA INVIARE AL SERVER ===" + info.getIdClient() + "===");
        Param paramNew[] = new Param[1];
        paramNew[0] = new Param("informazioniAusiliarie", ParametriConInformazioniAusiliarie.class, info, "");
        prevResult.setNewParams(paramNew);
        return prevResult;
    }

}
