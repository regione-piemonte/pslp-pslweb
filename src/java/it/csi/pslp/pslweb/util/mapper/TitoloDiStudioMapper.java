/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.GradoStudio;
import it.csi.pslp.pslweb.dto.be.TitoloDiStudio;
import it.csi.silos.silcommon.gestionedecodifiche.LivelloTitStudioDTO;
import it.csi.silos.silcommon.gestionedecodifiche.TitoloStudioDTO;
import it.csi.silpcommonobj.util.SilpConvertUtils;

/**
 * The Class TitoloDiStudioMapper.
 */
public class TitoloDiStudioMapper extends BaseMapper<TitoloDiStudio, TitoloStudioDTO> {

    /** The lm. */
    LuogoMapper lm = new LuogoMapper();
    
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
    public <C extends TitoloStudioDTO> C map(TitoloDiStudio in, C out) {
        if (in.getLivelloScolarizzazione() != null) {
            out.setCodiceMinisteriale(in.getLivelloScolarizzazione().getCodice());
        }

        if (in.getCorsoDiStudio() != null) {
            out.setLivelloTitoloStudio(new LivelloTitStudioDTO());
            out.getLivelloTitoloStudio().setCodiceMinisteriale(in.getCorsoDiStudio().getCodiceMinisteriale());
        }
        out.setDescrizioneAggiuntiva(in.getDescrizione());
        if (in.getFrequentatoIn() != null) {
            out.setLuogoFrequentazione(lm.map(in.getFrequentatoIn()));
        }
        out.setRiconosciutoInItalia(toSiNo(in.isRiconosciutoInItalia()));
        out.setAnnoConseguimento(SilpConvertUtils.toString(in.getAnnoSeConseguito()));
        out.setVotazione(in.getVotazioneConseguita());
        out.setAnnoUltimaFrequentazione(SilpConvertUtils.toString(in.getUltimoAnnoFrequentatoSeNonConseguito()));
        out.setAnnoFrequenzaInCorso(SilpConvertUtils.toString(in.getAnnoDiFrequenzaSeInCorso()));
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
    public <C extends TitoloDiStudio> C mapReverse(TitoloStudioDTO in, C out) {
        if (in == null)
            return out;

        out.setCorsoDiStudio(dm.mapNullableReverse(in.getLivelloTitoloStudio()));
        GradoStudio g = new GradoStudio();
        g.setCodice(in.getCodiceMinisteriale());
        g.setDescrizione(in.getDescrizione());
        out.setLivelloScolarizzazione(g);

        out.setDescrizione(in.getDescrizioneAggiuntiva());
        out.setFrequentatoIn(lm.mapReverse(in.getLuogoFrequentazione()));
        out.setRiconosciutoInItalia(SilpConvertUtils.toBoolean(in.getRiconosciutoInItalia()));
        out.setAnnoSeConseguito(SilpConvertUtils.toInteger(in.getAnnoConseguimento()));
        out.setVotazioneConseguita(in.getVotazione());
        out.setUltimoAnnoFrequentatoSeNonConseguito(SilpConvertUtils.toInteger(in.getAnnoUltimaFrequentazione()));
        out.setAnnoDiFrequenzaSeInCorso(SilpConvertUtils.toInteger(in.getAnnoFrequenzaInCorso()));

        return out;
    }

    /**
     * Instantiate.
     *
     * @return the titolo di studio
     */
    @Override
    protected TitoloDiStudio instantiate() {
        return new TitoloDiStudio();
    }

    /**
     * Instantiate reverse.
     *
     * @return the titolo studio DTO
     */
    @Override
    protected TitoloStudioDTO instantiateReverse() {
        return new TitoloStudioDTO();
    }

}
