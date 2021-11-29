/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util;

import javax.servlet.http.HttpServletRequest;

/**
 * The Interface RestHelper.
 */
public interface RestHelper {

	/**
	 * Gets the incoming IP address.
	 *
	 * @param req the req
	 * @return the incoming IP address
	 */
	public String getIncomingIPAddress(HttpServletRequest req);
	
	/**
	 * Gets the codice fiscale current user.
	 *
	 * @param httpRequest the http request
	 * @return the codice fiscale current user
	 */
	public String getCodiceFiscaleCurrentUser(HttpServletRequest httpRequest);
	
	/**
	 * Check utente by SILP.
	 *
	 * @param httpRequest the http request
	 * @param idMetodo the id metodo
	 * @param idSilLavAnagrafica the id sil lav anagrafica
	 * @throws Exception the exception
	 */
	public void checkUtenteBySILP(HttpServletRequest httpRequest, Long idMetodo, Long idSilLavAnagrafica) throws Exception;
	
	/**
	 * Check utente.
	 *
	 * @param httpRequest the http request
	 * @param idMetodo the id metodo
	 * @param idUtente the id utente
	 * @throws Exception the exception
	 */
	public void checkUtente(HttpServletRequest httpRequest, Long idMetodo, Long idUtente) throws Exception;
	
	/**
	 * Check utente.
	 *
	 * @param httpRequest the http request
	 * @param idMetodo the id metodo
	 * @param codiceFiscale the codice fiscale
	 * @throws Exception the exception
	 */
	public void checkUtente(HttpServletRequest httpRequest, Long idMetodo, String codiceFiscale) throws Exception;
  
	/**
	 * Check utente operatore.
	 *
	 * @param httpRequest the http request
	 * @param idMetodo the id metodo
	 * @param idUtente the id utente
	 * @throws Exception the exception
	 */
	public void checkUtenteOperatore(HttpServletRequest httpRequest, Long idMetodo, Long idUtente) throws Exception;
}
