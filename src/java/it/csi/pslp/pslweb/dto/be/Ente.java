/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Sportello;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Ente   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String gruppoOperatore = null;
  private Long codOperatore = null;
  private Long subcodice = null;
  private String descrizione = null;
  private String codiceMinisterialeComune = null;
  private String codiceIntermediario = null;
  private List<Sportello> sportelli = new ArrayList<Sportello>();

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
  

  @JsonProperty("cod_operatore") 
 
  public Long getCodOperatore() {
    return codOperatore;
  }
  public void setCodOperatore(Long codOperatore) {
    this.codOperatore = codOperatore;
  }

  /**
   **/
  

  @JsonProperty("subcodice") 
 
  public Long getSubcodice() {
    return subcodice;
  }
  public void setSubcodice(Long subcodice) {
    this.subcodice = subcodice;
  }

  /**
   **/
  

  @JsonProperty("descrizione") 
 
  public String getDescrizione() {
    return descrizione;
  }
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   **/
  

  @JsonProperty("codice_ministeriale_comune") 
 
  public String getCodiceMinisterialeComune() {
    return codiceMinisterialeComune;
  }
  public void setCodiceMinisterialeComune(String codiceMinisterialeComune) {
    this.codiceMinisterialeComune = codiceMinisterialeComune;
  }

  /**
   **/
  

  @JsonProperty("codice_intermediario") 
 
  public String getCodiceIntermediario() {
    return codiceIntermediario;
  }
  public void setCodiceIntermediario(String codiceIntermediario) {
    this.codiceIntermediario = codiceIntermediario;
  }

  /**
   **/
  

  @JsonProperty("sportelli") 
 
  public List<Sportello> getSportelli() {
    return sportelli;
  }
  public void setSportelli(List<Sportello> sportelli) {
    this.sportelli = sportelli;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ente ente = (Ente) o;
    return Objects.equals(gruppoOperatore, ente.gruppoOperatore) &&
        Objects.equals(codOperatore, ente.codOperatore) &&
        Objects.equals(subcodice, ente.subcodice) &&
        Objects.equals(descrizione, ente.descrizione) &&
        Objects.equals(codiceMinisterialeComune, ente.codiceMinisterialeComune) &&
        Objects.equals(codiceIntermediario, ente.codiceIntermediario) &&
        Objects.equals(sportelli, ente.sportelli);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gruppoOperatore, codOperatore, subcodice, descrizione, codiceMinisterialeComune, codiceIntermediario, sportelli);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ente {\n");
    
    sb.append("    gruppoOperatore: ").append(toIndentedString(gruppoOperatore)).append("\n");
    sb.append("    codOperatore: ").append(toIndentedString(codOperatore)).append("\n");
    sb.append("    subcodice: ").append(toIndentedString(subcodice)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    codiceMinisterialeComune: ").append(toIndentedString(codiceMinisterialeComune)).append("\n");
    sb.append("    codiceIntermediario: ").append(toIndentedString(codiceIntermediario)).append("\n");
    sb.append("    sportelli: ").append(toIndentedString(sportelli)).append("\n");
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

