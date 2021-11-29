/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.TipoResponsabilita;
import it.csi.pslp.pslweb.dto.be.Utente;
import it.csi.pslp.pslweb.dto.be.UtentePresaVisione;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class UtenteACarico   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Utente tutelato = null;
  private TipoResponsabilita tipoResponsabilita = null;
  private List<UtentePresaVisione> preseVisione = new ArrayList<UtentePresaVisione>();

  /**
   **/
  

  @JsonProperty("tutelato") 
 
  public Utente getTutelato() {
    return tutelato;
  }
  public void setTutelato(Utente tutelato) {
    this.tutelato = tutelato;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UtenteACarico utenteACarico = (UtenteACarico) o;
    return Objects.equals(tutelato, utenteACarico.tutelato) &&
        Objects.equals(tipoResponsabilita, utenteACarico.tipoResponsabilita) &&
        Objects.equals(preseVisione, utenteACarico.preseVisione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tutelato, tipoResponsabilita, preseVisione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UtenteACarico {\n");
    
    sb.append("    tutelato: ").append(toIndentedString(tutelato)).append("\n");
    sb.append("    tipoResponsabilita: ").append(toIndentedString(tipoResponsabilita)).append("\n");
    sb.append("    preseVisione: ").append(toIndentedString(preseVisione)).append("\n");
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

