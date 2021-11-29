/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import it.csi.pslp.pslweb.dto.be.CategoriaProtetta;
import it.csi.pslp.pslweb.dto.be.CategoriaProtettaDisab;
import it.csi.pslp.pslweb.dto.be.Decodifica;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class ConfigurazioneCollocamentoMirato   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private List<Decodifica> elencoTipoIscrizione = new ArrayList<Decodifica>();
  private List<Decodifica> elencoMotivoIscrizione = new ArrayList<Decodifica>();
  private List<Decodifica> elencoStatoIscrizione = new ArrayList<Decodifica>();
  private List<Decodifica> elencoTipoComunicazione = new ArrayList<Decodifica>();
  private List<CategoriaProtettaDisab> elencoCategoriaProtettaDisabili = new ArrayList<CategoriaProtettaDisab>();
  private List<CategoriaProtetta> elencoCategoriaProtetta = new ArrayList<CategoriaProtetta>();
  private List<Decodifica> elencoQualificheNonVedenti = new ArrayList<Decodifica>();
  private List<Decodifica> elencoCategoriaInvaliditaDisabili = new ArrayList<Decodifica>();
  private Long limiteRedditoLordoAnnuoRapportoAutonomo = null;
  private Long limiteRedditoLordoAnnuoRapportoSubordinato = null;
  private String gradoDisabilitaNonVedenti = null;
  private String gradoDisabilitaSordomuti = null;
  private List<Decodifica> elencoDichiarazioneVisitaRevisioneInvaliditaCivile = new ArrayList<Decodifica>();

  /**
   **/
  

  @JsonProperty("elenco_tipo_iscrizione") 
 
  public List<Decodifica> getElencoTipoIscrizione() {
    return elencoTipoIscrizione;
  }
  public void setElencoTipoIscrizione(List<Decodifica> elencoTipoIscrizione) {
    this.elencoTipoIscrizione = elencoTipoIscrizione;
  }

  /**
   **/
  

  @JsonProperty("elenco_motivo_iscrizione") 
 
  public List<Decodifica> getElencoMotivoIscrizione() {
    return elencoMotivoIscrizione;
  }
  public void setElencoMotivoIscrizione(List<Decodifica> elencoMotivoIscrizione) {
    this.elencoMotivoIscrizione = elencoMotivoIscrizione;
  }

  /**
   **/
  

  @JsonProperty("elenco_stato_iscrizione") 
 
  public List<Decodifica> getElencoStatoIscrizione() {
    return elencoStatoIscrizione;
  }
  public void setElencoStatoIscrizione(List<Decodifica> elencoStatoIscrizione) {
    this.elencoStatoIscrizione = elencoStatoIscrizione;
  }

  /**
   **/
  

  @JsonProperty("elenco_tipo_comunicazione") 
 
  public List<Decodifica> getElencoTipoComunicazione() {
    return elencoTipoComunicazione;
  }
  public void setElencoTipoComunicazione(List<Decodifica> elencoTipoComunicazione) {
    this.elencoTipoComunicazione = elencoTipoComunicazione;
  }

  /**
   **/
  

  @JsonProperty("elenco_categoria_protetta_disabili") 
 
  public List<CategoriaProtettaDisab> getElencoCategoriaProtettaDisabili() {
    return elencoCategoriaProtettaDisabili;
  }
  public void setElencoCategoriaProtettaDisabili(List<CategoriaProtettaDisab> elencoCategoriaProtettaDisabili) {
    this.elencoCategoriaProtettaDisabili = elencoCategoriaProtettaDisabili;
  }

  /**
   **/
  

  @JsonProperty("elenco_categoria_protetta") 
 
  public List<CategoriaProtetta> getElencoCategoriaProtetta() {
    return elencoCategoriaProtetta;
  }
  public void setElencoCategoriaProtetta(List<CategoriaProtetta> elencoCategoriaProtetta) {
    this.elencoCategoriaProtetta = elencoCategoriaProtetta;
  }

  /**
   **/
  

  @JsonProperty("elenco_qualifiche_non_vedenti") 
 
  public List<Decodifica> getElencoQualificheNonVedenti() {
    return elencoQualificheNonVedenti;
  }
  public void setElencoQualificheNonVedenti(List<Decodifica> elencoQualificheNonVedenti) {
    this.elencoQualificheNonVedenti = elencoQualificheNonVedenti;
  }

  /**
   **/
  

  @JsonProperty("elenco_categoria_invalidita_disabili") 
 
  public List<Decodifica> getElencoCategoriaInvaliditaDisabili() {
    return elencoCategoriaInvaliditaDisabili;
  }
  public void setElencoCategoriaInvaliditaDisabili(List<Decodifica> elencoCategoriaInvaliditaDisabili) {
    this.elencoCategoriaInvaliditaDisabili = elencoCategoriaInvaliditaDisabili;
  }

  /**
   **/
  

  @JsonProperty("limite_reddito_lordo_annuo_rapporto_autonomo") 
 
  public Long getLimiteRedditoLordoAnnuoRapportoAutonomo() {
    return limiteRedditoLordoAnnuoRapportoAutonomo;
  }
  public void setLimiteRedditoLordoAnnuoRapportoAutonomo(Long limiteRedditoLordoAnnuoRapportoAutonomo) {
    this.limiteRedditoLordoAnnuoRapportoAutonomo = limiteRedditoLordoAnnuoRapportoAutonomo;
  }

  /**
   **/
  

  @JsonProperty("limite_reddito_lordo_annuo_rapporto_subordinato") 
 
  public Long getLimiteRedditoLordoAnnuoRapportoSubordinato() {
    return limiteRedditoLordoAnnuoRapportoSubordinato;
  }
  public void setLimiteRedditoLordoAnnuoRapportoSubordinato(Long limiteRedditoLordoAnnuoRapportoSubordinato) {
    this.limiteRedditoLordoAnnuoRapportoSubordinato = limiteRedditoLordoAnnuoRapportoSubordinato;
  }

  /**
   **/
  

  @JsonProperty("grado_disabilita_non_vedenti") 
 
  public String getGradoDisabilitaNonVedenti() {
    return gradoDisabilitaNonVedenti;
  }
  public void setGradoDisabilitaNonVedenti(String gradoDisabilitaNonVedenti) {
    this.gradoDisabilitaNonVedenti = gradoDisabilitaNonVedenti;
  }

  /**
   **/
  

  @JsonProperty("grado_disabilita_sordomuti") 
 
  public String getGradoDisabilitaSordomuti() {
    return gradoDisabilitaSordomuti;
  }
  public void setGradoDisabilitaSordomuti(String gradoDisabilitaSordomuti) {
    this.gradoDisabilitaSordomuti = gradoDisabilitaSordomuti;
  }

  /**
   **/
  

  @JsonProperty("elenco_dichiarazione_visita_revisione_invalidita_civile") 
 
  public List<Decodifica> getElencoDichiarazioneVisitaRevisioneInvaliditaCivile() {
    return elencoDichiarazioneVisitaRevisioneInvaliditaCivile;
  }
  public void setElencoDichiarazioneVisitaRevisioneInvaliditaCivile(List<Decodifica> elencoDichiarazioneVisitaRevisioneInvaliditaCivile) {
    this.elencoDichiarazioneVisitaRevisioneInvaliditaCivile = elencoDichiarazioneVisitaRevisioneInvaliditaCivile;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurazioneCollocamentoMirato configurazioneCollocamentoMirato = (ConfigurazioneCollocamentoMirato) o;
    return Objects.equals(elencoTipoIscrizione, configurazioneCollocamentoMirato.elencoTipoIscrizione) &&
        Objects.equals(elencoMotivoIscrizione, configurazioneCollocamentoMirato.elencoMotivoIscrizione) &&
        Objects.equals(elencoStatoIscrizione, configurazioneCollocamentoMirato.elencoStatoIscrizione) &&
        Objects.equals(elencoTipoComunicazione, configurazioneCollocamentoMirato.elencoTipoComunicazione) &&
        Objects.equals(elencoCategoriaProtettaDisabili, configurazioneCollocamentoMirato.elencoCategoriaProtettaDisabili) &&
        Objects.equals(elencoCategoriaProtetta, configurazioneCollocamentoMirato.elencoCategoriaProtetta) &&
        Objects.equals(elencoQualificheNonVedenti, configurazioneCollocamentoMirato.elencoQualificheNonVedenti) &&
        Objects.equals(elencoCategoriaInvaliditaDisabili, configurazioneCollocamentoMirato.elencoCategoriaInvaliditaDisabili) &&
        Objects.equals(limiteRedditoLordoAnnuoRapportoAutonomo, configurazioneCollocamentoMirato.limiteRedditoLordoAnnuoRapportoAutonomo) &&
        Objects.equals(limiteRedditoLordoAnnuoRapportoSubordinato, configurazioneCollocamentoMirato.limiteRedditoLordoAnnuoRapportoSubordinato) &&
        Objects.equals(gradoDisabilitaNonVedenti, configurazioneCollocamentoMirato.gradoDisabilitaNonVedenti) &&
        Objects.equals(gradoDisabilitaSordomuti, configurazioneCollocamentoMirato.gradoDisabilitaSordomuti) &&
        Objects.equals(elencoDichiarazioneVisitaRevisioneInvaliditaCivile, configurazioneCollocamentoMirato.elencoDichiarazioneVisitaRevisioneInvaliditaCivile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(elencoTipoIscrizione, elencoMotivoIscrizione, elencoStatoIscrizione, elencoTipoComunicazione, elencoCategoriaProtettaDisabili, elencoCategoriaProtetta, elencoQualificheNonVedenti, elencoCategoriaInvaliditaDisabili, limiteRedditoLordoAnnuoRapportoAutonomo, limiteRedditoLordoAnnuoRapportoSubordinato, gradoDisabilitaNonVedenti, gradoDisabilitaSordomuti, elencoDichiarazioneVisitaRevisioneInvaliditaCivile);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurazioneCollocamentoMirato {\n");
    
    sb.append("    elencoTipoIscrizione: ").append(toIndentedString(elencoTipoIscrizione)).append("\n");
    sb.append("    elencoMotivoIscrizione: ").append(toIndentedString(elencoMotivoIscrizione)).append("\n");
    sb.append("    elencoStatoIscrizione: ").append(toIndentedString(elencoStatoIscrizione)).append("\n");
    sb.append("    elencoTipoComunicazione: ").append(toIndentedString(elencoTipoComunicazione)).append("\n");
    sb.append("    elencoCategoriaProtettaDisabili: ").append(toIndentedString(elencoCategoriaProtettaDisabili)).append("\n");
    sb.append("    elencoCategoriaProtetta: ").append(toIndentedString(elencoCategoriaProtetta)).append("\n");
    sb.append("    elencoQualificheNonVedenti: ").append(toIndentedString(elencoQualificheNonVedenti)).append("\n");
    sb.append("    elencoCategoriaInvaliditaDisabili: ").append(toIndentedString(elencoCategoriaInvaliditaDisabili)).append("\n");
    sb.append("    limiteRedditoLordoAnnuoRapportoAutonomo: ").append(toIndentedString(limiteRedditoLordoAnnuoRapportoAutonomo)).append("\n");
    sb.append("    limiteRedditoLordoAnnuoRapportoSubordinato: ").append(toIndentedString(limiteRedditoLordoAnnuoRapportoSubordinato)).append("\n");
    sb.append("    gradoDisabilitaNonVedenti: ").append(toIndentedString(gradoDisabilitaNonVedenti)).append("\n");
    sb.append("    gradoDisabilitaSordomuti: ").append(toIndentedString(gradoDisabilitaSordomuti)).append("\n");
    sb.append("    elencoDichiarazioneVisitaRevisioneInvaliditaCivile: ").append(toIndentedString(elencoDichiarazioneVisitaRevisioneInvaliditaCivile)).append("\n");
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

