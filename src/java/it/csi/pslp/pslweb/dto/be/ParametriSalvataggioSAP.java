/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.SchedaAnagraficoProfessionale;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ParametriSalvataggioSAP   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceAmbito = null;
  private SchedaAnagraficoProfessionale sap = null;
  private List<String> sezioni = new ArrayList<String>();

  /**
   **/
  

  @JsonProperty("codice_ambito") 
 
  public String getCodiceAmbito() {
    return codiceAmbito;
  }
  public void setCodiceAmbito(String codiceAmbito) {
    this.codiceAmbito = codiceAmbito;
  }

  /**
   **/
  

  @JsonProperty("sap") 
 
  public SchedaAnagraficoProfessionale getSap() {
    return sap;
  }
  public void setSap(SchedaAnagraficoProfessionale sap) {
    this.sap = sap;
  }

  /**
   **/
  

  @JsonProperty("sezioni") 
 
  public List<String> getSezioni() {
    return sezioni;
  }
  public void setSezioni(List<String> sezioni) {
    this.sezioni = sezioni;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParametriSalvataggioSAP parametriSalvataggioSAP = (ParametriSalvataggioSAP) o;
    return Objects.equals(codiceAmbito, parametriSalvataggioSAP.codiceAmbito) &&
        Objects.equals(sap, parametriSalvataggioSAP.sap) &&
        Objects.equals(sezioni, parametriSalvataggioSAP.sezioni);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceAmbito, sap, sezioni);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParametriSalvataggioSAP {\n");
    
    sb.append("    codiceAmbito: ").append(toIndentedString(codiceAmbito)).append("\n");
    sb.append("    sap: ").append(toIndentedString(sap)).append("\n");
    sb.append("    sezioni: ").append(toIndentedString(sezioni)).append("\n");
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

