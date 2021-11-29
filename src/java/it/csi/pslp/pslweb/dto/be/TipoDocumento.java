/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.TipoDocumentoMimeType;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class TipoDocumento   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codice = null;
  private String descrizione = null;
  private List<TipoDocumentoMimeType> mimeType = new ArrayList<TipoDocumentoMimeType>();
  private String flgObbligatorio = null;

  /**
   * codice
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
   **/
  

  @JsonProperty("mime_type") 
 
  public List<TipoDocumentoMimeType> getMimeType() {
    return mimeType;
  }
  public void setMimeType(List<TipoDocumentoMimeType> mimeType) {
    this.mimeType = mimeType;
  }

  /**
   * flg di obbligatorieta
   **/
  

  @JsonProperty("flg_obbligatorio") 
 
  public String getFlgObbligatorio() {
    return flgObbligatorio;
  }
  public void setFlgObbligatorio(String flgObbligatorio) {
    this.flgObbligatorio = flgObbligatorio;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TipoDocumento tipoDocumento = (TipoDocumento) o;
    return Objects.equals(codice, tipoDocumento.codice) &&
        Objects.equals(descrizione, tipoDocumento.descrizione) &&
        Objects.equals(mimeType, tipoDocumento.mimeType) &&
        Objects.equals(flgObbligatorio, tipoDocumento.flgObbligatorio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codice, descrizione, mimeType, flgObbligatorio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TipoDocumento {\n");
    
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    mimeType: ").append(toIndentedString(mimeType)).append("\n");
    sb.append("    flgObbligatorio: ").append(toIndentedString(flgObbligatorio)).append("\n");
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

