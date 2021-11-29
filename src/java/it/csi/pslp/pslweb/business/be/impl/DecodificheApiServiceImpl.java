/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslcommonobj.dbdef.AmbitoTipoDocumentoDBDef;
import it.csi.pslp.pslcommonobj.dbdef.TipoDocumentoEstensioneDBDef;
import it.csi.pslp.pslcommonobj.dbdef.TipoResponsabilitaDBDef;
import it.csi.pslp.pslcommonobj.dto.AmbitoTipoDocumentoDTO;
import it.csi.pslp.pslcommonobj.dto.TipoDocumentoDTO;
import it.csi.pslp.pslcommonobj.dto.TipoDocumentoEstensioneDTO;
import it.csi.pslp.pslcommonobj.dto.TipoResponsabilitaDTO;
import it.csi.pslp.pslcommonobj.filter.AmbitoTipoDocumentoFilter;
import it.csi.pslp.pslweb.business.be.DecodificheApi;
import it.csi.pslp.pslweb.business.integration.AdapterSilpserver;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvdeWSImpl;
import it.csi.pslp.pslweb.dto.be.CentroPerImpiego;
import it.csi.pslp.pslweb.dto.be.Cittadinanza;
import it.csi.pslp.pslweb.dto.be.Comune;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.ElenchiDecodifiche;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.GradoStudio;
import it.csi.pslp.pslweb.dto.be.MotivoRilascioPermessoSoggiorno;
import it.csi.pslp.pslweb.dto.be.Nazione;
import it.csi.pslp.pslweb.dto.be.Provincia;
import it.csi.pslp.pslweb.dto.be.Sedime;
import it.csi.pslp.pslweb.dto.be.StatusExtraUE;
import it.csi.pslp.pslweb.dto.be.TipoDocumento;
import it.csi.pslp.pslweb.dto.be.TipoDocumentoMimeType;
import it.csi.pslp.pslweb.dto.be.TipoResponsabilita;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.RestHelper;
import it.csi.silos.jedi.core.DAO;
import it.csi.silos.jedi.core.DAOException;
import it.csi.util.performance.StopWatch;

// TODO: Auto-generated Javadoc
/**
 * The Class DecodificheApiServiceImpl.
 */
/**
 * @author 880
 *
 */
@Component("decodificheApi")
public class DecodificheApiServiceImpl implements DecodificheApi {

