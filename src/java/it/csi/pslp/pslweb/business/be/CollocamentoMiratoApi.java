/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/ 
package it.csi.pslp.pslweb.business.be;

import it.csi.pslp.pslweb.dto.be.*;


import it.csi.pslp.pslweb.dto.be.ConfigurazioneCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneFamiliariACarico;
import it.csi.pslp.pslweb.dto.be.DatiInputSaveRichiestaIscrizioneCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.DettaglioCompletoDichiarazioneFamiliariACarico;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.EsitoRicercaRichiestaIscrizioneCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.EsitoRiepilogoCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.EsitoSalvataggioRedditoCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.EsitoSaveDichiarazione;
import it.csi.pslp.pslweb.dto.be.EsitoSaveRichiestaIscrizioneCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.ParametriSalvataggioRedditoCollocamentoMirato;

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

@Path("/collocamento_mirato")

@Produces({ "application/json" })


public interface CollocamentoMiratoApi  {
   
    @GET
    @Path("/{id_utente}/find_richiesta_iscrizione_collocamento_mirato")
    
    @Produces({ "application/json" })

    public Response findRichiestaIscrizioneCollocamentoMirato( @PathParam("id_utente") Long idUtente, @QueryParam("id_richiesta") Long idRichiesta,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}")
    
    @Produces({ "application/json" })

    public Response getRiepilogoCollocamentoMirato( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/configurazione_familiari_a_carico")
    
    @Produces({ "application/json" })

    public Response loadConfigurazioneFamiliariACarico(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/configurazione_collocamento_mirato")
    
    @Produces({ "application/json" })

    public Response loadConfigurazioniCollocamentoMirato(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_dichiarazione")
    
    @Produces({ "application/json" })

    public Response saveFamiliariACarico( @PathParam("id_utente") Long idUtente, DettaglioCompletoDichiarazioneFamiliariACarico messaggio,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/reddito")
    
    @Produces({ "application/json" })

    public Response saveRedditoCollocamentoMirato( @PathParam("id_utente") Long idUtente, ParametriSalvataggioRedditoCollocamentoMirato parametriReddito,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_richiesta_iscrizione_collocamento_mirato")
    
    @Produces({ "application/json" })

    public Response saveRichiestaIscrizioneCollocamentoMirato( @PathParam("id_utente") Long idUtente, DatiInputSaveRichiestaIscrizioneCollocamentoMirato parametriRichiesta,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
