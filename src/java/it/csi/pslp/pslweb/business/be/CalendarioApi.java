/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/ 
package it.csi.pslp.pslweb.business.be;

import it.csi.pslp.pslweb.dto.be.*;


import it.csi.pslp.pslweb.dto.be.Calendario;
import java.util.Date;
import it.csi.pslp.pslweb.dto.be.Ente;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.EsitoSalvataggioIncontro;
import it.csi.pslp.pslweb.dto.be.ParametriRicercaDisponibilitaIncontri;
import it.csi.pslp.pslweb.dto.be.ParametriRicercaPrimaDisponibilitaIncontri;
import it.csi.pslp.pslweb.dto.be.ParametriSalvataggioIncontro;
import it.csi.pslp.pslweb.dto.be.SlotIncontro;

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

@Path("/calendario")

@Produces({ "application/json" })


public interface CalendarioApi  {
   
    @POST
    
    
    @Produces({ "application/json" })

    public Response findCalendario( ParametriRicercaPrimaDisponibilitaIncontri parametriRicercaDisponibilitaIncontri,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/intervallo_disponibile")
    
    @Produces({ "application/json" })

    public Response findIntervalloDisponibile( ParametriRicercaPrimaDisponibilitaIncontri parametriRicercaDisponibilitaIncontri,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/slots")
    
    @Produces({ "application/json" })

    public Response findSlots( ParametriRicercaDisponibilitaIncontri parametriRicercaDisponibilitaIncontri,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/sportelli")
    
    @Produces({ "application/json" })

    public Response findSportelli( @NotNull @QueryParam("cod_ambito") String codAmbito, @NotNull @QueryParam("cod_tipo_utente") String codTipoUtente, @QueryParam("codice_ministeriale_comune_domicilio") String codiceMinisterialeComuneDomicilio, @QueryParam("codice_ministeriale_comune_residenza") String codiceMinisterialeComuneResidenza,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/save_incontro")
    
    @Produces({ "application/json" })

    public Response saveIncontro( ParametriSalvataggioIncontro parametriSalvataggioIncontro,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
