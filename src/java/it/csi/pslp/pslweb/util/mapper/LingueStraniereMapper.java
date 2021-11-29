/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.Lingua;
import it.csi.silos.silcommon.gestionedecodifiche.LinguaDTO;

/**
 * The Class LingueStraniereMapper.
 */
public class LingueStraniereMapper extends BaseMapper<Lingua, LinguaDTO>{

	/** The dm. */
	DecodificaMapper dm = new DecodificaMapper();
	
	/** The llm. */
	LivelloLinguaMapper llm = new LivelloLinguaMapper();
	
	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends LinguaDTO> C map(Lingua in, C out) {
		out.setCodiceMinisteriale(in.getLingua().getCodiceMinisteriale());
		out.setLivelloLetto(llm.map(in.getLetto()));
		out.setLivelloScritto(llm.map(in.getScritto()));
		out.setLivelloParlato(llm.map(in.getParlato()));
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
	public <C extends Lingua> C mapReverse(LinguaDTO in, C out) {
		out.setLingua(dm.mapReverse(in));
		out.setLetto(llm.mapReverse(in.getLivelloLetto()));
		out.setScritto(llm.mapReverse(in.getLivelloScritto()));
		out.setParlato(llm.mapReverse(in.getLivelloParlato()));
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the lingua
	 */
	@Override
	protected Lingua instantiate() {
		return new Lingua();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the lingua DTO
	 */
	@Override
	protected LinguaDTO instantiateReverse() {
		return new LinguaDTO();
	}
	
}
