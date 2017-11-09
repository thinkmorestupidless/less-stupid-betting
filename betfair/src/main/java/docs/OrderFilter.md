
# OrderFilter

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**includeOverallPosition** | **Boolean** | Returns overall / net position (See: OrderRunnerChange.mb / OrderRunnerChange.ml). Default&#x3D;true |  [optional]
**accountIds** | **List&lt;Long&gt;** | Internal use only &amp; should not be set on your filter (your subscription is already locked to your account). If set subscription will fail. |  [optional]
**customerStrategyRefs** | **List&lt;String&gt;** | Restricts to specified customerStrategyRefs; this will filter orders and StrategyMatchChanges accordingly (Note: overall postition is not filtered) |  [optional]
**partitionMatchedByStrategyRef** | **Boolean** | Returns strategy positions (See: OrderRunnerChange.smc&#x3D;Map&lt;customerStrategyRef, StrategyMatchChange&gt;) - these are sent in delta format as per overall position. Default&#x3D;false |  [optional]



