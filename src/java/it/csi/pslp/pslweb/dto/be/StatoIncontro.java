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

public class StatoIncontro   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codice = null;
  private String codiceSilp = null;
  private String descrizione = null;
  private String dataInizio = null;
  private String dataFine = null;
  private Boolean statoFinale = null;

  /**
   **/
  

  @JsonProperty("codice") 
 
  public String getCodice() {
    return codice;
  }
  public void setCodice(String codice) {
    this.codice = codice;
  }

  /**
   **/
  

  @JsonProperty("codice_silp") 
 
  public String getCodiceSilp() {
    return codiceSilp;
  }
  public void setCodiceSilp(String codiceSilp) {
    this.codiceSilp = codiceSilp;
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
  

  @JsonProperty("data_inizio") 
 
  public String getDataInizio() {
    return dataInizio;
  }
  public void setDataInizio(String dataInizio) {
    this.dataInizio = dataInizio;
  }

  /**
   **/
  

  @JsonProperty("data_fine") 
 
  public String getDataFine() {
    return dataFine;
  }
  public void setDataFine(String dataFine) {
    this.dataFine = dataFine;
  }

  /**
   **/
  

  @JsonProperty("stato_finale") 
 
  public Boolean isStatoFinale() {
    return statoFinale;
  }
  public void setStatoFinale(Boolean statoFinale) {
    this.statoFinale = statoFinale;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatoIncontro statoIncontro = (StatoIncontro) o;
    return Objects.equals(codice, statoIncontro.codice) &&
        Objects.equals(codiceSilp, statoIncontro.codiceSilp) &&
        Objects.equals(descrizione, statoIncontro.descrizione) &&
        Objects.equals(dataInizio, statoIncontro.dataInizio) &&
        Objects.equals(dataFine, statoIncontro.dataFine) &&
        Objects.equals(statoFinale, statoIncontro.statoFinale);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codice, codiceSilp, descrizione, dataInizio, dataFine, statoFinale);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StatoIncontro {\n");
    
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    codiceSilp: ").append(toIndentedString(codiceSilp)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    dataInizio: ").append(toIndentedString(dataInizio)).append("\n");
    sb.append("    dataFine: ").append(toIndentedString(dataFine)).append("\n");
    sb.append("    statoFinale: ").append(toIndentedString(statoFinale)).append("\n");
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

