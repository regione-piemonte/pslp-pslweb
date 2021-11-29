/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.ConoscenzaInformatica;
import it.csi.pslp.pslweb.dto.be.DatiAmministrativi;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.Esito;
import it.csi.pslp.pslweb.dto.be.EsperienzaLavoro;
import it.csi.pslp.pslweb.dto.be.FormazioneProfessionale;
import it.csi.pslp.pslweb.dto.be.Indirizzo;
import it.csi.pslp.pslweb.dto.be.Lingua;
import it.csi.pslp.pslweb.dto.be.Luogo;
import it.csi.pslp.pslweb.dto.be.PermessoDiSoggiorno;
import it.csi.pslp.pslweb.dto.be.PoliticaAttiva;
import it.csi.pslp.pslweb.dto.be.Recapito;
import it.csi.pslp.pslweb.dto.be.TitoloDiStudio;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class SchedaAnagraficoProfessionale   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String identificativoSap = null;
  private Long idSilLavAnagrafica = null;
  private Long idInterscambioSap = null;
  private Date dataUltimoAggiornamento = null;
  private Decodifica enteTitolare = null;
  private String codiceFiscale = null;
  private String nome = null;
  private String cognome = null;
  private String sesso = null;
  private Date dataDiNascita = null;
  private Luogo luogoDiNascita = null;
  private String cittadinanza = null;
  private String codiceMinisterialeCittadinanza = null;
  private Indirizzo residenza = null;
  private Indirizzo domicilio = null;
  private Recapito recapito = null;
  private PermessoDiSoggiorno permessoDiSoggiorno = null;
  private DatiAmministrativi datiAmministrativi = null;
  private List<EsperienzaLavoro> esperienzeDiLavoro = new ArrayList<EsperienzaLavoro>();
  private List<TitoloDiStudio> titoliDiStudio = new ArrayList<TitoloDiStudio>();
  private List<FormazioneProfessionale> formazioneProfessionaleCorsi = new ArrayList<FormazioneProfessionale>();
  private List<Lingua> lingueStraniere = new ArrayList<Lingua>();
  private List<ConoscenzaInformatica> conoscenzeInformatiche = new ArrayList<ConoscenzaInformatica>();
  private List<Decodifica> albi = new ArrayList<Decodifica>();
  private List<Decodifica> patenti = new ArrayList<Decodifica>();
  private List<Decodifica> patentini = new ArrayList<Decodifica>();
  private List<PoliticaAttiva> politicheAttive = new ArrayList<PoliticaAttiva>();
  private Esito esito = null;

  /**
   **/
  

  @JsonProperty("identificativo_sap") 
 
  public String getIdentificativoSap() {
    return identificativoSap;
  }
  public void setIdentificativoSap(String identificativoSap) {
    this.identificativoSap = identificativoSap;
  }

  /**
   * id lavoratore su SILP
   **/
  

  @JsonProperty("id_sil_lav_anagrafica") 
 
  public Long getIdSilLavAnagrafica() {
    return idSilLavAnagrafica;
  }
  public void setIdSilLavAnagrafica(Long idSilLavAnagrafica) {
    this.idSilLavAnagrafica = idSilLavAnagrafica;
  }

  /**
   * id del record di interscambio che traccia una sap ricevuta e importata da silp
   **/
  

  @JsonProperty("id_interscambio_sap") 
 
  public Long getIdInterscambioSap() {
    return idInterscambioSap;
  }
  public void setIdInterscambioSap(Long idInterscambioSap) {
    this.idInterscambioSap = idInterscambioSap;
  }

  /**
   * data in cui e&#39; avvenuto l&#39;ultimo aggiornamento di un dato della sap
   **/
  

  @JsonProperty("data_ultimo_aggiornamento") 
 
  public Date getDataUltimoAggiornamento() {
    return dataUltimoAggiornamento;
  }
  public void setDataUltimoAggiornamento(Date dataUltimoAggiornamento) {
    this.dataUltimoAggiornamento = dataUltimoAggiornamento;
  }

  /**
   **/
  

  @JsonProperty("ente_titolare") 
 
  public Decodifica getEnteTitolare() {
    return enteTitolare;
  }
  public void setEnteTitolare(Decodifica enteTitolare) {
    this.enteTitolare = enteTitolare;
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

  /**
   **/
  

  @JsonProperty("cittadinanza") 
 
  public String getCittadinanza() {
    return cittadinanza;
  }
  public void setCittadinanza(String cittadinanza) {
    this.cittadinanza = cittadinanza;
  }

  /**
   **/
  

  @JsonProperty("codice_ministeriale_cittadinanza") 
 
  public String getCodiceMinisterialeCittadinanza() {
    return codiceMinisterialeCittadinanza;
  }
  public void setCodiceMinisterialeCittadinanza(String codiceMinisterialeCittadinanza) {
    this.codiceMinisterialeCittadinanza = codiceMinisterialeCittadinanza;
  }

  /**
   **/
  

  @JsonProperty("residenza") 
 
  public Indirizzo getResidenza() {
    return residenza;
  }
  public void setResidenza(Indirizzo residenza) {
    this.residenza = residenza;
  }

  /**
   **/
  

  @JsonProperty("domicilio") 
 
  public Indirizzo getDomicilio() {
    return domicilio;
  }
  public void setDomicilio(Indirizzo domicilio) {
    this.domicilio = domicilio;
  }

  /**
   **/
  

  @JsonProperty("recapito") 
 
  public Recapito getRecapito() {
    return recapito;
  }
  public void setRecapito(Recapito recapito) {
    this.recapito = recapito;
  }

  /**
   **/
  

  @JsonProperty("permessoDiSoggiorno") 
 
  public PermessoDiSoggiorno getPermessoDiSoggiorno() {
    return permessoDiSoggiorno;
  }
  public void setPermessoDiSoggiorno(PermessoDiSoggiorno permessoDiSoggiorno) {
    this.permessoDiSoggiorno = permessoDiSoggiorno;
  }

  /**
   **/
  

  @JsonProperty("datiAmministrativi") 
 
  public DatiAmministrativi getDatiAmministrativi() {
    return datiAmministrativi;
  }
  public void setDatiAmministrativi(DatiAmministrativi datiAmministrativi) {
    this.datiAmministrativi = datiAmministrativi;
  }

  /**
   **/
  

  @JsonProperty("esperienze_di_lavoro") 
 
  public List<EsperienzaLavoro> getEsperienzeDiLavoro() {
    return esperienzeDiLavoro;
  }
  public void setEsperienzeDiLavoro(List<EsperienzaLavoro> esperienzeDiLavoro) {
    this.esperienzeDiLavoro = esperienzeDiLavoro;
  }

  /**
   **/
  

  @JsonProperty("titoli_di_studio") 
 
  public List<TitoloDiStudio> getTitoliDiStudio() {
    return titoliDiStudio;
  }
  public void setTitoliDiStudio(List<TitoloDiStudio> titoliDiStudio) {
    this.titoliDiStudio = titoliDiStudio;
  }

  /**
   **/
  

  @JsonProperty("formazione_professionale_corsi") 
 
  public List<FormazioneProfessionale> getFormazioneProfessionaleCorsi() {
    return formazioneProfessionaleCorsi;
  }
  public void setFormazioneProfessionaleCorsi(List<FormazioneProfessionale> formazioneProfessionaleCorsi) {
    this.formazioneProfessionaleCorsi = formazioneProfessionaleCorsi;
  }

  /**
   **/
  

  @JsonProperty("lingue_straniere") 
 
  public List<Lingua> getLingueStraniere() {
    return lingueStraniere;
  }
  public void setLingueStraniere(List<Lingua> lingueStraniere) {
    this.lingueStraniere = lingueStraniere;
  }

  /**
   **/
  

  @JsonProperty("conoscenze_informatiche") 
 
  public List<ConoscenzaInformatica> getConoscenzeInformatiche() {
    return conoscenzeInformatiche;
  }
  public void setConoscenzeInformatiche(List<ConoscenzaInformatica> conoscenzeInformatiche) {
    this.conoscenzeInformatiche = conoscenzeInformatiche;
  }

  /**
   **/
  

  @JsonProperty("albi") 
 
  public List<Decodifica> getAlbi() {
    return albi;
  }
  public void setAlbi(List<Decodifica> albi) {
    this.albi = albi;
  }

  /**
   **/
  

  @JsonProperty("patenti") 
 
  public List<Decodifica> getPatenti() {
    return patenti;
  }
  public void setPatenti(List<Decodifica> patenti) {
    this.patenti = patenti;
  }

  /**
   **/
  

  @JsonProperty("patentini") 
 
  public List<Decodifica> getPatentini() {
    return patentini;
  }
  public void setPatentini(List<Decodifica> patentini) {
    this.patentini = patentini;
  }

  /**
   **/
  

  @JsonProperty("politiche_attive") 
 
  public List<PoliticaAttiva> getPoliticheAttive() {
    return politicheAttive;
  }
  public void setPoliticheAttive(List<PoliticaAttiva> politicheAttive) {
    this.politicheAttive = politicheAttive;
  }

  /**
   * esito valorizzato in caso di problemi logici nel caricamento sap, esempio codice sap duplicato
   **/
  

  @JsonProperty("esito") 
 
  public Esito getEsito() {
    return esito;
  }
  public void setEsito(Esito esito) {
    this.esito = esito;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SchedaAnagraficoProfessionale schedaAnagraficoProfessionale = (SchedaAnagraficoProfessionale) o;
    return Objects.equals(identificativoSap, schedaAnagraficoProfessionale.identificativoSap) &&
        Objects.equals(idSilLavAnagrafica, schedaAnagraficoProfessionale.idSilLavAnagrafica) &&
        Objects.equals(idInterscambioSap, schedaAnagraficoProfessionale.idInterscambioSap) &&
        Objects.equals(dataUltimoAggiornamento, schedaAnagraficoProfessionale.dataUltimoAggiornamento) &&
        Objects.equals(enteTitolare, schedaAnagraficoProfessionale.enteTitolare) &&
        Objects.equals(codiceFiscale, schedaAnagraficoProfessionale.codiceFiscale) &&
        Objects.equals(nome, schedaAnagraficoProfessionale.nome) &&
        Objects.equals(cognome, schedaAnagraficoProfessionale.cognome) &&
        Objects.equals(sesso, schedaAnagraficoProfessionale.sesso) &&
        Objects.equals(dataDiNascita, schedaAnagraficoProfessionale.dataDiNascita) &&
        Objects.equals(luogoDiNascita, schedaAnagraficoProfessionale.luogoDiNascita) &&
        Objects.equals(cittadinanza, schedaAnagraficoProfessionale.cittadinanza) &&
        Objects.equals(codiceMinisterialeCittadinanza, schedaAnagraficoProfessionale.codiceMinisterialeCittadinanza) &&
        Objects.equals(residenza, schedaAnagraficoProfessionale.residenza) &&
        Objects.equals(domicilio, schedaAnagraficoProfessionale.domicilio) &&
        Objects.equals(recapito, schedaAnagraficoProfessionale.recapito) &&
        Objects.equals(permessoDiSoggiorno, schedaAnagraficoProfessionale.permessoDiSoggiorno) &&
        Objects.equals(datiAmministrativi, schedaAnagraficoProfessionale.datiAmministrativi) &&
        Objects.equals(esperienzeDiLavoro, schedaAnagraficoProfessionale.esperienzeDiLavoro) &&
        Objects.equals(titoliDiStudio, schedaAnagraficoProfessionale.titoliDiStudio) &&
        Objects.equals(formazioneProfessionaleCorsi, schedaAnagraficoProfessionale.formazioneProfessionaleCorsi) &&
        Objects.equals(lingueStraniere, schedaAnagraficoProfessionale.lingueStraniere) &&
        Objects.equals(conoscenzeInformatiche, schedaAnagraficoProfessionale.conoscenzeInformatiche) &&
        Objects.equals(albi, schedaAnagraficoProfessionale.albi) &&
        Objects.equals(patenti, schedaAnagraficoProfessionale.patenti) &&
        Objects.equals(patentini, schedaAnagraficoProfessionale.patentini) &&
        Objects.equals(politicheAttive, schedaAnagraficoProfessionale.politicheAttive) &&
        Objects.equals(esito, schedaAnagraficoProfessionale.esito);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identificativoSap, idSilLavAnagrafica, idInterscambioSap, dataUltimoAggiornamento, enteTitolare, codiceFiscale, nome, cognome, sesso, dataDiNascita, luogoDiNascita, cittadinanza, codiceMinisterialeCittadinanza, residenza, domicilio, recapito, permessoDiSoggiorno, datiAmministrativi, esperienzeDiLavoro, titoliDiStudio, formazioneProfessionaleCorsi, lingueStraniere, conoscenzeInformatiche, albi, patenti, patentini, politicheAttive, esito);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SchedaAnagraficoProfessionale {\n");
    
    sb.append("    identificativoSap: ").append(toIndentedString(identificativoSap)).append("\n");
    sb.append("    idSilLavAnagrafica: ").append(toIndentedString(idSilLavAnagrafica)).append("\n");
    sb.append("    idInterscambioSap: ").append(toIndentedString(idInterscambioSap)).append("\n");
    sb.append("    dataUltimoAggiornamento: ").append(toIndentedString(dataUltimoAggiornamento)).append("\n");
    sb.append("    enteTitolare: ").append(toIndentedString(enteTitolare)).append("\n");
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
    sb.append("    sesso: ").append(toIndentedString(sesso)).append("\n");
    sb.append("    dataDiNascita: ").append(toIndentedString(dataDiNascita)).append("\n");
    sb.append("    luogoDiNascita: ").append(toIndentedString(luogoDiNascita)).append("\n");
    sb.append("    cittadinanza: ").append(toIndentedString(cittadinanza)).append("\n");
    sb.append("    codiceMinisterialeCittadinanza: ").append(toIndentedString(codiceMinisterialeCittadinanza)).append("\n");
    sb.append("    residenza: ").append(toIndentedString(residenza)).append("\n");
    sb.append("    domicilio: ").append(toIndentedString(domicilio)).append("\n");
    sb.append("    recapito: ").append(toIndentedString(recapito)).append("\n");
    sb.append("    permessoDiSoggiorno: ").append(toIndentedString(permessoDiSoggiorno)).append("\n");
    sb.append("    datiAmministrativi: ").append(toIndentedString(datiAmministrativi)).append("\n");
    sb.append("    esperienzeDiLavoro: ").append(toIndentedString(esperienzeDiLavoro)).append("\n");
    sb.append("    titoliDiStudio: ").append(toIndentedString(titoliDiStudio)).append("\n");
    sb.append("    formazioneProfessionaleCorsi: ").append(toIndentedString(formazioneProfessionaleCorsi)).append("\n");
    sb.append("    lingueStraniere: ").append(toIndentedString(lingueStraniere)).append("\n");
    sb.append("    conoscenzeInformatiche: ").append(toIndentedString(conoscenzeInformatiche)).append("\n");
    sb.append("    albi: ").append(toIndentedString(albi)).append("\n");
    sb.append("    patenti: ").append(toIndentedString(patenti)).append("\n");
    sb.append("    patentini: ").append(toIndentedString(patentini)).append("\n");
    sb.append("    politicheAttive: ").append(toIndentedString(politicheAttive)).append("\n");
    sb.append("    esito: ").append(toIndentedString(esito)).append("\n");
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

