/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Luogo;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class DatiAnagraficiFamiliareCollocamentoMirato   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceFiscale = null;
  private String nome = null;
  private String cognome = null;
  private String sesso = null;
  private Date dataDiNascita = null;
  private Luogo luogoDiNascita = null;

  /**
   * codice fiscale familiare
   **/
  

  @JsonProperty("codice_fiscale") 
 
  public String getCodiceFiscale() {
    return codiceFiscale;
  }
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
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
  

  @JsonProperty("cognome") 
 
  public String getCognome() {
    return cognome;
  }
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   **/
  

  @JsonProperty("sesso") 
 
  public String getSesso() {
    return sesso;
  }
  public void setSesso(String sesso) {
    this.sesso = sesso;
  }

  /**
   **/
  

  @JsonProperty("dataDiNascita") 
 
  public Date getDataDiNascita() {
    return dataDiNascita;
  }
  public void setDataDiNascita(Date dataDiNascita) {
    this.dataDiNascita = dataDiNascita;
  }

  /**
   **/
  

  @JsonProperty("luogoDiNascita") 
 
  public Luogo getLuogoDiNascita() {
    return luogoDiNascita;
  }
  public void setLuogoDiNascita(Luogo luogoDiNascita) {
    this.luogoDiNascita = luogoDiNascita;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatiAnagraficiFamiliareCollocamentoMirato datiAnagraficiFamiliareCollocamentoMirato = (DatiAnagraficiFamiliareCollocamentoMirato) o;
    return Objects.equals(codiceFiscale, datiAnagraficiFamiliareCollocamentoMirato.codiceFiscale) &&
        Objects.equals(nome, datiAnagraficiFamiliareCollocamentoMirato.nome) &&
        Objects.equals(cognome, datiAnagraficiFamiliareCollocamentoMirato.cognome) &&
        Objects.equals(sesso, datiAnagraficiFamiliareCollocamentoMirato.sesso) &&
        Objects.equals(dataDiNascita, datiAnagraficiFamiliareCollocamentoMirato.dataDiNascita) &&
        Objects.equals(luogoDiNascita, datiAnagraficiFamiliareCollocamentoMirato.luogoDiNascita);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceFiscale, nome, cognome, sesso, dataDiNascita, luogoDiNascita);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DatiAnagraficiFamiliareCollocamentoMirato {\n");
    
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
    sb.append("    sesso: ").append(toIndentedString(sesso)).append("\n");
    sb.append("    dataDiNascita: ").append(toIndentedString(dataDiNascita)).append("\n");
    sb.append("    luogoDiNascita: ").append(toIndentedString(luogoDiNascita)).append("\n");
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

