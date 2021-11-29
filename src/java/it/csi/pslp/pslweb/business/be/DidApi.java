/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/ 
package it.csi.pslp.pslweb.business.be;

import it.csi.pslp.pslweb.dto.be.*;


import it.csi.pslp.pslweb.dto.be.ConfigurazioneProfilingDid;
import it.csi.pslp.pslweb.dto.be.DatiInputAggiornamentoDid;
import it.csi.pslp.pslweb.dto.be.DatiInputProfilingDid;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.EsitoDettaglioDid;
import it.csi.pslp.pslweb.dto.be.EsitoSaveDid;
import it.csi.pslp.pslweb.dto.be.EsitoSaveProfilingDid;

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

@Path("/did")

@Produces({ "application/json" })


public interface DidApi  {
   
    @POST
    @Path("/{id_utente}/change_state_did")
    
    @Produces({ "application/json" })

    public Response changeStateDidAfterInsertProfilingService( @PathParam("id_utente") Long idUtente, DatiInputAggiornamentoDid parametriSendDid,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/controllo_did_service")
    
    @Produces({ "application/json" })

    public Response controlloDidService( @PathParam("id_utente") Long idUtente, @NotNull @QueryParam("scrivere_log_su_db") String scrivereLogSuDb, DatiInputAggiornamentoDid parametriSendDid,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/configurazione_profiling")
    
    @Produces({ "application/json" })

    public Response loadConfigurazioneProfilingDid(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/log_service")
    
    @Produces({ "application/json" })

    public Response logService( @PathParam("id_utente") Long idUtente, @NotNull @QueryParam("msg") String msg,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/ricerca_did")
    
    @Produces({ "application/json" })

    public Response ricercaDettaglioDIDService( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_did_service")
    
    @Produces({ "application/json" })

    public Response saveDidService( @PathParam("id_utente") Long idUtente, DatiInputAggiornamentoDid parametriSendDid,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_profiling_did_service")
    
    @Produces({ "application/json" })

    public Response saveProfilingDidService( @PathParam("id_utente") Long idUtente, DatiInputProfilingDid parametriSendProfilingDid,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
