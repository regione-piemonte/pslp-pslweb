/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.filter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.FilterUtil;

/**
 * Il filtro agisce su tutte le richieste in input. Serve a prevenire attacchi di tipo 
 * XSRF.
 * L'idea e' che il primo accesso avvenga sulla risorsa di accesso della componente SPA
 * (es. index.html). In quell'occasione il filtro non fa verifiche ma si preoccupa di 
 * creare una nuova sessione servlet se non presente, creare un XSRF cookie, metterlo in
 * sessione e passarlo al client. 
 * Nelle successive richieste, se la rihciesta e' destinata allo strato di servizi rest,
 * viene effettuata la verifica di presenza/corrispondenza di cookie+header XSRF.
 * Questo comportamento, unito alla corretta gestione client side del protocollo, 
 * permette di evitare il richiamo dello strato rest al di fuori del contesto corretto.  
 */
public class XSRFProtectionFilter implements Filter {

	/** The Constant LOG. */
	final static Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME + ".security");

	/** nome dell'header XSRF che la componente client deve inserire ad ogni richiesta rest. */
	private static final String XSRF_HEADER_NAME = "X-XSRF-TOKEN";

	/** The Constant XSRF_COOKIE_NAME. */
	/*
	 * nome del cookie XSRF
	 */
	public static final String XSRF_COOKIE_NAME = "XSRF-TOKEN";

	/** nome dell'attributo di sessione che mantiene il token XSRF. */
	private static final String XSRF_INTERNAL_TOKEN_NAME = "XSRF_SESSION_TOKEN";

	/**
	 * Destroy.
	 */
	@Override
	public void destroy() {
		// nothing to do
	}

	/**
	 * Azione del filtro:
	 * <ul>
	 * <li>creazione di una nuova sessione se non ancora presente</li>
	 * <li>se la richiesta corrente e' destinata alla parte SPA:
	 *   <ul>
	 *   <li>viene creato un nuovo token  che viene inserito in sessione e 
	 *   restituito come cookie</li>
	 *   </ul>
	 * </li>
	 * <li>se la richiesta corrente e' destinata alla parte REST:
	 *   <ul>
	 *   <li>se la request contiene una coppia di cookie e header XSRF validi e coincidenti con
	 *   quello corrente (contenuto in sessione) => procedo, altrimenti errore.</li>
	 *   </ul>
	 * </li>
	 * </ul>
	 *
	 * @param req the req
	 * @param resp the resp
	 * @param chain the chain
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) req;
		HttpServletResponse hresp = (HttpServletResponse) resp;
		
		String requestURI = hreq.getRequestURI();
		if(FilterUtil.isStaticResourcePath(requestURI) && !isPublicPath(requestURI)) {
			// Ignore such resources
			chain.doFilter(req, resp);
			return;
		}
		
		if (!isDisabled) {
			// get current session or create a new one
			HttpSession session = hreq.getSession();
			LOG.debug("[XSRFProtectionFilter::dofilter() - START]");
			if (!isRestRequest(hreq)) {
				LOG.debug("[XSRFProtectionFilter::dofilter() - request is NOT a REST request");
				if (!isXSRFSessionAlive(session)) {
					LOG.debug(
							"[XSRFProtectionFilter::dofilter() - XSRF token NOT already initialized: creating new one]");
					try {
						String newToken = createNewXSRFToken(session);
						addXSRFCookie(hreq, hresp, newToken);
						LOG.debug(
								"[XSRFProtectionFilter::dofilter() - new XSRF token created and added to session and response]");
					} catch (NoSuchAlgorithmException e) {
						throw new ServletException(e);
					}
				} else {
					LOG.debug("[XSRFProtectionFilter::dofilter() - XSRF token already initialized: nothing to do]");
				}
			} else { // rest request: token must be valid
				LOG.debug("[XSRFProtectionFilter::dofilter() - request is a REST request]");
				if (isXSRFSessionAlive(session)) {
					LOG.debug(
							"[XSRFProtectionFilter::dofilter() - XSRF token already initialized => must check request]");
					if (validXSRFCookieAndHeader(hreq, session)) {
						LOG.debug("[XSRFProtectionFilter::dofilter() - XSRF cookie&header matches in request => OK]");
					} else {
						LOG.error("[XSRFProtectionFilter::dofilter() - Invalid XSRF Header in request]");
						throw new ServletException("Invalid XSRF HEADER");
					}
				} else {
					LOG.error("[XSRFProtectionFilter::dofilter() - XSRF token not already initialized]");
					throw new ServletException("XSRF TOKEN not already initialized");
				}
			}
		}
		setNoCache(hresp);
		chain.doFilter(req, resp);
	}

	/**
	 * Checks whether the requested URI is part of the public path of the application.
	 *
	 * @param requestURI the request URI
	 * @return the evaluation result
	 */
	private boolean isPublicPath(String requestURI) {
		return requestURI != null
				&& publicPath != null
				&& requestURI.startsWith(publicPath)
				&& requestURI.endsWith(".html");
	}

	/**
	 * E' necessario fare in modo che le risorse (pagine, etc) non siano mantenute nella
	 * cache del browser per evitare che, ad un accesso in una sessione successiva, la fase
	 * di ingresso (e quindi di ricreazione del token) venga bypassata. Se cio' accadesse
	 * le richieste REST sarebbero le prime ad arrivare al server e si ricadrebbe nella 
	 * casistica di token non ancora inizializzato.
	 *
	 * @param response the new no cache
	 */
	private void setNoCache(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.
	}

	/**
	 * Checks if is rest request.
	 *
	 * @param hreq the hreq
	 * @return true se il path rappresenta una richiesta alla parte REST dell'applicazione
	 * che per default deve contenere la stringa "restfacade"
	 */
	private boolean isRestRequest(HttpServletRequest hreq) {
		return hreq.getRequestURI() != null && hreq.getRequestURI().contains("restfacade");
	}

	/**
	 * aggiunge alla response il cookie XSRF.
	 *
	 * @param hreq the hreq
	 * @param hresp la response in cui aggiungere il cookie
	 * @param token il valore del cookie
	 */
	private void addXSRFCookie(HttpServletRequest hreq, HttpServletResponse hresp, String token) {
		Cookie c = new Cookie(XSRF_COOKIE_NAME, token);
		// Use the same subpath as used by the session cookie
		String requestURI = hreq.getRequestURI();
		String path = "";
		String[] pieces = requestURI.split("\\/");
		// Extract the first block
		if(pieces.length == 0) {
			path = "/";
		} else if (pieces.length == 1) {
			path = "/" + pieces[0];
		} else if(requestURI.startsWith("/")) {
			path = "/" + pieces[1];
		} else {
			path = "/" + pieces[0];
		}
		
		c.setPath(path);
		c.setSecure(hreq.isSecure());
		// FIXME: add data such as SameSite=Strict as security; these data can only be set through the complete Header Set-Cookie (java does not support those properties on the Cookie Object)
		hresp.addCookie(c);
	}

	/**
	 * crea un nuovo token XSRF.
	 *
	 * @param session the session
	 * @return il token creato
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	private String createNewXSRFToken(HttpSession session) throws NoSuchAlgorithmException {
		SecureRandom random = new SecureRandom();
		String newToken = random.nextLong() + "" + random.nextLong();
		session.setAttribute(XSRF_INTERNAL_TOKEN_NAME, newToken);
		return newToken;
	}
	
	/**
	 * Gets the actual active XSRF token.
	 *
	 * @param session the session
	 * @return il token XSRF presente in sessione
	 */
	private String getActualActiveXSRFToken(HttpSession session) {
		return (String) session.getAttribute(XSRF_INTERNAL_TOKEN_NAME);
	}

	/**
	 * verifica se la richiesta possiede una coppia cookie/header XSRF validi
	 * e coincidenti con quanto memorizzato in sessione (se presente).
	 *
	 * @param hreq la request
	 * @param session la sessione servlet
	 * @return il risultato della verifica
	 */
	private boolean validXSRFCookieAndHeader(HttpServletRequest hreq, HttpSession session) {
		String actualActiveToken = getActualActiveXSRFToken(session);
		String actualRequestHeader = getActualXSRFHeader(hreq);
		String actualRequestCookie = getActualXSRFCookie(hreq);
    if(actualRequestHeader==null) actualRequestHeader = actualRequestCookie; // TODO: temporaneo in attesa della correzione su pslwcl
		LOG.debug("[XSRFProtectionFilter::validXSRFCookieAndHeader() - validXSRFCookieAndHeader actualActiveToken="+actualActiveToken
				+", actualRequestHeader="+actualRequestHeader+", actualRequestCookie="+actualRequestCookie);
		return actualRequestHeader != null && actualActiveToken != null && actualActiveToken.equals(actualRequestHeader)
				&& actualActiveToken.equals(actualRequestCookie);
	}

	/**
	 * Gets the actual XSRF header.
	 *
	 * @param hreq the hreq
	 * @return l'header XSRF se presente nella request
	 */
	private String getActualXSRFHeader(HttpServletRequest hreq) {
		return (String) hreq.getHeader(XSRF_HEADER_NAME);
	}

	/**
	 * Gets the actual XSRF cookie.
	 *
	 * @param hreq la request
	 * @return il valore del cookie XSRF, se presente nella request;
	 * la stringa vuota se invece non presente.
	 */
	private String getActualXSRFCookie(HttpServletRequest hreq) {
		Cookie[] cookies = hreq.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().contentEquals(XSRF_COOKIE_NAME)) {
				return cookies[i].getValue();
			}
		}
		// if not found...
		return "";
	}

	/**
	 * Checks if is XSRF session alive.
	 *
	 * @param session the session
	 * @return true se in sessione e' presente un token valido
	 */
	private boolean isXSRFSessionAlive(HttpSession session) {
		return getActualActiveXSRFToken(session) != null;
	}

	/** di default il filter abilita le verifiche. */
	private boolean isDisabled = false;
	
	/** Checks the publicPath. */
	private String publicPath;

	/**
	 * nome del parametro di inizializzazione che serve per disabilitare
	 * il meccanismo. Serve negli scenari di sviluppo in cui la parte client
	 * non e' deployata nell'ear dei servizi
	 */
	private static final String DISABLED_PARAM_NAME = "disabled";
	
	/** The Constant PUBLICPATH_PARAM_NAME. */
	private static final String PUBLICPATH_PARAM_NAME = "publicPath";

	/**
	 * Inits the.
	 *
	 * @param cfg the cfg
	 * @throws ServletException the servlet exception
	 */
	@Override
	public void init(FilterConfig cfg) throws ServletException {
		isDisabled = Boolean.parseBoolean(cfg.getInitParameter(DISABLED_PARAM_NAME));
		publicPath = cfg.getInitParameter(PUBLICPATH_PARAM_NAME);
	}

}
