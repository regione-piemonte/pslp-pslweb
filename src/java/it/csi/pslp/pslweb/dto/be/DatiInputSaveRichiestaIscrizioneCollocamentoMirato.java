/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.DettaglioRichiestaIscrizioneL68;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class DatiInputSaveRichiestaIscrizioneCollocamentoMirato   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String stepControllo = null;
  private DettaglioRichiestaIscrizioneL68 richiesta = null;

  /**
   **/
  

  @JsonProperty("step_controllo") 
 
  public String getStepControllo() {
    return stepControllo;
  }
  public void setStepControllo(String stepControllo) {
    this.stepControllo = stepControllo;
  }

  /**
   **/
  

  @JsonProperty("richiesta") 
 
  public DettaglioRichiestaIscrizioneL68 getRichiesta() {
    return richiesta;
  }
  public void setRichiesta(DettaglioRichiestaIscrizioneL68 richiesta) {
    this.richiesta = richiesta;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatiInputSaveRichiestaIscrizioneCollocamentoMirato datiInputSaveRichiestaIscrizioneCollocamentoMirato = (DatiInputSaveRichiestaIscrizioneCollocamentoMirato) o;
    return Objects.equals(stepControllo, datiInputSaveRichiestaIscrizioneCollocamentoMirato.stepControllo) &&
        Objects.equals(richiesta, datiInputSaveRichiestaIscrizioneCollocamentoMirato.richiesta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stepControllo, richiesta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DatiInputSaveRichiestaIscrizioneCollocamentoMirato {\n");
    
    sb.append("    stepControllo: ").append(toIndentedString(stepControllo)).append("\n");
    sb.append("    richiesta: ").append(toIndentedString(richiesta)).append("\n");
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

