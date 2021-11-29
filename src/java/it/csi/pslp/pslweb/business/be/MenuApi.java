/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/ 
package it.csi.pslp.pslweb.business.be;

import it.csi.pslp.pslweb.dto.be.*;


import it.csi.pslp.pslweb.dto.be.DomandeDidPage;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.MenuHelpPage;
import it.csi.pslp.pslweb.dto.be.MenuHomeCard;

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

@Path("/menu")

@Produces({ "application/json" })


public interface MenuApi  {
   
    @GET
    @Path("/domande_did")
    
    @Produces({ "application/json" })

    public Response loadDomandeDidPage(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cod_menu}")
    
    @Produces({ "application/json" })

    public Response loadMenu( @PathParam("cod_menu") String codMenu,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/help/{cod_help}")
    
    @Produces({ "application/json" })

    public Response loadMenuHelpPage( @PathParam("cod_help") String codHelp,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
