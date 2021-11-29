/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.pluginpdpa;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.csi.pfh.AbstractFunctHandler;
import it.csi.csi.pfh.FHInfo;
import it.csi.csi.pfh.FHResult;
import it.csi.csi.util.Param;
import it.csi.csi.wrapper.CSIException;
 
import it.csi.pslp.pslweb.util.ParametroUtils;
import it.csi.silpcommonobj.util.pluginpapd.ParametriConInformazioniAusiliarie;

/**
 * The Class ClientSecurityPlugin.
 */
public class ClientSecurityPlugin extends AbstractFunctHandler {
    
    /** The logger. */
    private Logger logger = Logger.getLogger(ClientSecurityPlugin.class);
   
    /**
     * Instantiates a new client security plugin.
     */
    public ClientSecurityPlugin() {
    }

    /**
     * Instantiates a new client security plugin.
     *
     * @param info the info
     */
    public ClientSecurityPlugin(FHInfo info) {
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
        if (inPreChain) {
                /* ATTENZIONEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE 
                 * Questi due parametri devono corrispondere assolutamente ai valori inseriti nella classe
                 * DI SILPSERVER  
                 * it.csi.silp.silpserver.util.pluginpdpa.ServerSecurityPlugin
                 * */
            String codiceFiscale;
            String sessionToken;
            try {
                codiceFiscale = ParametroUtils.getInstance().getParametro(ParametroUtils.CLIENT_SECURITY_CF);
                sessionToken = ParametroUtils.getInstance().getParametro(ParametroUtils.CLIENT_SECURITY_TOKEN);
            } catch (Exception e) {
                throw new CSIException("ERRORE DI SISTEMA", e);
            }

            ParametriConInformazioniAusiliarie param = (ParametriConInformazioniAusiliarie) paramList[0].getValue();
            param.setCodiceFiscaleUtenteCorrente(codiceFiscale);
            param.setSessionToken(sessionToken);
        }
        return prevResult;
    }
    
    
}
