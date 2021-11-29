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

import org.apache.log4j.Logger;

import it.csi.iride2.policy.entity.Identita;
import it.csi.iride2.policy.exceptions.MalformedIdTokenException;
import it.csi.pslp.pslweb.util.Constants;

/**
 * Inserisce in sessione:
 * <ul> 
 *  <li>l'identit&agrave; digitale relativa all'utente autenticato.
 *  <li>l'oggetto <code>currentUser</code>
 * </ul>
 * Funge da adapter tra il filter del metodo di autenticaizone previsto e la
 * logica applicativa.
 *
 * @author CSIPiemonte
 */
public class IrideIdAdapterFilter implements Filter {

	/** The Constant IRIDE_ID_SESSIONATTR. */
	public static final String IRIDE_ID_SESSIONATTR = "iride2_id";

	/** The Constant AUTH_ID_MARKER. */
	public static final String AUTH_ID_MARKER = "Shib-Iride-IdentitaDigitale";

	/** The Constant USERINFO_SESSIONATTR. */
	public static final String USERINFO_SESSIONATTR = "appDatacurrentUser";

	/** The Constant LOG. */
	protected static final Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME + ".security");

	/**
	 * Do filter.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @param fchn the fchn
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fchn)
			throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) req;
		if (hreq.getSession().getAttribute(IRIDE_ID_SESSIONATTR) == null) {
			String marker = getToken(hreq);
			if (marker != null) {
				try {
					Identita identita = new Identita(normalizeToken(marker));
					LOG.info("[IrideIdAdapterFilter::doFilter] Inserito in sessione marcatore IRIDE:" + identita);
					hreq.getSession().setAttribute(IRIDE_ID_SESSIONATTR, identita);
					it.csi.pslp.pslweb.dto.be.UserInfo userInfo = new it.csi.pslp.pslweb.dto.be.UserInfo();
					userInfo.setNome(identita.getNome());
					userInfo.setCognome(identita.getCognome());
					userInfo.setEnte("--");
					userInfo.setRuolo("--");
					userInfo.setCodFisc(identita.getCodFiscale());
					userInfo.setLivAuth(identita.getLivelloAutenticazione());
					userInfo.setCommunity(identita.getIdProvider());
					hreq.getSession().setAttribute(USERINFO_SESSIONATTR, userInfo);
				} catch (MalformedIdTokenException e) {
					LOG.error("[IrideIdAdapterFilter::doFilter] " + e.toString(), e);
				}
			} else if (devmode) {
				Identita identita = new Identita();
				LOG.info("[IrideIdAdapterFilter::doFilter] Inserito in sessione marcatore IRIDE di SVILUPPO:" + identita);
				hreq.getSession().setAttribute(IRIDE_ID_SESSIONATTR, identita);
				// devmode e non ho messo nella request il token: simulo accesso 
				it.csi.pslp.pslweb.dto.be.UserInfo userInfo = new it.csi.pslp.pslweb.dto.be.UserInfo();
				userInfo.setNome("Sviluppatore");
				userInfo.setCognome("DEVMODE");
				userInfo.setEnte("--");
				userInfo.setRuolo("--");
				userInfo.setCodFisc("AAAAAA00A11B000J"); //CF fittizio, da valorizzare
				userInfo.setLivAuth(2);
				userInfo.setCommunity("COMMUNITY");
				hreq.getSession().setAttribute(USERINFO_SESSIONATTR, userInfo);
				
			} else {
				// il marcatore deve sempre essere presente altrimenti e' una 
				// condizione di errore (escluse le pagine home e di servizio)
				if (mustCheckPage(hreq.getRequestURI())) {
					LOG.error(
							"[IrideIdAdapterFilter::doFilter] Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
					throw new ServletException(
							"Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
				}
			}
		}

		fchn.doFilter(req, resp);

	}

	/**
	 * Must check page.
	 *
	 * @param requestURI the request URI
	 * @return true, if successful
	 */
	private boolean mustCheckPage(String requestURI) {
	  if(requestURI.contains("pslpublicweb")) return false;
	  if(requestURI.contains("pslphome")) return false;
	  return true;
	}

	/**
	 * Destroy.
	 */
	public void destroy() {
		// NOP
	}

	/** The Constant DEVMODE_INIT_PARAM. */
	private static final String DEVMODE_INIT_PARAM = "devmode";

	/** The devmode. */
	private boolean devmode = false;

	/**
	 * Inits the.
	 *
	 * @param fc the fc
	 * @throws ServletException the servlet exception
	 */
	public void init(FilterConfig fc) throws ServletException {
		String sDevmode = fc.getInitParameter(DEVMODE_INIT_PARAM);
		if ("true".equals(sDevmode)) {
			devmode = true;
		} else {
			devmode = false;
		}
	}

	/**
	 * Gets the token.
	 *
	 * @param httpreq the httpreq
	 * @return the token
	 */
	public String getToken(HttpServletRequest httpreq) {
		String marker = (String) httpreq.getHeader(AUTH_ID_MARKER);
		if (marker == null && devmode) {
			return getTokenDevMode(httpreq);
		} else {
			try {
				// gestione dell'encoding
			  if(marker==null) return null;
				String decodedMarker = new String(marker.getBytes("ISO-8859-1"), "UTF-8");
				return decodedMarker;
			} catch (java.io.UnsupportedEncodingException e) {
				// se la decodifica non funziona comunque sempre meglio restituire 
				// il marker originale non decodificato
				return marker;
			}
		}
	}

	/**
	 * Gets the token dev mode.
	 *
	 * @param httpreq the httpreq
	 * @return the token dev mode
	 */
	private String getTokenDevMode(HttpServletRequest httpreq) {
		String marker = (String) httpreq.getParameter(AUTH_ID_MARKER);
		return marker;
	}

	/**
	 * Normalize token.
	 *
	 * @param token the token
	 * @return the string
	 */
	private String normalizeToken(String token) {
		return token;
	}

}
