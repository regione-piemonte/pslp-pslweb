/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.EventoPolitica;
import it.csi.pslp.pslweb.dto.be.PoliticaAttiva;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.PoliticheAttiveSapDTO;

/**
 * The Class PoliticheAttiveMapper.
 */
public class PoliticheAttiveMapper extends BaseMapper<PoliticaAttiva, PoliticheAttiveSapDTO> {

    /** The dm. */
    DecodificaMapper dm = new DecodificaMapper();

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends PoliticheAttiveSapDTO> C map(PoliticaAttiva in, C out) {

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
    public <C extends PoliticaAttiva> C mapReverse(PoliticheAttiveSapDTO in, C out) {
        out.setAttivita(dm.map(in.getTipoAttivita(), in.getDsTipoAttivitaMin()));
        out.setDenominazione(in.getTitoloDenominazione());

        out.setDataProposta(in.getDataProposta());
        out.setDataInizio(in.getDataInizio());
        out.setDataFine(in.getDataFine());
        out.setDurata(in.getDurata());
        out.setTipoDurata(in.getTipologiaDurata());
        out.setDescrizione(in.getDescrizioneAttivita());
        out.setTitoloProgetto(dm.map(in.getTitoloProgetto(), in.getDescrTipoProgettoMin()));
        out.setEntePromotore(dm.map(in.getCodiceEntePromotore(), in.getDescrizioneEntePromotore()));
        out.setIndiceProfiling(in.getIndiceProfiling());
        out.setUltimoEvento(new EventoPolitica());
        out.getUltimoEvento().setEvento(new Decodifica());
        if (in.getTipoEvento() != null) {
            out.getUltimoEvento().setEvento(dm.map(in.getTipoEvento(), in.getDescrizioneTipoEvento()));
            out.getUltimoEvento().setDataEvento(in.getDataEvento());
            out.getUltimoEvento().setDescrizione(in.getDescrizioneEvento());
            // Non confondere questa descrizione generica con quella della decodifica del
            // tipo evento
        }

        return out;
    }

    /**
     * Instantiate.
     *
     * @return the politica attiva
     */
    @Override
    protected PoliticaAttiva instantiate() {
        return new PoliticaAttiva();
    }

    /**
     * Instantiate reverse.
     *
     * @return the politiche attive sap DTO
     */
    @Override
    protected PoliticheAttiveSapDTO instantiateReverse() {
        return new PoliticheAttiveSapDTO();
    }

}
