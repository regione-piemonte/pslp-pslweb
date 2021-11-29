/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import org.apache.commons.lang.StringUtils;

import it.csi.pslp.pslweb.business.integration.AdapterSilpsvdeWSImpl;
import it.csi.pslp.pslweb.dto.be.Nazione;
import it.csi.silos.silcommon.gestionedecodifiche.NazioneDTO;

/**
 * The Class NazioneMapper.
 */
public class NazioneMapper extends BaseMapper<Nazione, NazioneDTO>{

	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends NazioneDTO> C map(Nazione in, C out) {
		if(in == null || out == null) {
			return out;
		}
		out.setCodiceMinisteriale(in.getCodiceMinisteriale());
		out.setDescrizione(in.getDescrizione());
		out.setSiglaNazione(in.getSigla());
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
	public <C extends Nazione> C mapReverse(NazioneDTO in, C out) {
		if(in == null || out == null) {
			return out;
		}
		out.setCodiceMinisteriale(in.getCodiceMinisteriale());
		out.setDescrizione(in.getDescrizione());
		out.setSigla(in.getSiglaNazione());
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the nazione
	 */
	@Override
	protected Nazione instantiate() {
		return new Nazione();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the nazione DTO
	 */
	@Override
	protected NazioneDTO instantiateReverse() {
		return new NazioneDTO();
	}
	
	/**
	 * Gets the nazione by codice.
	 *
	 * @param codiceNazione the codice nazione
	 * @return the nazione by codice
	 */
	public Nazione getNazioneByCodice(String codiceNazione) {
		if(StringUtils.isNotBlank(codiceNazione)) {
			try {
				return AdapterSilpsvdeWSImpl.getInstance().getNazioneByCodice(codiceNazione);
			} catch (Exception e) {
				log.error("Errore nel reperimento della nazione da AdapterSilpsvdeWSImpl.getNazioneByCodice for codice \"" + codiceNazione + "\"", e);
			}
		}
		return null;
	}
}
