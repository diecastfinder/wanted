FROM openjdk:17-slim as builder

WORKDIR ${project.artifactId}
ADD maven/${project.build.finalName}.jar ./
RUN java -Djarmode=tools -jar ${project.build.finalName}.jar extract --layers --launcher

FROM openjdk:17-slim
WORKDIR ${project.artifactId}
COPY --from=builder ${project.artifactId}/${project.build.finalName}/dependencies/ ./
COPY --from=builder ${project.artifactId}/${project.build.finalName}/spring-boot-loader/ ./
COPY --from=builder ${project.artifactId}/${project.build.finalName}/snapshot-dependencies/ ./
COPY --from=builder ${project.artifactId}/${project.build.finalName}/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
