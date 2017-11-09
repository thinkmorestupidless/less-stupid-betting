/**
 * Betfair: Exchange Streaming API
 * API to receive streamed updates. This is an ssl socket connection of CRLF delimited json messages (see RequestMessage & ResponseMessage)
 *
 * OpenAPI spec version: 1.0.1423
 * Contact: bdp@betfair.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;


/**
 * RunnerDefinition
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-15T12:32:57.474+01:00")
public class RunnerDefinition   {
  @SerializedName("sortPriority")
  private Integer sortPriority = null;

  @SerializedName("removalDate")
  private DateTime removalDate = null;

  @SerializedName("id")
  private Long id = null;

  @SerializedName("hc")
  private Double hc = null;

  @SerializedName("adjustmentFactor")
  private Double adjustmentFactor = null;

  @SerializedName("bsp")
  private Double bsp = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    @SerializedName("ACTIVE")
    ACTIVE("ACTIVE"),
    
    @SerializedName("WINNER")
    WINNER("WINNER"),
    
    @SerializedName("LOSER")
    LOSER("LOSER"),
    
    @SerializedName("REMOVED")
    REMOVED("REMOVED"),
    
    @SerializedName("REMOVED_VACANT")
    REMOVED_VACANT("REMOVED_VACANT"),
    
    @SerializedName("HIDDEN")
    HIDDEN("HIDDEN"),
    
    @SerializedName("PLACED")
    PLACED("PLACED");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }

  @SerializedName("status")
  private StatusEnum status = null;

  public RunnerDefinition sortPriority(Integer sortPriority) {
    this.sortPriority = sortPriority;
    return this;
  }

   /**
   * Get sortPriority
   * @return sortPriority
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getSortPriority() {
    return sortPriority;
  }

  public void setSortPriority(Integer sortPriority) {
    this.sortPriority = sortPriority;
  }

  public RunnerDefinition removalDate(DateTime removalDate) {
    this.removalDate = removalDate;
    return this;
  }

   /**
   * Get removalDate
   * @return removalDate
  **/
  @ApiModelProperty(example = "null", value = "")
  public DateTime getRemovalDate() {
    return removalDate;
  }

  public void setRemovalDate(DateTime removalDate) {
    this.removalDate = removalDate;
  }

  public RunnerDefinition id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Selection Id - the id of the runner (selection)
   * @return id
  **/
  @ApiModelProperty(example = "null", value = "Selection Id - the id of the runner (selection)")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RunnerDefinition hc(Double hc) {
    this.hc = hc;
    return this;
  }

   /**
   * Handicap - the handicap of the runner (selection) (null if not applicable)
   * @return hc
  **/
  @ApiModelProperty(example = "null", value = "Handicap - the handicap of the runner (selection) (null if not applicable)")
  public Double getHc() {
    return hc;
  }

  public void setHc(Double hc) {
    this.hc = hc;
  }

  public RunnerDefinition adjustmentFactor(Double adjustmentFactor) {
    this.adjustmentFactor = adjustmentFactor;
    return this;
  }

   /**
   * Get adjustmentFactor
   * @return adjustmentFactor
  **/
  @ApiModelProperty(example = "null", value = "")
  public Double getAdjustmentFactor() {
    return adjustmentFactor;
  }

  public void setAdjustmentFactor(Double adjustmentFactor) {
    this.adjustmentFactor = adjustmentFactor;
  }

  public RunnerDefinition bsp(Double bsp) {
    this.bsp = bsp;
    return this;
  }

   /**
   * Get bsp
   * @return bsp
  **/
  @ApiModelProperty(example = "null", value = "")
  public Double getBsp() {
    return bsp;
  }

  public void setBsp(Double bsp) {
    this.bsp = bsp;
  }

  public RunnerDefinition status(StatusEnum status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(example = "null", value = "")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunnerDefinition runnerDefinition = (RunnerDefinition) o;
    return Objects.equals(this.sortPriority, runnerDefinition.sortPriority) &&
        Objects.equals(this.removalDate, runnerDefinition.removalDate) &&
        Objects.equals(this.id, runnerDefinition.id) &&
        Objects.equals(this.hc, runnerDefinition.hc) &&
        Objects.equals(this.adjustmentFactor, runnerDefinition.adjustmentFactor) &&
        Objects.equals(this.bsp, runnerDefinition.bsp) &&
        Objects.equals(this.status, runnerDefinition.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sortPriority, removalDate, id, hc, adjustmentFactor, bsp, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunnerDefinition {\n");
    
    sb.append("    sortPriority: ").append(toIndentedString(sortPriority)).append("\n");
    sb.append("    removalDate: ").append(toIndentedString(removalDate)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    hc: ").append(toIndentedString(hc)).append("\n");
    sb.append("    adjustmentFactor: ").append(toIndentedString(adjustmentFactor)).append("\n");
    sb.append("    bsp: ").append(toIndentedString(bsp)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

