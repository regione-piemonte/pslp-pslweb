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

public class ConfigurazioneCalendarioDatiOperativi   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String flagVisibilitaCpi = null;
  private Integer oreLimiteSpostamento = null;
  private String messaggioSpostamento = null;
  private Boolean flagAnnullamento = null;
  private String messaggioAnnullamento = null;

  /**
   **/
  

  @JsonProperty("flag_visibilita_cpi") 
 
  public String getFlagVisibilitaCpi() {
    return flagVisibilitaCpi;
  }
  public void setFlagVisibilitaCpi(String flagVisibilitaCpi) {
    this.flagVisibilitaCpi = flagVisibilitaCpi;
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
  

  @JsonProperty("messaggio_spostamento") 
 
  public String getMessaggioSpostamento() {
    return messaggioSpostamento;
  }
  public void setMessaggioSpostamento(String messaggioSpostamento) {
    this.messaggioSpostamento = messaggioSpostamento;
  }

  /**
   **/
  

  @JsonProperty("flag_annullamento") 
 
  public Boolean isFlagAnnullamento() {
    return flagAnnullamento;
  }
  public void setFlagAnnullamento(Boolean flagAnnullamento) {
    this.flagAnnullamento = flagAnnullamento;
  }

  /**
   **/
  

  @JsonProperty("messaggio_annullamento") 
 
  public String getMessaggioAnnullamento() {
    return messaggioAnnullamento;
  }
  public void setMessaggioAnnullamento(String messaggioAnnullamento) {
    this.messaggioAnnullamento = messaggioAnnullamento;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneCalendarioDatiOperativi configurazioneCalendarioDatiOperativi = (ConfigurazioneCalendarioDatiOperativi) o;
    return Objects.equals(flagVisibilitaCpi, configurazioneCalendarioDatiOperativi.flagVisibilitaCpi) &&
        Objects.equals(oreLimiteSpostamento, configurazioneCalendarioDatiOperativi.oreLimiteSpostamento) &&
        Objects.equals(messaggioSpostamento, configurazioneCalendarioDatiOperativi.messaggioSpostamento) &&
        Objects.equals(flagAnnullamento, configurazioneCalendarioDatiOperativi.flagAnnullamento) &&
        Objects.equals(messaggioAnnullamento, configurazioneCalendarioDatiOperativi.messaggioAnnullamento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(flagVisibilitaCpi, oreLimiteSpostamento, messaggioSpostamento, flagAnnullamento, messaggioAnnullamento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneCalendarioDatiOperativi {\n");
    
    sb.append("    flagVisibilitaCpi: ").append(toIndentedString(flagVisibilitaCpi)).append("\n");
    sb.append("    oreLimiteSpostamento: ").append(toIndentedString(oreLimiteSpostamento)).append("\n");
    sb.append("    messaggioSpostamento: ").append(toIndentedString(messaggioSpostamento)).append("\n");
    sb.append("    flagAnnullamento: ").append(toIndentedString(flagAnnullamento)).append("\n");
    sb.append("    messaggioAnnullamento: ").append(toIndentedString(messaggioAnnullamento)).append("\n");
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

