/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.Comune;
import it.csi.pslp.pslweb.dto.be.Luogo;
import it.csi.pslp.pslweb.dto.be.Nazione;
import it.csi.silos.silcommon.dati.common.LuogoDTO;
import it.csi.silos.silcommon.gestionedecodifiche.ComuneDTO;
import it.csi.silos.silcommon.gestionedecodifiche.NazioneDTO;

/**
 * The Class LuogoMapper.
 */
public class LuogoMapper extends BaseMapper<Luogo, LuogoDTO> {

    /** The cm. */
    private final ComuneMapper cm = new ComuneMapper();
    
    /** The nm. */
    private final NazioneMapper nm = new NazioneMapper();

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends LuogoDTO> C map(Luogo in, C out) {
        if (in != null) {
            // Comune e nazione non devono essere valorizzati contemporaneamente con codici
            // negli invii sap a ministero/silp.
            if (in.getComune() != null && in.getComune().getCodiceMinisteriale() != null) {
                out.setComune(new ComuneDTO());
                out.getComune().setCodiceMinisteriale(in.getComune().getCodiceMinisteriale());
                out.getComune().setCap(in.getComune().getCap());

            } else if (in.getStato() != null && in.getStato().getCodiceMinisteriale() != null) {
                out.setStato(new NazioneDTO());
                out.getStato().setCodiceMinisteriale(in.getStato().getCodiceMinisteriale());
                out.getStato().setUE(in.getStato().isFlagUe());
            }

        }
        return out;
    }

    /**
     * Map reverse.
     *
     * @param <C> the generic type
     * @param in the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends Luogo> C mapReverse(LuogoDTO in, C out) {
        if (in != null) {
            if (in.getComune() != null) {
                out.setComune(new Comune());
                cm.mapReverse(in.getComune(), out.getComune());
            }
            if (in.getStato() != null) {
                out.setStato(new Nazione());
                out.getStato().setCodiceMinisteriale(in.getStato().getCodiceMinisteriale());
                out.getStato().setDescrizione(in.getStato().getDescrizione());
                out.getStato().setFlagUe(in.getStato().isUE());
            }

        }
        return out;
    }

    /**
     * Instantiate.
     *
     * @return the luogo
     */
    @Override
    protected Luogo instantiate() {
        return new Luogo();
    }

    /**
     * Instantiate reverse.
     *
     * @return the luogo DTO
     */
    @Override
    protected LuogoDTO instantiateReverse() {
        return new LuogoDTO();
    }

    /**
     * Map reverse.
     *
     * @param codiceComune the codice comune
     * @param codiceNazione the codice nazione
     * @return the luogo
     */
    public Luogo mapReverse(String codiceComune, String codiceNazione) {
        Comune c = cm.getComuneByCodice(codiceComune);
        Nazione n = nm.getNazioneByCodice(codiceNazione);
        if (c == null && n == null) {
            return null;
        }
        Luogo res = new Luogo();
        res.setComune(c);
        res.setStato(n);

        return res;
    }
}
