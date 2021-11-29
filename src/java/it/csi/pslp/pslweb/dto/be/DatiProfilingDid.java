/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class DatiProfilingDid   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer eta = null;
  private Date dataInserimento = null;
  private Date dataDid = null;
  private Date dataDisoccupazione = null;
  private String flgFigliCarico = null;
  private String flgFigliCaricoMinori = null;
  private String flgGenere = null;
  private String flgHaAvutoLavoro = null;
  private String dsCittadinanza = null;
  private String dsTitoloStudio = null;
  private String codiceMinisterialeTitoloStudio = null;
  private String dsCondizioneOccupazionale = null;
  private Integer numMesiUltimoRapporto = null;
  private Integer numMesiRicercaLavoro = null;
  private Integer numComponentiFamiglia = null;
  private String dsIscrizioneCorsi = null;
  private String dsProvinciaResidenza = null;
  private String dsPresenzaInItalia = null;
  private String dsPosizioneProfessionale = null;
  private String indiceProfiling = null;

  /**
   **/
  

  @JsonProperty("eta") 
 
  public Integer getEta() {
    return eta;
  }
  public void setEta(Integer eta) {
    this.eta = eta;
  }

  /**
   * data inserimento
   **/
  

  @JsonProperty("data_inserimento") 
 
  public Date getDataInserimento() {
    return dataInserimento;
  }
  public void setDataInserimento(Date dataInserimento) {
    this.dataInserimento = dataInserimento;
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
   * data disoccupazione profiling
   **/
  

  @JsonProperty("data_disoccupazione") 
 
  public Date getDataDisoccupazione() {
    return dataDisoccupazione;
  }
  public void setDataDisoccupazione(Date dataDisoccupazione) {
    this.dataDisoccupazione = dataDisoccupazione;
  }

  /**
   **/
  

  @JsonProperty("flg_figli_carico") 
 
  public String getFlgFigliCarico() {
    return flgFigliCarico;
  }
  public void setFlgFigliCarico(String flgFigliCarico) {
    this.flgFigliCarico = flgFigliCarico;
  }

  /**
   **/
  

  @JsonProperty("flg_figli_carico_minori") 
 
  public String getFlgFigliCaricoMinori() {
    return flgFigliCaricoMinori;
  }
  public void setFlgFigliCaricoMinori(String flgFigliCaricoMinori) {
    this.flgFigliCaricoMinori = flgFigliCaricoMinori;
  }

  /**
   **/
  

  @JsonProperty("flg_genere") 
 
  public String getFlgGenere() {
    return flgGenere;
  }
  public void setFlgGenere(String flgGenere) {
    this.flgGenere = flgGenere;
  }

  /**
   **/
  

  @JsonProperty("flg_ha_avuto_lavoro") 
 
  public String getFlgHaAvutoLavoro() {
    return flgHaAvutoLavoro;
  }
  public void setFlgHaAvutoLavoro(String flgHaAvutoLavoro) {
    this.flgHaAvutoLavoro = flgHaAvutoLavoro;
  }

  /**
   **/
  

  @JsonProperty("ds_cittadinanza") 
 
  public String getDsCittadinanza() {
    return dsCittadinanza;
  }
  public void setDsCittadinanza(String dsCittadinanza) {
    this.dsCittadinanza = dsCittadinanza;
  }

  /**
   **/
  

  @JsonProperty("ds_titolo_studio") 
 
  public String getDsTitoloStudio() {
    return dsTitoloStudio;
  }
  public void setDsTitoloStudio(String dsTitoloStudio) {
    this.dsTitoloStudio = dsTitoloStudio;
  }

  /**
   **/
  

  @JsonProperty("codice_ministeriale_titolo_studio") 
 
  public String getCodiceMinisterialeTitoloStudio() {
    return codiceMinisterialeTitoloStudio;
  }
  public void setCodiceMinisterialeTitoloStudio(String codiceMinisterialeTitoloStudio) {
    this.codiceMinisterialeTitoloStudio = codiceMinisterialeTitoloStudio;
  }

  /**
   **/
  

  @JsonProperty("ds_condizione_occupazionale") 
 
  public String getDsCondizioneOccupazionale() {
    return dsCondizioneOccupazionale;
  }
  public void setDsCondizioneOccupazionale(String dsCondizioneOccupazionale) {
    this.dsCondizioneOccupazionale = dsCondizioneOccupazionale;
  }

  /**
   **/
  

  @JsonProperty("num_mesi_ultimo_rapporto") 
 
  public Integer getNumMesiUltimoRapporto() {
    return numMesiUltimoRapporto;
  }
  public void setNumMesiUltimoRapporto(Integer numMesiUltimoRapporto) {
    this.numMesiUltimoRapporto = numMesiUltimoRapporto;
  }

  /**
   **/
  

  @JsonProperty("num_mesi_ricerca_lavoro") 
 
  public Integer getNumMesiRicercaLavoro() {
    return numMesiRicercaLavoro;
  }
  public void setNumMesiRicercaLavoro(Integer numMesiRicercaLavoro) {
    this.numMesiRicercaLavoro = numMesiRicercaLavoro;
  }

  /**
   **/
  

  @JsonProperty("num_componenti_famiglia") 
 
  public Integer getNumComponentiFamiglia() {
    return numComponentiFamiglia;
  }
  public void setNumComponentiFamiglia(Integer numComponentiFamiglia) {
    this.numComponentiFamiglia = numComponentiFamiglia;
  }

  /**
   **/
  

  @JsonProperty("ds_iscrizione_corsi") 
 
  public String getDsIscrizioneCorsi() {
    return dsIscrizioneCorsi;
  }
  public void setDsIscrizioneCorsi(String dsIscrizioneCorsi) {
    this.dsIscrizioneCorsi = dsIscrizioneCorsi;
  }

  /**
   **/
  

  @JsonProperty("ds_provincia_residenza") 
 
  public String getDsProvinciaResidenza() {
    return dsProvinciaResidenza;
  }
  public void setDsProvinciaResidenza(String dsProvinciaResidenza) {
    this.dsProvinciaResidenza = dsProvinciaResidenza;
  }

  /**
   **/
  

  @JsonProperty("ds_presenza_in_italia") 
 
  public String getDsPresenzaInItalia() {
    return dsPresenzaInItalia;
  }
  public void setDsPresenzaInItalia(String dsPresenzaInItalia) {
    this.dsPresenzaInItalia = dsPresenzaInItalia;
  }

  /**
   **/
  

  @JsonProperty("ds_posizione_professionale") 
 
  public String getDsPosizioneProfessionale() {
    return dsPosizioneProfessionale;
  }
  public void setDsPosizioneProfessionale(String dsPosizioneProfessionale) {
    this.dsPosizioneProfessionale = dsPosizioneProfessionale;
  }

  /**
   * numero decimale di lunghezza totale 10 con 9 decimali. Rappresento in stringa per comodita (preso da PoliticaAttiva)
   **/
  

  @JsonProperty("indice_profiling") 
 
  @Size(max=11)
  public String getIndiceProfiling() {
    return indiceProfiling;
  }
  public void setIndiceProfiling(String indiceProfiling) {
    this.indiceProfiling = indiceProfiling;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatiProfilingDid datiProfilingDid = (DatiProfilingDid) o;
    return Objects.equals(eta, datiProfilingDid.eta) &&
        Objects.equals(dataInserimento, datiProfilingDid.dataInserimento) &&
        Objects.equals(dataDid, datiProfilingDid.dataDid) &&
        Objects.equals(dataDisoccupazione, datiProfilingDid.dataDisoccupazione) &&
        Objects.equals(flgFigliCarico, datiProfilingDid.flgFigliCarico) &&
        Objects.equals(flgFigliCaricoMinori, datiProfilingDid.flgFigliCaricoMinori) &&
        Objects.equals(flgGenere, datiProfilingDid.flgGenere) &&
        Objects.equals(flgHaAvutoLavoro, datiProfilingDid.flgHaAvutoLavoro) &&
        Objects.equals(dsCittadinanza, datiProfilingDid.dsCittadinanza) &&
        Objects.equals(dsTitoloStudio, datiProfilingDid.dsTitoloStudio) &&
        Objects.equals(codiceMinisterialeTitoloStudio, datiProfilingDid.codiceMinisterialeTitoloStudio) &&
        Objects.equals(dsCondizioneOccupazionale, datiProfilingDid.dsCondizioneOccupazionale) &&
        Objects.equals(numMesiUltimoRapporto, datiProfilingDid.numMesiUltimoRapporto) &&
        Objects.equals(numMesiRicercaLavoro, datiProfilingDid.numMesiRicercaLavoro) &&
        Objects.equals(numComponentiFamiglia, datiProfilingDid.numComponentiFamiglia) &&
        Objects.equals(dsIscrizioneCorsi, datiProfilingDid.dsIscrizioneCorsi) &&
        Objects.equals(dsProvinciaResidenza, datiProfilingDid.dsProvinciaResidenza) &&
        Objects.equals(dsPresenzaInItalia, datiProfilingDid.dsPresenzaInItalia) &&
        Objects.equals(dsPosizioneProfessionale, datiProfilingDid.dsPosizioneProfessionale) &&
        Objects.equals(indiceProfiling, datiProfilingDid.indiceProfiling);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eta, dataInserimento, dataDid, dataDisoccupazione, flgFigliCarico, flgFigliCaricoMinori, flgGenere, flgHaAvutoLavoro, dsCittadinanza, dsTitoloStudio, codiceMinisterialeTitoloStudio, dsCondizioneOccupazionale, numMesiUltimoRapporto, numMesiRicercaLavoro, numComponentiFamiglia, dsIscrizioneCorsi, dsProvinciaResidenza, dsPresenzaInItalia, dsPosizioneProfessionale, indiceProfiling);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DatiProfilingDid {\n");
    
    sb.append("    eta: ").append(toIndentedString(eta)).append("\n");
    sb.append("    dataInserimento: ").append(toIndentedString(dataInserimento)).append("\n");
    sb.append("    dataDid: ").append(toIndentedString(dataDid)).append("\n");
    sb.append("    dataDisoccupazione: ").append(toIndentedString(dataDisoccupazione)).append("\n");
    sb.append("    flgFigliCarico: ").append(toIndentedString(flgFigliCarico)).append("\n");
    sb.append("    flgFigliCaricoMinori: ").append(toIndentedString(flgFigliCaricoMinori)).append("\n");
    sb.append("    flgGenere: ").append(toIndentedString(flgGenere)).append("\n");
    sb.append("    flgHaAvutoLavoro: ").append(toIndentedString(flgHaAvutoLavoro)).append("\n");
    sb.append("    dsCittadinanza: ").append(toIndentedString(dsCittadinanza)).append("\n");
    sb.append("    dsTitoloStudio: ").append(toIndentedString(dsTitoloStudio)).append("\n");
    sb.append("    codiceMinisterialeTitoloStudio: ").append(toIndentedString(codiceMinisterialeTitoloStudio)).append("\n");
    sb.append("    dsCondizioneOccupazionale: ").append(toIndentedString(dsCondizioneOccupazionale)).append("\n");
    sb.append("    numMesiUltimoRapporto: ").append(toIndentedString(numMesiUltimoRapporto)).append("\n");
    sb.append("    numMesiRicercaLavoro: ").append(toIndentedString(numMesiRicercaLavoro)).append("\n");
    sb.append("    numComponentiFamiglia: ").append(toIndentedString(numComponentiFamiglia)).append("\n");
    sb.append("    dsIscrizioneCorsi: ").append(toIndentedString(dsIscrizioneCorsi)).append("\n");
    sb.append("    dsProvinciaResidenza: ").append(toIndentedString(dsProvinciaResidenza)).append("\n");
    sb.append("    dsPresenzaInItalia: ").append(toIndentedString(dsPresenzaInItalia)).append("\n");
    sb.append("    dsPosizioneProfessionale: ").append(toIndentedString(dsPosizioneProfessionale)).append("\n");
    sb.append("    indiceProfiling: ").append(toIndentedString(indiceProfiling)).append("\n");
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

