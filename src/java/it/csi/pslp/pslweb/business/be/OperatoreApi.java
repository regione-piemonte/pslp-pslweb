/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be;

import it.csi.pslp.pslweb.dto.be.*;


import it.csi.pslp.pslweb.dto.be.Ambito;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCalendario;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCalendarioHeader;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneInformazioneAggiuntiva;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneMessaggiAggiuntivi;
import it.csi.pslp.pslweb.dto.be.Ente;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.Esito;
import it.csi.pslp.pslweb.dto.be.EsitoDuplicazionePeriodo;
import it.csi.pslp.pslweb.dto.be.Messaggio;
import it.csi.pslp.pslweb.dto.be.Operatore;
import it.csi.pslp.pslweb.dto.be.ParametriDatoCalendari;
import it.csi.pslp.pslweb.dto.be.ParametriDuplicazioneCalendario;
import it.csi.pslp.pslweb.dto.be.ParametriDuplicazionePeriodo;
import it.csi.pslp.pslweb.dto.be.ParametriEccezioneCalendari;
import it.csi.pslp.pslweb.dto.be.ParametriEliminazioneEccezione;
import it.csi.pslp.pslweb.dto.be.ParametriEliminazioneFascia;
import it.csi.pslp.pslweb.dto.be.ParametriRicercaCalendari;
import it.csi.pslp.pslweb.dto.be.ParametriRicercaIncontriCalendario;
import it.csi.pslp.pslweb.dto.be.PrenotazioneIncontro;
import it.csi.pslp.pslweb.dto.be.SlotIncontro;
import it.csi.pslp.pslweb.dto.be.Sportello;

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

@Path("/operatore")

@Produces({ "application/json" })


public interface OperatoreApi  {
   
    @POST
    @Path("/{id_utente}/applica_dato_a_calendari")
    
    @Produces({ "application/json" })

