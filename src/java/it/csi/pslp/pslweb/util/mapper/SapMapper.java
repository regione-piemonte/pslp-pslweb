/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.csi.pslp.pslweb.business.integration.AdapterSilpsvdeWSImpl;
import it.csi.pslp.pslweb.business.integration.AdapterSilpsvinWSImpl;
import it.csi.pslp.pslweb.business.integration.ManagerConversioneIndirizzo;
import it.csi.pslp.pslweb.dto.be.Comune;
import it.csi.pslp.pslweb.dto.be.Ente;
import it.csi.pslp.pslweb.dto.be.Esito;
import it.csi.pslp.pslweb.dto.be.EsperienzaLavoro;
import it.csi.pslp.pslweb.dto.be.Indirizzo;
import it.csi.pslp.pslweb.dto.be.Luogo;
import it.csi.pslp.pslweb.dto.be.Nazione;
import it.csi.pslp.pslweb.dto.be.Provincia;
import it.csi.pslp.pslweb.dto.be.Recapito;
import it.csi.pslp.pslweb.dto.be.SchedaAnagraficoProfessionale;
import it.csi.pslp.pslweb.dto.be.Sedime;
import it.csi.silos.silcommon.dati.anagrafe.DatiAnagraficiPersonaDTO;
import it.csi.silos.silcommon.dati.common.IndirizzoDTO;
import it.csi.silos.silcommon.dati.common.LuogoDTO;
import it.csi.silos.silcommon.dati.common.RecapitoDTO;
import it.csi.silos.silcommon.dati.lavoratore.curriculum.AbilitazioniPatentiDTO;
import it.csi.silos.silcommon.dati.lavoratore.curriculum.ConoscenzaInformaticaDTO;
import it.csi.silos.silcommon.dati.lavoratore.curriculum.EsperienzaLavorativaDTO;
import it.csi.silos.silcommon.dati.lavoratore.curriculum.FormazioneDTO;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.AllegatoSapDTO;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.DatiAnagraficiSapDTO;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.DatiInvioSapDTO;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.PoliticheAttiveSapDTO;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.SchedaAnagraficoProfessionaleDTO;
import it.csi.silos.silcommon.generic.CallInfoDTO;
import it.csi.silos.silcommon.gestionedecodifiche.AlboDTO;
import it.csi.silos.silcommon.gestionedecodifiche.CittadinanzaDTO;
import it.csi.silos.silcommon.gestionedecodifiche.ComuneDTO;
import it.csi.silos.silcommon.gestionedecodifiche.CpiDTO;
import it.csi.silos.silcommon.gestionedecodifiche.LinguaDTO;
import it.csi.silos.silcommon.gestionedecodifiche.NazioneDTO;
import it.csi.silos.silcommon.gestionedecodifiche.PatenteDTO;
import it.csi.silos.silcommon.gestionedecodifiche.ProvinciaDTO;
import it.csi.silos.silcommon.gestionedecodifiche.TitoloStudioDTO;
import it.csi.silos.silcommon.util.SilCommonUtils;
import it.csi.silpcommonobj.dati.common.IndirizzoSilpDTO;
import it.csi.silpservizi.sesp.bo.sap.SchedaAnagraficoProfessionaleLavoratoreDTO;

/**
 * Classe per mappare da oggetto di tipo scheda professionale da PSLP (client e
 * web) a oggetto delle silcommon e viceversa.
 *
 * @author 1871
 */
public class SapMapper extends BaseMapper<SchedaAnagraficoProfessionale, SchedaAnagraficoProfessionaleDTO> {

    /** The dm. */
    private final DecodificaMapper dm = new DecodificaMapper();
    
    /** The lm. */
    private final LuogoMapper lm = new LuogoMapper();
    
    /** The im. */
    private final IndirizzoMapper im = new IndirizzoMapper();
    
    /** The pm. */
    private final PermessoDiSoggiornoMapper pm = new PermessoDiSoggiornoMapper();
    
    /** The am. */
    private final DatiAmministrativiMapper am = new DatiAmministrativiMapper();
    
    /** The em. */
    private final EsperienzeLavoroMapper em = new EsperienzeLavoroMapper();
    
    /** The tm. */
    private final TitoloDiStudioMapper tm = new TitoloDiStudioMapper();
    
    /** The fm. */
    private final FormazioneProfessionaleMapper fm = new FormazioneProfessionaleMapper();
    
