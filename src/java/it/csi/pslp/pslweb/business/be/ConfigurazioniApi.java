/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/ 
package it.csi.pslp.pslweb.business.be;

import it.csi.pslp.pslweb.dto.be.*;


import it.csi.pslp.pslweb.dto.be.ConfigurazioneInformazioneAggiuntiva;
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

@Path("/configurazioni")

@Produces({ "application/json" })


public interface ConfigurazioniApi  {
   
    @GET
    @Path("/informazioni_aggiuntive")
    
    @Produces({ "application/json" })

    public Response getConfigurazioniInformazioniAggiuntive( @QueryParam("cod_ambito") String codAmbito, @QueryParam("flag_all") String flagAll,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
