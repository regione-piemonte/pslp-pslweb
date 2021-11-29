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

public class SlotIncontro   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idSlot = null;
  private String giorno = null;
  private String daOra = null;
  private String aOra = null;
  private Integer disponibilita = null;
  private Integer numeroPrenotazioniMassimo = null;
  private Integer numeroPrenotazioniValide = null;
  private Boolean flagEccezione = null;

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
  

  @JsonProperty("giorno") 
 
  public String getGiorno() {
    return giorno;
  }
  public void setGiorno(String giorno) {
    this.giorno = giorno;
  }

  /**
   **/
  

  @JsonProperty("da_ora") 
 
  public String getDaOra() {
    return daOra;
  }
  public void setDaOra(String daOra) {
    this.daOra = daOra;
  }

  /**
   **/
  

  @JsonProperty("a_ora") 
 
  public String getAOra() {
    return aOra;
  }
  public void setAOra(String aOra) {
    this.aOra = aOra;
  }

  /**
   **/
  

  @JsonProperty("disponibilita") 
 
  public Integer getDisponibilita() {
    return disponibilita;
  }
  public void setDisponibilita(Integer disponibilita) {
    this.disponibilita = disponibilita;
  }

  /**
   **/
  

  @JsonProperty("numero_prenotazioni_massimo") 
 
  public Integer getNumeroPrenotazioniMassimo() {
    return numeroPrenotazioniMassimo;
  }
  public void setNumeroPrenotazioniMassimo(Integer numeroPrenotazioniMassimo) {
    this.numeroPrenotazioniMassimo = numeroPrenotazioniMassimo;
  }

  /**
   **/
  

  @JsonProperty("numero_prenotazioni_valide") 
 
  public Integer getNumeroPrenotazioniValide() {
    return numeroPrenotazioniValide;
  }
  public void setNumeroPrenotazioniValide(Integer numeroPrenotazioniValide) {
    this.numeroPrenotazioniValide = numeroPrenotazioniValide;
  }

  /**
   **/
  

  @JsonProperty("flag_eccezione") 
 
  public Boolean isFlagEccezione() {
    return flagEccezione;
  }
  public void setFlagEccezione(Boolean flagEccezione) {
    this.flagEccezione = flagEccezione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SlotIncontro slotIncontro = (SlotIncontro) o;
    return Objects.equals(idSlot, slotIncontro.idSlot) &&
        Objects.equals(giorno, slotIncontro.giorno) &&
        Objects.equals(daOra, slotIncontro.daOra) &&
        Objects.equals(aOra, slotIncontro.aOra) &&
        Objects.equals(disponibilita, slotIncontro.disponibilita) &&
        Objects.equals(numeroPrenotazioniMassimo, slotIncontro.numeroPrenotazioniMassimo) &&
        Objects.equals(numeroPrenotazioniValide, slotIncontro.numeroPrenotazioniValide) &&
        Objects.equals(flagEccezione, slotIncontro.flagEccezione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idSlot, giorno, daOra, aOra, disponibilita, numeroPrenotazioniMassimo, numeroPrenotazioniValide, flagEccezione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SlotIncontro {\n");
    
    sb.append("    idSlot: ").append(toIndentedString(idSlot)).append("\n");
    sb.append("    giorno: ").append(toIndentedString(giorno)).append("\n");
    sb.append("    daOra: ").append(toIndentedString(daOra)).append("\n");
    sb.append("    aOra: ").append(toIndentedString(aOra)).append("\n");
    sb.append("    disponibilita: ").append(toIndentedString(disponibilita)).append("\n");
    sb.append("    numeroPrenotazioniMassimo: ").append(toIndentedString(numeroPrenotazioniMassimo)).append("\n");
    sb.append("    numeroPrenotazioniValide: ").append(toIndentedString(numeroPrenotazioniValide)).append("\n");
    sb.append("    flagEccezione: ").append(toIndentedString(flagEccezione)).append("\n");
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

