/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.io.Serializable;

public class StatoAdesione implements Serializable {

  private static final long serialVersionUID = 1L;

  private String codice;
  
  private String descrizione;

  public String getCodice() {
    return codice;
  }

  public void setCodice(String codice) {
    this.codice = codice;
  }

  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  @Override
  public String toString() {
    return "StatoAdesione [codice=" + codice + ", descrizione=" + descrizione + "]";
  }
  
  
}
