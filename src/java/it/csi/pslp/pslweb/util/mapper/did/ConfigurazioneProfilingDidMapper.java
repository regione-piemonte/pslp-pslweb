/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper.did;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import it.csi.pslp.pslweb.business.be.impl.ConfigurazioneProfiling;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneDomanda;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneProfilingDid;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneQuestionario;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneRisposta;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.TitoloStudio;
import it.csi.pslp.pslweb.util.mapper.BaseMapper;
import it.csi.pslp.pslweb.util.mapper.DecodificaMapper;
import it.csi.pslp.pslweb.util.mapper.TitoloDiStudioMapper;
import it.csi.silos.silcommon.util.SilCommonUtils;
import it.csi.silpcommonobj.dati.common.DecodificaSilp;
import it.csi.silpcommonobj.dati.decodifiche.GradoStudioSilpDTO;
import it.csi.silpcommonobj.dati.decodifiche.PresenzaInItaliaSilpDTO;
import it.csi.silpcommonobj.dati.decodifiche.TCittadinanzaMinDTO;
import it.csi.silpcommonobj.dati.decodifiche.TPosizioneProfMinDTO;
import it.csi.silpcommonobj.dati.decodifiche.TitoloStudioSilpDTO;
import it.csi.silpcommonobj.dati.questionari.ConfigurazioneDomandaSilpDTO;
import it.csi.silpcommonobj.dati.questionari.ConfigurazioneQuestionarioSilpDTO;
import it.csi.silpcommonobj.dati.questionari.ConfigurazioneRispostaSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.did.ConfigurazioneConferimentoDidDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.profiling.TitoloStudioProfilingSilpDTO;

/**
 * Classe per mappare da oggetto di tipo ConfigurazioneProfilingDid da PSLP
 * (client e web) a oggetto delle silcommon e viceversa.
 *
 * @author 1871
 */
