offer-demo-service
=============

A Maven / Spring Boot / Java project to implement the requested story and demonstrate some functional testing.

The project is composed of this parent project and 2 submodules:

- offer-demo-service which contains the unit tested / implemented story (one light integration test is using H2).<br /><br />
_This story has been implemented API contract first, there is also a test to prove is meets the design.<br />
Unfortunately, the swagger-models library used by assertj-swagger which verifies the implementation meets the design, is [buggy](http://www.programmersought.com/article/6509169151/), the test integration build will therefore copy the fixed version of that library to the current Maven repository._<br /><br />
_Note also that medium-size integration tests run in a Docker TestContainer._<br /><br />
- offer-demo-service-devops is used to create a docker-image and a docker-compose file integrating the production DB and [Swagger](http://editor.swagger.io), so one can play with the API.<br />

**Usage:**
-------------

###### _Prerequisites before building it:_ 

1. Java >= 8<br /> 
2. Lombok if you want to play with the code from an IDE.<br /> 
3. Docker / Compose<br />  

#### **Build it:**

From the root folder: <br /><br />
`mvnw clean install`<br />
This runs the unit tests, builds the application, packs it into docker and produces a docker-compose file.

This runs the integration tests:<br />
`mvnw -Pmedium verify -f offer-demo-service`<br />

#### **Launch it:**
`cd offer-demo-service-devops/target/docker_context`<br />
`docker-compose up`

#### **Use it:**
[http://localhost:8080](http://localhost:8080) -> Swagger to play with the API, the data are persisted.<br />
`http://localhost:8081` -> Spring Boot management, see the available endpoints [here] (eg. http://localhost:8081/actuator/health).<br />

[here]: https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html