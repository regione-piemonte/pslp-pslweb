/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;

public class UtenteRiepilogoIscrizioneComparable extends UtenteRiepilogoIscrizione implements Comparable<UtenteRiepilogoIscrizioneComparable> {

    @Override
    public int compareTo(UtenteRiepilogoIscrizioneComparable arg0) {
        Utente utente = getUtente();
        Utente utente0 = arg0.getUtente();
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
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Utente utente = getUtente();
        Utente utente0 = ((UtenteRiepilogoIscrizioneComparable) obj).getUtente();
        TipoResponsabilita tipoResp = getTipoResponsabilita();
        TipoResponsabilita tipoResp0 = ((UtenteRiepilogoIscrizioneComparable) obj).getTipoResponsabilita();
        ControlliIscrizione ctrlIscr = getDati();
        ControlliIscrizione ctrlIscr0 = ((UtenteRiepilogoIscrizioneComparable) obj).getDati();
        return Objects.equals(utente, utente0) && Objects.equals(tipoResp, tipoResp0) && Objects.equals(ctrlIscr, ctrlIscr0);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUtente() == null) ? 0 : getUtente().hashCode())
                + ((getTipoResponsabilita() == null) ? 0 : getTipoResponsabilita().hashCode()) + ((getDati() == null) ? 0 : getDati().hashCode());
        return result;
    }
}
