/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.DettaglioRichiestaIscrizioneL68;
import it.csi.pslp.pslweb.dto.be.RichiestaIscrizioneL68Header;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class EsitoRicercaRichiestaIscrizioneCollocamentoMirato   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private List<RichiestaIscrizioneL68Header> elencoRichieste = new ArrayList<RichiestaIscrizioneL68Header>();
  private DettaglioRichiestaIscrizioneL68 dettaglio = null;

  /**
   **/
  

  @JsonProperty("elencoRichieste") 
 
  public List<RichiestaIscrizioneL68Header> getElencoRichieste() {
    return elencoRichieste;
  }
  public void setElencoRichieste(List<RichiestaIscrizioneL68Header> elencoRichieste) {
    this.elencoRichieste = elencoRichieste;
  }

  /**
   **/
  

  @JsonProperty("dettaglio") 
 
  public DettaglioRichiestaIscrizioneL68 getDettaglio() {
    return dettaglio;
  }
  public void setDettaglio(DettaglioRichiestaIscrizioneL68 dettaglio) {
    this.dettaglio = dettaglio;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoRicercaRichiestaIscrizioneCollocamentoMirato esitoRicercaRichiestaIscrizioneCollocamentoMirato = (EsitoRicercaRichiestaIscrizioneCollocamentoMirato) o;
    return Objects.equals(elencoRichieste, esitoRicercaRichiestaIscrizioneCollocamentoMirato.elencoRichieste) &&
        Objects.equals(dettaglio, esitoRicercaRichiestaIscrizioneCollocamentoMirato.dettaglio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(elencoRichieste, dettaglio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoRicercaRichiestaIscrizioneCollocamentoMirato {\n");
    
    sb.append("    elencoRichieste: ").append(toIndentedString(elencoRichieste)).append("\n");
    sb.append("    dettaglio: ").append(toIndentedString(dettaglio)).append("\n");
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

