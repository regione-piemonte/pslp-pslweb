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

public class Lavoratore   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer idLavoratore = null;
  private String codiceFiscale = null;
  private String nome = null;
  private String cognome = null;
  private Boolean presaVisioneGenitore = null;
  private Boolean presaVisionePrivacy = null;
  private Boolean presaVisionePrivacyRdc = null;

  /**
   * id lavoratore su PSL
   **/
  

  @JsonProperty("id_lavoratore") 
 
  public Integer getIdLavoratore() {
    return idLavoratore;
  }
  public void setIdLavoratore(Integer idLavoratore) {
    this.idLavoratore = idLavoratore;
  }

  /**
   * codice fiscale lavoratore
   **/
  

  @JsonProperty("codice_fiscale") 
 
  public String getCodiceFiscale() {
    return codiceFiscale;
  }
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   **/
  

  @JsonProperty("nome") 
 
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   **/
  

  @JsonProperty("cognome") 
 
  public String getCognome() {
    return cognome;
  }
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * vero se l&#39;utente ha preso visione e dichiarato di essere genitore di altri lavoratori
   **/
  

  @JsonProperty("presa_visione_genitore") 
 
  public Boolean isPresaVisioneGenitore() {
    return presaVisioneGenitore;
  }
  public void setPresaVisioneGenitore(Boolean presaVisioneGenitore) {
    this.presaVisioneGenitore = presaVisioneGenitore;
  }

  /**
   * vero se l&#39;utente ha accettato la privacy al primo ingresso
   **/
  

  @JsonProperty("presa_visione_privacy") 
  public Boolean isPresaVisionePrivacy() {
    return presaVisionePrivacy;
  }
  public void setPresaVisionePrivacy(Boolean presaVisionePrivacy) {
    this.presaVisionePrivacy = presaVisionePrivacy;
  }

  @JsonProperty("presa_visione_privacy_rdc") 
  public Boolean isPresaVisionePrivacyRdc() {
    return presaVisionePrivacyRdc;
  }
  public void setPresaVisionePrivacyRdc(Boolean presaVisionePrivacyRdc) {
    this.presaVisionePrivacyRdc = presaVisionePrivacyRdc;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Lavoratore lavoratore = (Lavoratore) o;
    return Objects.equals(idLavoratore, lavoratore.idLavoratore) &&
        Objects.equals(codiceFiscale, lavoratore.codiceFiscale) &&
        Objects.equals(nome, lavoratore.nome) &&
        Objects.equals(cognome, lavoratore.cognome) &&
        Objects.equals(presaVisioneGenitore, lavoratore.presaVisioneGenitore) &&
        Objects.equals(presaVisionePrivacy, lavoratore.presaVisionePrivacy) &&
        Objects.equals(presaVisionePrivacyRdc, lavoratore.presaVisionePrivacyRdc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idLavoratore, codiceFiscale, nome, cognome, presaVisioneGenitore, presaVisionePrivacy, presaVisionePrivacyRdc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Lavoratore {\n");
    
    sb.append("    idLavoratore: ").append(toIndentedString(idLavoratore)).append("\n");
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
    sb.append("    presaVisioneGenitore: ").append(toIndentedString(presaVisioneGenitore)).append("\n");
    sb.append("    presaVisionePrivacy: ").append(toIndentedString(presaVisionePrivacy)).append("\n");
    sb.append("    presaVisionePrivacyRdc: ").append(toIndentedString(presaVisionePrivacyRdc)).append("\n");
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

