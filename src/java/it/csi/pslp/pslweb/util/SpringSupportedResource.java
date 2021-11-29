/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

/**
 * The Class SpringSupportedResource.
 *
 * @author CSI-Piemonte
 */
public abstract class SpringSupportedResource {

	/** The spring beans injected. */
	public boolean springBeansInjected = false;
	
	/** The sc. */
	protected ServletContext sc = null;
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(SpringSupportedResource.class);

	/**
	 * Context initialized.
	 *
	 * @param sc the sc
	 */
	public void contextInitialized(ServletContext sc) {
		logger.info("inizializzo risorsa " + this.getClass().getSimpleName());
		this.sc = sc;
	}

	/**
	 * Checks if is spring beans injected.
	 *
	 * @return true, if is spring beans injected
	 */
	public boolean isSpringBeansInjected() {
		return springBeansInjected;
	}

	/**
	 * Sets the spring beans injected.
	 *
	 * @param springBeansInjected the new spring beans injected
	 */
	public void setSpringBeansInjected(boolean springBeansInjected) {
		this.springBeansInjected = springBeansInjected;
	}

	/**
	 * Matches service name.
	 *
	 * @param name il nome del servizio jax-rs. In caso di implememtazione diretta coincide con il nome della classe, mentre
	 * in caso di implementazione indiretta (ovvero interface annotata alla jax-rs + classe di implementazione che estende da essa)
	 * occorre verificare il match del nome servizio con il nome dell'interfaccia impleentata.
	 * @return true se il servizio matcha con il nome fornito
	 */
	public boolean matchesServiceName(String name) {
		// prima verifico se il nome coincide direttamente
		if (this.getClass().getName().equals(name)) {
			return true;
		} else {
			// se non coincide direttamente, cerco tra le interfacce implementate
			Class<?>[] interfaces = this.getClass().getInterfaces();
			if (interfaces != null && interfaces.length > 0) {
				for (int i = 0; i < interfaces.length; i++) {
					Class<?> currIntf = interfaces[i];
					if (currIntf.getName().equals(name)) {
						return true;
					}
				}
				// se non trovato
				return false;
			} else {
				return false;
			}
		}
	}

}
