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

public class CategoriaProtettaDisab   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String idSilp = null;
  private Long numGradoDisabilitaMin = null;
  private String flgAttivareGradoOCategoria = null;
  private String descrizione = null;

  /**
   **/
  

  @JsonProperty("id_silp") 
 
  public String getIdSilp() {
    return idSilp;
  }
  public void setIdSilp(String idSilp) {
    this.idSilp = idSilp;
  }

  /**
   **/
  

  @JsonProperty("num_grado_disabilita_min") 
 
  public Long getNumGradoDisabilitaMin() {
    return numGradoDisabilitaMin;
  }
  public void setNumGradoDisabilitaMin(Long numGradoDisabilitaMin) {
    this.numGradoDisabilitaMin = numGradoDisabilitaMin;
  }

  /**
   **/
  

  @JsonProperty("flg_attivare_grado_o_categoria") 
 
  public String getFlgAttivareGradoOCategoria() {
    return flgAttivareGradoOCategoria;
  }
  public void setFlgAttivareGradoOCategoria(String flgAttivareGradoOCategoria) {
    this.flgAttivareGradoOCategoria = flgAttivareGradoOCategoria;
  }

  /**
   **/
  

  @JsonProperty("descrizione") 
 
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
    CategoriaProtettaDisab categoriaProtettaDisab = (CategoriaProtettaDisab) o;
    return Objects.equals(idSilp, categoriaProtettaDisab.idSilp) &&
        Objects.equals(numGradoDisabilitaMin, categoriaProtettaDisab.numGradoDisabilitaMin) &&
        Objects.equals(flgAttivareGradoOCategoria, categoriaProtettaDisab.flgAttivareGradoOCategoria) &&
        Objects.equals(descrizione, categoriaProtettaDisab.descrizione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idSilp, numGradoDisabilitaMin, flgAttivareGradoOCategoria, descrizione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CategoriaProtettaDisab {\n");
    
    sb.append("    idSilp: ").append(toIndentedString(idSilp)).append("\n");
    sb.append("    numGradoDisabilitaMin: ").append(toIndentedString(numGradoDisabilitaMin)).append("\n");
    sb.append("    flgAttivareGradoOCategoria: ").append(toIndentedString(flgAttivareGradoOCategoria)).append("\n");
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

