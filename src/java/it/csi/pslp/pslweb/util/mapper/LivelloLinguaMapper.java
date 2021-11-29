/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.silos.silcommon.gestionedecodifiche.LivelloLinguaDTO;

/**
 * The Class LivelloLinguaMapper.
 */
public class LivelloLinguaMapper extends BaseMapper<Decodifica, LivelloLinguaDTO>{

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
	public <C extends LivelloLinguaDTO> C map(Decodifica in, C out) {
		return dm.map(in, out);
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
	public <C extends Decodifica> C mapReverse(LivelloLinguaDTO in, C out) {
		out.setCodiceMinisteriale(in.getCodiceMinisteriale());
		//Si concatena codice a descrizione per la visualizzazione
		out.setDescrizione(in.getCodiceMinisteriale() + " "+ in.getDescrizione());
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
	 * @return the livello lingua DTO
	 */
	@Override
	protected LivelloLinguaDTO instantiateReverse() {
		return new LivelloLinguaDTO();
	}
	
}
