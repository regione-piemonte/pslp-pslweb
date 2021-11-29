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

public class RispostaQuestionario   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idDomanda = null;
  private Long idRisposta = null;
  private Long idConfigurazioneQuestionario = null;
  private String rispostaLibera = null;

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
  

  @JsonProperty("id_risposta") 
 
  public Long getIdRisposta() {
    return idRisposta;
  }
  public void setIdRisposta(Long idRisposta) {
    this.idRisposta = idRisposta;
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
  

  @JsonProperty("risposta_libera") 
 
  public String getRispostaLibera() {
    return rispostaLibera;
  }
  public void setRispostaLibera(String rispostaLibera) {
    this.rispostaLibera = rispostaLibera;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RispostaQuestionario rispostaQuestionario = (RispostaQuestionario) o;
    return Objects.equals(idDomanda, rispostaQuestionario.idDomanda) &&
        Objects.equals(idRisposta, rispostaQuestionario.idRisposta) &&
        Objects.equals(idConfigurazioneQuestionario, rispostaQuestionario.idConfigurazioneQuestionario) &&
        Objects.equals(rispostaLibera, rispostaQuestionario.rispostaLibera);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idDomanda, idRisposta, idConfigurazioneQuestionario, rispostaLibera);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RispostaQuestionario {\n");
    
    sb.append("    idDomanda: ").append(toIndentedString(idDomanda)).append("\n");
    sb.append("    idRisposta: ").append(toIndentedString(idRisposta)).append("\n");
    sb.append("    idConfigurazioneQuestionario: ").append(toIndentedString(idConfigurazioneQuestionario)).append("\n");
    sb.append("    rispostaLibera: ").append(toIndentedString(rispostaLibera)).append("\n");
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

