/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/ 
package it.csi.pslp.pslweb.business.be;

import it.csi.pslp.pslweb.dto.be.*;


import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.MenuItem;
import it.csi.pslp.pslweb.dto.be.Role;
import it.csi.pslp.pslweb.dto.be.Screen;
import it.csi.pslp.pslweb.dto.be.UserInfo;
import it.csi.pslp.pslweb.dto.be.Widget;

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

@Path("/currentUser")

@Produces({ "application/json" })


public interface CurrentUserApi  {
   
    @GET
    
    
    @Produces({ "application/json" })

    public Response getCurrentUser(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/menu")
    
    @Produces({ "application/json" })

    public Response getMenuStructureForUser(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/screens/{screen}")
    
    @Produces({ "application/json" })

    public Response getScreenByCod( @PathParam("screen") String screen,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/roles")
    
    @Produces({ "application/json" })

    public Response getUserRoles(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/screens/{screen}/widgets/{widget}")
    
    @Produces({ "application/json" })

    public Response getWidget( @PathParam("screen") String screen, @PathParam("widget") String widget,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/roles/{role}")
    
    @Produces({ "application/json" })

    public Response isUserInRole( @PathParam("role") String role,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/logout")
    
    @Produces({ "application/json" })

    public Response logout(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
