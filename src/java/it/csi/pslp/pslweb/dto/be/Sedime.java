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

public class Sedime   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceMinisteriale = null;
  private String descrizione = null;

  /**
   * codice ministeriale
   **/
  

  @JsonProperty("codice_ministeriale") 
 
  public String getCodiceMinisteriale() {
    return codiceMinisteriale;
  }
  public void setCodiceMinisteriale(String codiceMinisteriale) {
    this.codiceMinisteriale = codiceMinisteriale;
  }

  /**
   * descrizione
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
    Sedime sedime = (Sedime) o;
    return Objects.equals(codiceMinisteriale, sedime.codiceMinisteriale) &&
        Objects.equals(descrizione, sedime.descrizione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceMinisteriale, descrizione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Sedime {\n");
    
    sb.append("    codiceMinisteriale: ").append(toIndentedString(codiceMinisteriale)).append("\n");
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

