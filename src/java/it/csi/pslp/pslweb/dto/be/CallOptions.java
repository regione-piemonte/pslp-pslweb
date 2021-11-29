/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.dto.be;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import javax.validation.constraints.*;

public class CallOptions   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer desiredStatus = null;
  private Integer executionTime = null;

  /**
   * http status che il servizio dovra sollevare
   **/
  

  @JsonProperty("desiredStatus") 
 
  public Integer getDesiredStatus() {
    return desiredStatus;
  }
  public void setDesiredStatus(Integer desiredStatus) {
    this.desiredStatus = desiredStatus;
  }

  /**
   * tempo in millisecondi che il servizio dovra impiegare per l&#39;esecuzione (per testare meccanismi di timeout)
   **/
  

  @JsonProperty("executionTime") 
 
  public Integer getExecutionTime() {
    return executionTime;
  }
  public void setExecutionTime(Integer executionTime) {
    this.executionTime = executionTime;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CallOptions callOptions = (CallOptions) o;
    return Objects.equals(desiredStatus, callOptions.desiredStatus) &&
        Objects.equals(executionTime, callOptions.executionTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(desiredStatus, executionTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CallOptions {\n");
    
    sb.append("    desiredStatus: ").append(toIndentedString(desiredStatus)).append("\n");
    sb.append("    executionTime: ").append(toIndentedString(executionTime)).append("\n");
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

