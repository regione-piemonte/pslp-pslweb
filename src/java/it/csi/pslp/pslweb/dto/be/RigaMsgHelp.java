/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class RigaMsgHelp   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  

  private String codTipoMessaggio = null;
  private String codMessaggio = null;
  private String titolo = null;
  private String intestazione = null;
  private String testoLink = null;

  

  /**
   **/
  

  @JsonProperty("cod_tipo_messaggio") 
 
  public String getCodTipoMessaggio() {
    return codTipoMessaggio;
  }
  public void setCodTipoMessaggio(String codTipoMessaggio) {
    this.codTipoMessaggio = codTipoMessaggio;
  }

  /**
   **/
  
  @JsonProperty("cod_messaggio") 
  
  public String getCodMessaggio() {
    return codMessaggio;
  }
  public void setCodMessaggio(String codMessaggio) {
    this.codMessaggio = codMessaggio;
  }
  
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
  
  @JsonProperty("intestazione") 
  
  public String getIntestazione() {
    return intestazione;
  }
  public void setIntestazione(String intestazione) {
    this.intestazione = intestazione;
  }

  /**
   **/
  
  @JsonProperty("testo_link") 
  
  public String getTestoLink() {
    return testoLink;
  }
  public void setTestoLink(String testoLink) {
    this.testoLink = testoLink;
  }

  /**
   **/  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RigaMsgHelp riga = (RigaMsgHelp) o;
    return Objects.equals(testoLink, riga.testoLink) &&
        Objects.equals(intestazione, riga.intestazione) &&
        Objects.equals(codMessaggio, riga.codMessaggio) &&
        Objects.equals(codTipoMessaggio, riga.codTipoMessaggio) &&
        Objects.equals(titolo, riga.titolo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(testoLink, intestazione, codMessaggio, codTipoMessaggio, titolo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RigaMsgHelp {\n");
    
    sb.append("    testoLink: ").append(toIndentedString(testoLink)).append("\n");
    sb.append("    intestazione: ").append(toIndentedString(intestazione)).append("\n");
    sb.append("    codMessaggio: ").append(toIndentedString(codMessaggio)).append("\n");
    sb.append("    codTipoMessaggio: ").append(toIndentedString(codTipoMessaggio)).append("\n");
    sb.append("    codAmbito: ").append(toIndentedString(titolo)).append("\n");

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

