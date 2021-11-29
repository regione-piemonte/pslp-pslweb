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

public class TipoContratto   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceMinisteriale = null;
  private String codiceSilp = null;
  private String descrizione = null;
  private Date dataInizioValidita = null;
  private Date dataFineValidita = null;
  private String codiceTipoLavoro = null;
  private Boolean ammissibileLegge68 = null;
  private Boolean ammissibileMissione = null;
  private Boolean ammissibileMobilita = null;
  private Boolean ammissibileStagionale = null;
  private Boolean ammissibileAgricoltura = null;
  private Boolean ammissibileFormaTD = null;
  private Boolean ammissibileFormaTI = null;

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
   * rappresenta la categoria S subordinato, A autonomo, R speciale
   **/
  

  @JsonProperty("codice_tipo_lavoro") 
 
  public String getCodiceTipoLavoro() {
    return codiceTipoLavoro;
  }
  public void setCodiceTipoLavoro(String codiceTipoLavoro) {
    this.codiceTipoLavoro = codiceTipoLavoro;
  }

  /**
   * indica se il tipo lavoro e&#39; ammesso per legge 68
   **/
  

  @JsonProperty("ammissibile_legge_68") 
 
  public Boolean isAmmissibileLegge68() {
    return ammissibileLegge68;
  }
  public void setAmmissibileLegge68(Boolean ammissibileLegge68) {
    this.ammissibileLegge68 = ammissibileLegge68;
  }

  /**
   * indica se il tipo lavoro ammette la missione
   **/
  

  @JsonProperty("ammissibile_missione") 
 
  public Boolean isAmmissibileMissione() {
    return ammissibileMissione;
  }
  public void setAmmissibileMissione(Boolean ammissibileMissione) {
    this.ammissibileMissione = ammissibileMissione;
  }

  /**
   * indica se il tipo di lavoro e&#39; ammissibile pe mobilita&#39;
   **/
  

  @JsonProperty("ammissibile_mobilita") 
 
  public Boolean isAmmissibileMobilita() {
    return ammissibileMobilita;
  }
  public void setAmmissibileMobilita(Boolean ammissibileMobilita) {
    this.ammissibileMobilita = ammissibileMobilita;
  }

  /**
   * indica se il tipo di lavoro e&#39; ammissibile come stagionale
   **/
  

  @JsonProperty("ammissibile_stagionale") 
 
  public Boolean isAmmissibileStagionale() {
    return ammissibileStagionale;
  }
  public void setAmmissibileStagionale(Boolean ammissibileStagionale) {
    this.ammissibileStagionale = ammissibileStagionale;
  }

  /**
   * indica se il tipo di lavoro e&#39; ammissibile per agricoltura
   **/
  

  @JsonProperty("ammissibile_agricoltura") 
 
  public Boolean isAmmissibileAgricoltura() {
    return ammissibileAgricoltura;
  }
  public void setAmmissibileAgricoltura(Boolean ammissibileAgricoltura) {
    this.ammissibileAgricoltura = ammissibileAgricoltura;
  }

  /**
   * indica se il tipo di lavoro ammette come forma il tempo determinato
   **/
  

  @JsonProperty("ammissibile_forma_TD") 
 
  public Boolean isAmmissibileFormaTD() {
    return ammissibileFormaTD;
  }
  public void setAmmissibileFormaTD(Boolean ammissibileFormaTD) {
    this.ammissibileFormaTD = ammissibileFormaTD;
  }

  /**
   * indica se il tipo di lavoro ammette come forma il tempo indeterminato
   **/
  

  @JsonProperty("ammissibile_forma_TI") 
 
  public Boolean isAmmissibileFormaTI() {
    return ammissibileFormaTI;
  }
  public void setAmmissibileFormaTI(Boolean ammissibileFormaTI) {
    this.ammissibileFormaTI = ammissibileFormaTI;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TipoContratto tipoContratto = (TipoContratto) o;
    return Objects.equals(codiceMinisteriale, tipoContratto.codiceMinisteriale) &&
        Objects.equals(codiceSilp, tipoContratto.codiceSilp) &&
        Objects.equals(descrizione, tipoContratto.descrizione) &&
        Objects.equals(dataInizioValidita, tipoContratto.dataInizioValidita) &&
        Objects.equals(dataFineValidita, tipoContratto.dataFineValidita) &&
        Objects.equals(codiceTipoLavoro, tipoContratto.codiceTipoLavoro) &&
        Objects.equals(ammissibileLegge68, tipoContratto.ammissibileLegge68) &&
        Objects.equals(ammissibileMissione, tipoContratto.ammissibileMissione) &&
        Objects.equals(ammissibileMobilita, tipoContratto.ammissibileMobilita) &&
        Objects.equals(ammissibileStagionale, tipoContratto.ammissibileStagionale) &&
        Objects.equals(ammissibileAgricoltura, tipoContratto.ammissibileAgricoltura) &&
        Objects.equals(ammissibileFormaTD, tipoContratto.ammissibileFormaTD) &&
        Objects.equals(ammissibileFormaTI, tipoContratto.ammissibileFormaTI);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceMinisteriale, codiceSilp, descrizione, dataInizioValidita, dataFineValidita, codiceTipoLavoro, ammissibileLegge68, ammissibileMissione, ammissibileMobilita, ammissibileStagionale, ammissibileAgricoltura, ammissibileFormaTD, ammissibileFormaTI);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TipoContratto {\n");
    
    sb.append("    codiceMinisteriale: ").append(toIndentedString(codiceMinisteriale)).append("\n");
    sb.append("    codiceSilp: ").append(toIndentedString(codiceSilp)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    dataInizioValidita: ").append(toIndentedString(dataInizioValidita)).append("\n");
    sb.append("    dataFineValidita: ").append(toIndentedString(dataFineValidita)).append("\n");
    sb.append("    codiceTipoLavoro: ").append(toIndentedString(codiceTipoLavoro)).append("\n");
    sb.append("    ammissibileLegge68: ").append(toIndentedString(ammissibileLegge68)).append("\n");
    sb.append("    ammissibileMissione: ").append(toIndentedString(ammissibileMissione)).append("\n");
    sb.append("    ammissibileMobilita: ").append(toIndentedString(ammissibileMobilita)).append("\n");
    sb.append("    ammissibileStagionale: ").append(toIndentedString(ammissibileStagionale)).append("\n");
    sb.append("    ammissibileAgricoltura: ").append(toIndentedString(ammissibileAgricoltura)).append("\n");
    sb.append("    ammissibileFormaTD: ").append(toIndentedString(ammissibileFormaTD)).append("\n");
    sb.append("    ammissibileFormaTI: ").append(toIndentedString(ammissibileFormaTI)).append("\n");
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

