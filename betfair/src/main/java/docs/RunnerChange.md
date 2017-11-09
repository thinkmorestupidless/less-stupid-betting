
# RunnerChange

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**tv** | **Double** | The total amount matched. This value is truncated at 2dp. |  [optional]
**batb** | [**List&lt;List&lt;Double&gt;&gt;**](List.md) | Best Available To Back - LevelPriceVol triple delta of price changes, keyed by level (0 vol is remove) |  [optional]
**spb** | [**List&lt;List&lt;Double&gt;&gt;**](List.md) | Starting Price Back - PriceVol tuple delta of price changes (0 vol is remove) |  [optional]
**bdatl** | [**List&lt;List&lt;Double&gt;&gt;**](List.md) | Best Display Available To Lay (includes virtual prices)- LevelPriceVol triple delta of price changes, keyed by level (0 vol is remove) |  [optional]
**trd** | [**List&lt;List&lt;Double&gt;&gt;**](List.md) | Traded - PriceVol tuple delta of price changes (0 vol is remove) |  [optional]
**spf** | **Double** | Starting Price Far - The far starting price (or null if un-changed) |  [optional]
**ltp** | **Double** | Last Traded Price - The last traded price (or null if un-changed) |  [optional]
**atb** | [**List&lt;List&lt;Double&gt;&gt;**](List.md) | Available To Back - PriceVol tuple delta of price changes (0 vol is remove) |  [optional]
**spl** | [**List&lt;List&lt;Double&gt;&gt;**](List.md) | Starting Price Lay - PriceVol tuple delta of price changes (0 vol is remove) |  [optional]
**spn** | **Double** | Starting Price Near - The far starting price (or null if un-changed) |  [optional]
**atl** | [**List&lt;List&lt;Double&gt;&gt;**](List.md) | Available To Lay - PriceVol tuple delta of price changes (0 vol is remove) |  [optional]
**batl** | [**List&lt;List&lt;Double&gt;&gt;**](List.md) | Best Available To Lay - LevelPriceVol triple delta of price changes, keyed by level (0 vol is remove) |  [optional]
**id** | **Long** | Selection Id - the id of the runner (selection) |  [optional]
**hc** | **Double** | Handicap - the handicap of the runner (selection) (null if not applicable) |  [optional]
**bdatb** | [**List&lt;List&lt;Double&gt;&gt;**](List.md) | Best Display Available To Back (includes virtual prices)- LevelPriceVol triple delta of price changes, keyed by level (0 vol is remove) |  [optional]



