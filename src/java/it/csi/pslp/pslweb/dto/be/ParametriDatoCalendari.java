/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ParametriDatoCalendari   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private List<Long> idCalendari = new ArrayList<Long>();
  private String campo = null;
  private String valore = null;

  /**
   **/
  

  @JsonProperty("id_calendari") 
 
  public List<Long> getIdCalendari() {
    return idCalendari;
  }
  public void setIdCalendari(List<Long> idCalendari) {
    this.idCalendari = idCalendari;
  }

  /**
   **/
  

  @JsonProperty("campo") 
 
  public String getCampo() {
    return campo;
  }
  public void setCampo(String campo) {
    this.campo = campo;
  }

  /**
   **/
  

  @JsonProperty("valore") 
 
  public String getValore() {
    return valore;
  }
  public void setValore(String valore) {
    this.valore = valore;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParametriDatoCalendari parametriDatoCalendari = (ParametriDatoCalendari) o;
    return Objects.equals(idCalendari, parametriDatoCalendari.idCalendari) &&
        Objects.equals(campo, parametriDatoCalendari.campo) &&
        Objects.equals(valore, parametriDatoCalendari.valore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCalendari, campo, valore);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParametriDatoCalendari {\n");
    
    sb.append("    idCalendari: ").append(toIndentedString(idCalendari)).append("\n");
    sb.append("    campo: ").append(toIndentedString(campo)).append("\n");
    sb.append("    valore: ").append(toIndentedString(valore)).append("\n");
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

