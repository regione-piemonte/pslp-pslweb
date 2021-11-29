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

public class ParametriDuplicazioneCalendario   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String nomeCalendario = null;
  private String ambito = null;
  private String gruppoOperatore = null;
  private Long codiceOperatore = null;
  private Long subcodice = null;
  private Boolean duplicaFasce = null;
  private Boolean duplicaEccezioni = null;

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
  

  @JsonProperty("ambito") 
 
  public String getAmbito() {
    return ambito;
  }
  public void setAmbito(String ambito) {
    this.ambito = ambito;
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
  

  @JsonProperty("duplica_fasce") 
 
  public Boolean isDuplicaFasce() {
    return duplicaFasce;
  }
  public void setDuplicaFasce(Boolean duplicaFasce) {
    this.duplicaFasce = duplicaFasce;
  }

  /**
   **/
  

  @JsonProperty("duplica_eccezioni") 
 
  public Boolean isDuplicaEccezioni() {
    return duplicaEccezioni;
  }
  public void setDuplicaEccezioni(Boolean duplicaEccezioni) {
    this.duplicaEccezioni = duplicaEccezioni;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParametriDuplicazioneCalendario parametriDuplicazioneCalendario = (ParametriDuplicazioneCalendario) o;
    return Objects.equals(nomeCalendario, parametriDuplicazioneCalendario.nomeCalendario) &&
        Objects.equals(ambito, parametriDuplicazioneCalendario.ambito) &&
        Objects.equals(gruppoOperatore, parametriDuplicazioneCalendario.gruppoOperatore) &&
        Objects.equals(codiceOperatore, parametriDuplicazioneCalendario.codiceOperatore) &&
        Objects.equals(subcodice, parametriDuplicazioneCalendario.subcodice) &&
        Objects.equals(duplicaFasce, parametriDuplicazioneCalendario.duplicaFasce) &&
        Objects.equals(duplicaEccezioni, parametriDuplicazioneCalendario.duplicaEccezioni);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nomeCalendario, ambito, gruppoOperatore, codiceOperatore, subcodice, duplicaFasce, duplicaEccezioni);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParametriDuplicazioneCalendario {\n");
    
    sb.append("    nomeCalendario: ").append(toIndentedString(nomeCalendario)).append("\n");
    sb.append("    ambito: ").append(toIndentedString(ambito)).append("\n");
    sb.append("    gruppoOperatore: ").append(toIndentedString(gruppoOperatore)).append("\n");
    sb.append("    codiceOperatore: ").append(toIndentedString(codiceOperatore)).append("\n");
    sb.append("    subcodice: ").append(toIndentedString(subcodice)).append("\n");
    sb.append("    duplicaFasce: ").append(toIndentedString(duplicaFasce)).append("\n");
    sb.append("    duplicaEccezioni: ").append(toIndentedString(duplicaEccezioni)).append("\n");
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

