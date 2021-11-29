/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.DatiInputStatoAdesione;
import it.csi.silpcommonobj.dati.schedaprofessionale.adesioneyg.DatiInputSendStatoAdesioneSilpDTO;

/**
 * The Class DatiSendStatoAdesioneMapper.
 */
public class DatiSendStatoAdesioneMapper extends BaseMapper<DatiInputStatoAdesione, DatiInputSendStatoAdesioneSilpDTO>{

	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends DatiInputSendStatoAdesioneSilpDTO> C map(DatiInputStatoAdesione in, C out) {
		out.setCodiceFiscale(in.getCodiceFiscale());
		out.setCodiceFiscaleOperatore(in.getCodiceFiscaleOperatore());
		out.setCodiceStatoAdesione(in.getCodiceStatoAdesione());
		out.setDataAdesione(in.getDataAdesione());
		out.setDataStatoAdesione(in.getDataStatoAdesione());
		out.setIdAdesione(in.getIdAdesione());
		out.setIdAnagrafica(in.getIdAnagrafica());
		out.setCodiceSap(in.getIdentificativoSap());
		out.setMotivo(in.getMotivo());
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
	public <C extends DatiInputStatoAdesione> C mapReverse(DatiInputSendStatoAdesioneSilpDTO in, C out) {
		out.setCodiceFiscale(in.getCodiceFiscale());
		out.setCodiceFiscaleOperatore(in.getCodiceFiscaleOperatore());
		out.setCodiceStatoAdesione(in.getCodiceStatoAdesione());
		out.setDataAdesione(in.getDataAdesione());
		out.setDataStatoAdesione(in.getDataStatoAdesione());
		out.setIdAdesione(in.getIdAdesione());
		out.setIdAnagrafica(in.getIdAnagrafica());
		out.setIdentificativoSap(in.getCodiceSap());
		out.setMotivo(in.getMotivo());
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the dati input stato adesione
	 */
	@Override
	protected DatiInputStatoAdesione instantiate() {
		return new DatiInputStatoAdesione();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the dati input send stato adesione silp DTO
	 */
	@Override
	protected DatiInputSendStatoAdesioneSilpDTO instantiateReverse() {
		return new DatiInputSendStatoAdesioneSilpDTO();
	}
	
}
