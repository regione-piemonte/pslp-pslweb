/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class PermessoDiSoggiorno   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String titolo = null;
  private String numero = null;
  private Date dataScadenza = null;
  private String motivoRilascio = null;
  private String codiceMinisterialeMotivoRilascio = null;
  private String codiceMinisterialeStatusExtraUe = null;

  /**
   **/
  

  @JsonProperty("titolo") 
 
  public String getTitolo() {
    return titolo;
  }
  public void setTitolo(String titolo) {
    this.titolo = titolo;
  }

  /**
   **/
  

  @JsonProperty("numero") 
 
  public String getNumero() {
    return numero;
  }
  public void setNumero(String numero) {
    this.numero = numero;
  }

  /**
   **/
  

  @JsonProperty("data_scadenza") 
 
  public Date getDataScadenza() {
    return dataScadenza;
  }
  public void setDataScadenza(Date dataScadenza) {
    this.dataScadenza = dataScadenza;
  }

  /**
   **/
  

  @JsonProperty("motivo_rilascio") 
 
  public String getMotivoRilascio() {
    return motivoRilascio;
  }
  public void setMotivoRilascio(String motivoRilascio) {
    this.motivoRilascio = motivoRilascio;
  }

  /**
   **/
  

  @JsonProperty("codice_ministeriale_motivo_rilascio") 
 
  public String getCodiceMinisterialeMotivoRilascio() {
    return codiceMinisterialeMotivoRilascio;
  }
  public void setCodiceMinisterialeMotivoRilascio(String codiceMinisterialeMotivoRilascio) {
    this.codiceMinisterialeMotivoRilascio = codiceMinisterialeMotivoRilascio;
  }

  /**
   **/
  

  @JsonProperty("codice_ministeriale_status_extra_ue") 
 
  public String getCodiceMinisterialeStatusExtraUe() {
    return codiceMinisterialeStatusExtraUe;
  }
  public void setCodiceMinisterialeStatusExtraUe(String codiceMinisterialeStatusExtraUe) {
    this.codiceMinisterialeStatusExtraUe = codiceMinisterialeStatusExtraUe;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PermessoDiSoggiorno permessoDiSoggiorno = (PermessoDiSoggiorno) o;
    return Objects.equals(titolo, permessoDiSoggiorno.titolo) &&
        Objects.equals(numero, permessoDiSoggiorno.numero) &&
        Objects.equals(dataScadenza, permessoDiSoggiorno.dataScadenza) &&
        Objects.equals(motivoRilascio, permessoDiSoggiorno.motivoRilascio) &&
        Objects.equals(codiceMinisterialeMotivoRilascio, permessoDiSoggiorno.codiceMinisterialeMotivoRilascio) &&
        Objects.equals(codiceMinisterialeStatusExtraUe, permessoDiSoggiorno.codiceMinisterialeStatusExtraUe);
  }

  @Override
  public int hashCode() {
    return Objects.hash(titolo, numero, dataScadenza, motivoRilascio, codiceMinisterialeMotivoRilascio, codiceMinisterialeStatusExtraUe);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PermessoDiSoggiorno {\n");
    
    sb.append("    titolo: ").append(toIndentedString(titolo)).append("\n");
    sb.append("    numero: ").append(toIndentedString(numero)).append("\n");
    sb.append("    dataScadenza: ").append(toIndentedString(dataScadenza)).append("\n");
    sb.append("    motivoRilascio: ").append(toIndentedString(motivoRilascio)).append("\n");
    sb.append("    codiceMinisterialeMotivoRilascio: ").append(toIndentedString(codiceMinisterialeMotivoRilascio)).append("\n");
    sb.append("    codiceMinisterialeStatusExtraUe: ").append(toIndentedString(codiceMinisterialeStatusExtraUe)).append("\n");
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

