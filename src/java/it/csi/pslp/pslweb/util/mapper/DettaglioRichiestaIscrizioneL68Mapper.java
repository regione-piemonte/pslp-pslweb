/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.DettaglioDichiarazioneFamiliariACarico;
import it.csi.pslp.pslweb.dto.be.DettaglioRichiestaIscrizioneL68;
import it.csi.pslp.pslweb.dto.be.Documento;
import it.csi.pslp.pslweb.dto.be.ReferenteServizioTerritoriale;
import it.csi.pslp.pslweb.dto.be.RichiestaIscrizioneL68Header;
import it.csi.silpcommonobj.dati.collocamentomirato.DettaglioDichiarazioneFamiliariACaricoDTO;
import it.csi.silpcommonobj.dati.collocamentomirato.richiestaiscrizione.DettaglioRichiestaIscrizioneL68DTO;
import it.csi.silpcommonobj.dati.common.DecodificaSilpDTO;
import it.csi.silpcommonobj.dati.documenti.DocumentoSilpHeaderDTO;

/**
 * The Class EsitoRicercaRichiestaIscrizioneCollocamentoMiratoMapper.
 */
public class DettaglioRichiestaIscrizioneL68Mapper extends BaseMapper<DettaglioRichiestaIscrizioneL68, DettaglioRichiestaIscrizioneL68DTO> {

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in  the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends DettaglioRichiestaIscrizioneL68DTO> C map(DettaglioRichiestaIscrizioneL68 in, C out) {
        try {

            if (in != null && out != null) {
                out.setAnnoReddito(in.getAnnoReddito());
                out.setAnnoRifFamiliariACarico(in.getAnnoRiferimentoFamiliariACarico());

                if ("A".equalsIgnoreCase(in.getCodTipoIscrizione())) {
                    out.setCodCategoriaAltre(in.getCodCategoriaAppartenenza());
                } else {
                    out.setCodCategoriaDisabili(in.getCodCategoriaAppartenenza());
                }
                out.setCodCategoriaInvalidita(in.getCodCategoriaInvalidita());

                out.setCodMotivoRich(in.getCodMotivoRichiesta());

                out.setCodTipoComunicazione(in.getCodTipoComunicazione());
                out.setCodTipoIscr(in.getCodTipoIscrizione());

                if (in.getRichiestaIscrizioneHeader() != null) {
                    out.setCognome(in.getRichiestaIscrizioneHeader().getCognome());
                    out.setCognomeOperatore(in.getRichiestaIscrizioneHeader().getCognomeOperatore());
                    out.setId(in.getRichiestaIscrizioneHeader().getIdRichiesta());
                    out.setIdAnagrafica(in.getRichiestaIscrizioneHeader().getIdAnagrafica());
                    out.setNome(in.getRichiestaIscrizioneHeader().getNome());
                    out.setNomeOperatore(in.getRichiestaIscrizioneHeader().getNomeOperatore());
                    out.setFlgPresaInCarico(in.getRichiestaIscrizioneHeader().getFlgPresaInCarico());
                    out.setDataStato(in.getRichiestaIscrizioneHeader().getDataStato());
                    out.setCodStatoRich(in.getRichiestaIscrizioneHeader().getCodStatoRichiesta());
                    out.setCodFiscale(in.getRichiestaIscrizioneHeader().getCodiceFiscale());
//                    out.setCodFiscaleOperatore(in.getRichiestaIscrizioneHeader().getCodiceFiscaleOperatore());
                }
                out.setDataInvio(in.getDataInvio());
                out.setDataAnnullo(in.getDataAnnullo());
                out.setDataUltimaIscrizione(in.getDataUltimaIscrizione());

                if (null != in.getFlgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato()) {
                    out.setFlgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato(in.getFlgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato());
                } else {
                    out.setFlgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato("N");
                }

                if (null != in.getFlgLicenziatoInUltimoRapporto()) {
                    out.setFlgLicenziatoInUltimoRapporto(in.getFlgLicenziatoInUltimoRapporto());
                } else {
                    out.setFlgLicenziatoInUltimoRapporto("N");
                }

                if (null != in.getFlgSeguitoDaServiziTerritoriali()) {
                    out.setFlgSeguitoDaServiziTerritoriali(in.getFlgSeguitoDaServiziTerritoriali());
                } else {
                    out.setFlgSeguitoDaServiziTerritoriali("N");
                }
                out.setGradoInvalidita(in.getGradoInvalidita());

                out.setIdCpi(in.getIdCpi());
                out.setIdCpiUltimaIscrizione(in.getIdCpiUltimaIscrizione());
                out.setIdOperatorePresaInCarico(in.getIdOperatorePresaInCarico());
                out.setIdProvincia(in.getIdProvincia());
                out.setIdProvinciaUltimaIscrizione(in.getIdProvinciaUltimaIscrizione());
                if (in.getImportoReddito() != null) {
                    String n = in.getImportoReddito();
                    out.setImportoReddito( new BigDecimal(n.replaceAll(",", ".")) );
                }
                out.setNote(in.getNote());
                out.setNoteReddito(in.getNoteReddito());
                out.setNoteRifiuto(in.getNoteRifiuto());
                out.setNumFamiliariACarico(in.getNumeroFamiliariACarico());
                out.setRiepilogoHtml(in.getRiepilogHtml());

                out.setTipoOperazione(in.getTipoOperazione());

                out.setDescrCategoriaInvalidita(null);
                out.setDescrCpi(null);
                out.setDescrCpiUltimaIscrizione(null);
                out.setDescrMotivoRich(null);
                out.setDescrOperatorePresaInCarico(null);
                out.setDescrProvincia(null);
                out.setDescrProvinciaUltimaIscrizione(in.getDescrProvinciaUltimaIscrizione());
                out.setDescrStatoRichIscr(null);
                out.setDescrTipoComunicazione(null);
                out.setDescrTipoIscr(null);
                // out.setDescrTipoIscrCatprot(null);
                if (in.getReferenteServiziTerritoriali() != null) {
                    out.setCognomeReferente(in.getReferenteServiziTerritoriali().getCognomeReferente());
                    out.setEmailReferente(in.getReferenteServiziTerritoriali().getEmailReferente());
                    out.setEnteReferente(in.getReferenteServiziTerritoriali().getEnteReferente());
                    out.setNomeReferente(in.getReferenteServiziTerritoriali().getNomeReferente());
                    out.setTelefonoReferente(in.getReferenteServiziTerritoriali().getTelefonoReferente());
                    out.setCellulareReferente(in.getReferenteServiziTerritoriali().getCellulareReferente());
                }

                out.setDomicilioTrasferimento(new IndirizzoSimpleMapper().map(in.getDomicilioTrasferimento()));

                out.setFlgDichiarazioneVisitaRevisioneCollocamentoMirato(in.getFlgDichiarazioneVisitaRevisioneCollocamentoMirato());
                if (null != in.getVerbaleCollocamentoMirato()) {
                    out.setVerbaleCollocamentoMirato(new VerbaleL68Mapper().map(in.getVerbaleCollocamentoMirato()));
                } else {
                    out.setVerbaleCollocamentoMirato(null);
                }

                out.setCodDichiarazioneVisitaRevisioneInvaliditaCivile(in.getCodDichiarazioneVisitaRevisioneInvaliditaCivile());
                if (null != in.getVerbaleInvaliditaCivile()) {
                    out.setVerbaleInvaliditaCivile(new VerbaleL68Mapper().map(in.getVerbaleInvaliditaCivile()));
                } else {
                    out.setVerbaleInvaliditaCivile(null);
                }

                Vector<DettaglioDichiarazioneFamiliariACaricoDTO> elencoFam = new Vector<DettaglioDichiarazioneFamiliariACaricoDTO>();
                for (DettaglioDichiarazioneFamiliariACarico dettFamiliari : in.getElencoFamiliariACarico()) {
                    DettaglioDichiarazioneFamiliariACaricoDTO dettFamOut = new DettaglioDichiarazioneFamiliariMapper().map(dettFamiliari);
                    elencoFam.add(dettFamOut);
                }
                out.setElencoFamiliariACarico(elencoFam);
                Vector<DecodificaSilpDTO> elencoQNV = new Vector<DecodificaSilpDTO>();
                for (Decodifica dec : in.getElencoQualificaNonVedenti()) {
                    DecodificaSilpDTO decQNV = new DecodificaSilpMapper().map(dec);
                    elencoQNV.add(decQNV);
                }
                out.setElencoQualificaNonVedenti(elencoQNV);

                if (null != in.getElencoAllegati() && !in.getElencoAllegati().isEmpty()) {
                    Vector<DocumentoSilpHeaderDTO> ilDoc = new Vector<DocumentoSilpHeaderDTO>();
                    for (Documento documento : in.getElencoAllegati()) {
                        DocumentoSilpHeaderDTO ilDocumentoSilp = new DocumentoSilpHeaderDTO();
                        ilDocumentoSilp.setId(documento.getId());
                        ilDoc.add(ilDocumentoSilp);
                        /* DAVIDE da aggiungere il flgObbligatorio */
                    }
                    out.setElencoAllegati(ilDoc);

                }
                /*
                 * da sistemare
                 * 
                 * 
                 * 
                 * out.setElencoCheckList(in.getElencoCheckList());
                 * 
                 * 
                 */
                out.setDataAggiornamento(in.getDataAggiornamento());
                out.setDataAnnullo(in.getDataAnnullo());
                out.setDataInserimento(in.getDataInserimento());
                out.setIscrizioneGenerata(null);
                out.setCodUserAggiorn(null);
                out.setCodUserInserim(null);
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
    public <C extends DettaglioRichiestaIscrizioneL68> C mapReverse(DettaglioRichiestaIscrizioneL68DTO in, C out) {

        if (in == null || out == null) {
            return out;
        }

        out.setAnnoReddito(in.getAnnoReddito());
        out.setAnnoRiferimentoFamiliariACarico(in.getAnnoRifFamiliariACarico());

        if ("A".equalsIgnoreCase(in.getCodTipoIscr())) {
            out.setCodCategoriaAppartenenza(in.getCodCategoriaAltre());

        } else {
            out.setCodCategoriaAppartenenza(in.getCodCategoriaDisabili());
        }

        out.setCodCategoriaInvalidita(in.getCodCategoriaInvalidita());

        out.setCodMotivoRichiesta(in.getCodMotivoRich());

        out.setCodTipoComunicazione(in.getCodTipoComunicazione());
        out.setCodTipoIscrizione(in.getCodTipoIscr());

        RichiestaIscrizioneL68Header richiesta = new RichiestaIscrizioneL68Header();

        richiesta.setCognome(in.getCognome());
        richiesta.setCognomeOperatore(in.getCognomeOperatore());
        richiesta.setIdRichiesta(in.getId());

        richiesta.setIdAnagrafica(in.getIdAnagrafica());
        richiesta.setNome(in.getNome());
        richiesta.setNomeOperatore(in.getNomeOperatore());
        richiesta.setFlgPresaInCarico(in.getFlgPresaInCarico());
        richiesta.setDataStato(in.getDataStato());
        richiesta.setCodStatoRichiesta(in.getCodStatoRich());
        richiesta.setCodiceFiscale(in.getCodFiscale());
//        richiesta.setCodiceFiscaleOperatore(in.getCodFiscaleOperatore());
        out.setRichiestaIscrizioneHeader(richiesta);

        out.setDataInvio(in.getDataInvio());
        out.setDataAnnullo(in.getDataAnnullo());
        out.setDataUltimaIscrizione(in.getDataUltimaIscrizione());

        if (null != in.getFlgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato()) {
            out.setFlgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato(in.getFlgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato());
        } else {
            out.setFlgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato("N");
        }

        if (null != in.getFlgLicenziatoInUltimoRapporto()) {
            out.setFlgLicenziatoInUltimoRapporto(in.getFlgLicenziatoInUltimoRapporto());
        } else {
            out.setFlgLicenziatoInUltimoRapporto("N");
        }
        if (null != in.getFlgSeguitoDaServiziTerritoriali()) {
            out.setFlgSeguitoDaServiziTerritoriali(in.getFlgSeguitoDaServiziTerritoriali());
        } else {
            out.setFlgSeguitoDaServiziTerritoriali("N");
        }

        out.setGradoInvalidita(in.getGradoInvalidita());

        out.setIdCpi(in.getIdCpi());
        out.setIdCpiUltimaIscrizione(in.getIdCpiUltimaIscrizione());
        out.setIdOperatorePresaInCarico(in.getIdOperatorePresaInCarico());
        out.setIdProvincia(in.getIdProvincia());
        out.setIdProvinciaUltimaIscrizione(in.getIdProvinciaUltimaIscrizione());
        if (in.getImportoReddito() != null) {
            String formato = "###.#########";
            DecimalFormat fn = new DecimalFormat(formato, new DecimalFormatSymbols(Locale.ITALIAN));
            String valore = fn.format(in.getImportoReddito());
            out.setImportoReddito(valore);
        }

        out.setNote(in.getNote());
        out.setNoteReddito(in.getNoteReddito());
        out.setNoteRifiuto(in.getNoteRifiuto());
        if (in.getNumFamiliariACarico() != null) {
            out.setNumeroFamiliariACarico(in.getNumFamiliariACarico());
        }
        // out.setRiepilogoHtml(in.getRiepilogHtml());

        out.setTipoOperazione(in.getTipoOperazione());
        ReferenteServizioTerritoriale referente = new ReferenteServizioTerritoriale();

        referente.setCognomeReferente(in.getCognomeReferente());
        referente.setEmailReferente(in.getEmailReferente());
        referente.setEnteReferente(in.getEnteReferente());
        referente.setNomeReferente(in.getNomeReferente());
        referente.setTelefonoReferente(in.getTelefonoReferente());
        referente.setCellulareReferente(in.getCellulareReferente());
        out.setReferenteServiziTerritoriali(referente);

        if (null != in.getDomicilioTrasferimento()) {
            out.setDomicilioTrasferimento(new IndirizzoSimpleMapper().mapReverse(in.getDomicilioTrasferimento()));
        } else {
            out.setDomicilioTrasferimento(null);
        }

        out.setFlgDichiarazioneVisitaRevisioneCollocamentoMirato(in.getFlgDichiarazioneVisitaRevisioneCollocamentoMirato());
        if (null != in.getVerbaleCollocamentoMirato()) {
            if (null == in.getVerbaleCollocamentoMirato().getDataEmissione() && null == in.getVerbaleCollocamentoMirato().getDataProssimaRevisione()
                    && null == in.getVerbaleCollocamentoMirato().getFlgSoggettoARevisione()) {
                out.setVerbaleCollocamentoMirato(null);
            } else {
                out.setVerbaleCollocamentoMirato(new VerbaleL68Mapper().mapReverse(in.getVerbaleCollocamentoMirato()));
            }
        }

        out.setCodDichiarazioneVisitaRevisioneInvaliditaCivile(in.getCodDichiarazioneVisitaRevisioneInvaliditaCivile());
        if (null != in.getVerbaleInvaliditaCivile()) {
            if (null == in.getVerbaleInvaliditaCivile().getDataEmissione() && null == in.getVerbaleInvaliditaCivile().getDataProssimaRevisione()
                    && null == in.getVerbaleInvaliditaCivile().getFlgSoggettoARevisione()) {
                out.setVerbaleInvaliditaCivile(null);
            } else {
                out.setVerbaleInvaliditaCivile(new VerbaleL68Mapper().mapReverse(in.getVerbaleInvaliditaCivile()));
            }

        }

        List<DettaglioDichiarazioneFamiliariACarico> elencoFam = new ArrayList<DettaglioDichiarazioneFamiliariACarico>();
        for (DettaglioDichiarazioneFamiliariACaricoDTO dettFamiliari : in.getElencoFamiliariACarico()) {
            DettaglioDichiarazioneFamiliariACarico dettFamOut = new DettaglioDichiarazioneFamiliariMapper().mapReverse(dettFamiliari);
            elencoFam.add(dettFamOut);
        }
        out.setElencoFamiliariACarico(elencoFam);

        List<Decodifica> elencoQNV = new ArrayList<Decodifica>();
        for (DecodificaSilpDTO decSilp : in.getElencoQualificaNonVedenti()) {
            Decodifica decQNV = new DecodificaSilpMapper().mapReverse(decSilp);
            elencoQNV.add(decQNV);
        }
        out.setElencoQualificaNonVedenti(elencoQNV);

        /*
         * out.setDescrCategoriaInvalidita(null); out.setDescrCpi(null);
         * out.setDescrCpiUltimaIscrizione(null);
         * 
         * out.setDescrMotivoRich(null); out.setDescrOperatorePresaInCarico(null);
         * out.setDescrProvincia(null);
         * out.setDescrProvinciaUltimaIscrizione(in.getDescrProvinciaUltimaIscrizione())
         * ; out.setDescrStatoRichIscr(null); out.setDescrTipoComunicazione(null);
         * out.setDescrTipoIscr(null);
         * 
         * 
         * 
         */

        if (null != in.getElencoAllegati() && !in.getElencoAllegati().isEmpty()) {
            Vector<Documento> ilDoc = new Vector<Documento>();
            for (DocumentoSilpHeaderDTO documento : in.getElencoAllegati()) {
                Documento ilDocumento = new Documento();
                ilDocumento.setId(documento.getId());
                ilDoc.add(ilDocumento);
                /* DAVIDE da aggiungere il flgObbligatorio */
            }
            out.setElencoAllegati(ilDoc);
        }

        /*
         * da sistemare
         * 
         * 
         * 
         * out.setElencoCheckList(in.getElencoCheckList());
         * 
         * 
         */
        out.setDataAnnullo(in.getDataAnnullo());
        out.setDataAggiornamento(in.getDataAggiornamento());
        out.setDataInserimento(in.getDataInserimento());
        return out;
    }

    /**
     * Instantiate.
     *
     * @return the Dettaglio Richiesta Iscrizione Legge 68 collocamento mirato
     */
    @Override
    protected DettaglioRichiestaIscrizioneL68 instantiate() {
        return new DettaglioRichiestaIscrizioneL68();
    }

    /**
     * Instantiate reverse.
     *
     * @return the the Dettaglio Richiesta Iscrizione Legge 68 collocamento mirato
     */
    @Override
    protected DettaglioRichiestaIscrizioneL68DTO instantiateReverse() {
        return new DettaglioRichiestaIscrizioneL68DTO();
    }

}
