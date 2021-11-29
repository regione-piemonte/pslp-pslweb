/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class Ufficio   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long id = null;
  private String nome = null;
  private String indirizzo = null;
  private String tipo = null;
  private Float latitudine = null;
  private Float longitudine = null;
  private String codiceProvincia = null;
  private String provincia = null;
  private List<String> orario = new ArrayList<String>();

  /**
   * id ufficio
   **/
  

  @JsonProperty("id") 
 
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  /**
   **/
  

  @JsonProperty("nome") 
 
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   **/
  

  @JsonProperty("indirizzo") 
 
  public String getIndirizzo() {
    return indirizzo;
  }
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  /**
   **/
  

  @JsonProperty("tipo") 
 
  public String getTipo() {
    return tipo;
  }
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  /**
   **/
  

  @JsonProperty("latitudine") 
 
  public Float getLatitudine() {
    return latitudine;
  }
  public void setLatitudine(Float latitudine) {
    this.latitudine = latitudine;
  }

  /**
   **/
  

  @JsonProperty("longitudine") 
 
  public Float getLongitudine() {
    return longitudine;
  }
  public void setLongitudine(Float longitudine) {
    this.longitudine = longitudine;
  }

  /**
   **/
  

  @JsonProperty("codice provincia") 
 
  public String getCodiceProvincia() {
    return codiceProvincia;
  }
  public void setCodiceProvincia(String codiceProvincia) {
    this.codiceProvincia = codiceProvincia;
  }

  /**
   **/
  

  @JsonProperty("provincia") 
 
  public String getProvincia() {
    return provincia;
  }
  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  /**
   **/
  

  @JsonProperty("orario") 
 
  public List<String> getOrario() {
    return orario;
  }
  public void setOrario(List<String> orario) {
    this.orario = orario;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ufficio ufficio = (Ufficio) o;
    return Objects.equals(id, ufficio.id) &&
        Objects.equals(nome, ufficio.nome) &&
        Objects.equals(indirizzo, ufficio.indirizzo) &&
        Objects.equals(tipo, ufficio.tipo) &&
        Objects.equals(latitudine, ufficio.latitudine) &&
        Objects.equals(longitudine, ufficio.longitudine) &&
        Objects.equals(codiceProvincia, ufficio.codiceProvincia) &&
        Objects.equals(provincia, ufficio.provincia) &&
        Objects.equals(orario, ufficio.orario);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, indirizzo, tipo, latitudine, longitudine, codiceProvincia, provincia, orario);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ufficio {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    indirizzo: ").append(toIndentedString(indirizzo)).append("\n");
    sb.append("    tipo: ").append(toIndentedString(tipo)).append("\n");
    sb.append("    latitudine: ").append(toIndentedString(latitudine)).append("\n");
    sb.append("    longitudine: ").append(toIndentedString(longitudine)).append("\n");
    sb.append("    codiceProvincia: ").append(toIndentedString(codiceProvincia)).append("\n");
    sb.append("    provincia: ").append(toIndentedString(provincia)).append("\n");
    sb.append("    orario: ").append(toIndentedString(orario)).append("\n");
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

