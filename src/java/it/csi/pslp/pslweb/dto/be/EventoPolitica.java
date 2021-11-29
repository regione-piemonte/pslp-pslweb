/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class EventoPolitica   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Decodifica evento = null;
  private Date dataEvento = null;
  private String descrizione = null;

  /**
   * tabella ministeriale EVENTI POLITICA es. 01 Proposta
   **/
  

  @JsonProperty("evento") 
 
  public Decodifica getEvento() {
    return evento;
  }
  public void setEvento(Decodifica evento) {
    this.evento = evento;
  }

  /**
   **/
  

  @JsonProperty("data_evento") 
 
  public Date getDataEvento() {
    return dataEvento;
  }
  public void setDataEvento(Date dataEvento) {
    this.dataEvento = dataEvento;
  }

  /**
   **/
  

  @JsonProperty("descrizione") 
 
  @Size(max=100)
  public String getDescrizione() {
    return descrizione;
  }
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventoPolitica eventoPolitica = (EventoPolitica) o;
    return Objects.equals(evento, eventoPolitica.evento) &&
        Objects.equals(dataEvento, eventoPolitica.dataEvento) &&
        Objects.equals(descrizione, eventoPolitica.descrizione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(evento, dataEvento, descrizione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventoPolitica {\n");
    
    sb.append("    evento: ").append(toIndentedString(evento)).append("\n");
    sb.append("    dataEvento: ").append(toIndentedString(dataEvento)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
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

