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

public class ParametriSalvataggioIncontro   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idPrenotazione = null;
  private Long idPrenotazioneOld = null;
  private Long idUtente = null;
  private Long idSlot = null;
  private String idStatoAppuntamento = null;
  private Long idSilRifAmbito = null;
  private String note = null;

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
  

  @JsonProperty("id_prenotazione_old") 
 
  public Long getIdPrenotazioneOld() {
    return idPrenotazioneOld;
  }
  public void setIdPrenotazioneOld(Long idPrenotazioneOld) {
    this.idPrenotazioneOld = idPrenotazioneOld;
  }

  /**
   **/
  

  @JsonProperty("id_utente") 
 
  public Long getIdUtente() {
    return idUtente;
  }
  public void setIdUtente(Long idUtente) {
    this.idUtente = idUtente;
  }

  /**
   **/
  

  @JsonProperty("id_slot") 
 
  public Long getIdSlot() {
    return idSlot;
  }
  public void setIdSlot(Long idSlot) {
    this.idSlot = idSlot;
  }

  /**
   **/
  

  @JsonProperty("id_stato_appuntamento") 
 
  public String getIdStatoAppuntamento() {
    return idStatoAppuntamento;
  }
  public void setIdStatoAppuntamento(String idStatoAppuntamento) {
    this.idStatoAppuntamento = idStatoAppuntamento;
  }

  /**
   **/
  

  @JsonProperty("id_sil_rif_ambito") 
 
  public Long getIdSilRifAmbito() {
    return idSilRifAmbito;
  }
  public void setIdSilRifAmbito(Long idSilRifAmbito) {
    this.idSilRifAmbito = idSilRifAmbito;
  }

  /**
   **/
  

  @JsonProperty("note") 
 
  public String getNote() {
    return note;
  }
  public void setNote(String note) {
    this.note = note;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParametriSalvataggioIncontro parametriSalvataggioIncontro = (ParametriSalvataggioIncontro) o;
    return Objects.equals(idPrenotazione, parametriSalvataggioIncontro.idPrenotazione) &&
        Objects.equals(idPrenotazioneOld, parametriSalvataggioIncontro.idPrenotazioneOld) &&
        Objects.equals(idUtente, parametriSalvataggioIncontro.idUtente) &&
        Objects.equals(idSlot, parametriSalvataggioIncontro.idSlot) &&
        Objects.equals(idStatoAppuntamento, parametriSalvataggioIncontro.idStatoAppuntamento) &&
        Objects.equals(idSilRifAmbito, parametriSalvataggioIncontro.idSilRifAmbito) &&
        Objects.equals(note, parametriSalvataggioIncontro.note);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idPrenotazione, idPrenotazioneOld, idUtente, idSlot, idStatoAppuntamento, idSilRifAmbito, note);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParametriSalvataggioIncontro {\n");
    
    sb.append("    idPrenotazione: ").append(toIndentedString(idPrenotazione)).append("\n");
    sb.append("    idPrenotazioneOld: ").append(toIndentedString(idPrenotazioneOld)).append("\n");
    sb.append("    idUtente: ").append(toIndentedString(idUtente)).append("\n");
    sb.append("    idSlot: ").append(toIndentedString(idSlot)).append("\n");
    sb.append("    idStatoAppuntamento: ").append(toIndentedString(idStatoAppuntamento)).append("\n");
    sb.append("    idSilRifAmbito: ").append(toIndentedString(idSilRifAmbito)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
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

