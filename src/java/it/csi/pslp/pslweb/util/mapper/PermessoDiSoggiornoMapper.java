/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.PermessoDiSoggiorno;
import it.csi.silos.silcommon.dati.lavoratore.curriculum.StatusStranieroDTO;
import it.csi.silos.silcommon.gestionedecodifiche.MotivoPermessoDTO;

/**
 * The Class PermessoDiSoggiornoMapper.
 */
public class PermessoDiSoggiornoMapper extends BaseMapper<PermessoDiSoggiorno, StatusStranieroDTO> {

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends StatusStranieroDTO> C map(PermessoDiSoggiorno in, C out) {
        out.setCodiceMinisteriale(in.getCodiceMinisterialeStatusExtraUe());
        out.setDescrizione(in.getTitolo());
        out.setNumero(in.getNumero());
        out.setDataScadenza(in.getDataScadenza());
        out.setMotivo(new MotivoPermessoDTO());
        out.getMotivo().setCodiceMinisteriale(in.getCodiceMinisterialeMotivoRilascio());
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
    public <C extends PermessoDiSoggiorno> C mapReverse(StatusStranieroDTO in, C out) {

        out.setCodiceMinisterialeStatusExtraUe(in.getCodiceMinisteriale());
        out.setTitolo(in.getDescrizione());
        out.setNumero(in.getNumero());
        out.setDataScadenza(in.getDataScadenza());
        if (in.getMotivo() != null) {
            out.setMotivoRilascio(in.getMotivo().getDescrizione());
            out.setCodiceMinisterialeMotivoRilascio(in.getMotivo().getCodiceMinisteriale());
        }

        return out;
    }

    /**
     * Instantiate.
     *
     * @return the permesso di soggiorno
     */
    @Override
    protected PermessoDiSoggiorno instantiate() {
        return new PermessoDiSoggiorno();
    }

    /**
     * Instantiate reverse.
     *
     * @return the status straniero DTO
     */
    @Override
    protected StatusStranieroDTO instantiateReverse() {
        return new StatusStranieroDTO();
    }

}
