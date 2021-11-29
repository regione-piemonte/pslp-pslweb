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

public class Parametro   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idParametro = null;
  private String codParametro = null;
  private String descrParametro = null;
  private String valoreParametro = null;
  private Date dtInizio = null;
  private Date dtFine = null;

  /**
   * id parametro
   **/
  

  @JsonProperty("id_parametro") 
 
  public Long getIdParametro() {
    return idParametro;
  }
  public void setIdParametro(Long idParametro) {
    this.idParametro = idParametro;
  }

  /**
   **/
  

  @JsonProperty("cod_parametro") 
 
  public String getCodParametro() {
    return codParametro;
  }
  public void setCodParametro(String codParametro) {
    this.codParametro = codParametro;
  }

  /**
   **/
  

  @JsonProperty("descr_parametro") 
 
  public String getDescrParametro() {
    return descrParametro;
  }
  public void setDescrParametro(String descrParametro) {
    this.descrParametro = descrParametro;
  }

  /**
   **/
  

  @JsonProperty("valore_parametro") 
 
  public String getValoreParametro() {
    return valoreParametro;
  }
  public void setValoreParametro(String valoreParametro) {
    this.valoreParametro = valoreParametro;
  }

  /**
   * data inizio
   **/
  

  @JsonProperty("dt_inizio") 
 
  public Date getDtInizio() {
    return dtInizio;
  }
  public void setDtInizio(Date dtInizio) {
    this.dtInizio = dtInizio;
  }

  /**
   * data fine
   **/
  

  @JsonProperty("dt_fine") 
 
  public Date getDtFine() {
    return dtFine;
  }
  public void setDtFine(Date dtFine) {
    this.dtFine = dtFine;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Parametro parametro = (Parametro) o;
    return Objects.equals(idParametro, parametro.idParametro) &&
        Objects.equals(codParametro, parametro.codParametro) &&
        Objects.equals(descrParametro, parametro.descrParametro) &&
        Objects.equals(valoreParametro, parametro.valoreParametro) &&
        Objects.equals(dtInizio, parametro.dtInizio) &&
        Objects.equals(dtFine, parametro.dtFine);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idParametro, codParametro, descrParametro, valoreParametro, dtInizio, dtFine);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Parametro {\n");
    
    sb.append("    idParametro: ").append(toIndentedString(idParametro)).append("\n");
    sb.append("    codParametro: ").append(toIndentedString(codParametro)).append("\n");
    sb.append("    descrParametro: ").append(toIndentedString(descrParametro)).append("\n");
    sb.append("    valoreParametro: ").append(toIndentedString(valoreParametro)).append("\n");
    sb.append("    dtInizio: ").append(toIndentedString(dtInizio)).append("\n");
    sb.append("    dtFine: ").append(toIndentedString(dtFine)).append("\n");
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

