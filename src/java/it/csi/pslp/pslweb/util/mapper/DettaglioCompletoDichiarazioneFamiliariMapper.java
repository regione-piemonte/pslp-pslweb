/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.DettaglioCompletoDichiarazioneFamiliariACarico;
import it.csi.pslp.pslweb.dto.be.DettaglioDichiarazioneFamiliariACarico;
import it.csi.silpcommonobj.dati.collocamentomirato.DettaglioCompletoDichiarazioneFamiliariACaricoDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.DettaglioDichiarazioneFamiliariACaricoDTO;

/**
 * The Class DettaglioCompletoDichiarazioneFamiliariMapper.
 */
public class DettaglioCompletoDichiarazioneFamiliariMapper
        extends BaseMapper<DettaglioCompletoDichiarazioneFamiliariACarico, DettaglioCompletoDichiarazioneFamiliariACaricoDTO> {

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends DettaglioCompletoDichiarazioneFamiliariACaricoDTO> C map(DettaglioCompletoDichiarazioneFamiliariACarico in, C out) {

        try {
            out.setAnnoValidita(in.getAnnoValidita());
            out.setDsFonteDato(in.getFonte());

            /* Il CPI DI appartenenza viene popolato dal servizio */

            out.setDataDichiaraizone(in.getDataDichiarazione());
            out.setDataNascitaDichiarante(in.getDataNascitaDichiarante());
            out.setIdSilLavAnagrafica(in.getIdSilLavAnagrafica());
            out.setNumeroFamiliari(in.getNumeroFamiliari());
            out.setDsNote(in.getNote());

            Vector<DettaglioDichiarazioneFamiliariACaricoDTO> elencoFam = new Vector<DettaglioDichiarazioneFamiliariACaricoDTO>();
            for (DettaglioDichiarazioneFamiliariACarico dettFamiliari : in.getDettaglioDichiarazioneFamiliariACarico()) {
                DettaglioDichiarazioneFamiliariACaricoDTO dettFamOut = new DettaglioDichiarazioneFamiliariMapper().map(dettFamiliari);
                elencoFam.add(dettFamOut);
            }
            out.setDettagliFamiliari(elencoFam);
        } catch (NumberFormatException e) {
            throw e;
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
    public <C extends DettaglioCompletoDichiarazioneFamiliariACarico> C mapReverse(DettaglioCompletoDichiarazioneFamiliariACaricoDTO in, C out) {

        out.setAnnoValidita(in.getAnnoValidita());
        out.setFonte(in.getDsFonteDato());
        Decodifica cpi = new Decodifica();
        cpi.setDescrizione(in.getDsCpi());
        if (null != in.getIdCpi()) {
            cpi.setCodiceMinisteriale(in.getIdCpi().toString());
            cpi.setCodiceSilp(in.getIdCpi().toString());
        }
        out.setCpi(cpi);
        out.setDataDichiarazione(in.getDataDichiaraizone());
        out.setDataNascitaDichiarante(in.getDataNascitaDichiarante());
        out.setIdSilLavAnagrafica(in.getIdSilLavAnagrafica());
        out.setNumeroFamiliari(in.getNumeroFamiliari());
        out.setNote(in.getDsNote());

        List<DettaglioDichiarazioneFamiliariACarico> elencoFam = new ArrayList<DettaglioDichiarazioneFamiliariACarico>();
        for (DettaglioDichiarazioneFamiliariACaricoDTO dettFamiliari : in.getDettagliFamiliari()) {
            DettaglioDichiarazioneFamiliariACarico dettFamOut = new DettaglioDichiarazioneFamiliariMapper().mapReverse(dettFamiliari);
            elencoFam.add(dettFamOut);
        }
        out.setDettaglioDichiarazioneFamiliariACarico(elencoFam);

        return out;
    }

    /**
     * Instantiate.
     *
     * @return the dettaglio completo dichiarazione familiari A carico
     */
    @Override
    protected DettaglioCompletoDichiarazioneFamiliariACarico instantiate() {
        return new DettaglioCompletoDichiarazioneFamiliariACarico();
    }

    /**
     * Instantiate reverse.
     *
     * @return the dettaglio completo dichiarazione familiari A carico DTO
     */
    @Override
    protected DettaglioCompletoDichiarazioneFamiliariACaricoDTO instantiateReverse() {
        return new DettaglioCompletoDichiarazioneFamiliariACaricoDTO();
    }

}
