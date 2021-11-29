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

public class CalendarioHeader   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idCalendario = null;
  private String descrizioneAmbito = null;
  private String nome = null;
  private String descrizioneEnte = null;
  private String giorniAttivi = null;
  private Boolean visibileInBase = null;
  private Boolean prommoria = null;
  private Boolean eliminato = null;

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
   **/
  

  @JsonProperty("visibile_in_base") 
 
  public Boolean isVisibileInBase() {
    return visibileInBase;
  }
  public void setVisibileInBase(Boolean visibileInBase) {
    this.visibileInBase = visibileInBase;
  }

  /**
   **/
  

  @JsonProperty("prommoria") 
 
  public Boolean isPrommoria() {
    return prommoria;
  }
  public void setPrommoria(Boolean prommoria) {
    this.prommoria = prommoria;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CalendarioHeader calendarioHeader = (CalendarioHeader) o;
    return Objects.equals(idCalendario, calendarioHeader.idCalendario) &&
        Objects.equals(descrizioneAmbito, calendarioHeader.descrizioneAmbito) &&
        Objects.equals(nome, calendarioHeader.nome) &&
        Objects.equals(descrizioneEnte, calendarioHeader.descrizioneEnte) &&
        Objects.equals(giorniAttivi, calendarioHeader.giorniAttivi) &&
        Objects.equals(visibileInBase, calendarioHeader.visibileInBase) &&
        Objects.equals(prommoria, calendarioHeader.prommoria) &&
        Objects.equals(eliminato, calendarioHeader.eliminato);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCalendario, descrizioneAmbito, nome, descrizioneEnte, giorniAttivi, visibileInBase, prommoria, eliminato);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CalendarioHeader {\n");
    
    sb.append("    idCalendario: ").append(toIndentedString(idCalendario)).append("\n");
    sb.append("    descrizioneAmbito: ").append(toIndentedString(descrizioneAmbito)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    descrizioneEnte: ").append(toIndentedString(descrizioneEnte)).append("\n");
    sb.append("    giorniAttivi: ").append(toIndentedString(giorniAttivi)).append("\n");
    sb.append("    visibileInBase: ").append(toIndentedString(visibileInBase)).append("\n");
    sb.append("    prommoria: ").append(toIndentedString(prommoria)).append("\n");
    sb.append("    eliminato: ").append(toIndentedString(eliminato)).append("\n");
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

