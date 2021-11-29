/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import it.csi.pslp.pslcommonobj.dto.ResponsabileDTO;
import it.csi.pslp.pslcommonobj.dto.UtenteDTO;
import it.csi.pslp.pslweb.dto.be.Cittadinanza;
import it.csi.pslp.pslweb.dto.be.Comune;
import it.csi.pslp.pslweb.dto.be.Indirizzo;
import it.csi.pslp.pslweb.dto.be.Luogo;
import it.csi.pslp.pslweb.dto.be.MotivoRilascioPermessoSoggiorno;
import it.csi.pslp.pslweb.dto.be.Nazione;
import it.csi.pslp.pslweb.dto.be.PermessoDiSoggiorno;
import it.csi.pslp.pslweb.dto.be.Provincia;
import it.csi.pslp.pslweb.dto.be.Recapito;
import it.csi.pslp.pslweb.dto.be.Responsabile;
import it.csi.pslp.pslweb.dto.be.SchedaAnagraficoProfessionale;
import it.csi.pslp.pslweb.dto.be.Sedime;
import it.csi.pslp.pslweb.dto.be.StatusExtraUE;

/**
 * The Class ResponsabileMapper.
 */
public class ResponsabileMapper extends BaseMapper<Responsabile, ResponsabileDTO> {

    /** The comuni. */
    private List<Comune> comuni = new ArrayList<>();
    
    /** The province. */
    private List<Provincia> province = new ArrayList<>();
    
    /** The nazioni. */
    private List<Nazione> nazioni = new ArrayList<>();
    
    /** The cittadinanze. */
    private List<Cittadinanza> cittadinanze = new ArrayList<>();
    
    /** The motivi rilascio permesso soggiorno. */
    private List<MotivoRilascioPermessoSoggiorno> motiviRilascioPermessoSoggiorno = new ArrayList<>();
    
