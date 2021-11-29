/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCalendarioDatiOperativi;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCalendarioMail;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCalendarioPeriodoValidita;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ConfigurazioneCalendario   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idCalendario = null;
  private String nome = null;
  private String codiceAmbito = null;
  private String gruppoOperatore = null;
  private Long codiceOperatore = null;
  private Long subcodice = null;
  private ConfigurazioneCalendarioDatiOperativi datiOperativi = null;
  private List<ConfigurazioneCalendarioPeriodoValidita> periodiValidita = new ArrayList<ConfigurazioneCalendarioPeriodoValidita>();
  private ConfigurazioneCalendarioMail mail = null;

  /**
   * identificativo univoco
   **/
  

  @JsonProperty("id_calendario") 
 
  public Long getIdCalendario() {
    return idCalendario;
  }
  public void setIdCalendario(Long idCalendario) {
    this.idCalendario = idCalendario;
  }

  /**
   * nome calendario
   **/
  

  @JsonProperty("nome") 
 
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * codice ambito
   **/
  

  @JsonProperty("codice_ambito") 
 
  public String getCodiceAmbito() {
    return codiceAmbito;
  }
  public void setCodiceAmbito(String codiceAmbito) {
    this.codiceAmbito = codiceAmbito;
  }

  /**
   **/
  

  @JsonProperty("gruppo_operatore") 
 
  public String getGruppoOperatore() {
    return gruppoOperatore;
  }
  public void setGruppoOperatore(String gruppoOperatore) {
    this.gruppoOperatore = gruppoOperatore;
  }

  /**
   **/
  

  @JsonProperty("codice_operatore") 
 
  public Long getCodiceOperatore() {
    return codiceOperatore;
  }
  public void setCodiceOperatore(Long codiceOperatore) {
    this.codiceOperatore = codiceOperatore;
  }

  /**
   **/
  

  @JsonProperty("subcodice") 
 
  public Long getSubcodice() {
    return subcodice;
  }
  public void setSubcodice(Long subcodice) {
    this.subcodice = subcodice;
  }

  /**
   **/
  

  @JsonProperty("dati_operativi") 
 
  public ConfigurazioneCalendarioDatiOperativi getDatiOperativi() {
    return datiOperativi;
  }
  public void setDatiOperativi(ConfigurazioneCalendarioDatiOperativi datiOperativi) {
    this.datiOperativi = datiOperativi;
  }

  /**
   **/
  

  @JsonProperty("periodi_validita") 
 
  public List<ConfigurazioneCalendarioPeriodoValidita> getPeriodiValidita() {
    return periodiValidita;
  }
  public void setPeriodiValidita(List<ConfigurazioneCalendarioPeriodoValidita> periodiValidita) {
    this.periodiValidita = periodiValidita;
  }

  /**
   **/
  

  @JsonProperty("mail") 
 
  public ConfigurazioneCalendarioMail getMail() {
    return mail;
  }
  public void setMail(ConfigurazioneCalendarioMail mail) {
    this.mail = mail;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneCalendario configurazioneCalendario = (ConfigurazioneCalendario) o;
    return Objects.equals(idCalendario, configurazioneCalendario.idCalendario) &&
        Objects.equals(nome, configurazioneCalendario.nome) &&
        Objects.equals(codiceAmbito, configurazioneCalendario.codiceAmbito) &&
        Objects.equals(gruppoOperatore, configurazioneCalendario.gruppoOperatore) &&
        Objects.equals(codiceOperatore, configurazioneCalendario.codiceOperatore) &&
        Objects.equals(subcodice, configurazioneCalendario.subcodice) &&
        Objects.equals(datiOperativi, configurazioneCalendario.datiOperativi) &&
        Objects.equals(periodiValidita, configurazioneCalendario.periodiValidita) &&
        Objects.equals(mail, configurazioneCalendario.mail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCalendario, nome, codiceAmbito, gruppoOperatore, codiceOperatore, subcodice, datiOperativi, periodiValidita, mail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneCalendario {\n");
    
    sb.append("    idCalendario: ").append(toIndentedString(idCalendario)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    codiceAmbito: ").append(toIndentedString(codiceAmbito)).append("\n");
    sb.append("    gruppoOperatore: ").append(toIndentedString(gruppoOperatore)).append("\n");
    sb.append("    codiceOperatore: ").append(toIndentedString(codiceOperatore)).append("\n");
    sb.append("    subcodice: ").append(toIndentedString(subcodice)).append("\n");
    sb.append("    datiOperativi: ").append(toIndentedString(datiOperativi)).append("\n");
    sb.append("    periodiValidita: ").append(toIndentedString(periodiValidita)).append("\n");
    sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
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

