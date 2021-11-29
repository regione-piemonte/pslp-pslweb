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

public class RichiestaIscrizioneL68Header   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idRichiesta = null;
  private Long idAnagrafica = null;
  private String codStatoRichiesta = null;
  private Date dataStato = null;
  private String codiceFiscale = null;
  private String cognome = null;
  private String nome = null;
  private String descrProvincia = null;
  private String descrTipoIscrizione = null;
  private String descrTipoComunicazione = null;
  private String descrStatoRichiestaIscrizione = null;
  private String descrCpi = null;
  private String descrCpiUltimaIscrizione = null;
  private String flgPresaInCarico = null;
  private String descrOperatorePresaInCarico = null;
  private String descrMotivoRichiesta = null;
  private String descrCategoriaInvalidita = null;
  private String codiceFiscaleOperatore = null;
  private String cognomeOperatore = null;
  private String nomeOperatore = null;

  /**
   * id richiesta
   **/
  

  @JsonProperty("id_richiesta") 
 
  public Long getIdRichiesta() {
    return idRichiesta;
  }
  public void setIdRichiesta(Long idRichiesta) {
    this.idRichiesta = idRichiesta;
  }

  /**
   * id richiesta
   **/
  

  @JsonProperty("id_anagrafica") 
 
  public Long getIdAnagrafica() {
    return idAnagrafica;
  }
  public void setIdAnagrafica(Long idAnagrafica) {
    this.idAnagrafica = idAnagrafica;
  }

  /**
   * B &#x3D; Bozza - I &#x3D; Inviato - A &#x3D; Accettata - R &#x3D; Rifiutata - O &#x3D; Annullato da Operatore
   **/
  

  @JsonProperty("cod_stato_richiesta") 
 
  public String getCodStatoRichiesta() {
    return codStatoRichiesta;
  }
  public void setCodStatoRichiesta(String codStatoRichiesta) {
    this.codStatoRichiesta = codStatoRichiesta;
  }

  /**
   * data stato richiesta
   **/
  

  @JsonProperty("data_stato") 
 
  public Date getDataStato() {
    return dataStato;
  }
  public void setDataStato(Date dataStato) {
    this.dataStato = dataStato;
  }

  /**
   * codice fiscale utente
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
  

  @JsonProperty("cognome") 
 
  public String getCognome() {
    return cognome;
  }
  public void setCognome(String cognome) {
    this.cognome = cognome;
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
  

  @JsonProperty("descr_provincia") 
 
  public String getDescrProvincia() {
    return descrProvincia;
  }
  public void setDescrProvincia(String descrProvincia) {
    this.descrProvincia = descrProvincia;
  }

  /**
   **/
  

  @JsonProperty("descr_tipo_iscrizione") 
 
  public String getDescrTipoIscrizione() {
    return descrTipoIscrizione;
  }
  public void setDescrTipoIscrizione(String descrTipoIscrizione) {
    this.descrTipoIscrizione = descrTipoIscrizione;
  }

  /**
   **/
  

  @JsonProperty("descr_tipo_comunicazione") 
 
  public String getDescrTipoComunicazione() {
    return descrTipoComunicazione;
  }
  public void setDescrTipoComunicazione(String descrTipoComunicazione) {
    this.descrTipoComunicazione = descrTipoComunicazione;
  }

  /**
   **/
  

  @JsonProperty("descr_stato_richiesta_iscrizione") 
 
  public String getDescrStatoRichiestaIscrizione() {
    return descrStatoRichiestaIscrizione;
  }
  public void setDescrStatoRichiestaIscrizione(String descrStatoRichiestaIscrizione) {
    this.descrStatoRichiestaIscrizione = descrStatoRichiestaIscrizione;
  }

  /**
   **/
  

  @JsonProperty("descr_cpi") 
 
  public String getDescrCpi() {
    return descrCpi;
  }
  public void setDescrCpi(String descrCpi) {
    this.descrCpi = descrCpi;
  }

  /**
   **/
  

  @JsonProperty("descr_cpi_ultima_iscrizione") 
 
  public String getDescrCpiUltimaIscrizione() {
    return descrCpiUltimaIscrizione;
  }
  public void setDescrCpiUltimaIscrizione(String descrCpiUltimaIscrizione) {
    this.descrCpiUltimaIscrizione = descrCpiUltimaIscrizione;
  }

  /**
   **/
  

  @JsonProperty("flg_presa_in_carico") 
 
  public String getFlgPresaInCarico() {
    return flgPresaInCarico;
  }
  public void setFlgPresaInCarico(String flgPresaInCarico) {
    this.flgPresaInCarico = flgPresaInCarico;
  }

  /**
   **/
  

  @JsonProperty("descr_operatore_presa_in_carico") 
 
  public String getDescrOperatorePresaInCarico() {
    return descrOperatorePresaInCarico;
  }
  public void setDescrOperatorePresaInCarico(String descrOperatorePresaInCarico) {
    this.descrOperatorePresaInCarico = descrOperatorePresaInCarico;
  }

  /**
   **/
  

  @JsonProperty("descr_motivo_richiesta") 
 
  public String getDescrMotivoRichiesta() {
    return descrMotivoRichiesta;
  }
  public void setDescrMotivoRichiesta(String descrMotivoRichiesta) {
    this.descrMotivoRichiesta = descrMotivoRichiesta;
  }

  /**
   **/
  

  @JsonProperty("descr_categoria_invalidita") 
 
  public String getDescrCategoriaInvalidita() {
    return descrCategoriaInvalidita;
  }
  public void setDescrCategoriaInvalidita(String descrCategoriaInvalidita) {
    this.descrCategoriaInvalidita = descrCategoriaInvalidita;
  }

  /**
   * codice fiscale operatore
   **/
  

  @JsonProperty("codice_fiscale_operatore") 
 
  public String getCodiceFiscaleOperatore() {
    return codiceFiscaleOperatore;
  }
  public void setCodiceFiscaleOperatore(String codiceFiscaleOperatore) {
    this.codiceFiscaleOperatore = codiceFiscaleOperatore;
  }

  /**
   **/
  

  @JsonProperty("cognome_operatore") 
 
  public String getCognomeOperatore() {
    return cognomeOperatore;
  }
  public void setCognomeOperatore(String cognomeOperatore) {
    this.cognomeOperatore = cognomeOperatore;
  }

  /**
   **/
  

  @JsonProperty("nome_operatore") 
 
  public String getNomeOperatore() {
    return nomeOperatore;
  }
  public void setNomeOperatore(String nomeOperatore) {
    this.nomeOperatore = nomeOperatore;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RichiestaIscrizioneL68Header richiestaIscrizioneL68Header = (RichiestaIscrizioneL68Header) o;
    return Objects.equals(idRichiesta, richiestaIscrizioneL68Header.idRichiesta) &&
        Objects.equals(idAnagrafica, richiestaIscrizioneL68Header.idAnagrafica) &&
        Objects.equals(codStatoRichiesta, richiestaIscrizioneL68Header.codStatoRichiesta) &&
        Objects.equals(dataStato, richiestaIscrizioneL68Header.dataStato) &&
        Objects.equals(codiceFiscale, richiestaIscrizioneL68Header.codiceFiscale) &&
        Objects.equals(cognome, richiestaIscrizioneL68Header.cognome) &&
        Objects.equals(nome, richiestaIscrizioneL68Header.nome) &&
        Objects.equals(descrProvincia, richiestaIscrizioneL68Header.descrProvincia) &&
        Objects.equals(descrTipoIscrizione, richiestaIscrizioneL68Header.descrTipoIscrizione) &&
        Objects.equals(descrTipoComunicazione, richiestaIscrizioneL68Header.descrTipoComunicazione) &&
        Objects.equals(descrStatoRichiestaIscrizione, richiestaIscrizioneL68Header.descrStatoRichiestaIscrizione) &&
        Objects.equals(descrCpi, richiestaIscrizioneL68Header.descrCpi) &&
        Objects.equals(descrCpiUltimaIscrizione, richiestaIscrizioneL68Header.descrCpiUltimaIscrizione) &&
        Objects.equals(flgPresaInCarico, richiestaIscrizioneL68Header.flgPresaInCarico) &&
        Objects.equals(descrOperatorePresaInCarico, richiestaIscrizioneL68Header.descrOperatorePresaInCarico) &&
        Objects.equals(descrMotivoRichiesta, richiestaIscrizioneL68Header.descrMotivoRichiesta) &&
        Objects.equals(descrCategoriaInvalidita, richiestaIscrizioneL68Header.descrCategoriaInvalidita) &&
        Objects.equals(codiceFiscaleOperatore, richiestaIscrizioneL68Header.codiceFiscaleOperatore) &&
        Objects.equals(cognomeOperatore, richiestaIscrizioneL68Header.cognomeOperatore) &&
        Objects.equals(nomeOperatore, richiestaIscrizioneL68Header.nomeOperatore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idRichiesta, idAnagrafica, codStatoRichiesta, dataStato, codiceFiscale, cognome, nome, descrProvincia, descrTipoIscrizione, descrTipoComunicazione, descrStatoRichiestaIscrizione, descrCpi, descrCpiUltimaIscrizione, flgPresaInCarico, descrOperatorePresaInCarico, descrMotivoRichiesta, descrCategoriaInvalidita, codiceFiscaleOperatore, cognomeOperatore, nomeOperatore);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RichiestaIscrizioneL68Header {\n");
    
    sb.append("    idRichiesta: ").append(toIndentedString(idRichiesta)).append("\n");
    sb.append("    idAnagrafica: ").append(toIndentedString(idAnagrafica)).append("\n");
    sb.append("    codStatoRichiesta: ").append(toIndentedString(codStatoRichiesta)).append("\n");
    sb.append("    dataStato: ").append(toIndentedString(dataStato)).append("\n");
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    descrProvincia: ").append(toIndentedString(descrProvincia)).append("\n");
    sb.append("    descrTipoIscrizione: ").append(toIndentedString(descrTipoIscrizione)).append("\n");
    sb.append("    descrTipoComunicazione: ").append(toIndentedString(descrTipoComunicazione)).append("\n");
    sb.append("    descrStatoRichiestaIscrizione: ").append(toIndentedString(descrStatoRichiestaIscrizione)).append("\n");
    sb.append("    descrCpi: ").append(toIndentedString(descrCpi)).append("\n");
    sb.append("    descrCpiUltimaIscrizione: ").append(toIndentedString(descrCpiUltimaIscrizione)).append("\n");
    sb.append("    flgPresaInCarico: ").append(toIndentedString(flgPresaInCarico)).append("\n");
    sb.append("    descrOperatorePresaInCarico: ").append(toIndentedString(descrOperatorePresaInCarico)).append("\n");
    sb.append("    descrMotivoRichiesta: ").append(toIndentedString(descrMotivoRichiesta)).append("\n");
    sb.append("    descrCategoriaInvalidita: ").append(toIndentedString(descrCategoriaInvalidita)).append("\n");
    sb.append("    codiceFiscaleOperatore: ").append(toIndentedString(codiceFiscaleOperatore)).append("\n");
    sb.append("    cognomeOperatore: ").append(toIndentedString(cognomeOperatore)).append("\n");
    sb.append("    nomeOperatore: ").append(toIndentedString(nomeOperatore)).append("\n");
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

