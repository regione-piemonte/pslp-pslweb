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

public class ConfigurazioneRisposta   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idRisposta = null;
  private Long idDomanda = null;
  private Long posizione = null;
  private Long idDomandaSuccessiva = null;
  private Long idConfigurazioneQuestionario = null;
  private String risposta = null;

  /**
   **/
  

  @JsonProperty("id_risposta") 
 
  public Long getIdRisposta() {
    return idRisposta;
  }
  public void setIdRisposta(Long idRisposta) {
    this.idRisposta = idRisposta;
  }

  /**
   **/
  

  @JsonProperty("id_domanda") 
 
  public Long getIdDomanda() {
    return idDomanda;
  }
  public void setIdDomanda(Long idDomanda) {
    this.idDomanda = idDomanda;
  }

  /**
   **/
  

  @JsonProperty("posizione") 
 
  public Long getPosizione() {
    return posizione;
  }
  public void setPosizione(Long posizione) {
    this.posizione = posizione;
  }

  /**
   **/
  

  @JsonProperty("id_domanda_successiva") 
 
  public Long getIdDomandaSuccessiva() {
    return idDomandaSuccessiva;
  }
  public void setIdDomandaSuccessiva(Long idDomandaSuccessiva) {
    this.idDomandaSuccessiva = idDomandaSuccessiva;
  }

  /**
   **/
  

  @JsonProperty("id_configurazione_questionario") 
 
  public Long getIdConfigurazioneQuestionario() {
    return idConfigurazioneQuestionario;
  }
  public void setIdConfigurazioneQuestionario(Long idConfigurazioneQuestionario) {
    this.idConfigurazioneQuestionario = idConfigurazioneQuestionario;
  }

  /**
   **/
  

  @JsonProperty("risposta") 
 
  public String getRisposta() {
    return risposta;
  }
  public void setRisposta(String risposta) {
    this.risposta = risposta;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneRisposta configurazioneRisposta = (ConfigurazioneRisposta) o;
    return Objects.equals(idRisposta, configurazioneRisposta.idRisposta) &&
        Objects.equals(idDomanda, configurazioneRisposta.idDomanda) &&
        Objects.equals(posizione, configurazioneRisposta.posizione) &&
        Objects.equals(idDomandaSuccessiva, configurazioneRisposta.idDomandaSuccessiva) &&
        Objects.equals(idConfigurazioneQuestionario, configurazioneRisposta.idConfigurazioneQuestionario) &&
        Objects.equals(risposta, configurazioneRisposta.risposta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idRisposta, idDomanda, posizione, idDomandaSuccessiva, idConfigurazioneQuestionario, risposta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneRisposta {\n");
    
    sb.append("    idRisposta: ").append(toIndentedString(idRisposta)).append("\n");
    sb.append("    idDomanda: ").append(toIndentedString(idDomanda)).append("\n");
    sb.append("    posizione: ").append(toIndentedString(posizione)).append("\n");
    sb.append("    idDomandaSuccessiva: ").append(toIndentedString(idDomandaSuccessiva)).append("\n");
    sb.append("    idConfigurazioneQuestionario: ").append(toIndentedString(idConfigurazioneQuestionario)).append("\n");
    sb.append("    risposta: ").append(toIndentedString(risposta)).append("\n");
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

