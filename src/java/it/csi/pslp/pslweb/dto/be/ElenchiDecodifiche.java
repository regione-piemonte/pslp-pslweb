/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.TipoConoscenzaInformatica;
import it.csi.pslp.pslweb.dto.be.TipoContratto;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ElenchiDecodifiche   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private List<Decodifica> lingue = new ArrayList<Decodifica>();
  private List<Decodifica> gradiConoscenzaLingue = new ArrayList<Decodifica>();
  private List<TipoConoscenzaInformatica> conoscenzeInformatiche = new ArrayList<TipoConoscenzaInformatica>();
  private List<Decodifica> categorieConoscenzaInformatiche = new ArrayList<Decodifica>();
  private List<Decodifica> gradiConoscenzaInformatica = new ArrayList<Decodifica>();
  private List<Decodifica> patenti = new ArrayList<Decodifica>();
  private List<Decodifica> patentini = new ArrayList<Decodifica>();
  private List<Decodifica> albi = new ArrayList<Decodifica>();
  private List<TipoContratto> tipiContratto = new ArrayList<TipoContratto>();
  private List<Decodifica> categorieInquadramento = new ArrayList<Decodifica>();
  private List<Decodifica> modalitaLavoro = new ArrayList<Decodifica>();
  private List<Decodifica> certificazioniAttestazioni = new ArrayList<Decodifica>();
  private List<Decodifica> regioni = new ArrayList<Decodifica>();
  private List<Decodifica> settoriAteco = new ArrayList<Decodifica>();

  /**
   **/
  

  @JsonProperty("lingue") 
 
  public List<Decodifica> getLingue() {
    return lingue;
  }
  public void setLingue(List<Decodifica> lingue) {
    this.lingue = lingue;
  }

  /**
   **/
  

  @JsonProperty("gradi_conoscenza_lingue") 
 
  public List<Decodifica> getGradiConoscenzaLingue() {
    return gradiConoscenzaLingue;
  }
  public void setGradiConoscenzaLingue(List<Decodifica> gradiConoscenzaLingue) {
    this.gradiConoscenzaLingue = gradiConoscenzaLingue;
  }

  /**
   **/
  

  @JsonProperty("conoscenze_informatiche") 
 
  public List<TipoConoscenzaInformatica> getConoscenzeInformatiche() {
    return conoscenzeInformatiche;
  }
  public void setConoscenzeInformatiche(List<TipoConoscenzaInformatica> conoscenzeInformatiche) {
    this.conoscenzeInformatiche = conoscenzeInformatiche;
  }

  /**
   * categorie silp di massima in cui suddividere le conoscenze informatiche. Non hanno un corrispettivo ministeriale, servono solo a semplificare le ricerche
   **/
  

  @JsonProperty("categorie_conoscenza_informatiche") 
 
  public List<Decodifica> getCategorieConoscenzaInformatiche() {
    return categorieConoscenzaInformatiche;
  }
  public void setCategorieConoscenzaInformatiche(List<Decodifica> categorieConoscenzaInformatiche) {
    this.categorieConoscenzaInformatiche = categorieConoscenzaInformatiche;
  }

  /**
   **/
  

  @JsonProperty("gradi_conoscenza_informatica") 
 
  public List<Decodifica> getGradiConoscenzaInformatica() {
    return gradiConoscenzaInformatica;
  }
  public void setGradiConoscenzaInformatica(List<Decodifica> gradiConoscenzaInformatica) {
    this.gradiConoscenzaInformatica = gradiConoscenzaInformatica;
  }

  /**
   **/
  

  @JsonProperty("patenti") 
 
  public List<Decodifica> getPatenti() {
    return patenti;
  }
  public void setPatenti(List<Decodifica> patenti) {
    this.patenti = patenti;
  }

  /**
   **/
  

  @JsonProperty("patentini") 
 
  public List<Decodifica> getPatentini() {
    return patentini;
  }
  public void setPatentini(List<Decodifica> patentini) {
    this.patentini = patentini;
  }

  /**
   **/
  

  @JsonProperty("albi") 
 
  public List<Decodifica> getAlbi() {
    return albi;
  }
  public void setAlbi(List<Decodifica> albi) {
    this.albi = albi;
  }

  /**
   **/
  

  @JsonProperty("tipi_contratto") 
 
  public List<TipoContratto> getTipiContratto() {
    return tipiContratto;
  }
  public void setTipiContratto(List<TipoContratto> tipiContratto) {
    this.tipiContratto = tipiContratto;
  }

  /**
   **/
  

  @JsonProperty("categorie_inquadramento") 
 
  public List<Decodifica> getCategorieInquadramento() {
    return categorieInquadramento;
  }
  public void setCategorieInquadramento(List<Decodifica> categorieInquadramento) {
    this.categorieInquadramento = categorieInquadramento;
  }

  /**
   **/
  

  @JsonProperty("modalita_lavoro") 
 
  public List<Decodifica> getModalitaLavoro() {
    return modalitaLavoro;
  }
  public void setModalitaLavoro(List<Decodifica> modalitaLavoro) {
    this.modalitaLavoro = modalitaLavoro;
  }

  /**
   **/
  

  @JsonProperty("certificazioni_attestazioni") 
 
  public List<Decodifica> getCertificazioniAttestazioni() {
    return certificazioniAttestazioni;
  }
  public void setCertificazioniAttestazioni(List<Decodifica> certificazioniAttestazioni) {
    this.certificazioniAttestazioni = certificazioniAttestazioni;
  }

  /**
   **/
  

  @JsonProperty("regioni") 
 
  public List<Decodifica> getRegioni() {
    return regioni;
  }
  public void setRegioni(List<Decodifica> regioni) {
    this.regioni = regioni;
  }

  /**
   **/
  

  @JsonProperty("settori_ateco") 
 
  public List<Decodifica> getSettoriAteco() {
    return settoriAteco;
  }
  public void setSettoriAteco(List<Decodifica> settoriAteco) {
    this.settoriAteco = settoriAteco;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ElenchiDecodifiche elenchiDecodifiche = (ElenchiDecodifiche) o;
    return Objects.equals(lingue, elenchiDecodifiche.lingue) &&
        Objects.equals(gradiConoscenzaLingue, elenchiDecodifiche.gradiConoscenzaLingue) &&
        Objects.equals(conoscenzeInformatiche, elenchiDecodifiche.conoscenzeInformatiche) &&
        Objects.equals(categorieConoscenzaInformatiche, elenchiDecodifiche.categorieConoscenzaInformatiche) &&
        Objects.equals(gradiConoscenzaInformatica, elenchiDecodifiche.gradiConoscenzaInformatica) &&
        Objects.equals(patenti, elenchiDecodifiche.patenti) &&
        Objects.equals(patentini, elenchiDecodifiche.patentini) &&
        Objects.equals(albi, elenchiDecodifiche.albi) &&
        Objects.equals(tipiContratto, elenchiDecodifiche.tipiContratto) &&
        Objects.equals(categorieInquadramento, elenchiDecodifiche.categorieInquadramento) &&
        Objects.equals(modalitaLavoro, elenchiDecodifiche.modalitaLavoro) &&
        Objects.equals(certificazioniAttestazioni, elenchiDecodifiche.certificazioniAttestazioni) &&
        Objects.equals(regioni, elenchiDecodifiche.regioni) &&
        Objects.equals(settoriAteco, elenchiDecodifiche.settoriAteco);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lingue, gradiConoscenzaLingue, conoscenzeInformatiche, categorieConoscenzaInformatiche, gradiConoscenzaInformatica, patenti, patentini, albi, tipiContratto, categorieInquadramento, modalitaLavoro, certificazioniAttestazioni, regioni, settoriAteco);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ElenchiDecodifiche {\n");
    
    sb.append("    lingue: ").append(toIndentedString(lingue)).append("\n");
    sb.append("    gradiConoscenzaLingue: ").append(toIndentedString(gradiConoscenzaLingue)).append("\n");
    sb.append("    conoscenzeInformatiche: ").append(toIndentedString(conoscenzeInformatiche)).append("\n");
    sb.append("    categorieConoscenzaInformatiche: ").append(toIndentedString(categorieConoscenzaInformatiche)).append("\n");
    sb.append("    gradiConoscenzaInformatica: ").append(toIndentedString(gradiConoscenzaInformatica)).append("\n");
    sb.append("    patenti: ").append(toIndentedString(patenti)).append("\n");
    sb.append("    patentini: ").append(toIndentedString(patentini)).append("\n");
    sb.append("    albi: ").append(toIndentedString(albi)).append("\n");
    sb.append("    tipiContratto: ").append(toIndentedString(tipiContratto)).append("\n");
    sb.append("    categorieInquadramento: ").append(toIndentedString(categorieInquadramento)).append("\n");
    sb.append("    modalitaLavoro: ").append(toIndentedString(modalitaLavoro)).append("\n");
    sb.append("    certificazioniAttestazioni: ").append(toIndentedString(certificazioniAttestazioni)).append("\n");
    sb.append("    regioni: ").append(toIndentedString(regioni)).append("\n");
    sb.append("    settoriAteco: ").append(toIndentedString(settoriAteco)).append("\n");
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

