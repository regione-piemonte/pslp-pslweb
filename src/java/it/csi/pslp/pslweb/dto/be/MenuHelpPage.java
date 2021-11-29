/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.MenuHelpFile;
import it.csi.pslp.pslweb.dto.be.MenuHelpMsg;
import it.csi.pslp.pslweb.dto.be.MenuHelpVideo;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class MenuHelpPage   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String idPage = null;
  private String titolo = null;
  private List<MenuHelpMsg> messaggi = new ArrayList<MenuHelpMsg>();
  private List<MenuHelpFile> manuali = new ArrayList<MenuHelpFile>();
  private List<MenuHelpVideo> videoTutorial = new ArrayList<MenuHelpVideo>();

  /**
   **/
  

  @JsonProperty("id_page") 
 
  public String getIdPage() {
    return idPage;
  }
  public void setIdPage(String idPage) {
    this.idPage = idPage;
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
  

  @JsonProperty("messaggi") 
 
  public List<MenuHelpMsg> getMessaggi() {
    return messaggi;
  }
  public void setMessaggi(List<MenuHelpMsg> messaggi) {
    this.messaggi = messaggi;
  }

  /**
   **/
  

  @JsonProperty("manuali") 
 
  public List<MenuHelpFile> getManuali() {
    return manuali;
  }
  public void setManuali(List<MenuHelpFile> manuali) {
    this.manuali = manuali;
  }

  /**
   **/
  

  @JsonProperty("video_tutorial") 
 
  public List<MenuHelpVideo> getVideoTutorial() {
    return videoTutorial;
  }
  public void setVideoTutorial(List<MenuHelpVideo> videoTutorial) {
    this.videoTutorial = videoTutorial;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuHelpPage menuHelpPage = (MenuHelpPage) o;
    return Objects.equals(idPage, menuHelpPage.idPage) &&
        Objects.equals(titolo, menuHelpPage.titolo) &&
        Objects.equals(messaggi, menuHelpPage.messaggi) &&
        Objects.equals(manuali, menuHelpPage.manuali) &&
        Objects.equals(videoTutorial, menuHelpPage.videoTutorial);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idPage, titolo, messaggi, manuali, videoTutorial);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenuHelpPage {\n");
    
    sb.append("    idPage: ").append(toIndentedString(idPage)).append("\n");
    sb.append("    titolo: ").append(toIndentedString(titolo)).append("\n");
    sb.append("    messaggi: ").append(toIndentedString(messaggi)).append("\n");
    sb.append("    manuali: ").append(toIndentedString(manuali)).append("\n");
    sb.append("    videoTutorial: ").append(toIndentedString(videoTutorial)).append("\n");
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