    /** The Constant ERRORE_DI_SISTEMA. */
    private static final String ERRORE_DI_SISTEMA = "Errore di sistema: ";

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
     * Gets the comuni.
     *
     * @param descrizione     the descrizione
     * @param codiceProvincia the codice provincia
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the comuni
     */
    @Override
    public Response getComuni(String descrizione, String codiceProvincia, Long idCpiSilp, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::getComuni] descrizione=" + descrizione + ", codiceProvincia=" + codiceProvincia + ", idCpiSilp=" + idCpiSilp);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            List<Comune> comuni = AdapterSilpsvdeWSImpl.getInstance().findComuni();
            List<Comune> result = new ArrayList<>();
            String upperCaseDescrizione = descrizione == null ? null : descrizione.toUpperCase();
            for (Comune comune : comuni) {
                if (codiceProvincia != null && !codiceProvincia.equalsIgnoreCase(comune.getProvincia().getCodiceMinisteriale()))
                    continue;
                if (upperCaseDescrizione != null && !comune.getDescrizione().toUpperCase().contains(upperCaseDescrizione))
                    continue;
                if (idCpiSilp != null && !idCpiSilp.equals(comune.getIdCpiSilp()))
                    continue;
                result.add(comune);
            }
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::getComuni]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_COMUNI, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "getComuni()", "invocazione API DecodificheApiServiceImpl.getComuni", "");
            watcher.stop();
        }
    }

    /**
     * Restituisce i CPI definiti in SILP. Se idCpiSilp e' valorizzato restituisce
     * il record relativo, altrimenti tutti i CPI.
     * 
     * @param idCpiSilp identificatore del cpi di SILP
     * @return la response contenente una List<Cpi>
     */
    @Override
    public Response getCentriPerImpiego(Long idCpiSilp, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::getCentriPerImpiego] idCpiSilp=" + idCpiSilp);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            List<CentroPerImpiego> elencoCpi = AdapterSilpsvdeWSImpl.getInstance().findCpi();
            List<CentroPerImpiego> result = new ArrayList<>();
            for (CentroPerImpiego cpi : elencoCpi) {
                if (idCpiSilp != null && !idCpiSilp.equals(cpi.getIdCpiSilp()))
                    continue;
                result.add(cpi);
            }
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::getCentriPerImpiego]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_CPI, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "getCentriPerImpiego()", "invocazione API DecodificheApiServiceImpl.getCpi", "");
            watcher.stop();
        }
    }

    /**
     * Gets the qualifiche professionali.
     *
     * @param descrizione     the descrizione
     * @param codice          the codice
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the qualifiche professionali
     */
    @Override
    public Response getQualificheProfessionali(String descrizione, String codice, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
            @Context HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::getQualificheProfessionali] descrizione=" + descrizione + ", codice=" + codice);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {

            List<Decodifica> result = AdapterSilpsvdeWSImpl.getInstance().findQualificheProfessionali(codice, descrizione);
            log.debug("[DecodificheApiServiceImpl::getQualificheProfessionali] num. record filtrati=" + result.size());
            return Response.ok(result).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::getQualificheProfessionali]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_QUALIFICHE_PROFESSIONALI, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "getQualificheProfessionali()",
                    "invocazione API DecodificheApiServiceImpl.getQualificheProfessionali", "");
            watcher.stop();
        }
    }

    /**
     * Gets the province.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the province
     */
    @Override
    public Response getProvince(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::getProvince]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            List<Provincia> province = AdapterSilpsvdeWSImpl.getInstance().findProvince();
            return Response.ok(province).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::getProvince]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_PROVINCE, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "getProvince()", "invocazione API DecodificheApiServiceImpl.getProvince", "");
            watcher.stop();
        }
    }

    /**
     * Gets the sedimi.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the sedimi
     */
    @Override
    public Response getSedimi(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::getSedimi]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            List<Sedime> sedimi = AdapterSilpsvdeWSImpl.getInstance().findSedimi();
            return Response.ok(sedimi).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::getSedimi]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_SEDIMI, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed("DecodificheApiServiceImpl", "getSedimi()", "invocazione API DecodificheApiServiceImpl.getSedimi", "");
            watcher.stop();
        }
    }

    /**
     * Gets the nazioni.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the nazioni
     */
    @Override
    public Response getNazioni(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::getNazioni]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            List<Nazione> nazioni = AdapterSilpsvdeWSImpl.getInstance().findNazioni();
            return Response.ok(nazioni).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::getNazioni]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_NAZIONI, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "getNazioni()", "invocazione API DecodificheApiServiceImpl.findNazioni", "");
            watcher.stop();
        }
    }

    /**
     * Gets the cittadinanze.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the cittadinanze
     */
    @Override
    public Response getCittadinanze(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::getCittadinanze]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            List<Cittadinanza> cittadinanze = AdapterSilpsvdeWSImpl.getInstance().findCittadinanze();
            return Response.ok(cittadinanze).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::getCittadinanze]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_CITTADINANZE, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "getCittadinanze()", "invocazione API DecodificheApiServiceImpl.findCittadinanze",
                    "");
            watcher.stop();
        }
    }

    /**
     * Gets the condizioni occupazionali.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the condizioni occupazionali
     */
    @Override
    public Response getCondizioniOccupazionali(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::getCondizioniOccupazionali]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            ConfigurazioneProfiling conf = AdapterSilpserver.getInstance().loadConfigurazioneProfiling(codiceFiscaleUtente);
            if (conf == null || conf.getCondizioniOccupazionali().isEmpty()) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_CONDIZIONI_OCCUPAZIONALI, httpRequest, null, "Nessuna condizione occupazionale presente",
                        null);
            }
            return Response.ok(conf.getCondizioniOccupazionali()).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::getCondizioniOccupazionali]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_CONDIZIONI_OCCUPAZIONALI, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "getCondizioniOccupazionali()",
                    "invocazione API DecodificheApiServiceImpl.getCondizioniOccupazionali", "");
            watcher.stop();
        }
    }

    /**
     * Gets the gradi studio.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the gradi studio
     */
    @Override
    public Response getGradiStudio(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        return getGradiStudioPrivate("getGradiStudio", true, httpRequest);
    }

    /**
     * Gets the gradi studio silp.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the gradi studio silp
     */
    @Override
    public Response getGradiStudioSilp(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        return getGradiStudioPrivate("getGradiStudioSilp", false, httpRequest);
    }

    /**
     * Gets the gradi studio private.
     *
     * @param nomeMetodoChiamante the nome metodo chiamante
     * @param soloValidiProfiling the solo validi profiling
     * @param httpRequest         the http request
     * @return the gradi studio private
     */
    private Response getGradiStudioPrivate(String nomeMetodoChiamante, boolean soloValidiProfiling, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::" + nomeMetodoChiamante + "] filtra per profiling=" + soloValidiProfiling);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            ConfigurazioneProfiling conf = AdapterSilpserver.getInstance().loadConfigurazioneProfiling(codiceFiscaleUtente);
            List<GradoStudio> elencoGradi = conf.getGradiStudioSilp();
            if (soloValidiProfiling) {
                elencoGradi = conf.getGradiStudio();
            }

            if (conf == null || elencoGradi.isEmpty()) {
                tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_GRADI_STUDIO, httpRequest, null, "Nessuna grado di studio presente", null);
            }
            return Response.ok(elencoGradi).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::" + nomeMetodoChiamante + "]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_GRADI_STUDIO, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), nomeMetodoChiamante + "()",
                    "invocazione API DecodificheApiServiceImpl." + nomeMetodoChiamante, "");
            watcher.stop();
        }
    }

    /**
     * Gets the motivi presenza in italia.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the motivi presenza in italia
     */
    @Override
    public Response getMotiviPresenzaInItalia(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::getMotiviPresenzaInItalia]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            ConfigurazioneProfiling conf = AdapterSilpserver.getInstance().loadConfigurazioneProfiling(codiceFiscaleUtente);
            return Response.ok(conf.getMotiviPresenzaInItalia()).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::getMotiviPresenzaInItalia]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_MOTIVI_PRESENZA_IN_ITALIA, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "getMotiviPresenzaInItalia()",
                    "invocazione API DecodificheApiServiceImpl.getMotiviPresenzaInItalia", "");
            watcher.stop();
        }
    }

    /**
     * Ritorna i titoli studio in silp validi per l'utilizzo nel profiling.
     *
     * @param idGradoStudioSilp the id grado studio silp
     * @param securityContext   the security context
     * @param httpHeaders       the http headers
     * @param httpRequest       the http request
     * @return the titoli studio
     */
    @Override
    public Response getTitoliStudio(Long idGradoStudioSilp, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::getTitoliStudio]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            ConfigurazioneProfiling conf = AdapterSilpserver.getInstance().loadConfigurazioneProfiling(codiceFiscaleUtente);
            return Response.ok(conf.getTitoliStudio(idGradoStudioSilp)).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::getTitoliStudio]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_TITOLI_STUDIO, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "getTitoliStudio()", "invocazione API DecodificheApiServiceImpl.getTitoliStudio",
                    "");
            watcher.stop();
        }
    }

    /**
     * Ritorna tutti i titolii studio censiti in silp, compresi quelli validi per il
     * profiling.
     *
     * @param idGradoStudioSilp the id grado studio silp
     * @param securityContext   the security context
     * @param httpHeaders       the http headers
     * @param httpRequest       the http request
     * @return the titoli studio silp
     */
    @Override
    public Response getTitoliStudioSilp(Long idGradoStudioSilp, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::getTitoliStudioSilp]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            String codiceFiscaleUtente = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            ConfigurazioneProfiling conf = AdapterSilpserver.getInstance().loadConfigurazioneProfiling(codiceFiscaleUtente);
            return Response.ok(conf.getTitoliStudioSilp(idGradoStudioSilp)).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::getTitoliStudioSilp]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_TITOLI_STUDIO, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "getTitoliStudio()", "invocazione API DecodificheApiServiceImpl.getTitoliStudio",
                    "");
            watcher.stop();
        }
    }

    /**
     * Find motivi rilascio permesso di soggiorno.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response findMotiviRilascioPermessoDiSoggiorno(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::findMotiviRilascioPermessoDiSoggiorno]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            List<MotivoRilascioPermessoSoggiorno> motiviRilascioPermessoSoggiorno = AdapterSilpsvdeWSImpl.getInstance().findMotiviRilascioPermessoSoggiorno();
            return Response.ok(motiviRilascioPermessoSoggiorno).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::findMotiviRilascioPermessoDiSoggiorno]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.FIND_MOTIVI_RILASCIO_PERMESSO_SOGGIORNO, httpRequest, null,
                    ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "findMotiviRilascioPermessoDiSoggiorno()",
                    "invocazione API DecodificheApiServiceImpl.findMotiviRilascioPermessoDiSoggiorno", "");
            watcher.stop();
        }
    }

    /**
     * Find elenco status extra UE.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response findElencoStatusExtraUE(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::findElencoStatusExtraUE]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            List<StatusExtraUE> statusExtraUE = AdapterSilpsvdeWSImpl.getInstance().ricercaStatusExtraUE();
            return Response.ok(statusExtraUE).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::findElencoStatusExtraUE]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.FIND_ELENCO_STATUS_EXTRA_UE, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "findElencoStatusExtraUE()",
                    "invocazione API DecodificheApiServiceImpl.findElencoStatusExtraUE", "");
            watcher.stop();
        }
    }

    /**
     * Find tipi documenti.
     *
     * @param codAmbito       the cod ambito
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response findTipiDocumenti(String codAmbito, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::findTipiDocumenti] codAmbito=" + codAmbito);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            List<TipoDocumento> tipiDocumento = new ArrayList<>();
            Date now = new Date();

            // Legge le estensioni
            Map<String, List<TipoDocumentoMimeType>> estensioni = new HashMap<>();
            for (TipoDocumentoEstensioneDTO tde : dao.findAll(TipoDocumentoEstensioneDBDef.class, null, 0)) {
                if (tde.getDInizio() != null && tde.getDInizio().after(now))
                    continue;
                if (tde.getDFine() != null && tde.getDFine().before(now))
                    continue;
                creazioneLista(estensioni, tde);
            }

            leggeTipiDocumento(codAmbito, tipiDocumento, now, estensioni);

            return Response.ok(tipiDocumento).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::findTipiDocumenti] codAmbito=" + codAmbito, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.FIND_TIPI_DOCUMENTI, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), codAmbito);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "findTipiDocumenti()", "invocazione API DecodificheApiServiceImpl.findTipiDocumenti",
                    "");
            watcher.stop();
        }
    }

    /**
     * Find all tipi documenti.
     *
     * @param codAmbito       the cod ambito
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response findAllTipiDocumenti(String codAmbito, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::findAllTipiDocumenti] codAmbito=" + codAmbito);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            List<TipoDocumento> tipiDocumento = new ArrayList<>();
            Date now = new Date();

            // Legge le estensioni
            Map<String, List<TipoDocumentoMimeType>> estensioni = new HashMap<>();
            for (TipoDocumentoEstensioneDTO tde : dao.findAll(TipoDocumentoEstensioneDBDef.class, null, 0)) {
                creazioneLista(estensioni, tde);
            }

            leggeTipiDocumento(codAmbito, tipiDocumento, now, estensioni);

            return Response.ok(tipiDocumento).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::findAllTipiDocumenti] codAmbito=" + codAmbito, ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.FIND_TIPI_DOCUMENTI, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), codAmbito);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "findAllTipiDocumenti()",
                    "invocazione API DecodificheApiServiceImpl.findAllTipiDocumenti", "");
            watcher.stop();
        }
    }

    /**
     * Creazione lista.
     *
     * @param estensioni the estensioni
     * @param tde        the tde
     */
    private void creazioneLista(Map<String, List<TipoDocumentoMimeType>> estensioni, TipoDocumentoEstensioneDTO tde) {
        List<TipoDocumentoMimeType> listEstensioni = estensioni.get(tde.getTipoDocumento().getCodTipoDocumento());
        if (listEstensioni == null) {
            listEstensioni = new ArrayList<>();
            estensioni.put(tde.getTipoDocumento().getCodTipoDocumento(), listEstensioni);
        }
        // Gianluca G 12/11/2019 - imposto MimeType
        if (tde.getEstensione().getMimeType() != null) {
            TipoDocumentoMimeType td = new TipoDocumentoMimeType();
            td.setDescrizioneMimeType(tde.getEstensione().getMimeType());
            td.setCodiceEstensione(tde.getEstensione().getCodEstensione());
            td.setDescrizioneEstensione(tde.getEstensione().getDescrEstensione());
            listEstensioni.add(td);
        }
    }

    /**
     * Legge tipi documento.
     *
     * @param codAmbito     the cod ambito
     * @param tipiDocumento the tipi documento
     * @param now           the now
     * @param estensioni    the estensioni
     * @throws DAOException the DAO exception
     */
    private void leggeTipiDocumento(String codAmbito, List<TipoDocumento> tipiDocumento, Date now, Map<String, List<TipoDocumentoMimeType>> estensioni)
            throws DAOException {
        // Legge i tipi documento
        AmbitoTipoDocumentoFilter filter = new AmbitoTipoDocumentoFilter();
        filter.getAmbito().getCodAmbito().eq(codAmbito);
        for (AmbitoTipoDocumentoDTO ambitoTipoDocumentoDTO : dao.findAll(AmbitoTipoDocumentoDBDef.class, filter, 0)) {
            if (ambitoTipoDocumentoDTO.getDInizio() != null && ambitoTipoDocumentoDTO.getDInizio().after(now))
                continue;
            if (ambitoTipoDocumentoDTO.getDFine() != null && ambitoTipoDocumentoDTO.getDFine().before(now))
                continue;
            TipoDocumentoDTO tipoDocumentoDTO = ambitoTipoDocumentoDTO.getTipoDocumento();
            TipoDocumento tipoDocumento = new TipoDocumento();
            tipoDocumento.setCodice(tipoDocumentoDTO.getCodTipoDocumento());
            tipoDocumento.setDescrizione(tipoDocumentoDTO.getDescrTipoDocumento());
            tipoDocumento.setMimeType(new ArrayList<>());
            // viene caricato anche il flgObbligatorio
            if (null != ambitoTipoDocumentoDTO.getFlgObbligatorio()) {
                tipoDocumento.setFlgObbligatorio(ambitoTipoDocumentoDTO.getFlgObbligatorio());
            } else {
                tipoDocumento.setFlgObbligatorio("N");
            }
            List<TipoDocumentoMimeType> listEstensioni = estensioni.get(tipoDocumentoDTO.getCodTipoDocumento());
            if (listEstensioni != null)
                tipoDocumento.setMimeType(listEstensioni);
            else
                tipoDocumento.setMimeType(new ArrayList<>());
            tipiDocumento.add(tipoDocumento);
        }
    }

    /**
     * Find tipi responsabilita.
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    @Override
    public Response findTipiResponsabilita(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::findTipiResponsabilita]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            List<TipoResponsabilita> tipiResponsabilita = new ArrayList<>();
            for (TipoResponsabilitaDTO tipoResponsabilitaDTO : dao.findAll(TipoResponsabilitaDBDef.class, null, 0)) {
                TipoResponsabilita tipoResponsabilita = new TipoResponsabilita();
                tipoResponsabilita.setCodice(tipoResponsabilitaDTO.getCodice());
                tipoResponsabilita.setDescrizione(tipoResponsabilitaDTO.getDescrizione());
                tipiResponsabilita.add(tipoResponsabilita);
            }
            return Response.ok(tipiResponsabilita).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::findTipiResponsabilita]", ex);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "findTipiResponsabilita()",
                    "invocazione API DecodificheApiServiceImpl.findTipiResponsabilita", "");
            watcher.stop();
        }
    }

    /**
     * Carica un insieme di elenchi di decodifiche utilizzabili per
     * modifiche/inserimenti di dati di sezioni sap. Sono abbastanza brevi quindi
     * caricati in un colpo solo.
     * lingue,gradiConoscenzaLingue,conoscenzeInformatiche,gradiConoscenzaInformatica,patenti,patentini,albi,tipiContratto,categorieInquadramento
     *
     * @param securityContext the security context
     * @param httpHeaders     the http headers
     * @param httpRequest     the http request
     * @return the response
     */
    public Response findElenchiDecodificheSap(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::findElenchiDecodificheSap]");
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            ElenchiDecodifiche allDeco = new ElenchiDecodifiche();
            allDeco.setLingue(AdapterSilpsvdeWSImpl.getInstance().findLingue());
            allDeco.setGradiConoscenzaLingue(AdapterSilpsvdeWSImpl.getInstance().findGradiConoscenzaLingue());
            allDeco.setPatenti(AdapterSilpsvdeWSImpl.getInstance().findPatenti());
            allDeco.setPatentini(AdapterSilpsvdeWSImpl.getInstance().findPatentini());
            allDeco.setCategorieInquadramento(AdapterSilpsvdeWSImpl.getInstance().findCategorieInquadramento());
            allDeco.setTipiContratto(AdapterSilpsvdeWSImpl.getInstance().findTipiContratto());
            allDeco.setAlbi(AdapterSilpsvdeWSImpl.getInstance().findAlbi());
            allDeco.setModalitaLavoro(AdapterSilpsvdeWSImpl.getInstance().findModalitaLavoro());
            allDeco.setCertificazioniAttestazioni(AdapterSilpsvdeWSImpl.getInstance().findCertificazioniAttestazioni());
            allDeco.setConoscenzeInformatiche(AdapterSilpsvdeWSImpl.getInstance().findConoscenzeInformatiche());
            allDeco.setCategorieConoscenzaInformatiche(AdapterSilpsvdeWSImpl.getInstance().findCategorieConoscenzaInformatica());
            allDeco.setGradiConoscenzaInformatica(AdapterSilpsvdeWSImpl.getInstance().findGradiConoscenzeInformatiche());
            allDeco.setRegioni(AdapterSilpsvdeWSImpl.getInstance().findRegioni());
            allDeco.setSettoriAteco(AdapterSilpsvdeWSImpl.getInstance().findSettoriAteco());

            return Response.ok(allDeco).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::findElenchiDecodificheSap]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_ELENCHI_DECODIFICHE_SAP, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "findElenchiDecodificheSap()",
                    "invocazione API DecodificheApiServiceImpl.findElenchiDecodificheSap", "");
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

    @Override
    public Response getCpiDelComune(String codiceMinisterialeComune, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::getCpiDelComune] codiceMinisterialeComune=" + codiceMinisterialeComune);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {
            List<Comune> comuni = AdapterSilpsvdeWSImpl.getInstance().findComuni();
            Comune ilComune = new Comune();
            for (Comune comune : comuni) {
                if (codiceMinisterialeComune != null && codiceMinisterialeComune.equalsIgnoreCase(comune.getCodiceMinisteriale())) {
                    ilComune = comune;
                    break;
                }
            }

            CentroPerImpiego ilCpiDiRitorno = new CentroPerImpiego();
            if (ilComune != null) {
                List<CentroPerImpiego> elencoCpi = AdapterSilpsvdeWSImpl.getInstance().findCpi();
                for (CentroPerImpiego cpi : elencoCpi) {
                    if (ilComune.getIdCpiSilp() != null && ilComune.getIdCpiSilp().equals(cpi.getIdCpiSilp())) {
                        ilCpiDiRitorno = cpi;
                        break;
                    }
                }
            }
            return Response.ok(ilCpiDiRitorno).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::getCpiDelComune]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_COMUNI, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "getCpiDelComune()", "invocazione API DecodificheApiServiceImpl.getCpiDelComune",
                    "");
            watcher.stop();
        }
    }

    @Override
    public Response getCpiDellaProvincia(String codiceMinisterialeProvincia, SecurityContext securityContext, HttpHeaders httpHeaders,
            HttpServletRequest httpRequest) {
        log.info("[DecodificheApiServiceImpl::getCpiDellaProvincia] codiceMinisterialeProvincia=" + codiceMinisterialeProvincia);
        StopWatch watcher = new StopWatch(Constants.COMPONENT_NAME);
        watcher.start();
        try {

            List<CentroPerImpiego> iCentriPerProvincia = new ArrayList<CentroPerImpiego>();
            if (codiceMinisterialeProvincia != null) {
                List<CentroPerImpiego> elencoCpi = AdapterSilpsvdeWSImpl.getInstance().findCpi();
                for (CentroPerImpiego cpi : elencoCpi) {
                    if (codiceMinisterialeProvincia.equals(cpi.getCodiceMinisterialeProvincia())) {
                        iCentriPerProvincia.add(cpi);
                    }
                }
            }
            return Response.ok(iCentriPerProvincia).build();
        } catch (Exception ex) {
            log.error("[DecodificheApiServiceImpl::getCpiDellaProvincia]", ex);
            tracciamentoUtils.tracciaKo(TracciamentoUtils.GET_COMUNI, httpRequest, null, ERRORE_DI_SISTEMA + ex.getClass().getName(), null);
            return createErrorResponse("500", MessaggiUtils.ME002);
        } finally {
            watcher.dumpElapsed(DecodificheApiServiceImpl.class.getName(), "getCpiDellaProvincia()",
                    "invocazione API DecodificheApiServiceImpl.getCpiDellaProvincia", "");
            watcher.stop();
        }
    }

//    public boolean getFlgObbligatorioDocumento(String codAmbito, String codTipoDocumento) throws DAOException {
//        // restituisce se il documento e' obbligatorio o no
//        AmbitoTipoDocumentoFilter filter = new AmbitoTipoDocumentoFilter();
//        filter.getAmbito().getCodAmbito().eq(codAmbito);
//        filter.getTipoDocumento().getCodTipoDocumento().eq(codTipoDocumento);
//        AmbitoTipoDocumentoDTO ambitoTipoDocumentoDTO = dao.findFirst(AmbitoTipoDocumentoDBDef.class, filter);
//        if (null != ambitoTipoDocumentoDTO.getFlgObbligatorio() && !ambitoTipoDocumentoDTO.getFlgObbligatorio().equals("N")) {
//            return true;
//        }
//        return false;
//    }

}
