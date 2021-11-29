/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;

import it.csi.pslp.pslweb.business.be.CurrentUserApi;
import it.csi.pslp.pslweb.dto.be.UserInfo;
import it.csi.pslp.pslweb.filter.IrideIdAdapterFilter;
import it.csi.pslp.pslweb.filter.XSRFProtectionFilter;
import it.csi.pslp.pslweb.util.Constants;

/**
 * The Class CurrentUserApiServiceImpl.
 */
public class CurrentUserApiServiceImpl implements CurrentUserApi {

    /** The Constant LOG. */
    protected static final Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME + ".CurrentUserApiServiceImpl");

    /**
     * Gets the current user.
     *
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the current user
     */
    @Override
    public Response getCurrentUser(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        LOG.info("[CurrentUserApiServiceImpl::getCurrentUser] user in session "
                + httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR));
        UserInfo ui = new UserInfo();
        if (httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR) != null) {
            ui = (UserInfo) httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
        }
        return Response.ok(ui).build();
    }

    /**
     * Gets the user roles.
     *
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the user roles
     */
    @Override
    public Response getUserRoles(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

        return null;
    }

    /**
     * Checks if is user in role.
     *
     * @param role the role
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    public Response isUserInRole(String role, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

        return null;
    }

    /**
     * Gets the menu structure for user.
     *
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the menu structure for user
     */
    @Override
    public Response getMenuStructureForUser(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

        return null;
    }

    /**
     * Gets the screen by cod.
     *
     * @param screen the screen
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the screen by cod
     */
    @Override
    public Response getScreenByCod(String screen, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

        return null;
    }

    /**
     * Gets the widget.
     *
     * @param screen the screen
     * @param widget the widget
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the widget
     */
    @Override
    public Response getWidget(String screen, String widget, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

        return null;
    }

    /**
     * Logout.
     *
     * @param securityContext the security context
     * @param httpHeaders the http headers
     * @param httpRequest the http request
     * @return the response
     */
    @Override
    public Response logout(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // Delete cookie for XSRF
        Cookie[] cookies = httpRequest.getCookies();
        NewCookie newCookie = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (XSRFProtectionFilter.XSRF_COOKIE_NAME.equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    newCookie = new NewCookie(cookie.getName(), cookie.getValue(), cookie.getPath(), cookie.getDomain(), cookie.getVersion(),
                            cookie.getComment(), 0, cookie.getSecure());
                }
            }
        }
        ResponseBuilder rb = Response.ok("\"OK\"");
        if (newCookie != null) {
            rb = rb.cookie(newCookie);
        }
        return rb.build();
    }

}
