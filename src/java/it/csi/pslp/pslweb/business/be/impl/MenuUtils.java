/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.pslp.pslcommonobj.dto.MessaggioDTO;
import it.csi.pslp.pslcommonobj.dto.ParametroDTO;
import it.csi.pslp.pslweb.dto.be.DomandaDidMsg;
import it.csi.pslp.pslweb.dto.be.DomandeDidPage;
import it.csi.pslp.pslweb.dto.be.ErrorDef;
import it.csi.pslp.pslweb.dto.be.MenuHelpFile;
import it.csi.pslp.pslweb.dto.be.MenuHelpMsg;
import it.csi.pslp.pslweb.dto.be.MenuHelpPage;
import it.csi.pslp.pslweb.dto.be.MenuHelpVideo;
import it.csi.pslp.pslweb.dto.be.MenuHomeCard;
import it.csi.pslp.pslweb.dto.be.RigaDid;
import it.csi.pslp.pslweb.dto.be.RigaMsgHelp;
import it.csi.pslp.pslweb.dto.be.RispostaDidMsg;
import it.csi.pslp.pslweb.dto.be.UserInfo;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.pslp.pslweb.util.ParametroUtils;
import it.csi.silos.jedi.core.DAO;
import it.csi.silos.jedi.core.DAOException;
import it.csi.silos.jedi.core.QueryResult;
import it.csi.silos.jedi.core.RowReader;
import it.csi.silos.silcommon.util.SilCommonUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuUtils.
 */
@Component("menuiUtils")
public class MenuUtils {

    /** The Constant log. */
    protected static final Logger log = Logger.getLogger(Constants.COMPONENT_NAME);

    /** The messaggi utils. */
    @Autowired
    private MessaggiUtils messaggiUtils;

    /** The parametro utils. */
    @Autowired
    private ParametroUtils parametroUtils;

    /** The dao. */
    @Autowired
    private DAO dao;

    /**
     * Restituisce un menu per un codice.
     *
     * @param codMenu the cod menu
     * @return the menu
     * @throws Exception the exception
     */
    public ArrayList<MenuHomeCard> getMenu(String codMenu,  String codFisc) throws Exception {

        ArrayList<MenuHomeCard> array = new ArrayList<MenuHomeCard>();

        if ("OP".equals(codMenu)) {
            boolean isOPERenabled = getParm("PSLP_ACCESSO_OPER");
            if (isOPERenabled) {
                array = menuBackOffice(codFisc);
            }
        } else if ("100".equals(codMenu)) {
            array = menuPrenotazioneCittadino();
        } else {
            array = menuPrincipaleCittadino();
        }
        return array;
    }

    /**
     * @return
     * @throws Exception
     */
    private MenuHomeCard cardMappaCPI() throws Exception {
        MenuHomeCard card = new MenuHomeCard();
        card.setIdCard("5");
        card.setTitolo("Visualizza CPI e Sportelli");
        card.setIcon("fas fa-search-location");
        card.setLink("/mappa");
        MessaggioDTO messaggio4 = messaggiUtils.loadMessaggio("HC015");
        card.setTesto(messaggio4.getTesto());
        card.setUrlImg("assets/im/Puzzle.svg");
        card.setTipoLink("I");
        card.setCodApp("H");
        card.setFlgAccessoAutenticato(false);
        return card;
    }

    /**
     * @return
     * @throws Exception
     */
    private MenuHomeCard cardMenuPrenotazioneCittadino() throws Exception {
        MenuHomeCard card = new MenuHomeCard();
        card.setIdCard("4");
        card.setTitolo("Prenota appuntamento");
        card.setIcon("far fa-calendar-alt");
        card.setLink("/home?id=100");
        MessaggioDTO messaggio4 = messaggiUtils.loadMessaggio("HC014");
        card.setTesto(messaggio4.getTesto());
        card.setUrlImg("assets/im/Calendar.svg");
        card.setTipoLink("E");
        card.setCodApp("H");
        card.setFlgAccessoAutenticato(false);
        return card;
    }

