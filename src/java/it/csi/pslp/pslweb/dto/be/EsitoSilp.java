/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.MappaErrori;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class EsitoSilp   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Boolean esitoPositivo = null;
  private String messaggioCittadino = null;
  private String messaggioErrori = null;
  private String messaggioInfo = null;
  private String messaggioWarning = null;
  private String header = null;
  private List<MappaErrori> error = new ArrayList<MappaErrori>();
  private List<MappaErrori> info = new ArrayList<MappaErrori>();
  private List<MappaErrori> warning = new ArrayList<MappaErrori>();

  /**
   **/
  

  @JsonProperty("esitoPositivo") 
 
  public Boolean isEsitoPositivo() {
    return esitoPositivo;
  }
  public void setEsitoPositivo(Boolean esitoPositivo) {
    this.esitoPositivo = esitoPositivo;
  }

  /**
   **/
  

  @JsonProperty("messaggioCittadino") 
 
  public String getMessaggioCittadino() {
    return messaggioCittadino;
  }
  public void setMessaggioCittadino(String messaggioCittadino) {
    this.messaggioCittadino = messaggioCittadino;
  }

  /**
   **/
  

  @JsonProperty("messaggioErrori") 
 
  public String getMessaggioErrori() {
    return messaggioErrori;
  }
  public void setMessaggioErrori(String messaggioErrori) {
    this.messaggioErrori = messaggioErrori;
  }

  /**
   **/
  

  @JsonProperty("messaggioInfo") 
 
  public String getMessaggioInfo() {
    return messaggioInfo;
  }
  public void setMessaggioInfo(String messaggioInfo) {
    this.messaggioInfo = messaggioInfo;
  }

  /**
   **/
  

  @JsonProperty("messaggioWarning") 
 
  public String getMessaggioWarning() {
    return messaggioWarning;
  }
  public void setMessaggioWarning(String messaggioWarning) {
    this.messaggioWarning = messaggioWarning;
  }

  /**
   **/
  

  @JsonProperty("header") 
 
  public String getHeader() {
    return header;
  }
  public void setHeader(String header) {
    this.header = header;
  }

  /**
   **/
  

  @JsonProperty("error") 
 
  public List<MappaErrori> getError() {
    return error;
  }
  public void setError(List<MappaErrori> error) {
    this.error = error;
  }

  /**
   **/
  

  @JsonProperty("info") 
 
  public List<MappaErrori> getInfo() {
    return info;
  }
  public void setInfo(List<MappaErrori> info) {
    this.info = info;
  }

  /**
   **/
  

  @JsonProperty("warning") 
 
  public List<MappaErrori> getWarning() {
    return warning;
  }
  public void setWarning(List<MappaErrori> warning) {
    this.warning = warning;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoSilp esitoSilp = (EsitoSilp) o;
    return Objects.equals(esitoPositivo, esitoSilp.esitoPositivo) &&
        Objects.equals(messaggioCittadino, esitoSilp.messaggioCittadino) &&
        Objects.equals(messaggioErrori, esitoSilp.messaggioErrori) &&
        Objects.equals(messaggioInfo, esitoSilp.messaggioInfo) &&
        Objects.equals(messaggioWarning, esitoSilp.messaggioWarning) &&
        Objects.equals(header, esitoSilp.header) &&
        Objects.equals(error, esitoSilp.error) &&
        Objects.equals(info, esitoSilp.info) &&
        Objects.equals(warning, esitoSilp.warning);
  }

  @Override
  public int hashCode() {
    return Objects.hash(esitoPositivo, messaggioCittadino, messaggioErrori, messaggioInfo, messaggioWarning, header, error, info, warning);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoSilp {\n");
    
    sb.append("    esitoPositivo: ").append(toIndentedString(esitoPositivo)).append("\n");
    sb.append("    messaggioCittadino: ").append(toIndentedString(messaggioCittadino)).append("\n");
    sb.append("    messaggioErrori: ").append(toIndentedString(messaggioErrori)).append("\n");
    sb.append("    messaggioInfo: ").append(toIndentedString(messaggioInfo)).append("\n");
    sb.append("    messaggioWarning: ").append(toIndentedString(messaggioWarning)).append("\n");
    sb.append("    header: ").append(toIndentedString(header)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
    sb.append("    warning: ").append(toIndentedString(warning)).append("\n");
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

