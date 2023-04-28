
FROM amazoncorretto:17-alpine-jdk

EXPOSE 8080

COPY  target/API-0.0.1-SNAPSHOT app.jar

ENTRYPOINT ["java",".jar","/app.jar"]