    /** The sm. */
    private final LingueStraniereMapper sm = new LingueStraniereMapper();
    
    /** The mm. */
    private final ConoscenzeInformaticheMapper mm = new ConoscenzeInformaticheMapper();
    
    /** The vm. */
    private final PoliticheAttiveMapper vm = new PoliticheAttiveMapper();
    
    /** The patm. */
    private final PatenteMapper patm = new PatenteMapper();
    
    /** The alm. */
    private final AlboMapper alm = new AlboMapper();

    /**
     * Map campi identificativi silp.
     *
     * @param in the in
     * @param out the out
     */
    public void mapCampiIdentificativiSilp(SchedaAnagraficoProfessionaleLavoratoreDTO in, SchedaAnagraficoProfessionale out) {
        if (in != null && out != null) {
            out.setIdSilLavAnagrafica(in.getIdLavoratore());
            out.setIdInterscambioSap(in.getIdInterscambio());
        }
    }

    /**
     * Instantiate.
     *
     * @return the scheda anagrafico professionale
     */
    @Override
    protected SchedaAnagraficoProfessionale instantiate() {
        return new SchedaAnagraficoProfessionale();
    }

    /**
     * Instantiate reverse.
     *
     * @return the scheda anagrafico professionale DTO
     */
    @Override
    protected SchedaAnagraficoProfessionaleDTO instantiateReverse() {
        return new SchedaAnagraficoProfessionaleDTO();
    }

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends SchedaAnagraficoProfessionaleDTO> C map(SchedaAnagraficoProfessionale in, C out) {
        throw new UnsupportedOperationException();
    }

    /** The Constant DATI_ANAGRAFICI. */
    public static final String DATI_ANAGRAFICI = "DATI_ANAGRAFICI";
    
    /** The Constant DATI_AMMINISTRATIVI. */
    public static final String DATI_AMMINISTRATIVI = "DATI_AMMINISTRATIVI";
    
    /** The Constant ESPERIENZE_LAVORO. */
    public static final String ESPERIENZE_LAVORO = "ESPERIENZE_LAVORO";
    
    /** The Constant ISTRUZIONE. */
    public static final String ISTRUZIONE = "ISTRUZIONE";
    
    /** The Constant FORMAZIONE_PROFESSIONALE. */
    public static final String FORMAZIONE_PROFESSIONALE = "FORMAZIONE_PROFESSIONALE";
    
    /** The Constant LINGUE_STRANIERE. */
    public static final String LINGUE_STRANIERE = "LINGUE_STRANIERE";
    
    /** The Constant CONOSCENZE_INFORMATICHE. */
    public static final String CONOSCENZE_INFORMATICHE = "CONOSCENZE_INFORMATICHE";
    
    /** The Constant ABILITAZIONI_E_PATENTI. */
    public static final String ABILITAZIONI_E_PATENTI = "ABILITAZIONI_E_PATENTI";
    
    /** The Constant POLITICHE_ATTIVE. */
    public static final String POLITICHE_ATTIVE = "POLITICHE_ATTIVE";

