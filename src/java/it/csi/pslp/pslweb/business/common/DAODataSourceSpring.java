/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

import it.csi.silos.jedi.core.DAODataSource;
import it.csi.silos.jedi.core.DAOException;

/**
 * The Class DAODataSourceSpring.
 */
public class DAODataSourceSpring implements DAODataSource {
  
  /** The data source. */
  @Autowired
  private DataSource dataSource;
  
  /**
   * Gets the data source.
   *
   * @return the data source
   */
  public DataSource getDataSource() {
    return dataSource;
  }

  /**
   * Sets the data source.
   *
   * @param dataSource the new data source
   */
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  /**
   * Gets the connection.
   *
   * @return the connection
   * @throws DAOException the DAO exception
   */
  @Override
  public Connection getConnection() throws DAOException {
    return DataSourceUtils.getConnection(dataSource);
  }

  /**
   * Close connection.
   *
   * @param conn the conn
   * @throws SQLException the SQL exception
   */
  @Override
  public void closeConnection(Connection conn) throws SQLException {
    DataSourceUtils.releaseConnection(conn, dataSource);
  }

}
