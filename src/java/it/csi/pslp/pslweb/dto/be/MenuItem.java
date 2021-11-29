/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import it.csi.pslp.pslweb.dto.be.MenuItem;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class MenuItem   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String cod = null;
  private Boolean enabled = null;
  private Boolean visible = null;
  private List<MenuItem> submenu = new ArrayList<MenuItem>();

  /**
   * condice univoco dell&#39;elemento
   **/
  

  @JsonProperty("cod") 
 
  public String getCod() {
    return cod;
  }
  public void setCod(String cod) {
    this.cod = cod;
  }

  /**
   * true se il menu e&#39; abilitato
   **/
  

  @JsonProperty("enabled") 
 
  public Boolean isEnabled() {
    return enabled;
  }
  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * true se il menu e&#39; visibile
   **/
  

  @JsonProperty("visible") 
 
  public Boolean isVisible() {
    return visible;
  }
  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  /**
   **/
  

  @JsonProperty("submenu") 
 
  public List<MenuItem> getSubmenu() {
    return submenu;
  }
  public void setSubmenu(List<MenuItem> submenu) {
    this.submenu = submenu;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuItem menuItem = (MenuItem) o;
    return Objects.equals(cod, menuItem.cod) &&
        Objects.equals(enabled, menuItem.enabled) &&
        Objects.equals(visible, menuItem.visible) &&
        Objects.equals(submenu, menuItem.submenu);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cod, enabled, visible, submenu);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenuItem {\n");
    
    sb.append("    cod: ").append(toIndentedString(cod)).append("\n");
    sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
    sb.append("    visible: ").append(toIndentedString(visible)).append("\n");
    sb.append("    submenu: ").append(toIndentedString(submenu)).append("\n");
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

