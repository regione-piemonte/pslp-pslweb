/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import it.csi.pslp.pslweb.dto.be.DettaglioRichiestaIscrizioneL68;
import it.csi.pslp.pslweb.dto.be.EsitoRicercaRichiestaIscrizioneCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.RichiestaIscrizioneL68Header;
import it.csi.silpcommonobj.dati.collocamentomirato.richiestaiscrizione.DettaglioRichiestaIscrizioneL68DTO;
import it.csi.silpcommonobj.dati.collocamentomirato.richiestaiscrizione.EsitoRicercaRichiestaIscrizioneCollocamentoMiratoSilpDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.richiestaiscrizione.RichiestaIscrizioneL68HeaderDTO;


/**
 * The Class EsitoRicercaRichiestaIscrizioneCollocamentoMiratoMapper.
 */
public class EsitoRicercaRichiestaIscrizioneCollocamentoMiratoMapper extends BaseMapper<EsitoRicercaRichiestaIscrizioneCollocamentoMirato, EsitoRicercaRichiestaIscrizioneCollocamentoMiratoSilpDTO> {

	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends EsitoRicercaRichiestaIscrizioneCollocamentoMiratoSilpDTO> C map(EsitoRicercaRichiestaIscrizioneCollocamentoMirato in, C out) {
		try {
		    if (in != null && out != null) {
    		     out.setDettaglio(new DettaglioRichiestaIscrizioneL68Mapper().map(in.getDettaglio()));
    		     Vector<RichiestaIscrizioneL68HeaderDTO> elencoRichieste = new Vector<RichiestaIscrizioneL68HeaderDTO>();
    		     for (RichiestaIscrizioneL68Header richiesta : in.getElencoRichieste()) {
    		         RichiestaIscrizioneL68HeaderDTO richiestaSilp = new RichiestaIscrizioneL68HeaderMapper().map(richiesta);
    		         elencoRichieste.add(richiestaSilp);
                 }                     
    		     out.setRichieste(elencoRichieste);
		    }
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
	public <C extends EsitoRicercaRichiestaIscrizioneCollocamentoMirato> C mapReverse(EsitoRicercaRichiestaIscrizioneCollocamentoMiratoSilpDTO in, C out) {
	    if (in == null || out == null) {
            return out;
        }
	    out.setDettaglio(new DettaglioRichiestaIscrizioneL68Mapper().mapReverse(in.getDettaglio()));
	    
	    List<RichiestaIscrizioneL68Header > elencoRichieste = new ArrayList<RichiestaIscrizioneL68Header>();
        for (RichiestaIscrizioneL68HeaderDTO richiestaSilp : in.getRichieste()) {
            RichiestaIscrizioneL68Header  richiesta  = new RichiestaIscrizioneL68HeaderMapper().mapReverse(richiestaSilp);
            elencoRichieste.add(richiesta);
        }                     
      
		out.setElencoRichieste(elencoRichieste);
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the dati esito ricerca richiesta iscrizione
	 */
	@Override
	protected EsitoRicercaRichiestaIscrizioneCollocamentoMirato instantiate() {
		return new EsitoRicercaRichiestaIscrizioneCollocamentoMirato();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the dati esito ricerca richiesta iscrizione
	 */
	@Override
	protected EsitoRicercaRichiestaIscrizioneCollocamentoMiratoSilpDTO instantiateReverse() {
		return new EsitoRicercaRichiestaIscrizioneCollocamentoMiratoSilpDTO();
	}

}
