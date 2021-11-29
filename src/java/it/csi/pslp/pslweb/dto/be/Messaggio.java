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

public class Messaggio   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idMessaggio = null;
  private String codMessaggio = null;
  private String codTipoMessaggio = null;
  private String codAmbito = null;
  private String intestazione = null;
  private String testo = null;

  /**
   **/
  

  @JsonProperty("id_messaggio") 
 
  public Long getIdMessaggio() {
    return idMessaggio;
  }
  public void setIdMessaggio(Long idMessaggio) {
    this.idMessaggio = idMessaggio;
  }

  /**
   **/
  

  @JsonProperty("cod_messaggio") 
 
  public String getCodMessaggio() {
    return codMessaggio;
  }
  public void setCodMessaggio(String codMessaggio) {
    this.codMessaggio = codMessaggio;
  }

  /**
   **/
  

  @JsonProperty("cod_tipo_messaggio") 
 
  public String getCodTipoMessaggio() {
    return codTipoMessaggio;
  }
  public void setCodTipoMessaggio(String codTipoMessaggio) {
    this.codTipoMessaggio = codTipoMessaggio;
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
  

  @JsonProperty("intestazione") 
 
  public String getIntestazione() {
    return intestazione;
  }
  public void setIntestazione(String intestazione) {
    this.intestazione = intestazione;
  }

  /**
   **/
  

  @JsonProperty("testo") 
 
  public String getTesto() {
    return testo;
  }
  public void setTesto(String testo) {
    this.testo = testo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Messaggio messaggio = (Messaggio) o;
    return Objects.equals(idMessaggio, messaggio.idMessaggio) &&
        Objects.equals(codMessaggio, messaggio.codMessaggio) &&
        Objects.equals(codTipoMessaggio, messaggio.codTipoMessaggio) &&
        Objects.equals(codAmbito, messaggio.codAmbito) &&
        Objects.equals(intestazione, messaggio.intestazione) &&
        Objects.equals(testo, messaggio.testo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idMessaggio, codMessaggio, codTipoMessaggio, codAmbito, intestazione, testo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Messaggio {\n");
    
    sb.append("    idMessaggio: ").append(toIndentedString(idMessaggio)).append("\n");
    sb.append("    codMessaggio: ").append(toIndentedString(codMessaggio)).append("\n");
    sb.append("    codTipoMessaggio: ").append(toIndentedString(codTipoMessaggio)).append("\n");
    sb.append("    codAmbito: ").append(toIndentedString(codAmbito)).append("\n");
    sb.append("    intestazione: ").append(toIndentedString(intestazione)).append("\n");
    sb.append("    testo: ").append(toIndentedString(testo)).append("\n");
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

