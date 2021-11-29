/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.ControlliIscrizione;
import it.csi.pslp.pslweb.dto.be.TipoResponsabilita;
import it.csi.pslp.pslweb.dto.be.Utente;
import it.csi.pslp.pslweb.dto.be.UtentePresaVisione;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class UtenteRiepilogoIscrizione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Utente utente = null;
  private TipoResponsabilita tipoResponsabilita = null;
  private List<UtentePresaVisione> preseVisione = new ArrayList<UtentePresaVisione>();
  private ControlliIscrizione dati = null;

  /**
   **/
  

  @JsonProperty("utente") 
 
  public Utente getUtente() {
    return utente;
  }
  public void setUtente(Utente utente) {
    this.utente = utente;
  }

  /**
   **/
  

  @JsonProperty("tipo_responsabilita") 
 
  public TipoResponsabilita getTipoResponsabilita() {
    return tipoResponsabilita;
  }
  public void setTipoResponsabilita(TipoResponsabilita tipoResponsabilita) {
    this.tipoResponsabilita = tipoResponsabilita;
  }

  /**
   **/
  

  @JsonProperty("prese_visione") 
 
  public List<UtentePresaVisione> getPreseVisione() {
    return preseVisione;
  }
  public void setPreseVisione(List<UtentePresaVisione> preseVisione) {
    this.preseVisione = preseVisione;
  }

  /**
   **/
  

  @JsonProperty("dati") 
 
  public ControlliIscrizione getDati() {
    return dati;
  }
  public void setDati(ControlliIscrizione dati) {
    this.dati = dati;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UtenteRiepilogoIscrizione utenteRiepilogoIscrizione = (UtenteRiepilogoIscrizione) o;
    return Objects.equals(utente, utenteRiepilogoIscrizione.utente) &&
        Objects.equals(tipoResponsabilita, utenteRiepilogoIscrizione.tipoResponsabilita) &&
        Objects.equals(preseVisione, utenteRiepilogoIscrizione.preseVisione) &&
        Objects.equals(dati, utenteRiepilogoIscrizione.dati);
  }

  @Override
  public int hashCode() {
    return Objects.hash(utente, tipoResponsabilita, preseVisione, dati);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UtenteRiepilogoIscrizione {\n");
    
    sb.append("    utente: ").append(toIndentedString(utente)).append("\n");
    sb.append("    tipoResponsabilita: ").append(toIndentedString(tipoResponsabilita)).append("\n");
    sb.append("    preseVisione: ").append(toIndentedString(preseVisione)).append("\n");
    sb.append("    dati: ").append(toIndentedString(dati)).append("\n");
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

