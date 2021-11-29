/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.RedditoCollocamentoMirato;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class EsitoSalvataggioRedditoCollocamentoMirato   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String messaggioErrore = null;
  private String codiceFiscaleOperatore = null;
  private String codiceFiscale = null;
  private Long idAnagraficaSilp = null;
  private RedditoCollocamentoMirato reddito = null;

  /**
   **/
  

  @JsonProperty("messaggio_errore") 
 
  public String getMessaggioErrore() {
    return messaggioErrore;
  }
  public void setMessaggioErrore(String messaggioErrore) {
    this.messaggioErrore = messaggioErrore;
  }

  /**
   **/
  

  @JsonProperty("codiceFiscaleOperatore") 
 
  public String getCodiceFiscaleOperatore() {
    return codiceFiscaleOperatore;
  }
  public void setCodiceFiscaleOperatore(String codiceFiscaleOperatore) {
    this.codiceFiscaleOperatore = codiceFiscaleOperatore;
  }

  /**
   **/
  

  @JsonProperty("codiceFiscale") 
 
  public String getCodiceFiscale() {
    return codiceFiscale;
  }
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

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
  

  @JsonProperty("reddito") 
 
  public RedditoCollocamentoMirato getReddito() {
    return reddito;
  }
  public void setReddito(RedditoCollocamentoMirato reddito) {
    this.reddito = reddito;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoSalvataggioRedditoCollocamentoMirato esitoSalvataggioRedditoCollocamentoMirato = (EsitoSalvataggioRedditoCollocamentoMirato) o;
    return Objects.equals(messaggioErrore, esitoSalvataggioRedditoCollocamentoMirato.messaggioErrore) &&
        Objects.equals(codiceFiscaleOperatore, esitoSalvataggioRedditoCollocamentoMirato.codiceFiscaleOperatore) &&
        Objects.equals(codiceFiscale, esitoSalvataggioRedditoCollocamentoMirato.codiceFiscale) &&
        Objects.equals(idAnagraficaSilp, esitoSalvataggioRedditoCollocamentoMirato.idAnagraficaSilp) &&
        Objects.equals(reddito, esitoSalvataggioRedditoCollocamentoMirato.reddito);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messaggioErrore, codiceFiscaleOperatore, codiceFiscale, idAnagraficaSilp, reddito);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoSalvataggioRedditoCollocamentoMirato {\n");
    
    sb.append("    messaggioErrore: ").append(toIndentedString(messaggioErrore)).append("\n");
    sb.append("    codiceFiscaleOperatore: ").append(toIndentedString(codiceFiscaleOperatore)).append("\n");
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    idAnagraficaSilp: ").append(toIndentedString(idAnagraficaSilp)).append("\n");
    sb.append("    reddito: ").append(toIndentedString(reddito)).append("\n");
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

