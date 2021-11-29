/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.DatoreLavoro;
import it.csi.silos.silcommon.dati.anagrafe.DatiAnagraficiAziendaDTO;
import it.csi.silos.silcommon.dati.azienda.DatoreLavoroDTO;
import it.csi.silos.silcommon.dati.common.RecapitoDTO;
import it.csi.silos.silcommon.gestionedecodifiche.AtecoDTO;

/**
 * The Class DatoreLavoroMapper.
 */
public class DatoreLavoroMapper extends BaseMapper<DatoreLavoro, DatoreLavoroDTO>{

	/** The dm. */
	private final DecodificaMapper dm = new DecodificaMapper();
	
	/** The im. */
	private final IndirizzoMapper im = new IndirizzoMapper();
	
	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends DatoreLavoroDTO> C map( DatoreLavoro in,  C out) {
	  out.setDatiAnagrafici(new DatiAnagraficiAziendaDTO());
    out.getDatiAnagrafici().setCodiceFiscale(in.getCodiceFiscale());
    out.getDatiAnagrafici().setDenominazione(in.getDenominazione());
    if(in.getSettore()!=null) {
      out.getDatiAnagrafici().setSettore(dm.map(in.getSettore(),new AtecoDTO()));
    }
    if(in.getIndirizzo()!=null) {
      out.setRecapito(new RecapitoDTO());
      out.getRecapito().setIndirizzo(im.map(in.getIndirizzo()));
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
	public <C extends DatoreLavoro> C mapReverse(DatoreLavoroDTO in, C out) {
		if(in!=null && in.getDatiAnagrafici()!=null) {
			out.setCodiceFiscale(in.getDatiAnagrafici().getCodiceFiscale());
			out.setDenominazione(in.getDatiAnagrafici().getDenominazione());
			out.setSettore(dm.mapReverse(in.getDatiAnagrafici().getSettore()));
			if(in.getRecapito()!=null && in.getRecapito().getIndirizzo()!=null) {
				out.setIndirizzo(im.mapReverse(in.getRecapito().getIndirizzo()));
			}
		}
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the datore lavoro
	 */
	@Override
	protected  DatoreLavoro instantiate() {
		return new  DatoreLavoro();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the datore lavoro DTO
	 */
	@Override
	protected  DatoreLavoroDTO instantiateReverse() {
		return new  DatoreLavoroDTO();
	}
	
}
