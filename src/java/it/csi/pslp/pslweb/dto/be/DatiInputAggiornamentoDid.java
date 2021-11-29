/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.RispostaQuestionario;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class DatiInputAggiornamentoDid   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idAnagrafica = null;
  private Long idDid = null;
  private String codStatoDid = null;
  private String codiceFiscale = null;
  private Date dataDid = null;
  private Date dataStatoDid = null;
  private List<RispostaQuestionario> rispostaQuestionario = new ArrayList<RispostaQuestionario>();

  /**
   **/
  

  @JsonProperty("id_anagrafica") 
 
  public Long getIdAnagrafica() {
    return idAnagrafica;
  }
  public void setIdAnagrafica(Long idAnagrafica) {
    this.idAnagrafica = idAnagrafica;
  }

  /**
   **/
  

  @JsonProperty("id_did") 
 
  public Long getIdDid() {
    return idDid;
  }
  public void setIdDid(Long idDid) {
    this.idDid = idDid;
  }

  /**
   **/
  

  @JsonProperty("cod_stato_did") 
 
  public String getCodStatoDid() {
    return codStatoDid;
  }
  public void setCodStatoDid(String codStatoDid) {
    this.codStatoDid = codStatoDid;
  }

  /**
   **/
  

  @JsonProperty("codice_fiscale") 
 
  public String getCodiceFiscale() {
    return codiceFiscale;
  }
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   * data did
   **/
  

  @JsonProperty("data_did") 
 
  public Date getDataDid() {
    return dataDid;
  }
  public void setDataDid(Date dataDid) {
    this.dataDid = dataDid;
  }

  /**
   * data stato did
   **/
  

  @JsonProperty("data_stato_did") 
 
  public Date getDataStatoDid() {
    return dataStatoDid;
  }
  public void setDataStatoDid(Date dataStatoDid) {
    this.dataStatoDid = dataStatoDid;
  }

  /**
   **/
  

  @JsonProperty("risposta_questionario") 
 
  public List<RispostaQuestionario> getRispostaQuestionario() {
    return rispostaQuestionario;
  }
  public void setRispostaQuestionario(List<RispostaQuestionario> rispostaQuestionario) {
    this.rispostaQuestionario = rispostaQuestionario;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatiInputAggiornamentoDid datiInputAggiornamentoDid = (DatiInputAggiornamentoDid) o;
    return Objects.equals(idAnagrafica, datiInputAggiornamentoDid.idAnagrafica) &&
        Objects.equals(idDid, datiInputAggiornamentoDid.idDid) &&
        Objects.equals(codStatoDid, datiInputAggiornamentoDid.codStatoDid) &&
        Objects.equals(codiceFiscale, datiInputAggiornamentoDid.codiceFiscale) &&
        Objects.equals(dataDid, datiInputAggiornamentoDid.dataDid) &&
        Objects.equals(dataStatoDid, datiInputAggiornamentoDid.dataStatoDid) &&
        Objects.equals(rispostaQuestionario, datiInputAggiornamentoDid.rispostaQuestionario);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idAnagrafica, idDid, codStatoDid, codiceFiscale, dataDid, dataStatoDid, rispostaQuestionario);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DatiInputAggiornamentoDid {\n");
    
    sb.append("    idAnagrafica: ").append(toIndentedString(idAnagrafica)).append("\n");
    sb.append("    idDid: ").append(toIndentedString(idDid)).append("\n");
    sb.append("    codStatoDid: ").append(toIndentedString(codStatoDid)).append("\n");
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    dataDid: ").append(toIndentedString(dataDid)).append("\n");
    sb.append("    dataStatoDid: ").append(toIndentedString(dataStatoDid)).append("\n");
    sb.append("    rispostaQuestionario: ").append(toIndentedString(rispostaQuestionario)).append("\n");
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