    /**
     * Valorizza la SAP con i dati modificati da PSLP.
     *
     * @param in                  la SAP modificata da PSLP, contenente solo le
     *                            sezioni modificate
     * @param out                 la SAP letta da SILP, in cui vengono sovrascritte
     *                            le sezioni modificate
     * @param tipoVariazione the tipo variazione
     * @param sezioni             le sezioni della SAP da sovrascrivere
     * @param codiceFiscaleUtente utente che ha eseguito la modifica su PSLP
     *                            (cittadino, tutore o operatore)
     * @param codiceAmbito the codice ambito
     * @return the scheda anagrafico professionale DTO
     * @throws Exception the exception
     */
    public SchedaAnagraficoProfessionaleDTO map(SchedaAnagraficoProfessionale in, SchedaAnagraficoProfessionaleDTO out, String tipoVariazione,
            Set<String> sezioni, String codiceFiscaleUtente, String codiceAmbito) throws Exception {

        if (out == null) {
            out = new SchedaAnagraficoProfessionaleDTO();
        }

        // Dati comuni
        if (out.getDatiInvio() == null) {
            out.setDatiInvio(new DatiInvioSapDTO());
        }

        // Controllo di sicurezza per evitare di modificare sap errate caricate di altri
        // id (specie nei test)
        if (in.getIdentificativoSap() != null && out.getDatiInvio() != null && out.getDatiInvio().getIdentificativoSap() != null) {
            if (!in.getIdentificativoSap().equals(out.getDatiInvio().getIdentificativoSap())) {
                throw new UnsupportedOperationException("Attenzione si sta cercando di modificare una sap che ha identificativo diverso identificativoPSLP="
                        + in.getIdentificativoSap() + " identificativoSILP=" + out.getDatiInvio().getIdentificativoSap());
            }
        }

        out.setCaller(new CallInfoDTO());
        out.getCaller().setCodApplicativoChiamante(CallInfoDTO.CODICE_APPLICATIVO_PSLWEB);
        out.getCaller().setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
        out.getCaller().setCodiceFiscaleOperatore(codiceFiscaleUtente);
        out.getCaller().setCodiceAmbito(codiceAmbito);

        out.getDatiInvio().setIdentificativoSap(in.getIdentificativoSap());
        out.getDatiInvio().setTipoVariazione(tipoVariazione);
        out.getDatiInvio().setDataUltimoAggiornamento(new Date());
        out.getDatiInvio().setDataDiNascita(in.getDataDiNascita());
        CpiDTO cpiTitolare = new CpiDTO();
        Ente enteTitolare = findEnteTitolare(in, codiceFiscaleUtente);
        if (enteTitolare != null) {
            cpiTitolare.setCodiceMinisteriale(enteTitolare.getCodiceIntermediario());
        } else {
            cpiTitolare.setCodiceMinisteriale(in.getEnteTitolare().getCodiceMinisteriale());
        }
        out.getDatiInvio().setEnteTitolare(cpiTitolare);

        // Dati Anagrafici
        if (sezioni.contains(DATI_ANAGRAFICI)) {
            // Dati Personali
            if (out.getDatiAnagrafici() == null) {
                out.setDatiAnagrafici(new DatiAnagraficiSapDTO());
            }
            DatiAnagraficiSapDTO datiAnagrafici = out.getDatiAnagrafici();
            if (datiAnagrafici.getDatiPersonali() == null) {
                datiAnagrafici.setDatiPersonali(new DatiAnagraficiPersonaDTO());
            }
            DatiAnagraficiPersonaDTO datiPersonali = datiAnagrafici.getDatiPersonali();
            datiPersonali.setCodiceFiscale(in.getCodiceFiscale());
            datiPersonali.setCognome(in.getCognome());
            datiPersonali.setNome(in.getNome());
            datiPersonali.setSesso(in.getSesso());
            datiPersonali.setDataNascita(in.getDataDiNascita());
            datiPersonali.setCittadinanza(new CittadinanzaDTO());
            datiPersonali.getCittadinanza().setCodiceMinisteriale(in.getCodiceMinisterialeCittadinanza());
            datiPersonali.getCittadinanza().setDescrizione(in.getCittadinanza());
            datiPersonali.setLuogoNascita(lm.map(in.getLuogoDiNascita()));

            // Residenza
            datiAnagrafici.setResidenza(im.map(in.getResidenza()));

            // Domicilio
            datiAnagrafici.setDomicilio(im.map(in.getDomicilio()));

            // Recapiti
            RecapitoDTO recapito = new RecapitoDTO();
            datiAnagrafici.setRecapiti(recapito);
            if (in.getRecapito() != null) {
                recapito.setCellulare(getValoreONullSeVuoto(in.getRecapito().getCellulare()));
                recapito.setEmail(getValoreONullSeVuoto(in.getRecapito().getEmail()));
                recapito.setFax(getValoreONullSeVuoto(in.getRecapito().getFax()));
                recapito.setTelefono(getValoreONullSeVuoto(in.getRecapito().getTelefono()));
                recapito.setWeb(getValoreONullSeVuoto(in.getRecapito().getWeb()));
                recapito.setIndirizzo(im.map(in.getRecapito().getIndirizzo()));
            }

            // Permesso di Soggiorno
            datiAnagrafici.setDatiStranieri(pm.map(in.getPermessoDiSoggiorno()));
        }

        // Dati Amministrativi
        if (sezioni.contains(DATI_AMMINISTRATIVI)) {
            out.setDatiAmministrativi(am.map(in.getDatiAmministrativi()));
        }

        if (sezioni.contains(ESPERIENZE_LAVORO)) {
            List<EsperienzaLavorativaDTO> espLav = new ArrayList<>();
            for (EsperienzaLavoro e : in.getEsperienzeDiLavoro()) {
                if (e.isEsperienzaDichiarata()) {
                    espLav.add(em.map(e));
                }
            }
            out.setListEsperienzeLavoro(espLav.toArray(new EsperienzaLavorativaDTO[0]));
        }

        AllegatoSapDTO allegato = out.getAllegato();
        if (sezioni.contains(ISTRUZIONE) || sezioni.contains(FORMAZIONE_PROFESSIONALE) || sezioni.contains(LINGUE_STRANIERE)
                || sezioni.contains(CONOSCENZE_INFORMATICHE) || sezioni.contains(ABILITAZIONI_E_PATENTI)) {
            if (allegato == null) {
                allegato = new AllegatoSapDTO();
                out.setAllegato(allegato);
            }
        }

        if (sezioni.contains(ISTRUZIONE)) {
            allegato.setListTitoliStudio(tm.mapList(in.getTitoliDiStudio()).toArray(new TitoloStudioDTO[0]));
        }

        if (sezioni.contains(FORMAZIONE_PROFESSIONALE)) {
            allegato.setListFormazioneProfessionale(fm.mapList(in.getFormazioneProfessionaleCorsi()).toArray(new FormazioneDTO[0]));
        }

        if (sezioni.contains(LINGUE_STRANIERE)) {
            allegato.setListLingueStraniere(sm.mapList(in.getLingueStraniere()).toArray(new LinguaDTO[0]));
        }

        if (sezioni.contains(CONOSCENZE_INFORMATICHE)) {
            allegato.setListConoscenzeInformatiche(mm.mapList(in.getConoscenzeInformatiche()).toArray(new ConoscenzaInformaticaDTO[0]));
        }

        if (sezioni.contains(ABILITAZIONI_E_PATENTI)) {
            AbilitazioniPatentiDTO altreInformazioni = allegato.getAltreInformazioni();
            if (altreInformazioni == null) {
                altreInformazioni = new AbilitazioniPatentiDTO();
                allegato.setAltreInformazioni(altreInformazioni);
            }
            altreInformazioni.setListPatentiGuida(patm.mapList(in.getPatenti()).toArray(new PatenteDTO[0]));
            altreInformazioni.setListPatentini(patm.mapList(in.getPatentini()).toArray(new PatenteDTO[0]));
            altreInformazioni.setListAlbo(alm.mapList(in.getAlbi()).toArray(new AlboDTO[0]));
        }

        if (sezioni.contains(POLITICHE_ATTIVE)) {
            out.setListPoliticheAttive(vm.mapList(in.getPoliticheAttive()).toArray(new PoliticheAttiveSapDTO[0]));
        }

        return out;
    }

