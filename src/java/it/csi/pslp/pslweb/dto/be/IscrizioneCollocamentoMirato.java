/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class IscrizioneCollocamentoMirato   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idIscrizione = null;
  private Date dataIscrizione = null;
  private Date dataAnzianita = null;
  private String numeroIscrizione = null;
  private Decodifica provincia = null;
  private Decodifica cpi = null;
  private Long idCpiSilp = null;
  private String responsabileCpi = null;
  private String categoria = null;
  private String gradoInvalidita = null;
  private String percentualeInvalidita = null;
  private Date dataStato = null;
  private Decodifica stato = null;
  private Boolean statoFinale = null;
  private String note = null;

  /**
   **/
  

  @JsonProperty("idIscrizione") 
 
  public Long getIdIscrizione() {
    return idIscrizione;
  }
  public void setIdIscrizione(Long idIscrizione) {
    this.idIscrizione = idIscrizione;
  }

  /**
   **/
  

  @JsonProperty("dataIscrizione") 
 
  public Date getDataIscrizione() {
    return dataIscrizione;
  }
  public void setDataIscrizione(Date dataIscrizione) {
    this.dataIscrizione = dataIscrizione;
  }

  /**
   **/
  

  @JsonProperty("dataAnzianita") 
 
  public Date getDataAnzianita() {
    return dataAnzianita;
  }
  public void setDataAnzianita(Date dataAnzianita) {
    this.dataAnzianita = dataAnzianita;
  }

  /**
   **/
  

  @JsonProperty("numeroIscrizione") 
 
  public String getNumeroIscrizione() {
    return numeroIscrizione;
  }
  public void setNumeroIscrizione(String numeroIscrizione) {
    this.numeroIscrizione = numeroIscrizione;
  }

  /**
   **/
  

  @JsonProperty("provincia") 
 
  public Decodifica getProvincia() {
    return provincia;
  }
  public void setProvincia(Decodifica provincia) {
    this.provincia = provincia;
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
  

  @JsonProperty("idCpiSilp") 
 
  public Long getIdCpiSilp() {
    return idCpiSilp;
  }
  public void setIdCpiSilp(Long idCpiSilp) {
    this.idCpiSilp = idCpiSilp;
  }

  /**
   **/
  

  @JsonProperty("responsabileCpi") 
 
  public String getResponsabileCpi() {
    return responsabileCpi;
  }
  public void setResponsabileCpi(String responsabileCpi) {
    this.responsabileCpi = responsabileCpi;
  }

  /**
   **/
  

  @JsonProperty("categoria") 
 
  public String getCategoria() {
    return categoria;
  }
  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  /**
   **/
  

  @JsonProperty("gradoInvalidita") 
 
  public String getGradoInvalidita() {
    return gradoInvalidita;
  }
  public void setGradoInvalidita(String gradoInvalidita) {
    this.gradoInvalidita = gradoInvalidita;
  }

  /**
   **/
  

  @JsonProperty("percentualeInvalidita") 
 
  public String getPercentualeInvalidita() {
    return percentualeInvalidita;
  }
  public void setPercentualeInvalidita(String percentualeInvalidita) {
    this.percentualeInvalidita = percentualeInvalidita;
  }

  /**
   **/
  

  @JsonProperty("dataStato") 
 
  public Date getDataStato() {
    return dataStato;
  }
  public void setDataStato(Date dataStato) {
    this.dataStato = dataStato;
  }

  /**
   **/
  

  @JsonProperty("stato") 
 
  public Decodifica getStato() {
    return stato;
  }
  public void setStato(Decodifica stato) {
    this.stato = stato;
  }

  /**
   **/
  

  @JsonProperty("statoFinale") 
 
  public Boolean isStatoFinale() {
    return statoFinale;
  }
  public void setStatoFinale(Boolean statoFinale) {
    this.statoFinale = statoFinale;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IscrizioneCollocamentoMirato iscrizioneCollocamentoMirato = (IscrizioneCollocamentoMirato) o;
    return Objects.equals(idIscrizione, iscrizioneCollocamentoMirato.idIscrizione) &&
        Objects.equals(dataIscrizione, iscrizioneCollocamentoMirato.dataIscrizione) &&
        Objects.equals(dataAnzianita, iscrizioneCollocamentoMirato.dataAnzianita) &&
        Objects.equals(numeroIscrizione, iscrizioneCollocamentoMirato.numeroIscrizione) &&
        Objects.equals(provincia, iscrizioneCollocamentoMirato.provincia) &&
        Objects.equals(cpi, iscrizioneCollocamentoMirato.cpi) &&
        Objects.equals(idCpiSilp, iscrizioneCollocamentoMirato.idCpiSilp) &&
        Objects.equals(responsabileCpi, iscrizioneCollocamentoMirato.responsabileCpi) &&
        Objects.equals(categoria, iscrizioneCollocamentoMirato.categoria) &&
        Objects.equals(gradoInvalidita, iscrizioneCollocamentoMirato.gradoInvalidita) &&
        Objects.equals(percentualeInvalidita, iscrizioneCollocamentoMirato.percentualeInvalidita) &&
        Objects.equals(dataStato, iscrizioneCollocamentoMirato.dataStato) &&
        Objects.equals(stato, iscrizioneCollocamentoMirato.stato) &&
        Objects.equals(statoFinale, iscrizioneCollocamentoMirato.statoFinale) &&
        Objects.equals(note, iscrizioneCollocamentoMirato.note);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idIscrizione, dataIscrizione, dataAnzianita, numeroIscrizione, provincia, cpi, idCpiSilp, responsabileCpi, categoria, gradoInvalidita, percentualeInvalidita, dataStato, stato, statoFinale, note);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IscrizioneCollocamentoMirato {\n");
    
    sb.append("    idIscrizione: ").append(toIndentedString(idIscrizione)).append("\n");
    sb.append("    dataIscrizione: ").append(toIndentedString(dataIscrizione)).append("\n");
    sb.append("    dataAnzianita: ").append(toIndentedString(dataAnzianita)).append("\n");
    sb.append("    numeroIscrizione: ").append(toIndentedString(numeroIscrizione)).append("\n");
    sb.append("    provincia: ").append(toIndentedString(provincia)).append("\n");
    sb.append("    cpi: ").append(toIndentedString(cpi)).append("\n");
    sb.append("    idCpiSilp: ").append(toIndentedString(idCpiSilp)).append("\n");
    sb.append("    responsabileCpi: ").append(toIndentedString(responsabileCpi)).append("\n");
    sb.append("    categoria: ").append(toIndentedString(categoria)).append("\n");
    sb.append("    gradoInvalidita: ").append(toIndentedString(gradoInvalidita)).append("\n");
    sb.append("    percentualeInvalidita: ").append(toIndentedString(percentualeInvalidita)).append("\n");
    sb.append("    dataStato: ").append(toIndentedString(dataStato)).append("\n");
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
    sb.append("    statoFinale: ").append(toIndentedString(statoFinale)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
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

