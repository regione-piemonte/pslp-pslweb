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

public class VerbaleL68   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Date dataEmissione = null;
  private String flgSoggettoARevisione = null;
  private Date dataProssimaRevisione = null;

  /**
   * data emissione
   **/
  

  @JsonProperty("data_emissione") 
 
  public Date getDataEmissione() {
    return dataEmissione;
  }
  public void setDataEmissione(Date dataEmissione) {
    this.dataEmissione = dataEmissione;
  }

  /**
   **/
  

  @JsonProperty("flg_soggetto_a_revisione") 
 
  public String getFlgSoggettoARevisione() {
    return flgSoggettoARevisione;
  }
  public void setFlgSoggettoARevisione(String flgSoggettoARevisione) {
    this.flgSoggettoARevisione = flgSoggettoARevisione;
  }

  /**
   * data prossima revisione
   **/
  

  @JsonProperty("data_prossima_revisione") 
 
  public Date getDataProssimaRevisione() {
    return dataProssimaRevisione;
  }
  public void setDataProssimaRevisione(Date dataProssimaRevisione) {
    this.dataProssimaRevisione = dataProssimaRevisione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VerbaleL68 verbaleL68 = (VerbaleL68) o;
    return Objects.equals(dataEmissione, verbaleL68.dataEmissione) &&
        Objects.equals(flgSoggettoARevisione, verbaleL68.flgSoggettoARevisione) &&
        Objects.equals(dataProssimaRevisione, verbaleL68.dataProssimaRevisione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataEmissione, flgSoggettoARevisione, dataProssimaRevisione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VerbaleL68 {\n");
    
    sb.append("    dataEmissione: ").append(toIndentedString(dataEmissione)).append("\n");
    sb.append("    flgSoggettoARevisione: ").append(toIndentedString(flgSoggettoARevisione)).append("\n");
    sb.append("    dataProssimaRevisione: ").append(toIndentedString(dataProssimaRevisione)).append("\n");
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

