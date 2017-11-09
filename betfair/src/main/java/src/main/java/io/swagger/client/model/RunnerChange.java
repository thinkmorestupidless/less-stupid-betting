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
import java.util.ArrayList;
import java.util.List;


/**
 * RunnerChange
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-15T12:32:57.474+01:00")
public class RunnerChange   {
  @SerializedName("tv")
  private Double tv = null;

  @SerializedName("batb")
  private List<List<Double>> batb = new ArrayList<List<Double>>();

  @SerializedName("spb")
  private List<List<Double>> spb = new ArrayList<List<Double>>();

  @SerializedName("bdatl")
  private List<List<Double>> bdatl = new ArrayList<List<Double>>();

  @SerializedName("trd")
  private List<List<Double>> trd = new ArrayList<List<Double>>();

  @SerializedName("spf")
  private Double spf = null;

  @SerializedName("ltp")
  private Double ltp = null;

  @SerializedName("atb")
  private List<List<Double>> atb = new ArrayList<List<Double>>();

  @SerializedName("spl")
  private List<List<Double>> spl = new ArrayList<List<Double>>();

  @SerializedName("spn")
  private Double spn = null;

  @SerializedName("atl")
  private List<List<Double>> atl = new ArrayList<List<Double>>();

  @SerializedName("batl")
  private List<List<Double>> batl = new ArrayList<List<Double>>();

  @SerializedName("id")
  private Long id = null;

  @SerializedName("hc")
  private Double hc = null;

  @SerializedName("bdatb")
  private List<List<Double>> bdatb = new ArrayList<List<Double>>();

  public RunnerChange tv(Double tv) {
    this.tv = tv;
    return this;
  }

   /**
   * The total amount matched. This value is truncated at 2dp.
   * @return tv
  **/
  @ApiModelProperty(example = "null", value = "The total amount matched. This value is truncated at 2dp.")
  public Double getTv() {
    return tv;
  }

  public void setTv(Double tv) {
    this.tv = tv;
  }

  public RunnerChange batb(List<List<Double>> batb) {
    this.batb = batb;
    return this;
  }

  public RunnerChange addBatbItem(List<Double> batbItem) {
    this.batb.add(batbItem);
    return this;
  }

   /**
   * Best Available To Back - LevelPriceVol triple delta of price changes, keyed by level (0 vol is remove)
   * @return batb
  **/
  @ApiModelProperty(example = "null", value = "Best Available To Back - LevelPriceVol triple delta of price changes, keyed by level (0 vol is remove)")
  public List<List<Double>> getBatb() {
    return batb;
  }

  public void setBatb(List<List<Double>> batb) {
    this.batb = batb;
  }

  public RunnerChange spb(List<List<Double>> spb) {
    this.spb = spb;
    return this;
  }

  public RunnerChange addSpbItem(List<Double> spbItem) {
    this.spb.add(spbItem);
    return this;
  }

   /**
   * Starting Price Back - PriceVol tuple delta of price changes (0 vol is remove)
   * @return spb
  **/
  @ApiModelProperty(example = "null", value = "Starting Price Back - PriceVol tuple delta of price changes (0 vol is remove)")
  public List<List<Double>> getSpb() {
    return spb;
  }

  public void setSpb(List<List<Double>> spb) {
    this.spb = spb;
  }

  public RunnerChange bdatl(List<List<Double>> bdatl) {
    this.bdatl = bdatl;
    return this;
  }

  public RunnerChange addBdatlItem(List<Double> bdatlItem) {
    this.bdatl.add(bdatlItem);
    return this;
  }

   /**
   * Best Display Available To Lay (includes virtual prices)- LevelPriceVol triple delta of price changes, keyed by level (0 vol is remove)
   * @return bdatl
  **/
  @ApiModelProperty(example = "null", value = "Best Display Available To Lay (includes virtual prices)- LevelPriceVol triple delta of price changes, keyed by level (0 vol is remove)")
  public List<List<Double>> getBdatl() {
    return bdatl;
  }

  public void setBdatl(List<List<Double>> bdatl) {
    this.bdatl = bdatl;
  }

  public RunnerChange trd(List<List<Double>> trd) {
    this.trd = trd;
    return this;
  }

  public RunnerChange addTrdItem(List<Double> trdItem) {
    this.trd.add(trdItem);
    return this;
  }

   /**
   * Traded - PriceVol tuple delta of price changes (0 vol is remove)
   * @return trd
  **/
  @ApiModelProperty(example = "null", value = "Traded - PriceVol tuple delta of price changes (0 vol is remove)")
  public List<List<Double>> getTrd() {
    return trd;
  }

  public void setTrd(List<List<Double>> trd) {
    this.trd = trd;
  }

  public RunnerChange spf(Double spf) {
    this.spf = spf;
    return this;
  }

   /**
   * Starting Price Far - The far starting price (or null if un-changed)
   * @return spf
  **/
  @ApiModelProperty(example = "null", value = "Starting Price Far - The far starting price (or null if un-changed)")
  public Double getSpf() {
    return spf;
  }

  public void setSpf(Double spf) {
    this.spf = spf;
  }

  public RunnerChange ltp(Double ltp) {
    this.ltp = ltp;
    return this;
  }

   /**
   * Last Traded Price - The last traded price (or null if un-changed)
   * @return ltp
  **/
  @ApiModelProperty(example = "null", value = "Last Traded Price - The last traded price (or null if un-changed)")
  public Double getLtp() {
    return ltp;
  }

  public void setLtp(Double ltp) {
    this.ltp = ltp;
  }

  public RunnerChange atb(List<List<Double>> atb) {
    this.atb = atb;
    return this;
  }

  public RunnerChange addAtbItem(List<Double> atbItem) {
    this.atb.add(atbItem);
    return this;
  }

   /**
   * Available To Back - PriceVol tuple delta of price changes (0 vol is remove)
   * @return atb
  **/
  @ApiModelProperty(example = "null", value = "Available To Back - PriceVol tuple delta of price changes (0 vol is remove)")
  public List<List<Double>> getAtb() {
    return atb;
  }

  public void setAtb(List<List<Double>> atb) {
    this.atb = atb;
  }

  public RunnerChange spl(List<List<Double>> spl) {
    this.spl = spl;
    return this;
  }

  public RunnerChange addSplItem(List<Double> splItem) {
    this.spl.add(splItem);
    return this;
  }

   /**
   * Starting Price Lay - PriceVol tuple delta of price changes (0 vol is remove)
   * @return spl
  **/
  @ApiModelProperty(example = "null", value = "Starting Price Lay - PriceVol tuple delta of price changes (0 vol is remove)")
  public List<List<Double>> getSpl() {
    return spl;
  }

  public void setSpl(List<List<Double>> spl) {
    this.spl = spl;
  }

  public RunnerChange spn(Double spn) {
    this.spn = spn;
    return this;
  }

   /**
   * Starting Price Near - The far starting price (or null if un-changed)
   * @return spn
  **/
  @ApiModelProperty(example = "null", value = "Starting Price Near - The far starting price (or null if un-changed)")
  public Double getSpn() {
    return spn;
  }

  public void setSpn(Double spn) {
    this.spn = spn;
  }

  public RunnerChange atl(List<List<Double>> atl) {
    this.atl = atl;
    return this;
  }

  public RunnerChange addAtlItem(List<Double> atlItem) {
    this.atl.add(atlItem);
    return this;
  }

   /**
   * Available To Lay - PriceVol tuple delta of price changes (0 vol is remove)
   * @return atl
  **/
  @ApiModelProperty(example = "null", value = "Available To Lay - PriceVol tuple delta of price changes (0 vol is remove)")
  public List<List<Double>> getAtl() {
    return atl;
  }

  public void setAtl(List<List<Double>> atl) {
    this.atl = atl;
  }

  public RunnerChange batl(List<List<Double>> batl) {
    this.batl = batl;
    return this;
  }

  public RunnerChange addBatlItem(List<Double> batlItem) {
    this.batl.add(batlItem);
    return this;
  }

   /**
   * Best Available To Lay - LevelPriceVol triple delta of price changes, keyed by level (0 vol is remove)
   * @return batl
  **/
  @ApiModelProperty(example = "null", value = "Best Available To Lay - LevelPriceVol triple delta of price changes, keyed by level (0 vol is remove)")
  public List<List<Double>> getBatl() {
    return batl;
  }

  public void setBatl(List<List<Double>> batl) {
    this.batl = batl;
  }

  public RunnerChange id(Long id) {
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

  public RunnerChange hc(Double hc) {
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

  public RunnerChange bdatb(List<List<Double>> bdatb) {
    this.bdatb = bdatb;
    return this;
  }

  public RunnerChange addBdatbItem(List<Double> bdatbItem) {
    this.bdatb.add(bdatbItem);
    return this;
  }

   /**
   * Best Display Available To Back (includes virtual prices)- LevelPriceVol triple delta of price changes, keyed by level (0 vol is remove)
   * @return bdatb
  **/
  @ApiModelProperty(example = "null", value = "Best Display Available To Back (includes virtual prices)- LevelPriceVol triple delta of price changes, keyed by level (0 vol is remove)")
  public List<List<Double>> getBdatb() {
    return bdatb;
  }

  public void setBdatb(List<List<Double>> bdatb) {
    this.bdatb = bdatb;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunnerChange runnerChange = (RunnerChange) o;
    return Objects.equals(this.tv, runnerChange.tv) &&
        Objects.equals(this.batb, runnerChange.batb) &&
        Objects.equals(this.spb, runnerChange.spb) &&
        Objects.equals(this.bdatl, runnerChange.bdatl) &&
        Objects.equals(this.trd, runnerChange.trd) &&
        Objects.equals(this.spf, runnerChange.spf) &&
        Objects.equals(this.ltp, runnerChange.ltp) &&
        Objects.equals(this.atb, runnerChange.atb) &&
        Objects.equals(this.spl, runnerChange.spl) &&
        Objects.equals(this.spn, runnerChange.spn) &&
        Objects.equals(this.atl, runnerChange.atl) &&
        Objects.equals(this.batl, runnerChange.batl) &&
        Objects.equals(this.id, runnerChange.id) &&
        Objects.equals(this.hc, runnerChange.hc) &&
        Objects.equals(this.bdatb, runnerChange.bdatb);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tv, batb, spb, bdatl, trd, spf, ltp, atb, spl, spn, atl, batl, id, hc, bdatb);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunnerChange {\n");
    
    sb.append("    tv: ").append(toIndentedString(tv)).append("\n");
    sb.append("    batb: ").append(toIndentedString(batb)).append("\n");
    sb.append("    spb: ").append(toIndentedString(spb)).append("\n");
    sb.append("    bdatl: ").append(toIndentedString(bdatl)).append("\n");
    sb.append("    trd: ").append(toIndentedString(trd)).append("\n");
    sb.append("    spf: ").append(toIndentedString(spf)).append("\n");
    sb.append("    ltp: ").append(toIndentedString(ltp)).append("\n");
    sb.append("    atb: ").append(toIndentedString(atb)).append("\n");
    sb.append("    spl: ").append(toIndentedString(spl)).append("\n");
    sb.append("    spn: ").append(toIndentedString(spn)).append("\n");
    sb.append("    atl: ").append(toIndentedString(atl)).append("\n");
    sb.append("    batl: ").append(toIndentedString(batl)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    hc: ").append(toIndentedString(hc)).append("\n");
    sb.append("    bdatb: ").append(toIndentedString(bdatb)).append("\n");
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

