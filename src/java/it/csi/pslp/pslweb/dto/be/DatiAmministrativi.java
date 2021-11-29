/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.ListaSpeciale;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class DatiAmministrativi   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Decodifica statoOccupazionale = null;
  private Decodifica condizione = null;
  private Decodifica categoriaDlg297 = null;
  private Long anzianitaDisoccupazioneMesi = null;
  private String indiceProfiling = null;
  private Date dataEvento = null;
  private Date dataDichiarazioneDisponibilita = null;
  private List<ListaSpeciale> listeSpeciali = new ArrayList<ListaSpeciale>();
  private Boolean obbligoFormativoAssolto = null;
  private Decodifica appartenenzaCategorieProtette = null;
  private String indiceIsee = null;

  /**
   **/
  

  @JsonProperty("stato_occupazionale") 
 
  public Decodifica getStatoOccupazionale() {
    return statoOccupazionale;
  }
  public void setStatoOccupazionale(Decodifica statoOccupazionale) {
    this.statoOccupazionale = statoOccupazionale;
  }

  /**
   **/
  

  @JsonProperty("condizione") 
 
  public Decodifica getCondizione() {
    return condizione;
  }
  public void setCondizione(Decodifica condizione) {
    this.condizione = condizione;
  }

  /**
   **/
  

  @JsonProperty("categoria_dlg297") 
 
  public Decodifica getCategoriaDlg297() {
    return categoriaDlg297;
  }
  public void setCategoriaDlg297(Decodifica categoriaDlg297) {
    this.categoriaDlg297 = categoriaDlg297;
  }

  /**
   **/
  

  @JsonProperty("anzianita_disoccupazione_mesi") 
 
  public Long getAnzianitaDisoccupazioneMesi() {
    return anzianitaDisoccupazioneMesi;
  }
  public void setAnzianitaDisoccupazioneMesi(Long anzianitaDisoccupazioneMesi) {
    this.anzianitaDisoccupazioneMesi = anzianitaDisoccupazioneMesi;
  }

  /**
   **/
  

  @JsonProperty("indice_profiling") 
 
  public String getIndiceProfiling() {
    return indiceProfiling;
  }
  public void setIndiceProfiling(String indiceProfiling) {
    this.indiceProfiling = indiceProfiling;
  }

  /**
   **/
  

  @JsonProperty("data_evento") 
 
  public Date getDataEvento() {
    return dataEvento;
  }
  public void setDataEvento(Date dataEvento) {
    this.dataEvento = dataEvento;
  }

  /**
   **/
  

  @JsonProperty("data_dichiarazione_disponibilita") 
 
  public Date getDataDichiarazioneDisponibilita() {
    return dataDichiarazioneDisponibilita;
  }
  public void setDataDichiarazioneDisponibilita(Date dataDichiarazioneDisponibilita) {
    this.dataDichiarazioneDisponibilita = dataDichiarazioneDisponibilita;
  }

  /**
   **/
  

  @JsonProperty("liste_speciali") 
 
  public List<ListaSpeciale> getListeSpeciali() {
    return listeSpeciali;
  }
  public void setListeSpeciali(List<ListaSpeciale> listeSpeciali) {
    this.listeSpeciali = listeSpeciali;
  }

  /**
   **/
  

  @JsonProperty("obbligo_formativo_assolto") 
 
  public Boolean isObbligoFormativoAssolto() {
    return obbligoFormativoAssolto;
  }
  public void setObbligoFormativoAssolto(Boolean obbligoFormativoAssolto) {
    this.obbligoFormativoAssolto = obbligoFormativoAssolto;
  }

  /**
   **/
  

  @JsonProperty("appartenenza_categorie_protette") 
 
  public Decodifica getAppartenenzaCategorieProtette() {
    return appartenenzaCategorieProtette;
  }
  public void setAppartenenzaCategorieProtette(Decodifica appartenenzaCategorieProtette) {
    this.appartenenzaCategorieProtette = appartenenzaCategorieProtette;
  }

  /**
   **/
  

  @JsonProperty("indice_isee") 
 
  public String getIndiceIsee() {
    return indiceIsee;
  }
  public void setIndiceIsee(String indiceIsee) {
    this.indiceIsee = indiceIsee;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatiAmministrativi datiAmministrativi = (DatiAmministrativi) o;
    return Objects.equals(statoOccupazionale, datiAmministrativi.statoOccupazionale) &&
        Objects.equals(condizione, datiAmministrativi.condizione) &&
        Objects.equals(categoriaDlg297, datiAmministrativi.categoriaDlg297) &&
        Objects.equals(anzianitaDisoccupazioneMesi, datiAmministrativi.anzianitaDisoccupazioneMesi) &&
        Objects.equals(indiceProfiling, datiAmministrativi.indiceProfiling) &&
        Objects.equals(dataEvento, datiAmministrativi.dataEvento) &&
        Objects.equals(dataDichiarazioneDisponibilita, datiAmministrativi.dataDichiarazioneDisponibilita) &&
        Objects.equals(listeSpeciali, datiAmministrativi.listeSpeciali) &&
        Objects.equals(obbligoFormativoAssolto, datiAmministrativi.obbligoFormativoAssolto) &&
        Objects.equals(appartenenzaCategorieProtette, datiAmministrativi.appartenenzaCategorieProtette) &&
        Objects.equals(indiceIsee, datiAmministrativi.indiceIsee);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statoOccupazionale, condizione, categoriaDlg297, anzianitaDisoccupazioneMesi, indiceProfiling, dataEvento, dataDichiarazioneDisponibilita, listeSpeciali, obbligoFormativoAssolto, appartenenzaCategorieProtette, indiceIsee);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DatiAmministrativi {\n");
    
    sb.append("    statoOccupazionale: ").append(toIndentedString(statoOccupazionale)).append("\n");
    sb.append("    condizione: ").append(toIndentedString(condizione)).append("\n");
    sb.append("    categoriaDlg297: ").append(toIndentedString(categoriaDlg297)).append("\n");
    sb.append("    anzianitaDisoccupazioneMesi: ").append(toIndentedString(anzianitaDisoccupazioneMesi)).append("\n");
    sb.append("    indiceProfiling: ").append(toIndentedString(indiceProfiling)).append("\n");
    sb.append("    dataEvento: ").append(toIndentedString(dataEvento)).append("\n");
    sb.append("    dataDichiarazioneDisponibilita: ").append(toIndentedString(dataDichiarazioneDisponibilita)).append("\n");
    sb.append("    listeSpeciali: ").append(toIndentedString(listeSpeciali)).append("\n");
    sb.append("    obbligoFormativoAssolto: ").append(toIndentedString(obbligoFormativoAssolto)).append("\n");
    sb.append("    appartenenzaCategorieProtette: ").append(toIndentedString(appartenenzaCategorieProtette)).append("\n");
    sb.append("    indiceIsee: ").append(toIndentedString(indiceIsee)).append("\n");
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

