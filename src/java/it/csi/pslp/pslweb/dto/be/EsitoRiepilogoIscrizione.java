/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.UtenteRiepilogoIscrizione;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class EsitoRiepilogoIscrizione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String messaggioErrore = null;
  private String messaggioEtaNonCoerente = null;
  private Long idUtente = null;
  private UtenteRiepilogoIscrizione utenteTutore = null;
  private List<UtenteRiepilogoIscrizione> tutelati = new ArrayList<UtenteRiepilogoIscrizione>();

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
  

  @JsonProperty("messaggio_eta_non_coerente") 
 
  public String getMessaggioEtaNonCoerente() {
    return messaggioEtaNonCoerente;
  }
  public void setMessaggioEtaNonCoerente(String messaggioEtaNonCoerente) {
    this.messaggioEtaNonCoerente = messaggioEtaNonCoerente;
  }

  /**
   * id utente
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
  

  @JsonProperty("utente_tutore") 
 
  public UtenteRiepilogoIscrizione getUtenteTutore() {
    return utenteTutore;
  }
  public void setUtenteTutore(UtenteRiepilogoIscrizione utenteTutore) {
    this.utenteTutore = utenteTutore;
  }

  /**
   **/
  

  @JsonProperty("tutelati") 
 
  public List<UtenteRiepilogoIscrizione> getTutelati() {
    return tutelati;
  }
  public void setTutelati(List<UtenteRiepilogoIscrizione> tutelati) {
    this.tutelati = tutelati;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoRiepilogoIscrizione esitoRiepilogoIscrizione = (EsitoRiepilogoIscrizione) o;
    return Objects.equals(messaggioErrore, esitoRiepilogoIscrizione.messaggioErrore) &&
        Objects.equals(messaggioEtaNonCoerente, esitoRiepilogoIscrizione.messaggioEtaNonCoerente) &&
        Objects.equals(idUtente, esitoRiepilogoIscrizione.idUtente) &&
        Objects.equals(utenteTutore, esitoRiepilogoIscrizione.utenteTutore) &&
        Objects.equals(tutelati, esitoRiepilogoIscrizione.tutelati);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messaggioErrore, messaggioEtaNonCoerente, idUtente, utenteTutore, tutelati);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoRiepilogoIscrizione {\n");
    
    sb.append("    messaggioErrore: ").append(toIndentedString(messaggioErrore)).append("\n");
    sb.append("    messaggioEtaNonCoerente: ").append(toIndentedString(messaggioEtaNonCoerente)).append("\n");
    sb.append("    idUtente: ").append(toIndentedString(idUtente)).append("\n");
    sb.append("    utenteTutore: ").append(toIndentedString(utenteTutore)).append("\n");
    sb.append("    tutelati: ").append(toIndentedString(tutelati)).append("\n");
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

