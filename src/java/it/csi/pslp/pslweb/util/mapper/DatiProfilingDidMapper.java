/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.DatiProfilingDid;
import it.csi.silpcommonobj.dati.schedaprofessionale.profiling.ProfilingDidHeaderSilpDTO;

/**
 * The Class DatiProfilingDidMapper.
 */
public class DatiProfilingDidMapper extends BaseMapper<DatiProfilingDid, ProfilingDidHeaderSilpDTO> {

	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends ProfilingDidHeaderSilpDTO> C map(DatiProfilingDid in, C out) {

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
	public <C extends DatiProfilingDid> C mapReverse(ProfilingDidHeaderSilpDTO in, C out) {
		out.setCodiceMinisterialeTitoloStudio(in.getCodiceMinisterialeTitoloStudio());
		out.setDataDid(in.getDataDid());
		out.setDataInserimento(in.getDataInserimento());
		out.setDsCittadinanza(in.getDsCittadinanza());
		out.setDsCondizioneOccupazionale(in.getDsCondizioneOccupazionale());
		out.setDsIscrizioneCorsi(in.getDsIscrizioneCorsi());
		out.setDsPosizioneProfessionale(in.getDsPosizioneProfessionale());
		
		if (null != in.getDsPresenzaInItalia() && 
				!in.getDsPresenzaInItalia().equals("")
				&& in.getDsPresenzaInItalia().indexOf('(') > 0) {
			out.setDsPresenzaInItalia(in.getDsPresenzaInItalia().substring(0, in.getDsPresenzaInItalia().indexOf('(')));
		}  else {
			out.setDsPresenzaInItalia(in.getDsPresenzaInItalia());
		}
		
		out.setDsPresenzaInItalia(in.getDsPresenzaInItalia());
		out.setDsTitoloStudio(in.getDsTitoloStudio());
		out.setEta(in.getEta().intValue());
		out.setFlgFigliCarico(in.getFlgFigliCarico());
		out.setFlgFigliCaricoMinori(in.getFlgFigliCaricoMinori());
		out.setFlgGenere(in.getFlgGenere());
		out.setFlgHaAvutoLavoro(in.getFlgHaAvutoLavoro());
		out.setDsProvinciaResidenza(in.getDsProvinciaResidenza());
		if (null != in.getIndiceProfiling()) {
			out.setIndiceProfiling(in.getIndiceProfiling().toString());
		}
		out.setNumComponentiFamiglia(in.getNumComponentiFamiglia().intValue());
		out.setNumMesiRicercaLavoro(in.getNumMesiRicercaLavoro().intValue());
		out.setNumMesiUltimoRapporto(in.getNumMesiUltimoRapporto().intValue());
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the dati profiling did
	 */
	@Override
	protected DatiProfilingDid instantiate() {
		return new DatiProfilingDid();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the profiling did header silp DTO
	 */
	@Override
	protected ProfilingDidHeaderSilpDTO instantiateReverse() {
		return new ProfilingDidHeaderSilpDTO();
	}

}
