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

public class IscrizioneL68   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idIscrizione = null;
  private Date dataIscrizione = null;
  private Date dataAnzianita = null;
  private String codStatoIscrizione = null;
  private String descrStatoIscrizione = null;
  private Long numIscrizioneProvinciale = null;

  /**
   * id iscrizione
   **/
  

  @JsonProperty("id_iscrizione") 
 
  public Long getIdIscrizione() {
    return idIscrizione;
  }
  public void setIdIscrizione(Long idIscrizione) {
    this.idIscrizione = idIscrizione;
  }

  /**
   * Data iscrizione
   **/
  

  @JsonProperty("data_iscrizione") 
 
  public Date getDataIscrizione() {
    return dataIscrizione;
  }
  public void setDataIscrizione(Date dataIscrizione) {
    this.dataIscrizione = dataIscrizione;
  }

  /**
   * Data anzianita
   **/
  

  @JsonProperty("data_anzianita") 
 
  public Date getDataAnzianita() {
    return dataAnzianita;
  }
  public void setDataAnzianita(Date dataAnzianita) {
    this.dataAnzianita = dataAnzianita;
  }

  /**
   * codice stato iscrizione
   **/
  

  @JsonProperty("cod_stato_iscrizione") 
 
  public String getCodStatoIscrizione() {
    return codStatoIscrizione;
  }
  public void setCodStatoIscrizione(String codStatoIscrizione) {
    this.codStatoIscrizione = codStatoIscrizione;
  }

  /**
   * descrizione stato iscrizione
   **/
  

  @JsonProperty("descr_stato_iscrizione") 
 
  public String getDescrStatoIscrizione() {
    return descrStatoIscrizione;
  }
  public void setDescrStatoIscrizione(String descrStatoIscrizione) {
    this.descrStatoIscrizione = descrStatoIscrizione;
  }

  /**
   * num iscrizione provinciale
   **/
  

  @JsonProperty("num_iscrizione_provinciale") 
 
  public Long getNumIscrizioneProvinciale() {
    return numIscrizioneProvinciale;
  }
  public void setNumIscrizioneProvinciale(Long numIscrizioneProvinciale) {
    this.numIscrizioneProvinciale = numIscrizioneProvinciale;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IscrizioneL68 iscrizioneL68 = (IscrizioneL68) o;
    return Objects.equals(idIscrizione, iscrizioneL68.idIscrizione) &&
        Objects.equals(dataIscrizione, iscrizioneL68.dataIscrizione) &&
        Objects.equals(dataAnzianita, iscrizioneL68.dataAnzianita) &&
        Objects.equals(codStatoIscrizione, iscrizioneL68.codStatoIscrizione) &&
        Objects.equals(descrStatoIscrizione, iscrizioneL68.descrStatoIscrizione) &&
        Objects.equals(numIscrizioneProvinciale, iscrizioneL68.numIscrizioneProvinciale);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idIscrizione, dataIscrizione, dataAnzianita, codStatoIscrizione, descrStatoIscrizione, numIscrizioneProvinciale);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IscrizioneL68 {\n");
    
    sb.append("    idIscrizione: ").append(toIndentedString(idIscrizione)).append("\n");
    sb.append("    dataIscrizione: ").append(toIndentedString(dataIscrizione)).append("\n");
    sb.append("    dataAnzianita: ").append(toIndentedString(dataAnzianita)).append("\n");
    sb.append("    codStatoIscrizione: ").append(toIndentedString(codStatoIscrizione)).append("\n");
    sb.append("    descrStatoIscrizione: ").append(toIndentedString(descrStatoIscrizione)).append("\n");
    sb.append("    numIscrizioneProvinciale: ").append(toIndentedString(numIscrizioneProvinciale)).append("\n");
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

