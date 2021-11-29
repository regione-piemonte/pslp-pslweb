/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.ConoscenzaInformatica;
import it.csi.silos.silcommon.dati.lavoratore.curriculum.ConoscenzaInformaticaDTO;
import it.csi.silos.silcommon.gestionedecodifiche.LivelloConoscenzaInformaticaDTO;
import it.csi.silos.silcommon.gestionedecodifiche.TipoConoscenzaInformaticaDTO;


/**
 * The Class ConoscenzeInformaticheMapper.
 */
public class ConoscenzeInformaticheMapper extends BaseMapper<ConoscenzaInformatica, ConoscenzaInformaticaDTO>{

	/** The dm. */
	private DecodificaMapper dm = new DecodificaMapper();
	
	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends ConoscenzaInformaticaDTO> C map(ConoscenzaInformatica in, C out) {
		out.setTipoConoscenza(dm.map(in.getTipoConoscenza(),new TipoConoscenzaInformaticaDTO()));
		out.setLivelloConoscenza(dm.map(in.getLivelloConoscenza(),new LivelloConoscenzaInformaticaDTO()));
		out.setDescrizioneSpecifiche(in.getEventualiSpecifiche());
		out.setCategoriaConoscenza(in.getCodiceCategoriaConoscenzaInformatica());
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
	public <C extends ConoscenzaInformatica> C mapReverse(ConoscenzaInformaticaDTO in, C out) {
		out.setTipoConoscenza(dm.mapReverse(in.getTipoConoscenza()));
		out.setLivelloConoscenza(dm.mapReverse(in.getLivelloConoscenza()));
		out.setEventualiSpecifiche(in.getDescrizioneSpecifiche());
		out.setCodiceCategoriaConoscenzaInformatica(in.getCategoriaConoscenza());
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the conoscenza informatica
	 */
	@Override
	protected ConoscenzaInformatica instantiate() {
		return new ConoscenzaInformatica();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the conoscenza informatica DTO
	 */
	@Override
	protected ConoscenzaInformaticaDTO instantiateReverse() {
		return new ConoscenzaInformaticaDTO();
	}

	
	
}
