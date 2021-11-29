/**********************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 **********************************************/
package it.csi.pslp.pslweb.business.be;

import it.csi.pslp.pslweb.dto.be.*;


import it.csi.pslp.pslweb.dto.be.AdesioneYG;
import it.csi.pslp.pslweb.dto.be.DatiInputStatoAdesione;
import it.csi.pslp.pslweb.dto.be.Documento;
import it.csi.pslp.pslweb.dto.be.DomandaRDC;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.Esito;
import it.csi.pslp.pslweb.dto.be.EsitoRiepilogoIscrizione;
import it.csi.pslp.pslweb.dto.be.EsitoSendStatoAdesione;
import it.csi.pslp.pslweb.dto.be.EsitoVerificaEsistenzaSap;
import it.csi.pslp.pslweb.dto.be.EsitoVerificaMinore;
import it.csi.pslp.pslweb.dto.be.InformazioneAggiuntiva;
import it.csi.pslp.pslweb.dto.be.ParametriCalcoloProfilingYG;
import it.csi.pslp.pslweb.dto.be.ParametriSalvataggioSAP;
import it.csi.pslp.pslweb.dto.be.PrenotazioneIncontro;
import it.csi.pslp.pslweb.dto.be.ProfilingYG;
import it.csi.pslp.pslweb.dto.be.SchedaAnagraficoProfessionale;
import it.csi.pslp.pslweb.dto.be.Utente;
import it.csi.pslp.pslweb.dto.be.UtenteACarico;

import java.util.List;
import java.util.Map;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.HttpHeaders;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/utenti")

@Produces({ "application/json" })


public interface UtentiApi  {
   
    @POST
    @Path("/{id_utente}/calcolo_profiling_yg")
    
    @Produces({ "application/json" })

    public Response calcolaProfilingYG( @PathParam("id_utente") Long idUtente, ParametriCalcoloProfilingYG parametri,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/documenti/{cod_ambito}")
    
    @Produces({ "application/json" })

    public Response findDocumenti( @PathParam("id_utente") Long idUtente, @PathParam("cod_ambito") String codAmbito,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/documenti/{cod_ambito}/{id_did}")
    
    @Produces({ "application/json" })

    public Response findDocumentiPattiServizio( @PathParam("id_utente") Long idUtente, @PathParam("id_did") Long idDid, @PathParam("cod_ambito") String codAmbito,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/documentiCM/{cod_ambito}/{id_richiesta}")
    
    @Produces({ "application/json" })

    public Response findDocumentiRichiestaIscrizione( @PathParam("id_utente") Long idUtente, @PathParam("id_richiesta") Long idRichiesta, @PathParam("cod_ambito") String codAmbito,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/incontri")
    
    @Produces({ "application/json" })

    public Response findIncontri( @PathParam("id_utente") Long idUtente, @QueryParam("cod_ambito") String codAmbito,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/informazioni_aggiuntive")
    
    @Produces({ "application/json" })

    public Response findInformazioniAggiuntive( @PathParam("id_utente") Long idUtente, @NotNull @QueryParam("cod_ambito") String codAmbito,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/riepilogo_utenti_iscrizione")
    
    @Produces({ "application/json" })

    public Response findRiepilogoUtentiIscrizione( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/responsabili")
    
    @Produces({ "application/json" })

    public Response findTutori( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/utenti_a_carico")
    
    @Produces({ "application/json" })

    public Response findUtentiACarico( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/adesione")
    
    @Produces({ "application/json" })

    public Response getAdesioneYG( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/silp/{id_silp}/adesione")
    
    @Produces({ "application/json" })

    public Response getAdesioneYGBySILP( @PathParam("id_silp") Long idSilp,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/dati_responsabile")
    
    @Produces({ "application/json" })

    public Response getDatiResponsabile( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/domandardc")
    
    @Produces({ "application/json" })

    public Response getDomandaRDC( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/silp/{id_silp}/domandardc")
    
    @Produces({ "application/json" })

    public Response getDomandaRDCBySILP( @PathParam("id_silp") Long idSilp,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/profiling_yg")
    
    @Produces({ "application/json" })

    public Response getProfilingYG( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/sap")
    
    @Produces({ "application/json" })

    public Response getSAP( @PathParam("id_utente") Long idUtente, @QueryParam("cod_ambito") String codAmbito,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/silp/{id_silp}/sap")
    
    @Produces({ "application/json" })

    public Response getSAPBySILP( @PathParam("id_silp") Long idSilp, @QueryParam("cod_ambito") String codAmbito,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/sap_vuota")
    
    @Produces({ "application/json" })

    public Response getSAPVuota( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{codice_fiscale}")
    
    @Produces({ "application/json" })

    public Response getUtenteByCf( @PathParam("codice_fiscale") String codiceFiscale,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/id/{id_utente}")
    
    @Produces({ "application/json" })

    public Response getUtenteById( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_informazioni_aggiuntive")
    
    @Produces({ "application/json" })

    public Response saveInformazioniAggiuntive( @PathParam("id_utente") Long idUtente, InformazioneAggiuntiva informazioneAggiuntiva,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_dati_responsabile")
    
    @Produces({ "application/json" })

    public Response saveResponsabile( @PathParam("id_utente") Long idUtente, SchedaAnagraficoProfessionale utente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_sap")
    
    @Produces({ "application/json" })

    public Response saveSAP( @PathParam("id_utente") Long idUtente, ParametriSalvataggioSAP parametriSap,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    
    
    @Produces({ "application/json" })

    public Response saveUtente( Utente utente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_utente_a_carico")
    
    @Produces({ "application/json" })

    public Response saveUtenteACarico( @PathParam("id_utente") Long idUtente, UtenteACarico utente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/send_stato_adesioneGG")
    
    @Produces({ "application/json" })

    public Response sendStatoAdesione( @PathParam("id_utente") Long idUtente, DatiInputStatoAdesione parametriSendStatoAdesione,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/verificaesistenzasap/{codice_fiscale}")
    
    @Produces({ "application/json" })

    public Response verificaEsistenzaSAPSuSistemaMinisteriale( @PathParam("codice_fiscale") String codiceFiscale,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/verifica_minore/{codice_fiscale}")
    
    @Produces({ "application/json" })

    public Response verificaMinore( @PathParam("id_utente") Long idUtente, @PathParam("codice_fiscale") String codiceFiscale, @NotNull @QueryParam("cod_ambito") String codAmbito,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
