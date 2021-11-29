/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.ListaSpeciale;
import it.csi.silos.silcommon.dati.lavoratore.ListeSpecialiDTO;
import it.csi.silos.silcommon.gestionedecodifiche.ProvinciaDTO;
import it.csi.silpcommonobj.util.SilpConvertUtils;

/**
 * The Class ListeSpecialiMapper.
 */
public class ListeSpecialiMapper extends BaseMapper<ListaSpeciale, ListeSpecialiDTO>{

	/** The provincia mapper. */
	ProvinciaMapper provinciaMapper = new ProvinciaMapper();
	
	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends ListeSpecialiDTO> C map(ListaSpeciale in, C out) {
    out.setCodiceMinisteriale(in.getTipoLista().toString());
    out.setDataIscrizioneLista(in.getDataIscrizioneLista());
    out.setDataFineIscrizione(in.getDataTermineIscrizione());
    out.setDataMaxDeferimento(in.getDataMassimoDifferimento());
    out.setProvincia((ProvinciaDTO)provinciaMapper.map(in.getProvincia(),new ProvinciaDTO()));
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
	public <C extends ListaSpeciale> C mapReverse(ListeSpecialiDTO in, C out) {
		if(in!=null) {
			out.setTipoLista(SilpConvertUtils.toLong(in.getCodiceMinisteriale()));
			out.setDataIscrizioneLista(in.getDataIscrizioneLista());
			out.setDataTermineIscrizione(in.getDataFineIscrizione());
			out.setDataMassimoDifferimento(in.getDataMaxDeferimento());
			out.setDescrizioneLista(in.getDescrizione());
			out.setProvincia(provinciaMapper.mapReverse(in.getProvincia()));
		}
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the lista speciale
	 */
	@Override
	protected ListaSpeciale instantiate() {
		return new ListaSpeciale();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the liste speciali DTO
	 */
	@Override
	protected ListeSpecialiDTO instantiateReverse() {
		return new ListeSpecialiDTO();
	}
	
}
