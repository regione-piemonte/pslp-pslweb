/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.AdesioneYG;
import it.csi.pslp.pslweb.dto.be.MappaErrori;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class EsitoSendStatoAdesione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idAnagraficaSilp = null;
  private Long idAdesioneSilp = null;
  private String messaggioCittadino = null;
  private AdesioneYG adesione = null;
  private List<MappaErrori> warning = new ArrayList<MappaErrori>();
  private String code = null;
  private Boolean incontroCancellato = null;

  /**
   **/
  

  @JsonProperty("idAnagraficaSilp") 
 
  public Long getIdAnagraficaSilp() {
    return idAnagraficaSilp;
  }
  public void setIdAnagraficaSilp(Long idAnagraficaSilp) {
    this.idAnagraficaSilp = idAnagraficaSilp;
  }

  /**
   **/
  

  @JsonProperty("idAdesioneSilp") 
 
  public Long getIdAdesioneSilp() {
    return idAdesioneSilp;
  }
  public void setIdAdesioneSilp(Long idAdesioneSilp) {
    this.idAdesioneSilp = idAdesioneSilp;
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
  

  @JsonProperty("adesione") 
 
  public AdesioneYG getAdesione() {
    return adesione;
  }
  public void setAdesione(AdesioneYG adesione) {
    this.adesione = adesione;
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

  /**
   **/
  

  @JsonProperty("code") 
 
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }

  /**
   **/
  

  @JsonProperty("incontroCancellato") 
 
  public Boolean isIncontroCancellato() {
    return incontroCancellato;
  }
  public void setIncontroCancellato(Boolean incontroCancellato) {
    this.incontroCancellato = incontroCancellato;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoSendStatoAdesione esitoSendStatoAdesione = (EsitoSendStatoAdesione) o;
    return Objects.equals(idAnagraficaSilp, esitoSendStatoAdesione.idAnagraficaSilp) &&
        Objects.equals(idAdesioneSilp, esitoSendStatoAdesione.idAdesioneSilp) &&
        Objects.equals(messaggioCittadino, esitoSendStatoAdesione.messaggioCittadino) &&
        Objects.equals(adesione, esitoSendStatoAdesione.adesione) &&
        Objects.equals(warning, esitoSendStatoAdesione.warning) &&
        Objects.equals(code, esitoSendStatoAdesione.code) &&
        Objects.equals(incontroCancellato, esitoSendStatoAdesione.incontroCancellato);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idAnagraficaSilp, idAdesioneSilp, messaggioCittadino, adesione, warning, code, incontroCancellato);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoSendStatoAdesione {\n");
    
    sb.append("    idAnagraficaSilp: ").append(toIndentedString(idAnagraficaSilp)).append("\n");
    sb.append("    idAdesioneSilp: ").append(toIndentedString(idAdesioneSilp)).append("\n");
    sb.append("    messaggioCittadino: ").append(toIndentedString(messaggioCittadino)).append("\n");
    sb.append("    adesione: ").append(toIndentedString(adesione)).append("\n");
    sb.append("    warning: ").append(toIndentedString(warning)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    incontroCancellato: ").append(toIndentedString(incontroCancellato)).append("\n");
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

