/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util;

import com.google.gson.GsonBuilder;

/**
 * The Class GsonUtils.
 */
public class GsonUtils {
  
  /**
   * Converte una stringa Gson in una istanza di un oggetto della classe specificata in input.
   *
   * @param <T> the generic type
   * @param gsonString the gson string
   * @param objectClass the object class
   * @return the t
   */
  public static <T> T toGsonObject(String gsonString, Class<T> objectClass) {
    GsonBuilder gb = getDefaultGsonBuilder();
    return gb.create().fromJson(gsonString,objectClass);
  }
  
  
  public static <T> T toGsonObjectRest(String gsonString, Class<T> objectClass) {
	    GsonBuilder gb = new GsonBuilder();
	    gb.setPrettyPrinting();
	    //gb.setDateFormat("dd/MM/yyyy HH:mm:ss SSS"); //selezionare il formato voluto
	    //gb.setDateFormat("dd/MM/yyyy HH:mm:ss"); //selezionare il formato voluto
	    gb.setDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //selezionare il formato voluto
	    return gb.create().fromJson(gsonString,objectClass);
	  }
  
  /**
   * Converte un oggetto in una sua rappresentazione in stringa Gson.
   *
   * @param o the o
   * @return the string
   */
  public static String toGsonString(Object o) {
    GsonBuilder gb = getDefaultGsonBuilder();
    return gb.create().toJson(o);
  }


  /**
   * Regole di base per le conversione da e per stringa gson.
   *
   * @return the default gson builder
   */
  protected static GsonBuilder getDefaultGsonBuilder() {
    GsonBuilder gb = new GsonBuilder();
    gb.setPrettyPrinting();
    //gb.setDateFormat("dd/MM/yyyy HH:mm:ss SSS"); //selezionare il formato voluto
    gb.setDateFormat("dd/MM/yyyy HH:mm:ss"); //selezionare il formato voluto
    //gb.serializeNulls(); //per default  non stampa i campi nulli, usare questa istruzione 
    return gb;
  }
  
  /**
   * Remap object.
   *
   * @param <T> the generic type
   * @param sourceObj the source obj
   * @param destObjClass the dest obj class
   * @return the t
   */
  /*
   * Converte un oggetto sourceObj in rappresentazione gson e la ripristina sull'oggetto destObject.
   * In pratica esegue un remapping dei campi uguali su oggetti diversi
   */
  public static <T> T remapObject(Object sourceObj, Class<T> destObjClass){
    return toGsonObject(toGsonString(sourceObj),destObjClass);
  }

}
