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

public class RispostaDidMsg   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String descrRisposta = null;
  private String valore = null;

  /**
   **/
  

  @JsonProperty("descr_risposta") 
 
  public String getDescrRisposta() {
    return descrRisposta;
  }
  public void setDescrRisposta(String descrRisposta) {
    this.descrRisposta = descrRisposta;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RispostaDidMsg rispostaDidMsg = (RispostaDidMsg) o;
    return Objects.equals(descrRisposta, rispostaDidMsg.descrRisposta) &&
        Objects.equals(valore, rispostaDidMsg.valore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(descrRisposta, valore);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RispostaDidMsg {\n");
    
    sb.append("    descrRisposta: ").append(toIndentedString(descrRisposta)).append("\n");
    sb.append("    valore: ").append(toIndentedString(valore)).append("\n");
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