    /** The status extra UE. */
    private List<StatusExtraUE> statusExtraUE = new ArrayList<>();

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends ResponsabileDTO> C map(Responsabile in, C out) {

        out.setIdResponsabile(in.getIdResponsabile());
        out.setCapDom(in.getCapDom());
        out.setCapRes(in.getCapRes());
        out.setDNascita(in.getDNascita());
        out.setDScadenzaPermSogg(in.getDScadenzaPermSogg());
        out.setDsFax(in.getDsFax());
        out.setDsIndirizzoDom(in.getDsIndirizzoDom());
        out.setDsIndirizzoRes(in.getDsIndirizzoRes());
        out.setDsIndirizzoResEsteso(in.getDsIndirizzoResEsteso());
        out.setDsNumCivicoDom(in.getDsNumCivicoDom());
        out.setDsNumCivicoRes(in.getDsNumCivicoRes());
        out.setDsTelefonoCellulare(in.getDsTelefonoCellulare());
        out.setDsTelefonoFisso(in.getDsTelefonoFisso());
        out.setGenere(in.getGenere());
        out.setIdSilTCittadinanza(in.getIdSilTCittadinanza());
        out.setIdSilTComuneDom(in.getIdSilTComuneDom());
        out.setIdSilTComuneNasc(in.getIdSilTComuneNasc());
        out.setIdSilTComuneRes(in.getIdSilTComuneRes());
        out.setIdSilTMotRilPerm(in.getIdSilTMotRilPerm());
        out.setIdSilTNazioneNasc(in.getIdSilTNazioneNasc());
        out.setIdSilTNazioneRes(in.getIdSilTNazioneRes());
        out.setIdSilTProvinciaDom(in.getIdSilTProvinciaDom());
        out.setIdSilTProvinciaRes(in.getIdSilTProvinciaRes());
        out.setIdSilTStatusLavExtraUe(in.getIdSilTStatusLavExtraUe());
        out.setIdSilTToponimoDom(in.getIdSilTToponimoDom());
        out.setIdSilTToponimoRes(in.getIdSilTToponimoRes());
        out.setNumeroPermSogg(in.getNumeroPermSogg());
        out.setDsIndirizzoDomEsteso(in.getDsIndirizzoDomEsteso());
        out.setIdSilTNazioneDom(in.getIdSilTNazioneDom());
        return out;
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
    public <C extends Responsabile> C mapReverse(ResponsabileDTO in, C out) {
        out.setIdResponsabile(in.getIdResponsabile());
        out.setCapDom(in.getCapDom());
        out.setCapRes(in.getCapRes());
        out.setDNascita(in.getDNascita());
        out.setDScadenzaPermSogg(in.getDScadenzaPermSogg());
        out.setDsFax(in.getDsFax());
        out.setDsIndirizzoDom(in.getDsIndirizzoDom());
        out.setDsIndirizzoRes(in.getDsIndirizzoRes());
        out.setDsIndirizzoResEsteso(in.getDsIndirizzoResEsteso());
        out.setDsNumCivicoDom(in.getDsNumCivicoDom());
        out.setDsNumCivicoRes(in.getDsNumCivicoRes());
        out.setDsTelefonoCellulare(in.getDsTelefonoCellulare());
        out.setDsTelefonoFisso(in.getDsTelefonoFisso());
        out.setGenere(in.getGenere());
        out.setIdSilTCittadinanza(in.getIdSilTCittadinanza());
        out.setIdSilTComuneDom(in.getIdSilTComuneDom());
        out.setIdSilTComuneNasc(in.getIdSilTComuneNasc());
        out.setIdSilTComuneRes(in.getIdSilTComuneRes());
        out.setIdSilTMotRilPerm(in.getIdSilTMotRilPerm());
        out.setIdSilTNazioneNasc(in.getIdSilTNazioneNasc());
        out.setIdSilTNazioneRes(in.getIdSilTNazioneRes());
        out.setIdSilTProvinciaDom(in.getIdSilTProvinciaDom());
        out.setIdSilTProvinciaRes(in.getIdSilTProvinciaRes());
        out.setIdSilTStatusLavExtraUe(in.getIdSilTStatusLavExtraUe());
        out.setIdSilTToponimoDom(in.getIdSilTToponimoDom());
        out.setIdSilTToponimoRes(in.getIdSilTToponimoRes());
        out.setNumeroPermSogg(in.getNumeroPermSogg());
        out.setDsIndirizzoDomEsteso(in.getDsIndirizzoDomEsteso());
        out.setIdSilTNazioneDom(in.getIdSilTNazioneDom());
        return out;
    }

    /**
     * Instantiate.
     *
     * @return the responsabile
     */
    @Override
    protected Responsabile instantiate() {
        return new Responsabile();
    }

    /**
     * Instantiate reverse.
     *
     * @return the responsabile DTO
     */
    @Override
    protected ResponsabileDTO instantiateReverse() {
        return new ResponsabileDTO();
    }

    /**
     * Migra da SAP.
     *
     * @param sap the sap
     * @return the responsabile
     */
    public Responsabile migraDaSAP(SchedaAnagraficoProfessionale sap) {
        Responsabile out = new Responsabile();
        out.setDNascita(sap.getDataDiNascita());
        out.setGenere(sap.getSesso());
        out.setIdSilTCittadinanza(sap.getCodiceMinisterialeCittadinanza());

        if (sap.getLuogoDiNascita() != null) {
            out.setIdSilTComuneNasc(sap.getLuogoDiNascita().getComune() != null ? sap.getLuogoDiNascita().getComune().getCodiceMinisteriale() : null);
            out.setIdSilTNazioneNasc(sap.getLuogoDiNascita().getStato() != null ? sap.getLuogoDiNascita().getStato().getCodiceMinisteriale() : null);
        }
        if (sap.getPermessoDiSoggiorno() != null) {
            out.setDScadenzaPermSogg(sap.getPermessoDiSoggiorno().getDataScadenza());
            out.setNumeroPermSogg(sap.getPermessoDiSoggiorno().getNumero());
            out.setIdSilTMotRilPerm(sap.getPermessoDiSoggiorno().getCodiceMinisterialeMotivoRilascio());
            out.setIdSilTStatusLavExtraUe(sap.getPermessoDiSoggiorno().getCodiceMinisterialeStatusExtraUe());
        }
        if (sap.getResidenza() != null) {
            out.setIdSilTNazioneRes(sap.getResidenza().getStato() != null ? sap.getResidenza().getStato().getCodiceMinisteriale() : null);
            out.setDsIndirizzoRes(sap.getResidenza().getIndirizzo());
            out.setIdSilTComuneRes(sap.getResidenza().getComune() != null ? sap.getResidenza().getComune().getCodiceMinisteriale() : null);
            out.setIdSilTProvinciaRes(sap.getResidenza().getComune() != null && sap.getResidenza().getComune().getProvincia() != null
                    ? sap.getResidenza().getComune().getProvincia().getCodiceMinisteriale()
                    : null);
            out.setDsIndirizzoResEsteso(sap.getResidenza().getIndirizzoEsteso());
            out.setDsNumCivicoRes(sap.getResidenza().getNumeroCivico());
            out.setIdSilTToponimoRes(sap.getResidenza().getToponimo() != null ? sap.getResidenza().getToponimo().getCodiceMinisteriale() : null);
            out.setCapRes(sap.getResidenza().getComune() != null ? sap.getResidenza().getComune().getCap() : null);
        }
        if (sap.getDomicilio() != null) {
            out.setIdSilTNazioneDom(sap.getDomicilio().getStato() != null ? sap.getDomicilio().getStato().getCodiceMinisteriale() : null);
            out.setDsIndirizzoDom(sap.getDomicilio().getIndirizzo());
            out.setDsNumCivicoDom(sap.getDomicilio().getNumeroCivico());
            out.setIdSilTComuneDom(sap.getDomicilio().getComune() != null ? sap.getDomicilio().getComune().getCodiceMinisteriale() : null);
            out.setIdSilTProvinciaDom(sap.getDomicilio().getComune() != null && sap.getDomicilio().getComune().getProvincia() != null
                    ? sap.getDomicilio().getComune().getProvincia().getCodiceMinisteriale()
                    : null);
            out.setIdSilTToponimoDom(sap.getDomicilio().getToponimo() != null ? sap.getDomicilio().getToponimo().getCodiceMinisteriale() : null);
            out.setCapDom(sap.getDomicilio().getComune() != null ? sap.getDomicilio().getComune().getCap() : null);
            out.setDsIndirizzoDomEsteso(sap.getDomicilio().getIndirizzoEsteso());
        }
        if (sap.getRecapito() != null) {
            out.setDsFax(sap.getRecapito().getFax());
            out.setDsTelefonoCellulare(sap.getRecapito().getCellulare());
            out.setDsTelefonoFisso(sap.getRecapito().getTelefono());
        }
        return out;
    }

    /**
     * Migra to SAP.
     *
     * @param responsabile the responsabile
     * @param utente the utente
     * @return the scheda anagrafico professionale
     */
    public SchedaAnagraficoProfessionale migraToSAP(ResponsabileDTO responsabile, UtenteDTO utente) {
        SchedaAnagraficoProfessionale sap = new SchedaAnagraficoProfessionale();
        sap.setNome(utente.getNome());
        sap.setCognome(utente.getCognome());
        sap.setSesso(responsabile.getGenere());
        sap.setDataDiNascita(responsabile.getDNascita());
        sap.setCodiceMinisterialeCittadinanza(responsabile.getIdSilTCittadinanza());
        sap.setCittadinanza(findCittadinanza(responsabile.getIdSilTCittadinanza()).getDescrizione());
        sap.setCodiceFiscale(utente.getCfUtente());
        sap.setIdSilLavAnagrafica(utente.getIdSilLavAngrafica());

        sap.setLuogoDiNascita(new Luogo());
        sap.getLuogoDiNascita().setComune(findComune(responsabile.getIdSilTComuneNasc()));
        sap.getLuogoDiNascita().setStato(findNazione(responsabile.getIdSilTNazioneNasc()));

        sap.setResidenza(new Indirizzo());
        sap.getResidenza().setIndirizzo(responsabile.getDsIndirizzoRes());
        sap.getResidenza().setIndirizzoEsteso(responsabile.getDsIndirizzoResEsteso());
        sap.getResidenza().setNumeroCivico(responsabile.getDsNumCivicoRes());
        sap.getResidenza().setToponimo(new Sedime());
        sap.getResidenza().getToponimo().setCodiceMinisteriale(responsabile.getIdSilTToponimoRes());
        sap.getResidenza().setStato(findNazione(responsabile.getIdSilTNazioneRes()));
        sap.getResidenza().setComune(findComune(responsabile.getIdSilTComuneRes()));
        sap.getResidenza().getComune().setProvincia(findProvincia(responsabile.getIdSilTProvinciaRes()));
        sap.getResidenza().getComune().setCap(responsabile.getCapRes());

        sap.setDomicilio(new Indirizzo());
        sap.getDomicilio().setIndirizzo(responsabile.getDsIndirizzoDom());
        sap.getDomicilio().setIndirizzoEsteso(responsabile.getDsIndirizzoDomEsteso());
        sap.getDomicilio().setNumeroCivico(responsabile.getDsNumCivicoDom());
        sap.getDomicilio().setToponimo(new Sedime());
        sap.getDomicilio().getToponimo().setCodiceMinisteriale(responsabile.getIdSilTToponimoDom());
        sap.getDomicilio().setStato(findNazione(responsabile.getIdSilTNazioneDom()));
        sap.getDomicilio().setComune(findComune(responsabile.getIdSilTComuneDom()));
        sap.getDomicilio().getComune().setProvincia(findProvincia(responsabile.getIdSilTProvinciaDom()));
        sap.getDomicilio().getComune().setCap(responsabile.getCapDom());

        sap.setRecapito(new Recapito());
        sap.getRecapito().setTelefono(responsabile.getDsTelefonoFisso());
        sap.getRecapito().setCellulare(responsabile.getDsTelefonoCellulare());
        sap.getRecapito().setFax(responsabile.getDsFax());
        sap.getRecapito().setEmail(utente.getEmail());

        sap.setPermessoDiSoggiorno(new PermessoDiSoggiorno());
        sap.getPermessoDiSoggiorno().setNumero(responsabile.getNumeroPermSogg());
        sap.getPermessoDiSoggiorno().setDataScadenza(responsabile.getDScadenzaPermSogg());
        sap.getPermessoDiSoggiorno().setCodiceMinisterialeMotivoRilascio(responsabile.getIdSilTMotRilPerm());
        sap.getPermessoDiSoggiorno().setCodiceMinisterialeStatusExtraUe(responsabile.getIdSilTStatusLavExtraUe());
        sap.getPermessoDiSoggiorno().setMotivoRilascio(findMotivoRilascioPermessoSoggiorno(responsabile.getIdSilTMotRilPerm()).getDescrizione());
        sap.getPermessoDiSoggiorno().setTitolo(findStatusExtraUE(responsabile.getIdSilTStatusLavExtraUe()).getDescrizione());

        return sap;
    }

    /**
     * Cittadinanza clone.
     *
     * @param cittadinanza the cittadinanza
     * @return the cittadinanza
     */
    private Cittadinanza cittadinanzaClone(Cittadinanza cittadinanza) {
        if (cittadinanza == null) {
            return null;
        }
        Cittadinanza clone = new Cittadinanza();
        clone.setCodiceMinisteriale(cittadinanza.getCodiceMinisteriale());
        clone.setSigla(cittadinanza.getSigla());
        clone.setDescrizione(cittadinanza.getDescrizione());
        clone.setCodiceMinisterialeNazione(cittadinanza.getCodiceMinisterialeNazione());
        return clone;
    }

    /**
     * Find cittadinanza.
     *
     * @param codiceMinisteriale the codice ministeriale
     * @return the cittadinanza
     */
    private Cittadinanza findCittadinanza(String codiceMinisteriale) {
        if (StringUtils.isBlank(codiceMinisteriale)) {
            return new Cittadinanza();
        }
        for (Cittadinanza cittadinanza : cittadinanze) {
            if (codiceMinisteriale.equals(cittadinanza.getCodiceMinisteriale())) {
                return cittadinanzaClone(cittadinanza);
            }
        }
        return new Cittadinanza();
    }

    /**
     * Comune clone.
     *
     * @param comune the comune
     * @return the comune
     */
    private Comune comuneClone(Comune comune) {
        if (comune == null) {
            return null;
        }
        Comune clone = new Comune();
        clone.setCodiceMinisteriale(comune.getCodiceMinisteriale());
        clone.setDescrizione(comune.getDescrizione());
        clone.setCap(comune.getCap());
        clone.setProvincia(provinciaClone(comune.getProvincia()));
        return clone;
    }

    /**
     * Find comune.
     *
     * @param codiceMinisteriale the codice ministeriale
     * @return the comune
     */
    private Comune findComune(String codiceMinisteriale) {
        if (StringUtils.isBlank(codiceMinisteriale)) {
            return new Comune();
        }
        for (Comune comune : comuni) {
            if (codiceMinisteriale.equals(comune.getCodiceMinisteriale())) {
                return comuneClone(comune);
            }
        }
        return new Comune();
    }

    /**
     * Provincia clone.
     *
     * @param provincia the provincia
     * @return the provincia
     */
    private Provincia provinciaClone(Provincia provincia) {
        if (provincia == null) {
            return null;
        }
        Provincia clone = new Provincia();
        clone.setCodiceMinisteriale(provincia.getCodiceMinisteriale());
        clone.setTarga(provincia.getTarga());
        clone.setDescrizione(provincia.getDescrizione());
        return clone;
    }

    /**
     * Find provincia.
     *
     * @param codiceMinisteriale the codice ministeriale
     * @return the provincia
     */
    private Provincia findProvincia(String codiceMinisteriale) {
        if (StringUtils.isBlank(codiceMinisteriale)) {
            return new Provincia();
        }
        for (Provincia provincia : province) {
            if (codiceMinisteriale.equals(provincia.getCodiceMinisteriale())) {
                return provinciaClone(provincia);
            }
        }
        return new Provincia();
    }

    /**
     * Nazione clone.
     *
     * @param nazione the nazione
     * @return the nazione
     */
    private Nazione nazioneClone(Nazione nazione) {
        if (nazione == null) {
            return null;
        }
        Nazione clone = new Nazione();
        clone.setCodiceMinisteriale(nazione.getCodiceMinisteriale());
        clone.setSigla(nazione.getSigla());
        clone.setDescrizione(nazione.getDescrizione());
        clone.setFlagUe(nazione.isFlagUe());
        return clone;
    }

    /**
     * Find nazione.
     *
     * @param codiceMinisteriale the codice ministeriale
     * @return the nazione
     */
    private Nazione findNazione(String codiceMinisteriale) {
        if (StringUtils.isBlank(codiceMinisteriale)) {
            return new Nazione();
        }
        for (Nazione nazione : nazioni) {
            if (codiceMinisteriale.equals(nazione.getCodiceMinisteriale())) {
                return nazioneClone(nazione);
            }
        }
        return new Nazione();
    }

    /**
     * Motivo rilascio permesso soggiorno clone.
     *
     * @param motivo the motivo
     * @return the motivo rilascio permesso soggiorno
     */
    private MotivoRilascioPermessoSoggiorno motivoRilascioPermessoSoggiornoClone(MotivoRilascioPermessoSoggiorno motivo) {
        if (motivo == null) {
            return null;
        }
        MotivoRilascioPermessoSoggiorno clone = new MotivoRilascioPermessoSoggiorno();
        clone.setVigore(motivo.getVigore());
        clone.setDescrizione(motivo.getDescrizione());
        clone.setId(motivo.getId());
        return clone;
    }

    /**
     * Find motivo rilascio permesso soggiorno.
     *
     * @param codiceMinisteriale the codice ministeriale
     * @return the motivo rilascio permesso soggiorno
     */
    private MotivoRilascioPermessoSoggiorno findMotivoRilascioPermessoSoggiorno(String codiceMinisteriale) {
        if (StringUtils.isBlank(codiceMinisteriale)) {
            return new MotivoRilascioPermessoSoggiorno();
        }
        for (MotivoRilascioPermessoSoggiorno motivoRilascioPermessoSoggiorno : motiviRilascioPermessoSoggiorno) {
            if (codiceMinisteriale.equals(motivoRilascioPermessoSoggiorno.getId())) {
                return motivoRilascioPermessoSoggiornoClone(motivoRilascioPermessoSoggiorno);
            }
        }
        return new MotivoRilascioPermessoSoggiorno();
    }

    /**
     * Status extra UE clone.
     *
     * @param statusExtraUE the status extra UE
     * @return the status extra UE
     */
    private StatusExtraUE statusExtraUEClone(StatusExtraUE statusExtraUE) {
        if (statusExtraUE == null) {
            return null;
        }
        StatusExtraUE clone = new StatusExtraUE();
        clone.setCodiceMinisteriale(statusExtraUE.getCodiceMinisteriale());
        clone.setDescrizione(statusExtraUE.getDescrizione());
        clone.setIdTipoDocumentoSoggiorno(statusExtraUE.getIdTipoDocumentoSoggiorno());
        clone.setDataInizio(statusExtraUE.getDataInizio());
        clone.setDataFine(statusExtraUE.getDataFine());
        return clone;
    }

    /**
     * Find status extra UE.
     *
     * @param codiceMinisteriale the codice ministeriale
     * @return the status extra UE
     */
    private StatusExtraUE findStatusExtraUE(String codiceMinisteriale) {
        if (StringUtils.isBlank(codiceMinisteriale)) {
            return new StatusExtraUE();
        }
        for (StatusExtraUE nazione : statusExtraUE) {
            if (codiceMinisteriale.equals(nazione.getCodiceMinisteriale())) {
                return statusExtraUEClone(nazione);
            }
        }
        return new StatusExtraUE();
    }

    /**
     * Sets the comuni.
     *
     * @param comuni the new comuni
     */
    public void setComuni(List<Comune> comuni) {
        this.comuni = comuni != null ? comuni : new ArrayList<>();
    }

    /**
     * Sets the province.
     *
     * @param province the new province
     */
    public void setProvince(List<Provincia> province) {
        this.province = province != null ? province : new ArrayList<>();
    }

    /**
     * Sets the nazioni.
     *
     * @param nazioni the new nazioni
     */
    public void setNazioni(List<Nazione> nazioni) {
        this.nazioni = nazioni != null ? nazioni : new ArrayList<>();
    }

    /**
     * Sets the cittadinanze.
     *
     * @param cittadinanze the new cittadinanze
     */
    public void setCittadinanze(List<Cittadinanza> cittadinanze) {
        this.cittadinanze = cittadinanze != null ? cittadinanze : new ArrayList<>();
    }

    /**
     * Sets the motivi rilascio permesso soggiorno.
     *
     * @param motiviRilascioPermessoSoggiorno the new motivi rilascio permesso soggiorno
     */
    public void setMotiviRilascioPermessoSoggiorno(List<MotivoRilascioPermessoSoggiorno> motiviRilascioPermessoSoggiorno) {
        this.motiviRilascioPermessoSoggiorno = motiviRilascioPermessoSoggiorno != null ? motiviRilascioPermessoSoggiorno : new ArrayList<>();
    }

    /**
     * Sets the status extra UE.
     *
     * @param statusExtraUE the new status extra UE
     */
    public void setStatusExtraUE(List<StatusExtraUE> statusExtraUE) {
        this.statusExtraUE = statusExtraUE != null ? statusExtraUE : new ArrayList<>();
    }

}
