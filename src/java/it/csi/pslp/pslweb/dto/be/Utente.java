/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.UtentePresaVisione;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Utente   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idUtente = null;
  private Long idSilLavAnagrafica = null;
  private String identificativoSap = null;
  private String codiceTipoUtente = null;
  private String codiceFiscale = null;
  private String nome = null;
  private String cognome = null;
  private String email = null;
  private List<UtentePresaVisione> preseVisione = new ArrayList<UtentePresaVisione>();

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
   * id lavoratore su SILP
   **/
  

  @JsonProperty("id_sil_lav_anagrafica") 
 
  public Long getIdSilLavAnagrafica() {
    return idSilLavAnagrafica;
  }
  public void setIdSilLavAnagrafica(Long idSilLavAnagrafica) {
    this.idSilLavAnagrafica = idSilLavAnagrafica;
  }

  /**
   **/
  

  @JsonProperty("identificativoSap") 
 
  public String getIdentificativoSap() {
    return identificativoSap;
  }
  public void setIdentificativoSap(String identificativoSap) {
    this.identificativoSap = identificativoSap;
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

  /**
   **/
  

  @JsonProperty("prese_visione") 
 
  public List<UtentePresaVisione> getPreseVisione() {
    return preseVisione;
  }
  public void setPreseVisione(List<UtentePresaVisione> preseVisione) {
    this.preseVisione = preseVisione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Utente utente = (Utente) o;
    return Objects.equals(idUtente, utente.idUtente) &&
        Objects.equals(idSilLavAnagrafica, utente.idSilLavAnagrafica) &&
        Objects.equals(identificativoSap, utente.identificativoSap) &&
        Objects.equals(codiceTipoUtente, utente.codiceTipoUtente) &&
        Objects.equals(codiceFiscale, utente.codiceFiscale) &&
        Objects.equals(nome, utente.nome) &&
        Objects.equals(cognome, utente.cognome) &&
        Objects.equals(email, utente.email) &&
        Objects.equals(preseVisione, utente.preseVisione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idUtente, idSilLavAnagrafica, identificativoSap, codiceTipoUtente, codiceFiscale, nome, cognome, email, preseVisione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Utente {\n");
    
    sb.append("    idUtente: ").append(toIndentedString(idUtente)).append("\n");
    sb.append("    idSilLavAnagrafica: ").append(toIndentedString(idSilLavAnagrafica)).append("\n");
    sb.append("    identificativoSap: ").append(toIndentedString(identificativoSap)).append("\n");
    sb.append("    codiceTipoUtente: ").append(toIndentedString(codiceTipoUtente)).append("\n");
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    preseVisione: ").append(toIndentedString(preseVisione)).append("\n");
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

