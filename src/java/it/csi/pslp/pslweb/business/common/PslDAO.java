/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslweb.util.Constants;
import it.csi.silos.jedi.core.DAO;
import it.csi.silos.jedi.core.DAODynamicQueryInfo;

/**
 * The Class PslDAO.
 */
@Component("psldao")
public class PslDAO {

  /** The dao. */
  @Autowired
  private DAO dao;

  /**
   * Gets the dao.
   *
   * @return the dao
   */
  public DAO getDao() {
    return dao;
  }

  /**
   * Configure.
   *
   * @param dao the dao
   */
  public static void configure(DAO dao) {
    dao.setLoggerPrefix(Constants.COMPONENT_NAME);
    dao.setStopWatchThreshold(1000);
    dao.setQueryPath("it/csi/pslp/pslweb/business/");
    DAODynamicQueryInfo dynamicQueryInfo = new DAODynamicQueryInfo();
    dynamicQueryInfo.setTableName("PSLP_D_QUERY_REPLACED");
    dynamicQueryInfo.setQueryNameColumn("COD_QUERY_REPLACED");
    dynamicQueryInfo.setQueryValueColumn("DS_QUERY_REPLACED");
    dynamicQueryInfo.setQueryValueColumnExtension("DS_QUERY_REPLACED_EXT");
    dynamicQueryInfo.setQueryActiveColumn("FLG_ATTIVO");
    dynamicQueryInfo.setQueryTimeoutColumn("NUM_SECONDI_TIMEOUT");
    dao.setDynamicQueryInfo(dynamicQueryInfo);
    dao.setDefaultQueryTimeout(new Integer(3600));
  }
}
