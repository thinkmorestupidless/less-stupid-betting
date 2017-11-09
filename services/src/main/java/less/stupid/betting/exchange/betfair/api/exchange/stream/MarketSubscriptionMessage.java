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


package less.stupid.betting.exchange.betfair.api.exchange.stream;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;


/**
 * MarketSubscriptionMessage
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-15T12:32:57.474+01:00")
public class MarketSubscriptionMessage extends RequestMessage {
  @SerializedName("segmentationEnabled")
  private Boolean segmentationEnabled = null;

  @SerializedName("clk")
  private String clk = null;

  @SerializedName("heartbeatMs")
  private Long heartbeatMs = null;

  @SerializedName("initialClk")
  private String initialClk = null;

  @SerializedName("marketFilter")
  private MarketFilter marketFilter = null;

  @SerializedName("conflateMs")
  private Long conflateMs = null;

  @SerializedName("marketDataFilter")
  private MarketDataFilter marketDataFilter = null;

  public MarketSubscriptionMessage segmentationEnabled(Boolean segmentationEnabled) {
    this.segmentationEnabled = segmentationEnabled;
    return this;
  }

   /**
   * Segmentation Enabled - allow the server to send large sets of data in segments, instead of a single block
   * @return segmentationEnabled
  **/
  @ApiModelProperty(example = "null", value = "Segmentation Enabled - allow the server to send large sets of data in segments, instead of a single block")
  public Boolean getSegmentationEnabled() {
    return segmentationEnabled;
  }

  public void setSegmentationEnabled(Boolean segmentationEnabled) {
    this.segmentationEnabled = segmentationEnabled;
  }

  public MarketSubscriptionMessage clk(String clk) {
    this.clk = clk;
    return this;
  }

   /**
   * Token value delta (received in MarketChangeMessage) that should be passed to resume a subscription
   * @return clk
  **/
  @ApiModelProperty(example = "null", value = "Token value delta (received in MarketChangeMessage) that should be passed to resume a subscription")
  public String getClk() {
    return clk;
  }

  public void setClk(String clk) {
    this.clk = clk;
  }

  public MarketSubscriptionMessage heartbeatMs(Long heartbeatMs) {
    this.heartbeatMs = heartbeatMs;
    return this;
  }

   /**
   * Heartbeat Milliseconds - the heartbeat rate (looped back on initial image after validation: bounds are 500 to 5000)
   * @return heartbeatMs
  **/
  @ApiModelProperty(example = "null", value = "Heartbeat Milliseconds - the heartbeat rate (looped back on initial image after validation: bounds are 500 to 5000)")
  public Long getHeartbeatMs() {
    return heartbeatMs;
  }

  public void setHeartbeatMs(Long heartbeatMs) {
    this.heartbeatMs = heartbeatMs;
  }

  public MarketSubscriptionMessage initialClk(String initialClk) {
    this.initialClk = initialClk;
    return this;
  }

   /**
   * Token value (received in initial MarketChangeMessage) that should be passed to resume a subscription
   * @return initialClk
  **/
  @ApiModelProperty(example = "null", value = "Token value (received in initial MarketChangeMessage) that should be passed to resume a subscription")
  public String getInitialClk() {
    return initialClk;
  }

  public void setInitialClk(String initialClk) {
    this.initialClk = initialClk;
  }

  public MarketSubscriptionMessage marketFilter(MarketFilter marketFilter) {
    this.marketFilter = marketFilter;
    return this;
  }

   /**
   * Get marketFilter
   * @return marketFilter
  **/
  @ApiModelProperty(example = "null", value = "")
  public MarketFilter getMarketFilter() {
    return marketFilter;
  }

  public void setMarketFilter(MarketFilter marketFilter) {
    this.marketFilter = marketFilter;
  }

  public MarketSubscriptionMessage conflateMs(Long conflateMs) {
    this.conflateMs = conflateMs;
    return this;
  }

   /**
   * Conflate Milliseconds - the conflation rate (looped back on initial image after validation: bounds are 0 to 120000)
   * @return conflateMs
  **/
  @ApiModelProperty(example = "null", value = "Conflate Milliseconds - the conflation rate (looped back on initial image after validation: bounds are 0 to 120000)")
  public Long getConflateMs() {
    return conflateMs;
  }

  public void setConflateMs(Long conflateMs) {
    this.conflateMs = conflateMs;
  }

  public MarketSubscriptionMessage marketDataFilter(MarketDataFilter marketDataFilter) {
    this.marketDataFilter = marketDataFilter;
    return this;
  }

   /**
   * Get marketDataFilter
   * @return marketDataFilter
  **/
  @ApiModelProperty(example = "null", value = "")
  public MarketDataFilter getMarketDataFilter() {
    return marketDataFilter;
  }

  public void setMarketDataFilter(MarketDataFilter marketDataFilter) {
    this.marketDataFilter = marketDataFilter;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MarketSubscriptionMessage marketSubscriptionMessage = (MarketSubscriptionMessage) o;
    return Objects.equals(this.segmentationEnabled, marketSubscriptionMessage.segmentationEnabled) &&
        Objects.equals(this.clk, marketSubscriptionMessage.clk) &&
        Objects.equals(this.heartbeatMs, marketSubscriptionMessage.heartbeatMs) &&
        Objects.equals(this.initialClk, marketSubscriptionMessage.initialClk) &&
        Objects.equals(this.marketFilter, marketSubscriptionMessage.marketFilter) &&
        Objects.equals(this.conflateMs, marketSubscriptionMessage.conflateMs) &&
        Objects.equals(this.marketDataFilter, marketSubscriptionMessage.marketDataFilter) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(segmentationEnabled, clk, heartbeatMs, initialClk, marketFilter, conflateMs, marketDataFilter, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MarketSubscriptionMessage {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    segmentationEnabled: ").append(toIndentedString(segmentationEnabled)).append("\n");
    sb.append("    clk: ").append(toIndentedString(clk)).append("\n");
    sb.append("    heartbeatMs: ").append(toIndentedString(heartbeatMs)).append("\n");
    sb.append("    initialClk: ").append(toIndentedString(initialClk)).append("\n");
    sb.append("    marketFilter: ").append(toIndentedString(marketFilter)).append("\n");
    sb.append("    conflateMs: ").append(toIndentedString(conflateMs)).append("\n");
    sb.append("    marketDataFilter: ").append(toIndentedString(marketDataFilter)).append("\n");
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

