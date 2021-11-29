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

public class MotivoFamiliareACarico   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String idSilMotivo = null;
  private String idSilGradoParentela = null;
  private String descrizione = null;

  /**
   **/
  

  @JsonProperty("id_sil_motivo") 
 
  public String getIdSilMotivo() {
    return idSilMotivo;
  }
  public void setIdSilMotivo(String idSilMotivo) {
    this.idSilMotivo = idSilMotivo;
  }

  /**
   **/
  

  @JsonProperty("id_sil_grado_parentela") 
 
  public String getIdSilGradoParentela() {
    return idSilGradoParentela;
  }
  public void setIdSilGradoParentela(String idSilGradoParentela) {
    this.idSilGradoParentela = idSilGradoParentela;
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
    MotivoFamiliareACarico motivoFamiliareACarico = (MotivoFamiliareACarico) o;
    return Objects.equals(idSilMotivo, motivoFamiliareACarico.idSilMotivo) &&
        Objects.equals(idSilGradoParentela, motivoFamiliareACarico.idSilGradoParentela) &&
        Objects.equals(descrizione, motivoFamiliareACarico.descrizione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idSilMotivo, idSilGradoParentela, descrizione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MotivoFamiliareACarico {\n");
    
    sb.append("    idSilMotivo: ").append(toIndentedString(idSilMotivo)).append("\n");
    sb.append("    idSilGradoParentela: ").append(toIndentedString(idSilGradoParentela)).append("\n");
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

