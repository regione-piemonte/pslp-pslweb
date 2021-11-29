/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Lingua   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Decodifica lingua = null;
  private Decodifica letto = null;
  private Decodifica scritto = null;
  private Decodifica parlato = null;

  /**
   **/
  

  @JsonProperty("lingua") 
 
  public Decodifica getLingua() {
    return lingua;
  }
  public void setLingua(Decodifica lingua) {
    this.lingua = lingua;
  }

  /**
   **/
  

  @JsonProperty("letto") 
 
  public Decodifica getLetto() {
    return letto;
  }
  public void setLetto(Decodifica letto) {
    this.letto = letto;
  }

  /**
   **/
  

  @JsonProperty("scritto") 
 
  public Decodifica getScritto() {
    return scritto;
  }
  public void setScritto(Decodifica scritto) {
    this.scritto = scritto;
  }

  /**
   **/
  

  @JsonProperty("parlato") 
 
  public Decodifica getParlato() {
    return parlato;
  }
  public void setParlato(Decodifica parlato) {
    this.parlato = parlato;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Lingua lingua = (Lingua) o;
    return Objects.equals(lingua, lingua.lingua) &&
        Objects.equals(letto, lingua.letto) &&
        Objects.equals(scritto, lingua.scritto) &&
        Objects.equals(parlato, lingua.parlato);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lingua, letto, scritto, parlato);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Lingua {\n");
    
    sb.append("    lingua: ").append(toIndentedString(lingua)).append("\n");
    sb.append("    letto: ").append(toIndentedString(letto)).append("\n");
    sb.append("    scritto: ").append(toIndentedString(scritto)).append("\n");
    sb.append("    parlato: ").append(toIndentedString(parlato)).append("\n");
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

