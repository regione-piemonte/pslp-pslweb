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

public class ParametriEliminazioneEccezione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idEccezione = null;

  /**
   **/
  

  @JsonProperty("id_eccezione") 
 
  public Long getIdEccezione() {
    return idEccezione;
  }
  public void setIdEccezione(Long idEccezione) {
    this.idEccezione = idEccezione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParametriEliminazioneEccezione parametriEliminazioneEccezione = (ParametriEliminazioneEccezione) o;
    return Objects.equals(idEccezione, parametriEliminazioneEccezione.idEccezione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idEccezione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParametriEliminazioneEccezione {\n");
    
    sb.append("    idEccezione: ").append(toIndentedString(idEccezione)).append("\n");
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

