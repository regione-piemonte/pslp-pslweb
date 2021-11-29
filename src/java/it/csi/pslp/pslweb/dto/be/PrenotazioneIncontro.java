/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.SlotIncontro;
import it.csi.pslp.pslweb.dto.be.Sportello;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class PrenotazioneIncontro   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idPrenotazione = null;
  private String codAmbito = null;
  private String codiceAnpalStatoIncontro = null;
  private Sportello sportello = null;
  private SlotIncontro slot = null;

  /**
   **/
  

  @JsonProperty("id_prenotazione") 
 
  public Long getIdPrenotazione() {
    return idPrenotazione;
  }
  public void setIdPrenotazione(Long idPrenotazione) {
    this.idPrenotazione = idPrenotazione;
  }

  /**
   **/
  

  @JsonProperty("cod_ambito") 
 
  public String getCodAmbito() {
    return codAmbito;
  }
  public void setCodAmbito(String codAmbito) {
    this.codAmbito = codAmbito;
  }

  /**
   **/
  

  @JsonProperty("codice_anpal_stato_incontro") 
 
  public String getCodiceAnpalStatoIncontro() {
    return codiceAnpalStatoIncontro;
  }
  public void setCodiceAnpalStatoIncontro(String codiceAnpalStatoIncontro) {
    this.codiceAnpalStatoIncontro = codiceAnpalStatoIncontro;
  }

  /**
   **/
  

  @JsonProperty("sportello") 
 
  public Sportello getSportello() {
    return sportello;
  }
  public void setSportello(Sportello sportello) {
    this.sportello = sportello;
  }

  /**
   **/
  

  @JsonProperty("slot") 
 
  public SlotIncontro getSlot() {
    return slot;
  }
  public void setSlot(SlotIncontro slot) {
    this.slot = slot;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PrenotazioneIncontro prenotazioneIncontro = (PrenotazioneIncontro) o;
    return Objects.equals(idPrenotazione, prenotazioneIncontro.idPrenotazione) &&
        Objects.equals(codAmbito, prenotazioneIncontro.codAmbito) &&
        Objects.equals(codiceAnpalStatoIncontro, prenotazioneIncontro.codiceAnpalStatoIncontro) &&
        Objects.equals(sportello, prenotazioneIncontro.sportello) &&
        Objects.equals(slot, prenotazioneIncontro.slot);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idPrenotazione, codAmbito, codiceAnpalStatoIncontro, sportello, slot);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PrenotazioneIncontro {\n");
    
    sb.append("    idPrenotazione: ").append(toIndentedString(idPrenotazione)).append("\n");
    sb.append("    codAmbito: ").append(toIndentedString(codAmbito)).append("\n");
    sb.append("    codiceAnpalStatoIncontro: ").append(toIndentedString(codiceAnpalStatoIncontro)).append("\n");
    sb.append("    sportello: ").append(toIndentedString(sportello)).append("\n");
    sb.append("    slot: ").append(toIndentedString(slot)).append("\n");
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

