/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.PrenotazioneIncontro;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class DatiInputStatoAdesione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idAnagrafica = null;
  private String codiceFiscale = null;
  private Date dataAdesione = null;
  private String identificativoSap = null;
  private Long idAdesione = null;
  private String codiceStatoAdesione = null;
  private Date dataStatoAdesione = null;
  private String motivo = null;
  private String codiceFiscaleOperatore = null;
  private PrenotazioneIncontro appuntamentoDaCancellare = null;

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
  

  @JsonProperty("codice_fiscale") 
 
  public String getCodiceFiscale() {
    return codiceFiscale;
  }
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   **/
  

  @JsonProperty("data_adesione") 
 
  public Date getDataAdesione() {
    return dataAdesione;
  }
  public void setDataAdesione(Date dataAdesione) {
    this.dataAdesione = dataAdesione;
  }

  /**
   **/
  

  @JsonProperty("identificativo_sap") 
 
  public String getIdentificativoSap() {
    return identificativoSap;
  }
  public void setIdentificativoSap(String identificativoSap) {
    this.identificativoSap = identificativoSap;
  }

  /**
   **/
  

  @JsonProperty("id_adesione") 
 
  public Long getIdAdesione() {
    return idAdesione;
  }
  public void setIdAdesione(Long idAdesione) {
    this.idAdesione = idAdesione;
  }

  /**
   **/
  

  @JsonProperty("codice_stato_adesione") 
 
  public String getCodiceStatoAdesione() {
    return codiceStatoAdesione;
  }
  public void setCodiceStatoAdesione(String codiceStatoAdesione) {
    this.codiceStatoAdesione = codiceStatoAdesione;
  }

  /**
   **/
  

  @JsonProperty("data_stato_adesione") 
 
  public Date getDataStatoAdesione() {
    return dataStatoAdesione;
  }
  public void setDataStatoAdesione(Date dataStatoAdesione) {
    this.dataStatoAdesione = dataStatoAdesione;
  }

  /**
   **/
  

  @JsonProperty("motivo") 
 
  public String getMotivo() {
    return motivo;
  }
  public void setMotivo(String motivo) {
    this.motivo = motivo;
  }

  /**
   **/
  

  @JsonProperty("codice_fiscale_operatore") 
 
  public String getCodiceFiscaleOperatore() {
    return codiceFiscaleOperatore;
  }
  public void setCodiceFiscaleOperatore(String codiceFiscaleOperatore) {
    this.codiceFiscaleOperatore = codiceFiscaleOperatore;
  }

  /**
   **/
  

  @JsonProperty("appuntamento_da_cancellare") 
 
  public PrenotazioneIncontro getAppuntamentoDaCancellare() {
    return appuntamentoDaCancellare;
  }
  public void setAppuntamentoDaCancellare(PrenotazioneIncontro appuntamentoDaCancellare) {
    this.appuntamentoDaCancellare = appuntamentoDaCancellare;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatiInputStatoAdesione datiInputStatoAdesione = (DatiInputStatoAdesione) o;
    return Objects.equals(idAnagrafica, datiInputStatoAdesione.idAnagrafica) &&
        Objects.equals(codiceFiscale, datiInputStatoAdesione.codiceFiscale) &&
        Objects.equals(dataAdesione, datiInputStatoAdesione.dataAdesione) &&
        Objects.equals(identificativoSap, datiInputStatoAdesione.identificativoSap) &&
        Objects.equals(idAdesione, datiInputStatoAdesione.idAdesione) &&
        Objects.equals(codiceStatoAdesione, datiInputStatoAdesione.codiceStatoAdesione) &&
        Objects.equals(dataStatoAdesione, datiInputStatoAdesione.dataStatoAdesione) &&
        Objects.equals(motivo, datiInputStatoAdesione.motivo) &&
        Objects.equals(codiceFiscaleOperatore, datiInputStatoAdesione.codiceFiscaleOperatore) &&
        Objects.equals(appuntamentoDaCancellare, datiInputStatoAdesione.appuntamentoDaCancellare);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idAnagrafica, codiceFiscale, dataAdesione, identificativoSap, idAdesione, codiceStatoAdesione, dataStatoAdesione, motivo, codiceFiscaleOperatore, appuntamentoDaCancellare);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DatiInputStatoAdesione {\n");
    
    sb.append("    idAnagrafica: ").append(toIndentedString(idAnagrafica)).append("\n");
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    dataAdesione: ").append(toIndentedString(dataAdesione)).append("\n");
    sb.append("    identificativoSap: ").append(toIndentedString(identificativoSap)).append("\n");
    sb.append("    idAdesione: ").append(toIndentedString(idAdesione)).append("\n");
    sb.append("    codiceStatoAdesione: ").append(toIndentedString(codiceStatoAdesione)).append("\n");
    sb.append("    dataStatoAdesione: ").append(toIndentedString(dataStatoAdesione)).append("\n");
    sb.append("    motivo: ").append(toIndentedString(motivo)).append("\n");
    sb.append("    codiceFiscaleOperatore: ").append(toIndentedString(codiceFiscaleOperatore)).append("\n");
    sb.append("    appuntamentoDaCancellare: ").append(toIndentedString(appuntamentoDaCancellare)).append("\n");
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

