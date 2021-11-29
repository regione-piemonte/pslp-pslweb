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

public class EsitoSaveProfilingDid   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idDatiProfiling = null;
  private String messaggioCittadino = null;

  /**
   **/
  

  @JsonProperty("idDatiProfiling") 
 
  public Long getIdDatiProfiling() {
    return idDatiProfiling;
  }
  public void setIdDatiProfiling(Long idDatiProfiling) {
    this.idDatiProfiling = idDatiProfiling;
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
    EsitoSaveProfilingDid esitoSaveProfilingDid = (EsitoSaveProfilingDid) o;
    return Objects.equals(idDatiProfiling, esitoSaveProfilingDid.idDatiProfiling) &&
        Objects.equals(messaggioCittadino, esitoSaveProfilingDid.messaggioCittadino);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idDatiProfiling, messaggioCittadino);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoSaveProfilingDid {\n");
    
    sb.append("    idDatiProfiling: ").append(toIndentedString(idDatiProfiling)).append("\n");
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

