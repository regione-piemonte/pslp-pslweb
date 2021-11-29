/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import it.csi.pslp.pslweb.business.integration.AdapterSilpsvdeWSImpl;
import it.csi.pslp.pslweb.util.Constants;

/**
 * Tries to pre-loads some of the caches.
 *
 * @see CachePreloadServletContextEvent
 */
public class CachePreloadServletContextListener implements ServletContextListener {

	/** The Constant log. */
	private static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);
	
	/**
	 * Context initialized.
	 *
	 * @param sce the sce
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		boolean skipListener = Boolean.parseBoolean(sce.getServletContext().getInitParameter("cachePreloadListenerSkip"));
		
		if(skipListener) {
			log.trace("[CachePreloadServletContextListener::contextInitialized] cache preload skipped as per configuration");
			return;
		}
		
		try {
			log.trace("[CachePreloadServletContextListener::contextInitialized] preload cache...");
			AdapterSilpsvdeWSImpl adapterInstance = AdapterSilpsvdeWSImpl.getInstance();
			adapterInstance.findComuni();
			log.trace("[CachePreloadServletContextListener::contextInitialized] comuni cache preloaded");
			adapterInstance.findProvince();
			log.trace("[CachePreloadServletContextListener::contextInitialized] province cache preloaded");
			adapterInstance.findNazioni();
			log.trace("[CachePreloadServletContextListener::contextInitialized] nazioni cache preloaded");
			adapterInstance.findCittadinanze();
			log.trace("[CachePreloadServletContextListener::contextInitialized] cittadinanze cache preloaded");
			adapterInstance.findMotiviRilascioPermessoSoggiorno();
			log.trace("[CachePreloadServletContextListener::contextInitialized] motiviRilascioPermessoSoggiorno cache preloaded");
			adapterInstance.ricercaStatusExtraUE();
			log.trace("[CachePreloadServletContextListener::contextInitialized] statusExtraUE cache preloaded");
		} catch (Exception e) {
			// Ignore error and keep going
			log.error("[CachePreloadServletContextListener::contextInitialized] cache preload failed with error", e);
		}
		log.trace("[CachePreloadServletContextListener::contextInitialized] cache preload complete");
	}

	/**
	 * Context destroyed.
	 *
	 * @param sce the sce
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
