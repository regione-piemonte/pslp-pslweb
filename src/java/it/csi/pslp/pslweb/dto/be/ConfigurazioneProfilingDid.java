/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.ConfigurazioneQuestionario;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.GradoStudio;
import it.csi.pslp.pslweb.dto.be.TitoloStudio;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ConfigurazioneProfilingDid   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private List<Decodifica> elencoCittadinanza = new ArrayList<Decodifica>();
  private List<Decodifica> elencoPosizioneProfessionale = new ArrayList<Decodifica>();
  private List<Decodifica> elencoIscrizioneCorso = new ArrayList<Decodifica>();
  private List<Decodifica> elencoPresenzaInItalia = new ArrayList<Decodifica>();
  private List<Decodifica> elencoCondizioniOccupazionali = new ArrayList<Decodifica>();
  private List<TitoloStudio> elencoTitoloStudioProfiling = new ArrayList<TitoloStudio>();
  private List<GradoStudio> elencoGradoStudio = new ArrayList<GradoStudio>();
  private ConfigurazioneQuestionario configurazioneQuestionario = null;

  /**
   **/
  

  @JsonProperty("elencoCittadinanza") 
 
  public List<Decodifica> getElencoCittadinanza() {
    return elencoCittadinanza;
  }
  public void setElencoCittadinanza(List<Decodifica> elencoCittadinanza) {
    this.elencoCittadinanza = elencoCittadinanza;
  }

  /**
   **/
  

  @JsonProperty("elencoPosizioneProfessionale") 
 
  public List<Decodifica> getElencoPosizioneProfessionale() {
    return elencoPosizioneProfessionale;
  }
  public void setElencoPosizioneProfessionale(List<Decodifica> elencoPosizioneProfessionale) {
    this.elencoPosizioneProfessionale = elencoPosizioneProfessionale;
  }

  /**
   **/
  

  @JsonProperty("elencoIscrizioneCorso") 
 
  public List<Decodifica> getElencoIscrizioneCorso() {
    return elencoIscrizioneCorso;
  }
  public void setElencoIscrizioneCorso(List<Decodifica> elencoIscrizioneCorso) {
    this.elencoIscrizioneCorso = elencoIscrizioneCorso;
  }

  /**
   **/
  

  @JsonProperty("elencoPresenzaInItalia") 
 
  public List<Decodifica> getElencoPresenzaInItalia() {
    return elencoPresenzaInItalia;
  }
  public void setElencoPresenzaInItalia(List<Decodifica> elencoPresenzaInItalia) {
    this.elencoPresenzaInItalia = elencoPresenzaInItalia;
  }

  /**
   **/
  

  @JsonProperty("elencoCondizioniOccupazionali") 
 
  public List<Decodifica> getElencoCondizioniOccupazionali() {
    return elencoCondizioniOccupazionali;
  }
  public void setElencoCondizioniOccupazionali(List<Decodifica> elencoCondizioniOccupazionali) {
    this.elencoCondizioniOccupazionali = elencoCondizioniOccupazionali;
  }

  /**
   **/
  

  @JsonProperty("elencoTitoloStudioProfiling") 
 
  public List<TitoloStudio> getElencoTitoloStudioProfiling() {
    return elencoTitoloStudioProfiling;
  }
  public void setElencoTitoloStudioProfiling(List<TitoloStudio> elencoTitoloStudioProfiling) {
    this.elencoTitoloStudioProfiling = elencoTitoloStudioProfiling;
  }

  /**
   **/
  

  @JsonProperty("elencoGradoStudio") 
 
  public List<GradoStudio> getElencoGradoStudio() {
    return elencoGradoStudio;
  }
  public void setElencoGradoStudio(List<GradoStudio> elencoGradoStudio) {
    this.elencoGradoStudio = elencoGradoStudio;
  }

  /**
   **/
  

  @JsonProperty("configurazione_questionario") 
 
  public ConfigurazioneQuestionario getConfigurazioneQuestionario() {
    return configurazioneQuestionario;
  }
  public void setConfigurazioneQuestionario(ConfigurazioneQuestionario configurazioneQuestionario) {
    this.configurazioneQuestionario = configurazioneQuestionario;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneProfilingDid configurazioneProfilingDid = (ConfigurazioneProfilingDid) o;
    return Objects.equals(elencoCittadinanza, configurazioneProfilingDid.elencoCittadinanza) &&
        Objects.equals(elencoPosizioneProfessionale, configurazioneProfilingDid.elencoPosizioneProfessionale) &&
        Objects.equals(elencoIscrizioneCorso, configurazioneProfilingDid.elencoIscrizioneCorso) &&
        Objects.equals(elencoPresenzaInItalia, configurazioneProfilingDid.elencoPresenzaInItalia) &&
        Objects.equals(elencoCondizioniOccupazionali, configurazioneProfilingDid.elencoCondizioniOccupazionali) &&
        Objects.equals(elencoTitoloStudioProfiling, configurazioneProfilingDid.elencoTitoloStudioProfiling) &&
        Objects.equals(elencoGradoStudio, configurazioneProfilingDid.elencoGradoStudio) &&
        Objects.equals(configurazioneQuestionario, configurazioneProfilingDid.configurazioneQuestionario);
  }

  @Override
  public int hashCode() {
    return Objects.hash(elencoCittadinanza, elencoPosizioneProfessionale, elencoIscrizioneCorso, elencoPresenzaInItalia, elencoCondizioniOccupazionali, elencoTitoloStudioProfiling, elencoGradoStudio, configurazioneQuestionario);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneProfilingDid {\n");
    
    sb.append("    elencoCittadinanza: ").append(toIndentedString(elencoCittadinanza)).append("\n");
    sb.append("    elencoPosizioneProfessionale: ").append(toIndentedString(elencoPosizioneProfessionale)).append("\n");
    sb.append("    elencoIscrizioneCorso: ").append(toIndentedString(elencoIscrizioneCorso)).append("\n");
    sb.append("    elencoPresenzaInItalia: ").append(toIndentedString(elencoPresenzaInItalia)).append("\n");
    sb.append("    elencoCondizioniOccupazionali: ").append(toIndentedString(elencoCondizioniOccupazionali)).append("\n");
    sb.append("    elencoTitoloStudioProfiling: ").append(toIndentedString(elencoTitoloStudioProfiling)).append("\n");
    sb.append("    elencoGradoStudio: ").append(toIndentedString(elencoGradoStudio)).append("\n");
    sb.append("    configurazioneQuestionario: ").append(toIndentedString(configurazioneQuestionario)).append("\n");
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

