/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Privacy   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String descrAmbito = null;
  private BigDecimal idUtente = null;
  private BigDecimal idTutelato = null;
  private Boolean stato = null;
  private String codAmbito = null;
  private List<String> codeMsg = new ArrayList<String>();
  private Date dataPresaVisione = null;

  /**
   **/
  

  @JsonProperty("descr_ambito") 
 
  public String getDescrAmbito() {
    return descrAmbito;
  }
  public void setDescrAmbito(String descrAmbito) {
    this.descrAmbito = descrAmbito;
  }

  /**
   **/
  

  @JsonProperty("id_utente") 
 
  public BigDecimal getIdUtente() {
    return idUtente;
  }
  public void setIdUtente(BigDecimal idUtente) {
    this.idUtente = idUtente;
  }

  /**
   **/
  

  @JsonProperty("id_tutelato") 
 
  public BigDecimal getIdTutelato() {
    return idTutelato;
  }
  public void setIdTutelato(BigDecimal idTutelato) {
    this.idTutelato = idTutelato;
  }

  /**
   **/
  

  @JsonProperty("stato") 
 
  public Boolean isStato() {
    return stato;
  }
  public void setStato(Boolean stato) {
    this.stato = stato;
  }

  /**
   **/
  

  @JsonProperty("cod_ambito") 
 
  public String getCodAmbito() {
    return codAmbito;
  }
  public void setCodAmbito(String codAmbito) {
    this.codAmbito = codAmbito;
  }

  /**
   **/
  

  @JsonProperty("code_msg") 
 
  public List<String> getCodeMsg() {
    return codeMsg;
  }
  public void setCodeMsg(List<String> codeMsg) {
    this.codeMsg = codeMsg;
  }

  /**
   **/
  

  @JsonProperty("data_presa_visione") 
 
  public Date getDataPresaVisione() {
    return dataPresaVisione;
  }
  public void setDataPresaVisione(Date dataPresaVisione) {
    this.dataPresaVisione = dataPresaVisione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Privacy privacy = (Privacy) o;
    return Objects.equals(descrAmbito, privacy.descrAmbito) &&
        Objects.equals(idUtente, privacy.idUtente) &&
        Objects.equals(idTutelato, privacy.idTutelato) &&
        Objects.equals(stato, privacy.stato) &&
        Objects.equals(codAmbito, privacy.codAmbito) &&
        Objects.equals(codeMsg, privacy.codeMsg) &&
        Objects.equals(dataPresaVisione, privacy.dataPresaVisione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(descrAmbito, idUtente, idTutelato, stato, codAmbito, codeMsg, dataPresaVisione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Privacy {\n");
    
    sb.append("    descrAmbito: ").append(toIndentedString(descrAmbito)).append("\n");
    sb.append("    idUtente: ").append(toIndentedString(idUtente)).append("\n");
    sb.append("    idTutelato: ").append(toIndentedString(idTutelato)).append("\n");
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
    sb.append("    codAmbito: ").append(toIndentedString(codAmbito)).append("\n");
    sb.append("    codeMsg: ").append(toIndentedString(codeMsg)).append("\n");
    sb.append("    dataPresaVisione: ").append(toIndentedString(dataPresaVisione)).append("\n");
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

