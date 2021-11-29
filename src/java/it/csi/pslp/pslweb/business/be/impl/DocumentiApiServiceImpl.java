/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslcommonobj.dbdef.DocumentoBlobDBDef;
import it.csi.pslp.pslcommonobj.dbdef.DocumentoDBDef;
import it.csi.pslp.pslcommonobj.dto.DocumentoDTO;
import it.csi.pslp.pslcommonobj.dto.StatoDocumentoDTO;
import it.csi.pslp.pslcommonobj.dto.TipoDocumentoDTO;
import it.csi.pslp.pslcommonobj.dto.UtenteDTO;
import it.csi.pslp.pslcommonobj.filter.DocumentoFilter;
import it.csi.pslp.pslweb.business.be.DocumentiApi;
import it.csi.pslp.pslweb.dto.be.Documento;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.Esito;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.RestHelper;
import it.csi.pslp.pslweb.util.mapper.DocumentoMapper;
import it.csi.silos.jedi.core.DAO;
import it.csi.util.performance.StopWatch;

/**
 * The Class DocumentiApiServiceImpl.
 */
@Component("documentiApi")
public class DocumentiApiServiceImpl implements DocumentiApi {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The dao. */
    @Autowired
    private DAO dao;

    /** The rest helper. */
    @Autowired
    private RestHelper restHelper;

    /** The messaggi utils. */
    @Autowired
    private MessaggiUtils messaggiUtils;

    /** The tracciamento utils. */
    @Autowired
    private TracciamentoUtils tracciamentoUtils;

    /**
     * Load documento.
     *
     * @param idDocumento     the id documento
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response loadDocumento(Long idDocumento, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DocumentiApiServiceImpl::loadDocumento] idDocumento=" + idDocumento);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            DocumentoFilter filter = new DocumentoFilter();
            filter.getIdDocumento().eq(idDocumento);
            DocumentoDTO documentoDTO = dao.findFirst(DocumentoBlobDBDef.class, filter);
            if (documentoDTO == null) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.LOAD_DOCUMENTO, httpRequest, null, "Documento non trovato per idDocumento=" + idDocumento, null);
                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage("Documento non trovato per l'id " + idDocumento);
                log.debug("[DocumentiApiServiceImpl::loadDocumento] esito=404-" + err.getErrorMessage());
                return Response.status(404).entity(err).build();
            }
            Documento documento = new DocumentoMapper().mapReverse(documentoDTO);
            log.debug("[DocumentiApiServiceImpl::loadDocumento] documento=" + documento);
            return Response.ok(documento).build();
        } catch (Exception ex) {
            log.error("[DocumentiApiServiceImpl::loadDocumento] idDocumento=" + idDocumento, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.LOAD_DOCUMENTO, httpRequest, null,
                    "Errore di sistema: " + ex.getClass().getName() + "; idDocumento=" + idDocumento, null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed("DocumentiApiServiceImpl", "loadDocumento()", "invocazione API DocumentiApiServiceImpl.loadDocumento", "");
            watcher.stop();
        }
    }

    /**
     * Save documento.
     *
     * @param documento       the documento
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response saveDocumento(Documento documento, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DocumentiApiServiceImpl::saveDocumento] documento=" + documento);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            /*
             * Da codice, si dovra' poi accettare solo gli allegati con le estensioni valide
             * Che sono caricate nella tab. PSLP_R_TIPO_DOC_ESTENSIONE
             */
            Date now = new Date();
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            // Non uso il mapper in quanto non mi fido dei dati in input
            DocumentoDTO dto = new DocumentoDTO();
            if (documento.getCodiceTipoDocumento() != null) {
                dto.setTipoDocumento(new TipoDocumentoDTO(documento.getCodiceTipoDocumento()));
            }
            dto.setCodAmbito(documento.getAmbito());
            dto.setCodEstensione(documento.getCodiceEstensione());
            dto.setUtente(new UtenteDTO(documento.getIdUtente()));
            dto.setNomeDocumento(documento.getNome());
            dto.setDocumento(documento.getPdf());
            dto.setSubcodice(documento.getSubcodice());
            dto.setNoteCittadino(documento.getNoteCittadino());
            dto.setCodUserInserim(codiceFiscaleUtente);
            dto.setCodUserAggiorn(codiceFiscaleUtente);
            dto.setDataInserim(now);
            dto.setDataInserimento(now);
            dto.setDataAggiorn(now);
            dto.setStatoDocumento(new StatoDocumentoDTO("NI"));
            dto.setIdSilLavSapDid(documento.getIdSilLavSapDid());
            /* in caso di richiesta iscrizione aggiungere il valore dell'id richiesta */
            dto.setIdSilpRichCollMir(documento.getIdSilRichCollMir());

