/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/ 
package it.csi.pslp.pslweb.business.be;

import it.csi.pslp.pslweb.dto.be.*;


import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.Parametro;

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

@Path("/parametri")

@Produces({ "application/json" })


public interface ParametriApi  {
   
    @GET
    @Path("/{cod_parametro}")
    
    @Produces({ "application/json" })

    public Response getParametro( @PathParam("cod_parametro") String codParametro,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
