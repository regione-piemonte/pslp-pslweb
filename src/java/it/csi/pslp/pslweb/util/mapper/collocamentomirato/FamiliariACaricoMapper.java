/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper.collocamentomirato;

import it.csi.pslp.pslweb.dto.be.DichiarazioneFamiliariCollocamentoMirato;
import it.csi.pslp.pslweb.util.mapper.BaseMapper;
import it.csi.pslp.pslweb.util.mapper.DecodificaMapper;
import it.csi.silpcommonobj.dati.collocamentomirato.riepilogo.DichiarazioneFamiliariCollocamentoMiratoDTO;

/**
 * The Class FamiliariACaricoMapper.
 */
public class FamiliariACaricoMapper extends BaseMapper<DichiarazioneFamiliariCollocamentoMirato, DichiarazioneFamiliariCollocamentoMiratoDTO>{

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
	public <C extends DichiarazioneFamiliariCollocamentoMiratoDTO> C map(DichiarazioneFamiliariCollocamentoMirato in, C out) {

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
	public <C extends DichiarazioneFamiliariCollocamentoMirato> C mapReverse(DichiarazioneFamiliariCollocamentoMiratoDTO in, C out) {
		out.setIdDichiarazione(in.getIdDichiarazione());
		out.setAnno(in.getAnno());
		out.setDataDichiarazione(in.getDataDichiarazione());
		out.setNumeroFamiliariACarico(in.getNumeroFamiliariACarico());
		out.setFonte(in.getFonte());
		out.setCpi(dm.mapReverse(in.getCpi()));
		out.setNote(in.getNote());
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the dichiarazione familiari collocamento mirato
	 */
	@Override
	protected DichiarazioneFamiliariCollocamentoMirato instantiate() {
		return new DichiarazioneFamiliariCollocamentoMirato();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the dichiarazione familiari collocamento mirato DTO
	 */
	@Override
	protected DichiarazioneFamiliariCollocamentoMiratoDTO instantiateReverse() {
		return new DichiarazioneFamiliariCollocamentoMiratoDTO();
	}
	
}
