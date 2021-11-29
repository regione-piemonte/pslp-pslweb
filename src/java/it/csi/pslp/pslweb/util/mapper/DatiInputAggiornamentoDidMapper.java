/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.util.mapper;

import it.csi.pslp.pslweb.dto.be.DatiInputAggiornamentoDid;
import it.csi.pslp.pslweb.dto.be.RispostaQuestionario;
import it.csi.silpcommonobj.dati.questionari.EsecuzioneQuestionarioSilpDTO;
import it.csi.silpcommonobj.dati.questionari.RispostaQuestionarioSilpDTO;
import it.csi.silpcommonobj.dati.schedaprofessionale.did.pslp.DatiInputAggiornamentoDidDTO;

/**
 * The Class DatiInputAggiornamentoDidMapper.
 */
public class DatiInputAggiornamentoDidMapper extends BaseMapper<DatiInputAggiornamentoDid, DatiInputAggiornamentoDidDTO> {

    /**
     * Map.
     *
     * @param <C> the generic type
     * @param in the in
     * @param out the out
     * @return the c
     */
    @Override
    public <C extends DatiInputAggiornamentoDidDTO> C map(DatiInputAggiornamentoDid in, C out) {

        out.setCodiceFiscale(in.getCodiceFiscale());
        out.setCodStatoDid(in.getCodStatoDid());
        out.setDataDid(in.getDataDid());
        out.setDataStatoDid(in.getDataStatoDid());
        out.setIdAnagrafica(in.getIdAnagrafica());
        out.setIdDid(in.getIdDid());
        EsecuzioneQuestionarioSilpDTO questionarioDto = new EsecuzioneQuestionarioSilpDTO();
        if (null != in.getRispostaQuestionario() && !in.getRispostaQuestionario().isEmpty()) {
            RispostaQuestionarioSilpDTO[] leRisposte = new RispostaQuestionarioSilpDTO[in.getRispostaQuestionario().size()];
            int i = 0;
            for (RispostaQuestionario laRisposta : in.getRispostaQuestionario()) {
                RispostaQuestionarioSilpDTO laRispDto = new RispostaQuestionarioSilpDTO();
                laRispDto.setIdDomanda(laRisposta.getIdDomanda());
                laRispDto.setIdRisposta(laRisposta.getIdRisposta());
                laRispDto.setRispostaLibera(laRisposta.getRispostaLibera());
                leRisposte[i++] = laRispDto;
                questionarioDto.setIdConfigurazioneQuestionario(laRisposta.getIdConfigurazioneQuestionario());
            }
            questionarioDto.setRisposte(leRisposte);
        }
        out.setQuestionario(questionarioDto);
        return out;
    }

    /**
     * Instantiate.
     *
     * @return the dati input aggiornamento did
     */
    @Override
    protected DatiInputAggiornamentoDid instantiate() {
        return new DatiInputAggiornamentoDid();
    }

    /**
     * Instantiate reverse.
     *
     * @return the dati input aggiornamento did DTO
     */
    @Override
    protected DatiInputAggiornamentoDidDTO instantiateReverse() {
        return new DatiInputAggiornamentoDidDTO();
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
    public <C extends DatiInputAggiornamentoDid> C mapReverse(DatiInputAggiornamentoDidDTO in, C out) {
        // TODO Auto-generated method stub
        return null;
    }

}