    /**
     * Gets the valore O null se vuoto.
     *
     * @param in the in
     * @return the valore O null se vuoto
     */
    private String getValoreONullSeVuoto(String in) {
        if (SilCommonUtils.isVoid(in)) {
            return null;
        }
        return in;
    }

    /**
     * Map reverse.
     *
     * @param <C> the generic type
     * @param in the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends SchedaAnagraficoProfessionale> C mapReverse(SchedaAnagraficoProfessionaleDTO in, C out) {
        DatiAnagraficiSapDTO datiAnagrafici = in.getDatiAnagrafici();

        if (datiAnagrafici == null || in.getDatiInvio() == null)
            return null;

        out.setIdentificativoSap(in.getDatiInvio().getIdentificativoSap());
        out.setEnteTitolare(dm.mapReverse(in.getDatiInvio().getEnteTitolare()));
        out.setDataUltimoAggiornamento(in.getDatiInvio().getDataUltimoAggiornamento());
        mapReverseEsito(in, out);

        DatiAnagraficiPersonaDTO datiPersonali = datiAnagrafici.getDatiPersonali();
        out.setCodiceFiscale(datiPersonali.getCodiceFiscale());
        out.setCognome(datiPersonali.getCognome());
        out.setNome(datiPersonali.getNome());
        out.setSesso(datiPersonali.getSesso());
        out.setDataDiNascita(datiPersonali.getDataNascita());

        if (datiPersonali.getCittadinanza() != null) {
            out.setCittadinanza(datiPersonali.getCittadinanza().getDescrizione());
            out.setCodiceMinisterialeCittadinanza(datiPersonali.getCittadinanza().getCodiceMinisteriale());
        }

        out.setPermessoDiSoggiorno(new PermessoDiSoggiornoMapper().mapReverse(datiAnagrafici.getDatiStranieri()));

        LuogoDTO luogoNascitaDTO = datiPersonali.getLuogoNascita();
        if (luogoNascitaDTO != null) {
            Luogo luogoNascita = new Luogo();
            out.setLuogoDiNascita(luogoNascita);
            if (luogoNascitaDTO.getComune() != null && luogoNascitaDTO.getComune().getCodiceMinisteriale() != null) {
                luogoNascita.setComune(valorizzaComune(luogoNascitaDTO.getComune()));
            } else if (luogoNascitaDTO.getStato() != null) {
                luogoNascita.setStato(valorizzaNazione(luogoNascitaDTO.getStato()));
            }
        }
        out.setResidenza(valorizzaIndirizzo(datiAnagrafici.getResidenza()));
        out.setDomicilio(valorizzaIndirizzo(datiAnagrafici.getDomicilio()));
        RecapitoDTO recapitoDTO = datiAnagrafici.getRecapiti();
        if (recapitoDTO != null) {
            Recapito recapito = new Recapito();
            out.setRecapito(recapito);
            recapito.setCellulare(recapitoDTO.getCellulare());
            recapito.setEmail(recapitoDTO.getEmail());
            recapito.setFax(recapitoDTO.getFax());
            recapito.setTelefono(recapitoDTO.getTelefono());
            recapito.setWeb(recapitoDTO.getWeb());
            recapito.setIndirizzo(valorizzaIndirizzo(recapitoDTO.getIndirizzo()));
        }

        out.setDatiAmministrativi(new DatiAmministrativiMapper().mapReverse(in.getDatiAmministrativi()));

        out.setEsperienzeDiLavoro(new EsperienzeLavoroMapper().mapListReverse(in.getListEsperienzeLavoro()));

        if (in.getAllegato() != null) {
            out.setTitoliDiStudio(new TitoloDiStudioMapper().mapListReverse(in.getAllegato().getListTitoliStudio()));

            out.setFormazioneProfessionaleCorsi(new FormazioneProfessionaleMapper().mapListReverse(in.getAllegato().getListFormazioneProfessionale()));

            out.setLingueStraniere(new LingueStraniereMapper().mapListReverse(in.getAllegato().getListLingueStraniere()));

            out.setConoscenzeInformatiche(new ConoscenzeInformaticheMapper().mapListReverse(in.getAllegato().getListConoscenzeInformatiche()));

            if (in.getAllegato().getAltreInformazioni() != null) {
                out.setPatenti(dm.mapListReverse(in.getAllegato().getAltreInformazioni().getListPatentiGuida()));
                out.setPatentini(dm.mapListReverse(in.getAllegato().getAltreInformazioni().getListPatentini()));
                out.setAlbi(dm.mapListReverse(in.getAllegato().getAltreInformazioni().getListAlbo()));
            }

        }
        if (in.getListPoliticheAttive() != null) {
            out.setPoliticheAttive(new PoliticheAttiveMapper().mapListReverse(in.getListPoliticheAttive()));
        }
        return out;
    }

    /**
     * Mappa un eventuale esito con errori ottenuti nella sap.
     *
     * @param <C> the generic type
     * @param in the in
     * @param out the out
     */
    private <C extends SchedaAnagraficoProfessionale> void mapReverseEsito(SchedaAnagraficoProfessionaleDTO in, C out) {
        if (in.getEsito() != null && in.getEsito().getHeader() != null) {
            out.setEsito(new Esito());
            out.getEsito().setMessaggioCittadino(in.getEsito().getHeader());
        }
    }