    public Response applicaDatoACalendari( @PathParam("id_utente") Long idUtente, ParametriDatoCalendari parametriDatoCalendari,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/applica_eccezione_a_calendari")
    
    @Produces({ "application/json" })

    public Response applicaEccezioneACalendari( @PathParam("id_utente") Long idUtente, ParametriEccezioneCalendari parametriEccezioneCalendari,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/calendari/{id_calendario}/duplica_calendario")
    
    @Produces({ "application/json" })

    public Response duplicaCalendario( @PathParam("id_utente") Long idUtente, @PathParam("id_calendario") Long idCalendario, ParametriDuplicazioneCalendario parametriDuplicazioneCalendario,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/periodi/duplica_periodo")
    
    @Produces({ "application/json" })

    public Response duplicaPeriodo( @PathParam("id_utente") Long idUtente, ParametriDuplicazionePeriodo parametriDuplicazionePeriodo,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/calendari/{id_calendario}/elimina_calendario")
    
    @Produces({ "application/json" })

    public Response eliminaCalendario( @PathParam("id_utente") Long idUtente, @PathParam("id_calendario") Long idCalendario,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/elimina_eccezione")
    
    @Produces({ "application/json" })

    public Response eliminaEccezione( @PathParam("id_utente") Long idUtente, ParametriEliminazioneEccezione parametriEliminazioneEccezione,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/elimina_fascia")
    
    @Produces({ "application/json" })

    public Response eliminaFascia( @PathParam("id_utente") Long idUtente, ParametriEliminazioneFascia parametriEliminazioneFascia,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/calendari")
    
    @Produces({ "application/json" })

    public Response findCalendari( @PathParam("id_utente") Long idUtente, ParametriRicercaCalendari parametriRicercaCalendari,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/calendari/{id_calendario}/incontri")
    
    @Produces({ "application/json" })

    public Response findIncontriCalendario( @PathParam("id_utente") Long idUtente, @PathParam("id_calendario") Long idCalendario, ParametriRicercaIncontriCalendario parametriRicercaIncontriCalendario,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/calendari/{id_calendario}/slot")
    
    @Produces({ "application/json" })

    public Response findSlotCalendario( @PathParam("id_utente") Long idUtente, @PathParam("id_calendario") Long idCalendario, ParametriRicercaIncontriCalendario parametriRicercaIncontriCalendario,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/periodi/{id_periodo}/genera_slot")
    
    @Produces({ "application/json" })

    public Response generaSlot( @PathParam("id_utente") Long idUtente, @PathParam("id_periodo") Long idPeriodo,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/ambiti")
    
    @Produces({ "application/json" })

    public Response getAmbiti( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/enti")
    
    @Produces({ "application/json" })

    public Response getEnti( @PathParam("id_utente") Long idUtente, @NotNull @QueryParam("cod_tipo_utente") String codTipoUtente, @NotNull @QueryParam("modalita") String modalita,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/messaggi")
    
    @Produces({ "application/json" })

    public Response getMessaggi( @PathParam("id_utente") Long idUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{codice_fiscale}")
    
    @Produces({ "application/json" })

    public Response getOperatoreByCf( @PathParam("codice_fiscale") String codiceFiscale,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/sportelli")
    
    @Produces({ "application/json" })

    public Response getSportelli( @PathParam("id_utente") Long idUtente, @NotNull @QueryParam("cod_tipo_utente") String codTipoUtente,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/calendari/{id_calendario}/imposta_blocco_calendario/{flag_blocco_calendario}")
    
    @Produces({ "application/json" })

    public Response impostaBloccoCalendario( @PathParam("id_utente") Long idUtente, @PathParam("id_calendario") Long idCalendario, @PathParam("flag_blocco_calendario") Boolean flagBloccoCalendario,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/calendari/{id_calendario}")
    
    @Produces({ "application/json" })

    public Response loadCalendario( @PathParam("id_utente") Long idUtente, @PathParam("id_calendario") Long idCalendario,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/messaggi_aggiuntivi")
    
    @Produces({ "application/json" })

    public Response loadMessaggiAggiuntiviAmbito( @PathParam("id_utente") Long idUtente, @QueryParam("cod_ambito") String codAmbito,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_calendario_dati_generali")
    
    @Produces({ "application/json" })

    public Response saveCalendarioDatiGenerali( @PathParam("id_utente") Long idUtente, ConfigurazioneCalendario configurazioneCalendario,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_calendario_dati_operativi")
    
    @Produces({ "application/json" })

    public Response saveCalendarioDatiOperativi( @PathParam("id_utente") Long idUtente, ConfigurazioneCalendario configurazioneCalendario,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_calendario_eccezioni")
    
    @Produces({ "application/json" })

    public Response saveCalendarioEccezioni( @PathParam("id_utente") Long idUtente, ConfigurazioneCalendario configurazioneCalendario,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_calendario_fasce")
    
    @Produces({ "application/json" })

    public Response saveCalendarioFasce( @PathParam("id_utente") Long idUtente, ConfigurazioneCalendario configurazioneCalendario,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_calendario_mail")
    
    @Produces({ "application/json" })

    public Response saveCalendarioMail( @PathParam("id_utente") Long idUtente, ConfigurazioneCalendario configurazioneCalendario,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_configurazione_informazioni_aggiuntive")
    
    @Produces({ "application/json" })

    public Response saveConfigurazioneInformazioniAggiuntive( @PathParam("id_utente") Long idUtente, ConfigurazioneInformazioneAggiuntiva messaggio,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_messaggi_aggiuntivi")
    
    @Produces({ "application/json" })

    public Response saveMessaggiAggiuntiviAmbito( @PathParam("id_utente") Long idUtente, ConfigurazioneMessaggiAggiuntivi messaggi,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{id_utente}/save_messaggio")
    
    @Produces({ "application/json" })

    public Response saveMessaggio( @PathParam("id_utente") Long idUtente, Messaggio messaggio,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{id_utente}/calendari/{id_calendario}/vincoli_eliminazione_calendario")
    
    @Produces({ "application/json" })

    public Response verificaVincoliEliminazioneCalendario( @PathParam("id_utente") Long idUtente, @PathParam("id_calendario") Long idCalendario,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
