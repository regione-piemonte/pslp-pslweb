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

public class AdesioneYG   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idSilLavAdesione = null;
  private String codice = null;
  private Date dataAdesione = null;
  private String codiceFiscale = null;
  private String identificativoSap = null;
  private Date dataStatoCorrente = null;
  private String statoCorrenteFinale = null;
  private String descrizione = null;
  private String regione = null;
  private String flgAnpalStatoCorrente = null;
  private String motivoRifiutoStatoCorrente = null;
  private String flgRifiutoStatoCorrente = null;
  private Date dataRifiuto = null;
  private Boolean presenzaPiuAdesioniAperte = null;

  /**
   **/
  

  @JsonProperty("id_sil_lav_adesione") 
 
  public Long getIdSilLavAdesione() {
    return idSilLavAdesione;
  }
  public void setIdSilLavAdesione(Long idSilLavAdesione) {
    this.idSilLavAdesione = idSilLavAdesione;
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
  

  @JsonProperty("data_adesione") 
 
  public Date getDataAdesione() {
    return dataAdesione;
  }
  public void setDataAdesione(Date dataAdesione) {
    this.dataAdesione = dataAdesione;
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
  

  @JsonProperty("identificativo_sap") 
 
  public String getIdentificativoSap() {
    return identificativoSap;
  }
  public void setIdentificativoSap(String identificativoSap) {
    this.identificativoSap = identificativoSap;
  }

  /**
   **/
  

  @JsonProperty("data_stato_corrente") 
 
  public Date getDataStatoCorrente() {
    return dataStatoCorrente;
  }
  public void setDataStatoCorrente(Date dataStatoCorrente) {
    this.dataStatoCorrente = dataStatoCorrente;
  }

  /**
   **/
  

  @JsonProperty("stato_corrente_finale") 
 
  public String getStatoCorrenteFinale() {
    return statoCorrenteFinale;
  }
  public void setStatoCorrenteFinale(String statoCorrenteFinale) {
    this.statoCorrenteFinale = statoCorrenteFinale;
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
  

  @JsonProperty("regione") 
 
  public String getRegione() {
    return regione;
  }
  public void setRegione(String regione) {
    this.regione = regione;
  }

  /**
   **/
  

  @JsonProperty("flg_anpal_stato_corrente") 
 
  public String getFlgAnpalStatoCorrente() {
    return flgAnpalStatoCorrente;
  }
  public void setFlgAnpalStatoCorrente(String flgAnpalStatoCorrente) {
    this.flgAnpalStatoCorrente = flgAnpalStatoCorrente;
  }

  /**
   **/
  

  @JsonProperty("motivo_rifiuto_stato_corrente") 
 
  public String getMotivoRifiutoStatoCorrente() {
    return motivoRifiutoStatoCorrente;
  }
  public void setMotivoRifiutoStatoCorrente(String motivoRifiutoStatoCorrente) {
    this.motivoRifiutoStatoCorrente = motivoRifiutoStatoCorrente;
  }

  /**
   **/
  

  @JsonProperty("flg_rifiuto_stato_corrente") 
 
  public String getFlgRifiutoStatoCorrente() {
    return flgRifiutoStatoCorrente;
  }
  public void setFlgRifiutoStatoCorrente(String flgRifiutoStatoCorrente) {
    this.flgRifiutoStatoCorrente = flgRifiutoStatoCorrente;
  }

  /**
   **/
  

  @JsonProperty("data_rifiuto") 
 
  public Date getDataRifiuto() {
    return dataRifiuto;
  }
  public void setDataRifiuto(Date dataRifiuto) {
    this.dataRifiuto = dataRifiuto;
  }

  /**
   **/
  

  @JsonProperty("presenzaPiuAdesioniAperte") 
 
  public Boolean isPresenzaPiuAdesioniAperte() {
    return presenzaPiuAdesioniAperte;
  }
  public void setPresenzaPiuAdesioniAperte(Boolean presenzaPiuAdesioniAperte) {
    this.presenzaPiuAdesioniAperte = presenzaPiuAdesioniAperte;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdesioneYG adesioneYG = (AdesioneYG) o;
    return Objects.equals(idSilLavAdesione, adesioneYG.idSilLavAdesione) &&
        Objects.equals(codice, adesioneYG.codice) &&
        Objects.equals(dataAdesione, adesioneYG.dataAdesione) &&
        Objects.equals(codiceFiscale, adesioneYG.codiceFiscale) &&
        Objects.equals(identificativoSap, adesioneYG.identificativoSap) &&
        Objects.equals(dataStatoCorrente, adesioneYG.dataStatoCorrente) &&
        Objects.equals(statoCorrenteFinale, adesioneYG.statoCorrenteFinale) &&
        Objects.equals(descrizione, adesioneYG.descrizione) &&
        Objects.equals(regione, adesioneYG.regione) &&
        Objects.equals(flgAnpalStatoCorrente, adesioneYG.flgAnpalStatoCorrente) &&
        Objects.equals(motivoRifiutoStatoCorrente, adesioneYG.motivoRifiutoStatoCorrente) &&
        Objects.equals(flgRifiutoStatoCorrente, adesioneYG.flgRifiutoStatoCorrente) &&
        Objects.equals(dataRifiuto, adesioneYG.dataRifiuto) &&
        Objects.equals(presenzaPiuAdesioniAperte, adesioneYG.presenzaPiuAdesioniAperte);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idSilLavAdesione, codice, dataAdesione, codiceFiscale, identificativoSap, dataStatoCorrente, statoCorrenteFinale, descrizione, regione, flgAnpalStatoCorrente, motivoRifiutoStatoCorrente, flgRifiutoStatoCorrente, dataRifiuto, presenzaPiuAdesioniAperte);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdesioneYG {\n");
    
    sb.append("    idSilLavAdesione: ").append(toIndentedString(idSilLavAdesione)).append("\n");
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    dataAdesione: ").append(toIndentedString(dataAdesione)).append("\n");
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    identificativoSap: ").append(toIndentedString(identificativoSap)).append("\n");
    sb.append("    dataStatoCorrente: ").append(toIndentedString(dataStatoCorrente)).append("\n");
    sb.append("    statoCorrenteFinale: ").append(toIndentedString(statoCorrenteFinale)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    regione: ").append(toIndentedString(regione)).append("\n");
    sb.append("    flgAnpalStatoCorrente: ").append(toIndentedString(flgAnpalStatoCorrente)).append("\n");
    sb.append("    motivoRifiutoStatoCorrente: ").append(toIndentedString(motivoRifiutoStatoCorrente)).append("\n");
    sb.append("    flgRifiutoStatoCorrente: ").append(toIndentedString(flgRifiutoStatoCorrente)).append("\n");
    sb.append("    dataRifiuto: ").append(toIndentedString(dataRifiuto)).append("\n");
    sb.append("    presenzaPiuAdesioniAperte: ").append(toIndentedString(presenzaPiuAdesioniAperte)).append("\n");
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

