/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.DettaglioDichiarazioneFamiliariACarico;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class DettaglioCompletoDichiarazioneFamiliariACarico   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idDichiarazione = null;
  private Long idSilLavAnagrafica = null;
  private Long numeroFamiliari = null;
  private Long annoValidita = null;
  private Date dataDichiarazione = null;
  private Boolean flagUltimaDichiarazioneInserita = null;
  private Boolean flagDuplicazioneDichiarazione = null;
  private String fonte = null;
  private Decodifica cpi = null;
  private Date dataNascitaDichiarante = null;
  private String note = null;
  private List<DettaglioDichiarazioneFamiliariACarico> dettaglioDichiarazioneFamiliariACarico = new ArrayList<DettaglioDichiarazioneFamiliariACarico>();

  /**
   **/
  

  @JsonProperty("id_dichiarazione") 
 
  public Long getIdDichiarazione() {
    return idDichiarazione;
  }
  public void setIdDichiarazione(Long idDichiarazione) {
    this.idDichiarazione = idDichiarazione;
  }

  /**
   * id lavoratore su SILP
   **/
  

  @JsonProperty("id_sil_lav_anagrafica") 
 
  public Long getIdSilLavAnagrafica() {
    return idSilLavAnagrafica;
  }
  public void setIdSilLavAnagrafica(Long idSilLavAnagrafica) {
    this.idSilLavAnagrafica = idSilLavAnagrafica;
  }

  /**
   **/
  

  @JsonProperty("numero_familiari") 
 
  public Long getNumeroFamiliari() {
    return numeroFamiliari;
  }
  public void setNumeroFamiliari(Long numeroFamiliari) {
    this.numeroFamiliari = numeroFamiliari;
  }

  /**
   **/
  

  @JsonProperty("anno_validita") 
 
  public Long getAnnoValidita() {
    return annoValidita;
  }
  public void setAnnoValidita(Long annoValidita) {
    this.annoValidita = annoValidita;
  }

  /**
   **/
  

  @JsonProperty("data_dichiarazione") 
 
  public Date getDataDichiarazione() {
    return dataDichiarazione;
  }
  public void setDataDichiarazione(Date dataDichiarazione) {
    this.dataDichiarazione = dataDichiarazione;
  }

  /**
   * true se e&#39; l&#39;ultima dichiarazione inserita
   **/
  

  @JsonProperty("flag_ultima_dichiarazione_inserita") 
 
  public Boolean isFlagUltimaDichiarazioneInserita() {
    return flagUltimaDichiarazioneInserita;
  }
  public void setFlagUltimaDichiarazioneInserita(Boolean flagUltimaDichiarazioneInserita) {
    this.flagUltimaDichiarazioneInserita = flagUltimaDichiarazioneInserita;
  }

  /**
   * true se la dichiarazione e&#39; stata duplicata
   **/
  

  @JsonProperty("flag_duplicazione_dichiarazione") 
 
  public Boolean isFlagDuplicazioneDichiarazione() {
    return flagDuplicazioneDichiarazione;
  }
  public void setFlagDuplicazioneDichiarazione(Boolean flagDuplicazioneDichiarazione) {
    this.flagDuplicazioneDichiarazione = flagDuplicazioneDichiarazione;
  }

  /**
   **/
  

  @JsonProperty("fonte") 
 
  public String getFonte() {
    return fonte;
  }
  public void setFonte(String fonte) {
    this.fonte = fonte;
  }

  /**
   **/
  

  @JsonProperty("cpi") 
 
  public Decodifica getCpi() {
    return cpi;
  }
  public void setCpi(Decodifica cpi) {
    this.cpi = cpi;
  }

  /**
   **/
  

  @JsonProperty("data_nascita_dichiarante") 
 
  public Date getDataNascitaDichiarante() {
    return dataNascitaDichiarante;
  }
  public void setDataNascitaDichiarante(Date dataNascitaDichiarante) {
    this.dataNascitaDichiarante = dataNascitaDichiarante;
  }

  /**
   **/
  

  @JsonProperty("note") 
 
  public String getNote() {
    return note;
  }
  public void setNote(String note) {
    this.note = note;
  }

  /**
   **/
  

  @JsonProperty("dettaglio_dichiarazione_familiari_a_carico") 
 
  public List<DettaglioDichiarazioneFamiliariACarico> getDettaglioDichiarazioneFamiliariACarico() {
    return dettaglioDichiarazioneFamiliariACarico;
  }
  public void setDettaglioDichiarazioneFamiliariACarico(List<DettaglioDichiarazioneFamiliariACarico> dettaglioDichiarazioneFamiliariACarico) {
    this.dettaglioDichiarazioneFamiliariACarico = dettaglioDichiarazioneFamiliariACarico;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DettaglioCompletoDichiarazioneFamiliariACarico dettaglioCompletoDichiarazioneFamiliariACarico = (DettaglioCompletoDichiarazioneFamiliariACarico) o;
    return Objects.equals(idDichiarazione, dettaglioCompletoDichiarazioneFamiliariACarico.idDichiarazione) &&
        Objects.equals(idSilLavAnagrafica, dettaglioCompletoDichiarazioneFamiliariACarico.idSilLavAnagrafica) &&
        Objects.equals(numeroFamiliari, dettaglioCompletoDichiarazioneFamiliariACarico.numeroFamiliari) &&
        Objects.equals(annoValidita, dettaglioCompletoDichiarazioneFamiliariACarico.annoValidita) &&
        Objects.equals(dataDichiarazione, dettaglioCompletoDichiarazioneFamiliariACarico.dataDichiarazione) &&
        Objects.equals(flagUltimaDichiarazioneInserita, dettaglioCompletoDichiarazioneFamiliariACarico.flagUltimaDichiarazioneInserita) &&
        Objects.equals(flagDuplicazioneDichiarazione, dettaglioCompletoDichiarazioneFamiliariACarico.flagDuplicazioneDichiarazione) &&
        Objects.equals(fonte, dettaglioCompletoDichiarazioneFamiliariACarico.fonte) &&
        Objects.equals(cpi, dettaglioCompletoDichiarazioneFamiliariACarico.cpi) &&
        Objects.equals(dataNascitaDichiarante, dettaglioCompletoDichiarazioneFamiliariACarico.dataNascitaDichiarante) &&
        Objects.equals(note, dettaglioCompletoDichiarazioneFamiliariACarico.note) &&
        Objects.equals(dettaglioDichiarazioneFamiliariACarico, dettaglioCompletoDichiarazioneFamiliariACarico.dettaglioDichiarazioneFamiliariACarico);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idDichiarazione, idSilLavAnagrafica, numeroFamiliari, annoValidita, dataDichiarazione, flagUltimaDichiarazioneInserita, flagDuplicazioneDichiarazione, fonte, cpi, dataNascitaDichiarante, note, dettaglioDichiarazioneFamiliariACarico);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DettaglioCompletoDichiarazioneFamiliariACarico {\n");
    
    sb.append("    idDichiarazione: ").append(toIndentedString(idDichiarazione)).append("\n");
    sb.append("    idSilLavAnagrafica: ").append(toIndentedString(idSilLavAnagrafica)).append("\n");
    sb.append("    numeroFamiliari: ").append(toIndentedString(numeroFamiliari)).append("\n");
    sb.append("    annoValidita: ").append(toIndentedString(annoValidita)).append("\n");
    sb.append("    dataDichiarazione: ").append(toIndentedString(dataDichiarazione)).append("\n");
    sb.append("    flagUltimaDichiarazioneInserita: ").append(toIndentedString(flagUltimaDichiarazioneInserita)).append("\n");
    sb.append("    flagDuplicazioneDichiarazione: ").append(toIndentedString(flagDuplicazioneDichiarazione)).append("\n");
    sb.append("    fonte: ").append(toIndentedString(fonte)).append("\n");
    sb.append("    cpi: ").append(toIndentedString(cpi)).append("\n");
    sb.append("    dataNascitaDichiarante: ").append(toIndentedString(dataNascitaDichiarante)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    dettaglioDichiarazioneFamiliariACarico: ").append(toIndentedString(dettaglioDichiarazioneFamiliariACarico)).append("\n");
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

