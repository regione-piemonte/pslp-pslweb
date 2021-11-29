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

public class Esito   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String code = null;
  private String messaggioCittadino = null;
  private String messaggioInformativo = null;

  /**
   **/
  

  @JsonProperty("code") 
 
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }

  /**
   **/
  

  @JsonProperty("messaggioCittadino") 
 
  public String getMessaggioCittadino() {
    return messaggioCittadino;
  }
  public void setMessaggioCittadino(String messaggioCittadino) {
    this.messaggioCittadino = messaggioCittadino;
  }

  /**
   **/
  

  @JsonProperty("messaggioInformativo") 
 
  public String getMessaggioInformativo() {
    return messaggioInformativo;
  }
  public void setMessaggioInformativo(String messaggioInformativo) {
    this.messaggioInformativo = messaggioInformativo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Esito esito = (Esito) o;
    return Objects.equals(code, esito.code) &&
        Objects.equals(messaggioCittadino, esito.messaggioCittadino) &&
        Objects.equals(messaggioInformativo, esito.messaggioInformativo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, messaggioCittadino, messaggioInformativo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Esito {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    messaggioCittadino: ").append(toIndentedString(messaggioCittadino)).append("\n");
    sb.append("    messaggioInformativo: ").append(toIndentedString(messaggioInformativo)).append("\n");
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

