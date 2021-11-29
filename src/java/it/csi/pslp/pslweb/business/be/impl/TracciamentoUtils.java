/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslcommonobj.dbdef.EventoDBDef;
import it.csi.pslp.pslcommonobj.dto.EventoDTO;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.RestHelper;
import it.csi.silos.jedi.core.DAO;

/**
 * The Class TracciamentoUtils.
 */
@Component("tracciamentoUtils")
public class TracciamentoUtils {

    // Identificativi di eventi censiti in PSLP_D_TIPO_EVENTOe tracciati poi in
    // PSLP_T_EVENTO

    /** The Constant GET_UTENTE. */
    public static final Long GET_UTENTE = 10031L;

    /** The Constant GET_SAP. */
    public static final Long GET_SAP = 10026L;

    /** The Constant SAVE_SAP_INSERIMENTO. */
    public static final Long SAVE_SAP_INSERIMENTO = 10070L;

    /** The Constant SAVE_SAP_MODIFICA. */
    public static final Long SAVE_SAP_MODIFICA = 10041L;

    /** The Constant SAVE_UTENTE. */
    public static final Long SAVE_UTENTE = 20000L;

    /** The Constant UTENTI_A_CARICO. */
    public static final Long UTENTI_A_CARICO = 10015L;

    /** The Constant SAVE_UTENTI_A_CARICO. */
    public static final Long SAVE_UTENTI_A_CARICO = 10043L;

    /** The Constant DATI_RESPONSABILE. */
    public static final Long DATI_RESPONSABILE = 10025L;

    /** The Constant SAVE_DATI_RESPONSABILE. */
    public static final Long SAVE_DATI_RESPONSABILE = 10040L;

    /** The Constant ADESIONE. */
    public static final Long ADESIONE = 10029L;

    /** The Constant PROFILING. */
    public static final Long PROFILING = 10023L;

    /** The Constant CALCOLO_PROFILING. */
    public static final Long CALCOLO_PROFILING = 10000L;

    /** The Constant INCONTRI. */
    public static final Long INCONTRI = 10008L;

    /** The Constant SAVE_INCONTRO. */
    public static final Long SAVE_INCONTRO = 10038L;

    /** The Constant PRIMO_GIORNO_DISPONIBILE. */
    public static final Long PRIMO_GIORNO_DISPONIBILE = 10010l;

    /** The Constant INFORMAZIONI_AGGIUNTIVE. */
    public static final Long INFORMAZIONI_AGGIUNTIVE = 10005L;

    /** The Constant SAVE_INFORMAZIONI_AGGIUNTIVE. */
    public static final Long SAVE_INFORMAZIONI_AGGIUNTIVE = 10039L;

    /** The Constant FIND_SPORTELLI. */
    public static final Long FIND_SPORTELLI = 10013L;

    /** The Constant FIND_SLOTS. */
    public static final Long FIND_SLOTS = 10012L;

    /** The Constant FIND_DOCUMENTI. */
    public static final Long FIND_DOCUMENTI = 10003L;

    /** The Constant FIND_TIPI_DOCUMENTI. */
    public static final Long FIND_TIPI_DOCUMENTI = 10014L;

    /** The Constant STAMPA_ADESIONE. */
    public static final Long STAMPA_ADESIONE = 10001L;

    /** The Constant DOMANDA_RDC. */
    public static final Long DOMANDA_RDC = 10064L;

    /** The Constant GET_CONDIZIONI_OCCUPAZIONALI. */
    public static final Long GET_CONDIZIONI_OCCUPAZIONALI = 10018L;

    /** The Constant GET_GRADI_STUDIO. */
    public static final Long GET_GRADI_STUDIO = 10021L;

    /** The Constant GET_MOTIVI_PRESENZA_IN_ITALIA. */
    public static final Long GET_MOTIVI_PRESENZA_IN_ITALIA = 10022L;

    /** The Constant GET_TITOLI_STUDIO. */
    public static final Long GET_TITOLI_STUDIO = 10030L;

    /** The Constant GET_COMUNI. */
    public static final Long GET_COMUNI = 10017L;

    /** The Constant GET_PROVINCE. */
    public static final Long GET_PROVINCE = 10024L;

    /** The Constant GET_SEDIMI. */
    public static final Long GET_SEDIMI = 10028L;

    /** The Constant GET_NAZIONI. */
    public static final Long GET_NAZIONI = 10007L;

    /** The Constant GET_CITTADINANZE. */
    public static final Long GET_CITTADINANZE = 10002L;

    /** The Constant FIND_MOTIVI_RILASCIO_PERMESSO_SOGGIORNO. */
    public static final Long FIND_MOTIVI_RILASCIO_PERMESSO_SOGGIORNO = 10006L;

    /** The Constant FIND_ELENCO_STATUS_EXTRA_UE. */
    public static final Long FIND_ELENCO_STATUS_EXTRA_UE = 10004L;

    /** The Constant GET_CONFIGURAZIONI_INFO_AGGIUNTIVE. */
    public static final Long GET_CONFIGURAZIONI_INFO_AGGIUNTIVE = 10019L;

