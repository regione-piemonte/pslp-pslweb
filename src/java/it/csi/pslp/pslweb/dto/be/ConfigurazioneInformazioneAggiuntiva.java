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

public class ConfigurazioneInformazioneAggiuntiva   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long codice = null;
  private String codiceMinisteriale = null;
  private String codAmbito = null;
  private String descrizione = null;
  private String bloccante = null;
  private String valore = null;
  private String flagVisibilePslp = null;
  private Date dataInizio = null;
  private Date dataFine = null;

  /**
   **/
  

  @JsonProperty("codice") 
 
  public Long getCodice() {
    return codice;
  }
  public void setCodice(Long codice) {
    this.codice = codice;
  }

  /**
   **/
  

  @JsonProperty("codice_ministeriale") 
 
  public String getCodiceMinisteriale() {
    return codiceMinisteriale;
  }
  public void setCodiceMinisteriale(String codiceMinisteriale) {
    this.codiceMinisteriale = codiceMinisteriale;
  }

  /**
   **/
  

  @JsonProperty("cod_ambito") 
 
  public String getCodAmbito() {
    return codAmbito;
  }
  public void setCodAmbito(String codAmbito) {
    this.codAmbito = codAmbito;
  }

  /**
   **/
  

  @JsonProperty("descrizione") 
 
  public String getDescrizione() {
    return descrizione;
  }
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   **/
  

  @JsonProperty("bloccante") 
 
  public String getBloccante() {
    return bloccante;
  }
  public void setBloccante(String bloccante) {
    this.bloccante = bloccante;
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
  

  @JsonProperty("flag_visibile_pslp") 
 
  public String getFlagVisibilePslp() {
    return flagVisibilePslp;
  }
  public void setFlagVisibilePslp(String flagVisibilePslp) {
    this.flagVisibilePslp = flagVisibilePslp;
  }

  /**
   * data inizio
   **/
  

  @JsonProperty("data_inizio") 
 
  public Date getDataInizio() {
    return dataInizio;
  }
  public void setDataInizio(Date dataInizio) {
    this.dataInizio = dataInizio;
  }

  /**
   * data fine
   **/
  

  @JsonProperty("data_fine") 
 
  public Date getDataFine() {
    return dataFine;
  }
  public void setDataFine(Date dataFine) {
    this.dataFine = dataFine;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneInformazioneAggiuntiva configurazioneInformazioneAggiuntiva = (ConfigurazioneInformazioneAggiuntiva) o;
    return Objects.equals(codice, configurazioneInformazioneAggiuntiva.codice) &&
        Objects.equals(codiceMinisteriale, configurazioneInformazioneAggiuntiva.codiceMinisteriale) &&
        Objects.equals(codAmbito, configurazioneInformazioneAggiuntiva.codAmbito) &&
        Objects.equals(descrizione, configurazioneInformazioneAggiuntiva.descrizione) &&
        Objects.equals(bloccante, configurazioneInformazioneAggiuntiva.bloccante) &&
        Objects.equals(valore, configurazioneInformazioneAggiuntiva.valore) &&
        Objects.equals(flagVisibilePslp, configurazioneInformazioneAggiuntiva.flagVisibilePslp) &&
        Objects.equals(dataInizio, configurazioneInformazioneAggiuntiva.dataInizio) &&
        Objects.equals(dataFine, configurazioneInformazioneAggiuntiva.dataFine);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codice, codiceMinisteriale, codAmbito, descrizione, bloccante, valore, flagVisibilePslp, dataInizio, dataFine);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneInformazioneAggiuntiva {\n");
    
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    codiceMinisteriale: ").append(toIndentedString(codiceMinisteriale)).append("\n");
    sb.append("    codAmbito: ").append(toIndentedString(codAmbito)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    bloccante: ").append(toIndentedString(bloccante)).append("\n");
    sb.append("    valore: ").append(toIndentedString(valore)).append("\n");
    sb.append("    flagVisibilePslp: ").append(toIndentedString(flagVisibilePslp)).append("\n");
    sb.append("    dataInizio: ").append(toIndentedString(dataInizio)).append("\n");
    sb.append("    dataFine: ").append(toIndentedString(dataFine)).append("\n");
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

