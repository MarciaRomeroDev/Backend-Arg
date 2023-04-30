
FROM amazoncorretto:17-alpine-jdk

EXPOSE 8080

COPY target/API-0.0.1-SNAPSHOT.jar app.jar


ENTRYPOINT ["java","-jar","/app.jar"]
