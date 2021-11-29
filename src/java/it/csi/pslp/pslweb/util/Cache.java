/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class Cache.
 */
public class Cache {
  
  /** The Constant TIMEOUT. */
  static final long TIMEOUT = 30*60*1000; //30 minuti
  
  /** The Constant COMUNI. */
  public static final String COMUNI = "Comuni";
  
  /** The Constant COMUNI_MAP_BY_CODICE. */
  public static final String COMUNI_MAP_BY_CODICE = "Comuni_map_by_codice";
  
  /** The Constant PROVINCE. */
  public static final String PROVINCE = "Province";
  
  /** The Constant PROVINCE. */
  public static final String CPI = "Cpi";

  /** The Constant NAZIONI. */
  public static final String NAZIONI = "Nazioni";
  
  /** The Constant CITTADINANZE. */
  public static final String CITTADINANZE = "Cittadinanze";
  
  /** The Constant CONFIGURAZIONE_PROFILING. */
  public static final String CONFIGURAZIONE_PROFILING = "ConfigurazioneProfiling";
  
  /** The Constant STATUS_EXTRA_UE. */
  public static final String STATUS_EXTRA_UE = "StatusExtraUE";
  
  /** The Constant MOTIVI_RILASCIO_PERMESSO_SOGGIORNO. */
  public static final String MOTIVI_RILASCIO_PERMESSO_SOGGIORNO = "MotiviRilascioPErmessoSoggiorno";
  
  /** The Constant LINGUE. */
  public static final String LINGUE = "Lingue";
  
  /** The Constant GRADI_CONOSCENZA_LINGUE. */
  public static final String GRADI_CONOSCENZA_LINGUE = "GradiConoscenzaLingue";
  
  /** The Constant INFORMATICA. */
  public static final String INFORMATICA = "Informatica";
  
  /** The Constant CATEGORIA_INFORMATICA. */
  public static final String CATEGORIA_INFORMATICA = "CategoriaInformatica";
  
  /** The Constant GRADI_CONOSCENZA_INFORMATICA. */
  public static final String GRADI_CONOSCENZA_INFORMATICA = "GradiConoscenzaInformatica";
  
  /** The Constant PATENTI. */
  public static final String PATENTI = "Patenti";
  
  /** The Constant PATENTINI. */
  public static final String PATENTINI = "Patentini";
  
  /** The Constant CATEGORIE_INQUADRAMENTO. */
  public static final String CATEGORIE_INQUADRAMENTO = "CategorieInquadramento";
  
  /** The Constant QUALIFICHE_PROFESSIONALI. */
  public static final String QUALIFICHE_PROFESSIONALI = "QualificheProfessionali";
  
  /** The Constant TIPI_CONTRATTO. */
  public static final String TIPI_CONTRATTO = "TipiContratto";
  
  /** The Constant ALBI. */
  public static final String ALBI = "Albi";
  
  /** The Constant CERTIFICAZIONI_ATTESTAZIONI. */
  public static final String CERTIFICAZIONI_ATTESTAZIONI = "CertificazioniAttestazioni";
  
  /** The Constant MODALITA_LAVORO. */
  public static final String MODALITA_LAVORO = "ModalitaLavoro";
  
  /** The Constant REGIONI. */
  public static final String REGIONI = "Regioni";
  
  /** The Constant SETTORI_ATECO. */
  public static final String SETTORI_ATECO = "SettoriAteco";
  
  
  /** The Constant MESSAGGI. */
  public static final String MESSAGGI = "Messaggi";
  
  /** The instance. */
  private static Cache instance = new Cache();

  /** The els. */
  private Map<String,CacheEl> els = new HashMap<>();
  
  /**
   * Gets the single instance of Cache.
   *
   * @return single instance of Cache
   */
  public static Cache getInstance() {
    return instance;
  }
  
  /**
   * Gets the el.
   *
   * @param <E> the element type
   * @param key the key
   * @return the el
   */
  @SuppressWarnings("unchecked")
  public <E> E getEl(String key) {
    CacheEl el = els.get(key);
    if(el==null || el.isExpired()) return null;
    return (E) el.getValue();
  }
  
  /**
   * Put el.
   *
   * @param key the key
   * @param value the value
   */
  public void putEl(String key, Object value) {
    CacheEl el = new CacheEl(value);
    els.put(key, el);
  }
}
