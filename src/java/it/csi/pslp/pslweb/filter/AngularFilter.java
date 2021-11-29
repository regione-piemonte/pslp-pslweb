/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.FilterUtil;


/**
 * Filtro per la gestione di Angular.
 *
 * @author Marchino Alessandro
 */
public class AngularFilter implements Filter {

  /**  Logger. */
  protected static final Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME + ".security");
  
  /**  The init-param to be used for the backend. */
  private static final String BACKENDURL_INIT_PARAM = "backendUrl";
  
  /**  The init-param to be used for the index. */
  private static final String INDEXURL_INIT_PARAM = "indexUrl";

  /** The backend url. */
  private String backendUrl;
  
  /** The index url. */
  private String indexUrl;

  /**
   * Do filter.
   *
   * @param req the req
   * @param res the res
   * @param chain the chain
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws ServletException the servlet exception
   */
  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    if (!(req instanceof HttpServletRequest && res instanceof HttpServletResponse)) {
      LOG.debug("[AngularFilter::doFilter] Request or response are NOT HTTP. Skip...");
      chain.doFilter(req, res);
      return;
    }
    HttpServletRequest httpRequest = (HttpServletRequest) req;
    String contextPath = httpRequest.getContextPath();
    String requestURI = httpRequest.getRequestURI();
    String angularPath = requestURI.substring(contextPath.length());
    if(isAngularRoute(angularPath)) {
      req.getRequestDispatcher(indexUrl).forward(req, res);
    } else {
      chain.doFilter(req, res);
    }
  }

  /**
   * Inits the.
   *
   * @param filterConfig the filter config
   * @throws ServletException the servlet exception
   */
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.backendUrl = filterConfig.getInitParameter(BACKENDURL_INIT_PARAM);
    this.indexUrl = filterConfig.getInitParameter(INDEXURL_INIT_PARAM);
  }

  /**
   * Checks whether the given route is an Angular-type route.
   *
   * @param angularPath the given path
   * @return whether the route is Angular-type
   */
  private boolean isAngularRoute(String angularPath) {
    return !angularPath.contains(backendUrl)
        && !angularPath.contains(indexUrl)
        && !FilterUtil.isStaticResourcePath(angularPath);
  }

  /**
   * Destroy.
   */
  public void destroy() {
    // NOP
  }

}
