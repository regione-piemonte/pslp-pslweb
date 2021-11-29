/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.EsperienzaLavoro;
import it.csi.silos.silcommon.dati.lavoratore.curriculum.EsperienzaLavorativaDTO;
import it.csi.silos.silcommon.gestionedecodifiche.LivelloContrattoDTO;
import it.csi.silos.silcommon.gestionedecodifiche.ModalitaLavoroDTO;
import it.csi.silos.silcommon.gestionedecodifiche.QualificaDTO;
import it.csi.silos.silcommon.gestionedecodifiche.TipoContrattoDTO;
import it.csi.silpcommonobj.util.SilpConvertUtils;

/**
 * The Class EsperienzeLavoroMapper.
 */
public class EsperienzeLavoroMapper extends BaseMapper<EsperienzaLavoro, EsperienzaLavorativaDTO> {

    /** The dm. */
    DecodificaMapper dm = new DecodificaMapper();
    
    /** The im. */
    IndirizzoMapper im = new IndirizzoMapper();
    
    /** The dlm. */
    DatoreLavoroMapper dlm = new DatoreLavoroMapper();

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends EsperienzaLavorativaDTO> C map(EsperienzaLavoro in, C out) {
        out.setDataInizio(in.getDataInizioRapporto());
        out.setDataFine(in.getDataFineRapporto());
        out.setTipoContratto(dm.mapNullable(in.getTipoContratto(), new TipoContrattoDTO()));
        out.setQualifica(dm.mapNullable(in.getQualifica(), new QualificaDTO()));
        out.setGradoContrattuale(dm.mapNullable(in.getCategoriaInquadramento(), new LivelloContrattoDTO()));

        out.setDatoreLavoro(dlm.mapNullable(in.getDatoreLavoro()));
        out.setUtilizzatrice(dlm.mapNullable(in.getAziendaUtilizzatrice()));
        out.setDataFinePeriodoFormativo(in.getDataFinePeriodoFormativo());
        out.setPrincipaliMansioni(in.getPrincipaliMansioni());
        out.setAssunzioneLegge68(toSiNo(in.isAssunzioneL68()));

        out.setLavoratoreInMobilita(toSiNo(in.isLavoratoreInMobilita()));
        out.setLavoroStagionale(toSiNo(in.isLavoroStagionale()));
        out.setLavoroInAgricoltura(toSiNo(in.isLavoroInAgricoltura()));
        out.setModalitaLavoro(dm.mapNullable(in.getModalitaLavoro(), new ModalitaLavoroDTO()));
        out.setLuogoLavoro(im.mapNullable(in.getIndirizzoDiLavoro()));

        if (in.isEsperienzaDichiarata()) {
            out.getDatiAggiornamento().setTipoSpecificoEntita(EsperienzaLavorativaDTO.TIPO_ESPERIENZA_DICHIARATA);
        } else {
            out.getDatiAggiornamento().setTipoSpecificoEntita(EsperienzaLavorativaDTO.TIPO_ESPERIENZA_RAPPORTO);
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
    public <C extends EsperienzaLavoro> C mapReverse(EsperienzaLavorativaDTO in, C out) {
        out.setDataInizioRapporto(in.getDataInizio());
        out.setDataFineRapporto(in.getDataFine());
        out.setTipoContratto(dm.mapReverse(in.getTipoContratto()));

        out.setQualifica(dm.mapReverse(in.getQualifica()));
        out.setCategoriaInquadramento(dm.mapReverse(in.getGradoContrattuale()));

        out.setDatoreLavoro(dlm.mapReverse(in.getDatoreLavoro()));
        out.setAziendaUtilizzatrice(dlm.mapReverse(in.getUtilizzatrice()));
        out.setDataFinePeriodoFormativo(in.getDataFinePeriodoFormativo());
        out.setPrincipaliMansioni(in.getPrincipaliMansioni());
        out.setAssunzioneL68(SilpConvertUtils.toBoolean(in.getAssunzioneLegge68()));

        out.setLavoratoreInMobilita(SilpConvertUtils.toBoolean(in.getLavoratoreInMobilita()));
        out.setLavoroStagionale(SilpConvertUtils.toBoolean(in.getLavoroStagionale()));
        out.setLavoroInAgricoltura(SilpConvertUtils.toBoolean(in.getLavoroInAgricoltura()));
        out.setModalitaLavoro(dm.mapReverse(in.getModalitaLavoro()));
        out.setIndirizzoDiLavoro(im.mapNullableReverse(in.getLuogoLavoro()));

        out.setEsperienzaDichiarata(in.isTipoEsperienzaDichiarata());
        return out;
    }

    /**
     * Instantiate.
     *
     * @return the esperienza lavoro
     */
    @Override
    protected EsperienzaLavoro instantiate() {
        return new EsperienzaLavoro();
    }

    /**
     * Instantiate reverse.
     *
     * @return the esperienza lavorativa DTO
     */
    @Override
    protected EsperienzaLavorativaDTO instantiateReverse() {
        return new EsperienzaLavorativaDTO();
    }

}
