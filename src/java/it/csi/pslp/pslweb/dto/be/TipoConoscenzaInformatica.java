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

public class TipoConoscenzaInformatica   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceMinisteriale = null;
  private String codiceSilp = null;
  private String descrizione = null;
  private Date dataInizioValidita = null;
  private Date dataFineValidita = null;
  private String codiceCategoriaConoscenzaInformatica = null;

  /**
   **/
  

  @JsonProperty("codice_ministeriale") 
 
  public String getCodiceMinisteriale() {
    return codiceMinisteriale;
  }
  public void setCodiceMinisteriale(String codiceMinisteriale) {
    this.codiceMinisteriale = codiceMinisteriale;
  }

  /**
   **/
  

  @JsonProperty("codice_silp") 
 
  public String getCodiceSilp() {
    return codiceSilp;
  }
  public void setCodiceSilp(String codiceSilp) {
    this.codiceSilp = codiceSilp;
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

  /**
   **/
  

  @JsonProperty("data_inizio_validita") 
 
  public Date getDataInizioValidita() {
    return dataInizioValidita;
  }
  public void setDataInizioValidita(Date dataInizioValidita) {
    this.dataInizioValidita = dataInizioValidita;
  }

  /**
   **/
  

  @JsonProperty("data_fine_validita") 
 
  public Date getDataFineValidita() {
    return dataFineValidita;
  }
  public void setDataFineValidita(Date dataFineValidita) {
    this.dataFineValidita = dataFineValidita;
  }

  /**
   **/
  

  @JsonProperty("codice_categoria_conoscenza_informatica") 
 
  public String getCodiceCategoriaConoscenzaInformatica() {
    return codiceCategoriaConoscenzaInformatica;
  }
  public void setCodiceCategoriaConoscenzaInformatica(String codiceCategoriaConoscenzaInformatica) {
    this.codiceCategoriaConoscenzaInformatica = codiceCategoriaConoscenzaInformatica;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TipoConoscenzaInformatica tipoConoscenzaInformatica = (TipoConoscenzaInformatica) o;
    return Objects.equals(codiceMinisteriale, tipoConoscenzaInformatica.codiceMinisteriale) &&
        Objects.equals(codiceSilp, tipoConoscenzaInformatica.codiceSilp) &&
        Objects.equals(descrizione, tipoConoscenzaInformatica.descrizione) &&
        Objects.equals(dataInizioValidita, tipoConoscenzaInformatica.dataInizioValidita) &&
        Objects.equals(dataFineValidita, tipoConoscenzaInformatica.dataFineValidita) &&
        Objects.equals(codiceCategoriaConoscenzaInformatica, tipoConoscenzaInformatica.codiceCategoriaConoscenzaInformatica);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceMinisteriale, codiceSilp, descrizione, dataInizioValidita, dataFineValidita, codiceCategoriaConoscenzaInformatica);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TipoConoscenzaInformatica {\n");
    
    sb.append("    codiceMinisteriale: ").append(toIndentedString(codiceMinisteriale)).append("\n");
    sb.append("    codiceSilp: ").append(toIndentedString(codiceSilp)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    dataInizioValidita: ").append(toIndentedString(dataInizioValidita)).append("\n");
    sb.append("    dataFineValidita: ").append(toIndentedString(dataFineValidita)).append("\n");
    sb.append("    codiceCategoriaConoscenzaInformatica: ").append(toIndentedString(codiceCategoriaConoscenzaInformatica)).append("\n");
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

