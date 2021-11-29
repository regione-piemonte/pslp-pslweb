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

public class Calendario   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String invioConfermaPrenotazione = null;
  private String codiceAmbito = null;
  private Long codiceOperatore = null;
  private Long idCalendario = null;
  private String flagAnnullaGaranziaGiovani = null;
  private Long subcodice = null;
  private Integer oreInvioPromemoria = null;
  private Integer oreLimiteSpostamento = null;
  private String flagVisibileCpi = null;
  private String gruppoOperatore = null;
  private String nome = null;
  private String flagBloccato = null;
  private String messaggioSpostamento = null;
  private String messaggioAnnullamento = null;

  /**
   **/
  

  @JsonProperty("invio_conferma_prenotazione") 
 
  public String getInvioConfermaPrenotazione() {
    return invioConfermaPrenotazione;
  }
  public void setInvioConfermaPrenotazione(String invioConfermaPrenotazione) {
    this.invioConfermaPrenotazione = invioConfermaPrenotazione;
  }

  /**
   **/
  

  @JsonProperty("codice_ambito") 
 
  public String getCodiceAmbito() {
    return codiceAmbito;
  }
  public void setCodiceAmbito(String codiceAmbito) {
    this.codiceAmbito = codiceAmbito;
  }

  /**
   **/
  

  @JsonProperty("codice_operatore") 
 
  public Long getCodiceOperatore() {
    return codiceOperatore;
  }
  public void setCodiceOperatore(Long codiceOperatore) {
    this.codiceOperatore = codiceOperatore;
  }

  /**
   **/
  

  @JsonProperty("id_calendario") 
 
  public Long getIdCalendario() {
    return idCalendario;
  }
  public void setIdCalendario(Long idCalendario) {
    this.idCalendario = idCalendario;
  }

  /**
   **/
  

  @JsonProperty("flag_annulla_garanzia_giovani") 
 
  public String getFlagAnnullaGaranziaGiovani() {
    return flagAnnullaGaranziaGiovani;
  }
  public void setFlagAnnullaGaranziaGiovani(String flagAnnullaGaranziaGiovani) {
    this.flagAnnullaGaranziaGiovani = flagAnnullaGaranziaGiovani;
  }

  /**
   **/
  

  @JsonProperty("subcodice") 
 
  public Long getSubcodice() {
    return subcodice;
  }
  public void setSubcodice(Long subcodice) {
    this.subcodice = subcodice;
  }

  /**
   **/
  

  @JsonProperty("ore_invio_promemoria") 
 
  public Integer getOreInvioPromemoria() {
    return oreInvioPromemoria;
  }
  public void setOreInvioPromemoria(Integer oreInvioPromemoria) {
    this.oreInvioPromemoria = oreInvioPromemoria;
  }

  /**
   **/
  

  @JsonProperty("ore_limite_spostamento") 
 
  public Integer getOreLimiteSpostamento() {
    return oreLimiteSpostamento;
  }
  public void setOreLimiteSpostamento(Integer oreLimiteSpostamento) {
    this.oreLimiteSpostamento = oreLimiteSpostamento;
  }

  /**
   **/
  

  @JsonProperty("flag_visibile_cpi") 
 
  public String getFlagVisibileCpi() {
    return flagVisibileCpi;
  }
  public void setFlagVisibileCpi(String flagVisibileCpi) {
    this.flagVisibileCpi = flagVisibileCpi;
  }

  /**
   **/
  

  @JsonProperty("gruppo_operatore") 
 
  public String getGruppoOperatore() {
    return gruppoOperatore;
  }
  public void setGruppoOperatore(String gruppoOperatore) {
    this.gruppoOperatore = gruppoOperatore;
  }

  /**
   **/
  

  @JsonProperty("nome") 
 
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   **/
  

  @JsonProperty("flag_bloccato") 
 
  public String getFlagBloccato() {
    return flagBloccato;
  }
  public void setFlagBloccato(String flagBloccato) {
    this.flagBloccato = flagBloccato;
  }

  /**
   **/
  

  @JsonProperty("messaggio_spostamento") 
 
  public String getMessaggioSpostamento() {
    return messaggioSpostamento;
  }
  public void setMessaggioSpostamento(String messaggioSpostamento) {
    this.messaggioSpostamento = messaggioSpostamento;
  }

  /**
   **/
  

  @JsonProperty("messaggio_annullamento") 
 
  public String getMessaggioAnnullamento() {
    return messaggioAnnullamento;
  }
  public void setMessaggioAnnullamento(String messaggioAnnullamento) {
    this.messaggioAnnullamento = messaggioAnnullamento;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Calendario calendario = (Calendario) o;
    return Objects.equals(invioConfermaPrenotazione, calendario.invioConfermaPrenotazione) &&
        Objects.equals(codiceAmbito, calendario.codiceAmbito) &&
        Objects.equals(codiceOperatore, calendario.codiceOperatore) &&
        Objects.equals(idCalendario, calendario.idCalendario) &&
        Objects.equals(flagAnnullaGaranziaGiovani, calendario.flagAnnullaGaranziaGiovani) &&
        Objects.equals(subcodice, calendario.subcodice) &&
        Objects.equals(oreInvioPromemoria, calendario.oreInvioPromemoria) &&
        Objects.equals(oreLimiteSpostamento, calendario.oreLimiteSpostamento) &&
        Objects.equals(flagVisibileCpi, calendario.flagVisibileCpi) &&
        Objects.equals(gruppoOperatore, calendario.gruppoOperatore) &&
        Objects.equals(nome, calendario.nome) &&
        Objects.equals(flagBloccato, calendario.flagBloccato) &&
        Objects.equals(messaggioSpostamento, calendario.messaggioSpostamento) &&
        Objects.equals(messaggioAnnullamento, calendario.messaggioAnnullamento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(invioConfermaPrenotazione, codiceAmbito, codiceOperatore, idCalendario, flagAnnullaGaranziaGiovani, subcodice, oreInvioPromemoria, oreLimiteSpostamento, flagVisibileCpi, gruppoOperatore, nome, flagBloccato, messaggioSpostamento, messaggioAnnullamento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Calendario {\n");
    
    sb.append("    invioConfermaPrenotazione: ").append(toIndentedString(invioConfermaPrenotazione)).append("\n");
    sb.append("    codiceAmbito: ").append(toIndentedString(codiceAmbito)).append("\n");
    sb.append("    codiceOperatore: ").append(toIndentedString(codiceOperatore)).append("\n");
    sb.append("    idCalendario: ").append(toIndentedString(idCalendario)).append("\n");
    sb.append("    flagAnnullaGaranziaGiovani: ").append(toIndentedString(flagAnnullaGaranziaGiovani)).append("\n");
    sb.append("    subcodice: ").append(toIndentedString(subcodice)).append("\n");
    sb.append("    oreInvioPromemoria: ").append(toIndentedString(oreInvioPromemoria)).append("\n");
    sb.append("    oreLimiteSpostamento: ").append(toIndentedString(oreLimiteSpostamento)).append("\n");
    sb.append("    flagVisibileCpi: ").append(toIndentedString(flagVisibileCpi)).append("\n");
    sb.append("    gruppoOperatore: ").append(toIndentedString(gruppoOperatore)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    flagBloccato: ").append(toIndentedString(flagBloccato)).append("\n");
    sb.append("    messaggioSpostamento: ").append(toIndentedString(messaggioSpostamento)).append("\n");
    sb.append("    messaggioAnnullamento: ").append(toIndentedString(messaggioAnnullamento)).append("\n");
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

