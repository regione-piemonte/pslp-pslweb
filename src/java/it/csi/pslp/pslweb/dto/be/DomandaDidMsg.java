/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.RispostaDidMsg;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class DomandaDidMsg   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String descrDomanda = null;
  private String codice = null;
  private List<RispostaDidMsg> listaRisposte = new ArrayList<RispostaDidMsg>();

  /**
   **/
  

  @JsonProperty("descr_domanda") 
 
  public String getDescrDomanda() {
    return descrDomanda;
  }
  public void setDescrDomanda(String descrDomanda) {
    this.descrDomanda = descrDomanda;
  }

  /**
   **/
  

  @JsonProperty("codice") 
 
  public String getCodice() {
    return codice;
  }
  public void setCodice(String codice) {
    this.codice = codice;
  }

  /**
   **/
  

  @JsonProperty("lista_risposte") 
 
  public List<RispostaDidMsg> getListaRisposte() {
    return listaRisposte;
  }
  public void setListaRisposte(List<RispostaDidMsg> listaRisposte) {
    this.listaRisposte = listaRisposte;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DomandaDidMsg domandaDidMsg = (DomandaDidMsg) o;
    return Objects.equals(descrDomanda, domandaDidMsg.descrDomanda) &&
        Objects.equals(codice, domandaDidMsg.codice) &&
        Objects.equals(listaRisposte, domandaDidMsg.listaRisposte);
  }

  @Override
  public int hashCode() {
    return Objects.hash(descrDomanda, codice, listaRisposte);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DomandaDidMsg {\n");
    
    sb.append("    descrDomanda: ").append(toIndentedString(descrDomanda)).append("\n");
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    listaRisposte: ").append(toIndentedString(listaRisposte)).append("\n");
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

