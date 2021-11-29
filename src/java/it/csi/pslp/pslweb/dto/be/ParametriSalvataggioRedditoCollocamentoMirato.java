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

public class ParametriSalvataggioRedditoCollocamentoMirato   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idRedditoPerAnnullo = null;
  private String motivoAnnullo = null;
  private Long anno = null;
  private Date dataInserimento = null;
  private String codiceProvincia = null;
  private String valore = null;
  private String note = null;

  /**
   **/
  

  @JsonProperty("idRedditoPerAnnullo") 
 
  public Long getIdRedditoPerAnnullo() {
    return idRedditoPerAnnullo;
  }
  public void setIdRedditoPerAnnullo(Long idRedditoPerAnnullo) {
    this.idRedditoPerAnnullo = idRedditoPerAnnullo;
  }

  /**
   **/
  

  @JsonProperty("motivoAnnullo") 
 
  public String getMotivoAnnullo() {
    return motivoAnnullo;
  }
  public void setMotivoAnnullo(String motivoAnnullo) {
    this.motivoAnnullo = motivoAnnullo;
  }

  /**
   **/
  

  @JsonProperty("anno") 
 
  public Long getAnno() {
    return anno;
  }
  public void setAnno(Long anno) {
    this.anno = anno;
  }

  /**
   **/
  

  @JsonProperty("dataInserimento") 
 
  public Date getDataInserimento() {
    return dataInserimento;
  }
  public void setDataInserimento(Date dataInserimento) {
    this.dataInserimento = dataInserimento;
  }

  /**
   **/
  

  @JsonProperty("codiceProvincia") 
 
  public String getCodiceProvincia() {
    return codiceProvincia;
  }
  public void setCodiceProvincia(String codiceProvincia) {
    this.codiceProvincia = codiceProvincia;
  }

  /**
   **/
  

  @JsonProperty("valore") 
 
  public String getValore() {
    return valore;
  }
  public void setValore(String valore) {
    this.valore = valore;
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
    ParametriSalvataggioRedditoCollocamentoMirato parametriSalvataggioRedditoCollocamentoMirato = (ParametriSalvataggioRedditoCollocamentoMirato) o;
    return Objects.equals(idRedditoPerAnnullo, parametriSalvataggioRedditoCollocamentoMirato.idRedditoPerAnnullo) &&
        Objects.equals(motivoAnnullo, parametriSalvataggioRedditoCollocamentoMirato.motivoAnnullo) &&
        Objects.equals(anno, parametriSalvataggioRedditoCollocamentoMirato.anno) &&
        Objects.equals(dataInserimento, parametriSalvataggioRedditoCollocamentoMirato.dataInserimento) &&
        Objects.equals(codiceProvincia, parametriSalvataggioRedditoCollocamentoMirato.codiceProvincia) &&
        Objects.equals(valore, parametriSalvataggioRedditoCollocamentoMirato.valore) &&
        Objects.equals(note, parametriSalvataggioRedditoCollocamentoMirato.note);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idRedditoPerAnnullo, motivoAnnullo, anno, dataInserimento, codiceProvincia, valore, note);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParametriSalvataggioRedditoCollocamentoMirato {\n");
    
    sb.append("    idRedditoPerAnnullo: ").append(toIndentedString(idRedditoPerAnnullo)).append("\n");
    sb.append("    motivoAnnullo: ").append(toIndentedString(motivoAnnullo)).append("\n");
    sb.append("    anno: ").append(toIndentedString(anno)).append("\n");
    sb.append("    dataInserimento: ").append(toIndentedString(dataInserimento)).append("\n");
    sb.append("    codiceProvincia: ").append(toIndentedString(codiceProvincia)).append("\n");
    sb.append("    valore: ").append(toIndentedString(valore)).append("\n");
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

