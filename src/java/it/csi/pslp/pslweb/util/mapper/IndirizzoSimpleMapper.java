/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import java.util.List;

import it.csi.pslp.pslweb.business.integration.AdapterSilpsvdeWSImpl;
import it.csi.pslp.pslweb.dto.be.Comune;
import it.csi.pslp.pslweb.dto.be.Indirizzo;
import it.csi.pslp.pslweb.dto.be.Sedime;
import it.csi.silpcommonobj.dati.collocamentomirato.richiestaiscrizione.IndirizzoSimpleSilpDTO;

/**
 * The Class IndirizzoMapper.
 */
public class IndirizzoSimpleMapper extends BaseMapper<Indirizzo, IndirizzoSimpleSilpDTO> {

    /** The cm. */
    private final ComuneMapper cm = new ComuneMapper();

    /** The nm. */
    private final NazioneMapper nm = new NazioneMapper();

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in  the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends IndirizzoSimpleSilpDTO> C map(Indirizzo in, C out) {

        if (in != null && out != null) {
            out.setIndirizzo(in.getIndirizzo());
            out.setNumeroCivico(in.getNumeroCivico());
            if (null != in.getComune()) {
                out.setCap(in.getComune().getCap());
                out.setCodComune(in.getComune().getCodiceMinisteriale());

            }
            if (null != in.getToponimo()) {
//               out.setToponimo(in.getToponimo().getDescrizione());
                out.setToponimo(in.getToponimo().getCodiceMinisteriale());
            }
        }
        return out;
    }

    /**
     * Map reverse.
     *
     * @param <C> the generic type
     * @param in  the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends Indirizzo> C mapReverse(IndirizzoSimpleSilpDTO in, C out) {
        if (in == null || out == null) {
            return out;
        }

        out.setIndirizzo(in.getIndirizzo());
        try {
            Comune c = AdapterSilpsvdeWSImpl.getInstance().getComuneByCodice(in.getCodComune());
            if (c != null) {
                c.setCap(in.getCap());
                out.setComune(c);

            }
        } catch (Exception e) {
            log.error("Errore nel reperimento della provincia del comune da AdapterSilpsvdeWSImpl.getComuneByCodice", e);
        }
        out.setIndirizzo(in.getIndirizzo());
        out.setNumeroCivico(in.getNumeroCivico());
        Sedime s = new Sedime();
        /*
         * Bisogna intervenire andando a recuperare la descrizione del codice
         * ministeriale che ci arriva
         */
        try {
            /*
             * se esiste un toponimo inserito allora si cerca la descrizione e il codice
             * ministeriale
             */
            if (null != in.getToponimo()) {
                List<Sedime> listaSedimi = AdapterSilpsvdeWSImpl.getInstance().findIlSedime(in.getToponimo());
                if (listaSedimi != null && listaSedimi.size() > 0) {
                    s = listaSedimi.get(0);
                }
                out.setToponimo(s);
//            s.setDescrizione(in.getToponimo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out;
    }

    /**
     * Instantiate.
     *
     * @return the indirizzo
     */
    @Override
    protected Indirizzo instantiate() {
        return new Indirizzo();
    }

    /**
     * Instantiate reverse.
     *
     * @return the indirizzo DTO
     */
    @Override
    protected IndirizzoSimpleSilpDTO instantiateReverse() {
        return new IndirizzoSimpleSilpDTO();
    }

}
