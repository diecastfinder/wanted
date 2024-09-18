# Wanted

The project that helps to manage list of desired models for collection.
## Project tech stack
Java 17,
Spring boot,
RestTemplate,
Circuit Breaker

Spring JPA
H2 in memory DB,
or MariaDB

Spring MVC
Thymeleaf for UI

## Used patterns and approaches.
* Microservices
* Repositories (JPA/Hibernate) for working with DB
* Mapstruct to convert POJOs between domain (DB) model and API model.
* Message queue: ActiveMQ

## Testing
* PACT consumer contract test

## Configuration in IntelliJ
1. Add "local-discovery" to Active profiles

## How to run application
### On localhost standalone
1. Run Docker (Docker Decktop for Win OS)
2. Run artemis container: docker run --detach --name mycontainer -p 61616:61616 -p 8161:8161 --rm apache/activemq-artemis:latest-alpine
3. Run project eurekareganddiscovery, check http://localhost:8761
4. Run [WantedApplication.java](src%2Fmain%2Fjava%2Forg%2Fdiecastfinder%2Fwanted%2FWantedApplication.java) .main().
5. Open main page of UI http://localhost:8082/wanted-models

### Standalone in Docker

### As a part of application in Docker Compose

## Persistent layer
H2 - in memory DB with devtools to have a h2-console
after running the service open http://localhost:port/h2-console

## Deployment: artifacts preparation
* Docker - to make a container out of service from DevOps point of view.<br>
To build docker image use command:
`C:\Projects\DiecastFinder2\wanted> docker build -f .\src\main\java\docker\Dockerfile -t diecastfinder-wanted .`
<br>To run build use command:
`C:\Projects\DiecastFinder2\wanted> docker run -p 8081:8081 diecastfinder-wanted`


* Spring Boot docker Layers - to optimize consuming of space by sharing common parts of software between services and versions.
  https://springframework.guru/why-you-should-be-using-spring-boot-docker-layers/
  * `mvn clean package` -> fat jar 
  
  Manual creation of layers. Just to test the command.
  * `java -Djarmode=tools -jar target/wanted-0.0.1-SNAPSHOT.jar list-layers` -> printed list
  * `java -Djarmode=tools -jar target/wanted-0.0.1-SNAPSHOT.jar extract --layers --launcher` -> folder with layers
  
  Run docker file and let docker do all job.
  * `--no-cache` - cache is turned off,<br>
    `-D --progress=plain` - print stdout to console<br>
    `C:\Projects\DiecastFinder2\wanted> docker -D build -f .\src\main\docker\Dockerfile -t diecastfinder-wanted --progress=plain --no-cache .`
   

* Run Docker from Maven and use properties - in order not to update dockerfile every time as you change version, same docker file for every project.<br>

Plugin docker-maven-plugin from Fabric8 add docker jobs to mvn.<br>
To build docker image from mvn: `mvn docker:build`

## Deployment: docker hub import
* Create public repository on dockerhub and push image<br>
`docker tag local-image:tagname new-repo:tagname`<br>
`docker push new-repo:tagname` 

## Log monitoring: ELK (Filebeat, Elasticsearch, Kibana)
#### Source
`src/main/docker/elk` have everything for running and configure elk logging<br>
#### Setup 
Setup is based on docker compose, in order to run elk do the following:<br>
* cd to elk folder
* run `docker-compose -f .\docker-compose-elk.yaml up -d`
* in browser open http://localhost:5601/ (localhost server we provided for Kibana)<br> 
and http://localhost:9200/ (Elasticsearch host server) to check if it's working fine.
#### Add another microservice logs and test results
* open `docker-compose-elk.yaml` and add paths to `filebeat:volumes:`
