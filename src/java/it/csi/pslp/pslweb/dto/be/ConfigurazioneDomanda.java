/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneRisposta;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ConfigurazioneDomanda   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idConfigurazioneQuestionario = null;
  private Long idDomanda = null;
  private String tipoDomanda = null;
  private String domanda = null;
  private Long posizione = null;
  private List<ConfigurazioneRisposta> risposte = new ArrayList<ConfigurazioneRisposta>();

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
  

  @JsonProperty("id_domanda") 
 
  public Long getIdDomanda() {
    return idDomanda;
  }
  public void setIdDomanda(Long idDomanda) {
    this.idDomanda = idDomanda;
  }

  /**
   **/
  

  @JsonProperty("tipo_domanda") 
 
  public String getTipoDomanda() {
    return tipoDomanda;
  }
  public void setTipoDomanda(String tipoDomanda) {
    this.tipoDomanda = tipoDomanda;
  }

  /**
   **/
  

  @JsonProperty("domanda") 
 
  public String getDomanda() {
    return domanda;
  }
  public void setDomanda(String domanda) {
    this.domanda = domanda;
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
  

  @JsonProperty("risposte") 
 
  public List<ConfigurazioneRisposta> getRisposte() {
    return risposte;
  }
  public void setRisposte(List<ConfigurazioneRisposta> risposte) {
    this.risposte = risposte;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneDomanda configurazioneDomanda = (ConfigurazioneDomanda) o;
    return Objects.equals(idConfigurazioneQuestionario, configurazioneDomanda.idConfigurazioneQuestionario) &&
        Objects.equals(idDomanda, configurazioneDomanda.idDomanda) &&
        Objects.equals(tipoDomanda, configurazioneDomanda.tipoDomanda) &&
        Objects.equals(domanda, configurazioneDomanda.domanda) &&
        Objects.equals(posizione, configurazioneDomanda.posizione) &&
        Objects.equals(risposte, configurazioneDomanda.risposte);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idConfigurazioneQuestionario, idDomanda, tipoDomanda, domanda, posizione, risposte);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneDomanda {\n");
    
    sb.append("    idConfigurazioneQuestionario: ").append(toIndentedString(idConfigurazioneQuestionario)).append("\n");
    sb.append("    idDomanda: ").append(toIndentedString(idDomanda)).append("\n");
    sb.append("    tipoDomanda: ").append(toIndentedString(tipoDomanda)).append("\n");
    sb.append("    domanda: ").append(toIndentedString(domanda)).append("\n");
    sb.append("    posizione: ").append(toIndentedString(posizione)).append("\n");
    sb.append("    risposte: ").append(toIndentedString(risposte)).append("\n");
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

