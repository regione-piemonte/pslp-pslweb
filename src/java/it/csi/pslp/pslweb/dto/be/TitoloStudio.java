/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.GradoStudio;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class TitoloStudio   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codice = null;
  private String codiceSilp = null;
  private String descrizione = null;
  private String descrizioneTipoScuola = null;
  private String sinonimo = null;
  private GradoStudio gradoStudio = null;

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
  

  @JsonProperty("descrizioneTipoScuola") 
 
  public String getDescrizioneTipoScuola() {
    return descrizioneTipoScuola;
  }
  public void setDescrizioneTipoScuola(String descrizioneTipoScuola) {
    this.descrizioneTipoScuola = descrizioneTipoScuola;
  }

  /**
   **/
  

  @JsonProperty("sinonimo") 
 
  public String getSinonimo() {
    return sinonimo;
  }
  public void setSinonimo(String sinonimo) {
    this.sinonimo = sinonimo;
  }

  /**
   **/
  

  @JsonProperty("gradoStudio") 
 
  public GradoStudio getGradoStudio() {
    return gradoStudio;
  }
  public void setGradoStudio(GradoStudio gradoStudio) {
    this.gradoStudio = gradoStudio;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TitoloStudio titoloStudio = (TitoloStudio) o;
    return Objects.equals(codice, titoloStudio.codice) &&
        Objects.equals(codiceSilp, titoloStudio.codiceSilp) &&
        Objects.equals(descrizione, titoloStudio.descrizione) &&
        Objects.equals(descrizioneTipoScuola, titoloStudio.descrizioneTipoScuola) &&
        Objects.equals(sinonimo, titoloStudio.sinonimo) &&
        Objects.equals(gradoStudio, titoloStudio.gradoStudio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codice, codiceSilp, descrizione, descrizioneTipoScuola, sinonimo, gradoStudio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TitoloStudio {\n");
    
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    codiceSilp: ").append(toIndentedString(codiceSilp)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    descrizioneTipoScuola: ").append(toIndentedString(descrizioneTipoScuola)).append("\n");
    sb.append("    sinonimo: ").append(toIndentedString(sinonimo)).append("\n");
    sb.append("    gradoStudio: ").append(toIndentedString(gradoStudio)).append("\n");
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

