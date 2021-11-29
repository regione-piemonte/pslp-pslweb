/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class RigaDid {
    // verra' utilizzata la seguente strategia serializzazione degli attributi:
    // [explicit-as-modeled]

    private String codMessaggio = null;
    private String domanda = null;
    private String rispostaSi = null;
    private String rispostaNo = null;

    /**
     **/

    @JsonProperty("cod_messaggio")

    public String getCodMessaggio() {
        return codMessaggio;
    }

    public void setCodMessaggio(String codMessaggio) {
        this.codMessaggio = codMessaggio;
    }

    /**
     **/

    @JsonProperty("domanda")

    public String getDomanda() {
        return domanda;
    }

    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    /**
     **/

    @JsonProperty("risposta_si")

    public String getRispostaSi() {
        return rispostaSi;
    }

    public void setRispostaSi(String rispostaSi) {
        this.rispostaSi = rispostaSi;
    }

    /**
     **/

    @JsonProperty("risposta_no")

    public String getRispostaNo() {
        return rispostaNo;
    }

    public void setRispostaNo(String rispostaNo) {
        this.rispostaNo = rispostaNo;
    }

    /**
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RigaDid riga = (RigaDid) o;
        return Objects.equals(domanda, riga.domanda) && Objects.equals(rispostaSi, riga.rispostaSi) && Objects.equals(rispostaNo, riga.rispostaNo)
                && Objects.equals(codMessaggio, riga.codMessaggio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codMessaggio, domanda, rispostaSi, rispostaNo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RigaMsgHelp {\n");

        sb.append("    codMessaggio: ").append(toIndentedString(codMessaggio)).append("\n");
        sb.append("    domanda: ").append(toIndentedString(domanda)).append("\n");
        sb.append("    risposta: ").append(toIndentedString(rispostaSi)).append("\n");
        sb.append("    risposta: ").append(toIndentedString(rispostaNo)).append("\n");

        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
