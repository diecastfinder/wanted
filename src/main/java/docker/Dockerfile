FROM openjdk:17-slim

ENV JAVA_OPTS " -Xms512m -Xmx512m -Djava.security.edg=file:/dev/./urandom"

WORKDIR application

COPY ../../../../target/wanted-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["java", "-jar", "wanted-0.0.1-SNAPSHOT.jar"]