    /**
     * Valorizza indirizzo.
     *
     * @param indirizzoDTO the indirizzo DTO
     * @return the indirizzo
     */
    private Indirizzo valorizzaIndirizzo(IndirizzoDTO indirizzoDTO) {
        Indirizzo indirizzo = new Indirizzo();
        if (indirizzoDTO != null) {
            indirizzo.setIndirizzoEsteso(indirizzoDTO.getIndirizzo());
            indirizzo.setComune(valorizzaComune(indirizzoDTO.getComune()));
            IndirizzoSilpDTO indirizzoSilpDTO = new IndirizzoSilpDTO();
            ManagerConversioneIndirizzo.getInstance().getIndirizzoFormatoSilp(indirizzoDTO, indirizzoSilpDTO);
            indirizzo.setIndirizzo(indirizzoSilpDTO.getIndirizzo());
            indirizzo.setLocalita(indirizzoSilpDTO.getLocalita());
            indirizzo.setNumeroCivico(indirizzoSilpDTO.getNumeroCivico());
            if (indirizzoSilpDTO.getToponimo() != null) {
                Sedime toponimo = new Sedime();
                toponimo.setCodiceMinisteriale(indirizzoSilpDTO.getToponimo().getIdentificativoDecodifica());
                toponimo.setDescrizione(indirizzoSilpDTO.getToponimo().getDescrizione());
                indirizzo.setToponimo(toponimo);
            }
            if (indirizzoDTO.getStato() != null) {
                indirizzo.setStato(new Nazione());
                indirizzo.getStato().setCodiceMinisteriale(indirizzoDTO.getStato().getCodiceMinisteriale());
                indirizzo.getStato().setDescrizione(indirizzoDTO.getStato().getDescrizione());
                indirizzo.getStato().setSigla(indirizzoDTO.getStato().getSiglaNazione());
            }
        }
        return indirizzo;
    }

