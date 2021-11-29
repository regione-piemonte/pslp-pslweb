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

public class ParametriCalcoloProfilingYG   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceSilpTitoloStudio = null;
  private Long codiceSilpCondizioneOccupazionale = null;
  private Long codiceSilpPresenzaInItalia = null;
  private String codiceMinisterialeProvincia = null;

  /**
   **/
  

  @JsonProperty("codice_silp_titolo_studio") 
 
  public String getCodiceSilpTitoloStudio() {
    return codiceSilpTitoloStudio;
  }
  public void setCodiceSilpTitoloStudio(String codiceSilpTitoloStudio) {
    this.codiceSilpTitoloStudio = codiceSilpTitoloStudio;
  }

  /**
   **/
  

  @JsonProperty("codice_silp_condizione_occupazionale") 
 
  public Long getCodiceSilpCondizioneOccupazionale() {
    return codiceSilpCondizioneOccupazionale;
  }
  public void setCodiceSilpCondizioneOccupazionale(Long codiceSilpCondizioneOccupazionale) {
    this.codiceSilpCondizioneOccupazionale = codiceSilpCondizioneOccupazionale;
  }

  /**
   **/
  

  @JsonProperty("codice_silp_presenza_in_italia") 
 
  public Long getCodiceSilpPresenzaInItalia() {
    return codiceSilpPresenzaInItalia;
  }
  public void setCodiceSilpPresenzaInItalia(Long codiceSilpPresenzaInItalia) {
    this.codiceSilpPresenzaInItalia = codiceSilpPresenzaInItalia;
  }

  /**
   * codice ministeriale
   **/
  

  @JsonProperty("codice_ministeriale_provincia") 
 
  public String getCodiceMinisterialeProvincia() {
    return codiceMinisterialeProvincia;
  }
  public void setCodiceMinisterialeProvincia(String codiceMinisterialeProvincia) {
    this.codiceMinisterialeProvincia = codiceMinisterialeProvincia;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParametriCalcoloProfilingYG parametriCalcoloProfilingYG = (ParametriCalcoloProfilingYG) o;
    return Objects.equals(codiceSilpTitoloStudio, parametriCalcoloProfilingYG.codiceSilpTitoloStudio) &&
        Objects.equals(codiceSilpCondizioneOccupazionale, parametriCalcoloProfilingYG.codiceSilpCondizioneOccupazionale) &&
        Objects.equals(codiceSilpPresenzaInItalia, parametriCalcoloProfilingYG.codiceSilpPresenzaInItalia) &&
        Objects.equals(codiceMinisterialeProvincia, parametriCalcoloProfilingYG.codiceMinisterialeProvincia);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceSilpTitoloStudio, codiceSilpCondizioneOccupazionale, codiceSilpPresenzaInItalia, codiceMinisterialeProvincia);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParametriCalcoloProfilingYG {\n");
    
    sb.append("    codiceSilpTitoloStudio: ").append(toIndentedString(codiceSilpTitoloStudio)).append("\n");
    sb.append("    codiceSilpCondizioneOccupazionale: ").append(toIndentedString(codiceSilpCondizioneOccupazionale)).append("\n");
    sb.append("    codiceSilpPresenzaInItalia: ").append(toIndentedString(codiceSilpPresenzaInItalia)).append("\n");
    sb.append("    codiceMinisterialeProvincia: ").append(toIndentedString(codiceMinisterialeProvincia)).append("\n");
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

