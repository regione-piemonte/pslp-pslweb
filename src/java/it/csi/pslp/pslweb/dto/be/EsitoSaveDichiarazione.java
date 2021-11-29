/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.DettaglioCompletoDichiarazioneFamiliariACarico;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class EsitoSaveDichiarazione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String messaggioErrore = null;
  private String codiceFiscaleOperatore = null;
  private String codiceFiscale = null;
  private DettaglioCompletoDichiarazioneFamiliariACarico dichiarazione = null;

  /**
   **/
  

  @JsonProperty("messaggio_errore") 
 
  public String getMessaggioErrore() {
    return messaggioErrore;
  }
  public void setMessaggioErrore(String messaggioErrore) {
    this.messaggioErrore = messaggioErrore;
  }

  /**
   **/
  

  @JsonProperty("codiceFiscaleOperatore") 
 
  public String getCodiceFiscaleOperatore() {
    return codiceFiscaleOperatore;
  }
  public void setCodiceFiscaleOperatore(String codiceFiscaleOperatore) {
    this.codiceFiscaleOperatore = codiceFiscaleOperatore;
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
  

  @JsonProperty("dichiarazione") 
 
  public DettaglioCompletoDichiarazioneFamiliariACarico getDichiarazione() {
    return dichiarazione;
  }
  public void setDichiarazione(DettaglioCompletoDichiarazioneFamiliariACarico dichiarazione) {
    this.dichiarazione = dichiarazione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoSaveDichiarazione esitoSaveDichiarazione = (EsitoSaveDichiarazione) o;
    return Objects.equals(messaggioErrore, esitoSaveDichiarazione.messaggioErrore) &&
        Objects.equals(codiceFiscaleOperatore, esitoSaveDichiarazione.codiceFiscaleOperatore) &&
        Objects.equals(codiceFiscale, esitoSaveDichiarazione.codiceFiscale) &&
        Objects.equals(dichiarazione, esitoSaveDichiarazione.dichiarazione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messaggioErrore, codiceFiscaleOperatore, codiceFiscale, dichiarazione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoSaveDichiarazione {\n");
    
    sb.append("    messaggioErrore: ").append(toIndentedString(messaggioErrore)).append("\n");
    sb.append("    codiceFiscaleOperatore: ").append(toIndentedString(codiceFiscaleOperatore)).append("\n");
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    dichiarazione: ").append(toIndentedString(dichiarazione)).append("\n");
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

