/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ConfigurazioneCalendarioEccezione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idEccezione = null;
  private Date giorno = null;
  private Integer oraInizio = null;
  private Integer oraFine = null;
  private Integer numMaxPrenotazioni = null;
  private Boolean flagElaborata = null;

  /**
   **/
  

  @JsonProperty("id_eccezione") 
 
  public Long getIdEccezione() {
    return idEccezione;
  }
  public void setIdEccezione(Long idEccezione) {
    this.idEccezione = idEccezione;
  }

  /**
   **/
  

  @JsonProperty("giorno") 
 
  public Date getGiorno() {
    return giorno;
  }
  public void setGiorno(Date giorno) {
    this.giorno = giorno;
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
  

  @JsonProperty("num_max_prenotazioni") 
 
  public Integer getNumMaxPrenotazioni() {
    return numMaxPrenotazioni;
  }
  public void setNumMaxPrenotazioni(Integer numMaxPrenotazioni) {
    this.numMaxPrenotazioni = numMaxPrenotazioni;
  }

  /**
   **/
  

  @JsonProperty("flag_elaborata") 
 
  public Boolean isFlagElaborata() {
    return flagElaborata;
  }
  public void setFlagElaborata(Boolean flagElaborata) {
    this.flagElaborata = flagElaborata;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneCalendarioEccezione configurazioneCalendarioEccezione = (ConfigurazioneCalendarioEccezione) o;
    return Objects.equals(idEccezione, configurazioneCalendarioEccezione.idEccezione) &&
        Objects.equals(giorno, configurazioneCalendarioEccezione.giorno) &&
        Objects.equals(oraInizio, configurazioneCalendarioEccezione.oraInizio) &&
        Objects.equals(oraFine, configurazioneCalendarioEccezione.oraFine) &&
        Objects.equals(numMaxPrenotazioni, configurazioneCalendarioEccezione.numMaxPrenotazioni) &&
        Objects.equals(flagElaborata, configurazioneCalendarioEccezione.flagElaborata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idEccezione, giorno, oraInizio, oraFine, numMaxPrenotazioni, flagElaborata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneCalendarioEccezione {\n");
    
    sb.append("    idEccezione: ").append(toIndentedString(idEccezione)).append("\n");
    sb.append("    giorno: ").append(toIndentedString(giorno)).append("\n");
    sb.append("    oraInizio: ").append(toIndentedString(oraInizio)).append("\n");
    sb.append("    oraFine: ").append(toIndentedString(oraFine)).append("\n");
    sb.append("    numMaxPrenotazioni: ").append(toIndentedString(numMaxPrenotazioni)).append("\n");
    sb.append("    flagElaborata: ").append(toIndentedString(flagElaborata)).append("\n");
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

