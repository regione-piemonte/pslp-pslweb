/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class JacksonConfig.
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonConfig implements ContextResolver<org.codehaus.jackson.map.ObjectMapper> {

    /** The object mapper. */
    private ObjectMapper objectMapper;

    /**
     * Instantiates a new jackson config.
     *
     * @throws Exception the exception
     */
    public JacksonConfig() throws Exception {
        this.objectMapper = new ObjectMapper();

        this.objectMapper.setSerializationInclusion(Inclusion.NON_NULL);

        // converte ogni DateTime in timestamp in formato ISO-8601
        this.objectMapper.configure(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

        // imposto di ignorare proprieta' sconosciute. A volte per prevenire errori
        // passando classi da pslwcl che estendono da decodifica
        this.objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // permette di serializzare automaticamente in snake_case le property che sono
        // in camelCase, senza dover specificare le annotation sulle singole property
        // this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        // permette di escludere dal JSON le proprieta' con valore nullo
        this.objectMapper.setSerializationInclusion(Inclusion.NON_EMPTY);

        // AGGIUNTA MODIFICA PER PROBLEMATICA DELLE DATE SU SAFARI
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        this.objectMapper.setDateFormat(df);
    }

    /**
     * Gets the context.
     *
     * @param objectType the object type
     * @return the context
     */
    public ObjectMapper getContext(Class<?> objectType) {
        return objectMapper;
    }
}
