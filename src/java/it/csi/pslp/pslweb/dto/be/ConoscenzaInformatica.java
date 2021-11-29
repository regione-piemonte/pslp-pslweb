/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ConoscenzaInformatica   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Decodifica tipoConoscenza = null;
  private Decodifica livelloConoscenza = null;
  private String eventualiSpecifiche = null;
  private String codiceCategoriaConoscenzaInformatica = null;

  /**
   **/
  

  @JsonProperty("tipo_conoscenza") 
 
  public Decodifica getTipoConoscenza() {
    return tipoConoscenza;
  }
  public void setTipoConoscenza(Decodifica tipoConoscenza) {
    this.tipoConoscenza = tipoConoscenza;
  }

  /**
   **/
  

  @JsonProperty("livello_conoscenza") 
 
  public Decodifica getLivelloConoscenza() {
    return livelloConoscenza;
  }
  public void setLivelloConoscenza(Decodifica livelloConoscenza) {
    this.livelloConoscenza = livelloConoscenza;
  }

  /**
   **/
  

  @JsonProperty("eventuali_specifiche") 
 
  public String getEventualiSpecifiche() {
    return eventualiSpecifiche;
  }
  public void setEventualiSpecifiche(String eventualiSpecifiche) {
    this.eventualiSpecifiche = eventualiSpecifiche;
  }

  /**
   **/
  

  @JsonProperty("codice_categoria_conoscenza_informatica") 
 
  public String getCodiceCategoriaConoscenzaInformatica() {
    return codiceCategoriaConoscenzaInformatica;
  }
  public void setCodiceCategoriaConoscenzaInformatica(String codiceCategoriaConoscenzaInformatica) {
    this.codiceCategoriaConoscenzaInformatica = codiceCategoriaConoscenzaInformatica;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConoscenzaInformatica conoscenzaInformatica = (ConoscenzaInformatica) o;
    return Objects.equals(tipoConoscenza, conoscenzaInformatica.tipoConoscenza) &&
        Objects.equals(livelloConoscenza, conoscenzaInformatica.livelloConoscenza) &&
        Objects.equals(eventualiSpecifiche, conoscenzaInformatica.eventualiSpecifiche) &&
        Objects.equals(codiceCategoriaConoscenzaInformatica, conoscenzaInformatica.codiceCategoriaConoscenzaInformatica);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tipoConoscenza, livelloConoscenza, eventualiSpecifiche, codiceCategoriaConoscenzaInformatica);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConoscenzaInformatica {\n");
    
    sb.append("    tipoConoscenza: ").append(toIndentedString(tipoConoscenza)).append("\n");
    sb.append("    livelloConoscenza: ").append(toIndentedString(livelloConoscenza)).append("\n");
    sb.append("    eventualiSpecifiche: ").append(toIndentedString(eventualiSpecifiche)).append("\n");
    sb.append("    codiceCategoriaConoscenzaInformatica: ").append(toIndentedString(codiceCategoriaConoscenzaInformatica)).append("\n");
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

