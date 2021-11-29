/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslweb.util.Constants;
import it.csi.silos.jedi.core.DAO;
import it.csi.silos.jedi.core.DAOException;
import it.csi.silos.silcommon.util.SilTimeUtils;

/**
 * The Class DBDateUtils.
 */
@Component("dbdateUtils")
public class DBDateUtils {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The dao. */
    @Autowired
    private DAO dao;

    /**
     * Ritorna la data corrente troncata senza ore minuti secondi.
     *
     * @return the current date trunc
     */
    public Date getCurrentDateTrunc() {
        return SilTimeUtils.truncDate(getCurrentDate());
    }

    /**
     * Ritorna la data attuale comprensiva di ore minuti secondi.
     *
     * @return the current date
     */
    public Date getCurrentDate() {
        return readDate();
    }

    /**
     * Read date.
     *
     * @return the date
     */
    public Date readDate() {

        try {
            String nowString = dao.readString("SELECT to_char(SYSDATE,'dd/mm/yyyy hh24:mi:ss') FROM DUAL", null);
            return SilTimeUtils.convertStringaInDataConOreMinutiSecondi(nowString);
        } catch (DAOException e) {
            log.error("DBDateUtils.readDate Exception durante la lettura della data attuale", e);
            throw new UnsupportedOperationException("Exception durante la lettura della data attuale", e);
        }

    }

}
