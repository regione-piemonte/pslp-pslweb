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

public class ParametriRicercaIncontriCalendario   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Date dataDa = null;
  private Date dataA = null;

  /**
   **/
  

  @JsonProperty("data_da") 
 
  public Date getDataDa() {
    return dataDa;
  }
  public void setDataDa(Date dataDa) {
    this.dataDa = dataDa;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParametriRicercaIncontriCalendario parametriRicercaIncontriCalendario = (ParametriRicercaIncontriCalendario) o;
    return Objects.equals(dataDa, parametriRicercaIncontriCalendario.dataDa) &&
        Objects.equals(dataA, parametriRicercaIncontriCalendario.dataA);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataDa, dataA);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParametriRicercaIncontriCalendario {\n");
    
    sb.append("    dataDa: ").append(toIndentedString(dataDa)).append("\n");
    sb.append("    dataA: ").append(toIndentedString(dataA)).append("\n");
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

