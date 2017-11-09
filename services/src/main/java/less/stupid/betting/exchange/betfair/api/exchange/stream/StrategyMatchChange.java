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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * StrategyMatchChange
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-10-15T12:32:57.474+01:00")
public class StrategyMatchChange   {
  @SerializedName("mb")
  private List<List<Double>> mb = new ArrayList<List<Double>>();

  @SerializedName("ml")
  private List<List<Double>> ml = new ArrayList<List<Double>>();

  public StrategyMatchChange mb(List<List<Double>> mb) {
    this.mb = mb;
    return this;
  }

  public StrategyMatchChange addMbItem(List<Double> mbItem) {
    this.mb.add(mbItem);
    return this;
  }

   /**
   * Matched Backs - matched amounts by distinct matched price on the Back side for this strategy
   * @return mb
  **/
  @ApiModelProperty(example = "null", value = "Matched Backs - matched amounts by distinct matched price on the Back side for this strategy")
  public List<List<Double>> getMb() {
    return mb;
  }

  public void setMb(List<List<Double>> mb) {
    this.mb = mb;
  }

  public StrategyMatchChange ml(List<List<Double>> ml) {
    this.ml = ml;
    return this;
  }

  public StrategyMatchChange addMlItem(List<Double> mlItem) {
    this.ml.add(mlItem);
    return this;
  }

   /**
   * Matched Lays - matched amounts by distinct matched price on the Lay side for this strategy
   * @return ml
  **/
  @ApiModelProperty(example = "null", value = "Matched Lays - matched amounts by distinct matched price on the Lay side for this strategy")
  public List<List<Double>> getMl() {
    return ml;
  }

  public void setMl(List<List<Double>> ml) {
    this.ml = ml;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StrategyMatchChange strategyMatchChange = (StrategyMatchChange) o;
    return Objects.equals(this.mb, strategyMatchChange.mb) &&
        Objects.equals(this.ml, strategyMatchChange.ml);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mb, ml);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StrategyMatchChange {\n");

    sb.append("    mb: ").append(toIndentedString(mb)).append("\n");
    sb.append("    ml: ").append(toIndentedString(ml)).append("\n");
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

