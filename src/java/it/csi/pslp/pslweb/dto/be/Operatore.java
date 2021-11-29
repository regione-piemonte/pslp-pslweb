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

public class Operatore   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idUtente = null;
  private String codiceTipoUtente = null;
  private String codiceFiscale = null;
  private String nome = null;
  private String cognome = null;
  private String email = null;

  /**
   * id utente su PSL
   **/
  

  @JsonProperty("id_utente") 
 
  public Long getIdUtente() {
    return idUtente;
  }
  public void setIdUtente(Long idUtente) {
    this.idUtente = idUtente;
  }

  /**
   * codice tipo utente definito nella tabella PSLP_D_TIPO_UTENTE (APL, CIT, CPI)
   **/
  

  @JsonProperty("codice_tipo_utente") 
 
  public String getCodiceTipoUtente() {
    return codiceTipoUtente;
  }
  public void setCodiceTipoUtente(String codiceTipoUtente) {
    this.codiceTipoUtente = codiceTipoUtente;
  }

  /**
   * codice fiscale utente
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
   **/
  

  @JsonProperty("email") 
 
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Operatore operatore = (Operatore) o;
    return Objects.equals(idUtente, operatore.idUtente) &&
        Objects.equals(codiceTipoUtente, operatore.codiceTipoUtente) &&
        Objects.equals(codiceFiscale, operatore.codiceFiscale) &&
        Objects.equals(nome, operatore.nome) &&
        Objects.equals(cognome, operatore.cognome) &&
        Objects.equals(email, operatore.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idUtente, codiceTipoUtente, codiceFiscale, nome, cognome, email);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Operatore {\n");
    
    sb.append("    idUtente: ").append(toIndentedString(idUtente)).append("\n");
    sb.append("    codiceTipoUtente: ").append(toIndentedString(codiceTipoUtente)).append("\n");
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
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

