/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.DatoreLavoro;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.Indirizzo;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class EsperienzaLavoro   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Date dataInizioRapporto = null;
  private Date dataFineRapporto = null;
  private Decodifica tipoContratto = null;
  private Decodifica qualifica = null;
  private Decodifica categoriaInquadramento = null;
  private DatoreLavoro datoreLavoro = null;
  private DatoreLavoro aziendaUtilizzatrice = null;
  private Date dataFinePeriodoFormativo = null;
  private String principaliMansioni = null;
  private Boolean assunzioneL68 = null;
  private Boolean lavoratoreInMobilita = null;
  private Boolean lavoroStagionale = null;
  private Boolean lavoroInAgricoltura = null;
  private Boolean esperienzaDichiarata = null;
  private Decodifica modalitaLavoro = null;
  private Indirizzo indirizzoDiLavoro = null;

  /**
   **/
  

  @JsonProperty("data_inizio_rapporto") 
 
  public Date getDataInizioRapporto() {
    return dataInizioRapporto;
  }
  public void setDataInizioRapporto(Date dataInizioRapporto) {
    this.dataInizioRapporto = dataInizioRapporto;
  }

  /**
   **/
  

  @JsonProperty("data_fine_rapporto") 
 
  public Date getDataFineRapporto() {
    return dataFineRapporto;
  }
  public void setDataFineRapporto(Date dataFineRapporto) {
    this.dataFineRapporto = dataFineRapporto;
  }

  /**
   **/
  

  @JsonProperty("tipo_contratto") 
 
  public Decodifica getTipoContratto() {
    return tipoContratto;
  }
  public void setTipoContratto(Decodifica tipoContratto) {
    this.tipoContratto = tipoContratto;
  }

  /**
   **/
  

  @JsonProperty("qualifica") 
 
  public Decodifica getQualifica() {
    return qualifica;
  }
  public void setQualifica(Decodifica qualifica) {
    this.qualifica = qualifica;
  }

  /**
   **/
  

  @JsonProperty("categoria_inquadramento") 
 
  public Decodifica getCategoriaInquadramento() {
    return categoriaInquadramento;
  }
  public void setCategoriaInquadramento(Decodifica categoriaInquadramento) {
    this.categoriaInquadramento = categoriaInquadramento;
  }

  /**
   **/
  

  @JsonProperty("datore_lavoro") 
 
  public DatoreLavoro getDatoreLavoro() {
    return datoreLavoro;
  }
  public void setDatoreLavoro(DatoreLavoro datoreLavoro) {
    this.datoreLavoro = datoreLavoro;
  }

  /**
   **/
  

  @JsonProperty("azienda_utilizzatrice") 
 
  public DatoreLavoro getAziendaUtilizzatrice() {
    return aziendaUtilizzatrice;
  }
  public void setAziendaUtilizzatrice(DatoreLavoro aziendaUtilizzatrice) {
    this.aziendaUtilizzatrice = aziendaUtilizzatrice;
  }

  /**
   **/
  

  @JsonProperty("data_fine_periodo_formativo") 
 
  public Date getDataFinePeriodoFormativo() {
    return dataFinePeriodoFormativo;
  }
  public void setDataFinePeriodoFormativo(Date dataFinePeriodoFormativo) {
    this.dataFinePeriodoFormativo = dataFinePeriodoFormativo;
  }

  /**
   **/
  

  @JsonProperty("principali_mansioni") 
 
  public String getPrincipaliMansioni() {
    return principaliMansioni;
  }
  public void setPrincipaliMansioni(String principaliMansioni) {
    this.principaliMansioni = principaliMansioni;
  }

  /**
   **/
  

  @JsonProperty("assunzione_l68") 
 
  public Boolean isAssunzioneL68() {
    return assunzioneL68;
  }
  public void setAssunzioneL68(Boolean assunzioneL68) {
    this.assunzioneL68 = assunzioneL68;
  }

  /**
   **/
  

  @JsonProperty("lavoratore_in_mobilita") 
 
  public Boolean isLavoratoreInMobilita() {
    return lavoratoreInMobilita;
  }
  public void setLavoratoreInMobilita(Boolean lavoratoreInMobilita) {
    this.lavoratoreInMobilita = lavoratoreInMobilita;
  }

  /**
   **/
  

  @JsonProperty("lavoro_stagionale") 
 
  public Boolean isLavoroStagionale() {
    return lavoroStagionale;
  }
  public void setLavoroStagionale(Boolean lavoroStagionale) {
    this.lavoroStagionale = lavoroStagionale;
  }

  /**
   **/
  

  @JsonProperty("lavoro_in_agricoltura") 
 
  public Boolean isLavoroInAgricoltura() {
    return lavoroInAgricoltura;
  }
  public void setLavoroInAgricoltura(Boolean lavoroInAgricoltura) {
    this.lavoroInAgricoltura = lavoroInAgricoltura;
  }

  /**
   * indica che l&#39;esperienza e&#39; dichiarata dal cittadino, non proveniente da una comunicazione ufficiale, puo&#39; quindi essere modificata
   **/
  

  @JsonProperty("esperienza_dichiarata") 
 
  public Boolean isEsperienzaDichiarata() {
    return esperienzaDichiarata;
  }
  public void setEsperienzaDichiarata(Boolean esperienzaDichiarata) {
    this.esperienzaDichiarata = esperienzaDichiarata;
  }

  /**
   **/
  

  @JsonProperty("modalita_lavoro") 
 
  public Decodifica getModalitaLavoro() {
    return modalitaLavoro;
  }
  public void setModalitaLavoro(Decodifica modalitaLavoro) {
    this.modalitaLavoro = modalitaLavoro;
  }

  /**
   **/
  

  @JsonProperty("indirizzo_di_lavoro") 
 
  public Indirizzo getIndirizzoDiLavoro() {
    return indirizzoDiLavoro;
  }
  public void setIndirizzoDiLavoro(Indirizzo indirizzoDiLavoro) {
    this.indirizzoDiLavoro = indirizzoDiLavoro;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsperienzaLavoro esperienzaLavoro = (EsperienzaLavoro) o;
    return Objects.equals(dataInizioRapporto, esperienzaLavoro.dataInizioRapporto) &&
        Objects.equals(dataFineRapporto, esperienzaLavoro.dataFineRapporto) &&
        Objects.equals(tipoContratto, esperienzaLavoro.tipoContratto) &&
        Objects.equals(qualifica, esperienzaLavoro.qualifica) &&
        Objects.equals(categoriaInquadramento, esperienzaLavoro.categoriaInquadramento) &&
        Objects.equals(datoreLavoro, esperienzaLavoro.datoreLavoro) &&
        Objects.equals(aziendaUtilizzatrice, esperienzaLavoro.aziendaUtilizzatrice) &&
        Objects.equals(dataFinePeriodoFormativo, esperienzaLavoro.dataFinePeriodoFormativo) &&
        Objects.equals(principaliMansioni, esperienzaLavoro.principaliMansioni) &&
        Objects.equals(assunzioneL68, esperienzaLavoro.assunzioneL68) &&
        Objects.equals(lavoratoreInMobilita, esperienzaLavoro.lavoratoreInMobilita) &&
        Objects.equals(lavoroStagionale, esperienzaLavoro.lavoroStagionale) &&
        Objects.equals(lavoroInAgricoltura, esperienzaLavoro.lavoroInAgricoltura) &&
        Objects.equals(esperienzaDichiarata, esperienzaLavoro.esperienzaDichiarata) &&
        Objects.equals(modalitaLavoro, esperienzaLavoro.modalitaLavoro) &&
        Objects.equals(indirizzoDiLavoro, esperienzaLavoro.indirizzoDiLavoro);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataInizioRapporto, dataFineRapporto, tipoContratto, qualifica, categoriaInquadramento, datoreLavoro, aziendaUtilizzatrice, dataFinePeriodoFormativo, principaliMansioni, assunzioneL68, lavoratoreInMobilita, lavoroStagionale, lavoroInAgricoltura, esperienzaDichiarata, modalitaLavoro, indirizzoDiLavoro);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsperienzaLavoro {\n");
    
    sb.append("    dataInizioRapporto: ").append(toIndentedString(dataInizioRapporto)).append("\n");
    sb.append("    dataFineRapporto: ").append(toIndentedString(dataFineRapporto)).append("\n");
    sb.append("    tipoContratto: ").append(toIndentedString(tipoContratto)).append("\n");
    sb.append("    qualifica: ").append(toIndentedString(qualifica)).append("\n");
    sb.append("    categoriaInquadramento: ").append(toIndentedString(categoriaInquadramento)).append("\n");
    sb.append("    datoreLavoro: ").append(toIndentedString(datoreLavoro)).append("\n");
    sb.append("    aziendaUtilizzatrice: ").append(toIndentedString(aziendaUtilizzatrice)).append("\n");
    sb.append("    dataFinePeriodoFormativo: ").append(toIndentedString(dataFinePeriodoFormativo)).append("\n");
    sb.append("    principaliMansioni: ").append(toIndentedString(principaliMansioni)).append("\n");
    sb.append("    assunzioneL68: ").append(toIndentedString(assunzioneL68)).append("\n");
    sb.append("    lavoratoreInMobilita: ").append(toIndentedString(lavoratoreInMobilita)).append("\n");
    sb.append("    lavoroStagionale: ").append(toIndentedString(lavoroStagionale)).append("\n");
    sb.append("    lavoroInAgricoltura: ").append(toIndentedString(lavoroInAgricoltura)).append("\n");
    sb.append("    esperienzaDichiarata: ").append(toIndentedString(esperienzaDichiarata)).append("\n");
    sb.append("    modalitaLavoro: ").append(toIndentedString(modalitaLavoro)).append("\n");
    sb.append("    indirizzoDiLavoro: ").append(toIndentedString(indirizzoDiLavoro)).append("\n");
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

