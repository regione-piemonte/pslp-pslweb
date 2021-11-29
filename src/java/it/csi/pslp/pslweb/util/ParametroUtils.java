/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslcommonobj.dbdef.ParametroDBDef;
import it.csi.pslp.pslcommonobj.dto.ParametroDTO;
import it.csi.pslp.pslcommonobj.filter.ParametroFilter;
import it.csi.pslp.pslweb.business.SpringApplicationContextHelper;
import it.csi.silos.jedi.core.DAO;
import it.csi.silos.silcommon.util.SilCommonUtils;

/**
 * The Class ParametroUtils.
 */
@Component("parametroUtils")
public class ParametroUtils {
  
  /** The Constant STAMPA_PRIVACY. */
  public static final String STAMPA_PRIVACY = "STAMPA_PRIVACY";

  /** The Constant MINORE_ETA. */
  public static final String MINORE_ETA = "MINORE_ETA";
  
  /** The Constant GG_MINORE_ETA. */
  public static final String GG_MINORE_ETA = "GG_MINORE_ETA";
  
  /** The Constant GG_MAGGIORE_ETA. */
  public static final String GG_MAGGIORE_ETA = "GG_MAGGIORE_ETA";
  
  /** The Constant NUMERO_MASSIMO_ESPERIENZE_LAVORO_SAP. */
  public static final String NUMERO_MASSIMO_ESPERIENZE_LAVORO_SAP = "GG_MAGGIORE_ETA";
  
  /** The Constant CLIENT_SECURITY_CF. */
  public static final String CLIENT_SECURITY_CF = "CLIENTSECURITYCF";
  
  /** The Constant CLIENT_SECURITY_TOKEN. */
  public static final String CLIENT_SECURITY_TOKEN = "CLIENTSECURITYTOKEN";

  
  /** The Constant log. */
  protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

  /** The dao. */
  @Autowired
  private DAO dao;
  
  /**
   * Gets the single instance of ParametroUtils.
   *
   * @return single instance of ParametroUtils
   */
  public static ParametroUtils getInstance() {
      return (ParametroUtils)SpringApplicationContextHelper.getBean("parametroUtils");
    }
  
  /**
   * Gets the parametro.
   *
   * @param codice the codice
   * @param defaultValue the default value
   * @return the parametro
   * @throws Exception the exception
   */
  public String getParametro(String codice, String defaultValue) throws Exception {
    String value = getParametro(codice);
    if(value==null) value = defaultValue;
    return value;
  }

  /**
   * Gets the parametro.
   *
   * @param codice the codice
   * @return the parametro
   * @throws Exception the exception
   */
  public String getParametro(String codice) throws Exception {
    ParametroDTO parametroDTO = getParametroDTO(codice);
    if(parametroDTO!=null) {
      return (String) SilCommonUtils.nvl(parametroDTO.getValoreParametroExt(),parametroDTO.getValoreParametro());
    }
    return null;
  }

  /**
   * Gets the parametro DTO.
   *
   * @param codice the codice
   * @return the parametro DTO
   * @throws Exception the exception
   */
  public ParametroDTO getParametroDTO(String codice) throws Exception {
    Date now = new Date();
    ParametroFilter filter1 = new ParametroFilter();
    filter1.getCodParametro().eq(codice);
    filter1.getDtInizio().le(now);
    ParametroFilter filter2 = new ParametroFilter();
    filter2.getDtFine().eq(null);
    ParametroFilter filter3 = new ParametroFilter();
    filter3.getDtFine().ge(now);
    ParametroFilter filter = (ParametroFilter)filter1.and(filter2.or(filter3));
    ParametroDTO parametroDTO = dao.findFirst(ParametroDBDef.class, filter);
    return parametroDTO;
  }
}