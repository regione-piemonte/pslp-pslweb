/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.FamiliareACaricoCollocamentoMirato;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class DichiarazioneFamiliariCollocamentoMirato   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idDichiarazione = null;
  private Long anno = null;
  private Date dataDichiarazione = null;
  private Long numeroFamiliariACarico = null;
  private List<FamiliareACaricoCollocamentoMirato> familiariACarico = new ArrayList<FamiliareACaricoCollocamentoMirato>();
  private String fonte = null;
  private Decodifica cpi = null;
  private String note = null;

  /**
   **/
  

  @JsonProperty("idDichiarazione") 
 
  public Long getIdDichiarazione() {
    return idDichiarazione;
  }
  public void setIdDichiarazione(Long idDichiarazione) {
    this.idDichiarazione = idDichiarazione;
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
  

  @JsonProperty("dataDichiarazione") 
 
  public Date getDataDichiarazione() {
    return dataDichiarazione;
  }
  public void setDataDichiarazione(Date dataDichiarazione) {
    this.dataDichiarazione = dataDichiarazione;
  }

  /**
   **/
  

  @JsonProperty("numeroFamiliariACarico") 
 
  public Long getNumeroFamiliariACarico() {
    return numeroFamiliariACarico;
  }
  public void setNumeroFamiliariACarico(Long numeroFamiliariACarico) {
    this.numeroFamiliariACarico = numeroFamiliariACarico;
  }

  /**
   **/
  

  @JsonProperty("familiariACarico") 
 
  public List<FamiliareACaricoCollocamentoMirato> getFamiliariACarico() {
    return familiariACarico;
  }
  public void setFamiliariACarico(List<FamiliareACaricoCollocamentoMirato> familiariACarico) {
    this.familiariACarico = familiariACarico;
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
    DichiarazioneFamiliariCollocamentoMirato dichiarazioneFamiliariCollocamentoMirato = (DichiarazioneFamiliariCollocamentoMirato) o;
    return Objects.equals(idDichiarazione, dichiarazioneFamiliariCollocamentoMirato.idDichiarazione) &&
        Objects.equals(anno, dichiarazioneFamiliariCollocamentoMirato.anno) &&
        Objects.equals(dataDichiarazione, dichiarazioneFamiliariCollocamentoMirato.dataDichiarazione) &&
        Objects.equals(numeroFamiliariACarico, dichiarazioneFamiliariCollocamentoMirato.numeroFamiliariACarico) &&
        Objects.equals(familiariACarico, dichiarazioneFamiliariCollocamentoMirato.familiariACarico) &&
        Objects.equals(fonte, dichiarazioneFamiliariCollocamentoMirato.fonte) &&
        Objects.equals(cpi, dichiarazioneFamiliariCollocamentoMirato.cpi) &&
        Objects.equals(note, dichiarazioneFamiliariCollocamentoMirato.note);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idDichiarazione, anno, dataDichiarazione, numeroFamiliariACarico, familiariACarico, fonte, cpi, note);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DichiarazioneFamiliariCollocamentoMirato {\n");
    
    sb.append("    idDichiarazione: ").append(toIndentedString(idDichiarazione)).append("\n");
    sb.append("    anno: ").append(toIndentedString(anno)).append("\n");
    sb.append("    dataDichiarazione: ").append(toIndentedString(dataDichiarazione)).append("\n");
    sb.append("    numeroFamiliariACarico: ").append(toIndentedString(numeroFamiliariACarico)).append("\n");
    sb.append("    familiariACarico: ").append(toIndentedString(familiariACarico)).append("\n");
    sb.append("    fonte: ").append(toIndentedString(fonte)).append("\n");
    sb.append("    cpi: ").append(toIndentedString(cpi)).append("\n");
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

