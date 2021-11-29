/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.RichiestaIscrizioneL68Header;
import it.csi.silpcommonobj.dati.collocamentomirato.richiestaiscrizione.RichiestaIscrizioneL68HeaderDTO;

/**
 * The Class EsitoRicercaRichiestaIscrizioneCollocamentoMiratoMapper.
 */
public class RichiestaIscrizioneL68HeaderMapper extends BaseMapper<RichiestaIscrizioneL68Header, RichiestaIscrizioneL68HeaderDTO> {

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in  the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends RichiestaIscrizioneL68HeaderDTO> C map(RichiestaIscrizioneL68Header in, C out) {
        try {

            if (in != null && out != null) {

                out.setCodStatoRich(in.getCodStatoRichiesta());
                out.setCodFiscale(in.getCodiceFiscale());
//                out.setCodFiscaleOperatore(in.getCodiceFiscaleOperatore());
                out.setCognome(in.getCognome());
                out.setCognomeOperatore(in.getCognomeOperatore());
                out.setDataStato(in.getDataStato());
                out.setDescrCategoriaInvalidita(in.getDescrCategoriaInvalidita());
                out.setDescrCpi(in.getDescrCpi());
                out.setDescrCpiUltimaIscrizione(in.getDescrCpiUltimaIscrizione());
                out.setDescrMotivoRich(in.getDescrMotivoRichiesta());
                out.setDescrOperatorePresaInCarico(in.getDescrOperatorePresaInCarico());
                out.setDescrProvincia(in.getDescrProvincia());
                out.setDescrStatoRichIscr(in.getDescrStatoRichiestaIscrizione());
                out.setDescrTipoComunicazione(in.getDescrTipoComunicazione());
                out.setDescrTipoIscr(in.getDescrTipoIscrizione());
                out.setFlgPresaInCarico(in.getFlgPresaInCarico());
                out.setId(in.getIdRichiesta());
                out.setIdAnagrafica(in.getIdAnagrafica());
                out.setFlgPresaInCarico(in.getFlgPresaInCarico());
                out.setNome(in.getNome());
                out.setNomeOperatore(in.getNomeOperatore());
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
     * @param in  the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends RichiestaIscrizioneL68Header> C mapReverse(RichiestaIscrizioneL68HeaderDTO in, C out) {
        if (in == null || out == null) {
            return out;
        }

        out.setCodStatoRichiesta(in.getCodStatoRich());
        out.setCodiceFiscale(in.getCodFiscale());
//           out.setCodiceFiscaleOperatore(in.getCodFiscaleOperatore());
        out.setCognome(in.getCognome());
        out.setCognomeOperatore(in.getCognomeOperatore());
        out.setDataStato(in.getDataStato());
        out.setDescrCategoriaInvalidita(in.getDescrCategoriaInvalidita());
        out.setDescrCpi(in.getDescrCpi());
        out.setDescrCpiUltimaIscrizione(in.getDescrCpiUltimaIscrizione());
        out.setDescrMotivoRichiesta(in.getDescrMotivoRich());
        out.setDescrOperatorePresaInCarico(in.getDescrOperatorePresaInCarico());
        out.setDescrProvincia(in.getDescrProvincia());
        out.setDescrStatoRichiestaIscrizione(in.getDescrStatoRichIscr());
        out.setDescrTipoComunicazione(in.getDescrTipoComunicazione());
        out.setDescrTipoIscrizione(in.getDescrTipoIscr());
        out.setFlgPresaInCarico(in.getFlgPresaInCarico());
        out.setIdRichiesta(in.getId());
        out.setIdAnagrafica(in.getIdAnagrafica());
        out.setFlgPresaInCarico(in.getFlgPresaInCarico());
        out.setNome(in.getNome());
        out.setNomeOperatore(in.getNomeOperatore());

        return out;
    }

    /**
     * Instantiate.
     *
     * @return the Dettaglio Richiesta Iscrizione Legge 68 collocamento mirato
     */
    @Override
    protected RichiestaIscrizioneL68Header instantiate() {
        return new RichiestaIscrizioneL68Header();
    }

    /**
     * Instantiate reverse.
     *
     * @return the the Dettaglio Richiesta Iscrizione Legge 68 collocamento mirato
     */
    @Override
    protected RichiestaIscrizioneL68HeaderDTO instantiateReverse() {
        return new RichiestaIscrizioneL68HeaderDTO();
    }

}
