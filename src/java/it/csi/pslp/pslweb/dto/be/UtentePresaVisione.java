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

public class UtentePresaVisione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codAmbito = null;

  /**
   **/
  

  @JsonProperty("cod_ambito") 
 
  public String getCodAmbito() {
    return codAmbito;
  }
  public void setCodAmbito(String codAmbito) {
    this.codAmbito = codAmbito;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UtentePresaVisione utentePresaVisione = (UtentePresaVisione) o;
    return Objects.equals(codAmbito, utentePresaVisione.codAmbito);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codAmbito);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UtentePresaVisione {\n");
    
    sb.append("    codAmbito: ").append(toIndentedString(codAmbito)).append("\n");
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

