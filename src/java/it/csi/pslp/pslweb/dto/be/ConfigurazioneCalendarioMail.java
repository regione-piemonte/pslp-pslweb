/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ConfigurazioneCalendarioMail   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Boolean flagInvioMailConfermaPrenotazione = null;
  private Integer oreInvioMailPromemoriaPrenotazione = null;
  private String oggettoMail = null;
  private String messaggioAggiuntivoMailConferma = null;
  private String messaggioAggiuntivoMailPromemoria = null;
  private String messaggioAggiuntivoMailAnnullamento = null;
  private String messaggioAggiuntivoMailNonPresentato = null;

  /**
   **/
  

  @JsonProperty("flag_invio_mail_conferma_prenotazione") 
 
  public Boolean isFlagInvioMailConfermaPrenotazione() {
    return flagInvioMailConfermaPrenotazione;
  }
  public void setFlagInvioMailConfermaPrenotazione(Boolean flagInvioMailConfermaPrenotazione) {
    this.flagInvioMailConfermaPrenotazione = flagInvioMailConfermaPrenotazione;
  }

  /**
   **/
  

  @JsonProperty("ore_invio_mail_promemoria_prenotazione") 
 
  public Integer getOreInvioMailPromemoriaPrenotazione() {
    return oreInvioMailPromemoriaPrenotazione;
  }
  public void setOreInvioMailPromemoriaPrenotazione(Integer oreInvioMailPromemoriaPrenotazione) {
    this.oreInvioMailPromemoriaPrenotazione = oreInvioMailPromemoriaPrenotazione;
  }

  /**
   **/
  

  @JsonProperty("oggetto_mail") 
 
  public String getOggettoMail() {
    return oggettoMail;
  }
  public void setOggettoMail(String oggettoMail) {
    this.oggettoMail = oggettoMail;
  }

  /**
   **/
  

  @JsonProperty("messaggio_aggiuntivo_mail_conferma") 
 
  public String getMessaggioAggiuntivoMailConferma() {
    return messaggioAggiuntivoMailConferma;
  }
  public void setMessaggioAggiuntivoMailConferma(String messaggioAggiuntivoMailConferma) {
    this.messaggioAggiuntivoMailConferma = messaggioAggiuntivoMailConferma;
  }

  /**
   **/
  

  @JsonProperty("messaggio_aggiuntivo_mail_promemoria") 
 
  public String getMessaggioAggiuntivoMailPromemoria() {
    return messaggioAggiuntivoMailPromemoria;
  }
  public void setMessaggioAggiuntivoMailPromemoria(String messaggioAggiuntivoMailPromemoria) {
    this.messaggioAggiuntivoMailPromemoria = messaggioAggiuntivoMailPromemoria;
  }

  /**
   **/
  

  @JsonProperty("messaggio_aggiuntivo_mail_annullamento") 
 
  public String getMessaggioAggiuntivoMailAnnullamento() {
    return messaggioAggiuntivoMailAnnullamento;
  }
  public void setMessaggioAggiuntivoMailAnnullamento(String messaggioAggiuntivoMailAnnullamento) {
    this.messaggioAggiuntivoMailAnnullamento = messaggioAggiuntivoMailAnnullamento;
  }

  /**
   **/
  

  @JsonProperty("messaggio_aggiuntivo_mail_non_presentato") 
 
  public String getMessaggioAggiuntivoMailNonPresentato() {
    return messaggioAggiuntivoMailNonPresentato;
  }
  public void setMessaggioAggiuntivoMailNonPresentato(String messaggioAggiuntivoMailNonPresentato) {
    this.messaggioAggiuntivoMailNonPresentato = messaggioAggiuntivoMailNonPresentato;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneCalendarioMail configurazioneCalendarioMail = (ConfigurazioneCalendarioMail) o;
    return Objects.equals(flagInvioMailConfermaPrenotazione, configurazioneCalendarioMail.flagInvioMailConfermaPrenotazione) &&
        Objects.equals(oreInvioMailPromemoriaPrenotazione, configurazioneCalendarioMail.oreInvioMailPromemoriaPrenotazione) &&
        Objects.equals(oggettoMail, configurazioneCalendarioMail.oggettoMail) &&
        Objects.equals(messaggioAggiuntivoMailConferma, configurazioneCalendarioMail.messaggioAggiuntivoMailConferma) &&
        Objects.equals(messaggioAggiuntivoMailPromemoria, configurazioneCalendarioMail.messaggioAggiuntivoMailPromemoria) &&
        Objects.equals(messaggioAggiuntivoMailAnnullamento, configurazioneCalendarioMail.messaggioAggiuntivoMailAnnullamento) &&
        Objects.equals(messaggioAggiuntivoMailNonPresentato, configurazioneCalendarioMail.messaggioAggiuntivoMailNonPresentato);
  }

  @Override
  public int hashCode() {
    return Objects.hash(flagInvioMailConfermaPrenotazione, oreInvioMailPromemoriaPrenotazione, oggettoMail, messaggioAggiuntivoMailConferma, messaggioAggiuntivoMailPromemoria, messaggioAggiuntivoMailAnnullamento, messaggioAggiuntivoMailNonPresentato);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneCalendarioMail {\n");
    
    sb.append("    flagInvioMailConfermaPrenotazione: ").append(toIndentedString(flagInvioMailConfermaPrenotazione)).append("\n");
    sb.append("    oreInvioMailPromemoriaPrenotazione: ").append(toIndentedString(oreInvioMailPromemoriaPrenotazione)).append("\n");
    sb.append("    oggettoMail: ").append(toIndentedString(oggettoMail)).append("\n");
    sb.append("    messaggioAggiuntivoMailConferma: ").append(toIndentedString(messaggioAggiuntivoMailConferma)).append("\n");
    sb.append("    messaggioAggiuntivoMailPromemoria: ").append(toIndentedString(messaggioAggiuntivoMailPromemoria)).append("\n");
    sb.append("    messaggioAggiuntivoMailAnnullamento: ").append(toIndentedString(messaggioAggiuntivoMailAnnullamento)).append("\n");
    sb.append("    messaggioAggiuntivoMailNonPresentato: ").append(toIndentedString(messaggioAggiuntivoMailNonPresentato)).append("\n");
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

