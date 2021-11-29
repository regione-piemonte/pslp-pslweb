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

public class CondizioneOccupazionale   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codice = null;
  private Long codiceSilp = null;
  private String descrizione = null;

  /**
   **/
  

  @JsonProperty("codice") 
 
  public String getCodice() {
    return codice;
  }
  public void setCodice(String codice) {
    this.codice = codice;
  }

  /**
   **/
  

  @JsonProperty("codice_silp") 
 
  public Long getCodiceSilp() {
    return codiceSilp;
  }
  public void setCodiceSilp(Long codiceSilp) {
    this.codiceSilp = codiceSilp;
  }

  /**
   **/
  

  @JsonProperty("descrizione") 
 
  public String getDescrizione() {
    return descrizione;
  }
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CondizioneOccupazionale condizioneOccupazionale = (CondizioneOccupazionale) o;
    return Objects.equals(codice, condizioneOccupazionale.codice) &&
        Objects.equals(codiceSilp, condizioneOccupazionale.codiceSilp) &&
        Objects.equals(descrizione, condizioneOccupazionale.descrizione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codice, codiceSilp, descrizione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CondizioneOccupazionale {\n");
    
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    codiceSilp: ").append(toIndentedString(codiceSilp)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
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

