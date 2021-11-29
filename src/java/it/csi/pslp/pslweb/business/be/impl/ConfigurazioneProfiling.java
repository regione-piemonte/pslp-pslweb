/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business.be.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.csi.pslp.pslweb.dto.be.CondizioneOccupazionale;
import it.csi.pslp.pslweb.dto.be.GradoStudio;
import it.csi.pslp.pslweb.dto.be.MotivoPresenzaInItalia;
import it.csi.pslp.pslweb.dto.be.TitoloStudio;
import it.csi.silpcommonobj.dati.decodifiche.CondizioneOccupazionaleSilpDTO;
import it.csi.silpcommonobj.dati.decodifiche.GradoStudioSilpDTO;
import it.csi.silpcommonobj.dati.decodifiche.PresenzaInItaliaSilpDTO;
import it.csi.silpcommonobj.dati.decodifiche.TitoloStudioSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.profiling.ConfigurazioneProfilingSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.profiling.TitoloStudioProfilingSilpDTO;

/**
 * The Class ConfigurazioneProfiling.
 */
public class ConfigurazioneProfiling implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The conf. */
    private ConfigurazioneProfilingSilpDTO conf = null;

    /** The condizioni occupazionali. */
    private List<CondizioneOccupazionale> condizioniOccupazionali = new ArrayList<>();

    /** The gradi studio. */
    private List<GradoStudio> gradiStudio = new ArrayList<>();

    /** The gradi studio silp. */
    private List<GradoStudio> gradiStudioSilp = new ArrayList<>();

    /** The motivi presenza in italia. */
    private List<MotivoPresenzaInItalia> motiviPresenzaInItalia = new ArrayList<>();

    /** The titoli studio per codice ministeriale. */
    private Map<String, TitoloStudio> titoliStudioPerCodiceMinisteriale = new HashMap<>();

    /** The titoli studio silp per codice ministeriale. */
    private Map<String, TitoloStudio> titoliStudioSilpPerCodiceMinisteriale = new HashMap<>();

    /** The condizioni occupazionali per codice ministeriale. */
    private Map<String, CondizioneOccupazionale> condizioniOccupazionaliPerCodiceMinisteriale = new HashMap<>();
    
    /** The motivi presenza in italia per codice ministeriale. */
    private Map<String, MotivoPresenzaInItalia> motiviPresenzaInItaliaPerCodiceMinisteriale = new HashMap<>();

    /**
     * Instantiates a new configurazione profiling.
     *
     * @param conf the conf
     */
    public ConfigurazioneProfiling(ConfigurazioneProfilingSilpDTO conf) {

        this.conf = conf;

        Date now = new Date();

        Set<String> condizioniOccupazionaliSet = new HashSet<>();
        for (CondizioneOccupazionaleSilpDTO dto : conf.getElencoAllCondizioneOccupazionale()) {
            if (dto.getDataInizio() != null && dto.getDataInizio().after(now))
                continue;
            if (dto.getDataFine() != null && dto.getDataFine().before(now))
                continue;
            if (condizioniOccupazionaliSet.contains(dto.getCodiceMinisteriale()))
                continue;
            CondizioneOccupazionale cond = new CondizioneOccupazionale();
            cond.setCodice(dto.getCodiceMinisteriale());
            cond.setCodiceSilp(dto.getId());
            cond.setDescrizione(dto.getDescrizione());
            condizioniOccupazionali.add(cond);
            condizioniOccupazionaliSet.add(dto.getCodiceMinisteriale());
            condizioniOccupazionaliPerCodiceMinisteriale.put(cond.getCodice(), cond);
        }

        for (TitoloStudioSilpDTO dtoTitoloStudioSilp : conf.getElencoTitoloStudioSilp()) {
            if (dtoTitoloStudioSilp.getDtInizio() != null && dtoTitoloStudioSilp.getDtInizio().after(now))
                continue;
            if (dtoTitoloStudioSilp.getDtFine() != null && dtoTitoloStudioSilp.getDtFine().before(now))
                continue;
            titoliStudioSilpPerCodiceMinisteriale.put(dtoTitoloStudioSilp.getCodiceMinisteriale(), mapTitoloStudio(dtoTitoloStudioSilp));
        }

        // filtro i soli gradi di studio distinti usati nei titoli del profiling
        Set<String> setGradiConTitoliDiStudio = new HashSet<>();
        for (TitoloStudioProfilingSilpDTO dtoProfiling : conf.getElencoTitoloStudioProfiling()) {
            TitoloStudioSilpDTO dto = dtoProfiling.getTitolo();
            if (dto.getDtInizio() != null && dto.getDtInizio().after(now))
                continue;
            if (dto.getDtFine() != null && dto.getDtFine().before(now))
                continue;
            titoliStudioPerCodiceMinisteriale.put(dto.getCodiceMinisteriale(), mapTitoloStudio(dto));
            setGradiConTitoliDiStudio.add(dto.getGradoStudio().getCodiceMinisteriale());
        }

        // rimappo i gradi studio (tutti) e quelli per profiling
        for (GradoStudioSilpDTO dto : conf.getElencoGradoStudio()) {
            if (dto.getDataInizio() != null && dto.getDataInizio().after(now))
                continue;
            if (dto.getDataFine() != null && dto.getDataFine().before(now))
                continue;
            gradiStudioSilp.add(mapGradoStudio(dto));
            if (setGradiConTitoliDiStudio.contains(dto.getCodiceMinisteriale()))
                gradiStudio.add(mapGradoStudio(dto));
        }

        Set<String> motiviPresenzaInItaliaSet = new HashSet<>();
        for (PresenzaInItaliaSilpDTO dto : conf.getAllPresenzaInItalia()) {
            if (dto.getDataInizio() != null && dto.getDataInizio().after(now))
                continue;
            if (dto.getDataFine() != null && dto.getDataFine().before(now))
                continue;
            if (motiviPresenzaInItaliaSet.contains(dto.getCodiceMinisteriale()))
                continue;
            MotivoPresenzaInItalia motivo = new MotivoPresenzaInItalia();
            motivo.setCodice(dto.getCodiceMinisteriale());
            motivo.setCodiceSilp(dto.getId());
            motivo.setDescrizione(dto.getDescrizione());
            motiviPresenzaInItalia.add(motivo);
            motiviPresenzaInItaliaSet.add(dto.getCodiceMinisteriale());
            motiviPresenzaInItaliaPerCodiceMinisteriale.put(motivo.getCodice(), motivo);
        }
    }

    /**
     * Gets the condizioni occupazionali.
     *
     * @return the condizioni occupazionali
     */
    public List<CondizioneOccupazionale> getCondizioniOccupazionali() {
        return condizioniOccupazionali;
    }

    /**
     * Sets the condizioni occupazionali.
     *
     * @param condizioniOccupazionali the new condizioni occupazionali
     */
    public void setCondizioniOccupazionali(List<CondizioneOccupazionale> condizioniOccupazionali) {
        this.condizioniOccupazionali = condizioniOccupazionali;
    }

    /**
     * Gets the gradi studio.
     *
     * @return the gradi studio
     */
    public List<GradoStudio> getGradiStudio() {
        return gradiStudio;
    }

    /**
     * Sets the gradi studio.
     *
     * @param gradiStudio the new gradi studio
     */
    public void setGradiStudio(List<GradoStudio> gradiStudio) {
        this.gradiStudio = gradiStudio;
    }

    /**
     * Gets the titoli studio.
     *
     * @param idGradoStudioSilp the id grado studio silp
     * @return the titoli studio
     */
    public List<TitoloStudio> getTitoliStudio(Long idGradoStudioSilp) {
        Date now = new Date();
        List<TitoloStudio> titoli = new ArrayList<>();
        for (TitoloStudioSilpDTO dto : conf.getElencoTitoliStudioAll()) {
            if (idGradoStudioSilp != null && !idGradoStudioSilp.equals(dto.getGradoStudio().getId()))
                continue;
            if (dto.getDtInizio() != null && dto.getDtInizio().after(now))
                continue;
            if (dto.getDtFine() != null && dto.getDtFine().before(now))
                continue;
            titoli.add(mapTitoloStudio(dto));
        }
        return titoli;
    }

    /**
     * Gets the titoli studio silp.
     *
     * @param idGradoStudioSilp the id grado studio silp
     * @return the titoli studio silp
     */
    public List<TitoloStudio> getTitoliStudioSilp(Long idGradoStudioSilp) {
        Date now = new Date();
        List<TitoloStudio> titoli = new ArrayList<>();
        for (TitoloStudioSilpDTO dto : conf.getElencoTitoloStudioSilp()) {
            if (idGradoStudioSilp != null && !idGradoStudioSilp.equals(dto.getGradoStudio().getId()))
                continue;
            if (dto.getDtInizio() != null && dto.getDtInizio().after(now))
                continue;
            if (dto.getDtFine() != null && dto.getDtFine().before(now))
                continue;
            titoli.add(mapTitoloStudio(dto));
        }
        return titoli;
    }

    /**
     * Gets the titolo studio per codice ministeriale.
     *
     * @param codice the codice
     * @return the titolo studio per codice ministeriale
     */
    public TitoloStudio getTitoloStudioPerCodiceMinisteriale(String codice) {
        return titoliStudioPerCodiceMinisteriale.get(codice);
    }

    /**
     * Gets the condizione occupazionale per codice ministeriale.
     *
     * @param codice the codice
     * @return the condizione occupazionale per codice ministeriale
     */
    public CondizioneOccupazionale getCondizioneOccupazionalePerCodiceMinisteriale(String codice) {
        return condizioniOccupazionaliPerCodiceMinisteriale.get(codice);
    }

    /**
     * Gets the motivo presenza in italia per codice ministeriale.
     *
     * @param codice the codice
     * @return the motivo presenza in italia per codice ministeriale
     */
    public MotivoPresenzaInItalia getMotivoPresenzaInItaliaPerCodiceMinisteriale(String codice) {
        return motiviPresenzaInItaliaPerCodiceMinisteriale.get(codice);
    }

    /**
     * Map titolo studio.
     *
     * @param dto the dto
     * @return the titolo studio
     */
    public static TitoloStudio mapTitoloStudio(TitoloStudioSilpDTO dto) {
        TitoloStudio titolo = new TitoloStudio();
        titolo.setCodice(dto.getCodiceMinisteriale());
        titolo.setCodiceSilp(dto.getId());
        titolo.setDescrizione(dto.getDescrizione());
        titolo.setDescrizioneTipoScuola(dto.getDescrizioneTipoScuola());
        titolo.setSinonimo(dto.getSinonimoTitoloStudio());
        titolo.setGradoStudio(mapGradoStudio(dto.getGradoStudio()));

        if (titolo.getDescrizioneTipoScuola() != null) {
            titolo.setDescrizione(titolo.getDescrizione() + " - " + dto.getDescrizioneTipoScuola());
        }

        return titolo;
    }

    /**
     * Map grado studio.
     *
     * @param dto the dto
     * @return the grado studio
     */
    public static GradoStudio mapGradoStudio(GradoStudioSilpDTO dto) {
        GradoStudio grado = new GradoStudio();
        grado.setCodice(dto.getCodiceMinisteriale());
        grado.setCodiceSilp(dto.getId());
        grado.setDescrizione(dto.getDescrizione());
        return grado;
    }

    /**
     * Gets the motivi presenza in italia.
     *
     * @return the motivi presenza in italia
     */
    public List<MotivoPresenzaInItalia> getMotiviPresenzaInItalia() {
        return motiviPresenzaInItalia;
    }

    /**
     * Sets the motivi presenza in italia.
     *
     * @param motiviPresenzaInItalia the new motivi presenza in italia
     */
    public void setMotiviPresenzaInItalia(List<MotivoPresenzaInItalia> motiviPresenzaInItalia) {
        this.motiviPresenzaInItalia = motiviPresenzaInItalia;
    }

    /**
     * Gets the titoli studio silp per codice ministeriale.
     *
     * @return the titoli studio silp per codice ministeriale
     */
    public Map<String, TitoloStudio> getTitoliStudioSilpPerCodiceMinisteriale() {
        return titoliStudioSilpPerCodiceMinisteriale;
    }

    /**
     * Sets the titoli studio silp per codice ministeriale.
     *
     * @param titoliStudioSilpPerCodiceMinisteriale the titoli studio silp per codice ministeriale
     */
    public void setTitoliStudioSilpPerCodiceMinisteriale(Map<String, TitoloStudio> titoliStudioSilpPerCodiceMinisteriale) {
        this.titoliStudioSilpPerCodiceMinisteriale = titoliStudioSilpPerCodiceMinisteriale;
    }

    /**
     * Gets the gradi studio silp.
     *
     * @return the gradi studio silp
     */
    public List<GradoStudio> getGradiStudioSilp() {
        return gradiStudioSilp;
    }

    /**
     * Sets the gradi studio silp.
     *
     * @param gradiStudioSilp the new gradi studio silp
     */
    public void setGradiStudioSilp(List<GradoStudio> gradiStudioSilp) {
        this.gradiStudioSilp = gradiStudioSilp;
    }

}
