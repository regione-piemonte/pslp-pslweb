/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.Provincia;
import it.csi.silos.silcommon.gestionedecodifiche.ProvinciaDTO;

/**
 * The Class ProvinciaMapper.
 */
public class ProvinciaMapper extends BaseMapper<Provincia, ProvinciaDTO>{

	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends ProvinciaDTO> C map(Provincia in, C out) {
    out.setCodiceMinisteriale(in.getCodiceMinisteriale());
    out.setDescrizione(in.getDescrizione());
    out.setTarga(in.getTarga());
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
	public <C extends Provincia> C mapReverse(ProvinciaDTO in, C out) {
		if(in!=null) {
			out.setCodiceMinisteriale(in.getCodiceMinisteriale());
			out.setDescrizione(in.getDescrizione());
			out.setTarga(in.getTarga());
		}
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the provincia
	 */
	@Override
	protected Provincia instantiate() {
		return new Provincia();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the provincia DTO
	 */
	@Override
	protected ProvinciaDTO instantiateReverse() {
		return new ProvinciaDTO();
	}
	
}
