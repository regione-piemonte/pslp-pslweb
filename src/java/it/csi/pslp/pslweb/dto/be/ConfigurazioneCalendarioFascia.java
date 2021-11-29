/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ConfigurazioneCalendarioFascia   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idFascia = null;
  private Integer idGiornoSettimana = null;
  private Integer numMaxPrenotazioni = null;
  private Integer oraInizio = null;
  private Integer oraFine = null;
  private Integer durataSlot = null;

  /**
   **/
  

  @JsonProperty("id_fascia") 
 
  public Long getIdFascia() {
    return idFascia;
  }
  public void setIdFascia(Long idFascia) {
    this.idFascia = idFascia;
  }

  /**
   **/
  

  @JsonProperty("id_giorno_settimana") 
 
  public Integer getIdGiornoSettimana() {
    return idGiornoSettimana;
  }
  public void setIdGiornoSettimana(Integer idGiornoSettimana) {
    this.idGiornoSettimana = idGiornoSettimana;
  }

  /**
   **/
  

  @JsonProperty("num_max_prenotazioni") 
 
  public Integer getNumMaxPrenotazioni() {
    return numMaxPrenotazioni;
  }
  public void setNumMaxPrenotazioni(Integer numMaxPrenotazioni) {
    this.numMaxPrenotazioni = numMaxPrenotazioni;
  }

  /**
   **/
  

  @JsonProperty("ora_inizio") 
 
  public Integer getOraInizio() {
    return oraInizio;
  }
  public void setOraInizio(Integer oraInizio) {
    this.oraInizio = oraInizio;
  }

  /**
   **/
  

  @JsonProperty("ora_fine") 
 
  public Integer getOraFine() {
    return oraFine;
  }
  public void setOraFine(Integer oraFine) {
    this.oraFine = oraFine;
  }

  /**
   **/
  

  @JsonProperty("durata_slot") 
 
  public Integer getDurataSlot() {
    return durataSlot;
  }
  public void setDurataSlot(Integer durataSlot) {
    this.durataSlot = durataSlot;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneCalendarioFascia configurazioneCalendarioFascia = (ConfigurazioneCalendarioFascia) o;
    return Objects.equals(idFascia, configurazioneCalendarioFascia.idFascia) &&
        Objects.equals(idGiornoSettimana, configurazioneCalendarioFascia.idGiornoSettimana) &&
        Objects.equals(numMaxPrenotazioni, configurazioneCalendarioFascia.numMaxPrenotazioni) &&
        Objects.equals(oraInizio, configurazioneCalendarioFascia.oraInizio) &&
        Objects.equals(oraFine, configurazioneCalendarioFascia.oraFine) &&
        Objects.equals(durataSlot, configurazioneCalendarioFascia.durataSlot);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idFascia, idGiornoSettimana, numMaxPrenotazioni, oraInizio, oraFine, durataSlot);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneCalendarioFascia {\n");
    
    sb.append("    idFascia: ").append(toIndentedString(idFascia)).append("\n");
    sb.append("    idGiornoSettimana: ").append(toIndentedString(idGiornoSettimana)).append("\n");
    sb.append("    numMaxPrenotazioni: ").append(toIndentedString(numMaxPrenotazioni)).append("\n");
    sb.append("    oraInizio: ").append(toIndentedString(oraInizio)).append("\n");
    sb.append("    oraFine: ").append(toIndentedString(oraFine)).append("\n");
    sb.append("    durataSlot: ").append(toIndentedString(durataSlot)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

