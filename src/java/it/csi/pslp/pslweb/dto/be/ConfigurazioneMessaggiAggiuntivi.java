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

public class ConfigurazioneMessaggiAggiuntivi   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codAmbito = null;
  private String messaggioAggiuntivoMailNotificaInizioDomanda = null;
  private String messaggioAggiuntivoMailNotificaTermineDomanda = null;

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
  

  @JsonProperty("messaggio_aggiuntivo_mail_notifica_inizio_domanda") 
 
  public String getMessaggioAggiuntivoMailNotificaInizioDomanda() {
    return messaggioAggiuntivoMailNotificaInizioDomanda;
  }
  public void setMessaggioAggiuntivoMailNotificaInizioDomanda(String messaggioAggiuntivoMailNotificaInizioDomanda) {
    this.messaggioAggiuntivoMailNotificaInizioDomanda = messaggioAggiuntivoMailNotificaInizioDomanda;
  }

  /**
   **/
  

  @JsonProperty("messaggio_aggiuntivo_mail_notifica_termine_domanda") 
 
  public String getMessaggioAggiuntivoMailNotificaTermineDomanda() {
    return messaggioAggiuntivoMailNotificaTermineDomanda;
  }
  public void setMessaggioAggiuntivoMailNotificaTermineDomanda(String messaggioAggiuntivoMailNotificaTermineDomanda) {
    this.messaggioAggiuntivoMailNotificaTermineDomanda = messaggioAggiuntivoMailNotificaTermineDomanda;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneMessaggiAggiuntivi configurazioneMessaggiAggiuntivi = (ConfigurazioneMessaggiAggiuntivi) o;
    return Objects.equals(codAmbito, configurazioneMessaggiAggiuntivi.codAmbito) &&
        Objects.equals(messaggioAggiuntivoMailNotificaInizioDomanda, configurazioneMessaggiAggiuntivi.messaggioAggiuntivoMailNotificaInizioDomanda) &&
        Objects.equals(messaggioAggiuntivoMailNotificaTermineDomanda, configurazioneMessaggiAggiuntivi.messaggioAggiuntivoMailNotificaTermineDomanda);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codAmbito, messaggioAggiuntivoMailNotificaInizioDomanda, messaggioAggiuntivoMailNotificaTermineDomanda);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneMessaggiAggiuntivi {\n");
    
    sb.append("    codAmbito: ").append(toIndentedString(codAmbito)).append("\n");
    sb.append("    messaggioAggiuntivoMailNotificaInizioDomanda: ").append(toIndentedString(messaggioAggiuntivoMailNotificaInizioDomanda)).append("\n");
    sb.append("    messaggioAggiuntivoMailNotificaTermineDomanda: ").append(toIndentedString(messaggioAggiuntivoMailNotificaTermineDomanda)).append("\n");
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