public class ConfigurazioneProfilingDidMapper
		extends BaseMapper<ConfigurazioneProfilingDid, ConfigurazioneConferimentoDidDTO> {

	/** The dm. */
	private final DecodificaMapper dm = new DecodificaMapper();
	
	/** The tm. */
	private final TitoloDiStudioMapper tm = new TitoloDiStudioMapper();

	/**
	 * Instantiate.
	 *
	 * @return the configurazione profiling did
	 */
	@Override
	protected ConfigurazioneProfilingDid instantiate() {
		return new ConfigurazioneProfilingDid();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the configurazione conferimento did DTO
	 */
	@Override
	protected ConfigurazioneConferimentoDidDTO instantiateReverse() {
		return new ConfigurazioneConferimentoDidDTO();
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
	public <C extends ConfigurazioneConferimentoDidDTO> C map(ConfigurazioneProfilingDid in, C out) {
		throw new UnsupportedOperationException();
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
	public <C extends ConfigurazioneProfilingDid> C mapReverse(ConfigurazioneConferimentoDidDTO in, C out) {
		// Iscrizione corsi
		mapElenco(in.getElencoIscrizioneCorsoMin(), out.getElencoIscrizioneCorso());
		// Cittadinanza
		mapElenco(in.getElencoCittadinanzaMin(), out.getElencoCittadinanza());
		// Posizione professionale
		mapElenco(in.getElencoPosizioneProfessionaleMin(), out.getElencoPosizioneProfessionale());
		// Presenza in Italia
//		mapElenco(in.getElencoPresenzaInItaliaAll(), out.getElencoPresenzaInItalia());  
		mapElencoValido(in.getElencoPresenzaInItaliaAll(), out.getElencoPresenzaInItalia());
		// Condizioni occupazionali
		mapElenco(in.getElencoCondizioniOccupazionali(), out.getElencoCondizioniOccupazionali());
		Date now = new Date();
		for (TitoloStudioProfilingSilpDTO tsp : in.getElencoTitoloStudioProfiling()) {
//			if (idGradoStudioSilp != null && !idGradoStudioSilp.equals(tsp.getTitolo().getGradoStudio().getId())) continue;
	        if (tsp.getTitolo().getDtInizio() != null && tsp.getTitolo().getDtInizio().after(now)) continue;
	        if(tsp.getTitolo().getDtFine() != null && tsp.getTitolo().getDtFine().before(now)) continue;
	        
			out.getElencoTitoloStudioProfiling().add(ConfigurazioneProfiling.mapTitoloStudio(tsp.getTitolo()));
		}

		for (GradoStudioSilpDTO gs : in.getElencoGradoStudio()) {
		    for(TitoloStudio dto: out.getElencoTitoloStudioProfiling()) {
			      	if (gs.getId() != null && gs.getId().equals(dto.getGradoStudio().getCodiceSilp())) {
				      	out.getElencoGradoStudio().add(ConfigurazioneProfiling.mapGradoStudio(gs));
			      		break;			      
			      	}
			    }
		}

		ConfigurazioneQuestionario ilQuest = new ConfigurazioneQuestionario();
		ilQuest.setIdConfigurazioneQuestionario(in.getQuestionario().getIdConfigurazioneQuestionario());
		ilQuest.setNomeQuestionario(in.getQuestionario().getNomeQuestionario());
		if (SilCommonUtils.isNotVoid(in.getQuestionario().getDomande())) {
			List<ConfigurazioneDomanda> leDomande = new ArrayList<ConfigurazioneDomanda>();
			for (ConfigurazioneDomandaSilpDTO laDomDto : in.getQuestionario().getDomande()) {
				leDomande.add(mapReverse(laDomDto));
			}
			ilQuest.setConfigurazioneDomanda(leDomande);
		}

		out.setConfigurazioneQuestionario(ilQuest);
		return out;
	}

	/**
	 * Mappa un oggetto silp configurazione domanda in un oggetto pslp.
	 *
	 * @param laDomDto the la dom dto
	 * @return the configurazione domanda
	 */
	private ConfigurazioneDomanda mapReverse(ConfigurazioneDomandaSilpDTO laDomDto) {
		ConfigurazioneDomanda laDomanda = new ConfigurazioneDomanda();
		laDomanda.setDomanda(laDomDto.getDomanda());
		laDomanda.setIdConfigurazioneQuestionario(laDomDto.getIdConfigurazioneQuestionario());
		laDomanda.setIdDomanda(laDomDto.getIdDomanda());
		laDomanda.setPosizione(laDomDto.getPosizione());
		laDomanda.setTipoDomanda(laDomDto.getTipoDomanda());
		if (null != laDomDto.getRisposte() && laDomDto.getRisposte().length > 0) {
			List<ConfigurazioneRisposta> leRisposte = new ArrayList<ConfigurazioneRisposta>();
			for (ConfigurazioneRispostaSilpDTO rispDto : laDomDto.getRisposte()) {
				leRisposte.add(mapReverse(rispDto));
			}
			laDomanda.setRisposte(leRisposte);
		}
		return laDomanda;
	}

	/**
	 * Mappa un oggetto silp configurazione risposta in un oggetto pslp.
	 *
	 * @param rispDto the risp dto
	 * @return the configurazione risposta
	 */
	private ConfigurazioneRisposta mapReverse(ConfigurazioneRispostaSilpDTO rispDto) {
		ConfigurazioneRisposta laRisposta = new ConfigurazioneRisposta();
		laRisposta.setIdConfigurazioneQuestionario(rispDto.getIdConfigurazioneQuestionario());
		laRisposta.setIdDomanda(rispDto.getIdDomanda());
		laRisposta.setIdDomandaSuccessiva(rispDto.getIdDomandaSuccessiva());
		laRisposta.setIdRisposta(rispDto.getIdRisposta());
		laRisposta.setPosizione(rispDto.getPosizione());
		laRisposta.setRisposta(rispDto.getRisposta());
		return laRisposta;
	}

	/**
	 * Map elenco valido.
	 *
	 * @param elencoIn the elenco in
	 * @param elencoOut the elenco out
	 */
	private void mapElencoValido(Vector<PresenzaInItaliaSilpDTO> elencoIn, List<Decodifica> elencoOut) {
		Date now = new Date();
		for (PresenzaInItaliaSilpDTO d : elencoIn) {
			if ((null == d.getDataFine() || d.getDataFine().equals(""))) {
				mapping(elencoOut, d);
			} else if (now.after(d.getDataInizio()) && now.before(d.getDataFine())) {
				mapping(elencoOut, d);
			}
		}
	}

	/**
	 * Mapping.
	 *
	 * @param elencoOut the elenco out
	 * @param d the d
	 */
	private void mapping(List<Decodifica> elencoOut, PresenzaInItaliaSilpDTO d) {
		Decodifica dec = new Decodifica();
		dec.setCodiceMinisteriale(d.getCodiceMinisteriale());
		dec.setCodiceSilp(d.getIdentificativoDecodifica().toString());
		if (d.getDescrizione().indexOf("(YG)") > 0) {
			dec.setDescrizione(d.getDescrizioneDecodifica().substring(0, d.getDescrizione().indexOf("(YG)")));
		} else {
			dec.setDescrizione(d.getDescrizioneDecodifica());
		}
		dec.setDataFineValidita(d.getDataFine());
		dec.setDataInizioValidita(d.getDataInizio());
		dec.setCodiceMinisteriale(d.getCodiceMinisteriale());
		dec.setValidoOggi(true);
		elencoOut.add(dec);
	}

	/**
	 * Map elenco.
	 *
	 * @param elencoIn the elenco in
	 * @param elencoOut the elenco out
	 */
	private void mapElenco(Vector<? extends DecodificaSilp> elencoIn, List<Decodifica> elencoOut) {
		for (DecodificaSilp decSilp : elencoIn) {
			addDecodifica(elencoOut, decSilp);
		}
	}

	/**
	 * Adds the decodifica.
	 *
	 * @param elenco the elenco
	 * @param d the d
	 */
	private void addDecodifica(List<Decodifica> elenco, DecodificaSilp d) {
		Decodifica dec = new Decodifica();
		dec.setCodiceSilp(d.getIdentificativoDecodifica().toString());
		dec.setDescrizione(d.getDescrizioneDecodifica());
		elenco.add(dec);
	}

}
