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

public class TipoDocumentoMimeType   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceEstensione = null;
  private String descrizioneEstensione = null;
  private String descrizioneMimeType = null;

  /**
   **/
  

  @JsonProperty("codice_estensione") 
 
  public String getCodiceEstensione() {
    return codiceEstensione;
  }
  public void setCodiceEstensione(String codiceEstensione) {
    this.codiceEstensione = codiceEstensione;
  }

  /**
   **/
  

  @JsonProperty("descrizione_estensione") 
 
  public String getDescrizioneEstensione() {
    return descrizioneEstensione;
  }
  public void setDescrizioneEstensione(String descrizioneEstensione) {
    this.descrizioneEstensione = descrizioneEstensione;
  }

  /**
   **/
  

  @JsonProperty("descrizione_mime_type") 
 
  public String getDescrizioneMimeType() {
    return descrizioneMimeType;
  }
  public void setDescrizioneMimeType(String descrizioneMimeType) {
    this.descrizioneMimeType = descrizioneMimeType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TipoDocumentoMimeType tipoDocumentoMimeType = (TipoDocumentoMimeType) o;
    return Objects.equals(codiceEstensione, tipoDocumentoMimeType.codiceEstensione) &&
        Objects.equals(descrizioneEstensione, tipoDocumentoMimeType.descrizioneEstensione) &&
        Objects.equals(descrizioneMimeType, tipoDocumentoMimeType.descrizioneMimeType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceEstensione, descrizioneEstensione, descrizioneMimeType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TipoDocumentoMimeType {\n");
    
    sb.append("    codiceEstensione: ").append(toIndentedString(codiceEstensione)).append("\n");
    sb.append("    descrizioneEstensione: ").append(toIndentedString(descrizioneEstensione)).append("\n");
    sb.append("    descrizioneMimeType: ").append(toIndentedString(descrizioneMimeType)).append("\n");
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

