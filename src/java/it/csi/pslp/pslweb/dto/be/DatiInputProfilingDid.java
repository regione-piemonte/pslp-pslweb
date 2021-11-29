/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class DatiInputProfilingDid   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idAnagrafica = null;
  private String codiceFiscale = null;
  private String idTitoloStudio = null;
  private String idCondizioneOccupazionale = null;
  private String idPosizioneProfessionale = null;
  private String idIscrizioneCorsi = null;
  private String idPresenzaInItalia = null;
  private Boolean avutoAlmenoUnLav = null;
  private Boolean figliACarico = null;
  private Boolean figliMinoriACarico = null;
  private Long numMesiFineUltimoLavoro = null;
  private Long numMesiRicercaLavoro = null;
  private Long numComponentiFamiglia = null;

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
  

  @JsonProperty("id_titolo_studio") 
 
  public String getIdTitoloStudio() {
    return idTitoloStudio;
  }
  public void setIdTitoloStudio(String idTitoloStudio) {
    this.idTitoloStudio = idTitoloStudio;
  }

  /**
   **/
  

  @JsonProperty("id_condizione_occupazionale") 
 
  public String getIdCondizioneOccupazionale() {
    return idCondizioneOccupazionale;
  }
  public void setIdCondizioneOccupazionale(String idCondizioneOccupazionale) {
    this.idCondizioneOccupazionale = idCondizioneOccupazionale;
  }

  /**
   **/
  

  @JsonProperty("id_posizione_professionale") 
 
  public String getIdPosizioneProfessionale() {
    return idPosizioneProfessionale;
  }
  public void setIdPosizioneProfessionale(String idPosizioneProfessionale) {
    this.idPosizioneProfessionale = idPosizioneProfessionale;
  }

  /**
   **/
  

  @JsonProperty("id_iscrizione_corsi") 
 
  public String getIdIscrizioneCorsi() {
    return idIscrizioneCorsi;
  }
  public void setIdIscrizioneCorsi(String idIscrizioneCorsi) {
    this.idIscrizioneCorsi = idIscrizioneCorsi;
  }

  /**
   **/
  

  @JsonProperty("id_presenza_in_italia") 
 
  public String getIdPresenzaInItalia() {
    return idPresenzaInItalia;
  }
  public void setIdPresenzaInItalia(String idPresenzaInItalia) {
    this.idPresenzaInItalia = idPresenzaInItalia;
  }

  /**
   **/
  

  @JsonProperty("avuto_almeno_un_lav") 
 
  public Boolean isAvutoAlmenoUnLav() {
    return avutoAlmenoUnLav;
  }
  public void setAvutoAlmenoUnLav(Boolean avutoAlmenoUnLav) {
    this.avutoAlmenoUnLav = avutoAlmenoUnLav;
  }

  /**
   **/
  

  @JsonProperty("figli_a_carico") 
 
  public Boolean isFigliACarico() {
    return figliACarico;
  }
  public void setFigliACarico(Boolean figliACarico) {
    this.figliACarico = figliACarico;
  }

  /**
   **/
  

  @JsonProperty("figli_minori_a_carico") 
 
  public Boolean isFigliMinoriACarico() {
    return figliMinoriACarico;
  }
  public void setFigliMinoriACarico(Boolean figliMinoriACarico) {
    this.figliMinoriACarico = figliMinoriACarico;
  }

  /**
   **/
  

  @JsonProperty("num_mesi_fine_ultimo_lavoro") 
 
  public Long getNumMesiFineUltimoLavoro() {
    return numMesiFineUltimoLavoro;
  }
  public void setNumMesiFineUltimoLavoro(Long numMesiFineUltimoLavoro) {
    this.numMesiFineUltimoLavoro = numMesiFineUltimoLavoro;
  }

  /**
   **/
  

  @JsonProperty("num_mesi_ricerca_lavoro") 
 
  public Long getNumMesiRicercaLavoro() {
    return numMesiRicercaLavoro;
  }
  public void setNumMesiRicercaLavoro(Long numMesiRicercaLavoro) {
    this.numMesiRicercaLavoro = numMesiRicercaLavoro;
  }

  /**
   **/
  

  @JsonProperty("num_componenti_famiglia") 
 
  public Long getNumComponentiFamiglia() {
    return numComponentiFamiglia;
  }
  public void setNumComponentiFamiglia(Long numComponentiFamiglia) {
    this.numComponentiFamiglia = numComponentiFamiglia;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatiInputProfilingDid datiInputProfilingDid = (DatiInputProfilingDid) o;
    return Objects.equals(idAnagrafica, datiInputProfilingDid.idAnagrafica) &&
        Objects.equals(codiceFiscale, datiInputProfilingDid.codiceFiscale) &&
        Objects.equals(idTitoloStudio, datiInputProfilingDid.idTitoloStudio) &&
        Objects.equals(idCondizioneOccupazionale, datiInputProfilingDid.idCondizioneOccupazionale) &&
        Objects.equals(idPosizioneProfessionale, datiInputProfilingDid.idPosizioneProfessionale) &&
        Objects.equals(idIscrizioneCorsi, datiInputProfilingDid.idIscrizioneCorsi) &&
        Objects.equals(idPresenzaInItalia, datiInputProfilingDid.idPresenzaInItalia) &&
        Objects.equals(avutoAlmenoUnLav, datiInputProfilingDid.avutoAlmenoUnLav) &&
        Objects.equals(figliACarico, datiInputProfilingDid.figliACarico) &&
        Objects.equals(figliMinoriACarico, datiInputProfilingDid.figliMinoriACarico) &&
        Objects.equals(numMesiFineUltimoLavoro, datiInputProfilingDid.numMesiFineUltimoLavoro) &&
        Objects.equals(numMesiRicercaLavoro, datiInputProfilingDid.numMesiRicercaLavoro) &&
        Objects.equals(numComponentiFamiglia, datiInputProfilingDid.numComponentiFamiglia);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idAnagrafica, codiceFiscale, idTitoloStudio, idCondizioneOccupazionale, idPosizioneProfessionale, idIscrizioneCorsi, idPresenzaInItalia, avutoAlmenoUnLav, figliACarico, figliMinoriACarico, numMesiFineUltimoLavoro, numMesiRicercaLavoro, numComponentiFamiglia);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DatiInputProfilingDid {\n");
    
    sb.append("    idAnagrafica: ").append(toIndentedString(idAnagrafica)).append("\n");
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    idTitoloStudio: ").append(toIndentedString(idTitoloStudio)).append("\n");
    sb.append("    idCondizioneOccupazionale: ").append(toIndentedString(idCondizioneOccupazionale)).append("\n");
    sb.append("    idPosizioneProfessionale: ").append(toIndentedString(idPosizioneProfessionale)).append("\n");
    sb.append("    idIscrizioneCorsi: ").append(toIndentedString(idIscrizioneCorsi)).append("\n");
    sb.append("    idPresenzaInItalia: ").append(toIndentedString(idPresenzaInItalia)).append("\n");
    sb.append("    avutoAlmenoUnLav: ").append(toIndentedString(avutoAlmenoUnLav)).append("\n");
    sb.append("    figliACarico: ").append(toIndentedString(figliACarico)).append("\n");
    sb.append("    figliMinoriACarico: ").append(toIndentedString(figliMinoriACarico)).append("\n");
    sb.append("    numMesiFineUltimoLavoro: ").append(toIndentedString(numMesiFineUltimoLavoro)).append("\n");
    sb.append("    numMesiRicercaLavoro: ").append(toIndentedString(numMesiRicercaLavoro)).append("\n");
    sb.append("    numComponentiFamiglia: ").append(toIndentedString(numComponentiFamiglia)).append("\n");
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

