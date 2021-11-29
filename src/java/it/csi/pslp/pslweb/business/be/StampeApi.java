/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be;

import it.csi.pslp.pslweb.dto.be.*;


import it.csi.pslp.pslweb.dto.be.ErrorDef;

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

@Path("/stampe")

@Produces({ "application/json" })


public interface StampeApi  {
   
    @GET
    @Path("/{id_utente}/adesione")
    
    @Produces({ "application/pdf" })

    public Response creaStampaAdesione( @PathParam("id_utente") Long idUtente, @NotNull @QueryParam("id_sil_lav_adesione") Long idSilLavAdesione,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/iscrizione_collocamento_mirato")
    
    @Produces({ "application/pdf" })

    public Response creaStampaIscrizioneCollocamentoMirato( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/pattoDiServizio")
    
    @Produces({ "application/pdf" })

    public Response creaStampaPattoDiServizio( @PathParam("id_utente") Long idUtente, @NotNull @QueryParam("id_did") Long idDid,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/reddito_di_cittadinanza")
    
    @Produces({ "application/pdf" })

    public Response creaStampaRedditoDiCittadinanza( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/sap")
    
    @Produces({ "application/pdf" })

    public Response creaStampaSap( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
