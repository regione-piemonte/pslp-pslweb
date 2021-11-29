/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.silos.silcommon.gestionedecodifiche.DecodificaDTO;

/**
 * The Class DecodificaMapper.
 */
public class DecodificaMapper extends BaseMapper<Decodifica, DecodificaDTO> {

	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends DecodificaDTO> C map(Decodifica in, C out) {
		out.setCodiceMinisteriale(in.getCodiceMinisteriale());
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
	public <C extends Decodifica> C mapReverse(DecodificaDTO in, C out) {
		out.setCodiceMinisteriale(in.getCodiceMinisteriale());
		out.setDescrizione(in.getDescrizione());
		return out;
	}
	
	/**
	 * Map.
	 *
	 * @param codice the codice
	 * @param descrizione the descrizione
	 * @return the decodifica
	 */
	public Decodifica map(String codice, String descrizione) {
		Decodifica out = new Decodifica();
		out.setCodiceMinisteriale(codice);
		out.setDescrizione(descrizione);
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
	 * @return the decodifica DTO
	 */
	@Override
	protected DecodificaDTO instantiateReverse() {
		return new DecodificaDTO();
	}

}
