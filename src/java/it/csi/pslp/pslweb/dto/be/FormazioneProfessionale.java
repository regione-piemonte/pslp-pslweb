/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class FormazioneProfessionale   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String titoloCorso = null;
  private String enteErogatore = null;
  private Decodifica regioneSede = null;
  private Integer durata = null;
  private String tipoDurata = null;
  private Decodifica certificazioniAttestazioni = null;
  private Boolean stage = null;
  private String nomeAziendaStage = null;
  private String codiceFp = null;

  /**
   **/
  

  @JsonProperty("titolo_corso") 
 
  public String getTitoloCorso() {
    return titoloCorso;
  }
  public void setTitoloCorso(String titoloCorso) {
    this.titoloCorso = titoloCorso;
  }

  /**
   **/
  

  @JsonProperty("ente_erogatore") 
 
  public String getEnteErogatore() {
    return enteErogatore;
  }
  public void setEnteErogatore(String enteErogatore) {
    this.enteErogatore = enteErogatore;
  }

  /**
   * il codice ministeriale della regione
   **/
  

  @JsonProperty("regione_sede") 
 
  public Decodifica getRegioneSede() {
    return regioneSede;
  }
  public void setRegioneSede(Decodifica regioneSede) {
    this.regioneSede = regioneSede;
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
   * Tabella ministeriale Attestazioni
   **/
  

  @JsonProperty("certificazioni_attestazioni") 
 
  public Decodifica getCertificazioniAttestazioni() {
    return certificazioniAttestazioni;
  }
  public void setCertificazioniAttestazioni(Decodifica certificazioniAttestazioni) {
    this.certificazioniAttestazioni = certificazioniAttestazioni;
  }

  /**
   **/
  

  @JsonProperty("stage") 
 
  public Boolean isStage() {
    return stage;
  }
  public void setStage(Boolean stage) {
    this.stage = stage;
  }

  /**
   **/
  

  @JsonProperty("nome_azienda_stage") 
 
  public String getNomeAziendaStage() {
    return nomeAziendaStage;
  }
  public void setNomeAziendaStage(String nomeAziendaStage) {
    this.nomeAziendaStage = nomeAziendaStage;
  }

  /**
   * codice identificativo di un corso se ottenuto dai servizi FP di formazione professionale
   **/
  

  @JsonProperty("codice_fp") 
 
  public String getCodiceFp() {
    return codiceFp;
  }
  public void setCodiceFp(String codiceFp) {
    this.codiceFp = codiceFp;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FormazioneProfessionale formazioneProfessionale = (FormazioneProfessionale) o;
    return Objects.equals(titoloCorso, formazioneProfessionale.titoloCorso) &&
        Objects.equals(enteErogatore, formazioneProfessionale.enteErogatore) &&
        Objects.equals(regioneSede, formazioneProfessionale.regioneSede) &&
        Objects.equals(durata, formazioneProfessionale.durata) &&
        Objects.equals(tipoDurata, formazioneProfessionale.tipoDurata) &&
        Objects.equals(certificazioniAttestazioni, formazioneProfessionale.certificazioniAttestazioni) &&
        Objects.equals(stage, formazioneProfessionale.stage) &&
        Objects.equals(nomeAziendaStage, formazioneProfessionale.nomeAziendaStage) &&
        Objects.equals(codiceFp, formazioneProfessionale.codiceFp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(titoloCorso, enteErogatore, regioneSede, durata, tipoDurata, certificazioniAttestazioni, stage, nomeAziendaStage, codiceFp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FormazioneProfessionale {\n");
    
    sb.append("    titoloCorso: ").append(toIndentedString(titoloCorso)).append("\n");
    sb.append("    enteErogatore: ").append(toIndentedString(enteErogatore)).append("\n");
    sb.append("    regioneSede: ").append(toIndentedString(regioneSede)).append("\n");
    sb.append("    durata: ").append(toIndentedString(durata)).append("\n");
    sb.append("    tipoDurata: ").append(toIndentedString(tipoDurata)).append("\n");
    sb.append("    certificazioniAttestazioni: ").append(toIndentedString(certificazioniAttestazioni)).append("\n");
    sb.append("    stage: ").append(toIndentedString(stage)).append("\n");
    sb.append("    nomeAziendaStage: ").append(toIndentedString(nomeAziendaStage)).append("\n");
    sb.append("    codiceFp: ").append(toIndentedString(codiceFp)).append("\n");
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

