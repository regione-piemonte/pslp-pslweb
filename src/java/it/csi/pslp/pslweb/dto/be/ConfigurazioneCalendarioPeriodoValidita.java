/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCalendarioEccezione;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneCalendarioFascia;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ConfigurazioneCalendarioPeriodoValidita   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idPeriodo = null;
  private Date dataDa = null;
  private Date dataA = null;
  private Boolean flagSlotGenerati = null;
  private List<ConfigurazioneCalendarioFascia> fasce = new ArrayList<ConfigurazioneCalendarioFascia>();
  private List<ConfigurazioneCalendarioEccezione> eccezioni = new ArrayList<ConfigurazioneCalendarioEccezione>();

  /**
   **/
  

  @JsonProperty("id_periodo") 
 
  public Long getIdPeriodo() {
    return idPeriodo;
  }
  public void setIdPeriodo(Long idPeriodo) {
    this.idPeriodo = idPeriodo;
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
  

  @JsonProperty("flag_slot_generati") 
 
  public Boolean isFlagSlotGenerati() {
    return flagSlotGenerati;
  }
  public void setFlagSlotGenerati(Boolean flagSlotGenerati) {
    this.flagSlotGenerati = flagSlotGenerati;
  }

  /**
   **/
  

  @JsonProperty("fasce") 
 
  public List<ConfigurazioneCalendarioFascia> getFasce() {
    return fasce;
  }
  public void setFasce(List<ConfigurazioneCalendarioFascia> fasce) {
    this.fasce = fasce;
  }

  /**
   **/
  

  @JsonProperty("eccezioni") 
 
  public List<ConfigurazioneCalendarioEccezione> getEccezioni() {
    return eccezioni;
  }
  public void setEccezioni(List<ConfigurazioneCalendarioEccezione> eccezioni) {
    this.eccezioni = eccezioni;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneCalendarioPeriodoValidita configurazioneCalendarioPeriodoValidita = (ConfigurazioneCalendarioPeriodoValidita) o;
    return Objects.equals(idPeriodo, configurazioneCalendarioPeriodoValidita.idPeriodo) &&
        Objects.equals(dataDa, configurazioneCalendarioPeriodoValidita.dataDa) &&
        Objects.equals(dataA, configurazioneCalendarioPeriodoValidita.dataA) &&
        Objects.equals(flagSlotGenerati, configurazioneCalendarioPeriodoValidita.flagSlotGenerati) &&
        Objects.equals(fasce, configurazioneCalendarioPeriodoValidita.fasce) &&
        Objects.equals(eccezioni, configurazioneCalendarioPeriodoValidita.eccezioni);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idPeriodo, dataDa, dataA, flagSlotGenerati, fasce, eccezioni);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneCalendarioPeriodoValidita {\n");
    
    sb.append("    idPeriodo: ").append(toIndentedString(idPeriodo)).append("\n");
    sb.append("    dataDa: ").append(toIndentedString(dataDa)).append("\n");
    sb.append("    dataA: ").append(toIndentedString(dataA)).append("\n");
    sb.append("    flagSlotGenerati: ").append(toIndentedString(flagSlotGenerati)).append("\n");
    sb.append("    fasce: ").append(toIndentedString(fasce)).append("\n");
    sb.append("    eccezioni: ").append(toIndentedString(eccezioni)).append("\n");
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

