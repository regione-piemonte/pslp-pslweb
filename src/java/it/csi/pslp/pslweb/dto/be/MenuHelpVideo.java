/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.MenuHelpFile;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class MenuHelpVideo   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String titolo = null;
  private String codice = null;
  private List<MenuHelpFile> contenuto = new ArrayList<MenuHelpFile>();

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
  

  @JsonProperty("codice") 
 
  public String getCodice() {
    return codice;
  }
  public void setCodice(String codice) {
    this.codice = codice;
  }

  /**
   **/
  

  @JsonProperty("contenuto") 
 
  public List<MenuHelpFile> getContenuto() {
    return contenuto;
  }
  public void setContenuto(List<MenuHelpFile> contenuto) {
    this.contenuto = contenuto;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuHelpVideo menuHelpVideo = (MenuHelpVideo) o;
    return Objects.equals(titolo, menuHelpVideo.titolo) &&
        Objects.equals(codice, menuHelpVideo.codice) &&
        Objects.equals(contenuto, menuHelpVideo.contenuto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(titolo, codice, contenuto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenuHelpVideo {\n");
    
    sb.append("    titolo: ").append(toIndentedString(titolo)).append("\n");
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    contenuto: ").append(toIndentedString(contenuto)).append("\n");
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

