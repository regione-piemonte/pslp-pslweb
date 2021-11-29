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

public class EsitoVerificaEsistenzaSap   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceSap = null;
  private String messaggioCittadino = null;

  /**
   **/
  

  @JsonProperty("codice_sap") 
 
  public String getCodiceSap() {
    return codiceSap;
  }
  public void setCodiceSap(String codiceSap) {
    this.codiceSap = codiceSap;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoVerificaEsistenzaSap esitoVerificaEsistenzaSap = (EsitoVerificaEsistenzaSap) o;
    return Objects.equals(codiceSap, esitoVerificaEsistenzaSap.codiceSap) &&
        Objects.equals(messaggioCittadino, esitoVerificaEsistenzaSap.messaggioCittadino);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceSap, messaggioCittadino);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoVerificaEsistenzaSap {\n");
    
    sb.append("    codiceSap: ").append(toIndentedString(codiceSap)).append("\n");
    sb.append("    messaggioCittadino: ").append(toIndentedString(messaggioCittadino)).append("\n");
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

