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

public class StampaPattoDiServizio   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String titolo = null;
  private String sottotitolo = null;
  private String nome = null;
  private String cognome = null;
  private String codiceFiscale = null;
  private String percettore = null;
  private String nonPercettore = null;
  private String dataDownload = null;

  /**
   **/
  

  @JsonProperty("titolo") 
 
  public String getTitolo() {
    return titolo;
  }
  public void setTitolo(String titolo) {
    this.titolo = titolo;
  }

  /**
   **/
  

  @JsonProperty("sottotitolo") 
 
  public String getSottotitolo() {
    return sottotitolo;
  }
  public void setSottotitolo(String sottotitolo) {
    this.sottotitolo = sottotitolo;
  }

  /**
   **/
  

  @JsonProperty("nome") 
 
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   **/
  

  @JsonProperty("cognome") 
 
  public String getCognome() {
    return cognome;
  }
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   **/
  

  @JsonProperty("codiceFiscale") 
 
  public String getCodiceFiscale() {
    return codiceFiscale;
  }
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   **/
  

  @JsonProperty("percettore") 
 
  public String getPercettore() {
    return percettore;
  }
  public void setPercettore(String percettore) {
    this.percettore = percettore;
  }

  /**
   **/
  

  @JsonProperty("non_percettore") 
 
  public String getNonPercettore() {
    return nonPercettore;
  }
  public void setNonPercettore(String nonPercettore) {
    this.nonPercettore = nonPercettore;
  }

  /**
   **/
  

  @JsonProperty("data_download") 
 
  public String getDataDownload() {
    return dataDownload;
  }
  public void setDataDownload(String dataDownload) {
    this.dataDownload = dataDownload;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StampaPattoDiServizio stampaPattoDiServizio = (StampaPattoDiServizio) o;
    return Objects.equals(titolo, stampaPattoDiServizio.titolo) &&
        Objects.equals(sottotitolo, stampaPattoDiServizio.sottotitolo) &&
        Objects.equals(nome, stampaPattoDiServizio.nome) &&
        Objects.equals(cognome, stampaPattoDiServizio.cognome) &&
        Objects.equals(codiceFiscale, stampaPattoDiServizio.codiceFiscale) &&
        Objects.equals(percettore, stampaPattoDiServizio.percettore) &&
        Objects.equals(nonPercettore, stampaPattoDiServizio.nonPercettore) &&
        Objects.equals(dataDownload, stampaPattoDiServizio.dataDownload);
  }

  @Override
  public int hashCode() {
    return Objects.hash(titolo, sottotitolo, nome, cognome, codiceFiscale, percettore, nonPercettore, dataDownload);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StampaPattoDiServizio {\n");
    
    sb.append("    titolo: ").append(toIndentedString(titolo)).append("\n");
    sb.append("    sottotitolo: ").append(toIndentedString(sottotitolo)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    percettore: ").append(toIndentedString(percettore)).append("\n");
    sb.append("    nonPercettore: ").append(toIndentedString(nonPercettore)).append("\n");
    sb.append("    dataDownload: ").append(toIndentedString(dataDownload)).append("\n");
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

