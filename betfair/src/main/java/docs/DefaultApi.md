# DefaultApi

All URIs are relative to *http://stream-api.betfair.com:443/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**requestPost**](DefaultApi.md#requestPost) | **POST** /request | 


<a name="requestPost"></a>
# **requestPost**
> AllResponseTypesExample requestPost(requestMessage)



This is a socket protocol delimited by CRLF (not http)

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
AllRequestTypesExample requestMessage = new AllRequestTypesExample(); // AllRequestTypesExample | Requests are sent to socket
try {
    AllResponseTypesExample result = apiInstance.requestPost(requestMessage);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#requestPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestMessage** | [**AllRequestTypesExample**](AllRequestTypesExample.md)| Requests are sent to socket |

### Return type

[**AllResponseTypesExample**](AllResponseTypesExample.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

