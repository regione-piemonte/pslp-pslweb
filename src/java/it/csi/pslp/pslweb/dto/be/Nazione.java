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

public class Nazione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceMinisteriale = null;
  private String sigla = null;
  private String descrizione = null;
  private Boolean flagUe = null;

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
   **/
  

  @JsonProperty("sigla") 
 
  public String getSigla() {
    return sigla;
  }
  public void setSigla(String sigla) {
    this.sigla = sigla;
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
   * true se la nazione e&#39; europea
   **/
  

  @JsonProperty("flag_ue") 
 
  public Boolean isFlagUe() {
    return flagUe;
  }
  public void setFlagUe(Boolean flagUe) {
    this.flagUe = flagUe;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Nazione nazione = (Nazione) o;
    return Objects.equals(codiceMinisteriale, nazione.codiceMinisteriale) &&
        Objects.equals(sigla, nazione.sigla) &&
        Objects.equals(descrizione, nazione.descrizione) &&
        Objects.equals(flagUe, nazione.flagUe);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceMinisteriale, sigla, descrizione, flagUe);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Nazione {\n");
    
    sb.append("    codiceMinisteriale: ").append(toIndentedString(codiceMinisteriale)).append("\n");
    sb.append("    sigla: ").append(toIndentedString(sigla)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    flagUe: ").append(toIndentedString(flagUe)).append("\n");
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

