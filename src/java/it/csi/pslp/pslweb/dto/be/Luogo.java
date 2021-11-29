/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Comune;
import it.csi.pslp.pslweb.dto.be.Nazione;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Luogo   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Comune comune = null;
  private Nazione stato = null;

  /**
   **/
  

  @JsonProperty("comune") 
 
  public Comune getComune() {
    return comune;
  }
  public void setComune(Comune comune) {
    this.comune = comune;
  }

  /**
   **/
  

  @JsonProperty("stato") 
 
  public Nazione getStato() {
    return stato;
  }
  public void setStato(Nazione stato) {
    this.stato = stato;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Luogo luogo = (Luogo) o;
    return Objects.equals(comune, luogo.comune) &&
        Objects.equals(stato, luogo.stato);
  }

  @Override
  public int hashCode() {
    return Objects.hash(comune, stato);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Luogo {\n");
    
    sb.append("    comune: ").append(toIndentedString(comune)).append("\n");
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
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

