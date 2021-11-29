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

public class Responsabile   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idResponsabile = null;
  private String idSilTNazioneRes = null;
  private String dsIndirizzoDom = null;
  private String dsFax = null;
  private String dsNumCivicoDom = null;
  private String idSilTComuneDom = null;
  private String genere = null;
  private String capDom = null;
  private String dsIndirizzoRes = null;
  private String idSilTComuneRes = null;
  private Date dScadenzaPermSogg = null;
  private String idSilTCittadinanza = null;
  private String idSilTProvinciaDom = null;
  private String idSilTToponimoDom = null;
  private String idSilTProvinciaRes = null;
  private Date dNascita = null;
  private String dsTelefonoCellulare = null;
  private String dsIndirizzoResEsteso = null;
  private String idSilTComuneNasc = null;
  private String numeroPermSogg = null;
  private String idSilTNazioneNasc = null;
  private String capRes = null;
  private String dsTelefonoFisso = null;
  private String dsNumCivicoRes = null;
  private String idSilTStatusLavExtraUe = null;
  private String idSilTMotRilPerm = null;
  private String idSilTToponimoRes = null;
  private String dsIndirizzoDomEsteso = null;
  private String idSilTNazioneDom = null;

  /**
   * id responsabile&#x3D;&#x3D;id utente
   **/
  

  @JsonProperty("id_responsabile") 
 
  public Long getIdResponsabile() {
    return idResponsabile;
  }
  public void setIdResponsabile(Long idResponsabile) {
    this.idResponsabile = idResponsabile;
  }

  /**
   * nazione di residenza
   **/
  

  @JsonProperty("idSilTNazioneRes") 
 
  public String getIdSilTNazioneRes() {
    return idSilTNazioneRes;
  }
  public void setIdSilTNazioneRes(String idSilTNazioneRes) {
    this.idSilTNazioneRes = idSilTNazioneRes;
  }

  /**
   * indirizzo di domicilio
   **/
  

  @JsonProperty("dsIndirizzoDom") 
 
  public String getDsIndirizzoDom() {
    return dsIndirizzoDom;
  }
  public void setDsIndirizzoDom(String dsIndirizzoDom) {
    this.dsIndirizzoDom = dsIndirizzoDom;
  }

  /**
   * fax
   **/
  

  @JsonProperty("dsFax") 
 
  public String getDsFax() {
    return dsFax;
  }
  public void setDsFax(String dsFax) {
    this.dsFax = dsFax;
  }

  /**
   * numero civico domicilio
   **/
  

  @JsonProperty("dsNumCivicoDom") 
 
  public String getDsNumCivicoDom() {
    return dsNumCivicoDom;
  }
  public void setDsNumCivicoDom(String dsNumCivicoDom) {
    this.dsNumCivicoDom = dsNumCivicoDom;
  }

  /**
   * comune di domicilio
   **/
  

  @JsonProperty("idSilTComuneDom") 
 
  public String getIdSilTComuneDom() {
    return idSilTComuneDom;
  }
  public void setIdSilTComuneDom(String idSilTComuneDom) {
    this.idSilTComuneDom = idSilTComuneDom;
  }

  /**
   * genere
   **/
  

  @JsonProperty("genere") 
 
  public String getGenere() {
    return genere;
  }
  public void setGenere(String genere) {
    this.genere = genere;
  }

  /**
   * cap comune domicilio
   **/
  

  @JsonProperty("capDom") 
 
  public String getCapDom() {
    return capDom;
  }
  public void setCapDom(String capDom) {
    this.capDom = capDom;
  }

  /**
   * indirizzo di residenza
   **/
  

  @JsonProperty("dsIndirizzoRes") 
 
  public String getDsIndirizzoRes() {
    return dsIndirizzoRes;
  }
  public void setDsIndirizzoRes(String dsIndirizzoRes) {
    this.dsIndirizzoRes = dsIndirizzoRes;
  }

  /**
   * comune di residenza
   **/
  

  @JsonProperty("idSilTComuneRes") 
 
  public String getIdSilTComuneRes() {
    return idSilTComuneRes;
  }
  public void setIdSilTComuneRes(String idSilTComuneRes) {
    this.idSilTComuneRes = idSilTComuneRes;
  }

  /**
   * data scadenza permesso di soggiorno
   **/
  

  @JsonProperty("dScadenzaPermSogg") 
 
  public Date getDScadenzaPermSogg() {
    return dScadenzaPermSogg;
  }
  public void setDScadenzaPermSogg(Date dScadenzaPermSogg) {
    this.dScadenzaPermSogg = dScadenzaPermSogg;
  }

  /**
   * citadinanza
   **/
  

  @JsonProperty("idSilTCittadinanza") 
 
  public String getIdSilTCittadinanza() {
    return idSilTCittadinanza;
  }
  public void setIdSilTCittadinanza(String idSilTCittadinanza) {
    this.idSilTCittadinanza = idSilTCittadinanza;
  }

  /**
   * provincia di domicilio
   **/
  

  @JsonProperty("idSilTProvinciaDom") 
 
  public String getIdSilTProvinciaDom() {
    return idSilTProvinciaDom;
  }
  public void setIdSilTProvinciaDom(String idSilTProvinciaDom) {
    this.idSilTProvinciaDom = idSilTProvinciaDom;
  }

  /**
   * toponimo di domicilio
   **/
  

  @JsonProperty("idSilTToponimoDom") 
 
  public String getIdSilTToponimoDom() {
    return idSilTToponimoDom;
  }
  public void setIdSilTToponimoDom(String idSilTToponimoDom) {
    this.idSilTToponimoDom = idSilTToponimoDom;
  }

  /**
   * provincia di residenza
   **/
  

  @JsonProperty("idSilTProvinciaRes") 
 
  public String getIdSilTProvinciaRes() {
    return idSilTProvinciaRes;
  }
  public void setIdSilTProvinciaRes(String idSilTProvinciaRes) {
    this.idSilTProvinciaRes = idSilTProvinciaRes;
  }

  /**
   * data di nascita
   **/
  

  @JsonProperty("dNascita") 
 
  public Date getDNascita() {
    return dNascita;
  }
  public void setDNascita(Date dNascita) {
    this.dNascita = dNascita;
  }

  /**
   * telefono cellulare
   **/
  

  @JsonProperty("dsTelefonoCellulare") 
 
  public String getDsTelefonoCellulare() {
    return dsTelefonoCellulare;
  }
  public void setDsTelefonoCellulare(String dsTelefonoCellulare) {
    this.dsTelefonoCellulare = dsTelefonoCellulare;
  }

  /**
   * indirizzo di residenza esteso
   **/
  

  @JsonProperty("dsIndirizzoResEsteso") 
 
  public String getDsIndirizzoResEsteso() {
    return dsIndirizzoResEsteso;
  }
  public void setDsIndirizzoResEsteso(String dsIndirizzoResEsteso) {
    this.dsIndirizzoResEsteso = dsIndirizzoResEsteso;
  }

  /**
   * comune di nascita
   **/
  

  @JsonProperty("idSilTComuneNasc") 
 
  public String getIdSilTComuneNasc() {
    return idSilTComuneNasc;
  }
  public void setIdSilTComuneNasc(String idSilTComuneNasc) {
    this.idSilTComuneNasc = idSilTComuneNasc;
  }

  /**
   * numero permesso di soggiorno
   **/
  

  @JsonProperty("numeroPermSogg") 
 
  public String getNumeroPermSogg() {
    return numeroPermSogg;
  }
  public void setNumeroPermSogg(String numeroPermSogg) {
    this.numeroPermSogg = numeroPermSogg;
  }

  /**
   * nazione di nascita
   **/
  

  @JsonProperty("idSilTNazioneNasc") 
 
  public String getIdSilTNazioneNasc() {
    return idSilTNazioneNasc;
  }
  public void setIdSilTNazioneNasc(String idSilTNazioneNasc) {
    this.idSilTNazioneNasc = idSilTNazioneNasc;
  }

  /**
   * cap comune di residenza
   **/
  

  @JsonProperty("capRes") 
 
  public String getCapRes() {
    return capRes;
  }
  public void setCapRes(String capRes) {
    this.capRes = capRes;
  }

  /**
   * telefono fisso
   **/
  

  @JsonProperty("dsTelefonoFisso") 
 
  public String getDsTelefonoFisso() {
    return dsTelefonoFisso;
  }
  public void setDsTelefonoFisso(String dsTelefonoFisso) {
    this.dsTelefonoFisso = dsTelefonoFisso;
  }

  /**
   * numero civico di residenza
   **/
  

  @JsonProperty("dsNumCivicoRes") 
 
  public String getDsNumCivicoRes() {
    return dsNumCivicoRes;
  }
  public void setDsNumCivicoRes(String dsNumCivicoRes) {
    this.dsNumCivicoRes = dsNumCivicoRes;
  }

  /**
   * status lavoratore extra UE
   **/
  

  @JsonProperty("idSilTStatusLavExtraUe") 
 
  public String getIdSilTStatusLavExtraUe() {
    return idSilTStatusLavExtraUe;
  }
  public void setIdSilTStatusLavExtraUe(String idSilTStatusLavExtraUe) {
    this.idSilTStatusLavExtraUe = idSilTStatusLavExtraUe;
  }

  /**
   * motivo rilascio permesso di soggiorno
   **/
  

  @JsonProperty("idSilTMotRilPerm") 
 
  public String getIdSilTMotRilPerm() {
    return idSilTMotRilPerm;
  }
  public void setIdSilTMotRilPerm(String idSilTMotRilPerm) {
    this.idSilTMotRilPerm = idSilTMotRilPerm;
  }

  /**
   * toponimo di residenza
   **/
  

  @JsonProperty("idSilTToponimoRes") 
 
  public String getIdSilTToponimoRes() {
    return idSilTToponimoRes;
  }
  public void setIdSilTToponimoRes(String idSilTToponimoRes) {
    this.idSilTToponimoRes = idSilTToponimoRes;
  }

  /**
   * indirizzo di domicilio esteso
   **/
  

  @JsonProperty("dsIndirizzoDomEsteso") 
 
  public String getDsIndirizzoDomEsteso() {
    return dsIndirizzoDomEsteso;
  }
  public void setDsIndirizzoDomEsteso(String dsIndirizzoDomEsteso) {
    this.dsIndirizzoDomEsteso = dsIndirizzoDomEsteso;
  }

  /**
   * nazione di domicilio
   **/
  

  @JsonProperty("idSilTNazioneDom") 
 
  public String getIdSilTNazioneDom() {
    return idSilTNazioneDom;
  }
  public void setIdSilTNazioneDom(String idSilTNazioneDom) {
    this.idSilTNazioneDom = idSilTNazioneDom;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Responsabile responsabile = (Responsabile) o;
    return Objects.equals(idResponsabile, responsabile.idResponsabile) &&
        Objects.equals(idSilTNazioneRes, responsabile.idSilTNazioneRes) &&
        Objects.equals(dsIndirizzoDom, responsabile.dsIndirizzoDom) &&
        Objects.equals(dsFax, responsabile.dsFax) &&
        Objects.equals(dsNumCivicoDom, responsabile.dsNumCivicoDom) &&
        Objects.equals(idSilTComuneDom, responsabile.idSilTComuneDom) &&
        Objects.equals(genere, responsabile.genere) &&
        Objects.equals(capDom, responsabile.capDom) &&
        Objects.equals(dsIndirizzoRes, responsabile.dsIndirizzoRes) &&
        Objects.equals(idSilTComuneRes, responsabile.idSilTComuneRes) &&
        Objects.equals(dScadenzaPermSogg, responsabile.dScadenzaPermSogg) &&
        Objects.equals(idSilTCittadinanza, responsabile.idSilTCittadinanza) &&
        Objects.equals(idSilTProvinciaDom, responsabile.idSilTProvinciaDom) &&
        Objects.equals(idSilTToponimoDom, responsabile.idSilTToponimoDom) &&
        Objects.equals(idSilTProvinciaRes, responsabile.idSilTProvinciaRes) &&
        Objects.equals(dNascita, responsabile.dNascita) &&
        Objects.equals(dsTelefonoCellulare, responsabile.dsTelefonoCellulare) &&
        Objects.equals(dsIndirizzoResEsteso, responsabile.dsIndirizzoResEsteso) &&
        Objects.equals(idSilTComuneNasc, responsabile.idSilTComuneNasc) &&
        Objects.equals(numeroPermSogg, responsabile.numeroPermSogg) &&
        Objects.equals(idSilTNazioneNasc, responsabile.idSilTNazioneNasc) &&
        Objects.equals(capRes, responsabile.capRes) &&
        Objects.equals(dsTelefonoFisso, responsabile.dsTelefonoFisso) &&
        Objects.equals(dsNumCivicoRes, responsabile.dsNumCivicoRes) &&
        Objects.equals(idSilTStatusLavExtraUe, responsabile.idSilTStatusLavExtraUe) &&
        Objects.equals(idSilTMotRilPerm, responsabile.idSilTMotRilPerm) &&
        Objects.equals(idSilTToponimoRes, responsabile.idSilTToponimoRes) &&
        Objects.equals(dsIndirizzoDomEsteso, responsabile.dsIndirizzoDomEsteso) &&
        Objects.equals(idSilTNazioneDom, responsabile.idSilTNazioneDom);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idResponsabile, idSilTNazioneRes, dsIndirizzoDom, dsFax, dsNumCivicoDom, idSilTComuneDom, genere, capDom, dsIndirizzoRes, idSilTComuneRes, dScadenzaPermSogg, idSilTCittadinanza, idSilTProvinciaDom, idSilTToponimoDom, idSilTProvinciaRes, dNascita, dsTelefonoCellulare, dsIndirizzoResEsteso, idSilTComuneNasc, numeroPermSogg, idSilTNazioneNasc, capRes, dsTelefonoFisso, dsNumCivicoRes, idSilTStatusLavExtraUe, idSilTMotRilPerm, idSilTToponimoRes, dsIndirizzoDomEsteso, idSilTNazioneDom);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Responsabile {\n");
    
    sb.append("    idResponsabile: ").append(toIndentedString(idResponsabile)).append("\n");
    sb.append("    idSilTNazioneRes: ").append(toIndentedString(idSilTNazioneRes)).append("\n");
    sb.append("    dsIndirizzoDom: ").append(toIndentedString(dsIndirizzoDom)).append("\n");
    sb.append("    dsFax: ").append(toIndentedString(dsFax)).append("\n");
    sb.append("    dsNumCivicoDom: ").append(toIndentedString(dsNumCivicoDom)).append("\n");
    sb.append("    idSilTComuneDom: ").append(toIndentedString(idSilTComuneDom)).append("\n");
    sb.append("    genere: ").append(toIndentedString(genere)).append("\n");
    sb.append("    capDom: ").append(toIndentedString(capDom)).append("\n");
    sb.append("    dsIndirizzoRes: ").append(toIndentedString(dsIndirizzoRes)).append("\n");
    sb.append("    idSilTComuneRes: ").append(toIndentedString(idSilTComuneRes)).append("\n");
    sb.append("    dScadenzaPermSogg: ").append(toIndentedString(dScadenzaPermSogg)).append("\n");
    sb.append("    idSilTCittadinanza: ").append(toIndentedString(idSilTCittadinanza)).append("\n");
    sb.append("    idSilTProvinciaDom: ").append(toIndentedString(idSilTProvinciaDom)).append("\n");
    sb.append("    idSilTToponimoDom: ").append(toIndentedString(idSilTToponimoDom)).append("\n");
    sb.append("    idSilTProvinciaRes: ").append(toIndentedString(idSilTProvinciaRes)).append("\n");
    sb.append("    dNascita: ").append(toIndentedString(dNascita)).append("\n");
    sb.append("    dsTelefonoCellulare: ").append(toIndentedString(dsTelefonoCellulare)).append("\n");
    sb.append("    dsIndirizzoResEsteso: ").append(toIndentedString(dsIndirizzoResEsteso)).append("\n");
    sb.append("    idSilTComuneNasc: ").append(toIndentedString(idSilTComuneNasc)).append("\n");
    sb.append("    numeroPermSogg: ").append(toIndentedString(numeroPermSogg)).append("\n");
    sb.append("    idSilTNazioneNasc: ").append(toIndentedString(idSilTNazioneNasc)).append("\n");
    sb.append("    capRes: ").append(toIndentedString(capRes)).append("\n");
    sb.append("    dsTelefonoFisso: ").append(toIndentedString(dsTelefonoFisso)).append("\n");
    sb.append("    dsNumCivicoRes: ").append(toIndentedString(dsNumCivicoRes)).append("\n");
    sb.append("    idSilTStatusLavExtraUe: ").append(toIndentedString(idSilTStatusLavExtraUe)).append("\n");
    sb.append("    idSilTMotRilPerm: ").append(toIndentedString(idSilTMotRilPerm)).append("\n");
    sb.append("    idSilTToponimoRes: ").append(toIndentedString(idSilTToponimoRes)).append("\n");
    sb.append("    dsIndirizzoDomEsteso: ").append(toIndentedString(dsIndirizzoDomEsteso)).append("\n");
    sb.append("    idSilTNazioneDom: ").append(toIndentedString(idSilTNazioneDom)).append("\n");
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