    /**
     * @return
     * @throws Exception
     */
    private MenuHomeCard cardCollocamentoMiratoCittadino() throws Exception {
        MenuHomeCard card = new MenuHomeCard();
        card.setIdCard("3");
        card.setTitolo("Collocamento mirato");
        card.setIcon("far fa-wheelchair");
        MessaggioDTO messaggio4 = messaggiUtils.loadMessaggio("HC039");
        card.setTesto(messaggio4.getTesto());
        card.setUrlImg("assets/im/collocamento.svg");
        card.setLink("/collocamento-mirato-landing");
        card.setTipoLink("E");
        card.setCodApp("F");
        card.setFlgAccessoAutenticato(true);
        return card;
    }

    /**
     * @return
     * @throws Exception
     */
    private MenuHomeCard cardIscrizioneGaranziaGiovani() throws Exception {
        MenuHomeCard card = new MenuHomeCard();
        card.setIdCard("7");
        card.setTitolo("Iscrizione Garanzia Giovani");
        card.setIcon("far fa-wheelchair");
        MessaggioDTO messaggio4 = messaggiUtils.loadMessaggio("HC018");
        card.setTesto(messaggio4.getTesto());
        card.setUrlImg("assets/im/iscrGiovani.svg");
        card.setLink("/iscrizione-garanzia-landing");
        card.setTipoLink("E");
        card.setCodApp("C");
        card.setFlgAccessoAutenticato(true);
        return card;
    }

    /**
     * @return
     * @throws Exception
     */
    private MenuHomeCard cardDichiarazioneImmediataDisponibilita() throws Exception {
        MenuHomeCard card = new MenuHomeCard();
        card.setIdCard("8");
        card.setTitolo("Dichiarazione Immediata Disponibilit&agrave;");
        card.setIcon("far fa-wheelchair");
        MessaggioDTO messaggio4 = messaggiUtils.loadMessaggio("HC017");
        card.setTesto(messaggio4.getTesto());
        card.setUrlImg("assets/im/did.svg");
        card.setLink("/did-landing");
        card.setTipoLink("E");
        card.setCodApp("F");
        card.setFlgAccessoAutenticato(true);
        return card;
    }

    /**
     * @return
     * @throws Exception
     */
    private MenuHomeCard cardAiutoCittadino() throws Exception {
        MenuHomeCard card = new MenuHomeCard();
        card.setIdCard("6");
        card.setTitolo("Hai bisogno di aiuto ?");
        card.setLink("/assistenza");
        card.setIcon("far fa-question-circle");
        MessaggioDTO messaggio4 = messaggiUtils.loadMessaggio("HC016");
        card.setTesto(messaggio4.getTesto());
        card.setUrlImg("assets/im/question.svg");
        card.setTipoLink("I");
        card.setCodApp("H");
        card.setFlgAccessoAutenticato(false);
        return card;
    }

    /**
     * @return
     * @throws Exception
     */
    private MenuHomeCard cardPrenotazioneRedditoCittadinanza() throws Exception {
        MenuHomeCard card = new MenuHomeCard();
        card.setIdCard("1");
        card.setTitolo("Gestione Reddito cittadinanza");
        card.setLink("/cittadinanza-landing");
        MessaggioDTO messaggio2 = messaggiUtils.loadMessaggio("HC007");

        card.setTesto(messaggio2.getTesto());
        card.setUrlImg("assets/im/redditodicittadinanza.svg");
        card.setTipoLink("E");
        card.setCodApp("C");
        card.setFlgAccessoAutenticato(true);
        return card;
    }

    /**
     * @return
     * @throws Exception
     */
    private MenuHomeCard cardPrenotazioneGaranziaGiovani() throws Exception {
        MenuHomeCard card = new MenuHomeCard();
        card.setIdCard("1");
        card.setTitolo("Garanzia Giovani");
        card.setLink("/garanzia-landing");
        MessaggioDTO messaggio1 = messaggiUtils.loadMessaggio("HC006");

        card.setTesto(messaggio1.getTesto());
        card.setUrlImg("assets/im/garanziagiovani.svg");
        card.setTipoLink("E");
        card.setCodApp("C");
        card.setFlgAccessoAutenticato(true);
        return card;
    }

