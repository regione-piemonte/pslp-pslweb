/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import io.swagger.annotations.ApiModel;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneRisposta;
import it.csi.pslp.pslweb.dto.be.DatiProfilingDid;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class EsitoDettaglioDid   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idDid = null;
  private Date dataDid = null;
  private String flgRifiuto = null;
  private String motivoRifiuto = null;
  private String flgEnteTitolaritaPiemontese = null;
  private String enteTitolarita = null;
  private String flgRapportoLavoroAperto = null;
  private String flgPercettore = null;
  private String flgRapportoLavoroChiuso = null;
  private String codUltimoStato = null;
  private String flgStatoFinale = null;
  private String flgAttesaInvio = null;
  private Date dataStato = null;
  private Date dataDidRespinta = null;
  private DatiProfilingDid datiProfilingDid = null;
  private ErrorDef erroreRicercaDid = null;
  private List<ConfigurazioneRisposta> contenuto = new ArrayList<ConfigurazioneRisposta>();
  private List<String> error = new ArrayList<String>();
  private List<String> info = new ArrayList<String>();
  private List<String> warning = new ArrayList<String>();

  /**
   * id DID
   **/
  

  @JsonProperty("id_did") 
 
  public Long getIdDid() {
    return idDid;
  }
  public void setIdDid(Long idDid) {
    this.idDid = idDid;
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
   **/
  

  @JsonProperty("flg_rifiuto") 
 
  public String getFlgRifiuto() {
    return flgRifiuto;
  }
  public void setFlgRifiuto(String flgRifiuto) {
    this.flgRifiuto = flgRifiuto;
  }

  /**
   **/
  

  @JsonProperty("motivo_rifiuto") 
 
  public String getMotivoRifiuto() {
    return motivoRifiuto;
  }
  public void setMotivoRifiuto(String motivoRifiuto) {
    this.motivoRifiuto = motivoRifiuto;
  }

  /**
   **/
  

  @JsonProperty("flg_ente_titolarita_piemontese") 
 
  public String getFlgEnteTitolaritaPiemontese() {
    return flgEnteTitolaritaPiemontese;
  }
  public void setFlgEnteTitolaritaPiemontese(String flgEnteTitolaritaPiemontese) {
    this.flgEnteTitolaritaPiemontese = flgEnteTitolaritaPiemontese;
  }

  /**
   **/
  

  @JsonProperty("ente_titolarita") 
 
  public String getEnteTitolarita() {
    return enteTitolarita;
  }
  public void setEnteTitolarita(String enteTitolarita) {
    this.enteTitolarita = enteTitolarita;
  }

  /**
   **/
  

  @JsonProperty("flg_rapporto_lavoro_aperto") 
 
  public String getFlgRapportoLavoroAperto() {
    return flgRapportoLavoroAperto;
  }
  public void setFlgRapportoLavoroAperto(String flgRapportoLavoroAperto) {
    this.flgRapportoLavoroAperto = flgRapportoLavoroAperto;
  }

  /**
   **/
  

  @JsonProperty("flg_percettore") 
 
  public String getFlgPercettore() {
    return flgPercettore;
  }
  public void setFlgPercettore(String flgPercettore) {
    this.flgPercettore = flgPercettore;
  }

  /**
   **/
  

  @JsonProperty("flg_rapporto_lavoro_chiuso") 
 
  public String getFlgRapportoLavoroChiuso() {
    return flgRapportoLavoroChiuso;
  }
  public void setFlgRapportoLavoroChiuso(String flgRapportoLavoroChiuso) {
    this.flgRapportoLavoroChiuso = flgRapportoLavoroChiuso;
  }

  /**
   * codice ultimo stato
   **/
  

  @JsonProperty("cod_ultimo_stato") 
 
  public String getCodUltimoStato() {
    return codUltimoStato;
  }
  public void setCodUltimoStato(String codUltimoStato) {
    this.codUltimoStato = codUltimoStato;
  }

  /**
   **/
  

  @JsonProperty("flg_stato_finale") 
 
  public String getFlgStatoFinale() {
    return flgStatoFinale;
  }
  public void setFlgStatoFinale(String flgStatoFinale) {
    this.flgStatoFinale = flgStatoFinale;
  }

  /**
   * S conferito , null o N non conferito
   **/
  

  @JsonProperty("flg_attesa_invio") 
 
  public String getFlgAttesaInvio() {
    return flgAttesaInvio;
  }
  public void setFlgAttesaInvio(String flgAttesaInvio) {
    this.flgAttesaInvio = flgAttesaInvio;
  }

  /**
   * data ultimo stato
   **/
  

  @JsonProperty("data_stato") 
 
  public Date getDataStato() {
    return dataStato;
  }
  public void setDataStato(Date dataStato) {
    this.dataStato = dataStato;
  }

  /**
   * data did respinta
   **/
  

  @JsonProperty("data_did_respinta") 
 
  public Date getDataDidRespinta() {
    return dataDidRespinta;
  }
  public void setDataDidRespinta(Date dataDidRespinta) {
    this.dataDidRespinta = dataDidRespinta;
  }

  /**
   **/
  

  @JsonProperty("dati_profiling_did") 
 
  public DatiProfilingDid getDatiProfilingDid() {
    return datiProfilingDid;
  }
  public void setDatiProfilingDid(DatiProfilingDid datiProfilingDid) {
    this.datiProfilingDid = datiProfilingDid;
  }

  /**
   **/
  

  @JsonProperty("errore_ricerca_did") 
 
  public ErrorDef getErroreRicercaDid() {
    return erroreRicercaDid;
  }
  public void setErroreRicercaDid(ErrorDef erroreRicercaDid) {
    this.erroreRicercaDid = erroreRicercaDid;
  }

  /**
   **/
  

  @JsonProperty("contenuto") 
 
  public List<ConfigurazioneRisposta> getContenuto() {
    return contenuto;
  }
  public void setContenuto(List<ConfigurazioneRisposta> contenuto) {
    this.contenuto = contenuto;
  }

  /**
   **/
  

  @JsonProperty("error") 
 
  public List<String> getError() {
    return error;
  }
  public void setError(List<String> error) {
    this.error = error;
  }

  /**
   **/
  

  @JsonProperty("info") 
 
  public List<String> getInfo() {
    return info;
  }
  public void setInfo(List<String> info) {
    this.info = info;
  }

  /**
   **/
  

  @JsonProperty("warning") 
 
  public List<String> getWarning() {
    return warning;
  }
  public void setWarning(List<String> warning) {
    this.warning = warning;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoDettaglioDid esitoDettaglioDid = (EsitoDettaglioDid) o;
    return Objects.equals(idDid, esitoDettaglioDid.idDid) &&
        Objects.equals(dataDid, esitoDettaglioDid.dataDid) &&
        Objects.equals(flgRifiuto, esitoDettaglioDid.flgRifiuto) &&
        Objects.equals(motivoRifiuto, esitoDettaglioDid.motivoRifiuto) &&
        Objects.equals(flgEnteTitolaritaPiemontese, esitoDettaglioDid.flgEnteTitolaritaPiemontese) &&
        Objects.equals(enteTitolarita, esitoDettaglioDid.enteTitolarita) &&
        Objects.equals(flgRapportoLavoroAperto, esitoDettaglioDid.flgRapportoLavoroAperto) &&
        Objects.equals(flgPercettore, esitoDettaglioDid.flgPercettore) &&
        Objects.equals(flgRapportoLavoroChiuso, esitoDettaglioDid.flgRapportoLavoroChiuso) &&
        Objects.equals(codUltimoStato, esitoDettaglioDid.codUltimoStato) &&
        Objects.equals(flgStatoFinale, esitoDettaglioDid.flgStatoFinale) &&
        Objects.equals(flgAttesaInvio, esitoDettaglioDid.flgAttesaInvio) &&
        Objects.equals(dataStato, esitoDettaglioDid.dataStato) &&
        Objects.equals(dataDidRespinta, esitoDettaglioDid.dataDidRespinta) &&
        Objects.equals(datiProfilingDid, esitoDettaglioDid.datiProfilingDid) &&
        Objects.equals(erroreRicercaDid, esitoDettaglioDid.erroreRicercaDid) &&
        Objects.equals(contenuto, esitoDettaglioDid.contenuto) &&
        Objects.equals(error, esitoDettaglioDid.error) &&
        Objects.equals(info, esitoDettaglioDid.info) &&
        Objects.equals(warning, esitoDettaglioDid.warning);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idDid, dataDid, flgRifiuto, motivoRifiuto, flgEnteTitolaritaPiemontese, enteTitolarita, flgRapportoLavoroAperto, flgPercettore, flgRapportoLavoroChiuso, codUltimoStato, flgStatoFinale, flgAttesaInvio, dataStato, dataDidRespinta, datiProfilingDid, erroreRicercaDid, contenuto, error, info, warning);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoDettaglioDid {\n");
    
    sb.append("    idDid: ").append(toIndentedString(idDid)).append("\n");
    sb.append("    dataDid: ").append(toIndentedString(dataDid)).append("\n");
    sb.append("    flgRifiuto: ").append(toIndentedString(flgRifiuto)).append("\n");
    sb.append("    motivoRifiuto: ").append(toIndentedString(motivoRifiuto)).append("\n");
    sb.append("    flgEnteTitolaritaPiemontese: ").append(toIndentedString(flgEnteTitolaritaPiemontese)).append("\n");
    sb.append("    enteTitolarita: ").append(toIndentedString(enteTitolarita)).append("\n");
    sb.append("    flgRapportoLavoroAperto: ").append(toIndentedString(flgRapportoLavoroAperto)).append("\n");
    sb.append("    flgPercettore: ").append(toIndentedString(flgPercettore)).append("\n");
    sb.append("    flgRapportoLavoroChiuso: ").append(toIndentedString(flgRapportoLavoroChiuso)).append("\n");
    sb.append("    codUltimoStato: ").append(toIndentedString(codUltimoStato)).append("\n");
    sb.append("    flgStatoFinale: ").append(toIndentedString(flgStatoFinale)).append("\n");
    sb.append("    flgAttesaInvio: ").append(toIndentedString(flgAttesaInvio)).append("\n");
    sb.append("    dataStato: ").append(toIndentedString(dataStato)).append("\n");
    sb.append("    dataDidRespinta: ").append(toIndentedString(dataDidRespinta)).append("\n");
    sb.append("    datiProfilingDid: ").append(toIndentedString(datiProfilingDid)).append("\n");
    sb.append("    erroreRicercaDid: ").append(toIndentedString(erroreRicercaDid)).append("\n");
    sb.append("    contenuto: ").append(toIndentedString(contenuto)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
    sb.append("    warning: ").append(toIndentedString(warning)).append("\n");
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

