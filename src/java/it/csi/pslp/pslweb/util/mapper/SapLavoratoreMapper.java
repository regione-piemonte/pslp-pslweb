/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.SchedaAnagraficoProfessionale;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.SchedaAnagraficoProfessionaleDTO;
import it.csi.silpservizi.sesp.bo.sap.SchedaAnagraficoProfessionaleLavoratoreDTO;

/**
 * Classe per mappare da oggetto di tipo scheda professionale da PSLP (client e web) a oggetto di silpsvsp che contiene la sap piu' alcune informazioni specifiche si silp (vari id) .
 *
 * @author 1871
 */
public class SapLavoratoreMapper extends BaseMapper<SchedaAnagraficoProfessionale, SchedaAnagraficoProfessionaleLavoratoreDTO>{
	
	/**
	 * Instantiate.
	 *
	 * @return the scheda anagrafico professionale
	 */
	@Override
	protected SchedaAnagraficoProfessionale instantiate() {
		return new SchedaAnagraficoProfessionale();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the scheda anagrafico professionale lavoratore DTO
	 */
	@Override
	protected SchedaAnagraficoProfessionaleLavoratoreDTO instantiateReverse() {
		return new SchedaAnagraficoProfessionaleLavoratoreDTO();
	}
	
	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends SchedaAnagraficoProfessionaleLavoratoreDTO> C map(SchedaAnagraficoProfessionale in, C out) {
		
    out.setIdLavoratore(in.getIdSilLavAnagrafica());
    out.setIdInterscambio(in.getIdInterscambioSap());
    out.setSchedaAnagraficaProfessionale(new SchedaAnagraficoProfessionaleDTO());

    new SapMapper().map(in, out.getSchedaAnagraficaProfessionale());

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
	public <C extends SchedaAnagraficoProfessionale> C mapReverse(SchedaAnagraficoProfessionaleLavoratoreDTO in, C out) {

		/**
		 * Preleva alcuni campi con identificativi interni di silp e li pone nella sap usata da pslp
		 * idAnagraficaSilp: rappresenta l'id interno dellavoratore in silp
		 * idInterscambio: identificativo del tracciamento importazione di una sap inviata a silp. Potrebbe essere utile in futuro per riottenere una data fotografia di una sap inviata
		 */
		if(in!=null && out!=null) {
			out.setIdSilLavAnagrafica(in.getIdLavoratore());
			out.setIdInterscambioSap(in.getIdInterscambio());
		}

		//poi converto la sap effettiva dai dati ministeriali
		new SapMapper().mapReverse(in.getSchedaAnagraficaProfessionale(), out);

		return out;
	}

	
}
