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

public class InformazioneAggiuntiva   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long codice = null;
  private Long codiceConfigurazione = null;
  private String descrizioneConfigurazione = null;
  private Date data = null;
  private String valore = null;
  private String note = null;

  /**
   **/
  

  @JsonProperty("codice") 
 
  public Long getCodice() {
    return codice;
  }
  public void setCodice(Long codice) {
    this.codice = codice;
  }

  /**
   **/
  

  @JsonProperty("codice_configurazione") 
 
  public Long getCodiceConfigurazione() {
    return codiceConfigurazione;
  }
  public void setCodiceConfigurazione(Long codiceConfigurazione) {
    this.codiceConfigurazione = codiceConfigurazione;
  }

  /**
   **/
  

  @JsonProperty("descrizione_configurazione") 
 
  public String getDescrizioneConfigurazione() {
    return descrizioneConfigurazione;
  }
  public void setDescrizioneConfigurazione(String descrizioneConfigurazione) {
    this.descrizioneConfigurazione = descrizioneConfigurazione;
  }

  /**
   **/
  

  @JsonProperty("data") 
 
  public Date getData() {
    return data;
  }
  public void setData(Date data) {
    this.data = data;
  }

  /**
   **/
  

  @JsonProperty("valore") 
 
  public String getValore() {
    return valore;
  }
  public void setValore(String valore) {
    this.valore = valore;
  }

  /**
   **/
  

  @JsonProperty("note") 
 
  public String getNote() {
    return note;
  }
  public void setNote(String note) {
    this.note = note;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InformazioneAggiuntiva informazioneAggiuntiva = (InformazioneAggiuntiva) o;
    return Objects.equals(codice, informazioneAggiuntiva.codice) &&
        Objects.equals(codiceConfigurazione, informazioneAggiuntiva.codiceConfigurazione) &&
        Objects.equals(descrizioneConfigurazione, informazioneAggiuntiva.descrizioneConfigurazione) &&
        Objects.equals(data, informazioneAggiuntiva.data) &&
        Objects.equals(valore, informazioneAggiuntiva.valore) &&
        Objects.equals(note, informazioneAggiuntiva.note);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codice, codiceConfigurazione, descrizioneConfigurazione, data, valore, note);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InformazioneAggiuntiva {\n");
    
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    codiceConfigurazione: ").append(toIndentedString(codiceConfigurazione)).append("\n");
    sb.append("    descrizioneConfigurazione: ").append(toIndentedString(descrizioneConfigurazione)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    valore: ").append(toIndentedString(valore)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
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

