/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import org.apache.commons.lang.StringUtils;

import it.csi.pslp.pslweb.business.integration.ManagerConversioneIndirizzo;
import it.csi.pslp.pslweb.dto.be.Indirizzo;
import it.csi.pslp.pslweb.dto.be.Sedime;
import it.csi.silos.silcommon.dati.common.IndirizzoDTO;
import it.csi.silos.silcommon.gestionedecodifiche.ComuneDTO;
import it.csi.silos.silcommon.gestionedecodifiche.NazioneDTO;
import it.csi.silpcommonobj.dati.common.IndirizzoSilpDTO;

/**
 * The Class IndirizzoMapper.
 */
public class IndirizzoMapper extends BaseMapper<Indirizzo, IndirizzoDTO> {

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
    public <C extends IndirizzoDTO> C map(Indirizzo in, C out) {
        // Estratto da SendSAPMapper
        if (in != null && out != null) {
            out.setIndirizzo(in.getIndirizzoEsteso());

            out.setComune(null);
            out.setStato(null);
            out.setLocalita(in.getLocalita());
            if (in.getComune() != null) {
                ComuneDTO comuneDTO = new ComuneDTO();
                comuneDTO.setCodiceMinisteriale(in.getComune().getCodiceMinisteriale());
                comuneDTO.setCap(in.getComune().getCap());
                out.setComune(comuneDTO);
            }
            if (in.getStato() != null && !(NazioneDTO.COD_MIN_ITALIA.equals(in.getStato().getCodiceMinisteriale()))) {
                NazioneDTO statoDTO = new NazioneDTO();
                statoDTO.setCodiceMinisteriale(in.getStato().getCodiceMinisteriale());
                statoDTO.setSiglaNazione(in.getStato().getSigla());
                out.setStato(statoDTO);
            }
            if (in.getToponimo() != null && StringUtils.isNotBlank(in.getToponimo().getDescrizione()) && StringUtils.isNotBlank(in.getIndirizzo())
                    && StringUtils.isNotBlank(in.getNumeroCivico())) {
                // Ricompongo il dato per la SAP
                out.setIndirizzo(in.getToponimo().getDescrizione() + " " + in.getIndirizzo() + " " + in.getNumeroCivico());
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
    public <C extends Indirizzo> C mapReverse(IndirizzoDTO in, C out) {
        if (in == null || out == null) {
            return out;
        }
        out.setIndirizzoEsteso(in.getIndirizzo());
        out.setComune(cm.mapNullableReverse(in.getComune()));

        IndirizzoSilpDTO indirizzoSilpDTO = new IndirizzoSilpDTO();
        ManagerConversioneIndirizzo.getInstance().getIndirizzoFormatoSilp(in, indirizzoSilpDTO);
        out.setIndirizzo(indirizzoSilpDTO.getIndirizzo());
        out.setLocalita(indirizzoSilpDTO.getLocalita());
        out.setNumeroCivico(indirizzoSilpDTO.getNumeroCivico());

        if (indirizzoSilpDTO.getToponimo() != null) {
            Sedime toponimo = new Sedime();
            toponimo.setCodiceMinisteriale(indirizzoSilpDTO.getToponimo().getIdentificativoDecodifica());
            toponimo.setDescrizione(indirizzoSilpDTO.getToponimo().getDescrizione());
            out.setToponimo(toponimo);
        }
        out.setStato(nm.mapNullableReverse(in.getStato()));
        return out;
    }

    /**
     * Instantiate.
     *
     * @return the indirizzo
     */
    @Override
    protected Indirizzo instantiate() {
        return new Indirizzo();
    }

    /**
     * Instantiate reverse.
     *
     * @return the indirizzo DTO
     */
    @Override
    protected IndirizzoDTO instantiateReverse() {
        return new IndirizzoDTO();
    }

}
