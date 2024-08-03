# Wanted

The project that helps to manage list of desired models for collection.

### Persistent layer
H2 - in memory DB with devtools to have a h2-console
after running the service open http://localhost:port/h2-console

### Deployment: artifacts preparation
* Docker - to make a container out of service from DevOps point of view.
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
    `C:\Projects\DiecastFinder2\wanted> docker -D build -f .\src\main\java\docker\Dockerfile -t diecastfinder-wanted --progress=plain --no-cache .`

  
* Docker is using Maven properties