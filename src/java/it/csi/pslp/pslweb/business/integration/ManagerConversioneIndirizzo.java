/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

import java.util.Vector;

import it.csi.silos.jedi.core.DAOException;
import it.csi.silpcommonobj.dati.decodifiche.ToponimoSilpDTO;
import it.csi.silpcommonobj.util.sap.ManagerConversioneIndirizzoAbstract;

/**
 * The Class ManagerConversioneIndirizzo.
 */
public class ManagerConversioneIndirizzo extends ManagerConversioneIndirizzoAbstract {

    /** The Constant instance. */
    public static final ManagerConversioneIndirizzo instance = new ManagerConversioneIndirizzo();

    /**
     * Gets the single instance of ManagerConversioneIndirizzo.
     *
     * @return single instance of ManagerConversioneIndirizzo
     */
    public static ManagerConversioneIndirizzo getInstance() {
        return instance;
    }

    /**
     * Gets the elenco toponimi.
     *
     * @return the elenco toponimi
     * @throws DAOException the DAO exception
     */
    @Override
    protected Vector<ToponimoSilpDTO> getElencoToponimi() throws DAOException {
        try {
            return AdapterSilpsvdeWSImpl.getInstance().findToponimiSilp();
        } catch (Exception ex) {
            throw new DAOException("Impossibile caricare l'elenco dei toponimi", ex);
        }
    }

}
