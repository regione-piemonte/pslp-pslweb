/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.DettaglioCompletoDichiarazioneFamiliariACarico;
import it.csi.pslp.pslweb.dto.be.IscrizioneCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.RedditoCollocamentoMirato;
import it.csi.pslp.pslweb.dto.be.RichiestaIscrizioneL68Header;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class EsitoRiepilogoCollocamentoMirato   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String messaggioErrore = null;
  private IscrizioneCollocamentoMirato iscrizioneDisabili = null;
  private IscrizioneCollocamentoMirato iscrizioneAltreCategorie = null;
  private List<RedditoCollocamentoMirato> redditi = new ArrayList<RedditoCollocamentoMirato>();
  private List<DettaglioCompletoDichiarazioneFamiliariACarico> dettaglioCompletoDichiarazioneFamiliariACarico = new ArrayList<DettaglioCompletoDichiarazioneFamiliariACarico>();
  private List<RichiestaIscrizioneL68Header> elencoRichieste = new ArrayList<RichiestaIscrizioneL68Header>();

  /**
   **/
  

  @JsonProperty("messaggio_errore") 
 
  public String getMessaggioErrore() {
    return messaggioErrore;
  }
  public void setMessaggioErrore(String messaggioErrore) {
    this.messaggioErrore = messaggioErrore;
  }

  /**
   **/
  

  @JsonProperty("iscrizioneDisabili") 
 
  public IscrizioneCollocamentoMirato getIscrizioneDisabili() {
    return iscrizioneDisabili;
  }
  public void setIscrizioneDisabili(IscrizioneCollocamentoMirato iscrizioneDisabili) {
    this.iscrizioneDisabili = iscrizioneDisabili;
  }

  /**
   **/
  

  @JsonProperty("iscrizioneAltreCategorie") 
 
  public IscrizioneCollocamentoMirato getIscrizioneAltreCategorie() {
    return iscrizioneAltreCategorie;
  }
  public void setIscrizioneAltreCategorie(IscrizioneCollocamentoMirato iscrizioneAltreCategorie) {
    this.iscrizioneAltreCategorie = iscrizioneAltreCategorie;
  }

  /**
   **/
  

  @JsonProperty("redditi") 
 
  public List<RedditoCollocamentoMirato> getRedditi() {
    return redditi;
  }
  public void setRedditi(List<RedditoCollocamentoMirato> redditi) {
    this.redditi = redditi;
  }

  /**
   **/
  

  @JsonProperty("dettaglioCompletoDichiarazioneFamiliariACarico") 
 
  public List<DettaglioCompletoDichiarazioneFamiliariACarico> getDettaglioCompletoDichiarazioneFamiliariACarico() {
    return dettaglioCompletoDichiarazioneFamiliariACarico;
  }
  public void setDettaglioCompletoDichiarazioneFamiliariACarico(List<DettaglioCompletoDichiarazioneFamiliariACarico> dettaglioCompletoDichiarazioneFamiliariACarico) {
    this.dettaglioCompletoDichiarazioneFamiliariACarico = dettaglioCompletoDichiarazioneFamiliariACarico;
  }

  /**
   **/
  

  @JsonProperty("elencoRichieste") 
 
  public List<RichiestaIscrizioneL68Header> getElencoRichieste() {
    return elencoRichieste;
  }
  public void setElencoRichieste(List<RichiestaIscrizioneL68Header> elencoRichieste) {
    this.elencoRichieste = elencoRichieste;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoRiepilogoCollocamentoMirato esitoRiepilogoCollocamentoMirato = (EsitoRiepilogoCollocamentoMirato) o;
    return Objects.equals(messaggioErrore, esitoRiepilogoCollocamentoMirato.messaggioErrore) &&
        Objects.equals(iscrizioneDisabili, esitoRiepilogoCollocamentoMirato.iscrizioneDisabili) &&
        Objects.equals(iscrizioneAltreCategorie, esitoRiepilogoCollocamentoMirato.iscrizioneAltreCategorie) &&
        Objects.equals(redditi, esitoRiepilogoCollocamentoMirato.redditi) &&
        Objects.equals(dettaglioCompletoDichiarazioneFamiliariACarico, esitoRiepilogoCollocamentoMirato.dettaglioCompletoDichiarazioneFamiliariACarico) &&
        Objects.equals(elencoRichieste, esitoRiepilogoCollocamentoMirato.elencoRichieste);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messaggioErrore, iscrizioneDisabili, iscrizioneAltreCategorie, redditi, dettaglioCompletoDichiarazioneFamiliariACarico, elencoRichieste);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoRiepilogoCollocamentoMirato {\n");
    
    sb.append("    messaggioErrore: ").append(toIndentedString(messaggioErrore)).append("\n");
    sb.append("    iscrizioneDisabili: ").append(toIndentedString(iscrizioneDisabili)).append("\n");
    sb.append("    iscrizioneAltreCategorie: ").append(toIndentedString(iscrizioneAltreCategorie)).append("\n");
    sb.append("    redditi: ").append(toIndentedString(redditi)).append("\n");
    sb.append("    dettaglioCompletoDichiarazioneFamiliariACarico: ").append(toIndentedString(dettaglioCompletoDichiarazioneFamiliariACarico)).append("\n");
    sb.append("    elencoRichieste: ").append(toIndentedString(elencoRichieste)).append("\n");
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

