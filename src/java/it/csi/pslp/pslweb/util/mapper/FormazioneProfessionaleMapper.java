/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.FormazioneProfessionale;
import it.csi.silos.silcommon.dati.anagrafe.DatiAnagraficiAziendaDTO;
import it.csi.silos.silcommon.dati.azienda.AziendaDTO;
import it.csi.silos.silcommon.dati.lavoratore.curriculum.FormazioneDTO;
import it.csi.silos.silcommon.gestionedecodifiche.ComuneDTO;
import it.csi.silos.silcommon.gestionedecodifiche.ProvinciaDTO;
import it.csi.silos.silcommon.gestionedecodifiche.RegioneDTO;
import it.csi.silpcommonobj.util.SilpConvertUtils;

/**
 * The Class FormazioneProfessionaleMapper.
 */
public class FormazioneProfessionaleMapper extends BaseMapper<FormazioneProfessionale, FormazioneDTO>{

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
	public <C extends FormazioneDTO> C map(FormazioneProfessionale in, C out) {
    out.setTitoloCorso(in.getTitoloCorso());
    out.setEnteErogatore(in.getEnteErogatore());
    
    if(in.getRegioneSede()!=null) {
      out.setComuneSede(new ComuneDTO());
      out.getComuneSede().setProvincia(new ProvinciaDTO());
      out.getComuneSede().getProvincia().setRegione(dm.map(in.getRegioneSede(), new RegioneDTO()));
    }
    out.setDurata(SilpConvertUtils.toString(in.getDurata()));
    out.setCodiceTipologiaDurata(in.getTipoDurata());
    if(in.getCertificazioniAttestazioni()!=null) {
      out.setCodiceAttestazione(in.getCertificazioniAttestazioni().getCodiceMinisteriale());
      //out.setDescrizioneAttestazione(in.getCertificazioniAttestazioni().getDescrizione());
    }
   
    out.setStage(toSiNo(in.isStage()));
  
    
    if(in.getNomeAziendaStage()!=null) {
      out.setAziendaStage(new AziendaDTO());
      out.getAziendaStage().setDatiAnagrafici(new DatiAnagraficiAziendaDTO());
      out.getAziendaStage().getDatiAnagrafici().setDenominazione(in.getNomeAziendaStage());
    }
    out.setCodiceEnteFp(in.getCodiceFp());
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
	public <C extends FormazioneProfessionale> C mapReverse(FormazioneDTO in, C out) {
		
		out.setTitoloCorso(in.getTitoloCorso());
		out.setEnteErogatore(in.getEnteErogatore());
		
		if(in.getComuneSede()!=null && in.getComuneSede().getProvincia()!=null) {
			out.setRegioneSede(dm.mapReverse(in.getComuneSede().getProvincia().getRegione()));
		}
		out.setDurata(SilpConvertUtils.toInteger(in.getDurata()));
		out.setTipoDurata(in.getCodiceTipologiaDurata());
		out.setCertificazioniAttestazioni(dm.map(in.getCodiceAttestazione(),in.getDescrizioneAttestazione()));
		out.setStage(in.getFlgStageBoolean());
		if(in.getAziendaStage()!=null && in.getAziendaStage().getDatiAnagrafici()!=null) {
			out.setNomeAziendaStage(in.getAziendaStage().getDatiAnagrafici().getDenominazione());
		}
		out.setCodiceFp(in.getCodiceEnteFp());
		
		
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the formazione professionale
	 */
	@Override
	protected FormazioneProfessionale instantiate() {
		return new FormazioneProfessionale();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the formazione DTO
	 */
	@Override
	protected FormazioneDTO instantiateReverse() {
		return new FormazioneDTO();
	}
	
}