    /** The Constant LOAD_DOCUMENTO. */
    public static final Long LOAD_DOCUMENTO = 10033L;

    /** The Constant SAVE_DOCUMENTO. */
    public static final Long SAVE_DOCUMENTO = 10036L;

    /** The Constant SAVE_STATO_DOCUMENTO. */
    public static final Long SAVE_STATO_DOCUMENTO = 10042L;

    /** The Constant GET_QUALIFICHE_PROFESSIONALI. */
    public static final Long GET_QUALIFICHE_PROFESSIONALI = 10065L;

    /** The Constant GET_ELENCHI_DECODIFICHE_SAP. */
    public static final Long GET_ELENCHI_DECODIFICHE_SAP = 10066L;

    /** The Constant COLLOCAMENTO_MIRATO_RIEPILOGO. */
    public static final Long COLLOCAMENTO_MIRATO_RIEPILOGO = 10067L;

    /** The Constant COLLOCAMENTO_MIRATO_SAVE_REDDITO. */
    public static final Long COLLOCAMENTO_MIRATO_SAVE_REDDITO = 10068L;

    /** The Constant COLLOCAMENTO_MIRATO_SAVE_FAMILIARI_A_CARICO. */
    public static final Long COLLOCAMENTO_MIRATO_SAVE_FAMILIARI_A_CARICO = 10069L;

    /** The Constant GET_MESSAGGI. */
    public static final Long GET_MESSAGGI = 10100L;

    /** The Constant SAVE_MESSAGGIO. */
    public static final Long SAVE_MESSAGGIO = 10101L;

    /** The Constant SAVE_CONFIGURAZIONI_INFO_AGGIUNTIVE. */
    public static final Long SAVE_CONFIGURAZIONI_INFO_AGGIUNTIVE = 10102L;

    /** The Constant FIND_CALENDARI. */
    public static final Long FIND_CALENDARI = 10103L;

    /** The Constant GET_AMBITI. */
    public static final Long GET_AMBITI = 10104L;

    /** The Constant GET_ENTI. */
    public static final Long GET_ENTI = 10105L;

    /** The Constant SAVE_CALENDARIO_DATI_GENERALI. */
    public static final Long SAVE_CALENDARIO_DATI_GENERALI = 10106L;

    /** The Constant SAVE_CALENDARIO_MAIL. */
    public static final Long SAVE_CALENDARIO_MAIL = 10107L;

    /** The Constant LOAD_CALENDARIO. */
    public static final Long LOAD_CALENDARIO = 10108L;

    /** The Constant SAVE_CALENDARIO_DATI_OPERATIVI. */
    public static final Long SAVE_CALENDARIO_DATI_OPERATIVI = 10109L;

    /** The Constant IMPOSTA_BLOCCO_CALENDARIO. */
    public static final Long IMPOSTA_BLOCCO_CALENDARIO = 10110L;

    /** The Constant DUPLICA_CALENDARIO. */
    public static final Long DUPLICA_CALENDARIO = 10111L;

    /** The Constant ELIMINA_CALENDARIO. */
    public static final Long ELIMINA_CALENDARIO = 10112L;

    /** The Constant SAVE_CALENDARIO_ECCEZIONI. */
    public static final Long SAVE_CALENDARIO_ECCEZIONI = 10113L;

    /** The Constant SAVE_CALENDARIO_FASCE. */
    public static final Long SAVE_CALENDARIO_FASCE = 10114L;

    /** The Constant GENERA_SLOT. */
    public static final Long GENERA_SLOT = 10115L;

    /** The Constant VERIFICA_VINCOLI_ELIMINAZIONE_CALENDARIO. */
    public static final Long VERIFICA_VINCOLI_ELIMINAZIONE_CALENDARIO = 10116L;

    /** The Constant FIND_INCONTRI_CALENDARIO. */
    public static final Long FIND_INCONTRI_CALENDARIO = 10117L;

    /** The Constant GET_SPORTELLI. */
    public static final Long GET_SPORTELLI = 10118L;

    /** The Constant FIND_SLOT_CALENDARIO. */
    public static final Long FIND_SLOT_CALENDARIO = 10119L;

    /** The Constant LOAD_MESSAGGI_AGGIUNTIVI_AMBITO. */
    public static final Long LOAD_MESSAGGI_AGGIUNTIVI_AMBITO = 10120L;

    /** The Constant SAVE_MESSAGGI_AGGIUNTIVI_AMBITO. */
    public static final Long SAVE_MESSAGGI_AGGIUNTIVI_AMBITO = 10121L;

    /** The Constant ELIMINAZIONE_ECCEZIONE. */
    public static final Long ELIMINAZIONE_ECCEZIONE = 10122L;

    /** The Constant ELIMINAZIONE_FASCIA. */
    public static final Long ELIMINAZIONE_FASCIA = 10123L;

    /** The Constant DUPLICAZIONE_PERIODO. */
    public static final Long DUPLICAZIONE_PERIODO = 10124L;

