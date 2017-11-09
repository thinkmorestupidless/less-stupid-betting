
# MarketChange

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**rc** | [**List&lt;RunnerChange&gt;**](RunnerChange.md) | Runner Changes - a list of changes to runners (or null if un-changed) |  [optional]
**img** | **Boolean** | Image - replace existing prices / data with the data supplied: it is not a delta (or null if delta) |  [optional]
**tv** | **Double** | The total amount matched across the market. This value is truncated at 2dp (or null if un-changed) |  [optional]
**con** | **Boolean** | Conflated - have more than a single change been combined (or null if not conflated) |  [optional]
**marketDefinition** | [**MarketDefinition**](MarketDefinition.md) | Market Definition - the definition of the market (or null if un-changed) |  [optional]
**id** | **String** | Market Id - the id of the market |  [optional]



