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

public class Sportello   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String gruppoOperatore = null;
  private Long codOperatore = null;
  private Long subcodice = null;
  private String codiceMinisterialeComune = null;
  private String codiceIntermediario = null;
  private String descrizione = null;
  private String descrizioneIndirizzo = null;
  private String gruppoOperatoreEnteAppartenenza = null;
  private Long codOperatoreEnteAppartenenza = null;
  private Long subcodiceEnteAppartenenza = null;

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
  

  @JsonProperty("descrizione") 
 
  public String getDescrizione() {
    return descrizione;
  }
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   **/
  

  @JsonProperty("descrizione_indirizzo") 
 
  public String getDescrizioneIndirizzo() {
    return descrizioneIndirizzo;
  }
  public void setDescrizioneIndirizzo(String descrizioneIndirizzo) {
    this.descrizioneIndirizzo = descrizioneIndirizzo;
  }

  /**
   **/
  

  @JsonProperty("gruppo_operatore_ente_appartenenza") 
 
  public String getGruppoOperatoreEnteAppartenenza() {
    return gruppoOperatoreEnteAppartenenza;
  }
  public void setGruppoOperatoreEnteAppartenenza(String gruppoOperatoreEnteAppartenenza) {
    this.gruppoOperatoreEnteAppartenenza = gruppoOperatoreEnteAppartenenza;
  }

  /**
   **/
  

  @JsonProperty("cod_operatore_ente_appartenenza") 
 
  public Long getCodOperatoreEnteAppartenenza() {
    return codOperatoreEnteAppartenenza;
  }
  public void setCodOperatoreEnteAppartenenza(Long codOperatoreEnteAppartenenza) {
    this.codOperatoreEnteAppartenenza = codOperatoreEnteAppartenenza;
  }

  /**
   **/
  

  @JsonProperty("subcodice_ente_appartenenza") 
 
  public Long getSubcodiceEnteAppartenenza() {
    return subcodiceEnteAppartenenza;
  }
  public void setSubcodiceEnteAppartenenza(Long subcodiceEnteAppartenenza) {
    this.subcodiceEnteAppartenenza = subcodiceEnteAppartenenza;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sportello sportello = (Sportello) o;
    return Objects.equals(gruppoOperatore, sportello.gruppoOperatore) &&
        Objects.equals(codOperatore, sportello.codOperatore) &&
        Objects.equals(subcodice, sportello.subcodice) &&
        Objects.equals(codiceMinisterialeComune, sportello.codiceMinisterialeComune) &&
        Objects.equals(codiceIntermediario, sportello.codiceIntermediario) &&
        Objects.equals(descrizione, sportello.descrizione) &&
        Objects.equals(descrizioneIndirizzo, sportello.descrizioneIndirizzo) &&
        Objects.equals(gruppoOperatoreEnteAppartenenza, sportello.gruppoOperatoreEnteAppartenenza) &&
        Objects.equals(codOperatoreEnteAppartenenza, sportello.codOperatoreEnteAppartenenza) &&
        Objects.equals(subcodiceEnteAppartenenza, sportello.subcodiceEnteAppartenenza);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gruppoOperatore, codOperatore, subcodice, codiceMinisterialeComune, codiceIntermediario, descrizione, descrizioneIndirizzo, gruppoOperatoreEnteAppartenenza, codOperatoreEnteAppartenenza, subcodiceEnteAppartenenza);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Sportello {\n");
    
    sb.append("    gruppoOperatore: ").append(toIndentedString(gruppoOperatore)).append("\n");
    sb.append("    codOperatore: ").append(toIndentedString(codOperatore)).append("\n");
    sb.append("    subcodice: ").append(toIndentedString(subcodice)).append("\n");
    sb.append("    codiceMinisterialeComune: ").append(toIndentedString(codiceMinisterialeComune)).append("\n");
    sb.append("    codiceIntermediario: ").append(toIndentedString(codiceIntermediario)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    descrizioneIndirizzo: ").append(toIndentedString(descrizioneIndirizzo)).append("\n");
    sb.append("    gruppoOperatoreEnteAppartenenza: ").append(toIndentedString(gruppoOperatoreEnteAppartenenza)).append("\n");
    sb.append("    codOperatoreEnteAppartenenza: ").append(toIndentedString(codOperatoreEnteAppartenenza)).append("\n");
    sb.append("    subcodiceEnteAppartenenza: ").append(toIndentedString(subcodiceEnteAppartenenza)).append("\n");
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

