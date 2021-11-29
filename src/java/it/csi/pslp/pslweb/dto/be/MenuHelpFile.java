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

public class MenuHelpFile   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String fileTitolo = null;
  private String fileUrl = null;
  private String fileCod = null;

  /**
   **/
  

  @JsonProperty("file_titolo") 
 
  public String getFileTitolo() {
    return fileTitolo;
  }
  public void setFileTitolo(String fileTitolo) {
    this.fileTitolo = fileTitolo;
  }

  /**
   **/
  

  @JsonProperty("file_url") 
 
  public String getFileUrl() {
    return fileUrl;
  }
  public void setFileUrl(String fileUrl) {
    this.fileUrl = fileUrl;
  }

  /**
   **/
  

  @JsonProperty("file_cod") 
 
  public String getFileCod() {
    return fileCod;
  }
  public void setFileCod(String fileCod) {
    this.fileCod = fileCod;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuHelpFile menuHelpFile = (MenuHelpFile) o;
    return Objects.equals(fileTitolo, menuHelpFile.fileTitolo) &&
        Objects.equals(fileUrl, menuHelpFile.fileUrl) &&
        Objects.equals(fileCod, menuHelpFile.fileCod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileTitolo, fileUrl, fileCod);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenuHelpFile {\n");
    
    sb.append("    fileTitolo: ").append(toIndentedString(fileTitolo)).append("\n");
    sb.append("    fileUrl: ").append(toIndentedString(fileUrl)).append("\n");
    sb.append("    fileCod: ").append(toIndentedString(fileCod)).append("\n");
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

