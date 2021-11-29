/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.silpcommonobj.dati.common.DecodificaSilpDTO;

/**
 * The Class DecodificaMapper.
 */
public class DecodificaSilpMapper extends BaseMapper<Decodifica, DecodificaSilpDTO> {

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in  the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends DecodificaSilpDTO> C map(Decodifica in, C out) {
        if (in == null || out == null) {
            return out;
        }
        Object obj = null;
        if (null != in.getCodiceMinisteriale()) {
            obj = in.getCodiceMinisteriale();
        } else if (null != in.getCodiceSilp()) {
            obj = in.getCodiceSilp();
        }
        out.setIdentificativoDecodifica(obj);
        out.setDescrizioneDecodifica(in.getDescrizione());
        return out;
    }

    /**
     * Map reverse.
     *
     * @param <C> the generic type
     * @param in  the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends Decodifica> C mapReverse(DecodificaSilpDTO in, C out) {
        if (in == null || out == null) {
            return out;
        }
        if (in.getIdentificativoDecodifica() instanceof String) {
            out.setCodiceMinisteriale((String) in.getIdentificativoDecodifica());
        } else if (in.getIdentificativoDecodifica() instanceof Long) {
            Long a = (Long) in.getIdentificativoDecodifica();
            out.setCodiceMinisteriale(a.toString());
        } else {
            Integer a = (Integer) in.getIdentificativoDecodifica();
            out.setCodiceMinisteriale(a.toString());
        }
        out.setDescrizione(in.getDescrizioneDecodifica());
        return out;
    }

    /**
     * Map.
     *
     * @param codice      the codice
     * @param descrizione the descrizione
     * @return the decodifica
     */
    public Decodifica map(String codice, String descrizione) {
        Decodifica out = new Decodifica();
        out.setCodiceMinisteriale(codice);
        out.setDescrizione(descrizione);
        return out;
    }

    /**
     * Instantiate.
     *
     * @return the decodifica
     */
    @Override
    protected Decodifica instantiate() {
        return new Decodifica();
    }

    /**
     * Instantiate reverse.
     *
     * @return the decodifica DTO
     */
    @Override
    protected DecodificaSilpDTO instantiateReverse() {
        return new DecodificaSilpDTO();
    }

}
