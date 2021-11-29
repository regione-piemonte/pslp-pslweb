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

public class MappaErrori   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceMessaggio = null;
  private String descrMessaggio = null;

  /**
   **/
  

  @JsonProperty("codice_messaggio") 
 
  public String getCodiceMessaggio() {
    return codiceMessaggio;
  }
  public void setCodiceMessaggio(String codiceMessaggio) {
    this.codiceMessaggio = codiceMessaggio;
  }

  /**
   **/
  

  @JsonProperty("descr_messaggio") 
 
  public String getDescrMessaggio() {
    return descrMessaggio;
  }
  public void setDescrMessaggio(String descrMessaggio) {
    this.descrMessaggio = descrMessaggio;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MappaErrori mappaErrori = (MappaErrori) o;
    return Objects.equals(codiceMessaggio, mappaErrori.codiceMessaggio) &&
        Objects.equals(descrMessaggio, mappaErrori.descrMessaggio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceMessaggio, descrMessaggio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MappaErrori {\n");
    
    sb.append("    codiceMessaggio: ").append(toIndentedString(codiceMessaggio)).append("\n");
    sb.append("    descrMessaggio: ").append(toIndentedString(descrMessaggio)).append("\n");
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

