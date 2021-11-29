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

/**
 * The Class CorsFilter.
 */
public class CorsFilter implements Filter {

	/**
	 * Do filter.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @param chain the chain
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
				
		boolean isHttpReqRes = req instanceof HttpServletRequest && resp instanceof HttpServletResponse;
		if(!enableCors || !isHttpReqRes ) {
			//vado avanti nella catena di filtri ed esco.
			chain.doFilter(req, resp);
			return;
		}
		
		
		HttpServletRequest hreq = (HttpServletRequest) req;
		HttpServletResponse hresp = (HttpServletResponse) resp;
		
		hresp.setHeader("Access-Control-Allow-Origin", "*");
		hresp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, HEAD, OPTIONS, PATCH");
		hresp.setHeader("Access-Control-Allow-Headers", "Authorization, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
				
		//CORS handshake
		if (hreq.getMethod().equals("OPTIONS")) { 
			System.out.println(">>>>> OPTIONS always accepted!!");
			hresp.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}
		
		chain.doFilter(req, resp);

	}

	/** The Constant ENABLECORS_INIT_PARAM. */
	private static final String ENABLECORS_INIT_PARAM = "enablecors";
	
	/** The enable cors. */
	private boolean enableCors = false;

	/**
	 * Inits the.
	 *
	 * @param filterConfig the filter config
	 * @throws ServletException the servlet exception
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String sEnableCors = filterConfig.getInitParameter(ENABLECORS_INIT_PARAM);
		if ("true".equals(sEnableCors)) {
			enableCors = true;
		} else {
			enableCors = false;
		}
	}

	/**
	 * Destroy.
	 */
	@Override
	public void destroy() {
	}

}