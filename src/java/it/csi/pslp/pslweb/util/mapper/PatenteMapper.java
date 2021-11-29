/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.silos.silcommon.gestionedecodifiche.PatenteDTO;


/**
 * The Class PatenteMapper.
 */
public class PatenteMapper extends BaseMapper<Decodifica, PatenteDTO>{

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
	public <C extends PatenteDTO> C map(Decodifica in, C out) {
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
	public <C extends Decodifica> C mapReverse(PatenteDTO in, C out) {
		return dm.mapReverse(in, out);
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
	 * @return the patente DTO
	 */
	@Override
	protected PatenteDTO instantiateReverse() {
		return new PatenteDTO();
	}
	
}
