/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Utente;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class EsitoVerificaMinore   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private List<String> codiciEsito = new ArrayList<String>();
  private Utente utente = null;

  /**
   **/
  

  @JsonProperty("codici_esito") 
 
  public List<String> getCodiciEsito() {
    return codiciEsito;
  }
  public void setCodiciEsito(List<String> codiciEsito) {
    this.codiciEsito = codiciEsito;
  }

  /**
   **/
  

  @JsonProperty("utente") 
 
  public Utente getUtente() {
    return utente;
  }
  public void setUtente(Utente utente) {
    this.utente = utente;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoVerificaMinore esitoVerificaMinore = (EsitoVerificaMinore) o;
    return Objects.equals(codiciEsito, esitoVerificaMinore.codiciEsito) &&
        Objects.equals(utente, esitoVerificaMinore.utente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiciEsito, utente);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoVerificaMinore {\n");
    
    sb.append("    codiciEsito: ").append(toIndentedString(codiciEsito)).append("\n");
    sb.append("    utente: ").append(toIndentedString(utente)).append("\n");
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

