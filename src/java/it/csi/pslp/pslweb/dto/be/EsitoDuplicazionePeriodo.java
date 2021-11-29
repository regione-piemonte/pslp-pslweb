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

public class EsitoDuplicazionePeriodo   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idPeriodoCeato = null;
  private String code = null;
  private String messaggioCittadino = null;

  /**
   **/
  

  @JsonProperty("id_periodo_ceato") 
 
  public Long getIdPeriodoCeato() {
    return idPeriodoCeato;
  }
  public void setIdPeriodoCeato(Long idPeriodoCeato) {
    this.idPeriodoCeato = idPeriodoCeato;
  }

  /**
   **/
  

  @JsonProperty("code") 
 
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
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
    EsitoDuplicazionePeriodo esitoDuplicazionePeriodo = (EsitoDuplicazionePeriodo) o;
    return Objects.equals(idPeriodoCeato, esitoDuplicazionePeriodo.idPeriodoCeato) &&
        Objects.equals(code, esitoDuplicazionePeriodo.code) &&
        Objects.equals(messaggioCittadino, esitoDuplicazionePeriodo.messaggioCittadino);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idPeriodoCeato, code, messaggioCittadino);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoDuplicazionePeriodo {\n");
    
    sb.append("    idPeriodoCeato: ").append(toIndentedString(idPeriodoCeato)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
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

