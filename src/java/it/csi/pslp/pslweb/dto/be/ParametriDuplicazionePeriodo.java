/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ParametriDuplicazionePeriodo   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idPeriodo = null;
  private Date dataA = null;
  private Boolean duplicaEccezione = null;

  /**
   **/
  

  @JsonProperty("id_periodo") 
 
  public Long getIdPeriodo() {
    return idPeriodo;
  }
  public void setIdPeriodo(Long idPeriodo) {
    this.idPeriodo = idPeriodo;
  }

  /**
   **/
  

  @JsonProperty("data_a") 
 
  public Date getDataA() {
    return dataA;
  }
  public void setDataA(Date dataA) {
    this.dataA = dataA;
  }

  /**
   **/
  

  @JsonProperty("duplica_eccezione") 
 
  public Boolean isDuplicaEccezione() {
    return duplicaEccezione;
  }
  public void setDuplicaEccezione(Boolean duplicaEccezione) {
    this.duplicaEccezione = duplicaEccezione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParametriDuplicazionePeriodo parametriDuplicazionePeriodo = (ParametriDuplicazionePeriodo) o;
    return Objects.equals(idPeriodo, parametriDuplicazionePeriodo.idPeriodo) &&
        Objects.equals(dataA, parametriDuplicazionePeriodo.dataA) &&
        Objects.equals(duplicaEccezione, parametriDuplicazionePeriodo.duplicaEccezione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idPeriodo, dataA, duplicaEccezione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParametriDuplicazionePeriodo {\n");
    
    sb.append("    idPeriodo: ").append(toIndentedString(idPeriodo)).append("\n");
    sb.append("    dataA: ").append(toIndentedString(dataA)).append("\n");
    sb.append("    duplicaEccezione: ").append(toIndentedString(duplicaEccezione)).append("\n");
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

