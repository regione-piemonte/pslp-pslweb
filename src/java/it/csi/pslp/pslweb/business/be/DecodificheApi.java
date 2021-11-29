/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/ 
package it.csi.pslp.pslweb.business.be;

import it.csi.pslp.pslweb.dto.be.*;


import it.csi.pslp.pslweb.dto.be.CentroPerImpiego;
import it.csi.pslp.pslweb.dto.be.Cittadinanza;
import it.csi.pslp.pslweb.dto.be.Comune;
import it.csi.pslp.pslweb.dto.be.CondizioneOccupazionale;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.ElenchiDecodifiche;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.GradoStudio;
import it.csi.pslp.pslweb.dto.be.MotivoPresenzaInItalia;
import it.csi.pslp.pslweb.dto.be.MotivoRilascioPermessoSoggiorno;
import it.csi.pslp.pslweb.dto.be.Nazione;
import it.csi.pslp.pslweb.dto.be.Provincia;
import it.csi.pslp.pslweb.dto.be.Sedime;
import it.csi.pslp.pslweb.dto.be.StatusExtraUE;
import it.csi.pslp.pslweb.dto.be.TipoDocumento;
import it.csi.pslp.pslweb.dto.be.TipoResponsabilita;
import it.csi.pslp.pslweb.dto.be.TitoloStudio;

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

@Path("/decodifiche")

@Produces({ "application/json" })


public interface DecodificheApi  {
   
    @GET
    @Path("/tipi_documenti_all/{cod_ambito}")
    
    @Produces({ "application/json" })

    public Response findAllTipiDocumenti( @PathParam("cod_ambito") String codAmbito,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/decodifiche_sap")
    
    @Produces({ "application/json" })

    public Response findElenchiDecodificheSap(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/status_extra_ue")
    
    @Produces({ "application/json" })

    public Response findElencoStatusExtraUE(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/motivi_rilascio_permesso_di_soggiorno")
    
    @Produces({ "application/json" })

    public Response findMotiviRilascioPermessoDiSoggiorno(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/tipi_documenti/{cod_ambito}")
    
    @Produces({ "application/json" })

    public Response findTipiDocumenti( @PathParam("cod_ambito") String codAmbito,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/tipi_responsabilita")
    
    @Produces({ "application/json" })

    public Response findTipiResponsabilita(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/centri_per_impiego")
    
    @Produces({ "application/json" })

    public Response getCentriPerImpiego( @QueryParam("id_cpi_silp") Long idCpiSilp,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/cittadinanze")
    
    @Produces({ "application/json" })

    public Response getCittadinanze(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/comuni")
    
    @Produces({ "application/json" })

    public Response getComuni( @QueryParam("descrizione") String descrizione, @QueryParam("codice_provincia") String codiceProvincia, @QueryParam("id_cpi_silp") Long idCpiSilp,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/condizioni_occupazionali")
    
    @Produces({ "application/json" })

    public Response getCondizioniOccupazionali(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/cpi_del_comune")
    
    @Produces({ "application/json" })

    public Response getCpiDelComune( @NotNull @QueryParam("codice_ministeriale_comune") String codiceMinisterialeComune,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/cpi_della_provincia")
    
    @Produces({ "application/json" })

    public Response getCpiDellaProvincia( @NotNull @QueryParam("codice_ministeriale_provincia") String codiceMinisterialeProvincia,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/gradi_studio")
    
    @Produces({ "application/json" })

    public Response getGradiStudio(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/gradi_studio_silp")
    
    @Produces({ "application/json" })

    public Response getGradiStudioSilp(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/motivi_presenza_in_italia")
    
    @Produces({ "application/json" })

    public Response getMotiviPresenzaInItalia(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/nazioni")
    
    @Produces({ "application/json" })

    public Response getNazioni(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/province")
    
    @Produces({ "application/json" })

    public Response getProvince(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/qualifiche_professionali")
    
    @Produces({ "application/json" })

    public Response getQualificheProfessionali( @NotNull @QueryParam("descrizione") String descrizione, @QueryParam("codice") String codice,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/sedimi")
    
    @Produces({ "application/json" })

    public Response getSedimi(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/titoli_studio")
    
    @Produces({ "application/json" })

    public Response getTitoliStudio( @QueryParam("id_grado_studio_silp") Long idGradoStudioSilp,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/titoli_studio_silp")
    
    @Produces({ "application/json" })

    public Response getTitoliStudioSilp( @QueryParam("id_grado_studio_silp") Long idGradoStudioSilp,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
