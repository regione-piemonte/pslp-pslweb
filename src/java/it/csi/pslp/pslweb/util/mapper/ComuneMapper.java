/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import org.apache.commons.lang.StringUtils;

import it.csi.pslp.pslweb.business.integration.AdapterSilpsvdeWSImpl;
import it.csi.pslp.pslweb.dto.be.Comune;
import it.csi.pslp.pslweb.dto.be.Provincia;
import it.csi.silos.silcommon.gestionedecodifiche.ComuneDTO;

/**
 * The Class ComuneMapper.
 */
public class ComuneMapper extends BaseMapper<Comune, ComuneDTO> {

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends ComuneDTO> C map(Comune in, C out) {
        // TODO
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
    public <C extends Comune> C mapReverse(ComuneDTO in, C out) {
        if (in == null || in.getCodiceMinisteriale() == null || out == null) {
            return out;
        }
        out.setCap(in.getCap());
        out.setCodiceMinisteriale(in.getCodiceMinisteriale());
        out.setDescrizione(in.getDescrizione());
        out.setProvincia(getProvinciaDelComune(in.getCodiceMinisteriale()));

        return out;
    }

    /**
     * Gets the provincia del comune.
     *
     * @param codiceComune the codice comune
     * @return the provincia del comune
     */
    private Provincia getProvinciaDelComune(String codiceComune) {
        Provincia provincia = null;
        if (codiceComune != null) {
            try {
                Comune c = AdapterSilpsvdeWSImpl.getInstance().getComuneByCodice(codiceComune);
                if (c != null) {
                    return c.getProvincia();
                }
            } catch (Exception e) {
                log.error("Errore nel reperimento della provincia del comune da AdapterSilpsvdeWSImpl.getComuneByCodice", e);
            }
        }
        return provincia;
    }

    /**
     * Instantiate.
     *
     * @return the comune
     */
    @Override
    protected Comune instantiate() {
        return new Comune();
    }

    /**
     * Instantiate reverse.
     *
     * @return the comune DTO
     */
    @Override
    protected ComuneDTO instantiateReverse() {
        return new ComuneDTO();
    }

    /**
     * Gets the comune by codice.
     *
     * @param codiceComune the codice comune
     * @return the comune by codice
     */
    public Comune getComuneByCodice(String codiceComune) {
        if (StringUtils.isNotBlank(codiceComune)) {
            try {
                return AdapterSilpsvdeWSImpl.getInstance().getComuneByCodice(codiceComune);
            } catch (Exception e) {
                log.error("Errore nel reperimento del comune da AdapterSilpsvdeWSImpl.getComuneByCodice for codice \"" + codiceComune + "\"", e);
            }
        }
        return null;
    }
}
