/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Comune;
import it.csi.pslp.pslweb.dto.be.Nazione;
import it.csi.pslp.pslweb.dto.be.Sedime;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Indirizzo   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String indirizzoEsteso = null;
  private Sedime toponimo = null;
  private String indirizzo = null;
  private String numeroCivico = null;
  private String localita = null;
  private Comune comune = null;
  private Nazione stato = null;

  /**
   **/
  

  @JsonProperty("indirizzo_esteso") 
 
  public String getIndirizzoEsteso() {
    return indirizzoEsteso;
  }
  public void setIndirizzoEsteso(String indirizzoEsteso) {
    this.indirizzoEsteso = indirizzoEsteso;
  }

  /**
   **/
  

  @JsonProperty("toponimo") 
 
  public Sedime getToponimo() {
    return toponimo;
  }
  public void setToponimo(Sedime toponimo) {
    this.toponimo = toponimo;
  }

  /**
   **/
  

  @JsonProperty("indirizzo") 
 
  public String getIndirizzo() {
    return indirizzo;
  }
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  /**
   **/
  

  @JsonProperty("numero_civico") 
 
  public String getNumeroCivico() {
    return numeroCivico;
  }
  public void setNumeroCivico(String numeroCivico) {
    this.numeroCivico = numeroCivico;
  }

  /**
   **/
  

  @JsonProperty("localita") 
 
  public String getLocalita() {
    return localita;
  }
  public void setLocalita(String localita) {
    this.localita = localita;
  }

  /**
   **/
  

  @JsonProperty("comune") 
 
  public Comune getComune() {
    return comune;
  }
  public void setComune(Comune comune) {
    this.comune = comune;
  }

  /**
   **/
  

  @JsonProperty("stato") 
 
  public Nazione getStato() {
    return stato;
  }
  public void setStato(Nazione stato) {
    this.stato = stato;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Indirizzo indirizzo = (Indirizzo) o;
    return Objects.equals(indirizzoEsteso, indirizzo.indirizzoEsteso) &&
        Objects.equals(toponimo, indirizzo.toponimo) &&
        Objects.equals(indirizzo, indirizzo.indirizzo) &&
        Objects.equals(numeroCivico, indirizzo.numeroCivico) &&
        Objects.equals(localita, indirizzo.localita) &&
        Objects.equals(comune, indirizzo.comune) &&
        Objects.equals(stato, indirizzo.stato);
  }

  @Override
  public int hashCode() {
    return Objects.hash(indirizzoEsteso, toponimo, indirizzo, numeroCivico, localita, comune, stato);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Indirizzo {\n");
    
    sb.append("    indirizzoEsteso: ").append(toIndentedString(indirizzoEsteso)).append("\n");
    sb.append("    toponimo: ").append(toIndentedString(toponimo)).append("\n");
    sb.append("    indirizzo: ").append(toIndentedString(indirizzo)).append("\n");
    sb.append("    numeroCivico: ").append(toIndentedString(numeroCivico)).append("\n");
    sb.append("    localita: ").append(toIndentedString(localita)).append("\n");
    sb.append("    comune: ").append(toIndentedString(comune)).append("\n");
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
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