    /**
     * @return
     * @throws Exception
     */
    private MenuHomeCard cardFascicoloCittadino() throws Exception {
        MenuHomeCard card = new MenuHomeCard();
        card.setIdCard("2");
        card.setTitolo("Fascicolo Cittadino");
        card.setLink("/fascicolo-cittadino-landing");
        card.setIcon("fas fa-folder-open");
        MessaggioDTO messaggio2 = messaggiUtils.loadMessaggio("HC010");
        card.setTesto(messaggio2.getTesto());
        card.setUrlImg("assets/im/Folder.svg");
        card.setTipoLink("E");
        card.setCodApp("F");
        card.setFlgAccessoAutenticato(true);
        return card;
    }

    /**
     * @return
     * @throws Exception
     */
    private MenuHomeCard cardGestionePrivacyEMinori() throws Exception {
        MenuHomeCard card = new MenuHomeCard();
        card.setIdCard("1");
        card.setTitolo("Gestione Privacy e minori");
        card.setLink("/privacy-landing");
        card.setIcon("fas fa-unlock-alt");
        MessaggioDTO messaggio3 = messaggiUtils.loadMessaggio("HC013");
        card.setTesto(messaggio3.getTesto());
        card.setUrlImg("assets/im/Lock.svg");
        card.setTipoLink("E");
        card.setCodApp("F");
        card.setFlgAccessoAutenticato(true);
        return card;
    }

    /**
     * @return
     * @throws Exception
     */
    private ArrayList<MenuHomeCard> menuBackOffice(String codFiscale) throws Exception {
        boolean isOperContoTerzi = getParm("OPER_CONTO_TERZI_FLG");
        boolean isOperCalendario = getParm("OPER_CALENDARIO");
        boolean isOperConfigurazioni = getParm("OPER_CONFIGURAZIONI");
        boolean isOperCOMI = getFlgContoTerziCollocamentoMirato(codFiscale );        
        ArrayList<MenuHomeCard> array = new ArrayList<MenuHomeCard>();
        if (isOperContoTerzi) {
            array.add(cardBoContoTerzi());
        }
        if (isOperCalendario) {
            array.add(cardBoCalendario());
        }
        if (isOperConfigurazioni) {
            array.add(cardBoConfigurazioni());
        }          
        if (isOperCOMI) {
            array.add(cardBoContoTerziCollocamentoMirato());
        }
        return array;
    }

    /**
     * @param codFiscale
     *  
     * @return true se previsto che l'operatore possa eseguire la funzione conto terzi per collocamento mirato
     * @throws DAOException
     */
    private boolean getFlgContoTerziCollocamentoMirato(String codFiscale) throws DAOException {
        boolean isOperCOMI = false;
        Map<String, Object> params = new HashMap<>();
        params.put("codFisc", codFiscale);
        String flagSlot = dao.readString("be/impl/MenuApiServiceImpl.flgContoTerziCOMI", params);
        if (flagSlot != null) {
            isOperCOMI = true;
        }
        return isOperCOMI;
    }

    /**
     * @return
     * @throws Exception
     */
    private ArrayList<MenuHomeCard> menuPrenotazioneCittadino() throws Exception {
        ArrayList<MenuHomeCard> array = new ArrayList<MenuHomeCard>();
        array.add(cardPrenotazioneGaranziaGiovani());
        boolean isRDCenabled = getParm("RDC_FLG");
        if (isRDCenabled) {
           array.add(cardPrenotazioneRedditoCittadinanza());
        }
        return array;
    }
    
