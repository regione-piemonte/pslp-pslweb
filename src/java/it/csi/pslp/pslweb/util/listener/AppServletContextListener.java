/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * The listener interface for receiving appServletContext events.
 * The class that is interested in processing a appServletContext
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addAppServletContextListener<code> method. When
 * the appServletContext event occurs, that object's appropriate
 * method is invoked.
 *
 * @see AppServletContextEvent
 */
public class AppServletContextListener implements ServletContextListener {

	/** The sc. */
	private static ServletContext sc;
	
	/**
	 * Context initialized.
	 *
	 * @param sce the sce
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/* Sets the context in a static variable */
		AppServletContextListener.sc = sce.getServletContext();
	}

	/**
	 * Context destroyed.
	 *
	 * @param sce the sce
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	/**
	 * Gets the servlet context.
	 *
	 * @return the servlet context
	 */
	public static ServletContext getServletContext() {
		return sc;
	}
}
