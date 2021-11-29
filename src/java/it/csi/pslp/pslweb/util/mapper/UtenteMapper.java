/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslcommonobj.dto.UtenteDTO;
import it.csi.pslp.pslweb.dto.be.Utente;

/**
 * The Class UtenteMapper.
 */
public class UtenteMapper extends BaseMapper<Utente, UtenteDTO> {

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends UtenteDTO> C map(Utente in, C out) {
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
    public <C extends Utente> C mapReverse(UtenteDTO in, C out) {
        out.setCodiceFiscale(in.getCfUtente());
        out.setCognome(in.getCognome());
        out.setCodiceTipoUtente(in.getCodTipoUtente());
        out.setEmail(in.getEmail());
        out.setIdentificativoSap(in.getIdentificativoSap());
        out.setIdSilLavAnagrafica(in.getIdSilLavAngrafica());
        out.setIdUtente(in.getIdUtente());
        out.setNome(in.getNome());
        return out;
    }

    /**
     * Instantiate.
     *
     * @return the utente
     */
    @Override
    protected Utente instantiate() {
        return new Utente();
    }

    /**
     * Instantiate reverse.
     *
     * @return the utente DTO
     */
    @Override
    protected UtenteDTO instantiateReverse() {
        return new UtenteDTO();
    }

}
