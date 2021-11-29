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

public class MenuHomeCard   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String idCard = null;
  private String titolo = null;
  private String testo = null;
  private String urlImg = null;
  private String icon = null;
  private String link = null;
  private String tipoLink = null;
  private String codApp = null;
  private Boolean flgAccessoAutenticato = null;

  /**
   **/
  

  @JsonProperty("id_card") 
 
  public String getIdCard() {
    return idCard;
  }
  public void setIdCard(String idCard) {
    this.idCard = idCard;
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
  

  @JsonProperty("testo") 
 
  public String getTesto() {
    return testo;
  }
  public void setTesto(String testo) {
    this.testo = testo;
  }

  /**
   **/
  

  @JsonProperty("url_img") 
 
  public String getUrlImg() {
    return urlImg;
  }
  public void setUrlImg(String urlImg) {
    this.urlImg = urlImg;
  }

  /**
   **/
  

  @JsonProperty("icon") 
 
  public String getIcon() {
    return icon;
  }
  public void setIcon(String icon) {
    this.icon = icon;
  }

  /**
   **/
  

  @JsonProperty("link") 
 
  public String getLink() {
    return link;
  }
  public void setLink(String link) {
    this.link = link;
  }

  /**
   **/
  

  @JsonProperty("tipo_link") 
 
  public String getTipoLink() {
    return tipoLink;
  }
  public void setTipoLink(String tipoLink) {
    this.tipoLink = tipoLink;
  }

  /**
   **/
  

  @JsonProperty("cod_app") 
 
  public String getCodApp() {
    return codApp;
  }
  public void setCodApp(String codApp) {
    this.codApp = codApp;
  }

  /**
   **/
  

  @JsonProperty("flg_accesso_autenticato") 
 
  public Boolean isFlgAccessoAutenticato() {
    return flgAccessoAutenticato;
  }
  public void setFlgAccessoAutenticato(Boolean flgAccessoAutenticato) {
    this.flgAccessoAutenticato = flgAccessoAutenticato;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuHomeCard menuHomeCard = (MenuHomeCard) o;
    return Objects.equals(idCard, menuHomeCard.idCard) &&
        Objects.equals(titolo, menuHomeCard.titolo) &&
        Objects.equals(testo, menuHomeCard.testo) &&
        Objects.equals(urlImg, menuHomeCard.urlImg) &&
        Objects.equals(icon, menuHomeCard.icon) &&
        Objects.equals(link, menuHomeCard.link) &&
        Objects.equals(tipoLink, menuHomeCard.tipoLink) &&
        Objects.equals(codApp, menuHomeCard.codApp) &&
        Objects.equals(flgAccessoAutenticato, menuHomeCard.flgAccessoAutenticato);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCard, titolo, testo, urlImg, icon, link, tipoLink, codApp, flgAccessoAutenticato);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenuHomeCard {\n");
    
    sb.append("    idCard: ").append(toIndentedString(idCard)).append("\n");
    sb.append("    titolo: ").append(toIndentedString(titolo)).append("\n");
    sb.append("    testo: ").append(toIndentedString(testo)).append("\n");
    sb.append("    urlImg: ").append(toIndentedString(urlImg)).append("\n");
    sb.append("    icon: ").append(toIndentedString(icon)).append("\n");
    sb.append("    link: ").append(toIndentedString(link)).append("\n");
    sb.append("    tipoLink: ").append(toIndentedString(tipoLink)).append("\n");
    sb.append("    codApp: ").append(toIndentedString(codApp)).append("\n");
    sb.append("    flgAccessoAutenticato: ").append(toIndentedString(flgAccessoAutenticato)).append("\n");
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

