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

public class ConfigurazioneCalendarioHeader   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idCalendario = null;
  private String descrizioneAmbito = null;
  private String nome = null;
  private String descrizioneEnte = null;
  private String giorniAttivi = null;
  private String periodo = null;
  private String visibileInBase = null;
  private Boolean promemoria = null;
  private Boolean eliminato = null;
  private Boolean bloccato = null;
  private Integer oreLimiteSpostamento = null;
  private Boolean annullaGaranziaGiovani = null;
  private String messaggioAnnullamentoAppuntamento = null;
  private String messaggioSpostamentoAppuntamento = null;
  private Integer numeroSlotLiberi = null;
  private Integer numeroSlotPrenotabili = null;

  /**
   * identificativo in PSLP_T_CALENDARIO
   **/
  

  @JsonProperty("id_calendario") 
 
  public Long getIdCalendario() {
    return idCalendario;
  }
  public void setIdCalendario(Long idCalendario) {
    this.idCalendario = idCalendario;
  }

  /**
   * ambito di appartenenza
   **/
  

  @JsonProperty("descrizione_ambito") 
 
  public String getDescrizioneAmbito() {
    return descrizioneAmbito;
  }
  public void setDescrizioneAmbito(String descrizioneAmbito) {
    this.descrizioneAmbito = descrizioneAmbito;
  }

  /**
   * nome del calendario
   **/
  

  @JsonProperty("nome") 
 
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * descrizione del cpi di appartenenza del calendario
   **/
  

  @JsonProperty("descrizione_ente") 
 
  public String getDescrizioneEnte() {
    return descrizioneEnte;
  }
  public void setDescrizioneEnte(String descrizioneEnte) {
    this.descrizioneEnte = descrizioneEnte;
  }

  /**
   * sequenza di giorni ddd
   **/
  

  @JsonProperty("giorni_attivi") 
 
  public String getGiorniAttivi() {
    return giorniAttivi;
  }
  public void setGiorniAttivi(String giorniAttivi) {
    this.giorniAttivi = giorniAttivi;
  }

  /**
   * da-a
   **/
  

  @JsonProperty("periodo") 
 
  public String getPeriodo() {
    return periodo;
  }
  public void setPeriodo(String periodo) {
    this.periodo = periodo;
  }

  /**
   **/
  

  @JsonProperty("visibile_in_base") 
 
  public String getVisibileInBase() {
    return visibileInBase;
  }
  public void setVisibileInBase(String visibileInBase) {
    this.visibileInBase = visibileInBase;
  }

  /**
   **/
  

  @JsonProperty("promemoria") 
 
  public Boolean isPromemoria() {
    return promemoria;
  }
  public void setPromemoria(Boolean promemoria) {
    this.promemoria = promemoria;
  }

  /**
   **/
  

  @JsonProperty("eliminato") 
 
  public Boolean isEliminato() {
    return eliminato;
  }
  public void setEliminato(Boolean eliminato) {
    this.eliminato = eliminato;
  }

  /**
   **/
  

  @JsonProperty("bloccato") 
 
  public Boolean isBloccato() {
    return bloccato;
  }
  public void setBloccato(Boolean bloccato) {
    this.bloccato = bloccato;
  }

  /**
   **/
  

  @JsonProperty("ore_limite_spostamento") 
 
  public Integer getOreLimiteSpostamento() {
    return oreLimiteSpostamento;
  }
  public void setOreLimiteSpostamento(Integer oreLimiteSpostamento) {
    this.oreLimiteSpostamento = oreLimiteSpostamento;
  }

  /**
   **/
  

  @JsonProperty("annulla_garanzia_giovani") 
 
  public Boolean isAnnullaGaranziaGiovani() {
    return annullaGaranziaGiovani;
  }
  public void setAnnullaGaranziaGiovani(Boolean annullaGaranziaGiovani) {
    this.annullaGaranziaGiovani = annullaGaranziaGiovani;
  }

  /**
   **/
  

  @JsonProperty("messaggio_annullamento_appuntamento") 
 
  public String getMessaggioAnnullamentoAppuntamento() {
    return messaggioAnnullamentoAppuntamento;
  }
  public void setMessaggioAnnullamentoAppuntamento(String messaggioAnnullamentoAppuntamento) {
    this.messaggioAnnullamentoAppuntamento = messaggioAnnullamentoAppuntamento;
  }

  /**
   **/
  

  @JsonProperty("messaggio_spostamento_appuntamento") 
 
  public String getMessaggioSpostamentoAppuntamento() {
    return messaggioSpostamentoAppuntamento;
  }
  public void setMessaggioSpostamentoAppuntamento(String messaggioSpostamentoAppuntamento) {
    this.messaggioSpostamentoAppuntamento = messaggioSpostamentoAppuntamento;
  }

  /**
   **/
  

  @JsonProperty("numero_slot_liberi") 
 
  public Integer getNumeroSlotLiberi() {
    return numeroSlotLiberi;
  }
  public void setNumeroSlotLiberi(Integer numeroSlotLiberi) {
    this.numeroSlotLiberi = numeroSlotLiberi;
  }

  /**
   **/
  

  @JsonProperty("numero_slot_prenotabili") 
 
  public Integer getNumeroSlotPrenotabili() {
    return numeroSlotPrenotabili;
  }
  public void setNumeroSlotPrenotabili(Integer numeroSlotPrenotabili) {
    this.numeroSlotPrenotabili = numeroSlotPrenotabili;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneCalendarioHeader configurazioneCalendarioHeader = (ConfigurazioneCalendarioHeader) o;
    return Objects.equals(idCalendario, configurazioneCalendarioHeader.idCalendario) &&
        Objects.equals(descrizioneAmbito, configurazioneCalendarioHeader.descrizioneAmbito) &&
        Objects.equals(nome, configurazioneCalendarioHeader.nome) &&
        Objects.equals(descrizioneEnte, configurazioneCalendarioHeader.descrizioneEnte) &&
        Objects.equals(giorniAttivi, configurazioneCalendarioHeader.giorniAttivi) &&
        Objects.equals(periodo, configurazioneCalendarioHeader.periodo) &&
        Objects.equals(visibileInBase, configurazioneCalendarioHeader.visibileInBase) &&
        Objects.equals(promemoria, configurazioneCalendarioHeader.promemoria) &&
        Objects.equals(eliminato, configurazioneCalendarioHeader.eliminato) &&
        Objects.equals(bloccato, configurazioneCalendarioHeader.bloccato) &&
        Objects.equals(oreLimiteSpostamento, configurazioneCalendarioHeader.oreLimiteSpostamento) &&
        Objects.equals(annullaGaranziaGiovani, configurazioneCalendarioHeader.annullaGaranziaGiovani) &&
        Objects.equals(messaggioAnnullamentoAppuntamento, configurazioneCalendarioHeader.messaggioAnnullamentoAppuntamento) &&
        Objects.equals(messaggioSpostamentoAppuntamento, configurazioneCalendarioHeader.messaggioSpostamentoAppuntamento) &&
        Objects.equals(numeroSlotLiberi, configurazioneCalendarioHeader.numeroSlotLiberi) &&
        Objects.equals(numeroSlotPrenotabili, configurazioneCalendarioHeader.numeroSlotPrenotabili);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCalendario, descrizioneAmbito, nome, descrizioneEnte, giorniAttivi, periodo, visibileInBase, promemoria, eliminato, bloccato, oreLimiteSpostamento, annullaGaranziaGiovani, messaggioAnnullamentoAppuntamento, messaggioSpostamentoAppuntamento, numeroSlotLiberi, numeroSlotPrenotabili);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneCalendarioHeader {\n");
    
    sb.append("    idCalendario: ").append(toIndentedString(idCalendario)).append("\n");
    sb.append("    descrizioneAmbito: ").append(toIndentedString(descrizioneAmbito)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    descrizioneEnte: ").append(toIndentedString(descrizioneEnte)).append("\n");
    sb.append("    giorniAttivi: ").append(toIndentedString(giorniAttivi)).append("\n");
    sb.append("    periodo: ").append(toIndentedString(periodo)).append("\n");
    sb.append("    visibileInBase: ").append(toIndentedString(visibileInBase)).append("\n");
    sb.append("    promemoria: ").append(toIndentedString(promemoria)).append("\n");
    sb.append("    eliminato: ").append(toIndentedString(eliminato)).append("\n");
    sb.append("    bloccato: ").append(toIndentedString(bloccato)).append("\n");
    sb.append("    oreLimiteSpostamento: ").append(toIndentedString(oreLimiteSpostamento)).append("\n");
    sb.append("    annullaGaranziaGiovani: ").append(toIndentedString(annullaGaranziaGiovani)).append("\n");
    sb.append("    messaggioAnnullamentoAppuntamento: ").append(toIndentedString(messaggioAnnullamentoAppuntamento)).append("\n");
    sb.append("    messaggioSpostamentoAppuntamento: ").append(toIndentedString(messaggioSpostamentoAppuntamento)).append("\n");
    sb.append("    numeroSlotLiberi: ").append(toIndentedString(numeroSlotLiberi)).append("\n");
    sb.append("    numeroSlotPrenotabili: ").append(toIndentedString(numeroSlotPrenotabili)).append("\n");
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

