/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.AdesioneYG;
import it.csi.pslp.pslweb.dto.be.PrenotazioneIncontro;
import it.csi.pslp.pslweb.dto.be.SchedaAnagraficoProfessionale;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ControlliIscrizione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private SchedaAnagraficoProfessionale sap = null;
  private AdesioneYG adesione = null;
  private Boolean adesioneFuoriRegione = null;
  private PrenotazioneIncontro appuntamento = null;
  private Integer eta = null;
  private Boolean soggettoIdoneo = null;
  private Boolean presentePrivacy = null;
  private Boolean domicilioPiemonte = null;
  private Boolean residenzaItalia = null;
  private Boolean etaCoerente = null;
  private Boolean maggiorenne = null;
  private List<String> messaggi = new ArrayList<String>();
  private Boolean possibileIscrizione = null;
  private Boolean possibileAnnullamento = null;
  private Boolean possibileStampa = null;
  private Boolean possibileAppuntamento = null;

  /**
   **/
  

  @JsonProperty("sap") 
 
  public SchedaAnagraficoProfessionale getSap() {
    return sap;
  }
  public void setSap(SchedaAnagraficoProfessionale sap) {
    this.sap = sap;
  }

  /**
   **/
  

  @JsonProperty("adesione") 
 
  public AdesioneYG getAdesione() {
    return adesione;
  }
  public void setAdesione(AdesioneYG adesione) {
    this.adesione = adesione;
  }

  /**
   **/
  

  @JsonProperty("adesioneFuoriRegione") 
 
  public Boolean isAdesioneFuoriRegione() {
    return adesioneFuoriRegione;
  }
  public void setAdesioneFuoriRegione(Boolean adesioneFuoriRegione) {
    this.adesioneFuoriRegione = adesioneFuoriRegione;
  }

  /**
   **/
  

  @JsonProperty("appuntamento") 
 
  public PrenotazioneIncontro getAppuntamento() {
    return appuntamento;
  }
  public void setAppuntamento(PrenotazioneIncontro appuntamento) {
    this.appuntamento = appuntamento;
  }

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
   **/
  

  @JsonProperty("soggetto_idoneo") 
 
  public Boolean isSoggettoIdoneo() {
    return soggettoIdoneo;
  }
  public void setSoggettoIdoneo(Boolean soggettoIdoneo) {
    this.soggettoIdoneo = soggettoIdoneo;
  }

  /**
   **/
  

  @JsonProperty("presente_privacy") 
 
  public Boolean isPresentePrivacy() {
    return presentePrivacy;
  }
  public void setPresentePrivacy(Boolean presentePrivacy) {
    this.presentePrivacy = presentePrivacy;
  }

  /**
   **/
  

  @JsonProperty("domicilio_piemonte") 
 
  public Boolean isDomicilioPiemonte() {
    return domicilioPiemonte;
  }
  public void setDomicilioPiemonte(Boolean domicilioPiemonte) {
    this.domicilioPiemonte = domicilioPiemonte;
  }

  /**
   **/
  

  @JsonProperty("residenza_italia") 
 
  public Boolean isResidenzaItalia() {
    return residenzaItalia;
  }
  public void setResidenzaItalia(Boolean residenzaItalia) {
    this.residenzaItalia = residenzaItalia;
  }

  /**
   **/
  

  @JsonProperty("eta_coerente") 
 
  public Boolean isEtaCoerente() {
    return etaCoerente;
  }
  public void setEtaCoerente(Boolean etaCoerente) {
    this.etaCoerente = etaCoerente;
  }

  /**
   **/
  

  @JsonProperty("maggiorenne") 
 
  public Boolean isMaggiorenne() {
    return maggiorenne;
  }
  public void setMaggiorenne(Boolean maggiorenne) {
    this.maggiorenne = maggiorenne;
  }

  /**
   **/
  

  @JsonProperty("messaggi") 
 
  public List<String> getMessaggi() {
    return messaggi;
  }
  public void setMessaggi(List<String> messaggi) {
    this.messaggi = messaggi;
  }

  /**
   **/
  

  @JsonProperty("possibileIscrizione") 
 
  public Boolean isPossibileIscrizione() {
    return possibileIscrizione;
  }
  public void setPossibileIscrizione(Boolean possibileIscrizione) {
    this.possibileIscrizione = possibileIscrizione;
  }

  /**
   **/
  

  @JsonProperty("possibileAnnullamento") 
 
  public Boolean isPossibileAnnullamento() {
    return possibileAnnullamento;
  }
  public void setPossibileAnnullamento(Boolean possibileAnnullamento) {
    this.possibileAnnullamento = possibileAnnullamento;
  }

  /**
   **/
  

  @JsonProperty("possibileStampa") 
 
  public Boolean isPossibileStampa() {
    return possibileStampa;
  }
  public void setPossibileStampa(Boolean possibileStampa) {
    this.possibileStampa = possibileStampa;
  }

  /**
   **/
  

  @JsonProperty("possibileAppuntamento") 
 
  public Boolean isPossibileAppuntamento() {
    return possibileAppuntamento;
  }
  public void setPossibileAppuntamento(Boolean possibileAppuntamento) {
    this.possibileAppuntamento = possibileAppuntamento;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ControlliIscrizione controlliIscrizione = (ControlliIscrizione) o;
    return Objects.equals(sap, controlliIscrizione.sap) &&
        Objects.equals(adesione, controlliIscrizione.adesione) &&
        Objects.equals(adesioneFuoriRegione, controlliIscrizione.adesioneFuoriRegione) &&
        Objects.equals(appuntamento, controlliIscrizione.appuntamento) &&
        Objects.equals(eta, controlliIscrizione.eta) &&
        Objects.equals(soggettoIdoneo, controlliIscrizione.soggettoIdoneo) &&
        Objects.equals(presentePrivacy, controlliIscrizione.presentePrivacy) &&
        Objects.equals(domicilioPiemonte, controlliIscrizione.domicilioPiemonte) &&
        Objects.equals(residenzaItalia, controlliIscrizione.residenzaItalia) &&
        Objects.equals(etaCoerente, controlliIscrizione.etaCoerente) &&
        Objects.equals(maggiorenne, controlliIscrizione.maggiorenne) &&
        Objects.equals(messaggi, controlliIscrizione.messaggi) &&
        Objects.equals(possibileIscrizione, controlliIscrizione.possibileIscrizione) &&
        Objects.equals(possibileAnnullamento, controlliIscrizione.possibileAnnullamento) &&
        Objects.equals(possibileStampa, controlliIscrizione.possibileStampa) &&
        Objects.equals(possibileAppuntamento, controlliIscrizione.possibileAppuntamento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sap, adesione, adesioneFuoriRegione, appuntamento, eta, soggettoIdoneo, presentePrivacy, domicilioPiemonte, residenzaItalia, etaCoerente, maggiorenne, messaggi, possibileIscrizione, possibileAnnullamento, possibileStampa, possibileAppuntamento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ControlliIscrizione {\n");
    
    sb.append("    sap: ").append(toIndentedString(sap)).append("\n");
    sb.append("    adesione: ").append(toIndentedString(adesione)).append("\n");
    sb.append("    adesioneFuoriRegione: ").append(toIndentedString(adesioneFuoriRegione)).append("\n");
    sb.append("    appuntamento: ").append(toIndentedString(appuntamento)).append("\n");
    sb.append("    eta: ").append(toIndentedString(eta)).append("\n");
    sb.append("    soggettoIdoneo: ").append(toIndentedString(soggettoIdoneo)).append("\n");
    sb.append("    presentePrivacy: ").append(toIndentedString(presentePrivacy)).append("\n");
    sb.append("    domicilioPiemonte: ").append(toIndentedString(domicilioPiemonte)).append("\n");
    sb.append("    residenzaItalia: ").append(toIndentedString(residenzaItalia)).append("\n");
    sb.append("    etaCoerente: ").append(toIndentedString(etaCoerente)).append("\n");
    sb.append("    maggiorenne: ").append(toIndentedString(maggiorenne)).append("\n");
    sb.append("    messaggi: ").append(toIndentedString(messaggi)).append("\n");
    sb.append("    possibileIscrizione: ").append(toIndentedString(possibileIscrizione)).append("\n");
    sb.append("    possibileAnnullamento: ").append(toIndentedString(possibileAnnullamento)).append("\n");
    sb.append("    possibileStampa: ").append(toIndentedString(possibileStampa)).append("\n");
    sb.append("    possibileAppuntamento: ").append(toIndentedString(possibileAppuntamento)).append("\n");
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

