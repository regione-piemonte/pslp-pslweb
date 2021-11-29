/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import it.csi.pslp.pslweb.dto.be.Indirizzo;
import it.csi.pslp.pslweb.dto.be.SchedaAnagraficoProfessionale;
import it.csi.pslp.pslweb.util.Constants;
import it.csi.silos.silcommon.dati.common.IndirizzoDTO;
import it.csi.silos.silcommon.dati.common.RecapitoDTO;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.DatiAggiornamentoSezioneSapDTO;
import it.csi.silos.silcommon.dati.lavoratore.schedaprofessionale.OggettoImportabileSap;
import it.csi.silos.silcommon.generic.CallInfoDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.sap.TipoVariazioneDTO;
import it.csi.silpservizi.sesp.bo.sap.SchedaAnagraficoProfessionaleLavoratoreDTO;

/**
 * The Class SendSAPMapper.
 */
public class SendSAPMapper {

    /** The sap. */
    private SchedaAnagraficoProfessionaleLavoratoreDTO sap;
    
    /** The sap PLSP. */
    private SchedaAnagraficoProfessionale sapPLSP;
    
    /** The codice fiscale utente. */
    private String codiceFiscaleUtente;
    
    /** The now. */
    private Date now;

    /**
     * Map SAP.
     *
     * @param sap the sap
     * @param sapPLSP the sap PLSP
     * @param codiceFiscaleUtente the codice fiscale utente
     * @param dataInvio the data invio
     * @throws Exception the exception
     */
    public void mapSAP(SchedaAnagraficoProfessionaleLavoratoreDTO sap, SchedaAnagraficoProfessionale sapPLSP, String codiceFiscaleUtente, Date dataInvio)
            throws Exception {
        this.sap = sap;
        this.sapPLSP = sapPLSP;
        this.codiceFiscaleUtente = codiceFiscaleUtente;
        now = dataInvio;

        // Sfruttare i vari mapper esistenti se possibile, per ora sono stati
        // implemntati i metodi solo da sap silp a sap pslp
        mapResidenza();
        mapDomicilio();
        mapRecapito();

        setCallInfo();
        setMetadata();
    }

    /**
     * Map residenza.
     *
     * @throws Exception the exception
     */
    private void mapResidenza() throws Exception {
        if (sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().getResidenza() == null) {
            sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().setResidenza(new IndirizzoDTO());
        }
        mapIndirizzo(sapPLSP.getResidenza(), sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().getResidenza());
    }

    /**
     * Map domicilio.
     *
     * @throws Exception the exception
     */
    private void mapDomicilio() throws Exception {
        if (sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().getDomicilio() == null) {
            sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().setDomicilio(new IndirizzoDTO());
        }
        mapIndirizzo(sapPLSP.getDomicilio(), sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().getDomicilio());
    }

    /**
     * Map recapito.
     *
     * @throws Exception the exception
     */
    private void mapRecapito() throws Exception {
        if (sapPLSP.getRecapito() == null) {
            return;
        }
        if (sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().getRecapiti() == null) {
            sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().setRecapiti(new RecapitoDTO());
        }
        sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().getRecapiti().setCellulare(sapPLSP.getRecapito().getCellulare());
        sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().getRecapiti()
                .setEmail(ifNotBlank(sapPLSP.getRecapito().getEmail(), sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().getRecapiti().getEmail()));
        sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().getRecapiti().setFax(sapPLSP.getRecapito().getFax());
        sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().getRecapiti().setTelefono(sapPLSP.getRecapito().getTelefono());
        sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().getRecapiti()
                .setWeb(ifNotBlank(sapPLSP.getRecapito().getWeb(), sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().getRecapiti().getWeb()));
        mapIndirizzo(sapPLSP.getRecapito().getIndirizzo(), sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().getRecapiti().getIndirizzo());
        updateDatiAggiornamento(sap.getSchedaAnagraficaProfessionale().getDatiAnagrafici().getRecapiti());
    }

    /**
     * Map indirizzo.
     *
     * @param indirizzo the indirizzo
     * @param indirizzoDTO the indirizzo DTO
     * @throws Exception the exception
     */
    private void mapIndirizzo(Indirizzo indirizzo, IndirizzoDTO indirizzoDTO) throws Exception {
        if (indirizzo == null || indirizzoDTO == null)
            return;

        new IndirizzoMapper().map(indirizzo, indirizzoDTO);
        updateDatiAggiornamento(indirizzoDTO);
    }

    /**
     * Update dati aggiornamento.
     *
     * @param oggettoSAP the oggetto SAP
     */
    private void updateDatiAggiornamento(OggettoImportabileSap oggettoSAP) {
        if (oggettoSAP.getDatiAggiornamento() == null) {
            oggettoSAP.setDatiAggiornamento(new DatiAggiornamentoSezioneSapDTO());
        }
        oggettoSAP.getDatiAggiornamento().setDataUltimoAggiornamento(now);
        oggettoSAP.getDatiAggiornamento().setDsFonteDatoAggiornamento(Constants.PRODUCT_NAME);
        oggettoSAP.getDatiAggiornamento().setCodUserAggiornamento(codiceFiscaleUtente);
    }

    /**
     * If not blank.
     *
     * @param str the str
     * @param def the def
     * @return the string
     */
    private String ifNotBlank(String str, String def) {
        return StringUtils.isNotBlank(str) ? str : def;
    }

    /**
     * Sets the call info.
     */
    private void setCallInfo() {
        sap.getSchedaAnagraficaProfessionale().setCaller(new CallInfoDTO());
        // FIXME: da mettere a posto?
        sap.getSchedaAnagraficaProfessionale().getCaller().setCodApplicativoChiamante(CallInfoDTO.CODICE_APPLICATIVO_PSLWEB);
        sap.getSchedaAnagraficaProfessionale().getCaller().setCodApplicativoDestinatario(CallInfoDTO.CODICE_APPLICATIVO_SILP);
        sap.getSchedaAnagraficaProfessionale().getCaller().setCodiceFiscaleOperatore(codiceFiscaleUtente);
    }

    /**
     * "motivoRichiestaSap": 7<br/>
     * "tipoVariazione": "02".
     */
    private void setMetadata() {
        sap.getSchedaAnagraficaProfessionale().getDatiInvio().setTipoVariazione(TipoVariazioneDTO.TIPO_VARIAZIONE_02_AGGIORNAMENTO);
        sap.getSchedaAnagraficaProfessionale().getDatiInvio().setDataUltimoAggiornamento(now);
        sap.setMotivoRichiestaSap(Long.valueOf(7L));
    }

    /**
     * Sets the now.
     *
     * @param now the new now
     */
    public void setNow(Date now) {
        this.now = now;
    }
}
