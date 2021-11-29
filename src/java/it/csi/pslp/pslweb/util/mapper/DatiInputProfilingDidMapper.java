/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.DatiInputProfilingDid;
import it.csi.silpcommonobj.dati.schedaprofessionale.did.pslp.DatiInputProfilingDidDTO;

/**
 * The Class DatiInputProfilingDidMapper.
 */
public class DatiInputProfilingDidMapper extends BaseMapper<DatiInputProfilingDid, DatiInputProfilingDidDTO> {

	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends DatiInputProfilingDidDTO> C map(DatiInputProfilingDid in, C out) {
		try {

			out.setAvutoAlmenoUnLav(in.isAvutoAlmenoUnLav());
			out.setCodiceFiscale(in.getCodiceFiscale());
			out.setFigliACarico(in.isFigliACarico());
			out.setFigliMinoriACarico(in.isFigliMinoriACarico());
			out.setIdAnagrafica(in.getIdAnagrafica());
			out.setIdCondizioneOccupazionale(in.getIdCondizioneOccupazionale());
			out.setIdIscrizioneCorsi(in.getIdIscrizioneCorsi());
			out.setIdPosizioneProfessionale(in.getIdPosizioneProfessionale());
			out.setIdPresenzaInItalia(in.getIdPresenzaInItalia());
			out.setIdTitoloStudio(in.getIdTitoloStudio());
			out.setNumComponentiFamiglia(
					null == in.getNumComponentiFamiglia() ? 0 : in.getNumComponentiFamiglia().longValue());
			out.setNumMesiFineUltimoLavoro(
					null == in.getNumMesiFineUltimoLavoro() ? 0 : in.getNumMesiFineUltimoLavoro().longValue());
			out.setNumMesiRicercaLavoro(
					null == in.getNumMesiRicercaLavoro() ? 0 : in.getNumMesiRicercaLavoro().longValue());
		} catch (NumberFormatException e) {
			throw e;
		}
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
	public <C extends DatiInputProfilingDid> C mapReverse(DatiInputProfilingDidDTO in, C out) {
		out.setAvutoAlmenoUnLav(in.getAvutoAlmenoUnLav());
		out.setCodiceFiscale(in.getCodiceFiscale());
		out.setFigliACarico(in.getFigliACarico());
		out.setFigliMinoriACarico(in.getFigliMinoriACarico());
		out.setIdAnagrafica(in.getIdAnagrafica());
		out.setIdCondizioneOccupazionale(in.getIdCondizioneOccupazionale());
		out.setIdIscrizioneCorsi(in.getIdIscrizioneCorsi());
		out.setIdPosizioneProfessionale(in.getIdPosizioneProfessionale());
		out.setIdPresenzaInItalia(in.getIdPresenzaInItalia());
		out.setIdTitoloStudio(in.getIdTitoloStudio());
		out.setNumComponentiFamiglia(in.getNumComponentiFamiglia());
		out.setNumMesiFineUltimoLavoro(in.getNumMesiFineUltimoLavoro().longValue());
		out.setNumMesiRicercaLavoro(in.getNumMesiRicercaLavoro().longValue());

		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the dati input profiling did
	 */
	@Override
	protected DatiInputProfilingDid instantiate() {
		return new DatiInputProfilingDid();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the dati input profiling did DTO
	 */
	@Override
	protected DatiInputProfilingDidDTO instantiateReverse() {
		return new DatiInputProfilingDidDTO();
	}

}
