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

public class ReferenteServizioTerritoriale   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String cognomeReferente = null;
  private String nomeReferente = null;
  private String enteReferente = null;
  private String emailReferente = null;
  private String telefonoReferente = null;
  private String cellulareReferente = null;

  /**
   **/
  

  @JsonProperty("cognome_referente") 
 
  public String getCognomeReferente() {
    return cognomeReferente;
  }
  public void setCognomeReferente(String cognomeReferente) {
    this.cognomeReferente = cognomeReferente;
  }

  /**
   **/
  

  @JsonProperty("nome_referente") 
 
  public String getNomeReferente() {
    return nomeReferente;
  }
  public void setNomeReferente(String nomeReferente) {
    this.nomeReferente = nomeReferente;
  }

  /**
   **/
  

  @JsonProperty("ente_referente") 
 
  public String getEnteReferente() {
    return enteReferente;
  }
  public void setEnteReferente(String enteReferente) {
    this.enteReferente = enteReferente;
  }

  /**
   **/
  

  @JsonProperty("email_referente") 
 
  public String getEmailReferente() {
    return emailReferente;
  }
  public void setEmailReferente(String emailReferente) {
    this.emailReferente = emailReferente;
  }

  /**
   **/
  

  @JsonProperty("telefono_referente") 
 
  public String getTelefonoReferente() {
    return telefonoReferente;
  }
  public void setTelefonoReferente(String telefonoReferente) {
    this.telefonoReferente = telefonoReferente;
  }

  /**
   **/
  

  @JsonProperty("cellulare_referente") 
 
  public String getCellulareReferente() {
    return cellulareReferente;
  }
  public void setCellulareReferente(String cellulareReferente) {
    this.cellulareReferente = cellulareReferente;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReferenteServizioTerritoriale referenteServizioTerritoriale = (ReferenteServizioTerritoriale) o;
    return Objects.equals(cognomeReferente, referenteServizioTerritoriale.cognomeReferente) &&
        Objects.equals(nomeReferente, referenteServizioTerritoriale.nomeReferente) &&
        Objects.equals(enteReferente, referenteServizioTerritoriale.enteReferente) &&
        Objects.equals(emailReferente, referenteServizioTerritoriale.emailReferente) &&
        Objects.equals(telefonoReferente, referenteServizioTerritoriale.telefonoReferente) &&
        Objects.equals(cellulareReferente, referenteServizioTerritoriale.cellulareReferente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cognomeReferente, nomeReferente, enteReferente, emailReferente, telefonoReferente, cellulareReferente);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReferenteServizioTerritoriale {\n");
    
    sb.append("    cognomeReferente: ").append(toIndentedString(cognomeReferente)).append("\n");
    sb.append("    nomeReferente: ").append(toIndentedString(nomeReferente)).append("\n");
    sb.append("    enteReferente: ").append(toIndentedString(enteReferente)).append("\n");
    sb.append("    emailReferente: ").append(toIndentedString(emailReferente)).append("\n");
    sb.append("    telefonoReferente: ").append(toIndentedString(telefonoReferente)).append("\n");
    sb.append("    cellulareReferente: ").append(toIndentedString(cellulareReferente)).append("\n");
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

