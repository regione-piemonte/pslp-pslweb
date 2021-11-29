/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.VerbaleL68;
import it.csi.silpcommonobj.dati.collocamentomirato.richiestaiscrizione.VerbaleL68SilpDTO;
 

/**
 * The Class IndirizzoMapper.
 */
public class VerbaleL68Mapper extends BaseMapper<VerbaleL68, VerbaleL68SilpDTO> {

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends VerbaleL68SilpDTO> C map(VerbaleL68 in, C out) {
         
        if (in != null && out != null) {
            out.setDataEmissione(in.getDataEmissione());
            out.setDataProssimaRevisione(in.getDataProssimaRevisione());
            out.setFlgSoggettoARevisione(in.getFlgSoggettoARevisione());
            
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
    public <C extends VerbaleL68> C mapReverse(VerbaleL68SilpDTO in, C out) {
        if (in == null || out == null) {
            return out;
        }
      
        out.setDataEmissione(in.getDataEmissione());
        out.setDataProssimaRevisione(in.getDataProssimaRevisione());
        out.setFlgSoggettoARevisione(in.getFlgSoggettoARevisione());
         
        
        return out;
    }

    /**
     * Instantiate.
     *
     * @return the indirizzo
     */
    @Override
    protected VerbaleL68 instantiate() {
        return new VerbaleL68();
    }

    /**
     * Instantiate reverse.
     *
     * @return the indirizzo DTO
     */
    @Override
    protected VerbaleL68SilpDTO instantiateReverse() {
        return new VerbaleL68SilpDTO();
    }

}
