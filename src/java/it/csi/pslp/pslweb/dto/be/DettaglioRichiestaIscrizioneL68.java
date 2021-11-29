/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.DettaglioDichiarazioneFamiliariACarico;
import it.csi.pslp.pslweb.dto.be.Documento;
import it.csi.pslp.pslweb.dto.be.Indirizzo;
import it.csi.pslp.pslweb.dto.be.IscrizioneL68;
import it.csi.pslp.pslweb.dto.be.MessaggioEsito;
import it.csi.pslp.pslweb.dto.be.ReferenteServizioTerritoriale;
import it.csi.pslp.pslweb.dto.be.RichiestaIscrizioneL68Header;
import it.csi.pslp.pslweb.dto.be.VerbaleL68;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class DettaglioRichiestaIscrizioneL68   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private RichiestaIscrizioneL68Header richiestaIscrizioneHeader = null;
  private String tipoOperazione = null;
  private String codTipoIscrizione = null;
  private String codMotivoRichiesta = null;
  private String codCategoriaAppartenenza = null;
  private String codTipoComunicazione = null;
  private Long idCpi = null;
  private String idProvincia = null;
  private Long idCpiUltimaIscrizione = null;
  private Date dataUltimaIscrizione = null;
  private Date dataAnnullo = null;
  private String idProvinciaUltimaIscrizione = null;
  private String descrProvinciaUltimaIscrizione = null;
  private Indirizzo domicilioTrasferimento = null;
  private Long gradoInvalidita = null;
  private Long codCategoriaInvalidita = null;
  private List<Decodifica> elencoQualificaNonVedenti = new ArrayList<Decodifica>();
  private VerbaleL68 verbaleCollocamentoMirato = null;
  private VerbaleL68 verbaleInvaliditaCivile = null;
  private String flgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato = null;
  private String flgSeguitoDaServiziTerritoriali = null;
  private ReferenteServizioTerritoriale referenteServiziTerritoriali = null;
  private String flgLicenziatoInUltimoRapporto = null;
  private Long annoReddito = null;
  private String importoReddito = null;
  private String noteReddito = null;
  private Long annoRiferimentoFamiliariACarico = null;
  private Long numeroFamiliariACarico = null;
  private List<DettaglioDichiarazioneFamiliariACarico> elencoFamiliariACarico = new ArrayList<DettaglioDichiarazioneFamiliariACarico>();
  private String note = null;
  private List<Documento> elencoAllegati = new ArrayList<Documento>();
  private String idOperatorePresaInCarico = null;
  private Date dataInvio = null;
  private String noteRifiuto = null;
  private IscrizioneL68 iscrizioneLegge68 = null;
  private List<MessaggioEsito> elencoCheckList = new ArrayList<MessaggioEsito>();
  private Date dataInserimento = null;
  private Date dataAggiornamento = null;
  private String riepilogHtml = null;
  private String flgPrimaIscrizione = null;
  private String flgDichiarazioneVisitaRevisioneCollocamentoMirato = null;
  private String codDichiarazioneVisitaRevisioneInvaliditaCivile = null;

  /**
   **/
  

  @JsonProperty("richiesta_iscrizione_header") 
 
  public RichiestaIscrizioneL68Header getRichiestaIscrizioneHeader() {
    return richiestaIscrizioneHeader;
  }
  public void setRichiestaIscrizioneHeader(RichiestaIscrizioneL68Header richiestaIscrizioneHeader) {
    this.richiestaIscrizioneHeader = richiestaIscrizioneHeader;
  }

  /**
   * tipo operazione I &#x3D; Inserimento se CALLER PSLP - A &#x3D; Aggiornamento se CALLER PSLP o SILP - N &#x3D; Annullamento logico se CALLER PSLP - O &#x3D; Annullamento logico se CALLER SILP
   **/
  

  @JsonProperty("tipo_operazione") 
 
  public String getTipoOperazione() {
    return tipoOperazione;
  }
  public void setTipoOperazione(String tipoOperazione) {
    this.tipoOperazione = tipoOperazione;
  }

  /**
   * cod tipo iscrizione   A &#x3D; Altre Categorie Protette   -   D &#x3D; Disabili
   **/
  

  @JsonProperty("cod_tipo_iscrizione") 
 
  public String getCodTipoIscrizione() {
    return codTipoIscrizione;
  }
  public void setCodTipoIscrizione(String codTipoIscrizione) {
    this.codTipoIscrizione = codTipoIscrizione;
  }

  /**
   * cod motivo richiesta   L Ricerca Lavoro - O Gia&#39; Occupato
   **/
  

  @JsonProperty("cod_motivo_richiesta") 
 
  public String getCodMotivoRichiesta() {
    return codMotivoRichiesta;
  }
  public void setCodMotivoRichiesta(String codMotivoRichiesta) {
    this.codMotivoRichiesta = codMotivoRichiesta;
  }

  /**
   * Se cod tipo iscrizione A da valori da tab SIL_T_CAT_PROTETTA (01,02...)  -     Se D da tab. SIL_T_CATEG_PROTETTA_DISAB (1,2,3,4,5)
   **/
  

  @JsonProperty("cod_categoria_appartenenza") 
 
  public String getCodCategoriaAppartenenza() {
    return codCategoriaAppartenenza;
  }
  public void setCodCategoriaAppartenenza(String codCategoriaAppartenenza) {
    this.codCategoriaAppartenenza = codCategoriaAppartenenza;
  }

  /**
   * P   Prima Iscrizione -  I  Iscrizione successiva -  T Trasferimento
   **/
  

  @JsonProperty("cod_tipo_comunicazione") 
 
  public String getCodTipoComunicazione() {
    return codTipoComunicazione;
  }
  public void setCodTipoComunicazione(String codTipoComunicazione) {
    this.codTipoComunicazione = codTipoComunicazione;
  }

  /**
   **/
  

  @JsonProperty("id_cpi") 
 
  public Long getIdCpi() {
    return idCpi;
  }
  public void setIdCpi(Long idCpi) {
    this.idCpi = idCpi;
  }

  /**
   **/
  

  @JsonProperty("id_provincia") 
 
  public String getIdProvincia() {
    return idProvincia;
  }
  public void setIdProvincia(String idProvincia) {
    this.idProvincia = idProvincia;
  }

  /**
   **/
  

  @JsonProperty("id_cpi_ultima_iscrizione") 
 
  public Long getIdCpiUltimaIscrizione() {
    return idCpiUltimaIscrizione;
  }
  public void setIdCpiUltimaIscrizione(Long idCpiUltimaIscrizione) {
    this.idCpiUltimaIscrizione = idCpiUltimaIscrizione;
  }

  /**
   * Data dell&#39;ultima iscrizione
   **/
  

  @JsonProperty("data_ultima_iscrizione") 
 
  public Date getDataUltimaIscrizione() {
    return dataUltimaIscrizione;
  }
  public void setDataUltimaIscrizione(Date dataUltimaIscrizione) {
    this.dataUltimaIscrizione = dataUltimaIscrizione;
  }

  /**
   * Data annullo
   **/
  

  @JsonProperty("data_annullo") 
 
  public Date getDataAnnullo() {
    return dataAnnullo;
  }
  public void setDataAnnullo(Date dataAnnullo) {
    this.dataAnnullo = dataAnnullo;
  }

  /**
   **/
  

  @JsonProperty("id_provincia_ultima_iscrizione") 
 
  public String getIdProvinciaUltimaIscrizione() {
    return idProvinciaUltimaIscrizione;
  }
  public void setIdProvinciaUltimaIscrizione(String idProvinciaUltimaIscrizione) {
    this.idProvinciaUltimaIscrizione = idProvinciaUltimaIscrizione;
  }

  /**
   **/
  

  @JsonProperty("descr_provincia_ultima_iscrizione") 
 
  public String getDescrProvinciaUltimaIscrizione() {
    return descrProvinciaUltimaIscrizione;
  }
  public void setDescrProvinciaUltimaIscrizione(String descrProvinciaUltimaIscrizione) {
    this.descrProvinciaUltimaIscrizione = descrProvinciaUltimaIscrizione;
  }

  /**
   **/
  

  @JsonProperty("domicilio_trasferimento") 
 
  public Indirizzo getDomicilioTrasferimento() {
    return domicilioTrasferimento;
  }
  public void setDomicilioTrasferimento(Indirizzo domicilioTrasferimento) {
    this.domicilioTrasferimento = domicilioTrasferimento;
  }

  /**
   **/
  

  @JsonProperty("grado_invalidita") 
 
  public Long getGradoInvalidita() {
    return gradoInvalidita;
  }
  public void setGradoInvalidita(Long gradoInvalidita) {
    this.gradoInvalidita = gradoInvalidita;
  }

  /**
   **/
  

  @JsonProperty("cod_categoria_invalidita") 
 
  public Long getCodCategoriaInvalidita() {
    return codCategoriaInvalidita;
  }
  public void setCodCategoriaInvalidita(Long codCategoriaInvalidita) {
    this.codCategoriaInvalidita = codCategoriaInvalidita;
  }

  /**
   **/
  

  @JsonProperty("elenco_qualifica_non_vedenti") 
 
  public List<Decodifica> getElencoQualificaNonVedenti() {
    return elencoQualificaNonVedenti;
  }
  public void setElencoQualificaNonVedenti(List<Decodifica> elencoQualificaNonVedenti) {
    this.elencoQualificaNonVedenti = elencoQualificaNonVedenti;
  }

  /**
   **/
  

  @JsonProperty("verbale_collocamento_mirato") 
 
  public VerbaleL68 getVerbaleCollocamentoMirato() {
    return verbaleCollocamentoMirato;
  }
  public void setVerbaleCollocamentoMirato(VerbaleL68 verbaleCollocamentoMirato) {
    this.verbaleCollocamentoMirato = verbaleCollocamentoMirato;
  }

  /**
   **/
  

  @JsonProperty("verbale_invalidita_civile") 
 
  public VerbaleL68 getVerbaleInvaliditaCivile() {
    return verbaleInvaliditaCivile;
  }
  public void setVerbaleInvaliditaCivile(VerbaleL68 verbaleInvaliditaCivile) {
    this.verbaleInvaliditaCivile = verbaleInvaliditaCivile;
  }

  /**
   **/
  

  @JsonProperty("flg_autorizzazione_cpi_prenotazione_visita_collocamento_mirato") 
 
  public String getFlgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato() {
    return flgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato;
  }
  public void setFlgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato(String flgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato) {
    this.flgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato = flgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato;
  }

  /**
   **/
  

  @JsonProperty("flg_seguito_da_servizi_territoriali") 
 
  public String getFlgSeguitoDaServiziTerritoriali() {
    return flgSeguitoDaServiziTerritoriali;
  }
  public void setFlgSeguitoDaServiziTerritoriali(String flgSeguitoDaServiziTerritoriali) {
    this.flgSeguitoDaServiziTerritoriali = flgSeguitoDaServiziTerritoriali;
  }

  /**
   **/
  

  @JsonProperty("referente_servizi_territoriali") 
 
  public ReferenteServizioTerritoriale getReferenteServiziTerritoriali() {
    return referenteServiziTerritoriali;
  }
  public void setReferenteServiziTerritoriali(ReferenteServizioTerritoriale referenteServiziTerritoriali) {
    this.referenteServiziTerritoriali = referenteServiziTerritoriali;
  }

  /**
   **/
  

  @JsonProperty("flg_licenziato_in_ultimo_rapporto") 
 
  public String getFlgLicenziatoInUltimoRapporto() {
    return flgLicenziatoInUltimoRapporto;
  }
  public void setFlgLicenziatoInUltimoRapporto(String flgLicenziatoInUltimoRapporto) {
    this.flgLicenziatoInUltimoRapporto = flgLicenziatoInUltimoRapporto;
  }

  /**
   **/
  

  @JsonProperty("anno_reddito") 
 
  public Long getAnnoReddito() {
    return annoReddito;
  }
  public void setAnnoReddito(Long annoReddito) {
    this.annoReddito = annoReddito;
  }

  /**
   **/
  

  @JsonProperty("importo_reddito") 
 
  public String getImportoReddito() {
    return importoReddito;
  }
  public void setImportoReddito(String importoReddito) {
    this.importoReddito = importoReddito;
  }

  /**
   **/
  

  @JsonProperty("note_reddito") 
 
  public String getNoteReddito() {
    return noteReddito;
  }
  public void setNoteReddito(String noteReddito) {
    this.noteReddito = noteReddito;
  }

  /**
   **/
  

  @JsonProperty("anno_riferimento_familiari_a_carico") 
 
  public Long getAnnoRiferimentoFamiliariACarico() {
    return annoRiferimentoFamiliariACarico;
  }
  public void setAnnoRiferimentoFamiliariACarico(Long annoRiferimentoFamiliariACarico) {
    this.annoRiferimentoFamiliariACarico = annoRiferimentoFamiliariACarico;
  }

  /**
   **/
  

  @JsonProperty("numero_familiari_a_carico") 
 
  public Long getNumeroFamiliariACarico() {
    return numeroFamiliariACarico;
  }
  public void setNumeroFamiliariACarico(Long numeroFamiliariACarico) {
    this.numeroFamiliariACarico = numeroFamiliariACarico;
  }

  /**
   **/
  

  @JsonProperty("elenco_familiari_a_carico") 
 
  public List<DettaglioDichiarazioneFamiliariACarico> getElencoFamiliariACarico() {
    return elencoFamiliariACarico;
  }
  public void setElencoFamiliariACarico(List<DettaglioDichiarazioneFamiliariACarico> elencoFamiliariACarico) {
    this.elencoFamiliariACarico = elencoFamiliariACarico;
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

  /**
   **/
  

  @JsonProperty("elenco_allegati") 
 
  public List<Documento> getElencoAllegati() {
    return elencoAllegati;
  }
  public void setElencoAllegati(List<Documento> elencoAllegati) {
    this.elencoAllegati = elencoAllegati;
  }

  /**
   **/
  

  @JsonProperty("id_operatore_presa_in_carico") 
 
  public String getIdOperatorePresaInCarico() {
    return idOperatorePresaInCarico;
  }
  public void setIdOperatorePresaInCarico(String idOperatorePresaInCarico) {
    this.idOperatorePresaInCarico = idOperatorePresaInCarico;
  }

  /**
   * Data invio
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
  

  @JsonProperty("note_rifiuto") 
 
  public String getNoteRifiuto() {
    return noteRifiuto;
  }
  public void setNoteRifiuto(String noteRifiuto) {
    this.noteRifiuto = noteRifiuto;
  }

  /**
   **/
  

  @JsonProperty("iscrizione_legge68") 
 
  public IscrizioneL68 getIscrizioneLegge68() {
    return iscrizioneLegge68;
  }
  public void setIscrizioneLegge68(IscrizioneL68 iscrizioneLegge68) {
    this.iscrizioneLegge68 = iscrizioneLegge68;
  }

  /**
   **/
  

  @JsonProperty("elencoCheckList") 
 
  public List<MessaggioEsito> getElencoCheckList() {
    return elencoCheckList;
  }
  public void setElencoCheckList(List<MessaggioEsito> elencoCheckList) {
    this.elencoCheckList = elencoCheckList;
  }

  /**
   * Data inserimento
   **/
  

  @JsonProperty("data_inserimento") 
 
  public Date getDataInserimento() {
    return dataInserimento;
  }
  public void setDataInserimento(Date dataInserimento) {
    this.dataInserimento = dataInserimento;
  }

  /**
   * Data aggiornamento
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
  

  @JsonProperty("riepilogHtml") 
 
  public String getRiepilogHtml() {
    return riepilogHtml;
  }
  public void setRiepilogHtml(String riepilogHtml) {
    this.riepilogHtml = riepilogHtml;
  }

  /**
   * flg per indicare che si tratta di una prima iscrizione
   **/
  

  @JsonProperty("flg_prima_iscrizione") 
 
  public String getFlgPrimaIscrizione() {
    return flgPrimaIscrizione;
  }
  public void setFlgPrimaIscrizione(String flgPrimaIscrizione) {
    this.flgPrimaIscrizione = flgPrimaIscrizione;
  }

  /**
   * flg per indicare che esiste un codice per la dichiarazione
   **/
  

  @JsonProperty("flg_dichiarazione_visita_revisione_collocamento_mirato") 
 
  public String getFlgDichiarazioneVisitaRevisioneCollocamentoMirato() {
    return flgDichiarazioneVisitaRevisioneCollocamentoMirato;
  }
  public void setFlgDichiarazioneVisitaRevisioneCollocamentoMirato(String flgDichiarazioneVisitaRevisioneCollocamentoMirato) {
    this.flgDichiarazioneVisitaRevisioneCollocamentoMirato = flgDichiarazioneVisitaRevisioneCollocamentoMirato;
  }

  /**
   * codice dichiarazione visita revisione invalidita civile
   **/
  

  @JsonProperty("cod_dichiarazione_visita_revisione_invalidita_civile") 
 
  public String getCodDichiarazioneVisitaRevisioneInvaliditaCivile() {
    return codDichiarazioneVisitaRevisioneInvaliditaCivile;
  }
  public void setCodDichiarazioneVisitaRevisioneInvaliditaCivile(String codDichiarazioneVisitaRevisioneInvaliditaCivile) {
    this.codDichiarazioneVisitaRevisioneInvaliditaCivile = codDichiarazioneVisitaRevisioneInvaliditaCivile;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DettaglioRichiestaIscrizioneL68 dettaglioRichiestaIscrizioneL68 = (DettaglioRichiestaIscrizioneL68) o;
    return Objects.equals(richiestaIscrizioneHeader, dettaglioRichiestaIscrizioneL68.richiestaIscrizioneHeader) &&
        Objects.equals(tipoOperazione, dettaglioRichiestaIscrizioneL68.tipoOperazione) &&
        Objects.equals(codTipoIscrizione, dettaglioRichiestaIscrizioneL68.codTipoIscrizione) &&
        Objects.equals(codMotivoRichiesta, dettaglioRichiestaIscrizioneL68.codMotivoRichiesta) &&
        Objects.equals(codCategoriaAppartenenza, dettaglioRichiestaIscrizioneL68.codCategoriaAppartenenza) &&
        Objects.equals(codTipoComunicazione, dettaglioRichiestaIscrizioneL68.codTipoComunicazione) &&
        Objects.equals(idCpi, dettaglioRichiestaIscrizioneL68.idCpi) &&
        Objects.equals(idProvincia, dettaglioRichiestaIscrizioneL68.idProvincia) &&
        Objects.equals(idCpiUltimaIscrizione, dettaglioRichiestaIscrizioneL68.idCpiUltimaIscrizione) &&
        Objects.equals(dataUltimaIscrizione, dettaglioRichiestaIscrizioneL68.dataUltimaIscrizione) &&
        Objects.equals(dataAnnullo, dettaglioRichiestaIscrizioneL68.dataAnnullo) &&
        Objects.equals(idProvinciaUltimaIscrizione, dettaglioRichiestaIscrizioneL68.idProvinciaUltimaIscrizione) &&
        Objects.equals(descrProvinciaUltimaIscrizione, dettaglioRichiestaIscrizioneL68.descrProvinciaUltimaIscrizione) &&
        Objects.equals(domicilioTrasferimento, dettaglioRichiestaIscrizioneL68.domicilioTrasferimento) &&
        Objects.equals(gradoInvalidita, dettaglioRichiestaIscrizioneL68.gradoInvalidita) &&
        Objects.equals(codCategoriaInvalidita, dettaglioRichiestaIscrizioneL68.codCategoriaInvalidita) &&
        Objects.equals(elencoQualificaNonVedenti, dettaglioRichiestaIscrizioneL68.elencoQualificaNonVedenti) &&
        Objects.equals(verbaleCollocamentoMirato, dettaglioRichiestaIscrizioneL68.verbaleCollocamentoMirato) &&
        Objects.equals(verbaleInvaliditaCivile, dettaglioRichiestaIscrizioneL68.verbaleInvaliditaCivile) &&
        Objects.equals(flgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato, dettaglioRichiestaIscrizioneL68.flgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato) &&
        Objects.equals(flgSeguitoDaServiziTerritoriali, dettaglioRichiestaIscrizioneL68.flgSeguitoDaServiziTerritoriali) &&
        Objects.equals(referenteServiziTerritoriali, dettaglioRichiestaIscrizioneL68.referenteServiziTerritoriali) &&
        Objects.equals(flgLicenziatoInUltimoRapporto, dettaglioRichiestaIscrizioneL68.flgLicenziatoInUltimoRapporto) &&
        Objects.equals(annoReddito, dettaglioRichiestaIscrizioneL68.annoReddito) &&
        Objects.equals(importoReddito, dettaglioRichiestaIscrizioneL68.importoReddito) &&
        Objects.equals(noteReddito, dettaglioRichiestaIscrizioneL68.noteReddito) &&
        Objects.equals(annoRiferimentoFamiliariACarico, dettaglioRichiestaIscrizioneL68.annoRiferimentoFamiliariACarico) &&
        Objects.equals(numeroFamiliariACarico, dettaglioRichiestaIscrizioneL68.numeroFamiliariACarico) &&
        Objects.equals(elencoFamiliariACarico, dettaglioRichiestaIscrizioneL68.elencoFamiliariACarico) &&
        Objects.equals(note, dettaglioRichiestaIscrizioneL68.note) &&
        Objects.equals(elencoAllegati, dettaglioRichiestaIscrizioneL68.elencoAllegati) &&
        Objects.equals(idOperatorePresaInCarico, dettaglioRichiestaIscrizioneL68.idOperatorePresaInCarico) &&
        Objects.equals(dataInvio, dettaglioRichiestaIscrizioneL68.dataInvio) &&
        Objects.equals(noteRifiuto, dettaglioRichiestaIscrizioneL68.noteRifiuto) &&
        Objects.equals(iscrizioneLegge68, dettaglioRichiestaIscrizioneL68.iscrizioneLegge68) &&
        Objects.equals(elencoCheckList, dettaglioRichiestaIscrizioneL68.elencoCheckList) &&
        Objects.equals(dataInserimento, dettaglioRichiestaIscrizioneL68.dataInserimento) &&
        Objects.equals(dataAggiornamento, dettaglioRichiestaIscrizioneL68.dataAggiornamento) &&
        Objects.equals(riepilogHtml, dettaglioRichiestaIscrizioneL68.riepilogHtml) &&
        Objects.equals(flgPrimaIscrizione, dettaglioRichiestaIscrizioneL68.flgPrimaIscrizione) &&
        Objects.equals(flgDichiarazioneVisitaRevisioneCollocamentoMirato, dettaglioRichiestaIscrizioneL68.flgDichiarazioneVisitaRevisioneCollocamentoMirato) &&
        Objects.equals(codDichiarazioneVisitaRevisioneInvaliditaCivile, dettaglioRichiestaIscrizioneL68.codDichiarazioneVisitaRevisioneInvaliditaCivile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(richiestaIscrizioneHeader, tipoOperazione, codTipoIscrizione, codMotivoRichiesta, codCategoriaAppartenenza, codTipoComunicazione, idCpi, idProvincia, idCpiUltimaIscrizione, dataUltimaIscrizione, dataAnnullo, idProvinciaUltimaIscrizione, descrProvinciaUltimaIscrizione, domicilioTrasferimento, gradoInvalidita, codCategoriaInvalidita, elencoQualificaNonVedenti, verbaleCollocamentoMirato, verbaleInvaliditaCivile, flgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato, flgSeguitoDaServiziTerritoriali, referenteServiziTerritoriali, flgLicenziatoInUltimoRapporto, annoReddito, importoReddito, noteReddito, annoRiferimentoFamiliariACarico, numeroFamiliariACarico, elencoFamiliariACarico, note, elencoAllegati, idOperatorePresaInCarico, dataInvio, noteRifiuto, iscrizioneLegge68, elencoCheckList, dataInserimento, dataAggiornamento, riepilogHtml, flgPrimaIscrizione, flgDichiarazioneVisitaRevisioneCollocamentoMirato, codDichiarazioneVisitaRevisioneInvaliditaCivile);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DettaglioRichiestaIscrizioneL68 {\n");
    
    sb.append("    richiestaIscrizioneHeader: ").append(toIndentedString(richiestaIscrizioneHeader)).append("\n");
    sb.append("    tipoOperazione: ").append(toIndentedString(tipoOperazione)).append("\n");
    sb.append("    codTipoIscrizione: ").append(toIndentedString(codTipoIscrizione)).append("\n");
    sb.append("    codMotivoRichiesta: ").append(toIndentedString(codMotivoRichiesta)).append("\n");
    sb.append("    codCategoriaAppartenenza: ").append(toIndentedString(codCategoriaAppartenenza)).append("\n");
    sb.append("    codTipoComunicazione: ").append(toIndentedString(codTipoComunicazione)).append("\n");
    sb.append("    idCpi: ").append(toIndentedString(idCpi)).append("\n");
    sb.append("    idProvincia: ").append(toIndentedString(idProvincia)).append("\n");
    sb.append("    idCpiUltimaIscrizione: ").append(toIndentedString(idCpiUltimaIscrizione)).append("\n");
    sb.append("    dataUltimaIscrizione: ").append(toIndentedString(dataUltimaIscrizione)).append("\n");
    sb.append("    dataAnnullo: ").append(toIndentedString(dataAnnullo)).append("\n");
    sb.append("    idProvinciaUltimaIscrizione: ").append(toIndentedString(idProvinciaUltimaIscrizione)).append("\n");
    sb.append("    descrProvinciaUltimaIscrizione: ").append(toIndentedString(descrProvinciaUltimaIscrizione)).append("\n");
    sb.append("    domicilioTrasferimento: ").append(toIndentedString(domicilioTrasferimento)).append("\n");
    sb.append("    gradoInvalidita: ").append(toIndentedString(gradoInvalidita)).append("\n");
    sb.append("    codCategoriaInvalidita: ").append(toIndentedString(codCategoriaInvalidita)).append("\n");
    sb.append("    elencoQualificaNonVedenti: ").append(toIndentedString(elencoQualificaNonVedenti)).append("\n");
    sb.append("    verbaleCollocamentoMirato: ").append(toIndentedString(verbaleCollocamentoMirato)).append("\n");
    sb.append("    verbaleInvaliditaCivile: ").append(toIndentedString(verbaleInvaliditaCivile)).append("\n");
    sb.append("    flgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato: ").append(toIndentedString(flgAutorizzazioneCpiPrenotazioneVisitaCollocamentoMirato)).append("\n");
    sb.append("    flgSeguitoDaServiziTerritoriali: ").append(toIndentedString(flgSeguitoDaServiziTerritoriali)).append("\n");
    sb.append("    referenteServiziTerritoriali: ").append(toIndentedString(referenteServiziTerritoriali)).append("\n");
    sb.append("    flgLicenziatoInUltimoRapporto: ").append(toIndentedString(flgLicenziatoInUltimoRapporto)).append("\n");
    sb.append("    annoReddito: ").append(toIndentedString(annoReddito)).append("\n");
    sb.append("    importoReddito: ").append(toIndentedString(importoReddito)).append("\n");
    sb.append("    noteReddito: ").append(toIndentedString(noteReddito)).append("\n");
    sb.append("    annoRiferimentoFamiliariACarico: ").append(toIndentedString(annoRiferimentoFamiliariACarico)).append("\n");
    sb.append("    numeroFamiliariACarico: ").append(toIndentedString(numeroFamiliariACarico)).append("\n");
    sb.append("    elencoFamiliariACarico: ").append(toIndentedString(elencoFamiliariACarico)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    elencoAllegati: ").append(toIndentedString(elencoAllegati)).append("\n");
    sb.append("    idOperatorePresaInCarico: ").append(toIndentedString(idOperatorePresaInCarico)).append("\n");
    sb.append("    dataInvio: ").append(toIndentedString(dataInvio)).append("\n");
    sb.append("    noteRifiuto: ").append(toIndentedString(noteRifiuto)).append("\n");
    sb.append("    iscrizioneLegge68: ").append(toIndentedString(iscrizioneLegge68)).append("\n");
    sb.append("    elencoCheckList: ").append(toIndentedString(elencoCheckList)).append("\n");
    sb.append("    dataInserimento: ").append(toIndentedString(dataInserimento)).append("\n");
    sb.append("    dataAggiornamento: ").append(toIndentedString(dataAggiornamento)).append("\n");
    sb.append("    riepilogHtml: ").append(toIndentedString(riepilogHtml)).append("\n");
    sb.append("    flgPrimaIscrizione: ").append(toIndentedString(flgPrimaIscrizione)).append("\n");
    sb.append("    flgDichiarazioneVisitaRevisioneCollocamentoMirato: ").append(toIndentedString(flgDichiarazioneVisitaRevisioneCollocamentoMirato)).append("\n");
    sb.append("    codDichiarazioneVisitaRevisioneInvaliditaCivile: ").append(toIndentedString(codDichiarazioneVisitaRevisioneInvaliditaCivile)).append("\n");
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

