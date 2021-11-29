/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;

public class UtenteACaricoComparable extends UtenteACarico implements Comparable<UtenteACaricoComparable> {

    @Override
    public int compareTo(UtenteACaricoComparable arg0) {
        Utente utente = getTutelato();
        Utente utente0 = arg0.getTutelato();
        if (utente == null || utente0 == null || utente.getCognome() == null || utente0.getCognome() == null) {
            return 0;
        } else {
            int r = utente.getCognome().compareTo(utente0.getCognome());
            if (r == 0) {
                r = utente.getNome().compareTo(utente0.getNome());
                if (r == 0) {
                    r = utente.getCodiceFiscale().compareTo(utente0.getCodiceFiscale());
                }
            }
            return r;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UtenteACaricoComparable utenteACarico = (UtenteACaricoComparable) obj;
        return Objects.equals(getTutelato(), utenteACarico.getTutelato()) && Objects.equals(getTipoResponsabilita(), utenteACarico.getTipoResponsabilita())
                && Objects.equals(getPreseVisione(), utenteACarico.getPreseVisione());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTutelato() == null) ? 0 : getTutelato().hashCode())
                + ((getTipoResponsabilita() == null) ? 0 : getTipoResponsabilita().hashCode())
                + ((getPreseVisione() == null) ? 0 : getPreseVisione().hashCode());
        return result;
    }
}