    /** The Constant APPLICA_DATO_A_CALENDARI. */
    public static final Long APPLICA_DATO_A_CALENDARI = 10125L;

    /** The Constant APPLICA_ECCEZIONE_A_CALENDARI. */
    public static final Long APPLICA_ECCEZIONE_A_CALENDARI = 10126L;

    /** The Constant LOAD_CONFIGURAZIONI_PROFILING_DID. */
    public static final Long LOAD_CONFIGURAZIONI_PROFILING_DID = 10130L;

    /** The Constant ISCRIZIONE_GG. */
    public static final Long ISCRIZIONE_GG = 10131L;

    /** The Constant SAVE_PROFILING_DID. */
    public static final Long SAVE_PROFILING_DID = 10132L;

    /** The Constant SAVE_DID. */
    public static final Long SAVE_DID = 10133L;

    /** The Constant RICERCA_DETTAGLIO_DID. */
    public static final Long RICERCA_DETTAGLIO_DID = 10134L;

    /** The Constant RIEPILOGO_ISCRIZIONE. */
    public static final Long RIEPILOGO_ISCRIZIONE = 10135L;

    /** The Constant ANNULLAMENTO_GG. */
    public static final Long ANNULLAMENTO_GG = 10140L;

    /** The Constant SAVE_DID_CONTROLLER. */
    public static final Long SAVE_DID_CONTROLLER = 10136L;

    /** The Constant SAVE_PROFILING_DID_CONTROLLER. */
    public static final Long SAVE_PROFILING_DID_CONTROLLER = 10137L;

    /** The Constant FIND_DOCUMENTI_DID. */
    public static final Long FIND_DOCUMENTI_DID = 10138L;

    /** The Constant STAMPA_PATTO_DI_SERVIZIO. */
    public static final Long STAMPA_PATTO_DI_SERVIZIO = 10139L;

    /** The Constant LOG_DID. */
    public static final Long LOG_DID = 10141L;

    /** The Constant GET_CPI. */
    public static final Long GET_CPI = 10150L;

    /** The Constant LOAD_CONFIGURAZIONI_PROFILING_L68. */
    public static final Long LOAD_CONFIGURAZIONI_PROFILING_L68 = 10151L;

    /** The Constant STAMPA_ISCRIZIONE_COLLOCAMENTO_MIRATO. */
    public static final Long STAMPA_ISCRIZIONE_COLLOCAMENTO_MIRATO = 10152L;

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The dao. */
    @Autowired
    private DAO dao;

    /** The rest helper. */
    @Autowired
    private RestHelper restHelper;

    /**
     * Traccia ok.
     *
     * @param idTipoEvento the id tipo evento
     * @param httpRequest  the http request
     * @param idUtente     the id utente
     * @param note         the note
     * @param codiceAmbito the codice ambito
     * @return the evento DTO
     */
    public EventoDTO tracciaOk(Long idTipoEvento, HttpServletRequest httpRequest, Long idUtente, String note, String codiceAmbito) {
        return traccia("OK", idTipoEvento, httpRequest, idUtente, note, codiceAmbito);
    }

    /**
     * Traccia ko.
     *
     * @param idTipoEvento the id tipo evento
     * @param httpRequest  the http request
     * @param idUtente     the id utente
     * @param note         the note
     * @param codiceAmbito the codice ambito
     * @return the evento DTO
     */
    public EventoDTO tracciaKo(Long idTipoEvento, HttpServletRequest httpRequest, Long idUtente, String note, String codiceAmbito) {
        return traccia("KO", idTipoEvento, httpRequest, idUtente, note, codiceAmbito);
    }

    /**
     * Traccia.
     *
     * @param esito        the esito
     * @param idTipoEvento the id tipo evento
     * @param httpRequest  the http request
     * @param idUtente     the id utente
     * @param note         the note
     * @param codiceAmbito the codice ambito
     * @return the evento DTO
     */
    private EventoDTO traccia(String esito, Long idTipoEvento, HttpServletRequest httpRequest, Long idUtente, String note, String codiceAmbito) {
        try {
            EventoDTO evento = new EventoDTO();
            evento.setCodEsito(esito);
            evento.setIdTipoEvento(idTipoEvento);
            evento.setIdUtente(idUtente);
            if (note != null && note.length() >= 4000)
                note = note.substring(0, 3999);
            evento.setNote(note);
            evento.setCodiceAmbito(codiceAmbito);
            String codiceFiscaleUtenteCollegato = restHelper.getCodiceFiscaleCurrentUser(httpRequest);
            evento.setCodUserInserim(codiceFiscaleUtenteCollegato);
            evento.setCodUserAggiorn(codiceFiscaleUtenteCollegato);
            evento.setDEvento(new Date());
            evento.setIpChiamante(restHelper.getIncomingIPAddress(httpRequest));
            evento = dao.insert(EventoDBDef.class, evento);
            return evento;
        } catch (Throwable ex) {
            log.error("[TracciamentoUtils::traccia]", ex);
            return null;
        }
    }
}
