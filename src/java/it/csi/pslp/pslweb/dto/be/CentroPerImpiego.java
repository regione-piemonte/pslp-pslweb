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

public class CentroPerImpiego   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codice = null;
  private String descrizione = null;
  private Boolean flgAttivo = null;
  private Long idCpiSilp = null;
  private String codiceMinisterialeProvincia = null;

  /**
   * codice centro per impiego
   **/
  

  @JsonProperty("codice") 
 
  public String getCodice() {
    return codice;
  }
  public void setCodice(String codice) {
    this.codice = codice;
  }

  /**
   * descrizione
   **/
  

  @JsonProperty("descrizione") 
 
  public String getDescrizione() {
    return descrizione;
  }
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   * flg attivo
   **/
  

  @JsonProperty("flg_attivo") 
 
  public Boolean isFlgAttivo() {
    return flgAttivo;
  }
  public void setFlgAttivo(Boolean flgAttivo) {
    this.flgAttivo = flgAttivo;
  }

  /**
   * identificativo univoco su silp
   **/
  

  @JsonProperty("id_cpi_silp") 
 
  public Long getIdCpiSilp() {
    return idCpiSilp;
  }
  public void setIdCpiSilp(Long idCpiSilp) {
    this.idCpiSilp = idCpiSilp;
  }

  /**
   * identificativo univoco su silp della provincia
   **/
  

  @JsonProperty("codice_ministeriale_provincia") 
 
  public String getCodiceMinisterialeProvincia() {
    return codiceMinisterialeProvincia;
  }
  public void setCodiceMinisterialeProvincia(String codiceMinisterialeProvincia) {
    this.codiceMinisterialeProvincia = codiceMinisterialeProvincia;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CentroPerImpiego centroPerImpiego = (CentroPerImpiego) o;
    return Objects.equals(codice, centroPerImpiego.codice) &&
        Objects.equals(descrizione, centroPerImpiego.descrizione) &&
        Objects.equals(flgAttivo, centroPerImpiego.flgAttivo) &&
        Objects.equals(idCpiSilp, centroPerImpiego.idCpiSilp) &&
        Objects.equals(codiceMinisterialeProvincia, centroPerImpiego.codiceMinisterialeProvincia);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codice, descrizione, flgAttivo, idCpiSilp, codiceMinisterialeProvincia);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CentroPerImpiego {\n");
    
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    flgAttivo: ").append(toIndentedString(flgAttivo)).append("\n");
    sb.append("    idCpiSilp: ").append(toIndentedString(idCpiSilp)).append("\n");
    sb.append("    codiceMinisterialeProvincia: ").append(toIndentedString(codiceMinisterialeProvincia)).append("\n");
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

