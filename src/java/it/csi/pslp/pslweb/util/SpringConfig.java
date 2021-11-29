/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import it.csi.pslp.pslweb.business.common.DAODataSourceSpring;
import it.csi.pslp.pslweb.business.common.PslDAO;
import it.csi.silos.jedi.core.DAO;

/**
 * The Class SpringConfig.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages={"it.csi.pslp.pslweb.business", "it.csi.pslp.pslweb.business.be.impl", "it.csi.pslp.pslweb.business.common", "it.csi.pslp.pslweb.util"})
public class SpringConfig {

  /**
   * Data source.
   *
   * @return the data source
   * @throws IllegalArgumentException the illegal argument exception
   * @throws NamingException the naming exception
   */
  @Bean
  public DataSource dataSource() throws IllegalArgumentException, NamingException {
    JndiObjectFactoryBean f = new JndiObjectFactoryBean();
    f.setProxyInterface(DataSource.class);
    f.setJndiName("java:/pslwebDS");
    f.afterPropertiesSet();
    return (DataSource)f.getObject();
  }

  /**
   * Dao.
   *
   * @return the dao
   * @throws IllegalArgumentException the illegal argument exception
   * @throws NamingException the naming exception
   */
  @Bean
  public DAO dao() throws IllegalArgumentException, NamingException {
    DAO dao = new DAO();
    PslDAO.configure(dao);
    DAODataSourceSpring daoDS = new DAODataSourceSpring();
    daoDS.setDataSource((DataSource) dataSource());
    dao.setDataSource(daoDS);
    return dao;
  }
  
  /**
   * Transaction manager.
   *
   * @return the data source transaction manager
   * @throws IllegalArgumentException the illegal argument exception
   * @throws NamingException the naming exception
   */
  @Bean
  public DataSourceTransactionManager transactionManager() throws IllegalArgumentException, NamingException {
    DataSourceTransactionManager tx = new DataSourceTransactionManager((DataSource)dataSource());
    return tx;
  }
  
  /**
   * Rest helper.
   *
   * @return the rest helper
   */
  @Bean
  public RestHelper restHelper() {
    return new RestHelperImpl();
  }

}
