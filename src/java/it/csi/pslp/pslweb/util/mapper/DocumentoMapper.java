/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import java.util.ArrayList;
import java.util.List;

import it.csi.pslp.pslcommonobj.dto.DocumentoDTO;
import it.csi.pslp.pslcommonobj.dto.StatoDocumentoDTO;
import it.csi.pslp.pslcommonobj.dto.TipoDocumentoDTO;
import it.csi.pslp.pslcommonobj.dto.UtenteDTO;
import it.csi.pslp.pslweb.dto.be.Documento;

/**
 * The Class DocumentoMapper.
 */
public class DocumentoMapper extends BaseMapper<Documento, DocumentoDTO> {

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in  the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends DocumentoDTO> C map(Documento in, C out) {

        out.setIdDocumento(in.getId());
        out.setCodOperatore(in.getCodiceOperatore());
        out.setCodUserInserim(in.getCodiceUtenteInserimento());
        out.setCodUserAggiorn(in.getCodiceUtenteAggiornamento());
        out.setDataInserim(in.getDataInserim());
        out.setDataInserimento(in.getDataInserimento());
        out.setDataAggiorn(in.getDataAggiornamento());
        out.setDataInvio(in.getDataInvio());
        out.setNomeDocumento(in.getNome());
        out.setSubcodice(in.getSubcodice());
        out.setNoteOperatore(in.getNoteOperatore());
        out.setUtente(new UtenteDTO(in.getIdUtente()));
        out.setNoteCittadino(in.getNoteCittadino());
        out.setStatoDocumento(new StatoDocumentoDTO(in.getStato()));
        out.setGruppoOperatore(in.getGruppoOperatore());
        out.setCfOperatore(in.getCodiceFiscaleOperatore());
        if (in.getCodiceTipoDocumento() != null) {
            out.setTipoDocumento(new TipoDocumentoDTO(in.getCodiceTipoDocumento()));
        }
        out.setCodAmbito(in.getAmbito());
        out.setCodEstensione(in.getCodiceEstensione());
        out.setDocumento(in.getPdf());
        out.setIdSilLavSapDid(in.getIdSilLavSapDid());

        // Aggiunta campo flgObbligatorio
        out.setFlgObbligatorio(in.getFlgObbligatorio());

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
    public <C extends Documento> C mapReverse(DocumentoDTO in, C out) {
        mapNoPdf(in, out);
        out.setPdf(in.getDocumento());
        return out;
    }

    /**
     * Map no pdf.
     *
     * @param in  the in
     * @param out the out
     * @return the documento
     */
    private Documento mapNoPdf(DocumentoDTO in, Documento out) {
        out.setId(in.getIdDocumento());
        out.setCodiceTipoDocumento(in.getTipoDocumento().getCodTipoDocumento());
        out.setCodiceOperatore(in.getCodOperatore());
        out.setCodiceUtenteInserimento(in.getCodUserInserim());
        out.setCodiceUtenteAggiornamento(in.getCodUserAggiorn());
        out.setDataInserim(in.getDataInserim());
        out.setDataInserimento(in.getDataInserimento());
        out.setDataAggiornamento(in.getDataAggiorn());
        out.setDataInvio(in.getDataInvio());
        out.setNome(in.getNomeDocumento());
        out.setSubcodice(in.getSubcodice());
        out.setNoteOperatore(in.getNoteOperatore());
        out.setIdUtente(in.getUtente().getIdUtente());
        out.setNoteCittadino(in.getNoteCittadino());
        out.setStato(in.getStatoDocumento().getCodStatoDocumento());
        out.setGruppoOperatore(in.getGruppoOperatore());
        out.setCodiceFiscaleOperatore(in.getCfOperatore());
        out.setCodiceEstensione(in.getCodEstensione());
        out.setAmbito(in.getCodAmbito());
        out.setIdSilLavSapDid(in.getIdSilLavSapDid());
        // Aggiunta campo flgObbligatorio
        out.setFlgObbligatorio(in.getFlgObbligatorio());
        return out;
    }

    /**
     * Map list no pdf.
     *
     * @param <C> the generic type
     * @param in  the in
     * @return the list
     */
    public <C extends Iterable<? extends DocumentoDTO>> List<Documento> mapListNoPdf(C in) {
        List<Documento> out = new ArrayList<>();
        if (in == null) {
            return out;
        }
        for (DocumentoDTO inst : in) {
            Documento d = instantiate();
            out.add(mapNoPdf(inst, d));
        }
        return out;
    }

    /**
     * Instantiate.
     *
     * @return the documento
     */
    @Override
    protected Documento instantiate() {
        return new Documento();
    }

    /**
     * Instantiate reverse.
     *
     * @return the documento DTO
     */
    @Override
    protected DocumentoDTO instantiateReverse() {
        return new DocumentoDTO();
    }

}
