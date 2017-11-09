
# MarketDefinition

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**venue** | **String** |  |  [optional]
**settledTime** | [**DateTime**](DateTime.md) |  |  [optional]
**timezone** | **String** |  |  [optional]
**eachWayDivisor** | **Double** |  |  [optional]
**regulators** | **List&lt;String&gt;** | The market regulators. |  [optional]
**marketType** | **String** |  |  [optional]
**marketBaseRate** | **Double** |  |  [optional]
**numberOfWinners** | **Integer** |  |  [optional]
**countryCode** | **String** |  |  [optional]
**lineMaxUnit** | **Double** | For Handicap and Line markets, the maximum value for the outcome, in market units for this market (eg 100 runs). |  [optional]
**inPlay** | **Boolean** |  |  [optional]
**betDelay** | **Integer** |  |  [optional]
**bspMarket** | **Boolean** |  |  [optional]
**bettingType** | [**BettingTypeEnum**](#BettingTypeEnum) |  |  [optional]
**numberOfActiveRunners** | **Integer** |  |  [optional]
**lineMinUnit** | **Double** | For Handicap and Line markets, the minimum value for the outcome, in market units for this market (eg 0 runs). |  [optional]
**eventId** | **String** |  |  [optional]
**crossMatching** | **Boolean** |  |  [optional]
**runnersVoidable** | **Boolean** |  |  [optional]
**turnInPlayEnabled** | **Boolean** |  |  [optional]
**priceLadderDefinition** | [**PriceLadderDefinition**](PriceLadderDefinition.md) | Definition of the price ladder type and any related data. |  [optional]
**keyLineDefinition** | [**KeyLineDefinition**](KeyLineDefinition.md) | Definition of a markets key line selection (for valid markets), comprising the selectionId and handicap of the team it is applied to. |  [optional]
**suspendTime** | [**DateTime**](DateTime.md) |  |  [optional]
**discountAllowed** | **Boolean** |  |  [optional]
**persistenceEnabled** | **Boolean** |  |  [optional]
**runners** | [**List&lt;RunnerDefinition&gt;**](RunnerDefinition.md) |  |  [optional]
**version** | **Long** |  |  [optional]
**eventTypeId** | **String** | The Event Type the market is contained within. |  [optional]
**complete** | **Boolean** |  |  [optional]
**openDate** | [**DateTime**](DateTime.md) |  |  [optional]
**marketTime** | [**DateTime**](DateTime.md) |  |  [optional]
**bspReconciled** | **Boolean** |  |  [optional]
**lineInterval** | **Double** | For Handicap and Line markets, the lines available on this market will be between the range of lineMinUnit and lineMaxUnit, in increments of the lineInterval value. e.g. If unit is runs, lineMinUnit&#x3D;10, lineMaxUnit&#x3D;20 and lineInterval&#x3D;0.5, then valid lines include 10, 10.5, 11, 11.5 up to 20 runs. |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]


<a name="BettingTypeEnum"></a>
## Enum: BettingTypeEnum
Name | Value
---- | -----
ODDS | &quot;ODDS&quot;
LINE | &quot;LINE&quot;
RANGE | &quot;RANGE&quot;
ASIAN_HANDICAP_DOUBLE_LINE | &quot;ASIAN_HANDICAP_DOUBLE_LINE&quot;
ASIAN_HANDICAP_SINGLE_LINE | &quot;ASIAN_HANDICAP_SINGLE_LINE&quot;


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
INACTIVE | &quot;INACTIVE&quot;
OPEN | &quot;OPEN&quot;
SUSPENDED | &quot;SUSPENDED&quot;
CLOSED | &quot;CLOSED&quot;