    /**
     * Valorizza comune.
     *
     * @param comuneDTO the comune DTO
     * @return the comune
     */
    private Comune valorizzaComune(ComuneDTO comuneDTO) {
        Comune comune = null;
        if (comuneDTO != null) {
            comune = new Comune();
            comune.setCap(comuneDTO.getCap());
            comune.setCodiceMinisteriale(comuneDTO.getCodiceMinisteriale());
            comune.setDescrizione(comuneDTO.getDescrizione());
            ProvinciaDTO provinciaDTO = comuneDTO.getProvincia();
            if (provinciaDTO != null) {
                Map<String, Provincia> province;
                try {
                    province = AdapterSilpsvdeWSImpl.getInstance().getMapProvincePerTarga();
                } catch (Exception e) {
                    throw new UnsupportedOperationException("Errore nel reperimento mappe provincie da AdapterSilpsvdeWSImpl", e);
                }
                Provincia provincia = province.get(provinciaDTO.getTarga());
                comune.setProvincia(provincia);
            }
        }
        return comune;
    }

    /**
     * Valorizza nazione.
     *
     * @param nazioneDTO the nazione DTO
     * @return the nazione
     */
    private Nazione valorizzaNazione(NazioneDTO nazioneDTO) {
        Nazione nazione = null;
        if (nazioneDTO != null) {
            nazione = new Nazione();
            nazione.setCodiceMinisteriale(nazioneDTO.getCodiceMinisteriale());
            nazione.setDescrizione(nazioneDTO.getDescrizione());
        }
        return nazione;
    }

    /**
     * Find ente titolare.
     *
     * @param sap the sap
     * @param codiceFiscaleUtente the codice fiscale utente
     * @return the ente
     * @throws Exception the exception
     */
    private Ente findEnteTitolare(SchedaAnagraficoProfessionale sap, String codiceFiscaleUtente) throws Exception {
        if (sap.getDomicilio() != null && sap.getDomicilio().getComune() != null) {
            String codiceMinisterialeComuneDomicilio = sap.getDomicilio().getComune().getCodiceMinisteriale();
            List<Ente> entiDomicilio = AdapterSilpsvinWSImpl.getInstance().findElencoEnti(codiceFiscaleUtente, codiceMinisterialeComuneDomicilio);
            if (entiDomicilio != null && !entiDomicilio.isEmpty())
                return entiDomicilio.get(0);
        }
        if (sap.getResidenza() != null && sap.getResidenza().getComune() != null) {
            String codiceMinisterialeComuneResidenza = sap.getResidenza().getComune().getCodiceMinisteriale();
            List<Ente> entiResidenza = AdapterSilpsvinWSImpl.getInstance().findElencoEnti(codiceFiscaleUtente, codiceMinisterialeComuneResidenza);
            if (entiResidenza != null && !entiResidenza.isEmpty())
                return entiResidenza.get(0);
        }
        return null;
    }

}
