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

public class MotivoRilascioPermessoSoggiorno   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String vigore = null;
  private String descrizione = null;
  private String id = null;

  /**
   * vigore
   **/
  

  @JsonProperty("vigore") 
 
  public String getVigore() {
    return vigore;
  }
  public void setVigore(String vigore) {
    this.vigore = vigore;
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

  /**
   * id
   **/
  

  @JsonProperty("id") 
 
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MotivoRilascioPermessoSoggiorno motivoRilascioPermessoSoggiorno = (MotivoRilascioPermessoSoggiorno) o;
    return Objects.equals(vigore, motivoRilascioPermessoSoggiorno.vigore) &&
        Objects.equals(descrizione, motivoRilascioPermessoSoggiorno.descrizione) &&
        Objects.equals(id, motivoRilascioPermessoSoggiorno.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vigore, descrizione, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MotivoRilascioPermessoSoggiorno {\n");
    
    sb.append("    vigore: ").append(toIndentedString(vigore)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