    /**
     * @return
     * @throws Exception
     */
    private ArrayList<MenuHomeCard> menuPrincipaleCittadino() throws Exception {
        ArrayList<MenuHomeCard> array = new ArrayList<MenuHomeCard>();
        
        boolean isCITTenabled = getParm("PSLP_ACCESSO_CITT");
        boolean isCOMIenabled = getParm("COMI_FLG");

        boolean isIscrGGenabled = getParm("ISCR_GG_FLG");
        boolean isDIDenabled = getParm("DID_FLG");
                
        array.add(cardGestionePrivacyEMinori());
        array.add(cardFascicoloCittadino());
        if (isDIDenabled) {
            array.add(cardDichiarazioneImmediataDisponibilita());
        }
        if (isIscrGGenabled) {
            array.add(cardIscrizioneGaranziaGiovani());
        }
        if (isCOMIenabled) {
            array.add(cardCollocamentoMiratoCittadino());
        }
        if (isCITTenabled) {
            array.add(cardMenuPrenotazioneCittadino());
        }
        array.add(cardMappaCPI());
        array.add(cardAiutoCittadino());
        return array;
    }
    /**
     * @return
     * @throws Exception
     */
    private MenuHomeCard cardBoContoTerziCollocamentoMirato() throws Exception {
        MenuHomeCard card = new MenuHomeCard();
        card.setIdCard("3");
        card.setTitolo("Conto terzi: Collocamento Mirato");
        card.setLink("/conto-terzi/ricerca-collocamento-mirato");
        MessaggioDTO messaggio = messaggiUtils.loadMessaggio("HC040");
        card.setIcon("fas fa-unlock-alt");
        card.setTesto(messaggio.getTesto());
        card.setUrlImg("assets/im/collocamento.svg");
        card.setTipoLink("I");
        card.setCodApp("H");
        card.setFlgAccessoAutenticato(false);
        return card;
    }

    /**
     * @return
     * @throws Exception
     */
    private MenuHomeCard cardBoConfigurazioni() throws Exception {
        MenuHomeCard card = new MenuHomeCard();
        card.setIdCard("3");
        card.setTitolo("Configurazione piattaforma");
        card.setLink("/configurazione");
        MessaggioDTO messaggio3 = messaggiUtils.loadMessaggio("MI020");
        card.setIcon("fas fa-unlock-alt");
        card.setTesto(messaggio3.getTesto());
        card.setUrlImg("assets/im/foto_home_03.jpg");
        card.setTipoLink("I");
        card.setCodApp("H");
        card.setFlgAccessoAutenticato(false);
        return card;
    }

    /**
     * @return MenuHomeCard Gestione Calendario per operatori abilitati
     * @throws Exception
     */
    private MenuHomeCard cardBoCalendario() throws Exception {
        MenuHomeCard card = new MenuHomeCard();
        card.setIdCard("2");
        card.setTitolo("Gestione calendari");
        card.setLink("/calendario/ricerca");
        MessaggioDTO messaggio2 = messaggiUtils.loadMessaggio("MI019");
        card.setIcon("fas fa-unlock-alt");
        card.setTesto(messaggio2.getTesto());
        card.setUrlImg("assets/im/foto_home_02.jpg");
        card.setTipoLink("I");
        card.setCodApp("H");
        card.setFlgAccessoAutenticato(false);
        return card;
    }

    /**
     * @return MenuHomeCard 
     *    Gestione Prenotazione conto terzi per operatore
     * @throws Exception
     */
    private MenuHomeCard cardBoContoTerzi() throws Exception {
        MenuHomeCard card = new MenuHomeCard();
        card.setIdCard("1");
        card.setTitolo("Conto terzi: Prenotazione Incontro");
        card.setLink("/conto-terzi/ricerca");
        MessaggioDTO messaggio = messaggiUtils.loadMessaggio("MI018");
        card.setIcon("fas fa-unlock-alt");
        card.setTesto(messaggio.getTesto());
        card.setUrlImg("assets/im/foto_home_01.jpg");
        card.setTipoLink("I");
        card.setCodApp("H");
        card.setFlgAccessoAutenticato(false);
        return card;
    }

