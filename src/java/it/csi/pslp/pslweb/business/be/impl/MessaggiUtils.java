/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslcommonobj.dbdef.MessaggioDBDef;
import it.csi.pslp.pslcommonobj.dto.MessaggioDTO;
import it.csi.pslp.pslcommonobj.filter.MessaggioFilter;
import it.csi.pslp.pslweb.util.Cache;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.silos.jedi.core.DAO;

/**
 * The Class MessaggiUtils.
 */
@Component("messaggiUtils")
public class MessaggiUtils {

    /** The Constant ME001. */
    public static final String ME001 = "ME001";

    /** The Constant ME002. */
    public static final String ME002 = "ME002";

    /** The Constant ME003. */
    public static final String ME003 = "ME003";

    /** The Constant ME004. */
    public static final String ME004 = "ME004";

    /** The Constant ME005. */
    public static final String ME005 = "ME005";

    /** The Constant ME006. */
    public static final String ME006 = "ME006";

    /** The Constant ME007. */
    public static final String ME007 = "ME007";

    /** The Constant ME008. */
    public static final String ME008 = "ME008";

    /** The Constant ME009. */
    public static final String ME009 = "ME009";

    /** The Constant ME010. */
    public static final String ME010 = "ME010";

    /** The Constant ME011. */
    public static final String ME011 = "ME011";

    /** The Constant ME012. */
    public static final String ME012 = "ME012";

    /** The Constant ME013. */
    public static final String ME013 = "ME013";

    /** The Constant ME014. */
    public static final String ME014 = "ME014";

    /** The Constant ME015. */
    public static final String ME015 = "ME015";

    /** The Constant ME016. */
    public static final String ME016 = "ME016";

    /** The Constant ME017. */
    public static final String ME017 = "ME017";

    /** The Constant ME018. */
    public static final String ME018 = "ME018";

    /** The Constant ME019. */
    public static final String ME019 = "ME019";

    /** The Constant ME020. */
    public static final String ME020 = "ME020";

    /** The Constant ME021. */
    public static final String ME021 = "ME021";

    /** The Constant ME022. */
    public static final String ME022 = "ME022";

    /** The Constant ME023. */
    public static final String ME023 = "ME023";

    /** The Constant ME024. */
    public static final String ME024 = "ME024";

    /** The Constant ME025. */
    public static final String ME025 = "ME025";

    /** The Constant ME026. */
    public static final String ME026 = "ME026";

    /** The Constant ME027. */
    public static final String ME027 = "ME027";

    /** The Constant ME028. */
    public static final String ME028 = "ME028";

    /** The Constant ME029. */
    public static final String ME029 = "ME029";

    /** The Constant ME030. */
    public static final String ME030 = "ME030";

    /** The Constant ME048. */
    public static final String ME048 = "ME048";

    /** The Constant ME049. */
    public static final String ME049 = "ME049";

    /** The Constant ME050. */
    public static final String ME050 = "ME050";

    /** The Constant ME051. */
    public static final String ME051 = "ME051";

    /** The Constant ME052. */
    public static final String ME052 = "ME052";

    /** The Constant ME053. */
    public static final String ME053 = "ME053";

    /** The Constant ME054. */
    public static final String ME054 = "ME054";

    /** The Constant ME055. */
    public static final String ME055 = "ME055";

    /** The Constant ME058. */
    public static final String ME058 = "ME058";

    /** The Constant ME059. */
    public static final String ME059 = "ME059";

    /** The Constant ME069. */
    public static final String ME069 = "ME069";

    /** The Constant ME073. */
    public static final String ME073 = "ME073";

    /** The Constant ME074. */
    public static final String ME074 = "ME074";

    /** The Constant ME077. */
    public static final String ME077 = "ME077";

    /** The Constant ST001. */
    public static final String ST001 = "ST001";

    /** The Constant ST002. */
    public static final String ST002 = "ST002";

    /** The Constant ST003. */
    public static final String ST003 = "ST003"; // Stampa riepilogo Adesione Garanzia Giovani - Privacy

    /** The Constant ST004. */
    public static final String ST004 = "ST004"; // Stampa riepilogo Domanda Reddito di Cittadinanza - Privacy

    /** The Constant ME145. */
    public static final String ME145 = "ME145";

    /** The Constant ME137. */
    public static final String ME137 = "ME137";

    /** The Constant ME148. */
    public static final String ME148 = "ME148";

    /** The Constant ME152. */
    public static final String ME152 = "ME152"; // profiling

    /** The Constant ME143. */
    public static final String ME143 = "ME143"; // eta non coerente per GG

    /** The Constant ME144. */
    public static final String ME144 = "ME144"; // adesione altra regione

