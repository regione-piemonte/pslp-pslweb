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

public class Documento   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long id = null;
  private Long idSilLavSapDid = null;
  private String codiceTipoDocumento = null;
  private String codiceEstensione = null;
  private Long codiceOperatore = null;
  private String codiceUtenteInserimento = null;
  private String codiceUtenteAggiornamento = null;
  private Date dataInserimento = null;
  private Date dataInserim = null;
  private Date dataAggiornamento = null;
  private Date dataInvio = null;
  private String nome = null;
  private String ambito = null;
  private byte[] pdf = null;
  private Long subcodice = null;
  private String noteOperatore = null;
  private Long idUtente = null;
  private String noteCittadino = null;
  private String stato = null;
  private String gruppoOperatore = null;
  private String codiceFiscaleOperatore = null;
  private String flgObbligatorio = null;
  private Long idSilRichCollMir = null;

  /**
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
  

  @JsonProperty("id_sil_lav_sap_did") 
 
  public Long getIdSilLavSapDid() {
    return idSilLavSapDid;
  }
  public void setIdSilLavSapDid(Long idSilLavSapDid) {
    this.idSilLavSapDid = idSilLavSapDid;
  }

  /**
   **/
  

  @JsonProperty("codice_tipo_documento") 
 
  public String getCodiceTipoDocumento() {
    return codiceTipoDocumento;
  }
  public void setCodiceTipoDocumento(String codiceTipoDocumento) {
    this.codiceTipoDocumento = codiceTipoDocumento;
  }

  /**
   **/
  

  @JsonProperty("codice_estensione") 
 
  public String getCodiceEstensione() {
    return codiceEstensione;
  }
  public void setCodiceEstensione(String codiceEstensione) {
    this.codiceEstensione = codiceEstensione;
  }

  /**
   **/
  

  @JsonProperty("codice_operatore") 
 
  public Long getCodiceOperatore() {
    return codiceOperatore;
  }
  public void setCodiceOperatore(Long codiceOperatore) {
    this.codiceOperatore = codiceOperatore;
  }

  /**
   **/
  

  @JsonProperty("codice_utente_inserimento") 
 
  public String getCodiceUtenteInserimento() {
    return codiceUtenteInserimento;
  }
  public void setCodiceUtenteInserimento(String codiceUtenteInserimento) {
    this.codiceUtenteInserimento = codiceUtenteInserimento;
  }

  /**
   **/
  

  @JsonProperty("codice_utente_aggiornamento") 
 
  public String getCodiceUtenteAggiornamento() {
    return codiceUtenteAggiornamento;
  }
  public void setCodiceUtenteAggiornamento(String codiceUtenteAggiornamento) {
    this.codiceUtenteAggiornamento = codiceUtenteAggiornamento;
  }

  /**
   **/
  

  @JsonProperty("data_inserimento") 
 
  public Date getDataInserimento() {
    return dataInserimento;
  }
  public void setDataInserimento(Date dataInserimento) {
    this.dataInserimento = dataInserimento;
  }

  /**
   **/
  

  @JsonProperty("data_inserim") 
 
  public Date getDataInserim() {
    return dataInserim;
  }
  public void setDataInserim(Date dataInserim) {
    this.dataInserim = dataInserim;
  }

  /**
   **/
  

  @JsonProperty("data_aggiornamento") 
 
  public Date getDataAggiornamento() {
    return dataAggiornamento;
  }
  public void setDataAggiornamento(Date dataAggiornamento) {
    this.dataAggiornamento = dataAggiornamento;
  }

  /**
   **/
  

  @JsonProperty("data_invio") 
 
  public Date getDataInvio() {
    return dataInvio;
  }
  public void setDataInvio(Date dataInvio) {
    this.dataInvio = dataInvio;
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
  

  @JsonProperty("ambito") 
 
  public String getAmbito() {
    return ambito;
  }
  public void setAmbito(String ambito) {
    this.ambito = ambito;
  }

  /**
   **/
  

  @JsonProperty("pdf") 
 
  @Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$")
  public byte[] getPdf() {
    return pdf;
  }
  public void setPdf(byte[] pdf) {
    this.pdf = pdf;
  }

  /**
   **/
  

  @JsonProperty("subcodice") 
 
  public Long getSubcodice() {
    return subcodice;
  }
  public void setSubcodice(Long subcodice) {
    this.subcodice = subcodice;
  }

  /**
   **/
  

  @JsonProperty("note_operatore") 
 
  public String getNoteOperatore() {
    return noteOperatore;
  }
  public void setNoteOperatore(String noteOperatore) {
    this.noteOperatore = noteOperatore;
  }

  /**
   **/
  

  @JsonProperty("id_utente") 
 
  public Long getIdUtente() {
    return idUtente;
  }
  public void setIdUtente(Long idUtente) {
    this.idUtente = idUtente;
  }

  /**
   **/
  

  @JsonProperty("note_cittadino") 
 
  public String getNoteCittadino() {
    return noteCittadino;
  }
  public void setNoteCittadino(String noteCittadino) {
    this.noteCittadino = noteCittadino;
  }

  /**
   **/
  

  @JsonProperty("stato") 
 
  public String getStato() {
    return stato;
  }
  public void setStato(String stato) {
    this.stato = stato;
  }

  /**
   **/
  

  @JsonProperty("gruppo_operatore") 
 
  public String getGruppoOperatore() {
    return gruppoOperatore;
  }
  public void setGruppoOperatore(String gruppoOperatore) {
    this.gruppoOperatore = gruppoOperatore;
  }

  /**
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
  

  @JsonProperty("flg_obbligatorio") 
 
  public String getFlgObbligatorio() {
    return flgObbligatorio;
  }
  public void setFlgObbligatorio(String flgObbligatorio) {
    this.flgObbligatorio = flgObbligatorio;
  }

  /**
   **/
  

  @JsonProperty("id_sil_rich_coll_mir") 
 
  public Long getIdSilRichCollMir() {
    return idSilRichCollMir;
  }
  public void setIdSilRichCollMir(Long idSilRichCollMir) {
    this.idSilRichCollMir = idSilRichCollMir;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Documento documento = (Documento) o;
    return Objects.equals(id, documento.id) &&
        Objects.equals(idSilLavSapDid, documento.idSilLavSapDid) &&
        Objects.equals(codiceTipoDocumento, documento.codiceTipoDocumento) &&
        Objects.equals(codiceEstensione, documento.codiceEstensione) &&
        Objects.equals(codiceOperatore, documento.codiceOperatore) &&
        Objects.equals(codiceUtenteInserimento, documento.codiceUtenteInserimento) &&
        Objects.equals(codiceUtenteAggiornamento, documento.codiceUtenteAggiornamento) &&
        Objects.equals(dataInserimento, documento.dataInserimento) &&
        Objects.equals(dataInserim, documento.dataInserim) &&
        Objects.equals(dataAggiornamento, documento.dataAggiornamento) &&
        Objects.equals(dataInvio, documento.dataInvio) &&
        Objects.equals(nome, documento.nome) &&
        Objects.equals(ambito, documento.ambito) &&
        Objects.equals(pdf, documento.pdf) &&
        Objects.equals(subcodice, documento.subcodice) &&
        Objects.equals(noteOperatore, documento.noteOperatore) &&
        Objects.equals(idUtente, documento.idUtente) &&
        Objects.equals(noteCittadino, documento.noteCittadino) &&
        Objects.equals(stato, documento.stato) &&
        Objects.equals(gruppoOperatore, documento.gruppoOperatore) &&
        Objects.equals(codiceFiscaleOperatore, documento.codiceFiscaleOperatore) &&
        Objects.equals(flgObbligatorio, documento.flgObbligatorio) &&
        Objects.equals(idSilRichCollMir, documento.idSilRichCollMir);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idSilLavSapDid, codiceTipoDocumento, codiceEstensione, codiceOperatore, codiceUtenteInserimento, codiceUtenteAggiornamento, dataInserimento, dataInserim, dataAggiornamento, dataInvio, nome, ambito, pdf, subcodice, noteOperatore, idUtente, noteCittadino, stato, gruppoOperatore, codiceFiscaleOperatore, flgObbligatorio, idSilRichCollMir);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Documento {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idSilLavSapDid: ").append(toIndentedString(idSilLavSapDid)).append("\n");
    sb.append("    codiceTipoDocumento: ").append(toIndentedString(codiceTipoDocumento)).append("\n");
    sb.append("    codiceEstensione: ").append(toIndentedString(codiceEstensione)).append("\n");
    sb.append("    codiceOperatore: ").append(toIndentedString(codiceOperatore)).append("\n");
    sb.append("    codiceUtenteInserimento: ").append(toIndentedString(codiceUtenteInserimento)).append("\n");
    sb.append("    codiceUtenteAggiornamento: ").append(toIndentedString(codiceUtenteAggiornamento)).append("\n");
    sb.append("    dataInserimento: ").append(toIndentedString(dataInserimento)).append("\n");
    sb.append("    dataInserim: ").append(toIndentedString(dataInserim)).append("\n");
    sb.append("    dataAggiornamento: ").append(toIndentedString(dataAggiornamento)).append("\n");
    sb.append("    dataInvio: ").append(toIndentedString(dataInvio)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    ambito: ").append(toIndentedString(ambito)).append("\n");
    sb.append("    pdf: ").append(toIndentedString(pdf)).append("\n");
    sb.append("    subcodice: ").append(toIndentedString(subcodice)).append("\n");
    sb.append("    noteOperatore: ").append(toIndentedString(noteOperatore)).append("\n");
    sb.append("    idUtente: ").append(toIndentedString(idUtente)).append("\n");
    sb.append("    noteCittadino: ").append(toIndentedString(noteCittadino)).append("\n");
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
    sb.append("    gruppoOperatore: ").append(toIndentedString(gruppoOperatore)).append("\n");
    sb.append("    codiceFiscaleOperatore: ").append(toIndentedString(codiceFiscaleOperatore)).append("\n");
    sb.append("    flgObbligatorio: ").append(toIndentedString(flgObbligatorio)).append("\n");
    sb.append("    idSilRichCollMir: ").append(toIndentedString(idSilRichCollMir)).append("\n");
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

