/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import it.csi.pslp.pslcommonobj.dto.EsitoDTO;
import it.csi.pslp.pslcommonobj.dto.EsitoSalvataggioIncontroDTO;
import it.csi.pslp.pslcommonobj.dto.ParametriAnnulloCalendario;
import it.csi.pslp.pslcommonobj.dto.ParametriRicercaCalendarioDTO;
import it.csi.pslp.pslcommonobj.dto.ParametriRicercaSlotDTO;
import it.csi.pslp.pslcommonobj.dto.ParametriSalvataggioIncontroDTO;
import it.csi.pslp.pslcommonobj.dto.PrenotazioniDTO;
import it.csi.pslp.pslcommonobj.dto.SlotHeadersDTO;
import it.csi.pslp.pslweb.util.Constants;

/**
 * The Class PslOrchApiClient.
 */
public class PslOrchApiClient {

  /** The logger. */
  private static Logger logger = Logger.getLogger(Constants.COMPONENT_NAME);
  
  /** The api client. */
  private ApiClient apiClient;
  
  /**
   * Instantiates a new psl orch api client.
   *
   * @throws Exception the exception
   */
  public PslOrchApiClient() throws Exception {
    apiClient = new ApiClient("pslorch.url", 1000);
  }

  /**
   * Find prenotazioni incontri.
   *
   * @param idUtente the id utente
   * @param codAmbito the cod ambito
   * @return the prenotazioni DTO
   * @throws Exception the exception
   */
  public PrenotazioniDTO findPrenotazioniIncontri(Long idUtente, String codAmbito) throws Exception {
    
    String localVarPath = "/utenti/"+idUtente+"/prenotazioni";
    if(codAmbito!=null) localVarPath += "?cod_ambito="+codAmbito;
    String localVarPostBody = null;
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    final String localVarContentType = "application/json";
    final String localVarAccept = "application/json";
    logger.debug("[PslOrchApiClient::findPrenotazioniIncontri] url="+apiClient.getUrl()+localVarPath);
    ApiResponse response = apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody,
        localVarHeaderParams, localVarAccept, localVarContentType);
    try {
      PrenotazioniDTO prenotazioni = new ObjectMapper().reader(PrenotazioniDTO.class).readValue(response.getData());
      logger.debug("[PslOrchApiClient::findPrenotazioniIncontri] prenotazioni="+prenotazioni);
      return prenotazioni;
    } catch (Exception ex) {
      throw new ApiException("Response from server " + response.getData(), ex);
    }
  }
  
  /**
   * Find slot.
   *
   * @param params the params
   * @return the slot headers DTO
   * @throws Exception the exception
   */
  public SlotHeadersDTO findSlot(ParametriRicercaSlotDTO params) throws Exception {
    
    String localVarPath = "/calendario/slots";
    String localVarPostBody = new ObjectMapper().writerWithType(ParametriRicercaSlotDTO.class).writeValueAsString(params);
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    final String localVarContentType = "application/json";
    final String localVarAccept = "application/json";
    logger.debug("[PslOrchApiClient::findSlot] url="+apiClient.getUrl()+localVarPath+" ,localVarPostBody="+localVarPostBody);
    ApiResponse response = apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody,
        localVarHeaderParams, localVarAccept, localVarContentType);
    try {
      SlotHeadersDTO slots = new ObjectMapper().reader(SlotHeadersDTO.class).readValue(response.getData());
      logger.debug("[PslOrchApiClient::findSlot] slots="+slots);
      return slots;
    } catch (Exception ex) {
      throw new ApiException("Response from server " + response.getData(), ex);
    }
  }

  /**
   * Find intervallo disponibile.
   *
   * @param params the params
   * @return the string[]
   * @throws Exception the exception
   */
  public String[] findIntervalloDisponibile(ParametriRicercaCalendarioDTO params) throws Exception {
    
    String localVarPath = "/calendario/intervallo_disponibile";
    String localVarPostBody = new ObjectMapper().writerWithType(ParametriRicercaCalendarioDTO.class).writeValueAsString(params);
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    final String localVarContentType = "application/json";
    final String localVarAccept = "application/json";
    logger.debug("[PslOrchApiClient::findIntervalloDisponibile] url="+apiClient.getUrl()+localVarPath+" ,localVarPostBody="+localVarPostBody);
    ApiResponse response = apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody,
        localVarHeaderParams, localVarAccept, localVarContentType);
    try {
      logger.debug("[PslOrchApiClient::findIntervalloDisponibile] response="+response.getData());
      String[] result = new ObjectMapper().reader(String[].class).readValue(response.getData());
      return result;
    } catch (Exception ex) {
      throw new ApiException("Response from server " + response.getData(), ex);
    }
  }
  
  /**
   * Save incontro.
   *
   * @param params the params
   * @return the esito salvataggio incontro DTO
   * @throws Exception the exception
   */
  public EsitoSalvataggioIncontroDTO saveIncontro(ParametriSalvataggioIncontroDTO params) throws Exception {
    
    String localVarPath = "/calendario/save_incontro";
    String localVarPostBody = new ObjectMapper().writerWithType(ParametriSalvataggioIncontroDTO.class).writeValueAsString(params);
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    final String localVarContentType = "application/json";
    final String localVarAccept = "application/json";
    logger.debug("[PslOrchApiClient::saveIncontro] url="+apiClient.getUrl()+localVarPath+" ,localVarPostBody="+localVarPostBody);
    ApiResponse response = apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody,
        localVarHeaderParams, localVarAccept, localVarContentType);
    try {
      EsitoSalvataggioIncontroDTO prenotazione = new ObjectMapper().reader(EsitoSalvataggioIncontroDTO.class).readValue(response.getData());
      logger.debug("[PslOrchApiClient::saveIncontro] prenotazione="+prenotazione);
      return prenotazione;
    } catch (Exception ex) {
      throw new ApiException("Response from server " + response.getData(), ex);
    }
  }

  /**
   * Annulla calendario.
   *
   * @param params the params
   * @return the esito DTO
   * @throws Exception the exception
   */
  public EsitoDTO annullaCalendario(ParametriAnnulloCalendario params) throws Exception {
    
    String localVarPath = "/calendario/annulla_calendario";
    String localVarPostBody = new ObjectMapper().writerWithType(ParametriAnnulloCalendario.class).writeValueAsString(params);
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    final String localVarContentType = "application/json";
    final String localVarAccept = "application/json";
    logger.debug("[PslOrchApiClient::annullaCalendario] url="+apiClient.getUrl()+localVarPath+" ,localVarPostBody="+localVarPostBody);
    ApiResponse response = apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody,
        localVarHeaderParams, localVarAccept, localVarContentType);
    try {
      EsitoDTO esito = new ObjectMapper().reader(EsitoDTO.class).readValue(response.getData());
      logger.debug("[PslOrchApiClient::annullaCalendario] esito="+esito);
      return esito;
    } catch (Exception ex) {
      throw new ApiException("Response from server " + response.getData(), ex);
    }
  }
  
}