    /** The Constant MI051. */
    public static final String MI051 = "MI051"; // eta non compresa

    /** The Constant MI050. */
    public static final String MI050 = "MI050"; // domicilio fuori regione

    /** The Constant MI041. */
    public static final String MI041 = "MI041"; // iscrizione ok

    /** The Constant MI043. */
    public static final String MI043 = "MI043"; // annullamento adesione ok

    /** The Constant MI055. */
    public static final String MI055 = "MI055"; // al posto di me021

    /** The Constant MI044. */
    public static final String MI044 = "MI044";

    /** The Constant MI045. */
    public static final String MI045 = "MI045";

    /** The Constant MI046. */
    public static final String MI046 = "MI046";

    /** The Constant MI047. */
    public static final String MI047 = "MI047";

    /** The Constant ME153. */
    public static final String ME153 = "ME153";

    /** The Constant ME154. */
    public static final String ME154 = "ME154";

    /** The Constant ME154. */
    public static final String ME175 = "ME175";

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The dao. */
    @Autowired
    private DAO dao;

    /**
     * Restituisce un messaggio valido per un codice.
     *
     * @param codMessaggio the cod messaggio
     * @return the messaggio DTO
     * @throws Exception the exception
     */
    public MessaggioDTO loadMessaggio(String codMessaggio) throws Exception {
        List<MessaggioDTO> elencoMsg = getMessaggi();
        if (elencoMsg != null) {
            for (MessaggioDTO msg1 : elencoMsg) {
                if (codMessaggio != null && codMessaggio.equals(msg1.getCodMessaggio())) {
                    return msg1;
                }
            }
        }

        Date now = new Date();
        MessaggioFilter filter1 = new MessaggioFilter();
        filter1.getCodMessaggio().eq(codMessaggio);
        filter1.getDInizio().le(now);
        MessaggioFilter filter2 = new MessaggioFilter();
        filter2.getDFine().eq(null);
        MessaggioFilter filter3 = new MessaggioFilter();
        filter3.getDFine().ge(now);
        MessaggioFilter filter = (MessaggioFilter) filter1.and(filter2.or(filter3));
        MessaggioDTO messaggioDTO = dao.findFirst(MessaggioDBDef.class, filter);
        if (elencoMsg != null) {
            elencoMsg.add(messaggioDTO);
            Cache.getInstance().putEl(Cache.MESSAGGI, elencoMsg);
        }
        return messaggioDTO;
    }

    /**
     * Restituisce un messaggio valido per un codice o un generico messaggio di
     * errore se non viene trovato il messaggio corrispondente al codice indicato.
     *
     * @param codMessaggio the cod messaggio
     * @return the messaggio DTO
     */
    public MessaggioDTO loadMessaggioErrore(String codMessaggio) {
        MessaggioDTO messaggioDTO = null;
        try {
            messaggioDTO = loadMessaggio(codMessaggio);
        } catch (Throwable ex) {
            log.error("[MessaggiUtils::loadMessaggioErrore]", ex);
        }
        if (messaggioDTO == null) {
            messaggioDTO = new MessaggioDTO();
            messaggioDTO.setCodTipoMessaggio("MSGE");
            messaggioDTO.setCodAmbito("TRV");
            messaggioDTO.setIntestazione("Messaggio di errore bloccante");
            messaggioDTO.setTesto(
                    "Cortesemente, si richiede di effettuare nuovamente l'operazione, in quanto si e' verificato un problema sul portale. Se persistesse il problema, e' necessario recarsi presso il Centro per l'Impiego di riferimento domiciliare");
        }
        return messaggioDTO;
    }

    private List<MessaggioDTO> getMessaggi() {
        List<MessaggioDTO> elenco = Cache.getInstance().getEl(Cache.MESSAGGI);
        if (elenco != null)
            return elenco;
        elenco = new ArrayList<>();
        try {
            Date now = new Date();
            MessaggioFilter filter1 = new MessaggioFilter();

            filter1.getDInizio().le(now);
            MessaggioFilter filter2 = new MessaggioFilter();
            filter2.getDFine().eq(null);
            MessaggioFilter filter3 = new MessaggioFilter();
            filter3.getDFine().ge(now);
            MessaggioFilter filter = (MessaggioFilter) filter1.and(filter2.or(filter3));

            for (MessaggioDTO msg : dao.findAll(MessaggioDBDef.class, filter, 0)) {
                elenco.add(msg);
            }
        } catch (Throwable ex) {
            log.error("[MessaggiUtils::loadMessaggioErrore]", ex);
        }
        Cache.getInstance().putEl(Cache.MESSAGGI, elenco);
        return elenco;
    }

}
