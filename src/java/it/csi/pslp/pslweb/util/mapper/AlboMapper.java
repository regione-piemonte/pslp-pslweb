/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.silos.silcommon.gestionedecodifiche.AlboDTO;


/**
 * The Class AlboMapper.
 */
public class AlboMapper extends BaseMapper<Decodifica, AlboDTO>{

	/** The dm. */
	private final DecodificaMapper dm = new DecodificaMapper();
	
	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends AlboDTO> C map(Decodifica in, C out) {
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
	public <C extends Decodifica> C mapReverse(AlboDTO in, C out) {
		return mapReverse(in, out);
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
	 * @return the albo DTO
	 */
	@Override
	protected AlboDTO instantiateReverse() {
		return new AlboDTO();
	}
	
}
