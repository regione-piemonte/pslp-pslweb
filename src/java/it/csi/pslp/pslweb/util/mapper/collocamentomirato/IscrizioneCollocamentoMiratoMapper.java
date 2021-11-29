/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper.collocamentomirato;

import it.csi.pslp.pslweb.dto.be.IscrizioneCollocamentoMirato;
import it.csi.pslp.pslweb.util.mapper.BaseMapper;
import it.csi.pslp.pslweb.util.mapper.DecodificaMapper;
import it.csi.silpcommonobj.dati.collocamentomirato.riepilogo.IscrizioneCollocamentoMiratoDTO;

/**
 * The Class IscrizioneCollocamentoMiratoMapper.
 */
public class IscrizioneCollocamentoMiratoMapper extends BaseMapper<IscrizioneCollocamentoMirato, IscrizioneCollocamentoMiratoDTO>{

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
	public <C extends IscrizioneCollocamentoMiratoDTO> C map(IscrizioneCollocamentoMirato in, C out) {

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
	public <C extends IscrizioneCollocamentoMirato> C mapReverse(IscrizioneCollocamentoMiratoDTO in, C out) {
		out.setIdIscrizione(in.getIdIscrizione());
		out.setDataIscrizione(in.getDataIscrizione());
		out.setDataAnzianita(in.getDataAnzianita());
		out.setNumeroIscrizione(in.getNumeroIscrizione());
		out.setProvincia(dm.mapReverse(in.getProvincia()));
		out.getProvincia().setCodiceSilp(out.getProvincia().getCodiceMinisteriale());
		out.setCpi(dm.mapReverse(in.getCpi()));
        out.setIdCpiSilp(in.getIdSilpCpi());
        out.setResponsabileCpi(in.getResponsabileCpi());
        out.setCategoria(in.getCategoria());
		out.setGradoInvalidita(in.getGradoInvalidita());
		out.setPercentualeInvalidita(in.getPercentualeInvalidita());
		
		out.setDataStato(in.getDataStato());
		out.setStato(dm.mapReverse(in.getStato()));
		out.setStatoFinale(in.isStatoFinale());
		out.setNote(in.getNote());
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the iscrizione collocamento mirato
	 */
	@Override
	protected IscrizioneCollocamentoMirato instantiate() {
		return new IscrizioneCollocamentoMirato();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the iscrizione collocamento mirato DTO
	 */
	@Override
	protected IscrizioneCollocamentoMiratoDTO instantiateReverse() {
		return new IscrizioneCollocamentoMiratoDTO();
	}

	
	
	
	
}
