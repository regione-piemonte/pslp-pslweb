/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import it.csi.pslp.pslweb.dto.be.MotivoFamiliareACarico;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ConfigurazioneFamiliariACarico   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private List<Decodifica> elencoGradoParentela = new ArrayList<Decodifica>();
  private List<Decodifica> elencoDecodeMotivoCarico = new ArrayList<Decodifica>();
  private List<Decodifica> elencoMotivoCaricoDescrizioneLunga = new ArrayList<Decodifica>();
  private List<MotivoFamiliareACarico> elencoCompletoMotivoCarico = new ArrayList<MotivoFamiliareACarico>();
  private List<MotivoFamiliareACarico> elencoCompletoMotivoCaricoCompresiScaduti = new ArrayList<MotivoFamiliareACarico>();

  /**
   **/
  

  @JsonProperty("elencoGradoParentela") 
 
  public List<Decodifica> getElencoGradoParentela() {
    return elencoGradoParentela;
  }
  public void setElencoGradoParentela(List<Decodifica> elencoGradoParentela) {
    this.elencoGradoParentela = elencoGradoParentela;
  }

  /**
   **/
  

  @JsonProperty("elencoDecodeMotivoCarico") 
 
  public List<Decodifica> getElencoDecodeMotivoCarico() {
    return elencoDecodeMotivoCarico;
  }
  public void setElencoDecodeMotivoCarico(List<Decodifica> elencoDecodeMotivoCarico) {
    this.elencoDecodeMotivoCarico = elencoDecodeMotivoCarico;
  }

  /**
   **/
  

  @JsonProperty("elencoMotivoCaricoDescrizioneLunga") 
 
  public List<Decodifica> getElencoMotivoCaricoDescrizioneLunga() {
    return elencoMotivoCaricoDescrizioneLunga;
  }
  public void setElencoMotivoCaricoDescrizioneLunga(List<Decodifica> elencoMotivoCaricoDescrizioneLunga) {
    this.elencoMotivoCaricoDescrizioneLunga = elencoMotivoCaricoDescrizioneLunga;
  }

  /**
   **/
  

  @JsonProperty("elencoCompletoMotivoCarico") 
 
  public List<MotivoFamiliareACarico> getElencoCompletoMotivoCarico() {
    return elencoCompletoMotivoCarico;
  }
  public void setElencoCompletoMotivoCarico(List<MotivoFamiliareACarico> elencoCompletoMotivoCarico) {
    this.elencoCompletoMotivoCarico = elencoCompletoMotivoCarico;
  }

  /**
   **/
  

  @JsonProperty("elencoCompletoMotivoCaricoCompresiScaduti") 
 
  public List<MotivoFamiliareACarico> getElencoCompletoMotivoCaricoCompresiScaduti() {
    return elencoCompletoMotivoCaricoCompresiScaduti;
  }
  public void setElencoCompletoMotivoCaricoCompresiScaduti(List<MotivoFamiliareACarico> elencoCompletoMotivoCaricoCompresiScaduti) {
    this.elencoCompletoMotivoCaricoCompresiScaduti = elencoCompletoMotivoCaricoCompresiScaduti;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneFamiliariACarico configurazioneFamiliariACarico = (ConfigurazioneFamiliariACarico) o;
    return Objects.equals(elencoGradoParentela, configurazioneFamiliariACarico.elencoGradoParentela) &&
        Objects.equals(elencoDecodeMotivoCarico, configurazioneFamiliariACarico.elencoDecodeMotivoCarico) &&
        Objects.equals(elencoMotivoCaricoDescrizioneLunga, configurazioneFamiliariACarico.elencoMotivoCaricoDescrizioneLunga) &&
        Objects.equals(elencoCompletoMotivoCarico, configurazioneFamiliariACarico.elencoCompletoMotivoCarico) &&
        Objects.equals(elencoCompletoMotivoCaricoCompresiScaduti, configurazioneFamiliariACarico.elencoCompletoMotivoCaricoCompresiScaduti);
  }

  @Override
  public int hashCode() {
    return Objects.hash(elencoGradoParentela, elencoDecodeMotivoCarico, elencoMotivoCaricoDescrizioneLunga, elencoCompletoMotivoCarico, elencoCompletoMotivoCaricoCompresiScaduti);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneFamiliariACarico {\n");
    
    sb.append("    elencoGradoParentela: ").append(toIndentedString(elencoGradoParentela)).append("\n");
    sb.append("    elencoDecodeMotivoCarico: ").append(toIndentedString(elencoDecodeMotivoCarico)).append("\n");
    sb.append("    elencoMotivoCaricoDescrizioneLunga: ").append(toIndentedString(elencoMotivoCaricoDescrizioneLunga)).append("\n");
    sb.append("    elencoCompletoMotivoCarico: ").append(toIndentedString(elencoCompletoMotivoCarico)).append("\n");
    sb.append("    elencoCompletoMotivoCaricoCompresiScaduti: ").append(toIndentedString(elencoCompletoMotivoCaricoCompresiScaduti)).append("\n");
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

