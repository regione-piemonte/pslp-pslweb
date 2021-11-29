/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util;

/**
 * The Class FilterUtil.
 */
public class FilterUtil {

    /**
     * valuta un generico criterio stringa.
     *
     * @param crit il criterio
     * @param val  il valore da valutare
     * @param ignoreCase the ignore case
     * @return true, if successful
     */
    private static boolean evalStringCritS(String crit, String val, boolean ignoreCase) {
        if (!ignoreCase) {
            if (val == null) {
                return false;
            } else {
                return val.startsWith(crit);
            }
        } else {
            if (val == null) {
                return false;
            } else {
                return val.toUpperCase().startsWith(crit.toUpperCase());
            }
        }
    }

    /**
     * Eval string crit E.
     *
     * @param crit the crit
     * @param val the val
     * @param ignoreCase the ignore case
     * @return true, if successful
     */
    private static boolean evalStringCritE(String crit, String val, boolean ignoreCase) {
        if (!ignoreCase) {
            if (val == null) {
                return false;
            } else {
                return val.endsWith(crit);
            }
        } else {
            if (val == null) {
                return false;
            } else {
                return val.toUpperCase().endsWith(crit.toUpperCase());
            }
        }
    }

    /**
     * Eval string crit C.
     *
     * @param crit the crit
     * @param val the val
     * @param ignoreCase the ignore case
     * @return true, if successful
     */
    private static boolean evalStringCritC(String crit, String val, boolean ignoreCase) {
        if (!ignoreCase) {
            return crit.indexOf(val) > -1;
        } else {
            if (val == null) {
                return false;
            } else {
                return crit.toUpperCase().indexOf(val.toUpperCase()) > -1;
            }
        }
    }

    /**
     * Eval string crit eq.
     *
     * @param eq the eq
     * @param val the val
     * @return true, if successful
     */
    private static boolean evalStringCritEq(String eq, String val) {
        return val != null && val.equals(eq);
    }

    /**
     * Checks if is static resource path.
     *
     * @param path the path
     * @param nonStaticResourceSuffixes the non static resource suffixes
     * @return true, if is static resource path
     */
    public static boolean isStaticResourcePath(String path, String... nonStaticResourceSuffixes) {
        for (String nonStaticResourceSuffix : nonStaticResourceSuffixes) {
            if (path.endsWith(nonStaticResourceSuffix)) {
                return false;
            }
        }
        return path.contains("/assets/")
                // Files
                || path.endsWith(".html") || path.endsWith(".js") || path.endsWith(".css") || path.endsWith(".ico") || path.endsWith(".svg")
                || path.endsWith(".map")
                // Fonts
                || path.endsWith(".woff2") || path.endsWith(".woff") || path.endsWith(".ttf") || path.endsWith(".eot");
    }
}
