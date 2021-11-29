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

public class ParametriEliminazioneFascia   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idFascia = null;

  /**
   **/
  

  @JsonProperty("id_fascia") 
 
  public Long getIdFascia() {
    return idFascia;
  }
  public void setIdFascia(Long idFascia) {
    this.idFascia = idFascia;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParametriEliminazioneFascia parametriEliminazioneFascia = (ParametriEliminazioneFascia) o;
    return Objects.equals(idFascia, parametriEliminazioneFascia.idFascia);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idFascia);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParametriEliminazioneFascia {\n");
    
    sb.append("    idFascia: ").append(toIndentedString(idFascia)).append("\n");
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

