# Business Process Model and Notation

## Getting Started

### Download and Installation

First, you need to install the Camunda Platform and the Camunda Modeler.  
To install the Camunda Platform locally on your machine run command below:
```
docker pull camunda/camunda-bpm-platform:latest
docker run -d --name camunda -p 8080:8080 camunda/camunda-bpm-platform:latest
```
### Create a new BPMN Diagram

#### Configure Properties for Execution
- Set process Id: payment-retrieval
- Set process name: Payment Retrieval
- Mark the process to be executable

### Deploy the Process



#### Use the Camunda Modeler to Deploy the Process

From version 3.0.0 on, you will be required to provide an URL for an Endpoint Configuration along with Deployment Details.

```
http://localhost:8080/engine-rest
```

#### Verify the Deployment with Cockpit
Open the link below and the default user and password would be: demo/demo
```
http://localhost:8080/camunda/app/cockpit
```

#### Start a Process Instance
- Use POSTMAN

Make a POST request to the following endpoint: http://localhost:8080/engine-rest/process-definition/key/payment-retrieval/start

The JSON Body should look like this:
```
{
  "variables": {
    "amount": {
      "value":555,
      "type":"long"
    },
    "item": {
      "value": "item-xyz"
    }
  }
}
```

- Use curl

```
curl -H "Content-Type: application/json" -X POST -d '{"variables": {"amount": {"value":555,"type":"long"}, "item": {"value":"item-xyz"} } }' http://localhost:8080/engine-rest/process-definition/key/payment-retrieval/start
```

### Implement an external task worker

Add following dependency in your project:
```
<dependency>
    <groupId>org.camunda.bpm</groupId>
    <artifactId>camunda-external-task-client</artifactId>
    <version>1.4.0</version>
</dependency>
```

```
ExternalTaskClient client = ExternalTaskClient.create()
        .baseUrl("http://localhost:8080/engine-rest")
        .asyncResponseTimeout(10000) // long polling timeout
        .build();
```









### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/#build-image)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-mongodb)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)

