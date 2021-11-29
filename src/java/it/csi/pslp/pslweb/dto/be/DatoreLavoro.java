/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.Indirizzo;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class DatoreLavoro   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceFiscale = null;
  private String denominazione = null;
  private Indirizzo indirizzo = null;
  private Decodifica settore = null;

  /**
   **/
  

  @JsonProperty("codice_fiscale") 
 
  public String getCodiceFiscale() {
    return codiceFiscale;
  }
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   **/
  

  @JsonProperty("denominazione") 
 
  public String getDenominazione() {
    return denominazione;
  }
  public void setDenominazione(String denominazione) {
    this.denominazione = denominazione;
  }

  /**
   **/
  

  @JsonProperty("indirizzo") 
 
  public Indirizzo getIndirizzo() {
    return indirizzo;
  }
  public void setIndirizzo(Indirizzo indirizzo) {
    this.indirizzo = indirizzo;
  }

  /**
   **/
  

  @JsonProperty("settore") 
 
  public Decodifica getSettore() {
    return settore;
  }
  public void setSettore(Decodifica settore) {
    this.settore = settore;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatoreLavoro datoreLavoro = (DatoreLavoro) o;
    return Objects.equals(codiceFiscale, datoreLavoro.codiceFiscale) &&
        Objects.equals(denominazione, datoreLavoro.denominazione) &&
        Objects.equals(indirizzo, datoreLavoro.indirizzo) &&
        Objects.equals(settore, datoreLavoro.settore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceFiscale, denominazione, indirizzo, settore);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DatoreLavoro {\n");
    
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    denominazione: ").append(toIndentedString(denominazione)).append("\n");
    sb.append("    indirizzo: ").append(toIndentedString(indirizzo)).append("\n");
    sb.append("    settore: ").append(toIndentedString(settore)).append("\n");
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

