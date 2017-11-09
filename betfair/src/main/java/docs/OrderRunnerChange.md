
# OrderRunnerChange

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**mb** | [**List&lt;List&lt;Double&gt;&gt;**](List.md) | Matched Backs - matched amounts by distinct matched price on the Back side for this runner (selection) |  [optional]
**smc** | [**Map&lt;String, StrategyMatchChange&gt;**](StrategyMatchChange.md) | Strategy Matches - Matched Backs and Matched Lays grouped by strategy reference |  [optional]
**uo** | [**List&lt;Order&gt;**](Order.md) | Unmatched Orders - orders on this runner (selection) that are not fully matched |  [optional]
**id** | **Long** | Selection Id - the id of the runner (selection) |  [optional]
**hc** | **Double** | Handicap - the handicap of the runner (selection) (null if not applicable) |  [optional]
**fullImage** | **Boolean** |  |  [optional]
**ml** | [**List&lt;List&lt;Double&gt;&gt;**](List.md) | Matched Lays - matched amounts by distinct matched price on the Lay side for this runner (selection) |  [optional]



