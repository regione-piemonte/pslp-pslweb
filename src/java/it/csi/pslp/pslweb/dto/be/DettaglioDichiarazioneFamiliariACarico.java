/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.Luogo;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class DettaglioDichiarazioneFamiliariACarico   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Decodifica motivoCarico = null;
  private String codiceFiscaleFamiliare = null;
  private String cognomeFamiliare = null;
  private String nomeFamiliare = null;
  private Date dataNascitaFamiliare = null;
  private String sesso = null;
  private Luogo luogoDiNascita = null;
  private String flgValiditaCf = null;

  /**
   **/
  

  @JsonProperty("motivo_carico") 
 
  public Decodifica getMotivoCarico() {
    return motivoCarico;
  }
  public void setMotivoCarico(Decodifica motivoCarico) {
    this.motivoCarico = motivoCarico;
  }

  /**
   * codice fiscale familiare
   **/
  

  @JsonProperty("codice_fiscale_familiare") 
 
  public String getCodiceFiscaleFamiliare() {
    return codiceFiscaleFamiliare;
  }
  public void setCodiceFiscaleFamiliare(String codiceFiscaleFamiliare) {
    this.codiceFiscaleFamiliare = codiceFiscaleFamiliare;
  }

  /**
   **/
  

  @JsonProperty("cognome_familiare") 
 
  public String getCognomeFamiliare() {
    return cognomeFamiliare;
  }
  public void setCognomeFamiliare(String cognomeFamiliare) {
    this.cognomeFamiliare = cognomeFamiliare;
  }

  /**
   **/
  

  @JsonProperty("nome_familiare") 
 
  public String getNomeFamiliare() {
    return nomeFamiliare;
  }
  public void setNomeFamiliare(String nomeFamiliare) {
    this.nomeFamiliare = nomeFamiliare;
  }

  /**
   **/
  

  @JsonProperty("data_nascita_familiare") 
 
  public Date getDataNascitaFamiliare() {
    return dataNascitaFamiliare;
  }
  public void setDataNascitaFamiliare(Date dataNascitaFamiliare) {
    this.dataNascitaFamiliare = dataNascitaFamiliare;
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
  

  @JsonProperty("luogo_di_nascita") 
 
  public Luogo getLuogoDiNascita() {
    return luogoDiNascita;
  }
  public void setLuogoDiNascita(Luogo luogoDiNascita) {
    this.luogoDiNascita = luogoDiNascita;
  }

  /**
   **/
  

  @JsonProperty("flg_validita_cf") 
 
  public String getFlgValiditaCf() {
    return flgValiditaCf;
  }
  public void setFlgValiditaCf(String flgValiditaCf) {
    this.flgValiditaCf = flgValiditaCf;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DettaglioDichiarazioneFamiliariACarico dettaglioDichiarazioneFamiliariACarico = (DettaglioDichiarazioneFamiliariACarico) o;
    return Objects.equals(motivoCarico, dettaglioDichiarazioneFamiliariACarico.motivoCarico) &&
        Objects.equals(codiceFiscaleFamiliare, dettaglioDichiarazioneFamiliariACarico.codiceFiscaleFamiliare) &&
        Objects.equals(cognomeFamiliare, dettaglioDichiarazioneFamiliariACarico.cognomeFamiliare) &&
        Objects.equals(nomeFamiliare, dettaglioDichiarazioneFamiliariACarico.nomeFamiliare) &&
        Objects.equals(dataNascitaFamiliare, dettaglioDichiarazioneFamiliariACarico.dataNascitaFamiliare) &&
        Objects.equals(sesso, dettaglioDichiarazioneFamiliariACarico.sesso) &&
        Objects.equals(luogoDiNascita, dettaglioDichiarazioneFamiliariACarico.luogoDiNascita) &&
        Objects.equals(flgValiditaCf, dettaglioDichiarazioneFamiliariACarico.flgValiditaCf);
  }

  @Override
  public int hashCode() {
    return Objects.hash(motivoCarico, codiceFiscaleFamiliare, cognomeFamiliare, nomeFamiliare, dataNascitaFamiliare, sesso, luogoDiNascita, flgValiditaCf);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DettaglioDichiarazioneFamiliariACarico {\n");
    
    sb.append("    motivoCarico: ").append(toIndentedString(motivoCarico)).append("\n");
    sb.append("    codiceFiscaleFamiliare: ").append(toIndentedString(codiceFiscaleFamiliare)).append("\n");
    sb.append("    cognomeFamiliare: ").append(toIndentedString(cognomeFamiliare)).append("\n");
    sb.append("    nomeFamiliare: ").append(toIndentedString(nomeFamiliare)).append("\n");
    sb.append("    dataNascitaFamiliare: ").append(toIndentedString(dataNascitaFamiliare)).append("\n");
    sb.append("    sesso: ").append(toIndentedString(sesso)).append("\n");
    sb.append("    luogoDiNascita: ").append(toIndentedString(luogoDiNascita)).append("\n");
    sb.append("    flgValiditaCf: ").append(toIndentedString(flgValiditaCf)).append("\n");
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

