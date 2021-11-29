/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.PrenotazioneIncontro;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class EsitoSalvataggioIncontro   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String messaggioErrore = null;
  private PrenotazioneIncontro prenotazioneIncontro = null;

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
  

  @JsonProperty("prenotazione_incontro") 
 
  public PrenotazioneIncontro getPrenotazioneIncontro() {
    return prenotazioneIncontro;
  }
  public void setPrenotazioneIncontro(PrenotazioneIncontro prenotazioneIncontro) {
    this.prenotazioneIncontro = prenotazioneIncontro;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoSalvataggioIncontro esitoSalvataggioIncontro = (EsitoSalvataggioIncontro) o;
    return Objects.equals(messaggioErrore, esitoSalvataggioIncontro.messaggioErrore) &&
        Objects.equals(prenotazioneIncontro, esitoSalvataggioIncontro.prenotazioneIncontro);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messaggioErrore, prenotazioneIncontro);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoSalvataggioIncontro {\n");
    
    sb.append("    messaggioErrore: ").append(toIndentedString(messaggioErrore)).append("\n");
    sb.append("    prenotazioneIncontro: ").append(toIndentedString(prenotazioneIncontro)).append("\n");
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

