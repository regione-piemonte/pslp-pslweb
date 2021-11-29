/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Indirizzo;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Recapito   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String telefono = null;
  private String cellulare = null;
  private String fax = null;
  private String email = null;
  private String web = null;
  private Indirizzo indirizzo = null;

  /**
   **/
  

  @JsonProperty("telefono") 
 
  public String getTelefono() {
    return telefono;
  }
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  /**
   **/
  

  @JsonProperty("cellulare") 
 
  public String getCellulare() {
    return cellulare;
  }
  public void setCellulare(String cellulare) {
    this.cellulare = cellulare;
  }

  /**
   **/
  

  @JsonProperty("fax") 
 
  public String getFax() {
    return fax;
  }
  public void setFax(String fax) {
    this.fax = fax;
  }

  /**
   **/
  

  @JsonProperty("email") 
 
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   **/
  

  @JsonProperty("web") 
 
  public String getWeb() {
    return web;
  }
  public void setWeb(String web) {
    this.web = web;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Recapito recapito = (Recapito) o;
    return Objects.equals(telefono, recapito.telefono) &&
        Objects.equals(cellulare, recapito.cellulare) &&
        Objects.equals(fax, recapito.fax) &&
        Objects.equals(email, recapito.email) &&
        Objects.equals(web, recapito.web) &&
        Objects.equals(indirizzo, recapito.indirizzo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(telefono, cellulare, fax, email, web, indirizzo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Recapito {\n");
    
    sb.append("    telefono: ").append(toIndentedString(telefono)).append("\n");
    sb.append("    cellulare: ").append(toIndentedString(cellulare)).append("\n");
    sb.append("    fax: ").append(toIndentedString(fax)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    web: ").append(toIndentedString(web)).append("\n");
    sb.append("    indirizzo: ").append(toIndentedString(indirizzo)).append("\n");
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

