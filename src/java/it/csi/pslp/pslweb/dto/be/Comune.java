/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Provincia;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Comune   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceMinisteriale = null;
  private String descrizione = null;
  private String cap = null;
  private Provincia provincia = null;
  private Long idCpiSilp = null;

  /**
   * codice ministeriale
   **/
  

  @JsonProperty("codice_ministeriale") 
 
  public String getCodiceMinisteriale() {
    return codiceMinisteriale;
  }
  public void setCodiceMinisteriale(String codiceMinisteriale) {
    this.codiceMinisteriale = codiceMinisteriale;
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
   * cap
   **/
  

  @JsonProperty("cap") 
 
  public String getCap() {
    return cap;
  }
  public void setCap(String cap) {
    this.cap = cap;
  }

  /**
   * provincia
   **/
  

  @JsonProperty("provincia") 
 
  public Provincia getProvincia() {
    return provincia;
  }
  public void setProvincia(Provincia provincia) {
    this.provincia = provincia;
  }

  /**
   * id cpi silp
   **/
  

  @JsonProperty("id_cpi_silp") 
 
  public Long getIdCpiSilp() {
    return idCpiSilp;
  }
  public void setIdCpiSilp(Long idCpiSilp) {
    this.idCpiSilp = idCpiSilp;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comune comune = (Comune) o;
    return Objects.equals(codiceMinisteriale, comune.codiceMinisteriale) &&
        Objects.equals(descrizione, comune.descrizione) &&
        Objects.equals(cap, comune.cap) &&
        Objects.equals(provincia, comune.provincia) &&
        Objects.equals(idCpiSilp, comune.idCpiSilp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceMinisteriale, descrizione, cap, provincia, idCpiSilp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Comune {\n");
    
    sb.append("    codiceMinisteriale: ").append(toIndentedString(codiceMinisteriale)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    cap: ").append(toIndentedString(cap)).append("\n");
    sb.append("    provincia: ").append(toIndentedString(provincia)).append("\n");
    sb.append("    idCpiSilp: ").append(toIndentedString(idCpiSilp)).append("\n");
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

