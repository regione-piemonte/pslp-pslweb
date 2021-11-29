/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ParametriRicercaCalendari   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codAmbito = null;
  private String gruppoOperatore = null;
  private Long codOperaratore = null;
  private Long subcodice = null;
  private Date dataDa = null;
  private Date dataA = null;
  private String nomeCalendario = null;
  private Boolean calendarioEliminato = null;
  private String codTipoUtente = null;
  private Long idEccezioneApplicabile = null;

  /**
   **/
  

  @JsonProperty("cod_ambito") 
 
  public String getCodAmbito() {
    return codAmbito;
  }
  public void setCodAmbito(String codAmbito) {
    this.codAmbito = codAmbito;
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
  

  @JsonProperty("cod_operaratore") 
 
  public Long getCodOperaratore() {
    return codOperaratore;
  }
  public void setCodOperaratore(Long codOperaratore) {
    this.codOperaratore = codOperaratore;
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
  

  @JsonProperty("data_da") 
 
  public Date getDataDa() {
    return dataDa;
  }
  public void setDataDa(Date dataDa) {
    this.dataDa = dataDa;
  }

  /**
   **/
  

  @JsonProperty("data_a") 
 
  public Date getDataA() {
    return dataA;
  }
  public void setDataA(Date dataA) {
    this.dataA = dataA;
  }

  /**
   **/
  

  @JsonProperty("nome_calendario") 
 
  public String getNomeCalendario() {
    return nomeCalendario;
  }
  public void setNomeCalendario(String nomeCalendario) {
    this.nomeCalendario = nomeCalendario;
  }

  /**
   **/
  

  @JsonProperty("calendario_eliminato") 
 
  public Boolean isCalendarioEliminato() {
    return calendarioEliminato;
  }
  public void setCalendarioEliminato(Boolean calendarioEliminato) {
    this.calendarioEliminato = calendarioEliminato;
  }

  /**
   * tipologia di utente CIT, APL, CPI, REG
   **/
  

  @JsonProperty("cod_tipo_utente") 
 
  public String getCodTipoUtente() {
    return codTipoUtente;
  }
  public void setCodTipoUtente(String codTipoUtente) {
    this.codTipoUtente = codTipoUtente;
  }

  /**
   * se valorizzato include nel risultato solo i calendari per cui questa eccezione sia valorizzabile
   **/
  

  @JsonProperty("id_eccezione_applicabile") 
 
  public Long getIdEccezioneApplicabile() {
    return idEccezioneApplicabile;
  }
  public void setIdEccezioneApplicabile(Long idEccezioneApplicabile) {
    this.idEccezioneApplicabile = idEccezioneApplicabile;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParametriRicercaCalendari parametriRicercaCalendari = (ParametriRicercaCalendari) o;
    return Objects.equals(codAmbito, parametriRicercaCalendari.codAmbito) &&
        Objects.equals(gruppoOperatore, parametriRicercaCalendari.gruppoOperatore) &&
        Objects.equals(codOperaratore, parametriRicercaCalendari.codOperaratore) &&
        Objects.equals(subcodice, parametriRicercaCalendari.subcodice) &&
        Objects.equals(dataDa, parametriRicercaCalendari.dataDa) &&
        Objects.equals(dataA, parametriRicercaCalendari.dataA) &&
        Objects.equals(nomeCalendario, parametriRicercaCalendari.nomeCalendario) &&
        Objects.equals(calendarioEliminato, parametriRicercaCalendari.calendarioEliminato) &&
        Objects.equals(codTipoUtente, parametriRicercaCalendari.codTipoUtente) &&
        Objects.equals(idEccezioneApplicabile, parametriRicercaCalendari.idEccezioneApplicabile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codAmbito, gruppoOperatore, codOperaratore, subcodice, dataDa, dataA, nomeCalendario, calendarioEliminato, codTipoUtente, idEccezioneApplicabile);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParametriRicercaCalendari {\n");
    
    sb.append("    codAmbito: ").append(toIndentedString(codAmbito)).append("\n");
    sb.append("    gruppoOperatore: ").append(toIndentedString(gruppoOperatore)).append("\n");
    sb.append("    codOperaratore: ").append(toIndentedString(codOperaratore)).append("\n");
    sb.append("    subcodice: ").append(toIndentedString(subcodice)).append("\n");
    sb.append("    dataDa: ").append(toIndentedString(dataDa)).append("\n");
    sb.append("    dataA: ").append(toIndentedString(dataA)).append("\n");
    sb.append("    nomeCalendario: ").append(toIndentedString(nomeCalendario)).append("\n");
    sb.append("    calendarioEliminato: ").append(toIndentedString(calendarioEliminato)).append("\n");
    sb.append("    codTipoUtente: ").append(toIndentedString(codTipoUtente)).append("\n");
    sb.append("    idEccezioneApplicabile: ").append(toIndentedString(idEccezioneApplicabile)).append("\n");
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

