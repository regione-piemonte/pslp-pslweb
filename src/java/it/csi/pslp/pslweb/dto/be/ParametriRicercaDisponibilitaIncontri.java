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

public class ParametriRicercaDisponibilitaIncontri   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String dataDa = null;
  private String dataA = null;
  private String codAmbito = null;
  private String gruppoOperatore = null;
  private Integer codOperaratore = null;
  private Integer subcodice = null;

  /**
   **/
  

  @JsonProperty("data_da") 
 
  public String getDataDa() {
    return dataDa;
  }
  public void setDataDa(String dataDa) {
    this.dataDa = dataDa;
  }

  /**
   **/
  

  @JsonProperty("data_a") 
 
  public String getDataA() {
    return dataA;
  }
  public void setDataA(String dataA) {
    this.dataA = dataA;
  }

  /**
   **/
  

  @JsonProperty("cod_ambito") 
 
  public String getCodAmbito() {
    return codAmbito;
  }
  public void setCodAmbito(String codAmbito) {
    this.codAmbito = codAmbito;
  }

  /**
   **/
  

  @JsonProperty("gruppo_operatore") 
 
  public String getGruppoOperatore() {
    return gruppoOperatore;
  }
  public void setGruppoOperatore(String gruppoOperatore) {
    this.gruppoOperatore = gruppoOperatore;
  }

  /**
   **/
  

  @JsonProperty("cod_operaratore") 
 
  public Integer getCodOperaratore() {
    return codOperaratore;
  }
  public void setCodOperaratore(Integer codOperaratore) {
    this.codOperaratore = codOperaratore;
  }

  /**
   **/
  

  @JsonProperty("subcodice") 
 
  public Integer getSubcodice() {
    return subcodice;
  }
  public void setSubcodice(Integer subcodice) {
    this.subcodice = subcodice;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParametriRicercaDisponibilitaIncontri parametriRicercaDisponibilitaIncontri = (ParametriRicercaDisponibilitaIncontri) o;
    return Objects.equals(dataDa, parametriRicercaDisponibilitaIncontri.dataDa) &&
        Objects.equals(dataA, parametriRicercaDisponibilitaIncontri.dataA) &&
        Objects.equals(codAmbito, parametriRicercaDisponibilitaIncontri.codAmbito) &&
        Objects.equals(gruppoOperatore, parametriRicercaDisponibilitaIncontri.gruppoOperatore) &&
        Objects.equals(codOperaratore, parametriRicercaDisponibilitaIncontri.codOperaratore) &&
        Objects.equals(subcodice, parametriRicercaDisponibilitaIncontri.subcodice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataDa, dataA, codAmbito, gruppoOperatore, codOperaratore, subcodice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParametriRicercaDisponibilitaIncontri {\n");
    
    sb.append("    dataDa: ").append(toIndentedString(dataDa)).append("\n");
    sb.append("    dataA: ").append(toIndentedString(dataA)).append("\n");
    sb.append("    codAmbito: ").append(toIndentedString(codAmbito)).append("\n");
    sb.append("    gruppoOperatore: ").append(toIndentedString(gruppoOperatore)).append("\n");
    sb.append("    codOperaratore: ").append(toIndentedString(codOperaratore)).append("\n");
    sb.append("    subcodice: ").append(toIndentedString(subcodice)).append("\n");
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

