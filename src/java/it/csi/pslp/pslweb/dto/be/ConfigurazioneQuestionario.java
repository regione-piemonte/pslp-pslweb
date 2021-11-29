/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneDomanda;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ConfigurazioneQuestionario   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idConfigurazioneQuestionario = null;
  private String nomeQuestionario = null;
  private List<ConfigurazioneDomanda> configurazioneDomanda = new ArrayList<ConfigurazioneDomanda>();

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
  

  @JsonProperty("nome_questionario") 
 
  public String getNomeQuestionario() {
    return nomeQuestionario;
  }
  public void setNomeQuestionario(String nomeQuestionario) {
    this.nomeQuestionario = nomeQuestionario;
  }

  /**
   **/
  

  @JsonProperty("configurazione_domanda") 
 
  public List<ConfigurazioneDomanda> getConfigurazioneDomanda() {
    return configurazioneDomanda;
  }
  public void setConfigurazioneDomanda(List<ConfigurazioneDomanda> configurazioneDomanda) {
    this.configurazioneDomanda = configurazioneDomanda;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneQuestionario configurazioneQuestionario = (ConfigurazioneQuestionario) o;
    return Objects.equals(idConfigurazioneQuestionario, configurazioneQuestionario.idConfigurazioneQuestionario) &&
        Objects.equals(nomeQuestionario, configurazioneQuestionario.nomeQuestionario) &&
        Objects.equals(configurazioneDomanda, configurazioneQuestionario.configurazioneDomanda);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idConfigurazioneQuestionario, nomeQuestionario, configurazioneDomanda);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneQuestionario {\n");
    
    sb.append("    idConfigurazioneQuestionario: ").append(toIndentedString(idConfigurazioneQuestionario)).append("\n");
    sb.append("    nomeQuestionario: ").append(toIndentedString(nomeQuestionario)).append("\n");
    sb.append("    configurazioneDomanda: ").append(toIndentedString(configurazioneDomanda)).append("\n");
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