    /**
     * Gets the menu help page.
     *
     * @param codHelp the cod help
     * @return the menu help page
     * @throws Exception the exception
     */
    public MenuHelpPage getMenuHelpPage(String codHelp) throws Exception {
        String stringQuery = "be/impl/MenuApiServiceImpl.loadMenuHelpPage";

        log.info("[MenuApiServiceImpl::loadMenuHelpPage] query da Eseguire =" + stringQuery);

        boolean isCOMIenabled = getParm("COMI_FLG");
        boolean isRDCenabled = getParm("RDC_FLG");
        boolean isISCRenabled = getParm("ISCR_GG_FLG");
        boolean isDIDenabled = getParm("DID_FLG");

        MenuHelpPage result = new MenuHelpPage();
        List<MenuHelpMsg> messaggi = new ArrayList<MenuHelpMsg>();
        List<MenuHelpFile> manuali = new ArrayList<MenuHelpFile>();
        List<MenuHelpVideo> videoTutorial = new ArrayList<MenuHelpVideo>();
        result.setManuali(manuali);
        result.setMessaggi(messaggi);
        result.setVideoTutorial(videoTutorial);

        Map<String, Object> params = new HashMap<>();
        QueryResult<RigaMsgHelp> righeHelp;
        try {
            righeHelp = dao.findAll(stringQuery, params, new MenuHelpRowReader(), 0);
            if (righeHelp == null || righeHelp.size() == 0) {
                ErrorDef err = new ErrorDef();
                err.setCode("400");
                err.setErrorMessage("help menu non trovato ");
                return result;
            }
        } catch (DAOException e) {
            return result;
        }

        for (Iterator<RigaMsgHelp> it = righeHelp.iterator(); it.hasNext();) {
            RigaMsgHelp r = it.next();
            String codice = r.getCodTipoMessaggio();
            if ("HELP_ASSISTENZA".equals(codice)) {
                MenuHelpMsg msg = new MenuHelpMsg();
                msg.setTitolo(r.getIntestazione());
                List<String> l = new ArrayList<String>();
                l.add(r.getTestoLink());
                msg.setContenuto(l);

                messaggi.add(msg);

            } else if (codice != null && codice.startsWith("MANUALI")) {
                if ((!isCOMIenabled && codice.endsWith("_COLL")) || (!isDIDenabled && codice.endsWith("_DID")) || (!isISCRenabled && codice.endsWith("_ISCR"))
                        || (!isRDCenabled && codice.endsWith("_RDC"))) {
                    int a = 0;
                } else {
                    MenuHelpFile man = new MenuHelpFile();
                    man.setFileTitolo(r.getIntestazione());
                    man.setFileUrl(r.getTestoLink());
                    man.setFileCod(r.getCodTipoMessaggio());
                    manuali.add(man);
                }
            } else if (codice != null && codice.startsWith("TUTORIAL")) {
                if ((!isCOMIenabled && codice.endsWith("_COLL")) || (!isDIDenabled && codice.endsWith("_DID")) || (!isISCRenabled && codice.endsWith("_ISCR"))
                        || (!isRDCenabled && codice.endsWith("_RDC"))) {
                    int a = 0;
                } else {
                    boolean found = false;
                    MenuHelpVideo helpVideo = null;
                    for (Iterator<MenuHelpVideo> itMhv = videoTutorial.iterator(); itMhv.hasNext();) {
                        MenuHelpVideo mhv = itMhv.next();
                        if (mhv.getCodice().equals(r.getCodTipoMessaggio())) {
                            found = true;
                            helpVideo = mhv;
                            break;
                        }
                    }
                    if (!found) {
                        helpVideo = new MenuHelpVideo();
                        List<MenuHelpFile> listaLink = new ArrayList<MenuHelpFile>();
                        helpVideo.setCodice(r.getCodTipoMessaggio());
                        helpVideo.setTitolo(r.getTitolo());
                        helpVideo.setContenuto(listaLink);
                        videoTutorial.add(helpVideo);
                    }
                    MenuHelpFile video = new MenuHelpFile();
                    video.setFileTitolo(r.getIntestazione());
                    video.setFileCod(r.getCodTipoMessaggio());
                    video.setFileUrl(r.getTestoLink());
                    helpVideo.getContenuto().add(video);
                }
            }

        }

        return result;
    }

