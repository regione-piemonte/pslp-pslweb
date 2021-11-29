/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.EventoPolitica;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class PoliticaAttiva   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Decodifica attivita = null;
  private String denominazione = null;
  private Date dataProposta = null;
  private Date dataInizio = null;
  private Date dataFine = null;
  private Integer durata = null;
  private String tipoDurata = null;
  private String descrizione = null;
  private Decodifica titoloProgetto = null;
  private Decodifica entePromotore = null;
  private String identificativoPolitica = null;
  private String identificativoPresaInCarico = null;
  private String indiceProfiling = null;
  private EventoPolitica ultimoEvento = null;

  /**
   * tabella ministeriale TIPO ATTIVITA es. A02 PATTO DI ATTIVAZIONE ED EVENTUALE PROFILING
   **/
  

  @JsonProperty("attivita") 
 
  public Decodifica getAttivita() {
    return attivita;
  }
  public void setAttivita(Decodifica attivita) {
    this.attivita = attivita;
  }

  /**
   **/
  

  @JsonProperty("denominazione") 
 
  public String getDenominazione() {
    return denominazione;
  }
  public void setDenominazione(String denominazione) {
    this.denominazione = denominazione;
  }

  /**
   **/
  

  @JsonProperty("data_proposta") 
 
  public Date getDataProposta() {
    return dataProposta;
  }
  public void setDataProposta(Date dataProposta) {
    this.dataProposta = dataProposta;
  }

  /**
   **/
  

  @JsonProperty("data_inizio") 
 
  public Date getDataInizio() {
    return dataInizio;
  }
  public void setDataInizio(Date dataInizio) {
    this.dataInizio = dataInizio;
  }

  /**
   **/
  

  @JsonProperty("data_fine") 
 
  public Date getDataFine() {
    return dataFine;
  }
  public void setDataFine(Date dataFine) {
    this.dataFine = dataFine;
  }

  /**
   **/
  

  @JsonProperty("durata") 
 
  public Integer getDurata() {
    return durata;
  }
  public void setDurata(Integer durata) {
    this.durata = durata;
  }

  /**
   * O ore, G giorni, M mesi, A anni
   **/
  

  @JsonProperty("tipo_durata") 
 
  @Size(max=1)
  public String getTipoDurata() {
    return tipoDurata;
  }
  public void setTipoDurata(String tipoDurata) {
    this.tipoDurata = tipoDurata;
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
   * tabella ministeriale TIPO PROGETTI es. 02 GARANZIA GIOVANI
   **/
  

  @JsonProperty("titolo_progetto") 
 
  public Decodifica getTitoloProgetto() {
    return titoloProgetto;
  }
  public void setTitoloProgetto(Decodifica titoloProgetto) {
    this.titoloProgetto = titoloProgetto;
  }

  /**
   * tabella ministeriale CPI, rappresenta CPI o Societa&#39; che ha fornito la politica
   **/
  

  @JsonProperty("ente_promotore") 
 
  public Decodifica getEntePromotore() {
    return entePromotore;
  }
  public void setEntePromotore(Decodifica entePromotore) {
    this.entePromotore = entePromotore;
  }

  /**
   **/
  

  @JsonProperty("identificativo_politica") 
 
  @Size(max=11)
  public String getIdentificativoPolitica() {
    return identificativoPolitica;
  }
  public void setIdentificativoPolitica(String identificativoPolitica) {
    this.identificativoPolitica = identificativoPolitica;
  }

  /**
   **/
  

  @JsonProperty("identificativo_presa_in_carico") 
 
  @Size(max=11)
  public String getIdentificativoPresaInCarico() {
    return identificativoPresaInCarico;
  }
  public void setIdentificativoPresaInCarico(String identificativoPresaInCarico) {
    this.identificativoPresaInCarico = identificativoPresaInCarico;
  }

  /**
   * numero decimale di lunghezza totale 10 con 9 decimali. Rappresento in stringa per comodita
   **/
  

  @JsonProperty("indice_profiling") 
 
  @Size(max=11)
  public String getIndiceProfiling() {
    return indiceProfiling;
  }
  public void setIndiceProfiling(String indiceProfiling) {
    this.indiceProfiling = indiceProfiling;
  }

  /**
   **/
  

  @JsonProperty("ultimo_evento") 
 
  public EventoPolitica getUltimoEvento() {
    return ultimoEvento;
  }
  public void setUltimoEvento(EventoPolitica ultimoEvento) {
    this.ultimoEvento = ultimoEvento;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PoliticaAttiva politicaAttiva = (PoliticaAttiva) o;
    return Objects.equals(attivita, politicaAttiva.attivita) &&
        Objects.equals(denominazione, politicaAttiva.denominazione) &&
        Objects.equals(dataProposta, politicaAttiva.dataProposta) &&
        Objects.equals(dataInizio, politicaAttiva.dataInizio) &&
        Objects.equals(dataFine, politicaAttiva.dataFine) &&
        Objects.equals(durata, politicaAttiva.durata) &&
        Objects.equals(tipoDurata, politicaAttiva.tipoDurata) &&
        Objects.equals(descrizione, politicaAttiva.descrizione) &&
        Objects.equals(titoloProgetto, politicaAttiva.titoloProgetto) &&
        Objects.equals(entePromotore, politicaAttiva.entePromotore) &&
        Objects.equals(identificativoPolitica, politicaAttiva.identificativoPolitica) &&
        Objects.equals(identificativoPresaInCarico, politicaAttiva.identificativoPresaInCarico) &&
        Objects.equals(indiceProfiling, politicaAttiva.indiceProfiling) &&
        Objects.equals(ultimoEvento, politicaAttiva.ultimoEvento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attivita, denominazione, dataProposta, dataInizio, dataFine, durata, tipoDurata, descrizione, titoloProgetto, entePromotore, identificativoPolitica, identificativoPresaInCarico, indiceProfiling, ultimoEvento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PoliticaAttiva {\n");
    
    sb.append("    attivita: ").append(toIndentedString(attivita)).append("\n");
    sb.append("    denominazione: ").append(toIndentedString(denominazione)).append("\n");
    sb.append("    dataProposta: ").append(toIndentedString(dataProposta)).append("\n");
    sb.append("    dataInizio: ").append(toIndentedString(dataInizio)).append("\n");
    sb.append("    dataFine: ").append(toIndentedString(dataFine)).append("\n");
    sb.append("    durata: ").append(toIndentedString(durata)).append("\n");
    sb.append("    tipoDurata: ").append(toIndentedString(tipoDurata)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    titoloProgetto: ").append(toIndentedString(titoloProgetto)).append("\n");
    sb.append("    entePromotore: ").append(toIndentedString(entePromotore)).append("\n");
    sb.append("    identificativoPolitica: ").append(toIndentedString(identificativoPolitica)).append("\n");
    sb.append("    identificativoPresaInCarico: ").append(toIndentedString(identificativoPresaInCarico)).append("\n");
    sb.append("    indiceProfiling: ").append(toIndentedString(indiceProfiling)).append("\n");
    sb.append("    ultimoEvento: ").append(toIndentedString(ultimoEvento)).append("\n");
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

