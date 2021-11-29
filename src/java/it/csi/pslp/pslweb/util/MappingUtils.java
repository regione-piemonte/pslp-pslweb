/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * The Class MappingUtils.
 */
public final class MappingUtils {

	/**
	 * To date.
	 *
	 * @param xmlDate the xml date
	 * @return the date
	 */
	public static Date toDate(XMLGregorianCalendar xmlDate) {
		if(xmlDate == null) {
			return null;
		}
		return xmlDate.toGregorianCalendar().getTime();
	}
	
	/**
	 * To XML gregorian calendar.
	 *
	 * @param date the date
	 * @return the XML gregorian calendar
	 * @throws DatatypeConfigurationException the datatype configuration exception
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {
		return toXMLGregorianCalendar(date, DatatypeFactory.newInstance());
	}
	
	/**
	 * To XML gregorian calendar.
	 *
	 * @param date the date
	 * @param datatypeFactory the datatype factory
	 * @return the XML gregorian calendar
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date, DatatypeFactory datatypeFactory) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		return datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
	}
	
	/**
	 * To boolean.
	 *
	 * @param str the str
	 * @param yes the yes
	 * @param no the no
	 * @return the boolean
	 */
	public static Boolean toBoolean(String str, String yes, String no) {
		return yes.equals(str) ? Boolean.TRUE : no.equals(str) ? Boolean.FALSE : null;
	}
}
