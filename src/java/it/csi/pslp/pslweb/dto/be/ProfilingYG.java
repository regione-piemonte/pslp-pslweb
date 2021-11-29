/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.CondizioneOccupazionale;
import it.csi.pslp.pslweb.dto.be.MotivoPresenzaInItalia;
import it.csi.pslp.pslweb.dto.be.TitoloStudio;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ProfilingYG   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long indice = null;
  private String descrizioneIndice = null;
  private Date dataProfiling = null;
  private Long eta = null;
  private String genere = null;
  private TitoloStudio titoloStudio = null;
  private CondizioneOccupazionale condizioneOccupazionale = null;
  private MotivoPresenzaInItalia motivoPresenzaInItalia = null;
  private String codiceMinisterialeProvincia = null;

  /**
   **/
  

  @JsonProperty("indice") 
 
  public Long getIndice() {
    return indice;
  }
  public void setIndice(Long indice) {
    this.indice = indice;
  }

  /**
   **/
  

  @JsonProperty("descrizione_indice") 
 
  public String getDescrizioneIndice() {
    return descrizioneIndice;
  }
  public void setDescrizioneIndice(String descrizioneIndice) {
    this.descrizioneIndice = descrizioneIndice;
  }

  /**
   **/
  

  @JsonProperty("data_profiling") 
 
  public Date getDataProfiling() {
    return dataProfiling;
  }
  public void setDataProfiling(Date dataProfiling) {
    this.dataProfiling = dataProfiling;
  }

  /**
   **/
  

  @JsonProperty("eta") 
 
  public Long getEta() {
    return eta;
  }
  public void setEta(Long eta) {
    this.eta = eta;
  }

  /**
   **/
  

  @JsonProperty("genere") 
 
  public String getGenere() {
    return genere;
  }
  public void setGenere(String genere) {
    this.genere = genere;
  }

  /**
   **/
  

  @JsonProperty("titolo_studio") 
 
  public TitoloStudio getTitoloStudio() {
    return titoloStudio;
  }
  public void setTitoloStudio(TitoloStudio titoloStudio) {
    this.titoloStudio = titoloStudio;
  }

  /**
   **/
  

  @JsonProperty("condizione_occupazionale") 
 
  public CondizioneOccupazionale getCondizioneOccupazionale() {
    return condizioneOccupazionale;
  }
  public void setCondizioneOccupazionale(CondizioneOccupazionale condizioneOccupazionale) {
    this.condizioneOccupazionale = condizioneOccupazionale;
  }

  /**
   **/
  

  @JsonProperty("motivo_presenza_in_italia") 
 
  public MotivoPresenzaInItalia getMotivoPresenzaInItalia() {
    return motivoPresenzaInItalia;
  }
  public void setMotivoPresenzaInItalia(MotivoPresenzaInItalia motivoPresenzaInItalia) {
    this.motivoPresenzaInItalia = motivoPresenzaInItalia;
  }

  /**
   **/
  

  @JsonProperty("codice_ministeriale_provincia") 
 
  public String getCodiceMinisterialeProvincia() {
    return codiceMinisterialeProvincia;
  }
  public void setCodiceMinisterialeProvincia(String codiceMinisterialeProvincia) {
    this.codiceMinisterialeProvincia = codiceMinisterialeProvincia;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProfilingYG profilingYG = (ProfilingYG) o;
    return Objects.equals(indice, profilingYG.indice) &&
        Objects.equals(descrizioneIndice, profilingYG.descrizioneIndice) &&
        Objects.equals(dataProfiling, profilingYG.dataProfiling) &&
        Objects.equals(eta, profilingYG.eta) &&
        Objects.equals(genere, profilingYG.genere) &&
        Objects.equals(titoloStudio, profilingYG.titoloStudio) &&
        Objects.equals(condizioneOccupazionale, profilingYG.condizioneOccupazionale) &&
        Objects.equals(motivoPresenzaInItalia, profilingYG.motivoPresenzaInItalia) &&
        Objects.equals(codiceMinisterialeProvincia, profilingYG.codiceMinisterialeProvincia);
  }

  @Override
  public int hashCode() {
    return Objects.hash(indice, descrizioneIndice, dataProfiling, eta, genere, titoloStudio, condizioneOccupazionale, motivoPresenzaInItalia, codiceMinisterialeProvincia);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProfilingYG {\n");
    
    sb.append("    indice: ").append(toIndentedString(indice)).append("\n");
    sb.append("    descrizioneIndice: ").append(toIndentedString(descrizioneIndice)).append("\n");
    sb.append("    dataProfiling: ").append(toIndentedString(dataProfiling)).append("\n");
    sb.append("    eta: ").append(toIndentedString(eta)).append("\n");
    sb.append("    genere: ").append(toIndentedString(genere)).append("\n");
    sb.append("    titoloStudio: ").append(toIndentedString(titoloStudio)).append("\n");
    sb.append("    condizioneOccupazionale: ").append(toIndentedString(condizioneOccupazionale)).append("\n");
    sb.append("    motivoPresenzaInItalia: ").append(toIndentedString(motivoPresenzaInItalia)).append("\n");
    sb.append("    codiceMinisterialeProvincia: ").append(toIndentedString(codiceMinisterialeProvincia)).append("\n");
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

