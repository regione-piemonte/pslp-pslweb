/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.DatiAnagraficiFamiliareCollocamentoMirato;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class FamiliareACaricoCollocamentoMirato   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceTipoMotivo = null;
  private DatiAnagraficiFamiliareCollocamentoMirato datiAnagrafici = null;

  /**
   * codice tipo motivo di carico
   **/
  

  @JsonProperty("codiceTipoMotivo") 
 
  public String getCodiceTipoMotivo() {
    return codiceTipoMotivo;
  }
  public void setCodiceTipoMotivo(String codiceTipoMotivo) {
    this.codiceTipoMotivo = codiceTipoMotivo;
  }

  /**
   **/
  

  @JsonProperty("datiAnagrafici") 
 
  public DatiAnagraficiFamiliareCollocamentoMirato getDatiAnagrafici() {
    return datiAnagrafici;
  }
  public void setDatiAnagrafici(DatiAnagraficiFamiliareCollocamentoMirato datiAnagrafici) {
    this.datiAnagrafici = datiAnagrafici;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FamiliareACaricoCollocamentoMirato familiareACaricoCollocamentoMirato = (FamiliareACaricoCollocamentoMirato) o;
    return Objects.equals(codiceTipoMotivo, familiareACaricoCollocamentoMirato.codiceTipoMotivo) &&
        Objects.equals(datiAnagrafici, familiareACaricoCollocamentoMirato.datiAnagrafici);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceTipoMotivo, datiAnagrafici);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FamiliareACaricoCollocamentoMirato {\n");
    
    sb.append("    codiceTipoMotivo: ").append(toIndentedString(codiceTipoMotivo)).append("\n");
    sb.append("    datiAnagrafici: ").append(toIndentedString(datiAnagrafici)).append("\n");
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

