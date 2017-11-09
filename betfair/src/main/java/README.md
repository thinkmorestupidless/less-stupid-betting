# swagger-java-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-java-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-java-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        AllRequestTypesExample requestMessage = new AllRequestTypesExample(); // AllRequestTypesExample | Requests are sent to socket
        try {
            AllResponseTypesExample result = apiInstance.requestPost(requestMessage);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#requestPost");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://stream-api.betfair.com:443/api*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DefaultApi* | [**requestPost**](docs/DefaultApi.md#requestPost) | **POST** /request | 


## Documentation for Models

 - [AllRequestTypesExample](docs/AllRequestTypesExample.md)
 - [AllResponseTypesExample](docs/AllResponseTypesExample.md)
 - [AuthenticationMessage](docs/AuthenticationMessage.md)
 - [ConnectionMessage](docs/ConnectionMessage.md)
 - [HeartbeatMessage](docs/HeartbeatMessage.md)
 - [KeyLineDefinition](docs/KeyLineDefinition.md)
 - [KeyLineSelection](docs/KeyLineSelection.md)
 - [MarketChange](docs/MarketChange.md)
 - [MarketChangeMessage](docs/MarketChangeMessage.md)
 - [MarketDataFilter](docs/MarketDataFilter.md)
 - [MarketDefinition](docs/MarketDefinition.md)
 - [MarketFilter](docs/MarketFilter.md)
 - [MarketSubscriptionMessage](docs/MarketSubscriptionMessage.md)
 - [Order](docs/Order.md)
 - [OrderChangeMessage](docs/OrderChangeMessage.md)
 - [OrderFilter](docs/OrderFilter.md)
 - [OrderMarketChange](docs/OrderMarketChange.md)
 - [OrderRunnerChange](docs/OrderRunnerChange.md)
 - [OrderSubscriptionMessage](docs/OrderSubscriptionMessage.md)
 - [PriceLadderDefinition](docs/PriceLadderDefinition.md)
 - [RequestMessage](docs/RequestMessage.md)
 - [ResponseMessage](docs/ResponseMessage.md)
 - [RunnerChange](docs/RunnerChange.md)
 - [RunnerDefinition](docs/RunnerDefinition.md)
 - [StatusMessage](docs/StatusMessage.md)
 - [StrategyMatchChange](docs/StrategyMatchChange.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author

bdp@betfair.com

