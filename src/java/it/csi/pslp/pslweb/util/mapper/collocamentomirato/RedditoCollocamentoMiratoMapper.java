/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper.collocamentomirato;

import it.csi.pslp.pslweb.dto.be.RedditoCollocamentoMirato;
import it.csi.pslp.pslweb.util.mapper.BaseMapper;
import it.csi.pslp.pslweb.util.mapper.DecodificaMapper;
import it.csi.silpcommonobj.dati.collocamentomirato.riepilogo.RedditoCollocamentoMiratoDTO;

/**
 * The Class RedditoCollocamentoMiratoMapper.
 */
public class RedditoCollocamentoMiratoMapper extends BaseMapper<RedditoCollocamentoMirato, RedditoCollocamentoMiratoDTO>{

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
	public <C extends RedditoCollocamentoMiratoDTO> C map(RedditoCollocamentoMirato in, C out) {

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
	public <C extends RedditoCollocamentoMirato> C mapReverse(RedditoCollocamentoMiratoDTO in, C out) {
		out.setIdReddito(in.getIdReddito());
		out.setAnno(in.getAnno());
		out.setDataInserimento(in.getDataInserimento());
		out.setFonte(in.getFonte());
		out.setProvincia(dm.mapReverse(in.getProvincia()));
		out.getProvincia().setCodiceSilp(out.getProvincia().getCodiceMinisteriale());
		out.setCpi(dm.mapReverse(in.getCpi()));
		out.setValore(in.getValore());
		out.setNote(in.getNote());
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the reddito collocamento mirato
	 */
	@Override
	protected RedditoCollocamentoMirato instantiate() {
		return new RedditoCollocamentoMirato();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the reddito collocamento mirato DTO
	 */
	@Override
	protected RedditoCollocamentoMiratoDTO instantiateReverse() {
		return new RedditoCollocamentoMiratoDTO();
	}
	
}
