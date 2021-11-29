/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.DatiAmministrativi;
import it.csi.silos.silcommon.dati.lavoratore.ListeSpecialiDTO;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.AltreNotizieSapDTO;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.AssolvimentoIstruzioneSapDTO;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.DatiAmministrativiSapDTO;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.StatoInAnagrafeSapDTO;
import it.csi.silos.silcommon.gestionedecodifiche.ClasseOccupazionaleDTO;
import it.csi.silos.silcommon.gestionedecodifiche.StatoOccupazionaleDTO;
import it.csi.silpcommonobj.util.SilpConvertUtils;

/**
 * The Class DatiAmministrativiMapper.
 */
public class DatiAmministrativiMapper extends BaseMapper<DatiAmministrativi, DatiAmministrativiSapDTO>{

	/** The lm. */
	ListeSpecialiMapper lm = new ListeSpecialiMapper();
	
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
	public <C extends DatiAmministrativiSapDTO> C map(DatiAmministrativi in, C out) {

		if(in==null) {
			return out;
		}

		//Si costruisce la sezione stato in anagrafe solo se c'e' almeno un elemento significativo.
		//Altrimeneti la sap in silp valuterebbe l'assenza di dat obbligatori nella sezione
		StatoInAnagrafeSapDTO statoAnag = null;
		if(in.getStatoOccupazionale() !=null || in.getCategoriaDlg297() !=null || in.getAnzianitaDisoccupazioneMesi() !=null 
				|| in.getIndiceProfiling() !=null || in.getDataEvento() !=null || in.getDataDichiarazioneDisponibilita() != null ) {
			statoAnag = new StatoInAnagrafeSapDTO();
			out.setStatoInAnagrafe(statoAnag);
		} 

		//Occhio a questa rimappatura. Nella sap stato e' DI, IN, A,L OC ossia le CLASSI occupazionali piu' generiche di silp
		if(in.getStatoOccupazionale()!=null) {
			statoAnag.setStatoOccupazionale(new StatoOccupazionaleDTO());
			statoAnag.getStatoOccupazionale().setClasse(new ClasseOccupazionaleDTO());
			statoAnag.getStatoOccupazionale().getClasse().setCodiceMinisteriale(in.getStatoOccupazionale().getCodiceMinisteriale());
			//La condizione e' invece l'effettivo stato occupazionale piu' specifico silp (es: SO,CO) 
			if(in.getCondizione()!=null) {
				statoAnag.getStatoOccupazionale().setCodiceMinisteriale(in.getCondizione().getCodiceMinisteriale());
				statoAnag.getStatoOccupazionale().setDescrizione(in.getCondizione().getDescrizione());
			}
		}

		if(in.getCategoriaDlg297()!=null) {
			statoAnag.setCategoria297(in.getCategoriaDlg297().getCodiceMinisteriale());
		}

		if(in.getAnzianitaDisoccupazioneMesi()!=null) {
			statoAnag.setAnzianita(SilpConvertUtils.toString(in.getAnzianitaDisoccupazioneMesi()));
		}
		if(in.getIndiceProfiling()!=null) {
			statoAnag.setIndiceProfiling(SilpConvertUtils.toBigDecimal(in.getIndiceProfiling()));
		}
		if(in.getDataEvento()!=null) {
			statoAnag.setDataEvento(in.getDataEvento());
		}
		if(in.getDataDichiarazioneDisponibilita()!=null) {
			statoAnag.setDataDichiarazioneDisponibilita(in.getDataDichiarazioneDisponibilita());
		}

		out.setListeSpeciali(lm.mapList(in.getListeSpeciali()).toArray(new ListeSpecialiDTO[0]));
		out.setAssolvimentoIstruzione(new AssolvimentoIstruzioneSapDTO());
		out.getAssolvimentoIstruzione().setObbligoFormativo(toSiNo(in.isObbligoFormativoAssolto()));
		if(in.getAppartenenzaCategorieProtette()!=null) {
			out.setAltreNotizie(dm.map(in.getAppartenenzaCategorieProtette(), new AltreNotizieSapDTO()));
			out.getAltreNotizie().setIndiceIsee(in.getIndiceIsee());
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
	public <C extends DatiAmministrativi> C mapReverse(DatiAmministrativiSapDTO in, C out) {
		if(in!=null && in.getStatoInAnagrafe()!=null) {
			StatoInAnagrafeSapDTO statoAnag = in.getStatoInAnagrafe();
			if(statoAnag.getStatoOccupazionale()!=null) {
				//Occhio a questa rimappatura. Nella sap stato e' DI, IN, A,L OC ossia le CLASSI occupazionali piu' generiche di silp
				out.setStatoOccupazionale(dm.mapReverse(statoAnag.getStatoOccupazionale().getClasse()));
				//La condizione e' invece l'effettivo stato occupazionale piu' specifico silp (es: SO,CO) 
				out.setCondizione(dm.mapReverse(statoAnag.getStatoOccupazionale()));
			}
			out.setCategoriaDlg297(dm.map(statoAnag.getCategoria297(),statoAnag.getDescrizione())); 
			out.setAnzianitaDisoccupazioneMesi(SilpConvertUtils.toLong(statoAnag.getAnzianita()));
			out.setIndiceProfiling(statoAnag.getIndiceProfilingFormatted());
			out.setDataEvento(statoAnag.getDataEvento());
			out.setDataDichiarazioneDisponibilita(statoAnag.getDataDichiarazioneDisponibilita());
			out.setListeSpeciali(lm.mapListReverse(in.getListeSpeciali()));
			if(in.getAssolvimentoIstruzione()!=null) {
				out.setObbligoFormativoAssolto(SilpConvertUtils.toBoolean(in.getAssolvimentoIstruzione().getObbligoFormativo()));
			}
			
			if(in.getAltreNotizie()!=null) {
				out.setAppartenenzaCategorieProtette(dm.mapNullableReverse(in.getAltreNotizie().getCategoriaProtetta()));
				out.setIndiceIsee(in.getAltreNotizie().getIndiceIsee());
			}
			
		}
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the dati amministrativi
	 */
	@Override
	protected DatiAmministrativi instantiate() {
		return new DatiAmministrativi();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the dati amministrativi sap DTO
	 */
	@Override
	protected DatiAmministrativiSapDTO instantiateReverse() {
		return new DatiAmministrativiSapDTO();
	}
	
}
