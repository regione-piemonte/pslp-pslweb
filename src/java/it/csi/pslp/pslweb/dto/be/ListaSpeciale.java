/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Provincia;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ListaSpeciale   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long tipoLista = null;
  private String descrizioneLista = null;
  private Date dataIscrizioneLista = null;
  private Date dataTermineIscrizione = null;
  private Date dataMassimoDifferimento = null;
  private Provincia provincia = null;

  /**
   **/
  

  @JsonProperty("tipo_lista") 
 
  public Long getTipoLista() {
    return tipoLista;
  }
  public void setTipoLista(Long tipoLista) {
    this.tipoLista = tipoLista;
  }

  /**
   **/
  

  @JsonProperty("descrizione_lista") 
 
  public String getDescrizioneLista() {
    return descrizioneLista;
  }
  public void setDescrizioneLista(String descrizioneLista) {
    this.descrizioneLista = descrizioneLista;
  }

  /**
   **/
  

  @JsonProperty("data_iscrizione_lista") 
 
  public Date getDataIscrizioneLista() {
    return dataIscrizioneLista;
  }
  public void setDataIscrizioneLista(Date dataIscrizioneLista) {
    this.dataIscrizioneLista = dataIscrizioneLista;
  }

  /**
   **/
  

  @JsonProperty("data_termine_iscrizione") 
 
  public Date getDataTermineIscrizione() {
    return dataTermineIscrizione;
  }
  public void setDataTermineIscrizione(Date dataTermineIscrizione) {
    this.dataTermineIscrizione = dataTermineIscrizione;
  }

  /**
   **/
  

  @JsonProperty("data_massimo_differimento") 
 
  public Date getDataMassimoDifferimento() {
    return dataMassimoDifferimento;
  }
  public void setDataMassimoDifferimento(Date dataMassimoDifferimento) {
    this.dataMassimoDifferimento = dataMassimoDifferimento;
  }

  /**
   **/
  

  @JsonProperty("provincia") 
 
  public Provincia getProvincia() {
    return provincia;
  }
  public void setProvincia(Provincia provincia) {
    this.provincia = provincia;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListaSpeciale listaSpeciale = (ListaSpeciale) o;
    return Objects.equals(tipoLista, listaSpeciale.tipoLista) &&
        Objects.equals(descrizioneLista, listaSpeciale.descrizioneLista) &&
        Objects.equals(dataIscrizioneLista, listaSpeciale.dataIscrizioneLista) &&
        Objects.equals(dataTermineIscrizione, listaSpeciale.dataTermineIscrizione) &&
        Objects.equals(dataMassimoDifferimento, listaSpeciale.dataMassimoDifferimento) &&
        Objects.equals(provincia, listaSpeciale.provincia);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tipoLista, descrizioneLista, dataIscrizioneLista, dataTermineIscrizione, dataMassimoDifferimento, provincia);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListaSpeciale {\n");
    
    sb.append("    tipoLista: ").append(toIndentedString(tipoLista)).append("\n");
    sb.append("    descrizioneLista: ").append(toIndentedString(descrizioneLista)).append("\n");
    sb.append("    dataIscrizioneLista: ").append(toIndentedString(dataIscrizioneLista)).append("\n");
    sb.append("    dataTermineIscrizione: ").append(toIndentedString(dataTermineIscrizione)).append("\n");
    sb.append("    dataMassimoDifferimento: ").append(toIndentedString(dataMassimoDifferimento)).append("\n");
    sb.append("    provincia: ").append(toIndentedString(provincia)).append("\n");
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

