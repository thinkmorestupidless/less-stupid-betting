
# OrderChangeMessage

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**ct** | [**CtEnum**](#CtEnum) | Change Type - set to indicate the type of change - if null this is a delta) |  [optional]
**clk** | **String** | Token value (non-null) should be stored and passed in a MarketSubscriptionMessage to resume subscription (in case of disconnect) |  [optional]
**heartbeatMs** | **Long** | Heartbeat Milliseconds - the heartbeat rate (may differ from requested: bounds are 500 to 30000) |  [optional]
**pt** | **Long** | Publish Time (in millis since epoch) that the changes were generated |  [optional]
**oc** | [**List&lt;OrderMarketChange&gt;**](OrderMarketChange.md) | OrderMarketChanges - the modifications to account&#39;s orders (will be null on a heartbeat |  [optional]
**initialClk** | **String** | Token value (non-null) should be stored and passed in a MarketSubscriptionMessage to resume subscription (in case of disconnect) |  [optional]
**conflateMs** | **Long** | Conflate Milliseconds - the conflation rate (may differ from that requested if subscription is delayed) |  [optional]
**segmentType** | [**SegmentTypeEnum**](#SegmentTypeEnum) | Segment Type - if the change is split into multiple segments, this denotes the beginning and end of a change, and segments in between. Will be null if data is not segmented |  [optional]
**status** | **Integer** | Stream status: set to null if the exchange stream data is up to date and 503 if the downstream services are experiencing latencies |  [optional]


<a name="CtEnum"></a>
## Enum: CtEnum
Name | Value
---- | -----
SUB_IMAGE | &quot;SUB_IMAGE&quot;
RESUB_DELTA | &quot;RESUB_DELTA&quot;
HEARTBEAT | &quot;HEARTBEAT&quot;


<a name="SegmentTypeEnum"></a>
## Enum: SegmentTypeEnum
Name | Value
---- | -----
SEG_START | &quot;SEG_START&quot;
SEG | &quot;SEG&quot;
SEG_END | &quot;SEG_END&quot;



