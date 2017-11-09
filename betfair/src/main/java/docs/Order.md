
# Order

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**side** | [**SideEnum**](#SideEnum) | Side - the side of the order. For Line markets a &#39;B&#39; bet refers to a SELL line and an &#39;L&#39; bet refers to a BUY line. |  [optional]
**sv** | **Double** | Size Voided - the amount of the order that has been voided |  [optional]
**pt** | [**PtEnum**](#PtEnum) | Persistence Type - whether the order will persist at in play or not (L &#x3D; LAPSE, P &#x3D; PERSIST, MOC &#x3D; Market On Close) |  [optional]
**ot** | [**OtEnum**](#OtEnum) | Order Type - the type of the order (L &#x3D; LIMIT, MOC &#x3D; MARKET_ON_CLOSE, LOC &#x3D; LIMIT_ON_CLOSE) |  [optional]
**p** | **Double** | Price - the original placed price of the order. Line markets operate at even-money odds of 2.0. However, price for these markets refers to the line positions available as defined by the markets min-max range and interval steps |  [optional]
**sc** | **Double** | Size Cancelled - the amount of the order that has been cancelled |  [optional]
**rc** | **String** | Regulator Code - the regulator of the order |  [optional]
**s** | **Double** | Size - the original placed size of the order |  [optional]
**pd** | **Long** | Placed Date - the date the order was placed |  [optional]
**rac** | **String** | Regulator Auth Code - the auth code returned by the regulator |  [optional]
**md** | **Long** | Matched Date - the date the order was matched (null if the order is not matched) |  [optional]
**ld** | **Long** | Lapsed Date - the date the order was lapsed (null if the order is not lapsed) |  [optional]
**sl** | **Double** | Size Lapsed - the amount of the order that has been lapsed |  [optional]
**avp** | **Double** | Average Price Matched - the average price the order was matched at (null if the order is not matched). This value is not meaningful for activity on Line markets and is not guaranteed to be returned or maintained for these markets. |  [optional]
**sm** | **Double** | Size Matched - the amount of the order that has been matched |  [optional]
**rfo** | **String** | Order Reference - the customer&#39;s order reference for this order (empty string if one was not set) |  [optional]
**id** | **String** | Bet Id - the id of the order |  [optional]
**bsp** | **Double** | BSP Liability - the BSP liability of the order (null if the order is not a BSP order) |  [optional]
**rfs** | **String** | Strategy Reference - the customer&#39;s strategy reference for this order (empty string if one was not set) |  [optional]
**status** | [**StatusEnum**](#StatusEnum) | Status - the status of the order (E &#x3D; EXECUTABLE, EC &#x3D; EXECUTION_COMPLETE) |  [optional]
**sr** | **Double** | Size Remaining - the amount of the order that is remaining unmatched |  [optional]


<a name="SideEnum"></a>
## Enum: SideEnum
Name | Value
---- | -----
B | &quot;B&quot;
L | &quot;L&quot;


<a name="PtEnum"></a>
## Enum: PtEnum
Name | Value
---- | -----
L | &quot;L&quot;
P | &quot;P&quot;
MOC | &quot;MOC&quot;


<a name="OtEnum"></a>
## Enum: OtEnum
Name | Value
---- | -----
L | &quot;L&quot;
LOC | &quot;LOC&quot;
MOC | &quot;MOC&quot;


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
E | &quot;E&quot;
EC | &quot;EC&quot;



