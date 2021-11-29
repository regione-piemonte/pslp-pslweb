/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.GradoStudio;
import it.csi.pslp.pslweb.dto.be.Luogo;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class TitoloDiStudio   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private GradoStudio livelloScolarizzazione = null;
  private Decodifica corsoDiStudio = null;
  private String descrizione = null;
  private Luogo frequentatoIn = null;
  private Boolean riconosciutoInItalia = null;
  private Integer annoSeConseguito = null;
  private String votazioneConseguita = null;
  private Integer ultimoAnnoFrequentatoSeNonConseguito = null;
  private Integer annoDiFrequenzaSeInCorso = null;

  /**
   * da tabella ministeriale LIVELLO DI STUDIO, 2 caratteri es. 20 SCUOLA MEDIA
   **/
  

  @JsonProperty("livello_scolarizzazione") 
 
  public GradoStudio getLivelloScolarizzazione() {
    return livelloScolarizzazione;
  }
  public void setLivelloScolarizzazione(GradoStudio livelloScolarizzazione) {
    this.livelloScolarizzazione = livelloScolarizzazione;
  }

  /**
   * da tabella ministeriale TDS completa con indici, 8 caratteri  es. 30101005  Esperto agrumicoltore
   **/
  

  @JsonProperty("corso_di_studio") 
 
  public Decodifica getCorsoDiStudio() {
    return corsoDiStudio;
  }
  public void setCorsoDiStudio(Decodifica corsoDiStudio) {
    this.corsoDiStudio = corsoDiStudio;
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
  

  @JsonProperty("frequentato_in") 
 
  public Luogo getFrequentatoIn() {
    return frequentatoIn;
  }
  public void setFrequentatoIn(Luogo frequentatoIn) {
    this.frequentatoIn = frequentatoIn;
  }

  /**
   **/
  

  @JsonProperty("riconosciuto_in_italia") 
 
  public Boolean isRiconosciutoInItalia() {
    return riconosciutoInItalia;
  }
  public void setRiconosciutoInItalia(Boolean riconosciutoInItalia) {
    this.riconosciutoInItalia = riconosciutoInItalia;
  }

  /**
   **/
  

  @JsonProperty("anno_se_conseguito") 
 
  public Integer getAnnoSeConseguito() {
    return annoSeConseguito;
  }
  public void setAnnoSeConseguito(Integer annoSeConseguito) {
    this.annoSeConseguito = annoSeConseguito;
  }

  /**
   **/
  

  @JsonProperty("votazione_conseguita") 
 
  public String getVotazioneConseguita() {
    return votazioneConseguita;
  }
  public void setVotazioneConseguita(String votazioneConseguita) {
    this.votazioneConseguita = votazioneConseguita;
  }

  /**
   **/
  

  @JsonProperty("ultimo_anno_frequentato_se_non_conseguito") 
 
  public Integer getUltimoAnnoFrequentatoSeNonConseguito() {
    return ultimoAnnoFrequentatoSeNonConseguito;
  }
  public void setUltimoAnnoFrequentatoSeNonConseguito(Integer ultimoAnnoFrequentatoSeNonConseguito) {
    this.ultimoAnnoFrequentatoSeNonConseguito = ultimoAnnoFrequentatoSeNonConseguito;
  }

  /**
   **/
  

  @JsonProperty("anno_di_frequenza_se_in_corso") 
 
  public Integer getAnnoDiFrequenzaSeInCorso() {
    return annoDiFrequenzaSeInCorso;
  }
  public void setAnnoDiFrequenzaSeInCorso(Integer annoDiFrequenzaSeInCorso) {
    this.annoDiFrequenzaSeInCorso = annoDiFrequenzaSeInCorso;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TitoloDiStudio titoloDiStudio = (TitoloDiStudio) o;
    return Objects.equals(livelloScolarizzazione, titoloDiStudio.livelloScolarizzazione) &&
        Objects.equals(corsoDiStudio, titoloDiStudio.corsoDiStudio) &&
        Objects.equals(descrizione, titoloDiStudio.descrizione) &&
        Objects.equals(frequentatoIn, titoloDiStudio.frequentatoIn) &&
        Objects.equals(riconosciutoInItalia, titoloDiStudio.riconosciutoInItalia) &&
        Objects.equals(annoSeConseguito, titoloDiStudio.annoSeConseguito) &&
        Objects.equals(votazioneConseguita, titoloDiStudio.votazioneConseguita) &&
        Objects.equals(ultimoAnnoFrequentatoSeNonConseguito, titoloDiStudio.ultimoAnnoFrequentatoSeNonConseguito) &&
        Objects.equals(annoDiFrequenzaSeInCorso, titoloDiStudio.annoDiFrequenzaSeInCorso);
  }

  @Override
  public int hashCode() {
    return Objects.hash(livelloScolarizzazione, corsoDiStudio, descrizione, frequentatoIn, riconosciutoInItalia, annoSeConseguito, votazioneConseguita, ultimoAnnoFrequentatoSeNonConseguito, annoDiFrequenzaSeInCorso);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TitoloDiStudio {\n");
    
    sb.append("    livelloScolarizzazione: ").append(toIndentedString(livelloScolarizzazione)).append("\n");
    sb.append("    corsoDiStudio: ").append(toIndentedString(corsoDiStudio)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    frequentatoIn: ").append(toIndentedString(frequentatoIn)).append("\n");
    sb.append("    riconosciutoInItalia: ").append(toIndentedString(riconosciutoInItalia)).append("\n");
    sb.append("    annoSeConseguito: ").append(toIndentedString(annoSeConseguito)).append("\n");
    sb.append("    votazioneConseguita: ").append(toIndentedString(votazioneConseguita)).append("\n");
    sb.append("    ultimoAnnoFrequentatoSeNonConseguito: ").append(toIndentedString(ultimoAnnoFrequentatoSeNonConseguito)).append("\n");
    sb.append("    annoDiFrequenzaSeInCorso: ").append(toIndentedString(annoDiFrequenzaSeInCorso)).append("\n");
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

