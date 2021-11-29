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

public class CategoriaProtetta   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String idSilp = null;
  private String descrizioneSilp = null;
  private String codiceMinistero = null;
  private String descrizioneMinistero = null;
  private String flgSpeciale = null;
  private Date dataInizio = null;
  private Date dataFine = null;

  /**
   **/
  

  @JsonProperty("id_silp") 
 
  public String getIdSilp() {
    return idSilp;
  }
  public void setIdSilp(String idSilp) {
    this.idSilp = idSilp;
  }

  /**
   **/
  

  @JsonProperty("descrizione_silp") 
 
  public String getDescrizioneSilp() {
    return descrizioneSilp;
  }
  public void setDescrizioneSilp(String descrizioneSilp) {
    this.descrizioneSilp = descrizioneSilp;
  }

  /**
   **/
  

  @JsonProperty("codice_ministero") 
 
  public String getCodiceMinistero() {
    return codiceMinistero;
  }
  public void setCodiceMinistero(String codiceMinistero) {
    this.codiceMinistero = codiceMinistero;
  }

  /**
   **/
  

  @JsonProperty("descrizione_ministero") 
 
  public String getDescrizioneMinistero() {
    return descrizioneMinistero;
  }
  public void setDescrizioneMinistero(String descrizioneMinistero) {
    this.descrizioneMinistero = descrizioneMinistero;
  }

  /**
   **/
  

  @JsonProperty("flg_speciale") 
 
  public String getFlgSpeciale() {
    return flgSpeciale;
  }
  public void setFlgSpeciale(String flgSpeciale) {
    this.flgSpeciale = flgSpeciale;
  }

  /**
   **/
  

  @JsonProperty("data_inizio") 
 
  public Date getDataInizio() {
    return dataInizio;
  }
  public void setDataInizio(Date dataInizio) {
    this.dataInizio = dataInizio;
  }

  /**
   **/
  

  @JsonProperty("data_fine") 
 
  public Date getDataFine() {
    return dataFine;
  }
  public void setDataFine(Date dataFine) {
    this.dataFine = dataFine;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CategoriaProtetta categoriaProtetta = (CategoriaProtetta) o;
    return Objects.equals(idSilp, categoriaProtetta.idSilp) &&
        Objects.equals(descrizioneSilp, categoriaProtetta.descrizioneSilp) &&
        Objects.equals(codiceMinistero, categoriaProtetta.codiceMinistero) &&
        Objects.equals(descrizioneMinistero, categoriaProtetta.descrizioneMinistero) &&
        Objects.equals(flgSpeciale, categoriaProtetta.flgSpeciale) &&
        Objects.equals(dataInizio, categoriaProtetta.dataInizio) &&
        Objects.equals(dataFine, categoriaProtetta.dataFine);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idSilp, descrizioneSilp, codiceMinistero, descrizioneMinistero, flgSpeciale, dataInizio, dataFine);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CategoriaProtetta {\n");
    
    sb.append("    idSilp: ").append(toIndentedString(idSilp)).append("\n");
    sb.append("    descrizioneSilp: ").append(toIndentedString(descrizioneSilp)).append("\n");
    sb.append("    codiceMinistero: ").append(toIndentedString(codiceMinistero)).append("\n");
    sb.append("    descrizioneMinistero: ").append(toIndentedString(descrizioneMinistero)).append("\n");
    sb.append("    flgSpeciale: ").append(toIndentedString(flgSpeciale)).append("\n");
    sb.append("    dataInizio: ").append(toIndentedString(dataInizio)).append("\n");
    sb.append("    dataFine: ").append(toIndentedString(dataFine)).append("\n");
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

