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

public class RedditoCollocamentoMirato   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idReddito = null;
  private Long anno = null;
  private Date dataInserimento = null;
  private String fonte = null;
  private Decodifica cpi = null;
  private Decodifica provincia = null;
  private String valore = null;
  private String note = null;

  /**
   **/
  

  @JsonProperty("idReddito") 
 
  public Long getIdReddito() {
    return idReddito;
  }
  public void setIdReddito(Long idReddito) {
    this.idReddito = idReddito;
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
  

  @JsonProperty("provincia") 
 
  public Decodifica getProvincia() {
    return provincia;
  }
  public void setProvincia(Decodifica provincia) {
    this.provincia = provincia;
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
    RedditoCollocamentoMirato redditoCollocamentoMirato = (RedditoCollocamentoMirato) o;
    return Objects.equals(idReddito, redditoCollocamentoMirato.idReddito) &&
        Objects.equals(anno, redditoCollocamentoMirato.anno) &&
        Objects.equals(dataInserimento, redditoCollocamentoMirato.dataInserimento) &&
        Objects.equals(fonte, redditoCollocamentoMirato.fonte) &&
        Objects.equals(cpi, redditoCollocamentoMirato.cpi) &&
        Objects.equals(provincia, redditoCollocamentoMirato.provincia) &&
        Objects.equals(valore, redditoCollocamentoMirato.valore) &&
        Objects.equals(note, redditoCollocamentoMirato.note);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idReddito, anno, dataInserimento, fonte, cpi, provincia, valore, note);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RedditoCollocamentoMirato {\n");
    
    sb.append("    idReddito: ").append(toIndentedString(idReddito)).append("\n");
    sb.append("    anno: ").append(toIndentedString(anno)).append("\n");
    sb.append("    dataInserimento: ").append(toIndentedString(dataInserimento)).append("\n");
    sb.append("    fonte: ").append(toIndentedString(fonte)).append("\n");
    sb.append("    cpi: ").append(toIndentedString(cpi)).append("\n");
    sb.append("    provincia: ").append(toIndentedString(provincia)).append("\n");
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

