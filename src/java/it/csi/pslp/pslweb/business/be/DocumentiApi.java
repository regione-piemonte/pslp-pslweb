/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/ 
package it.csi.pslp.pslweb.business.be;

import it.csi.pslp.pslweb.dto.be.*;


import it.csi.pslp.pslweb.dto.be.Documento;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.Esito;

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

@Path("/documenti")

@Produces({ "application/json" })


public interface DocumentiApi  {
   
    @DELETE
    @Path("/{id_documento}")
    
    @Produces({ "application/json" })

    public Response deleteDocumento( @PathParam("id_documento") Long idDocumento,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_documento}")
    
    @Produces({ "application/json" })

    public Response loadDocumento( @PathParam("id_documento") Long idDocumento,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/save_documento")
    
    @Produces({ "application/json" })

    public Response saveDocumento( Documento documento,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/save_stato_documento")
    
    @Produces({ "application/json" })

    public Response saveStatoDocumento( Documento documento,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