            dto = dao.insert(DocumentoBlobDBDef.class, dto);
            documento.setId(dto.getIdDocumento());
            log.debug("[DocumentiApiServiceImpl::saveDocumento] documento=" + documento);
            return Response.ok(documento).build();
        } catch (Exception ex) {
            log.error("[DocumentiApiServiceImpl::saveDocumento] documento=" + documento, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_DOCUMENTO, httpRequest, null, "Errore di sistema: " + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed("DocumentiApiServiceImpl", "saveDocumento()", "invocazione API DocumentiApiServiceImpl.saveDocumento", "");
            watcher.stop();
        }
    }

    /**
     * Save stato documento.
     *
     * @param documento       the documento
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response saveStatoDocumento(Documento documento, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DocumentiApiServiceImpl::saveStatoDocumento] documento=" + documento);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            Date now = new Date();
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            DocumentoFilter filter = new DocumentoFilter();
            filter.getIdDocumento().eq(documento.getId());
            DocumentoDTO documentoDTO = dao.findFirst(DocumentoDBDef.class, filter);
            if (documentoDTO == null) {
                ErrorDef err = new ErrorDef();
                err.setCode("404");
                err.setErrorMessage("Documento non trovato per l'id " + documento.getId());
                log.debug("[DocumentiApiServiceImpl::saveStatoDocumento] esito=404-" + err.getErrorMessage());
                return Response.status(404).entity(err).build();
            }
            documentoDTO.setCodUserAggiorn(codiceFiscaleUtente);
            documentoDTO.setDataAggiorn(now);
            documentoDTO.setStatoDocumento(new StatoDocumentoDTO(documento.getStato()));
            if (StatoDocumentoDTO.COD_STATO_DOCUMENTO_INVIATO.equals(documento.getStato())) {
                documentoDTO.setDataInvio(now);
            }
            documentoDTO = dao.update(DocumentoDBDef.class, documentoDTO);
            Documento result = new DocumentoMapper().mapReverse(documentoDTO);
            log.debug("[DocumentiApiServiceImpl::saveStatoDocumento] result=" + result);
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[DocumentiApiServiceImpl::saveStatoDocumento] documento=" + documento, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.SAVE_STATO_DOCUMENTO, httpRequest, null, "Errore di sistema: " + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed("DocumentiApiServiceImpl", "saveStatoDocumento()", "invocazione API DocumentiApiServiceImpl.saveStatoDocumento", "");
            watcher.stop();
        }
    }

    /**
     * Delete documento.
     *
     * @param idDocumento     the id documento
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response deleteDocumento(Long idDocumento, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DocumentiApiServiceImpl::deleteDocumento] idDocumento=" + idDocumento);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            Esito esito = new Esito();
            DocumentoFilter filter = new DocumentoFilter();
            filter.getIdDocumento().eq(idDocumento);
            DocumentoDTO documentoDTO = dao.findFirst(DocumentoDBDef.class, filter);
            if (documentoDTO == null) {

                esito.setCode("KO");
                esito.setMessaggioCittadino("Documento non trovato per l'id \" + idDocumento");
                log.debug("[DocumentiApiServiceImpl::deleteDocumento] esito=404-");
                return Response.ok(esito).build();

//                ErrorDef err = new ErrorDef();
//                err.setCode("404");
//                err.setErrorMessage("Documento non trovato per l'id " + idDocumento);
//                return Response.status(404).entity(err).build();
            }
            dao.delete(DocumentoDBDef.class, documentoDTO);
            log.debug("[DocumentiApiServiceImpl::deleteDocumento] idDocumento=" + idDocumento);
//            return Response.noContent().build();
            esito.setCode("OK");
            esito.setMessaggioCittadino("Cancellazione avvenuta correttamente");
            return Response.ok(esito).build();
        } catch (Exception ex) {
            log.error("[DocumentiApiServiceImpl::deleteDocumento] idDocumento=" + idDocumento, ex);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed("DocumentiApiServiceImpl", "deleteDocumento()", "invocazione API DocumentiApiServiceImpl.deleteDocumento", "");
            watcher.stop();
        }
    }

    /**
     * Creates the error response.
     *
     * @param code        the code
     * @param messageCode the message code
     * @return the response
     */
    protected Response createErrorResponse(String code, String messageCode) {
        ErrorDef err = new ErrorDef();
        err.setCode(code);
        err.setErrorMessage(messaggiUtils.loadMessaggioErrore(messageCode).getTesto());
        return Response.serverError().entity(err).build();
    }

}