    /**
     * Gets the parm.
     *
     * @param codice the codice
     * @return the parm
     * @throws Exception the exception
     */
    private boolean getParm(String codice) throws Exception {
        ParametroDTO parametroDTO = parametroUtils.getParametroDTO(codice);
        String valoreParametro = (String) SilCommonUtils.nvl(parametroDTO.getValoreParametroExt(), parametroDTO.getValoreParametro());
        boolean flg = ("S".equalsIgnoreCase(valoreParametro) ? true : false);
        return flg;
    }

    /**
     * The Class MenuHelpRowReader.
     */
    private static class MenuHelpRowReader implements RowReader<RigaMsgHelp> {

        /**
         * Row readed.
         *
         * @param rs the rs
         * @return the riga msg help
         * @throws DAOException the DAO exception
         */
        @Override
        public RigaMsgHelp rowReaded(ResultSet rs) throws DAOException {
            RigaMsgHelp p = new RigaMsgHelp();
            try {

                p.setCodTipoMessaggio(rs.getString(1));
                p.setCodMessaggio(rs.getString(2));
                p.setTitolo(rs.getString(3));
                p.setIntestazione(rs.getString(4));
                p.setTestoLink(rs.getString(5));
            } catch (SQLException e) {
                throw new DAOException("Errore nella lettura dei dati della pagina help", e);
            }
            return p;
        }

    }

    /**
     * The Class DomandaDidRowReader.
     */
    private static class DomandaDidRowReader implements RowReader<RigaDid> {

        /**
         * Row readed.
         *
         * @param rs the rs
         * @return the riga did
         * @throws DAOException the DAO exception
         */
        @Override
        public RigaDid rowReaded(ResultSet rs) throws DAOException {
            RigaDid p = new RigaDid();
            try {
                p.setCodMessaggio(rs.getString(1));
                p.setDomanda(rs.getString(2));
                p.setRispostaSi(rs.getString(3));
                p.setRispostaNo(rs.getString(4));
            } catch (SQLException e) {
                throw new DAOException("Errore nella lettura dei dati della pagina help", e);
            }
            return p;
        }

    }

    /**
     * Gets the domande did page.
     *
     * @return the domande did page
     * @throws Exception the exception
     */
    public DomandeDidPage getDomandeDidPage() throws Exception {
        String stringQuery = "be/impl/MenuApiServiceImpl.loadDomandaDidPage";

        log.info("[MenuApiServiceImpl::loadDomandeDidPage] query da Eseguire =" + stringQuery);
        DomandeDidPage result = new DomandeDidPage();

        Map<String, Object> params = new HashMap<>();
        QueryResult<RigaDid> righeDid;
        try {
            righeDid = dao.findAll(stringQuery, params, new DomandaDidRowReader(), 0);
            if (righeDid == null || righeDid.size() == 0) {
                ErrorDef err = new ErrorDef();
                err.setCode("400");
                err.setErrorMessage("help menu non trovato ");
                return result;
            }
        } catch (DAOException e) {
            return result;
        }

        List<DomandaDidMsg> leDomande = new ArrayList<DomandaDidMsg>();

        for (Iterator<RigaDid> it = righeDid.iterator(); it.hasNext();) {
            RigaDid laRiga = it.next();
            DomandaDidMsg laDomanda = new DomandaDidMsg();
            laDomanda.setDescrDomanda(laRiga.getDomanda());
            laDomanda.setCodice(laRiga.getCodMessaggio());
            List<RispostaDidMsg> elencoRisposte = new ArrayList<RispostaDidMsg>();
            RispostaDidMsg unaRisposta = new RispostaDidMsg();
            unaRisposta.setDescrRisposta(laRiga.getRispostaSi());
            unaRisposta.setValore("SI");
            elencoRisposte.add(unaRisposta);
            unaRisposta = new RispostaDidMsg();
            unaRisposta.setDescrRisposta(laRiga.getRispostaNo());
            unaRisposta.setValore("NO");
            elencoRisposte.add(unaRisposta);
            laDomanda.setListaRisposte(elencoRisposte);
            leDomande.add(laDomanda);
        }

        result.setDomanda(leDomande);

        return result;
    }

}
