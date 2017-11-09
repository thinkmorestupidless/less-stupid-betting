
# StatusMessage

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**errorMessage** | **String** | Additional message in case of a failure |  [optional]
**errorCode** | [**ErrorCodeEnum**](#ErrorCodeEnum) | The type of error in case of a failure |  [optional]
**connectionId** | **String** | The connection id |  [optional]
**connectionClosed** | **Boolean** | Is the connection now closed |  [optional]
**statusCode** | [**StatusCodeEnum**](#StatusCodeEnum) | The status of the last request |  [optional]


<a name="ErrorCodeEnum"></a>
## Enum: ErrorCodeEnum
Name | Value
---- | -----
NO_APP_KEY | &quot;NO_APP_KEY&quot;
INVALID_APP_KEY | &quot;INVALID_APP_KEY&quot;
NO_SESSION | &quot;NO_SESSION&quot;
INVALID_SESSION_INFORMATION | &quot;INVALID_SESSION_INFORMATION&quot;
NOT_AUTHORIZED | &quot;NOT_AUTHORIZED&quot;
INVALID_INPUT | &quot;INVALID_INPUT&quot;
INVALID_CLOCK | &quot;INVALID_CLOCK&quot;
UNEXPECTED_ERROR | &quot;UNEXPECTED_ERROR&quot;
TIMEOUT | &quot;TIMEOUT&quot;
SUBSCRIPTION_LIMIT_EXCEEDED | &quot;SUBSCRIPTION_LIMIT_EXCEEDED&quot;
INVALID_REQUEST | &quot;INVALID_REQUEST&quot;
CONNECTION_FAILED | &quot;CONNECTION_FAILED&quot;
MAX_CONNECTION_LIMIT_EXCEEDED | &quot;MAX_CONNECTION_LIMIT_EXCEEDED&quot;


<a name="StatusCodeEnum"></a>
## Enum: StatusCodeEnum
Name | Value
---- | -----
SUCCESS | &quot;SUCCESS&quot;
FAILURE | &quot;FAILURE&quot;



