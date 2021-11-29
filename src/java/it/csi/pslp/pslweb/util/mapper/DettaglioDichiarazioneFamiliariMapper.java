/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.DettaglioDichiarazioneFamiliariACarico;
import it.csi.silpcommonobj.dati.collocamentomirato.DettaglioDichiarazioneFamiliariACaricoDTO;

/**
 * The Class DettaglioDichiarazioneFamiliariMapper.
 */
public class DettaglioDichiarazioneFamiliariMapper
		extends BaseMapper<DettaglioDichiarazioneFamiliariACarico, DettaglioDichiarazioneFamiliariACaricoDTO> {

	/**
	 * Map.
	 *
	 * @param <C> the generic type
	 * @param in the in
	 * @param out the out
	 * @return the c
	 */
	@Override
	public <C extends DettaglioDichiarazioneFamiliariACaricoDTO> C map(DettaglioDichiarazioneFamiliariACarico in,
			C out) {
		out.setCodiceFiscaleFamiliare(in.getCodiceFiscaleFamiliare());
		out.setDsCognomeFamiliare(in.getCognomeFamiliare());
		out.setDsNomeFamiliare(in.getNomeFamiliare());
		out.setDtNascitaFamiliare(in.getDataNascitaFamiliare());
		out.setFlgGenereFamiliare(in.getSesso());

		Decodifica motivoCarico = in.getMotivoCarico();
//		  out.setIdMotivoCarico(motivoCarico.getCodiceMinisteriale());
		out.setIdMotivoCarico(motivoCarico.getCodiceSilp());
		out.setDsMotivoCarico(motivoCarico.getDescrizione());

		if (null != in.getLuogoDiNascita().getComune()) {
			out.setCodComuneNascitaFamiliare(in.getLuogoDiNascita().getComune().getCodiceMinisteriale());
		}
		if (null!= in.getLuogoDiNascita().getStato()) {
			out.setCodNazioneNascitaFamiliare(in.getLuogoDiNascita().getStato().getCodiceMinisteriale());
		}
     /* forzature per salvataggio i controlli per valorizzare vanno fatti lato silp 
		out.setIdGradoParentela("S");
		out.setFlgLuogoNascitaFamiliare("I");
		out.setFlgValiditaCf("V");
		*/
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
	public <C extends DettaglioDichiarazioneFamiliariACarico> C mapReverse(DettaglioDichiarazioneFamiliariACaricoDTO in,
			C out) {

		LuogoMapper ilLuogo = new LuogoMapper();
		out.setLuogoDiNascita(
				ilLuogo.mapReverse(in.getCodComuneNascitaFamiliare(), in.getCodNazioneNascitaFamiliare()));
		out.setCodiceFiscaleFamiliare(in.getCodiceFiscaleFamiliare());
		out.setCognomeFamiliare(in.getDsCognomeFamiliare());
		out.setNomeFamiliare(in.getDsNomeFamiliare());
		out.setDataNascitaFamiliare(in.getDtNascitaFamiliare());
		out.setSesso(in.getFlgGenereFamiliare());

		Decodifica motivoCarico = new Decodifica();
		motivoCarico.setCodiceMinisteriale(in.getIdMotivoCarico());
		motivoCarico.setCodiceSilp(in.getIdMotivoCarico());
		motivoCarico.setDescrizione(in.getDsMotivoCarico());
		out.setMotivoCarico(motivoCarico);
		return out;
	}

	/**
	 * Instantiate.
	 *
	 * @return the dettaglio dichiarazione familiari A carico
	 */
	@Override
	protected DettaglioDichiarazioneFamiliariACarico instantiate() {
		return new DettaglioDichiarazioneFamiliariACarico();
	}

	/**
	 * Instantiate reverse.
	 *
	 * @return the dettaglio dichiarazione familiari A carico DTO
	 */
	@Override
	protected DettaglioDichiarazioneFamiliariACaricoDTO instantiateReverse() {
		return new DettaglioDichiarazioneFamiliariACaricoDTO();
	}

}
