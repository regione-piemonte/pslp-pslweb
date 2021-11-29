/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Comune;
import it.csi.pslp.pslweb.dto.be.Ente;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class DomandaRDC   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idSilLavDomandaRdc = null;
  private String codice = null;
  private Date dataDomanda = null;
  private Date dataNotificaSilp = null;
  private String statoDomanda = null;
  private String statoPoliticaRc1 = null;
  private Date dataStatoPoliticaRc1 = null;
  private Comune comuneResidenza = null;
  private Ente enteResidenza = null;

  /**
   **/
  

  @JsonProperty("id_sil_lav_domanda_rdc") 
 
  public Long getIdSilLavDomandaRdc() {
    return idSilLavDomandaRdc;
  }
  public void setIdSilLavDomandaRdc(Long idSilLavDomandaRdc) {
    this.idSilLavDomandaRdc = idSilLavDomandaRdc;
  }

  /**
   **/
  

  @JsonProperty("codice") 
 
  public String getCodice() {
    return codice;
  }
  public void setCodice(String codice) {
    this.codice = codice;
  }

  /**
   **/
  

  @JsonProperty("data_domanda") 
 
  public Date getDataDomanda() {
    return dataDomanda;
  }
  public void setDataDomanda(Date dataDomanda) {
    this.dataDomanda = dataDomanda;
  }

  /**
   **/
  

  @JsonProperty("data_notifica_silp") 
 
  public Date getDataNotificaSilp() {
    return dataNotificaSilp;
  }
  public void setDataNotificaSilp(Date dataNotificaSilp) {
    this.dataNotificaSilp = dataNotificaSilp;
  }

  /**
   **/
  

  @JsonProperty("stato_domanda") 
 
  public String getStatoDomanda() {
    return statoDomanda;
  }
  public void setStatoDomanda(String statoDomanda) {
    this.statoDomanda = statoDomanda;
  }

  /**
   **/
  

  @JsonProperty("stato_politica_rc1") 
 
  public String getStatoPoliticaRc1() {
    return statoPoliticaRc1;
  }
  public void setStatoPoliticaRc1(String statoPoliticaRc1) {
    this.statoPoliticaRc1 = statoPoliticaRc1;
  }

  /**
   **/
  

  @JsonProperty("data_stato_politica_rc1") 
 
  public Date getDataStatoPoliticaRc1() {
    return dataStatoPoliticaRc1;
  }
  public void setDataStatoPoliticaRc1(Date dataStatoPoliticaRc1) {
    this.dataStatoPoliticaRc1 = dataStatoPoliticaRc1;
  }

  /**
   **/
  

  @JsonProperty("comune_residenza") 
 
  public Comune getComuneResidenza() {
    return comuneResidenza;
  }
  public void setComuneResidenza(Comune comuneResidenza) {
    this.comuneResidenza = comuneResidenza;
  }

  /**
   **/
  

  @JsonProperty("ente_residenza") 
 
  public Ente getEnteResidenza() {
    return enteResidenza;
  }
  public void setEnteResidenza(Ente enteResidenza) {
    this.enteResidenza = enteResidenza;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DomandaRDC domandaRDC = (DomandaRDC) o;
    return Objects.equals(idSilLavDomandaRdc, domandaRDC.idSilLavDomandaRdc) &&
        Objects.equals(codice, domandaRDC.codice) &&
        Objects.equals(dataDomanda, domandaRDC.dataDomanda) &&
        Objects.equals(dataNotificaSilp, domandaRDC.dataNotificaSilp) &&
        Objects.equals(statoDomanda, domandaRDC.statoDomanda) &&
        Objects.equals(statoPoliticaRc1, domandaRDC.statoPoliticaRc1) &&
        Objects.equals(dataStatoPoliticaRc1, domandaRDC.dataStatoPoliticaRc1) &&
        Objects.equals(comuneResidenza, domandaRDC.comuneResidenza) &&
        Objects.equals(enteResidenza, domandaRDC.enteResidenza);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idSilLavDomandaRdc, codice, dataDomanda, dataNotificaSilp, statoDomanda, statoPoliticaRc1, dataStatoPoliticaRc1, comuneResidenza, enteResidenza);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DomandaRDC {\n");
    
    sb.append("    idSilLavDomandaRdc: ").append(toIndentedString(idSilLavDomandaRdc)).append("\n");
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    dataDomanda: ").append(toIndentedString(dataDomanda)).append("\n");
    sb.append("    dataNotificaSilp: ").append(toIndentedString(dataNotificaSilp)).append("\n");
    sb.append("    statoDomanda: ").append(toIndentedString(statoDomanda)).append("\n");
    sb.append("    statoPoliticaRc1: ").append(toIndentedString(statoPoliticaRc1)).append("\n");
    sb.append("    dataStatoPoliticaRc1: ").append(toIndentedString(dataStatoPoliticaRc1)).append("\n");
    sb.append("    comuneResidenza: ").append(toIndentedString(comuneResidenza)).append("\n");
    sb.append("    enteResidenza: ").append(toIndentedString(enteResidenza)).append("\n");
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

