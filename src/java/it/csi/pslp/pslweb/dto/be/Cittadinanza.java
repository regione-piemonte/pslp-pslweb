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

public class Cittadinanza   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceMinisteriale = null;
  private String sigla = null;
  private String descrizione = null;
  private String codiceMinisterialeNazione = null;

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
   * codice ministeriale nazione
   **/
  

  @JsonProperty("codice_ministeriale_nazione") 
 
  public String getCodiceMinisterialeNazione() {
    return codiceMinisterialeNazione;
  }
  public void setCodiceMinisterialeNazione(String codiceMinisterialeNazione) {
    this.codiceMinisterialeNazione = codiceMinisterialeNazione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cittadinanza cittadinanza = (Cittadinanza) o;
    return Objects.equals(codiceMinisteriale, cittadinanza.codiceMinisteriale) &&
        Objects.equals(sigla, cittadinanza.sigla) &&
        Objects.equals(descrizione, cittadinanza.descrizione) &&
        Objects.equals(codiceMinisterialeNazione, cittadinanza.codiceMinisterialeNazione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceMinisteriale, sigla, descrizione, codiceMinisterialeNazione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cittadinanza {\n");
    
    sb.append("    codiceMinisteriale: ").append(toIndentedString(codiceMinisteriale)).append("\n");
    sb.append("    sigla: ").append(toIndentedString(sigla)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    codiceMinisterialeNazione: ").append(toIndentedString(codiceMinisterialeNazione)).append("\n");
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

