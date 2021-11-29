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

public class MessaggioEsito   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceMessaggio = null;
  private String tipoMessaggio = null;
  private String messaggio = null;

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
  

  @JsonProperty("tipo_messaggio") 
 
  public String getTipoMessaggio() {
    return tipoMessaggio;
  }
  public void setTipoMessaggio(String tipoMessaggio) {
    this.tipoMessaggio = tipoMessaggio;
  }

  /**
   **/
  

  @JsonProperty("messaggio") 
 
  public String getMessaggio() {
    return messaggio;
  }
  public void setMessaggio(String messaggio) {
    this.messaggio = messaggio;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessaggioEsito messaggioEsito = (MessaggioEsito) o;
    return Objects.equals(codiceMessaggio, messaggioEsito.codiceMessaggio) &&
        Objects.equals(tipoMessaggio, messaggioEsito.tipoMessaggio) &&
        Objects.equals(messaggio, messaggioEsito.messaggio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceMessaggio, tipoMessaggio, messaggio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessaggioEsito {\n");
    
    sb.append("    codiceMessaggio: ").append(toIndentedString(codiceMessaggio)).append("\n");
    sb.append("    tipoMessaggio: ").append(toIndentedString(tipoMessaggio)).append("\n");
    sb.append("    messaggio: ").append(toIndentedString(messaggio)).append("\n");
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

