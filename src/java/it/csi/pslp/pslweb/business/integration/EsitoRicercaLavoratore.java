/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

import java.util.ArrayList;
import java.util.List;

import it.csi.pslp.pslweb.dto.be.Lavoratore;

/**
 * The Class EsitoRicercaLavoratore.
 */
public class EsitoRicercaLavoratore {

    /** The codice errore. */
    private String codiceErrore;

    /** The messaggio errore. */
    private String messaggioErrore;

    /** The exception. */
    private Throwable exception;

    /** The archiviati presenti. */
    private boolean archiviatiPresenti;

    /**
     * Checks if is archiviati presenti.
     *
     * @return true, if is archiviati presenti
     */
    public boolean isArchiviatiPresenti() {
        return archiviatiPresenti;
    }

    /**
     * Sets the archiviati presenti.
     *
     * @param archiviatiPresenti the new archiviati presenti
     */
    public void setArchiviatiPresenti(boolean archiviatiPresenti) {
        this.archiviatiPresenti = archiviatiPresenti;
    }

    /** The lavoratori. */
    private List<Lavoratore> lavoratori = new ArrayList<>();

    /**
     * Gets the codice errore.
     *
     * @return the codice errore
     */
    public String getCodiceErrore() {
        return codiceErrore;
    }

    /**
     * Sets the codice errore.
     *
     * @param codiceErrore the new codice errore
     */
    public void setCodiceErrore(String codiceErrore) {
        this.codiceErrore = codiceErrore;
    }

    /**
     * Gets the messaggio errore.
     *
     * @return the messaggio errore
     */
    public String getMessaggioErrore() {
        return messaggioErrore;
    }

    /**
     * Sets the messaggio errore.
     *
     * @param messaggioErrore the new messaggio errore
     */
    public void setMessaggioErrore(String messaggioErrore) {
        this.messaggioErrore = messaggioErrore;
    }

    /**
     * Gets the exception.
     *
     * @return the exception
     */
    public Throwable getException() {
        return exception;
    }

    /**
     * Sets the exception.
     *
     * @param exception the new exception
     */
    public void setException(Throwable exception) {
        this.exception = exception;
    }

    /**
     * Gets the lavoratori.
     *
     * @return the lavoratori
     */
    public List<Lavoratore> getLavoratori() {
        return lavoratori;
    }

    /**
     * Sets the lavoratori.
     *
     * @param lavoratori the new lavoratori
     */
    public void setLavoratori(List<Lavoratore> lavoratori) {
        this.lavoratori = lavoratori;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "EsitoRicercaLavoratore [codiceErrore=" + codiceErrore + ", messaggioErrore=" + messaggioErrore + ", exception=" + exception + ", lavoratori="
                + lavoratori + "]";
    }

}
